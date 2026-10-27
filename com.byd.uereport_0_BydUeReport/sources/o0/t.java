package o0;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
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
import o0.a;
import p0.c;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f6700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f6701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f6702d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f6703e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static WeakHashMap<View, String> f6704f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Field f6706h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static ThreadLocal<Rect> f6708j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicInteger f6699a = new AtomicInteger(1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static WeakHashMap<View, x> f6705g = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f6707i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f6709k = {R$id.accessibility_custom_action_0, R$id.accessibility_custom_action_1, R$id.accessibility_custom_action_2, R$id.accessibility_custom_action_3, R$id.accessibility_custom_action_4, R$id.accessibility_custom_action_5, R$id.accessibility_custom_action_6, R$id.accessibility_custom_action_7, R$id.accessibility_custom_action_8, R$id.accessibility_custom_action_9, R$id.accessibility_custom_action_10, R$id.accessibility_custom_action_11, R$id.accessibility_custom_action_12, R$id.accessibility_custom_action_13, R$id.accessibility_custom_action_14, R$id.accessibility_custom_action_15, R$id.accessibility_custom_action_16, R$id.accessibility_custom_action_17, R$id.accessibility_custom_action_18, R$id.accessibility_custom_action_19, R$id.accessibility_custom_action_20, R$id.accessibility_custom_action_21, R$id.accessibility_custom_action_22, R$id.accessibility_custom_action_23, R$id.accessibility_custom_action_24, R$id.accessibility_custom_action_25, R$id.accessibility_custom_action_26, R$id.accessibility_custom_action_27, R$id.accessibility_custom_action_28, R$id.accessibility_custom_action_29, R$id.accessibility_custom_action_30, R$id.accessibility_custom_action_31};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static e f6710l = new e();

    /* JADX INFO: compiled from: ViewCompat.java */
    public class a implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ p f6711a;

        public a(p pVar) {
            this.f6711a = pVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return this.f6711a.a(view, b0.p(windowInsets)).o();
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class b extends f<Boolean> {
        public b(int i8, Class cls, int i9) {
            super(i8, cls, i9);
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isScreenReaderFocusable());
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setScreenReaderFocusable(bool.booleanValue());
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class c extends f<CharSequence> {
        public c(int i8, Class cls, int i9, int i10) {
            super(i8, cls, i9, i10);
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public CharSequence d(View view) {
            return view.getAccessibilityPaneTitle();
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public class d extends f<Boolean> {
        public d(int i8, Class cls, int i9) {
            super(i8, cls, i9);
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public Boolean d(View view) {
            return Boolean.valueOf(view.isAccessibilityHeading());
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void e(View view, Boolean bool) {
            view.setAccessibilityHeading(bool.booleanValue());
        }

        @Override // o0.t.f
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean h(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f6712d = new WeakHashMap<>();

        public final void a(View view, boolean z7) {
            boolean z8 = view.getVisibility() == 0;
            if (z7 != z8) {
                if (z8) {
                    t.U(view, 16);
                }
                this.f6712d.put(view, Boolean.valueOf(z8));
            }
        }

        public final void b(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            for (Map.Entry<View, Boolean> entry : this.f6712d.entrySet()) {
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
        public final int f6713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<T> f6714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6715c;

        public f(int i8, Class<T> cls, int i9) {
            this(i8, cls, 0, i9);
        }

        public boolean a(Boolean bool, Boolean bool2) {
            return (bool == null ? false : bool.booleanValue()) == (bool2 == null ? false : bool2.booleanValue());
        }

        public final boolean b() {
            return Build.VERSION.SDK_INT >= 19;
        }

        public final boolean c() {
            return Build.VERSION.SDK_INT >= this.f6715c;
        }

        public abstract T d(View view);

        public abstract void e(View view, T t7);

        public T f(View view) {
            if (c()) {
                return d(view);
            }
            if (!b()) {
                return null;
            }
            T t7 = (T) view.getTag(this.f6713a);
            if (this.f6714b.isInstance(t7)) {
                return t7;
            }
            return null;
        }

        public void g(View view, T t7) {
            if (c()) {
                e(view, t7);
            } else if (b() && h(f(view), t7)) {
                t.D(view);
                view.setTag(this.f6713a, t7);
                t.U(view, 0);
            }
        }

        public abstract boolean h(T t7, T t8);

        public f(int i8, Class<T> cls, int i9, int i10) {
            this.f6713a = i8;
            this.f6714b = cls;
            this.f6715c = i10;
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
        public static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i8, int i9) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i8, i9);
        }
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public interface j {
        boolean a(View view, KeyEvent keyEvent);
    }

    /* JADX INFO: compiled from: ViewCompat.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ArrayList<WeakReference<View>> f6716a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakHashMap<View, Boolean> f6717b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public SparseArray<WeakReference<View>> f6718c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public WeakReference<KeyEvent> f6719d = null;

        public static k a(View view) {
            int i8 = R$id.tag_unhandled_key_event_manager;
            k kVar = (k) view.getTag(i8);
            if (kVar != null) {
                return kVar;
            }
            k kVar2 = new k();
            view.setTag(i8, kVar2);
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
            WeakHashMap<View, Boolean> weakHashMap = this.f6717b;
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
            if (this.f6718c == null) {
                this.f6718c = new SparseArray<>();
            }
            return this.f6718c;
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
            WeakReference<KeyEvent> weakReference = this.f6719d;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.f6719d = new WeakReference<>(keyEvent);
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
            if (view != null && t.P(view)) {
                e(view, keyEvent);
            }
            return true;
        }

        public final void g() {
            WeakHashMap<View, Boolean> weakHashMap = this.f6717b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = f6716a;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.f6717b == null) {
                    this.f6717b = new WeakHashMap<>();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList<WeakReference<View>> arrayList2 = f6716a;
                    View view = arrayList2.get(size).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.f6717b.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f6717b.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }
    }

    public static int A(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static void A0(View view, r rVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (rVar != null ? rVar.a() : null));
        }
    }

    public static int B(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!f6703e) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinHeight");
                f6702d = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f6703e = true;
        }
        Field field = f6702d;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static void B0(View view, int i8, int i9) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i8, i9);
        }
    }

    public static int C(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        if (!f6701c) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinWidth");
                f6700b = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f6701c = true;
        }
        Field field = f6700b;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static void C0(View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setTransitionName(str);
            return;
        }
        if (f6704f == null) {
            f6704f = new WeakHashMap<>();
        }
        f6704f.put(view, str);
    }

    public static o0.a D(View view) {
        o0.a aVarL = l(view);
        if (aVarL == null) {
            aVarL = new o0.a();
        }
        l0(view, aVarL);
        return aVarL;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void D0(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof o0.j) {
            ((o0.j) view).stopNestedScroll();
        }
    }

    public static int E(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingEnd() : view.getPaddingRight();
    }

    public static void E0(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static int F(View view) {
        return Build.VERSION.SDK_INT >= 17 ? view.getPaddingStart() : view.getPaddingLeft();
    }

    public static ViewParent G(View view) {
        return Build.VERSION.SDK_INT >= 16 ? view.getParentForAccessibility() : view.getParent();
    }

    public static b0 H(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return b0.p(h.a(view));
        }
        return null;
    }

    public static String I(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getTransitionName();
        }
        WeakHashMap<View, String> weakHashMap = f6704f;
        if (weakHashMap == null) {
            return null;
        }
        return weakHashMap.get(view);
    }

    public static int J(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static float K(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    public static boolean L(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean M(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasOverlappingRendering();
        }
        return true;
    }

    public static boolean N(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.hasTransientState();
        }
        return false;
    }

    public static boolean O(View view) {
        Boolean boolF = a().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    public static boolean P(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static boolean Q(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean R(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.isNestedScrollingEnabled();
        }
        if (view instanceof o0.j) {
            return ((o0.j) view).isNestedScrollingEnabled();
        }
        return false;
    }

    public static boolean S(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.isPaddingRelative();
        }
        return false;
    }

    public static boolean T(View view) {
        Boolean boolF = k0().f(view);
        if (boolF == null) {
            return false;
        }
        return boolF.booleanValue();
    }

    public static void U(View view, int i8) {
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            boolean z7 = p(view) != null;
            if (o(view) != 0 || (z7 && view.getVisibility() == 0)) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z7 ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i8);
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i8);
                } catch (AbstractMethodError e8) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e8);
                }
            }
        }
    }

    public static void V(View view, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 23) {
            view.offsetLeftAndRight(i8);
            return;
        }
        if (i9 < 21) {
            e(view, i8);
            return;
        }
        Rect rectW = w();
        boolean z7 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectW.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z7 = !rectW.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        e(view, i8);
        if (z7 && rectW.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectW);
        }
    }

    public static void W(View view, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 23) {
            view.offsetTopAndBottom(i8);
            return;
        }
        if (i9 < 21) {
            f(view, i8);
            return;
        }
        Rect rectW = w();
        boolean z7 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectW.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z7 = !rectW.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        f(view, i8);
        if (z7 && rectW.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectW);
        }
    }

    public static b0 X(View view, b0 b0Var) {
        WindowInsets windowInsetsO;
        if (Build.VERSION.SDK_INT >= 21 && (windowInsetsO = b0Var.o()) != null) {
            WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsO);
            if (!windowInsetsOnApplyWindowInsets.equals(windowInsetsO)) {
                return b0.p(windowInsetsOnApplyWindowInsets);
            }
        }
        return b0Var;
    }

    public static void Y(View view, p0.c cVar) {
        view.onInitializeAccessibilityNodeInfo(cVar.E0());
    }

    public static f<CharSequence> Z() {
        return new c(R$id.tag_accessibility_pane_title, CharSequence.class, 8, 28);
    }

    public static f<Boolean> a() {
        return new d(R$id.tag_accessibility_heading, Boolean.class, 28);
    }

    public static boolean a0(View view, int i8, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.performAccessibilityAction(i8, bundle);
        }
        return false;
    }

    public static void b(View view, c.a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            D(view);
            g0(aVar.b(), view);
            q(view).add(aVar);
            U(view, 0);
        }
    }

    public static void b0(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static x c(View view) {
        if (f6705g == null) {
            f6705g = new WeakHashMap<>();
        }
        x xVar = f6705g.get(view);
        if (xVar != null) {
            return xVar;
        }
        x xVar2 = new x(view);
        f6705g.put(view, xVar2);
        return xVar2;
    }

    public static void c0(View view, int i8, int i9, int i10, int i11) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation(i8, i9, i10, i11);
        } else {
            view.postInvalidate(i8, i9, i10, i11);
        }
    }

    @Deprecated
    public static boolean d(View view, int i8) {
        return view.canScrollVertically(i8);
    }

    public static void d0(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void e(View view, int i8) {
        view.offsetLeftAndRight(i8);
        if (view.getVisibility() == 0) {
            E0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                E0((View) parent);
            }
        }
    }

    public static void e0(View view, Runnable runnable, long j8) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j8);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j8);
        }
    }

    public static void f(View view, int i8) {
        view.offsetTopAndBottom(i8);
        if (view.getVisibility() == 0) {
            E0(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                E0((View) parent);
            }
        }
    }

    public static void f0(View view, int i8) {
        if (Build.VERSION.SDK_INT >= 21) {
            g0(i8, view);
            U(view, 0);
        }
    }

    public static b0 g(View view, b0 b0Var, Rect rect) {
        return Build.VERSION.SDK_INT >= 21 ? g.a(view, b0Var, rect) : b0Var;
    }

    public static void g0(int i8, View view) {
        List<c.a> listQ = q(view);
        for (int i9 = 0; i9 < listQ.size(); i9++) {
            if (listQ.get(i9).b() == i8) {
                listQ.remove(i9);
                return;
            }
        }
    }

    public static b0 h(View view, b0 b0Var) {
        WindowInsets windowInsetsO;
        return (Build.VERSION.SDK_INT < 21 || (windowInsetsO = b0Var.o()) == null || view.dispatchApplyWindowInsets(windowInsetsO).equals(windowInsetsO)) ? b0Var : b0.p(windowInsetsO);
    }

    public static void h0(View view, c.a aVar, CharSequence charSequence, p0.f fVar) {
        if (fVar == null && charSequence == null) {
            f0(view, aVar.b());
        } else {
            b(view, aVar.a(charSequence, fVar));
        }
    }

    public static boolean i(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).b(view, keyEvent);
    }

    public static void i0(View view) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 20) {
            view.requestApplyInsets();
        } else if (i8 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static boolean j(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return k.a(view).f(keyEvent);
    }

    public static void j0(View view, @SuppressLint({"ContextFirst"}) Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i8, int i9) {
        if (Build.VERSION.SDK_INT >= 29) {
            i.a(view, context, iArr, attributeSet, typedArray, i8, i9);
        }
    }

    public static int k() {
        AtomicInteger atomicInteger;
        int i8;
        int i9;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            atomicInteger = f6699a;
            i8 = atomicInteger.get();
            i9 = i8 + 1;
            if (i9 > 16777215) {
                i9 = 1;
            }
        } while (!atomicInteger.compareAndSet(i8, i9));
        return i8;
    }

    public static f<Boolean> k0() {
        return new b(R$id.tag_screen_reader_focusable, Boolean.class, 28);
    }

    public static o0.a l(View view) {
        View.AccessibilityDelegate accessibilityDelegateM = m(view);
        if (accessibilityDelegateM == null) {
            return null;
        }
        return accessibilityDelegateM instanceof a.C0097a ? ((a.C0097a) accessibilityDelegateM).f6660a : new o0.a(accessibilityDelegateM);
    }

    public static void l0(View view, o0.a aVar) {
        if (aVar == null && (m(view) instanceof a.C0097a)) {
            aVar = new o0.a();
        }
        view.setAccessibilityDelegate(aVar == null ? null : aVar.d());
    }

    public static View.AccessibilityDelegate m(View view) {
        return Build.VERSION.SDK_INT >= 29 ? view.getAccessibilityDelegate() : n(view);
    }

    public static void m0(View view, boolean z7) {
        a().g(view, Boolean.valueOf(z7));
    }

    public static View.AccessibilityDelegate n(View view) {
        if (f6707i) {
            return null;
        }
        if (f6706h == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f6706h = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f6707i = true;
                return null;
            }
        }
        try {
            Object obj = f6706h.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f6707i = true;
            return null;
        }
    }

    public static void n0(View view, int i8) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.setAccessibilityLiveRegion(i8);
        }
    }

    public static int o(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.getAccessibilityLiveRegion();
        }
        return 0;
    }

    public static void o0(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static CharSequence p(View view) {
        return Z().f(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void p0(View view, ColorStateList colorStateList) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 21) {
            if (view instanceof s) {
                ((s) view).setSupportBackgroundTintList(colorStateList);
                return;
            }
            return;
        }
        view.setBackgroundTintList(colorStateList);
        if (i8 == 21) {
            Drawable background = view.getBackground();
            boolean z7 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z7) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static List<c.a> q(View view) {
        int i8 = R$id.tag_accessibility_actions;
        ArrayList arrayList = (ArrayList) view.getTag(i8);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(i8, arrayList2);
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void q0(View view, PorterDuff.Mode mode) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 21) {
            if (view instanceof s) {
                ((s) view).setSupportBackgroundTintMode(mode);
                return;
            }
            return;
        }
        view.setBackgroundTintMode(mode);
        if (i8 == 21) {
            Drawable background = view.getBackground();
            boolean z7 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z7) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList r(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static void r0(View view, Rect rect) {
        if (Build.VERSION.SDK_INT >= 18) {
            view.setClipBounds(rect);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode s(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof s) {
            return ((s) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static void s0(View view, float f8) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f8);
        }
    }

    public static Rect t(View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return view.getClipBounds();
        }
        return null;
    }

    @Deprecated
    public static void t0(View view, boolean z7) {
        view.setFitsSystemWindows(z7);
    }

    public static Display u(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getDisplay();
        }
        if (P(view)) {
            return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
        }
        return null;
    }

    public static void u0(View view, boolean z7) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setHasTransientState(z7);
        }
    }

    public static float v(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getElevation();
        }
        return 0.0f;
    }

    public static void v0(View view, int i8) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 19) {
            view.setImportantForAccessibility(i8);
        } else if (i9 >= 16) {
            if (i8 == 4) {
                i8 = 2;
            }
            view.setImportantForAccessibility(i8);
        }
    }

    public static Rect w() {
        if (f6708j == null) {
            f6708j = new ThreadLocal<>();
        }
        Rect rect = f6708j.get();
        if (rect == null) {
            rect = new Rect();
            f6708j.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static void w0(View view, int i8) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i8);
        }
    }

    public static boolean x(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getFitsSystemWindows();
        }
        return false;
    }

    public static void x0(View view, Paint paint) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayerPaint(paint);
        } else {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }
    }

    public static int y(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getImportantForAccessibility();
        }
        return 0;
    }

    public static void y0(View view, p pVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (pVar == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new a(pVar));
            }
        }
    }

    @SuppressLint({"InlinedApi"})
    public static int z(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static void z0(View view, int i8, int i9, int i10, int i11) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(i8, i9, i10, i11);
        } else {
            view.setPadding(i8, i9, i10, i11);
        }
    }
}
