<%@page session="false" %><%
%><%@page import="org.apache.sling.api.resource.ResourceUtil,
                org.apache.sling.api.resource.ValueMap" %><%
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %>
<sling:defineObjects/><%
  request.setAttribute("pageResource", resource);
%>
<html>
  <head>
    <title>Content Creator</title>
  </head>

  <body>
    <sling:include path="creator" resourceType="paul/components/content-creator" />
  </body>
</html>
