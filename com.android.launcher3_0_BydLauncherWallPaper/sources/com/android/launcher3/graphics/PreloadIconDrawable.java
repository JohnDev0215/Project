package com.android.launcher3.graphics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.Property;
import android.util.SparseArray;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.anim.Interpolators;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes4.dex */
public class PreloadIconDrawable extends FastBitmapDrawable {
    private static final int COLOR_SHADOW = 1426063360;
    private static final int COLOR_TRACK = 2012147438;
    private static final float COMPLETE_ANIM_FRACTION = 0.3f;
    private static final long DURATION_SCALE = 500;
    private static final int MAX_PAINT_ALPHA = 255;
    private static final float PROGRESS_GAP = 2.0f;
    private static final float PROGRESS_WIDTH = 7.0f;
    private static final float SMALL_SCALE = 0.6f;
    private ObjectAnimator mCurrentAnim;
    private float mIconScale;
    private final int mIndicatorColor;
    private float mInternalStateProgress;
    private final ItemInfoWithIcon mItem;
    private final PathMeasure mPathMeasure;
    private final Paint mProgressPaint;
    private final Path mProgressPath;
    private boolean mRanFinishAnimation;
    private final Path mScaledProgressPath;
    private final Path mScaledTrackPath;
    private Bitmap mShadowBitmap;
    private final Matrix mTmpMatrix;
    private int mTrackAlpha;
    private float mTrackLength;
    private static final Property<PreloadIconDrawable, Float> INTERNAL_STATE = new Property<PreloadIconDrawable, Float>(Float.TYPE, "internalStateProgress") { // from class: com.android.launcher3.graphics.PreloadIconDrawable.1
        @Override // android.util.Property
        public Float get(PreloadIconDrawable object) {
            return Float.valueOf(object.mInternalStateProgress);
        }

        @Override // android.util.Property
        public void set(PreloadIconDrawable object, Float value) {
            object.setInternalProgress(value.floatValue());
        }
    };
    private static final SparseArray<WeakReference<Bitmap>> sShadowCache = new SparseArray<>();

    public PreloadIconDrawable(ItemInfoWithIcon info, Path progressPath, Context context) {
        super(info);
        this.mTmpMatrix = new Matrix();
        this.mPathMeasure = new PathMeasure();
        this.mItem = info;
        this.mProgressPath = progressPath;
        this.mScaledTrackPath = new Path();
        this.mScaledProgressPath = new Path();
        Paint paint = new Paint(3);
        this.mProgressPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.mIndicatorColor = IconPalette.getPreloadProgressColor(context, this.mIconColor);
        setInternalProgress(0.0f);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mTmpMatrix.setScale(((bounds.width() - 14.0f) - 4.0f) / 100.0f, ((bounds.height() - 14.0f) - 4.0f) / 100.0f);
        this.mTmpMatrix.postTranslate(bounds.left + PROGRESS_WIDTH + PROGRESS_GAP, bounds.top + PROGRESS_WIDTH + PROGRESS_GAP);
        this.mProgressPath.transform(this.mTmpMatrix, this.mScaledTrackPath);
        float fWidth = bounds.width() / 100;
        this.mProgressPaint.setStrokeWidth(PROGRESS_WIDTH * fWidth);
        this.mShadowBitmap = getShadowBitmap(bounds.width(), bounds.height(), fWidth * PROGRESS_GAP);
        this.mPathMeasure.setPath(this.mScaledTrackPath, true);
        this.mTrackLength = this.mPathMeasure.getLength();
        setInternalProgress(this.mInternalStateProgress);
    }

    private Bitmap getShadowBitmap(int width, int height, float shadowRadius) {
        int i = (width << 16) | height;
        SparseArray<WeakReference<Bitmap>> sparseArray = sShadowCache;
        WeakReference<Bitmap> weakReference = sparseArray.get(i);
        Bitmap bitmap = weakReference != null ? weakReference.get() : null;
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        this.mProgressPaint.setShadowLayer(shadowRadius, 0.0f, 0.0f, COLOR_SHADOW);
        this.mProgressPaint.setColor(COLOR_TRACK);
        this.mProgressPaint.setAlpha(255);
        canvas.drawPath(this.mScaledTrackPath, this.mProgressPaint);
        this.mProgressPaint.clearShadowLayer();
        canvas.setBitmap(null);
        sparseArray.put(i, new WeakReference<>(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    @Override // com.android.launcher3.FastBitmapDrawable
    public void drawInternal(Canvas canvas, Rect bounds) {
        if (this.mRanFinishAnimation) {
            super.drawInternal(canvas, bounds);
            return;
        }
        this.mProgressPaint.setColor(this.mIndicatorColor);
        this.mProgressPaint.setAlpha(this.mTrackAlpha);
        Bitmap bitmap = this.mShadowBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, bounds.left, bounds.top, this.mProgressPaint);
        }
        canvas.drawPath(this.mScaledProgressPath, this.mProgressPaint);
        int iSave = canvas.save();
        float f = this.mIconScale;
        canvas.scale(f, f, bounds.exactCenterX(), bounds.exactCenterY());
        super.drawInternal(canvas, bounds);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int level) {
        updateInternalState(level * 0.01f, getBounds().width() > 0, false);
        return true;
    }

    public void maybePerformFinishedAnimation() {
        if (this.mInternalStateProgress == 0.0f) {
            this.mInternalStateProgress = 1.0f;
        }
        updateInternalState(1.3f, true, true);
    }

    public boolean hasNotCompleted() {
        return !this.mRanFinishAnimation;
    }

    private void updateInternalState(float finalProgress, boolean shouldAnimate, boolean isFinish) {
        ObjectAnimator objectAnimator = this.mCurrentAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mCurrentAnim = null;
        }
        if (Float.compare(finalProgress, this.mInternalStateProgress) == 0) {
            return;
        }
        if (finalProgress < this.mInternalStateProgress) {
            shouldAnimate = false;
        }
        if (!shouldAnimate || this.mRanFinishAnimation) {
            setInternalProgress(finalProgress);
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, INTERNAL_STATE, finalProgress);
        this.mCurrentAnim = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration((long) ((finalProgress - this.mInternalStateProgress) * 500.0f));
        this.mCurrentAnim.setInterpolator(Interpolators.LINEAR);
        if (isFinish) {
            this.mCurrentAnim.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.graphics.PreloadIconDrawable.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    PreloadIconDrawable.this.mRanFinishAnimation = true;
                }
            });
        }
        this.mCurrentAnim.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInternalProgress(float progress) {
        this.mInternalStateProgress = progress;
        if (progress <= 0.0f) {
            this.mIconScale = 0.6f;
            this.mScaledTrackPath.reset();
            this.mTrackAlpha = 255;
            setIsDisabled(true);
        }
        if (progress < 1.0f && progress > 0.0f) {
            this.mPathMeasure.getSegment(0.0f, progress * this.mTrackLength, this.mScaledProgressPath, true);
            this.mIconScale = 0.6f;
            this.mTrackAlpha = 255;
            setIsDisabled(true);
        } else if (progress >= 1.0f) {
            setIsDisabled(this.mItem.isDisabled());
            this.mScaledTrackPath.set(this.mScaledProgressPath);
            float f = (progress - 1.0f) / COMPLETE_ANIM_FRACTION;
            if (f >= 1.0f) {
                this.mIconScale = 1.0f;
                this.mTrackAlpha = 0;
            } else {
                this.mTrackAlpha = Math.round((1.0f - f) * 255.0f);
                this.mIconScale = (f * 0.39999998f) + 0.6f;
            }
        }
        invalidateSelf();
    }
}
