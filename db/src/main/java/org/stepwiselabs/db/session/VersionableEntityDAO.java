package org.stepwiselabs.db.session;

import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


        import org.jdbi.v3.core.Handle;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Stream;

/**
 * A DAO dealing with entities that are keyed off of their {@code name} and have {@code version}
 * and {@code current_version} fields.
 *
 * @param <T>
 */
public class VersionableEntityDAO<T> {

    private final Class<T> entityClass;
    private final Handle handle;
    private final String tableName;

    public VersionableEntityDAO(Handle handle, Class<T> entityClass, String tableName) {
        this.handle = handle;
        this.entityClass = entityClass;
        this.tableName = tableName;
    }

    /**
     * Returns {@code true} if an entity exists with the given {@literal name} and {@literal version}.
     *
     * @param name
     * @param version
     */
    public boolean versionExists(String name, int version) {
        return handle.createQuery(String.format("select count(*) from %s where name = :name AND version = :version", tableName))
                .bind("name", name)
                .bind("version", version)
                .mapTo(Integer.class)
                .findOnly() > 0;
    }

    /**
     * Set the current version of the entity corresponding to the given {@literal name} and {@literal version}.
     *
     * @param name
     * @param version
     */
    public void setCurrentVersion(String name, int version) {

        handle.createUpdate(String.format("update %s set current_version = :current_version where name = :name", tableName))
                .bind("name", name)
                .bind("current_version", version)
                .execute();
    }

    /**
     * Return the current version that exists for the entity name.
     *
     * @param name
     * @return {@code Optional.empty()} if no versions exist, otherwise the max version found.
     */
    private Optional<Integer> getCurrentVersion(String name) {

        return handle.createQuery(String.format("select version from %s where name = :name and version = current_version", tableName))
                .bind("name", name)
                .mapTo(Integer.class)
                .findFirst();
    }

    /**
     * Returns the current version of the subject associated with this entity.
     *
     * @param name
     * @return
     */
    public Optional<T> getCurrent(String name) {

        // The last entity to be set as latest version has it's version equal to it's current_version column
        return handle.createQuery(String.format("select * from %s where name = :name and version = current_version", tableName))
                .bind("name", name)
                .mapTo(entityClass)
                .findFirst();
    }

    /**
     * Returns the specific {@literal version} of the entity with the given {@literal name}.
     *
     * @param name
     * @param version
     * @return
     */
    public Optional<T> getVersion(String name, int version) {
        return handle.createQuery(String.format("select * from %s where name = :name and version = :version", tableName))
                .bind("name", name)
                .bind("version", version)
                .mapTo(entityClass)
                .findFirst();
    }


    /**
     * Removes the specific version of the entity corresponding to the given {@literal name} and {@literal version}.
     * If the {@literal version} is the current then it will be set the the version before. If there is
     * no version before then the current version will be set to the version after. If there is only one version then it will
     * just be deleted.
     *
     * @param name
     * @param version
     * @return {@code true} if the specified version was removed
     */
    public boolean removeVersion(String name, int version) {
        // if the version is the current then we will have to bump it down before deleting it
        Optional<Integer> optCurrentVersion = getCurrentVersion(name);
        if ( !optCurrentVersion.isPresent() ){
            // entity with name does not exist
            return false;
        }

        // check if the latest version is getting deleted
        int currentVersion = optCurrentVersion.get();
        if( currentVersion == version ){
            // if so set the next version to the one before the current
            List<Integer> versionNumbers = getAllVersionNumbers(name);
            if ( versionNumbers.size() > 1) {
                int nextVersion = 0;
                for (int n = 0; n < versionNumbers.size(); n++) {
                    int v = versionNumbers.get(n);
                    if (v == currentVersion) {

                        // if there are not any lower versions
                        if (n == versionNumbers.size() - 1) {
                            nextVersion = versionNumbers.get(n - 1);
                        } else {
                            nextVersion = versionNumbers.get(n + 1);
                        }
                        break;
                    }
                }

                setCurrentVersion(name, nextVersion);
            }
        }

        // go ahead and delete the entity
        return handle.createUpdate(String.format("delete from %s where name = :name and version = :version", tableName))
                .bind("name", name)
                .bind("version", version)
                .execute() == 1;
    }

    /**
     * Returns all the entity names.
     *
     * @return a {@link List} of entity names
     */
    public List<String> getAllEntityNames() {
        return handle.createQuery(String.format("select distinct name from %s order by name", tableName))
                .mapTo(String.class)
                .list();
    }

    /**
     * Returns all entities at their latest version
     *
     * @return
     */
    public List<T> getAllLatest() {
        return handle.createQuery(String.format("select * from %s where version = current_version", tableName))
                .mapTo(entityClass)
                .list();
    }


    /**
     * Gets all the versions of a specific entity
     *
     * @param name
     * @return
     */
    public List<T> getAllVersions(String name) {
        return handle.createQuery(String.format("select * from %s where name = :name order by version desc", tableName))
                .bind("name", name)
                .mapTo(entityClass)
                .list();
    }

    /**
     * Gets all the version numbers of a specific entity
     *
     * @param name
     * @return
     */
    public List<Integer> getAllVersionNumbers(String name) {
        return handle.createQuery(String.format("select version from %s where name = :name order by version desc", tableName))
                .bind("name", name)
                .mapTo(Integer.class)
                .list();
    }

    /**
     * Returns all the versions of all the entities
     *
     * @return a {@link Stream} of entities.
     */
    public Stream<T> getAllRecords() {
        return handle.createQuery(String.format("select * from %s order by name, version", tableName))
                .mapTo(entityClass)
                .stream();
    }


    /**
     * returns the number of persisted entity records.
     *
     * @return int
     */
    public int getCount() {
        try {
            return handle.createQuery(String.format("select count(distinct name) as num_entities FROM %s", tableName))
                    .mapTo(Integer.class)
                    .findOnly();

        } catch (Throwable e) {
            throw new DatabaseException(e, "Encountered %s while querying the number of %s entities : %s",
                    e.getClass().getSimpleName(), entityClass.getSimpleName(), e.getMessage());
        }
    }
}
