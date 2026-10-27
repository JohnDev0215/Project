package org.chromium.chrome.browser.toolbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import defpackage.a8;
import defpackage.ii2;
import defpackage.in3;
import defpackage.l62;
import defpackage.om3;
import defpackage.pc;
import defpackage.uh3;
import defpackage.uw;
import defpackage.ya0;
import defpackage.yp;
import defpackage.z7;
import defpackage.zx1;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.components.browser_ui.widget.ClipDrawableProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public class ToolbarProgressBar extends ClipDrawableProgressBar {
    public static final /* synthetic */ int z = 0;
    public boolean i;
    public float j;
    public final l62 k;
    public boolean l;
    public int m;
    public ViewGroup n;
    public int o;
    public int p;
    public final boolean q;
    public ToolbarProgressBarAnimatingView r;
    public boolean s;
    public View t;
    public final int u;
    public Animator v;
    public final uw w;
    public final a x;
    public final TimeAnimator y;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ToolbarProgressBar toolbarProgressBar = ToolbarProgressBar.this;
            if (toolbarProgressBar.i) {
                float progress = toolbarProgressBar.getProgress();
                l62 l62Var = toolbarProgressBar.k;
                l62Var.a = progress;
                l62Var.b = 0.0f;
                toolbarProgressBar.y.start();
                if (toolbarProgressBar.r != null) {
                    int iAbs = Math.abs(toolbarProgressBar.getDrawable().getBounds().right - toolbarProgressBar.getDrawable().getBounds().left);
                    ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = toolbarProgressBar.r;
                    toolbarProgressBarAnimatingView.g = toolbarProgressBar.getProgress() * iAbs;
                    toolbarProgressBarAnimatingView.a(toolbarProgressBarAnimatingView.n, toolbarProgressBarAnimatingView.m);
                    ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView2 = toolbarProgressBar.r;
                    toolbarProgressBarAnimatingView2.k = false;
                    AnimatorSet animatorSet = toolbarProgressBarAnimatingView2.h;
                    if (animatorSet.isStarted()) {
                        return;
                    }
                    toolbarProgressBarAnimatingView2.b();
                    animatorSet.setStartDelay(0L);
                    toolbarProgressBarAnimatingView2.setScaleX(0.0f);
                    toolbarProgressBarAnimatingView2.setTranslationX(0.0f);
                    animatorSet.start();
                    toolbarProgressBarAnimatingView2.animate().alpha(1.0f).setDuration(500L).setInterpolator(pc.e);
                }
            }
        }
    }

    public class b implements TimeAnimator.TimeListener {
        public b() {
        }

        @Override // android.animation.TimeAnimator.TimeListener
        public final void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
            float fMax;
            ToolbarProgressBar toolbarProgressBar = ToolbarProgressBar.this;
            if (a8.h(toolbarProgressBar.getProgress(), toolbarProgressBar.j)) {
                return;
            }
            float f = toolbarProgressBar.j;
            float fMin = Math.min(j2, 50L) * 0.001f;
            int width = toolbarProgressBar.getWidth();
            l62 l62Var = toolbarProgressBar.k;
            l62Var.getClass();
            if (f == 1.0f) {
                fMax = fMin;
            } else {
                fMax = Math.max(0.0f, Math.min(fMin, ((-6.6666665f) * l62Var.b) + ((float) Math.sqrt((22.222221f * r2 * r2) + ((f - l62Var.a) * 6.6666665f)))));
            }
            float f2 = fMin - fMax;
            if (fMax > 0.0f) {
                float f3 = (f == 1.0f ? 7.0f : 0.15f) * fMax;
                float f4 = l62Var.a;
                float f5 = l62Var.b;
                l62Var.a = (((f3 * 0.5f) + f5) * fMax) + f4;
                l62Var.b = f5 + f3;
            }
            if (f2 > 0.0f) {
                float f6 = (-0.15f) * f2;
                float f7 = l62Var.a;
                float f8 = l62Var.b;
                l62Var.a = (((f6 * 0.5f) + f8) * f2) + f7;
                l62Var.b = f8 + f6;
            }
            float fMin2 = Math.min(l62Var.a, f);
            l62Var.a = fMin2;
            if (f - fMin2 < 0.5f / width) {
                l62Var.a = f;
                l62Var.b = 0.0f;
            }
            float fMax2 = Math.max(l62Var.a, 0.0f);
            ToolbarProgressBar.super.setProgress(fMax2);
            if (toolbarProgressBar.r != null) {
                int iAbs = Math.abs(toolbarProgressBar.getDrawable().getBounds().right - toolbarProgressBar.getDrawable().getBounds().left);
                ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = toolbarProgressBar.r;
                toolbarProgressBarAnimatingView.g = fMax2 * iAbs;
                toolbarProgressBarAnimatingView.a(toolbarProgressBarAnimatingView.n, toolbarProgressBarAnimatingView.m);
            }
            if (a8.h(toolbarProgressBar.getProgress(), 1.0f)) {
                toolbarProgressBar.c(true);
            }
        }
    }

    public ToolbarProgressBar(int i, Context context, View view) {
        super(context, i);
        this.w = new uw(3, this);
        this.x = new a();
        TimeAnimator timeAnimator = new TimeAnimator();
        this.y = timeAnimator;
        timeAnimator.setTimeListener(new b());
        this.u = i;
        setAlpha(0.0f);
        setAnchorView(view);
        this.q = false;
        this.k = new l62();
        setVisibility(0);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.g.e(this, 1);
    }

    private void setProgressInternal(float f) {
        if (!this.i || a8.h(this.j, f)) {
            return;
        }
        this.j = f;
        a aVar = this.x;
        removeCallbacks(aVar);
        if (!this.y.isRunning()) {
            postDelayed(aVar, 5000L);
            super.setProgress(this.j);
        }
        sendAccessibilityEvent(4);
        if (a8.h(f, 1.0f) || f > 1.0f) {
            c(true);
        }
    }

    private void setTopMargin(int i) {
        this.m = i;
        if (this.s) {
            ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = this.m;
            ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
            if (toolbarProgressBarAnimatingView == null || toolbarProgressBarAnimatingView.getLayoutParams() == null) {
                return;
            }
            ((ViewGroup.MarginLayoutParams) this.r.getLayoutParams()).topMargin = this.m;
        }
    }

    public final void b(float f) {
        float alpha = f - getAlpha();
        if (alpha == 0.0f) {
            return;
        }
        long jAbs = (long) Math.abs(140.0f * alpha);
        pc pcVar = pc.e;
        if (alpha < 0.0f) {
            pcVar = pc.d;
        }
        Animator animator = this.v;
        if (animator != null) {
            animator.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<ToolbarProgressBar, Float>) ImageView.ALPHA, getAlpha(), f);
        objectAnimatorOfFloat.setDuration(jAbs);
        objectAnimatorOfFloat.setInterpolator(pcVar);
        this.v = objectAnimatorOfFloat;
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(toolbarProgressBarAnimatingView, (Property<ToolbarProgressBarAnimatingView, Float>) ImageView.ALPHA, toolbarProgressBarAnimatingView.getAlpha(), f);
            objectAnimatorOfFloat2.setDuration(jAbs);
            objectAnimatorOfFloat2.setInterpolator(pcVar);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(this.v, objectAnimatorOfFloat2);
            this.v = animatorSet;
        }
        this.v.start();
    }

    public final void c(boolean z2) {
        Object obj = ThreadUtils.a;
        boolean zH = a8.h(getProgress(), 1.0f);
        TimeAnimator timeAnimator = this.y;
        if (!zH) {
            setProgress(1.0f);
            if (timeAnimator.isRunning() && z2) {
                return;
            }
        }
        this.i = false;
        this.j = 0.0f;
        removeCallbacks(this.x);
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            toolbarProgressBarAnimatingView.k = true;
            toolbarProgressBarAnimatingView.h.cancel();
            toolbarProgressBarAnimatingView.setScaleX(0.0f);
            toolbarProgressBarAnimatingView.setTranslationX(0.0f);
            toolbarProgressBarAnimatingView.animate().cancel();
            toolbarProgressBarAnimatingView.setAlpha(0.0f);
            toolbarProgressBarAnimatingView.m = 0.0f;
            toolbarProgressBarAnimatingView.g = 0.0f;
        }
        timeAnimator.cancel();
        if (z2) {
            postDelayed(new zx1(22, this), 100L);
        } else {
            if (this.i) {
                return;
            }
            animate().cancel();
            setAlpha(0.0f);
        }
    }

    public final void d() {
        if (this.l) {
            return;
        }
        this.l = true;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getLayoutParams());
        layoutParams.width = 1;
        layoutParams.topMargin = this.m;
        this.r = new ToolbarProgressBarAnimatingView(getContext(), layoutParams);
        int i = this.p;
        if (i != 0 || this.q) {
            e(i, false);
        } else {
            setForegroundColor(getForegroundColor());
        }
        Log.e("fww    ", "initializeAnimation:  " + uh3.i(this.n, this.r, this, true));
    }

    public final void e(int i, boolean z2) {
        this.p = i;
        boolean z3 = this.p == yp.a(getContext(), z2);
        if (this.q) {
            setForegroundColor(getContext().getColor(R$color.modern_grey_400));
            setBackgroundColor(0);
            return;
        }
        if ((z3 || z7.S(i)) && !z2) {
            setForegroundColor(ii2.b(getContext()));
            setBackgroundColor(0);
            return;
        }
        setForegroundColor((z7.h0(i) || z2) ? -1 : z7.o(i, -16777216, 0.64f, false));
        if (this.r != null && (z7.h0(i) || z2)) {
            this.r.setColor(z7.o(i, -1, 0.4f, false));
        }
        setBackgroundColor(0);
    }

    public final void f() {
        Object obj = ThreadUtils.a;
        this.i = true;
        this.o++;
        a aVar = this.x;
        removeCallbacks(aVar);
        postDelayed(aVar, 5000L);
        super.setProgress(0.0f);
        l62 l62Var = this.k;
        l62Var.a = 0.0f;
        l62Var.b = 0.0f;
        b(1.0f);
    }

    public final void g() {
        int dimension;
        View view = this.t;
        if (view != null) {
            dimension = view.getMeasuredHeight();
        } else {
            dimension = (int) ((((int) getResources().getDimension(R$dimen.modern_toolbar_tablet_background_size)) * ya0.a(getContext()).d) + 0.5f);
        }
        int i = dimension - this.u;
        if (this.m != i) {
            setTopMargin(i);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    public Animator getIndeterminateAnimatorForTesting() {
        return this.y;
    }

    public int getStartCountForTesting() {
        return this.o;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s = true;
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = this.m;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.s = false;
        TimeAnimator timeAnimator = this.y;
        timeAnimator.setTimeListener(null);
        timeAnimator.cancel();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setCurrentItemIndex((int) (this.j * 100.0f));
        accessibilityEvent.setItemCount(100);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            toolbarProgressBarAnimatingView.g = getProgress() * i;
            toolbarProgressBarAnimatingView.a(toolbarProgressBarAnimatingView.n, toolbarProgressBarAnimatingView.m);
        }
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            toolbarProgressBarAnimatingView.setAlpha(f);
        }
    }

    public void setAnchorView(View view) {
        View view2 = this.t;
        if (view2 == view) {
            return;
        }
        uw uwVar = this.w;
        if (view2 != null) {
            view2.removeOnLayoutChangeListener(uwVar);
        }
        this.t = view;
        g();
        View view3 = this.t;
        if (view3 != null) {
            view3.addOnLayoutChangeListener(uwVar);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.ClipDrawableProgressBar
    public void setForegroundColor(int i) {
        int color = getContext().getColor(R$color.color_pro_url_load);
        super.setForegroundColor(color);
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            toolbarProgressBarAnimatingView.setColor(z7.o(color, -1, 0.4f, false));
        }
    }

    @Override // org.chromium.components.browser_ui.widget.ClipDrawableProgressBar
    public void setProgress(float f) {
        Object obj = ThreadUtils.a;
        setProgressInternal(f);
    }

    public void setProgressBarContainer(ViewGroup viewGroup) {
        this.n = viewGroup;
    }

    @Override // org.chromium.components.browser_ui.widget.ClipDrawableProgressBar, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        ToolbarProgressBarAnimatingView toolbarProgressBarAnimatingView = this.r;
        if (toolbarProgressBarAnimatingView != null) {
            toolbarProgressBarAnimatingView.setVisibility(i);
        }
    }
}
