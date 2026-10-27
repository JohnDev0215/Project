package androidx.lifecycle;

import a.n.d0;
import a.n.e0;
import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.n.v;
import a.n.x;
import a.q.a;
import a.q.c;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1586b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f1587c;

    public static final class a implements a.InterfaceC0026a {
        @Override // a.q.a.InterfaceC0026a
        public void a(c cVar) {
            if (!(cVar instanceof e0)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            d0 d0VarE = ((e0) cVar).e();
            a.q.a aVarD = cVar.d();
            if (d0VarE == null) {
                throw null;
            }
            Iterator it = new HashSet(d0VarE.f1085a.keySet()).iterator();
            while (it.hasNext()) {
                SavedStateHandleController.h(d0VarE.f1085a.get((String) it.next()), aVarD, cVar.a());
            }
            if (new HashSet(d0VarE.f1085a.keySet()).isEmpty()) {
                return;
            }
            aVarD.b(a.class);
        }
    }

    public SavedStateHandleController(String str, v vVar) {
        this.f1585a = str;
        this.f1587c = vVar;
    }

    public static void h(x xVar, a.q.a aVar, g gVar) {
        Object obj;
        Map<String, Object> map = xVar.f1121a;
        if (map == null) {
            obj = null;
        } else {
            synchronized (map) {
                obj = xVar.f1121a.get("androidx.lifecycle.savedstate.vm.tag");
            }
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
        if (savedStateHandleController == null || savedStateHandleController.f1586b) {
            return;
        }
        savedStateHandleController.i(aVar, gVar);
        j(aVar, gVar);
    }

    public static void j(final a.q.a aVar, final g gVar) {
        g.b bVar = ((l) gVar).f1094b;
        if (bVar != g.b.INITIALIZED) {
            if (!(bVar.compareTo(g.b.STARTED) >= 0)) {
                gVar.a(new i() { // from class: androidx.lifecycle.SavedStateHandleController.1
                    @Override // a.n.i
                    public void g(k kVar, g.a aVar2) {
                        if (aVar2 == g.a.ON_START) {
                            ((l) gVar).f1093a.g(this);
                            aVar.b(a.class);
                        }
                    }
                });
                return;
            }
        }
        aVar.b(a.class);
    }

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        if (aVar == g.a.ON_DESTROY) {
            this.f1586b = false;
            ((l) kVar.a()).f1093a.g(this);
        }
    }

    public void i(a.q.a aVar, g gVar) {
        if (this.f1586b) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f1586b = true;
        gVar.a(this);
        if (aVar.f1270a.f(this.f1585a, this.f1587c.f1113b) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }
}
