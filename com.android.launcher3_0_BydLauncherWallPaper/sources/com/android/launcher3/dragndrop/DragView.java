package com.android.launcher3.dragndrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.FloatArrayEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.FirstFrameAnimatorHelper;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.BaseDragLayer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class DragView extends View implements LauncherStateManager.StateListener {
    public static final int COLOR_CHANGE_DURATION = 120;
    public static final int VIEW_ZOOM_DURATION = 150;
    private static final ColorMatrix sTempMatrix1 = new ColorMatrix();
    private static final ColorMatrix sTempMatrix2 = new ColorMatrix();
    ValueAnimator mAnim;
    private int mAnimatedShiftX;
    private int mAnimatedShiftY;
    private boolean mAnimationCancelled;
    private Drawable mBadge;
    private ColorMatrixColorFilter mBaseFilter;
    private Drawable mBgSpringDrawable;
    private Bitmap mBitmap;
    private final int mBlurSizeOutline;
    private Bitmap mCrossFadeBitmap;
    float mCrossFadeProgress;
    float[] mCurrentFilter;
    final DragController mDragController;
    private final DragLayer mDragLayer;
    private Rect mDragRegion;
    private Point mDragVisualizeOffset;
    private boolean mDrawBitmap;
    private Drawable mFgSpringDrawable;
    private ValueAnimator mFilterAnimator;
    final FirstFrameAnimatorHelper mFirstFrameAnimatorHelper;
    private boolean mHasDrawn;
    private final float mInitialScale;
    private float mIntrinsicIconScale;
    private int mLastTouchX;
    private int mLastTouchY;
    private final Launcher mLauncher;
    Paint mPaint;
    private final int mRegistrationX;
    private final int mRegistrationY;
    private final float mScaleOnDrop;
    private Path mScaledMaskPath;
    private final int[] mTempLoc;
    private SpringFloatValue mTranslateX;
    private SpringFloatValue mTranslateY;

    @Override // com.android.launcher3.LauncherStateManager.StateListener
    public void onStateTransitionStart(LauncherState toState) {
    }

    public DragView(Launcher launcher, Bitmap bitmap, int registrationX, int registrationY, final float initialScale, final float scaleOnDrop, final float finalScaleDps) {
        super(launcher);
        this.mDrawBitmap = true;
        this.mTempLoc = new int[2];
        this.mDragVisualizeOffset = null;
        this.mDragRegion = null;
        this.mHasDrawn = false;
        this.mCrossFadeProgress = 0.0f;
        this.mAnimationCancelled = false;
        this.mIntrinsicIconScale = 1.0f;
        this.mLauncher = launcher;
        this.mDragLayer = launcher.getDragLayer();
        this.mDragController = launcher.getDragController();
        this.mFirstFrameAnimatorHelper = new FirstFrameAnimatorHelper(this);
        final float width = (bitmap.getWidth() + finalScaleDps) / bitmap.getWidth();
        setScaleX(initialScale);
        setScaleY(initialScale);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnim = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(150L);
        this.mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.dragndrop.-$$Lambda$DragView$OppVAAWY4HrFEm6Bo_Yl1myBaXc
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$new$0$DragView(initialScale, width, valueAnimator);
            }
        });
        this.mAnim.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.dragndrop.DragView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (DragView.this.mAnimationCancelled) {
                    return;
                }
                DragView.this.mDragController.onDragViewAnimationEnd();
            }
        });
        this.mBitmap = bitmap;
        setDragRegion(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
        this.mRegistrationX = registrationX;
        this.mRegistrationY = registrationY;
        this.mInitialScale = initialScale;
        this.mScaleOnDrop = scaleOnDrop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        measure(iMakeMeasureSpec, iMakeMeasureSpec);
        this.mPaint = new Paint(2);
        this.mBlurSizeOutline = getResources().getDimensionPixelSize(R.dimen.blur_size_medium_outline);
        setElevation(getResources().getDimension(R.dimen.drag_elevation));
    }

    public /* synthetic */ void lambda$new$0$DragView(float f, float f2, ValueAnimator valueAnimator) {
        float fFloatValue = f + (((Float) valueAnimator.getAnimatedValue()).floatValue() * (f2 - f));
        setScaleX(fFloatValue);
        setScaleY(fFloatValue);
        if (isAttachedToWindow()) {
            return;
        }
        valueAnimator.cancel();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLauncher.getStateManager().addStateListener(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLauncher.getStateManager().removeStateListener(this);
    }

    @Override // com.android.launcher3.LauncherStateManager.StateListener
    public void onStateTransitionComplete(LauncherState finalState) {
        setVisibility((finalState == LauncherState.NORMAL || finalState == LauncherState.SPRING_LOADED) ? 0 : 4);
    }

    public void setItemInfo(final ItemInfo info) {
        if (Utilities.ATLEAST_OREO) {
            if (info.itemType == 0 || info.itemType == 6 || info.itemType == 2) {
                new Handler(LauncherModel.getWorkerLooper()).postAtFrontOfQueue(new Runnable() { // from class: com.android.launcher3.dragndrop.DragView.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Object[] objArr = new Object[1];
                        int width = DragView.this.mBitmap.getWidth();
                        int height = DragView.this.mBitmap.getHeight();
                        Drawable fullDrawable = Utilities.getFullDrawable(DragView.this.mLauncher, info, width, height, false, objArr);
                        if (fullDrawable instanceof AdaptiveIconDrawable) {
                            int dimension = ((int) DragView.this.mLauncher.getResources().getDimension(R.dimen.blur_size_medium_outline)) / 2;
                            Rect rect = new Rect(0, 0, width, height);
                            rect.inset(dimension, dimension);
                            Rect rect2 = new Rect(rect);
                            DragView dragView = DragView.this;
                            dragView.mBadge = Utilities.getBadge(dragView.mLauncher, info, objArr[0]);
                            DragView.this.mBadge.setBounds(rect2);
                            DragView.this.mDrawBitmap = true ^ (fullDrawable instanceof FolderAdaptiveIcon);
                            LauncherIcons launcherIconsObtain = LauncherIcons.obtain(DragView.this.mLauncher);
                            try {
                                Utilities.scaleRectAboutCenter(rect, launcherIconsObtain.getNormalizer().getScale(DragView.this.mDrawBitmap ? fullDrawable : new AdaptiveIconDrawable(new ColorDrawable(-16777216), (Drawable) null), null, null, null));
                                if (launcherIconsObtain != null) {
                                    launcherIconsObtain.close();
                                }
                                AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) fullDrawable;
                                Rect rect3 = new Rect(rect);
                                Utilities.scaleRectAboutCenter(rect3, 0.98f);
                                adaptiveIconDrawable.setBounds(rect3);
                                final Path iconMask = adaptiveIconDrawable.getIconMask();
                                DragView.this.mTranslateX = new SpringFloatValue(DragView.this, width * AdaptiveIconDrawable.getExtraInsetFraction());
                                DragView.this.mTranslateY = new SpringFloatValue(DragView.this, height * AdaptiveIconDrawable.getExtraInsetFraction());
                                rect.inset((int) ((-rect.width()) * AdaptiveIconDrawable.getExtraInsetFraction()), (int) ((-rect.height()) * AdaptiveIconDrawable.getExtraInsetFraction()));
                                DragView.this.mBgSpringDrawable = adaptiveIconDrawable.getBackground();
                                if (DragView.this.mBgSpringDrawable == null) {
                                    DragView.this.mBgSpringDrawable = new ColorDrawable(0);
                                }
                                DragView.this.mBgSpringDrawable.setBounds(rect);
                                DragView.this.mFgSpringDrawable = adaptiveIconDrawable.getForeground();
                                if (DragView.this.mFgSpringDrawable == null) {
                                    DragView.this.mFgSpringDrawable = new ColorDrawable(0);
                                }
                                DragView.this.mFgSpringDrawable.setBounds(rect);
                                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.android.launcher3.dragndrop.DragView.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        DragView.this.mScaledMaskPath = iconMask;
                                        if (info.isDisabled()) {
                                            FastBitmapDrawable fastBitmapDrawable = new FastBitmapDrawable((Bitmap) null);
                                            fastBitmapDrawable.setIsDisabled(true);
                                            DragView.this.mBaseFilter = (ColorMatrixColorFilter) fastBitmapDrawable.getColorFilter();
                                        }
                                        DragView.this.updateColorFilter();
                                    }
                                });
                            } catch (Throwable th) {
                                if (launcherIconsObtain != null) {
                                    try {
                                        launcherIconsObtain.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                }
                                throw th;
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateColorFilter() {
        if (this.mCurrentFilter == null) {
            this.mPaint.setColorFilter(null);
            if (this.mScaledMaskPath != null) {
                this.mBgSpringDrawable.setColorFilter(this.mBaseFilter);
                this.mFgSpringDrawable.setColorFilter(this.mBaseFilter);
                this.mBadge.setColorFilter(this.mBaseFilter);
            }
        } else {
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(this.mCurrentFilter);
            this.mPaint.setColorFilter(colorMatrixColorFilter);
            if (this.mScaledMaskPath != null) {
                ColorMatrixColorFilter colorMatrixColorFilter2 = this.mBaseFilter;
                if (colorMatrixColorFilter2 != null) {
                    ColorMatrix colorMatrix = sTempMatrix1;
                    colorMatrixColorFilter2.getColorMatrix(colorMatrix);
                    ColorMatrix colorMatrix2 = sTempMatrix2;
                    colorMatrix2.set(this.mCurrentFilter);
                    colorMatrix.postConcat(colorMatrix2);
                    colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
                }
                this.mBgSpringDrawable.setColorFilter(colorMatrixColorFilter);
                this.mFgSpringDrawable.setColorFilter(colorMatrixColorFilter);
                this.mBadge.setColorFilter(colorMatrixColorFilter);
            }
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }

    public void setIntrinsicIconScaleFactor(float scale) {
        this.mIntrinsicIconScale = scale;
    }

    public float getIntrinsicIconScaleFactor() {
        return this.mIntrinsicIconScale;
    }

    public int getDragRegionLeft() {
        return this.mDragRegion.left;
    }

    public int getDragRegionTop() {
        return this.mDragRegion.top;
    }

    public int getDragRegionWidth() {
        return this.mDragRegion.width();
    }

    public int getDragRegionHeight() {
        return this.mDragRegion.height();
    }

    public void setDragVisualizeOffset(Point p) {
        this.mDragVisualizeOffset = p;
    }

    public Point getDragVisualizeOffset() {
        return this.mDragVisualizeOffset;
    }

    public void setDragRegion(Rect r) {
        this.mDragRegion = r;
    }

    public Rect getDragRegion() {
        return this.mDragRegion;
    }

    public Bitmap getPreviewBitmap() {
        return this.mBitmap;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mHasDrawn = true;
        if (this.mDrawBitmap) {
            float f = this.mCrossFadeProgress;
            boolean z = f > 0.0f && this.mCrossFadeBitmap != null;
            if (z) {
                this.mPaint.setAlpha(z ? (int) ((1.0f - f) * 255.0f) : 255);
            }
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
            if (z) {
                this.mPaint.setAlpha((int) (this.mCrossFadeProgress * 255.0f));
                int iSave = canvas.save();
                canvas.scale((this.mBitmap.getWidth() * 1.0f) / this.mCrossFadeBitmap.getWidth(), (this.mBitmap.getHeight() * 1.0f) / this.mCrossFadeBitmap.getHeight());
                canvas.drawBitmap(this.mCrossFadeBitmap, 0.0f, 0.0f, this.mPaint);
                canvas.restoreToCount(iSave);
            }
        }
        if (this.mScaledMaskPath != null) {
            int iSave2 = canvas.save();
            canvas.clipPath(this.mScaledMaskPath);
            this.mBgSpringDrawable.draw(canvas);
            canvas.translate(this.mTranslateX.mValue, this.mTranslateY.mValue);
            this.mFgSpringDrawable.draw(canvas);
            canvas.restoreToCount(iSave2);
            this.mBadge.draw(canvas);
        }
    }

    public void setCrossFadeBitmap(Bitmap crossFadeBitmap) {
        this.mCrossFadeBitmap = crossFadeBitmap;
    }

    public void crossFade(int duration) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(duration);
        valueAnimatorOfFloat.setInterpolator(Interpolators.DEACCEL_1_5);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.dragndrop.-$$Lambda$DragView$W9lpANeo6MWKxqlepUZk8ydfRks
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$crossFade$1$DragView(valueAnimator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    public /* synthetic */ void lambda$crossFade$1$DragView(ValueAnimator valueAnimator) {
        this.mCrossFadeProgress = valueAnimator.getAnimatedFraction();
        invalidate();
    }

    public void setColor(int color) {
        if (this.mPaint == null) {
            this.mPaint = new Paint(2);
        }
        if (color != 0) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            ColorMatrix colorMatrix2 = new ColorMatrix();
            Themes.setColorScaleOnMatrix(color, colorMatrix2);
            colorMatrix.postConcat(colorMatrix2);
            animateFilterTo(colorMatrix.getArray());
            return;
        }
        if (this.mCurrentFilter == null) {
            updateColorFilter();
        } else {
            animateFilterTo(new ColorMatrix().getArray());
        }
    }

    private void animateFilterTo(float[] targetFilter) {
        float[] array = this.mCurrentFilter;
        if (array == null) {
            array = new ColorMatrix().getArray();
        }
        this.mCurrentFilter = Arrays.copyOf(array, array.length);
        ValueAnimator valueAnimator = this.mFilterAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new FloatArrayEvaluator(this.mCurrentFilter), array, targetFilter);
        this.mFilterAnimator = valueAnimatorOfObject;
        valueAnimatorOfObject.setDuration(120L);
        this.mFilterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.dragndrop.DragView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                DragView.this.updateColorFilter();
            }
        });
        this.mFilterAnimator.start();
    }

    public boolean hasDrawn() {
        return this.mHasDrawn;
    }

    @Override // android.view.View
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        this.mPaint.setAlpha((int) (alpha * 255.0f));
        invalidate();
    }

    public void show(int touchX, int touchY) {
        this.mDragLayer.addView(this);
        BaseDragLayer.LayoutParams layoutParams = new BaseDragLayer.LayoutParams(0, 0);
        layoutParams.width = this.mBitmap.getWidth();
        layoutParams.height = this.mBitmap.getHeight();
        layoutParams.customPosition = true;
        setLayoutParams(layoutParams);
        move(touchX, touchY);
        post(new Runnable() { // from class: com.android.launcher3.dragndrop.DragView.4
            @Override // java.lang.Runnable
            public void run() {
                DragView.this.mAnim.start();
            }
        });
    }

    public void cancelAnimation() {
        this.mAnimationCancelled = true;
        ValueAnimator valueAnimator = this.mAnim;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mAnim.cancel();
    }

    public void move(int touchX, int touchY) {
        int i;
        if (touchX > 0 && touchY > 0 && (i = this.mLastTouchX) > 0 && this.mLastTouchY > 0 && this.mScaledMaskPath != null) {
            this.mTranslateX.animateToPos(i - touchX);
            this.mTranslateY.animateToPos(this.mLastTouchY - touchY);
        }
        this.mLastTouchX = touchX;
        this.mLastTouchY = touchY;
        applyTranslation();
    }

    public void animateTo(int toTouchX, int toTouchY, Runnable onCompleteRunnable, int duration) {
        int[] iArr = this.mTempLoc;
        iArr[0] = toTouchX - this.mRegistrationX;
        iArr[1] = toTouchY - this.mRegistrationY;
        DragLayer dragLayer = this.mDragLayer;
        float f = this.mScaleOnDrop;
        dragLayer.animateViewIntoPosition(this, iArr, 1.0f, f, f, 0, onCompleteRunnable, duration);
    }

    public void animateShift(final int shiftX, final int shiftY) {
        if (this.mAnim.isStarted()) {
            return;
        }
        this.mAnimatedShiftX = shiftX;
        this.mAnimatedShiftY = shiftY;
        applyTranslation();
        this.mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.dragndrop.DragView.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = 1.0f - animation.getAnimatedFraction();
                DragView.this.mAnimatedShiftX = (int) (shiftX * animatedFraction);
                DragView.this.mAnimatedShiftY = (int) (animatedFraction * shiftY);
                DragView.this.applyTranslation();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyTranslation() {
        setTranslationX((this.mLastTouchX - this.mRegistrationX) + this.mAnimatedShiftX);
        setTranslationY((this.mLastTouchY - this.mRegistrationY) + this.mAnimatedShiftY);
    }

    public void remove() {
        if (getParent() != null) {
            this.mDragLayer.removeView(this);
        }
    }

    public int getBlurSizeOutline() {
        return this.mBlurSizeOutline;
    }

    public float getInitialScale() {
        return this.mInitialScale;
    }

    private static class SpringFloatValue {
        private static final float DAMPENING_RATIO = 1.0f;
        private static final int PARALLAX_MAX_IN_DP = 8;
        private static final int STIFFNESS = 4000;
        private static final FloatPropertyCompat<SpringFloatValue> VALUE = new FloatPropertyCompat<SpringFloatValue>("value") { // from class: com.android.launcher3.dragndrop.DragView.SpringFloatValue.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(SpringFloatValue object) {
                return object.mValue;
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(SpringFloatValue object, float value) {
                object.mValue = value;
                object.mView.invalidate();
            }
        };
        private final float mDelta;
        private final SpringAnimation mSpring;
        private float mValue;
        private final View mView;

        public SpringFloatValue(View view, float range) {
            this.mView = view;
            this.mSpring = new SpringAnimation(this, VALUE, 0.0f).setMinValue(-range).setMaxValue(range).setSpring(new SpringForce(0.0f).setDampingRatio(1.0f).setStiffness(4000.0f));
            this.mDelta = view.getResources().getDisplayMetrics().density * 8.0f;
        }

        public void animateToPos(float value) {
            SpringAnimation springAnimation = this.mSpring;
            float f = this.mDelta;
            springAnimation.animateToFinalPosition(Utilities.boundToRange(value, -f, f));
        }
    }
}
