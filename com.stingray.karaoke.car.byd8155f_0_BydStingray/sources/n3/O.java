package n3;

import V8.f0;
import java.util.List;
import java.util.ListIterator;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public abstract class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C1948m f19841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f19842b;

    public abstract y a();

    public final C1948m b() {
        C1948m c1948m = this.f19841a;
        if (c1948m != null) {
            return c1948m;
        }
        throw new IllegalStateException("You cannot access the Navigator's state until the Navigator is attached");
    }

    public y c(y yVar) {
        return yVar;
    }

    public void d(List list, C1933H c1933h) {
        P8.f fVar = new P8.f(new P8.g(P8.m.g0(AbstractC1998m.S(list), new S0.p(this, c1933h)), false, new A3.c(18)));
        while (fVar.hasNext()) {
            b().f((C1945j) fVar.next());
        }
    }

    public void e(C1945j c1945j, boolean z6) {
        A7.m.f(c1945j, "popUpTo");
        List list = (List) ((f0) b().f19880e.l).getValue();
        if (!list.contains(c1945j)) {
            throw new IllegalStateException(("popBackStack was called with " + c1945j + " which does not exist in back stack " + list).toString());
        }
        ListIterator listIterator = list.listIterator(list.size());
        C1945j c1945j2 = null;
        while (f()) {
            c1945j2 = (C1945j) listIterator.previous();
            if (A7.m.a(c1945j2, c1945j)) {
                break;
            }
        }
        if (c1945j2 != null) {
            b().c(c1945j2, z6);
        }
    }

    public boolean f() {
        return true;
    }
}
