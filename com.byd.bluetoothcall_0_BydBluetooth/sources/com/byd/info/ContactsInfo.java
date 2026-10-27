package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes.dex */
public class ContactsInfo implements Parcelable {
    public static final Parcelable.Creator<ContactsInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(unique = true)
    private long f3557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Column(defaultValue = "未知")
    private String f3558c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3559d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3560e;
    private String f;
    private long g;
    private String h;

    @Column(defaultValue = "-1")
    private int i;

    @Column(defaultValue = "-1")
    private int j;
    private String k;
    private String l;

    class a implements Parcelable.Creator<ContactsInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ContactsInfo createFromParcel(Parcel parcel) {
            return new ContactsInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ContactsInfo[] newArray(int i) {
            return new ContactsInfo[i];
        }
    }

    public ContactsInfo() {
        this.f3558c = "unknown";
        this.f3559d = "";
        this.f3560e = "";
        this.f = "";
        this.g = 0L;
        this.h = "";
        this.i = -1;
        this.j = -1;
        this.k = "";
        this.l = "";
    }

    public String a() {
        return this.l;
    }

    public int c() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String f() {
        return this.h;
    }

    public String i() {
        return this.k;
    }

    public String j() {
        return this.f3558c;
    }

    public String k() {
        return this.f3559d;
    }

    public String l() {
        return this.f;
    }

    public String m() {
        return this.f3560e;
    }

    public void n(String str) {
        this.l = str;
    }

    public void o(int i) {
        this.i = i;
    }

    public void p(String str) {
        this.h = str;
    }

    public void q(String str) {
        this.k = str;
    }

    public void r(String str) {
        this.f3558c = str;
    }

    public void s(String str) {
        this.f3559d = str;
    }

    public void t(String str) {
        this.f = str;
    }

    public String toString() {
        return "ContactsInfo{id=" + this.f3557b + ", name='" + this.f3558c + "', number='" + this.f3559d + "', sortLetters='" + this.f3560e + "', pinyin='" + this.f + "', time=" + this.g + ", imageId=" + this.i + ", type=" + this.j + ", mac='" + this.k + "', first_word='" + this.l + "'}";
    }

    public void u(String str) {
        this.f3560e = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f3557b);
        parcel.writeString(this.f3558c);
        parcel.writeString(this.f3559d);
        parcel.writeString(this.f3560e);
        parcel.writeString(this.f);
        parcel.writeLong(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
    }

    protected ContactsInfo(Parcel parcel) {
        this.f3558c = "unknown";
        this.f3559d = "";
        this.f3560e = "";
        this.f = "";
        this.g = 0L;
        this.h = "";
        this.i = -1;
        this.j = -1;
        this.k = "";
        this.l = "";
        this.f3557b = parcel.readLong();
        this.f3558c = parcel.readString();
        this.f3559d = parcel.readString();
        this.f3560e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readLong();
        this.h = parcel.readString();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readString();
        this.l = parcel.readString();
    }
}
