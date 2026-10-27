package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ViolationEventOccurrenceRange implements Serializable {
    private Date endTime;
    private Date startTime;

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public ViolationEventOccurrenceRange withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public ViolationEventOccurrenceRange withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getEndTime() != null) {
            sb.append("endTime: " + getEndTime());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getStartTime() == null ? 0 : getStartTime().hashCode()) + 31) * 31) + (getEndTime() != null ? getEndTime().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ViolationEventOccurrenceRange)) {
            return false;
        }
        ViolationEventOccurrenceRange violationEventOccurrenceRange = (ViolationEventOccurrenceRange) obj;
        if ((violationEventOccurrenceRange.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (violationEventOccurrenceRange.getStartTime() != null && !violationEventOccurrenceRange.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((violationEventOccurrenceRange.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        return violationEventOccurrenceRange.getEndTime() == null || violationEventOccurrenceRange.getEndTime().equals(getEndTime());
    }
}
