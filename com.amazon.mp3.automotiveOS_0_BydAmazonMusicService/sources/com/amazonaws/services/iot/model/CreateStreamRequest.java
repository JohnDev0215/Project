package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String description;
    private List<StreamFile> files;
    private String roleArn;
    private String streamId;
    private List<Tag> tags;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public CreateStreamRequest withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateStreamRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public List<StreamFile> getFiles() {
        return this.files;
    }

    public void setFiles(Collection<StreamFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public CreateStreamRequest withFiles(StreamFile... streamFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(streamFileArr.length);
        }
        for (StreamFile streamFile : streamFileArr) {
            this.files.add(streamFile);
        }
        return this;
    }

    public CreateStreamRequest withFiles(Collection<StreamFile> collection) {
        setFiles(collection);
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CreateStreamRequest withRoleArn(String str) {
        this.roleArn = str;
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

    public CreateStreamRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateStreamRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamId() != null) {
            sb.append("streamId: " + getStreamId() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getFiles() != null) {
            sb.append("files: " + getFiles() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateStreamRequest)) {
            return false;
        }
        CreateStreamRequest createStreamRequest = (CreateStreamRequest) obj;
        if ((createStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (createStreamRequest.getStreamId() != null && !createStreamRequest.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((createStreamRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createStreamRequest.getDescription() != null && !createStreamRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createStreamRequest.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (createStreamRequest.getFiles() != null && !createStreamRequest.getFiles().equals(getFiles())) {
            return false;
        }
        if ((createStreamRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (createStreamRequest.getRoleArn() != null && !createStreamRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((createStreamRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createStreamRequest.getTags() == null || createStreamRequest.getTags().equals(getTags());
    }
}
