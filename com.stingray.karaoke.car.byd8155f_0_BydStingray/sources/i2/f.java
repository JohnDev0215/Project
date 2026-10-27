package i2;

import android.os.Parcel;
import android.os.Parcelable;
import e2.J;

/* JADX INFO: loaded from: classes.dex */
public final class f implements J {
    public static final Parcelable.Creator<f> CREATOR = new android.support.v4.media.a(11);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f15675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f15676n;

    public f(long j3, long j10, long j11) {
        this.l = j3;
        this.f15675m = j10;
        this.f15676n = j11;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.l == fVar.l && this.f15675m == fVar.f15675m && this.f15676n == fVar.f15676n;
    }

    public final int hashCode() {
        return C7.a.P(this.f15676n) + ((C7.a.P(this.f15675m) + ((C7.a.P(this.l) + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Mp4Timestamp: creation time=" + this.l + ", modification time=" + this.f15675m + ", timescale=" + this.f15676n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeLong(this.f15675m);
        parcel.writeLong(this.f15676n);
    }

    public f(Parcel parcel) {
        this.l = parcel.readLong();
        this.f15675m = parcel.readLong();
        this.f15676n = parcel.readLong();
    }
}
