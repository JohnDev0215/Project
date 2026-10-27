package s3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements Parcelable {
    public static final Parcelable.Creator<Z> CREATOR = new android.support.v4.media.a(26);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22242m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f22243n;
    public boolean o;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.l + ", mGapDir=" + this.f22242m + ", mHasUnwantedGapAfter=" + this.o + ", mGapPerSpan=" + Arrays.toString(this.f22243n) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f22242m);
        parcel.writeInt(this.o ? 1 : 0);
        int[] iArr = this.f22243n;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.f22243n);
        }
    }
}
