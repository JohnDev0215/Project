package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDimensionResult implements Serializable {
    private String arn;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public CreateDimensionResult withName(String str) {
        this.name = str;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String str) {
        this.arn = str;
    }

    public CreateDimensionResult withArn(String str) {
        this.arn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getArn() != null) {
            sb.append("arn: " + getArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getArn() != null ? getArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateDimensionResult)) {
            return false;
        }
        CreateDimensionResult createDimensionResult = (CreateDimensionResult) obj;
        if ((createDimensionResult.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (createDimensionResult.getName() != null && !createDimensionResult.getName().equals(getName())) {
            return false;
        }
        if ((createDimensionResult.getArn() == null) ^ (getArn() == null)) {
            return false;
        }
        return createDimensionResult.getArn() == null || createDimensionResult.getArn().equals(getArn());
    }
}
