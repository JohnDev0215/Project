package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new a(3);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f11251m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11252n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f11253p;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f11252n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.f11253p);
        parcel.writeInt(this.f11251m);
    }
}
