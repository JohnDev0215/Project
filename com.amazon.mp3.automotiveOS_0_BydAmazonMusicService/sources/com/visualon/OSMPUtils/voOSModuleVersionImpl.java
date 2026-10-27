package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSModuleVersionImpl implements voOSModuleVersion {
    private int mModuleType;
    private String mVersion;

    public voOSModuleVersionImpl(int i, String str) {
        this.mModuleType = i;
        this.mVersion = str;
    }

    public voOSModuleVersionImpl() {
        this.mModuleType = -1;
        this.mVersion = null;
    }

    @Override // com.visualon.OSMPUtils.voOSModuleVersion
    public String GetVersion() {
        return this.mVersion;
    }

    @Override // com.visualon.OSMPUtils.voOSModuleVersion
    public int GetModuleType() {
        return this.mModuleType;
    }

    public boolean parse(Parcel parcel) {
        if (parcel == null) {
            return false;
        }
        parcel.setDataPosition(0);
        this.mModuleType = parcel.readInt();
        this.mVersion = parcel.readString();
        return true;
    }
}
