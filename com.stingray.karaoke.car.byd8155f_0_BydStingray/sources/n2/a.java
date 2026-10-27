package N2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.H;
import e2.J;
import e2.L;
import h2.AbstractC1343A;
import h2.t;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public static final Parcelable.Creator<a> CREATOR = new g(4);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f4565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f4566n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f4567p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f4568q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f4569r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final byte[] f4570s;

    public a(int i7, String str, String str2, int i10, int i11, int i12, int i13, byte[] bArr) {
        this.l = i7;
        this.f4565m = str;
        this.f4566n = str2;
        this.o = i10;
        this.f4567p = i11;
        this.f4568q = i12;
        this.f4569r = i13;
        this.f4570s = bArr;
    }

    public static a a(t tVar) {
        int iH = tVar.h();
        String strL = L.l(tVar.t(tVar.h(), StandardCharsets.US_ASCII));
        String strT = tVar.t(tVar.h(), StandardCharsets.UTF_8);
        int iH2 = tVar.h();
        int iH3 = tVar.h();
        int iH4 = tVar.h();
        int iH5 = tVar.h();
        int iH6 = tVar.h();
        byte[] bArr = new byte[iH6];
        tVar.f(0, iH6, bArr);
        return new a(iH, strL, strT, iH2, iH3, iH4, iH5, bArr);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e2.J
    public final void e(H h10) {
        h10.a(this.l, this.f4570s);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.l == aVar.l && this.f4565m.equals(aVar.f4565m) && this.f4566n.equals(aVar.f4566n) && this.o == aVar.o && this.f4567p == aVar.f4567p && this.f4568q == aVar.f4568q && this.f4569r == aVar.f4569r && Arrays.equals(this.f4570s, aVar.f4570s);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f4570s) + ((((((((M5.b.c(this.f4566n, M5.b.c(this.f4565m, (527 + this.l) * 31, 31), 31) + this.o) * 31) + this.f4567p) * 31) + this.f4568q) * 31) + this.f4569r) * 31);
    }

    public final String toString() {
        return "Picture: mimeType=" + this.f4565m + ", description=" + this.f4566n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeString(this.f4565m);
        parcel.writeString(this.f4566n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.f4567p);
        parcel.writeInt(this.f4568q);
        parcel.writeInt(this.f4569r);
        parcel.writeByteArray(this.f4570s);
    }

    public a(Parcel parcel) {
        this.l = parcel.readInt();
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f4565m = string;
        this.f4566n = parcel.readString();
        this.o = parcel.readInt();
        this.f4567p = parcel.readInt();
        this.f4568q = parcel.readInt();
        this.f4569r = parcel.readInt();
        this.f4570s = parcel.createByteArray();
    }
}
