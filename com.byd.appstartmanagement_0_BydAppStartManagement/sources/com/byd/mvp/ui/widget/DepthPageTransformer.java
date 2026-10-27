package com.byd.mvp.ui.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes2.dex */
public class DepthPageTransformer implements ViewPager2.PageTransformer {
    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(@NonNull View view, float f) {
        int width = view.getWidth();
        view.getHeight();
        float fAbs = ((1.0f - Math.abs(f)) * 0.100000024f) + 0.9f;
        if (f < -1.0f) {
            view.setAlpha(0.0f);
            view.setElevation(-1.0f);
            return;
        }
        if (f < 0.0f) {
            view.setAlpha(1.0f + f);
            view.setTranslationX(width * (-f));
            view.setScaleX(fAbs);
            view.setScaleY(fAbs);
            view.setElevation(-1.0f);
            return;
        }
        if (f == 0.0f) {
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            view.setElevation(0.0f);
            return;
        }
        if (f >= 1.0f) {
            view.setAlpha(0.0f);
            view.setElevation(-1.0f);
            return;
        }
        view.setAlpha(1.0f - f);
        view.setTranslationX(width * (-f));
        view.setScaleX(fAbs);
        view.setScaleY(fAbs);
        view.setElevation(-1.0f);
    }
}
