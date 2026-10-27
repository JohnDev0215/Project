package b.r.d;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.h.m.t;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends b.h.m.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f3120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f3121e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends b.h.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l f3122d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<View, b.h.m.a> f3123e = new WeakHashMap();

        public a(l lVar) {
            this.f3122d = lVar;
        }

        @Override // b.h.m.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            b.h.m.a aVar = this.f3123e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // b.h.m.a
        public b.h.m.c0.d b(View view) {
            b.h.m.a aVar = this.f3123e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // b.h.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            if (this.f3122d.o() || this.f3122d.f3120d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f3122d.f3120d.getLayoutManager().O0(view, cVar);
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // b.h.m.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // b.h.m.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            b.h.m.a aVar = this.f3123e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // b.h.m.a
        public boolean j(View view, int i2, Bundle bundle) {
            if (this.f3122d.o() || this.f3122d.f3120d.getLayoutManager() == null) {
                return super.j(view, i2, bundle);
            }
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i2, bundle)) {
                    return true;
                }
            } else if (super.j(view, i2, bundle)) {
                return true;
            }
            return this.f3122d.f3120d.getLayoutManager().i1(view, i2, bundle);
        }

        @Override // b.h.m.a
        public void l(View view, int i2) {
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                aVar.l(view, i2);
            } else {
                super.l(view, i2);
            }
        }

        @Override // b.h.m.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            b.h.m.a aVar = this.f3123e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public b.h.m.a n(View view) {
            return this.f3123e.remove(view);
        }

        public void o(View view) {
            b.h.m.a aVarK = t.k(view);
            if (aVarK == null || aVarK == this) {
                return;
            }
            this.f3123e.put(view, aVarK);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f3120d = recyclerView;
        b.h.m.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f3121e = new a(this);
        } else {
            this.f3121e = (a) aVarN;
        }
    }

    @Override // b.h.m.a
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

    @Override // b.h.m.a
    public void g(View view, b.h.m.c0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f3120d.getLayoutManager() == null) {
            return;
        }
        this.f3120d.getLayoutManager().M0(cVar);
    }

    @Override // b.h.m.a
    public boolean j(View view, int i2, Bundle bundle) {
        if (super.j(view, i2, bundle)) {
            return true;
        }
        if (o() || this.f3120d.getLayoutManager() == null) {
            return false;
        }
        return this.f3120d.getLayoutManager().g1(i2, bundle);
    }

    public b.h.m.a n() {
        return this.f3121e;
    }

    public boolean o() {
        return this.f3120d.n0();
    }
}
