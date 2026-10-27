package com.byd.uereport.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ReportBean implements Parcelable {
    public static final Parcelable.Creator<ReportBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3158g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3159h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f3160i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3161j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3162k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3163l;

    public class a implements Parcelable.Creator<ReportBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ReportBean createFromParcel(Parcel parcel) {
            return new ReportBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ReportBean[] newArray(int i8) {
            return new ReportBean[i8];
        }
    }

    public ReportBean(int i8, String str, int i9, long j8, int i10, int i11) {
        this.f3155d = -1;
        this.f3155d = i8;
        this.f3157f = str;
        this.f3158g = i9;
        this.f3160i = j8;
        this.f3159h = i10;
        this.f3161j = i11;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ReportBean{voiceResID=" + this.f3155d + ", reportText='" + this.f3157f + ", playTimes=" + this.f3158g + ", isDMSType=" + this.f3159h + ", playInterval=" + this.f3160i + ", event=" + this.f3161j + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f3155d);
        parcel.writeString(this.f3157f);
        parcel.writeInt(this.f3158g);
        parcel.writeLong(this.f3160i);
        parcel.writeSerializable(Integer.valueOf(this.f3159h));
        parcel.writeInt(this.f3161j);
        parcel.writeInt(this.f3162k);
        parcel.writeInt(this.f3163l);
        parcel.writeString(this.f3156e);
    }

    public ReportBean(String str, String str2, int i8, long j8, int i9, int i10) {
        this.f3155d = -1;
        this.f3156e = str;
        this.f3157f = str2;
        this.f3158g = i8;
        this.f3160i = j8;
        this.f3159h = i9;
        this.f3161j = i10;
    }

    public ReportBean(Parcel parcel) {
        this.f3155d = -1;
        this.f3155d = parcel.readInt();
        this.f3157f = parcel.readString();
        this.f3158g = parcel.readInt();
        this.f3159h = parcel.readInt();
        this.f3160i = parcel.readLong();
        this.f3161j = parcel.readInt();
        this.f3162k = parcel.readInt();
        this.f3163l = parcel.readInt();
        this.f3156e = parcel.readString();
    }
}
