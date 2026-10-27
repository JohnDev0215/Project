package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PublishFindingToSnsParams implements Serializable {
    private String topicArn;

    public String getTopicArn() {
        return this.topicArn;
    }

    public void setTopicArn(String str) {
        this.topicArn = str;
    }

    public PublishFindingToSnsParams withTopicArn(String str) {
        this.topicArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTopicArn() != null) {
            sb.append("topicArn: " + getTopicArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTopicArn() == null ? 0 : getTopicArn().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PublishFindingToSnsParams)) {
            return false;
        }
        PublishFindingToSnsParams publishFindingToSnsParams = (PublishFindingToSnsParams) obj;
        if ((publishFindingToSnsParams.getTopicArn() == null) ^ (getTopicArn() == null)) {
            return false;
        }
        return publishFindingToSnsParams.getTopicArn() == null || publishFindingToSnsParams.getTopicArn().equals(getTopicArn());
    }
}
