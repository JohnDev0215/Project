package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StartOnDemandAuditTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> targetCheckNames;

    public List<String> getTargetCheckNames() {
        return this.targetCheckNames;
    }

    public void setTargetCheckNames(Collection<String> collection) {
        if (collection == null) {
            this.targetCheckNames = null;
        } else {
            this.targetCheckNames = new ArrayList(collection);
        }
    }

    public StartOnDemandAuditTaskRequest withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public StartOnDemandAuditTaskRequest withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargetCheckNames() != null) {
            sb.append("targetCheckNames: " + getTargetCheckNames());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartOnDemandAuditTaskRequest)) {
            return false;
        }
        StartOnDemandAuditTaskRequest startOnDemandAuditTaskRequest = (StartOnDemandAuditTaskRequest) obj;
        if ((startOnDemandAuditTaskRequest.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        return startOnDemandAuditTaskRequest.getTargetCheckNames() == null || startOnDemandAuditTaskRequest.getTargetCheckNames().equals(getTargetCheckNames());
    }
}
