package com.appdynamics.extensions.service.appd;


import com.appdynamics.extensions.http.Response;
import com.appdynamics.extensions.http.SimpleHttpClient;
import com.appdynamics.extensions.http.WebTarget;
import com.appdynamics.extensions.service.appd.app.Application;
import com.appdynamics.extensions.service.appd.app.ApplicationWrapper;
import com.appdynamics.extensions.service.appd.events.Event;
import com.appdynamics.extensions.service.appd.events.EventWrapper;
import com.appdynamics.extensions.service.appd.hrv.PolicyViolation;
import com.appdynamics.extensions.service.appd.hrv.PolicyViolationWrapper;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceImplTest {

    IService service = new ServiceImpl();


    @Test(expected = ServiceException.class)
    public void testGetApplicationsCallShouldThrowExceptionForIncorrectXmlResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(ApplicationWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(HttpURLConnection.HTTP_OK);
        when(response.xml(ApplicationWrapper.class)).thenThrow(new RuntimeException("Unable to parse xml"));
        List<Application> resp = service.getApplications(builder,endpoint);
    }

    @Test
    public void testGetApplicationsCallShouldReturnEmptyListForNullResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(ApplicationWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(null);
        List<Application> resp = service.getApplications(builder,endpoint);
        Assert.assertTrue(resp != null);
        Assert.assertTrue(resp.size() == 0);
    }

    @Test
    public void testGetApplicationsCallShouldReturnSuccessfully(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(ApplicationWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(HttpURLConnection.HTTP_OK);
        ApplicationWrapper applicationWrapper = createMockApplicationWrapper();
        when(response.xml(ApplicationWrapper.class)).thenReturn(applicationWrapper);
        List<Application> resp = service.getApplications(builder,endpoint);
        Assert.assertTrue(resp != null);
        Assert.assertTrue(resp.size() == applicationWrapper.getApplications().size());
    }


    @Test
    public void testGetApplicationsResponseIsDeserializedSuccessfully() throws IOException, JAXBException {
        InputStream is = this.getClass().getResource("/applications.mock.xml").openStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ApplicationWrapper aw =  (ApplicationWrapper)unmarshaller.unmarshal(is);
        Assert.assertTrue(aw != null && aw.getApplications() != null);
    }


    private ApplicationWrapper createMockApplicationWrapper() {
        ApplicationWrapper wrapper = new ApplicationWrapper();
        List<Application> applications = new ArrayList<Application>();
        Application app1 = new Application();
        app1.setId(1);app1.setName("name1");
        applications.add(app1);
        Application app2 = new Application();
        app2.setId(2);app2.setName("name2");
        applications.add(app2);
        wrapper.setApplications(applications);
        return wrapper;
    }


    @Test
    public void testGetHealthRuleViolationsResponseIsDeserializedSuccessfully() throws IOException, JAXBException {
        InputStream is = this.getClass().getResource("/health-rule-violations.mock.xml").openStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(PolicyViolationWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        PolicyViolationWrapper aw =  (PolicyViolationWrapper)unmarshaller.unmarshal(is);
        Assert.assertTrue(aw != null && aw.getPolicyViolations() != null);
    }


    @Test(expected = ServiceException.class)
    public void testGetHealthRuleViolationsRShouldThrowExceptionForIncorrectXmlResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(PolicyViolationWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(HttpURLConnection.HTTP_OK);
        when(response.xml(PolicyViolationWrapper.class)).thenThrow(new RuntimeException("Unable to parse xml"));
        List<PolicyViolation> resp = service.getHealthRuleViolations(builder,endpoint);
    }

    @Test
    public void testGetHealthRuleViolationsShouldReturnEmptyListForNullResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(PolicyViolationWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(null);
        List<PolicyViolation> resp = service.getHealthRuleViolations(builder, endpoint);
        Assert.assertTrue(resp != null);
        Assert.assertTrue(resp.size() == 0);
    }

    @Test
    public void testGetEventsResponseIsDeserializedSuccessfully() throws IOException, JAXBException {
        InputStream is = this.getClass().getResource("/events.mock.xml").openStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(EventWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        EventWrapper aw =  (EventWrapper)unmarshaller.unmarshal(is);
        Assert.assertTrue(aw != null && aw.getEvents() != null);
    }


    @Test(expected = ServiceException.class)
    public void testGetEventsResponseShouldThrowExceptionForIncorrectXmlResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(EventWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(HttpURLConnection.HTTP_OK);
        when(response.xml(EventWrapper.class)).thenThrow(new RuntimeException("Unable to parse xml"));
        List<Event> resp = service.getEvents(builder,endpoint);
    }

    @Test
    public void testGetEventsShouldReturnEmptyListForNullResponse(){
        ServiceBuilder builder = mock(ServiceBuilder.class);
        SimpleHttpClient client = mock(SimpleHttpClient.class);
        WebTarget webTarget = mock(WebTarget.class);
        Response response = mock(Response.class);
        String endpoint = "http://www.saas.appdynamics.com";
        when(builder.buildHttpClient(EventWrapper.class)).thenReturn(client);
        when(client.target(endpoint)).thenReturn(webTarget);
        when(webTarget.get()).thenReturn(null);
        List<Event> resp = service.getEvents(builder,endpoint);
        Assert.assertTrue(resp != null);
        Assert.assertTrue(resp.size() == 0);
    }
}
