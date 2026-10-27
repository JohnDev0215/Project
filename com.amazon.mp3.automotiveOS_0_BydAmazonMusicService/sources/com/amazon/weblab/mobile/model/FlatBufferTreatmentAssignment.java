package com.amazon.weblab.mobile.model;

import com.amazon.weblab.mobile.model.flatbuffer.Assignment;

/* JADX INFO: loaded from: classes5.dex */
public class FlatBufferTreatmentAssignment extends TreatmentAssignment {
    private final Assignment mAssignment;

    public FlatBufferTreatmentAssignment(Assignment assignment) {
        super(assignment.name());
        this.mAssignment = assignment;
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public boolean canTrigger() {
        return this.mAssignment.canTrigger();
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public String getVersion() {
        return this.mAssignment.version();
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public boolean isLocked() {
        return this.mAssignment.isLocked();
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public String getTreatment() {
        return this.mAssignment.treatment();
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public Long getDateModified() {
        return Long.valueOf(this.mAssignment.cacheInsertionSecs() * 1000);
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public Long getSuggestedExpiration() {
        return Long.valueOf(this.mAssignment.expirationSecs() * 1000);
    }

    @Override // com.amazon.weblab.mobile.model.TreatmentAssignment
    public long getKeepInCacheDateInMillis() {
        return this.mAssignment.cacheInsertionSecs();
    }
}
