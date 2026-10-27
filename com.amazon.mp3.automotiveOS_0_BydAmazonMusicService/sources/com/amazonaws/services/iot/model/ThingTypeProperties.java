package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ThingTypeProperties implements Serializable {
    private List<String> searchableAttributes;
    private String thingTypeDescription;

    public String getThingTypeDescription() {
        return this.thingTypeDescription;
    }

    public void setThingTypeDescription(String str) {
        this.thingTypeDescription = str;
    }

    public ThingTypeProperties withThingTypeDescription(String str) {
        this.thingTypeDescription = str;
        return this;
    }

    public List<String> getSearchableAttributes() {
        return this.searchableAttributes;
    }

    public void setSearchableAttributes(Collection<String> collection) {
        if (collection == null) {
            this.searchableAttributes = null;
        } else {
            this.searchableAttributes = new ArrayList(collection);
        }
    }

    public ThingTypeProperties withSearchableAttributes(String... strArr) {
        if (getSearchableAttributes() == null) {
            this.searchableAttributes = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.searchableAttributes.add(str);
        }
        return this;
    }

    public ThingTypeProperties withSearchableAttributes(Collection<String> collection) {
        setSearchableAttributes(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeDescription() != null) {
            sb.append("thingTypeDescription: " + getThingTypeDescription() + ",");
        }
        if (getSearchableAttributes() != null) {
            sb.append("searchableAttributes: " + getSearchableAttributes());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingTypeDescription() == null ? 0 : getThingTypeDescription().hashCode()) + 31) * 31) + (getSearchableAttributes() != null ? getSearchableAttributes().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeProperties)) {
            return false;
        }
        ThingTypeProperties thingTypeProperties = (ThingTypeProperties) obj;
        if ((thingTypeProperties.getThingTypeDescription() == null) ^ (getThingTypeDescription() == null)) {
            return false;
        }
        if (thingTypeProperties.getThingTypeDescription() != null && !thingTypeProperties.getThingTypeDescription().equals(getThingTypeDescription())) {
            return false;
        }
        if ((thingTypeProperties.getSearchableAttributes() == null) ^ (getSearchableAttributes() == null)) {
            return false;
        }
        return thingTypeProperties.getSearchableAttributes() == null || thingTypeProperties.getSearchableAttributes().equals(getSearchableAttributes());
    }
}
