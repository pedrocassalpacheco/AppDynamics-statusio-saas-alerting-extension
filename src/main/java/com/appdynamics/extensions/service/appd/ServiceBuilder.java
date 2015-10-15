package com.appdynamics.extensions.service.appd;


import com.appdynamics.TaskInputArgs;
import com.appdynamics.extensions.StatusioAlertExtension;
import com.appdynamics.extensions.http.SimpleHttpClient;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class ServiceBuilder {
    private boolean isSSLEnabled;
    private String userAccount;
    private String password;
    private int connectTimeout;
    private int socketTimeout;
    
    private static final Logger logger = Logger.getLogger(ServiceBuilder.class);
    
    public ServiceBuilder(boolean isSSLEnabled,String userAccount,String password,int connectTimeout,int socketTimeout){
        this.isSSLEnabled = isSSLEnabled;
        this.userAccount = userAccount;
        this.password = password;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    public SimpleHttpClient buildHttpClient(Class clazz) {
        Map<String, String> httpConfigMap = createHttpConfigMap();
        SimpleHttpClient simpleHttpClient = SimpleHttpClient.builder(httpConfigMap)
                .connectionTimeout(connectTimeout)
                .socketTimeout(socketTimeout)
                .jaxbClasses(clazz)
                .build();
        return simpleHttpClient;
    }

    private Map<String, String> createHttpConfigMap() {
        Map<String,String> map = new HashMap<String, String>();
        map.put(TaskInputArgs.USER,userAccount);
        map.put(TaskInputArgs.PASSWORD,password);
        
        logger.debug("Setting " + TaskInputArgs.USER + " to " + userAccount);
        logger.debug("Setting " + TaskInputArgs.PASSWORD + " to " + password);
        
        if(isSSLEnabled) {
            map.put(TaskInputArgs.USE_SSL, "true");
        }
    
        return map;
    }

    public boolean isSSLEnabled() {
        return isSSLEnabled;
    }
}
