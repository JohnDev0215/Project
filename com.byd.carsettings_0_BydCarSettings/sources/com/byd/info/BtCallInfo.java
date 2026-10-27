package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.EnvironmentCompat;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes2.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Column(unique = true)
    public long f3649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(defaultValue = "未知")
    public String f3650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3651c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3654f;
    public String g;

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
        this.f3650b = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f3651c = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f3652d = -1;
        this.f3653e = 0;
        this.f3654f = true;
        this.g = "";
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
        return this.f3650b;
    }

    public String c() {
        return this.f3651c;
    }

    public int d() {
        return this.f3653e;
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
        return "BtCallInfo{id=" + this.f3649a + ", name='" + this.f3650b + "', number='" + this.f3651c + "', state=" + this.f3652d + ", time=" + this.f3653e + ", idle=" + this.f3654f + ", date='" + this.g + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f3649a);
        parcel.writeString(this.f3650b);
        parcel.writeString(this.f3651c);
        parcel.writeInt(this.f3652d);
        parcel.writeInt(this.f3653e);
        parcel.writeBoolean(this.f3654f);
        parcel.writeString(this.g);
    }

    public BtCallInfo(Parcel parcel) {
        this.f3650b = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f3651c = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f3652d = -1;
        this.f3653e = 0;
        this.f3654f = true;
        this.g = "";
        this.f3649a = parcel.readLong();
        this.f3650b = parcel.readString();
        this.f3651c = parcel.readString();
        this.f3652d = parcel.readInt();
        this.f3653e = parcel.readInt();
        this.f3654f = parcel.readBoolean();
        this.g = parcel.readString();
    }
}
