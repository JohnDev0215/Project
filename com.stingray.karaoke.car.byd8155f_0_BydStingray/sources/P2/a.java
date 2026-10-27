package P2;

import android.os.Parcel;
import android.os.Parcelable;
import e2.H;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i {
    public static final Parcelable.Creator<a> CREATOR = new I1.g(8);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5296m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5297n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final byte[] f5298p;

    public a(String str, String str2, int i7, byte[] bArr) {
        super("APIC");
        this.f5296m = str;
        this.f5297n = str2;
        this.o = i7;
        this.f5298p = bArr;
    }

    @Override // e2.J
    public final void e(H h10) {
        h10.a(this.o, this.f5298p);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.o == aVar.o) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f5296m, aVar.f5296m) && Objects.equals(this.f5297n, aVar.f5297n) && Arrays.equals(this.f5298p, aVar.f5298p)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = (527 + this.o) * 31;
        String str = this.f5296m;
        int iHashCode = (i7 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5297n;
        return Arrays.hashCode(this.f5298p) + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": mimeType=" + this.f5296m + ", description=" + this.f5297n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5296m);
        parcel.writeString(this.f5297n);
        parcel.writeInt(this.o);
        parcel.writeByteArray(this.f5298p);
    }

    public a(Parcel parcel) {
        super("APIC");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5296m = string;
        this.f5297n = parcel.readString();
        this.o = parcel.readInt();
        this.f5298p = parcel.createByteArray();
    }
}
