package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDefaultAuthorizerResult implements Serializable {
    private AuthorizerDescription authorizerDescription;

    public AuthorizerDescription getAuthorizerDescription() {
        return this.authorizerDescription;
    }

    public void setAuthorizerDescription(AuthorizerDescription authorizerDescription) {
        this.authorizerDescription = authorizerDescription;
    }

    public DescribeDefaultAuthorizerResult withAuthorizerDescription(AuthorizerDescription authorizerDescription) {
        this.authorizerDescription = authorizerDescription;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizerDescription() != null) {
            sb.append("authorizerDescription: " + getAuthorizerDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAuthorizerDescription() == null ? 0 : getAuthorizerDescription().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeDefaultAuthorizerResult)) {
            return false;
        }
        DescribeDefaultAuthorizerResult describeDefaultAuthorizerResult = (DescribeDefaultAuthorizerResult) obj;
        if ((describeDefaultAuthorizerResult.getAuthorizerDescription() == null) ^ (getAuthorizerDescription() == null)) {
            return false;
        }
        return describeDefaultAuthorizerResult.getAuthorizerDescription() == null || describeDefaultAuthorizerResult.getAuthorizerDescription().equals(getAuthorizerDescription());
    }
}
