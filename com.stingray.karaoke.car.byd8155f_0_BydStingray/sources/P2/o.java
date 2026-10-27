package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class o extends i {
    public static final Parcelable.Creator<o> CREATOR = new I1.g(18);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5328m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5329n;

    public o(String str, String str2, String str3) {
        super(str);
        this.f5328m = str2;
        this.f5329n = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.l.equals(oVar.l)) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f5328m, oVar.f5328m) && Objects.equals(this.f5329n, oVar.f5329n)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iC = M5.b.c(this.l, 527, 31);
        String str = this.f5328m;
        int iHashCode = (iC + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5329n;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": url=" + this.f5329n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f5328m);
        parcel.writeString(this.f5329n);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public o(Parcel parcel) {
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        super(string);
        this.f5328m = parcel.readString();
        this.f5329n = parcel.readString();
    }
}
