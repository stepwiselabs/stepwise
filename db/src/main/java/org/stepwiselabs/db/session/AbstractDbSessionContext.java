package org.stepwiselabs.db.session;

import org.jdbi.v3.core.Handle;

public abstract class AbstractDbSessionContext {
    protected Handle handle;

    public AbstractDbSessionContext(Handle handle){
        this.handle = handle;
    }

}

