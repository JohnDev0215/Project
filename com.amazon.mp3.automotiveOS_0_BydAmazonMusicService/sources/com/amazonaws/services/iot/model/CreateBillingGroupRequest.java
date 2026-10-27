package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateBillingGroupRequest extends AmazonWebServiceRequest implements Serializable {
    private String billingGroupName;
    private BillingGroupProperties billingGroupProperties;
    private List<Tag> tags;

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public CreateBillingGroupRequest withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public BillingGroupProperties getBillingGroupProperties() {
        return this.billingGroupProperties;
    }

    public void setBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
    }

    public CreateBillingGroupRequest withBillingGroupProperties(BillingGroupProperties billingGroupProperties) {
        this.billingGroupProperties = billingGroupProperties;
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

    public CreateBillingGroupRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateBillingGroupRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBillingGroupName() != null) {
            sb.append("billingGroupName: " + getBillingGroupName() + ",");
        }
        if (getBillingGroupProperties() != null) {
            sb.append("billingGroupProperties: " + getBillingGroupProperties() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getBillingGroupName() == null ? 0 : getBillingGroupName().hashCode()) + 31) * 31) + (getBillingGroupProperties() == null ? 0 : getBillingGroupProperties().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateBillingGroupRequest)) {
            return false;
        }
        CreateBillingGroupRequest createBillingGroupRequest = (CreateBillingGroupRequest) obj;
        if ((createBillingGroupRequest.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        if (createBillingGroupRequest.getBillingGroupName() != null && !createBillingGroupRequest.getBillingGroupName().equals(getBillingGroupName())) {
            return false;
        }
        if ((createBillingGroupRequest.getBillingGroupProperties() == null) ^ (getBillingGroupProperties() == null)) {
            return false;
        }
        if (createBillingGroupRequest.getBillingGroupProperties() != null && !createBillingGroupRequest.getBillingGroupProperties().equals(getBillingGroupProperties())) {
            return false;
        }
        if ((createBillingGroupRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createBillingGroupRequest.getTags() == null || createBillingGroupRequest.getTags().equals(getTags());
    }
}
