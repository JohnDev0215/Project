package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ip implements Parcelable {
    public static final Parcelable.Creator<ip> CREATOR = new bg1(13);
    public final bl1 c;
    public final bl1 e;
    public final hp k;
    public final bl1 l;
    public final int m;
    public final int n;

    public ip(bl1 bl1Var, bl1 bl1Var2, hp hpVar, bl1 bl1Var3) {
        this.c = bl1Var;
        this.e = bl1Var2;
        this.l = bl1Var3;
        this.k = hpVar;
        if (bl1Var3 != null && bl1Var.c.compareTo(bl1Var3.c) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (bl1Var3 != null && bl1Var3.c.compareTo(bl1Var2.c) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.n = bl1Var.e(bl1Var2) + 1;
        this.m = (bl1Var2.k - bl1Var.k) + 1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ip)) {
            return false;
        }
        ip ipVar = (ip) obj;
        return this.c.equals(ipVar.c) && this.e.equals(ipVar.e) && ap1.a(this.l, ipVar.l) && this.k.equals(ipVar.k);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.c, this.e, this.l, this.k});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, 0);
        parcel.writeParcelable(this.e, 0);
        parcel.writeParcelable(this.l, 0);
        parcel.writeParcelable(this.k, 0);
    }
}
