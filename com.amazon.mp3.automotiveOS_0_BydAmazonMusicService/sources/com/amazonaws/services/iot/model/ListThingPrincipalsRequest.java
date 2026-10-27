package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingPrincipalsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String thingName;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingPrincipalsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListThingPrincipalsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListThingPrincipalsRequest withThingName(String str) {
        this.thingName = str;
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
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getThingName() != null ? getThingName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingPrincipalsRequest)) {
            return false;
        }
        ListThingPrincipalsRequest listThingPrincipalsRequest = (ListThingPrincipalsRequest) obj;
        if ((listThingPrincipalsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingPrincipalsRequest.getNextToken() != null && !listThingPrincipalsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingPrincipalsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listThingPrincipalsRequest.getMaxResults() != null && !listThingPrincipalsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listThingPrincipalsRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        return listThingPrincipalsRequest.getThingName() == null || listThingPrincipalsRequest.getThingName().equals(getThingName());
    }
}
