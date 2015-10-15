package com.appdynamics.extensions.service.statusio;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Alert {

    @JsonProperty("statuspage_id")
    private String statuspageId;

    @JsonProperty("all_infrastructure_affected")
    private String allInfrastructureAffected;

    @JsonProperty("components")
    private List<String> components;

    @JsonProperty("containers")
    private List<String>containers;

    @JsonProperty("incident_name")
    private String incidentName;
    
    @JsonProperty("incident_details")
    private String description;

    @JsonProperty("current_status")
    private Integer currentStatus;

    @JsonProperty("current_state")
    private Integer currentState;

    @JsonProperty("notify_email")
    private String notifyEmail;
    
    @JsonProperty("notify_sms")
    private String notifySMS;

    @JsonProperty("notify_webhook")
    private String notifyWebhook;

    @JsonProperty("social")
    private String social;

    @JsonProperty("irc")
    private String irc;
    
    @JsonProperty("hipchat")
    private String hipchat;
    
    @JsonProperty("slack")
    private String slack;

    public String getStatuspageId() {
        return statuspageId;
    }

    public void setStatuspageId(String statuspageId) {
        this.statuspageId = statuspageId;
    }

    public String getAllInfrastructureAffected() {
        return allInfrastructureAffected;
    }

    public void setAllInfrastructureAffected(String allInfrastructureAffected) {
        this.allInfrastructureAffected = allInfrastructureAffected;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public List<String> getContainers() {
        return containers;
    }

    public void setContainers(List<String> containers) {
        this.containers = containers;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getNotifySMS() {
        return notifySMS;
    }

    public void setNotifySMS(String notifySMS) {
        this.notifySMS = notifySMS;
    }

    public String getNotifyWebhook() {
        return notifyWebhook;
    }

    public void setNotifyWebhook(String notifyWebhook) {
        this.notifyWebhook = notifyWebhook;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getIrc() {
        return irc;
    }

    public void setIrc(String irc) {
        this.irc = irc;
    }

    public String getHipchat() {
        return hipchat;
    }

    public void setHipchat(String hipchat) {
        this.hipchat = hipchat;
    }

    public String getSlack() {
        return slack;
    }

    public void setSlack(String slack) {
        this.slack = slack;
    }
      
}
