package w0;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;
import l0.t;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends l0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f5657d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f5658e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends l0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l f5659d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<View, l0.a> f5660e = new WeakHashMap();

        public a(l lVar) {
            this.f5659d = lVar;
        }

        @Override // l0.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            l0.a aVar = this.f5660e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // l0.a
        public m0.d b(View view) {
            l0.a aVar = this.f5660e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // l0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            if (this.f5659d.o() || this.f5659d.f5657d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f5659d.f5657d.getLayoutManager().O0(view, cVar);
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // l0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // l0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            l0.a aVar = this.f5660e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // l0.a
        public boolean j(View view, int i3, Bundle bundle) {
            if (this.f5659d.o() || this.f5659d.f5657d.getLayoutManager() == null) {
                return super.j(view, i3, bundle);
            }
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i3, bundle)) {
                    return true;
                }
            } else if (super.j(view, i3, bundle)) {
                return true;
            }
            return this.f5659d.f5657d.getLayoutManager().i1(view, i3, bundle);
        }

        @Override // l0.a
        public void l(View view, int i3) {
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                aVar.l(view, i3);
            } else {
                super.l(view, i3);
            }
        }

        @Override // l0.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            l0.a aVar = this.f5660e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public l0.a n(View view) {
            return this.f5660e.remove(view);
        }

        public void o(View view) {
            l0.a aVarK = t.k(view);
            if (aVarK == null || aVarK == this) {
                return;
            }
            this.f5660e.put(view, aVarK);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f5657d = recyclerView;
        l0.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f5658e = new a(this);
        } else {
            this.f5658e = (a) aVarN;
        }
    }

    @Override // l0.a
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

    @Override // l0.a
    public void g(View view, m0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f5657d.getLayoutManager() == null) {
            return;
        }
        this.f5657d.getLayoutManager().M0(cVar);
    }

    @Override // l0.a
    public boolean j(View view, int i3, Bundle bundle) {
        if (super.j(view, i3, bundle)) {
            return true;
        }
        if (o() || this.f5657d.getLayoutManager() == null) {
            return false;
        }
        return this.f5657d.getLayoutManager().g1(i3, bundle);
    }

    public l0.a n() {
        return this.f5658e;
    }

    public boolean o() {
        return this.f5657d.m0();
    }
}
