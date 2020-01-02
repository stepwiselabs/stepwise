package org.stepwiselabs.db.definition;

import org.stepwiselabs.db.Entity;
import org.stepwiselabs.db.instance.StateStack;

import java.util.Set;

public class WorkflowDefinition extends Entity {

    private Set<StateDefinition> stateDefinitions;
    private Set<Transition> transitions;
    private StateStack currentState;


}
