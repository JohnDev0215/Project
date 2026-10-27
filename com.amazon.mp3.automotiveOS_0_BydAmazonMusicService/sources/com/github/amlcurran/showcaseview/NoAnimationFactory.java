package com.github.amlcurran.showcaseview;

import android.graphics.Point;
import android.view.View;
import com.github.amlcurran.showcaseview.AnimationFactory;

/* JADX INFO: loaded from: classes6.dex */
class NoAnimationFactory implements AnimationFactory {
    NoAnimationFactory() {
    }

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void fadeInView(View view, long j, AnimationFactory.AnimationStartListener animationStartListener) {
        animationStartListener.onAnimationStart();
    }

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void fadeOutView(View view, long j, AnimationFactory.AnimationEndListener animationEndListener) {
        animationEndListener.onAnimationEnd();
    }

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void animateTargetToPoint(ShowcaseView showcaseView, Point point) {
        showcaseView.setShowcasePosition(point.x, point.y);
    }
}
