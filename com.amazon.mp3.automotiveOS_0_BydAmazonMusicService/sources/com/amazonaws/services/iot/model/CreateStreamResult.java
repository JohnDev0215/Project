package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateStreamResult implements Serializable {
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

    public CreateStreamResult withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getStreamArn() {
        return this.streamArn;
    }

    public void setStreamArn(String str) {
        this.streamArn = str;
    }

    public CreateStreamResult withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateStreamResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public Integer getStreamVersion() {
        return this.streamVersion;
    }

    public void setStreamVersion(Integer num) {
        this.streamVersion = num;
    }

    public CreateStreamResult withStreamVersion(Integer num) {
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
        if (obj == null || !(obj instanceof CreateStreamResult)) {
            return false;
        }
        CreateStreamResult createStreamResult = (CreateStreamResult) obj;
        if ((createStreamResult.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (createStreamResult.getStreamId() != null && !createStreamResult.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((createStreamResult.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (createStreamResult.getStreamArn() != null && !createStreamResult.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((createStreamResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createStreamResult.getDescription() != null && !createStreamResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createStreamResult.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        return createStreamResult.getStreamVersion() == null || createStreamResult.getStreamVersion().equals(getStreamVersion());
    }
}
