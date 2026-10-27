package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeMitigationActionRequest extends AmazonWebServiceRequest implements Serializable {
    private String actionName;

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public DescribeMitigationActionRequest withActionName(String str) {
        this.actionName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionName() != null) {
            sb.append("actionName: " + getActionName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getActionName() == null ? 0 : getActionName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeMitigationActionRequest)) {
            return false;
        }
        DescribeMitigationActionRequest describeMitigationActionRequest = (DescribeMitigationActionRequest) obj;
        if ((describeMitigationActionRequest.getActionName() == null) ^ (getActionName() == null)) {
            return false;
        }
        return describeMitigationActionRequest.getActionName() == null || describeMitigationActionRequest.getActionName().equals(getActionName());
    }
}
