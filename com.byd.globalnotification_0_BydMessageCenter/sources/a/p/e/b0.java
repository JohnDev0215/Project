package a.p.e;

import a.h.k.x.b;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class b0 extends a.h.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f1140d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1141e;

    public static class a extends a.h.k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b0 f1142d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<View, a.h.k.a> f1143e = new WeakHashMap();

        public a(b0 b0Var) {
            this.f1142d = b0Var;
        }

        @Override // a.h.k.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            a.h.k.a aVar = this.f1143e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : this.f807a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // a.h.k.a
        public a.h.k.x.c b(View view) {
            a.h.k.a aVar = this.f1143e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // a.h.k.a
        public void c(View view, AccessibilityEvent accessibilityEvent) {
            a.h.k.a aVar = this.f1143e.get(view);
            if (aVar != null) {
                aVar.c(view, accessibilityEvent);
            } else {
                this.f807a.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // a.h.k.a
        public void d(View view, a.h.k.x.b bVar) {
            if (!this.f1142d.j() && this.f1142d.f1140d.getLayoutManager() != null) {
                this.f1142d.f1140d.getLayoutManager().l0(view, bVar);
                a.h.k.a aVar = this.f1143e.get(view);
                if (aVar != null) {
                    aVar.d(view, bVar);
                    return;
                }
            }
            this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        }

        @Override // a.h.k.a
        public void e(View view, AccessibilityEvent accessibilityEvent) {
            a.h.k.a aVar = this.f1143e.get(view);
            if (aVar != null) {
                aVar.e(view, accessibilityEvent);
            } else {
                this.f807a.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
        }

        @Override // a.h.k.a
        public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            a.h.k.a aVar = this.f1143e.get(viewGroup);
            return aVar != null ? aVar.f(viewGroup, view, accessibilityEvent) : this.f807a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // a.h.k.a
        public boolean g(View view, int i, Bundle bundle) {
            if (this.f1142d.j() || this.f1142d.f1140d.getLayoutManager() == null) {
                return super.g(view, i, bundle);
            }
            a.h.k.a aVar = this.f1143e.get(view);
            if (aVar != null) {
                if (aVar.g(view, i, bundle)) {
                    return true;
                }
            } else if (super.g(view, i, bundle)) {
                return true;
            }
            RecyclerView.m layoutManager = this.f1142d.f1140d.getLayoutManager();
            RecyclerView.t tVar = layoutManager.f1648b.f1616b;
            return layoutManager.D0();
        }

        @Override // a.h.k.a
        public void h(View view, int i) {
            a.h.k.a aVar = this.f1143e.get(view);
            if (aVar != null) {
                aVar.h(view, i);
            } else {
                this.f807a.sendAccessibilityEvent(view, i);
            }
        }

        @Override // a.h.k.a
        public void i(View view, AccessibilityEvent accessibilityEvent) {
            a.h.k.a aVar = this.f1143e.get(view);
            if (aVar != null) {
                aVar.i(view, accessibilityEvent);
            } else {
                this.f807a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        }
    }

    public b0(RecyclerView recyclerView) {
        this.f1140d = recyclerView;
        a aVar = this.f1141e;
        this.f1141e = aVar == null ? new a(this) : aVar;
    }

    @Override // a.h.k.a
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || j()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().j0(accessibilityEvent);
        }
    }

    @Override // a.h.k.a
    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        if (j() || this.f1140d.getLayoutManager() == null) {
            return;
        }
        RecyclerView.m layoutManager = this.f1140d.getLayoutManager();
        RecyclerView recyclerView = layoutManager.f1648b;
        RecyclerView.t tVar = recyclerView.f1616b;
        RecyclerView.y yVar = recyclerView.e0;
        if (recyclerView.canScrollVertically(-1) || layoutManager.f1648b.canScrollHorizontally(-1)) {
            bVar.f856a.addAction(8192);
            bVar.f856a.setScrollable(true);
        }
        if (layoutManager.f1648b.canScrollVertically(1) || layoutManager.f1648b.canScrollHorizontally(1)) {
            bVar.f856a.addAction(4096);
            bVar.f856a.setScrollable(true);
        }
        bVar.h(b.C0016b.a(layoutManager.T(tVar, yVar), layoutManager.B(tVar, yVar), layoutManager.X(), layoutManager.U()));
    }

    @Override // a.h.k.a
    public boolean g(View view, int i, Bundle bundle) {
        if (super.g(view, i, bundle)) {
            return true;
        }
        if (j() || this.f1140d.getLayoutManager() == null) {
            return false;
        }
        RecyclerView.m layoutManager = this.f1140d.getLayoutManager();
        RecyclerView.t tVar = layoutManager.f1648b.f1616b;
        return layoutManager.C0(i);
    }

    public boolean j() {
        return this.f1140d.N();
    }
}
