package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import defpackage.ew1;
import defpackage.r30;
import defpackage.rt3;
import java.util.Objects;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: PartialCustomTabBottomSheetStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class aw1 extends yv1 implements bx, ValueAnimator.AnimatorUpdateListener, ew1.a {
    public final a F;
    public final int G;
    public final boolean H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final b f25J;
    public int K;
    public ImageView L;
    public CircularProgressDrawable M;
    public Runnable N;
    public boolean O;
    public boolean P;
    public int Q;
    public float R;
    public boolean S;

    /* JADX INFO: compiled from: PartialCustomTabBottomSheetStrategy.java */
    public static class b {
        public final ValueAnimator a;
        public int b;
        public boolean c;

        public b(ValueAnimator.AnimatorUpdateListener animatorUpdateListener, int i, zv1 zv1Var) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.a = valueAnimator;
            valueAnimator.addListener(new bw1(zv1Var));
            valueAnimator.addUpdateListener(animatorUpdateListener);
            valueAnimator.setInterpolator(new AccelerateInterpolator());
            valueAnimator.setDuration(i);
        }
    }

    public aw1(Activity activity, int i, boolean z, r30.a aVar, q2 q2Var, rq0 rq0Var, boolean z2, boolean z3, boolean z4, fw1 fw1Var) {
        super(activity, aVar, rq0Var, z2, z3, fw1Var);
        this.K = 1;
        this.f25J = new b(this, activity.getResources().getInteger(R.integer.config_mediumAnimTime), new zv1(this, 0));
        q2Var.f(this);
        if (z4) {
            this.K = 0;
        }
        this.F = new a();
        ow1 ow1Var = this.k;
        Objects.requireNonNull(ow1Var);
        this.o = new wq0(24, ow1Var);
        this.G = i;
        this.H = z;
    }

    @Override // defpackage.yv1
    public final void A(Runnable runnable) {
        if (v() || this.K != 1) {
            runnable.run();
        } else {
            this.N = runnable;
            K(0, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0011  */
    @Override // defpackage.yv1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C(int r10, int r11) {
        /*
            r9 = this;
            int r0 = org.chromium.chrome.R$id.custom_tabs_handle_view
            android.app.Activity r1 = r9.e
            android.view.View r0 = r1.findViewById(r0)
            sk r2 = defpackage.bq.o
            boolean r3 = r2.a()
            r4 = 0
            if (r3 != 0) goto L13
        L11:
            r8 = r4
            goto L34
        L13:
            ow1 r3 = r9.k
            int r3 = r3.c()
            android.content.res.Resources r5 = r1.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            float r5 = r5.density
            int r5 = (int) r5
            int r6 = r9.B
            r7 = 2
            r8 = 1
            if (r6 != r7) goto L2c
            r6 = r8
            goto L2d
        L2c:
            r6 = r4
        L2d:
            if (r6 == 0) goto L11
            int r3 = r3 / r5
            r5 = 900(0x384, float:1.261E-42)
            if (r3 <= r5) goto L11
        L34:
            boolean r2 = r2.a()
            if (r2 == 0) goto L64
            android.content.res.Resources r2 = r1.getResources()
            int r3 = org.chromium.chrome.R$dimen.default_elevation_2
            int r2 = r2.getDimensionPixelSize(r3)
            float r2 = (float) r2
            android.content.res.Resources r3 = r1.getResources()
            int r5 = org.chromium.chrome.R$dimen.custom_tabs_elevation
            int r3 = r3.getDimensionPixelSize(r5)
            float r3 = (float) r3
            if (r8 == 0) goto L53
            goto L54
        L53:
            r2 = r3
        L54:
            int r3 = org.chromium.chrome.R$id.coordinator
            android.view.View r1 = r1.findViewById(r3)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r1.setElevation(r2)
            if (r0 == 0) goto L64
            r0.setElevation(r2)
        L64:
            if (r8 == 0) goto L68
            r1 = r10
            goto L69
        L68:
            r1 = r4
        L69:
            if (r0 == 0) goto L74
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            r0.setMargins(r1, r10, r1, r4)
        L74:
            android.view.View r9 = r9.v
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r9 = (android.view.ViewGroup.MarginLayoutParams) r9
            r9.setMargins(r1, r11, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aw1.C(int, int):void");
    }

    @Override // defpackage.yv1
    public final boolean F() {
        return this.K == 0 || (bq.o.a() && this.e.getWindow().getAttributes().y <= this.r);
    }

    @Override // defpackage.yv1
    public final void I() {
        if (w()) {
            return;
        }
        Activity activity = this.e;
        if (activity.findViewById(R.id.content) == null) {
            return;
        }
        t();
        int iC = this.k.c();
        if (bq.o.a()) {
            int i = (int) activity.getResources().getDisplayMetrics().density;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            if ((this.B == 2) && iC / ((int) activity.getResources().getDisplayMetrics().density) > 900) {
                iC = i * 900;
            }
            attributes.width = iC;
            activity.getWindow().setAttributes(attributes);
        }
        J();
        y();
        if (this.H) {
            return;
        }
        this.P = false;
    }

    public final void K(int i, boolean z) {
        int iQ;
        Window window = this.e.getWindow();
        window.addFlags(512);
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (i == 0) {
            int i2 = attributes.height;
            int i3 = this.l - this.q;
            if (i2 < i3) {
                attributes.height = i3;
                window.setAttributes(attributes);
            }
            iQ = this.r + this.I;
        } else if (i == 1) {
            int i4 = this.l;
            iQ = i4 - a8.q(this.G, i4 - this.r, (int) (i4 * 0.5f));
        } else if (i != 3) {
            iQ = 0;
        } else {
            iQ = this.l - this.q;
            if (v()) {
                attributes.y = this.r;
                window.setAttributes(attributes);
            }
        }
        this.K = 2;
        if (z) {
            this.Q = window.getAttributes().y;
        }
        int i5 = attributes.y;
        b bVar = this.f25J;
        bVar.b = i;
        bVar.c = z;
        ValueAnimator valueAnimator = bVar.a;
        valueAnimator.setIntValues(i5, iQ);
        valueAnimator.start();
    }

    public final void L(ViewGroup.LayoutParams layoutParams) {
        layoutParams.height = (this.l - this.e.getWindow().getAttributes().y) - this.u.getHeight();
        this.L.setLayoutParams(layoutParams);
    }

    public final void M(boolean z) {
        Activity activity = this.e;
        rt3.e eVar = new rt3(activity.getWindow(), activity.getWindow().getDecorView()).a;
        if (z) {
            eVar.f(2);
        } else {
            eVar.a(2);
        }
    }

    public final void N() {
        if (O()) {
            this.L.animate().alpha(0.0f).setDuration(400L).setListener(this.F);
        }
    }

    public final boolean O() {
        ImageView imageView = this.L;
        return imageView != null && imageView.getVisibility() == 0;
    }

    public final boolean P(int i) {
        int iMin = this.e.getWindow().getAttributes().y + i;
        int i2 = this.r;
        int i3 = this.I + i2;
        int i4 = this.l;
        int iQ = i4 - a8.q(this.G, i4 - i2, (int) (i4 * 0.5f));
        int i5 = this.l - this.q;
        if (iMin < iQ) {
            K((!(Math.abs(i3 - iMin) < Math.abs(iMin - iQ)) || this.H) ? 1 : 0, false);
            return true;
        }
        if (this.K == 0) {
            iMin = Math.min(iQ, iMin);
        }
        if (Math.abs(iQ - iMin) >= Math.abs(iMin - i5)) {
            return false;
        }
        K(1, false);
        return true;
    }

    public final void Q(int i) {
        Activity activity = this.e;
        Window window = activity.getWindow();
        window.addFlags(512);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = this.l;
        window.setAttributes(attributes);
        M(false);
        new Handler().postDelayed(new wq0(25, this), 150L);
        this.Q = activity.getWindow().getAttributes().y;
        this.R = r0 - i;
        this.O = false;
    }

    public final void R(int i) {
        Activity activity = this.e;
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (v() || w()) {
            attributes.height = -1;
            attributes.y = 0;
        } else {
            int i2 = this.q;
            int i3 = i - i2;
            attributes.height = i3;
            attributes.y = (this.l - i3) - i2;
        }
        if (bq.o.a()) {
            attributes.x = 0;
        }
        attributes.gravity = 48;
        activity.getWindow().setAttributes(attributes);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void S(int r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aw1.S(int, boolean):void");
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void Y0() {
        super.Y0();
        if (w()) {
            C(0, 0);
        }
    }

    @Override // defpackage.yv1, rq0.a
    public final void a(sq0 sq0Var, Tab tab) {
        R(0);
        C(0, 0);
        y();
    }

    @Override // defpackage.yv1, rq0.a
    public final void b(Tab tab) {
        new Handler().post(new l10(6, this));
    }

    @Override // defpackage.r30, defpackage.ym0
    public final void c() {
        if (this.h) {
            return;
        }
        Activity activity = this.e;
        ((GradientDrawable) activity.findViewById(R$id.drag_bar).getBackground()).setColor(activity.getResources().getColor(R$color.find_in_page_background_color));
        if (!v() && this.K == 1) {
            K(0, true);
            this.P = true;
        }
    }

    @Override // defpackage.r30, defpackage.ym0
    public final void d() {
        if (this.h) {
            return;
        }
        ((GradientDrawable) this.e.findViewById(R$id.drag_bar).getBackground()).setColor(this.w);
        if (!v() && this.P) {
            K(1, true);
            this.P = false;
        }
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void i(Runnable runnable) {
        if (this.p != null) {
            return;
        }
        this.p = runnable;
        this.k.g(null);
        if (this.K == 2) {
            return;
        }
        K(3, true);
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void k(View view, CustomTabToolbar customTabToolbar, int i) {
        super.k(view, customTabToolbar, i);
        s20 s20Var = new s20(1, this);
        yo yoVar = new yo(3, this);
        this.x.getClass();
        customTabToolbar.setHandleStrategy(new ew1(this.e, s20Var, yoVar, this));
        H(this.H ? 8 : 0);
    }

    @Override // defpackage.r30
    public final void m(float f) {
        Activity activity = this.e;
        float f2 = (r1 >>> 24) / 255.0f;
        int color = activity.getResources().getColor(R$color.default_scrim_color) & (-16777216);
        float f3 = f2 * f;
        ((GradientDrawable) activity.findViewById(R$id.drag_bar).getBackground()).setColor(z7.o(this.w, color, f3, false));
        ImageView imageView = (ImageView) activity.findViewById(R$id.drag_handlebar);
        int color2 = activity.getColor(R$color.drag_handlebar_color_baseline);
        if (f > 0.0f) {
            imageView.setColorFilter(z7.o(color2, color, f3, false));
        } else {
            imageView.clearColorFilter();
        }
    }

    @Override // defpackage.yv1
    public final void n(GradientDrawable gradientDrawable, int i) {
        gradientDrawable.mutate();
        float f = i;
        gradientDrawable.setCornerRadii(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f});
    }

    @Override // defpackage.yv1
    public final void o() {
        if (this.k.g(null)) {
            this.K = 1;
        }
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        S(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        if (bq.o.a()) {
            return;
        }
        z(configuration.orientation);
    }

    @Override // defpackage.yv1
    public final int r() {
        if (v()) {
            return 0;
        }
        return this.j;
    }

    @Override // defpackage.yv1
    public final int s() {
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0041  */
    @Override // defpackage.yv1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t() {
        /*
            r5 = this;
            super.t()
            sk r0 = defpackage.bq.m
            boolean r0 = r0.a()
            r1 = 0
            if (r0 == 0) goto L18
            int r0 = r5.l
            int r2 = r5.r
            int r0 = r0 - r2
            float r0 = (float) r0
            r2 = 1036831949(0x3dcccccd, float:0.1)
            float r0 = r0 * r2
            int r0 = (int) r0
            goto L19
        L18:
            r0 = r1
        L19:
            r5.I = r0
            int r0 = r5.r
            boolean r2 = r5.v()
            r3 = 1
            if (r2 != 0) goto L41
            int r2 = r5.K
            if (r2 != r3) goto L3a
            int r0 = r5.l
            int r2 = r5.r
            int r2 = r0 - r2
            float r0 = (float) r0
            r4 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 * r4
            int r0 = (int) r0
            int r4 = r5.G
            int r0 = defpackage.a8.q(r4, r2, r0)
            goto L42
        L3a:
            if (r2 != 0) goto L41
            int r2 = r5.l
            int r0 = r2 - r0
            goto L42
        L41:
            r0 = r1
        L42:
            android.app.Activity r2 = r5.e
            android.view.Window r2 = r2.getWindow()
            android.view.WindowManager$LayoutParams r2 = r2.getAttributes()
            int r2 = r2.height
            if (r2 != r0) goto L51
            return
        L51:
            r5.R(r0)
            boolean r0 = r5.S
            if (r0 != 0) goto L6e
            int r0 = r5.l
            r5.B(r0)
            r5.S = r3
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            androidx.appcompat.widget.a r2 = new androidx.appcompat.widget.a
            r3 = 25
            r2.<init>(r3, r5)
            r0.post(r2)
        L6e:
            boolean r0 = r5.H
            if (r0 == 0) goto L74
            r1 = 8
        L74:
            r5.H(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aw1.t():void");
    }

    @Override // defpackage.yv1
    public final boolean v() {
        boolean zA = bq.o.a();
        Activity activity = this.e;
        if (zA) {
            vj1.h.getClass();
            return vj1.o(activity);
        }
        if (this.B == 2) {
            return true;
        }
        vj1.h.getClass();
        return vj1.o(activity);
    }

    @Override // defpackage.yv1
    public final boolean x() {
        return this.K == 0;
    }

    /* JADX INFO: compiled from: PartialCustomTabBottomSheetStrategy.java */
    public class a implements Animator.AnimatorListener {
        public a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            aw1 aw1Var = aw1.this;
            aw1Var.M.stop();
            aw1Var.L.setVisibility(8);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }
}
