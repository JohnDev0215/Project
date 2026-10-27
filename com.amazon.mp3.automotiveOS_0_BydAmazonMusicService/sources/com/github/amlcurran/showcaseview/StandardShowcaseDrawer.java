package com.github.amlcurran.showcaseview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes6.dex */
class StandardShowcaseDrawer implements ShowcaseDrawer {
    protected int backgroundColour;
    private final Paint basicPaint;
    protected final Paint eraserPaint;
    protected final Drawable showcaseDrawable;
    private final float showcaseRadius;

    public StandardShowcaseDrawer(Resources resources, Resources.Theme theme) {
        int i;
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        Paint paint = new Paint();
        this.eraserPaint = paint;
        paint.setColor(ViewCompat.MEASURED_SIZE_MASK);
        paint.setAlpha(0);
        paint.setXfermode(porterDuffXfermode);
        paint.setAntiAlias(true);
        this.basicPaint = new Paint();
        this.showcaseRadius = resources.getDimension(R$dimen.showcase_radius);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (displayMetrics.heightPixels / displayMetrics.density < resources.getDimension(R$dimen.minimum_screen_height) / displayMetrics.density) {
            i = R$drawable.cling_bleached_small;
        } else {
            i = R$drawable.cling_bleached;
        }
        this.showcaseDrawable = ResourcesCompat.getDrawable(resources, i, theme);
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void setShowcaseColour(int i) {
        this.showcaseDrawable.setColorFilter(i, PorterDuff.Mode.MULTIPLY);
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void drawShowcase(Bitmap bitmap, float f, float f2, float f3) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(f, f2, this.showcaseRadius, this.eraserPaint);
        int showcaseWidth = (int) (f - (getShowcaseWidth() / 2));
        int showcaseHeight = (int) (f2 - (getShowcaseHeight() / 2));
        this.showcaseDrawable.setBounds(showcaseWidth, showcaseHeight, getShowcaseWidth() + showcaseWidth, getShowcaseHeight() + showcaseHeight);
        this.showcaseDrawable.draw(canvas);
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public int getShowcaseWidth() {
        return this.showcaseDrawable.getIntrinsicWidth();
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public int getShowcaseHeight() {
        return this.showcaseDrawable.getIntrinsicHeight();
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public float getBlockedRadius() {
        return this.showcaseRadius;
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void setBackgroundColour(int i) {
        this.backgroundColour = i;
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void erase(Bitmap bitmap) {
        bitmap.eraseColor(this.backgroundColour);
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void drawToCanvas(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.basicPaint);
    }
}
