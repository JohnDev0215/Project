package b.o.a;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.g.j.t;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends b.g.j.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f1407d;
    public final a e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends b.g.j.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l f1408d;
        public Map<View, b.g.j.a> e = new WeakHashMap();

        public a(l lVar) {
            this.f1408d = lVar;
        }

        @Override // b.g.j.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            b.g.j.a aVar = this.e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // b.g.j.a
        public b.g.j.c0.d b(View view) {
            b.g.j.a aVar = this.e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // b.g.j.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // b.g.j.a
        public void g(View view, b.g.j.c0.c cVar) {
            if (this.f1408d.o() || this.f1408d.f1407d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f1408d.f1407d.getLayoutManager().O0(view, cVar);
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // b.g.j.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // b.g.j.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            b.g.j.a aVar = this.e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // b.g.j.a
        public boolean j(View view, int i, Bundle bundle) {
            if (this.f1408d.o() || this.f1408d.f1407d.getLayoutManager() == null) {
                return super.j(view, i, bundle);
            }
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.f1408d.f1407d.getLayoutManager().i1(view, i, bundle);
        }

        @Override // b.g.j.a
        public void l(View view, int i) {
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // b.g.j.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            b.g.j.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public b.g.j.a n(View view) {
            return this.e.remove(view);
        }

        public void o(View view) {
            b.g.j.a aVarK = t.k(view);
            if (aVarK == null || aVarK == this) {
                return;
            }
            this.e.put(view, aVarK);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f1407d = recyclerView;
        b.g.j.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.e = new a(this);
        } else {
            this.e = (a) aVarN;
        }
    }

    @Override // b.g.j.a
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

    @Override // b.g.j.a
    public void g(View view, b.g.j.c0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f1407d.getLayoutManager() == null) {
            return;
        }
        this.f1407d.getLayoutManager().M0(cVar);
    }

    @Override // b.g.j.a
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.f1407d.getLayoutManager() == null) {
            return false;
        }
        return this.f1407d.getLayoutManager().g1(i, bundle);
    }

    public b.g.j.a n() {
        return this.e;
    }

    public boolean o() {
        return this.f1407d.hasPendingAdapterUpdates();
    }
}
