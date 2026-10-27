package U6;

import N0.q;
import Q0.b;
import S6.c;
import S6.h;
import T6.f;
import V6.d;
import V6.e;
import io.sentry.hints.i;

/* JADX INFO: loaded from: classes.dex */
public final class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9221a;

    @Override // S6.h
    public final Object a(q qVar, c cVar) {
        switch (this.f9221a) {
            case 0:
                return new V6.a((f) qVar.f4399m, 0);
            case 1:
                return new V6.c((f) qVar.f4399m);
            case 2:
                return new b((f) qVar.f4399m, 2);
            case 3:
                return new d(0);
            case 4:
                return new e((f) qVar.f4399m, ((Integer) T6.e.d.a(cVar)).intValue());
            case 5:
                return new V6.f((f) qVar.f4399m, (String) T6.e.f8752e.a(cVar), (i) qVar.o);
            case 6:
                T6.d dVar = T6.d.l;
                Object objA = T6.e.f8750a.a(cVar);
                f fVar = (f) qVar.f4399m;
                return dVar == objA ? new V6.b(fVar, ((Integer) T6.e.f8751b.a(cVar)).intValue()) : new V6.h(fVar, String.valueOf(T6.e.c.a(cVar)).concat(". "));
            case 7:
                return new d(1);
            default:
                return new V6.a((f) qVar.f4399m, 1);
        }
    }
}
