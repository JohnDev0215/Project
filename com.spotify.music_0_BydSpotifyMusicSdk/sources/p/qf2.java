package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class qf2 implements Parcelable {
    public static final Parcelable.Creator<qf2> CREATOR = new bg1(9);
    public int c;
    public int e;
    public int[] k;
    public boolean l;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.c + ", mGapDir=" + this.e + ", mHasUnwantedGapAfter=" + this.l + ", mGapPerSpan=" + Arrays.toString(this.k) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.e);
        parcel.writeInt(this.l ? 1 : 0);
        int[] iArr = this.k;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.k);
        }
    }
}
