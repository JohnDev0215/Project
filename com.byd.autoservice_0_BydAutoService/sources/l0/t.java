package l0;

import android.animation.ValueAnimator;
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
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import l0.a;
import m0.c;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f3652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3653c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f3654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3655e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static WeakHashMap<View, String> f3656f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Field f3658h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static ThreadLocal<Rect> f3660j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f3651a = new AtomicInteger(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static WeakHashMap<View, x> f3657g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f3659i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f3661k = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static e f3662l = new e();

    /* JADX INFO: compiled from: ViewCompat.java */
    public class a implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ p f3663a;

        public a(p pVar) {
            this.f3663a = pVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return this.f3663a.a(view, b0.p(windowInsets)).o();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class b extends f<Boolean> {
        public b(int i3, Class cls, int i4) {
            super(i3, cls, i4);
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class c extends f<CharSequence> {
        public c(int i3, Class cls, int i4, int i5) {
            super(i3, cls, i4, i5);
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getAccessibilityPaneTitle();
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class d extends f<Boolean> {
        public d(int i3, Class cls, int i4) {
            super(i3, cls, i4);
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }

        @Override // l0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f3664b = new WeakHashMap<>();

        public final void a(View view, boolean z3) {
            boolean z4 = view.getVisibility() == 0;
            if (z3 != z4) {
                if (z4) {
                    t.T(view, 16);
                }
                this.f3664b.put(view, Boolean.valueOf(z4));
            }
        }

        public final void b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f3664b.entrySet()) {
                a(entry.getKey(), entry.getValue().booleanValue());
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            b(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static abstract class f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3665a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<T> f3666b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3667c;

        public f(int i3, Class<T> cls, int i4) {
            this(i3, cls, 0, i4);
        }

        public boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        public final boolean b() {
            return Build.VERSION.SDK_INT >= 19;
        }

        public final boolean c() {
            return Build.VERSION.SDK_INT >= this.f3667c;
        }

        public abstract T d(View view);

        public abstract void e(View view, T t3);

        public T f(View view) {
            if (c()) {
                return d(view);
            }
            if (!b()) {
                return null;
            }
            T t3 = (T) view.getTag(this.f3665a);
            if (this.f3666b.isInstance(t3)) {
                return t3;
            }
            return null;
        }

        public void g(View view, T t3) {
            if (c()) {
                e(view, t3);
            } else if (b() && h(f(view), t3)) {
                t.C(view);
                view.setTag(this.f3665a, t3);
                t.T(view, 0);
            }
        }

        public abstract boolean h(T t3, T t4);

        public f(int i3, Class<T> cls, int i4, int i5) {
            this.f3665a = i3;
            this.f3666b = cls;
            this.f3667c = i5;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class g {
        public static b0 a(View view, b0 b0Var, Rect rect) {
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                return b0.p(view.computeSystemWindowInsets(windowInsetsO, rect));
            }
            rect.setEmpty();
            return b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class h {
        public static WindowInsets a(View view) {
            return view.getRootWindowInsets();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class i {
        public static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i3, int i4) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i3, i4);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public interface j {
        boolean a(View view, KeyEvent keyEvent);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ArrayList<WeakReference<View>> f3668a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f3669b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SparseArray<WeakReference<View>> f3670c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public WeakReference<KeyEvent> f3671d = null;

        public static k a(View view) {
            int i3 = R$id.tag_unhandled_key_event_manager;
            k kVar = (k) view.getTag(i3);
            if (kVar != null) {
                return kVar;
            }
            k kVar2 = new k();
            view.setTag(i3, kVar2);
            return kVar2;
        }

        public boolean b(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                g();
            }
            View viewC = c(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewC != null && !KeyEvent.isModifierKey(keyCode)) {
                    d().put(keyCode, new WeakReference<>(viewC));
                }
            }
            return viewC != null;
        }

        public final View c(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.f3669b;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewC = c(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewC != null) {
                            return viewC;
                        }
                    }
                }
                if (e(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        public final SparseArray<WeakReference<View>> d() {
            if (this.f3670c == null) {
                this.f3670c = new SparseArray<>();
            }
            return this.f3670c;
        }

        public final boolean e(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(R$id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((j) arrayList.get(size)).a(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        public boolean f(KeyEvent keyEvent) {
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.f3671d;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f3671d = new WeakReference<>(keyEvent);
            WeakReference<View> weakReferenceValueAt = null;
            SparseArray<WeakReference<View>> sparseArrayD = d();
            if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArrayD.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReferenceValueAt = sparseArrayD.valueAt(iIndexOfKey);
                sparseArrayD.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = sparseArrayD.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && t.O(view)) {
                e(view, keyEvent);
            }
            return true;
        }

        public final void g() {
            WeakHashMap<View, Boolean> weakHashMap = this.f3669b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = f3668a;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.f3669b == null) {
                    this.f3669b = new WeakHashMap<>();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList<WeakReference<View>> arrayList2 = f3668a;
                    View view = arrayList2.get(size).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.f3669b.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f3669b.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    public static int A(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f3655e) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinHeight");
                f3654d = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f3655e = true;
        }
        Field field = f3654d;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void A0(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof l0.j) {
            ((l0.j) view).stopNestedScroll();
        }
    }

    public static int B(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!f3653c) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinWidth");
                f3652b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f3653c = true;
        }
        Field field = f3652b;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static void B0(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static l0.a C(View view) {
        l0.a aVarK = k(view);
        if (aVarK == null) {
            aVarK = new l0.a();
        }
        j0(view, aVarK);
        return aVarK;
    }

    public static int D(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingEnd() : view.getPaddingRight();
    }

    public static int E(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingStart() : view.getPaddingLeft();
    }

    public static ViewParent F(View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static b0 G(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return b0.p(h.a(view));
        }
        return null;
    }

    public static String H(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f3656f;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int I(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static float J(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean K(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean L(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean M(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static boolean N(View view) {
        Boolean boolF = a().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    public static boolean O(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static boolean P(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean Q(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof l0.j) {
            return ((l0.j) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static boolean R(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.isPaddingRelative();
        }
        return false;
    }

    public static boolean S(View view) {
        Boolean boolF = i0().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    public static void T(View view, int i3) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z3 = o(view) != null;
            if (n(view) != 0 || (z3 && view.getVisibility() == 0)) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z3 ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i3);
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i3);
                } catch (AbstractMethodError e3) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e3);
                }
            }
        }
    }

    public static void U(View view, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 23) {
            view.offsetLeftAndRight(i3);
            return;
        }
        if (i4 < 21) {
            d(view, i3);
            return;
        }
        Rect rectV = v();
        boolean z3 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectV.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z3 = !rectV.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        d(view, i3);
        if (z3 && rectV.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectV);
        }
    }

    public static void V(View view, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 23) {
            view.offsetTopAndBottom(i3);
            return;
        }
        if (i4 < 21) {
            e(view, i3);
            return;
        }
        Rect rectV = v();
        boolean z3 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectV.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z3 = !rectV.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        e(view, i3);
        if (z3 && rectV.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectV);
        }
    }

    public static b0 W(View view, b0 b0Var) {
        WindowInsets windowInsetsO;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsO = b0Var.o()) != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsO);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsO)) {
                return b0.p(windowInsetsOnApplyWindowInsets);
            }
        }
        return b0Var;
    }

    public static void X(View view, m0.c cVar) {
        view.onInitializeAccessibilityNodeInfo(cVar.C0());
    }

    public static f<CharSequence> Y() {
        return new c(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static boolean Z(View view, int i3, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.performAccessibilityAction(i3, bundle);
        }
        return false;
    }

    public static f<Boolean> a() {
        return new d(R$id.tag_accessibility_heading, Boolean.class, 28);
    }

    public static void a0(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void b(View view, c.a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            C(view);
            e0(aVar.b(), view);
            p(view).add(aVar);
            T(view, 0);
        }
    }

    public static void b0(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static x c(View view) {
        if (f3657g == null) {
            f3657g = new WeakHashMap<>();
        }
        x xVar = f3657g.get(view);
        if (xVar != null) {
            return xVar;
        }
        x xVar2 = new x(view);
        f3657g.put(view, xVar2);
        return xVar2;
    }

    public static void c0(View view, Runnable runnable, long j3) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j3);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j3);
        }
    }

    public static void d(View view, int i3) {
        view.offsetLeftAndRight(i3);
        if (view.getVisibility() == 0) {
            B0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                B0((View) parent);
            }
        }
    }

    public static void d0(View view, int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            e0(i3, view);
            T(view, 0);
        }
    }

    public static void e(View view, int i3) {
        view.offsetTopAndBottom(i3);
        if (view.getVisibility() == 0) {
            B0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                B0((View) parent);
            }
        }
    }

    public static void e0(int i3, View view) {
        List<c.a> listP = p(view);
        for (int i4 = 0; i4 < listP.size(); i4++) {
            if (listP.get(i4).b() == i3) {
                listP.remove(i4);
                return;
            }
        }
    }

    public static b0 f(View view, b0 b0Var, Rect rect) {
        return Build.VERSION.SDK_INT >= 21 ? g.a(view, b0Var, rect) : b0Var;
    }

    public static void f0(View view, c.a aVar, CharSequence charSequence, m0.f fVar) {
        if (fVar == null && charSequence == null) {
            d0(view, aVar.b());
        } else {
            b(view, aVar.a(charSequence, fVar));
        }
    }

    public static b0 g(View view, b0 b0Var) {
        WindowInsets windowInsetsO;
        return (Build.VERSION.SDK_INT < 21 || (windowInsetsO = b0Var.o()) == null || view.dispatchApplyWindowInsets(windowInsetsO).equals(windowInsetsO)) ? b0Var : b0.p(windowInsetsO);
    }

    public static void g0(View view) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 20) {
            view.requestApplyInsets();
        } else if (i3 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static boolean h(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).b(view, keyEvent);
    }

    public static void h0(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 29) {
            i.a(view, context, iArr, attributeSet, typedArray, i3, i4);
        }
    }

    public static boolean i(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).f(keyEvent);
    }

    public static f<Boolean> i0() {
        return new b(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static int j() {
        AtomicInteger atomicInteger;
        int i3;
        int i4;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            atomicInteger = f3651a;
            i3 = atomicInteger.get();
            i4 = i3 + 1;
            if (i4 > 16777215) {
                i4 = 1;
            }
        } while (!atomicInteger.compareAndSet(i3, i4));
        return i3;
    }

    public static void j0(View view, l0.a aVar) {
        if (aVar == null && (l(view) instanceof a.C0032a)) {
            aVar = new l0.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.d());
    }

    public static l0.a k(View view) {
        View.AccessibilityDelegate accessibilityDelegateL = l(view);
        if (accessibilityDelegateL == null) {
            return null;
        }
        return accessibilityDelegateL instanceof a.C0032a ? ((a.C0032a) accessibilityDelegateL).f3612a : new l0.a(accessibilityDelegateL);
    }

    public static void k0(View view, boolean z3) {
        a().g(view, Boolean.valueOf(z3));
    }

    public static View.AccessibilityDelegate l(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : m(view);
    }

    public static void l0(View view, int i3) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i3);
        }
    }

    public static View.AccessibilityDelegate m(View view) {
        if (f3659i) {
            return null;
        }
        if (f3658h == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f3658h = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f3659i = true;
                return null;
            }
        }
        try {
            Object obj = f3658h.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f3659i = true;
            return null;
        }
    }

    public static void m0(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int n(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void n0(View view, ColorStateList colorStateList) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 21) {
            if (view instanceof s) {
                ((s) view).setSupportBackgroundTintList(colorStateList);
                return;
            }
            return;
        }
        view.setBackgroundTintList(colorStateList);
        if (i3 == 21) {
            Drawable background = view.getBackground();
            boolean z3 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z3) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static CharSequence o(View view) {
        return Y().f(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void o0(View view, PorterDuff.Mode mode) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 21) {
            if (view instanceof s) {
                ((s) view).setSupportBackgroundTintMode(mode);
                return;
            }
            return;
        }
        view.setBackgroundTintMode(mode);
        if (i3 == 21) {
            Drawable background = view.getBackground();
            boolean z3 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z3) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static List<c.a> p(View view) {
        int i3 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i3);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i3, arrayList2);
        return arrayList2;
    }

    public static void p0(View view, Rect rect) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList q(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static void q0(View view, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode r(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    @Deprecated
    public static void r0(View view, boolean z3) {
        view.setFitsSystemWindows(z3);
    }

    public static Rect s(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.getClipBounds();
        }
        return null;
    }

    public static void s0(View view, boolean z3) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z3);
        }
    }

    public static Display t(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (O(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static void t0(View view, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 19) {
            view.setImportantForAccessibility(i3);
        } else if (i4 >= 16) {
            if (i3 == 4) {
                i3 = 2;
            }
            view.setImportantForAccessibility(i3);
        }
    }

    public static float u(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static void u0(View view, int i3) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i3);
        }
    }

    public static Rect v() {
        if (f3660j == null) {
            f3660j = new ThreadLocal<>();
        }
        Rect rect = f3660j.get();
        if (rect == null) {
            rect = new Rect();
            f3660j.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void v0(View view, p pVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (pVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new a(pVar));
            }
        }
    }

    public static boolean w(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static void w0(View view, int i3, int i4, int i5, int i6) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i3, i4, i5, i6);
        } else {
            view.setPadding(i3, i4, i5, i6);
        }
    }

    public static int x(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    public static void x0(View view, r rVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (rVar != null ? rVar.a() : null));
        }
    }

    @SuppressLint({"InlinedApi"})
    public static int y(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static void y0(View view, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i3, i4);
        }
    }

    public static int z(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static void z0(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f3656f == null) {
            f3656f = new WeakHashMap<>();
        }
        f3656f.put(view, str);
    }
}
