package org.chromium.chrome.browser.toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import defpackage.pc;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ToolbarProgressBarAnimatingView extends ImageView {
    public final ColorDrawable e;
    public final pc f;
    public float g;
    public final AnimatorSet h;
    public final ValueAnimator i;
    public final ValueAnimator j;
    public boolean k;
    public final boolean l;
    public float m;
    public ValueAnimator n;
    public final float o;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = ToolbarProgressBarAnimatingView.this;
            if (toolbarProgressBarAnimatingView.k) {
                return;
            }
            toolbarProgressBarAnimatingView.h.setStartDelay(1000L);
            toolbarProgressBarAnimatingView.b();
            if (animator == toolbarProgressBarAnimatingView.i) {
                toolbarProgressBarAnimatingView.h.start();
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = ToolbarProgressBarAnimatingView.this;
            toolbarProgressBarAnimatingView.n = valueAnimator;
            toolbarProgressBarAnimatingView.m = valueAnimator.getAnimatedFraction();
            toolbarProgressBarAnimatingView.a(toolbarProgressBarAnimatingView.n, toolbarProgressBarAnimatingView.m);
        }
    }

    public ToolbarProgressBarAnimatingView(Context context, FrameLayout.LayoutParams layoutParams) {
        super(context);
        this.f = pc.d;
        setLayoutParams(layoutParams);
        this.k = true;
        this.l = LocalizationUtils.isLayoutRtl();
        this.o = getResources().getDisplayMetrics().density;
        ColorDrawable colorDrawable = new ColorDrawable(-1);
        this.e = colorDrawable;
        setImageDrawable(colorDrawable);
        setAlpha(0.0f);
        b bVar = new b();
        AnimatorSet animatorSet = new AnimatorSet();
        this.h = animatorSet;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.j = valueAnimator;
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.addUpdateListener(bVar);
        ValueAnimator valueAnimator2 = new ValueAnimator();
        this.i = valueAnimator2;
        valueAnimator2.setFloatValues(0.0f, 1.0f);
        valueAnimator2.addUpdateListener(bVar);
        b();
        animatorSet.playSequentially(valueAnimator, valueAnimator2);
        a aVar = new a();
        valueAnimator.addListener(aVar);
        valueAnimator2.addListener(aVar);
    }

    public final void a(ValueAnimator valueAnimator, float f) {
        if (this.k) {
            return;
        }
        float interpolation = this.f.getInterpolation(f);
        boolean z = this.l;
        float f2 = z ? -this.g : 0.0f;
        float f3 = z ? 0.0f : this.g;
        float fMin = Math.min(this.o * 400.0f, this.g * ((valueAnimator != this.j || f > 0.6f) ? 0.3f : ((f / 0.6f) * 0.20000002f) + 0.1f));
        float f4 = fMin / 2.0f;
        float fAbs = ((this.g + fMin) * interpolation) - f4;
        if (z) {
            fAbs *= -1.0f;
        }
        float f5 = fAbs + f4;
        float f6 = fAbs - f4;
        if (f5 > f3) {
            float f7 = f5 - f3;
            fMin -= Math.abs(f7);
            fAbs -= Math.abs(f7) / 2.0f;
        } else if (f6 < f2) {
            float f8 = f6 - f2;
            fMin -= Math.abs(f8);
            fAbs += Math.abs(f8) / 2.0f;
        }
        setScaleX(fMin);
        setTranslationX(fAbs);
    }

    public final void b() {
        if (this.g <= 0.0f) {
            return;
        }
        long jLog = ((long) (Math.log(r0 / this.o) / Math.log(2.718281828459045d))) * 260;
        if (jLog <= 0) {
            return;
        }
        float f = jLog;
        this.j.setDuration((long) (0.6f * f));
        ValueAnimator valueAnimator = this.i;
        valueAnimator.setStartDelay((long) (0.02f * f));
        valueAnimator.setDuration((long) (f * 0.38f));
    }

    public void setColor(int i) {
        this.e.setColor(i);
    }
}
