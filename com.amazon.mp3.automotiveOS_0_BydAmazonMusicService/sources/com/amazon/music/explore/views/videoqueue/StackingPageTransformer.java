package com.amazon.music.explore.views.videoqueue;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes4.dex */
public class StackingPageTransformer implements ViewPager.PageTransformer {
    private static final float SCALE = 0.98f;
    private static final float SLIDE = 0.02f;

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        int width = view.getWidth();
        if (-1.0f > f || f > 0.0f) {
            if (f <= 1.0f) {
                view.setTranslationX(0.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                return;
            }
            return;
        }
        float fAbs = ((1.0f - Math.abs(f)) * 0.01999998f) + SCALE;
        view.setScaleX(fAbs);
        view.setScaleY(fAbs);
        view.setTranslationX(((width * (-f)) - ((1.0f - fAbs) * view.getWidth())) - ((SLIDE - ((1.0f - Math.abs(f)) * SLIDE)) * view.getWidth()));
    }
}
