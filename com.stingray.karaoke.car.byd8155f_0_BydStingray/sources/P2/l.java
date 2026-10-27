package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class l extends i {
    public static final Parcelable.Creator<l> CREATOR = new I1.g(15);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f5320m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f5321n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int[] f5322p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int[] f5323q;

    public l(int i7, int i10, int i11, int[] iArr, int[] iArr2) {
        super("MLLT");
        this.f5320m = i7;
        this.f5321n = i10;
        this.o = i11;
        this.f5322p = iArr;
        this.f5323q = iArr2;
    }

    @Override // P2.i, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        return this.f5320m == lVar.f5320m && this.f5321n == lVar.f5321n && this.o == lVar.o && Arrays.equals(this.f5322p, lVar.f5322p) && Arrays.equals(this.f5323q, lVar.f5323q);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f5323q) + ((Arrays.hashCode(this.f5322p) + ((((((527 + this.f5320m) * 31) + this.f5321n) * 31) + this.o) * 31)) * 31);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f5320m);
        parcel.writeInt(this.f5321n);
        parcel.writeInt(this.o);
        parcel.writeIntArray(this.f5322p);
        parcel.writeIntArray(this.f5323q);
    }

    public l(Parcel parcel) {
        super("MLLT");
        this.f5320m = parcel.readInt();
        this.f5321n = parcel.readInt();
        this.o = parcel.readInt();
        int[] iArrCreateIntArray = parcel.createIntArray();
        int i7 = AbstractC1343A.f15195a;
        this.f5322p = iArrCreateIntArray;
        this.f5323q = parcel.createIntArray();
    }
}
