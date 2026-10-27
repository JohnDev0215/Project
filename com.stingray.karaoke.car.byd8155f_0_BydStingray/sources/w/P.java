package w;

import h2.AbstractC1347d;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H f23083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f23084b;
    public final t c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f23085e;

    public P(H h10, N n10, t tVar, h6.n nVar, boolean z6, Map map) {
        this.f23083a = h10;
        this.f23084b = n10;
        this.c = tVar;
        this.d = z6;
        this.f23085e = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof P)) {
            return false;
        }
        P p10 = (P) obj;
        return A7.m.a(this.f23083a, p10.f23083a) && A7.m.a(this.f23084b, p10.f23084b) && A7.m.a(this.c, p10.c) && A7.m.a(null, null) && this.d == p10.d && A7.m.a(this.f23085e, p10.f23085e);
    }

    public final int hashCode() {
        H h10 = this.f23083a;
        int iHashCode = (h10 == null ? 0 : h10.hashCode()) * 31;
        N n10 = this.f23084b;
        int iHashCode2 = (iHashCode + (n10 == null ? 0 : n10.hashCode())) * 31;
        t tVar = this.c;
        return this.f23085e.hashCode() + AbstractC1347d.d((((iHashCode2 + (tVar == null ? 0 : tVar.hashCode())) * 31) + 0) * 31, 31, this.d);
    }

    public final String toString() {
        return "TransitionData(fade=" + this.f23083a + ", slide=" + this.f23084b + ", changeSize=" + this.c + ", scale=" + ((Object) null) + ", hold=" + this.d + ", effectsMap=" + this.f23085e + ')';
    }

    public /* synthetic */ P(H h10, N n10, t tVar, h6.n nVar, boolean z6, LinkedHashMap linkedHashMap, int i7) {
        this((i7 & 1) != 0 ? null : h10, (i7 & 2) != 0 ? null : n10, (i7 & 4) != 0 ? null : tVar, (i7 & 8) != 0 ? null : nVar, (i7 & 16) != 0 ? false : z6, (i7 & 32) != 0 ? n7.v.l : linkedHashMap);
    }
}
