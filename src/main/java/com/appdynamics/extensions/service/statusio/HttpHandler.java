package com.appdynamics.extensions.service.statusio;

import com.appdynamics.extensions.config.Configuration;
import com.appdynamics.extensions.config.customer.StatusioConfig;
import com.appdynamics.extensions.http.Response;
import com.appdynamics.extensions.http.SimpleHttpClient;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class HttpHandler {

    public static final String HTTPS        = "https://";
    public static final String HTTP         = "http://";
    public static final String SEP          ="://";
    public static final String X_API_KEY    = "x-api-key";
    public static final String X_API_ID     = "x-api-id";

    private StatusioConfig config;
    private static Logger logger = Logger.getLogger(HttpHandler.class);

    public HttpHandler(StatusioConfig config){
        this.config = config;
    }

    /**
     * Posts the data on Statusio Endpoint.
     * @param data
     * @return
     */
    public Response postAlert(String data) {
        Map<String, String> httpConfigMap = createHttpConfigMap();
        logger.debug("Building the httpClient for Status.io post");
        SimpleHttpClient simpleHttpClient = SimpleHttpClient.builder(httpConfigMap)
                .connectionTimeout(config.getConnectTimeout() * 1000)
                .socketTimeout(config.getSocketTimeout() * 1000)
                .build();
        String targetUrl = buildTargetUrl();
        logger.debug("Posting data to Status.io at " + targetUrl);
        Response response = simpleHttpClient.target(targetUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHandler.X_API_ID, config.getAppID())
                .header(HttpHandler.X_API_KEY, config.getApiKey())
                .post(data); 
        logger.debug("HTTP Response status from VO " + response.getStatus());
        return response;
    }


    private Map<String, String> createHttpConfigMap() {
        Map<String,String> map = new HashMap<String, String>();
        if(isSSLEnabled()) {
            map.put("use-ssl", "true");
        }
        return map;
    }

    private boolean isSSLEnabled() {
        return config.getProtocol().equalsIgnoreCase(HTTPS);
    }
    
    //private void add

    private String buildTargetUrl() {
        StringBuffer sb = new StringBuffer();
        sb.append(config.getProtocol())
                .append(SEP)
                .append(config.getHost())
                .append(config.getIncidentPath());
        return sb.toString();
    }
}
