package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListNamedShadowsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private String nextToken;
    private Integer pageSize;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListNamedShadowsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListNamedShadowsForThingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListNamedShadowsForThingRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getPageSize() != null) {
            sb.append("pageSize: " + getPageSize());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getPageSize() != null ? getPageSize().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListNamedShadowsForThingRequest)) {
            return false;
        }
        ListNamedShadowsForThingRequest listNamedShadowsForThingRequest = (ListNamedShadowsForThingRequest) obj;
        if ((listNamedShadowsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listNamedShadowsForThingRequest.getThingName() != null && !listNamedShadowsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listNamedShadowsForThingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listNamedShadowsForThingRequest.getNextToken() != null && !listNamedShadowsForThingRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listNamedShadowsForThingRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        return listNamedShadowsForThingRequest.getPageSize() == null || listNamedShadowsForThingRequest.getPageSize().equals(getPageSize());
    }
}
