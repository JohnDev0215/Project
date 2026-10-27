package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class ZoomOutPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinAlpha;
    private float mMinScale;

    public ZoomOutPageTransformer() {
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMinAlpha = 0.5f;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (f8 < -1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        if (f8 > 1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        float fMax = Math.max(this.mMinScale, 1.0f - Math.abs(f8));
        float f9 = 1.0f - fMax;
        float f10 = (height * f9) / 2.0f;
        float f11 = (width * f9) / 2.0f;
        if (f8 < 0.0f) {
            view.setTranslationX(f11 - (f10 / 2.0f));
        } else {
            view.setTranslationX((-f11) + (f10 / 2.0f));
        }
        view.setScaleX(fMax);
        view.setScaleY(fMax);
        float f12 = this.mMinAlpha;
        float f13 = this.mMinScale;
        view.setAlpha(f12 + (((fMax - f13) / (1.0f - f13)) * (1.0f - f12)));
    }

    public ZoomOutPageTransformer(float f8, float f9) {
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMinAlpha = 0.5f;
        this.mMinScale = f8;
        this.mMinAlpha = f9;
    }
}
