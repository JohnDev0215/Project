package com.amazon.mp3.util.extensions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\f\u0010\u0007\u001a\u00020\u0006*\u00020\u0000H\u0002\u001a\u0018\u0010\n\u001a\u00020\u0003*\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0000H\u0002\u001a\u001a\u0010\u0010\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e\u001a\n\u0010\u0011\u001a\u00020\u0003*\u00020\u0000\u001a\n\u0010\u0012\u001a\u00020\u0003*\u00020\u0000\u001a(\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0015¨\u0006\u0018"}, d2 = {"Landroid/view/View;", "Landroid/graphics/Rect;", "getAbsoluteRect", "", "removeFromParent", "(Landroid/view/View;)Lkotlin/Unit;", "", "isVisible", "Lkotlin/Function0;", "callback", "waitForLayout", "view", "", "visibility", "", "animationDuration", "fadeAndToggleViewVisibility", "fadeOut", "fadeIn", "initialView", "finalView", "", "finalAlpha", "crossFade", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "ViewExtensions")
public final class ViewExtensions {
    public static final Rect getAbsoluteRect(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return getAbsoluteRect(new Rect(), view);
    }

    public static final Unit removeFromParent(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return null;
        }
        viewGroup.removeView(view);
        return Unit.INSTANCE;
    }

    private static final boolean isVisible(View view) {
        if (!view.isShown()) {
            return false;
        }
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.intersect(new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels));
    }

    public static final void waitForLayout(final View view, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (isVisible(view)) {
            callback.invoke();
            return;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final ViewTreeObserver.OnDrawListener onDrawListener = new ViewTreeObserver.OnDrawListener() { // from class: com.amazon.mp3.util.extensions.ViewExtensions$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public final void onDraw() {
                ViewExtensions.m1665waitForLayout$lambda0(callback, booleanRef);
            }
        };
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnDrawListener(onDrawListener);
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.util.extensions.ViewExtensions$waitForLayout$1$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (booleanRef.element) {
                    view.getViewTreeObserver().removeOnDrawListener(onDrawListener);
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: waitForLayout$lambda-0, reason: not valid java name */
    public static final void m1665waitForLayout$lambda0(Function0 callback, Ref.BooleanRef didDrawView) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(didDrawView, "$didDrawView");
        callback.invoke();
        didDrawView.element = true;
    }

    private static final Rect getAbsoluteRect(Rect rect, View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        rect.set(i, iArr[1], view.getMeasuredWidth() + i, iArr[1] + view.getMeasuredHeight());
        return rect;
    }

    public static final void fadeAndToggleViewVisibility(final View view, int i, long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.clearAnimation();
        if (i != 0) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
            viewPropertyAnimatorAnimate.alpha(0.0f);
            viewPropertyAnimatorAnimate.setDuration(j);
            viewPropertyAnimatorAnimate.setInterpolator(new LinearInterpolator());
            viewPropertyAnimatorAnimate.setListener(new AnimatorListenerAdapter() { // from class: com.amazon.mp3.util.extensions.ViewExtensions$fadeAndToggleViewVisibility$1$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    super.onAnimationEnd(animation);
                    view.setVisibility(4);
                }
            }).start();
            return;
        }
        view.setVisibility(0);
        view.setAlpha(1.0f);
        ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view.animate();
        viewPropertyAnimatorAnimate2.alpha(1.0f);
        viewPropertyAnimatorAnimate2.setDuration(j);
        viewPropertyAnimatorAnimate2.setInterpolator(new LinearInterpolator());
        viewPropertyAnimatorAnimate2.setListener(null).start();
    }

    public static final void fadeOut(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.clearAnimation();
        view.animate().alpha(0.0f).setDuration(200L).setInterpolator(new DecelerateInterpolator());
    }

    public static final void fadeIn(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
        view.clearAnimation();
        view.animate().alpha(1.0f).setInterpolator(new DecelerateInterpolator()).setDuration(200L);
    }

    public static final void crossFade(View initialView, View finalView, long j, float f) {
        Intrinsics.checkNotNullParameter(initialView, "initialView");
        Intrinsics.checkNotNullParameter(finalView, "finalView");
        float alpha = finalView.getAlpha();
        finalView.clearAnimation();
        finalView.setAlpha(alpha);
        float alpha2 = initialView.getAlpha();
        initialView.clearAnimation();
        initialView.setAlpha(alpha2);
        finalView.setAlpha(0.0f);
        finalView.setVisibility(0);
        finalView.animate().alpha(f).setDuration(j);
        initialView.animate().alpha(0.0f).setDuration(j);
    }
}
