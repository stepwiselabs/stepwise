package org.stepwiselabs.db.definition;

import org.stepwiselabs.db.Entity;

import java.util.Collections;
import java.util.Set;

/**
 * s1 -[t1]-> s2
 */
public class StateDefinition extends Entity {

    private Set<Action> actions;

    public StateDefinition(Set<Action> actions) {
        this.actions = Collections.unmodifiableSet(actions);
    }

    public Set<Action> getActions() {
        return actions;
    }


}
