package Q2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new g(21);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f6120m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f6121n;

    public b(int i7, long j3, long j10) {
        AbstractC1346c.c(j3 < j10);
        this.l = j3;
        this.f6120m = j10;
        this.f6121n = i7;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.l == bVar.l && this.f6120m == bVar.f6120m && this.f6121n == bVar.f6121n;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.l), Long.valueOf(this.f6120m), Integer.valueOf(this.f6121n)});
    }

    public final String toString() {
        int i7 = AbstractC1343A.f15195a;
        Locale locale = Locale.US;
        return "Segment: startTimeMs=" + this.l + ", endTimeMs=" + this.f6120m + ", speedDivisor=" + this.f6121n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeLong(this.f6120m);
        parcel.writeInt(this.f6121n);
    }
}
