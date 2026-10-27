package com.amazon.mp3.video.stories.storyqueue;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StackingPageTransformer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/video/stories/storyqueue/StackingPageTransformer;", "Landroidx/viewpager/widget/ViewPager$PageTransformer;", "()V", "transformPage", "", "view", "Landroid/view/View;", "position", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StackingPageTransformer implements ViewPager.PageTransformer {
    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float position) {
        Intrinsics.checkNotNullParameter(view, "view");
        int width = view.getWidth();
        if (-1.0f > position || position > 0.0f) {
            if (position <= 1.0f) {
                view.setTranslationX(0.0f);
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                return;
            }
            return;
        }
        float f = 1;
        float fAbs = ((f - Math.abs(position)) * 0.01999998f) + 0.98f;
        view.setScaleX(fAbs);
        view.setScaleY(fAbs);
        view.setTranslationX(((width * (-position)) - ((1.0f - fAbs) * view.getWidth())) - ((0.1f - ((f - Math.abs(position)) * 0.1f)) * view.getWidth()));
    }
}
