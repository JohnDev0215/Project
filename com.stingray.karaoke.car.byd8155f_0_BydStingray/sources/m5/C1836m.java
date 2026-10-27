package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import q5.C2185a;
import r5.C2297a;
import r5.C2298b;
import x.AbstractC2641j;

/* JADX INFO: renamed from: m5.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1836m extends j5.y {
    public static final C1834k c = new C1834k(j5.x.l, 1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j5.m f19231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j5.x f19232b;

    public C1836m(j5.m mVar, j5.x xVar) {
        this.f19231a = mVar;
        this.f19232b = xVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        Object arrayList;
        Serializable arrayList2;
        int iQ = c2297a.Q();
        int iC = AbstractC2641j.c(iQ);
        if (iC == 0) {
            c2297a.b();
            arrayList = new ArrayList();
        } else if (iC != 2) {
            arrayList = null;
        } else {
            c2297a.g();
            arrayList = new l5.n(true);
        }
        if (arrayList == null) {
            return c(c2297a, iQ);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (c2297a.v()) {
                String strK = arrayList instanceof Map ? c2297a.K() : null;
                int iQ2 = c2297a.Q();
                int iC2 = AbstractC2641j.c(iQ2);
                if (iC2 == 0) {
                    c2297a.b();
                    arrayList2 = new ArrayList();
                } else if (iC2 != 2) {
                    arrayList2 = null;
                } else {
                    c2297a.g();
                    arrayList2 = new l5.n(true);
                }
                boolean z6 = arrayList2 != null;
                if (arrayList2 == null) {
                    arrayList2 = c(c2297a, iQ2);
                }
                if (arrayList instanceof List) {
                    ((List) arrayList).add(arrayList2);
                } else {
                    ((Map) arrayList).put(strK, arrayList2);
                }
                if (z6) {
                    arrayDeque.addLast(arrayList);
                    arrayList = arrayList2;
                }
            } else {
                if (arrayList instanceof List) {
                    c2297a.k();
                } else {
                    c2297a.m();
                }
                if (arrayDeque.isEmpty()) {
                    return arrayList;
                }
                arrayList = arrayDeque.removeLast();
            }
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (obj == null) {
            c2298b.v();
            return;
        }
        Class<?> cls = obj.getClass();
        j5.m mVar = this.f19231a;
        mVar.getClass();
        j5.y yVarC = mVar.c(new C2185a(cls));
        if (!(yVarC instanceof C1836m)) {
            yVarC.b(c2298b, obj);
        } else {
            c2298b.i();
            c2298b.m();
        }
    }

    public final Serializable c(C2297a c2297a, int i7) throws IOException {
        int iC = AbstractC2641j.c(i7);
        if (iC == 5) {
            return c2297a.O();
        }
        if (iC == 6) {
            return this.f19232b.a(c2297a);
        }
        if (iC == 7) {
            return Boolean.valueOf(c2297a.C());
        }
        if (iC != 8) {
            throw new IllegalStateException("Unexpected token: ".concat(AbstractC1347d.v(i7)));
        }
        c2297a.M();
        return null;
    }
}
