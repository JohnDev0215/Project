package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes.dex */
public class CallLogInfo implements Parcelable, Comparable<CallLogInfo> {
    public static final Parcelable.Creator<CallLogInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(unique = true)
    private long f3553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Column(defaultValue = "未知")
    private String f3554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3556e;
    private long f;

    @Column(defaultValue = "-1")
    private int g;

    @Column(defaultValue = "3")
    private String h;
    private int i;
    private String j;

    class a implements Parcelable.Creator<CallLogInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CallLogInfo createFromParcel(Parcel parcel) {
            return new CallLogInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CallLogInfo[] newArray(int i) {
            return new CallLogInfo[i];
        }
    }

    public CallLogInfo() {
        this.f3554c = "";
        this.f3555d = "unknown";
        this.f3556e = "unknown";
        this.f = 0L;
        this.h = "unknown";
        this.i = 1;
        this.j = "unknown";
    }

    public void A(String str) {
        this.h = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CallLogInfo) {
            CallLogInfo callLogInfo = (CallLogInfo) obj;
            if (n().equals(callLogInfo.n()) && o().equals(callLogInfo.o()) && r().equals(callLogInfo.r())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (simpleDateFormat.format(Long.valueOf(this.f)).equals(simpleDateFormat.format(Long.valueOf(callLogInfo.q())))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public int compareTo(CallLogInfo callLogInfo) {
        long jQ = callLogInfo.q() - q();
        if (jQ > 0) {
            return 1;
        }
        return jQ == 0 ? 0 : -1;
    }

    public int j() {
        return this.i;
    }

    public long k() {
        return this.f3553b;
    }

    public int l() {
        return this.g;
    }

    public String m() {
        return this.j;
    }

    public String n() {
        return this.f3554c;
    }

    public String o() {
        return this.f3555d;
    }

    public String p() {
        return this.f3556e;
    }

    public long q() {
        return this.f;
    }

    public String r() {
        return this.h;
    }

    public void s(int i) {
        this.i = i;
    }

    public void t(long j) {
        this.f3553b = j;
    }

    public String toString() {
        return "CallLogInfo{id=" + this.f3553b + ", name='" + this.f3554c + "', number='" + this.f3555d + "', sortLetters='" + this.f3556e + "', time=" + this.f + ", imageId=" + this.g + ", type='" + this.h + "', cardinal_number=" + this.i + ", mac='" + this.j + "'}";
    }

    public void u(int i) {
        this.g = i;
    }

    public void v(String str) {
        this.j = str;
    }

    public void w(String str) {
        this.f3554c = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f3553b);
        parcel.writeString(this.f3554c);
        parcel.writeString(this.f3555d);
        parcel.writeString(this.f3556e);
        parcel.writeLong(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.i);
        parcel.writeString(this.h);
        parcel.writeString(this.j);
    }

    public void x(String str) {
        this.f3555d = str;
    }

    public void y(String str) {
        this.f3556e = str;
    }

    public void z(long j) {
        this.f = j;
    }

    public CallLogInfo(Parcel parcel) {
        this.f3554c = "";
        this.f3555d = "unknown";
        this.f3556e = "unknown";
        this.f = 0L;
        this.h = "unknown";
        this.i = 1;
        this.j = "unknown";
        this.f3553b = parcel.readLong();
        this.f3554c = parcel.readString();
        this.f3555d = parcel.readString();
        this.f3556e = parcel.readString();
        this.f = parcel.readLong();
        this.g = parcel.readInt();
        this.i = parcel.readInt();
        this.h = parcel.readString();
        this.j = parcel.readString();
    }
}
