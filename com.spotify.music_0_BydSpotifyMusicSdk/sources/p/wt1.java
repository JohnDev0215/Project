package p;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class wt1 implements Parcelable {
    public static final Parcelable.Creator<wt1> CREATOR = new bg1(24);
    public final String c;
    public final String e;
    public final ImmutableMap k;
    public final iu1 l;
    public final xt1[] m;
    public final xt1[] n;

    public wt1(Parcel parcel) {
        this.c = parcel.readString();
        this.e = parcel.readString();
        int i = parcel.readInt();
        com.google.common.collect.b bVarA = ImmutableMap.a();
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                this.k = bVarA.a();
                this.l = (iu1) ((Parcelable) parcel.readTypedObject(iu1.CREATOR));
                Parcelable.Creator<xt1> creator = xt1.CREATOR;
                this.m = (xt1[]) parcel.createTypedArray(creator);
                this.n = (xt1[]) parcel.createTypedArray(creator);
                return;
            }
            bVarA.b(parcel.readString(), parcel.readString());
            i = i2;
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wt1)) {
            return false;
        }
        wt1 wt1Var = (wt1) obj;
        if (!this.c.equals(wt1Var.c)) {
            return false;
        }
        String str = wt1Var.e;
        String str2 = this.e;
        if (str2 == null ? str != null : !str2.equals(str)) {
            return false;
        }
        if (!this.k.equals(wt1Var.k)) {
            return false;
        }
        iu1 iu1Var = wt1Var.l;
        iu1 iu1Var2 = this.l;
        if (iu1Var2 == null ? iu1Var != null : !iu1Var2.equals(iu1Var)) {
            return false;
        }
        if (Arrays.equals(this.m, wt1Var.m)) {
            return Arrays.equals(this.n, wt1Var.n);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() * 31;
        String str = this.e;
        int iHashCode2 = (this.k.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31)) * 31;
        iu1 iu1Var = this.l;
        return ((((iHashCode2 + (iu1Var != null ? iu1Var.hashCode() : 0)) * 31) + Arrays.hashCode(this.m)) * 31) + Arrays.hashCode(this.n);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        ux2.C(parcel, this.k);
        parcel.writeTypedObject(this.l, i);
        parcel.writeTypedArray(this.m, i);
        parcel.writeTypedArray(this.n, i);
    }

    public wt1(String str, Map map, iu1 iu1Var, xt1[] xt1VarArr, xt1[] xt1VarArr2, String str2) {
        this.c = str;
        this.e = str2;
        if (map != null && !map.isEmpty()) {
            this.k = ImmutableMap.b(map);
        } else {
            this.k = ImmutableMap.d();
        }
        this.l = iu1Var;
        this.m = xt1VarArr;
        this.n = xt1VarArr2;
    }
}
