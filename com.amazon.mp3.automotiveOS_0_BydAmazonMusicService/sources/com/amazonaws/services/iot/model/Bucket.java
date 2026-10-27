package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Bucket implements Serializable {
    private Integer count;
    private String keyValue;

    public String getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(String str) {
        this.keyValue = str;
    }

    public Bucket withKeyValue(String str) {
        this.keyValue = str;
        return this;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public Bucket withCount(Integer num) {
        this.count = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeyValue() != null) {
            sb.append("keyValue: " + getKeyValue() + ",");
        }
        if (getCount() != null) {
            sb.append("count: " + getCount());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getKeyValue() == null ? 0 : getKeyValue().hashCode()) + 31) * 31) + (getCount() != null ? getCount().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        if ((bucket.getKeyValue() == null) ^ (getKeyValue() == null)) {
            return false;
        }
        if (bucket.getKeyValue() != null && !bucket.getKeyValue().equals(getKeyValue())) {
            return false;
        }
        if ((bucket.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        return bucket.getCount() == null || bucket.getCount().equals(getCount());
    }
}
