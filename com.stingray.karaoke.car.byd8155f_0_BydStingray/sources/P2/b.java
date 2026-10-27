package P2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b extends i {
    public static final Parcelable.Creator<b> CREATOR = new I1.g(9);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f5299m;

    public b(String str, byte[] bArr) {
        super(str);
        this.f5299m = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.l.equals(bVar.l) && Arrays.equals(this.f5299m, bVar.f5299m);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f5299m) + M5.b.c(this.l, 527, 31);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeByteArray(this.f5299m);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(Parcel parcel) {
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        super(string);
        this.f5299m = parcel.createByteArray();
    }
}
