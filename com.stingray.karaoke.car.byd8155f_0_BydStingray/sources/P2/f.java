package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class f extends i {
    public static final Parcelable.Creator<f> CREATOR = new I1.g(13);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5311m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5312n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final byte[] f5313p;

    public f(String str, String str2, String str3, byte[] bArr) {
        super("GEOB");
        this.f5311m = str;
        this.f5312n = str2;
        this.o = str3;
        this.f5313p = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f5311m, fVar.f5311m) && Objects.equals(this.f5312n, fVar.f5312n) && Objects.equals(this.o, fVar.o) && Arrays.equals(this.f5313p, fVar.f5313p);
    }

    public final int hashCode() {
        String str = this.f5311m;
        int iHashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5312n;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.o;
        return Arrays.hashCode(this.f5313p) + ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": mimeType=" + this.f5311m + ", filename=" + this.f5312n + ", description=" + this.o;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5311m);
        parcel.writeString(this.f5312n);
        parcel.writeString(this.o);
        parcel.writeByteArray(this.f5313p);
    }

    public f(Parcel parcel) {
        super("GEOB");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5311m = string;
        this.f5312n = parcel.readString();
        this.o = parcel.readString();
        this.f5313p = parcel.createByteArray();
    }
}
