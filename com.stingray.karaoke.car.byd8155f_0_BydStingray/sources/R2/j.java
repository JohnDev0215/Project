package R2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.t;

/* JADX INFO: loaded from: classes.dex */
public final class j extends b {
    public static final Parcelable.Creator<j> CREATOR = new I1.g(27);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f7384m;

    public j(long j3, long j10) {
        this.l = j3;
        this.f7384m = j10;
    }

    public static long a(long j3, t tVar) {
        long jV = tVar.v();
        if ((128 & jV) != 0) {
            return 8589934591L & ((((jV & 1) << 32) | tVar.x()) + j3);
        }
        return -9223372036854775807L;
    }

    @Override // R2.b
    public final String toString() {
        StringBuilder sb = new StringBuilder("SCTE-35 TimeSignalCommand { ptsTime=");
        sb.append(this.l);
        sb.append(", playbackPositionUs= ");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f7384m, " }", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeLong(this.f7384m);
    }
}
