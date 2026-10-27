package com.swmansion.rnscreens;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.Window;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import kotlin.Metadata;

/* JADX INFO: compiled from: ScreenWindowTraits.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/swmansion/rnscreens/ScreenWindowTraits$setColor$1", "Lcom/facebook/react/bridge/GuardedRunnable;", "runGuarded", "", "react-native-screens_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ScreenWindowTraits$setColor$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $animated;
    final /* synthetic */ Integer $color;
    final /* synthetic */ ReactContext $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScreenWindowTraits$setColor$1(ReactContext reactContext, Activity activity, Integer num, boolean z) {
        super(reactContext);
        this.$context = reactContext;
        this.$activity = activity;
        this.$color = num;
        this.$animated = z;
    }

    @Override // com.facebook.react.bridge.GuardedRunnable
    public void runGuarded() {
        final Window window = this.$activity.getWindow();
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(window.getStatusBarColor()), this.$color);
        valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.swmansion.rnscreens.ScreenWindowTraits$setColor$1$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenWindowTraits$setColor$1.m2770runGuarded$lambda0(window, valueAnimator);
            }
        });
        if (this.$animated) {
            valueAnimatorOfObject.setDuration(300L).setStartDelay(0L);
        } else {
            valueAnimatorOfObject.setDuration(0L).setStartDelay(300L);
        }
        valueAnimatorOfObject.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: runGuarded$lambda-0, reason: not valid java name */
    public static final void m2770runGuarded$lambda0(Window window, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        }
        window.setStatusBarColor(((Integer) animatedValue).intValue());
    }
}
