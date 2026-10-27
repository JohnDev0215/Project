package s3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: s3.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2400t implements Parcelable {
    public static final Parcelable.Creator<C2400t> CREATOR = new android.support.v4.media.a(25);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22349m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22350n;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f22349m);
        parcel.writeInt(this.f22350n ? 1 : 0);
    }
}
