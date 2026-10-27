package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class q7 extends x0 {
    public static final Parcelable.Creator<q7> CREATOR = new ya2(5);
    public final boolean k;
    public final int l;
    public final float m;
    public final boolean n;

    public q7(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = parcel.readByte() != 0;
        this.l = parcel.readInt();
        this.m = parcel.readFloat();
        this.n = parcel.readByte() != 0;
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.l);
        parcel.writeFloat(this.m);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
    }
}
