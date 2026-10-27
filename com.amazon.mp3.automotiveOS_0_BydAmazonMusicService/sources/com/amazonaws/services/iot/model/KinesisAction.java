package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class KinesisAction implements Serializable {
    private String partitionKey;
    private String roleArn;
    private String streamName;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public KinesisAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public KinesisAction withStreamName(String str) {
        this.streamName = str;
        return this;
    }

    public String getPartitionKey() {
        return this.partitionKey;
    }

    public void setPartitionKey(String str) {
        this.partitionKey = str;
    }

    public KinesisAction withPartitionKey(String str) {
        this.partitionKey = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getStreamName() != null) {
            sb.append("streamName: " + getStreamName() + ",");
        }
        if (getPartitionKey() != null) {
            sb.append("partitionKey: " + getPartitionKey());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getStreamName() == null ? 0 : getStreamName().hashCode())) * 31) + (getPartitionKey() != null ? getPartitionKey().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KinesisAction)) {
            return false;
        }
        KinesisAction kinesisAction = (KinesisAction) obj;
        if ((kinesisAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (kinesisAction.getRoleArn() != null && !kinesisAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((kinesisAction.getStreamName() == null) ^ (getStreamName() == null)) {
            return false;
        }
        if (kinesisAction.getStreamName() != null && !kinesisAction.getStreamName().equals(getStreamName())) {
            return false;
        }
        if ((kinesisAction.getPartitionKey() == null) ^ (getPartitionKey() == null)) {
            return false;
        }
        return kinesisAction.getPartitionKey() == null || kinesisAction.getPartitionKey().equals(getPartitionKey());
    }
}
