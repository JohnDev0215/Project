package p;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class o22 extends o2 {
    public final p22 d;
    public final WeakHashMap e = new WeakHashMap();

    public o22(p22 p22Var) {
        this.d = p22Var;
    }

    @Override // p.o2
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        o2 o2Var = (o2) this.e.get(view);
        return o2Var != null ? o2Var.a(view, accessibilityEvent) : this.a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // p.o2
    public final oh1 b(View view) {
        o2 o2Var = (o2) this.e.get(view);
        return o2Var != null ? o2Var.b(view) : super.b(view);
    }

    @Override // p.o2
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        o2 o2Var = (o2) this.e.get(view);
        if (o2Var != null) {
            o2Var.c(view, accessibilityEvent);
        } else {
            super.c(view, accessibilityEvent);
        }
    }

    @Override // p.o2
    public final void d(View view, c3 c3Var) {
        p22 p22Var = this.d;
        boolean zK = p22Var.d.K();
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        if (!zK) {
            RecyclerView recyclerView = p22Var.d;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().P(view, c3Var);
                o2 o2Var = (o2) this.e.get(view);
                if (o2Var != null) {
                    o2Var.d(view, c3Var);
                    return;
                } else {
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    return;
                }
            }
        }
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
    }

    @Override // p.o2
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        o2 o2Var = (o2) this.e.get(view);
        if (o2Var != null) {
            o2Var.e(view, accessibilityEvent);
        } else {
            super.e(view, accessibilityEvent);
        }
    }

    @Override // p.o2
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        o2 o2Var = (o2) this.e.get(viewGroup);
        return o2Var != null ? o2Var.f(viewGroup, view, accessibilityEvent) : this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // p.o2
    public final boolean g(View view, int i, Bundle bundle) {
        p22 p22Var = this.d;
        if (!p22Var.d.K()) {
            RecyclerView recyclerView = p22Var.d;
            if (recyclerView.getLayoutManager() != null) {
                o2 o2Var = (o2) this.e.get(view);
                if (o2Var != null) {
                    if (o2Var.g(view, i, bundle)) {
                        return true;
                    }
                } else if (super.g(view, i, bundle)) {
                    return true;
                }
                f22 f22Var = recyclerView.getLayoutManager().b.e;
                return false;
            }
        }
        return super.g(view, i, bundle);
    }

    @Override // p.o2
    public final void h(View view, int i) {
        o2 o2Var = (o2) this.e.get(view);
        if (o2Var != null) {
            o2Var.h(view, i);
        } else {
            super.h(view, i);
        }
    }

    @Override // p.o2
    public final void i(View view, AccessibilityEvent accessibilityEvent) {
        o2 o2Var = (o2) this.e.get(view);
        if (o2Var != null) {
            o2Var.i(view, accessibilityEvent);
        } else {
            super.i(view, accessibilityEvent);
        }
    }
}
