package O2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.H;
import e2.J;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b implements J {
    public static final Parcelable.Creator<b> CREATOR = new g(6);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f5063m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5064n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f5065p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f5066q;

    public b(int i7, String str, String str2, String str3, boolean z6, int i10) {
        AbstractC1346c.c(i10 == -1 || i10 > 0);
        this.l = i7;
        this.f5063m = str;
        this.f5064n = str2;
        this.o = str3;
        this.f5065p = z6;
        this.f5066q = i10;
    }

    public static b a(Map map) {
        boolean z6;
        int i7;
        String str;
        String str2;
        String str3;
        boolean zEquals;
        int i10;
        List list = (List) map.get("icy-br");
        boolean z10 = true;
        int i11 = -1;
        if (list != null) {
            String str4 = (String) list.get(0);
            try {
                i10 = Integer.parseInt(str4) * 1000;
                if (i10 > 0) {
                    z6 = true;
                } else {
                    try {
                        AbstractC1346c.z("IcyHeaders", "Invalid bitrate: " + str4);
                        z6 = false;
                        i10 = -1;
                    } catch (NumberFormatException unused) {
                        AbstractC1347d.q("Invalid bitrate header: ", str4, "IcyHeaders");
                        z6 = false;
                    }
                }
            } catch (NumberFormatException unused2) {
                i10 = -1;
            }
            i7 = i10;
        } else {
            z6 = false;
            i7 = -1;
        }
        List list2 = (List) map.get("icy-genre");
        if (list2 != null) {
            str = (String) list2.get(0);
            z6 = true;
        } else {
            str = null;
        }
        List list3 = (List) map.get("icy-name");
        if (list3 != null) {
            str2 = (String) list3.get(0);
            z6 = true;
        } else {
            str2 = null;
        }
        List list4 = (List) map.get("icy-url");
        if (list4 != null) {
            str3 = (String) list4.get(0);
            z6 = true;
        } else {
            str3 = null;
        }
        List list5 = (List) map.get("icy-pub");
        if (list5 != null) {
            zEquals = ((String) list5.get(0)).equals("1");
            z6 = true;
        } else {
            zEquals = false;
        }
        List list6 = (List) map.get("icy-metaint");
        if (list6 != null) {
            String str5 = (String) list6.get(0);
            try {
                int i12 = Integer.parseInt(str5);
                if (i12 > 0) {
                    i11 = i12;
                } else {
                    try {
                        AbstractC1346c.z("IcyHeaders", "Invalid metadata interval: " + str5);
                        z10 = z6;
                    } catch (NumberFormatException unused3) {
                        i11 = i12;
                        AbstractC1347d.q("Invalid metadata interval: ", str5, "IcyHeaders");
                    }
                }
                z6 = z10;
            } catch (NumberFormatException unused4) {
            }
        }
        int i13 = i11;
        if (z6) {
            return new b(i7, str, str2, str3, zEquals, i13);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // e2.J
    public final void e(H h10) {
        String str = this.f5064n;
        if (str != null) {
            h10.f13888F = str;
        }
        String str2 = this.f5063m;
        if (str2 != null) {
            h10.f13886D = str2;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.l == bVar.l) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f5063m, bVar.f5063m) && Objects.equals(this.f5064n, bVar.f5064n) && Objects.equals(this.o, bVar.o) && this.f5065p == bVar.f5065p && this.f5066q == bVar.f5066q) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = (527 + this.l) * 31;
        String str = this.f5063m;
        int iHashCode = (i7 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f5064n;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.o;
        return ((((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.f5065p ? 1 : 0)) * 31) + this.f5066q;
    }

    public final String toString() {
        return "IcyHeaders: name=\"" + this.f5064n + "\", genre=\"" + this.f5063m + "\", bitrate=" + this.l + ", metadataInterval=" + this.f5066q;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeString(this.f5063m);
        parcel.writeString(this.f5064n);
        parcel.writeString(this.o);
        int i10 = AbstractC1343A.f15195a;
        parcel.writeInt(this.f5065p ? 1 : 0);
        parcel.writeInt(this.f5066q);
    }

    public b(Parcel parcel) {
        this.l = parcel.readInt();
        this.f5063m = parcel.readString();
        this.f5064n = parcel.readString();
        this.o = parcel.readString();
        int i7 = AbstractC1343A.f15195a;
        this.f5065p = parcel.readInt() != 0;
        this.f5066q = parcel.readInt();
    }
}
