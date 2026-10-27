package com.amazon.weblab.mobile.model;

/* JADX INFO: loaded from: classes5.dex */
public class TreatmentAssignment implements Cloneable {
    private boolean mAllocationChanged;
    protected boolean mCanTrigger;
    protected Long mDateModified;
    protected long mKeepInCacheDateInMillis;
    protected boolean mLocked;
    protected Long mSuggestedExpiration;
    protected String mTreatment;
    protected String mVersion;
    protected String mWeblab;

    public TreatmentAssignment(String str) {
        this.mLocked = false;
        this.mCanTrigger = false;
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Weblab can't be null nor empty");
        }
        this.mWeblab = str;
    }

    public TreatmentAssignment(String str, String str2, String str3, Long l, Long l2, boolean z, long j) {
        this.mLocked = false;
        this.mCanTrigger = false;
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("weblab can't be null nor empty");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("treatment can't be null nor empty");
        }
        if (str3 == null || str3.isEmpty()) {
            throw new IllegalArgumentException("version can't be null nor empty");
        }
        if (l == null) {
            throw new IllegalArgumentException("dateModified can't be null");
        }
        if (l2 == null) {
            throw new IllegalArgumentException("suggestedExpiration can't be null");
        }
        this.mSuggestedExpiration = l2;
        this.mWeblab = str;
        this.mTreatment = str2;
        this.mVersion = str3;
        this.mDateModified = l;
        this.mCanTrigger = z;
        this.mAllocationChanged = false;
        this.mKeepInCacheDateInMillis = j;
    }

    public void setLocked(boolean z) {
        this.mLocked = z;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getTreatment() {
        return this.mTreatment;
    }

    public Long getDateModified() {
        return this.mDateModified;
    }

    public Long getSuggestedExpiration() {
        return this.mSuggestedExpiration;
    }

    public boolean isLocked() {
        return this.mLocked;
    }

    public boolean canTrigger() {
        return this.mCanTrigger;
    }

    public String getWeblabName() {
        return this.mWeblab;
    }

    public void setAllocationChanged(boolean z) {
        this.mAllocationChanged = z;
    }

    public boolean isAllocationChanged() {
        return this.mAllocationChanged;
    }

    public void setKeepInCacheDateInMillis(long j) {
        this.mKeepInCacheDateInMillis = j;
    }

    public long getKeepInCacheDateInMillis() {
        return this.mKeepInCacheDateInMillis;
    }

    public Object clone() {
        TreatmentAssignment treatmentAssignment = new TreatmentAssignment(this.mWeblab, this.mTreatment, this.mVersion, this.mDateModified, this.mSuggestedExpiration, this.mCanTrigger, this.mKeepInCacheDateInMillis);
        treatmentAssignment.mLocked = this.mLocked;
        return treatmentAssignment;
    }

    public int hashCode() {
        int i = ((this.mCanTrigger ? 1231 : 1237) + 31) * 31;
        Long l = this.mDateModified;
        int iHashCode = (((i + (l == null ? 0 : l.hashCode())) * 31) + (this.mLocked ? 1231 : 1237)) * 31;
        Long l2 = this.mSuggestedExpiration;
        int iHashCode2 = (iHashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str = this.mTreatment;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.mVersion;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mWeblab;
        return ((iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.mKeepInCacheDateInMillis != 0 ? 1472 : 4721);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TreatmentAssignment) {
            TreatmentAssignment treatmentAssignment = (TreatmentAssignment) obj;
            return treatmentAssignment.mCanTrigger == this.mCanTrigger && treatmentAssignment.mDateModified.equals(this.mDateModified) && treatmentAssignment.mLocked == this.mLocked && treatmentAssignment.mTreatment.equals(this.mTreatment) && treatmentAssignment.mVersion.equals(this.mVersion) && treatmentAssignment.mWeblab.equals(this.mWeblab) && treatmentAssignment.mKeepInCacheDateInMillis == this.mKeepInCacheDateInMillis;
        }
        return super.equals(obj);
    }
}
