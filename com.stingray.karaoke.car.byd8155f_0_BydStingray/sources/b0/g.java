package b0;

import A7.m;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0768u;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f12197a = new f(0, new long[0], new Object[0]);

    public static final int a(int i7, int i10) {
        return i7 << (((i10 % 10) * 3) + 1);
    }

    public static final b b(int i7, m7.c cVar, C0761q c0761q) {
        Object objG = c0761q.G();
        if (objG == C0753m.f8270a) {
            objG = new b(i7, cVar, true);
            c0761q.a0(objG);
        }
        b bVar = (b) objG;
        if (!m.a(bVar.f12191n, cVar)) {
            boolean z6 = bVar.f12191n == null;
            bVar.f12191n = cVar;
            if (!z6 && bVar.f12190m) {
                C0754m0 c0754m0 = bVar.o;
                if (c0754m0 != null) {
                    C0768u c0768u = c0754m0.f8272b;
                    if (c0768u != null) {
                        c0768u.p(c0754m0, null);
                    }
                    bVar.o = null;
                }
                ArrayList arrayList = bVar.f12192p;
                if (arrayList != null) {
                    int size = arrayList.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        C0754m0 c0754m02 = (C0754m0) arrayList.get(i10);
                        C0768u c0768u2 = c0754m02.f8272b;
                        if (c0768u2 != null) {
                            c0768u2.p(c0754m02, null);
                        }
                    }
                    arrayList.clear();
                }
            }
        }
        return bVar;
    }

    public static final boolean c(C0754m0 c0754m0, C0754m0 c0754m02) {
        return c0754m0 == null || ((c0754m0 instanceof C0754m0) && (!c0754m0.b() || c0754m0.equals(c0754m02) || m.a(c0754m0.c, c0754m02.c)));
    }
}
