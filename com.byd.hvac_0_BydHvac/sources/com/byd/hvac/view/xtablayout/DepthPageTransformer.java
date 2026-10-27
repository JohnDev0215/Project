package com.byd.hvac.view.xtablayout;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DepthPageTransformer implements ViewPager2.PageTransformer {
    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(@NonNull View view, float f) {
        int width = view.getWidth();
        view.getHeight();
        Math.abs(f);
        if (f < -1.0f) {
            view.setAlpha(0.0f);
            view.setElevation(-1.0f);
            return;
        }
        if (f < 0.0f) {
            view.setAlpha(1.0f + f);
            view.setTranslationX(width * (-f));
            view.setElevation(-1.0f);
        } else if (f == 0.0f) {
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setElevation(0.0f);
        } else if (f >= 1.0f) {
            view.setAlpha(0.0f);
            view.setElevation(-1.0f);
        } else {
            view.setAlpha(1.0f - f);
            view.setTranslationX(width * (-f));
            view.setElevation(-1.0f);
        }
    }
}
