package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i {
    public static final Parcelable.Creator<d> CREATOR = new I1.g(11);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5305m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f5306n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String[] f5307p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i[] f5308q;

    public d(String str, boolean z6, boolean z10, String[] strArr, i[] iVarArr) {
        super("CTOC");
        this.f5305m = str;
        this.f5306n = z6;
        this.o = z10;
        this.f5307p = strArr;
        this.f5308q = iVarArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.f5306n == dVar.f5306n && this.o == dVar.o) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f5305m, dVar.f5305m) && Arrays.equals(this.f5307p, dVar.f5307p) && Arrays.equals(this.f5308q, dVar.f5308q)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = (((527 + (this.f5306n ? 1 : 0)) * 31) + (this.o ? 1 : 0)) * 31;
        String str = this.f5305m;
        return i7 + (str != null ? str.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5305m);
        parcel.writeByte(this.f5306n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeStringArray(this.f5307p);
        i[] iVarArr = this.f5308q;
        parcel.writeInt(iVarArr.length);
        for (i iVar : iVarArr) {
            parcel.writeParcelable(iVar, 0);
        }
    }

    public d(Parcel parcel) {
        super("CTOC");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5305m = string;
        this.f5306n = parcel.readByte() != 0;
        this.o = parcel.readByte() != 0;
        this.f5307p = parcel.createStringArray();
        int i10 = parcel.readInt();
        this.f5308q = new i[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            this.f5308q[i11] = (i) parcel.readParcelable(i.class.getClassLoader());
        }
    }
}
