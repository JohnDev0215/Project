package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PutItemInput implements Serializable {
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public PutItemInput withTableName(String str) {
        this.tableName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("tableName: " + getTableName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTableName() == null ? 0 : getTableName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutItemInput)) {
            return false;
        }
        PutItemInput putItemInput = (PutItemInput) obj;
        if ((putItemInput.getTableName() == null) ^ (getTableName() == null)) {
            return false;
        }
        return putItemInput.getTableName() == null || putItemInput.getTableName().equals(getTableName());
    }
}
