package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import c.g.k.t;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends c.g.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final RecyclerView f1827d;
    private final a e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends c.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final l f1828d;
        private Map<View, c.g.k.a> e = new WeakHashMap();

        public a(l lVar) {
            this.f1828d = lVar;
        }

        @Override // c.g.k.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            c.g.k.a aVar = this.e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // c.g.k.a
        public c.g.k.c0.d b(View view) {
            c.g.k.a aVar = this.e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // c.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // c.g.k.a
        public void g(View view, c.g.k.c0.c cVar) {
            if (this.f1828d.o() || this.f1828d.f1827d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f1828d.f1827d.getLayoutManager().O0(view, cVar);
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // c.g.k.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // c.g.k.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            c.g.k.a aVar = this.e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // c.g.k.a
        public boolean j(View view, int i, Bundle bundle) {
            if (this.f1828d.o() || this.f1828d.f1827d.getLayoutManager() == null) {
                return super.j(view, i, bundle);
            }
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.f1828d.f1827d.getLayoutManager().i1(view, i, bundle);
        }

        @Override // c.g.k.a
        public void l(View view, int i) {
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // c.g.k.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            c.g.k.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        c.g.k.a n(View view) {
            return this.e.remove(view);
        }

        void o(View view) {
            c.g.k.a aVarK = t.k(view);
            if (aVarK == null || aVarK == this) {
                return;
            }
            this.e.put(view, aVarK);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f1827d = recyclerView;
        c.g.k.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.e = new a(this);
        } else {
            this.e = (a) aVarN;
        }
    }

    @Override // c.g.k.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().K0(accessibilityEvent);
        }
    }

    @Override // c.g.k.a
    public void g(View view, c.g.k.c0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f1827d.getLayoutManager() == null) {
            return;
        }
        this.f1827d.getLayoutManager().M0(cVar);
    }

    @Override // c.g.k.a
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.f1827d.getLayoutManager() == null) {
            return false;
        }
        return this.f1827d.getLayoutManager().g1(i, bundle);
    }

    public c.g.k.a n() {
        return this.e;
    }

    boolean o() {
        return this.f1827d.m0();
    }
}
