package com.appdynamics.extensions;

import com.appdynamics.extensions.config.Configuration;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.log4j.Logger;

public class EndpointBuilder {

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";
    public static final String SEP="://";
    public static final String APP_ID_HOLDER = "<#APP_ID#>";
    public static final String POLLING_FREQ_IN_MINS_HOLDER = "<#POLLING_FREQ_IN_MINS#>";
    public static final String METRIC_PATH_PLACE_HOLDER = "<#METRIC_PATH#>";

    private static final Logger logger = Logger.getLogger(EndpointBuilder.class);
 
    
    public String buildApplicationsEndpoint(Configuration baseConfig) {
        StringBuffer sb = getHost(baseConfig).append(baseConfig.getApplicationsUrlPath());
        return sb.toString();
    }

    public String buildHealthRulesViolationEndpoint(Configuration baseConfig,int applicationId) {
        StringBuffer sb = getHost(baseConfig);
        sb.append(baseConfig.getHealthRuleViolationsUrlPath());
        return replacePlaceHolders(sb.toString(),applicationId,baseConfig.getDurationInMins());
    }

    public String buildEventsEndpoint(Configuration baseConfig,int applicationId) {
        StringBuffer sb = getHost(baseConfig);
        sb.append(baseConfig.getEventsUrlPath());
        return replacePlaceHolders(sb.toString(),applicationId,baseConfig.getDurationInMins());
    }
    
    public String buildMetricsEndpoint(Configuration baseConfig, int applicationId, String metricPath) throws UnsupportedEncodingException {
        StringBuffer sb = getHost(baseConfig);
        logger.debug("buildMetricsEndpoint :: as configured " + baseConfig.getMetricsUrlPath());
        
        sb.append(baseConfig.getMetricsUrlPath());
        return replacePlaceHolders(sb.toString(),applicationId,baseConfig.getDurationInMins(), metricPath);
    }

    private StringBuffer getHost(Configuration baseConfig) {
        StringBuffer sb = new StringBuffer();
        sb.append(baseConfig.getProtocol())
                .append(SEP)
                .append(baseConfig.getSaasHost());
        return sb;
    }

    private String replacePlaceHolders(String str, int applicationId, int delaySchedule) {
        return str.replace(APP_ID_HOLDER,Integer.toString(applicationId)).replace(POLLING_FREQ_IN_MINS_HOLDER,Integer.toString(delaySchedule));
    }
    
    private String replacePlaceHolders(String str, int applicationId, int delaySchedule, String metricPath) throws UnsupportedEncodingException {
        return replacePlaceHolders(str.replace(METRIC_PATH_PLACE_HOLDER, URLEncoder.encode(metricPath,"UTF-8")), applicationId, delaySchedule);
    }

}
