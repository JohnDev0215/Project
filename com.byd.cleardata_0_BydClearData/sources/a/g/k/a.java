package a.g.k;

import a.g.k.c0.c;
import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final View.AccessibilityDelegate f371a = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View.AccessibilityDelegate f372b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View.AccessibilityDelegate f373c;

    /* JADX INFO: renamed from: a.g.k.a$a, reason: collision with other inner class name */
    static final class C0012a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a f374a;

        C0012a(a aVar) {
            this.f374a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f374a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            a.g.k.c0.d dVarB = this.f374a.b(view);
            if (dVarB != null) {
                return (AccessibilityNodeProvider) dVarB.d();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f374a.f(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            a.g.k.c0.c cVarV0 = a.g.k.c0.c.v0(accessibilityNodeInfo);
            cVarV0.o0(t.Q(view));
            cVarV0.g0(t.L(view));
            cVarV0.k0(t.n(view));
            this.f374a.g(view, cVarV0);
            cVarV0.e(accessibilityNodeInfo.getText(), view);
            List<c.a> listC = a.c(view);
            for (int i = 0; i < listC.size(); i++) {
                cVarV0.b(listC.get(i));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f374a.h(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f374a.i(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            return this.f374a.j(view, i, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            this.f374a.l(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f374a.m(view, accessibilityEvent);
        }
    }

    public a() {
        this(f371a);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f372b = accessibilityDelegate;
        this.f373c = new C0012a(this);
    }

    static List<c.a> c(View view) {
        List<c.a> list = (List) view.getTag(a.g.b.H);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean e(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrP = a.g.k.c0.c.p(view.createAccessibilityNodeInfo().getText());
            for (int i = 0; clickableSpanArrP != null && i < clickableSpanArrP.length; i++) {
                if (clickableSpan.equals(clickableSpanArrP[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean k(int i, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(a.g.b.I);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!e(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f372b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public a.g.k.c0.d b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = this.f372b.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new a.g.k.c0.d(accessibilityNodeProvider);
    }

    View.AccessibilityDelegate d() {
        return this.f373c;
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        this.f372b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void g(View view, a.g.k.c0.c cVar) {
        this.f372b.onInitializeAccessibilityNodeInfo(view, cVar.u0());
    }

    public void h(View view, AccessibilityEvent accessibilityEvent) {
        this.f372b.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f372b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean j(View view, int i, Bundle bundle) {
        List<c.a> listC = c(view);
        boolean zPerformAccessibilityAction = false;
        int i2 = 0;
        while (true) {
            if (i2 >= listC.size()) {
                break;
            }
            c.a aVar = listC.get(i2);
            if (aVar.b() == i) {
                zPerformAccessibilityAction = aVar.d(view, bundle);
                break;
            }
            i2++;
        }
        if (!zPerformAccessibilityAction && Build.VERSION.SDK_INT >= 16) {
            zPerformAccessibilityAction = this.f372b.performAccessibilityAction(view, i, bundle);
        }
        return (zPerformAccessibilityAction || i != a.g.b.f262a) ? zPerformAccessibilityAction : k(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public void l(View view, int i) {
        this.f372b.sendAccessibilityEvent(view, i);
    }

    public void m(View view, AccessibilityEvent accessibilityEvent) {
        this.f372b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
