package c9;

import g1.AbstractC1274a;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import r9.C2319i;
import r9.InterfaceC2320j;

/* JADX INFO: loaded from: classes.dex */
public final class r extends AbstractC1274a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final y f12604g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f12605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f12606f;

    static {
        Pattern pattern = y.d;
        f12604g = P8.n.w("application/x-www-form-urlencoded");
    }

    public r(ArrayList arrayList, ArrayList arrayList2) {
        this.f12605e = d9.b.x(arrayList);
        this.f12606f = d9.b.x(arrayList2);
    }

    @Override // g1.AbstractC1274a
    public final long B() {
        return g0(null, true);
    }

    @Override // g1.AbstractC1274a
    public final y C() {
        return f12604g;
    }

    @Override // g1.AbstractC1274a
    public final void f0(InterfaceC2320j interfaceC2320j) {
        g0(interfaceC2320j, false);
    }

    public final long g0(InterfaceC2320j interfaceC2320j, boolean z6) {
        C2319i c2319iC;
        if (z6) {
            c2319iC = new C2319i();
        } else {
            A7.m.c(interfaceC2320j);
            c2319iC = interfaceC2320j.c();
        }
        List list = this.f12605e;
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (i7 > 0) {
                c2319iC.X(38);
            }
            c2319iC.c0((String) list.get(i7));
            c2319iC.X(61);
            c2319iC.c0((String) this.f12606f.get(i7));
        }
        if (!z6) {
            return 0L;
        }
        long j3 = c2319iC.f21932m;
        c2319iC.b();
        return j3;
    }
}
