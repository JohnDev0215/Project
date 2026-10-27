package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    private String authorizerFunctionArn;
    private String authorizerName;
    private Boolean enableCachingForHttp;
    private Boolean signingDisabled;
    private String status;
    private List<Tag> tags;
    private String tokenKeyName;
    private Map<String, String> tokenSigningPublicKeys;

    public String getAuthorizerName() {
        return this.authorizerName;
    }

    public void setAuthorizerName(String str) {
        this.authorizerName = str;
    }

    public CreateAuthorizerRequest withAuthorizerName(String str) {
        this.authorizerName = str;
        return this;
    }

    public String getAuthorizerFunctionArn() {
        return this.authorizerFunctionArn;
    }

    public void setAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
    }

    public CreateAuthorizerRequest withAuthorizerFunctionArn(String str) {
        this.authorizerFunctionArn = str;
        return this;
    }

    public String getTokenKeyName() {
        return this.tokenKeyName;
    }

    public void setTokenKeyName(String str) {
        this.tokenKeyName = str;
    }

    public CreateAuthorizerRequest withTokenKeyName(String str) {
        this.tokenKeyName = str;
        return this;
    }

    public Map<String, String> getTokenSigningPublicKeys() {
        return this.tokenSigningPublicKeys;
    }

    public void setTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
    }

    public CreateAuthorizerRequest withTokenSigningPublicKeys(Map<String, String> map) {
        this.tokenSigningPublicKeys = map;
        return this;
    }

    public CreateAuthorizerRequest addtokenSigningPublicKeysEntry(String str, String str2) {
        if (this.tokenSigningPublicKeys == null) {
            this.tokenSigningPublicKeys = new HashMap();
        }
        if (this.tokenSigningPublicKeys.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.tokenSigningPublicKeys.put(str, str2);
        return this;
    }

    public CreateAuthorizerRequest cleartokenSigningPublicKeysEntries() {
        this.tokenSigningPublicKeys = null;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public CreateAuthorizerRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
    }

    public CreateAuthorizerRequest withStatus(AuthorizerStatus authorizerStatus) {
        this.status = authorizerStatus.toString();
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateAuthorizerRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateAuthorizerRequest withTags(Collection<Tag> collection) {
        setTags(collection);
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

    public CreateAuthorizerRequest withSigningDisabled(Boolean bool) {
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

    public CreateAuthorizerRequest withEnableCachingForHttp(Boolean bool) {
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
        if (getTags() != null) {
            sb.append("tags: " + getTags() + ",");
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
        return (((((((((((((((getAuthorizerName() == null ? 0 : getAuthorizerName().hashCode()) + 31) * 31) + (getAuthorizerFunctionArn() == null ? 0 : getAuthorizerFunctionArn().hashCode())) * 31) + (getTokenKeyName() == null ? 0 : getTokenKeyName().hashCode())) * 31) + (getTokenSigningPublicKeys() == null ? 0 : getTokenSigningPublicKeys().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getTags() == null ? 0 : getTags().hashCode())) * 31) + (getSigningDisabled() == null ? 0 : getSigningDisabled().hashCode())) * 31) + (getEnableCachingForHttp() != null ? getEnableCachingForHttp().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateAuthorizerRequest)) {
            return false;
        }
        CreateAuthorizerRequest createAuthorizerRequest = (CreateAuthorizerRequest) obj;
        if ((createAuthorizerRequest.getAuthorizerName() == null) ^ (getAuthorizerName() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getAuthorizerName() != null && !createAuthorizerRequest.getAuthorizerName().equals(getAuthorizerName())) {
            return false;
        }
        if ((createAuthorizerRequest.getAuthorizerFunctionArn() == null) ^ (getAuthorizerFunctionArn() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getAuthorizerFunctionArn() != null && !createAuthorizerRequest.getAuthorizerFunctionArn().equals(getAuthorizerFunctionArn())) {
            return false;
        }
        if ((createAuthorizerRequest.getTokenKeyName() == null) ^ (getTokenKeyName() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getTokenKeyName() != null && !createAuthorizerRequest.getTokenKeyName().equals(getTokenKeyName())) {
            return false;
        }
        if ((createAuthorizerRequest.getTokenSigningPublicKeys() == null) ^ (getTokenSigningPublicKeys() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getTokenSigningPublicKeys() != null && !createAuthorizerRequest.getTokenSigningPublicKeys().equals(getTokenSigningPublicKeys())) {
            return false;
        }
        if ((createAuthorizerRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getStatus() != null && !createAuthorizerRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((createAuthorizerRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getTags() != null && !createAuthorizerRequest.getTags().equals(getTags())) {
            return false;
        }
        if ((createAuthorizerRequest.getSigningDisabled() == null) ^ (getSigningDisabled() == null)) {
            return false;
        }
        if (createAuthorizerRequest.getSigningDisabled() != null && !createAuthorizerRequest.getSigningDisabled().equals(getSigningDisabled())) {
            return false;
        }
        if ((createAuthorizerRequest.getEnableCachingForHttp() == null) ^ (getEnableCachingForHttp() == null)) {
            return false;
        }
        return createAuthorizerRequest.getEnableCachingForHttp() == null || createAuthorizerRequest.getEnableCachingForHttp().equals(getEnableCachingForHttp());
    }
}
