package com.amazonaws.services.iotdata.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes5.dex */
public class PublishRequest extends AmazonWebServiceRequest implements Serializable {
    private ByteBuffer payload;
    private Integer qos;
    private Boolean retain;
    private String topic;

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public PublishRequest withTopic(String str) {
        this.topic = str;
        return this;
    }

    public Integer getQos() {
        return this.qos;
    }

    public void setQos(Integer num) {
        this.qos = num;
    }

    public PublishRequest withQos(Integer num) {
        this.qos = num;
        return this;
    }

    public Boolean isRetain() {
        return this.retain;
    }

    public Boolean getRetain() {
        return this.retain;
    }

    public void setRetain(Boolean bool) {
        this.retain = bool;
    }

    public PublishRequest withRetain(Boolean bool) {
        this.retain = bool;
        return this;
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }

    public PublishRequest withPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopic() != null) {
            sb.append("topic: " + getTopic() + ",");
        }
        if (getQos() != null) {
            sb.append("qos: " + getQos() + ",");
        }
        if (getRetain() != null) {
            sb.append("retain: " + getRetain() + ",");
        }
        if (getPayload() != null) {
            sb.append("payload: " + getPayload());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTopic() == null ? 0 : getTopic().hashCode()) + 31) * 31) + (getQos() == null ? 0 : getQos().hashCode())) * 31) + (getRetain() == null ? 0 : getRetain().hashCode())) * 31) + (getPayload() != null ? getPayload().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PublishRequest)) {
            return false;
        }
        PublishRequest publishRequest = (PublishRequest) obj;
        if ((publishRequest.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (publishRequest.getTopic() != null && !publishRequest.getTopic().equals(getTopic())) {
            return false;
        }
        if ((publishRequest.getQos() == null) ^ (getQos() == null)) {
            return false;
        }
        if (publishRequest.getQos() != null && !publishRequest.getQos().equals(getQos())) {
            return false;
        }
        if ((publishRequest.getRetain() == null) ^ (getRetain() == null)) {
            return false;
        }
        if (publishRequest.getRetain() != null && !publishRequest.getRetain().equals(getRetain())) {
            return false;
        }
        if ((publishRequest.getPayload() == null) ^ (getPayload() == null)) {
            return false;
        }
        return publishRequest.getPayload() == null || publishRequest.getPayload().equals(getPayload());
    }
}
