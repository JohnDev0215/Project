package com.byd.cleardata.d;

import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PackageInfo f2584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Drawable f2585c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2586d;
    private boolean e;
    private boolean f;
    private String g;
    private long h;
    private long i;
    private long j;
    private long k;

    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    /* JADX INFO: renamed from: com.byd.cleardata.d.b$b, reason: collision with other inner class name */
    public static class C0090b implements Serializable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<b> f2587b;

        public List<b> a() {
            return this.f2587b;
        }

        public void b(List<b> list) {
            this.f2587b = list;
        }
    }

    public b() {
    }

    public b(PackageInfo packageInfo, Drawable drawable, String str, boolean z) {
        this.f2584b = packageInfo;
        this.f2585c = drawable;
        this.f2586d = str;
        this.e = z;
    }

    protected b(Parcel parcel) {
        this.f2584b = (PackageInfo) parcel.readParcelable(PackageInfo.class.getClassLoader());
        this.f2585c = (Drawable) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.f2586d = parcel.readString();
        this.e = parcel.readByte() != 0;
        this.f = parcel.readByte() != 0;
        this.g = parcel.readString();
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readLong();
        this.k = parcel.readLong();
    }

    public static Bitmap u(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public void A(long j) {
        this.h = j;
    }

    public void B(long j) {
        this.j = j;
    }

    public void C(boolean z) {
        this.f = z;
    }

    public void D(long j) {
        this.k = j;
    }

    public void E(boolean z) {
        this.e = z;
    }

    public void F(String str) {
        this.g = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "BydAppInfo{info=" + this.f2584b + ", icon=" + this.f2585c + ", appName='" + this.f2586d + "', systemApp=" + this.e + ", disable=" + this.f + ", version='" + this.g + "', codeBytes=" + this.h + ", cacheBytes=" + this.i + ", dataBytes=" + this.j + ", installTime=" + this.k + '}';
    }

    public String v() {
        return this.f2586d;
    }

    public long w() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2584b, i);
        parcel.writeParcelable(u(this.f2585c), i);
        parcel.writeString(this.f2586d);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f ? (byte) 1 : (byte) 0);
        parcel.writeString(this.g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeLong(this.j);
        parcel.writeLong(this.k);
    }

    public Drawable x() {
        return this.f2585c;
    }

    public PackageInfo y() {
        return this.f2584b;
    }

    public void z(long j) {
        this.i = j;
    }
}
