package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionsRetryConfig implements Serializable {
    private List<RetryCriteria> criteriaList;

    public List<RetryCriteria> getCriteriaList() {
        return this.criteriaList;
    }

    public void setCriteriaList(Collection<RetryCriteria> collection) {
        if (collection == null) {
            this.criteriaList = null;
        } else {
            this.criteriaList = new ArrayList(collection);
        }
    }

    public JobExecutionsRetryConfig withCriteriaList(RetryCriteria... retryCriteriaArr) {
        if (getCriteriaList() == null) {
            this.criteriaList = new ArrayList(retryCriteriaArr.length);
        }
        for (RetryCriteria retryCriteria : retryCriteriaArr) {
            this.criteriaList.add(retryCriteria);
        }
        return this;
    }

    public JobExecutionsRetryConfig withCriteriaList(Collection<RetryCriteria> collection) {
        setCriteriaList(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCriteriaList() != null) {
            sb.append("criteriaList: " + getCriteriaList());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getCriteriaList() == null ? 0 : getCriteriaList().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionsRetryConfig)) {
            return false;
        }
        JobExecutionsRetryConfig jobExecutionsRetryConfig = (JobExecutionsRetryConfig) obj;
        if ((jobExecutionsRetryConfig.getCriteriaList() == null) ^ (getCriteriaList() == null)) {
            return false;
        }
        return jobExecutionsRetryConfig.getCriteriaList() == null || jobExecutionsRetryConfig.getCriteriaList().equals(getCriteriaList());
    }
}
