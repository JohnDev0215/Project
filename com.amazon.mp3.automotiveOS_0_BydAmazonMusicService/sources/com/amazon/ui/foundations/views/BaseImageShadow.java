package com.amazon.ui.foundations.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amazon.ui.foundations.R$color;
import com.amazon.ui.foundations.R$dimen;
import com.amazon.ui.foundations.styles.ShadowStyle;
import com.amazon.ui.foundations.utils.FixDimensionType;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseImageShadow.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\rJ\u0016\u0010 \u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006$"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseImageShadow;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cornerRadius", "fixedDimensionType", "Lcom/amazon/ui/foundations/utils/FixDimensionType;", "height", "", "Ljava/lang/Float;", "mPaint", "Landroid/graphics/Paint;", "rect", "Landroid/graphics/RectF;", "scalingFactor", "shadowColor", "shadowX", "shadowY", "width", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "setCornerRadius", "setFixDimensionType", "fixDimensionType", "setScalingFactor", "setSize", "setStyle", "shadowStyle", "Lcom/amazon/ui/foundations/styles/ShadowStyle;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseImageShadow extends ImageView {
    private int cornerRadius;
    private FixDimensionType fixedDimensionType;
    private Float height;
    private Paint mPaint;
    private RectF rect;
    private Float scalingFactor;
    private int shadowColor;
    private float shadowX;
    private float shadowY;
    private Float width;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImageShadow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BaseImageShadow(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImageShadow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mPaint = new Paint();
        this.shadowY = getResources().getDimension(R$dimen.spacer_10);
        this.shadowColor = getResources().getColor(R$color.black_30);
    }

    public final void setScalingFactor(float scalingFactor) {
        this.scalingFactor = Float.valueOf(scalingFactor);
    }

    public final void setFixDimensionType(FixDimensionType fixDimensionType) {
        Intrinsics.checkNotNullParameter(fixDimensionType, "fixDimensionType");
        this.fixedDimensionType = fixDimensionType;
    }

    public final void setSize(float width, float height) {
        this.width = Float.valueOf(width);
        this.height = Float.valueOf(height);
        this.rect = new RectF(0.0f, 0.0f, width, height);
        getLayoutParams().width = ((int) width) + ((int) this.shadowX);
        getLayoutParams().height = ((int) height) + ((int) this.shadowY);
    }

    public final void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public final void setStyle(ShadowStyle shadowStyle) {
        Intrinsics.checkNotNullParameter(shadowStyle, "shadowStyle");
        this.shadowX = shadowStyle.getShadowX();
        this.shadowY = shadowStyle.getShadowY();
        this.shadowColor = shadowStyle.getColor();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Float f = this.scalingFactor;
        if (Intrinsics.areEqual(this.fixedDimensionType, FixDimensionType.FixedHeight.INSTANCE)) {
            if (f != null) {
                Float f2 = this.height;
                this.width = f2 != null ? Float.valueOf(f2.floatValue() * f.floatValue()) : null;
            }
        } else if (f != null) {
            Float f3 = this.width;
            this.height = f3 != null ? Float.valueOf(f3.floatValue() / f.floatValue()) : null;
        }
        Float f4 = this.width;
        Float f5 = this.height;
        if (f4 != null && f5 != null) {
            setSize(f4.floatValue(), f5.floatValue());
        }
        if (this.rect != null) {
            float f6 = this.cornerRadius;
            this.mPaint.setShadowLayer(f6, this.shadowX, this.shadowY, this.shadowColor);
            if (canvas == null) {
                return;
            }
            RectF rectF = this.rect;
            Intrinsics.checkNotNull(rectF);
            canvas.drawRoundRect(rectF, f6, f6, this.mPaint);
        }
    }
}
