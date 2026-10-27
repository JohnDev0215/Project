package org.chromium.components.browser_ui.banners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import defpackage.ax2;
import defpackage.bx2;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SwipableOverlayView extends FrameLayout {
    public final ax2 e;
    public final bx2 f;
    public final DecelerateInterpolator g;
    public int h;
    public Animator i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public WebContents n;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            SwipableOverlayView swipableOverlayView = SwipableOverlayView.this;
            swipableOverlayView.h = 0;
            swipableOverlayView.i = null;
            swipableOverlayView.m = false;
        }
    }

    public SwipableOverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, true);
    }

    public final boolean a() {
        if (this.m) {
            return false;
        }
        Animator animator = this.i;
        if (animator == null) {
            return true;
        }
        animator.cancel();
        return true;
    }

    public final ObjectAnimator b(boolean z) {
        float f = z ? 0.0f : this.l;
        long jMax = Math.max(0L, (long) ((Math.abs(f - getTranslationY()) / this.l) * 250.0f));
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<SwipableOverlayView, Float>) View.TRANSLATION_Y, f);
        objectAnimatorOfFloat.setDuration(jMax);
        objectAnimatorOfFloat.setInterpolator(this.g);
        return objectAnimatorOfFloat;
    }

    public boolean d() {
        return true;
    }

    public boolean e() {
        return false;
    }

    public final boolean f() {
        if (getParent() == null) {
            return false;
        }
        ((ViewGroup) getParent()).removeView(this);
        removeOnLayoutChangeListener(this.f);
        return true;
    }

    public void g(boolean z) {
        Animator animator = this.i;
        if (animator != null) {
            animator.cancel();
        }
        ObjectAnimator objectAnimatorB = b(z);
        this.i = objectAnimatorB;
        objectAnimatorB.addListener(new a());
        this.i.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean gatherTransparentRegion(Region region) {
        float translationY = getTranslationY();
        setTranslationY(0.0f);
        boolean zGatherTransparentRegion = super.gatherTransparentRegion(region);
        setTranslationY(translationY);
        return zGatherTransparentRegion;
    }

    public int getTotalHeight() {
        return this.l;
    }

    public WebContents getWebContents() {
        return this.n;
    }

    public boolean h(int i) {
        return true;
    }

    public final boolean i(int i) {
        return ((((float) i) > (((float) this.l) * 0.5f) ? 1 : (((float) i) == (((float) this.l) * 0.5f) ? 0 : -1)) < 0) || ((getTranslationY() > (((float) this.l) * 0.5f) ? 1 : (getTranslationY() == (((float) this.l) * 0.5f) ? 0 : -1)) < 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (d()) {
            return;
        }
        setTranslationY(0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ax2 ax2Var;
        int height = getParent() == null ? 0 : ((View) getParent()).getHeight();
        if (this.j != height) {
            this.j = height;
            this.h = 0;
            Animator animator = this.i;
            if (animator != null) {
                animator.end();
            }
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int measuredHeight = getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        this.l = measuredHeight;
        WebContents webContents = this.n;
        if (webContents != null && (ax2Var = this.e) != null) {
            if (measuredHeight > 0) {
                GestureListenerManagerImpl.e(webContents).d(ax2Var, 2);
            } else {
                GestureListenerManagerImpl.e(webContents).h(ax2Var);
            }
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (d()) {
            return;
        }
        setTranslationY(0.0f);
    }

    public void setWebContents(WebContents webContents) {
        WebContents webContents2 = this.n;
        ax2 ax2Var = this.e;
        if (webContents2 != null) {
            GestureListenerManagerImpl.e(webContents2).h(ax2Var);
        }
        this.n = webContents;
        if (webContents == null || this.l <= 0) {
            return;
        }
        GestureListenerManagerImpl.e(webContents).d(ax2Var, 2);
    }

    public SwipableOverlayView(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.e = z ? new ax2(this) : null;
        this.h = 0;
        this.f = new bx2(this);
        this.g = new DecelerateInterpolator(1.0f);
        setWillNotDraw(false);
    }
}
