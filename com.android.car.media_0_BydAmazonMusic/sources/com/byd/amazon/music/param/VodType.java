package com.byd.amazon.music.param;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum VodType implements Parcelable {
    /* JADX INFO: Fake field, exist only in values array */
    SONG_NAME,
    /* JADX INFO: Fake field, exist only in values array */
    SINGER,
    /* JADX INFO: Fake field, exist only in values array */
    ALBUM,
    /* JADX INFO: Fake field, exist only in values array */
    SONG_CLASS;

    public static final Parcelable.Creator<VodType> CREATOR = new Parcelable.Creator<VodType>() { // from class: com.byd.amazon.music.param.VodType.a
        @Override // android.os.Parcelable.Creator
        public final VodType createFromParcel(Parcel parcel) {
            return VodType.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public final VodType[] newArray(int i) {
            return new VodType[i];
        }
    };

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
