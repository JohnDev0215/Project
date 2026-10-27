package com.byd.autoiot.service;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AutoiotProfile implements Parcelable {
    public static final Parcelable.Creator<AutoiotProfile> CREATOR = new Parcelable.Creator<AutoiotProfile>() { // from class: com.byd.autoiot.service.AutoiotProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoiotProfile createFromParcel(Parcel parcel) {
            return new AutoiotProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoiotProfile[] newArray(int i) {
            return new AutoiotProfile[i];
        }
    };
    private byte[] autoiotProfile;
    private int length;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AutoiotProfile() {
    }

    public AutoiotProfile(Parcel parcel) {
        int i = parcel.readInt();
        this.length = i;
        byte[] bArr = new byte[i];
        this.autoiotProfile = bArr;
        parcel.readByteArray(bArr);
    }

    public AutoiotProfile(byte[] bArr) {
        this.length = bArr.length;
        this.autoiotProfile = bArr;
    }

    public void setAutoiotProfile(byte[] bArr) {
        this.length = bArr.length;
        this.autoiotProfile = bArr;
    }

    public byte[] getAutoiotProfile() {
        return this.autoiotProfile;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.length);
        parcel.writeByteArray(this.autoiotProfile);
    }
}
