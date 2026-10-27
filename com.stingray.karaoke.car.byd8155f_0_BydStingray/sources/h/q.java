package h;

import java.util.ListIterator;
import n7.C1995j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15133m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f15134n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(x xVar, int i7) {
        super(1);
        this.f15133m = i7;
        this.f15134n = xVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Object objPrevious;
        Object objPrevious2;
        switch (this.f15133m) {
            case 0:
                C1330b c1330b = (C1330b) obj;
                A7.m.f(c1330b, "backEvent");
                x xVar = this.f15134n;
                C1995j c1995j = xVar.f15145b;
                ListIterator listIterator = c1995j.listIterator(c1995j.d());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        objPrevious = listIterator.previous();
                        if (((p) objPrevious).f15131a) {
                        }
                    } else {
                        objPrevious = null;
                    }
                }
                p pVar = (p) objPrevious;
                if (xVar.c != null) {
                    xVar.b();
                }
                xVar.c = pVar;
                if (pVar != null) {
                    pVar.d(c1330b);
                }
                break;
            default:
                C1330b c1330b2 = (C1330b) obj;
                A7.m.f(c1330b2, "backEvent");
                x xVar2 = this.f15134n;
                p pVar2 = xVar2.c;
                if (pVar2 == null) {
                    C1995j c1995j2 = xVar2.f15145b;
                    ListIterator listIterator2 = c1995j2.listIterator(c1995j2.d());
                    while (true) {
                        if (listIterator2.hasPrevious()) {
                            objPrevious2 = listIterator2.previous();
                            if (((p) objPrevious2).f15131a) {
                            }
                        } else {
                            objPrevious2 = null;
                        }
                    }
                    pVar2 = (p) objPrevious2;
                }
                if (pVar2 != null) {
                    pVar2.c(c1330b2);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
