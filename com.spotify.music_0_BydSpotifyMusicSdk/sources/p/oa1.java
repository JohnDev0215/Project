package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class oa1 implements Parcelable {
    public static final Parcelable.Creator<oa1> CREATOR = new bg1(8);
    public int c;
    public int e;
    public boolean k;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.e);
        parcel.writeInt(this.k ? 1 : 0);
    }
}
