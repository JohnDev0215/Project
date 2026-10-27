package d8;

import c8.C1036a;
import i8.C1417b;
import i8.EnumC1416a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import n7.AbstractC2000o;
import w8.C2608c;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1128o extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13612m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1129p f13613n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1128o(C1129p c1129p, int i7) {
        super(0);
        this.f13612m = i7;
        this.f13613n = c1129p;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13612m) {
            case 0:
                C1129p c1129p = this.f13613n;
                C1036a c1036a = (C1036a) c1129p.f13616t.f4399m;
                c1129p.f8767q.b();
                c1036a.l.getClass();
                return n7.z.x(new ArrayList());
            case 1:
                HashMap map = new HashMap();
                for (Map.Entry entry : ((Map) C7.a.N(this.f13613n.f13617u, C1129p.f13614y[0])).entrySet()) {
                    String str = (String) entry.getKey();
                    V7.c cVar = (V7.c) entry.getValue();
                    C2608c c2608cD = C2608c.d(str);
                    C1417b c1417b = cVar.f9549b;
                    EnumC1416a enumC1416a = (EnumC1416a) c1417b.c;
                    int iOrdinal = enumC1416a.ordinal();
                    if (iOrdinal == 2) {
                        map.put(c2608cD, c2608cD);
                    } else if (iOrdinal == 5) {
                        String str2 = enumC1416a == EnumC1416a.MULTIFILE_CLASS_PART ? (String) c1417b.f15788h : null;
                        if (str2 != null) {
                            map.put(c2608cD, C2608c.d(str2));
                        }
                    }
                }
                return map;
            default:
                this.f13613n.f13615s.getClass();
                return new ArrayList(AbstractC2000o.J(n7.u.l, 10));
        }
    }
}
