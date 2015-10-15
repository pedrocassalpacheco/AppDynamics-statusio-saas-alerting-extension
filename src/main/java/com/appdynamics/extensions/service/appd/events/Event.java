package com.appdynamics.extensions.service.appd.events;


public class Event {

    private String id;
    private String type;
    private long eventTime;
    private String severity;
    private AffectedEntities affectedEntities;
    private String deepLinkUrl;
    private String summary;
    private boolean markedAsRead;
    private boolean markedAsResolved;
    private boolean archived;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }


    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public AffectedEntities getAffectedEntities() {
        return affectedEntities;
    }

    public void setAffectedEntities(AffectedEntities affectedEntities) {
        this.affectedEntities = affectedEntities;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(String deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isMarkedAsRead() {
        return markedAsRead;
    }

    public void setMarkedAsRead(boolean markedAsRead) {
        this.markedAsRead = markedAsRead;
    }

    public boolean isMarkedAsResolved() {
        return markedAsResolved;
    }

    public void setMarkedAsResolved(boolean markedAsResolved) {
        this.markedAsResolved = markedAsResolved;
    }
}
