package p;

import androidx.work.NetworkType;
import java.util.Set;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public final class wy {
    public static final wy i = new wy(NetworkType.c, false, false, false, false, -1, -1, EmptySet.c);
    public final NetworkType a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final long f;
    public final long g;
    public final Set h;

    public wy(NetworkType networkType, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, Set set) {
        v03.j(networkType, "requiredNetworkType");
        v03.j(set, "contentUriTriggers");
        this.a = networkType;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = j;
        this.g = j2;
        this.h = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !v03.b(wy.class, obj.getClass())) {
            return false;
        }
        wy wyVar = (wy) obj;
        if (this.b == wyVar.b && this.c == wyVar.c && this.d == wyVar.d && this.e == wyVar.e && this.f == wyVar.f && this.g == wyVar.g && this.a == wyVar.a) {
            return v03.b(this.h, wyVar.h);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = ((((((((this.a.hashCode() * 31) + (this.b ? 1 : 0)) * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31;
        long j = this.f;
        int i2 = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.g;
        return this.h.hashCode() + ((i2 + ((int) (j2 ^ (j2 >>> 32)))) * 31);
    }

    public final String toString() {
        return "Constraints{requiredNetworkType=" + this.a + ", requiresCharging=" + this.b + ", requiresDeviceIdle=" + this.c + ", requiresBatteryNotLow=" + this.d + ", requiresStorageNotLow=" + this.e + ", contentTriggerUpdateDelayMillis=" + this.f + ", contentTriggerMaxDelayMillis=" + this.g + ", contentUriTriggers=" + this.h + ", }";
    }

    public wy(wy wyVar) {
        v03.j(wyVar, "other");
        this.b = wyVar.b;
        this.c = wyVar.c;
        this.a = wyVar.a;
        this.d = wyVar.d;
        this.e = wyVar.e;
        this.h = wyVar.h;
        this.f = wyVar.f;
        this.g = wyVar.g;
    }
}
