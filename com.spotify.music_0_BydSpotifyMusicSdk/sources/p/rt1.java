package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class rt1 implements Parcelable {
    public static final Parcelable.Creator<rt1> CREATOR = new bg1(23);
    public final String c;
    public final Integer e;
    public final String k;
    public final String l;
    public final int m;

    public rt1(String str, Integer num, String str2, String str3, int i) {
        this.c = str;
        this.e = num;
        this.k = str2;
        this.l = str3;
        this.m = i;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rt1)) {
            return false;
        }
        rt1 rt1Var = (rt1) obj;
        return f60.p(this.c, rt1Var.c) && f60.p(this.e, rt1Var.e) && f60.p(this.k, rt1Var.k) && f60.p(this.l, rt1Var.l) && this.m == rt1Var.m;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.c, this.e, this.k, this.l, Integer.valueOf(this.m)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeSerializable(this.e);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
    }

    public rt1(Parcel parcel) {
        this.c = parcel.readString();
        this.e = (Integer) parcel.readSerializable();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readInt();
    }
}
