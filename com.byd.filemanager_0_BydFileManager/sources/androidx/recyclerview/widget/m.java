package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import b.g.k.t;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends b.g.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final RecyclerView f1472d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a f1473e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends b.g.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final m f1474d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Map<View, b.g.k.a> f1475e = new WeakHashMap();

        public a(m mVar) {
            this.f1474d = mVar;
        }

        @Override // b.g.k.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            b.g.k.a aVar = this.f1475e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // b.g.k.a
        public b.g.k.c0.d b(View view) {
            b.g.k.a aVar = this.f1475e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // b.g.k.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            if (this.f1474d.o() || this.f1474d.f1472d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f1474d.f1472d.getLayoutManager().W0(view, cVar);
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // b.g.k.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // b.g.k.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            b.g.k.a aVar = this.f1475e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // b.g.k.a
        public boolean j(View view, int i, Bundle bundle) {
            if (this.f1474d.o() || this.f1474d.f1472d.getLayoutManager() == null) {
                return super.j(view, i, bundle);
            }
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.f1474d.f1472d.getLayoutManager().q1(view, i, bundle);
        }

        @Override // b.g.k.a
        public void l(View view, int i) {
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                aVar.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // b.g.k.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            b.g.k.a aVar = this.f1475e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        b.g.k.a n(View view) {
            return this.f1475e.remove(view);
        }

        void o(View view) {
            b.g.k.a aVarK = t.k(view);
            if (aVarK == null || aVarK == this) {
                return;
            }
            this.f1475e.put(view, aVarK);
        }
    }

    public m(RecyclerView recyclerView) {
        this.f1472d = recyclerView;
        b.g.k.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f1473e = new a(this);
        } else {
            this.f1473e = (a) aVarN;
        }
    }

    @Override // b.g.k.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().S0(accessibilityEvent);
        }
    }

    @Override // b.g.k.a
    public void g(View view, b.g.k.c0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f1472d.getLayoutManager() == null) {
            return;
        }
        this.f1472d.getLayoutManager().U0(cVar);
    }

    @Override // b.g.k.a
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.f1472d.getLayoutManager() == null) {
            return false;
        }
        return this.f1472d.getLayoutManager().o1(i, bundle);
    }

    public b.g.k.a n() {
        return this.f1473e;
    }

    boolean o() {
        return this.f1472d.o0();
    }
}
