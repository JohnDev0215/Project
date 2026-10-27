package com.amazon.weblab.mobile.service;

/* JADX INFO: loaded from: classes5.dex */
final class ServiceAssignment {
    private String mAllocationVersion;
    private boolean mMayTrigger;
    private String mTreatment;
    private String mWeblabName;

    ServiceAssignment(String str, String str2, String str3, boolean z) {
        this.mWeblabName = str;
        this.mTreatment = str2;
        this.mAllocationVersion = str3;
        this.mMayTrigger = z;
    }

    String getTreatment() {
        return this.mTreatment;
    }

    String getVersion() {
        return this.mAllocationVersion;
    }

    boolean getMayTrigger() {
        return this.mMayTrigger;
    }
}
