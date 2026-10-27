package org.chromium.chrome.browser.feed.sections;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.ii2;
import defpackage.sc1;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.feed.sections.SectionHeaderBadgeDrawable;
import org.chromium.components.browser_ui.styles.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class SectionHeaderBadgeDrawable extends Drawable {
    public static final /* synthetic */ int i = 0;
    public final TextPaint a;
    public final sc1 b;
    public final Context c;
    public final float d;
    public String e;
    public ValueAnimator f;
    public View g;
    public boolean h;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable = SectionHeaderBadgeDrawable.this;
            sectionHeaderBadgeDrawable.e = "";
            sectionHeaderBadgeDrawable.invalidateSelf();
        }
    }

    public SectionHeaderBadgeDrawable(Context context) {
        this.c = context;
        AppCompatTextView appCompatTextView = new AppCompatTextView(context);
        appCompatTextView.setTextAppearance(context, R$style.TextAppearance_Material3_LabelSmall);
        TextPaint paint = appCompatTextView.getPaint();
        this.a = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(ii2.g(context, R$attr.colorOnPrimary));
        this.d = paint.getTextSize();
        sc1 sc1Var = new sc1();
        this.b = sc1Var;
        sc1Var.setShapeAppearanceModel(sc1Var.e.a.f(context.getResources().getDimensionPixelSize(R$dimen.feed_badge_radius)));
        sc1Var.n(ColorStateList.valueOf(ii2.g(context, R$attr.colorPrimary)));
        this.e = "";
    }

    public final Rect a(View view, String str) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        Rect rect2 = new Rect();
        this.a.getTextBounds(str, 0, str.length(), rect2);
        Context context = this.c;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.feed_badge_radius);
        int iMax = Math.max(dimensionPixelSize, ((rect2.bottom - rect2.top) / 2) + dimensionPixelSize);
        int iMax2 = Math.max(dimensionPixelSize, ((rect2.right - rect2.left) / 2) + dimensionPixelSize);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.feed_badge_voffset) + rect.top + iMax;
        int dimensionPixelSize3 = (rect.right + iMax2) - context.getResources().getDimensionPixelSize(R$dimen.feed_badge_hoffset);
        return new Rect(dimensionPixelSize3 - iMax2, dimensionPixelSize2 - iMax, dimensionPixelSize3 + iMax2, dimensionPixelSize2 + iMax);
    }

    public final void b() {
        this.f = ValueAnimator.ofInt(0, 100);
        final Rect bounds = getBounds();
        final Rect rectA = a(this.g, "");
        this.f.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: wg2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i2 = SectionHeaderBadgeDrawable.i;
                SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable = this.e;
                sectionHeaderBadgeDrawable.getClass();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                Rect rect = bounds;
                int i3 = rect.left;
                Rect rect2 = rectA;
                Rect rect3 = new Rect((int) (i3 - ((i3 - rect2.left) * animatedFraction)), (int) (rect.top - ((r4 - rect2.top) * animatedFraction)), (int) (rect.right - ((r5 - rect2.right) * animatedFraction)), (int) (rect.bottom - ((r2 - rect2.bottom) * animatedFraction)));
                TextPaint textPaint = sectionHeaderBadgeDrawable.a;
                textPaint.setAlpha((int) (255.0f - (animatedFraction * 255.0f)));
                float f = sectionHeaderBadgeDrawable.d;
                textPaint.setTextSize(f - (animatedFraction * f));
                sectionHeaderBadgeDrawable.setBounds(rect3);
                sectionHeaderBadgeDrawable.invalidateSelf();
            }
        });
        this.f.addListener(new a());
        this.f.setStartDelay(1500L);
        this.f.setDuration(400L);
        this.f.start();
    }

    public final void c() {
        if (this.e.isEmpty()) {
            return;
        }
        ValueAnimator valueAnimator = this.f;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.f.pause();
        }
        if (this.g == null) {
            this.h = true;
        } else {
            b();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.b.draw(canvas);
        String str = this.e;
        float fCenterX = bounds.centerX();
        float fCenterY = bounds.centerY();
        TextPaint textPaint = this.a;
        canvas.drawText(str, fCenterX, fCenterY - ((textPaint.ascent() + textPaint.descent()) / 2.0f), textPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        this.a.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(Rect rect) {
        this.b.setBounds(rect);
        super.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
