package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DynamoDBv2Action implements Serializable {
    private PutItemInput putItem;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public DynamoDBv2Action withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public PutItemInput getPutItem() {
        return this.putItem;
    }

    public void setPutItem(PutItemInput putItemInput) {
        this.putItem = putItemInput;
    }

    public DynamoDBv2Action withPutItem(PutItemInput putItemInput) {
        this.putItem = putItemInput;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getPutItem() != null) {
            sb.append("putItem: " + getPutItem());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getPutItem() != null ? getPutItem().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DynamoDBv2Action)) {
            return false;
        }
        DynamoDBv2Action dynamoDBv2Action = (DynamoDBv2Action) obj;
        if ((dynamoDBv2Action.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (dynamoDBv2Action.getRoleArn() != null && !dynamoDBv2Action.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((dynamoDBv2Action.getPutItem() == null) ^ (getPutItem() == null)) {
            return false;
        }
        return dynamoDBv2Action.getPutItem() == null || dynamoDBv2Action.getPutItem().equals(getPutItem());
    }
}
