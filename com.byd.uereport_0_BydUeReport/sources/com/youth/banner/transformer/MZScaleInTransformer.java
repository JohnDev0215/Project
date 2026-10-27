package com.youth.banner.transformer;

import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: loaded from: classes2.dex */
public class MZScaleInTransformer extends BasePageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale;

    public MZScaleInTransformer() {
        this.mMinScale = DEFAULT_MIN_SCALE;
    }

    private ViewPager2 requireViewPager(View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof ViewPager2)) {
            return (ViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f8) {
        float paddingLeft = requireViewPager(view).getPaddingLeft();
        float measuredWidth = f8 - (paddingLeft / ((r0.getMeasuredWidth() - paddingLeft) - r0.getPaddingRight()));
        float width = view.getWidth();
        float f9 = this.mMinScale;
        float f10 = ((1.0f - f9) * width) / 2.0f;
        if (measuredWidth <= -1.0f) {
            view.setTranslationX(f10);
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
            return;
        }
        double d8 = measuredWidth;
        if (d8 > 1.0d) {
            view.setScaleX(f9);
            view.setScaleY(this.mMinScale);
            view.setTranslationX(-f10);
            return;
        }
        float fAbs = (1.0f - f9) * Math.abs(1.0f - Math.abs(measuredWidth));
        float f11 = (-f10) * measuredWidth;
        if (d8 <= -0.5d) {
            view.setTranslationX(f11 + (Math.abs(Math.abs(measuredWidth) - 0.5f) / 0.5f));
        } else if (measuredWidth > 0.0f && d8 >= 0.5d) {
            view.setTranslationX(f11 - (Math.abs(Math.abs(measuredWidth) - 0.5f) / 0.5f));
        } else {
            view.setTranslationX(f11);
        }
        view.setScaleX(this.mMinScale + fAbs);
        view.setScaleY(fAbs + this.mMinScale);
    }

    public MZScaleInTransformer(float f8) {
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mMinScale = f8;
    }
}
