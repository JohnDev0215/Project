package p;

import android.os.Parcel;
import android.os.Parcelable;
import com.spotify.connectivity.productstate.RxProductState;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class k2 implements Parcelable {
    public static final Parcelable.Creator<k2> CREATOR = new bg1(17);
    public final String c;
    public final String e;
    public final Date k;

    public k2(String str, String str2, Date date) {
        v03.j(str, RxProductState.Keys.KEY_TYPE);
        v03.j(str2, "token");
        v03.j(date, "expiresAt");
        this.c = str;
        this.e = str2;
        this.k = date;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k2)) {
            return false;
        }
        k2 k2Var = (k2) obj;
        return v03.b(this.c, k2Var.c) && v03.b(this.e, k2Var.e) && v03.b(this.k, k2Var.k);
    }

    public final int hashCode() {
        return this.k.hashCode() + hf2.b(this.e, this.c.hashCode() * 31, 31);
    }

    public final String toString() {
        return "AccessToken(type=" + this.c + ", token=" + this.e + ", expiresAt=" + this.k + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeSerializable(this.k);
    }
}
