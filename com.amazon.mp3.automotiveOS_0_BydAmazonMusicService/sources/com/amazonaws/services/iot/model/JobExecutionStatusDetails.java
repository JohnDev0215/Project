package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionStatusDetails implements Serializable {
    private Map<String, String> detailsMap;

    public Map<String, String> getDetailsMap() {
        return this.detailsMap;
    }

    public void setDetailsMap(Map<String, String> map) {
        this.detailsMap = map;
    }

    public JobExecutionStatusDetails withDetailsMap(Map<String, String> map) {
        this.detailsMap = map;
        return this;
    }

    public JobExecutionStatusDetails adddetailsMapEntry(String str, String str2) {
        if (this.detailsMap == null) {
            this.detailsMap = new HashMap();
        }
        if (this.detailsMap.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.detailsMap.put(str, str2);
        return this;
    }

    public JobExecutionStatusDetails cleardetailsMapEntries() {
        this.detailsMap = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDetailsMap() != null) {
            sb.append("detailsMap: " + getDetailsMap());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getDetailsMap() == null ? 0 : getDetailsMap().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionStatusDetails)) {
            return false;
        }
        JobExecutionStatusDetails jobExecutionStatusDetails = (JobExecutionStatusDetails) obj;
        if ((jobExecutionStatusDetails.getDetailsMap() == null) ^ (getDetailsMap() == null)) {
            return false;
        }
        return jobExecutionStatusDetails.getDetailsMap() == null || jobExecutionStatusDetails.getDetailsMap().equals(getDetailsMap());
    }
}
