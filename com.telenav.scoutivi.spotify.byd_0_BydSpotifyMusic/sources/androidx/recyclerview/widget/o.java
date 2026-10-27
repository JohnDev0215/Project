package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import i0.u;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class o extends i0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final RecyclerView f3170d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a f3171e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends i0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final o f3172d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Map<View, i0.a> f3173e = new WeakHashMap();

        public a(o oVar) {
            this.f3172d = oVar;
        }

        @Override // i0.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            i0.a aVar = this.f3173e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // i0.a
        public j0.d b(View view) {
            i0.a aVar = this.f3173e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // i0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            if (this.f3172d.o() || this.f3172d.f3170d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f3172d.f3170d.getLayoutManager().O0(view, cVar);
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // i0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // i0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            i0.a aVar = this.f3173e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // i0.a
        public boolean j(View view, int i7, Bundle bundle) {
            if (this.f3172d.o() || this.f3172d.f3170d.getLayoutManager() == null) {
                return super.j(view, i7, bundle);
            }
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i7, bundle)) {
                    return true;
                }
            } else if (super.j(view, i7, bundle)) {
                return true;
            }
            return this.f3172d.f3170d.getLayoutManager().i1(view, i7, bundle);
        }

        @Override // i0.a
        public void l(View view, int i7) {
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                aVar.l(view, i7);
            } else {
                super.l(view, i7);
            }
        }

        @Override // i0.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            i0.a aVar = this.f3173e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        i0.a n(View view) {
            return this.f3173e.remove(view);
        }

        void o(View view) {
            i0.a aVarI = u.i(view);
            if (aVarI == null || aVarI == this) {
                return;
            }
            this.f3173e.put(view, aVarI);
        }
    }

    public o(RecyclerView recyclerView) {
        this.f3170d = recyclerView;
        i0.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f3171e = new a(this);
        } else {
            this.f3171e = (a) aVarN;
        }
    }

    @Override // i0.a
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

    @Override // i0.a
    public void g(View view, j0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f3170d.getLayoutManager() == null) {
            return;
        }
        this.f3170d.getLayoutManager().N0(cVar);
    }

    @Override // i0.a
    public boolean j(View view, int i7, Bundle bundle) {
        if (super.j(view, i7, bundle)) {
            return true;
        }
        if (o() || this.f3170d.getLayoutManager() == null) {
            return false;
        }
        return this.f3170d.getLayoutManager().g1(i7, bundle);
    }

    public i0.a n() {
        return this.f3171e;
    }

    boolean o() {
        return this.f3170d.n0();
    }
}
