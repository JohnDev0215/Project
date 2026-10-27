package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateRoleAliasRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer credentialDurationSeconds;
    private String roleAlias;
    private String roleArn;
    private List<Tag> tags;

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public CreateRoleAliasRequest withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CreateRoleAliasRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Integer getCredentialDurationSeconds() {
        return this.credentialDurationSeconds;
    }

    public void setCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
    }

    public CreateRoleAliasRequest withCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
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

    public CreateRoleAliasRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateRoleAliasRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAlias() != null) {
            sb.append("roleAlias: " + getRoleAlias() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getCredentialDurationSeconds() != null) {
            sb.append("credentialDurationSeconds: " + getCredentialDurationSeconds() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getCredentialDurationSeconds() == null ? 0 : getCredentialDurationSeconds().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateRoleAliasRequest)) {
            return false;
        }
        CreateRoleAliasRequest createRoleAliasRequest = (CreateRoleAliasRequest) obj;
        if ((createRoleAliasRequest.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (createRoleAliasRequest.getRoleAlias() != null && !createRoleAliasRequest.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((createRoleAliasRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (createRoleAliasRequest.getRoleArn() != null && !createRoleAliasRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((createRoleAliasRequest.getCredentialDurationSeconds() == null) ^ (getCredentialDurationSeconds() == null)) {
            return false;
        }
        if (createRoleAliasRequest.getCredentialDurationSeconds() != null && !createRoleAliasRequest.getCredentialDurationSeconds().equals(getCredentialDurationSeconds())) {
            return false;
        }
        if ((createRoleAliasRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createRoleAliasRequest.getTags() == null || createRoleAliasRequest.getTags().equals(getTags());
    }
}
