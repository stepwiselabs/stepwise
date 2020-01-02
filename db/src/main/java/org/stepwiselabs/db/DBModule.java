package org.stepwiselabs.db;

import dagger.Module;
import dagger.Provides;
import org.jdbi.v3.core.Jdbi;

/**
 * persistent identifiable object
 */
@Module
public class DBModule {

    @Provides
    public Jdbi provideJdbi(){

    }
}
