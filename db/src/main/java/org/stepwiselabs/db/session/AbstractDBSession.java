package org.stepwiselabs.db.session;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.transaction.TransactionIsolationLevel;

import javax.inject.Inject;


public abstract class AbstractDBSession<C extends AbstractDbSessionContext> {

    private final Jdbi jdbi;

    @Inject
    public AbstractDBSession(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    protected abstract C createContext(Handle handle);

    public <R, X extends Exception> R inExisting(final AbstractDbSessionContext serviceContext, final ContextCallback<R, C, X> callback) throws X {
        C context = createContext(serviceContext.handle);
        return callback.apply(context);
    }

    public <R, X extends Exception> R inSerializable(final ContextCallback<R, C, X> callback) throws X {
        return inTransaction(TransactionIsolationLevel.SERIALIZABLE, callback);
    }

    public <R, X extends Exception> R inRepeatableRead(final ContextCallback<R, C, X> callback) throws X {
        return inTransaction(TransactionIsolationLevel.REPEATABLE_READ, callback);
    }

    public <R, X extends Exception> R inReadCommitted(final ContextCallback<R, C, X> callback) throws X {
        return inTransaction(TransactionIsolationLevel.READ_COMMITTED, callback);
    }

    private <R, X extends Exception> R inTransaction(TransactionIsolationLevel level, final ContextCallback<R, C, X> callback) throws X {
        return jdbi.inTransaction(level, handle -> {
            C context = createContext(handle);
            return callback.apply(context);
        });
    }

    public <X extends Exception> void useExisting(final AbstractDbSessionContext serviceContext, final ContextConsumer<C, X> consumer) throws X {
        C context = createContext(serviceContext.handle);
        consumer.apply(context);
    }

    public <X extends Exception> void useSerializable(final ContextConsumer<C, X> consumer) throws X {
        useTransaction(TransactionIsolationLevel.SERIALIZABLE, consumer);
    }

    public <X extends Exception> void useRepeatableRead(final ContextConsumer<C, X> consumer) throws X {
        useTransaction(TransactionIsolationLevel.REPEATABLE_READ, consumer);
    }

    public <X extends Exception> void useReadCommitted(final ContextConsumer<C, X> consumer) throws X {
        useTransaction(TransactionIsolationLevel.READ_COMMITTED, consumer);
    }

    private <X extends Exception> void useTransaction(TransactionIsolationLevel level, final ContextConsumer<C, X> consumer) throws X {
        jdbi.useTransaction(level, handle -> {
            C context = createContext(handle);
            consumer.apply(context);
        });
    }

    @FunctionalInterface
    public interface ContextCallback<T, C, X extends Exception> {
        T apply(C context) throws X;
    }

    @FunctionalInterface
    public interface ContextConsumer<C, X extends Exception> {
        void apply(C context) throws X;
    }
}
