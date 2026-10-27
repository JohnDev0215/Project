package i0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import j0.c;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final View.AccessibilityDelegate f6826c = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View.AccessibilityDelegate f6827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View.AccessibilityDelegate f6828b;

    /* JADX INFO: renamed from: i0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityDelegateCompat.java */
    static final class C0083a extends View.AccessibilityDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a f6829a;

        C0083a(a aVar) {
            this.f6829a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f6829a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            j0.d dVarB = this.f6829a.b(view);
            if (dVarB != null) {
                return (AccessibilityNodeProvider) dVarB.e();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f6829a.f(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            j0.c cVarP0 = j0.c.p0(accessibilityNodeInfo);
            cVarP0.g0(u.S(view));
            cVarP0.Y(u.N(view));
            cVarP0.c0(u.m(view));
            cVarP0.k0(u.G(view));
            this.f6829a.g(view, cVarP0);
            cVarP0.d(accessibilityNodeInfo.getText(), view);
            List<c.a> listC = a.c(view);
            for (int i7 = 0; i7 < listC.size(); i7++) {
                cVarP0.b(listC.get(i7));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f6829a.h(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f6829a.i(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i7, Bundle bundle) {
            return this.f6829a.j(view, i7, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i7) {
            this.f6829a.l(view, i7);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f6829a.m(view, accessibilityEvent);
        }
    }

    public a() {
        this(f6826c);
    }

    static List<c.a> c(View view) {
        List<c.a> list = (List) view.getTag(v.a.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean e(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrM = j0.c.m(view.createAccessibilityNodeInfo().getText());
            for (int i7 = 0; clickableSpanArrM != null && i7 < clickableSpanArrM.length; i7++) {
                if (clickableSpan.equals(clickableSpanArrM[i7])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean k(int i7, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(v.a.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i7)) == null) {
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
        return this.f6827a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public j0.d b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.f6827a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new j0.d(accessibilityNodeProvider);
        }
        return null;
    }

    View.AccessibilityDelegate d() {
        return this.f6828b;
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        this.f6827a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void g(View view, j0.c cVar) {
        this.f6827a.onInitializeAccessibilityNodeInfo(view, cVar.o0());
    }

    public void h(View view, AccessibilityEvent accessibilityEvent) {
        this.f6827a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f6827a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean j(View view, int i7, Bundle bundle) {
        List<c.a> listC = c(view);
        boolean zPerformAccessibilityAction = false;
        int i8 = 0;
        while (true) {
            if (i8 >= listC.size()) {
                break;
            }
            c.a aVar = listC.get(i8);
            if (aVar.b() == i7) {
                zPerformAccessibilityAction = aVar.d(view, bundle);
                break;
            }
            i8++;
        }
        if (!zPerformAccessibilityAction) {
            zPerformAccessibilityAction = this.f6827a.performAccessibilityAction(view, i7, bundle);
        }
        return (zPerformAccessibilityAction || i7 != v.a.accessibility_action_clickable_span) ? zPerformAccessibilityAction : k(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public void l(View view, int i7) {
        this.f6827a.sendAccessibilityEvent(view, i7);
    }

    public void m(View view, AccessibilityEvent accessibilityEvent) {
        this.f6827a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f6827a = accessibilityDelegate;
        this.f6828b = new C0083a(this);
    }
}
