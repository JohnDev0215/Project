package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.spotify.music.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class zt2 {
    public static WeakHashMap a;
    public static Field b;
    public static boolean c;
    public static final int[] d;
    public static final dt2 e;
    public static final ft2 f;

    static {
        new AtomicInteger(1);
        a = null;
        c = false;
        d = new int[]{R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
        e = new dt2();
        f = new ft2();
    }

    public static tu2 a(View view) {
        if (a == null) {
            a = new WeakHashMap();
        }
        tu2 tu2Var = (tu2) a.get(view);
        if (tu2Var != null) {
            return tu2Var;
        }
        tu2 tu2Var2 = new tu2(view);
        a.put(view, tu2Var2);
        return tu2Var2;
    }

    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = yt2.d;
        yt2 yt2Var = (yt2) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (yt2Var == null) {
            yt2Var = new yt2();
            yt2Var.a = null;
            yt2Var.b = null;
            yt2Var.c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, yt2Var);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = yt2Var.a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = yt2.d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (yt2Var.a == null) {
                            yt2Var.a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = yt2.d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                yt2Var.a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    yt2Var.a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View viewA = yt2Var.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                if (yt2Var.b == null) {
                    yt2Var.b = new SparseArray();
                }
                yt2Var.b.put(keyCode, new WeakReference(viewA));
            }
        }
        return viewA != null;
    }

    public static View.AccessibilityDelegate c(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return tt2.a(view);
        }
        if (c) {
            return null;
        }
        if (b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                c = true;
                return null;
            }
        }
        try {
            Object obj = b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            c = true;
            return null;
        }
    }

    public static ArrayList d(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static String[] e(View view) {
        return Build.VERSION.SDK_INT >= 31 ? vt2.a(view) : (String[]) view.getTag(R.id.tag_on_receive_content_mime_types);
    }

    public static void f(View view, int i) {
        Object tag;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            int i2 = Build.VERSION.SDK_INT;
            Object objB = null;
            if (i2 >= 28) {
                tag = st2.b(view);
            } else {
                tag = view.getTag(R.id.tag_accessibility_pane_title);
                if (!CharSequence.class.isInstance(tag)) {
                    tag = null;
                }
            }
            boolean z = ((CharSequence) tag) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (lt2.a(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                lt2.g(accessibilityEventObtain, i);
                if (z) {
                    List<CharSequence> text = accessibilityEventObtain.getText();
                    if (i2 >= 28) {
                        objB = st2.b(view);
                    } else {
                        Object tag2 = view.getTag(R.id.tag_accessibility_pane_title);
                        if (CharSequence.class.isInstance(tag2)) {
                            objB = tag2;
                        }
                    }
                    text.add((CharSequence) objB);
                    if (it2.c(view) == 0) {
                        it2.s(view, 1);
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i != 32) {
                if (view.getParent() != null) {
                    try {
                        lt2.e(view.getParent(), view, view, i);
                        return;
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e2);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.setEventType(32);
            lt2.g(accessibilityEventObtain2, i);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            List<CharSequence> text2 = accessibilityEventObtain2.getText();
            if (i2 >= 28) {
                objB = st2.b(view);
            } else {
                Object tag3 = view.getTag(R.id.tag_accessibility_pane_title);
                if (CharSequence.class.isInstance(tag3)) {
                    objB = tag3;
                }
            }
            text2.add((CharSequence) objB);
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static jz g(View view, jz jzVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Objects.toString(jzVar);
            view.getClass();
            view.getId();
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return vt2.b(view, jzVar);
        }
        bq1 bq1Var = (bq1) view.getTag(R.id.tag_on_receive_content_listener);
        cq1 cq1Var = e;
        if (bq1Var == null) {
            if (view instanceof cq1) {
                cq1Var = (cq1) view;
            }
            return cq1Var.a(jzVar);
        }
        jz jzVarA = ((gk2) bq1Var).a(view, jzVar);
        if (jzVarA == null) {
            return null;
        }
        if (view instanceof cq1) {
            cq1Var = (cq1) view;
        }
        return cq1Var.a(jzVarA);
    }

    public static void h(View view, int i) {
        ArrayList arrayListD = d(view);
        for (int i2 = 0; i2 < arrayListD.size(); i2++) {
            if (((w2) arrayListD.get(i2)).a() == i) {
                arrayListD.remove(i2);
                return;
            }
        }
    }

    public static void i(View view, w2 w2Var, p3 p3Var) {
        w2 w2Var2 = new w2(null, w2Var.b, null, p3Var, w2Var.c);
        View.AccessibilityDelegate accessibilityDelegateC = c(view);
        o2 o2Var = accessibilityDelegateC == null ? null : accessibilityDelegateC instanceof m2 ? ((m2) accessibilityDelegateC).a : new o2(accessibilityDelegateC);
        if (o2Var == null) {
            o2Var = new o2();
        }
        k(view, o2Var);
        h(view, w2Var2.a());
        d(view).add(w2Var2);
        f(view, 0);
    }

    public static void j(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            tt2.d(view, context, iArr, attributeSet, typedArray, i, 0);
        }
    }

    public static void k(View view, o2 o2Var) {
        if (o2Var == null && (c(view) instanceof m2)) {
            o2Var = new o2();
        }
        if (it2.c(view) == 0) {
            it2.s(view, 1);
        }
        view.setAccessibilityDelegate(o2Var == null ? null : o2Var.b);
    }

    public static void l(View view, CharSequence charSequence) {
        new et2(R.id.tag_accessibility_pane_title, 8, 28, true ? 1 : 0).b(view, charSequence);
        ft2 ft2Var = f;
        if (charSequence == null) {
            ft2Var.c.remove(view);
            view.removeOnAttachStateChangeListener(ft2Var);
            it2.o(view.getViewTreeObserver(), ft2Var);
        } else {
            ft2Var.c.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(ft2Var);
            if (lt2.b(view)) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(ft2Var);
            }
        }
    }
}
