package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class RotateDownPageTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate;

    public RotateDownPageTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        if (f8 < -1.0f) {
            view.setRotation(this.mMaxRotate * (-1.0f));
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());
        } else if (f8 > 1.0f) {
            view.setRotation(this.mMaxRotate);
            view.setPivotX(view.getWidth() * 0);
            view.setPivotY(view.getHeight());
        } else if (f8 < 0.0f) {
            view.setPivotX(view.getWidth() * (((-f8) * 0.5f) + 0.5f));
            view.setPivotY(view.getHeight());
            view.setRotation(this.mMaxRotate * f8);
        } else {
            view.setPivotX(view.getWidth() * 0.5f * (1.0f - f8));
            view.setPivotY(view.getHeight());
            view.setRotation(this.mMaxRotate * f8);
        }
    }

    public RotateDownPageTransformer(float f8) {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
        this.mMaxRotate = f8;
    }
}
