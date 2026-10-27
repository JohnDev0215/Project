package b.f.m;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import b.f.m.a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f1791b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1792c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f1793d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f1794e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static WeakHashMap<View, String> f1795f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Field f1797h;
    public static ThreadLocal<Rect> j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f1790a = new AtomicInteger(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static WeakHashMap<View, w> f1796g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f1798i = false;
    public static final int[] k = {b.f.c.f1542b, b.f.c.f1543c, b.f.c.n, b.f.c.y, b.f.c.B, b.f.c.C, b.f.c.D, b.f.c.E, b.f.c.F, b.f.c.G, b.f.c.f1544d, b.f.c.f1545e, b.f.c.f1546f, b.f.c.f1547g, b.f.c.f1548h, b.f.c.f1549i, b.f.c.j, b.f.c.k, b.f.c.l, b.f.c.m, b.f.c.o, b.f.c.p, b.f.c.q, b.f.c.r, b.f.c.s, b.f.c.t, b.f.c.u, b.f.c.v, b.f.c.w, b.f.c.x, b.f.c.z, b.f.c.A};
    public static e l = new e();

    /* JADX INFO: compiled from: ViewCompat.java */
    public class a implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ p f1799a;

        public a(p pVar) {
            this.f1799a = pVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return this.f1799a.a(view, a0.n(windowInsets)).m();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class b extends f<Boolean> {
        public b(int i2, Class cls, int i3) {
            super(i2, cls, i3);
        }

        @Override // b.f.m.s.f
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean c(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class c extends f<CharSequence> {
        public c(int i2, Class cls, int i3, int i4) {
            super(i2, cls, i3, i4);
        }

        @Override // b.f.m.s.f
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public CharSequence c(View view) {
            return view.getAccessibilityPaneTitle();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class d extends f<Boolean> {
        public d(int i2, Class cls, int i3) {
            super(i2, cls, i3);
        }

        @Override // b.f.m.s.f
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean c(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f1800c = new WeakHashMap<>();

        public final void a(View view, boolean z) {
            boolean z2 = view.getVisibility() == 0;
            if (z != z2) {
                if (z2) {
                    s.I(view, 16);
                }
                this.f1800c.put(view, Boolean.valueOf(z2));
            }
        }

        public final void b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f1800c.entrySet()) {
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
        public final int f1801a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<T> f1802b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1803c;

        public f(int i2, Class<T> cls, int i3) {
            this(i2, cls, 0, i3);
        }

        public final boolean a() {
            return Build.VERSION.SDK_INT >= 19;
        }

        public final boolean b() {
            return Build.VERSION.SDK_INT >= this.f1803c;
        }

        public abstract T c(View view);

        public T d(View view) {
            if (b()) {
                return c(view);
            }
            if (!a()) {
                return null;
            }
            T t = (T) view.getTag(this.f1801a);
            if (this.f1802b.isInstance(t)) {
                return t;
            }
            return null;
        }

        public f(int i2, Class<T> cls, int i3, int i4) {
            this.f1801a = i2;
            this.f1802b = cls;
            this.f1803c = i4;
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class g {
        public static a0 a(View view, a0 a0Var, Rect rect) {
            WindowInsets windowInsetsM = a0Var.m();
            if (windowInsetsM != null) {
                return a0.n(view.computeSystemWindowInsets(windowInsetsM, rect));
            }
            rect.setEmpty();
            return a0Var;
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
        public static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i2, i3);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public interface j {
        boolean a(View view, KeyEvent keyEvent);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ArrayList<WeakReference<View>> f1804a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f1805b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SparseArray<WeakReference<View>> f1806c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public WeakReference<KeyEvent> f1807d = null;

        public static k a(View view) {
            int i2 = b.f.c.N;
            k kVar = (k) view.getTag(i2);
            if (kVar != null) {
                return kVar;
            }
            k kVar2 = new k();
            view.setTag(i2, kVar2);
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
            WeakHashMap<View, Boolean> weakHashMap = this.f1805b;
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
            if (this.f1806c == null) {
                this.f1806c = new SparseArray<>();
            }
            return this.f1806c;
        }

        public final boolean e(View view, KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(b.f.c.O);
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
            WeakReference<KeyEvent> weakReference = this.f1807d;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f1807d = new WeakReference<>(keyEvent);
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
            if (view != null && s.F(view)) {
                e(view, keyEvent);
            }
            return true;
        }

        public final void g() {
            WeakHashMap<View, Boolean> weakHashMap = this.f1805b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = f1804a;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.f1805b == null) {
                    this.f1805b = new WeakHashMap<>();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList<WeakReference<View>> arrayList2 = f1804a;
                    View view = arrayList2.get(size).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.f1805b.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f1805b.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    public static String A(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f1795f;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int B(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static boolean C(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean D(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static boolean E(View view) {
        Boolean boolD = a().d(view);
        if (boolD == null) {
            return false;
        }
        return boolD.booleanValue();
    }

    public static boolean F(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static boolean G(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static boolean H(View view) {
        Boolean boolD = S().d(view);
        if (boolD == null) {
            return false;
        }
        return boolD.booleanValue();
    }

    public static void I(View view, int i2) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z = m(view) != null;
            if (l(view) != 0 || (z && view.getVisibility() == 0)) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i2);
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i2);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e2);
                }
            }
        }
    }

    public static void J(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetLeftAndRight(i2);
            return;
        }
        if (i3 < 21) {
            c(view, i2);
            return;
        }
        Rect rectR = r();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectR.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectR.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        c(view, i2);
        if (z && rectR.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectR);
        }
    }

    public static void K(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            view.offsetTopAndBottom(i2);
            return;
        }
        if (i3 < 21) {
            d(view, i2);
            return;
        }
        Rect rectR = r();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectR.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectR.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        d(view, i2);
        if (z && rectR.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectR);
        }
    }

    public static a0 L(View view, a0 a0Var) {
        WindowInsets windowInsetsM;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsM = a0Var.m()) != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsM);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsM)) {
                return a0.n(windowInsetsOnApplyWindowInsets);
            }
        }
        return a0Var;
    }

    public static f<CharSequence> M() {
        return new c(b.f.c.K, CharSequence.class, 8, 28);
    }

    public static void N(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void O(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void P(View view, Runnable runnable, long j2) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j2);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j2);
        }
    }

    public static void Q(View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            view.requestApplyInsets();
        } else if (i2 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static void R(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29) {
            i.a(view, context, iArr, attributeSet, typedArray, i2, i3);
        }
    }

    public static f<Boolean> S() {
        return new b(b.f.c.L, Boolean.class, 28);
    }

    public static void T(View view, b.f.m.a aVar) {
        if (aVar == null && (j(view) instanceof a.C0028a)) {
            aVar = new b.f.m.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.d());
    }

    public static void U(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void V(View view, ColorStateList colorStateList) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            if (view instanceof r) {
                ((r) view).setSupportBackgroundTintList(colorStateList);
                return;
            }
            return;
        }
        view.setBackgroundTintList(colorStateList);
        if (i2 == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void W(View view, PorterDuff.Mode mode) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21) {
            if (view instanceof r) {
                ((r) view).setSupportBackgroundTintMode(mode);
                return;
            }
            return;
        }
        view.setBackgroundTintMode(mode);
        if (i2 == 21) {
            Drawable background = view.getBackground();
            boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void X(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f2);
        }
    }

    public static void Y(View view, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            view.setImportantForAccessibility(i2);
        } else if (i3 >= 16) {
            if (i2 == 4) {
                i2 = 2;
            }
            view.setImportantForAccessibility(i2);
        }
    }

    public static void Z(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i2);
        }
    }

    public static f<Boolean> a() {
        return new d(b.f.c.J, Boolean.class, 28);
    }

    public static void a0(View view, p pVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (pVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new a(pVar));
            }
        }
    }

    public static w b(View view) {
        if (f1796g == null) {
            f1796g = new WeakHashMap<>();
        }
        w wVar = f1796g.get(view);
        if (wVar != null) {
            return wVar;
        }
        w wVar2 = new w(view);
        f1796g.put(view, wVar2);
        return wVar2;
    }

    public static void b0(View view, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i2, i3);
        }
    }

    public static void c(View view, int i2) {
        view.offsetLeftAndRight(i2);
        if (view.getVisibility() == 0) {
            e0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                e0((View) parent);
            }
        }
    }

    public static void c0(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f1795f == null) {
            f1795f = new WeakHashMap<>();
        }
        f1795f.put(view, str);
    }

    public static void d(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            e0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                e0((View) parent);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void d0(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof b.f.m.j) {
            ((b.f.m.j) view).stopNestedScroll();
        }
    }

    public static a0 e(View view, a0 a0Var, Rect rect) {
        return Build.VERSION.SDK_INT >= 21 ? g.a(view, a0Var, rect) : a0Var;
    }

    public static void e0(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static a0 f(View view, a0 a0Var) {
        WindowInsets windowInsetsM;
        return (Build.VERSION.SDK_INT < 21 || (windowInsetsM = a0Var.m()) == null || view.dispatchApplyWindowInsets(windowInsetsM).equals(windowInsetsM)) ? a0Var : a0.n(windowInsetsM);
    }

    public static boolean g(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).b(view, keyEvent);
    }

    public static boolean h(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).f(keyEvent);
    }

    public static b.f.m.a i(View view) {
        View.AccessibilityDelegate accessibilityDelegateJ = j(view);
        if (accessibilityDelegateJ == null) {
            return null;
        }
        return accessibilityDelegateJ instanceof a.C0028a ? ((a.C0028a) accessibilityDelegateJ).f1734a : new b.f.m.a(accessibilityDelegateJ);
    }

    public static View.AccessibilityDelegate j(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : k(view);
    }

    public static View.AccessibilityDelegate k(View view) {
        if (f1798i) {
            return null;
        }
        if (f1797h == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f1797h = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f1798i = true;
                return null;
            }
        }
        try {
            Object obj = f1797h.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f1798i = true;
            return null;
        }
    }

    public static int l(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    public static CharSequence m(View view) {
        return M().d(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList n(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof r) {
            return ((r) view).getSupportBackgroundTintList();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode o(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof r) {
            return ((r) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static Display p(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (F(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static float q(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static Rect r() {
        if (j == null) {
            j = new ThreadLocal<>();
        }
        Rect rect = j.get();
        if (rect == null) {
            rect = new Rect();
            j.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static boolean s(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static int t(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    @SuppressLint({"InlinedApi"})
    public static int u(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static int v(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static int w(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f1794e) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinHeight");
                f1793d = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f1794e = true;
        }
        Field field = f1793d;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static int x(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!f1792c) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinWidth");
                f1791b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f1792c = true;
        }
        Field field = f1791b;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static ViewParent y(View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static a0 z(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return a0.n(h.a(view));
        }
        return null;
    }
}
