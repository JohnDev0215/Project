package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class AlphaPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha;

    public AlphaPageTransformer() {
        this.mMinAlpha = 0.5f;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        view.setScaleX(0.999f);
        if (f8 < -1.0f) {
            view.setAlpha(this.mMinAlpha);
            return;
        }
        if (f8 > 1.0f) {
            view.setAlpha(this.mMinAlpha);
        } else if (f8 < 0.0f) {
            float f9 = this.mMinAlpha;
            view.setAlpha(f9 + ((1.0f - f9) * (f8 + 1.0f)));
        } else {
            float f10 = this.mMinAlpha;
            view.setAlpha(f10 + ((1.0f - f10) * (1.0f - f8)));
        }
    }

    public AlphaPageTransformer(float f8) {
        this.mMinAlpha = 0.5f;
        this.mMinAlpha = f8;
    }
}
