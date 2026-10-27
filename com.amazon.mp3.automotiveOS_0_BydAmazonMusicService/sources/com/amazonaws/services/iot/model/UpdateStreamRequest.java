package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String description;
    private List<StreamFile> files;
    private String roleArn;
    private String streamId;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public UpdateStreamRequest withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public UpdateStreamRequest withDescription(String str) {
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

    public UpdateStreamRequest withFiles(StreamFile... streamFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(streamFileArr.length);
        }
        for (StreamFile streamFile : streamFileArr) {
            this.files.add(streamFile);
        }
        return this;
    }

    public UpdateStreamRequest withFiles(Collection<StreamFile> collection) {
        setFiles(collection);
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public UpdateStreamRequest withRoleArn(String str) {
        this.roleArn = str;
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
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateStreamRequest)) {
            return false;
        }
        UpdateStreamRequest updateStreamRequest = (UpdateStreamRequest) obj;
        if ((updateStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (updateStreamRequest.getStreamId() != null && !updateStreamRequest.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((updateStreamRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (updateStreamRequest.getDescription() != null && !updateStreamRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((updateStreamRequest.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (updateStreamRequest.getFiles() != null && !updateStreamRequest.getFiles().equals(getFiles())) {
            return false;
        }
        if ((updateStreamRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return updateStreamRequest.getRoleArn() == null || updateStreamRequest.getRoleArn().equals(getRoleArn());
    }
}
