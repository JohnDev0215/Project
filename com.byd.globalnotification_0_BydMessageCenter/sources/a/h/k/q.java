package a.h.k;

import a.h.k.a;
import a.h.k.x.b;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f824a = new AtomicInteger(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static WeakHashMap<View, s> f825b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f826c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f827d = {a.h.a.accessibility_custom_action_0, a.h.a.accessibility_custom_action_1, a.h.a.accessibility_custom_action_2, a.h.a.accessibility_custom_action_3, a.h.a.accessibility_custom_action_4, a.h.a.accessibility_custom_action_5, a.h.a.accessibility_custom_action_6, a.h.a.accessibility_custom_action_7, a.h.a.accessibility_custom_action_8, a.h.a.accessibility_custom_action_9, a.h.a.accessibility_custom_action_10, a.h.a.accessibility_custom_action_11, a.h.a.accessibility_custom_action_12, a.h.a.accessibility_custom_action_13, a.h.a.accessibility_custom_action_14, a.h.a.accessibility_custom_action_15, a.h.a.accessibility_custom_action_16, a.h.a.accessibility_custom_action_17, a.h.a.accessibility_custom_action_18, a.h.a.accessibility_custom_action_19, a.h.a.accessibility_custom_action_20, a.h.a.accessibility_custom_action_21, a.h.a.accessibility_custom_action_22, a.h.a.accessibility_custom_action_23, a.h.a.accessibility_custom_action_24, a.h.a.accessibility_custom_action_25, a.h.a.accessibility_custom_action_26, a.h.a.accessibility_custom_action_27, a.h.a.accessibility_custom_action_28, a.h.a.accessibility_custom_action_29, a.h.a.accessibility_custom_action_30, a.h.a.accessibility_custom_action_31};

    public static abstract class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f828a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<T> f829b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f830c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f831d;

        public a(int i, Class<T> cls, int i2) {
            this.f828a = i;
            this.f829b = cls;
            this.f831d = 0;
            this.f830c = i2;
        }

        public a(int i, Class<T> cls, int i2, int i3) {
            this.f828a = i;
            this.f829b = cls;
            this.f831d = i2;
            this.f830c = i3;
        }

        public boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        public abstract T b(View view);

        public abstract void c(View view, T t);

        public T d(View view) {
            if (Build.VERSION.SDK_INT >= this.f830c) {
                return b(view);
            }
            T t = (T) view.getTag(this.f828a);
            if (this.f829b.isInstance(t)) {
                return t;
            }
            return null;
        }

        public abstract boolean e(T t, T t2);
    }

    static {
        new WeakHashMap();
    }

    public static boolean A(View view) {
        return view.hasTransientState();
    }

    public static boolean B(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean C(View view) {
        return view.isLaidOut();
    }

    public static boolean D(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static boolean E(View view) {
        return view.isPaddingRelative();
    }

    public static boolean F(View view) {
        Boolean boolD = new m(a.h.a.tag_screen_reader_focusable, Boolean.class, 28).d(view);
        if (boolD == null) {
            return false;
        }
        return boolD.booleanValue();
    }

    public static void G(View view, int i) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = g(view) != null && view.getVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i);
                if (z) {
                    accessibilityEventObtain.getText().add(g(view));
                    if (view.getImportantForAccessibility() == 0) {
                        view.setImportantForAccessibility(1);
                    }
                    ViewParent parent = view.getParent();
                    while (true) {
                        if (!(parent instanceof View)) {
                            break;
                        }
                        if (((View) parent).getImportantForAccessibility() == 4) {
                            view.setImportantForAccessibility(2);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i == 32) {
                AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.setEventType(32);
                accessibilityEventObtain2.setContentChangeTypes(i);
                accessibilityEventObtain2.setSource(view);
                view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.getText().add(g(view));
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void H(View view, int i) {
        view.offsetLeftAndRight(i);
    }

    public static void I(View view, int i) {
        view.offsetTopAndBottom(i);
    }

    public static w J(View view, w wVar) {
        WindowInsets windowInsetsF = wVar.f();
        if (windowInsetsF != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsF);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsF)) {
                return w.h(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return wVar;
    }

    public static void K(View view, a.h.k.x.b bVar) {
        view.onInitializeAccessibilityNodeInfo(bVar.f856a);
    }

    public static boolean L(View view, int i, Bundle bundle) {
        return view.performAccessibilityAction(i, bundle);
    }

    public static void M(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void N(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void O(View view, Runnable runnable, long j) {
        view.postOnAnimationDelayed(runnable, j);
    }

    public static void P(View view, int i) {
        Q(i, view);
        G(view, 0);
    }

    public static void Q(int i, View view) {
        List<b.a> listH = h(view);
        for (int i2 = 0; i2 < listH.size(); i2++) {
            if (listH.get(i2).a() == i) {
                listH.remove(i2);
                return;
            }
        }
    }

    public static void R(View view, b.a aVar, CharSequence charSequence, a.h.k.x.d dVar) {
        a(view, new b.a(null, aVar.f862b, null, dVar, aVar.f863c));
    }

    public static void S(View view) {
        view.requestApplyInsets();
    }

    public static void T(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
        view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
    }

    public static void U(View view, a.h.k.a aVar) {
        if (aVar == null && (view.getAccessibilityDelegate() instanceof a.C0015a)) {
            aVar = new a.h.k.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.f808b);
    }

    public static void V(View view, boolean z) {
        p pVar = new p(a.h.a.tag_accessibility_heading, Boolean.class, 28);
        Boolean boolValueOf = Boolean.valueOf(z);
        if (Build.VERSION.SDK_INT >= pVar.f830c) {
            pVar.c(view, boolValueOf);
            return;
        }
        if (pVar.e(pVar.d(view), boolValueOf)) {
            a.h.k.a aVarF = f(view);
            if (aVarF == null) {
                aVarF = new a.h.k.a();
            }
            U(view, aVarF);
            view.setTag(pVar.f828a, boolValueOf);
            G(view, pVar.f831d);
        }
    }

    public static void W(View view, int i) {
        view.setAccessibilityLiveRegion(i);
    }

    public static void X(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void Y(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static void Z(View view, float f2) {
        view.setElevation(f2);
    }

    public static void a(View view, b.a aVar) {
        a.h.k.a aVarF = f(view);
        if (aVarF == null) {
            aVarF = new a.h.k.a();
        }
        U(view, aVarF);
        Q(aVar.a(), view);
        h(view).add(aVar);
        G(view, 0);
    }

    public static void a0(View view, boolean z) {
        view.setHasTransientState(z);
    }

    public static s b(View view) {
        if (f825b == null) {
            f825b = new WeakHashMap<>();
        }
        s sVar = f825b.get(view);
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s(view);
        f825b.put(view, sVar2);
        return sVar2;
    }

    public static void b0(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static w c(View view, w wVar, Rect rect) {
        WindowInsets windowInsetsF = wVar.f();
        if (windowInsetsF != null) {
            return w.h(view.computeSystemWindowInsets(windowInsetsF, rect), view);
        }
        rect.setEmpty();
        return wVar;
    }

    public static void c0(View view, k kVar) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(a.h.a.tag_on_apply_window_listener, kVar);
        }
        if (kVar == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(a.h.a.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new r(view, kVar));
        }
    }

    public static boolean d(View view, KeyEvent keyEvent) {
        return false;
    }

    public static void d0(View view, int i, int i2, int i3, int i4) {
        view.setPaddingRelative(i, i2, i3, i4);
    }

    public static int e() {
        return View.generateViewId();
    }

    public static void e0(View view, int i, int i2) {
        view.setScrollIndicators(i, i2);
    }

    public static a.h.k.a f(View view) {
        View.AccessibilityDelegate accessibilityDelegate = view.getAccessibilityDelegate();
        if (accessibilityDelegate == null) {
            return null;
        }
        return accessibilityDelegate instanceof a.C0015a ? ((a.C0015a) accessibilityDelegate).f809a : new a.h.k.a(accessibilityDelegate);
    }

    public static void f0(View view, String str) {
        view.setTransitionName(str);
    }

    public static CharSequence g(View view) {
        return new n(a.h.a.tag_accessibility_pane_title, CharSequence.class, 8, 28).d(view);
    }

    public static void g0(View view) {
        view.stopNestedScroll();
    }

    public static List<b.a> h(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(a.h.a.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(a.h.a.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    public static ColorStateList i(View view) {
        return view.getBackgroundTintList();
    }

    public static Rect j(View view) {
        return view.getClipBounds();
    }

    public static Display k(View view) {
        return view.getDisplay();
    }

    public static float l(View view) {
        return view.getElevation();
    }

    public static boolean m(View view) {
        return view.getFitsSystemWindows();
    }

    public static int n(View view) {
        return view.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int o(View view) {
        return view.getImportantForAutofill();
    }

    public static int p(View view) {
        return view.getLayoutDirection();
    }

    public static int q(View view) {
        return view.getMinimumHeight();
    }

    public static int r(View view) {
        return view.getMinimumWidth();
    }

    public static int s(View view) {
        return view.getPaddingEnd();
    }

    public static int t(View view) {
        return view.getPaddingStart();
    }

    public static w u(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        w wVarG = w.g(rootWindowInsets);
        wVarG.f844a.m(wVarG);
        wVarG.f844a.d(view.getRootView());
        return wVarG;
    }

    public static String v(View view) {
        return view.getTransitionName();
    }

    public static int w(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float x(View view) {
        return view.getZ();
    }

    public static boolean y(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean z(View view) {
        return view.hasOverlappingRendering();
    }
}
