package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.R$integer;
import com.amazon.music.views.library.R$styleable;
import com.amazon.music.views.library.utils.ScalingType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FixedAspectImageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0015\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0018\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/amazon/music/views/library/views/FixedAspectImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "getAttrs", "()Landroid/util/AttributeSet;", "getDefStyleAttr", "()I", "getDefStyleRes", "defaultScalingFactor", "", "getDefaultScalingFactor", "()F", "defaultScalingFactor$delegate", "Lkotlin/Lazy;", "defaultScalingType", "getDefaultScalingType", "defaultScalingType$delegate", "scalingFactor", "getScalingFactor", "setScalingFactor", "(F)V", "scalingType", "Lcom/amazon/music/views/library/utils/ScalingType;", "getScalingType", "()Lcom/amazon/music/views/library/utils/ScalingType;", "setScalingType", "(Lcom/amazon/music/views/library/utils/ScalingType;)V", "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FixedAspectImageView extends ImageView {
    private final AttributeSet attrs;
    private final int defStyleAttr;
    private final int defStyleRes;

    /* JADX INFO: renamed from: defaultScalingFactor$delegate, reason: from kotlin metadata */
    private final Lazy defaultScalingFactor;

    /* JADX INFO: renamed from: defaultScalingType$delegate, reason: from kotlin metadata */
    private final Lazy defaultScalingType;
    private float scalingFactor;
    private ScalingType scalingType;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public FixedAspectImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ FixedAspectImageView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
    }

    public final int getDefStyleAttr() {
        return this.defStyleAttr;
    }

    public final int getDefStyleRes() {
        return this.defStyleRes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public FixedAspectImageView(final Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
        this.scalingFactor = context.getResources().getFraction(R$fraction.default_fixed_aspect_imageview_scaling_factor, 1, 1);
        this.defaultScalingFactor = LazyKt__LazyJVMKt.lazy(new Function0<Float>() { // from class: com.amazon.music.views.library.views.FixedAspectImageView$defaultScalingFactor$2
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
        this.defaultScalingType = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.FixedAspectImageView$defaultScalingType$2
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
        if (attributeSet == null) {
            this.scalingFactor = getDefaultScalingFactor();
            this.scalingType = ScalingType.INSTANCE.getByValue(getDefaultScalingType());
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.BaseImage, i, i2);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.theme.obtainStyl…efStyleAttr, defStyleRes)");
        try {
            this.scalingFactor = typedArrayObtainStyledAttributes.getFloat(R$styleable.BaseImage_scalingFactor, getDefaultScalingFactor());
            this.scalingType = ScalingType.INSTANCE.getByValue(typedArrayObtainStyledAttributes.getInt(R$styleable.BaseImage_scalingType, getDefaultScalingType()));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final float getScalingFactor() {
        return this.scalingFactor;
    }

    public final void setScalingFactor(float f) {
        this.scalingFactor = f;
    }

    public final ScalingType getScalingType() {
        return this.scalingType;
    }

    public final void setScalingType(ScalingType scalingType) {
        Intrinsics.checkNotNullParameter(scalingType, "<set-?>");
        this.scalingType = scalingType;
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
        setMeasuredDimension(this.scalingType.getWidth(this.scalingFactor, getMeasuredWidth(), getMeasuredHeight()), this.scalingType.getHeight(this.scalingFactor, getMeasuredWidth(), getMeasuredHeight()));
    }
}
