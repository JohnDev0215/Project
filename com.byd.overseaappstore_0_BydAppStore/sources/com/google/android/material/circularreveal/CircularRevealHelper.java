package com.google.android.material.circularreveal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class CircularRevealHelper {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    private static final boolean DEBUG = false;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY = 2;
    private boolean buildingCircularRevealCache;
    private Paint debugPaint;
    private final Delegate delegate;
    private boolean hasCircularRevealCache;

    @Nullable
    private Drawable overlayDrawable;

    @Nullable
    private CircularRevealWidget.RevealInfo revealInfo;

    @NonNull
    private final Paint revealPaint;

    @NonNull
    private final Path revealPath;

    @NonNull
    private final Paint scrimPaint;

    @NonNull
    private final View view;

    public interface Delegate {
        void actualDraw(Canvas canvas);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Strategy {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CircularRevealHelper(Delegate delegate) {
        this.delegate = delegate;
        View view = (View) delegate;
        this.view = view;
        view.setWillNotDraw(false);
        this.revealPath = new Path();
        this.revealPaint = new Paint(7);
        Paint paint = new Paint(1);
        this.scrimPaint = paint;
        paint.setColor(0);
    }

    private void drawDebugCircle(@NonNull Canvas canvas, int i, float f) {
        this.debugPaint.setColor(i);
        this.debugPaint.setStrokeWidth(f);
        CircularRevealWidget.RevealInfo revealInfo = this.revealInfo;
        canvas.drawCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius - (f / 2.0f), this.debugPaint);
    }

    private void drawDebugMode(@NonNull Canvas canvas) {
        this.delegate.actualDraw(canvas);
        if (shouldDrawScrim()) {
            CircularRevealWidget.RevealInfo revealInfo = this.revealInfo;
            canvas.drawCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius, this.scrimPaint);
        }
        if (shouldDrawCircularReveal()) {
            drawDebugCircle(canvas, ViewCompat.MEASURED_STATE_MASK, 10.0f);
            drawDebugCircle(canvas, SupportMenu.CATEGORY_MASK, 5.0f);
        }
        drawOverlayDrawable(canvas);
    }

    private void drawOverlayDrawable(@NonNull Canvas canvas) {
        if (shouldDrawOverlayDrawable()) {
            Rect bounds = this.overlayDrawable.getBounds();
            float fWidth = this.revealInfo.centerX - (bounds.width() / 2.0f);
            float fHeight = this.revealInfo.centerY - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.overlayDrawable.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    private float getDistanceToFurthestCorner(@NonNull CircularRevealWidget.RevealInfo revealInfo) {
        return MathUtils.distanceToFurthestCorner(revealInfo.centerX, revealInfo.centerY, 0.0f, 0.0f, this.view.getWidth(), this.view.getHeight());
    }

    private void invalidateRevealInfo() {
        if (STRATEGY == 1) {
            this.revealPath.rewind();
            CircularRevealWidget.RevealInfo revealInfo = this.revealInfo;
            if (revealInfo != null) {
                this.revealPath.addCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius, Path.Direction.CW);
            }
        }
        this.view.invalidate();
    }

    private boolean shouldDrawCircularReveal() {
        CircularRevealWidget.RevealInfo revealInfo = this.revealInfo;
        boolean z = revealInfo == null || revealInfo.isInvalid();
        return STRATEGY == 0 ? !z && this.hasCircularRevealCache : !z;
    }

    private boolean shouldDrawOverlayDrawable() {
        return (this.buildingCircularRevealCache || this.overlayDrawable == null || this.revealInfo == null) ? false : true;
    }

    private boolean shouldDrawScrim() {
        return (this.buildingCircularRevealCache || Color.alpha(this.scrimPaint.getColor()) == 0) ? false : true;
    }

    public void buildCircularRevealCache() {
        if (STRATEGY == 0) {
            this.buildingCircularRevealCache = true;
            this.hasCircularRevealCache = false;
            this.view.buildDrawingCache();
            Bitmap drawingCache = this.view.getDrawingCache();
            if (drawingCache == null && this.view.getWidth() != 0 && this.view.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.view.getWidth(), this.view.getHeight(), Bitmap.Config.ARGB_8888);
                this.view.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.revealPaint;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.buildingCircularRevealCache = false;
            this.hasCircularRevealCache = true;
        }
    }

    public void destroyCircularRevealCache() {
        if (STRATEGY == 0) {
            this.hasCircularRevealCache = false;
            this.view.destroyDrawingCache();
            this.revealPaint.setShader(null);
            this.view.invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(@androidx.annotation.NonNull android.graphics.Canvas r9) {
        /*
            r8 = this;
            boolean r0 = r8.shouldDrawCircularReveal()
            if (r0 == 0) goto L77
            int r0 = com.google.android.material.circularreveal.CircularRevealHelper.STRATEGY
            if (r0 == 0) goto L56
            r1 = 1
            if (r0 == r1) goto L28
            r1 = 2
            if (r0 != r1) goto L1c
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r0 = r8.delegate
            r0.actualDraw(r9)
            boolean r0 = r8.shouldDrawScrim()
            if (r0 == 0) goto L98
            goto L82
        L1c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r1 = "Unsupported strategy "
            java.lang.String r0 = spi.e.c(r1, r0)
            r9.<init>(r0)
            throw r9
        L28:
            int r0 = r9.save()
            android.graphics.Path r1 = r8.revealPath
            r9.clipPath(r1)
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r1 = r8.delegate
            r1.actualDraw(r9)
            boolean r1 = r8.shouldDrawScrim()
            if (r1 == 0) goto L52
            r3 = 0
            r4 = 0
            android.view.View r1 = r8.view
            int r1 = r1.getWidth()
            float r5 = (float) r1
            android.view.View r1 = r8.view
            int r1 = r1.getHeight()
            float r6 = (float) r1
            android.graphics.Paint r7 = r8.scrimPaint
            r2 = r9
            r2.drawRect(r3, r4, r5, r6, r7)
        L52:
            r9.restoreToCount(r0)
            goto L98
        L56:
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r0 = r8.revealInfo
            float r1 = r0.centerX
            float r2 = r0.centerY
            float r0 = r0.radius
            android.graphics.Paint r3 = r8.revealPaint
            r9.drawCircle(r1, r2, r0, r3)
            boolean r0 = r8.shouldDrawScrim()
            if (r0 == 0) goto L98
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r0 = r8.revealInfo
            float r1 = r0.centerX
            float r2 = r0.centerY
            float r0 = r0.radius
            android.graphics.Paint r3 = r8.scrimPaint
            r9.drawCircle(r1, r2, r0, r3)
            goto L98
        L77:
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r0 = r8.delegate
            r0.actualDraw(r9)
            boolean r0 = r8.shouldDrawScrim()
            if (r0 == 0) goto L98
        L82:
            r2 = 0
            r3 = 0
            android.view.View r0 = r8.view
            int r0 = r0.getWidth()
            float r4 = (float) r0
            android.view.View r0 = r8.view
            int r0 = r0.getHeight()
            float r5 = (float) r0
            android.graphics.Paint r6 = r8.scrimPaint
            r1 = r9
            r1.drawRect(r2, r3, r4, r5, r6)
        L98:
            r8.drawOverlayDrawable(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.circularreveal.CircularRevealHelper.draw(android.graphics.Canvas):void");
    }

    @Nullable
    public Drawable getCircularRevealOverlayDrawable() {
        return this.overlayDrawable;
    }

    @ColorInt
    public int getCircularRevealScrimColor() {
        return this.scrimPaint.getColor();
    }

    @Nullable
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo revealInfo = this.revealInfo;
        if (revealInfo == null) {
            return null;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = new CircularRevealWidget.RevealInfo(revealInfo);
        if (revealInfo2.isInvalid()) {
            revealInfo2.radius = getDistanceToFurthestCorner(revealInfo2);
        }
        return revealInfo2;
    }

    public boolean isOpaque() {
        return this.delegate.actualIsOpaque() && !shouldDrawCircularReveal();
    }

    public void setCircularRevealOverlayDrawable(@Nullable Drawable drawable) {
        this.overlayDrawable = drawable;
        this.view.invalidate();
    }

    public void setCircularRevealScrimColor(@ColorInt int i) {
        this.scrimPaint.setColor(i);
        this.view.invalidate();
    }

    public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo revealInfo) {
        if (revealInfo == null) {
            this.revealInfo = null;
        } else {
            CircularRevealWidget.RevealInfo revealInfo2 = this.revealInfo;
            if (revealInfo2 == null) {
                this.revealInfo = new CircularRevealWidget.RevealInfo(revealInfo);
            } else {
                revealInfo2.set(revealInfo);
            }
            if (MathUtils.geq(revealInfo.radius, getDistanceToFurthestCorner(revealInfo), 1.0E-4f)) {
                this.revealInfo.radius = Float.MAX_VALUE;
            }
        }
        invalidateRevealInfo();
    }
}
