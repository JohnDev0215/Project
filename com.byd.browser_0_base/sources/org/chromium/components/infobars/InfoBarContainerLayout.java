package org.chromium.components.infobars;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.l10;
import defpackage.tz0;
import defpackage.xz0;
import defpackage.yz0;
import java.util.ArrayList;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class InfoBarContainerLayout extends OptimizedFrameLayout {
    public static final /* synthetic */ int m = 0;
    public final int f;
    public final ArrayList<xz0> g;
    public final ArrayList<yz0> h;
    public final tz0 i;
    public g j;
    public final c k;
    public final Runnable l;

    public class a extends g {
        public final yz0 c;

        public a(xz0 xz0Var) {
            super();
            this.c = new yz0(InfoBarContainerLayout.this.getContext(), xz0Var);
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            yz0 yz0Var = this.c;
            yz0Var.setTranslationY(yz0Var.getHeight());
            return b(yz0Var, 0.0f).setDuration(250L);
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 0;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            yz0 yz0Var = this.c;
            yz0Var.removeView(yz0Var.e.getView());
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            int i = InfoBarContainerLayout.m;
            InfoBarContainerLayout.this.j(this.c);
        }
    }

    public class b extends g {
        public final xz0 c;
        public yz0 d;
        public View e;

        public b(xz0 xz0Var) {
            super();
            this.c = xz0Var;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            this.d.setTranslationY(r0.getHeight());
            this.e.setAlpha(0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(b(this.d, 0.0f).setDuration(250L), ObjectAnimator.ofFloat(this.e, (Property<View, Float>) View.ALPHA, 1.0f).setDuration(100L));
            return animatorSet;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 0;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            InfoBarContainerLayout.this.announceForAccessibility(this.c.i());
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            xz0 xz0Var = this.c;
            this.e = xz0Var.getView();
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            yz0 yz0Var = new yz0(infoBarContainerLayout.getContext(), xz0Var);
            this.d = yz0Var;
            yz0Var.addView(this.e);
            yz0 yz0Var2 = this.d;
            int i = InfoBarContainerLayout.m;
            infoBarContainerLayout.j(yz0Var2);
        }
    }

    public static class c {
        public final FrameLayout a;
        public final int b;
        public final int c;
        public boolean d;
        public View e;
        public View f;

        public c(FrameLayout frameLayout) {
            this.a = frameLayout;
            Resources resources = frameLayout.getContext().getResources();
            this.b = resources.getDimensionPixelSize(R$dimen.infobar_max_width);
            this.c = resources.getDimensionPixelSize(R$dimen.infobar_shadow_width);
        }

        public final void a() {
            if (this.d) {
                FrameLayout frameLayout = this.a;
                float height = frameLayout.getHeight();
                int childCount = frameLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = frameLayout.getChildAt(i);
                    if (childAt != this.e && childAt != this.f) {
                        height = Math.min(height, childAt.getY());
                    }
                }
                this.e.setY(height);
                this.f.setY(height);
            }
        }
    }

    public class d extends g {
        public final xz0 c;
        public yz0 d;
        public yz0 e;
        public View f;

        public d(xz0 xz0Var) {
            super();
            this.c = xz0Var;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            int i;
            int height = this.d.getHeight();
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            int height2 = (height + infoBarContainerLayout.f) - this.e.getHeight();
            int height3 = this.d.getHeight();
            if (height2 < 0) {
                height3 -= height2;
                i = 0 - height2;
            } else {
                i = 0;
            }
            this.d.setTranslationY(height3);
            this.f.setAlpha(0.0f);
            infoBarContainerLayout.l.run();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(b(this.d, i).setDuration(250L));
            int iMax = Math.max(0, height2);
            int iMax2 = Math.max(-height2, 0);
            for (int i2 = 1; i2 < infoBarContainerLayout.h.size(); i2++) {
                infoBarContainerLayout.h.get(i2).setTranslationY(iMax);
                animatorSet.play(b(infoBarContainerLayout.h.get(i2), iMax2).setDuration(250L));
            }
            animatorSet.play(ObjectAnimator.ofFloat(this.f, (Property<View, Float>) View.ALPHA, 1.0f).setDuration(100L)).after(250L);
            return animatorSet;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 0;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            this.e.removeAllViews();
            int i = 0;
            while (true) {
                InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
                if (i >= infoBarContainerLayout.h.size()) {
                    infoBarContainerLayout.n();
                    infoBarContainerLayout.announceForAccessibility(this.c.i());
                    return;
                } else {
                    infoBarContainerLayout.h.get(i).setTranslationY(0.0f);
                    i++;
                }
            }
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            this.e = infoBarContainerLayout.h.get(0);
            xz0 xz0Var = this.c;
            this.f = xz0Var.getView();
            yz0 yz0Var = new yz0(infoBarContainerLayout.getContext(), xz0Var);
            this.d = yz0Var;
            yz0Var.addView(this.f);
            yz0 yz0Var2 = this.d;
            infoBarContainerLayout.getClass();
            infoBarContainerLayout.addView(yz0Var2, new FrameLayout.LayoutParams(-1, -2));
            infoBarContainerLayout.h.add(0, yz0Var2);
            infoBarContainerLayout.n();
        }
    }

    public class e extends g {
        public yz0 c;
        public yz0 d;
        public View e;

        public e() {
            super();
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            int height = this.d.getHeight();
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            int height2 = (height - infoBarContainerLayout.f) - this.c.getHeight();
            int iMax = Math.max(height2, 0);
            int iMax2 = Math.max(-height2, 0);
            AnimatorSet animatorSet = new AnimatorSet();
            float f = iMax;
            this.c.setTranslationY(f);
            animatorSet.play(b(this.c, r5.getHeight() + iMax).setDuration(250L));
            for (int i = 1; i < infoBarContainerLayout.h.size(); i++) {
                infoBarContainerLayout.h.get(i).setTranslationY(f);
                animatorSet.play(b(infoBarContainerLayout.h.get(i), iMax2).setDuration(250L));
            }
            this.e.setAlpha(0.0f);
            animatorSet.play(ObjectAnimator.ofFloat(this.e, (Property<View, Float>) View.ALPHA, 1.0f).setDuration(100L)).after(250L);
            return animatorSet;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 2;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            this.c.removeAllViews();
            yz0 yz0Var = this.c;
            int i = InfoBarContainerLayout.m;
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            infoBarContainerLayout.removeView(yz0Var);
            infoBarContainerLayout.h.remove(yz0Var);
            infoBarContainerLayout.n();
            int i2 = 0;
            while (true) {
                ArrayList<yz0> arrayList = infoBarContainerLayout.h;
                if (i2 >= arrayList.size()) {
                    infoBarContainerLayout.announceForAccessibility(this.d.e.i());
                    return;
                } else {
                    arrayList.get(i2).setTranslationY(0.0f);
                    i2++;
                }
            }
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            this.c = infoBarContainerLayout.h.get(0);
            yz0 yz0Var = infoBarContainerLayout.h.get(1);
            this.d = yz0Var;
            View view = yz0Var.e.getView();
            this.e = view;
            this.d.addView(view);
        }
    }

    public class f extends g {
        public yz0 c;
        public View d;
        public View e;

        public f() {
            super();
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            float fMax = Math.max(0, this.e.getHeight() - this.d.getHeight());
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            infoBarContainerLayout.setTranslationY(fMax);
            this.e.setAlpha(0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(ObjectAnimator.ofFloat(this.d, (Property<View, Float>) View.ALPHA, 0.0f).setDuration(200L), ObjectAnimator.ofFloat(infoBarContainerLayout, (Property<InfoBarContainerLayout, Float>) View.TRANSLATION_Y, Math.max(0, -r0)).setDuration(250L), ObjectAnimator.ofFloat(this.e, (Property<View, Float>) View.ALPHA, 1.0f).setDuration(200L));
            return animatorSet;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 1;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            this.c.removeViewAt(0);
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            infoBarContainerLayout.setTranslationY(0.0f);
            this.c.e.b();
            infoBarContainerLayout.announceForAccessibility(this.c.e.i());
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            yz0 yz0Var = InfoBarContainerLayout.this.h.get(0);
            this.c = yz0Var;
            this.d = yz0Var.getChildAt(0);
            View view = this.c.e.getView();
            this.e = view;
            this.c.addView(view);
        }
    }

    public abstract class g {
        public Animator a;

        public class a implements ValueAnimator.AnimatorUpdateListener {
            public final /* synthetic */ yz0 e;

            public a(yz0 yz0Var) {
                this.e = yz0Var;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.e.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                InfoBarContainerLayout.this.k.a();
            }
        }

        public g() {
        }

        public abstract Animator a();

        public final ValueAnimator b(yz0 yz0Var, float f) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(yz0Var.getTranslationY(), f);
            valueAnimatorOfFloat.addUpdateListener(new a(yz0Var));
            return valueAnimatorOfFloat;
        }

        public abstract int c();

        public abstract void d();

        public abstract void e();
    }

    public class h extends g {
        public yz0 c;

        public h() {
            super();
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final Animator a() {
            return b(this.c, r0.getHeight()).setDuration(250L);
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final int c() {
            return 2;
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void d() {
            this.c.removeAllViews();
            yz0 yz0Var = this.c;
            int i = InfoBarContainerLayout.m;
            InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
            infoBarContainerLayout.removeView(yz0Var);
            infoBarContainerLayout.h.remove(yz0Var);
            infoBarContainerLayout.n();
        }

        @Override // org.chromium.components.infobars.InfoBarContainerLayout.g
        public final void e() {
            this.c = InfoBarContainerLayout.this.h.get(r0.size() - 1);
        }
    }

    public InfoBarContainerLayout(Context context, l10 l10Var, org.chromium.chrome.browser.infobar.c cVar) {
        super(context, null);
        this.g = new ArrayList<>();
        this.h = new ArrayList<>();
        this.f = context.getResources().getDimensionPixelSize(R$dimen.infobar_peeking_height);
        this.k = new c(this);
        this.i = cVar;
        this.l = l10Var;
    }

    @Override // android.view.View
    public final void announceForAccessibility(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        super.announceForAccessibility(charSequence);
    }

    public final void j(yz0 yz0Var) {
        addView(yz0Var, 0, new FrameLayout.LayoutParams(-1, -2));
        this.h.add(yz0Var);
        n();
    }

    public final void l() {
        ArrayList<xz0> arrayList;
        if (this.j != null) {
            return;
        }
        ArrayList<yz0> arrayList2 = this.h;
        int size = arrayList2.size();
        do {
            size--;
            arrayList = this.g;
            if (size < 0) {
                if (!arrayList2.isEmpty()) {
                    if (arrayList2.get(0).getChildAt(0) != arrayList2.get(0).e.getView()) {
                        m(new f());
                        return;
                    }
                }
                if (!arrayList2.isEmpty()) {
                    xz0 xz0Var = arrayList2.get(0).e;
                    xz0 xz0Var2 = null;
                    for (int i = 0; i < arrayList.size() && arrayList.get(i) != xz0Var; i++) {
                        xz0Var2 = arrayList.get(i);
                    }
                    if (xz0Var2 != null) {
                        m(new d(xz0Var2));
                        return;
                    }
                }
                if (arrayList2.size() >= Math.min(arrayList.size(), 3)) {
                    this.i.f(arrayList2.size() > 0 ? arrayList2.get(0).e : null);
                    return;
                } else {
                    xz0 xz0Var3 = arrayList.get(arrayList2.size());
                    m(arrayList2.isEmpty() ? new b(xz0Var3) : new a(xz0Var3));
                    return;
                }
            }
        } while (arrayList.contains(arrayList2.get(size).e));
        if (size == 0 && arrayList2.size() >= 2) {
            m(new e());
            return;
        }
        yz0 yz0Var = arrayList2.get(size);
        if (size != arrayList2.size() - 1) {
            removeView(yz0Var);
            arrayList2.remove(yz0Var);
            n();
            j(yz0Var);
        }
        m(new h());
    }

    public final void m(g gVar) {
        this.j = gVar;
        gVar.e();
        if (isLayoutRequested()) {
            return;
        }
        g gVar2 = this.j;
        gVar2.getClass();
        org.chromium.components.infobars.a aVar = new org.chromium.components.infobars.a(gVar2);
        Animator animatorA = gVar2.a();
        gVar2.a = animatorA;
        animatorA.addListener(aVar);
        gVar2.a.start();
    }

    public final void n() {
        ArrayList<yz0> arrayList = this.h;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            yz0 yz0Var = arrayList.get(i);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) yz0Var.getLayoutParams();
            layoutParams.topMargin = ((size - 1) - i) * this.f;
            yz0Var.setLayoutParams(layoutParams);
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!super.onInterceptTouchEvent(motionEvent) && this.j == null) {
            ArrayList<yz0> arrayList = this.h;
            if (arrayList.isEmpty() || arrayList.get(0).e.m()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.k.a();
        g gVar = this.j;
        if (gVar != null) {
            if (gVar.a != null) {
                return;
            }
            org.chromium.components.infobars.a aVar = new org.chromium.components.infobars.a(gVar);
            Animator animatorA = gVar.a();
            gVar.a = animatorA;
            animatorA.addListener(aVar);
            gVar.a.start();
        }
    }

    @Override // org.chromium.ui.widget.OptimizedFrameLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        c cVar = this.k;
        cVar.getClass();
        int size = View.MeasureSpec.getSize(i);
        int i3 = cVar.b;
        boolean z = size > i3;
        boolean z2 = cVar.d;
        int i4 = cVar.c;
        if (z != z2) {
            cVar.d = z;
            FrameLayout frameLayout = cVar.a;
            if (z) {
                if (cVar.e == null) {
                    View view = new View(frameLayout.getContext());
                    cVar.e = view;
                    int i5 = R$drawable.infobar_shadow_left;
                    view.setBackgroundResource(i5);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0, 3);
                    int i6 = -i4;
                    layoutParams.leftMargin = i6;
                    cVar.e.setLayoutParams(layoutParams);
                    View view2 = new View(frameLayout.getContext());
                    cVar.f = view2;
                    view2.setBackgroundResource(i5);
                    FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(0, 0, 5);
                    layoutParams2.rightMargin = i6;
                    cVar.f.setScaleX(-1.0f);
                    cVar.f.setLayoutParams(layoutParams2);
                }
                frameLayout.setPadding(i4, 0, i4, 0);
                frameLayout.setClipToPadding(false);
                frameLayout.addView(cVar.e);
                frameLayout.addView(cVar.f);
            } else {
                frameLayout.setPadding(0, 0, 0, 0);
                frameLayout.removeView(cVar.e);
                frameLayout.removeView(cVar.f);
            }
        }
        if (z) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(size, (i4 * 2) + i3), View.MeasureSpec.getMode(i));
        }
        super.onMeasure(i, i2);
        int measuredHeight = getMeasuredHeight();
        if (cVar.d) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(cVar.c, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            cVar.e.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            cVar.f.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }
}
