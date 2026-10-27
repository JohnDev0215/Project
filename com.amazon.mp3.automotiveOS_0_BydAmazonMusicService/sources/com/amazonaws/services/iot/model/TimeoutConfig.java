package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TimeoutConfig implements Serializable {
    private Long inProgressTimeoutInMinutes;

    public Long getInProgressTimeoutInMinutes() {
        return this.inProgressTimeoutInMinutes;
    }

    public void setInProgressTimeoutInMinutes(Long l) {
        this.inProgressTimeoutInMinutes = l;
    }

    public TimeoutConfig withInProgressTimeoutInMinutes(Long l) {
        this.inProgressTimeoutInMinutes = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getInProgressTimeoutInMinutes() != null) {
            sb.append("inProgressTimeoutInMinutes: " + getInProgressTimeoutInMinutes());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getInProgressTimeoutInMinutes() == null ? 0 : getInProgressTimeoutInMinutes().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimeoutConfig)) {
            return false;
        }
        TimeoutConfig timeoutConfig = (TimeoutConfig) obj;
        if ((timeoutConfig.getInProgressTimeoutInMinutes() == null) ^ (getInProgressTimeoutInMinutes() == null)) {
            return false;
        }
        return timeoutConfig.getInProgressTimeoutInMinutes() == null || timeoutConfig.getInProgressTimeoutInMinutes().equals(getInProgressTimeoutInMinutes());
    }
}
