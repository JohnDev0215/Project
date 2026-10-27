package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateCustomMetricRequest extends AmazonWebServiceRequest implements Serializable {
    private String clientRequestToken;
    private String displayName;
    private String metricName;
    private String metricType;
    private List<Tag> tags;

    public String getMetricName() {
        return this.metricName;
    }

    public void setMetricName(String str) {
        this.metricName = str;
    }

    public CreateCustomMetricRequest withMetricName(String str) {
        this.metricName = str;
        return this;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public CreateCustomMetricRequest withDisplayName(String str) {
        this.displayName = str;
        return this;
    }

    public String getMetricType() {
        return this.metricType;
    }

    public void setMetricType(String str) {
        this.metricType = str;
    }

    public CreateCustomMetricRequest withMetricType(String str) {
        this.metricType = str;
        return this;
    }

    public void setMetricType(CustomMetricType customMetricType) {
        this.metricType = customMetricType.toString();
    }

    public CreateCustomMetricRequest withMetricType(CustomMetricType customMetricType) {
        this.metricType = customMetricType.toString();
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

    public CreateCustomMetricRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateCustomMetricRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String getClientRequestToken() {
        return this.clientRequestToken;
    }

    public void setClientRequestToken(String str) {
        this.clientRequestToken = str;
    }

    public CreateCustomMetricRequest withClientRequestToken(String str) {
        this.clientRequestToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMetricName() != null) {
            sb.append("metricName: " + getMetricName() + ",");
        }
        if (getDisplayName() != null) {
            sb.append("displayName: " + getDisplayName() + ",");
        }
        if (getMetricType() != null) {
            sb.append("metricType: " + getMetricType() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags() + ",");
        }
        if (getClientRequestToken() != null) {
            sb.append("clientRequestToken: " + getClientRequestToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getMetricName() == null ? 0 : getMetricName().hashCode()) + 31) * 31) + (getDisplayName() == null ? 0 : getDisplayName().hashCode())) * 31) + (getMetricType() == null ? 0 : getMetricType().hashCode())) * 31) + (getTags() == null ? 0 : getTags().hashCode())) * 31) + (getClientRequestToken() != null ? getClientRequestToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateCustomMetricRequest)) {
            return false;
        }
        CreateCustomMetricRequest createCustomMetricRequest = (CreateCustomMetricRequest) obj;
        if ((createCustomMetricRequest.getMetricName() == null) ^ (getMetricName() == null)) {
            return false;
        }
        if (createCustomMetricRequest.getMetricName() != null && !createCustomMetricRequest.getMetricName().equals(getMetricName())) {
            return false;
        }
        if ((createCustomMetricRequest.getDisplayName() == null) ^ (getDisplayName() == null)) {
            return false;
        }
        if (createCustomMetricRequest.getDisplayName() != null && !createCustomMetricRequest.getDisplayName().equals(getDisplayName())) {
            return false;
        }
        if ((createCustomMetricRequest.getMetricType() == null) ^ (getMetricType() == null)) {
            return false;
        }
        if (createCustomMetricRequest.getMetricType() != null && !createCustomMetricRequest.getMetricType().equals(getMetricType())) {
            return false;
        }
        if ((createCustomMetricRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (createCustomMetricRequest.getTags() != null && !createCustomMetricRequest.getTags().equals(getTags())) {
            return false;
        }
        if ((createCustomMetricRequest.getClientRequestToken() == null) ^ (getClientRequestToken() == null)) {
            return false;
        }
        return createCustomMetricRequest.getClientRequestToken() == null || createCustomMetricRequest.getClientRequestToken().equals(getClientRequestToken());
    }
}
