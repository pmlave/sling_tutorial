package com.pm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.lang3.ArrayUtils;
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
        ResourceUtil.getOrCreateResource(resourceResolver, contentPath, getPropertyMap(request.getParameterMap()), JcrResourceConstants.NT_SLING_FOLDER, true);
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * Generates a map by getting all the different associated key/value pairs passed in from the
     * dialog.
     *
     * @param requestMap Map of request parameters
     * @return the map of property objects
     */
    private Map<String, Object> getPropertyMap(Map requestMap) {
        Map<String, Object> propertyMap = new HashMap<>();
        int keySize = getKeySize(((Map<String, String[]>) requestMap).keySet());
        for (int i = 0; i < keySize; i++) {
            String[] key = (String[])requestMap.get("key-" + i);
            String[] value = (String[])requestMap.get("value-" + i);
            if (ArrayUtils.isNotEmpty(key)) {
                if (value.length == 1) {
                    propertyMap.put(key[0], value[0]);
                }
            }
        }
        return propertyMap;
    }

    /**
     * Gets the length of the keys that represent key values passed from the form.
     *
     * @param keys total parameter set keys
     * @return the number of params that start with key-
     */
    private int getKeySize(Set<String> keys) {
        int count = 0;
        for (String key : keys) {
            if (key.startsWith("key-")) {
                count++;
            }
        }
        return count;
    }
}
