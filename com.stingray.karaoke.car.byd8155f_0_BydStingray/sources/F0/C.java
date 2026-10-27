package F0;

import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import com.stingray.drivemodedetector.R;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import q.ViewOnKeyListenerC2148e;
import q.ViewOnKeyListenerC2160q;

/* JADX INFO: loaded from: classes.dex */
public final class C implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2009m;

    public /* synthetic */ C(Object obj, int i7) {
        this.l = i7;
        this.f2009m = obj;
    }

    private final void a(View view) {
    }

    private final void b(View view) {
    }

    private final void c(View view) {
    }

    private final void d(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.l) {
            case 0:
                M m10 = (M) this.f2009m;
                AccessibilityManager accessibilityManager = m10.f2067g;
                accessibilityManager.addAccessibilityStateChangeListener(m10.f2069i);
                accessibilityManager.addTouchExplorationStateChangeListener(m10.f2070j);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) throws IllegalAccessException, InvocationTargetException {
        boolean z6;
        switch (this.l) {
            case 0:
                M m10 = (M) this.f2009m;
                m10.l.removeCallbacks(m10.f2062K);
                AccessibilityManager accessibilityManager = m10.f2067g;
                accessibilityManager.removeAccessibilityStateChangeListener(m10.f2069i);
                accessibilityManager.removeTouchExplorationStateChangeListener(m10.f2070j);
                break;
            case 1:
                AbstractC0191a abstractC0191a = (AbstractC0191a) this.f2009m;
                A7.m.f(abstractC0191a, "<this>");
                Iterator it = P8.m.d0(abstractC0191a.getParent(), F1.K.f2308t).iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object obj = (ViewParent) it.next();
                        if (obj instanceof View) {
                            View view2 = (View) obj;
                            A7.m.f(view2, "<this>");
                            Object tag = view2.getTag(R.id.is_pooling_container_tag);
                            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
                            if (bool != null ? bool.booleanValue() : false) {
                                z6 = true;
                            }
                        }
                    }
                }
                if (!z6) {
                    abstractC0191a.c();
                }
                break;
            case 2:
                view.removeOnAttachStateChangeListener(this);
                ((S8.k0) this.f2009m).g(null);
                break;
            case 3:
                ViewOnKeyListenerC2148e viewOnKeyListenerC2148e = (ViewOnKeyListenerC2148e) this.f2009m;
                ViewTreeObserver viewTreeObserver = viewOnKeyListenerC2148e.f21028I;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        viewOnKeyListenerC2148e.f21028I = view.getViewTreeObserver();
                    }
                    viewOnKeyListenerC2148e.f21028I.removeGlobalOnLayoutListener(viewOnKeyListenerC2148e.f21037t);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            default:
                ViewOnKeyListenerC2160q viewOnKeyListenerC2160q = (ViewOnKeyListenerC2160q) this.f2009m;
                ViewTreeObserver viewTreeObserver2 = viewOnKeyListenerC2160q.f21114z;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        viewOnKeyListenerC2160q.f21114z = view.getViewTreeObserver();
                    }
                    viewOnKeyListenerC2160q.f21114z.removeGlobalOnLayoutListener(viewOnKeyListenerC2160q.f21108t);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }
}
