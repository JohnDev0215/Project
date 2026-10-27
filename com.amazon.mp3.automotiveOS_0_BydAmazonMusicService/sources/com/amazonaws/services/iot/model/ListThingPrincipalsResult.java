package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingPrincipalsResult implements Serializable {
    private String nextToken;
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

    public ListThingPrincipalsResult withPrincipals(String... strArr) {
        if (getPrincipals() == null) {
            this.principals = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.principals.add(str);
        }
        return this;
    }

    public ListThingPrincipalsResult withPrincipals(Collection<String> collection) {
        setPrincipals(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingPrincipalsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPrincipals() != null) {
            sb.append("principals: " + getPrincipals() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPrincipals() == null ? 0 : getPrincipals().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingPrincipalsResult)) {
            return false;
        }
        ListThingPrincipalsResult listThingPrincipalsResult = (ListThingPrincipalsResult) obj;
        if ((listThingPrincipalsResult.getPrincipals() == null) ^ (getPrincipals() == null)) {
            return false;
        }
        if (listThingPrincipalsResult.getPrincipals() != null && !listThingPrincipalsResult.getPrincipals().equals(getPrincipals())) {
            return false;
        }
        if ((listThingPrincipalsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingPrincipalsResult.getNextToken() == null || listThingPrincipalsResult.getNextToken().equals(getNextToken());
    }
}
