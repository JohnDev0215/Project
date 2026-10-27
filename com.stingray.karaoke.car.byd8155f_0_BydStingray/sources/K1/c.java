package K1;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class c implements Parcelable {
    public final Parcelable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f3507m = new a();
    public static final Parcelable.Creator<c> CREATOR = new b(0);

    public c() {
        this.l = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.l, i7);
    }

    public c(Parcelable parcelable) {
        if (parcelable != null) {
            this.l = parcelable == f3507m ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public c(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.l = parcelable == null ? f3507m : parcelable;
    }
}
