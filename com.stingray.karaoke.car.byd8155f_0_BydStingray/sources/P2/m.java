package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class m extends i {
    public static final Parcelable.Creator<m> CREATOR = new I1.g(16);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final byte[] f5325n;

    public m(String str, byte[] bArr) {
        super("PRIV");
        this.f5324m = str;
        this.f5325n = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f5324m, mVar.f5324m) && Arrays.equals(this.f5325n, mVar.f5325n);
    }

    public final int hashCode() {
        String str = this.f5324m;
        return Arrays.hashCode(this.f5325n) + ((527 + (str != null ? str.hashCode() : 0)) * 31);
    }

    @Override // P2.i
    public final String toString() {
        return this.l + ": owner=" + this.f5324m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f5324m);
        parcel.writeByteArray(this.f5325n);
    }

    public m(Parcel parcel) {
        super("PRIV");
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5324m = string;
        this.f5325n = parcel.createByteArray();
    }
}
