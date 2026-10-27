package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StreamSummary implements Serializable {
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

    public StreamSummary withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getStreamArn() {
        return this.streamArn;
    }

    public void setStreamArn(String str) {
        this.streamArn = str;
    }

    public StreamSummary withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public Integer getStreamVersion() {
        return this.streamVersion;
    }

    public void setStreamVersion(Integer num) {
        this.streamVersion = num;
    }

    public StreamSummary withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public StreamSummary withDescription(String str) {
        this.description = str;
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
        if (getStreamVersion() != null) {
            sb.append("streamVersion: " + getStreamVersion() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getStreamVersion() == null ? 0 : getStreamVersion().hashCode())) * 31) + (getDescription() != null ? getDescription().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamSummary)) {
            return false;
        }
        StreamSummary streamSummary = (StreamSummary) obj;
        if ((streamSummary.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (streamSummary.getStreamId() != null && !streamSummary.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((streamSummary.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (streamSummary.getStreamArn() != null && !streamSummary.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((streamSummary.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        if (streamSummary.getStreamVersion() != null && !streamSummary.getStreamVersion().equals(getStreamVersion())) {
            return false;
        }
        if ((streamSummary.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return streamSummary.getDescription() == null || streamSummary.getDescription().equals(getDescription());
    }
}
