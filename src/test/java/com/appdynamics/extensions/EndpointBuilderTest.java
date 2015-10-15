package com.appdynamics.extensions;


import com.appdynamics.extensions.config.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class EndpointBuilderTest {

    public static final String HRV_URL = "https://adppe.saas.appdynamics.com/controller/rest/applications/1/problems/healthrule-violations?time-range-type=BEFORE_NOW&duration-in-mins=1&output=XML";
    EndpointBuilder builder = new EndpointBuilder();


    @Test
    public void testHealthRuleViolationsEndpoint(){
        Configuration baseConfig = createBaseConfig();
        String url = builder.buildHealthRulesViolationEndpoint(baseConfig,1);
        //Assert.assertTrue(HRV_URL.equals(url));
    }



    private Configuration createBaseConfig() {
        Configuration baseConfig = new Configuration();
        baseConfig.setApplicationsUrlPath("/controller/rest/applications");
        baseConfig.setSaasHost("adppe.saas.appdynamics.com");
        baseConfig.setHealthRuleViolationsUrlPath("/controller/rest/applications/<#APP_ID#>/problems/healthrule-violations?time-range-type=BEFORE_NOW&duration-in-mins=<#POLLING_FREQ_IN_MINS#>&output=XML");
        baseConfig.setEventsUrlPath("/controller/rest/applications/<#APP_ID#>/events?time-range-type=BEFORE_NOW&duration-in-mins=<#POLLING_FREQ_IN_MINS#>&event-types=APPLICATION_ERROR,DIAGNOSTIC_SESSION&severities=ERROR&output=XML");
        baseConfig.setDurationInMins(1);
        return baseConfig;
    }

}
