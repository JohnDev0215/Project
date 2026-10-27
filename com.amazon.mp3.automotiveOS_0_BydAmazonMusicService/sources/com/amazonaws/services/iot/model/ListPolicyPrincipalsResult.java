package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListPolicyPrincipalsResult implements Serializable {
    private String nextMarker;
    private List<String> principals;

    public List<String> getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(Collection<String> collection) {
        if (collection == null) {
            this.principals = null;
        } else {
            this.principals = new ArrayList(collection);
        }
    }

    public ListPolicyPrincipalsResult withPrincipals(String... strArr) {
        if (getPrincipals() == null) {
            this.principals = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.principals.add(str);
        }
        return this;
    }

    public ListPolicyPrincipalsResult withPrincipals(Collection<String> collection) {
        setPrincipals(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListPolicyPrincipalsResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPrincipals() != null) {
            sb.append("principals: " + getPrincipals() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPrincipals() == null ? 0 : getPrincipals().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPolicyPrincipalsResult)) {
            return false;
        }
        ListPolicyPrincipalsResult listPolicyPrincipalsResult = (ListPolicyPrincipalsResult) obj;
        if ((listPolicyPrincipalsResult.getPrincipals() == null) ^ (getPrincipals() == null)) {
            return false;
        }
        if (listPolicyPrincipalsResult.getPrincipals() != null && !listPolicyPrincipalsResult.getPrincipals().equals(getPrincipals())) {
            return false;
        }
        if ((listPolicyPrincipalsResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listPolicyPrincipalsResult.getNextMarker() == null || listPolicyPrincipalsResult.getNextMarker().equals(getNextMarker());
    }
}
