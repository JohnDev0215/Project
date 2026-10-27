package com.android.launcher3;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.util.Property;
import android.util.SparseArray;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.icons.BitmapInfo;

/* JADX INFO: loaded from: classes4.dex */
public class FastBitmapDrawable extends Drawable {
    public static final int CLICK_FEEDBACK_DURATION = 200;
    private static final float DISABLED_BRIGHTNESS = 0.5f;
    private static final float DISABLED_DESATURATION = 1.0f;
    private static final float PRESSED_SCALE = 1.1f;
    private static final int REDUCED_FILTER_VALUE_SPACE = 48;
    private int mAlpha;
    protected Bitmap mBitmap;
    private int mBrightness;
    private int mDesaturation;
    protected final int mIconColor;
    private boolean mIsDisabled;
    private boolean mIsPressed;
    protected final Paint mPaint;
    private int mPrevUpdateKey;
    private float mScale;
    private ObjectAnimator mScaleAnimation;
    private static final SparseArray<ColorFilter> sCachedFilter = new SparseArray<>();
    private static final ColorMatrix sTempBrightnessMatrix = new ColorMatrix();
    private static final ColorMatrix sTempFilterMatrix = new ColorMatrix();
    private static final Property<FastBitmapDrawable, Float> SCALE = new Property<FastBitmapDrawable, Float>(Float.TYPE, BatteryManager.EXTRA_SCALE) { // from class: com.android.launcher3.FastBitmapDrawable.1
        @Override // android.util.Property
        public Float get(FastBitmapDrawable fastBitmapDrawable) {
            return Float.valueOf(fastBitmapDrawable.mScale);
        }

        @Override // android.util.Property
        public void set(FastBitmapDrawable fastBitmapDrawable, Float value) {
            fastBitmapDrawable.mScale = value.floatValue();
            fastBitmapDrawable.invalidateSelf();
        }
    };

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
    }

    public FastBitmapDrawable(Bitmap b) {
        this(b, 0);
    }

    public FastBitmapDrawable(BitmapInfo info) {
        this(info.icon, info.color);
    }

    public FastBitmapDrawable(ItemInfoWithIcon info) {
        this(info.iconBitmap, info.iconColor);
    }

    protected FastBitmapDrawable(Bitmap b, int iconColor) {
        this(b, iconColor, false);
    }

    protected FastBitmapDrawable(Bitmap b, int iconColor, boolean isDisabled) {
        this.mPaint = new Paint(3);
        this.mScale = 1.0f;
        this.mDesaturation = 0;
        this.mBrightness = 0;
        this.mAlpha = 255;
        this.mPrevUpdateKey = Integer.MAX_VALUE;
        this.mBitmap = b;
        this.mIconColor = iconColor;
        setFilterBitmap(true);
        setIsDisabled(isDisabled);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.mScale != 1.0f) {
            int iSave = canvas.save();
            Rect bounds = getBounds();
            float f = this.mScale;
            canvas.scale(f, f, bounds.exactCenterX(), bounds.exactCenterY());
            drawInternal(canvas, bounds);
            canvas.restoreToCount(iSave);
            return;
        }
        drawInternal(canvas, getBounds());
    }

    protected void drawInternal(Canvas canvas, Rect bounds) {
        canvas.drawBitmap(this.mBitmap, (Rect) null, bounds, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
        this.mPaint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean filterBitmap) {
        this.mPaint.setFilterBitmap(filterBitmap);
        this.mPaint.setAntiAlias(filterBitmap);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    public void setScale(float scale) {
        ObjectAnimator objectAnimator = this.mScaleAnimation;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mScaleAnimation = null;
        }
        this.mScale = scale;
        invalidateSelf();
    }

    public float getAnimatedScale() {
        if (this.mScaleAnimation == null) {
            return 1.0f;
        }
        return this.mScale;
    }

    public float getScale() {
        return this.mScale;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmap.getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmap.getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return getBounds().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] state) {
        boolean z;
        int length = state.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (state[i] == 16842919) {
                z = true;
                break;
            }
            i++;
        }
        if (this.mIsPressed == z) {
            return false;
        }
        this.mIsPressed = z;
        ObjectAnimator objectAnimator = this.mScaleAnimation;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mScaleAnimation = null;
        }
        if (this.mIsPressed) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, SCALE, PRESSED_SCALE);
            this.mScaleAnimation = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(200L);
            this.mScaleAnimation.setInterpolator(Interpolators.ACCEL);
            this.mScaleAnimation.start();
        } else if (isVisible()) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, SCALE, 1.0f);
            this.mScaleAnimation = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(200L);
            this.mScaleAnimation.setInterpolator(Interpolators.DEACCEL);
            this.mScaleAnimation.start();
        } else {
            this.mScale = 1.0f;
            invalidateSelf();
        }
        return true;
    }

    private void invalidateDesaturationAndBrightness() {
        setDesaturation(this.mIsDisabled ? 1.0f : 0.0f);
        setBrightness(this.mIsDisabled ? 0.5f : 0.0f);
    }

    public void setIsDisabled(boolean isDisabled) {
        if (this.mIsDisabled != isDisabled) {
            this.mIsDisabled = isDisabled;
            invalidateDesaturationAndBrightness();
        }
    }

    protected boolean isDisabled() {
        return this.mIsDisabled;
    }

    private void setDesaturation(float desaturation) {
        int iFloor = (int) Math.floor(desaturation * 48.0f);
        if (this.mDesaturation != iFloor) {
            this.mDesaturation = iFloor;
            updateFilter();
        }
    }

    public float getDesaturation() {
        return this.mDesaturation / 48.0f;
    }

    private void setBrightness(float brightness) {
        int iFloor = (int) Math.floor(brightness * 48.0f);
        if (this.mBrightness != iFloor) {
            this.mBrightness = iFloor;
            updateFilter();
        }
    }

    private float getBrightness() {
        return this.mBrightness / 48.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void updateFilter() {
        /*
            r8 = this;
            int r0 = r8.mDesaturation
            r1 = -1
            r2 = 0
            if (r0 <= 0) goto Lc
            int r0 = r0 << 16
            int r3 = r8.mBrightness
            r0 = r0 | r3
            goto L16
        Lc:
            int r0 = r8.mBrightness
            if (r0 <= 0) goto L15
            r3 = 65536(0x10000, float:9.1835E-41)
            r0 = r0 | r3
            r3 = 1
            goto L17
        L15:
            r0 = r1
        L16:
            r3 = r2
        L17:
            int r4 = r8.mPrevUpdateKey
            if (r0 != r4) goto L1c
            return
        L1c:
            r8.mPrevUpdateKey = r0
            if (r0 == r1) goto L82
            android.util.SparseArray<android.graphics.ColorFilter> r1 = com.android.launcher3.FastBitmapDrawable.sCachedFilter
            java.lang.Object r4 = r1.get(r0)
            android.graphics.ColorFilter r4 = (android.graphics.ColorFilter) r4
            if (r4 != 0) goto L7c
            float r4 = r8.getBrightness()
            r5 = 1132396544(0x437f0000, float:255.0)
            float r5 = r5 * r4
            int r5 = (int) r5
            if (r3 == 0) goto L43
            android.graphics.PorterDuffColorFilter r2 = new android.graphics.PorterDuffColorFilter
            r3 = 255(0xff, float:3.57E-43)
            int r3 = android.graphics.Color.argb(r5, r3, r3, r3)
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_ATOP
            r2.<init>(r3, r4)
        L41:
            r4 = r2
            goto L79
        L43:
            float r3 = r8.getDesaturation()
            r6 = 1065353216(0x3f800000, float:1.0)
            float r3 = r6 - r3
            android.graphics.ColorMatrix r7 = com.android.launcher3.FastBitmapDrawable.sTempFilterMatrix
            r7.setSaturation(r3)
            int r3 = r8.mBrightness
            if (r3 <= 0) goto L73
            float r6 = r6 - r4
            android.graphics.ColorMatrix r3 = com.android.launcher3.FastBitmapDrawable.sTempBrightnessMatrix
            float[] r4 = r3.getArray()
            r4[r2] = r6
            r2 = 6
            r4[r2] = r6
            r2 = 12
            r4[r2] = r6
            r2 = 4
            float r5 = (float) r5
            r4[r2] = r5
            r2 = 9
            r4[r2] = r5
            r2 = 14
            r4[r2] = r5
            r7.preConcat(r3)
        L73:
            android.graphics.ColorMatrixColorFilter r2 = new android.graphics.ColorMatrixColorFilter
            r2.<init>(r7)
            goto L41
        L79:
            r1.append(r0, r4)
        L7c:
            android.graphics.Paint r0 = r8.mPaint
            r0.setColorFilter(r4)
            goto L88
        L82:
            android.graphics.Paint r0 = r8.mPaint
            r1 = 0
            r0.setColorFilter(r1)
        L88:
            r8.invalidateSelf()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.FastBitmapDrawable.updateFilter():void");
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return new MyConstantState(this.mBitmap, this.mIconColor, this.mIsDisabled);
    }

    protected static class MyConstantState extends Drawable.ConstantState {
        protected final Bitmap mBitmap;
        protected final int mIconColor;
        protected final boolean mIsDisabled;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        public MyConstantState(Bitmap bitmap, int color, boolean isDisabled) {
            this.mBitmap = bitmap;
            this.mIconColor = color;
            this.mIsDisabled = isDisabled;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new FastBitmapDrawable(this.mBitmap, this.mIconColor, this.mIsDisabled);
        }
    }
}
