package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetRetainedMessageRequest extends AmazonWebServiceRequest implements Serializable {
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public GetRetainedMessageRequest withTopic(String str) {
        this.topic = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopic() != null) {
            sb.append("topic: " + getTopic());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTopic() == null ? 0 : getTopic().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRetainedMessageRequest)) {
            return false;
        }
        GetRetainedMessageRequest getRetainedMessageRequest = (GetRetainedMessageRequest) obj;
        if ((getRetainedMessageRequest.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        return getRetainedMessageRequest.getTopic() == null || getRetainedMessageRequest.getTopic().equals(getTopic());
    }
}
