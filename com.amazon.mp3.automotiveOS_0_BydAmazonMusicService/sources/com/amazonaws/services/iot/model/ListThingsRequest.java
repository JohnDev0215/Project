package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingsRequest extends AmazonWebServiceRequest implements Serializable {
    private String attributeName;
    private String attributeValue;
    private Integer maxResults;
    private String nextToken;
    private String thingTypeName;
    private Boolean usePrefixAttributeValue;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String str) {
        this.attributeName = str;
    }

    public ListThingsRequest withAttributeName(String str) {
        this.attributeName = str;
        return this;
    }

    public String getAttributeValue() {
        return this.attributeValue;
    }

    public void setAttributeValue(String str) {
        this.attributeValue = str;
    }

    public ListThingsRequest withAttributeValue(String str) {
        this.attributeValue = str;
        return this;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public ListThingsRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public Boolean isUsePrefixAttributeValue() {
        return this.usePrefixAttributeValue;
    }

    public Boolean getUsePrefixAttributeValue() {
        return this.usePrefixAttributeValue;
    }

    public void setUsePrefixAttributeValue(Boolean bool) {
        this.usePrefixAttributeValue = bool;
    }

    public ListThingsRequest withUsePrefixAttributeValue(Boolean bool) {
        this.usePrefixAttributeValue = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getAttributeName() != null) {
            sb.append("attributeName: " + getAttributeName() + ",");
        }
        if (getAttributeValue() != null) {
            sb.append("attributeValue: " + getAttributeValue() + ",");
        }
        if (getThingTypeName() != null) {
            sb.append("thingTypeName: " + getThingTypeName() + ",");
        }
        if (getUsePrefixAttributeValue() != null) {
            sb.append("usePrefixAttributeValue: " + getUsePrefixAttributeValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getAttributeName() == null ? 0 : getAttributeName().hashCode())) * 31) + (getAttributeValue() == null ? 0 : getAttributeValue().hashCode())) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getUsePrefixAttributeValue() != null ? getUsePrefixAttributeValue().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingsRequest)) {
            return false;
        }
        ListThingsRequest listThingsRequest = (ListThingsRequest) obj;
        if ((listThingsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingsRequest.getNextToken() != null && !listThingsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingsRequest.getMaxResults() != null && !listThingsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingsRequest.getAttributeName() == null) ^ (getAttributeName() == null)) {
            return false;
        }
        if (listThingsRequest.getAttributeName() != null && !listThingsRequest.getAttributeName().equals(getAttributeName())) {
            return false;
        }
        if ((listThingsRequest.getAttributeValue() == null) ^ (getAttributeValue() == null)) {
            return false;
        }
        if (listThingsRequest.getAttributeValue() != null && !listThingsRequest.getAttributeValue().equals(getAttributeValue())) {
            return false;
        }
        if ((listThingsRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (listThingsRequest.getThingTypeName() != null && !listThingsRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((listThingsRequest.getUsePrefixAttributeValue() == null) ^ (getUsePrefixAttributeValue() == null)) {
            return false;
        }
        return listThingsRequest.getUsePrefixAttributeValue() == null || listThingsRequest.getUsePrefixAttributeValue().equals(getUsePrefixAttributeValue());
    }
}
