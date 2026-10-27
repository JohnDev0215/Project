package com.amazon.weblab.mobile.experimental;

/* JADX INFO: loaded from: classes5.dex */
public enum PlatformWeblabs {
    MBM_AUTOMATION("MBM_AUTOMATION_140447", C_CLIENT, false);

    public static final String C = "C";
    public static final String C_CLIENT = "C_Client";
    public static final String T1 = "T1";
    public static final String T2 = "T2";
    public static final String T3 = "T3";
    public static final String T4 = "T4";
    public static final String T5 = "T5";
    private String mDefaultTreatment;
    private final boolean mUpdateOnlyOnce;
    private String mWeblabName;

    PlatformWeblabs(String str, String str2, boolean z) {
        this.mWeblabName = str;
        this.mDefaultTreatment = str2;
        this.mUpdateOnlyOnce = z;
    }

    public String getWeblabName() {
        return this.mWeblabName;
    }

    public String getDefaultTreatment() {
        return this.mDefaultTreatment;
    }

    public boolean isUpdateOnlyOnce() {
        return this.mUpdateOnlyOnce;
    }
}
