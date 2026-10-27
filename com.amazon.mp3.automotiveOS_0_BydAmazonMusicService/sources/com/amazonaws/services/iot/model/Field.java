package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Field implements Serializable {
    private String name;
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Field withName(String str) {
        this.name = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Field withType(String str) {
        this.type = str;
        return this;
    }

    public void setType(FieldType fieldType) {
        this.type = fieldType.toString();
    }

    public Field withType(FieldType fieldType) {
        this.type = fieldType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getType() != null) {
            sb.append("type: " + getType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getType() != null ? getType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        if ((field.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (field.getName() != null && !field.getName().equals(getName())) {
            return false;
        }
        if ((field.getType() == null) ^ (getType() == null)) {
            return false;
        }
        return field.getType() == null || field.getType().equals(getType());
    }
}
