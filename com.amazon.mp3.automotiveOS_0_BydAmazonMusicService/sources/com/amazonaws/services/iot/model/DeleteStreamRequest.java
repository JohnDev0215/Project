package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteStreamRequest extends AmazonWebServiceRequest implements Serializable {
    private String streamId;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public DeleteStreamRequest withStreamId(String str) {
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
        if (obj == null || !(obj instanceof DeleteStreamRequest)) {
            return false;
        }
        DeleteStreamRequest deleteStreamRequest = (DeleteStreamRequest) obj;
        if ((deleteStreamRequest.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        return deleteStreamRequest.getStreamId() == null || deleteStreamRequest.getStreamId().equals(getStreamId());
    }
}
