package com.amazon.ui.foundations.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.amazon.ui.foundations.R$fraction;
import com.amazon.ui.foundations.R$integer;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.styles.OutlineStyle;
import com.amazon.ui.foundations.utils.FixDimensionType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseImageView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0014J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u0015J\u000e\u0010&\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u000bJ\u000e\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020,R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cornerRadius", "defaultScalingFactor", "", "getDefaultScalingFactor", "()F", "defaultScalingFactor$delegate", "Lkotlin/Lazy;", "defaultScalingType", "getDefaultScalingType", "()I", "defaultScalingType$delegate", "fixedDimensionType", "Lcom/amazon/ui/foundations/utils/FixDimensionType;", "outlineColor", "Ljava/lang/Integer;", "outlineWidth", "paint", "Landroid/graphics/Paint;", "scalingFactor", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setCornerRadius", "setFixDimensionType", "fixDimensionType", "setScalingFactor", "setSize", "imageSize", "Lcom/amazon/ui/foundations/styles/ImageSize;", "setStyle", "imageStyle", "Lcom/amazon/ui/foundations/styles/ImageStyle;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseImageView extends ImageView {
    private int cornerRadius;

    /* JADX INFO: renamed from: defaultScalingFactor$delegate, reason: from kotlin metadata */
    private final Lazy defaultScalingFactor;

    /* JADX INFO: renamed from: defaultScalingType$delegate, reason: from kotlin metadata */
    private final Lazy defaultScalingType;
    private FixDimensionType fixedDimensionType;
    private Integer outlineColor;
    private Integer outlineWidth;
    private final Paint paint;
    private float scalingFactor;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BaseImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImageView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.paint = new Paint();
        this.defaultScalingFactor = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.ui.foundations.views.BaseImageView$defaultScalingFactor$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(context.getResources().getFraction(R$fraction.default_fixed_aspect_imageview_scaling_factor, 1, 1));
            }
        });
        this.defaultScalingType = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.ui.foundations.views.BaseImageView$defaultScalingType$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(context.getResources().getInteger(R$integer.default_fixed_aspect_imageview_scaling_type));
            }
        });
        this.scalingFactor = getDefaultScalingFactor();
        this.fixedDimensionType = FixDimensionType.INSTANCE.getByValue(getDefaultScalingType());
        setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    private final float getDefaultScalingFactor() {
        return ((Number) this.defaultScalingFactor.getValue()).floatValue();
    }

    private final int getDefaultScalingType() {
        return ((Number) this.defaultScalingType.getValue()).intValue();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.fixedDimensionType.getWidth(this.scalingFactor, getMeasuredWidth(), getMeasuredHeight()), this.fixedDimensionType.getHeight(this.scalingFactor, getMeasuredWidth(), getMeasuredHeight()));
    }

    public final void setScalingFactor(float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    public final void setFixDimensionType(FixDimensionType fixDimensionType) {
        Intrinsics.checkNotNullParameter(fixDimensionType, "fixDimensionType");
        this.fixedDimensionType = fixDimensionType;
    }

    public final void setSize(ImageSize imageSize) {
        Intrinsics.checkNotNullParameter(imageSize, "imageSize");
        int size = imageSize.getSize();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = size;
        }
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        if (layoutParams2 == null) {
            return;
        }
        layoutParams2.width = size;
    }

    public final void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public final void setStyle(ImageStyle imageStyle) {
        Intrinsics.checkNotNullParameter(imageStyle, "imageStyle");
        OutlineStyle outlineStyle = imageStyle.getOutlineStyle();
        this.outlineColor = outlineStyle == null ? null : Integer.valueOf(outlineStyle.getColor());
        OutlineStyle outlineStyle2 = imageStyle.getOutlineStyle();
        this.outlineWidth = outlineStyle2 != null ? Integer.valueOf(outlineStyle2.getWidth()) : null;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Integer num = this.outlineColor;
        Integer num2 = this.outlineWidth;
        if (num == null || num2 == null) {
            return;
        }
        this.paint.setColor(num.intValue());
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(num2.intValue());
        if (canvas == null) {
            return;
        }
        float measuredWidth = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        int i = this.cornerRadius;
        canvas.drawRoundRect(0.0f, 0.0f, measuredWidth, measuredHeight, i, i, this.paint);
    }
}
