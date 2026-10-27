package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AuthorizerDescription implements Serializable {
    private String authorizerArn;
    private String authorizerFunctionArn;
    private String authorizerName;
    private Date creationDate;
    private Boolean enableCachingForHttp;
    private Date lastModifiedDate;
    private Boolean signingDisabled;
    private String status;
    private String tokenKeyName;
    private Map<String, String> tokenSigningPublicKeys;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public AuthorizerDescription withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public String getAuthorizerArn() {
        return this.authorizerArn;
    }

    public void setAuthorizerArn(String str) {
        this.authorizerArn = str;
    }

    public AuthorizerDescription withAuthorizerArn(String str) {
        this.authorizerArn = str;
        return this;
    }

    public String getAuthorizerFunctionArn() {
        return this.authorizerFunctionArn;
    }

    public void setAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
    }

    public AuthorizerDescription withAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
        return this;
    }

    public String getTokenKeyName() {
        return this.tokenKeyName;
    }

    public void setTokenKeyName(String str) {
        this.tokenKeyName = str;
    }

    public AuthorizerDescription withTokenKeyName(String str) {
        this.tokenKeyName = str;
        return this;
    }

    public Map<String, String> getTokenSigningPublicKeys() {
        return this.tokenSigningPublicKeys;
    }

    public void setTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
    }

    public AuthorizerDescription withTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
        return this;
    }

    public AuthorizerDescription addtokenSigningPublicKeysEntry(String str, String str2) {
        if (this.tokenSigningPublicKeys == null) {
            this.tokenSigningPublicKeys = new HashMap();
        }
        if (this.tokenSigningPublicKeys.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.tokenSigningPublicKeys.put(str, str2);
        return this;
    }

    public AuthorizerDescription cleartokenSigningPublicKeysEntries() {
        this.tokenSigningPublicKeys = null;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public AuthorizerDescription withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public AuthorizerDescription withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public AuthorizerDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public AuthorizerDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public Boolean isSigningDisabled() {
        return this.signingDisabled;
    }

    public Boolean getSigningDisabled() {
        return this.signingDisabled;
    }

    public void setSigningDisabled(Boolean bool) {
        this.signingDisabled = bool;
    }

    public AuthorizerDescription withSigningDisabled(Boolean bool) {
        this.signingDisabled = bool;
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

    public AuthorizerDescription withEnableCachingForHttp(Boolean bool) {
        this.enableCachingForHttp = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthorizerName() != null) {
            sb.append("authorizerName: " + getAuthorizerName() + ",");
        }
        if (getAuthorizerArn() != null) {
            sb.append("authorizerArn: " + getAuthorizerArn() + ",");
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
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getSigningDisabled() != null) {
            sb.append("signingDisabled: " + getSigningDisabled() + ",");
        }
        if (getEnableCachingForHttp() != null) {
            sb.append("enableCachingForHttp: " + getEnableCachingForHttp());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerArn() == null ? 0 : getAuthorizerArn().hashCode())) * 31) + (getAuthorizerFunctionArn() == null ? 0 : getAuthorizerFunctionArn().hashCode())) * 31) + (getTokenKeyName() == null ? 0 : getTokenKeyName().hashCode())) * 31) + (getTokenSigningPublicKeys() == null ? 0 : getTokenSigningPublicKeys().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getSigningDisabled() == null ? 0 : getSigningDisabled().hashCode())) * 31) + (getEnableCachingForHttp() != null ? getEnableCachingForHttp().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthorizerDescription)) {
            return false;
        }
        AuthorizerDescription authorizerDescription = (AuthorizerDescription) obj;
        if ((authorizerDescription.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerName() != null && !authorizerDescription.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((authorizerDescription.getAuthorizerArn() == null) ^ (getAuthorizerArn() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerArn() != null && !authorizerDescription.getAuthorizerArn().equals(getAuthorizerArn())) {
            return false;
        }
        if ((authorizerDescription.getAuthorizerFunctionArn() == null) ^ (getAuthorizerFunctionArn() == null)) {
            return false;
        }
        if (authorizerDescription.getAuthorizerFunctionArn() != null && !authorizerDescription.getAuthorizerFunctionArn().equals(getAuthorizerFunctionArn())) {
            return false;
        }
        if ((authorizerDescription.getTokenKeyName() == null) ^ (getTokenKeyName() == null)) {
            return false;
        }
        if (authorizerDescription.getTokenKeyName() != null && !authorizerDescription.getTokenKeyName().equals(getTokenKeyName())) {
            return false;
        }
        if ((authorizerDescription.getTokenSigningPublicKeys() == null) ^ (getTokenSigningPublicKeys() == null)) {
            return false;
        }
        if (authorizerDescription.getTokenSigningPublicKeys() != null && !authorizerDescription.getTokenSigningPublicKeys().equals(getTokenSigningPublicKeys())) {
            return false;
        }
        if ((authorizerDescription.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (authorizerDescription.getStatus() != null && !authorizerDescription.getStatus().equals(getStatus())) {
            return false;
        }
        if ((authorizerDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (authorizerDescription.getCreationDate() != null && !authorizerDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((authorizerDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (authorizerDescription.getLastModifiedDate() != null && !authorizerDescription.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((authorizerDescription.getSigningDisabled() == null) ^ (getSigningDisabled() == null)) {
            return false;
        }
        if (authorizerDescription.getSigningDisabled() != null && !authorizerDescription.getSigningDisabled().equals(getSigningDisabled())) {
            return false;
        }
        if ((authorizerDescription.getEnableCachingForHttp() == null) ^ (getEnableCachingForHttp() == null)) {
            return false;
        }
        return authorizerDescription.getEnableCachingForHttp() == null || authorizerDescription.getEnableCachingForHttp().equals(getEnableCachingForHttp());
    }
}
