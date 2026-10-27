package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AbortConfig implements Serializable {
    private List<AbortCriteria> criteriaList;

    public List<AbortCriteria> getCriteriaList() {
        return this.criteriaList;
    }

    public void setCriteriaList(Collection<AbortCriteria> collection) {
        if (collection == null) {
            this.criteriaList = null;
        } else {
            this.criteriaList = new ArrayList(collection);
        }
    }

    public AbortConfig withCriteriaList(AbortCriteria... abortCriteriaArr) {
        if (getCriteriaList() == null) {
            this.criteriaList = new ArrayList(abortCriteriaArr.length);
        }
        for (AbortCriteria abortCriteria : abortCriteriaArr) {
            this.criteriaList.add(abortCriteria);
        }
        return this;
    }

    public AbortConfig withCriteriaList(Collection<AbortCriteria> collection) {
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
        if (obj == null || !(obj instanceof AbortConfig)) {
            return false;
        }
        AbortConfig abortConfig = (AbortConfig) obj;
        if ((abortConfig.getCriteriaList() == null) ^ (getCriteriaList() == null)) {
            return false;
        }
        return abortConfig.getCriteriaList() == null || abortConfig.getCriteriaList().equals(getCriteriaList());
    }
}
