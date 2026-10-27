package com.amazon.weblab.mobile;

import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes5.dex */
class MobileWeblabTreatmentAndTriggerResult implements IMobileWeblabTreatmentAndTriggerResult {
    private Future<MobileWeblabTriggerResult> mFuture;
    private String mTreatment;

    MobileWeblabTreatmentAndTriggerResult(String str, Future<MobileWeblabTriggerResult> future) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("treatment can't be null");
        }
        if (future == null) {
            throw new IllegalArgumentException("trigger can't be null");
        }
        this.mTreatment = str;
        this.mFuture = future;
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabTreatmentAndTriggerResult
    public String getTreatment() {
        return this.mTreatment;
    }

    @Override // com.amazon.weblab.mobile.IMobileWeblabTreatmentAndTriggerResult
    public Future<MobileWeblabTriggerResult> getFutureMobileWeblabTrigger() {
        return this.mFuture;
    }
}
