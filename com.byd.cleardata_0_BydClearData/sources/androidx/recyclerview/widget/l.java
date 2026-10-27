package androidx.recyclerview.widget;

import a.g.k.t;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class l extends a.g.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final RecyclerView f1600d;
    private final a e;

    public static class a extends a.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final l f1601d;
        private Map<View, a.g.k.a> e = new WeakHashMap();

        public a(l lVar) {
            this.f1601d = lVar;
        }

        @Override // a.g.k.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            a.g.k.a aVar = this.e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // a.g.k.a
        public a.g.k.c0.d b(View view) {
            a.g.k.a aVar = this.e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // a.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            a.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // a.g.k.a
        public void g(View view, a.g.k.c0.c cVar) {
            if (!this.f1601d.o() && this.f1601d.f1600d.getLayoutManager() != null) {
                this.f1601d.f1600d.getLayoutManager().i1(view, cVar);
                a.g.k.a aVar = this.e.get(view);
                if (aVar != null) {
                    aVar.g(view, cVar);
                    return;
                }
            }
            super.g(view, cVar);
        }

        @Override // a.g.k.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            a.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // a.g.k.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            a.g.k.a aVar = this.e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // a.g.k.a
        public boolean j(View view, int i, Bundle bundle) {
            if (this.f1601d.o() || this.f1601d.f1600d.getLayoutManager() == null) {
                return super.j(view, i, bundle);
            }
            a.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.f1601d.f1600d.getLayoutManager().C1(view, i, bundle);
        }

        @Override // a.g.k.a
        public void l(View view, int i) {
            a.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // a.g.k.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            a.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        a.g.k.a n(View view) {
            return this.e.remove(view);
        }

        void o(View view) {
            a.g.k.a aVarJ = t.j(view);
            if (aVarJ == null || aVarJ == this) {
                return;
            }
            this.e.put(view, aVarJ);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f1600d = recyclerView;
        a.g.k.a aVarN = n();
        this.e = (aVarN == null || !(aVarN instanceof a)) ? new a(this) : (a) aVarN;
    }

    @Override // a.g.k.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().e1(accessibilityEvent);
        }
    }

    @Override // a.g.k.a
    public void g(View view, a.g.k.c0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f1600d.getLayoutManager() == null) {
            return;
        }
        this.f1600d.getLayoutManager().g1(cVar);
    }

    @Override // a.g.k.a
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.f1600d.getLayoutManager() == null) {
            return false;
        }
        return this.f1600d.getLayoutManager().A1(i, bundle);
    }

    public a.g.k.a n() {
        return this.e;
    }

    boolean o() {
        return this.f1600d.n0();
    }
}
