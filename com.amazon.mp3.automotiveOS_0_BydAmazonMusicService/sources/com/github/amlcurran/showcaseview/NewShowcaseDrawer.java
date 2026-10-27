package com.github.amlcurran.showcaseview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes6.dex */
class NewShowcaseDrawer extends StandardShowcaseDrawer {
    private final float innerRadius;
    private final float outerRadius;

    public NewShowcaseDrawer(Resources resources, Resources.Theme theme) {
        super(resources, theme);
        this.outerRadius = resources.getDimension(R$dimen.showcase_radius_outer);
        this.innerRadius = resources.getDimension(R$dimen.showcase_radius_inner);
    }

    @Override // com.github.amlcurran.showcaseview.StandardShowcaseDrawer, com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void setShowcaseColour(int i) {
        this.eraserPaint.setColor(i);
    }

    @Override // com.github.amlcurran.showcaseview.StandardShowcaseDrawer, com.github.amlcurran.showcaseview.ShowcaseDrawer
    public void drawShowcase(Bitmap bitmap, float f, float f2, float f3) {
        Canvas canvas = new Canvas(bitmap);
        this.eraserPaint.setAlpha(GeneratorAdapter.EQ);
        canvas.drawCircle(f, f2, this.outerRadius, this.eraserPaint);
        this.eraserPaint.setAlpha(0);
        canvas.drawCircle(f, f2, this.innerRadius, this.eraserPaint);
    }

    @Override // com.github.amlcurran.showcaseview.StandardShowcaseDrawer, com.github.amlcurran.showcaseview.ShowcaseDrawer
    public int getShowcaseWidth() {
        return (int) (this.outerRadius * 2.0f);
    }

    @Override // com.github.amlcurran.showcaseview.StandardShowcaseDrawer, com.github.amlcurran.showcaseview.ShowcaseDrawer
    public int getShowcaseHeight() {
        return (int) (this.outerRadius * 2.0f);
    }

    @Override // com.github.amlcurran.showcaseview.StandardShowcaseDrawer, com.github.amlcurran.showcaseview.ShowcaseDrawer
    public float getBlockedRadius() {
        return this.innerRadius;
    }
}
