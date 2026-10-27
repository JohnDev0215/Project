package p;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class vk2 {
    public final UUID a;
    public final String b;
    public final Map c;
    public final Map d;
    public final Set e;
    public final String f;
    public final UUID g;
    public final Long h;
    public final xk2 i;

    public vk2(UUID uuid, String str, ConcurrentHashMap concurrentHashMap, ConcurrentHashMap concurrentHashMap2, Set set, String str2, xk2 xk2Var) {
        v03.j(uuid, "measurementId");
        v03.j(str, "category");
        v03.j(concurrentHashMap, "metadata");
        v03.j(concurrentHashMap2, "dimensions");
        v03.j(set, "points");
        this.a = uuid;
        this.b = str;
        this.c = concurrentHashMap;
        this.d = concurrentHashMap2;
        this.e = set;
        this.f = str2;
        this.g = null;
        this.h = null;
        this.i = xk2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof vk2)) {
            return false;
        }
        vk2 vk2Var = (vk2) obj;
        return v03.b(this.a, vk2Var.a) && v03.b(this.b, vk2Var.b) && v03.b(this.c, vk2Var.c) && v03.b(this.d, vk2Var.d) && v03.b(this.e, vk2Var.e) && v03.b(this.f, vk2Var.f) && v03.b(this.g, vk2Var.g) && v03.b(this.h, vk2Var.h) && v03.b(this.i, vk2Var.i);
    }

    public final int hashCode() {
        int iHashCode = (this.e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + hf2.b(this.b, this.a.hashCode() * 31, 31)) * 31)) * 31)) * 31;
        String str = this.f;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        UUID uuid = this.g;
        int iHashCode3 = (iHashCode2 + (uuid == null ? 0 : uuid.hashCode())) * 31;
        Long l = this.h;
        int iHashCode4 = (iHashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        xk2 xk2Var = this.i;
        return iHashCode4 + (xk2Var != null ? xk2Var.hashCode() : 0);
    }

    public final String toString() {
        return "TimeMeasurement(measurementId=" + this.a + ", category=" + this.b + ", metadata=" + this.c + ", dimensions=" + this.d + ", points=" + this.e + ", featureId=" + this.f + ", parentMeasurementId=" + this.g + ", parentEpochOffset=" + this.h + ", error=" + this.i + ')';
    }
}
