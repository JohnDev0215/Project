package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class gm2 extends x0 {
    public static final Parcelable.Creator<gm2> CREATOR = new ya2(1);
    public int k;
    public boolean l;

    public gm2(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = parcel.readInt();
        this.l = parcel.readInt() != 0;
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l ? 1 : 0);
    }
}
