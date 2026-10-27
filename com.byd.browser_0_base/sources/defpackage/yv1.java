package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import defpackage.r30;
import defpackage.rq0;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: PartialCustomTabBaseStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class yv1 extends r30 implements rq0.a {
    public boolean A;
    public int B;
    public ValueAnimator C;
    public Runnable D;
    public final t20 E;
    public final Activity e;
    public final r30.a f;
    public final rq0 g;
    public final boolean h;
    public final boolean i;
    public final int j;
    public final ow1 k;
    public int l;
    public int m;
    public ViewGroup n;
    public Runnable o;
    public Runnable p;
    public int q;
    public int r;
    public int s;
    public int t;
    public CustomTabToolbar u;
    public View v;
    public int w;
    public final fw1 x;
    public int y;
    public final boolean z;

    public yv1(Activity activity, r30.a aVar, rq0 rq0Var, boolean z, boolean z2, fw1 fw1Var) {
        this.e = activity;
        this.f = aVar;
        this.h = z;
        this.i = z2;
        ow1 ow1VarA = ow1.a(activity, new androidx.appcompat.widget.a(24, this));
        this.k = ow1VarA;
        this.l = ow1VarA.b();
        this.m = ow1VarA.c();
        this.g = rq0Var;
        rq0Var.h(this);
        this.z = SysUtils.isLowEndDevice();
        this.j = activity.getResources().getDimensionPixelSize(R$dimen.custom_tabs_handle_height);
        this.B = activity.getResources().getConfiguration().orientation;
        vj1.h.getClass();
        this.A = vj1.o(activity);
        this.x = fw1Var;
        this.E = new t20(1, rq0Var);
        this.s = -1;
        this.t = -1;
    }

    public void A(Runnable runnable) {
        runnable.run();
    }

    public final void B(int i) {
        ViewGroup.LayoutParams layoutParams = this.n.getLayoutParams();
        layoutParams.height = i;
        this.n.setLayoutParams(layoutParams);
    }

    public abstract void C(int i, int i2);

    public final void D() {
        ValueAnimator valueAnimator = new ValueAnimator();
        this.C = valueAnimator;
        valueAnimator.addListener(new a());
        this.C.setDuration(this.e.getResources().getInteger(R.integer.config_mediumAnimTime));
        this.C.setInterpolator(new AccelerateInterpolator());
    }

    public abstract boolean F();

    public final void G(int i, int i2, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, Runnable runnable) {
        this.C.removeAllUpdateListeners();
        this.C.addUpdateListener(animatorUpdateListener);
        this.D = runnable;
        this.C.setIntValues(i, i2);
        this.C.start();
    }

    public final void H(int i) {
        int i2 = R$id.drag_bar;
        Activity activity = this.e;
        View viewFindViewById = activity.findViewById(i2);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(v() ? 8 : 0);
        }
        View viewFindViewById2 = activity.findViewById(R$id.drag_handlebar);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(i);
        }
    }

    public abstract void I();

    public final void J() {
        if (v() || w() || this.z || F()) {
            this.y = 0;
        } else {
            this.y = this.e.getResources().getDimensionPixelSize(R$dimen.custom_tabs_shadow_offset);
        }
        C(this.y, r() + this.y);
        ao3.e(this.v, "PartialCustomTabBaseStrategy.updateShadowOffset");
    }

    @Override // defpackage.r30
    public void Y0() {
        int i = R$id.coordinator;
        this.n = (ViewGroup) this.e.findViewById(i);
        this.n.setElevation(r1.getResources().getDimensionPixelSize(R$dimen.custom_tabs_elevation));
        this.o.run();
    }

    public void a(sq0 sq0Var, Tab tab) {
        Activity activity = this.e;
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.height = -1;
        attributes.width = -1;
        attributes.y = 0;
        attributes.x = 0;
        activity.getWindow().setAttributes(attributes);
        J();
        y();
    }

    public void b(Tab tab) {
        new Handler().post(new xv1(this, 0));
    }

    @Override // defpackage.r30
    public void g() {
        this.g.c(this);
    }

    @Override // defpackage.r30
    public void i(Runnable runnable) {
        if (this.p != null) {
            return;
        }
        this.p = runnable;
        p(true);
        G(this.e.getWindow().getAttributes().y, this.s, new ut(1, this), new xv1(this, 1));
    }

    @Override // defpackage.r30
    public void k(View view, CustomTabToolbar customTabToolbar, int i) {
        this.v = view;
        this.u = customTabToolbar;
        this.w = customTabToolbar.getBackground().getColor();
        int i2 = R$id.custom_tabs_handle_view_stub;
        Activity activity = this.e;
        ViewStub viewStub = (ViewStub) activity.findViewById(i2);
        if (viewStub != null) {
            viewStub.inflate();
        }
        View viewFindViewById = activity.findViewById(R$id.custom_tabs_handle_view);
        viewFindViewById.setElevation(activity.getResources().getDimensionPixelSize(R$dimen.custom_tabs_elevation));
        J();
        GradientDrawable gradientDrawable = (GradientDrawable) viewFindViewById.getBackground();
        n(gradientDrawable, i);
        viewFindViewById.setBackground(gradientDrawable);
        View viewFindViewById2 = viewFindViewById.findViewById(R$id.drag_bar);
        GradientDrawable gradientDrawable2 = (GradientDrawable) viewFindViewById2.getBackground();
        n(gradientDrawable2, i);
        if (this.z) {
            int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R$dimen.custom_tabs_outline_width);
            gradientDrawable.setStroke(dimensionPixelSize, customTabToolbar.g(this.w));
            viewFindViewById2.setBackground(new InsetDrawable((Drawable) gradientDrawable2, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, 0));
        } else {
            viewFindViewById2.setBackground(gradientDrawable2);
        }
        customTabToolbar.setHandleBackground(gradientDrawable2);
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    public abstract void n(GradientDrawable gradientDrawable, int i);

    public abstract void o();

    public final void p(boolean z) {
        Window window = this.e.getWindow();
        if (z) {
            window.addFlags(512);
        } else {
            window.clearFlags(512);
        }
    }

    public abstract int r();

    public abstract int s();

    public void t() {
        Window window = this.e.getWindow();
        if (this.i) {
            window.addFlags(32);
            window.clearFlags(2);
        } else {
            window.clearFlags(32);
            window.addFlags(2);
            window.setDimAmount(0.6f);
        }
        ow1 ow1Var = this.k;
        this.q = ow1Var.e();
        this.r = ow1Var.f();
    }

    public abstract boolean v();

    public final boolean w() {
        return this.E.getAsBoolean();
    }

    public abstract boolean x();

    public final void y() {
        int i;
        WindowManager.LayoutParams attributes = this.e.getWindow().getAttributes();
        boolean zV = v();
        r30.a aVar = this.f;
        if (zV || w()) {
            aVar.d(this.l, this.m);
            this.s = this.l;
            this.t = this.m;
            return;
        }
        int i2 = this.s;
        int i3 = attributes.height;
        if ((i2 != i3 && i2 > 0) || ((i = this.t) != attributes.width && i > 0)) {
            aVar.d(i3, attributes.width);
        }
        this.s = attributes.height;
        this.t = attributes.width;
    }

    public final void z(int i) {
        vj1.h.getClass();
        boolean zO = vj1.o(this.e);
        ow1 ow1Var = this.k;
        int iB = ow1Var.b();
        int iC = ow1Var.c();
        if (zO == this.A && i == this.B && iB == this.l && iC == this.m) {
            return;
        }
        this.A = zO;
        this.B = i;
        this.l = iB;
        this.m = iC;
        if (v()) {
            p(false);
            o();
        }
        this.o.run();
    }

    public void u() {
    }

    /* JADX INFO: compiled from: PartialCustomTabBaseStrategy.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            yv1.this.D.run();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }
}
