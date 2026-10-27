package o0;

import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import p0.c;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final View.AccessibilityDelegate f6657a = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View.AccessibilityDelegate f6658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View.AccessibilityDelegate f6659c;

    /* JADX INFO: renamed from: o0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    public static final class C0097a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f6660a;

        public C0097a(a aVar) {
            this.f6660a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f6660a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            p0.d dVarB = this.f6660a.b(view);
            if (dVarB != null) {
                return (AccessibilityNodeProvider) dVarB.d();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f6660a.f(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            p0.c cVarF0 = p0.c.F0(accessibilityNodeInfo);
            cVarF0.w0(t.T(view));
            cVarF0.l0(t.O(view));
            cVarF0.r0(t.p(view));
            this.f6660a.g(view, cVarF0);
            cVarF0.f(accessibilityNodeInfo.getText(), view);
            List<c.a> listC = a.c(view);
            for (int i8 = 0; i8 < listC.size(); i8++) {
                cVarF0.b(listC.get(i8));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f6660a.h(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f6660a.i(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i8, Bundle bundle) {
            return this.f6660a.j(view, i8, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i8) {
            this.f6660a.l(view, i8);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f6660a.m(view, accessibilityEvent);
        }
    }

    public a() {
        this(f6657a);
    }

    public static List<c.a> c(View view) {
        List<c.a> list = (List) view.getTag(R$id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f6658b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public p0.d b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        if (Build.VERSION.SDK_INT < 16 || (accessibilityNodeProvider = this.f6658b.getAccessibilityNodeProvider(view)) == null) {
            return null;
        }
        return new p0.d(accessibilityNodeProvider);
    }

    public View.AccessibilityDelegate d() {
        return this.f6659c;
    }

    public final boolean e(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrQ = p0.c.q(view.createAccessibilityNodeInfo().getText());
            for (int i8 = 0; clickableSpanArrQ != null && i8 < clickableSpanArrQ.length; i8++) {
                if (clickableSpan.equals(clickableSpanArrQ[i8])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        this.f6658b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void g(View view, p0.c cVar) {
        this.f6658b.onInitializeAccessibilityNodeInfo(view, cVar.E0());
    }

    public void h(View view, AccessibilityEvent accessibilityEvent) {
        this.f6658b.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f6658b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean j(View view, int i8, Bundle bundle) {
        List<c.a> listC = c(view);
        boolean zPerformAccessibilityAction = false;
        int i9 = 0;
        while (true) {
            if (i9 >= listC.size()) {
                break;
            }
            c.a aVar = listC.get(i9);
            if (aVar.b() == i8) {
                zPerformAccessibilityAction = aVar.d(view, bundle);
                break;
            }
            i9++;
        }
        if (!zPerformAccessibilityAction && Build.VERSION.SDK_INT >= 16) {
            zPerformAccessibilityAction = this.f6658b.performAccessibilityAction(view, i8, bundle);
        }
        return (zPerformAccessibilityAction || i8 != R$id.accessibility_action_clickable_span) ? zPerformAccessibilityAction : k(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public final boolean k(int i8, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i8)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!e(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    public void l(View view, int i8) {
        this.f6658b.sendAccessibilityEvent(view, i8);
    }

    public void m(View view, AccessibilityEvent accessibilityEvent) {
        this.f6658b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f6658b = accessibilityDelegate;
        this.f6659c = new C0097a(this);
    }
}
