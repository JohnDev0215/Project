package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class DepthPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.75f;
    private float mMinScale;

    public DepthPageTransformer() {
        this.mMinScale = DEFAULT_MIN_SCALE;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        int width = view.getWidth();
        if (f8 < -1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        if (f8 <= 0.0f) {
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        if (f8 > 1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        view.setVisibility(0);
        view.setAlpha(1.0f - f8);
        view.setTranslationX(width * (-f8));
        float f9 = this.mMinScale;
        float fAbs = f9 + ((1.0f - f9) * (1.0f - Math.abs(f8)));
        view.setScaleX(fAbs);
        view.setScaleY(fAbs);
        if (f8 == 1.0f) {
            view.setVisibility(4);
        }
    }

    public DepthPageTransformer(float f8) {
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMinScale = f8;
    }
}
