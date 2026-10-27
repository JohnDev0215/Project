package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class af1 extends x0 {
    public static final Parcelable.Creator<af1> CREATOR = new ya2(7);
    public boolean k;

    public af1(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        if (classLoader == null) {
            af1.class.getClassLoader();
        }
        this.k = parcel.readInt() == 1;
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.k ? 1 : 0);
    }
}
