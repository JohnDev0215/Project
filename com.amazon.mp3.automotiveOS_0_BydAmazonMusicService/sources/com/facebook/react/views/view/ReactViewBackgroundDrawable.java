package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class ReactViewBackgroundDrawable extends Drawable {

    @Nullable
    private Spacing mBorderAlpha;

    @Nullable
    private float[] mBorderCornerRadii;

    @Nullable
    private Spacing mBorderRGB;

    @Nullable
    private BorderStyle mBorderStyle;

    @Nullable
    private Spacing mBorderWidth;

    @Nullable
    private Path mCenterDrawPath;
    private final Context mContext;

    @Nullable
    private PointF mInnerBottomLeftCorner;

    @Nullable
    private PointF mInnerBottomRightCorner;

    @Nullable
    private Path mInnerClipPathForBorderRadius;

    @Nullable
    private RectF mInnerClipTempRectForBorderRadius;

    @Nullable
    private PointF mInnerTopLeftCorner;

    @Nullable
    private PointF mInnerTopRightCorner;
    private int mLayoutDirection;

    @Nullable
    private Path mOuterClipPathForBorderRadius;

    @Nullable
    private RectF mOuterClipTempRectForBorderRadius;

    @Nullable
    private Path mPathForBorder;

    @Nullable
    private Path mPathForBorderRadiusOutline;

    @Nullable
    private RectF mTempRectForBorderRadiusOutline;

    @Nullable
    private RectF mTempRectForCenterDrawPath;
    private final Path mPathForSingleBorder = new Path();
    private boolean mNeedUpdatePathForBorderRadius = false;
    private float mBorderRadius = Float.NaN;
    private final Paint mPaint = new Paint(1);
    private int mColor = 0;
    private int mAlpha = 255;

    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    private static int colorFromAlphaAndRGBComponents(float f, float f2) {
        return ((((int) f) << 24) & ViewCompat.MEASURED_STATE_MASK) | (((int) f2) & ViewCompat.MEASURED_SIZE_MASK);
    }

    private static int fastBorderCompatibleColorOrZero(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = (i4 > 0 ? i8 : -1) & (i > 0 ? i5 : -1) & (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1);
        if (i <= 0) {
            i5 = 0;
        }
        if (i2 <= 0) {
            i6 = 0;
        }
        int i10 = i5 | i6;
        if (i3 <= 0) {
            i7 = 0;
        }
        int i11 = i10 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        if (i9 == (i11 | i8)) {
            return i9;
        }
        return 0;
    }

    public boolean onResolvedLayoutDirectionChanged(int i) {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX INFO: renamed from: com.facebook.react.views.view.ReactViewBackgroundDrawable$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle;

        static {
            int[] iArr = new int[BorderStyle.values().length];
            $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle = iArr;
            try {
                iArr[BorderStyle.SOLID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[BorderStyle.DASHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[BorderStyle.DOTTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        @Nullable
        public static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
            int i = AnonymousClass1.$SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[borderStyle.ordinal()];
            if (i == 2) {
                float f2 = f * 3.0f;
                return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
            }
            if (i != 3) {
                return null;
            }
            return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
        }
    }

    public ReactViewBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        updatePathEffect();
        if (!hasRoundedBorders()) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    public boolean hasRoundedBorders() {
        if (!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) {
            return true;
        }
        float[] fArr = this.mBorderCornerRadii;
        if (fArr != null) {
            for (float f : fArr) {
                if (!YogaConstants.isUndefined(f) && f > 0.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if ((!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) || this.mBorderCornerRadii != null) {
            updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
        } else {
            outline.setRect(getBounds());
        }
    }

    public void setBorderWidth(int i, float f) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (FloatUtil.floatsEqual(this.mBorderWidth.getRaw(i), f)) {
            return;
        }
        this.mBorderWidth.set(i, f);
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
            this.mNeedUpdatePathForBorderRadius = true;
        }
        invalidateSelf();
    }

    public void setBorderColor(int i, float f, float f2) {
        setBorderRGB(i, f);
        setBorderAlpha(i, f2);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    private void setBorderRGB(int i, float f) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderRGB.getRaw(i), f)) {
            return;
        }
        this.mBorderRGB.set(i, f);
        invalidateSelf();
    }

    private void setBorderAlpha(int i, float f) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(i), f)) {
            return;
        }
        this.mBorderAlpha.set(i, f);
        invalidateSelf();
    }

    public void setBorderStyle(@Nullable String str) {
        BorderStyle borderStyleValueOf = str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != borderStyleValueOf) {
            this.mBorderStyle = borderStyleValueOf;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float f) {
        if (FloatUtil.floatsEqual(this.mBorderRadius, f)) {
            return;
        }
        this.mBorderRadius = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public void setRadius(float f, int i) {
        if (this.mBorderCornerRadii == null) {
            float[] fArr = new float[8];
            this.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (FloatUtil.floatsEqual(this.mBorderCornerRadii[i], f)) {
            return;
        }
        this.mBorderCornerRadii[i] = f;
        this.mNeedUpdatePathForBorderRadius = true;
        invalidateSelf();
    }

    public float getFullBorderRadius() {
        if (YogaConstants.isUndefined(this.mBorderRadius)) {
            return 0.0f;
        }
        return this.mBorderRadius;
    }

    public float getBorderRadius(BorderRadiusLocation borderRadiusLocation) {
        return getBorderRadiusOrDefaultTo(Float.NaN, borderRadiusLocation);
    }

    public float getBorderRadiusOrDefaultTo(float f, BorderRadiusLocation borderRadiusLocation) {
        float[] fArr = this.mBorderCornerRadii;
        if (fArr == null) {
            return f;
        }
        float f2 = fArr[borderRadiusLocation.ordinal()];
        return YogaConstants.isUndefined(f2) ? f : f2;
    }

    public void setColor(int i) {
        this.mColor = i;
        invalidateSelf();
    }

    public int getResolvedLayoutDirection() {
        return this.mLayoutDirection;
    }

    public boolean setResolvedLayoutDirection(int i) {
        if (this.mLayoutDirection == i) {
            return false;
        }
        this.mLayoutDirection = i;
        return onResolvedLayoutDirectionChanged(i);
    }

    public int getColor() {
        return this.mColor;
    }

    private void drawRoundedBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        updatePath();
        canvas.save();
        int iMultiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(iMultiplyColorAlpha) != 0) {
            this.mPaint.setColor(iMultiplyColorAlpha);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(this.mInnerClipPathForBorderRadius, this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int borderColor = getBorderColor(0);
        int borderColor2 = getBorderColor(1);
        int borderColor3 = getBorderColor(2);
        int borderColor4 = getBorderColor(3);
        if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.right > 0.0f) {
            float fullBorderWidth = getFullBorderWidth();
            int borderColor5 = getBorderColor(8);
            if (directionAwareBorderInsets.top != fullBorderWidth || directionAwareBorderInsets.bottom != fullBorderWidth || directionAwareBorderInsets.left != fullBorderWidth || directionAwareBorderInsets.right != fullBorderWidth || borderColor != borderColor5 || borderColor2 != borderColor5 || borderColor3 != borderColor5 || borderColor4 != borderColor5) {
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas.clipPath(this.mOuterClipPathForBorderRadius, Region.Op.INTERSECT);
                canvas.clipPath(this.mInnerClipPathForBorderRadius, Region.Op.DIFFERENCE);
                boolean z = getResolvedLayoutDirection() == 1;
                int borderColor6 = getBorderColor(4);
                int borderColor7 = getBorderColor(5);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                    if (isBorderColorDefined(4)) {
                        borderColor = borderColor6;
                    }
                    if (isBorderColorDefined(5)) {
                        borderColor3 = borderColor7;
                    }
                    i = z ? borderColor3 : borderColor;
                    if (!z) {
                        borderColor = borderColor3;
                    }
                    i2 = borderColor;
                } else {
                    int i3 = z ? borderColor7 : borderColor6;
                    if (!z) {
                        borderColor6 = borderColor7;
                    }
                    boolean zIsBorderColorDefined = isBorderColorDefined(4);
                    boolean zIsBorderColorDefined2 = isBorderColorDefined(5);
                    boolean z2 = z ? zIsBorderColorDefined2 : zIsBorderColorDefined;
                    if (!z) {
                        zIsBorderColorDefined = zIsBorderColorDefined2;
                    }
                    if (z2) {
                        borderColor = i3;
                    }
                    if (zIsBorderColorDefined) {
                        i = borderColor;
                        i2 = borderColor6;
                    } else {
                        i = borderColor;
                        i2 = borderColor3;
                    }
                }
                RectF rectF = this.mOuterClipTempRectForBorderRadius;
                float f5 = rectF.left;
                float f6 = rectF.right;
                float f7 = rectF.top;
                float f8 = rectF.bottom;
                if (directionAwareBorderInsets.left > 0.0f) {
                    PointF pointF = this.mInnerTopLeftCorner;
                    float f9 = pointF.x;
                    float f10 = pointF.y;
                    PointF pointF2 = this.mInnerBottomLeftCorner;
                    f = f8;
                    f2 = f7;
                    f3 = f6;
                    f4 = f5;
                    drawQuadrilateral(canvas, i, f5, f7, f9, f10, pointF2.x, pointF2.y, f5, f);
                } else {
                    f = f8;
                    f2 = f7;
                    f3 = f6;
                    f4 = f5;
                }
                if (directionAwareBorderInsets.top > 0.0f) {
                    PointF pointF3 = this.mInnerTopLeftCorner;
                    float f11 = pointF3.x;
                    float f12 = pointF3.y;
                    PointF pointF4 = this.mInnerTopRightCorner;
                    drawQuadrilateral(canvas, borderColor2, f4, f2, f11, f12, pointF4.x, pointF4.y, f3, f2);
                }
                if (directionAwareBorderInsets.right > 0.0f) {
                    PointF pointF5 = this.mInnerTopRightCorner;
                    float f13 = pointF5.x;
                    float f14 = pointF5.y;
                    PointF pointF6 = this.mInnerBottomRightCorner;
                    drawQuadrilateral(canvas, i2, f3, f2, f13, f14, pointF6.x, pointF6.y, f3, f);
                }
                if (directionAwareBorderInsets.bottom > 0.0f) {
                    PointF pointF7 = this.mInnerBottomLeftCorner;
                    float f15 = pointF7.x;
                    float f16 = pointF7.y;
                    PointF pointF8 = this.mInnerBottomRightCorner;
                    drawQuadrilateral(canvas, borderColor4, f4, f, f15, f16, pointF8.x, pointF8.y, f3, f);
                }
            } else if (fullBorderWidth > 0.0f) {
                this.mPaint.setColor(ColorUtil.multiplyColorAlpha(borderColor5, this.mAlpha));
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(fullBorderWidth);
                canvas.drawPath(this.mCenterDrawPath, this.mPaint);
            }
        }
        canvas.restore();
    }

    private void updatePath() {
        float f;
        float f2;
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(8);
            if (Color.alpha(borderColor) != 0 && Color.alpha(borderColor2) != 0 && Color.alpha(borderColor3) != 0 && Color.alpha(borderColor4) != 0 && Color.alpha(borderColor5) != 0) {
                RectF rectF = this.mInnerClipTempRectForBorderRadius;
                rectF.top += directionAwareBorderInsets.top;
                rectF.bottom -= directionAwareBorderInsets.bottom;
                rectF.left += directionAwareBorderInsets.left;
                rectF.right -= directionAwareBorderInsets.right;
            }
            RectF rectF2 = this.mTempRectForCenterDrawPath;
            rectF2.top += directionAwareBorderInsets.top * 0.5f;
            rectF2.bottom -= directionAwareBorderInsets.bottom * 0.5f;
            rectF2.left += directionAwareBorderInsets.left * 0.5f;
            rectF2.right -= directionAwareBorderInsets.right * 0.5f;
            float fullBorderRadius = getFullBorderRadius();
            float borderRadiusOrDefaultTo = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_LEFT);
            float borderRadiusOrDefaultTo2 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_RIGHT);
            float borderRadiusOrDefaultTo3 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_LEFT);
            float borderRadiusOrDefaultTo4 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_RIGHT);
            boolean z = getResolvedLayoutDirection() == 1;
            float borderRadius = getBorderRadius(BorderRadiusLocation.TOP_START);
            float borderRadius2 = getBorderRadius(BorderRadiusLocation.TOP_END);
            float borderRadius3 = getBorderRadius(BorderRadiusLocation.BOTTOM_START);
            float borderRadius4 = getBorderRadius(BorderRadiusLocation.BOTTOM_END);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo = borderRadius;
                }
                if (!YogaConstants.isUndefined(borderRadius2)) {
                    borderRadiusOrDefaultTo2 = borderRadius2;
                }
                if (!YogaConstants.isUndefined(borderRadius3)) {
                    borderRadiusOrDefaultTo3 = borderRadius3;
                }
                if (!YogaConstants.isUndefined(borderRadius4)) {
                    borderRadiusOrDefaultTo4 = borderRadius4;
                }
                f = z ? borderRadiusOrDefaultTo2 : borderRadiusOrDefaultTo;
                if (!z) {
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                }
                f2 = z ? borderRadiusOrDefaultTo4 : borderRadiusOrDefaultTo3;
                if (z) {
                    borderRadiusOrDefaultTo4 = borderRadiusOrDefaultTo3;
                }
            } else {
                float f3 = z ? borderRadius2 : borderRadius;
                if (!z) {
                    borderRadius = borderRadius2;
                }
                float f4 = z ? borderRadius4 : borderRadius3;
                if (!z) {
                    borderRadius3 = borderRadius4;
                }
                if (!YogaConstants.isUndefined(f3)) {
                    borderRadiusOrDefaultTo = f3;
                }
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo2 = borderRadius;
                }
                if (!YogaConstants.isUndefined(f4)) {
                    borderRadiusOrDefaultTo3 = f4;
                }
                if (YogaConstants.isUndefined(borderRadius3)) {
                    f = borderRadiusOrDefaultTo;
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                    f2 = borderRadiusOrDefaultTo3;
                } else {
                    f = borderRadiusOrDefaultTo;
                    borderRadiusOrDefaultTo = borderRadiusOrDefaultTo2;
                    f2 = borderRadiusOrDefaultTo3;
                    borderRadiusOrDefaultTo4 = borderRadius3;
                }
            }
            float f5 = f2;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{Math.max(f - directionAwareBorderInsets.left, 0.0f), Math.max(f - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.bottom, 0.0f), Math.max(f2 - directionAwareBorderInsets.left, 0.0f), Math.max(f2 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{f, f, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo, borderRadiusOrDefaultTo4, borderRadiusOrDefaultTo4, f5, f5}, Path.Direction.CW);
            Spacing spacing = this.mBorderWidth;
            float f6 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            float f7 = f + f6;
            float f8 = borderRadiusOrDefaultTo + f6;
            float f9 = borderRadiusOrDefaultTo4 + f6;
            float f10 = f5 + f6;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f7, f7, f8, f8, f9, f9, f10, f10}, Path.Direction.CW);
            Path path = this.mCenterDrawPath;
            RectF rectF3 = this.mTempRectForCenterDrawPath;
            float[] fArr = new float[8];
            float f11 = directionAwareBorderInsets.left;
            fArr[0] = Math.max(f - (f11 * 0.5f), f11 > 0.0f ? f / f11 : 0.0f);
            float f12 = directionAwareBorderInsets.top;
            fArr[1] = Math.max(f - (f12 * 0.5f), f12 > 0.0f ? f / f12 : 0.0f);
            float f13 = directionAwareBorderInsets.right;
            fArr[2] = Math.max(borderRadiusOrDefaultTo - (f13 * 0.5f), f13 > 0.0f ? borderRadiusOrDefaultTo / f13 : 0.0f);
            float f14 = directionAwareBorderInsets.top;
            fArr[3] = Math.max(borderRadiusOrDefaultTo - (f14 * 0.5f), f14 > 0.0f ? borderRadiusOrDefaultTo / f14 : 0.0f);
            float f15 = directionAwareBorderInsets.right;
            fArr[4] = Math.max(borderRadiusOrDefaultTo4 - (f15 * 0.5f), f15 > 0.0f ? borderRadiusOrDefaultTo4 / f15 : 0.0f);
            float f16 = directionAwareBorderInsets.bottom;
            fArr[5] = Math.max(borderRadiusOrDefaultTo4 - (f16 * 0.5f), f16 > 0.0f ? borderRadiusOrDefaultTo4 / f16 : 0.0f);
            float f17 = directionAwareBorderInsets.left;
            fArr[6] = Math.max(f5 - (f17 * 0.5f), f17 > 0.0f ? f5 / f17 : 0.0f);
            float f18 = directionAwareBorderInsets.bottom;
            fArr[7] = Math.max(f5 - (f18 * 0.5f), f18 > 0.0f ? f5 / f18 : 0.0f);
            path.addRoundRect(rectF3, fArr, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            PointF pointF = this.mInnerTopLeftCorner;
            RectF rectF4 = this.mInnerClipTempRectForBorderRadius;
            float f19 = rectF4.left;
            pointF.x = f19;
            float f20 = rectF4.top;
            pointF.y = f20 * 2.0f;
            RectF rectF5 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f19, f20, (r8 * 2.0f) + f19, (r11 * 2.0f) + f20, rectF5.left, rectF5.top, f19, f20, pointF);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            PointF pointF2 = this.mInnerBottomLeftCorner;
            RectF rectF6 = this.mInnerClipTempRectForBorderRadius;
            float f21 = rectF6.left;
            pointF2.x = f21;
            float f22 = rectF6.bottom;
            pointF2.y = f22 * (-2.0f);
            RectF rectF7 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f21, f22 - (r1 * 2.0f), (r4 * 2.0f) + f21, f22, rectF7.left, rectF7.bottom, f21, f22, pointF2);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            PointF pointF3 = this.mInnerTopRightCorner;
            RectF rectF8 = this.mInnerClipTempRectForBorderRadius;
            float f23 = rectF8.right;
            pointF3.x = f23;
            float f24 = rectF8.top;
            pointF3.y = f24 * 2.0f;
            RectF rectF9 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f23 - (r12 * 2.0f), f24, f23, (r13 * 2.0f) + f24, rectF9.right, rectF9.top, f23, f24, pointF3);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            PointF pointF4 = this.mInnerBottomRightCorner;
            RectF rectF10 = this.mInnerClipTempRectForBorderRadius;
            float f25 = rectF10.right;
            pointF4.x = f25;
            float f26 = rectF10.bottom;
            pointF4.y = (-2.0f) * f26;
            RectF rectF11 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine(f25 - (r14 * 2.0f), f26 - (r15 * 2.0f), f25, f26, rectF11.right, rectF11.bottom, f25, f26, pointF4);
        }
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double dAbs = Math.abs(d3 - d) / 2.0d;
        double dAbs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = dAbs2 * dAbs2;
        double d16 = dAbs * dAbs;
        double d17 = d15 + (d16 * d13 * d13);
        double d18 = dAbs * 2.0d * dAbs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double dSqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = (d13 * dSqrt) + d14;
        double d22 = dSqrt + d9;
        double d23 = d21 + d10;
        if (Double.isNaN(d22) || Double.isNaN(d23)) {
            return;
        }
        pointF.x = (float) d22;
        pointF.y = (float) d23;
    }

    public float getBorderWidthOrDefaultTo(float f, int i) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return f;
        }
        float raw = spacing.getRaw(i);
        return YogaConstants.isUndefined(raw) ? f : raw;
    }

    private void updatePathEffect() {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, getFullBorderWidth()) : null);
    }

    private void updatePathEffect(int i) {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, i) : null);
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || YogaConstants.isUndefined(spacing.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    private void drawRectangularBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        int i3;
        this.mPaint.setStyle(Paint.Style.FILL);
        int iMultiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(iMultiplyColorAlpha) != 0) {
            this.mPaint.setColor(iMultiplyColorAlpha);
            canvas.drawRect(getBounds(), this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int iRound = Math.round(directionAwareBorderInsets.left);
        int iRound2 = Math.round(directionAwareBorderInsets.top);
        int iRound3 = Math.round(directionAwareBorderInsets.right);
        int iRound4 = Math.round(directionAwareBorderInsets.bottom);
        if (iRound > 0 || iRound3 > 0 || iRound2 > 0 || iRound4 > 0) {
            Rect bounds = getBounds();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            boolean z = getResolvedLayoutDirection() == 1;
            int borderColor5 = getBorderColor(4);
            int borderColor6 = getBorderColor(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (isBorderColorDefined(4)) {
                    borderColor = borderColor5;
                }
                if (isBorderColorDefined(5)) {
                    borderColor3 = borderColor6;
                }
                int i4 = z ? borderColor3 : borderColor;
                if (!z) {
                    borderColor = borderColor3;
                }
                i2 = borderColor;
                i = i4;
            } else {
                int i5 = z ? borderColor6 : borderColor5;
                if (!z) {
                    borderColor5 = borderColor6;
                }
                boolean zIsBorderColorDefined = isBorderColorDefined(4);
                boolean zIsBorderColorDefined2 = isBorderColorDefined(5);
                boolean z2 = z ? zIsBorderColorDefined2 : zIsBorderColorDefined;
                if (!z) {
                    zIsBorderColorDefined = zIsBorderColorDefined2;
                }
                if (z2) {
                    borderColor = i5;
                }
                i = borderColor;
                i2 = zIsBorderColorDefined ? borderColor5 : borderColor3;
            }
            int i6 = bounds.left;
            int i7 = bounds.top;
            int iFastBorderCompatibleColorOrZero = fastBorderCompatibleColorOrZero(iRound, iRound2, iRound3, iRound4, i, borderColor2, i2, borderColor4);
            if (iFastBorderCompatibleColorOrZero != 0) {
                if (Color.alpha(iFastBorderCompatibleColorOrZero) != 0) {
                    int i8 = bounds.right;
                    int i9 = bounds.bottom;
                    this.mPaint.setColor(iFastBorderCompatibleColorOrZero);
                    this.mPaint.setStyle(Paint.Style.STROKE);
                    if (iRound > 0) {
                        this.mPathForSingleBorder.reset();
                        int iRound5 = Math.round(directionAwareBorderInsets.left);
                        updatePathEffect(iRound5);
                        this.mPaint.setStrokeWidth(iRound5);
                        float f = i6 + (iRound5 / 2);
                        this.mPathForSingleBorder.moveTo(f, i7);
                        this.mPathForSingleBorder.lineTo(f, i9);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (iRound2 > 0) {
                        this.mPathForSingleBorder.reset();
                        int iRound6 = Math.round(directionAwareBorderInsets.top);
                        updatePathEffect(iRound6);
                        this.mPaint.setStrokeWidth(iRound6);
                        float f2 = i7 + (iRound6 / 2);
                        this.mPathForSingleBorder.moveTo(i6, f2);
                        this.mPathForSingleBorder.lineTo(i8, f2);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (iRound3 > 0) {
                        this.mPathForSingleBorder.reset();
                        int iRound7 = Math.round(directionAwareBorderInsets.right);
                        updatePathEffect(iRound7);
                        this.mPaint.setStrokeWidth(iRound7);
                        float f3 = i8 - (iRound7 / 2);
                        this.mPathForSingleBorder.moveTo(f3, i7);
                        this.mPathForSingleBorder.lineTo(f3, i9);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                    }
                    if (iRound4 > 0) {
                        this.mPathForSingleBorder.reset();
                        int iRound8 = Math.round(directionAwareBorderInsets.bottom);
                        updatePathEffect(iRound8);
                        this.mPaint.setStrokeWidth(iRound8);
                        float f4 = i9 - (iRound8 / 2);
                        this.mPathForSingleBorder.moveTo(i6, f4);
                        this.mPathForSingleBorder.lineTo(i8, f4);
                        canvas.drawPath(this.mPathForSingleBorder, this.mPaint);
                        return;
                    }
                    return;
                }
                return;
            }
            this.mPaint.setAntiAlias(false);
            int iWidth = bounds.width();
            int iHeight = bounds.height();
            if (iRound > 0) {
                float f5 = i6;
                float f6 = i6 + iRound;
                i3 = i7;
                drawQuadrilateral(canvas, i, f5, i7, f6, i7 + iRound2, f6, r8 - iRound4, f5, i7 + iHeight);
            } else {
                i3 = i7;
            }
            if (iRound2 > 0) {
                float f7 = i3;
                float f8 = i3 + iRound2;
                drawQuadrilateral(canvas, borderColor2, i6, f7, i6 + iRound, f8, r9 - iRound3, f8, i6 + iWidth, f7);
            }
            if (iRound3 > 0) {
                int i10 = i6 + iWidth;
                float f9 = i10;
                float f10 = i10 - iRound3;
                drawQuadrilateral(canvas, i2, f9, i3, f9, i3 + iHeight, f10, r8 - iRound4, f10, i3 + iRound2);
            }
            if (iRound4 > 0) {
                int i11 = i3 + iHeight;
                float f11 = i11;
                float f12 = i11 - iRound4;
                drawQuadrilateral(canvas, borderColor4, i6, f11, i6 + iWidth, f11, r9 - iRound3, f12, i6 + iRound, f12);
            }
            this.mPaint.setAntiAlias(true);
        }
    }

    private void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i == 0) {
            return;
        }
        if (this.mPathForBorder == null) {
            this.mPathForBorder = new Path();
        }
        this.mPaint.setColor(i);
        this.mPathForBorder.reset();
        this.mPathForBorder.moveTo(f, f2);
        this.mPathForBorder.lineTo(f3, f4);
        this.mPathForBorder.lineTo(f5, f6);
        this.mPathForBorder.lineTo(f7, f8);
        this.mPathForBorder.lineTo(f, f2);
        canvas.drawPath(this.mPathForBorder, this.mPaint);
    }

    private boolean isBorderColorDefined(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(i) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        return (YogaConstants.isUndefined(f) || YogaConstants.isUndefined(spacing2 != null ? spacing2.get(i) : Float.NaN)) ? false : true;
    }

    public int getBorderColor(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(i) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return colorFromAlphaAndRGBComponents(spacing2 != null ? spacing2.get(i) : 255.0f, f);
    }

    @TargetApi(21)
    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (this.mBorderWidth != null) {
            boolean z = getResolvedLayoutDirection() == 1;
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo4 = raw;
                }
                if (!YogaConstants.isUndefined(raw2)) {
                    borderWidthOrDefaultTo5 = raw2;
                }
                float f = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
                borderWidthOrDefaultTo4 = f;
            } else {
                float f2 = z ? raw2 : raw;
                if (!z) {
                    raw = raw2;
                }
                if (!YogaConstants.isUndefined(f2)) {
                    borderWidthOrDefaultTo4 = f2;
                }
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo5 = raw;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }
}
