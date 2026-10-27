package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class ds extends x0 {
    public static final Parcelable.Creator<ds> CREATOR = new ya2(8);
    public boolean k;

    public ds(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = parcel.readInt() == 1;
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.k ? 1 : 0);
    }
}
