/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.pm;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello World Servlet registered by path
 * 
 * Annotations below are short version of:
 * 
 * @Component
 * @Service(Servlet.class)
 * @Properties({
 *     @Property(name="service.description", value="Hello World Path Servlet"),
 *     @Property(name="service.vendor", value="The Apache Software Foundation"),
 *     @Property(name="sling.servlet.paths", value="/hello-world-servlet")
 * })
 */
@SlingServlet(paths="/hello-world-servlet")
@Properties({
    @Property(name="service.description", value="Hello World Path Servlet"),
    @Property(name="service.vendor", value="The Apache Software Foundation")
})
@SuppressWarnings("serial")
public class ByPathServlet extends SlingAllMethodsServlet {
    
    private final Logger log = LoggerFactory.getLogger(ByPathServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws ServletException,
            IOException {

        String pagePath = request.getParameter("pagePath");
        ResourceResolver resolver = request.getResourceResolver();
        Resource pageResource = resolver.getResource(pagePath);
        if (pageResource == null) {
            log.debug("Page Resource is null.");
            return;
        }

        String inputValue = request.getParameter("inputValue");
        if (inputValue == null) {
            log.debug("Input Value is empty.");
            return;
        }

        ModifiableValueMap pageProperties = pageResource.adaptTo(ModifiableValueMap.class);
        if (pageProperties == null) {
            log.debug("properties not accessible - probably log in.");
            return;
        }
        pageProperties.put("sampleValue", inputValue);
        resolver.commit();

        response.sendRedirect(request.getHeader("Referer"));
    }

}

