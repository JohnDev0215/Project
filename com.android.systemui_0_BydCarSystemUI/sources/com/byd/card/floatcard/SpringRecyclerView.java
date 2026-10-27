package com.byd.card.floatcard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class SpringRecyclerView extends RecyclerView {
    private static final int DEF_FLING_BACK_ANIM_DURATION = 300;
    private static final int DEF_RELEASE_BACK_ANIM_DURATION = 300;
    private static final int INVALID_POINTER = -1;
    private static final int STATE_DRAG_BOTTOM_OR_RIGHT = 2;
    private static final int STATE_DRAG_TOP_OR_LEFT = 1;
    private static final int STATE_FLING = 4;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_SPRING_BACK = 3;
    private Interpolator flingBackAnimInterpolator;
    private int mActivePointerId;
    private boolean mEnableSpringEffectWhenDrag;
    private boolean mEnableSpringEffectWhenFling;
    private int mFlingBackAnimDuration;
    private float mFrom;
    private float mLastMotionPos;
    private float mOffset;
    private int mOrientation;
    private int mReleaseBackAnimDuration;
    private int mState;
    private final int mTouchSlop;
    private Interpolator releaseBackAnimInterpolator;
    private Animation springAnimation;

    public SpringRecyclerView(Context context) {
        this(context, null);
    }

    public SpringRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mState = 0;
        this.mActivePointerId = -1;
        setOverScrollMode(0);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SpringRecyclerView);
        this.mReleaseBackAnimDuration = typedArrayObtainStyledAttributes.getInt(3, 300);
        this.mFlingBackAnimDuration = typedArrayObtainStyledAttributes.getInt(2, 300);
        this.mEnableSpringEffectWhenDrag = typedArrayObtainStyledAttributes.getBoolean(0, true);
        this.mEnableSpringEffectWhenFling = typedArrayObtainStyledAttributes.getBoolean(1, true);
        typedArrayObtainStyledAttributes.recycle();
        initAnimation();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.mEnableSpringEffectWhenDrag && onInterceptTouchEventInternal(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean onInterceptTouchEventInternal(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.floatcard.SpringRecyclerView.onInterceptTouchEventInternal(android.view.MotionEvent):boolean");
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mEnableSpringEffectWhenDrag && onTouchEventInternal(ev)) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean onTouchEventInternal(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.floatcard.SpringRecyclerView.onTouchEventInternal(android.view.MotionEvent):boolean");
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mState == 0) {
            super.draw(canvas);
            return;
        }
        int iSave = canvas.save();
        if (this.mOrientation == 1) {
            canvas.scale(1.0f, ((Math.abs(this.mOffset) / getHeight()) * 0.3f) + 1.0f, 0.0f, this.mOffset >= 0.0f ? 0.0f : r1 + getScrollY());
        } else {
            canvas.scale(((Math.abs(this.mOffset) / getWidth()) * 0.3f) + 1.0f, 1.0f, this.mOffset >= 0.0f ? 0.0f : r1 + getScrollX(), 0.0f);
        }
        super.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    private void initAnimation() {
        this.springAnimation = new Animation() { // from class: com.byd.card.floatcard.SpringRecyclerView.1
            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                SpringRecyclerView springRecyclerView = SpringRecyclerView.this;
                springRecyclerView.mOffset = springRecyclerView.mFrom * interpolatedTime;
                if (hasEnded()) {
                    SpringRecyclerView.this.mOffset = 0.0f;
                    SpringRecyclerView.this.setState(0);
                }
                SpringRecyclerView.this.invalidate();
            }
        };
        this.releaseBackAnimInterpolator = new Interpolator() { // from class: com.byd.card.floatcard.SpringRecyclerView.2
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float v) {
                return (float) Math.cos((((double) v) * 3.141592653589793d) / 2.0d);
            }
        };
        this.flingBackAnimInterpolator = new Interpolator() { // from class: com.byd.card.floatcard.SpringRecyclerView.3
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float v) {
                return (float) Math.sin(((double) v) * 3.141592653589793d);
            }
        };
    }

    private void startReleaseAnimation() {
        this.springAnimation.setDuration(this.mReleaseBackAnimDuration);
        this.springAnimation.setInterpolator(this.releaseBackAnimInterpolator);
        startAnimation(this.springAnimation);
    }

    private void startFlingAnimation() {
        this.springAnimation.setDuration(this.mFlingBackAnimDuration);
        this.springAnimation.setInterpolator(this.flingBackAnimInterpolator);
        startAnimation(this.springAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int newState) {
        if (this.mState != newState) {
            this.mState = newState;
        }
    }

    private boolean isDragged() {
        int i = this.mState;
        return i == 1 || i == 2;
    }

    private boolean isDraggedTopOrLeft() {
        return this.mState == 1;
    }

    private boolean isDraggedBottomOrRight() {
        return this.mState == 2;
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int actionIndex = ev.getActionIndex();
        if (ev.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = ev.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager != null) {
            this.mOrientation = !layoutManager.canScrollHorizontally() ? 1 : 0;
        }
    }

    public void setEnableSpringEffectWhenDrag(boolean enable) {
        this.mEnableSpringEffectWhenDrag = enable;
    }

    public void setEnableSpringEffectWhenFling(boolean enable) {
        this.mEnableSpringEffectWhenFling = enable;
    }

    public void setReleaseBackAnimDuration(int duration) {
        this.mReleaseBackAnimDuration = duration;
    }

    public void setFlingBackAnimDuration(int duration) {
        this.mFlingBackAnimDuration = duration;
    }
}
