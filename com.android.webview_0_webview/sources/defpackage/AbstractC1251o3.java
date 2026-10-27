package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: o3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1251o3 {
    public static boolean a;
    public static ThreadLocal b;

    static {
        new AtomicInteger(1);
        a = false;
    }

    public static void A(View view) {
        view.requestApplyInsets();
    }

    public static void B(View view, AbstractC0750f3 abstractC0750f3) {
        view.setAccessibilityDelegate(abstractC0750f3 == null ? null : abstractC0750f3.a);
    }

    public static void C(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void D(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
        if (Build.VERSION.SDK_INT == 21) {
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

    public static void E(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
        if (Build.VERSION.SDK_INT == 21) {
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

    public static void F(View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static void G(View view, float f) {
        view.setElevation(f);
    }

    public static void H(View view, boolean z) {
        view.setHasTransientState(z);
    }

    public static void I(View view, String str) {
        view.setTransitionName(str);
    }

    public static void J(View view) {
        view.stopNestedScroll();
    }

    public static void K(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void a(View view, int i) {
        view.offsetLeftAndRight(i);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            Object parent = view.getParent();
            if (parent instanceof View) {
                K((View) parent);
            }
        }
    }

    public static void b(View view, int i) {
        view.offsetTopAndBottom(i);
        if (view.getVisibility() == 0) {
            float translationY = view.getTranslationY();
            view.setTranslationY(1.0f + translationY);
            view.setTranslationY(translationY);
            Object parent = view.getParent();
            if (parent instanceof View) {
                K((View) parent);
            }
        }
    }

    public static boolean c(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        C1196n3 c1196n3A = C1196n3.a(view);
        if (keyEvent.getAction() == 0) {
            Boolean bool = Boolean.TRUE;
            WeakHashMap weakHashMap = c1196n3A.a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!C1196n3.d.isEmpty()) {
                synchronized (C1196n3.d) {
                    if (c1196n3A.a == null) {
                        c1196n3A.a = new WeakHashMap();
                    }
                    int size = C1196n3.d.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        View view2 = (View) ((WeakReference) C1196n3.d.get(size)).get();
                        if (view2 == null) {
                            C1196n3.d.remove(size);
                        } else {
                            c1196n3A.a.put(view2, bool);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                c1196n3A.a.put((View) parent, bool);
                            }
                        }
                    }
                }
            }
        }
        View viewB = c1196n3A.b(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewB != null && !KeyEvent.isModifierKey(keyCode)) {
                if (c1196n3A.b == null) {
                    c1196n3A.b = new SparseArray();
                }
                c1196n3A.b.put(keyCode, new WeakReference(viewB));
            }
        }
        return viewB != null;
    }

    public static boolean d(View view, KeyEvent keyEvent) {
        int iIndexOfKey;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        C1196n3 c1196n3A = C1196n3.a(view);
        WeakReference weakReference = c1196n3A.c;
        if (weakReference != null && weakReference.get() == keyEvent) {
            return false;
        }
        c1196n3A.c = new WeakReference(keyEvent);
        WeakReference weakReference2 = null;
        if (c1196n3A.b == null) {
            c1196n3A.b = new SparseArray();
        }
        SparseArray sparseArray = c1196n3A.b;
        if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference2 = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference2 == null) {
            weakReference2 = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference2 == null) {
            return false;
        }
        View view2 = (View) weakReference2.get();
        if (view2 != null && view2.isAttachedToWindow()) {
            c1196n3A.c(view2, keyEvent);
        }
        return true;
    }

    public static ColorStateList e(View view) {
        return view.getBackgroundTintList();
    }

    public static Rect f(View view) {
        return view.getClipBounds();
    }

    public static Display g(View view) {
        return view.getDisplay();
    }

    public static Rect h() {
        if (b == null) {
            b = new ThreadLocal();
        }
        Rect rect = (Rect) b.get();
        if (rect == null) {
            rect = new Rect();
            b.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static boolean i(View view) {
        return view.getFitsSystemWindows();
    }

    public static int j(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static int k(View view) {
        return view.getLayoutDirection();
    }

    public static int l(View view) {
        return view.getMinimumHeight();
    }

    public static int m(View view) {
        return view.getMinimumWidth();
    }

    public static int n(View view) {
        return view.getPaddingEnd();
    }

    public static int o(View view) {
        return view.getPaddingStart();
    }

    public static String p(View view) {
        return view.getTransitionName();
    }

    public static int q(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static float r(View view) {
        return view.getZ();
    }

    public static boolean s(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean t(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean u(View view) {
        return view.isLaidOut();
    }

    public static boolean v(View view) {
        return view.isPaddingRelative();
    }

    public static void w(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i);
            return;
        }
        Rect rectH = h();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectH.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectH.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        a(view, i);
        if (z && rectH.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectH);
        }
    }

    public static void x(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i);
            return;
        }
        Rect rectH = h();
        boolean z = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectH.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z = !rectH.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        b(view, i);
        if (z && rectH.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectH);
        }
    }

    public static void y(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void z(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
