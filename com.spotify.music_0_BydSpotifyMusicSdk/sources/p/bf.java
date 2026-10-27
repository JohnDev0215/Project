package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class bf implements Parcelable {
    public static final Parcelable.Creator<bf> CREATOR = new bg1(19);
    public static final bf l;
    public final String c;
    public final k2 e;
    public final re k;

    static {
        byte[] bytes = "".getBytes(cs.a);
        v03.i(bytes, "this as java.lang.String).getBytes(charset)");
        l = new bf("", null, new qe("", bytes));
    }

    public bf(String str, k2 k2Var, re reVar) {
        v03.j(str, "username");
        v03.j(reVar, "authBlob");
        this.c = str;
        this.e = k2Var;
        this.k = reVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bf)) {
            return false;
        }
        bf bfVar = (bf) obj;
        return v03.b(this.c, bfVar.c) && v03.b(this.e, bfVar.e) && v03.b(this.k, bfVar.k);
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() * 31;
        k2 k2Var = this.e;
        return this.k.hashCode() + ((iHashCode + (k2Var == null ? 0 : k2Var.hashCode())) * 31);
    }

    public final String toString() {
        return "AuthUserInfo(username=" + this.c + ", accessToken=" + this.e + ", authBlob=" + this.k + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.c);
        k2 k2Var = this.e;
        if (k2Var == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            k2Var.writeToParcel(parcel, i);
        }
        parcel.writeParcelable(this.k, i);
    }
}
