package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class ScaleInTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale;

    public ScaleInTransformer() {
        this.mMinScale = DEFAULT_MIN_SCALE;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        int width = view.getWidth();
        view.setPivotY(view.getHeight() / 2);
        view.setPivotX(width / 2);
        if (f8 < -1.0f) {
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
            view.setPivotX(width);
            return;
        }
        if (f8 > 1.0f) {
            view.setPivotX(0.0f);
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
        } else {
            if (f8 < 0.0f) {
                float f9 = this.mMinScale;
                float f10 = ((f8 + 1.0f) * (1.0f - f9)) + f9;
                view.setScaleX(f10);
                view.setScaleY(f10);
                view.setPivotX(width * (((-f8) * 0.5f) + 0.5f));
                return;
            }
            float f11 = 1.0f - f8;
            float f12 = this.mMinScale;
            float f13 = ((1.0f - f12) * f11) + f12;
            view.setScaleX(f13);
            view.setScaleY(f13);
            view.setPivotX(width * f11 * 0.5f);
        }
    }

    public ScaleInTransformer(float f8) {
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMinScale = f8;
    }
}
