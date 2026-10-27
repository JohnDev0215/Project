package M2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.C1207o;
import e2.C1208p;
import e2.J;
import e2.L;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public static final Parcelable.Creator<a> CREATOR;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1208p f4184r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1208p f4185s;
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f4186m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f4187n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final byte[] f4188p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4189q;

    static {
        C1207o c1207o = new C1207o();
        c1207o.f14246m = L.l("application/id3");
        f4184r = new C1208p(c1207o);
        C1207o c1207o2 = new C1207o();
        c1207o2.f14246m = L.l("application/x-scte35");
        f4185s = new C1208p(c1207o2);
        CREATOR = new g(3);
    }

    public a(String str, String str2, long j3, long j10, byte[] bArr) {
        this.l = str;
        this.f4186m = str2;
        this.f4187n = j3;
        this.o = j10;
        this.f4188p = bArr;
    }

    @Override // e2.J
    public final C1208p b() {
        String str = this.l;
        str.getClass();
        switch (str) {
            case "urn:scte:scte35:2014:bin":
                return f4185s;
            case "https://aomedia.org/emsg/ID3":
            case "https://developer.apple.com/streaming/emsg-id3":
                return f4184r;
            default:
                return null;
        }
    }

    @Override // e2.J
    public final byte[] c() {
        if (b() != null) {
            return this.f4188p;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f4187n == aVar.f4187n && this.o == aVar.o) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.l, aVar.l) && Objects.equals(this.f4186m, aVar.f4186m) && Arrays.equals(this.f4188p, aVar.f4188p)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        if (this.f4189q == 0) {
            String str = this.l;
            int iHashCode = (527 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.f4186m;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            long j3 = this.f4187n;
            int i7 = (iHashCode2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
            long j10 = this.o;
            this.f4189q = Arrays.hashCode(this.f4188p) + ((i7 + ((int) (j10 ^ (j10 >>> 32)))) * 31);
        }
        return this.f4189q;
    }

    public final String toString() {
        return "EMSG: scheme=" + this.l + ", id=" + this.o + ", durationMs=" + this.f4187n + ", value=" + this.f4186m;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.l);
        parcel.writeString(this.f4186m);
        parcel.writeLong(this.f4187n);
        parcel.writeLong(this.o);
        parcel.writeByteArray(this.f4188p);
    }

    public a(Parcel parcel) {
        String string = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.l = string;
        this.f4186m = parcel.readString();
        this.f4187n = parcel.readLong();
        this.o = parcel.readLong();
        this.f4188p = parcel.createByteArray();
    }
}
