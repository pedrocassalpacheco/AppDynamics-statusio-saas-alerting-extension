package com.appdynamics.extensions.service.appd;

import com.appdynamics.appdrestapi.data.MetricDatas;
import com.appdynamics.appdrestapi.data.MetricValue;
import com.appdynamics.extensions.http.Response;
import com.appdynamics.extensions.http.SimpleHttpClient;
import com.appdynamics.extensions.http.WebTarget;

import com.appdynamics.extensions.service.appd.app.Application;
import com.appdynamics.extensions.service.appd.app.ApplicationWrapper;
import com.appdynamics.extensions.service.appd.events.Event;
import com.appdynamics.extensions.service.appd.events.EventWrapper;
import com.appdynamics.extensions.service.appd.hrv.PolicyViolation;
import com.appdynamics.extensions.service.appd.hrv.PolicyViolationWrapper;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements IService {

    private static final Logger logger = Logger.getLogger(ServiceImpl.class);

    @Override
    public List<Application> getApplications(ServiceBuilder serviceBuilder, String endpoint) throws ServiceException {
        logger.debug("getApplications :: building http client");
        try {
            SimpleHttpClient simpleHttpClient = serviceBuilder.buildHttpClient(ApplicationWrapper.class);
            logger.debug("getApplications :: target url " + endpoint);
            Response response = simpleHttpClient.target(endpoint).get();
            ApplicationWrapper applicationWrapper = null;
            if (response != null && response.getStatus() == HttpURLConnection.HTTP_OK) {
                applicationWrapper = response.xml(ApplicationWrapper.class);
                if (applicationWrapper != null && applicationWrapper.getApplications() != null) {
                    logger.debug("getApplications :: returning successfully");
                    return applicationWrapper.getApplications();
                }
            }
        } catch (Exception e) {
            String msg = "getApplications :: unable to get applications for " + endpoint;
            logger.error(msg, e);
            throw new ServiceException(msg, e);
        }
        return Lists.newArrayList();
    }

    @Override
    public List<PolicyViolation> getHealthRuleViolations(ServiceBuilder serviceBuilder, String endpoint) throws ServiceException {
        logger.debug("getHealthRuleViolations :: building http client");
        try {
            SimpleHttpClient simpleHttpClient = serviceBuilder.buildHttpClient(PolicyViolationWrapper.class);
            logger.debug("getHealthRuleViolations :: target url" + endpoint);
            Response response = simpleHttpClient.target(endpoint).get();
            PolicyViolationWrapper violationWrapper = null;
            if (response != null && response.getStatus() == HttpURLConnection.HTTP_OK) {
                violationWrapper = response.xml(PolicyViolationWrapper.class);
                if (violationWrapper != null && violationWrapper.getPolicyViolations() != null) {
                    logger.debug("getHealthRuleViolations :: returning successfully");
                    return violationWrapper.getPolicyViolations();
                }
            }
        } catch (Exception e) {
            String msg = "getHealthRuleViolations :: unable to get applications for " + endpoint;
            logger.error(msg, e);
            throw new ServiceException(msg, e);
        }
        return Lists.newArrayList();
    }

    @Override
    public List<Event> getEvents(ServiceBuilder serviceBuilder, String endpoint) throws ServiceException {
        logger.debug("getEvents :: building http client");
        try {
            SimpleHttpClient simpleHttpClient = serviceBuilder.buildHttpClient(EventWrapper.class);
            logger.debug("getEvents :: target url " + endpoint);
            Response response = simpleHttpClient.target(endpoint).get();
            EventWrapper eventWrapper = null;
            if (response != null && response.getStatus() == HttpURLConnection.HTTP_OK) {
                eventWrapper = response.xml(EventWrapper.class);
                if (eventWrapper != null && eventWrapper.getEvents() != null) {
                    logger.debug("getEvents :: returning successfully");
                    return eventWrapper.getEvents();
                }
            }
        } catch (Exception e) {
            String msg = "getEvents :: unable to get applications for " + endpoint;
            logger.error(msg, e);
            throw new ServiceException(msg, e);
        }
        return Lists.newArrayList();
    }

    /**
     *
     * @param serviceBuilder
     * @param endpoint
     * @return
     * @throws ServiceException
     */
    @Override
    public MetricDatas getMetrics(ServiceBuilder serviceBuilder, String endpoint) throws ServiceException {
        logger.debug("getMetrics :: building http client");
        try {

            SimpleHttpClient simpleHttpClient = serviceBuilder.buildHttpClient(MetricDatas.class);
            logger.debug("getMetrics :: target url " + endpoint);

            WebTarget target = simpleHttpClient.target(endpoint);
            Response response = simpleHttpClient.target(endpoint).get();
            MetricDatas metricDatas = null;
           
            if (response != null && response.getStatus() == HttpURLConnection.HTTP_OK) {
                metricDatas = response.xml(MetricDatas.class);
                if (metricDatas != null && metricDatas.getMetric_data() != null) {
                    logger.debug("getMetrics :: returning successfully " + metricDatas.toString());
                    return metricDatas;
                }
            }
        } catch (Exception e) {
            String msg = "getMetrics :: unable to get metrics for " + endpoint;
            logger.error(msg, e);
            throw new ServiceException(msg, e);
        }
        
        // Returns empty collection to keep framework code from breaking
        return new MetricDatas();
    }

}
