package e2;

import android.os.Parcel;
import android.os.Parcelable;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: renamed from: e2.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1203k implements Parcelable {
    public static final Parcelable.Creator<C1203k> CREATOR = new android.support.v4.media.a(5);
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final UUID f14134m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f14135n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final byte[] f14136p;

    public C1203k(UUID uuid, String str, String str2, byte[] bArr) {
        uuid.getClass();
        this.f14134m = uuid;
        this.f14135n = str;
        str2.getClass();
        this.o = L.l(str2);
        this.f14136p = bArr;
    }

    public final boolean a(UUID uuid) {
        UUID uuid2 = AbstractC1199g.f14099a;
        UUID uuid3 = this.f14134m;
        return uuid2.equals(uuid3) || uuid.equals(uuid3);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1203k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C1203k c1203k = (C1203k) obj;
        String str = c1203k.f14135n;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f14135n, str) && Objects.equals(this.o, c1203k.o) && Objects.equals(this.f14134m, c1203k.f14134m) && Arrays.equals(this.f14136p, c1203k.f14136p);
    }

    public final int hashCode() {
        if (this.l == 0) {
            int iHashCode = this.f14134m.hashCode() * 31;
            String str = this.f14135n;
            this.l = Arrays.hashCode(this.f14136p) + M5.b.c(this.o, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        }
        return this.l;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        UUID uuid = this.f14134m;
        parcel.writeLong(uuid.getMostSignificantBits());
        parcel.writeLong(uuid.getLeastSignificantBits());
        parcel.writeString(this.f14135n);
        parcel.writeString(this.o);
        parcel.writeByteArray(this.f14136p);
    }

    public C1203k(Parcel parcel) {
        this.f14134m = new UUID(parcel.readLong(), parcel.readLong());
        this.f14135n = parcel.readString();
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.o = string;
        this.f14136p = parcel.createByteArray();
    }
}
