package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AssetPropertyValue implements Serializable {
    private String quality;
    private AssetPropertyTimestamp timestamp;
    private AssetPropertyVariant value;

    public AssetPropertyVariant getValue() {
        return this.value;
    }

    public void setValue(AssetPropertyVariant assetPropertyVariant) {
        this.value = assetPropertyVariant;
    }

    public AssetPropertyValue withValue(AssetPropertyVariant assetPropertyVariant) {
        this.value = assetPropertyVariant;
        return this;
    }

    public AssetPropertyTimestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(AssetPropertyTimestamp assetPropertyTimestamp) {
        this.timestamp = assetPropertyTimestamp;
    }

    public AssetPropertyValue withTimestamp(AssetPropertyTimestamp assetPropertyTimestamp) {
        this.timestamp = assetPropertyTimestamp;
        return this;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public AssetPropertyValue withQuality(String str) {
        this.quality = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getValue() != null) {
            sb.append("value: " + getValue() + ",");
        }
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp() + ",");
        }
        if (getQuality() != null) {
            sb.append("quality: " + getQuality());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31) + (getTimestamp() == null ? 0 : getTimestamp().hashCode())) * 31) + (getQuality() != null ? getQuality().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssetPropertyValue)) {
            return false;
        }
        AssetPropertyValue assetPropertyValue = (AssetPropertyValue) obj;
        if ((assetPropertyValue.getValue() == null) ^ (getValue() == null)) {
            return false;
        }
        if (assetPropertyValue.getValue() != null && !assetPropertyValue.getValue().equals(getValue())) {
            return false;
        }
        if ((assetPropertyValue.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (assetPropertyValue.getTimestamp() != null && !assetPropertyValue.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((assetPropertyValue.getQuality() == null) ^ (getQuality() == null)) {
            return false;
        }
        return assetPropertyValue.getQuality() == null || assetPropertyValue.getQuality().equals(getQuality());
    }
}
