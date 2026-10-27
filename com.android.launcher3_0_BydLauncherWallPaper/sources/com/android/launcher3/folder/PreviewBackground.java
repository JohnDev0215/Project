package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.Property;
import android.view.View;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.R;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.views.ActivityContext;

/* JADX INFO: loaded from: classes4.dex */
public class PreviewBackground {
    private static final float ACCEPT_COLOR_MULTIPLIER = 1.5f;
    private static final float ACCEPT_SCALE_FACTOR = 1.2f;
    private static final int BG_OPACITY = 160;
    private static final int CONSUMPTION_ANIMATION_DURATION = 100;
    private static final int MAX_BG_OPACITY = 225;
    private static final int SHADOW_OPACITY = 40;
    int basePreviewOffsetX;
    int basePreviewOffsetY;
    public int delegateCellX;
    public int delegateCellY;
    private int mBgColor;
    private int mDotColor;
    private CellLayout mDrawingDelegate;
    private View mInvalidateDelegate;
    private ValueAnimator mScaleAnimator;
    private ObjectAnimator mShadowAnimator;
    private ObjectAnimator mStrokeAlphaAnimator;
    private int mStrokeColor;
    private float mStrokeWidth;
    int previewSize;
    private static final Property<PreviewBackground, Integer> STROKE_ALPHA = new Property<PreviewBackground, Integer>(Integer.class, "strokeAlpha") { // from class: com.android.launcher3.folder.PreviewBackground.1
        @Override // android.util.Property
        public Integer get(PreviewBackground previewBackground) {
            return Integer.valueOf(previewBackground.mStrokeAlpha);
        }

        @Override // android.util.Property
        public void set(PreviewBackground previewBackground, Integer alpha) {
            previewBackground.mStrokeAlpha = alpha.intValue();
            previewBackground.invalidate();
        }
    };
    private static final Property<PreviewBackground, Integer> SHADOW_ALPHA = new Property<PreviewBackground, Integer>(Integer.class, "shadowAlpha") { // from class: com.android.launcher3.folder.PreviewBackground.2
        @Override // android.util.Property
        public Integer get(PreviewBackground previewBackground) {
            return Integer.valueOf(previewBackground.mShadowAlpha);
        }

        @Override // android.util.Property
        public void set(PreviewBackground previewBackground, Integer alpha) {
            previewBackground.mShadowAlpha = alpha.intValue();
            previewBackground.invalidate();
        }
    };
    private final PorterDuffXfermode mShadowPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    private RadialGradient mShadowShader = null;
    private final Matrix mShaderMatrix = new Matrix();
    private final Path mPath = new Path();
    private final Paint mPaint = new Paint(1);
    float mScale = 1.0f;
    private float mColorMultiplier = 1.0f;
    private int mStrokeAlpha = 225;
    private int mShadowAlpha = 255;
    public boolean isClipping = true;

    public void setup(Context context, ActivityContext activity, View invalidateDelegate, int availableSpaceX, int topPadding) {
        this.mInvalidateDelegate = invalidateDelegate;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(R.styleable.FolderIconPreview);
        this.mDotColor = typedArrayObtainStyledAttributes.getColor(0, 0);
        this.mStrokeColor = typedArrayObtainStyledAttributes.getColor(2, 0);
        this.mBgColor = typedArrayObtainStyledAttributes.getColor(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        DeviceProfile wallpaperDeviceProfile = activity.getWallpaperDeviceProfile();
        int i = wallpaperDeviceProfile.folderIconSizePx;
        this.previewSize = i;
        this.basePreviewOffsetX = (availableSpaceX - i) / 2;
        this.basePreviewOffsetY = topPadding + wallpaperDeviceProfile.folderIconOffsetYPx;
        this.mStrokeWidth = context.getResources().getDisplayMetrics().density;
        float scaledRadius = getScaledRadius();
        this.mShadowShader = new RadialGradient(0.0f, 0.0f, 1.0f, new int[]{Color.argb(40, 0, 0, 0), 0}, new float[]{scaledRadius / (this.mStrokeWidth + scaledRadius), 1.0f}, Shader.TileMode.CLAMP);
        invalidate();
    }

    void getBounds(Rect outBounds) {
        int i = this.basePreviewOffsetY;
        int i2 = this.basePreviewOffsetX;
        int i3 = this.previewSize;
        outBounds.set(i2, i, i2 + i3, i3 + i);
    }

    int getRadius() {
        return this.previewSize / 2;
    }

    int getScaledRadius() {
        return (int) (this.mScale * getRadius());
    }

    int getOffsetX() {
        return this.basePreviewOffsetX - (getScaledRadius() - getRadius());
    }

    int getOffsetY() {
        return this.basePreviewOffsetY - (getScaledRadius() - getRadius());
    }

    float getScaleProgress() {
        return (this.mScale - 1.0f) / 0.20000005f;
    }

    void invalidate() {
        View view = this.mInvalidateDelegate;
        if (view != null) {
            view.invalidate();
        }
        CellLayout cellLayout = this.mDrawingDelegate;
        if (cellLayout != null) {
            cellLayout.invalidate();
        }
    }

    void setInvalidateDelegate(View invalidateDelegate) {
        this.mInvalidateDelegate = invalidateDelegate;
        invalidate();
    }

    public int getBgColor() {
        return GraphicsUtils.setColorAlphaBound(this.mBgColor, (int) Math.min(225.0f, this.mColorMultiplier * 160.0f));
    }

    public int getDotColor() {
        return this.mDotColor;
    }

    public void drawBackground(Canvas canvas) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(getBgColor());
        IconShape.getShape().drawShape(canvas, getOffsetX(), getOffsetY(), getScaledRadius(), this.mPaint);
        drawShadow(canvas);
    }

    public void drawShadow(Canvas canvas) {
        int iSave;
        if (this.mShadowShader == null) {
            return;
        }
        float scaledRadius = getScaledRadius();
        float f = this.mStrokeWidth + scaledRadius;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-16777216);
        int offsetX = getOffsetX();
        int offsetY = getOffsetY();
        if (canvas.isHardwareAccelerated()) {
            float f2 = offsetX;
            float f3 = offsetY;
            iSave = canvas.saveLayer(f2 - this.mStrokeWidth, f3, f2 + scaledRadius + f, f3 + f + f, null);
        } else {
            iSave = canvas.save();
            canvas.clipPath(getClipPath(), Region.Op.DIFFERENCE);
        }
        int i = iSave;
        this.mShaderMatrix.setScale(f, f);
        float f4 = offsetX;
        float f5 = offsetY;
        this.mShaderMatrix.postTranslate(scaledRadius + f4, f + f5);
        this.mShadowShader.setLocalMatrix(this.mShaderMatrix);
        this.mPaint.setAlpha(this.mShadowAlpha);
        this.mPaint.setShader(this.mShadowShader);
        canvas.drawPaint(this.mPaint);
        this.mPaint.setAlpha(255);
        this.mPaint.setShader(null);
        if (canvas.isHardwareAccelerated()) {
            this.mPaint.setXfermode(this.mShadowPorterDuffXfermode);
            IconShape.getShape().drawShape(canvas, f4, f5, scaledRadius, this.mPaint);
            this.mPaint.setXfermode(null);
        }
        canvas.restoreToCount(i);
    }

    public void fadeInBackgroundShadow() {
        ObjectAnimator objectAnimator = this.mShadowAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator duration = ObjectAnimator.ofInt(this, SHADOW_ALPHA, 0, 255).setDuration(100L);
        this.mShadowAnimator = duration;
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.PreviewBackground.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                PreviewBackground.this.mShadowAnimator = null;
            }
        });
        this.mShadowAnimator.start();
    }

    public void animateBackgroundStroke() {
        ObjectAnimator objectAnimator = this.mStrokeAlphaAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator duration = ObjectAnimator.ofInt(this, STROKE_ALPHA, 112, 225).setDuration(100L);
        this.mStrokeAlphaAnimator = duration;
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.PreviewBackground.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                PreviewBackground.this.mStrokeAlphaAnimator = null;
            }
        });
        this.mStrokeAlphaAnimator.start();
    }

    public void drawBackgroundStroke(Canvas canvas) {
        this.mPaint.setColor(GraphicsUtils.setColorAlphaBound(this.mStrokeColor, this.mStrokeAlpha));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        IconShape.getShape().drawShape(canvas, getOffsetX() + 1.0f, getOffsetY() + 1.0f, getScaledRadius() - 1.0f, this.mPaint);
    }

    public void drawLeaveBehind(Canvas canvas) {
        float f = this.mScale;
        this.mScale = 0.5f;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(Color.argb(160, 245, 245, 245));
        IconShape.getShape().drawShape(canvas, getOffsetX(), getOffsetY(), getScaledRadius(), this.mPaint);
        this.mScale = f;
    }

    public Path getClipPath() {
        this.mPath.reset();
        IconShape.getShape().addToPath(this.mPath, getOffsetX(), getOffsetY(), getScaledRadius());
        return this.mPath;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: delegateDrawing, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$animateToRest$1$PreviewBackground(CellLayout delegate, int cellX, int cellY) {
        if (this.mDrawingDelegate != delegate) {
            delegate.addFolderBackground(this);
        }
        this.mDrawingDelegate = delegate;
        this.delegateCellX = cellX;
        this.delegateCellY = cellY;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDrawingDelegate() {
        CellLayout cellLayout = this.mDrawingDelegate;
        if (cellLayout != null) {
            cellLayout.removeFolderBackground(this);
        }
        this.mDrawingDelegate = null;
        this.isClipping = true;
        invalidate();
    }

    boolean drawingDelegated() {
        return this.mDrawingDelegate != null;
    }

    private void animateScale(final float finalScale, final float finalMultiplier, final Runnable onStart, final Runnable onEnd) {
        final float f = this.mScale;
        final float f2 = this.mColorMultiplier;
        ValueAnimator valueAnimator = this.mScaleAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mScaleAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.folder.PreviewBackground.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                float f3 = 1.0f - animatedFraction;
                PreviewBackground.this.mScale = (finalScale * animatedFraction) + (f * f3);
                PreviewBackground.this.mColorMultiplier = (animatedFraction * finalMultiplier) + (f3 * f2);
                PreviewBackground.this.invalidate();
            }
        });
        this.mScaleAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.PreviewBackground.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Runnable runnable = onStart;
                if (runnable != null) {
                    runnable.run();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Runnable runnable = onEnd;
                if (runnable != null) {
                    runnable.run();
                }
                PreviewBackground.this.mScaleAnimator = null;
            }
        });
        this.mScaleAnimator.setDuration(100L);
        this.mScaleAnimator.start();
    }

    public void animateToAccept(final CellLayout cl, final int cellX, final int cellY) {
        animateScale(1.2f, 1.5f, new Runnable() { // from class: com.android.launcher3.folder.-$$Lambda$PreviewBackground$IxN5dgH1ouQqeDoyE4dZsWbAiKI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$animateToAccept$0$PreviewBackground(cl, cellX, cellY);
            }
        }, null);
    }

    public void animateToRest() {
        final CellLayout cellLayout = this.mDrawingDelegate;
        final int i = this.delegateCellX;
        final int i2 = this.delegateCellY;
        animateScale(1.0f, 1.0f, new Runnable() { // from class: com.android.launcher3.folder.-$$Lambda$PreviewBackground$TwMW_fUMqNNN7NRhQTFm1LpiH5I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$animateToRest$1$PreviewBackground(cellLayout, i, i2);
            }
        }, new Runnable() { // from class: com.android.launcher3.folder.-$$Lambda$PreviewBackground$qPwDiiuGSbKeVVWi_mymX5CRk48
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.clearDrawingDelegate();
            }
        });
    }

    public int getBackgroundAlpha() {
        return (int) Math.min(225.0f, this.mColorMultiplier * 160.0f);
    }

    public float getStrokeWidth() {
        return this.mStrokeWidth;
    }
}
