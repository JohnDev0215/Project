package com.android.launcher3.home.viewpage;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.android.launcher3.R;
import com.android.launcher3.views.BlurImageView;

/* JADX INFO: loaded from: classes4.dex */
public class ViewpagerTransformAnim implements ViewPager.PageTransformer {
    private static final String TAG = "ViewpagerTransformAnim";

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View page, float position) {
        BlurImageView blurImageView = (BlurImageView) page.findViewById(R.id.iv_wallpager);
        int width = page.getWidth();
        if (position < -1.0f) {
            page.setAlpha(1.0f);
            return;
        }
        if (position <= 1.0f) {
            int width2 = (int) ((-position) * blurImageView.getWidth());
            blurImageView.setScrolling((width2 == 0 || Math.abs(width2) == blurImageView.getWidth()) ? false : true);
            if (position > 0.0f) {
                blurImageView.setScrolling(false);
                float f = (-width) * position;
                page.setTranslationX(Math.max(f, (((1.0f - Math.abs(position)) / 2.5f) * f) - 400.0f));
                blurImageView.setTranslationX(0.0f);
                return;
            }
            blurImageView.setTansLength((int) (Math.abs(position) * width));
            page.setTranslationX(0.0f);
            return;
        }
        page.setAlpha(1.0f);
    }
}
