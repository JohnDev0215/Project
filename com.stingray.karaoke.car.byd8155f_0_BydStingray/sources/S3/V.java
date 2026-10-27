package s3;

import F1.C0243b;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;
import k3.C1648i0;

/* JADX INFO: loaded from: classes.dex */
public final class V extends C0243b {
    public final W d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WeakHashMap f22234e = new WeakHashMap();

    public V(W w10) {
        this.d = w10;
    }

    @Override // F1.C0243b
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        return c0243b != null ? c0243b.a(view, accessibilityEvent) : this.f2324a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // F1.C0243b
    public final B2.O b(View view) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        return c0243b != null ? c0243b.b(view) : super.b(view);
    }

    @Override // F1.C0243b
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        if (c0243b != null) {
            c0243b.c(view, accessibilityEvent);
        } else {
            super.c(view, accessibilityEvent);
        }
    }

    @Override // F1.C0243b
    public final void d(View view, G1.e eVar) {
        W w10 = this.d;
        boolean zI = w10.d.I();
        View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        if (!zI) {
            RecyclerView recyclerView = w10.d;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().R(view, eVar);
                C0243b c0243b = (C0243b) this.f22234e.get(view);
                if (c0243b != null) {
                    c0243b.d(view, eVar);
                    return;
                } else {
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    return;
                }
            }
        }
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
    }

    @Override // F1.C0243b
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        if (c0243b != null) {
            c0243b.e(view, accessibilityEvent);
        } else {
            super.e(view, accessibilityEvent);
        }
    }

    @Override // F1.C0243b
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        C0243b c0243b = (C0243b) this.f22234e.get(viewGroup);
        return c0243b != null ? c0243b.f(viewGroup, view, accessibilityEvent) : this.f2324a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // F1.C0243b
    public final boolean g(View view, int i7, Bundle bundle) {
        W w10 = this.d;
        if (!w10.d.I()) {
            RecyclerView recyclerView = w10.d;
            if (recyclerView.getLayoutManager() != null) {
                C0243b c0243b = (C0243b) this.f22234e.get(view);
                if (c0243b != null) {
                    if (c0243b.g(view, i7, bundle)) {
                        return true;
                    }
                } else if (super.g(view, i7, bundle)) {
                    return true;
                }
                C1648i0 c1648i0 = recyclerView.getLayoutManager().f22180b.f12138n;
                return false;
            }
        }
        return super.g(view, i7, bundle);
    }

    @Override // F1.C0243b
    public final void h(View view, int i7) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        if (c0243b != null) {
            c0243b.h(view, i7);
        } else {
            super.h(view, i7);
        }
    }

    @Override // F1.C0243b
    public final void i(View view, AccessibilityEvent accessibilityEvent) {
        C0243b c0243b = (C0243b) this.f22234e.get(view);
        if (c0243b != null) {
            c0243b.i(view, accessibilityEvent);
        } else {
            super.i(view, accessibilityEvent);
        }
    }
}
