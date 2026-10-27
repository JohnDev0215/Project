package com.android.launcher3.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.CancellationSignal;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.android.launcher3.InsettableFrameLayout;
import com.android.launcher3.Utilities;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class FloatingIconView extends FrameLayout implements Animator.AnimatorListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final int FADE_DURATION_MS = 200;
    public static final float SHAPE_PROGRESS_DURATION = 0.1f;
    private static final String TAG = "FloatingIconView";
    private Drawable mBadge;
    private ClipIconView mClipIconView;
    private Context mContext;
    private Runnable mEndRunnable;
    private AnimatorSet mFadeAnimatorSet;
    private Runnable mFastFinishRunnable;
    private final Rect mFinalDrawableBounds;
    private boolean mIsOpening;
    private final boolean mIsRtl;
    private boolean mIsVerticalBarLayout;
    private CancellationSignal mLoadIconSignal;
    private Runnable mOnTargetChangeRunnable;
    private View mOriginalIcon;
    private RectF mPositionOut;
    private static final RectF sTmpRectF = new RectF();
    private static final Object[] sTmpObjArray = new Object[1];

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
    }

    public FloatingIconView(Context context) {
        this(context, null);
    }

    public FloatingIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIsVerticalBarLayout = false;
        this.mFinalDrawableBounds = new Rect();
        this.mContext = context;
        this.mIsRtl = false;
        ClipIconView clipIconView = new ClipIconView(context, attrs);
        this.mClipIconView = clipIconView;
        addView(clipIconView);
        setWillNotDraw(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsOpening) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        super.onDetachedFromWindow();
    }

    public void update(RectF rect, float alpha, float progress, float shapeProgressStart, float cornerRadius, boolean isOpening, Rect windowTargetBounds) {
        setAlpha(alpha);
        this.mClipIconView.update(rect, progress, shapeProgressStart, cornerRadius, isOpening, this, this.mIsVerticalBarLayout, windowTargetBounds);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        CancellationSignal cancellationSignal = this.mLoadIconSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        Runnable runnable = this.mEndRunnable;
        if (runnable != null) {
            runnable.run();
        } else {
            this.mClipIconView.endReveal();
        }
    }

    private void updatePosition(RectF pos, InsettableFrameLayout.LayoutParams lp) {
        int marginStart;
        this.mPositionOut.set(pos);
        lp.ignoreInsets = true;
        lp.topMargin = Math.round(pos.top);
        if (this.mIsRtl) {
            lp.setMarginStart(Math.round(1920.0f - pos.right));
        } else {
            lp.setMarginStart(Math.round(pos.left));
        }
        if (this.mIsRtl) {
            marginStart = (1920 - lp.getMarginStart()) - lp.width;
        } else {
            marginStart = lp.leftMargin;
        }
        layout(marginStart, lp.topMargin, lp.width + marginStart, lp.topMargin + lp.height);
    }

    private void matchPositionOf(Context context, View v, boolean isOpening, RectF positionOut, int width, int height) {
        getLocationBoundsForView(context, v, isOpening, positionOut, width, height);
        String str = TAG;
        Log.e(str, "positionOut:" + positionOut.toString());
        InsettableFrameLayout.LayoutParams layoutParams = new InsettableFrameLayout.LayoutParams(Math.round(positionOut.width()), Math.round(positionOut.height()));
        updatePosition(positionOut, layoutParams);
        setLayoutParams(layoutParams);
        Log.e(str, "lp.width:" + layoutParams.width + ", lp.height:" + layoutParams.height);
        this.mClipIconView.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
    }

    private static void getLocationBoundsForView(Context context, View v, boolean isOpening, RectF outRect, int width, int height) {
        getLocationBoundsForView(context, v, isOpening, outRect, new Rect(), width, height);
    }

    public static void getLocationBoundsForView(Context context, View v, boolean isOpening, RectF outRect, Rect outViewBounds, int width, int height) {
        if (v == null) {
            return;
        }
        outViewBounds.set(0, 0, v.getWidth(), v.getHeight());
        outRect.set(v.getX(), v.getY(), v.getX() + Utilities.dp2Px(context, width), v.getY() + Utilities.dp2Px(context, height));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Drawable drawable = this.mBadge;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    private void setIcon(View view, int iconOffset, float x, float y) {
        InsettableFrameLayout.LayoutParams layoutParams = (InsettableFrameLayout.LayoutParams) getLayoutParams();
        this.mBadge = view.getBackground();
        this.mClipIconView.setIcon(view.getBackground(), iconOffset, layoutParams, this.mIsOpening, this.mIsVerticalBarLayout, x, y);
        this.mFinalDrawableBounds.set(0, 0, layoutParams.width, layoutParams.height);
        if (this.mIsVerticalBarLayout) {
            layoutParams.width = (int) Math.max(layoutParams.width, layoutParams.height * 0.5f);
        } else {
            layoutParams.height = (int) Math.max(layoutParams.height, layoutParams.width * 0.5f);
        }
        setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mClipIconView.getLayoutParams();
        int i = layoutParams2.height;
        int i2 = layoutParams2.width;
        layoutParams2.width = layoutParams.width;
        layoutParams2.height = layoutParams.height;
        this.mClipIconView.setLayoutParams(layoutParams2);
        this.mClipIconView.setX(x);
        this.mClipIconView.setY(y);
        Drawable drawable = this.mBadge;
        if (drawable != null) {
            drawable.setBounds((int) x, (int) y, i2, i);
        }
        invalidate();
    }

    public void setFastFinishRunnable(Runnable runnable) {
        this.mFastFinishRunnable = runnable;
    }

    public void fastFinish() {
        Runnable runnable = this.mFastFinishRunnable;
        if (runnable != null) {
            runnable.run();
            this.mFastFinishRunnable = null;
        }
        CancellationSignal cancellationSignal = this.mLoadIconSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            this.mLoadIconSignal = null;
        }
        Runnable runnable2 = this.mEndRunnable;
        if (runnable2 != null) {
            runnable2.run();
            this.mEndRunnable = null;
        }
        AnimatorSet animatorSet = this.mFadeAnimatorSet;
        if (animatorSet != null) {
            animatorSet.end();
            this.mFadeAnimatorSet = null;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Log.e(TAG, "onAnimationStart");
        setVisibility(0);
    }

    public void setOnTargetChangeListener(Runnable onTargetChangeListener) {
        this.mOnTargetChangeRunnable = onTargetChangeListener;
    }

    public static FloatingIconView getFloatingIconView(Context context, final Activity activity, final ViewGroup viewGroup, final View originalView, final boolean hideOriginal, RectF positionOut, final boolean isOpening, int width, int height) {
        final FloatingIconView floatingIconView = (FloatingIconView) LayoutInflater.from(context).inflate(R.layout.floating_icon_view, viewGroup, false);
        if (activity != null) {
            ((ViewGroup) activity.getWindow().getDecorView()).addView(floatingIconView);
        } else if (viewGroup != null) {
            viewGroup.addView(floatingIconView);
        }
        floatingIconView.recycle();
        floatingIconView.mIsVerticalBarLayout = false;
        floatingIconView.mIsOpening = isOpening;
        floatingIconView.mOriginalIcon = originalView;
        floatingIconView.mPositionOut = positionOut;
        floatingIconView.matchPositionOf(context, originalView, isOpening, positionOut, width, height);
        floatingIconView.setVisibility(4);
        floatingIconView.mEndRunnable = new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$rmYyCgYVZlc93C4xfpvGQv41T38
            @Override // java.lang.Runnable
            public final void run() {
                FloatingIconView.lambda$getFloatingIconView$0(this.f$0, activity, viewGroup, hideOriginal, isOpening, originalView);
            }
        };
        floatingIconView.setIcon(originalView, 0, originalView.getX(), originalView.getY());
        return floatingIconView;
    }

    static /* synthetic */ void lambda$getFloatingIconView$0(FloatingIconView floatingIconView, Activity activity, ViewGroup viewGroup, boolean z, boolean z2, View view) {
        floatingIconView.mEndRunnable = null;
        if (activity != null) {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(floatingIconView);
        } else if (viewGroup != null) {
            viewGroup.removeView(floatingIconView);
        }
        floatingIconView.recycle();
        if (!z || z2) {
            return;
        }
        AnimatorSet animatorSetCreateFadeAnimation = floatingIconView.createFadeAnimation(view);
        floatingIconView.mFadeAnimatorSet = animatorSetCreateFadeAnimation;
        animatorSetCreateFadeAnimation.start();
    }

    private AnimatorSet createFadeAnimation(final View originalView) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200L);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.FloatingIconView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Log.e(FloatingIconView.TAG, "createFadeAnimation onAnimationStart");
                originalView.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                FloatingIconView.this.recycle();
            }
        });
        animatorSet.play(ObjectAnimator.ofFloat(originalView, (Property<View, Float>) ALPHA, 0.0f, 1.0f));
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recycle() {
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
        CancellationSignal cancellationSignal = this.mLoadIconSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.mLoadIconSignal = null;
        this.mEndRunnable = null;
        this.mFinalDrawableBounds.setEmpty();
        AnimatorSet animatorSet = this.mFadeAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.mPositionOut = null;
        this.mFadeAnimatorSet = null;
        this.mOriginalIcon = null;
        this.mOnTargetChangeRunnable = null;
        this.mBadge = null;
        sTmpObjArray[0] = null;
        this.mClipIconView.recycle();
        this.mFastFinishRunnable = null;
    }
}
