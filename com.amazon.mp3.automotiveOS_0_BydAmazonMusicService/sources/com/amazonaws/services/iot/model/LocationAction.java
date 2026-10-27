package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class LocationAction implements Serializable {
    private String deviceId;
    private String latitude;
    private String longitude;
    private String roleArn;
    private LocationTimestamp timestamp;
    private String trackerName;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public LocationAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getTrackerName() {
        return this.trackerName;
    }

    public void setTrackerName(String str) {
        this.trackerName = str;
    }

    public LocationAction withTrackerName(String str) {
        this.trackerName = str;
        return this;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public LocationAction withDeviceId(String str) {
        this.deviceId = str;
        return this;
    }

    public LocationTimestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocationTimestamp locationTimestamp) {
        this.timestamp = locationTimestamp;
    }

    public LocationAction withTimestamp(LocationTimestamp locationTimestamp) {
        this.timestamp = locationTimestamp;
        return this;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public LocationAction withLatitude(String str) {
        this.latitude = str;
        return this;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public LocationAction withLongitude(String str) {
        this.longitude = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getTrackerName() != null) {
            sb.append("trackerName: " + getTrackerName() + ",");
        }
        if (getDeviceId() != null) {
            sb.append("deviceId: " + getDeviceId() + ",");
        }
        if (getTimestamp() != null) {
            sb.append("timestamp: " + getTimestamp() + ",");
        }
        if (getLatitude() != null) {
            sb.append("latitude: " + getLatitude() + ",");
        }
        if (getLongitude() != null) {
            sb.append("longitude: " + getLongitude());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getTrackerName() == null ? 0 : getTrackerName().hashCode())) * 31) + (getDeviceId() == null ? 0 : getDeviceId().hashCode())) * 31) + (getTimestamp() == null ? 0 : getTimestamp().hashCode())) * 31) + (getLatitude() == null ? 0 : getLatitude().hashCode())) * 31) + (getLongitude() != null ? getLongitude().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LocationAction)) {
            return false;
        }
        LocationAction locationAction = (LocationAction) obj;
        if ((locationAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (locationAction.getRoleArn() != null && !locationAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((locationAction.getTrackerName() == null) ^ (getTrackerName() == null)) {
            return false;
        }
        if (locationAction.getTrackerName() != null && !locationAction.getTrackerName().equals(getTrackerName())) {
            return false;
        }
        if ((locationAction.getDeviceId() == null) ^ (getDeviceId() == null)) {
            return false;
        }
        if (locationAction.getDeviceId() != null && !locationAction.getDeviceId().equals(getDeviceId())) {
            return false;
        }
        if ((locationAction.getTimestamp() == null) ^ (getTimestamp() == null)) {
            return false;
        }
        if (locationAction.getTimestamp() != null && !locationAction.getTimestamp().equals(getTimestamp())) {
            return false;
        }
        if ((locationAction.getLatitude() == null) ^ (getLatitude() == null)) {
            return false;
        }
        if (locationAction.getLatitude() != null && !locationAction.getLatitude().equals(getLatitude())) {
            return false;
        }
        if ((locationAction.getLongitude() == null) ^ (getLongitude() == null)) {
            return false;
        }
        return locationAction.getLongitude() == null || locationAction.getLongitude().equals(getLongitude());
    }
}
