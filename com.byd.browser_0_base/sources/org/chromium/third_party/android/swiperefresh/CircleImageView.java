package org.chromium.third_party.android.swiperefresh;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes3.dex */
public class CircleImageView extends ImageView {
    public Animation.AnimationListener e;
    public final int f;
    public final int g;

    public CircleImageView(Context context) {
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        int i = (int) (3.5f * f);
        this.f = i;
        this.g = i;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        setElevation(f * 4.0f);
        shapeDrawable.getPaint().setColor(-328966);
        setBackground(shapeDrawable);
    }

    @Override // android.view.View
    public final void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.e;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public final void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.e;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.f;
        int i4 = this.g;
        if (i4 > i3) {
            setMeasuredDimension((i4 * 2) + getMeasuredWidth(), (i4 * 2) + getMeasuredHeight());
        }
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.e = animationListener;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }

    public void setBackgroundColorRes(int i) {
        setBackgroundColor(getContext().getResources().getColor(i));
    }

    public void setProgress(float f) {
    }
}
