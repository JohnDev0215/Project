package o3;

import T.C0735d;
import T.C0738e0;
import T.S;
import V8.f0;
import androidx.lifecycle.EnumC0981t;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import n3.C1933H;
import n3.C1945j;
import n3.C1948m;
import n3.N;
import n3.O;
import n7.AbstractC1985B;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: o3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@N("composable")
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lo3/i;", "Ln3/O;", "Lo3/h;", "<init>", "()V", "navigation-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class C2036i extends O {
    public final C0738e0 c = C0735d.O(Boolean.FALSE, S.f8224q);

    @Override // n3.O
    public final n3.y a() {
        return new C2035h(this, AbstractC2030c.f20457a);
    }

    @Override // n3.O
    public final void d(List list, C1933H c1933h) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C1945j c1945j = (C1945j) it.next();
            C1948m c1948mB = b();
            A7.m.f(c1945j, "backStackEntry");
            f0 f0Var = c1948mB.c;
            Iterable iterable = (Iterable) f0Var.getValue();
            boolean z6 = iterable instanceof Collection;
            V8.N n10 = c1948mB.f19880e;
            if (!z6 || !((Collection) iterable).isEmpty()) {
                Iterator it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (((C1945j) it2.next()) == c1945j) {
                        Iterable iterable2 = (Iterable) ((f0) n10.l).getValue();
                        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                            Iterator it3 = iterable2.iterator();
                            while (it3.hasNext()) {
                                if (((C1945j) it3.next()) == c1945j) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            C1945j c1945j2 = (C1945j) AbstractC1998m.i0((List) ((f0) n10.l).getValue());
            if (c1945j2 != null) {
                f0Var.m(null, AbstractC1985B.r((Set) f0Var.getValue(), c1945j2));
            }
            f0Var.m(null, AbstractC1985B.r((Set) f0Var.getValue(), c1945j));
            c1948mB.f(c1945j);
        }
        this.c.setValue(Boolean.FALSE);
    }

    @Override // n3.O
    public final void e(C1945j c1945j, boolean z6) {
        b().e(c1945j, z6);
        this.c.setValue(Boolean.TRUE);
    }

    public final void g(C1945j c1945j) {
        C1948m c1948mB = b();
        A7.m.f(c1945j, "entry");
        f0 f0Var = c1948mB.c;
        f0Var.m(null, AbstractC1985B.r((Set) f0Var.getValue(), c1945j));
        if (!c1948mB.f19883h.f19804g.contains(c1945j)) {
            throw new IllegalStateException("Cannot transition entry that is not in the back stack");
        }
        c1945j.h(EnumC0981t.o);
    }
}
