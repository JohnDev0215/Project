package spi;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.car.ui.rotary.FocusParkingView;
import java.util.function.Predicate;
import spi.yc;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yc {

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface a {
        boolean a();

        void b(@Nullable Runnable runnable);

        void c(@Nullable Runnable runnable);
    }

    private yc() {
    }

    public static boolean a(@NonNull View view, int i, @Nullable View view2, boolean z, boolean z2) {
        a aVar;
        View viewF;
        if (!z && o(view2)) {
            return true;
        }
        if (i < 7 && o(c(view, new tc(view, 1), n7.k))) {
            return true;
        }
        if (i < 6 && o(e(view))) {
            return true;
        }
        if (i < 5) {
            View viewC = c(view, n7.l, n7.m);
            if (viewC == null) {
                viewF = null;
            } else {
                View viewG = g(viewC);
                viewF = viewG != null ? viewG : f(viewC);
            }
            if (o(viewF)) {
                return true;
            }
        }
        if (i < 4 && o(g(view))) {
            return true;
        }
        if (z2 && i < 5 && (aVar = (a) c(view, n7.r, n7.s)) != null && !aVar.a()) {
            j(aVar);
            return false;
        }
        if (z && o(view2)) {
            return true;
        }
        if (i < 3) {
            if (c(view, new tc(view, 0), n7.j) != null) {
                return true;
            }
        }
        if (i < 2) {
            return o(c(view, n7.n, n7.o));
        }
        return false;
    }

    public static boolean b(@NonNull View view) {
        if ((view.isFocusable() || TextUtils.equals(view.getContentDescription(), "com.android.car.ui.utils.FOCUS_DELEGATING_CONTAINER")) && view.isEnabled() && view.isShown() && view.getWidth() > 0 && view.getHeight() > 0 && view.isAttachedToWindow() && !(view instanceof FocusParkingView)) {
            return !m(view) || f(view) == null;
        }
        return false;
    }

    @Nullable
    public static View c(@NonNull View view, @NonNull Predicate<View> predicate, @Nullable Predicate<View> predicate2) {
        if (predicate2 != null && predicate2.test(view)) {
            return null;
        }
        if (predicate.test(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View viewC = c(viewGroup.getChildAt(i), predicate, predicate2);
                if (viewC != null) {
                    return viewC;
                }
            }
        }
        return null;
    }

    @Nullable
    public static Activity d(@Nullable Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static View e(@NonNull View view) {
        if (!view.isShown()) {
            return null;
        }
        if (view instanceof o4) {
            View defaultFocusView = ((o4) view).getDefaultFocusView();
            if (defaultFocusView != null && b(defaultFocusView)) {
                return defaultFocusView;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View viewE = e(viewGroup.getChildAt(i));
                if (viewE != null) {
                    return viewE;
                }
            }
        }
        return null;
    }

    @Nullable
    @VisibleForTesting
    public static View f(@NonNull View view) {
        return c(view, new tc(view, 3), n7.q);
    }

    @Nullable
    @VisibleForTesting
    public static View g(@NonNull View view) {
        return c(view, new tc(view, 2), n7.p);
    }

    @Nullable
    public static o4 h(@NonNull View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof o4) {
                return (o4) parent;
            }
        }
        return null;
    }

    @VisibleForTesting
    public static int i(@Nullable View view) {
        ViewGroup viewGroup;
        boolean z = true;
        if (view == null || (view instanceof FocusParkingView) || !view.isShown()) {
            return 1;
        }
        if (view.isFocusedByDefault()) {
            return 7;
        }
        o4 o4VarH = h(view);
        boolean z2 = false;
        if (o4VarH != null && view == o4VarH.getDefaultFocusView()) {
            return 6;
        }
        for (ViewParent parent = view.getParent(); parent != null && (parent instanceof ViewGroup); parent = parent.getParent()) {
            viewGroup = (ViewGroup) parent;
            if (l(viewGroup)) {
                break;
            }
        }
        viewGroup = null;
        if (viewGroup != null) {
            if (g(viewGroup) != view && f(viewGroup) != view) {
                z = false;
            }
            z2 = z;
        }
        if (z2) {
            return 5;
        }
        if (view.isSelected()) {
            return 4;
        }
        return m(view) ? 2 : 3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void j(@NonNull final a aVar) {
        if (aVar instanceof View) {
            final View view = (View) aVar;
            final Runnable[] runnableArr = new Runnable[1];
            final Runnable[] runnableArr2 = new Runnable[1];
            final ViewTreeObserver.OnGlobalLayoutListener[] onGlobalLayoutListenerArr = new ViewTreeObserver.OnGlobalLayoutListener[1];
            if (!aVar.a()) {
                Log.v("ViewUtils", "The lazyLayoutView has not completed layout: " + aVar);
                runnableArr[0] = new Runnable() { // from class: spi.rc
                    @Override // java.lang.Runnable
                    public final void run() {
                        yc.a aVar2 = aVar;
                        ViewTreeObserver.OnGlobalLayoutListener[] onGlobalLayoutListenerArr2 = onGlobalLayoutListenerArr;
                        Runnable[] runnableArr3 = runnableArr;
                        Runnable[] runnableArr4 = runnableArr2;
                        Log.v("ViewUtils", "The lazyLayoutView completed layout: " + aVar2);
                        if (yc.k(aVar2)) {
                            Log.v("ViewUtils", "Focus restored after lazyLayoutView completed layout");
                            yc.n(aVar2, onGlobalLayoutListenerArr2, runnableArr3, runnableArr4);
                        }
                    }
                };
                aVar.c(runnableArr[0]);
            }
            if (!view.isShown()) {
                Log.d("ViewUtils", "The lazyLayoutView is not shown: " + aVar);
                onGlobalLayoutListenerArr[0] = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: spi.qc
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public final void onGlobalLayout() {
                        View view2 = view;
                        yc.a aVar2 = aVar;
                        ViewTreeObserver.OnGlobalLayoutListener[] onGlobalLayoutListenerArr2 = onGlobalLayoutListenerArr;
                        Runnable[] runnableArr3 = runnableArr;
                        Runnable[] runnableArr4 = runnableArr2;
                        Log.d("ViewUtils", "onGlobalLayoutListener is called");
                        if (view2.isShown()) {
                            Log.d("ViewUtils", "The lazyLayoutView is shown");
                            if (yc.k(aVar2)) {
                                Log.v("ViewUtils", "Focus restored after showing lazyLayoutView");
                                yc.n(aVar2, onGlobalLayoutListenerArr2, runnableArr3, runnableArr4);
                            }
                        }
                    }
                };
                view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListenerArr[0]);
            }
            runnableArr2[0] = new Runnable() { // from class: spi.sc
                @Override // java.lang.Runnable
                public final void run() {
                    yc.a aVar2 = aVar;
                    ViewTreeObserver.OnGlobalLayoutListener[] onGlobalLayoutListenerArr2 = onGlobalLayoutListenerArr;
                    Runnable[] runnableArr3 = runnableArr;
                    Runnable[] runnableArr4 = runnableArr2;
                    View view2 = view;
                    Log.d("ViewUtils", "Starting delayedTask");
                    yc.n(aVar2, onGlobalLayoutListenerArr2, runnableArr3, runnableArr4);
                    View viewFindFocus = view2.getRootView().findFocus();
                    if ((viewFindFocus == null || (viewFindFocus instanceof FocusParkingView)) ? false : true) {
                        return;
                    }
                    if (aVar2.a() && view2.isShown()) {
                        Log.d("ViewUtils", "Last attempt to restore focus inside the lazyLayoutView");
                        if (yc.k(aVar2)) {
                            Log.d("ViewUtils", "Restored focus inside the lazyLayoutView");
                            return;
                        }
                    }
                    yc.a(view2.getRootView(), 1, null, false, false);
                }
            };
            view.postDelayed(runnableArr2[0], 3000L);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean k(@NonNull a aVar) {
        if (!(aVar instanceof View)) {
            return false;
        }
        View view = (View) aVar;
        View viewFindFocus = view.getRootView().findFocus();
        if ((viewFindFocus == null || (viewFindFocus instanceof FocusParkingView)) ? false : true) {
            return true;
        }
        return a(view, i(null), null, false, false);
    }

    public static boolean l(@NonNull View view) {
        CharSequence contentDescription = view.getContentDescription();
        return TextUtils.equals(contentDescription, "com.android.car.ui.utils.ROTARY_CONTAINER") || TextUtils.equals(contentDescription, "com.android.car.ui.utils.VERTICALLY_SCROLLABLE") || TextUtils.equals(contentDescription, "com.android.car.ui.utils.HORIZONTALLY_SCROLLABLE");
    }

    public static boolean m(@NonNull View view) {
        CharSequence contentDescription = view.getContentDescription();
        return TextUtils.equals(contentDescription, "com.android.car.ui.utils.VERTICALLY_SCROLLABLE") || TextUtils.equals(contentDescription, "com.android.car.ui.utils.HORIZONTALLY_SCROLLABLE");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void n(@NonNull a aVar, ViewTreeObserver.OnGlobalLayoutListener[] onGlobalLayoutListenerArr, Runnable[] runnableArr, Runnable[] runnableArr2) {
        aVar.b(runnableArr[0]);
        if (aVar instanceof View) {
            View view = (View) aVar;
            view.removeCallbacks(runnableArr2[0]);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListenerArr[0]);
        }
    }

    public static boolean o(@Nullable View view) {
        if (view == null || !b(view)) {
            return false;
        }
        if (view.isFocused()) {
            return true;
        }
        return view.performAccessibilityAction(1, null);
    }
}
