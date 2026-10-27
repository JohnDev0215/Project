package com.byd.viewsup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.byd.viewsup.ListenerStubs;
import com.byd.viewsup.adapters.IOverScrollDecoratorAdapter;
import com.byd.viewsup.adapters.StaticOverScrollDecorAdapter;

/* JADX INFO: loaded from: classes5.dex */
public abstract class OverScrollBounceEffectDecoratorBase implements IOverScrollDecor, View.OnTouchListener {
    public static final float DEFAULT_DECELERATE_FACTOR = -72000.0f;
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1.0f;
    public static final float DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3.0f;
    public static final int MAX_M_VELOCITY = 1000;
    public static final String TAG = "OverScrollDecor";
    protected final BounceBackState mBounceBackState;
    protected IDecoratorState mCurrentState;
    protected Interpolator mCustomInterpolator;
    protected final IdleState mIdleState;
    protected long mMaxBounceBackDurationMs;
    private float mMaxDistance;
    protected final OverScrollingState mOverScrollingState;
    protected float mVelocity;
    protected final IOverScrollDecoratorAdapter mViewAdapter;
    protected final long minBounceBackDurationMs = 100;
    protected final OverScrollStartAttributes mStartAttr = new OverScrollStartAttributes();
    protected IOverScrollStateListener mStateListener = new ListenerStubs.OverScrollStateListenerStub();
    protected IOverScrollUpdateListener mUpdateListener = new ListenerStubs.OverScrollUpdateListenerStub();

    protected interface IDecoratorState {
        int getStateId();

        void handleEntryTransition(IDecoratorState iDecoratorState);

        boolean handleMoveTouchEvent(MotionEvent motionEvent);

        boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent);
    }

    protected abstract AnimationAttributes createAnimationAttributes();

    protected abstract MotionAttributes createMotionAttributes();

    protected abstract void translateView(View view, float f);

    protected abstract void translateViewAndEvent(View view, float f, MotionEvent motionEvent);

    protected static abstract class MotionAttributes {
        public float mAbsOffset;
        public float mDeltaOffset;
        public boolean mDir;

        protected abstract boolean init(View view, MotionEvent motionEvent);

        protected MotionAttributes() {
        }
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public void setCustomInterpolator(Interpolator interpolator) {
        this.mCustomInterpolator = interpolator;
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public void setCanRebound(boolean z) {
        this.mViewAdapter.setCanRebound(z);
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public void setMaxDistance(float f) {
        this.mMaxDistance = f;
    }

    protected static class OverScrollStartAttributes {
        protected float mAbsOffset;
        protected boolean mDir;
        protected int mPointerId;

        protected OverScrollStartAttributes() {
        }
    }

    protected static abstract class AnimationAttributes {
        public float mAbsOffset;
        public float mMaxOffset;
        public Property<View, Float> mProperty;

        protected abstract void init(View view);

        protected AnimationAttributes() {
        }
    }

    protected class IdleState implements IDecoratorState {
        final MotionAttributes mMoveAttr;

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return 0;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public IdleState() {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            if (!this.mMoveAttr.init(view, motionEvent)) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                Object parent = view.getParent();
                if (parent instanceof BydScrollerSupportedFrameLayout) {
                    ((View) parent).invalidate();
                    break;
                }
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
                i++;
            }
            if ((!this.mMoveAttr.mDir || !OverScrollBounceEffectDecoratorBase.this.mViewAdapter.preConditionStart() || !OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteStart()) && (this.mMoveAttr.mDir || !OverScrollBounceEffectDecoratorBase.this.mViewAdapter.preConditionEnd() || !OverScrollBounceEffectDecoratorBase.this.mViewAdapter.isInAbsoluteEnd())) {
                return false;
            }
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId = motionEvent.getPointerId(0);
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset = this.mMoveAttr.mAbsOffset;
            OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir = this.mMoveAttr.mDir;
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mOverScrollingState);
            return OverScrollBounceEffectDecoratorBase.this.mOverScrollingState.handleMoveTouchEvent(motionEvent);
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
        }
    }

    protected class OverScrollingState implements IDecoratorState {
        int mCurrDragState;
        final MotionAttributes mMoveAttr;
        protected final float mTouchDragRatioBck;
        protected final float mTouchDragRatioFwd;

        public OverScrollingState(float f, float f2) {
            this.mMoveAttr = OverScrollBounceEffectDecoratorBase.this.createMotionAttributes();
            this.mTouchDragRatioFwd = f;
            this.mTouchDragRatioBck = f2;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return this.mCurrDragState;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            if (OverScrollBounceEffectDecoratorBase.this.mStartAttr.mPointerId != motionEvent.getPointerId(0)) {
                OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
                overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mBounceBackState);
                return true;
            }
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            if (!this.mMoveAttr.init(view, motionEvent)) {
                return true;
            }
            float f = this.mMoveAttr.mDeltaOffset / (this.mMoveAttr.mDir == OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? this.mTouchDragRatioFwd : this.mTouchDragRatioBck);
            if (Math.abs(this.mMoveAttr.mAbsOffset) > OverScrollBounceEffectDecoratorBase.this.mMaxDistance - 1.0f) {
                f = 0.0f;
            }
            float f2 = this.mMoveAttr.mAbsOffset + f;
            if ((OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir && !this.mMoveAttr.mDir && f2 <= OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset) || (!OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir && this.mMoveAttr.mDir && f2 >= OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset)) {
                OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase2 = OverScrollBounceEffectDecoratorBase.this;
                overScrollBounceEffectDecoratorBase2.translateViewAndEvent(view, overScrollBounceEffectDecoratorBase2.mStartAttr.mAbsOffset, motionEvent);
                OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, 0.0f);
                OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase3 = OverScrollBounceEffectDecoratorBase.this;
                overScrollBounceEffectDecoratorBase3.issueStateTransition(overScrollBounceEffectDecoratorBase3.mIdleState);
                return true;
            }
            if (view.getParent() != null) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
            }
            long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
            if (eventTime > 0) {
                OverScrollBounceEffectDecoratorBase.this.mVelocity = (f * 1000.0f) / eventTime;
            }
            if (OverScrollBounceEffectDecoratorBase.this.mVelocity > 1000.0f) {
                OverScrollBounceEffectDecoratorBase.this.mVelocity = 1000.0f;
            }
            if (OverScrollBounceEffectDecoratorBase.this.mVelocity < -1000.0f) {
                OverScrollBounceEffectDecoratorBase.this.mVelocity = -1000.0f;
            }
            OverScrollBounceEffectDecoratorBase.this.translateView(view, f2);
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, this.mCurrDragState, f2);
            return true;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mBounceBackState);
            return false;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            this.mCurrDragState = OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir ? 1 : 2;
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
        }
    }

    protected class BounceBackState implements IDecoratorState, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        protected final AnimationAttributes mAnimAttributes;
        protected final Interpolator mBounceBackInterpolator = new DecelerateInterpolator();
        protected float mDecelerateFactor;
        protected final float mDoubleDecelerateFactor;

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public int getStateId() {
            return 3;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleMoveTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public boolean handleUpOrCancelTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public BounceBackState(float f) {
            this.mDecelerateFactor = f;
            this.mDoubleDecelerateFactor = f * 2.0f;
            this.mAnimAttributes = OverScrollBounceEffectDecoratorBase.this.createAnimationAttributes();
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.IDecoratorState
        public void handleEntryTransition(IDecoratorState iDecoratorState) {
            OverScrollBounceEffectDecoratorBase.this.mStateListener.onOverScrollStateChange(OverScrollBounceEffectDecoratorBase.this, iDecoratorState.getStateId(), getStateId());
            Animator animatorCreateAnimator = createAnimator();
            animatorCreateAnimator.addListener(this);
            animatorCreateAnimator.start();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            OverScrollBounceEffectDecoratorBase overScrollBounceEffectDecoratorBase = OverScrollBounceEffectDecoratorBase.this;
            overScrollBounceEffectDecoratorBase.issueStateTransition(overScrollBounceEffectDecoratorBase.mIdleState);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            OverScrollBounceEffectDecoratorBase.this.mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, 3, ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }

        protected Animator createAnimator() {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            this.mAnimAttributes.init(view);
            if (OverScrollBounceEffectDecoratorBase.this.mVelocity == 0.0f || ((OverScrollBounceEffectDecoratorBase.this.mVelocity < 0.0f && OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir) || (OverScrollBounceEffectDecoratorBase.this.mVelocity > 0.0f && !OverScrollBounceEffectDecoratorBase.this.mStartAttr.mDir))) {
                return createBounceBackAnimator(this.mAnimAttributes.mAbsOffset);
            }
            float fMax = Math.max(OverScrollBounceEffectDecoratorBase.this.mVelocity, -5000.0f);
            long jRound = Math.round(Math.abs((fMax / this.mDecelerateFactor) * 1000.0f));
            float f = this.mAnimAttributes.mAbsOffset + ((fMax * fMax) / this.mDoubleDecelerateFactor);
            ObjectAnimator objectAnimatorCreateSlowdownAnimator = createSlowdownAnimator(view, jRound, f);
            ObjectAnimator objectAnimatorCreateBounceBackAnimator = createBounceBackAnimator(f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorCreateSlowdownAnimator, objectAnimatorCreateBounceBackAnimator);
            return animatorSet;
        }

        protected ObjectAnimator createSlowdownAnimator(View view, long j, float f) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.mAnimAttributes.mProperty, f);
            objectAnimatorOfFloat.setDuration(j);
            objectAnimatorOfFloat.setInterpolator(this.mBounceBackInterpolator);
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }

        protected ObjectAnimator createBounceBackAnimator(float f) {
            View view = OverScrollBounceEffectDecoratorBase.this.mViewAdapter.getView();
            float fAbs = (Math.abs(f) / OverScrollBounceEffectDecoratorBase.this.mMaxDistance) * OverScrollBounceEffectDecoratorBase.this.mMaxBounceBackDurationMs;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, this.mAnimAttributes.mProperty, OverScrollBounceEffectDecoratorBase.this.mStartAttr.mAbsOffset);
            objectAnimatorOfFloat.setDuration(Math.max((int) fAbs, 100L));
            if (OverScrollBounceEffectDecoratorBase.this.mCustomInterpolator != null) {
                objectAnimatorOfFloat.setInterpolator(OverScrollBounceEffectDecoratorBase.this.mCustomInterpolator);
            } else {
                objectAnimatorOfFloat.setInterpolator(this.mBounceBackInterpolator);
            }
            objectAnimatorOfFloat.addUpdateListener(this);
            return objectAnimatorOfFloat;
        }
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public void setMaxDuration(long j) {
        this.mMaxBounceBackDurationMs = j;
    }

    public OverScrollBounceEffectDecoratorBase(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3, float f4, long j, float f5) {
        this.mMaxDistance = -1.0f;
        this.mViewAdapter = iOverScrollDecoratorAdapter;
        this.mMaxBounceBackDurationMs = j;
        this.mMaxDistance = f4;
        this.mBounceBackState = new BounceBackState(f * f5);
        this.mOverScrollingState = new OverScrollingState(f2 * f5, f3);
        IdleState idleState = new IdleState();
        this.mIdleState = idleState;
        this.mCurrentState = idleState;
        attach();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                return this.mCurrentState.handleMoveTouchEvent(motionEvent);
            }
            if (action != 3) {
                return this.mViewAdapter instanceof StaticOverScrollDecorAdapter;
            }
        }
        return this.mCurrentState.handleUpOrCancelTouchEvent(motionEvent);
    }

    @Override // com.byd.viewsup.OverScrollUpdateListener
    public void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener) {
        if (iOverScrollStateListener == null) {
            iOverScrollStateListener = new ListenerStubs.OverScrollStateListenerStub();
        }
        this.mStateListener = iOverScrollStateListener;
    }

    @Override // com.byd.viewsup.OverScrollUpdateListener
    public void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener) {
        if (iOverScrollUpdateListener == null) {
            iOverScrollUpdateListener = new ListenerStubs.OverScrollUpdateListenerStub();
        }
        this.mUpdateListener = iOverScrollUpdateListener;
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public int getCurrentState() {
        return this.mCurrentState.getStateId();
    }

    @Override // com.byd.viewsup.IOverScrollDecor, com.byd.viewsup.adapters.IOverScroller
    public View getView() {
        return this.mViewAdapter.getView();
    }

    protected void issueStateTransition(IDecoratorState iDecoratorState) {
        IDecoratorState iDecoratorState2 = this.mCurrentState;
        this.mCurrentState = iDecoratorState;
        iDecoratorState.handleEntryTransition(iDecoratorState2);
    }

    protected void attach() {
        getView().setOnTouchListener(this);
        getView().setOverScrollMode(2);
    }

    @Override // com.byd.viewsup.IOverScrollDecor
    public void detach() {
        if (this.mCurrentState != this.mIdleState) {
            Log.w(TAG, "Decorator detached while over-scroll is in effect. You might want to add a precondition of that getCurrentState()==STATE_IDLE, first.");
        }
        getView().setOnTouchListener(null);
        getView().setOverScrollMode(0);
    }
}
