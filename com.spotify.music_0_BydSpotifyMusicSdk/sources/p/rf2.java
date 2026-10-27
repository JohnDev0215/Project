package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class rf2 implements Parcelable {
    public static final Parcelable.Creator<rf2> CREATOR = new bg1(10);
    public int c;
    public int e;
    public int k;
    public int[] l;
    public int m;
    public int[] n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f180p;
    public boolean q;
    public boolean r;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeInt(this.e);
        parcel.writeInt(this.k);
        if (this.k > 0) {
            parcel.writeIntArray(this.l);
        }
        parcel.writeInt(this.m);
        if (this.m > 0) {
            parcel.writeIntArray(this.n);
        }
        parcel.writeInt(this.f180p ? 1 : 0);
        parcel.writeInt(this.q ? 1 : 0);
        parcel.writeInt(this.r ? 1 : 0);
        parcel.writeList(this.o);
    }
}
