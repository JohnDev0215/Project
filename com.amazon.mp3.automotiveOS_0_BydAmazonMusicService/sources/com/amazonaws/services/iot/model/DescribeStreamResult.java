package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeStreamResult implements Serializable {
    private StreamInfo streamInfo;

    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }

    public void setStreamInfo(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
    }

    public DescribeStreamResult withStreamInfo(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamInfo() != null) {
            sb.append("streamInfo: " + getStreamInfo());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getStreamInfo() == null ? 0 : getStreamInfo().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamResult)) {
            return false;
        }
        DescribeStreamResult describeStreamResult = (DescribeStreamResult) obj;
        if ((describeStreamResult.getStreamInfo() == null) ^ (getStreamInfo() == null)) {
            return false;
        }
        return describeStreamResult.getStreamInfo() == null || describeStreamResult.getStreamInfo().equals(getStreamInfo());
    }
}
