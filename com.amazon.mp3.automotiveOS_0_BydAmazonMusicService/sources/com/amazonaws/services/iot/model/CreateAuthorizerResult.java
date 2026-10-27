package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuthorizerResult implements Serializable {
    private String authorizerArn;
    private String authorizerName;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public CreateAuthorizerResult withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public String getAuthorizerArn() {
        return this.authorizerArn;
    }

    public void setAuthorizerArn(String str) {
        this.authorizerArn = str;
    }

    public CreateAuthorizerResult withAuthorizerArn(String str) {
        this.authorizerArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizerName() != null) {
            sb.append("authorizerName: " + getAuthorizerName() + ",");
        }
        if (getAuthorizerArn() != null) {
            sb.append("authorizerArn: " + getAuthorizerArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerArn() != null ? getAuthorizerArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateAuthorizerResult)) {
            return false;
        }
        CreateAuthorizerResult createAuthorizerResult = (CreateAuthorizerResult) obj;
        if ((createAuthorizerResult.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (createAuthorizerResult.getAuthorizerName() != null && !createAuthorizerResult.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((createAuthorizerResult.getAuthorizerArn() == null) ^ (getAuthorizerArn() == null)) {
            return false;
        }
        return createAuthorizerResult.getAuthorizerArn() == null || createAuthorizerResult.getAuthorizerArn().equals(getAuthorizerArn());
    }
}
