package com.pm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

public class JsonViewerModel {

    private ValueMap properties;
    private String parent;
    private List<String> childFolders;
    private List<String> childNodes;
    @JsonIgnore
    private String host;

    public JsonViewerModel(Resource resource, String host) {
        this.properties = resource.getValueMap();
        Iterator<Resource> childIterator = resource.listChildren();
        childFolders = new ArrayList<>();
        childNodes = new ArrayList<>();
        this.host = host;
        while (childIterator.hasNext()) {
            Resource child = childIterator.next();
            if (isFolder(child)) {
                childFolders.add(generateLink(child.getPath()));
            }
            childNodes.add(child.getName());
        }
        if (resource.getParent() != null) {
            this.parent = generateLink(resource.getParent().getPath());
        }
    }

    private String generateLink(String path) {
        return host + path + ".jsonviewer.json";
    }

    private boolean isFolder(Resource resource) {
        return resource.getValueMap().get("jcr:primaryType", "").equals("sling:Folder");
    }

    public ValueMap getProperties() {
        return properties;
    }

    public String getParent() {
        return parent;
    }

    public List<String> getChildFolders() {
        return childFolders;
    }

    public List<String> getChildNodes() {
        return childNodes;
    }
}
