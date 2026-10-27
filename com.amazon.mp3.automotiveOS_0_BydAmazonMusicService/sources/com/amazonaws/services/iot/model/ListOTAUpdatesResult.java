package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListOTAUpdatesResult implements Serializable {
    private String nextToken;
    private List<OTAUpdateSummary> otaUpdates;

    public List<OTAUpdateSummary> getOtaUpdates() {
        return this.otaUpdates;
    }

    public void setOtaUpdates(Collection<OTAUpdateSummary> collection) {
        if (collection == null) {
            this.otaUpdates = null;
        } else {
            this.otaUpdates = new ArrayList(collection);
        }
    }

    public ListOTAUpdatesResult withOtaUpdates(OTAUpdateSummary... oTAUpdateSummaryArr) {
        if (getOtaUpdates() == null) {
            this.otaUpdates = new ArrayList(oTAUpdateSummaryArr.length);
        }
        for (OTAUpdateSummary oTAUpdateSummary : oTAUpdateSummaryArr) {
            this.otaUpdates.add(oTAUpdateSummary);
        }
        return this;
    }

    public ListOTAUpdatesResult withOtaUpdates(Collection<OTAUpdateSummary> collection) {
        setOtaUpdates(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListOTAUpdatesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdates() != null) {
            sb.append("otaUpdates: " + getOtaUpdates() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getOtaUpdates() == null ? 0 : getOtaUpdates().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListOTAUpdatesResult)) {
            return false;
        }
        ListOTAUpdatesResult listOTAUpdatesResult = (ListOTAUpdatesResult) obj;
        if ((listOTAUpdatesResult.getOtaUpdates() == null) ^ (getOtaUpdates() == null)) {
            return false;
        }
        if (listOTAUpdatesResult.getOtaUpdates() != null && !listOTAUpdatesResult.getOtaUpdates().equals(getOtaUpdates())) {
            return false;
        }
        if ((listOTAUpdatesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listOTAUpdatesResult.getNextToken() == null || listOTAUpdatesResult.getNextToken().equals(getNextToken());
    }
}
