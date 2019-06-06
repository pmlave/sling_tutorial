<%@page session="false" %><%
%><%@page import="org.apache.sling.api.resource.ResourceUtil,
                org.apache.sling.api.resource.ValueMap,
                org.apache.sling.api.resource.Resource" %><%
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %><%
%><sling:defineObjects/><%

    Resource pageResource = (Resource) request.getAttribute("pageResource");
    final ValueMap attributes = pageResource.getValueMap();
    final String sampleValue = attributes.get("sampleValue", "");
%>
<h2>Output: <%= sampleValue %></h2>
