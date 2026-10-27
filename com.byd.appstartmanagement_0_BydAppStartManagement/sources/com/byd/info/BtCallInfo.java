package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.EnvironmentCompat;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes2.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(unique = true)
    public long f608b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Column(defaultValue = "未知")
    public String f609c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f610d;
    public int e;
    public int f;
    public boolean g;
    public String h;

    public class a implements Parcelable.Creator<BtCallInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BtCallInfo createFromParcel(Parcel parcel) {
            return new BtCallInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BtCallInfo[] newArray(int i) {
            return new BtCallInfo[i];
        }
    }

    public BtCallInfo() {
        this.f609c = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f610d = EnvironmentCompat.MEDIA_UNKNOWN;
        this.e = -1;
        this.f = 0;
        this.g = true;
        this.h = "";
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(BtCallInfo btCallInfo) {
        long jD = btCallInfo.d() - d();
        if (jD > 0) {
            return 1;
        }
        return jD == 0 ? 0 : -1;
    }

    public String b() {
        return this.f609c;
    }

    public String c() {
        return this.f610d;
    }

    public int d() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BtCallInfo) {
            BtCallInfo btCallInfo = (BtCallInfo) obj;
            if (b().equals(btCallInfo.b()) && c().equals(btCallInfo.c())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "BtCallInfo{id=" + this.f608b + ", name='" + this.f609c + "', number='" + this.f610d + "', state=" + this.e + ", time=" + this.f + ", idle=" + this.g + ", date='" + this.h + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f608b);
        parcel.writeString(this.f609c);
        parcel.writeString(this.f610d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeBoolean(this.g);
        parcel.writeString(this.h);
    }

    public BtCallInfo(Parcel parcel) {
        this.f609c = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f610d = EnvironmentCompat.MEDIA_UNKNOWN;
        this.e = -1;
        this.f = 0;
        this.g = true;
        this.h = "";
        this.f608b = parcel.readLong();
        this.f609c = parcel.readString();
        this.f610d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readBoolean();
        this.h = parcel.readString();
    }
}
