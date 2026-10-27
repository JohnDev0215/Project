package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private List<Tag> tags;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public CreateThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
    }

    public CreateThingTypeRequest withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateThingTypeRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateThingTypeRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getThingTypeProperties() != null) {
            sb.append("thingTypeProperties: " + getThingTypeProperties() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateThingTypeRequest)) {
            return false;
        }
        CreateThingTypeRequest createThingTypeRequest = (CreateThingTypeRequest) obj;
        if ((createThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (createThingTypeRequest.getThingTypeName() != null && !createThingTypeRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((createThingTypeRequest.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (createThingTypeRequest.getThingTypeProperties() != null && !createThingTypeRequest.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((createThingTypeRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createThingTypeRequest.getTags() == null || createThingTypeRequest.getTags().equals(getTags());
    }
}
