package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForPolicyResult implements Serializable {
    private String nextMarker;
    private List<String> targets;

    public List<String> getTargets() {
        return this.targets;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public ListTargetsForPolicyResult withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public ListTargetsForPolicyResult withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListTargetsForPolicyResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargets() != null) {
            sb.append("targets: " + getTargets() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTargets() == null ? 0 : getTargets().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForPolicyResult)) {
            return false;
        }
        ListTargetsForPolicyResult listTargetsForPolicyResult = (ListTargetsForPolicyResult) obj;
        if ((listTargetsForPolicyResult.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (listTargetsForPolicyResult.getTargets() != null && !listTargetsForPolicyResult.getTargets().equals(getTargets())) {
            return false;
        }
        if ((listTargetsForPolicyResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listTargetsForPolicyResult.getNextMarker() == null || listTargetsForPolicyResult.getNextMarker().equals(getNextMarker());
    }
}
