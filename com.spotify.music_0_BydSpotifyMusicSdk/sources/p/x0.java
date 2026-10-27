package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class x0 implements Parcelable {
    public final Parcelable c;
    public static final w0 e = new w0();
    public static final Parcelable.Creator<x0> CREATOR = new ya2(3);

    public x0() {
        this.c = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
    }

    public x0(Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.c = parcelable == e ? null : parcelable;
    }

    public x0(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.c = parcelable == null ? e : parcelable;
    }
}
