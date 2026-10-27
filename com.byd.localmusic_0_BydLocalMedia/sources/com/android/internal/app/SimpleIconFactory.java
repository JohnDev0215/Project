package com.android.internal.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.Pools;
import android.util.TypedValue;
import com.android.internal.R;
import java.nio.ByteBuffer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class SimpleIconFactory {
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    private static final float CIRCLE_AREA_BY_RECT = 0.7853982f;
    private static final int DEFAULT_WRAPPER_BACKGROUND = -1;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_DISTANCE = 0.020833334f;
    private static final float LINEAR_SCALE_SLOPE = 0.040449437f;
    private static final float MAX_CIRCLE_AREA_FACTOR = 0.6597222f;
    private static final float MAX_SQUARE_AREA_FACTOR = 0.6510417f;
    private static final int MIN_VISIBLE_ALPHA = 40;
    private static final float SCALE_NOT_INITIALIZED = 0.0f;
    private static final Pools.SynchronizedPool<SimpleIconFactory> sPool = new Pools.SynchronizedPool<>(Runtime.getRuntime().availableProcessors());
    private final Rect mAdaptiveIconBounds;
    private float mAdaptiveIconScale;
    private int mBadgeBitmapSize;
    private final Bitmap mBitmap;
    private final Rect mBounds;
    private Canvas mCanvas;
    private Context mContext;
    private BlurMaskFilter mDefaultBlurMaskFilter;
    private int mFillResIconDpi;
    private int mIconBitmapSize;
    private final float[] mLeftBorder;
    private final int mMaxSize;
    private final byte[] mPixels;
    private PackageManager mPm;
    private final float[] mRightBorder;
    private final Canvas mScaleCheckCanvas;
    private int mWrapperBackgroundColor;
    private Drawable mWrapperIcon;
    private final Rect mOldBounds = new Rect();
    private Paint mBlurPaint = new Paint(3);
    private Paint mDrawPaint = new Paint(3);

    @Deprecated
    public static SimpleIconFactory obtain(Context context) {
        SimpleIconFactory simpleIconFactoryAcquire = sPool.acquire();
        if (simpleIconFactoryAcquire != null) {
            return simpleIconFactoryAcquire;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        SimpleIconFactory simpleIconFactory = new SimpleIconFactory(context, activityManager == null ? 0 : activityManager.getLauncherLargeIconDensity(), getIconSizeFromContext(context), getBadgeSizeFromContext(context));
        simpleIconFactory.setWrapperBackgroundColor(-1);
        return simpleIconFactory;
    }

    private static int getAttrDimFromContext(Context context, int i, String str) {
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            throw new IllegalStateException(str);
        }
        return resources.getDimensionPixelSize(typedValue.resourceId);
    }

    private static int getIconSizeFromContext(Context context) {
        return getAttrDimFromContext(context, R.attr.iconfactoryIconSize, "Expected theme to define iconfactoryIconSize.");
    }

    private static int getBadgeSizeFromContext(Context context) {
        return getAttrDimFromContext(context, R.attr.iconfactoryBadgeSize, "Expected theme to define iconfactoryBadgeSize.");
    }

    @Deprecated
    public void recycle() {
        setWrapperBackgroundColor(-1);
        sPool.release(this);
    }

    @Deprecated
    private SimpleIconFactory(Context context, int i, int i2, int i3) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPm = applicationContext.getPackageManager();
        this.mIconBitmapSize = i2;
        this.mBadgeBitmapSize = i3;
        this.mFillResIconDpi = i;
        Canvas canvas = new Canvas();
        this.mCanvas = canvas;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
        int i4 = i2 * 2;
        this.mMaxSize = i4;
        this.mBitmap = Bitmap.createBitmap(i4, i4, Bitmap.Config.ALPHA_8);
        this.mScaleCheckCanvas = new Canvas(this.mBitmap);
        int i5 = this.mMaxSize;
        this.mPixels = new byte[i5 * i5];
        this.mLeftBorder = new float[i5];
        this.mRightBorder = new float[i5];
        this.mBounds = new Rect();
        this.mAdaptiveIconBounds = new Rect();
        this.mAdaptiveIconScale = 0.0f;
        this.mDefaultBlurMaskFilter = new BlurMaskFilter(i2 * BLUR_FACTOR, BlurMaskFilter.Blur.NORMAL);
    }

    @Deprecated
    void setWrapperBackgroundColor(int i) {
        if (Color.alpha(i) < 255) {
            i = -1;
        }
        this.mWrapperBackgroundColor = i;
    }

    @Deprecated
    Bitmap createUserBadgedIconBitmap(Drawable drawable, UserHandle userHandle) {
        Bitmap bitmapCreateIconBitmap;
        float[] fArr = new float[1];
        if (drawable == null) {
            drawable = getFullResDefaultActivityIcon(this.mFillResIconDpi);
        }
        Drawable drawableNormalizeAndWrapToAdaptiveIcon = normalizeAndWrapToAdaptiveIcon(drawable, null, fArr);
        Bitmap bitmapCreateIconBitmap2 = createIconBitmap(drawableNormalizeAndWrapToAdaptiveIcon, fArr[0]);
        if (drawableNormalizeAndWrapToAdaptiveIcon instanceof AdaptiveIconDrawable) {
            this.mCanvas.setBitmap(bitmapCreateIconBitmap2);
            recreateIcon(Bitmap.createBitmap(bitmapCreateIconBitmap2), this.mCanvas);
            this.mCanvas.setBitmap(null);
        }
        if (userHandle == null) {
            return bitmapCreateIconBitmap2;
        }
        Drawable userBadgedIcon = this.mPm.getUserBadgedIcon(new FixedSizeBitmapDrawable(bitmapCreateIconBitmap2), userHandle);
        if (userBadgedIcon instanceof BitmapDrawable) {
            bitmapCreateIconBitmap = ((BitmapDrawable) userBadgedIcon).getBitmap();
        } else {
            bitmapCreateIconBitmap = createIconBitmap(userBadgedIcon, 1.0f);
        }
        return bitmapCreateIconBitmap;
    }

    @Deprecated
    public Bitmap createAppBadgedIconBitmap(Drawable drawable, Bitmap bitmap) {
        if (drawable == null) {
            drawable = getFullResDefaultActivityIcon(this.mFillResIconDpi);
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f = 1.0f;
        if (intrinsicHeight > intrinsicWidth && intrinsicWidth > 0) {
            f = intrinsicHeight / intrinsicWidth;
        } else if (intrinsicWidth > intrinsicHeight && intrinsicHeight > 0) {
            f = intrinsicWidth / intrinsicHeight;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), maskBitmapToCircle(createIconBitmapNoInsetOrMask(drawable, f)));
        Bitmap bitmapCreateIconBitmap = createIconBitmap(bitmapDrawable, getScale(bitmapDrawable, null));
        this.mCanvas.setBitmap(bitmapCreateIconBitmap);
        recreateIcon(Bitmap.createBitmap(bitmapCreateIconBitmap), this.mCanvas);
        if (bitmap != null) {
            int i = this.mBadgeBitmapSize;
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
            Canvas canvas = this.mCanvas;
            int i2 = this.mIconBitmapSize;
            int i3 = this.mBadgeBitmapSize;
            canvas.drawBitmap(bitmapCreateScaledBitmap, i2 - i3, i2 - i3, (Paint) null);
        }
        this.mCanvas.setBitmap(null);
        return bitmapCreateIconBitmap;
    }

    private Bitmap maskBitmapToCircle(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(7);
        int iMax = Math.max((int) Math.ceil(bitmap.getWidth() * BLUR_FACTOR), 1);
        paint.setColor(-1);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f, (bitmap.getWidth() / 2.0f) - iMax, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    private static Drawable getFullResDefaultActivityIcon(int i) {
        return Resources.getSystem().getDrawableForDensity(17629184, i);
    }

    private Bitmap createIconBitmap(Drawable drawable, float f) {
        return createIconBitmap(drawable, f, this.mIconBitmapSize, true, false);
    }

    private Bitmap createIconBitmapNoInsetOrMask(Drawable drawable, float f) {
        return createIconBitmap(drawable, f, this.mIconBitmapSize, false, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap createIconBitmap(android.graphics.drawable.Drawable r8, float r9, int r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.SimpleIconFactory.createIconBitmap(android.graphics.drawable.Drawable, float, int, boolean, boolean):android.graphics.Bitmap");
    }

    public /* synthetic */ void lambda$createIconBitmap$0$SimpleIconFactory(Rect rect, Drawable drawable) {
        drawable.setBounds(rect);
        drawable.draw(this.mCanvas);
    }

    public /* synthetic */ void lambda$createIconBitmap$1$SimpleIconFactory(Rect rect, Drawable drawable) {
        drawable.setBounds(rect);
        drawable.draw(this.mCanvas);
    }

    private Drawable normalizeAndWrapToAdaptiveIcon(Drawable drawable, RectF rectF, float[] fArr) {
        if (this.mWrapperIcon == null) {
            this.mWrapperIcon = this.mContext.getDrawable(R.drawable.iconfactory_adaptive_icon_drawable_wrapper).mutate();
        }
        AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) this.mWrapperIcon;
        adaptiveIconDrawable.setBounds(0, 0, 1, 1);
        float scale = getScale(drawable, rectF);
        if (!(drawable instanceof AdaptiveIconDrawable)) {
            FixedScaleDrawable fixedScaleDrawable = (FixedScaleDrawable) adaptiveIconDrawable.getForeground();
            fixedScaleDrawable.setDrawable(drawable);
            fixedScaleDrawable.setScale(scale);
            scale = getScale(adaptiveIconDrawable, rectF);
            ((ColorDrawable) adaptiveIconDrawable.getBackground()).setColor(this.mWrapperBackgroundColor);
            drawable = adaptiveIconDrawable;
        }
        fArr[0] = scale;
        return drawable;
    }

    private synchronized float getScale(Drawable drawable, RectF rectF) {
        if ((drawable instanceof AdaptiveIconDrawable) && this.mAdaptiveIconScale != 0.0f) {
            if (rectF != null) {
                rectF.set(this.mAdaptiveIconBounds);
            }
            return this.mAdaptiveIconScale;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            if (intrinsicWidth <= 0 || intrinsicWidth > this.mMaxSize) {
                intrinsicWidth = this.mMaxSize;
            }
            if (intrinsicHeight <= 0 || intrinsicHeight > this.mMaxSize) {
                intrinsicHeight = this.mMaxSize;
            }
        } else if (intrinsicWidth > this.mMaxSize || intrinsicHeight > this.mMaxSize) {
            int iMax = Math.max(intrinsicWidth, intrinsicHeight);
            intrinsicWidth = (this.mMaxSize * intrinsicWidth) / iMax;
            intrinsicHeight = (this.mMaxSize * intrinsicHeight) / iMax;
        }
        int i = 0;
        this.mBitmap.eraseColor(0);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(this.mScaleCheckCanvas);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.mPixels);
        byteBufferWrap.rewind();
        this.mBitmap.copyPixelsToBuffer(byteBufferWrap);
        int i2 = this.mMaxSize + 1;
        int i3 = this.mMaxSize - intrinsicWidth;
        int i4 = 0;
        int i5 = 0;
        int i6 = -1;
        int iMax2 = -1;
        int i7 = -1;
        while (i4 < intrinsicHeight) {
            int i8 = -1;
            int i9 = -1;
            while (i < intrinsicWidth) {
                if ((this.mPixels[i5] & 255) > 40) {
                    if (i8 == -1) {
                        i8 = i;
                    }
                    i9 = i;
                }
                i5++;
                i++;
            }
            i5 += i3;
            this.mLeftBorder[i4] = i8;
            this.mRightBorder[i4] = i9;
            if (i8 != -1) {
                if (i6 == -1) {
                    i6 = i4;
                }
                int iMin = Math.min(i2, i8);
                iMax2 = Math.max(iMax2, i9);
                i2 = iMin;
                i7 = i4;
            }
            i4++;
            i = 0;
        }
        if (i6 != -1 && iMax2 != -1) {
            convertToConvexArray(this.mLeftBorder, 1, i6, i7);
            convertToConvexArray(this.mRightBorder, -1, i6, i7);
            float f = 0.0f;
            for (int i10 = 0; i10 < intrinsicHeight; i10++) {
                if (this.mLeftBorder[i10] > -1.0f) {
                    f += (this.mRightBorder[i10] - this.mLeftBorder[i10]) + 1.0f;
                }
            }
            float f2 = f / (((i7 + 1) - i6) * ((iMax2 + 1) - i2));
            float f3 = f2 < CIRCLE_AREA_BY_RECT ? MAX_CIRCLE_AREA_FACTOR : ((1.0f - f2) * LINEAR_SCALE_SLOPE) + MAX_SQUARE_AREA_FACTOR;
            this.mBounds.left = i2;
            this.mBounds.right = iMax2;
            this.mBounds.top = i6;
            this.mBounds.bottom = i7;
            if (rectF != null) {
                float f4 = intrinsicWidth;
                float f5 = intrinsicHeight;
                rectF.set(this.mBounds.left / f4, this.mBounds.top / f5, 1.0f - (this.mBounds.right / f4), 1.0f - (this.mBounds.bottom / f5));
            }
            float fSqrt = f / (intrinsicWidth * intrinsicHeight) > f3 ? (float) Math.sqrt(f3 / r4) : 1.0f;
            if ((drawable instanceof AdaptiveIconDrawable) && this.mAdaptiveIconScale == 0.0f) {
                this.mAdaptiveIconScale = fSqrt;
                this.mAdaptiveIconBounds.set(this.mBounds);
            }
            return fSqrt;
        }
        return 1.0f;
    }

    private static void convertToConvexArray(float[] fArr, int i, int i2, int i3) {
        float[] fArr2 = new float[fArr.length - 1];
        int i4 = -1;
        float f = Float.MAX_VALUE;
        for (int i5 = i2 + 1; i5 <= i3; i5++) {
            if (fArr[i5] > -1.0f) {
                if (f == Float.MAX_VALUE) {
                    i4 = i2;
                } else {
                    float f2 = ((fArr[i5] - fArr[i4]) / (i5 - i4)) - f;
                    float f3 = i;
                    if (f2 * f3 < 0.0f) {
                        while (i4 > i2) {
                            i4--;
                            if ((((fArr[i5] - fArr[i4]) / (i5 - i4)) - fArr2[i4]) * f3 >= 0.0f) {
                                break;
                            }
                        }
                    }
                }
                f = (fArr[i5] - fArr[i4]) / (i5 - i4);
                for (int i6 = i4; i6 < i5; i6++) {
                    fArr2[i6] = f;
                    fArr[i6] = fArr[i4] + ((i6 - i4) * f);
                }
                i4 = i5;
            }
        }
    }

    private synchronized void recreateIcon(Bitmap bitmap, Canvas canvas) {
        recreateIcon(bitmap, this.mDefaultBlurMaskFilter, 30, 61, canvas);
    }

    private synchronized void recreateIcon(Bitmap bitmap, BlurMaskFilter blurMaskFilter, int i, int i2, Canvas canvas) {
        this.mBlurPaint.setMaskFilter(blurMaskFilter);
        Bitmap bitmapExtractAlpha = bitmap.extractAlpha(this.mBlurPaint, new int[2]);
        this.mDrawPaint.setAlpha(i);
        canvas.drawBitmap(bitmapExtractAlpha, r0[0], r0[1], this.mDrawPaint);
        this.mDrawPaint.setAlpha(i2);
        canvas.drawBitmap(bitmapExtractAlpha, r0[0], r0[1] + (this.mIconBitmapSize * KEY_SHADOW_DISTANCE), this.mDrawPaint);
        this.mDrawPaint.setAlpha(255);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mDrawPaint);
    }

    public static class FixedScaleDrawable extends DrawableWrapper {
        private static final float LEGACY_ICON_SCALE = 0.46669f;
        private float mScaleX;
        private float mScaleY;

        @Override // android.graphics.drawable.Drawable
        public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        }

        public FixedScaleDrawable() {
            super(new ColorDrawable());
            this.mScaleX = LEGACY_ICON_SCALE;
            this.mScaleY = LEGACY_ICON_SCALE;
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            int iSave = canvas.save();
            canvas.scale(this.mScaleX, this.mScaleY, getBounds().exactCenterX(), getBounds().exactCenterY());
            super.draw(canvas);
            canvas.restoreToCount(iSave);
        }

        public void setScale(float f) {
            float intrinsicHeight = getIntrinsicHeight();
            float intrinsicWidth = getIntrinsicWidth();
            float f2 = f * LEGACY_ICON_SCALE;
            this.mScaleX = f2;
            this.mScaleY = f2;
            if (intrinsicHeight > intrinsicWidth && intrinsicWidth > 0.0f) {
                this.mScaleX = f2 * (intrinsicWidth / intrinsicHeight);
            } else {
                if (intrinsicWidth <= intrinsicHeight || intrinsicHeight <= 0.0f) {
                    return;
                }
                this.mScaleY *= intrinsicHeight / intrinsicWidth;
            }
        }
    }

    private static class FixedSizeBitmapDrawable extends BitmapDrawable {
        FixedSizeBitmapDrawable(Bitmap bitmap) {
            super((Resources) null, bitmap);
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return getBitmap().getWidth();
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return getBitmap().getWidth();
        }
    }
}
