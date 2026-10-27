package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateThingGroupsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean overrideDynamicGroups;
    private List<String> thingGroupsToAdd;
    private List<String> thingGroupsToRemove;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public UpdateThingGroupsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public List<String> getThingGroupsToAdd() {
        return this.thingGroupsToAdd;
    }

    public void setThingGroupsToAdd(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupsToAdd = null;
        } else {
            this.thingGroupsToAdd = new ArrayList(collection);
        }
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToAdd(String... strArr) {
        if (getThingGroupsToAdd() == null) {
            this.thingGroupsToAdd = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupsToAdd.add(str);
        }
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToAdd(Collection<String> collection) {
        setThingGroupsToAdd(collection);
        return this;
    }

    public List<String> getThingGroupsToRemove() {
        return this.thingGroupsToRemove;
    }

    public void setThingGroupsToRemove(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupsToRemove = null;
        } else {
            this.thingGroupsToRemove = new ArrayList(collection);
        }
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToRemove(String... strArr) {
        if (getThingGroupsToRemove() == null) {
            this.thingGroupsToRemove = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupsToRemove.add(str);
        }
        return this;
    }

    public UpdateThingGroupsForThingRequest withThingGroupsToRemove(Collection<String> collection) {
        setThingGroupsToRemove(collection);
        return this;
    }

    public Boolean isOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
    }

    public Boolean getOverrideDynamicGroups() {
        return this.overrideDynamicGroups;
    }

    public void setOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
    }

    public UpdateThingGroupsForThingRequest withOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getThingGroupsToAdd() != null) {
            sb.append("thingGroupsToAdd: " + getThingGroupsToAdd() + ",");
        }
        if (getThingGroupsToRemove() != null) {
            sb.append("thingGroupsToRemove: " + getThingGroupsToRemove() + ",");
        }
        if (getOverrideDynamicGroups() != null) {
            sb.append("overrideDynamicGroups: " + getOverrideDynamicGroups());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getThingGroupsToAdd() == null ? 0 : getThingGroupsToAdd().hashCode())) * 31) + (getThingGroupsToRemove() == null ? 0 : getThingGroupsToRemove().hashCode())) * 31) + (getOverrideDynamicGroups() != null ? getOverrideDynamicGroups().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateThingGroupsForThingRequest)) {
            return false;
        }
        UpdateThingGroupsForThingRequest updateThingGroupsForThingRequest = (UpdateThingGroupsForThingRequest) obj;
        if ((updateThingGroupsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingName() != null && !updateThingGroupsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getThingGroupsToAdd() == null) ^ (getThingGroupsToAdd() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingGroupsToAdd() != null && !updateThingGroupsForThingRequest.getThingGroupsToAdd().equals(getThingGroupsToAdd())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getThingGroupsToRemove() == null) ^ (getThingGroupsToRemove() == null)) {
            return false;
        }
        if (updateThingGroupsForThingRequest.getThingGroupsToRemove() != null && !updateThingGroupsForThingRequest.getThingGroupsToRemove().equals(getThingGroupsToRemove())) {
            return false;
        }
        if ((updateThingGroupsForThingRequest.getOverrideDynamicGroups() == null) ^ (getOverrideDynamicGroups() == null)) {
            return false;
        }
        return updateThingGroupsForThingRequest.getOverrideDynamicGroups() == null || updateThingGroupsForThingRequest.getOverrideDynamicGroups().equals(getOverrideDynamicGroups());
    }
}
