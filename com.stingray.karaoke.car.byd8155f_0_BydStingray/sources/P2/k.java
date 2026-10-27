package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class k extends i {
    public static final Parcelable.Creator<k> CREATOR = new I1.g(14);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5318m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5319n;
    public final String o;

    public k(String str, String str2, String str3) {
        super("----");
        this.f5318m = str;
        this.f5319n = str2;
        this.o = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f5319n, kVar.f5319n) && Objects.equals(this.f5318m, kVar.f5318m) && Objects.equals(this.o, kVar.o);
    }

    public final int hashCode() {
        String str = this.f5318m;
        int iHashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5319n;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.o;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": domain=" + this.f5318m + ", description=" + this.f5319n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f5318m);
        parcel.writeString(this.o);
    }

    public k(Parcel parcel) {
        super("----");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5318m = string;
        this.f5319n = parcel.readString();
        this.o = parcel.readString();
    }
}
