package N6;

import B2.O;
import B5.u;
import I5.i;
import I5.j;
import I5.k;
import I5.l;
import N1.C0420a;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import androidx.lifecycle.a0;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import b2.C0993c;
import d2.C1065c;
import d5.AbstractC1095q;
import d5.i0;
import g1.AbstractC1274a;
import h.n;
import java.io.Closeable;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4782b;

    public /* synthetic */ c(Object obj, int i7) {
        this.f4781a = i7;
        this.f4782b = obj;
    }

    @Override // androidx.lifecycle.h0
    public final f0 c(Class cls, C0993c c0993c) {
        f0 f0Var;
        Application application;
        switch (this.f4781a) {
            case 0:
                final h hVar = new h();
                O o = (O) this.f4782b;
                a0.d(c0993c);
                k kVar = (k) o.f527m;
                l lVar = new l();
                lVar.f2995a = new j(kVar, 0, 1);
                lVar.f2996b = new j(kVar, 1, 1);
                lVar.c = new j(kVar, 2, 1);
                lVar.d = new j(kVar, 3, 1);
                lVar.f2997e = new j(kVar, 4, 1);
                lVar.f2998f = new j(kVar, 5, 1);
                lVar.f2999g = new j(kVar, 6, 1);
                lVar.f3000h = new j(kVar, 7, 1);
                lVar.f3001i = new j(kVar, 8, 1);
                lVar.f3002j = new j(kVar, 9, 1);
                lVar.f3003k = new j(kVar, 10, 1);
                lVar.l = new j(kVar, 11, 1);
                lVar.f3004m = new j(kVar, 12, 1);
                lVar.f3005n = new j(kVar, 13, 1);
                lVar.o = new j(kVar, 14, 1);
                lVar.f3006p = new j(kVar, 15, 1);
                lVar.f3007q = new j(kVar, 16, 1);
                lVar.f3008r = new j(kVar, 17, 1);
                lVar.f3009s = new j(kVar, 18, 1);
                lVar.f3010t = new j(kVar, 19, 1);
                l lVar2 = (l) ((e) AbstractC1274a.I(e.class, lVar));
                lVar2.getClass();
                AbstractC1095q.e(20, "expectedSize");
                u uVar = new u(20);
                uVar.s("h6.a", lVar2.f2995a);
                uVar.s("a6.a", lVar2.f2996b);
                uVar.s("c6.a", lVar2.c);
                uVar.s("h6.b", lVar2.d);
                uVar.s("h6.d", lVar2.f2997e);
                uVar.s("e6.h", lVar2.f2998f);
                uVar.s("h6.h", lVar2.f2999g);
                uVar.s("f6.g", lVar2.f3000h);
                uVar.s("f6.h", lVar2.f3001i);
                uVar.s("f6.o", lVar2.f3002j);
                uVar.s("e6.i", lVar2.f3003k);
                uVar.s("X5.D0", lVar2.l);
                uVar.s("h6.l", lVar2.f3004m);
                uVar.s("g6.d", lVar2.f3005n);
                uVar.s("f6.p", lVar2.o);
                uVar.s("d6.j", lVar2.f3006p);
                uVar.s("X5.H0", lVar2.f3007q);
                uVar.s("i6.d", lVar2.f3008r);
                uVar.s("Z5.c", lVar2.f3009s);
                uVar.s("h6.p", lVar2.f3010t);
                R6.c cVar = (R6.c) ((i0) uVar.g()).get(cls.getName());
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) c0993c.f12203a.get(f.d);
                ((e) AbstractC1274a.I(e.class, lVar)).getClass();
                Object obj = i0.f13428r.get(cls);
                if (obj == null) {
                    if (interfaceC2782k != null) {
                        throw new IllegalStateException("Found creation callback but class " + cls.getName() + " does not have an assisted factory specified in @HiltViewModel.");
                    }
                    if (cVar == null) {
                        throw new IllegalStateException("Expected the @HiltViewModel-annotated class " + cls.getName() + " to be available in the multi-binding of @HiltViewModelMap but none was found.");
                    }
                    f0Var = (f0) cVar.get();
                } else {
                    if (cVar != null) {
                        throw new AssertionError("Found the @HiltViewModel-annotated class " + cls.getName() + " in both the multi-bindings of @HiltViewModelMap and @HiltViewModelAssistedMap.");
                    }
                    if (interfaceC2782k == null) {
                        throw new IllegalStateException("Found @HiltViewModel-annotated class " + cls.getName() + " using @AssistedInject but no creation callback was provided in CreationExtras.");
                    }
                    f0Var = (f0) interfaceC2782k.b(obj);
                }
                Closeable closeable = new Closeable() { // from class: N6.b
                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public final void close() {
                        hVar.a();
                    }
                };
                f0Var.getClass();
                C1065c c1065c = f0Var.f11986a;
                if (c1065c != null) {
                    if (c1065c.d) {
                        C1065c.a(closeable);
                    } else {
                        synchronized (c1065c.f13335a) {
                            c1065c.c.add(closeable);
                        }
                    }
                }
                return f0Var;
            default:
                C0420a c0420a = new C0420a();
                c0420a.l = c0993c;
                Context applicationContext = ((n) this.f4782b).getApplicationContext();
                if (!(applicationContext instanceof Application)) {
                    Context baseContext = applicationContext;
                    while (baseContext instanceof ContextWrapper) {
                        baseContext = ((ContextWrapper) baseContext).getBaseContext();
                        if (baseContext instanceof Application) {
                            application = (Application) baseContext;
                        }
                    }
                    throw new IllegalStateException("Could not find an Application in the given context: " + applicationContext);
                }
                application = (Application) applicationContext;
                return new O6.d(new i(((k) ((O6.c) AbstractC1274a.I(O6.c.class, application))).f2974b), c0420a);
        }
    }
}
