package c1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;
import o0.t;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends o0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f2275d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f2276e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends o0.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l f2277d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Map<View, o0.a> f2278e = new WeakHashMap();

        public a(l lVar) {
            this.f2277d = lVar;
        }

        @Override // o0.a
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            o0.a aVar = this.f2278e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // o0.a
        public p0.d b(View view) {
            o0.a aVar = this.f2278e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            if (this.f2277d.o() || this.f2277d.f2275d.getLayoutManager() == null) {
                super.g(view, cVar);
                return;
            }
            this.f2277d.f2275d.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, cVar);
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                aVar.g(view, cVar);
            } else {
                super.g(view, cVar);
            }
        }

        @Override // o0.a
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // o0.a
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            o0.a aVar = this.f2278e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // o0.a
        public boolean j(View view, int i8, Bundle bundle) {
            if (this.f2277d.o() || this.f2277d.f2275d.getLayoutManager() == null) {
                return super.j(view, i8, bundle);
            }
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i8, bundle)) {
                    return true;
                }
            } else if (super.j(view, i8, bundle)) {
                return true;
            }
            return this.f2277d.f2275d.getLayoutManager().performAccessibilityActionForItem(view, i8, bundle);
        }

        @Override // o0.a
        public void l(View view, int i8) {
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                aVar.l(view, i8);
            } else {
                super.l(view, i8);
            }
        }

        @Override // o0.a
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            o0.a aVar = this.f2278e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public o0.a n(View view) {
            return this.f2278e.remove(view);
        }

        public void o(View view) {
            o0.a aVarL = t.l(view);
            if (aVarL == null || aVarL == this) {
                return;
            }
            this.f2278e.put(view, aVarL);
        }
    }

    public l(RecyclerView recyclerView) {
        this.f2275d = recyclerView;
        o0.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.f2276e = new a(this);
        } else {
            this.f2276e = (a) aVarN;
        }
    }

    @Override // o0.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // o0.a
    public void g(View view, p0.c cVar) {
        super.g(view, cVar);
        if (o() || this.f2275d.getLayoutManager() == null) {
            return;
        }
        this.f2275d.getLayoutManager().onInitializeAccessibilityNodeInfo(cVar);
    }

    @Override // o0.a
    public boolean j(View view, int i8, Bundle bundle) {
        if (super.j(view, i8, bundle)) {
            return true;
        }
        if (o() || this.f2275d.getLayoutManager() == null) {
            return false;
        }
        return this.f2275d.getLayoutManager().performAccessibilityAction(i8, bundle);
    }

    public o0.a n() {
        return this.f2276e;
    }

    public boolean o() {
        return this.f2275d.hasPendingAdapterUpdates();
    }
}
