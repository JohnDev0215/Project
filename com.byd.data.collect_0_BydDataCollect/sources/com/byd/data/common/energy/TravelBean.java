package com.byd.data.common.energy;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TravelBean implements Parcelable {
    public static final Parcelable.Creator<TravelBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f3363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f3367f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f3368g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f3369h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f3370i;
    private String j;
    private String k;
    private int l;
    private int m;
    private float n;
    private float o;
    private long p;
    private long q;
    private boolean r;

    class a implements Parcelable.Creator<TravelBean> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public TravelBean createFromParcel(Parcel parcel) {
            return new TravelBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public TravelBean[] newArray(int i2) {
            return new TravelBean[i2];
        }
    }

    public TravelBean() {
        this.f3364c = 0;
        this.f3365d = 0;
        this.f3366e = 0L;
        this.f3367f = 0L;
        this.f3368g = 0.0f;
        this.f3369h = 0.0f;
        this.f3370i = 0.0f;
        this.j = "";
        this.k = "";
        this.l = 0;
        this.m = 0;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0L;
        this.q = 0L;
        this.r = false;
    }

    public void A(float f2) {
        this.n = f2;
    }

    public void B(float f2) {
        this.o = f2;
    }

    public void C(long j) {
        this.f3363b = j;
    }

    public void D(int i2) {
        this.l = i2;
    }

    public void E(int i2) {
        this.m = i2;
    }

    public void F(long j) {
        this.p = j;
    }

    public void G(long j) {
        this.q = j;
    }

    public void H(float f2) {
        this.f3368g = f2;
    }

    public void I(float f2) {
        this.f3370i = f2;
    }

    public void J(long j) {
        this.f3366e = j;
    }

    public void K(String str) {
        this.j = str;
    }

    public void L(String str) {
        this.k = str;
    }

    public void M(int i2) {
        this.f3364c = i2;
    }

    public float a() {
        return this.f3369h;
    }

    public long c() {
        return this.f3367f;
    }

    public int d() {
        return this.f3365d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float f() {
        return this.n;
    }

    public float j() {
        return this.o;
    }

    public int l() {
        return this.l;
    }

    public int p() {
        return this.m;
    }

    public long q() {
        return this.p;
    }

    public long r() {
        return this.q;
    }

    public float s() {
        return this.f3368g;
    }

    public float t() {
        return this.f3370i;
    }

    public String toString() {
        return "TravelBean{id=" + this.f3363b + ", type=" + this.f3364c + ", flag=" + this.f3365d + ", startTime=" + this.f3366e + ", endTime=" + this.f3367f + ", mileage=" + this.f3368g + ", elecCons=" + this.f3369h + ", oilCons=" + this.f3370i + ", string0='" + this.j + "', string1='" + this.k + "', int0=" + this.l + ", int1=" + this.m + ", float0=" + this.n + ", float1=" + this.o + ", long0=" + this.p + ", long1=" + this.q + ", isSelected=" + this.r + '}';
    }

    public long u() {
        return this.f3366e;
    }

    public String v() {
        return this.j;
    }

    public String w() {
        return this.k;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f3363b);
        parcel.writeInt(this.f3364c);
        parcel.writeInt(this.f3365d);
        parcel.writeLong(this.f3366e);
        parcel.writeLong(this.f3367f);
        parcel.writeFloat(this.f3368g);
        parcel.writeFloat(this.f3369h);
        parcel.writeFloat(this.f3370i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeFloat(this.n);
        parcel.writeFloat(this.o);
        parcel.writeLong(this.p);
        parcel.writeLong(this.q);
    }

    public int x() {
        return this.f3364c;
    }

    public void y(float f2) {
        this.f3369h = f2;
    }

    public void z(long j) {
        this.f3367f = j;
    }

    protected TravelBean(Parcel parcel) {
        this.f3364c = 0;
        this.f3365d = 0;
        this.f3366e = 0L;
        this.f3367f = 0L;
        this.f3368g = 0.0f;
        this.f3369h = 0.0f;
        this.f3370i = 0.0f;
        this.j = "";
        this.k = "";
        this.l = 0;
        this.m = 0;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0L;
        this.q = 0L;
        this.r = false;
        this.f3363b = parcel.readLong();
        this.f3364c = parcel.readInt();
        this.f3365d = parcel.readInt();
        this.f3366e = parcel.readLong();
        this.f3367f = parcel.readLong();
        this.f3368g = parcel.readFloat();
        this.f3369h = parcel.readFloat();
        this.f3370i = parcel.readFloat();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readFloat();
        this.o = parcel.readFloat();
        this.p = parcel.readLong();
        this.q = parcel.readLong();
    }
}
