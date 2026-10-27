package Q2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.J;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public static final Parcelable.Creator<a> CREATOR = new g(19);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f6117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f6118n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f6119p;

    public a(long j3, long j10, long j11, long j12, long j13) {
        this.l = j3;
        this.f6117m = j10;
        this.f6118n = j11;
        this.o = j12;
        this.f6119p = j13;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.l == aVar.l && this.f6117m == aVar.f6117m && this.f6118n == aVar.f6118n && this.o == aVar.o && this.f6119p == aVar.f6119p;
    }

    public final int hashCode() {
        return C7.a.P(this.f6119p) + ((C7.a.P(this.o) + ((C7.a.P(this.f6118n) + ((C7.a.P(this.f6117m) + ((C7.a.P(this.l) + 527) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Motion photo metadata: photoStartPosition=" + this.l + ", photoSize=" + this.f6117m + ", photoPresentationTimestampUs=" + this.f6118n + ", videoStartPosition=" + this.o + ", videoSize=" + this.f6119p;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeLong(this.f6117m);
        parcel.writeLong(this.f6118n);
        parcel.writeLong(this.o);
        parcel.writeLong(this.f6119p);
    }

    public a(Parcel parcel) {
        this.l = parcel.readLong();
        this.f6117m = parcel.readLong();
        this.f6118n = parcel.readLong();
        this.o = parcel.readLong();
        this.f6119p = parcel.readLong();
    }
}
