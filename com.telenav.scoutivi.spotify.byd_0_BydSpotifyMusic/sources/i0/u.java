package i0;

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
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import i0.a;
import j0.c;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateConstructorForUtilityClass"})
public class u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f6887c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicInteger f6885a = new AtomicInteger(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static WeakHashMap<View, y> f6886b = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f6888d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int[] f6889e = {v.a.accessibility_custom_action_0, v.a.accessibility_custom_action_1, v.a.accessibility_custom_action_2, v.a.accessibility_custom_action_3, v.a.accessibility_custom_action_4, v.a.accessibility_custom_action_5, v.a.accessibility_custom_action_6, v.a.accessibility_custom_action_7, v.a.accessibility_custom_action_8, v.a.accessibility_custom_action_9, v.a.accessibility_custom_action_10, v.a.accessibility_custom_action_11, v.a.accessibility_custom_action_12, v.a.accessibility_custom_action_13, v.a.accessibility_custom_action_14, v.a.accessibility_custom_action_15, v.a.accessibility_custom_action_16, v.a.accessibility_custom_action_17, v.a.accessibility_custom_action_18, v.a.accessibility_custom_action_19, v.a.accessibility_custom_action_20, v.a.accessibility_custom_action_21, v.a.accessibility_custom_action_22, v.a.accessibility_custom_action_23, v.a.accessibility_custom_action_24, v.a.accessibility_custom_action_25, v.a.accessibility_custom_action_26, v.a.accessibility_custom_action_27, v.a.accessibility_custom_action_28, v.a.accessibility_custom_action_29, v.a.accessibility_custom_action_30, v.a.accessibility_custom_action_31};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final r f6890f = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static f f6891g = new f();

    /* JADX INFO: compiled from: ViewCompat.java */
    class a implements r {
        a() {
        }

        @Override // i0.r
        public i0.c a(i0.c cVar) {
            return cVar;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    class b extends g<Boolean> {
        b(int i7, Class cls, int i8) {
            super(i7, cls, i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    class c extends g<CharSequence> {
        c(int i7, Class cls, int i8, int i9) {
            super(i7, cls, i8, i9);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getAccessibilityPaneTitle();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    class d extends g<CharSequence> {
        d(int i7, Class cls, int i8, int i9) {
            super(i7, cls, i8, i9);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getStateDescription();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    class e extends g<Boolean> {
        e(int i7, Class cls, int i8) {
            super(i7, cls, i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // i0.u.g
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static class f implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private WeakHashMap<View, Boolean> f6892e = new WeakHashMap<>();

        f() {
        }

        private void a(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            a(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    static abstract class g<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f6893a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<T> f6894b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f6895c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f6896d;

        g(int i7, Class<T> cls, int i8) {
            this(i7, cls, 0, i8);
        }

        private boolean b() {
            return true;
        }

        private boolean c() {
            return Build.VERSION.SDK_INT >= this.f6895c;
        }

        boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        abstract T d(View view);

        abstract void e(View view, T t6);

        T f(View view) {
            if (c()) {
                return d(view);
            }
            if (!b()) {
                return null;
            }
            T t6 = (T) view.getTag(this.f6893a);
            if (this.f6894b.isInstance(t6)) {
                return t6;
            }
            return null;
        }

        void g(View view, T t6) {
            if (c()) {
                e(view, t6);
            } else if (b() && h(f(view), t6)) {
                u.C(view);
                view.setTag(this.f6893a, t6);
                u.T(view, this.f6896d);
            }
        }

        abstract boolean h(T t6, T t7);

        g(int i7, Class<T> cls, int i8, int i9) {
            this.f6893a = i7;
            this.f6894b = cls;
            this.f6896d = i8;
            this.f6895c = i9;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    private static class h {

        /* JADX INFO: compiled from: ViewCompat.java */
        class a implements View.OnApplyWindowInsetsListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            c0 f6897a = null;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ View f6898b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ p f6899c;

            a(View view, p pVar) {
                this.f6898b = view;
                this.f6899c = pVar;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                c0 c0VarT = c0.t(windowInsets, view);
                int i7 = Build.VERSION.SDK_INT;
                if (i7 < 30) {
                    h.a(windowInsets, this.f6898b);
                    if (c0VarT.equals(this.f6897a)) {
                        return this.f6899c.a(view, c0VarT).r();
                    }
                }
                this.f6897a = c0VarT;
                c0 c0VarA = this.f6899c.a(view, c0VarT);
                if (i7 >= 30) {
                    return c0VarA.r();
                }
                u.h0(view);
                return c0VarA.r();
            }
        }

        static void a(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(v.a.tag_window_insets_animation_callback);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        static c0 b(View view, c0 c0Var, Rect rect) {
            WindowInsets windowInsetsR = c0Var.r();
            if (windowInsetsR != null) {
                return c0.t(view.computeSystemWindowInsets(windowInsetsR, rect), view);
            }
            rect.setEmpty();
            return c0Var;
        }

        static void c(View view, p pVar) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(v.a.tag_on_apply_window_listener, pVar);
            }
            if (pVar == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(v.a.tag_window_insets_animation_callback));
            } else {
                view.setOnApplyWindowInsetsListener(new a(view, pVar));
            }
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    private static class i {
        public static c0 a(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            c0 c0VarS = c0.s(rootWindowInsets);
            c0VarS.p(c0VarS);
            c0VarS.d(view.getRootView());
            return c0VarS;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    private static class j {
        static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i7, int i8) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i7, i8);
        }
    }

    public static int A(View view) {
        return view.getMinimumWidth();
    }

    private static void A0(View view) {
        if (w(view) == 0) {
            t0(view, 1);
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (w((View) parent) == 4) {
                t0(view, 2);
                return;
            }
        }
    }

    public static String[] B(View view) {
        return (String[]) view.getTag(v.a.tag_on_receive_content_mime_types);
    }

    private static g<CharSequence> B0() {
        return new d(v.a.tag_state_description, CharSequence.class, 64, 30);
    }

    static i0.a C(View view) {
        i0.a aVarI = i(view);
        if (aVarI == null) {
            aVarI = new i0.a();
        }
        k0(view, aVarI);
        return aVarI;
    }

    public static void C0(View view) {
        view.stopNestedScroll();
    }

    public static int D(View view) {
        return view.getPaddingEnd();
    }

    public static int E(View view) {
        return view.getPaddingStart();
    }

    public static c0 F(View view) {
        return i.a(view);
    }

    public static final CharSequence G(View view) {
        return B0().f(view);
    }

    public static String H(View view) {
        return view.getTransitionName();
    }

    public static int I(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float J(View view) {
        return view.getZ();
    }

    public static boolean K(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean L(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean M(View view) {
        return view.hasTransientState();
    }

    public static boolean N(View view) {
        Boolean boolF = a().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    public static boolean O(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean P(View view) {
        return view.isLaidOut();
    }

    public static boolean Q(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static boolean R(View view) {
        return view.isPaddingRelative();
    }

    public static boolean S(View view) {
        Boolean boolF = j0().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    static void T(View view, int i7) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z6 = m(view) != null && view.getVisibility() == 0;
            if (l(view) != 0 || z6) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z6 ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i7);
                if (z6) {
                    accessibilityEventObtain.getText().add(m(view));
                    A0(view);
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i7 == 32) {
                AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.setEventType(32);
                accessibilityEventObtain2.setContentChangeTypes(i7);
                accessibilityEventObtain2.setSource(view);
                view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
                accessibilityEventObtain2.getText().add(m(view));
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i7);
                } catch (AbstractMethodError e7) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e7);
                }
            }
        }
    }

    public static void U(View view, int i7) {
        view.offsetLeftAndRight(i7);
    }

    public static void V(View view, int i7) {
        view.offsetTopAndBottom(i7);
    }

    public static c0 W(View view, c0 c0Var) {
        WindowInsets windowInsetsR = c0Var.r();
        if (windowInsetsR != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsR);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsR)) {
                return c0.t(windowInsetsOnApplyWindowInsets, view);
            }
        }
        return c0Var;
    }

    public static void X(View view, j0.c cVar) {
        view.onInitializeAccessibilityNodeInfo(cVar.o0());
    }

    private static g<CharSequence> Y() {
        return new c(v.a.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static boolean Z(View view, int i7, Bundle bundle) {
        return view.performAccessibilityAction(i7, bundle);
    }

    private static g<Boolean> a() {
        return new e(v.a.tag_accessibility_heading, Boolean.class, 28);
    }

    public static i0.c a0(View view, i0.c cVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + cVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        q qVar = (q) view.getTag(v.a.tag_on_receive_content_listener);
        if (qVar == null) {
            return u(view).a(cVar);
        }
        i0.c cVarA = qVar.a(view, cVar);
        if (cVarA == null) {
            return null;
        }
        return u(view).a(cVarA);
    }

    public static int b(View view, CharSequence charSequence, j0.f fVar) {
        int iO = o(view);
        if (iO != -1) {
            c(view, new c.a(iO, charSequence, fVar));
        }
        return iO;
    }

    public static void b0(View view) {
        view.postInvalidateOnAnimation();
    }

    private static void c(View view, c.a aVar) {
        C(view);
        f0(aVar.b(), view);
        n(view).add(aVar);
        T(view, 0);
    }

    public static void c0(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static y d(View view) {
        if (f6886b == null) {
            f6886b = new WeakHashMap<>();
        }
        y yVar = f6886b.get(view);
        if (yVar != null) {
            return yVar;
        }
        y yVar2 = new y(view);
        f6886b.put(view, yVar2);
        return yVar2;
    }

    public static void d0(View view, Runnable runnable, long j7) {
        view.postOnAnimationDelayed(runnable, j7);
    }

    public static c0 e(View view, c0 c0Var, Rect rect) {
        return h.b(view, c0Var, rect);
    }

    public static void e0(View view, int i7) {
        f0(i7, view);
        T(view, 0);
    }

    public static c0 f(View view, c0 c0Var) {
        WindowInsets windowInsetsR = c0Var.r();
        if (windowInsetsR != null) {
            WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsetsR);
            if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsR)) {
                return c0.t(windowInsetsDispatchApplyWindowInsets, view);
            }
        }
        return c0Var;
    }

    private static void f0(int i7, View view) {
        List<c.a> listN = n(view);
        for (int i8 = 0; i8 < listN.size(); i8++) {
            if (listN.get(i8).b() == i7) {
                listN.remove(i8);
                return;
            }
        }
    }

    static boolean g(View view, KeyEvent keyEvent) {
        return false;
    }

    public static void g0(View view, c.a aVar, CharSequence charSequence, j0.f fVar) {
        if (fVar == null && charSequence == null) {
            e0(view, aVar.b());
        } else {
            c(view, aVar.a(charSequence, fVar));
        }
    }

    public static int h() {
        return View.generateViewId();
    }

    public static void h0(View view) {
        view.requestApplyInsets();
    }

    public static i0.a i(View view) {
        View.AccessibilityDelegate accessibilityDelegateJ = j(view);
        if (accessibilityDelegateJ == null) {
            return null;
        }
        return accessibilityDelegateJ instanceof a.C0083a ? ((a.C0083a) accessibilityDelegateJ).f6829a : new i0.a(accessibilityDelegateJ);
    }

    public static void i0(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i7, int i8) {
        if (Build.VERSION.SDK_INT >= 29) {
            j.a(view, context, iArr, attributeSet, typedArray, i7, i8);
        }
    }

    private static View.AccessibilityDelegate j(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : k(view);
    }

    private static g<Boolean> j0() {
        return new b(v.a.tag_screen_reader_focusable, Boolean.class, 28);
    }

    private static View.AccessibilityDelegate k(View view) {
        if (f6888d) {
            return null;
        }
        if (f6887c == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f6887c = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f6888d = true;
                return null;
            }
        }
        try {
            Object obj = f6887c.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f6888d = true;
            return null;
        }
    }

    public static void k0(View view, i0.a aVar) {
        if (aVar == null && (j(view) instanceof a.C0083a)) {
            aVar = new i0.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.d());
    }

    public static int l(View view) {
        return view.getAccessibilityLiveRegion();
    }

    public static void l0(View view, boolean z6) {
        a().g(view, Boolean.valueOf(z6));
    }

    public static CharSequence m(View view) {
        return Y().f(view);
    }

    public static void m0(View view, int i7) {
        view.setAccessibilityLiveRegion(i7);
    }

    private static List<c.a> n(View view) {
        int i7 = v.a.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i7);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i7, arrayList2);
        return arrayList2;
    }

    public static void n0(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    private static int o(View view) {
        List<c.a> listN = n(view);
        int i7 = -1;
        int i8 = 0;
        while (true) {
            int[] iArr = f6889e;
            if (i8 >= iArr.length || i7 != -1) {
                break;
            }
            int i9 = iArr[i8];
            boolean z6 = true;
            for (int i10 = 0; i10 < listN.size(); i10++) {
                z6 &= listN.get(i10).b() != i9;
            }
            if (z6) {
                i7 = i9;
            }
            i8++;
        }
        return i7;
    }

    public static void o0(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    public static ColorStateList p(View view) {
        return view.getBackgroundTintList();
    }

    public static void p0(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static PorterDuff.Mode q(View view) {
        return view.getBackgroundTintMode();
    }

    public static void q0(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static Rect r(View view) {
        return view.getClipBounds();
    }

    public static void r0(View view, float f7) {
        view.setElevation(f7);
    }

    public static Display s(View view) {
        return view.getDisplay();
    }

    public static void s0(View view, boolean z6) {
        view.setHasTransientState(z6);
    }

    public static float t(View view) {
        return view.getElevation();
    }

    public static void t0(View view, int i7) {
        view.setImportantForAccessibility(i7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static r u(View view) {
        return view instanceof r ? (r) view : f6890f;
    }

    public static void u0(View view, int i7) {
        view.setImportantForAutofill(i7);
    }

    public static boolean v(View view) {
        return view.getFitsSystemWindows();
    }

    public static void v0(View view, p pVar) {
        h.c(view, pVar);
    }

    public static int w(View view) {
        return view.getImportantForAccessibility();
    }

    public static void w0(View view, int i7, int i8, int i9, int i10) {
        view.setPaddingRelative(i7, i8, i9, i10);
    }

    @SuppressLint({"InlinedApi"})
    public static int x(View view) {
        return view.getImportantForAutofill();
    }

    public static void x0(View view, t tVar) {
        view.setPointerIcon((PointerIcon) (tVar != null ? tVar.a() : null));
    }

    public static int y(View view) {
        return view.getLayoutDirection();
    }

    public static void y0(View view, int i7, int i8) {
        view.setScrollIndicators(i7, i8);
    }

    public static int z(View view) {
        return view.getMinimumHeight();
    }

    public static void z0(View view, String str) {
        view.setTransitionName(str);
    }
}
