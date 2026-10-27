package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class fd extends a9 {
    public final RecyclerView d;
    public final a e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends a9 {
        public final fd d;
        public Map<View, a9> e = new WeakHashMap();

        public a(fd fdVar) {
            this.d = fdVar;
        }

        @Override // defpackage.a9
        public boolean a(View view, AccessibilityEvent accessibilityEvent) {
            a9 a9Var = this.e.get(view);
            return a9Var != null ? a9Var.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // defpackage.a9
        public fa b(View view) {
            a9 a9Var = this.e.get(view);
            return a9Var != null ? a9Var.b(view) : super.b(view);
        }

        @Override // defpackage.a9
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                a9Var.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // defpackage.a9
        public void g(View view, ea eaVar) {
            if (this.d.o() || this.d.d.getLayoutManager() == null) {
                super.g(view, eaVar);
                return;
            }
            this.d.d.getLayoutManager().O0(view, eaVar);
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                a9Var.g(view, eaVar);
            } else {
                super.g(view, eaVar);
            }
        }

        @Override // defpackage.a9
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                a9Var.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // defpackage.a9
        public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            a9 a9Var = this.e.get(viewGroup);
            return a9Var != null ? a9Var.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // defpackage.a9
        public boolean j(View view, int i, Bundle bundle) {
            if (this.d.o() || this.d.d.getLayoutManager() == null) {
                return super.j(view, i, bundle);
            }
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                if (a9Var.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.d.d.getLayoutManager().i1(view, i, bundle);
        }

        @Override // defpackage.a9
        public void l(View view, int i) {
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                a9Var.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // defpackage.a9
        public void m(View view, AccessibilityEvent accessibilityEvent) {
            a9 a9Var = this.e.get(view);
            if (a9Var != null) {
                a9Var.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        public a9 n(View view) {
            return this.e.remove(view);
        }

        public void o(View view) {
            a9 a9VarK = t9.k(view);
            if (a9VarK == null || a9VarK == this) {
                return;
            }
            this.e.put(view, a9VarK);
        }
    }

    public fd(RecyclerView recyclerView) {
        this.d = recyclerView;
        a9 a9VarN = n();
        if (a9VarN == null || !(a9VarN instanceof a)) {
            this.e = new a(this);
        } else {
            this.e = (a) a9VarN;
        }
    }

    @Override // defpackage.a9
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

    @Override // defpackage.a9
    public void g(View view, ea eaVar) {
        super.g(view, eaVar);
        if (o() || this.d.getLayoutManager() == null) {
            return;
        }
        this.d.getLayoutManager().M0(eaVar);
    }

    @Override // defpackage.a9
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.d.getLayoutManager() == null) {
            return false;
        }
        return this.d.getLayoutManager().g1(i, bundle);
    }

    public a9 n() {
        return this.e;
    }

    public boolean o() {
        return this.d.n0();
    }
}
