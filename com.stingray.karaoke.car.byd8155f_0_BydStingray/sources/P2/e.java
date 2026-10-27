package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e extends i {
    public static final Parcelable.Creator<e> CREATOR = new I1.g(12);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5309m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5310n;
    public final String o;

    public e(String str, String str2, String str3) {
        super("COMM");
        this.f5309m = str;
        this.f5310n = str2;
        this.o = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e.class != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f5310n, eVar.f5310n) && Objects.equals(this.f5309m, eVar.f5309m) && Objects.equals(this.o, eVar.o);
    }

    public final int hashCode() {
        String str = this.f5309m;
        int iHashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5310n;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.o;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": language=" + this.f5309m + ", description=" + this.f5310n + ", text=" + this.o;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f5309m);
        parcel.writeString(this.o);
    }

    public e(Parcel parcel) {
        super("COMM");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5309m = string;
        this.f5310n = parcel.readString();
        this.o = parcel.readString();
    }
}
