package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Stream implements Serializable {
    private Integer fileId;
    private String streamId;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public Stream withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public void setFileId(Integer num) {
        this.fileId = num;
    }

    public Stream withFileId(Integer num) {
        this.fileId = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamId() != null) {
            sb.append("streamId: " + getStreamId() + ",");
        }
        if (getFileId() != null) {
            sb.append("fileId: " + getFileId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getFileId() != null ? getFileId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Stream)) {
            return false;
        }
        Stream stream = (Stream) obj;
        if ((stream.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (stream.getStreamId() != null && !stream.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((stream.getFileId() == null) ^ (getFileId() == null)) {
            return false;
        }
        return stream.getFileId() == null || stream.getFileId().equals(getFileId());
    }
}
