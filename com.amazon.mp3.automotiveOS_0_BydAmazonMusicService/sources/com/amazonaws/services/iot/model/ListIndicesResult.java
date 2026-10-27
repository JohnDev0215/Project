package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListIndicesResult implements Serializable {
    private List<String> indexNames;
    private String nextToken;

    public List<String> getIndexNames() {
        return this.indexNames;
    }

    public void setIndexNames(Collection<String> collection) {
        if (collection == null) {
            this.indexNames = null;
        } else {
            this.indexNames = new ArrayList(collection);
        }
    }

    public ListIndicesResult withIndexNames(String... strArr) {
        if (getIndexNames() == null) {
            this.indexNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.indexNames.add(str);
        }
        return this;
    }

    public ListIndicesResult withIndexNames(Collection<String> collection) {
        setIndexNames(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListIndicesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIndexNames() != null) {
            sb.append("indexNames: " + getIndexNames() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getIndexNames() == null ? 0 : getIndexNames().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIndicesResult)) {
            return false;
        }
        ListIndicesResult listIndicesResult = (ListIndicesResult) obj;
        if ((listIndicesResult.getIndexNames() == null) ^ (getIndexNames() == null)) {
            return false;
        }
        if (listIndicesResult.getIndexNames() != null && !listIndicesResult.getIndexNames().equals(getIndexNames())) {
            return false;
        }
        if ((listIndicesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listIndicesResult.getNextToken() == null || listIndicesResult.getNextToken().equals(getNextToken());
    }
}
