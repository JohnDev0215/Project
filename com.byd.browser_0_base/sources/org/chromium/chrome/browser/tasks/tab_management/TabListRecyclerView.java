package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0175b;
import defpackage.df0;
import defpackage.pc;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.tasks.tab_management.j0;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class TabListRecyclerView extends RecyclerView implements j0.m {
    public static final /* synthetic */ int d1 = 0;
    public final int P0;
    public ValueAnimator Q0;
    public ValueAnimator R0;
    public d S0;
    public df0 T0;
    public s0 U0;
    public boolean V0;
    public long W0;
    public ImageView X0;
    public int Y0;
    public c Z0;
    public RecyclerView.j a1;
    public o0 b1;
    public boolean c1;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TabListRecyclerView tabListRecyclerView = TabListRecyclerView.this;
            tabListRecyclerView.R0 = null;
            tabListRecyclerView.setVisibility(4);
            tabListRecyclerView.c1 = true;
            tabListRecyclerView.S0.d();
        }
    }

    public static class b {
        public final int a;
        public final int b;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public class c extends RecyclerView.q {
        public c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void b(RecyclerView recyclerView, int i, int i2) {
            int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
            TabListRecyclerView tabListRecyclerView = TabListRecyclerView.this;
            if (iComputeVerticalScrollOffset == 0) {
                tabListRecyclerView.q0(false);
            } else {
                if (i2 == 0 || recyclerView.getScrollState() == 2) {
                    return;
                }
                tabListRecyclerView.q0(iComputeVerticalScrollOffset > 0);
            }
        }
    }

    public interface d {
        void d();

        void e();

        void f();

        void h();
    }

    public TabListRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c1 = true;
        this.P0 = toString().hashCode();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        ViewParent viewParentInvalidateChildInParent = super.invalidateChildInParent(iArr, rect);
        s0 s0Var = this.U0;
        if (s0Var != null) {
            s0Var.g(rect);
        }
        return viewParentInvalidateChildInParent;
    }

    public final int o0() {
        int i = 0;
        for (int i2 = 0; i2 < getAdapter().k(); i2++) {
            if (getAdapter().m(i2) == 1) {
                i++;
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c cVar = new c();
        this.Z0 = cVar;
        addOnScrollListener(cVar);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    @SuppressLint({"NewApi"})
    public final void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        s0 s0Var = this.U0;
        if (s0Var != null) {
            s0Var.g(null);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ImageView imageView = this.X0;
        if (imageView != null) {
            removeViewInLayout(imageView);
            this.X0 = null;
        }
        c cVar = this.Z0;
        if (cVar != null) {
            removeOnScrollListener(cVar);
            this.Z0 = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        o0 o0Var = this.b1;
        if (o0Var != null) {
            this.b1 = null;
            o0Var.run();
        }
    }

    public final void p0(boolean z) {
        this.S0.f();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<TabListRecyclerView, Float>) View.ALPHA, 0.0f);
        this.R0 = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setInterpolator(pc.d);
        this.R0.setDuration(218L);
        this.R0.addListener(new a());
        q0(false);
        this.R0.start();
        if (z) {
            return;
        }
        this.R0.end();
    }

    public final void q0(boolean z) {
        if (this.X0 == null) {
            Context context = getContext();
            this.X0 = new ImageView(context);
            this.X0.setImageDrawable(context.getDrawable(R$drawable.toolbar_hairline));
            this.X0.setScaleType(ImageView.ScaleType.FIT_XY);
            this.X0.setTag("TabListViewShadow");
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.toolbar_hairline_height);
            if (getParent() instanceof FrameLayout) {
                this.X0.setLayoutParams(new FrameLayout.LayoutParams(-1, dimensionPixelSize, 48));
                this.X0.setTranslationY(this.Y0);
                ((FrameLayout) getParent()).addView(this.X0);
            } else if (getParent() instanceof RelativeLayout) {
                RelativeLayout relativeLayout = (RelativeLayout) getParent();
                View childAt = relativeLayout.getChildAt(0);
                if (!(childAt instanceof TabGroupUiToolbarView)) {
                    return;
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dimensionPixelSize);
                layoutParams.addRule(3, childAt.getId());
                relativeLayout.addView(this.X0, layoutParams);
            }
        }
        if (z && this.X0.getVisibility() != 0) {
            this.X0.setVisibility(0);
        } else {
            if (z || this.X0.getVisibility() == 8) {
                return;
            }
            this.X0.setVisibility(8);
        }
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i) {
        try {
            int childCount = getChildCount();
            if (childCount <= 0) {
                requestLayout();
                invalidate();
                return;
            }
            if (i < 0) {
                i = 0;
            }
            if (i >= childCount) {
                i = childCount - 1;
            }
            super.removeViewAt(i);
        } catch (Exception e) {
            StringBuilder sbM = C0175b.m("removeViewAt: ", i, "");
            sbM.append(getChildCount());
            Log.e("TabListRecyclerView", sbM.toString());
            Log.e("TabListRecyclerView", e.toString());
        }
    }
}
