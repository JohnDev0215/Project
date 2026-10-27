package com.amazon.ui.foundations.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.ui.foundations.R$id;
import com.amazon.ui.foundations.R$layout;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.styles.ShadowStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FixDimensionType;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseImage.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001&B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0019R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseImage;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "imageShadow", "Lcom/amazon/ui/foundations/views/BaseImageShadow;", "imageSize", "Lcom/amazon/ui/foundations/styles/ImageSize;", "imageView", "Lcom/amazon/ui/foundations/views/BaseImageView;", "applyCornerRadius", "", "cornerRadius", "getImage", "Landroid/widget/ImageView;", "setBackgroundDrawable", "setBlurBitmapBackground", "bitmap", "Landroid/graphics/Bitmap;", "blurRadius", "", "setFixDimensionType", "fixDimensionType", "Lcom/amazon/ui/foundations/utils/FixDimensionType;", "setImage", "drawable", "Landroid/graphics/drawable/Drawable;", "setImageSize", "setImageStyle", "imageStyle", "Lcom/amazon/ui/foundations/styles/ImageStyle;", "setScalingFactor", "scalingFactor", "StyleBuilder", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseImage extends FrameLayout {
    private BaseImageShadow imageShadow;
    private ImageSize imageSize;
    private BaseImageView imageView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ BaseImage(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BaseImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(context, R$layout.base_image_layout, this);
        View viewFindViewById = findViewById(R$id.image_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.image_view)");
        this.imageView = (BaseImageView) viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.image_shadow);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.image_shadow)");
        this.imageShadow = (BaseImageShadow) viewFindViewById2;
    }

    public final void setScalingFactor(float scalingFactor) {
        this.imageView.setScalingFactor(scalingFactor);
        this.imageShadow.setScalingFactor(scalingFactor);
    }

    public final void setFixDimensionType(FixDimensionType fixDimensionType) {
        Intrinsics.checkNotNullParameter(fixDimensionType, "fixDimensionType");
        this.imageView.setFixDimensionType(fixDimensionType);
        this.imageShadow.setFixDimensionType(fixDimensionType);
    }

    public final void setImage(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this.imageView.setImageDrawable(drawable);
    }

    public final void setBlurBitmapBackground(Bitmap bitmap, float blurRadius) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
        Bitmap scaledBitmap = bitmapEffectUtils.getScaledBitmap(bitmap, this.imageView.getWidth(), this.imageView.getHeight());
        Bitmap bmpCropped = Bitmap.createBitmap(scaledBitmap, 0, 0, this.imageView.getWidth(), this.imageView.getHeight());
        if (!Intrinsics.areEqual(bmpCropped, scaledBitmap) && !scaledBitmap.isRecycled()) {
            scaledBitmap.recycle();
        }
        BaseImageView baseImageView = this.imageView;
        Resources resources = getContext().getResources();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        Intrinsics.checkNotNullExpressionValue(bmpCropped, "bmpCropped");
        baseImageView.setBackground(RoundedBitmapDrawableFactory.create(resources, bitmapEffectUtils.getBlurBitmap(context, bmpCropped, blurRadius)));
    }

    public final ImageView getImage() {
        return this.imageView;
    }

    public final void applyCornerRadius(int cornerRadius) {
        if (this.imageView.getBackground() == null || (!(this.imageView.getBackground() instanceof GradientDrawable) && !(this.imageView.getBackground() instanceof RoundedBitmapDrawable))) {
            setBackgroundDrawable();
        }
        if (this.imageView.getBackground() instanceof GradientDrawable) {
            Drawable background = this.imageView.getBackground();
            if (background == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            }
            ((GradientDrawable) background).setCornerRadius(cornerRadius);
        } else if (this.imageView.getBackground() instanceof RoundedBitmapDrawable) {
            Drawable background2 = this.imageView.getBackground();
            if (background2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.core.graphics.drawable.RoundedBitmapDrawable");
            }
            ((RoundedBitmapDrawable) background2).setCornerRadius(cornerRadius);
        }
        this.imageView.setClipToOutline(true);
        this.imageView.setCornerRadius(cornerRadius);
        this.imageShadow.setCornerRadius(cornerRadius);
    }

    public final void setImageSize(ImageSize imageSize) {
        Intrinsics.checkNotNullParameter(imageSize, "imageSize");
        this.imageSize = imageSize;
        this.imageView.setSize(imageSize);
        Integer cornerRadius = imageSize.getCornerRadius();
        if (cornerRadius == null) {
            return;
        }
        int iIntValue = cornerRadius.intValue();
        applyCornerRadius(iIntValue);
        if (iIntValue == imageSize.getSize() / 2) {
            this.imageShadow.setVisibility(8);
        } else {
            this.imageShadow.setSize(imageSize.getSize(), imageSize.getSize());
        }
    }

    public final void setImageStyle(ImageStyle imageStyle) {
        Intrinsics.checkNotNullParameter(imageStyle, "imageStyle");
        this.imageView.setStyle(imageStyle);
        ShadowStyle shadowStyle = imageStyle.getShadowStyle();
        if (shadowStyle != null) {
            this.imageShadow.setStyle(shadowStyle);
        } else {
            this.imageShadow.setVisibility(8);
        }
    }

    private final void setBackgroundDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        this.imageView.setBackground(gradientDrawable);
        this.imageView.setClipToOutline(true);
    }

    /* JADX INFO: compiled from: BaseImage.kt */
    @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/ui/foundations/views/BaseImage$StyleBuilder;", "", "Lcom/amazon/ui/foundations/views/BaseImage;", "Lcom/amazon/ui/foundations/styles/ImageStyle;", "Lcom/amazon/ui/foundations/styles/ImageSize;", "imageSize", "withSize", "Landroid/widget/ImageView$ScaleType;", "scaleType", "withScaleType", ContextMappingConstants.IMAGE, "", "applyStyle", "size", "Lcom/amazon/ui/foundations/styles/ImageSize;", "style", "Lcom/amazon/ui/foundations/styles/ImageStyle;", "", "scalingFactor", "Ljava/lang/Float;", "Lcom/amazon/ui/foundations/utils/FixDimensionType;", "fixDimensionType", "Lcom/amazon/ui/foundations/utils/FixDimensionType;", "Landroid/widget/ImageView$ScaleType;", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
    public static final class StyleBuilder {
        private FixDimensionType fixDimensionType;
        private ImageView.ScaleType scaleType;
        private Float scalingFactor;
        private ImageSize size;
        private ImageStyle style;

        public final StyleBuilder withSize(ImageSize imageSize) {
            Intrinsics.checkNotNullParameter(imageSize, "imageSize");
            this.size = imageSize;
            return this;
        }

        public final StyleBuilder withScaleType(ImageView.ScaleType scaleType) {
            Intrinsics.checkNotNullParameter(scaleType, "scaleType");
            this.scaleType = scaleType;
            return this;
        }

        public void applyStyle(BaseImage image) {
            Intrinsics.checkNotNullParameter(image, "image");
            ImageSize imageSize = this.size;
            if (imageSize != null) {
                image.setImageSize(imageSize);
            }
            ImageStyle imageStyle = this.style;
            if (imageStyle != null) {
                image.setImageStyle(imageStyle);
            }
            Float f = this.scalingFactor;
            if (f != null) {
                image.setScalingFactor(f.floatValue());
            }
            FixDimensionType fixDimensionType = this.fixDimensionType;
            if (fixDimensionType != null) {
                image.setFixDimensionType(fixDimensionType);
            }
            ImageView.ScaleType scaleType = this.scaleType;
            if (scaleType == null) {
                return;
            }
            image.imageView.setScaleType(scaleType);
        }
    }
}
