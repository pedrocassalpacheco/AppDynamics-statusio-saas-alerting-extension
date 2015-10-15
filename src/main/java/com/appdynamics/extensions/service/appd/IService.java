package com.appdynamics.extensions.service.appd;


import com.appdynamics.appdrestapi.data.MetricDatas;
import com.appdynamics.appdrestapi.data.MetricValue;
import com.appdynamics.extensions.service.appd.app.Application;
import com.appdynamics.extensions.service.appd.events.Event;
import com.appdynamics.extensions.service.appd.hrv.PolicyViolation;

import java.util.List;

/**
 * Interface for AppD Controller Rest APIs
 */
public interface IService {

    List<Application> getApplications(ServiceBuilder serviceBuilder,String endpoint) throws ServiceException;
    List<PolicyViolation> getHealthRuleViolations(ServiceBuilder serviceBuilder,String endpoint) throws ServiceException;
    List<Event>  getEvents(ServiceBuilder serviceBuilder,String endpoint) throws ServiceException;
    MetricDatas getMetrics(ServiceBuilder serviceBuilder, String endpoint) throws ServiceException;
}
