package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateMitigationActionRequest extends AmazonWebServiceRequest implements Serializable {
    private String actionName;
    private MitigationActionParams actionParams;
    private String roleArn;
    private List<Tag> tags;

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public CreateMitigationActionRequest withActionName(String str) {
        this.actionName = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CreateMitigationActionRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public MitigationActionParams getActionParams() {
        return this.actionParams;
    }

    public void setActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
    }

    public CreateMitigationActionRequest withActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
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

    public CreateMitigationActionRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateMitigationActionRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionName() != null) {
            sb.append("actionName: " + getActionName() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getActionParams() != null) {
            sb.append("actionParams: " + getActionParams() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getActionName() == null ? 0 : getActionName().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getActionParams() == null ? 0 : getActionParams().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateMitigationActionRequest)) {
            return false;
        }
        CreateMitigationActionRequest createMitigationActionRequest = (CreateMitigationActionRequest) obj;
        if ((createMitigationActionRequest.getActionName() == null) ^ (getActionName() == null)) {
            return false;
        }
        if (createMitigationActionRequest.getActionName() != null && !createMitigationActionRequest.getActionName().equals(getActionName())) {
            return false;
        }
        if ((createMitigationActionRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (createMitigationActionRequest.getRoleArn() != null && !createMitigationActionRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((createMitigationActionRequest.getActionParams() == null) ^ (getActionParams() == null)) {
            return false;
        }
        if (createMitigationActionRequest.getActionParams() != null && !createMitigationActionRequest.getActionParams().equals(getActionParams())) {
            return false;
        }
        if ((createMitigationActionRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createMitigationActionRequest.getTags() == null || createMitigationActionRequest.getTags().equals(getTags());
    }
}
