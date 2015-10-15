package com.appdynamics.extensions.service.appd.hrv;

import com.appdynamics.extensions.service.appd.EntityDefinition;

public class PolicyViolation {
    private String id;
    private String name;
    private long startTimeInMillis;
    private long detectedTimeInMillis;
    private long endTimeInMillis;
    private String incidentStatus;
    private String severity;
    private EntityDefinition triggeredEntityDefinition;
    private EntityDefinition affectedEntityDefinition;
    private String deepLinkUrl;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    public void setStartTimeInMillis(long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }

    public long getDetectedTimeInMillis() {
        return detectedTimeInMillis;
    }

    public void setDetectedTimeInMillis(long detectedTimeInMillis) {
        this.detectedTimeInMillis = detectedTimeInMillis;
    }

    public long getEndTimeInMillis() {
        return endTimeInMillis;
    }

    public void setEndTimeInMillis(long endTimeInMillis) {
        this.endTimeInMillis = endTimeInMillis;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public EntityDefinition getTriggeredEntityDefinition() {
        return triggeredEntityDefinition;
    }

    public void setTriggeredEntityDefinition(EntityDefinition triggeredEntityDefinition) {
        this.triggeredEntityDefinition = triggeredEntityDefinition;
    }

    public EntityDefinition getAffectedEntityDefinition() {
        return affectedEntityDefinition;
    }

    public void setAffectedEntityDefinition(EntityDefinition affectedEntityDefinition) {
        this.affectedEntityDefinition = affectedEntityDefinition;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(String deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
