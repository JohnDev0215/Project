package l3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class V implements Parcelable {
    public static final Parcelable.Creator<V> CREATOR = new android.support.v4.media.a(20);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f18546m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f18547n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f18548p;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f18547n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.f18548p);
        parcel.writeInt(this.f18546m);
    }
}
