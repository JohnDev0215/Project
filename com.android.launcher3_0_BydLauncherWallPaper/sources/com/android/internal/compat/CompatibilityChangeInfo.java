package com.android.internal.compat;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public class CompatibilityChangeInfo implements Parcelable {
    public static final Parcelable.Creator<CompatibilityChangeInfo> CREATOR = new Parcelable.Creator<CompatibilityChangeInfo>() { // from class: com.android.internal.compat.CompatibilityChangeInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatibilityChangeInfo createFromParcel(Parcel parcel) {
            return new CompatibilityChangeInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatibilityChangeInfo[] newArray(int i) {
            return new CompatibilityChangeInfo[i];
        }
    };
    private final long mChangeId;
    private final String mDescription;
    private final boolean mDisabled;
    private final int mEnableAfterTargetSdk;
    private final boolean mLoggingOnly;
    private final String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return this.mChangeId;
    }

    public String getName() {
        return this.mName;
    }

    public int getEnableAfterTargetSdk() {
        return this.mEnableAfterTargetSdk;
    }

    public boolean getDisabled() {
        return this.mDisabled;
    }

    public boolean getLoggingOnly() {
        return this.mLoggingOnly;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public CompatibilityChangeInfo(Long l, String str, int i, boolean z, boolean z2, String str2) {
        this.mChangeId = l.longValue();
        this.mName = str;
        this.mEnableAfterTargetSdk = i;
        this.mDisabled = z;
        this.mLoggingOnly = z2;
        this.mDescription = str2;
    }

    private CompatibilityChangeInfo(Parcel parcel) {
        this.mChangeId = parcel.readLong();
        this.mName = parcel.readString();
        this.mEnableAfterTargetSdk = parcel.readInt();
        this.mDisabled = parcel.readBoolean();
        this.mLoggingOnly = parcel.readBoolean();
        this.mDescription = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mChangeId);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mEnableAfterTargetSdk);
        parcel.writeBoolean(this.mDisabled);
        parcel.writeBoolean(this.mLoggingOnly);
        parcel.writeString(this.mDescription);
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("CompatibilityChangeInfo(").append(getId());
        if (getName() != null) {
            sbAppend.append("; name=").append(getName());
        }
        if (getEnableAfterTargetSdk() != -1) {
            sbAppend.append("; enableAfterTargetSdk=").append(getEnableAfterTargetSdk());
        }
        if (getDisabled()) {
            sbAppend.append("; disabled");
        }
        if (getLoggingOnly()) {
            sbAppend.append("; loggingOnly");
        }
        return sbAppend.append(")").toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CompatibilityChangeInfo)) {
            return false;
        }
        CompatibilityChangeInfo compatibilityChangeInfo = (CompatibilityChangeInfo) obj;
        return this.mChangeId == compatibilityChangeInfo.mChangeId && this.mName.equals(compatibilityChangeInfo.mName) && this.mEnableAfterTargetSdk == compatibilityChangeInfo.mEnableAfterTargetSdk && this.mDisabled == compatibilityChangeInfo.mDisabled && this.mLoggingOnly == compatibilityChangeInfo.mLoggingOnly && this.mDescription.equals(compatibilityChangeInfo.mDescription);
    }
}
