package com.appdynamics.extensions.config.customer;

import java.util.List;

public class StatusioConfig {

    private String host;
    private String statusPageID;
    private String appID;
    private String apiKey;
    private List<String> container;
    private List<String> component;
    private List<String> metricMapping;
    private String protocol;
    private String incidentPath;
    private int connectTimeout;
    private int socketTimeout;
    private boolean showDetails;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStatusPageID() {
        return statusPageID;
    }

    public void setStatusPageID(String statusPageID) {
        this.statusPageID = statusPageID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<String> getContainer() {
        return container;
    }

    public void setContainer(List<String> container) {
        this.container = container;
    }

    public List<String> getComponent() {
        return component;
    }

    public void setComponent(List<String> component) {
        this.component = component;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getIncidentPath() {
        return incidentPath;
    }

    public void setIncidentPath(String incidentPath) {
        this.incidentPath = incidentPath;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isShowDetails() {
        return showDetails;
    }

    public void setShowDetails(boolean showDetails) {
        this.showDetails = showDetails;
    }

    public List<String> getMetricMapping() {
        return metricMapping;
    }

    public void setMetricMapping(List<String> metricMapping) {
        this.metricMapping = metricMapping;
    }
}
