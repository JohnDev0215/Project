package com.youth.banner.transformer;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class RotateYTransformer extends BasePageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 35.0f;
    private float mMaxRotate;

    public RotateYTransformer() {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        view.setPivotY(view.getHeight() / 2);
        if (f8 < -1.0f) {
            view.setRotationY(this.mMaxRotate * (-1.0f));
            view.setPivotX(view.getWidth());
            return;
        }
        if (f8 > 1.0f) {
            view.setRotationY(this.mMaxRotate * 1.0f);
            view.setPivotX(0.0f);
            return;
        }
        view.setRotationY(this.mMaxRotate * f8);
        if (f8 < 0.0f) {
            view.setPivotX(view.getWidth() * (((-f8) * 0.5f) + 0.5f));
            view.setPivotX(view.getWidth());
        } else {
            view.setPivotX(view.getWidth() * 0.5f * (1.0f - f8));
            view.setPivotX(0.0f);
        }
    }

    public RotateYTransformer(float f8) {
        this.mMaxRotate = DEFAULT_MAX_ROTATE;
        this.mMaxRotate = f8;
    }
}
