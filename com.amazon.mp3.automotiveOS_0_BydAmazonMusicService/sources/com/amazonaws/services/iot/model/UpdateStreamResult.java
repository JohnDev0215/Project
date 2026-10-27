package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateStreamResult implements Serializable {
    private String description;
    private String streamArn;
    private String streamId;
    private Integer streamVersion;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public UpdateStreamResult withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getStreamArn() {
        return this.streamArn;
    }

    public void setStreamArn(String str) {
        this.streamArn = str;
    }

    public UpdateStreamResult withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public UpdateStreamResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public Integer getStreamVersion() {
        return this.streamVersion;
    }

    public void setStreamVersion(Integer num) {
        this.streamVersion = num;
    }

    public UpdateStreamResult withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamId() != null) {
            sb.append("streamId: " + getStreamId() + ",");
        }
        if (getStreamArn() != null) {
            sb.append("streamArn: " + getStreamArn() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getStreamVersion() != null) {
            sb.append("streamVersion: " + getStreamVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getStreamVersion() != null ? getStreamVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateStreamResult)) {
            return false;
        }
        UpdateStreamResult updateStreamResult = (UpdateStreamResult) obj;
        if ((updateStreamResult.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (updateStreamResult.getStreamId() != null && !updateStreamResult.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((updateStreamResult.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (updateStreamResult.getStreamArn() != null && !updateStreamResult.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((updateStreamResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (updateStreamResult.getDescription() != null && !updateStreamResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((updateStreamResult.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        return updateStreamResult.getStreamVersion() == null || updateStreamResult.getStreamVersion().equals(getStreamVersion());
    }
}
