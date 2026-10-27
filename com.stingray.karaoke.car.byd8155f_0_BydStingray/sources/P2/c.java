package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i {
    public static final Parcelable.Creator<c> CREATOR = new I1.g(10);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f5301n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f5302p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f5303q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i[] f5304r;

    public c(String str, int i7, int i10, long j3, long j10, i[] iVarArr) {
        super("CHAP");
        this.f5300m = str;
        this.f5301n = i7;
        this.o = i10;
        this.f5302p = j3;
        this.f5303q = j10;
        this.f5304r = iVarArr;
    }

    @Override // P2.i, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f5301n == cVar.f5301n && this.o == cVar.o && this.f5302p == cVar.f5302p && this.f5303q == cVar.f5303q) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f5300m, cVar.f5300m) && Arrays.equals(this.f5304r, cVar.f5304r)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = (((((((527 + this.f5301n) * 31) + this.o) * 31) + ((int) this.f5302p)) * 31) + ((int) this.f5303q)) * 31;
        String str = this.f5300m;
        return i7 + (str != null ? str.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5300m);
        parcel.writeInt(this.f5301n);
        parcel.writeInt(this.o);
        parcel.writeLong(this.f5302p);
        parcel.writeLong(this.f5303q);
        i[] iVarArr = this.f5304r;
        parcel.writeInt(iVarArr.length);
        for (i iVar : iVarArr) {
            parcel.writeParcelable(iVar, 0);
        }
    }

    public c(Parcel parcel) {
        super("CHAP");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5300m = string;
        this.f5301n = parcel.readInt();
        this.o = parcel.readInt();
        this.f5302p = parcel.readLong();
        this.f5303q = parcel.readLong();
        int i10 = parcel.readInt();
        this.f5304r = new i[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            this.f5304r[i11] = (i) parcel.readParcelable(i.class.getClassLoader());
        }
    }
}
