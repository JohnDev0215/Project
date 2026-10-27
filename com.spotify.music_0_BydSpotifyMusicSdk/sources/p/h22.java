package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class h22 extends x0 {
    public static final Parcelable.Creator<h22> CREATOR = new ya2(4);
    public Parcelable k;

    public h22(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.k = parcel.readParcelable(classLoader == null ? y12.class.getClassLoader() : classLoader);
    }

    @Override // p.x0, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeParcelable(this.k, 0);
    }
}
