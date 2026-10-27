package androidx.lifecycle;

import android.annotation.SuppressLint;
import androidx.lifecycle.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private l.a<n, a> f2549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i.c f2550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<o> f2551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2552e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2553f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2554g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<i.c> f2555h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f2556i;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        i.c f2557a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        m f2558b;

        a(n nVar, i.c cVar) {
            this.f2558b = r.f(nVar);
            this.f2557a = cVar;
        }

        void a(o oVar, i.b bVar) {
            i.c targetState = bVar.getTargetState();
            this.f2557a = p.k(this.f2557a, targetState);
            this.f2558b.d(oVar, bVar);
            this.f2557a = targetState;
        }
    }

    public p(o oVar) {
        this(oVar, true);
    }

    private void d(o oVar) {
        Iterator<Map.Entry<n, a>> itC = this.f2549b.c();
        while (itC.hasNext() && !this.f2554g) {
            Map.Entry<n, a> next = itC.next();
            a value = next.getValue();
            while (value.f2557a.compareTo(this.f2550c) > 0 && !this.f2554g && this.f2549b.contains(next.getKey())) {
                i.b bVarDownFrom = i.b.downFrom(value.f2557a);
                if (bVarDownFrom == null) {
                    throw new IllegalStateException("no event down from " + value.f2557a);
                }
                n(bVarDownFrom.getTargetState());
                value.a(oVar, bVarDownFrom);
                m();
            }
        }
    }

    private i.c e(n nVar) {
        Map.Entry<n, a> entryK = this.f2549b.k(nVar);
        i.c cVar = null;
        i.c cVar2 = entryK != null ? entryK.getValue().f2557a : null;
        if (!this.f2555h.isEmpty()) {
            cVar = this.f2555h.get(r0.size() - 1);
        }
        return k(k(this.f2550c, cVar2), cVar);
    }

    @SuppressLint({"RestrictedApi"})
    private void f(String str) {
        if (!this.f2556i || k.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Method " + str + " must be called on the main thread");
    }

    private void g(o oVar) {
        l.b<n, a>.d dVarF = this.f2549b.f();
        while (dVarF.hasNext() && !this.f2554g) {
            Map.Entry next = dVarF.next();
            a aVar = (a) next.getValue();
            while (aVar.f2557a.compareTo(this.f2550c) < 0 && !this.f2554g && this.f2549b.contains((n) next.getKey())) {
                n(aVar.f2557a);
                i.b bVarUpFrom = i.b.upFrom(aVar.f2557a);
                if (bVarUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f2557a);
                }
                aVar.a(oVar, bVarUpFrom);
                m();
            }
        }
    }

    private boolean i() {
        if (this.f2549b.size() == 0) {
            return true;
        }
        i.c cVar = this.f2549b.d().getValue().f2557a;
        i.c cVar2 = this.f2549b.g().getValue().f2557a;
        return cVar == cVar2 && this.f2550c == cVar2;
    }

    static i.c k(i.c cVar, i.c cVar2) {
        return (cVar2 == null || cVar2.compareTo(cVar) >= 0) ? cVar : cVar2;
    }

    private void l(i.c cVar) {
        if (this.f2550c == cVar) {
            return;
        }
        this.f2550c = cVar;
        if (this.f2553f || this.f2552e != 0) {
            this.f2554g = true;
            return;
        }
        this.f2553f = true;
        p();
        this.f2553f = false;
    }

    private void m() {
        this.f2555h.remove(r0.size() - 1);
    }

    private void n(i.c cVar) {
        this.f2555h.add(cVar);
    }

    private void p() {
        o oVar = this.f2551d.get();
        if (oVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!i()) {
            this.f2554g = false;
            if (this.f2550c.compareTo(this.f2549b.d().getValue().f2557a) < 0) {
                d(oVar);
            }
            Map.Entry<n, a> entryG = this.f2549b.g();
            if (!this.f2554g && entryG != null && this.f2550c.compareTo(entryG.getValue().f2557a) > 0) {
                g(oVar);
            }
        }
        this.f2554g = false;
    }

    @Override // androidx.lifecycle.i
    public void a(n nVar) {
        o oVar;
        f("addObserver");
        i.c cVar = this.f2550c;
        i.c cVar2 = i.c.DESTROYED;
        if (cVar != cVar2) {
            cVar2 = i.c.INITIALIZED;
        }
        a aVar = new a(nVar, cVar2);
        if (this.f2549b.i(nVar, aVar) == null && (oVar = this.f2551d.get()) != null) {
            boolean z6 = this.f2552e != 0 || this.f2553f;
            i.c cVarE = e(nVar);
            this.f2552e++;
            while (aVar.f2557a.compareTo(cVarE) < 0 && this.f2549b.contains(nVar)) {
                n(aVar.f2557a);
                i.b bVarUpFrom = i.b.upFrom(aVar.f2557a);
                if (bVarUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f2557a);
                }
                aVar.a(oVar, bVarUpFrom);
                m();
                cVarE = e(nVar);
            }
            if (!z6) {
                p();
            }
            this.f2552e--;
        }
    }

    @Override // androidx.lifecycle.i
    public i.c b() {
        return this.f2550c;
    }

    @Override // androidx.lifecycle.i
    public void c(n nVar) {
        f("removeObserver");
        this.f2549b.j(nVar);
    }

    public void h(i.b bVar) {
        f("handleLifecycleEvent");
        l(bVar.getTargetState());
    }

    @Deprecated
    public void j(i.c cVar) {
        f("markState");
        o(cVar);
    }

    public void o(i.c cVar) {
        f("setCurrentState");
        l(cVar);
    }

    private p(o oVar, boolean z6) {
        this.f2549b = new l.a<>();
        this.f2552e = 0;
        this.f2553f = false;
        this.f2554g = false;
        this.f2555h = new ArrayList<>();
        this.f2551d = new WeakReference<>(oVar);
        this.f2550c = i.c.INITIALIZED;
        this.f2556i = z6;
    }
}
