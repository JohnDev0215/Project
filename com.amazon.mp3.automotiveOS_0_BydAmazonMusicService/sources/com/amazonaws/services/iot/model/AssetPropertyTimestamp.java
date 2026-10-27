package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AssetPropertyTimestamp implements Serializable {
    private String offsetInNanos;
    private String timeInSeconds;

    public String getTimeInSeconds() {
        return this.timeInSeconds;
    }

    public void setTimeInSeconds(String str) {
        this.timeInSeconds = str;
    }

    public AssetPropertyTimestamp withTimeInSeconds(String str) {
        this.timeInSeconds = str;
        return this;
    }

    public String getOffsetInNanos() {
        return this.offsetInNanos;
    }

    public void setOffsetInNanos(String str) {
        this.offsetInNanos = str;
    }

    public AssetPropertyTimestamp withOffsetInNanos(String str) {
        this.offsetInNanos = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTimeInSeconds() != null) {
            sb.append("timeInSeconds: " + getTimeInSeconds() + ",");
        }
        if (getOffsetInNanos() != null) {
            sb.append("offsetInNanos: " + getOffsetInNanos());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTimeInSeconds() == null ? 0 : getTimeInSeconds().hashCode()) + 31) * 31) + (getOffsetInNanos() != null ? getOffsetInNanos().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssetPropertyTimestamp)) {
            return false;
        }
        AssetPropertyTimestamp assetPropertyTimestamp = (AssetPropertyTimestamp) obj;
        if ((assetPropertyTimestamp.getTimeInSeconds() == null) ^ (getTimeInSeconds() == null)) {
            return false;
        }
        if (assetPropertyTimestamp.getTimeInSeconds() != null && !assetPropertyTimestamp.getTimeInSeconds().equals(getTimeInSeconds())) {
            return false;
        }
        if ((assetPropertyTimestamp.getOffsetInNanos() == null) ^ (getOffsetInNanos() == null)) {
            return false;
        }
        return assetPropertyTimestamp.getOffsetInNanos() == null || assetPropertyTimestamp.getOffsetInNanos().equals(getOffsetInNanos());
    }
}
