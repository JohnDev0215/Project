package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AwsJobAbortConfig implements Serializable {
    private List<AwsJobAbortCriteria> abortCriteriaList;

    public List<AwsJobAbortCriteria> getAbortCriteriaList() {
        return this.abortCriteriaList;
    }

    public void setAbortCriteriaList(Collection<AwsJobAbortCriteria> collection) {
        if (collection == null) {
            this.abortCriteriaList = null;
        } else {
            this.abortCriteriaList = new ArrayList(collection);
        }
    }

    public AwsJobAbortConfig withAbortCriteriaList(AwsJobAbortCriteria... awsJobAbortCriteriaArr) {
        if (getAbortCriteriaList() == null) {
            this.abortCriteriaList = new ArrayList(awsJobAbortCriteriaArr.length);
        }
        for (AwsJobAbortCriteria awsJobAbortCriteria : awsJobAbortCriteriaArr) {
            this.abortCriteriaList.add(awsJobAbortCriteria);
        }
        return this;
    }

    public AwsJobAbortConfig withAbortCriteriaList(Collection<AwsJobAbortCriteria> collection) {
        setAbortCriteriaList(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAbortCriteriaList() != null) {
            sb.append("abortCriteriaList: " + getAbortCriteriaList());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAbortCriteriaList() == null ? 0 : getAbortCriteriaList().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwsJobAbortConfig)) {
            return false;
        }
        AwsJobAbortConfig awsJobAbortConfig = (AwsJobAbortConfig) obj;
        if ((awsJobAbortConfig.getAbortCriteriaList() == null) ^ (getAbortCriteriaList() == null)) {
            return false;
        }
        return awsJobAbortConfig.getAbortCriteriaList() == null || awsJobAbortConfig.getAbortCriteriaList().equals(getAbortCriteriaList());
    }
}
