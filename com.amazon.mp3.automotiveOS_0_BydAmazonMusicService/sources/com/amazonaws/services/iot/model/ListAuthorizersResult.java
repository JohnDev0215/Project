package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuthorizersResult implements Serializable {
    private List<AuthorizerSummary> authorizers;
    private String nextMarker;

    public List<AuthorizerSummary> getAuthorizers() {
        return this.authorizers;
    }

    public void setAuthorizers(Collection<AuthorizerSummary> collection) {
        if (collection == null) {
            this.authorizers = null;
        } else {
            this.authorizers = new ArrayList(collection);
        }
    }

    public ListAuthorizersResult withAuthorizers(AuthorizerSummary... authorizerSummaryArr) {
        if (getAuthorizers() == null) {
            this.authorizers = new ArrayList(authorizerSummaryArr.length);
        }
        for (AuthorizerSummary authorizerSummary : authorizerSummaryArr) {
            this.authorizers.add(authorizerSummary);
        }
        return this;
    }

    public ListAuthorizersResult withAuthorizers(Collection<AuthorizerSummary> collection) {
        setAuthorizers(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListAuthorizersResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizers() != null) {
            sb.append("authorizers: " + getAuthorizers() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getAuthorizers() == null ? 0 : getAuthorizers().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuthorizersResult)) {
            return false;
        }
        ListAuthorizersResult listAuthorizersResult = (ListAuthorizersResult) obj;
        if ((listAuthorizersResult.getAuthorizers() == null) ^ (getAuthorizers() == null)) {
            return false;
        }
        if (listAuthorizersResult.getAuthorizers() != null && !listAuthorizersResult.getAuthorizers().equals(getAuthorizers())) {
            return false;
        }
        if ((listAuthorizersResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listAuthorizersResult.getNextMarker() == null || listAuthorizersResult.getNextMarker().equals(getNextMarker());
    }
}
