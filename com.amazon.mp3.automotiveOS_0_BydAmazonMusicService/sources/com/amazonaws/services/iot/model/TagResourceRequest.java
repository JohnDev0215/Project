package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TagResourceRequest extends AmazonWebServiceRequest implements Serializable {
    private String resourceArn;
    private List<Tag> tags;

    public String getResourceArn() {
        return this.resourceArn;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public TagResourceRequest withResourceArn(String str) {
        this.resourceArn = str;
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

    public TagResourceRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public TagResourceRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getResourceArn() != null) {
            sb.append("resourceArn: " + getResourceArn() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getResourceArn() == null ? 0 : getResourceArn().hashCode()) + 31) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TagResourceRequest)) {
            return false;
        }
        TagResourceRequest tagResourceRequest = (TagResourceRequest) obj;
        if ((tagResourceRequest.getResourceArn() == null) ^ (getResourceArn() == null)) {
            return false;
        }
        if (tagResourceRequest.getResourceArn() != null && !tagResourceRequest.getResourceArn().equals(getResourceArn())) {
            return false;
        }
        if ((tagResourceRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return tagResourceRequest.getTags() == null || tagResourceRequest.getTags().equals(getTags());
    }
}
