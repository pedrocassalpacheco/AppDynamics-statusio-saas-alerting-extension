package com.appdynamics.extensions.service.appd.events;


import com.appdynamics.extensions.service.appd.EntityDefinition;

import java.util.List;

public class AffectedEntities {

    List<EntityDefinition> entityDefinitions;

    public List<EntityDefinition> getEntityDefinitions() {
        return entityDefinitions;
    }

    public void setEntityDefinitions(List<EntityDefinition> entityDefinitions) {
        this.entityDefinitions = entityDefinitions;
    }
}
