package l3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: l3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1753i implements Parcelable {
    public static final Parcelable.Creator<C1753i> CREATOR = new android.support.v4.media.a(14);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1740E f18576m;

    public C1753i(Parcel parcel) {
        this.l = parcel.readInt();
        this.f18576m = C1740E.CREATOR.createFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaItem{mFlags=" + this.l + ", mDescription=" + this.f18576m + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        this.f18576m.writeToParcel(parcel, i7);
    }
}
