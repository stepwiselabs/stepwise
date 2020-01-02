package org.stepwiselabs.db;

import dagger.Component;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;

/**
 * persistent identifiable object
 */
@Singleton
@Component(modules = DBModule)
public interface DBComponent {

    public Jdbi getJdbi();
}
