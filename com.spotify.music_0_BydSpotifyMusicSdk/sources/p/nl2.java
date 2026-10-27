package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class nl2 implements Parcelable {
    public static final Parcelable.Creator<nl2> CREATOR = new bg1(16);
    public final int c;
    public final int e;
    public final int k;
    public final int l;

    public nl2(Parcel parcel) {
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        this.e = i;
        this.k = i2;
        this.l = i3;
        this.c = i4;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nl2)) {
            return false;
        }
        nl2 nl2Var = (nl2) obj;
        return this.e == nl2Var.e && this.k == nl2Var.k && this.c == nl2Var.c && this.l == nl2Var.l;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.e), Integer.valueOf(this.k), Integer.valueOf(this.l)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.c);
    }
}
