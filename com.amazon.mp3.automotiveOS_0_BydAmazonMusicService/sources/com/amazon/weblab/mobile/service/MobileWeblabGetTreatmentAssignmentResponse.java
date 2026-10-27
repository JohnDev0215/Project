package com.amazon.weblab.mobile.service;

import com.amazon.weblab.mobile.model.TreatmentAssignment;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class MobileWeblabGetTreatmentAssignmentResponse {
    private Map<String, TreatmentAssignment> mTreatmentAssignments;

    public MobileWeblabGetTreatmentAssignmentResponse(Map<String, TreatmentAssignment> map) {
        this.mTreatmentAssignments = map;
    }

    public Map<String, TreatmentAssignment> getTreatmentAssignments() {
        return this.mTreatmentAssignments;
    }
}
