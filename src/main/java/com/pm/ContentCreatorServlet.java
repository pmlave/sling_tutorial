package com.pm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;

@SlingServlet(paths = "/content-creator")
public class ContentCreatorServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = (Map<String, String[]>) request.getParameterMap();
        ResourceResolver resourceResolver = request.getResourceResolver();
        String contentPath = params.get("contentPath")[0];
        String propertiesString = params.get("properties")[0];
        ResourceUtil.getOrCreateResource(resourceResolver, contentPath, getPropertyMap(propertiesString), JcrResourceConstants.NT_SLING_FOLDER, true);
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * Generates a map by splitting the key/value pairs on a comma and then each item it splits on
     * an equals sign to create the map.
     *
     * @param propertyString full request parameter string
     * @return the map of property objects
     */
    private Map<String, Object> getPropertyMap(String propertyString) {
        Map<String, Object> propertyMap = new HashMap<>();
        String[] properties = propertyString.split(",");
        for (String propertyPair : properties) {
            String[] keyValue = propertyPair.split("=");
            if (StringUtils.isNotBlank(keyValue[0])) {
                propertyMap.put(keyValue[0], keyValue[1]);
            }
        }
        return propertyMap;
    }
}
