package org.chromium.chrome.browser.toolbar.optional_button;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bm2;
import defpackage.bq;
import defpackage.n3;
import defpackage.oj2;
import defpackage.sk;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class OptionalButtonView extends FrameLayout implements Transition.TransitionListener {
    public BooleanSupplier A;
    public final a B;
    public final int e;
    public final int f;
    public TextView g;
    public ImageView h;
    public ListMenuButton i;
    public ImageView j;
    public Drawable k;
    public ViewGroup l;
    public String m;
    public String n;
    public int o;
    public Runnable p;
    public Handler q;
    public int r;
    public int s;
    public boolean t;
    public int u;
    public int v;
    public View.OnClickListener w;
    public View.OnLongClickListener x;
    public Callback<Integer> y;
    public Callback<Integer> z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            OptionalButtonView optionalButtonView = OptionalButtonView.this;
            if (!optionalButtonView.A.getAsBoolean()) {
                optionalButtonView.e(false);
                return;
            }
            TransitionManager.beginDelayedTransition(optionalButtonView.l, optionalButtonView.a());
            optionalButtonView.h.setColorFilter(optionalButtonView.o);
            optionalButtonView.g.setVisibility(8);
            optionalButtonView.d(optionalButtonView.e);
            optionalButtonView.r = 6;
        }
    }

    public OptionalButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = 1;
        this.B = new a();
        this.r = 0;
        float f = getResources().getDisplayMetrics().density;
        this.e = (int) (52.0f * f);
        this.f = (int) (f * 8.0f);
    }

    public final TransitionSet a() {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(0);
        ChangeBounds changeBounds = new ChangeBounds();
        Fade fade = new Fade();
        transitionSet.addTransition(changeBounds).addTransition(fade).addTransition(new bm2());
        transitionSet.setDuration(300L);
        transitionSet.addListener((Transition.TransitionListener) this);
        return transitionSet;
    }

    public final TransitionSet b() {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(0);
        Fade fade = new Fade();
        ChangeBounds changeBounds = new ChangeBounds();
        bm2 bm2Var = new bm2();
        Slide slide = new Slide(8388613);
        slide.addTarget(this.i);
        slide.addTarget(this.h);
        transitionSet.addTransition(slide).addTransition(bm2Var).addTransition(fade).addTransition(changeBounds);
        transitionSet.setDuration(225L);
        transitionSet.addListener((Transition.TransitionListener) this);
        return transitionSet;
    }

    public final int c() {
        switch (this.r) {
            case 0:
            case 1:
            case 2:
                return 5;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            case 7:
                return 0;
            default:
                throw new IllegalStateException("Unexpected value: " + this.r);
        }
    }

    public final void d(int i) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i;
        setLayoutParams(layoutParams);
    }

    public final void e(boolean z) {
        TransitionSet transitionSetB = b();
        if (!z) {
            transitionSetB.setDuration(0L);
        }
        setVisibility(0);
        d(0);
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        this.j.setVisibility(8);
        this.g.setVisibility(8);
        this.i.setImageDrawable(this.k);
        TransitionManager.beginDelayedTransition(this.l, transitionSetB);
        d(this.e);
        this.i.setVisibility(0);
        this.h.setColorFilter(this.o);
        this.h.setVisibility(this.v != 1 ? 8 : 0);
        this.r = 3;
    }

    public final void f(boolean z) {
        int i = this.r;
        if (i == 0 && this.n == null) {
            e(z);
            return;
        }
        if (z && this.n != null) {
            if (i == 1 || i == 0) {
                if (getVisibility() == 8) {
                    setVisibility(0);
                    d(0);
                }
                this.g.setVisibility(8);
                this.g.setText(this.n);
                this.j.setImageDrawable(this.i.getDrawable());
                this.j.setVisibility(0);
                this.i.setImageDrawable(this.k);
                this.i.setVisibility(8);
                String strX = n3.X(this.s);
                sk skVar = bq.a;
                if (N.M6bsIDpc(strX, "action_chip_with_different_color", false)) {
                    this.h.setColorFilter(oj2.p(this, R$attr.colorSecondaryContainer));
                } else {
                    this.h.setColorFilter(this.o);
                }
                TransitionManager.beginDelayedTransition(this.l, a());
                this.i.setVisibility(0);
                this.j.setVisibility(8);
                this.g.setVisibility(0);
                this.h.setVisibility(0);
                d(Math.min((int) (this.e + this.g.getPaint().measureText(this.n) + this.f), getResources().getDimensionPixelSize(R$dimen.toolbar_phone_optional_button_action_chip_max_width)));
                this.r = 5;
                return;
            }
            return;
        }
        if (!z || this.n != null) {
            e(false);
            return;
        }
        if (i != 1) {
            return;
        }
        boolean z2 = this.u == 1 && this.v == 0;
        if (this.v == 1) {
            this.h.setColorFilter(this.o);
        }
        ImageView imageView = this.j;
        ListMenuButton listMenuButton = this.i;
        Drawable drawable = this.k;
        Drawable drawable2 = listMenuButton.getDrawable();
        if (z2) {
            imageView.setImageDrawable(drawable2);
            imageView.setVisibility(0);
            listMenuButton.setImageDrawable(drawable);
            listMenuButton.setVisibility(8);
        } else {
            imageView.setImageDrawable(drawable);
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(0);
        Fade fade = new Fade();
        bm2 bm2Var = new bm2();
        bm2Var.addTarget(this.i);
        Slide slide = new Slide(48);
        slide.addTarget(this.j);
        transitionSet.addTransition(slide).addTransition(bm2Var).addTransition(fade);
        transitionSet.setDuration(300L);
        transitionSet.addListener((Transition.TransitionListener) this);
        TransitionManager.beginDelayedTransition(this.l, transitionSet);
        if (z2) {
            imageView.setVisibility(8);
            listMenuButton.setVisibility(0);
        } else {
            imageView.setVisibility(0);
            listMenuButton.setVisibility(8);
        }
        this.h.setVisibility(this.v == 1 ? 0 : 8);
        this.r = 7;
    }

    @Override // android.view.View
    public final Handler getHandler() {
        if (this.q == null) {
            this.q = new Handler(ThreadUtils.d());
        }
        return this.q;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.h = (ImageView) findViewById(R$id.swappable_icon_secondary_background);
        this.i = (ListMenuButton) findViewById(R$id.optional_toolbar_button);
        this.j = (ImageView) findViewById(R$id.swappable_icon_animation_image);
        this.g = (TextView) findViewById(R$id.action_chip_label);
        this.h.setImageDrawable(AppCompatResources.a(getContext(), R$drawable.modern_toolbar_text_box_background_with_primary_color));
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        ListMenuButton listMenuButton = this.i;
        if (listMenuButton == null || this.j == null) {
            return;
        }
        if (i == 0) {
            listMenuButton.setScaleType(ImageView.ScaleType.FIT_START);
            this.j.setScaleType(ImageView.ScaleType.FIT_START);
        } else {
            listMenuButton.setScaleType(ImageView.ScaleType.FIT_END);
            this.j.setScaleType(ImageView.ScaleType.FIT_END);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    @Override // android.transition.Transition.TransitionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onTransitionEnd(android.transition.Transition r6) {
        /*
            r5 = this;
            org.chromium.base.Callback<java.lang.Integer> r6 = r5.z
            r0 = 5
            if (r6 == 0) goto L18
            int r6 = r5.c()
            if (r6 == r0) goto L18
            org.chromium.base.Callback<java.lang.Integer> r6 = r5.z
            int r1 = r5.c()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.onResult(r1)
        L18:
            int r6 = r5.r
            r1 = 3
            r2 = 2
            r3 = 0
            r4 = 1
            if (r6 == r1) goto L30
            r1 = 4
            if (r6 == r1) goto L2e
            if (r6 == r0) goto L2c
            r0 = 6
            if (r6 == r0) goto L30
            r0 = 7
            if (r6 == r0) goto L30
            goto L31
        L2c:
            r6 = r2
            goto L31
        L2e:
            r6 = r3
            goto L31
        L30:
            r6 = r4
        L31:
            r5.r = r6
            int r6 = r5.v
            r5.u = r6
            android.widget.ImageView r6 = r5.j
            r0 = 8
            r6.setVisibility(r0)
            int r6 = r5.r
            if (r6 != 0) goto L46
            r5.setVisibility(r0)
            goto L71
        L46:
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            r6.setVisibility(r3)
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            android.graphics.drawable.Drawable r0 = r5.k
            r6.setImageDrawable(r0)
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            android.view.View$OnClickListener r0 = r5.w
            r6.setOnClickListener(r0)
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            android.view.View$OnLongClickListener r0 = r5.x
            if (r0 == 0) goto L60
            r3 = r4
        L60:
            r6.setLongClickable(r3)
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            android.view.View$OnLongClickListener r0 = r5.x
            r6.setOnLongClickListener(r0)
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            java.lang.String r0 = r5.m
            r6.setContentDescription(r0)
        L71:
            int r6 = r5.r
            if (r6 != r2) goto L8f
            android.os.Handler r6 = r5.getHandler()
            int r0 = r5.s
            java.lang.String r0 = defpackage.n3.X(r0)
            sk r1 = defpackage.bq.a
            java.lang.String r1 = "action_chip_time_ms"
            r2 = 3000(0xbb8, float:4.204E-42)
            int r0 = J.N.M37SqSAy(r0, r1, r2)
            long r0 = (long) r0
            org.chromium.chrome.browser.toolbar.optional_button.OptionalButtonView$a r5 = r5.B
            r6.postDelayed(r5, r0)
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.optional_button.OptionalButtonView.onTransitionEnd(android.transition.Transition):void");
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
        if (this.r != 6) {
            this.i.setOnClickListener(null);
            this.i.setOnLongClickListener(null);
            this.i.setContentDescription(null);
        }
        Callback<Integer> callback = this.y;
        if (callback != null) {
            callback.onResult(Integer.valueOf(c()));
        }
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }
}
