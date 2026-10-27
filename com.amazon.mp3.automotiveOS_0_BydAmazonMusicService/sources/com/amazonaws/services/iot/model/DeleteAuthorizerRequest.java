package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerName;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public DeleteAuthorizerRequest withAuthorizerName(String str) {
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
        if (obj == null || !(obj instanceof DeleteAuthorizerRequest)) {
            return false;
        }
        DeleteAuthorizerRequest deleteAuthorizerRequest = (DeleteAuthorizerRequest) obj;
        if ((deleteAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        return deleteAuthorizerRequest.getAuthorizerName() == null || deleteAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName());
    }
}
