package com.appdynamics.extensions.service.statusio;

import com.appdynamics.extensions.alerts.customevents.*;
import com.appdynamics.extensions.config.customer.StatusioConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 * Builds an Alert from Health Rule violation event.
 */

public class AlertBuilder {

    public static final String DASH_SEPARATOR = "-";
    public static final String SLASH_SEPARATOR = "/";
    public static final String APP_DYNAMICS = "AppDynamics";
    public static final String DASH = "-";
    public static final String POLICY_CLOSE = "POLICY_CLOSE";
    public static final String RESOLVE = "resolve";
    public static final String TRIGGER = "trigger";
    private static Logger logger = Logger.getLogger(AlertBuilder.class);

    public Alert buildAlertFromHealthRuleViolationEvent(HealthRuleViolationEvent violationEvent, StatusioConfig config) {
        if(violationEvent != null) {
            Alert alert = new Alert();
            alert.setStatuspageId(config.getStatusPageID());
            alert.setAllInfrastructureAffected("1");
            alert.setComponents(config.getComponent());
            alert.setContainers(config.getContainer());
            alert.setCurrentState(100);
            alert.setCurrentStatus(100);
            alert.setIncidentName(violationEvent.getAppName());
            alert.setDescription(getDescription(violationEvent));
            alert.setHipchat("0");
            alert.setIrc("0");
            alert.setNotifyEmail("0");
            alert.setSocial("0");
            alert.setSlack("0");
            alert.setNotifySMS("0");
            alert.setNotifyWebhook("0");
            return alert;
        }
        return null;
    }

    private void setSeverity(String severity, Event event) {
        if (severity.equalsIgnoreCase("WARN")) {
            event.setSeverity("WARNING");
        } else if(severity.equalsIgnoreCase("INFO")) {
            event.setSeverity("INFORMATION");
        } else if (severity.equalsIgnoreCase("ERROR")) {
            event.setSeverity("CRITICAL");
        }
    }

    private String getEventType(String eventType) {
        if (eventType != null && eventType.equalsIgnoreCase(POLICY_CLOSE)) {
            return RESOLVE;
        }
        return TRIGGER;
    }

    public Alert buildAlertFromOtherEvent(OtherEvent otherEvent, String serviceKey) {
        if (otherEvent != null) {
            Alert alert = new Alert();
            setSeverity(otherEvent.getSeverity(),otherEvent);
            /*alert.setServiceKey(serviceKey);
            alert.setIncidentKey(getIncidentKey(otherEvent));
            alert.setEventType(TRIGGER);
            alert.setDetails(getSummary(otherEvent, Boolean.valueOf(true)));
            alert.setDescription(getDescription(otherEvent));
            */
            return alert;
        }
        return null;
    }

    private String getDescription(OtherEvent otherEvent) {
        return "Event : " + otherEvent.getEventNotificationName() + " Severity: " + otherEvent.getSeverity();
    }

    private String getDescription(HealthRuleViolationEvent violationEvent) {
        return "Health Rule: " + violationEvent.getHealthRuleName() + " Severity: " + violationEvent.getSeverity();
    }

    private String getIncidentKey(HealthRuleViolationEvent violationEvent) {
        return violationEvent.getAppID() + DASH + violationEvent.getAffectedEntityID() + DASH + violationEvent.getHealthRuleID();
    }

    private String getIncidentKey(OtherEvent otherEvent) {
        return otherEvent.getAppID() + DASH + otherEvent.getEventNotificationId();
    }

    public String convertIntoJsonString(Alert alert) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(alert);
    }

    private AlertDetails getSummary(HealthRuleViolationEvent violationEvent,boolean showDetails) {
        AlertHeatlhRuleVioEventDetails details = new AlertHeatlhRuleVioEventDetails();
        details.setApplicationId(violationEvent.getAppID());
        details.setApplicationName(violationEvent.getAppName());
        details.setPolicyViolationAlertTime(violationEvent.getPvnAlertTime());
        details.setSeverity(violationEvent.getSeverity());
        details.setPriority(violationEvent.getPriority());
        details.setHealthRuleName(violationEvent.getHealthRuleName());
        details.setAffectedEntityType(violationEvent.getAffectedEntityType());
        details.setAffectedEntityName(violationEvent.getAffectedEntityName());
        details.setIncidentId(violationEvent.getIncidentID());
        details.setDeepLinkUrl(violationEvent.getDeepLinkUrl());
        if(showDetails) {
            for (EvaluationEntity eval : violationEvent.getEvaluationEntity()) {
                AlertEvaluationEntity alertEval = buildAlertEvalutionEntity(eval);
                details.getEvaluationEntities().add(alertEval);
            }
        }
        return details;
    }

    private AlertDetails getSummary(OtherEvent otherEvent,boolean showDetails) {
        AlertOtherEventDetails details = new AlertOtherEventDetails();
        details.setApplicationId(otherEvent.getAppID());
        details.setApplicationName(otherEvent.getAppName());
        details.setEventNotificationIntervalInMins(otherEvent.getEventNotificationIntervalInMin());
        details.setSeverity(otherEvent.getSeverity());
        details.setPriority(otherEvent.getPriority());
        details.setEventNotificationName(otherEvent.getEventNotificationName());
        details.setEventNotificationId(otherEvent.getEventNotificationId());
        for(EventType eventType : otherEvent.getEventTypes()){
            AlertEventType alertEventType = new AlertEventType();
            alertEventType.setEventType(eventType.getEventType());
            alertEventType.setEventTypeNum(eventType.getEventTypeNum());
            details.getEventTypes().add(alertEventType);
        }
        if(showDetails) {
            for (EventSummary eventSummary : otherEvent.getEventSummaries()) {
                AlertEventSummary alertSummary = new AlertEventSummary();
                alertSummary.setEventSummaryId(eventSummary.getEventSummaryId());
                alertSummary.setEventSummaryTime(eventSummary.getEventSummaryTime());
                alertSummary.setEventSummaryType(eventSummary.getEventSummaryType());
                alertSummary.setEventSummarySeverity(eventSummary.getEventSummarySeverity());
                alertSummary.setEventSummaryString(eventSummary.getEventSummaryString());
                alertSummary.setEventSummaryDeepLinkUrl(otherEvent.getDeepLinkUrl() + alertSummary.getEventSummaryId());
                details.getEventSummaries().add(alertSummary);
            }
        }
        return details;
    }

    private AlertEvaluationEntity buildAlertEvalutionEntity(EvaluationEntity eval) {
        AlertEvaluationEntity alertEval = new AlertEvaluationEntity();
        alertEval.setName(eval.getName());
        alertEval.setId(eval.getId());
        alertEval.setType(eval.getType());
        alertEval.setNumberOfTriggeredConditions(eval.getNumberOfTriggeredConditions());
        for(TriggerCondition tc : eval.getTriggeredConditions()){
            AlertTriggeredCondition alertTrigger =  buildAlertTriggeredConditions(tc);
            alertEval.getTriggeredConditions().add(alertTrigger);
        }
        return alertEval;
    }

    private AlertTriggeredCondition buildAlertTriggeredConditions(TriggerCondition tc) {
        AlertTriggeredCondition alertTrigger = new AlertTriggeredCondition();
        alertTrigger.setScopeName(tc.getScopeName());
        alertTrigger.setScopeId(tc.getScopeId());
        alertTrigger.setScopeType(tc.getScopeType());
        alertTrigger.setConditionName(tc.getConditionName());
        alertTrigger.setConditionUnitType(tc.getConditionUnitType());
        alertTrigger.setConditionId(tc.getConditionId());
        alertTrigger.setBaselineId(tc.getBaselineId());
        alertTrigger.setBaselineName(tc.getBaselineName());
        alertTrigger.setUseDefaultBaseline(tc.isUseDefaultBaseline());
        alertTrigger.setOperator(tc.getOperator());
        alertTrigger.setObservedValue(tc.getObservedValue());
        alertTrigger.setThresholdValue(tc.getThresholdValue());
        return alertTrigger;
    }
}
