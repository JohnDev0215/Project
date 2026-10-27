package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AddThingsToThingGroupParams implements Serializable {
    private Boolean overrideDynamicGroups;
    private List<String> thingGroupNames;

    public List<String> getThingGroupNames() {
        return this.thingGroupNames;
    }

    public void setThingGroupNames(Collection<String> collection) {
        if (collection == null) {
            this.thingGroupNames = null;
        } else {
            this.thingGroupNames = new ArrayList(collection);
        }
    }

    public AddThingsToThingGroupParams withThingGroupNames(String... strArr) {
        if (getThingGroupNames() == null) {
            this.thingGroupNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.thingGroupNames.add(str);
        }
        return this;
    }

    public AddThingsToThingGroupParams withThingGroupNames(Collection<String> collection) {
        setThingGroupNames(collection);
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

    public AddThingsToThingGroupParams withOverrideDynamicGroups(Boolean bool) {
        this.overrideDynamicGroups = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroupNames() != null) {
            sb.append("thingGroupNames: " + getThingGroupNames() + ",");
        }
        if (getOverrideDynamicGroups() != null) {
            sb.append("overrideDynamicGroups: " + getOverrideDynamicGroups());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingGroupNames() == null ? 0 : getThingGroupNames().hashCode()) + 31) * 31) + (getOverrideDynamicGroups() != null ? getOverrideDynamicGroups().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AddThingsToThingGroupParams)) {
            return false;
        }
        AddThingsToThingGroupParams addThingsToThingGroupParams = (AddThingsToThingGroupParams) obj;
        if ((addThingsToThingGroupParams.getThingGroupNames() == null) ^ (getThingGroupNames() == null)) {
            return false;
        }
        if (addThingsToThingGroupParams.getThingGroupNames() != null && !addThingsToThingGroupParams.getThingGroupNames().equals(getThingGroupNames())) {
            return false;
        }
        if ((addThingsToThingGroupParams.getOverrideDynamicGroups() == null) ^ (getOverrideDynamicGroups() == null)) {
            return false;
        }
        return addThingsToThingGroupParams.getOverrideDynamicGroups() == null || addThingsToThingGroupParams.getOverrideDynamicGroups().equals(getOverrideDynamicGroups());
    }
}
