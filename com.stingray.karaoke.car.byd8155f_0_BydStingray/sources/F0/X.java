package F0;

import android.content.Context;
import androidx.lifecycle.C0971i;
import androidx.lifecycle.C0973k;
import c0.C1016a;
import io.sentry.android.navigation.SentryNavigationListener;
import java.util.Iterator;
import java.util.List;
import n3.C1930E;
import n3.C1945j;
import n7.AbstractC1998m;
import o3.C2036i;
import o3.C2039l;
import x.C2620I;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class X implements T.F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2147a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2148b;
    public final /* synthetic */ Object c;

    public /* synthetic */ X(Object obj, int i7, Object obj2) {
        this.f2147a = i7;
        this.f2148b = obj;
        this.c = obj2;
    }

    @Override // T.F
    public final void a() {
        n3.y yVar;
        String str;
        switch (this.f2147a) {
            case 0:
                ((Context) this.f2148b).getApplicationContext().unregisterComponentCallbacks((Y) this.c);
                break;
            case 1:
                ((Context) this.f2148b).getApplicationContext().unregisterComponentCallbacks((Z) this.c);
                break;
            case 2:
                ((G.e0) this.f2148b).c.add(this.c);
                break;
            case 3:
                T.X x8 = (T.X) this.f2148b;
                C.m mVar = (C.m) x8.getValue();
                if (mVar != null) {
                    C.l lVar = new C.l(mVar);
                    C.k kVar = (C.k) this.c;
                    if (kVar != null) {
                        kVar.b(lVar);
                    }
                    x8.setValue(null);
                }
                break;
            case 4:
                ((K.J0) this.f2148b).d.remove((InterfaceC2782k) this.c);
                break;
            case 5:
                ((InterfaceC2782k) this.f2148b).b((Integer) ((T.X) this.c).getValue());
                break;
            case 6:
                C1945j c1945j = (C1945j) ((T.X) this.f2148b).getValue();
                String str2 = (c1945j == null || (yVar = c1945j.f19865m) == null || (str = yVar.f19918r) == null) ? null : (String) AbstractC1998m.Y(Q8.h.V0(str, new String[]{"?"}, 0, 6));
                if (str2 != null && !str2.equals("ExplorePage")) {
                    ((e6.h) this.c).f14449v.a();
                    break;
                }
                break;
            case 7:
                y6.f fVar = (y6.f) ((T.X) this.f2148b).getValue();
                if (fVar != null) {
                    fVar.k();
                }
                y6.f fVar2 = (y6.f) ((T.X) this.c).getValue();
                if (fVar2 != null) {
                    fVar2.k();
                }
                break;
            case 8:
                ((C0971i) this.f2148b).h((C1016a) this.c);
                break;
            case 9:
                C0973k c0973k = (C0973k) this.f2148b;
                ((C1930E) c0973k.f11995m).f19812q.remove((SentryNavigationListener) c0973k.f11996n);
                ((A9.b) this.c).V0(c0973k);
                break;
            case 10:
                ((C1945j) this.f2148b).f19870s.V0((C2039l) this.c);
                break;
            case 11:
                Iterator it = ((List) ((T.Q0) this.f2148b).getValue()).iterator();
                while (it.hasNext()) {
                    ((C2036i) this.c).b().b((C1945j) it.next());
                }
                break;
            case 12:
                ((x.L) this.f2148b).f23435a.n((C2620I) this.c);
                break;
            case 13:
                ((x.x0) this.f2148b).f23668j.remove((x.x0) this.c);
                break;
            case 14:
                x.x0 x0Var = (x.x0) this.f2148b;
                x0Var.getClass();
                x.q0 q0Var = (x.q0) ((x.r0) this.c).f23614b.getValue();
                if (q0Var != null) {
                    x0Var.f23667i.remove(q0Var.l);
                }
                break;
            default:
                ((x.x0) this.f2148b).f23667i.remove((x.u0) this.c);
                break;
        }
    }
}
