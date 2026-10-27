package b.n.d;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import b.f.m.s;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.f.m.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f2104d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f2105e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends b.f.m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final i f2106d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<View, b.f.m.a> f2107e = new WeakHashMap();

        public a(i iVar) {
            this.f2106d = iVar;
        }

        @Override // b.f.m.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            b.f.m.a aVar = this.f2107e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // b.f.m.a
        public b.f.m.b0.d b(View view) {
            b.f.m.a aVar = this.f2107e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // b.f.m.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // b.f.m.a
        public void g(View view, b.f.m.b0.c cVar) {
            if (this.f2106d.o() || this.f2106d.f2104d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f2106d.f2104d.getLayoutManager().M0(view, cVar);
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // b.f.m.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // b.f.m.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            b.f.m.a aVar = this.f2107e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // b.f.m.a
        public boolean j(View view, int i2, Bundle bundle) {
            if (this.f2106d.o() || this.f2106d.f2104d.getLayoutManager() == null) {
                return super.j(view, i2, bundle);
            }
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i2, bundle)) {
                    return true;
                }
            } else if (super.j(view, i2, bundle)) {
                return true;
            }
            return this.f2106d.f2104d.getLayoutManager().f1(view, i2, bundle);
        }

        @Override // b.f.m.a
        public void l(View view, int i2) {
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                aVar.l(view, i2);
            } else {
                super.l(view, i2);
            }
        }

        @Override // b.f.m.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            b.f.m.a aVar = this.f2107e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public b.f.m.a n(View view) {
            return this.f2107e.remove(view);
        }

        public void o(View view) {
            b.f.m.a aVarI = s.i(view);
            if (aVarI == null || aVarI == this) {
                return;
            }
            this.f2107e.put(view, aVarI);
        }
    }

    public i(RecyclerView recyclerView) {
        this.f2104d = recyclerView;
        b.f.m.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f2105e = new a(this);
        } else {
            this.f2105e = (a) aVarN;
        }
    }

    @Override // b.f.m.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().I0(accessibilityEvent);
        }
    }

    @Override // b.f.m.a
    public void g(View view, b.f.m.b0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f2104d.getLayoutManager() == null) {
            return;
        }
        this.f2104d.getLayoutManager().K0(cVar);
    }

    @Override // b.f.m.a
    public boolean j(View view, int i2, Bundle bundle) {
        if (super.j(view, i2, bundle)) {
            return true;
        }
        if (o() || this.f2104d.getLayoutManager() == null) {
            return false;
        }
        return this.f2104d.getLayoutManager().d1(i2, bundle);
    }

    public b.f.m.a n() {
        return this.f2105e;
    }

    public boolean o() {
        return this.f2104d.j0();
    }
}
