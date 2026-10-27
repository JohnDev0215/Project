package F1;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import r.AbstractC2260s;

/* JADX INFO: loaded from: classes.dex */
public abstract class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f2303a = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0263w f2304b = new C0263w();
    public static final ViewTreeObserverOnGlobalLayoutListenerC0264x c = new ViewTreeObserverOnGlobalLayoutListenerC0264x();

    public static ArrayList a(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static String[] b(AbstractC2260s abstractC2260s) {
        return Build.VERSION.SDK_INT >= 31 ? G.a(abstractC2260s) : (String[]) abstractC2260s.getTag(R.id.tag_on_receive_content_mime_types);
    }

    public static void c(View view, int i7) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z6 = D.a(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z6) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z6 ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i7);
                if (z6) {
                    accessibilityEventObtain.getText().add(D.a(view));
                    if (view.getImportantForAccessibility() == 0) {
                        view.setImportantForAccessibility(1);
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i7 != 32) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i7);
                        return;
                    } catch (AbstractMethodError e10) {
                        h6.n.i("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e10);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.setEventType(32);
            accessibilityEventObtain2.setContentChangeTypes(i7);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.getText().add(D.a(view));
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static C0247f d(View view, C0247f c0247f) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + c0247f + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return G.b(view, c0247f);
        }
        I1.i iVar = (I1.i) view.getTag(R.id.tag_on_receive_content_listener);
        InterfaceC0256o interfaceC0256o = f2304b;
        if (iVar == null) {
            if (view instanceof InterfaceC0256o) {
                interfaceC0256o = (InterfaceC0256o) view;
            }
            return interfaceC0256o.a(c0247f);
        }
        C0247f c0247fA = I1.i.a(view, c0247f);
        if (c0247fA == null) {
            return null;
        }
        if (view instanceof InterfaceC0256o) {
            interfaceC0256o = (InterfaceC0256o) view;
        }
        return interfaceC0256o.a(c0247fA);
    }

    public static void e(View view, int i7) {
        ArrayList arrayListA = a(view);
        for (int i10 = 0; i10 < arrayListA.size(); i10++) {
            if (((G1.d) arrayListA.get(i10)).a() == i7) {
                arrayListA.remove(i10);
                return;
            }
        }
    }

    public static void f(View view, G1.d dVar, G1.n nVar) {
        G1.d dVar2 = new G1.d(null, dVar.f2645b, null, nVar, dVar.c);
        View.AccessibilityDelegate accessibilityDelegateA = E.a(view);
        C0243b c0243b = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0242a ? ((C0242a) accessibilityDelegateA).f2323a : new C0243b(accessibilityDelegateA);
        if (c0243b == null) {
            c0243b = new C0243b();
        }
        g(view, c0243b);
        e(view, dVar2.a());
        a(view).add(dVar2);
        c(view, 0);
    }

    public static void g(View view, C0243b c0243b) {
        if (c0243b == null && (E.a(view) instanceof C0242a)) {
            c0243b = new C0243b();
        }
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        view.setAccessibilityDelegate(c0243b == null ? null : c0243b.f2325b);
    }

    public static void h(View view, CharSequence charSequence) {
        Object tag;
        E5.z zVar = new E5.z(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
        if (Build.VERSION.SDK_INT >= zVar.c) {
            D.d(view, charSequence);
        } else {
            if (Build.VERSION.SDK_INT >= zVar.c) {
                tag = D.a(view);
            } else {
                tag = view.getTag(zVar.f1841b);
                if (!((Class) zVar.f1842e).isInstance(tag)) {
                    tag = null;
                }
            }
            if (!TextUtils.equals((CharSequence) tag, charSequence)) {
                View.AccessibilityDelegate accessibilityDelegateA = E.a(view);
                C0243b c0243b = accessibilityDelegateA != null ? accessibilityDelegateA instanceof C0242a ? ((C0242a) accessibilityDelegateA).f2323a : new C0243b(accessibilityDelegateA) : null;
                if (c0243b == null) {
                    c0243b = new C0243b();
                }
                g(view, c0243b);
                view.setTag(zVar.f1841b, charSequence);
                c(view, zVar.d);
            }
        }
        ViewTreeObserverOnGlobalLayoutListenerC0264x viewTreeObserverOnGlobalLayoutListenerC0264x = c;
        if (charSequence == null) {
            viewTreeObserverOnGlobalLayoutListenerC0264x.l.remove(view);
            view.removeOnAttachStateChangeListener(viewTreeObserverOnGlobalLayoutListenerC0264x);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC0264x);
        } else {
            viewTreeObserverOnGlobalLayoutListenerC0264x.l.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(viewTreeObserverOnGlobalLayoutListenerC0264x);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC0264x);
            }
        }
    }
}
