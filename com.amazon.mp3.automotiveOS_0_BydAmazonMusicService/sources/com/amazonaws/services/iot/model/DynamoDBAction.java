package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DynamoDBAction implements Serializable {
    private String hashKeyField;
    private String hashKeyType;
    private String hashKeyValue;
    private String operation;
    private String payloadField;
    private String rangeKeyField;
    private String rangeKeyType;
    private String rangeKeyValue;
    private String roleArn;
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public DynamoDBAction withTableName(String str) {
        this.tableName = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public DynamoDBAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String str) {
        this.operation = str;
    }

    public DynamoDBAction withOperation(String str) {
        this.operation = str;
        return this;
    }

    public String getHashKeyField() {
        return this.hashKeyField;
    }

    public void setHashKeyField(String str) {
        this.hashKeyField = str;
    }

    public DynamoDBAction withHashKeyField(String str) {
        this.hashKeyField = str;
        return this;
    }

    public String getHashKeyValue() {
        return this.hashKeyValue;
    }

    public void setHashKeyValue(String str) {
        this.hashKeyValue = str;
    }

    public DynamoDBAction withHashKeyValue(String str) {
        this.hashKeyValue = str;
        return this;
    }

    public String getHashKeyType() {
        return this.hashKeyType;
    }

    public void setHashKeyType(String str) {
        this.hashKeyType = str;
    }

    public DynamoDBAction withHashKeyType(String str) {
        this.hashKeyType = str;
        return this;
    }

    public void setHashKeyType(DynamoKeyType dynamoKeyType) {
        this.hashKeyType = dynamoKeyType.toString();
    }

    public DynamoDBAction withHashKeyType(DynamoKeyType dynamoKeyType) {
        this.hashKeyType = dynamoKeyType.toString();
        return this;
    }

    public String getRangeKeyField() {
        return this.rangeKeyField;
    }

    public void setRangeKeyField(String str) {
        this.rangeKeyField = str;
    }

    public DynamoDBAction withRangeKeyField(String str) {
        this.rangeKeyField = str;
        return this;
    }

    public String getRangeKeyValue() {
        return this.rangeKeyValue;
    }

    public void setRangeKeyValue(String str) {
        this.rangeKeyValue = str;
    }

    public DynamoDBAction withRangeKeyValue(String str) {
        this.rangeKeyValue = str;
        return this;
    }

    public String getRangeKeyType() {
        return this.rangeKeyType;
    }

    public void setRangeKeyType(String str) {
        this.rangeKeyType = str;
    }

    public DynamoDBAction withRangeKeyType(String str) {
        this.rangeKeyType = str;
        return this;
    }

    public void setRangeKeyType(DynamoKeyType dynamoKeyType) {
        this.rangeKeyType = dynamoKeyType.toString();
    }

    public DynamoDBAction withRangeKeyType(DynamoKeyType dynamoKeyType) {
        this.rangeKeyType = dynamoKeyType.toString();
        return this;
    }

    public String getPayloadField() {
        return this.payloadField;
    }

    public void setPayloadField(String str) {
        this.payloadField = str;
    }

    public DynamoDBAction withPayloadField(String str) {
        this.payloadField = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("tableName: " + getTableName() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getOperation() != null) {
            sb.append("operation: " + getOperation() + ",");
        }
        if (getHashKeyField() != null) {
            sb.append("hashKeyField: " + getHashKeyField() + ",");
        }
        if (getHashKeyValue() != null) {
            sb.append("hashKeyValue: " + getHashKeyValue() + ",");
        }
        if (getHashKeyType() != null) {
            sb.append("hashKeyType: " + getHashKeyType() + ",");
        }
        if (getRangeKeyField() != null) {
            sb.append("rangeKeyField: " + getRangeKeyField() + ",");
        }
        if (getRangeKeyValue() != null) {
            sb.append("rangeKeyValue: " + getRangeKeyValue() + ",");
        }
        if (getRangeKeyType() != null) {
            sb.append("rangeKeyType: " + getRangeKeyType() + ",");
        }
        if (getPayloadField() != null) {
            sb.append("payloadField: " + getPayloadField());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getOperation() == null ? 0 : getOperation().hashCode())) * 31) + (getHashKeyField() == null ? 0 : getHashKeyField().hashCode())) * 31) + (getHashKeyValue() == null ? 0 : getHashKeyValue().hashCode())) * 31) + (getHashKeyType() == null ? 0 : getHashKeyType().hashCode())) * 31) + (getRangeKeyField() == null ? 0 : getRangeKeyField().hashCode())) * 31) + (getRangeKeyValue() == null ? 0 : getRangeKeyValue().hashCode())) * 31) + (getRangeKeyType() == null ? 0 : getRangeKeyType().hashCode())) * 31) + (getPayloadField() != null ? getPayloadField().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DynamoDBAction)) {
            return false;
        }
        DynamoDBAction dynamoDBAction = (DynamoDBAction) obj;
        if ((dynamoDBAction.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        if (dynamoDBAction.getTableName() != null && !dynamoDBAction.getTableName().equals(getTableName())) {
            return false;
        }
        if ((dynamoDBAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (dynamoDBAction.getRoleArn() != null && !dynamoDBAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((dynamoDBAction.getOperation() == null) ^ (getOperation() == null)) {
            return false;
        }
        if (dynamoDBAction.getOperation() != null && !dynamoDBAction.getOperation().equals(getOperation())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyField() == null) ^ (getHashKeyField() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyField() != null && !dynamoDBAction.getHashKeyField().equals(getHashKeyField())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyValue() == null) ^ (getHashKeyValue() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyValue() != null && !dynamoDBAction.getHashKeyValue().equals(getHashKeyValue())) {
            return false;
        }
        if ((dynamoDBAction.getHashKeyType() == null) ^ (getHashKeyType() == null)) {
            return false;
        }
        if (dynamoDBAction.getHashKeyType() != null && !dynamoDBAction.getHashKeyType().equals(getHashKeyType())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyField() == null) ^ (getRangeKeyField() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyField() != null && !dynamoDBAction.getRangeKeyField().equals(getRangeKeyField())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyValue() == null) ^ (getRangeKeyValue() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyValue() != null && !dynamoDBAction.getRangeKeyValue().equals(getRangeKeyValue())) {
            return false;
        }
        if ((dynamoDBAction.getRangeKeyType() == null) ^ (getRangeKeyType() == null)) {
            return false;
        }
        if (dynamoDBAction.getRangeKeyType() != null && !dynamoDBAction.getRangeKeyType().equals(getRangeKeyType())) {
            return false;
        }
        if ((dynamoDBAction.getPayloadField() == null) ^ (getPayloadField() == null)) {
            return false;
        }
        return dynamoDBAction.getPayloadField() == null || dynamoDBAction.getPayloadField().equals(getPayloadField());
    }
}
