package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0180d0;
import defpackage.C0214v;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class x extends C0214v {
    public final RecyclerView d;
    public final a e;

    /* JADX INFO: compiled from: RecyclerViewAccessibilityDelegate.java */
    public static class a extends C0214v {
        public final x d;
        public final WeakHashMap e = new WeakHashMap();

        public a(x xVar) {
            this.d = xVar;
        }

        @Override // defpackage.C0214v
        public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
            C0214v c0214v = (C0214v) this.e.get(view);
            return c0214v != null ? c0214v.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // defpackage.C0214v
        public final C0180d0 b(View view) {
            C0214v c0214v = (C0214v) this.e.get(view);
            return c0214v != null ? c0214v.b(view) : super.b(view);
        }

        @Override // defpackage.C0214v
        public final void c(View view, AccessibilityEvent accessibilityEvent) {
            C0214v c0214v = (C0214v) this.e.get(view);
            if (c0214v != null) {
                c0214v.c(view, accessibilityEvent);
            } else {
                super.c(view, accessibilityEvent);
            }
        }

        @Override // defpackage.C0214v
        public final void d(@SuppressLint({"InvalidNullabilityOverride"}) View view, @SuppressLint({"InvalidNullabilityOverride"}) AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            x xVar = this.d;
            RecyclerView recyclerView = xVar.d;
            boolean z = !recyclerView.z || recyclerView.I || recyclerView.i.g();
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            if (!z) {
                RecyclerView recyclerView2 = xVar.d;
                if (recyclerView2.getLayoutManager() != null) {
                    recyclerView2.getLayoutManager().a0(view, accessibilityNodeInfoCompat);
                    C0214v c0214v = (C0214v) this.e.get(view);
                    if (c0214v != null) {
                        c0214v.d(view, accessibilityNodeInfoCompat);
                        return;
                    } else {
                        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        return;
                    }
                }
            }
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }

        @Override // defpackage.C0214v
        public final void e(View view, AccessibilityEvent accessibilityEvent) {
            C0214v c0214v = (C0214v) this.e.get(view);
            if (c0214v != null) {
                c0214v.e(view, accessibilityEvent);
            } else {
                super.e(view, accessibilityEvent);
            }
        }

        @Override // defpackage.C0214v
        public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            C0214v c0214v = (C0214v) this.e.get(viewGroup);
            return c0214v != null ? c0214v.f(viewGroup, view, accessibilityEvent) : super.f(viewGroup, view, accessibilityEvent);
        }

        @Override // defpackage.C0214v
        public final boolean g(@SuppressLint({"InvalidNullabilityOverride"}) View view, int i, @SuppressLint({"InvalidNullabilityOverride"}) Bundle bundle) {
            x xVar = this.d;
            RecyclerView recyclerView = xVar.d;
            if (!(!recyclerView.z || recyclerView.I || recyclerView.i.g())) {
                RecyclerView recyclerView2 = xVar.d;
                if (recyclerView2.getLayoutManager() != null) {
                    C0214v c0214v = (C0214v) this.e.get(view);
                    if (c0214v != null) {
                        if (c0214v.g(view, i, bundle)) {
                            return true;
                        }
                    } else if (super.g(view, i, bundle)) {
                        return true;
                    }
                    RecyclerView.s sVar = recyclerView2.getLayoutManager().b.g;
                    return false;
                }
            }
            return super.g(view, i, bundle);
        }

        @Override // defpackage.C0214v
        public final void h(View view, int i) {
            C0214v c0214v = (C0214v) this.e.get(view);
            if (c0214v != null) {
                c0214v.h(view, i);
            } else {
                super.h(view, i);
            }
        }

        @Override // defpackage.C0214v
        public final void i(View view, AccessibilityEvent accessibilityEvent) {
            C0214v c0214v = (C0214v) this.e.get(view);
            if (c0214v != null) {
                c0214v.i(view, accessibilityEvent);
            } else {
                super.i(view, accessibilityEvent);
            }
        }
    }

    public x(RecyclerView recyclerView) {
        this.d = recyclerView;
        C0214v c0214vJ = j();
        if (c0214vJ == null || !(c0214vJ instanceof a)) {
            this.e = new a(this);
        } else {
            this.e = (a) c0214vJ;
        }
    }

    @Override // defpackage.C0214v
    public final void c(@SuppressLint({"InvalidNullabilityOverride"}) View view, @SuppressLint({"InvalidNullabilityOverride"}) AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = this.d;
            if (!recyclerView.z || recyclerView.I || recyclerView.i.g()) {
                return;
            }
            RecyclerView recyclerView2 = (RecyclerView) view;
            if (recyclerView2.getLayoutManager() != null) {
                recyclerView2.getLayoutManager().Y(accessibilityEvent);
            }
        }
    }

    @Override // defpackage.C0214v
    public void d(@SuppressLint({"InvalidNullabilityOverride"}) View view, @SuppressLint({"InvalidNullabilityOverride"}) AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
        RecyclerView recyclerView = this.d;
        if ((!recyclerView.z || recyclerView.I || recyclerView.i.g()) || recyclerView.getLayoutManager() == null) {
            return;
        }
        RecyclerView.m layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.b;
        layoutManager.Z(recyclerView2.g, recyclerView2.m0, accessibilityNodeInfoCompat);
    }

    @Override // defpackage.C0214v
    public final boolean g(@SuppressLint({"InvalidNullabilityOverride"}) View view, int i, @SuppressLint({"InvalidNullabilityOverride"}) Bundle bundle) {
        boolean z = true;
        if (super.g(view, i, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.d;
        if (recyclerView.z && !recyclerView.I && !recyclerView.i.g()) {
            z = false;
        }
        if (z || recyclerView.getLayoutManager() == null) {
            return false;
        }
        return recyclerView.getLayoutManager().m0(i, bundle);
    }

    public C0214v j() {
        return this.e;
    }
}
