package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListTagsForResourceRequest extends AmazonWebServiceRequest implements Serializable {
    private String nextToken;
    private String resourceArn;

    public String getResourceArn() {
        return this.resourceArn;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public ListTagsForResourceRequest withResourceArn(String str) {
        this.resourceArn = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTagsForResourceRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getResourceArn() != null) {
            sb.append("resourceArn: " + getResourceArn() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getResourceArn() == null ? 0 : getResourceArn().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForResourceRequest)) {
            return false;
        }
        ListTagsForResourceRequest listTagsForResourceRequest = (ListTagsForResourceRequest) obj;
        if ((listTagsForResourceRequest.getResourceArn() == null) ^ (getResourceArn() == null)) {
            return false;
        }
        if (listTagsForResourceRequest.getResourceArn() != null && !listTagsForResourceRequest.getResourceArn().equals(getResourceArn())) {
            return false;
        }
        if ((listTagsForResourceRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTagsForResourceRequest.getNextToken() == null || listTagsForResourceRequest.getNextToken().equals(getNextToken());
    }
}
