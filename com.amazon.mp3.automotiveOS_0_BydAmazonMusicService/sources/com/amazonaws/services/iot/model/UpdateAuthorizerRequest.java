package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerFunctionArn;
    private String authorizerName;
    private Boolean enableCachingForHttp;
    private String status;
    private String tokenKeyName;
    private Map<String, String> tokenSigningPublicKeys;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public UpdateAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public String getAuthorizerFunctionArn() {
        return this.authorizerFunctionArn;
    }

    public void setAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
    }

    public UpdateAuthorizerRequest withAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
        return this;
    }

    public String getTokenKeyName() {
        return this.tokenKeyName;
    }

    public void setTokenKeyName(String str) {
        this.tokenKeyName = str;
    }

    public UpdateAuthorizerRequest withTokenKeyName(String str) {
        this.tokenKeyName = str;
        return this;
    }

    public Map<String, String> getTokenSigningPublicKeys() {
        return this.tokenSigningPublicKeys;
    }

    public void setTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
    }

    public UpdateAuthorizerRequest withTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
        return this;
    }

    public UpdateAuthorizerRequest addtokenSigningPublicKeysEntry(String str, String str2) {
        if (this.tokenSigningPublicKeys == null) {
            this.tokenSigningPublicKeys = new HashMap();
        }
        if (this.tokenSigningPublicKeys.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.tokenSigningPublicKeys.put(str, str2);
        return this;
    }

    public UpdateAuthorizerRequest cleartokenSigningPublicKeysEntries() {
        this.tokenSigningPublicKeys = null;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public UpdateAuthorizerRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public UpdateAuthorizerRequest withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }

    public Boolean isEnableCachingForHttp() {
        return this.enableCachingForHttp;
    }

    public Boolean getEnableCachingForHttp() {
        return this.enableCachingForHttp;
    }

    public void setEnableCachingForHttp(Boolean bool) {
        this.enableCachingForHttp = bool;
    }

    public UpdateAuthorizerRequest withEnableCachingForHttp(Boolean bool) {
        this.enableCachingForHttp = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizerName() != null) {
            sb.append("authorizerName: " + getAuthorizerName() + ",");
        }
        if (getAuthorizerFunctionArn() != null) {
            sb.append("authorizerFunctionArn: " + getAuthorizerFunctionArn() + ",");
        }
        if (getTokenKeyName() != null) {
            sb.append("tokenKeyName: " + getTokenKeyName() + ",");
        }
        if (getTokenSigningPublicKeys() != null) {
            sb.append("tokenSigningPublicKeys: " + getTokenSigningPublicKeys() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getEnableCachingForHttp() != null) {
            sb.append("enableCachingForHttp: " + getEnableCachingForHttp());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerFunctionArn() == null ? 0 : getAuthorizerFunctionArn().hashCode())) * 31) + (getTokenKeyName() == null ? 0 : getTokenKeyName().hashCode())) * 31) + (getTokenSigningPublicKeys() == null ? 0 : getTokenSigningPublicKeys().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getEnableCachingForHttp() != null ? getEnableCachingForHttp().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateAuthorizerRequest)) {
            return false;
        }
        UpdateAuthorizerRequest updateAuthorizerRequest = (UpdateAuthorizerRequest) obj;
        if ((updateAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getAuthorizerName() != null && !updateAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((updateAuthorizerRequest.getAuthorizerFunctionArn() == null) ^ (getAuthorizerFunctionArn() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getAuthorizerFunctionArn() != null && !updateAuthorizerRequest.getAuthorizerFunctionArn().equals(getAuthorizerFunctionArn())) {
            return false;
        }
        if ((updateAuthorizerRequest.getTokenKeyName() == null) ^ (getTokenKeyName() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getTokenKeyName() != null && !updateAuthorizerRequest.getTokenKeyName().equals(getTokenKeyName())) {
            return false;
        }
        if ((updateAuthorizerRequest.getTokenSigningPublicKeys() == null) ^ (getTokenSigningPublicKeys() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getTokenSigningPublicKeys() != null && !updateAuthorizerRequest.getTokenSigningPublicKeys().equals(getTokenSigningPublicKeys())) {
            return false;
        }
        if ((updateAuthorizerRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (updateAuthorizerRequest.getStatus() != null && !updateAuthorizerRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((updateAuthorizerRequest.getEnableCachingForHttp() == null) ^ (getEnableCachingForHttp() == null)) {
            return false;
        }
        return updateAuthorizerRequest.getEnableCachingForHttp() == null || updateAuthorizerRequest.getEnableCachingForHttp().equals(getEnableCachingForHttp());
    }
}
