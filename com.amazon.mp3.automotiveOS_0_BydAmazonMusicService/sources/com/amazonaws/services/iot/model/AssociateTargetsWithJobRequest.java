package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AssociateTargetsWithJobRequest extends AmazonWebServiceRequest implements Serializable {
    private String comment;
    private String jobId;
    private String namespaceId;
    private List<String> targets;

    public List<String> getTargets() {
        return this.targets;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public AssociateTargetsWithJobRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public AssociateTargetsWithJobRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public AssociateTargetsWithJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public AssociateTargetsWithJobRequest withComment(String str) {
        this.comment = str;
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public AssociateTargetsWithJobRequest withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargets() != null) {
            sb.append("targets: " + getTargets() + ",");
        }
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getComment() != null) {
            sb.append("comment: " + getComment() + ",");
        }
        if (getNamespaceId() != null) {
            sb.append("namespaceId: " + getNamespaceId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTargets() == null ? 0 : getTargets().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31) + (getComment() == null ? 0 : getComment().hashCode())) * 31) + (getNamespaceId() != null ? getNamespaceId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssociateTargetsWithJobRequest)) {
            return false;
        }
        AssociateTargetsWithJobRequest associateTargetsWithJobRequest = (AssociateTargetsWithJobRequest) obj;
        if ((associateTargetsWithJobRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (associateTargetsWithJobRequest.getTargets() != null && !associateTargetsWithJobRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((associateTargetsWithJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (associateTargetsWithJobRequest.getJobId() != null && !associateTargetsWithJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((associateTargetsWithJobRequest.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        if (associateTargetsWithJobRequest.getComment() != null && !associateTargetsWithJobRequest.getComment().equals(getComment())) {
            return false;
        }
        if ((associateTargetsWithJobRequest.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        return associateTargetsWithJobRequest.getNamespaceId() == null || associateTargetsWithJobRequest.getNamespaceId().equals(getNamespaceId());
    }
}
