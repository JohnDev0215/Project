package com.amazonaws.services.iotdata.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class GetRetainedMessageResult implements Serializable {
    private Long lastModifiedTime;
    private ByteBuffer payload;
    private Integer qos;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public GetRetainedMessageResult withTopic(String str) {
        this.topic = str;
        return this;
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public GetRetainedMessageResult withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public Integer getQos() {
        return this.qos;
    }

    public void setQos(Integer num) {
        this.qos = num;
    }

    public GetRetainedMessageResult withQos(Integer num) {
        this.qos = num;
        return this;
    }

    public Long getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(Long l) {
        this.lastModifiedTime = l;
    }

    public GetRetainedMessageResult withLastModifiedTime(Long l) {
        this.lastModifiedTime = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopic() != null) {
            sb.append("topic: " + getTopic() + ",");
        }
        if (getPayload() != null) {
            sb.append("payload: " + getPayload() + ",");
        }
        if (getQos() != null) {
            sb.append("qos: " + getQos() + ",");
        }
        if (getLastModifiedTime() != null) {
            sb.append("lastModifiedTime: " + getLastModifiedTime());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTopic() == null ? 0 : getTopic().hashCode()) + 31) * 31) + (getPayload() == null ? 0 : getPayload().hashCode())) * 31) + (getQos() == null ? 0 : getQos().hashCode())) * 31) + (getLastModifiedTime() != null ? getLastModifiedTime().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetRetainedMessageResult)) {
            return false;
        }
        GetRetainedMessageResult getRetainedMessageResult = (GetRetainedMessageResult) obj;
        if ((getRetainedMessageResult.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (getRetainedMessageResult.getTopic() != null && !getRetainedMessageResult.getTopic().equals(getTopic())) {
            return false;
        }
        if ((getRetainedMessageResult.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        if (getRetainedMessageResult.getPayload() != null && !getRetainedMessageResult.getPayload().equals(getPayload())) {
            return false;
        }
        if ((getRetainedMessageResult.getQos() == null) ^ (getQos() == null)) {
            return false;
        }
        if (getRetainedMessageResult.getQos() != null && !getRetainedMessageResult.getQos().equals(getQos())) {
            return false;
        }
        if ((getRetainedMessageResult.getLastModifiedTime() == null) ^ (getLastModifiedTime() == null)) {
            return false;
        }
        return getRetainedMessageResult.getLastModifiedTime() == null || getRetainedMessageResult.getLastModifiedTime().equals(getLastModifiedTime());
    }
}
