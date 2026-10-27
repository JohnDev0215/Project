package com.amazon.ui.foundations.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.BV.LinearGradient.LinearGradientManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: BitmapEffectUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b$\u0010%J,\u0010\n\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\u001a\u0010\u000b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u001e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fJ\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fJ,\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bJ4\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bJ2\u0010\u0019\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\fJ\u0016\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\fJ\u001e\u0010 \u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fJ\u001e\u0010#\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f¨\u0006&"}, d2 = {"Lcom/amazon/ui/foundations/utils/BitmapEffectUtils;", "", "Landroid/graphics/Bitmap;", "bitmap", "", "isHorizontal", "", "positions", "", LinearGradientManager.PROP_COLORS, "applyGradientOverlayBitmap", "isValid", "", "width", "height", "", "blurRadius", "isBlurRadiusValid", "Landroid/content/Context;", "context", "getBlurBitmap", "Landroid/graphics/drawable/Drawable;", "getBlurBitmapDrawable", "getGradientOverlayBitmap", "getGradientOverlayDrawable", "createGradientOverlayBitmap", "color", "getColorOverlayBitmap", "sourceBitmap", "widthToScale", "getWidthScaledBitmap", "heightToScale", "getWidthScaledBitmapWithHorizontalCrop", "finalWidth", "finalHeight", "getScaledBitmap", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class BitmapEffectUtils {
    public static final BitmapEffectUtils INSTANCE = new BitmapEffectUtils();

    private final boolean isBlurRadiusValid(float blurRadius) {
        return blurRadius > 0.0f;
    }

    private final boolean isValid(int width, int height) {
        return width > 0 && height > 0;
    }

    private BitmapEffectUtils() {
    }

    public final Bitmap getBlurBitmap(Context context, Bitmap bitmap, float blurRadius) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (!isBlurRadiusValid(blurRadius)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (blurRadius > 25.0f) {
            bitmap = getBlurBitmap(context, bitmap, blurRadius - 25.0f);
            blurRadius = 25.0f;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, MathKt__MathJVMKt.roundToInt(bitmap.getWidth() * 0.4f), MathKt__MathJVMKt.roundToInt(bitmap.getHeight() * 0.4f), false);
        Bitmap outputBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        RenderScript renderScriptCreate = RenderScript.create(context);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, outputBitmap);
        scriptIntrinsicBlurCreate.setRadius(blurRadius);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(outputBitmap);
        if (renderScriptCreate != null) {
            renderScriptCreate.destroy();
        }
        scriptIntrinsicBlurCreate.destroy();
        if (allocationCreateFromBitmap != null) {
            allocationCreateFromBitmap.destroy();
        }
        allocationCreateFromBitmap2.destroy();
        Intrinsics.checkNotNullExpressionValue(outputBitmap, "outputBitmap");
        return outputBitmap;
    }

    public final Drawable getBlurBitmapDrawable(Context context, Bitmap bitmap, float blurRadius) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return new BitmapDrawable(context.getResources(), getBlurBitmap(context, bitmap, blurRadius));
    }

    public final Bitmap getGradientOverlayBitmap(Bitmap bitmap, boolean isHorizontal, float[] positions, int[] colors) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(colors, "colors");
        return applyGradientOverlayBitmap(bitmap, isHorizontal, positions, colors);
    }

    public final Drawable getGradientOverlayDrawable(Context context, Bitmap bitmap, boolean isHorizontal, float[] positions, int[] colors) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(colors, "colors");
        return new BitmapDrawable(context.getResources(), applyGradientOverlayBitmap(bitmap, isHorizontal, positions, colors));
    }

    public final Bitmap createGradientOverlayBitmap(int width, int height, boolean isHorizontal, float[] positions, int[] colors) throws IllegalArgumentException {
        LinearGradient linearGradient;
        Intrinsics.checkNotNullParameter(colors, "colors");
        if (!isValid(positions, colors)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!isValid(width, height)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (isHorizontal) {
            linearGradient = new LinearGradient(0.0f, 0.0f, width, 0.0f, colors, positions, Shader.TileMode.REPEAT);
        } else {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, height, colors, positions, Shader.TileMode.REPEAT);
        }
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(result).drawRect(0.0f, 0.0f, width, height, paint);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    private final Bitmap applyGradientOverlayBitmap(Bitmap bitmap, boolean isHorizontal, float[] positions, int[] colors) {
        LinearGradient linearGradient;
        if (!isValid(positions, colors)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (isHorizontal) {
            linearGradient = new LinearGradient(0.0f, 0.0f, width, 0.0f, colors, positions, Shader.TileMode.REPEAT);
        } else {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, height, colors, positions, Shader.TileMode.REPEAT);
        }
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawRect(0.0f, 0.0f, width, height, paint);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public final Bitmap getColorOverlayBitmap(Bitmap bitmap, int color) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawColor(color, PorterDuff.Mode.SRC_OVER);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public final Bitmap getWidthScaledBitmap(Bitmap sourceBitmap, int widthToScale) {
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        Matrix matrix = new Matrix();
        float width = widthToScale / sourceBitmap.getWidth();
        matrix.postScale(width, width);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight(), matrix, false);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(sourceBitma…ap.height, matrix, false)");
        return bitmapCreateBitmap;
    }

    public final Bitmap getWidthScaledBitmapWithHorizontalCrop(Bitmap sourceBitmap, int widthToScale, int heightToScale) {
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        Matrix matrix = new Matrix();
        float f = widthToScale;
        int height = (int) ((sourceBitmap.getHeight() / heightToScale) * f);
        float f2 = f / height;
        matrix.postScale(f2, f2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(sourceBitmap, sourceBitmap.getWidth() >= height ? (sourceBitmap.getWidth() - height) / 2 : 0, 0, sourceBitmap.getWidth() >= height ? height : sourceBitmap.getWidth(), sourceBitmap.getHeight(), matrix, false);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(sourceBitma…ap.height, matrix, false)");
        return bitmapCreateBitmap;
    }

    public final Bitmap getScaledBitmap(Bitmap sourceBitmap, float finalWidth, float finalHeight) {
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        float width = sourceBitmap.getWidth();
        float height = sourceBitmap.getHeight();
        if (width / height < finalWidth / finalHeight) {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(sourceBitmap, (int) finalWidth, (int) ((height * finalWidth) / width), false);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "{\n            val scaleH…toInt(), false)\n        }");
            return bitmapCreateScaledBitmap;
        }
        Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(sourceBitmap, (int) ((width * finalHeight) / height), (int) finalHeight, false);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap2, "{\n            val scaleW…toInt(), false)\n        }");
        return bitmapCreateScaledBitmap2;
    }

    private final boolean isValid(float[] positions, int[] colors) {
        return positions == null || positions.length == colors.length;
    }
}
