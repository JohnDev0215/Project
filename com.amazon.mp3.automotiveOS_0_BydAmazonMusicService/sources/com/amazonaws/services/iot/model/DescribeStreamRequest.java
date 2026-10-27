package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamId;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public DescribeStreamRequest withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamId() != null) {
            sb.append("streamId: " + getStreamId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getStreamId() == null ? 0 : getStreamId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeStreamRequest)) {
            return false;
        }
        DescribeStreamRequest describeStreamRequest = (DescribeStreamRequest) obj;
        if ((describeStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        return describeStreamRequest.getStreamId() == null || describeStreamRequest.getStreamId().equals(getStreamId());
    }
}
