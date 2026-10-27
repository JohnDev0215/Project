package com.android.launcher3.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.Log;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.launcher3.config.FeatureFlags;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AnimatorPlaybackController implements ValueAnimator.AnimatorUpdateListener {
    private static boolean DEBUG = false;
    private static final String TAG = "AnimatorPlaybackCtrler";
    protected final AnimatorSet mAnim;
    private final ValueAnimator mAnimationPlayer;
    protected float mCurrentFraction;
    private final long mDuration;
    private Runnable mEndAction;
    private OnAnimationEndDispatcher mEndListener;
    protected Runnable mOnCancelRunnable;
    private DynamicAnimation.OnAnimationEndListener mSpringEndListener;
    private Set<SpringAnimation> mSprings;
    protected boolean mTargetCancelled = false;
    private boolean mSkipToEnd = false;

    public abstract void setPlayFraction(float fraction);

    public static AnimatorPlaybackController wrap(AnimatorSet anim, long duration) {
        return wrap(anim, duration, null);
    }

    public static AnimatorPlaybackController wrap(AnimatorSet anim, long duration, Runnable onCancelRunnable) {
        return new AnimatorPlaybackControllerVL(anim, duration, onCancelRunnable);
    }

    protected AnimatorPlaybackController(AnimatorSet anim, long duration, Runnable onCancelRunnable) {
        this.mAnim = anim;
        this.mDuration = duration;
        this.mOnCancelRunnable = onCancelRunnable;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnimationPlayer = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setInterpolator(Interpolators.LINEAR);
        OnAnimationEndDispatcher onAnimationEndDispatcher = new OnAnimationEndDispatcher();
        this.mEndListener = onAnimationEndDispatcher;
        valueAnimatorOfFloat.addListener(onAnimationEndDispatcher);
        valueAnimatorOfFloat.addUpdateListener(this);
        anim.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.anim.AnimatorPlaybackController.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                AnimatorPlaybackController.this.mTargetCancelled = true;
                if (AnimatorPlaybackController.this.mOnCancelRunnable != null) {
                    AnimatorPlaybackController.this.mOnCancelRunnable.run();
                    AnimatorPlaybackController.this.mOnCancelRunnable = null;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                AnimatorPlaybackController.this.mTargetCancelled = false;
                AnimatorPlaybackController.this.mOnCancelRunnable = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                AnimatorPlaybackController.this.mTargetCancelled = false;
            }
        });
        this.mSprings = new HashSet();
        this.mSpringEndListener = new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.launcher3.anim.-$$Lambda$AnimatorPlaybackController$xtcD-5NTwC_Ljnx9W8GqqW5bff8
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                this.f$0.lambda$new$0$AnimatorPlaybackController(dynamicAnimation, z, f, f2);
            }
        };
    }

    public /* synthetic */ void lambda$new$0$AnimatorPlaybackController(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (z) {
            this.mEndListener.onAnimationCancel(this.mAnimationPlayer);
        } else {
            this.mEndListener.onAnimationEnd(this.mAnimationPlayer);
        }
    }

    public AnimatorSet getTarget() {
        return this.mAnim;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public TimeInterpolator getInterpolator() {
        return this.mAnim.getInterpolator() != null ? this.mAnim.getInterpolator() : Interpolators.LINEAR;
    }

    public void start() {
        this.mAnimationPlayer.setFloatValues(this.mCurrentFraction, 1.0f);
        this.mAnimationPlayer.setDuration(clampDuration(1.0f - this.mCurrentFraction));
        this.mAnimationPlayer.start();
    }

    public void reverse() {
        this.mAnimationPlayer.setFloatValues(this.mCurrentFraction, 0.0f);
        this.mAnimationPlayer.setDuration(clampDuration(this.mCurrentFraction));
        this.mAnimationPlayer.start();
    }

    public void pause() {
        this.mAnimationPlayer.cancel();
    }

    public ValueAnimator getAnimationPlayer() {
        return this.mAnimationPlayer;
    }

    public float getProgressFraction() {
        return this.mCurrentFraction;
    }

    public float getInterpolatedProgress() {
        return getInterpolator().getInterpolation(this.mCurrentFraction);
    }

    public void setEndAction(Runnable runnable) {
        this.mEndAction = runnable;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        setPlayFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    protected long clampDuration(float fraction) {
        long j = this.mDuration;
        float f = j * fraction;
        if (f <= 0.0f) {
            return 0L;
        }
        return Math.min((long) f, j);
    }

    public void dispatchOnStartWithVelocity(float end, float velocity) {
        if (!FeatureFlags.QUICKSTEP_SPRINGS.get()) {
            dispatchOnStart();
            return;
        }
        if (DEBUG) {
            Log.d(TAG, "dispatchOnStartWithVelocity#end=" + end + ", velocity=" + velocity);
        }
        for (Animator animator : this.mAnim.getChildAnimations()) {
            if (animator instanceof SpringObjectAnimator) {
                if (DEBUG) {
                    Log.d(TAG, "Found springAnimator=" + animator);
                }
                SpringObjectAnimator springObjectAnimator = (SpringObjectAnimator) animator;
                this.mSprings.add(springObjectAnimator.getSpring());
                springObjectAnimator.startSpring(end, velocity, this.mSpringEndListener);
            }
        }
        dispatchOnStart();
    }

    public void dispatchOnStart() {
        dispatchOnStartRecursively(this.mAnim);
    }

    private void dispatchOnStartRecursively(Animator animator) {
        List listNonNullList;
        if (animator instanceof SpringObjectAnimator) {
            listNonNullList = nonNullList(((SpringObjectAnimator) animator).getObjectAnimatorListeners());
        } else {
            listNonNullList = nonNullList(animator.getListeners());
        }
        Iterator it = listNonNullList.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationStart(animator);
        }
        if (animator instanceof AnimatorSet) {
            Iterator it2 = nonNullList(((AnimatorSet) animator).getChildAnimations()).iterator();
            while (it2.hasNext()) {
                dispatchOnStartRecursively((Animator) it2.next());
            }
        }
    }

    public void dispatchOnCancel() {
        dispatchOnCancelRecursively(this.mAnim);
    }

    private void dispatchOnCancelRecursively(Animator animator) {
        Iterator it = nonNullList(animator.getListeners()).iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationCancel(animator);
        }
        if (animator instanceof AnimatorSet) {
            Iterator it2 = nonNullList(((AnimatorSet) animator).getChildAnimations()).iterator();
            while (it2.hasNext()) {
                dispatchOnCancelRecursively((Animator) it2.next());
            }
        }
    }

    public void dispatchSetInterpolator(TimeInterpolator interpolator) {
        dispatchSetInterpolatorRecursively(this.mAnim, interpolator);
    }

    private void dispatchSetInterpolatorRecursively(Animator anim, TimeInterpolator interpolator) {
        anim.setInterpolator(interpolator);
        if (anim instanceof AnimatorSet) {
            Iterator it = nonNullList(((AnimatorSet) anim).getChildAnimations()).iterator();
            while (it.hasNext()) {
                dispatchSetInterpolatorRecursively((Animator) it.next(), interpolator);
            }
        }
    }

    public void setOnCancelRunnable(Runnable runnable) {
        this.mOnCancelRunnable = runnable;
    }

    public Runnable getOnCancelRunnable() {
        return this.mOnCancelRunnable;
    }

    public void skipToEnd() {
        this.mSkipToEnd = true;
        for (SpringAnimation springAnimation : this.mSprings) {
            if (springAnimation.canSkipToEnd()) {
                springAnimation.skipToEnd();
            }
        }
        this.mAnimationPlayer.end();
        this.mSkipToEnd = false;
    }

    public static class AnimatorPlaybackControllerVL extends AnimatorPlaybackController {
        private final ValueAnimator[] mChildAnimations;

        private AnimatorPlaybackControllerVL(AnimatorSet anim, long duration, Runnable onCancelRunnable) {
            super(anim, duration, onCancelRunnable);
            ArrayList<ValueAnimator> arrayList = new ArrayList<>();
            getAnimationsRecur(this.mAnim, arrayList);
            this.mChildAnimations = (ValueAnimator[]) arrayList.toArray(new ValueAnimator[arrayList.size()]);
        }

        private void getAnimationsRecur(AnimatorSet anim, ArrayList<ValueAnimator> out) {
            long duration = anim.getDuration();
            TimeInterpolator interpolator = anim.getInterpolator();
            for (Animator animator : anim.getChildAnimations()) {
                if (duration > 0) {
                    animator.setDuration(duration);
                }
                if (interpolator != null) {
                    animator.setInterpolator(interpolator);
                }
                if (animator instanceof ValueAnimator) {
                    out.add((ValueAnimator) animator);
                } else if (animator instanceof AnimatorSet) {
                    getAnimationsRecur((AnimatorSet) animator, out);
                } else {
                    throw new RuntimeException("Unknown animation type " + animator);
                }
            }
        }

        @Override // com.android.launcher3.anim.AnimatorPlaybackController
        public void setPlayFraction(float fraction) {
            this.mCurrentFraction = fraction;
            if (this.mTargetCancelled) {
                return;
            }
            long jClampDuration = clampDuration(fraction);
            for (ValueAnimator valueAnimator : this.mChildAnimations) {
                valueAnimator.setCurrentPlayTime(Math.min(jClampDuration, valueAnimator.getDuration()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnySpringRunning() {
        Iterator<SpringAnimation> it = this.mSprings.iterator();
        while (it.hasNext()) {
            if (it.next().isRunning()) {
                return true;
            }
        }
        return false;
    }

    private class OnAnimationEndDispatcher extends AnimationSuccessListener {
        boolean mAnimatorDone;
        boolean mDispatched;
        boolean mSpringsDone;

        private OnAnimationEndDispatcher() {
            this.mAnimatorDone = false;
            this.mSpringsDone = false;
            this.mDispatched = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
            this.mCancelled = false;
            this.mDispatched = false;
        }

        @Override // com.android.launcher3.anim.AnimationSuccessListener
        public void onAnimationSuccess(Animator animator) {
            if (AnimatorPlaybackController.this.mSprings.isEmpty()) {
                this.mAnimatorDone = true;
                this.mSpringsDone = true;
            }
            if (AnimatorPlaybackController.this.isAnySpringRunning()) {
                this.mAnimatorDone = true;
            } else {
                this.mSpringsDone = true;
            }
            if (this.mDispatched) {
                return;
            }
            if (AnimatorPlaybackController.this.mSkipToEnd || (this.mAnimatorDone && this.mSpringsDone)) {
                dispatchOnEndRecursively(AnimatorPlaybackController.this.mAnim);
                if (AnimatorPlaybackController.this.mEndAction != null) {
                    AnimatorPlaybackController.this.mEndAction.run();
                }
                this.mDispatched = true;
            }
        }

        private void dispatchOnEndRecursively(Animator animator) {
            Iterator it = AnimatorPlaybackController.nonNullList(animator.getListeners()).iterator();
            while (it.hasNext()) {
                ((Animator.AnimatorListener) it.next()).onAnimationEnd(animator);
            }
            if (animator instanceof AnimatorSet) {
                Iterator it2 = AnimatorPlaybackController.nonNullList(((AnimatorSet) animator).getChildAnimations()).iterator();
                while (it2.hasNext()) {
                    dispatchOnEndRecursively((Animator) it2.next());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> nonNullList(ArrayList<T> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
