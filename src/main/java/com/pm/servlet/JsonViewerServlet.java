package com.pm.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.model.JsonViewerModel;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@SlingServlet(resourceTypes = "sling/servlet/default", selectors = "jsonviewer", extensions = "json")
public class JsonViewerServlet extends SlingSafeMethodsServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Resource currentResource = request.getResource();
        String host = getHostPrefix(request);
        response.setContentType("application/json");
        DateFormat format = new SimpleDateFormat("EE MMM dd yyyy HH:mm:ss 'GMT'Z");
        objectMapper.setDateFormat(format);
        response.getWriter().write(objectMapper.writeValueAsString(new JsonViewerModel(currentResource, host)));
    }

    private String getHostPrefix(SlingHttpServletRequest request) {
        String host = request.getScheme() + "://" + request.getServerName();
        return request.getServerPort() == 80 ? host : host + ":" + request.getServerPort();
    }
}
