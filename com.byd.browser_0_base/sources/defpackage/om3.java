package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContentInfo;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.C0214v;
import defpackage.oy;
import defpackage.pt3;
import java.util.ArrayList;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public final class om3 {
    public static WeakHashMap<View, in3> a;
    public static final int[] b;
    public static final km3 c;
    public static final a d;

    /* JADX INFO: compiled from: ViewCompat.java */
    public static abstract class b<T> {
        public final int a;
        public final Class<T> b;
        public final int c;
        public final int d;

        public b(int i, Class<T> cls, int i2, int i3) {
            this.a = i;
            this.b = cls;
            this.d = i2;
            this.c = i3;
        }

        public static boolean a(Boolean bool, Boolean bool2) {
            return (bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue());
        }

        public abstract T b(View view);

        public abstract void c(View view, T t);

        public final T d(View view) {
            if (Build.VERSION.SDK_INT >= this.c) {
                return b(view);
            }
            T t = (T) view.getTag(this.a);
            if (this.b.isInstance(t)) {
                return t;
            }
            return null;
        }

        public final void e(View view, T t) {
            if (Build.VERSION.SDK_INT >= this.c) {
                c(view, t);
                return;
            }
            if (f(d(view), t)) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                View.AccessibilityDelegate accessibilityDelegateA = n.a(view);
                C0214v c0214v = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0214v.a ? ((C0214v.a) accessibilityDelegateA).a : new C0214v(accessibilityDelegateA);
                if (c0214v == null) {
                    c0214v = new C0214v();
                }
                om3.k(view, c0214v);
                view.setTag(this.a, t);
                om3.f(view, this.d);
            }
        }

        public abstract boolean f(T t, T t2);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class c {
        public static boolean a(View view) {
            return view.hasOnClickListeners();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class d {
        public static boolean a(View view) {
            return view.getFitsSystemWindows();
        }

        public static int b(View view) {
            return view.getImportantForAccessibility();
        }

        public static int c(View view) {
            return view.getMinimumHeight();
        }

        public static int d(View view) {
            return view.getMinimumWidth();
        }

        public static ViewParent e(View view) {
            return view.getParentForAccessibility();
        }

        public static int f(View view) {
            return view.getWindowSystemUiVisibility();
        }

        public static boolean g(View view) {
            return view.hasOverlappingRendering();
        }

        public static boolean h(View view) {
            return view.hasTransientState();
        }

        public static boolean i(View view, int i, Bundle bundle) {
            return view.performAccessibilityAction(i, bundle);
        }

        public static void j(View view) {
            view.postInvalidateOnAnimation();
        }

        public static void k(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }

        public static void l(View view, Runnable runnable, long j) {
            view.postOnAnimationDelayed(runnable, j);
        }

        public static void m(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }

        public static void n(View view, Drawable drawable) {
            view.setBackground(drawable);
        }

        public static void o(View view, boolean z) {
            view.setHasTransientState(z);
        }

        public static void p(View view, int i) {
            view.setImportantForAccessibility(i);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class e {
        public static int a() {
            return View.generateViewId();
        }

        public static Display b(View view) {
            return view.getDisplay();
        }

        public static int c(View view) {
            return view.getLayoutDirection();
        }

        public static int d(View view) {
            return view.getPaddingEnd();
        }

        public static int e(View view) {
            return view.getPaddingStart();
        }

        public static boolean f(View view) {
            return view.isPaddingRelative();
        }

        public static void g(View view, int i) {
            view.setLabelFor(i);
        }

        public static void h(View view, int i) {
            view.setLayoutDirection(i);
        }

        public static void i(View view, int i, int i2, int i3, int i4) {
            view.setPaddingRelative(i, i2, i3, i4);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class f {
        public static Rect a(View view) {
            return view.getClipBounds();
        }

        public static void b(View view, Rect rect) {
            view.setClipBounds(rect);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class g {
        public static int a(View view) {
            return view.getAccessibilityLiveRegion();
        }

        public static boolean b(View view) {
            return view.isAttachedToWindow();
        }

        public static boolean c(View view) {
            return view.isLaidOut();
        }

        public static void d(ViewParent viewParent, View view, View view2, int i) {
            viewParent.notifySubtreeAccessibilityStateChanged(view, view2, i);
        }

        public static void e(View view, int i) {
            view.setAccessibilityLiveRegion(i);
        }

        public static void f(AccessibilityEvent accessibilityEvent, int i) {
            accessibilityEvent.setContentChangeTypes(i);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class h {
        public static WindowInsets a(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        public static WindowInsets b(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        public static void c(View view) {
            view.requestApplyInsets();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class i {

        /* JADX INFO: compiled from: ViewCompat.java */
        public class a implements View.OnApplyWindowInsetsListener {
            public pt3 a = null;
            public final /* synthetic */ View b;
            public final /* synthetic */ wr1 c;

            public a(View view, wr1 wr1Var) {
                this.b = view;
                this.c = wr1Var;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                pt3 pt3VarI = pt3.i(view, windowInsets);
                int i = Build.VERSION.SDK_INT;
                wr1 wr1Var = this.c;
                if (i < 30) {
                    i.a(windowInsets, this.b);
                    if (pt3VarI.equals(this.a)) {
                        return wr1Var.k(view, pt3VarI).h();
                    }
                }
                this.a = pt3VarI;
                pt3 pt3VarK = wr1Var.k(view, pt3VarI);
                if (i >= 30) {
                    return pt3VarK.h();
                }
                WeakHashMap<View, in3> weakHashMap = om3.a;
                h.c(view);
                return pt3VarK.h();
            }
        }

        public static void a(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        public static pt3 b(View view, pt3 pt3Var, Rect rect) {
            WindowInsets windowInsetsH = pt3Var.h();
            if (windowInsetsH != null) {
                return pt3.i(view, view.computeSystemWindowInsets(windowInsetsH, rect));
            }
            rect.setEmpty();
            return pt3Var;
        }

        public static ColorStateList c(View view) {
            return view.getBackgroundTintList();
        }

        public static PorterDuff.Mode d(View view) {
            return view.getBackgroundTintMode();
        }

        public static float e(View view) {
            return view.getElevation();
        }

        public static String f(View view) {
            return view.getTransitionName();
        }

        public static float g(View view) {
            return view.getTranslationZ();
        }

        public static float h(View view) {
            return view.getZ();
        }

        public static boolean i(View view) {
            return view.isNestedScrollingEnabled();
        }

        public static void j(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        public static void k(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        public static void l(View view, float f) {
            view.setElevation(f);
        }

        public static void m(View view, wr1 wr1Var) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(R$id.tag_on_apply_window_listener, wr1Var);
            }
            if (wr1Var == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new a(view, wr1Var));
            }
        }

        public static void n(View view, String str) {
            view.setTransitionName(str);
        }

        public static void o(View view, float f) {
            view.setTranslationZ(f);
        }

        public static void p(View view) {
            view.stopNestedScroll();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class j {
        public static pt3 a(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            pt3 pt3VarI = pt3.i(null, rootWindowInsets);
            pt3.i iVar = pt3VarI.a;
            iVar.p(pt3VarI);
            iVar.d(view.getRootView());
            return pt3VarI;
        }

        public static void b(View view, int i, int i2) {
            view.setScrollIndicators(i, i2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class k {
        public static void a(View view, PointerIcon pointerIcon) {
            view.setPointerIcon(pointerIcon);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class l {
        public static int a(View view) {
            return view.getImportantForAutofill();
        }

        public static void b(View view, int i) {
            view.setImportantForAutofill(i);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class m {
        public static CharSequence a(View view) {
            return view.getAccessibilityPaneTitle();
        }

        public static boolean b(View view) {
            return view.isAccessibilityHeading();
        }

        public static boolean c(View view) {
            return view.isScreenReaderFocusable();
        }

        public static void d(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        public static void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class n {
        public static View.AccessibilityDelegate a(View view) {
            return view.getAccessibilityDelegate();
        }

        public static void b(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i, int i2) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i, i2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class o {
        public static CharSequence a(View view) {
            return view.getStateDescription();
        }

        public static void b(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static final class p {
        public static String[] a(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static oy b(View view, oy oyVar) {
            ContentInfo contentInfoB = oyVar.a.b();
            Objects.requireNonNull(contentInfoB);
            ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoB);
            if (contentInfoPerformReceiveContent == null) {
                return null;
            }
            return contentInfoPerformReceiveContent == contentInfoB ? oyVar : new oy(new oy.d(contentInfoPerformReceiveContent));
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [km3] */
    static {
        new AtomicInteger(1);
        a = null;
        b = new int[]{R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};
        c = new hs1() { // from class: km3
            @Override // defpackage.hs1
            public final oy a(oy oyVar) {
                return oyVar;
            }
        };
        d = new a();
    }

    public static int a(View view, String str, androidx.core.view.accessibility.a aVar) {
        int iA;
        ArrayList arrayListD = d(view);
        int i2 = 0;
        while (true) {
            if (i2 >= arrayListD.size()) {
                int i3 = 0;
                int i4 = -1;
                while (true) {
                    int[] iArr = b;
                    if (i3 >= iArr.length || i4 != -1) {
                        break;
                    }
                    int i5 = iArr[i3];
                    boolean z = true;
                    for (int i6 = 0; i6 < arrayListD.size(); i6++) {
                        z &= ((AccessibilityNodeInfoCompat.a) arrayListD.get(i6)).a() != i5;
                    }
                    if (z) {
                        i4 = i5;
                    }
                    i3++;
                }
                iA = i4;
            } else {
                if (TextUtils.equals(str, ((AccessibilityNodeInfoCompat.a) arrayListD.get(i2)).b())) {
                    iA = ((AccessibilityNodeInfoCompat.a) arrayListD.get(i2)).a();
                    break;
                }
                i2++;
            }
        }
        if (iA != -1) {
            AccessibilityNodeInfoCompat.a aVar2 = new AccessibilityNodeInfoCompat.a(null, iA, str, aVar, null);
            View.AccessibilityDelegate accessibilityDelegateA = n.a(view);
            C0214v c0214v = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0214v.a ? ((C0214v.a) accessibilityDelegateA).a : new C0214v(accessibilityDelegateA);
            if (c0214v == null) {
                c0214v = new C0214v();
            }
            k(view, c0214v);
            i(view, aVar2.a());
            d(view).add(aVar2);
            f(view, 0);
        }
        return iA;
    }

    public static in3 b(View view) {
        if (a == null) {
            a = new WeakHashMap<>();
        }
        in3 in3Var = a.get(view);
        if (in3Var != null) {
            return in3Var;
        }
        in3 in3Var2 = new in3(view);
        a.put(view, in3Var2);
        return in3Var2;
    }

    public static pt3 c(View view, pt3 pt3Var) {
        WindowInsets windowInsetsH = pt3Var.h();
        if (windowInsetsH != null) {
            WindowInsets windowInsetsA = h.a(view, windowInsetsH);
            if (!windowInsetsA.equals(windowInsetsH)) {
                return pt3.i(view, windowInsetsA);
            }
        }
        return pt3Var;
    }

    public static ArrayList d(View view) {
        int i2 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i2);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i2, arrayList2);
        return arrayList2;
    }

    public static String[] e(View view) {
        return Build.VERSION.SDK_INT >= 31 ? p.a(view) : (String[]) view.getTag(R$id.tag_on_receive_content_mime_types);
    }

    public static void f(View view, int i2) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z = m.a(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (g.a(view) != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                g.f(accessibilityEventObtain, i2);
                if (z) {
                    accessibilityEventObtain.getText().add(m.a(view));
                    if (d.b(view) == 0) {
                        d.p(view, 1);
                    }
                    ViewParent parent = view.getParent();
                    while (true) {
                        if (!(parent instanceof View)) {
                            break;
                        }
                        if (d.b((View) parent) == 4) {
                            d.p(view, 2);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i2 != 32) {
                if (view.getParent() != null) {
                    try {
                        g.d(view.getParent(), view, view, i2);
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
            g.f(accessibilityEventObtain2, i2);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.getText().add(m.a(view));
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    public static pt3 g(View view, pt3 pt3Var) {
        WindowInsets windowInsetsH = pt3Var.h();
        if (windowInsetsH != null) {
            WindowInsets windowInsetsB = h.b(view, windowInsetsH);
            if (!windowInsetsB.equals(windowInsetsH)) {
                return pt3.i(view, windowInsetsB);
            }
        }
        return pt3Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static oy h(View view, oy oyVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Objects.toString(oyVar);
            view.getClass();
            view.getId();
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return p.b(view, oyVar);
        }
        gs1 gs1Var = (gs1) view.getTag(R$id.tag_on_receive_content_listener);
        hs1 hs1Var = c;
        if (gs1Var == null) {
            if (view instanceof hs1) {
                hs1Var = (hs1) view;
            }
            return hs1Var.a(oyVar);
        }
        oy oyVarA = gs1Var.a(view, oyVar);
        if (oyVarA == null) {
            return null;
        }
        if (view instanceof hs1) {
            hs1Var = (hs1) view;
        }
        return hs1Var.a(oyVarA);
    }

    public static void i(View view, int i2) {
        ArrayList arrayListD = d(view);
        for (int i3 = 0; i3 < arrayListD.size(); i3++) {
            if (((AccessibilityNodeInfoCompat.a) arrayListD.get(i3)).a() == i2) {
                arrayListD.remove(i3);
                return;
            }
        }
    }

    public static void j(View view, AccessibilityNodeInfoCompat.a aVar, String str, androidx.core.view.accessibility.a aVar2) {
        if (aVar2 == null && str == null) {
            i(view, aVar.a());
            f(view, 0);
            return;
        }
        AccessibilityNodeInfoCompat.a aVar3 = new AccessibilityNodeInfoCompat.a(null, aVar.b, str, aVar2, aVar.c);
        View.AccessibilityDelegate accessibilityDelegateA = n.a(view);
        C0214v c0214v = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0214v.a ? ((C0214v.a) accessibilityDelegateA).a : new C0214v(accessibilityDelegateA);
        if (c0214v == null) {
            c0214v = new C0214v();
        }
        k(view, c0214v);
        i(view, aVar3.a());
        d(view).add(aVar3);
        f(view, 0);
    }

    public static void k(View view, C0214v c0214v) {
        if (c0214v == null && (n.a(view) instanceof C0214v.a)) {
            c0214v = new C0214v();
        }
        view.setAccessibilityDelegate(c0214v == null ? null : c0214v.b);
    }

    public static void l(View view, CharSequence charSequence) {
        new lm3(R$id.tag_accessibility_pane_title).e(view, charSequence);
        a aVar = d;
        if (charSequence == null) {
            aVar.e.remove(view);
            view.removeOnAttachStateChangeListener(aVar);
            d.m(view.getViewTreeObserver(), aVar);
        } else {
            aVar.e.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(aVar);
            if (g.b(view)) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
            }
        }
    }

    public static void m(View view) {
        d.p(view, 1);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class a implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {
        public final WeakHashMap<View, Boolean> e = new WeakHashMap<>();

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
