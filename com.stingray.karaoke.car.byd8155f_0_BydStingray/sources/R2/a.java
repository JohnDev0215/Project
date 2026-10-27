package R2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b {
    public static final Parcelable.Creator<a> CREATOR = new I1.g(23);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f7357m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final byte[] f7358n;

    public a(long j3, byte[] bArr, long j10) {
        this.l = j10;
        this.f7357m = j3;
        this.f7358n = bArr;
    }

    @Override // R2.b
    public final String toString() {
        StringBuilder sb = new StringBuilder("SCTE-35 PrivateCommand { ptsAdjustment=");
        sb.append(this.l);
        sb.append(", identifier= ");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f7357m, " }", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeLong(this.f7357m);
        parcel.writeByteArray(this.f7358n);
    }

    public a(Parcel parcel) {
        this.l = parcel.readLong();
        this.f7357m = parcel.readLong();
        byte[] bArrCreateByteArray = parcel.createByteArray();
        int i7 = AbstractC1343A.f15195a;
        this.f7358n = bArrCreateByteArray;
    }
}
