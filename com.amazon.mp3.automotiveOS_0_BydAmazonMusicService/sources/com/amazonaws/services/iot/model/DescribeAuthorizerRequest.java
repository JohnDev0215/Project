package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerName;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public DescribeAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizerName() != null) {
            sb.append("authorizerName: " + getAuthorizerName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuthorizerRequest)) {
            return false;
        }
        DescribeAuthorizerRequest describeAuthorizerRequest = (DescribeAuthorizerRequest) obj;
        if ((describeAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        return describeAuthorizerRequest.getAuthorizerName() == null || describeAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName());
    }
}
