<%@page session="false" %><%
%><%@page import="org.apache.sling.api.resource.ResourceUtil,
                org.apache.sling.api.resource.ValueMap,
                org.apache.sling.api.resource.Resource" %><%
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %><%
%><sling:defineObjects/><%

    Resource pageResource = (Resource) request.getAttribute("pageResource");
%>
<form action="/hello-world-servlet" method="POST">
    <input type="text" placeholder="Input Something" name="inputValue"/>
    <input type="hidden" name="pagePath" value="<%= pageResource.getPath() %>"/>
</form>
