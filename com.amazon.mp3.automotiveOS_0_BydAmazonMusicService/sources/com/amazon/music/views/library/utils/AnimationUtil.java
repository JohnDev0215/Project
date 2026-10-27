package com.amazon.music.views.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ&\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010JF\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J6\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J4\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010J*\u0010$\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"J&\u0010&\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001e0(J\u0016\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006,"}, d2 = {"Lcom/amazon/music/views/library/utils/AnimationUtil;", "", "()V", "createAnimationSet", "Landroid/view/animation/AnimationSet;", "shareInterpolator", "", "animations", "", "Landroid/view/animation/Animation;", "createFadeAnimation", "startAlpha", "", "endAlpha", "fillAfter", "duration", "", "createScaleToSelfAnimation", "startXScaleValue", "startYScaleValue", "endXScaleValue", "endYScaleValue", "pointOfXScaling", "pointOfYScaling", "createTranslateAnimation", "fromXDelta", "toXDelta", "fromYDelta", "toYDelta", "fadeInAnimation", "", "fadeImage", "Landroid/widget/ImageView;", "animatorListener", "Landroid/animation/AnimatorListenerAdapter;", "imageView", "fadeOutAnimation", "alpha", "fadeOutImage", "onAnimationEnd", "Lkotlin/Function0;", "panInAnimation", "layout", "Landroid/view/ViewGroup;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AnimationUtil {
    public static final AnimationUtil INSTANCE = new AnimationUtil();

    private AnimationUtil() {
    }

    public final void panInAnimation(ViewGroup layout, long duration) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        layout.startAnimation(INSTANCE.createScaleToSelfAnimation(1.0f, 1.0f, 1.1f, 1.1f, 0.5f, 0.5f, false, duration));
    }

    public final void fadeInAnimation(ImageView imageView, long duration) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        fadeInAnimation$default(this, imageView, 0.0f, 1.0f, duration, null, 16, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void fadeOutImage$default(AnimationUtil animationUtil, ImageView imageView, long j, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = new Function0<Unit>() { // from class: com.amazon.music.views.library.utils.AnimationUtil.fadeOutImage.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        animationUtil.fadeOutImage(imageView, j, function0);
    }

    public final void fadeOutImage(final ImageView imageView, long duration, final Function0<Unit> onAnimationEnd) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(onAnimationEnd, "onAnimationEnd");
        fadeOutAnimation(imageView, 0.0f, duration, new AnimatorListenerAdapter() { // from class: com.amazon.music.views.library.utils.AnimationUtil.fadeOutImage.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                imageView.setVisibility(8);
                onAnimationEnd.invoke();
            }
        });
    }

    static /* synthetic */ void fadeInAnimation$default(AnimationUtil animationUtil, ImageView imageView, float f, float f2, long j, AnimatorListenerAdapter animatorListenerAdapter, int i, Object obj) {
        if ((i & 16) != 0) {
            animatorListenerAdapter = null;
        }
        animationUtil.fadeInAnimation(imageView, f, f2, j, animatorListenerAdapter);
    }

    private final void fadeInAnimation(ImageView fadeImage, float startAlpha, float endAlpha, long duration, AnimatorListenerAdapter animatorListener) {
        fadeImage.setAlpha(startAlpha);
        fadeImage.setVisibility(0);
        fadeImage.animate().alpha(endAlpha).setDuration(duration).setListener(animatorListener);
    }

    public final void fadeOutAnimation(ImageView fadeImage, float alpha, long duration, AnimatorListenerAdapter animatorListener) {
        Intrinsics.checkNotNullParameter(fadeImage, "fadeImage");
        fadeImage.animate().alpha(alpha).setDuration(duration).setListener(animatorListener);
    }

    public final Animation createScaleToSelfAnimation(float startXScaleValue, float startYScaleValue, float endXScaleValue, float endYScaleValue, float pointOfXScaling, float pointOfYScaling, boolean fillAfter, long duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(startXScaleValue, endXScaleValue, startYScaleValue, endYScaleValue, 1, pointOfXScaling, 1, pointOfYScaling);
        scaleAnimation.setFillAfter(fillAfter);
        scaleAnimation.setDuration(duration);
        return scaleAnimation;
    }

    public final Animation createFadeAnimation(float startAlpha, float endAlpha, boolean fillAfter, long duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(startAlpha, endAlpha);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(fillAfter);
        return alphaAnimation;
    }

    public final AnimationSet createAnimationSet(boolean shareInterpolator, List<? extends Animation> animations) {
        Intrinsics.checkNotNullParameter(animations, "animations");
        AnimationSet animationSet = new AnimationSet(shareInterpolator);
        Iterator<T> it = animations.iterator();
        while (it.hasNext()) {
            animationSet.addAnimation((Animation) it.next());
        }
        return animationSet;
    }

    public final Animation createTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, boolean fillAfter, long duration) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillAfter(fillAfter);
        return translateAnimation;
    }
}
