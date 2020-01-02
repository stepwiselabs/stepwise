package org.stepwiselabs.db.definition;

import org.stepwiselabs.db.Entity;

import java.util.Set;

/**
 * Defines an action that is executed in context of a workflow state, usually for transitioning the workflow
 */
public class Action extends Entity {

    private Set<ConditionDefinition> conditionDefinitions;
}
