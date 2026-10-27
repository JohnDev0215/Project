package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ThingConnectivity implements Serializable {
    private Boolean connected;
    private String disconnectReason;
    private Long timestamp;

    public Boolean isConnected() {
        return this.connected;
    }

    public Boolean getConnected() {
        return this.connected;
    }

    public void setConnected(Boolean bool) {
        this.connected = bool;
    }

    public ThingConnectivity withConnected(Boolean bool) {
        this.connected = bool;
        return this;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public ThingConnectivity withTimestamp(Long l) {
        this.timestamp = l;
        return this;
    }

    public String getDisconnectReason() {
        return this.disconnectReason;
    }

    public void setDisconnectReason(String str) {
        this.disconnectReason = str;
    }

    public ThingConnectivity withDisconnectReason(String str) {
        this.disconnectReason = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getConnected() != null) {
            sb.append("connected: " + getConnected() + ",");
        }
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp() + ",");
        }
        if (getDisconnectReason() != null) {
            sb.append("disconnectReason: " + getDisconnectReason());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getConnected() == null ? 0 : getConnected().hashCode()) + 31) * 31) + (getTimestamp() == null ? 0 : getTimestamp().hashCode())) * 31) + (getDisconnectReason() != null ? getDisconnectReason().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingConnectivity)) {
            return false;
        }
        ThingConnectivity thingConnectivity = (ThingConnectivity) obj;
        if ((thingConnectivity.getConnected() == null) ^ (getConnected() == null)) {
            return false;
        }
        if (thingConnectivity.getConnected() != null && !thingConnectivity.getConnected().equals(getConnected())) {
            return false;
        }
        if ((thingConnectivity.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (thingConnectivity.getTimestamp() != null && !thingConnectivity.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((thingConnectivity.getDisconnectReason() == null) ^ (getDisconnectReason() == null)) {
            return false;
        }
        return thingConnectivity.getDisconnectReason() == null || thingConnectivity.getDisconnectReason().equals(getDisconnectReason());
    }
}
