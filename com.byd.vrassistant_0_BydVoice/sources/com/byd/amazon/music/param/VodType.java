package com.byd.amazon.music.param;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum VodType implements Parcelable {
    SONG_NAME,
    SINGER,
    ALBUM,
    SONG_CLASS;

    public static final Parcelable.Creator<VodType> CREATOR = new Parcelable.Creator<VodType>() { // from class: com.byd.amazon.music.param.VodType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VodType createFromParcel(Parcel parcel) {
            return VodType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VodType[] newArray(int i) {
            return new VodType[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
