package N7;

import F8.A;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f4792m;

    public /* synthetic */ f(i iVar, int i7) {
        this.l = i7;
        this.f4792m = iVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        i iVar = this.f4792m;
        switch (this.l) {
            case 0:
                return Arrays.asList(iVar.k().n0(o.f4875j), iVar.k().n0(o.l), iVar.k().n0(o.f4877m), iVar.k().n0(o.f4876k));
            default:
                EnumMap enumMap = new EnumMap(k.class);
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                for (k kVar : k.values()) {
                    String strB = kVar.l.b();
                    if (strB == null) {
                        iVar.getClass();
                        i.a(46);
                        throw null;
                    }
                    A aJ = iVar.j(strB).j();
                    if (aJ == null) {
                        i.a(47);
                        throw null;
                    }
                    String strB2 = kVar.f4811m.b();
                    if (strB2 == null) {
                        i.a(46);
                        throw null;
                    }
                    A aJ2 = iVar.j(strB2).j();
                    if (aJ2 == null) {
                        i.a(47);
                        throw null;
                    }
                    enumMap.put(kVar, aJ2);
                    map.put(aJ, aJ2);
                    map2.put(aJ2, aJ);
                }
                return new h(enumMap, map, map2);
        }
    }
}
