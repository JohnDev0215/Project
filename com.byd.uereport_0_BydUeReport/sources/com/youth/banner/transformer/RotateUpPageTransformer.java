package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class RotateUpPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate;

    public RotateUpPageTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        if (f8 < -1.0f) {
            view.setRotation(this.mMaxRotate);
            view.setPivotX(view.getWidth());
            view.setPivotY(0.0f);
        } else if (f8 > 1.0f) {
            view.setRotation(-this.mMaxRotate);
            view.setPivotX(0.0f);
            view.setPivotY(0.0f);
        } else if (f8 < 0.0f) {
            view.setPivotX(view.getWidth() * (((-f8) * 0.5f) + 0.5f));
            view.setPivotY(0.0f);
            view.setRotation((-this.mMaxRotate) * f8);
        } else {
            view.setPivotX(view.getWidth() * 0.5f * (1.0f - f8));
            view.setPivotY(0.0f);
            view.setRotation((-this.mMaxRotate) * f8);
        }
    }

    public RotateUpPageTransformer(float f8) {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
        this.mMaxRotate = f8;
    }
}
