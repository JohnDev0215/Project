package s3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Parcelable {
    public static final Parcelable.Creator<a0> CREATOR = new android.support.v4.media.a(27);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22246m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22247n;
    public int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22248p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int[] f22249q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f22250r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f22251s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f22252t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f22253u;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeInt(this.f22246m);
        parcel.writeInt(this.f22247n);
        if (this.f22247n > 0) {
            parcel.writeIntArray(this.o);
        }
        parcel.writeInt(this.f22248p);
        if (this.f22248p > 0) {
            parcel.writeIntArray(this.f22249q);
        }
        parcel.writeInt(this.f22251s ? 1 : 0);
        parcel.writeInt(this.f22252t ? 1 : 0);
        parcel.writeInt(this.f22253u ? 1 : 0);
        parcel.writeList(this.f22250r);
    }
}
