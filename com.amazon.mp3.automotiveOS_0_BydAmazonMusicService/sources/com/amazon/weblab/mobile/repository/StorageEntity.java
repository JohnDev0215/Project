package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class StorageEntity {
    private String mApplicationVersion;
    private SessionInfo mSessionInfo;
    private Map<String, TreatmentAssignment> mTreatments;

    StorageEntity(String str, SessionInfo sessionInfo, Map<String, TreatmentAssignment> map) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("appVersion can't be null or empty");
        }
        if (sessionInfo == null) {
            throw new IllegalArgumentException("info can't be null");
        }
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("treatments can't be null or empty");
        }
        this.mApplicationVersion = str;
        this.mSessionInfo = sessionInfo;
        this.mTreatments = map;
    }

    String getApplicationVersion() {
        return this.mApplicationVersion;
    }

    SessionInfo getSessionInfo() {
        return this.mSessionInfo;
    }

    Map<String, TreatmentAssignment> getTreatments() {
        return this.mTreatments;
    }
}
