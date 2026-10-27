package com.azoft.carousellayoutmanager;

import android.view.View;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;

/* JADX INFO: loaded from: classes4.dex */
public class CarouselZoomPostLayoutListener implements CarouselLayoutManager.PostLayoutListener {
    @Override // com.azoft.carousellayoutmanager.CarouselLayoutManager.PostLayoutListener
    public ItemTransformation transformChild(View view, float f, int i) {
        float fSignum;
        float f2 = (float) (((((-StrictMath.atan(((double) Math.abs(f)) + 1.0d)) * 2.0d) / 3.141592653589793d) + 1.0d) * 2.0d);
        float fSignum2 = 0.0f;
        if (1 == i) {
            fSignum = Math.signum(f) * ((view.getMeasuredHeight() * (1.0f - f2)) / 2.0f);
        } else {
            fSignum2 = Math.signum(f) * ((view.getMeasuredWidth() * (1.0f - f2)) / 2.0f);
            fSignum = 0.0f;
        }
        return new ItemTransformation(f2, f2, fSignum2, fSignum);
    }
}
