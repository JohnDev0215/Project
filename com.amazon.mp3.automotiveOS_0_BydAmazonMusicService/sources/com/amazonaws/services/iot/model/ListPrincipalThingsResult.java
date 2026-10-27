package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListPrincipalThingsResult implements Serializable {
    private String nextToken;
    private List<String> things;

    public List<String> getThings() {
        return this.things;
    }

    public void setThings(Collection<String> collection) {
        if (collection == null) {
            this.things = null;
        } else {
            this.things = new ArrayList(collection);
        }
    }

    public ListPrincipalThingsResult withThings(String... strArr) {
        if (getThings() == null) {
            this.things = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.things.add(str);
        }
        return this;
    }

    public ListPrincipalThingsResult withThings(Collection<String> collection) {
        setThings(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListPrincipalThingsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThings() != null) {
            sb.append("things: " + getThings() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThings() == null ? 0 : getThings().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListPrincipalThingsResult)) {
            return false;
        }
        ListPrincipalThingsResult listPrincipalThingsResult = (ListPrincipalThingsResult) obj;
        if ((listPrincipalThingsResult.getThings() == null) ^ (getThings() == null)) {
            return false;
        }
        if (listPrincipalThingsResult.getThings() != null && !listPrincipalThingsResult.getThings().equals(getThings())) {
            return false;
        }
        if ((listPrincipalThingsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listPrincipalThingsResult.getNextToken() == null || listPrincipalThingsResult.getNextToken().equals(getNextToken());
    }
}
