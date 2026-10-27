package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class KafkaAction implements Serializable {
    private Map<String, String> clientProperties;
    private String destinationArn;
    private String key;
    private String partition;
    private String topic;

    public String getDestinationArn() {
        return this.destinationArn;
    }

    public void setDestinationArn(String str) {
        this.destinationArn = str;
    }

    public KafkaAction withDestinationArn(String str) {
        this.destinationArn = str;
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public KafkaAction withTopic(String str) {
        this.topic = str;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public KafkaAction withKey(String str) {
        this.key = str;
        return this;
    }

    public String getPartition() {
        return this.partition;
    }

    public void setPartition(String str) {
        this.partition = str;
    }

    public KafkaAction withPartition(String str) {
        this.partition = str;
        return this;
    }

    public Map<String, String> getClientProperties() {
        return this.clientProperties;
    }

    public void setClientProperties(Map<String, String> map) {
        this.clientProperties = map;
    }

    public KafkaAction withClientProperties(Map<String, String> map) {
        this.clientProperties = map;
        return this;
    }

    public KafkaAction addclientPropertiesEntry(String str, String str2) {
        if (this.clientProperties == null) {
            this.clientProperties = new HashMap();
        }
        if (this.clientProperties.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.clientProperties.put(str, str2);
        return this;
    }

    public KafkaAction clearclientPropertiesEntries() {
        this.clientProperties = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDestinationArn() != null) {
            sb.append("destinationArn: " + getDestinationArn() + ",");
        }
        if (getTopic() != null) {
            sb.append("topic: " + getTopic() + ",");
        }
        if (getKey() != null) {
            sb.append("key: " + getKey() + ",");
        }
        if (getPartition() != null) {
            sb.append("partition: " + getPartition() + ",");
        }
        if (getClientProperties() != null) {
            sb.append("clientProperties: " + getClientProperties());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getDestinationArn() == null ? 0 : getDestinationArn().hashCode()) + 31) * 31) + (getTopic() == null ? 0 : getTopic().hashCode())) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31) + (getPartition() == null ? 0 : getPartition().hashCode())) * 31) + (getClientProperties() != null ? getClientProperties().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KafkaAction)) {
            return false;
        }
        KafkaAction kafkaAction = (KafkaAction) obj;
        if ((kafkaAction.getDestinationArn() == null) ^ (getDestinationArn() == null)) {
            return false;
        }
        if (kafkaAction.getDestinationArn() != null && !kafkaAction.getDestinationArn().equals(getDestinationArn())) {
            return false;
        }
        if ((kafkaAction.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (kafkaAction.getTopic() != null && !kafkaAction.getTopic().equals(getTopic())) {
            return false;
        }
        if ((kafkaAction.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (kafkaAction.getKey() != null && !kafkaAction.getKey().equals(getKey())) {
            return false;
        }
        if ((kafkaAction.getPartition() == null) ^ (getPartition() == null)) {
            return false;
        }
        if (kafkaAction.getPartition() != null && !kafkaAction.getPartition().equals(getPartition())) {
            return false;
        }
        if ((kafkaAction.getClientProperties() == null) ^ (getClientProperties() == null)) {
            return false;
        }
        return kafkaAction.getClientProperties() == null || kafkaAction.getClientProperties().equals(getClientProperties());
    }
}
