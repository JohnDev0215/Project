package F1;

import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import com.stingray.drivemodedetector.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: F1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0243b {
    public static final View.AccessibilityDelegate c = new View.AccessibilityDelegate();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View.AccessibilityDelegate f2324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0242a f2325b;

    public C0243b() {
        this(c);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f2324a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public B2.O b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.f2324a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new B2.O(accessibilityNodeProvider, 15);
        }
        return null;
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f2324a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, G1.e eVar) {
        this.f2324a.onInitializeAccessibilityNodeInfo(view, eVar.f2646a);
    }

    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.f2324a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f2324a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean g(View view, int i7, Bundle bundle) {
        boolean zPerformAccessibilityAction;
        WeakReference weakReference;
        ClickableSpan clickableSpan;
        List listEmptyList = (List) view.getTag(R.id.tag_accessibility_actions);
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        boolean z6 = false;
        int i10 = 0;
        while (true) {
            if (i10 >= listEmptyList.size()) {
                break;
            }
            G1.d dVar = (G1.d) listEmptyList.get(i10);
            if (dVar.a() == i7) {
                G1.n nVar = dVar.d;
                if (nVar != null) {
                    Class cls = dVar.c;
                    if (cls != null) {
                        try {
                            if (cls.getDeclaredConstructor(null).newInstance(null) == null) {
                                throw null;
                            }
                            throw new ClassCastException();
                        } catch (Exception e10) {
                            h6.n.i("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: ".concat(cls.getName()), e10);
                        }
                    }
                    zPerformAccessibilityAction = nVar.a(view);
                }
            } else {
                i10++;
            }
        }
        zPerformAccessibilityAction = false;
        if (!zPerformAccessibilityAction) {
            zPerformAccessibilityAction = this.f2324a.performAccessibilityAction(view, i7, bundle);
        }
        if (zPerformAccessibilityAction || i7 != R.id.accessibility_action_clickable_span || bundle == null) {
            return zPerformAccessibilityAction;
        }
        int i11 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray != null && (weakReference = (WeakReference) sparseArray.get(i11)) != null && (clickableSpan = (ClickableSpan) weakReference.get()) != null) {
            CharSequence text = view.createAccessibilityNodeInfo().getText();
            ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
            int i12 = 0;
            while (true) {
                if (clickableSpanArr == null || i12 >= clickableSpanArr.length) {
                    break;
                }
                if (clickableSpan.equals(clickableSpanArr[i12])) {
                    clickableSpan.onClick(view);
                    z6 = true;
                    break;
                }
                i12++;
            }
        }
        return z6;
    }

    public void h(View view, int i7) {
        this.f2324a.sendAccessibilityEvent(view, i7);
    }

    public void i(View view, AccessibilityEvent accessibilityEvent) {
        this.f2324a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public C0243b(View.AccessibilityDelegate accessibilityDelegate) {
        this.f2324a = accessibilityDelegate;
        this.f2325b = new C0242a(this);
    }
}
