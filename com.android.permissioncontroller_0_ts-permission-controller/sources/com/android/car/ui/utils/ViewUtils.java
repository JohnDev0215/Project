package com.android.car.ui.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.car.ui.FocusArea;
import com.android.car.ui.FocusParkingView;
import com.android.car.ui.R;
import com.android.car.ui.uxr.DrawableStateView;
import com.android.permissioncontroller.permission.utils.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes7.dex */
public final class ViewUtils {
    static final int DEFAULT_FOCUS = 5;
    static final int FOCUSED_BY_DEFAULT = 6;
    static final int IMPLICIT_DEFAULT_FOCUS = 4;
    static final int NO_FOCUS = 1;
    static final int REGULAR_FOCUS = 3;
    static final int SCROLLABLE_CONTAINER_FOCUS = 2;
    private static int[] sRestrictedState;

    @Retention(RetentionPolicy.SOURCE)
    private @interface FocusLevel {
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface Predicate<T> {
        boolean test(T t);
    }

    private ViewUtils() {
    }

    public static FocusArea getAncestorFocusArea(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof FocusArea) {
                return (FocusArea) parent;
            }
        }
        return null;
    }

    public static ViewGroup getAncestorScrollableContainer(View view) {
        if (view == null) {
            return null;
        }
        for (ViewParent parent = view.getParent(); parent != null && (parent instanceof ViewGroup) && !(parent instanceof FocusArea); parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (isScrollableContainer(viewGroup)) {
                return viewGroup;
            }
        }
        return null;
    }

    public static boolean requestFocus(View view) {
        if (view == null || !canTakeFocus(view)) {
            return false;
        }
        if (view.isFocused()) {
            return true;
        }
        return view.performAccessibilityAction(1, null);
    }

    public static boolean adjustFocus(View root, View currentFocus) {
        int currentLevel = getFocusLevel(currentFocus);
        return adjustFocus(root, currentLevel, null, false);
    }

    public static boolean initFocus(View root, View currentFocus) {
        int currentLevel = getFocusLevel(currentFocus);
        if (currentLevel >= 3) {
            return false;
        }
        return adjustFocus(root, currentLevel, null, false);
    }

    static boolean adjustFocus(View root, int currentLevel) {
        return adjustFocus(root, currentLevel, null, false);
    }

    public static boolean adjustFocus(View root, View cachedFocusedView, boolean defaultFocusOverridesHistory) {
        return adjustFocus(root, 1, cachedFocusedView, defaultFocusOverridesHistory);
    }

    private static boolean adjustFocus(View root, int currentLevel, View cachedFocusedView, boolean defaultFocusOverridesHistory) {
        if (!defaultFocusOverridesHistory && requestFocus(cachedFocusedView)) {
            return true;
        }
        if (currentLevel < 6 && focusOnFocusedByDefaultView(root)) {
            return true;
        }
        if (currentLevel < 5 && focusOnDefaultFocusView(root)) {
            return true;
        }
        if (currentLevel < 4 && focusOnImplicitDefaultFocusView(root)) {
            return true;
        }
        if (defaultFocusOverridesHistory && requestFocus(cachedFocusedView)) {
            return true;
        }
        if (currentLevel < 3 && focusOnFirstRegularView(root)) {
            return true;
        }
        if (currentLevel < 2) {
            return focusOnScrollableContainer(root);
        }
        return false;
    }

    static int getFocusLevel(View view) {
        if (view == null || (view instanceof FocusParkingView) || !view.isShown()) {
            return 1;
        }
        if (view.isFocusedByDefault()) {
            return 6;
        }
        if (isDefaultFocus(view)) {
            return 5;
        }
        if (isImplicitDefaultFocusView(view)) {
            return 4;
        }
        if (isScrollableContainer(view)) {
            return 2;
        }
        return 3;
    }

    private static boolean isDefaultFocus(View view) {
        FocusArea parent = getAncestorFocusArea(view);
        return parent != null && view == parent.getDefaultFocusView();
    }

    static boolean isImplicitDefaultFocusView(View view) {
        ViewGroup rotaryContainer = null;
        ViewParent parent = view.getParent();
        while (true) {
            if (parent == null || !(parent instanceof ViewGroup)) {
                break;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if (isRotaryContainer(viewGroup)) {
                rotaryContainer = viewGroup;
                break;
            }
            parent = parent.getParent();
        }
        if (rotaryContainer == null) {
            return false;
        }
        return findFirstSelectedFocusableDescendant(rotaryContainer) == view || findFirstFocusableDescendant(rotaryContainer) == view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isRotaryContainer(View view) {
        CharSequence contentDescription = view.getContentDescription();
        return TextUtils.equals(contentDescription, RotaryConstants.ROTARY_CONTAINER) || TextUtils.equals(contentDescription, RotaryConstants.ROTARY_VERTICALLY_SCROLLABLE) || TextUtils.equals(contentDescription, RotaryConstants.ROTARY_HORIZONTALLY_SCROLLABLE);
    }

    private static boolean isScrollableContainer(View view) {
        CharSequence contentDescription = view.getContentDescription();
        return TextUtils.equals(contentDescription, RotaryConstants.ROTARY_VERTICALLY_SCROLLABLE) || TextUtils.equals(contentDescription, RotaryConstants.ROTARY_HORIZONTALLY_SCROLLABLE);
    }

    private static boolean isFocusDelegatingContainer(View view) {
        CharSequence contentDescription = view.getContentDescription();
        return TextUtils.equals(contentDescription, RotaryConstants.ROTARY_FOCUS_DELEGATING_CONTAINER);
    }

    private static boolean focusOnDefaultFocusView(View root) {
        View defaultFocus = findDefaultFocusView(root);
        return requestFocus(defaultFocus);
    }

    private static boolean focusOnFocusedByDefaultView(View root) {
        View focusedByDefault = findFocusedByDefaultView(root);
        return requestFocus(focusedByDefault);
    }

    private static boolean focusOnImplicitDefaultFocusView(View root) {
        View implicitDefaultFocus = findImplicitDefaultFocusView(root);
        return requestFocus(implicitDefaultFocus);
    }

    private static boolean focusOnFirstRegularView(View root) {
        View focusedView = depthFirstSearch(root, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$-f0elFuTjnZZivfe4ZsiLnbp0hM
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$focusOnFirstRegularView$0((View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$5fadWWu2xGaldYGt0IC1S9p3lgM
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$focusOnFirstRegularView$1((View) obj);
            }
        });
        return focusedView != null;
    }

    static /* synthetic */ boolean lambda$focusOnFirstRegularView$0(View v) {
        return !isScrollableContainer(v) && canTakeFocus(v) && requestFocus(v);
    }

    static /* synthetic */ boolean lambda$focusOnFirstRegularView$1(View v) {
        return !v.isShown();
    }

    private static boolean focusOnScrollableContainer(View root) {
        View focusedView = depthFirstSearch(root, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$Ldp7f94iHvXljZsO3uGB_Yb6Ahg
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$focusOnScrollableContainer$2((View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$R0vM2cRe_LFvJ5NTZ-jlkzoFu8Y
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$focusOnScrollableContainer$3((View) obj);
            }
        });
        return requestFocus(focusedView);
    }

    static /* synthetic */ boolean lambda$focusOnScrollableContainer$2(View v) {
        return isScrollableContainer(v) && canTakeFocus(v);
    }

    static /* synthetic */ boolean lambda$focusOnScrollableContainer$3(View v) {
        return !v.isShown();
    }

    private static View findDefaultFocusView(View view) {
        if (!view.isShown()) {
            return null;
        }
        if (view instanceof FocusArea) {
            FocusArea focusArea = (FocusArea) view;
            View defaultFocus = focusArea.getDefaultFocusView();
            if (defaultFocus != null && canTakeFocus(defaultFocus)) {
                return defaultFocus;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                View defaultFocus2 = findDefaultFocusView(child);
                if (defaultFocus2 != null) {
                    return defaultFocus2;
                }
            }
        }
        return null;
    }

    static View findFocusedByDefaultView(View view) {
        return depthFirstSearch(view, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$LNDf1_V-9dEBj8w6o0TUT7CyWBs
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFocusedByDefaultView$4((View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$0lqA5Z0cQq8Y0OzUXUv4zAq6N5s
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFocusedByDefaultView$5((View) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$findFocusedByDefaultView$4(View v) {
        return v.isFocusedByDefault() && canTakeFocus(v);
    }

    static /* synthetic */ boolean lambda$findFocusedByDefaultView$5(View v) {
        return !v.isShown();
    }

    static View findImplicitDefaultFocusView(View view) {
        View rotaryContainer = findRotaryContainer(view);
        if (rotaryContainer == null) {
            return null;
        }
        View selectedItem = findFirstSelectedFocusableDescendant(rotaryContainer);
        if (selectedItem != null) {
            return selectedItem;
        }
        return findFirstFocusableDescendant(rotaryContainer);
    }

    static View findFirstFocusableDescendant(final View view) {
        return depthFirstSearch(view, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$1Qs0X9w1bt4i6TeuTWEnWLMTSXc
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFirstFocusableDescendant$6(view, (View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$JMj1s78NFBENZZ4ljNxTETdoCeE
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFirstFocusableDescendant$7((View) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$findFirstFocusableDescendant$6(View view, View v) {
        return v != view && canTakeFocus(v);
    }

    static /* synthetic */ boolean lambda$findFirstFocusableDescendant$7(View v) {
        return !v.isShown();
    }

    static View findFirstSelectedFocusableDescendant(final View view) {
        return depthFirstSearch(view, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$R1-2GIH1gXI5ngUmQF_Rxh1vIz0
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFirstSelectedFocusableDescendant$8(view, (View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$new_C-BFAf6QmATZgvCq7O8lUJA
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findFirstSelectedFocusableDescendant$9((View) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$findFirstSelectedFocusableDescendant$8(View view, View v) {
        return v != view && v.isSelected() && canTakeFocus(v);
    }

    static /* synthetic */ boolean lambda$findFirstSelectedFocusableDescendant$9(View v) {
        return !v.isShown();
    }

    private static View findRotaryContainer(View view) {
        return depthFirstSearch(view, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$y0h8NkJo-9VwOjtntyZrTbkJLxc
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.isRotaryContainer((View) obj);
            }
        }, new Predicate() { // from class: com.android.car.ui.utils.-$$Lambda$ViewUtils$dsMq7KwkD-jTJCP5CG17K8deF4c
            @Override // com.android.car.ui.utils.ViewUtils.Predicate
            public final boolean test(Object obj) {
                return ViewUtils.lambda$findRotaryContainer$11((View) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$findRotaryContainer$11(View v) {
        return !v.isShown();
    }

    private static View depthFirstSearch(View view, Predicate<View> targetPredicate, Predicate<View> skipPredicate) {
        if (skipPredicate != null && skipPredicate.test(view)) {
            return null;
        }
        if (targetPredicate.test(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                View target = depthFirstSearch(child, targetPredicate, skipPredicate);
                if (target != null) {
                    return target;
                }
            }
        }
        return null;
    }

    private static boolean canTakeFocus(View view) {
        boolean focusable = view.isFocusable() || isFocusDelegatingContainer(view);
        if (focusable && view.isEnabled() && view.isShown() && view.getWidth() > 0 && view.getHeight() > 0 && view.isAttachedToWindow() && !(view instanceof FocusParkingView)) {
            return !isScrollableContainer(view) || findFirstFocusableDescendant(view) == null;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void makeAllViewsUxRestricted(View view, boolean restricted) {
        if (view instanceof DrawableStateView) {
            if (sRestrictedState == null) {
                int androidStateUxRestricted = view.getResources().getIdentifier("state_ux_restricted", "attr", Utils.OS_PKG);
                if (androidStateUxRestricted == 0) {
                    sRestrictedState = new int[]{R.attr.state_ux_restricted};
                } else {
                    sRestrictedState = new int[]{R.attr.state_ux_restricted, androidStateUxRestricted};
                }
            }
            ((DrawableStateView) view).setExtraDrawableState(restricted ? sRestrictedState : null, null);
        }
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                makeAllViewsUxRestricted(vg.getChildAt(i), restricted);
            }
        }
    }
}
