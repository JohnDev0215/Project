package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DetachThingPrincipalRequest extends AmazonWebServiceRequest implements Serializable {
    private String principal;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DetachThingPrincipalRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public DetachThingPrincipalRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getPrincipal() != null) {
            sb.append("principal: " + getPrincipal());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getPrincipal() != null ? getPrincipal().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetachThingPrincipalRequest)) {
            return false;
        }
        DetachThingPrincipalRequest detachThingPrincipalRequest = (DetachThingPrincipalRequest) obj;
        if ((detachThingPrincipalRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (detachThingPrincipalRequest.getThingName() != null && !detachThingPrincipalRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((detachThingPrincipalRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        return detachThingPrincipalRequest.getPrincipal() == null || detachThingPrincipalRequest.getPrincipal().equals(getPrincipal());
    }
}
