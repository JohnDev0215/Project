package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RepublishAction implements Serializable {
    private Integer qos;
    private String roleArn;
    private String topic;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public RepublishAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public RepublishAction withTopic(String str) {
        this.topic = str;
        return this;
    }

    public Integer getQos() {
        return this.qos;
    }

    public void setQos(Integer num) {
        this.qos = num;
    }

    public RepublishAction withQos(Integer num) {
        this.qos = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getTopic() != null) {
            sb.append("topic: " + getTopic() + ",");
        }
        if (getQos() != null) {
            sb.append("qos: " + getQos());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getTopic() == null ? 0 : getTopic().hashCode())) * 31) + (getQos() != null ? getQos().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RepublishAction)) {
            return false;
        }
        RepublishAction republishAction = (RepublishAction) obj;
        if ((republishAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (republishAction.getRoleArn() != null && !republishAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((republishAction.getTopic() == null) ^ (getTopic() == null)) {
            return false;
        }
        if (republishAction.getTopic() != null && !republishAction.getTopic().equals(getTopic())) {
            return false;
        }
        if ((republishAction.getQos() == null) ^ (getQos() == null)) {
            return false;
        }
        return republishAction.getQos() == null || republishAction.getQos().equals(getQos());
    }
}
