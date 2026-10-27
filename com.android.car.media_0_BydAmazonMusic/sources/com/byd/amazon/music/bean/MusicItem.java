package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import spi.c;

/* JADX INFO: loaded from: classes2.dex */
public class MusicItem implements Parcelable {
    public static final Parcelable.Creator<MusicItem> CREATOR = new a();
    public final transient String a;
    public final transient String b;
    public final transient String c;
    public final transient String d;
    public final transient String e;

    public class a implements Parcelable.Creator<MusicItem> {
        @Override // android.os.Parcelable.Creator
        public final MusicItem createFromParcel(Parcel parcel) {
            return new MusicItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final MusicItem[] newArray(int i) {
            return new MusicItem[i];
        }
    }

    public MusicItem(String str, String str2, String str3, String str4, String str5) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sbK = c.k("MusicItem{mMediaId='");
        sbK.append(this.a);
        sbK.append('\'');
        sbK.append(", mTitle='");
        sbK.append(this.b);
        sbK.append('\'');
        sbK.append(", mSinger='");
        sbK.append(this.c);
        sbK.append('\'');
        sbK.append(", mAlum='");
        sbK.append(this.d);
        sbK.append('\'');
        sbK.append(", mSongClass='");
        sbK.append(this.e);
        sbK.append('\'');
        sbK.append('}');
        return sbK.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    public MusicItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
