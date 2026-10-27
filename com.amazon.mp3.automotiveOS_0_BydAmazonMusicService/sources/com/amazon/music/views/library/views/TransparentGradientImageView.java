package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.BV.LinearGradient.LinearGradientManager;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TransparentGradientImageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u00106\u001a\u000205\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u000107\u0012\b\b\u0002\u00109\u001a\u00020\u001b¢\u0006\u0004\b:\u0010;J\b\u0010\u0003\u001a\u00020\u0002H\u0002J2\u0010\r\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J8\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\tJ8\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\tJ.\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006JF\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001bJ\u000e\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001eJ\u0012\u0010#\u001a\u00020\u00022\b\u0010\"\u001a\u0004\u0018\u00010!H\u0014R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010+R\"\u0010/\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006<"}, d2 = {"Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "Landroid/widget/ImageView;", "", "turnOffHardwareAccelerationForOldApiVersions", "Landroid/graphics/Bitmap;", "bitmap", "", "width", "height", "", "addBorderToBitmap", "Landroid/graphics/Shader;", "gradientShader", "applyTransparentGradient", "originalBitmap", "applyBorderToBitmap", "", "positions", "", LinearGradientManager.PROP_COLORS, "applyTransparentGradientToBottom", "applyTransparentGradientToLeft", "applyTransparentGradientToLeftAndBottom", "topGradientPositions", "bottomGradientPositions", "topGradientColors", "bottomGradientColors", "", "topGradientCrop", "applyTransparentGradientToTopAndBottom", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "Landroid/graphics/Canvas;", "canvas", "onDraw", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "Landroid/graphics/Paint;", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "borderPaint", "cornerRadius", "I", "getCornerRadius", "()I", "setCornerRadius", "(I)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class TransparentGradientImageView extends ImageView {
    private Bitmap bitmap;
    private final Paint borderPaint;
    private int cornerRadius;
    private final Paint paint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TransparentGradientImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ TransparentGradientImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TransparentGradientImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.paint = new Paint();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDimension(R$dimen.spacer_1));
        paint.setAntiAlias(true);
        this.borderPaint = paint;
        setWillNotDraw(false);
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public final Paint getPaint() {
        return this.paint;
    }

    public final int getCornerRadius() {
        return this.cornerRadius;
    }

    public final void setCornerRadius(int i) {
        this.cornerRadius = i;
    }

    private final void turnOffHardwareAccelerationForOldApiVersions() {
        if (Build.VERSION.SDK_INT <= 27) {
            setLayerType(1, null);
            return;
        }
        try {
            Class.forName("com.amazon.mp3.FireOSApplication");
            setLayerType(1, null);
        } catch (ClassNotFoundException unused) {
        }
    }

    public static /* synthetic */ void applyTransparentGradientToBottom$default(TransparentGradientImageView transparentGradientImageView, Bitmap bitmap, float[] fArr, int[] iArr, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        transparentGradientImageView.applyTransparentGradientToBottom(bitmap, fArr, iArr, f, f2, z);
    }

    public final void applyTransparentGradientToBottom(Bitmap bitmap, float[] positions, int[] colors, float width, float height, boolean addBorderToBitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(positions, "positions");
        Intrinsics.checkNotNullParameter(colors, "colors");
        applyTransparentGradient(bitmap, width, height, addBorderToBitmap, new LinearGradient(0.0f, 0.0f, 0.0f, height, colors, positions, Shader.TileMode.CLAMP));
    }

    private final void applyTransparentGradient(Bitmap bitmap, float width, float height, boolean addBorderToBitmap, Shader gradientShader) {
        if (width <= 0.0f || height <= 0.0f || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return;
        }
        Bitmap it = Bitmap.createBitmap(BitmapEffectUtils.INSTANCE.getScaledBitmap(bitmap, width, height), 0, 0, (int) width, (int) height);
        if (addBorderToBitmap) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            it = applyBorderToBitmap(it);
        }
        this.bitmap = it;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.paint.setShader(new ComposeShader(gradientShader, new BitmapShader(it, tileMode, tileMode), PorterDuff.Mode.SRC_IN));
    }

    public static /* synthetic */ void applyTransparentGradientToLeft$default(TransparentGradientImageView transparentGradientImageView, Bitmap bitmap, float[] fArr, int[] iArr, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        transparentGradientImageView.applyTransparentGradientToLeft(bitmap, fArr, iArr, f, f2, z);
    }

    public final void applyTransparentGradientToLeft(Bitmap bitmap, float[] positions, int[] colors, float width, float height, boolean addBorderToBitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(positions, "positions");
        Intrinsics.checkNotNullParameter(colors, "colors");
        applyTransparentGradient(bitmap, width, height, addBorderToBitmap, new LinearGradient(width, 0.0f, 0.0f, 0.0f, colors, positions, Shader.TileMode.CLAMP));
    }

    private final Bitmap applyBorderToBitmap(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap mutableBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        float strokeWidth = this.borderPaint.getStrokeWidth() / 2;
        int i = this.cornerRadius;
        canvas.drawRoundRect(strokeWidth, strokeWidth, width - strokeWidth, height - strokeWidth, i, i, this.borderPaint);
        Intrinsics.checkNotNullExpressionValue(mutableBitmap, "mutableBitmap");
        return mutableBitmap;
    }

    public final void applyTransparentGradientToLeftAndBottom(Bitmap bitmap, float[] positions, int[] colors, float width, float height) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(positions, "positions");
        Intrinsics.checkNotNullParameter(colors, "colors");
        turnOffHardwareAccelerationForOldApiVersions();
        Bitmap scaledBitmap = BitmapEffectUtils.INSTANCE.getScaledBitmap(bitmap, width, height);
        int i = (int) (((double) height) * 0.1d);
        int i2 = (int) height;
        if (i + i2 > scaledBitmap.getHeight()) {
            i = 0;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(scaledBitmap, 0, i, (int) width, i2);
        this.bitmap = bitmapCreateBitmap;
        ComposeShader composeShader = new ComposeShader(new LinearGradient(width, 0.0f, 0.0f, 0.0f, colors, positions, Shader.TileMode.CLAMP), new LinearGradient(0.0f, 0.0f, 0.0f, bitmapCreateBitmap.getHeight(), colors, positions, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_IN);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.paint.setShader(new ComposeShader(composeShader, new BitmapShader(bitmapCreateBitmap, tileMode, tileMode), PorterDuff.Mode.SRC_IN));
    }

    public final void applyTransparentGradientToTopAndBottom(Bitmap bitmap, float[] topGradientPositions, float[] bottomGradientPositions, int[] topGradientColors, int[] bottomGradientColors, float width, float height, int topGradientCrop) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(topGradientPositions, "topGradientPositions");
        Intrinsics.checkNotNullParameter(bottomGradientPositions, "bottomGradientPositions");
        Intrinsics.checkNotNullParameter(topGradientColors, "topGradientColors");
        Intrinsics.checkNotNullParameter(bottomGradientColors, "bottomGradientColors");
        turnOffHardwareAccelerationForOldApiVersions();
        Bitmap widthScaledBitmap = BitmapEffectUtils.INSTANCE.getWidthScaledBitmap(bitmap, (int) width);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(widthScaledBitmap, 0, 0, widthScaledBitmap.getWidth(), widthScaledBitmap.getHeight() - topGradientCrop);
        this.bitmap = bitmapCreateBitmap;
        ComposeShader composeShader = new ComposeShader(new LinearGradient(0.0f, topGradientCrop, 0.0f, 0.0f, topGradientColors, topGradientPositions, Shader.TileMode.CLAMP), new LinearGradient(0.0f, 0.0f, 0.0f, bitmapCreateBitmap.getHeight(), bottomGradientColors, bottomGradientPositions, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_IN);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.paint.setShader(new ComposeShader(composeShader, new BitmapShader(bitmapCreateBitmap, tileMode, tileMode), PorterDuff.Mode.SRC_IN));
    }

    public final void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2);
        if (color == null) {
            return;
        }
        this.borderPaint.setColor(color.intValue());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.bitmap == null || canvas == null) {
            return;
        }
        canvas.drawRoundRect(0.0f, 0.0f, r0.getWidth(), r0.getHeight(), getCornerRadius(), getCornerRadius(), getPaint());
    }
}
