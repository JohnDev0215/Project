package e2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements Comparable, Parcelable {
    public static final Parcelable.Creator<Z> CREATOR = new android.support.v4.media.a(7);
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f14017p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f14018q;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14019m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f14020n;

    static {
        int i7 = AbstractC1343A.f15195a;
        o = Integer.toString(0, 36);
        f14017p = Integer.toString(1, 36);
        f14018q = Integer.toString(2, 36);
    }

    public Z(int i7, int i10, int i11) {
        this.l = i7;
        this.f14019m = i10;
        this.f14020n = i11;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        Z z6 = (Z) obj;
        int i7 = this.l - z6.l;
        if (i7 != 0) {
            return i7;
        }
        int i10 = this.f14019m - z6.f14019m;
        return i10 == 0 ? this.f14020n - z6.f14020n : i10;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Z.class != obj.getClass()) {
            return false;
        }
        Z z6 = (Z) obj;
        return this.l == z6.l && this.f14019m == z6.f14019m && this.f14020n == z6.f14020n;
    }

    public final int hashCode() {
        return (((this.l * 31) + this.f14019m) * 31) + this.f14020n;
    }

    public final String toString() {
        return this.l + "." + this.f14019m + "." + this.f14020n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f14019m);
        parcel.writeInt(this.f14020n);
    }

    public Z(Parcel parcel) {
        this.l = parcel.readInt();
        this.f14019m = parcel.readInt();
        this.f14020n = parcel.readInt();
    }
}
