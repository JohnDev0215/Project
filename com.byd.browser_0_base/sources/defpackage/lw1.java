package defpackage;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import defpackage.r30;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: PartialCustomTabSideSheetStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class lw1 extends yv1 {
    public static final a L = new a();
    public final int F;
    public final boolean G;
    public boolean H;
    public final int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final boolean f45J;
    public final boolean K;

    /* JADX INFO: compiled from: PartialCustomTabSideSheetStrategy.java */
    public static class a extends ValueAnimator {
        public int e;

        @Override // android.animation.ValueAnimator
        public final Object getAnimatedValue() {
            return Integer.valueOf(this.e);
        }
    }

    public lw1(Activity activity, int i, r30.a aVar, rq0 rq0Var, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, fw1 fw1Var, int i4) {
        super(activity, aVar, rq0Var, z, z2, fw1Var);
        this.F = i;
        this.G = z3;
        this.o = new iw1(this, 1);
        this.H = z4;
        this.I = i4;
        this.K = !(LocalizationUtils.isLayoutRtl() ^ (i2 == 1));
        this.f45J = i3 == 1;
        D();
    }

    @Override // defpackage.yv1
    public final void A(Runnable runnable) {
        runnable.run();
    }

    @Override // defpackage.yv1
    public final void C(int i, int i2) {
        boolean z = this.K;
        int i3 = z ? i : 0;
        int i4 = !z ? i : 0;
        int iK = K(this.F);
        int iC = this.k.c();
        float f = iK >= (iC * 3) / 4 ? 5.0f : iK >= iC / 2 ? 7.0f : iK > iC / 3 ? 9.0f : 11.0f;
        int i5 = R$id.coordinator;
        Activity activity = this.e;
        ((ViewGroup) activity.findViewById(i5)).setElevation(f);
        View viewFindViewById = activity.findViewById(R$id.custom_tabs_handle_view);
        if (viewFindViewById != null) {
            viewFindViewById.setElevation(f);
        }
        if (viewFindViewById != null) {
            ((ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams()).setMargins(i3, 0, i4, 0);
        }
        ((ViewGroup.MarginLayoutParams) this.v.getLayoutParams()).setMargins(i3, Math.max(i2 - i, 0), i4, 0);
    }

    @Override // defpackage.yv1
    public final boolean F() {
        int i;
        return this.H || K(this.F) == this.k.c() || (i = this.I) == 1 || i == 3;
    }

    @Override // defpackage.yv1
    public final void I() {
        if (w() || this.e.findViewById(R.id.content) == null) {
            return;
        }
        u();
        J();
        y();
    }

    public final int K(int i) {
        return a8.q(i, this.k.c(), (int) (r2.c() * 0.33f));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006c A[PHI: r0
  0x006c: PHI (r0v7 int) = (r0v4 int), (r0v12 int) binds: [B:15:0x006a, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean L(boolean r8) {
        /*
            r7 = this;
            boolean r0 = r7.H
            r1 = 1
            r0 = r0 ^ r1
            r7.H = r0
            r2 = 0
            if (r0 == 0) goto Ld
            r7.C(r2, r2)
            goto L10
        Ld:
            r7.J()
        L10:
            ow1 r0 = r7.k
            int r3 = r0.c()
            int r4 = r7.F
            int r3 = r3 - r4
            android.app.Activity r4 = r7.e
            android.view.Window r5 = r4.getWindow()
            r7.p(r1)
            boolean r1 = r7.K
            if (r1 == 0) goto L46
            int r0 = r0.c()
            android.view.Window r1 = r4.getWindow()
            android.view.WindowManager$LayoutParams r1 = r1.getAttributes()
            r1.width = r0
            android.view.Window r0 = r4.getWindow()
            r0.setAttributes(r1)
            android.view.WindowManager$LayoutParams r0 = r5.getAttributes()
            int r0 = r0.x
            boolean r1 = r7.H
            if (r1 == 0) goto L6f
            goto L6c
        L46:
            boolean r1 = r7.H
            if (r1 == 0) goto L62
            android.view.Window r1 = r4.getWindow()
            android.view.WindowManager$LayoutParams r1 = r1.getAttributes()
            int r6 = -r3
            r1.x = r6
            int r0 = r0.c()
            r1.width = r0
            android.view.Window r0 = r4.getWindow()
            r0.setAttributes(r1)
        L62:
            android.view.WindowManager$LayoutParams r0 = r5.getAttributes()
            int r0 = r0.x
            boolean r1 = r7.H
            if (r1 == 0) goto L6e
        L6c:
            r3 = r2
            goto L6f
        L6e:
            int r3 = -r3
        L6f:
            jw1 r1 = new jw1
            r1.<init>(r2, r7)
            kw1 r4 = new kw1
            r4.<init>(r7, r2, r8)
            if (r8 == 0) goto L7f
            r7.G(r0, r3, r1, r4)
            goto L89
        L7f:
            lw1$a r8 = defpackage.lw1.L
            r8.e = r3
            r1.onAnimationUpdate(r8)
            r4.run()
        L89:
            boolean r7 = r7.H
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lw1.L(boolean):boolean");
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void g() {
        super.g();
        if (this.G) {
            CustomTabToolbar customTabToolbar = this.u;
            customTabToolbar.getClass();
            if (bq.o.a()) {
                ImageButton imageButton = (ImageButton) customTabToolbar.findViewById(R$id.custom_tabs_sidepanel_maximize);
                imageButton.setOnClickListener(null);
                imageButton.setVisibility(8);
            }
        }
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void i(Runnable runnable) {
        int iC;
        ValueAnimator.AnimatorUpdateListener utVar;
        int i;
        if (this.p != null) {
            return;
        }
        this.p = runnable;
        int i2 = 1;
        p(true);
        Window window = this.e.getWindow();
        boolean z = this.f45J;
        ow1 ow1Var = this.k;
        if (z) {
            i = window.getAttributes().y;
            iC = ow1Var.b();
            utVar = new le0(i2, this);
        } else {
            int i3 = window.getAttributes().x;
            iC = this.K ? ow1Var.c() : -window.getAttributes().width;
            utVar = new ut(2, this);
            i = i3;
        }
        G(i, iC, utVar, new wq0(27, this));
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void k(View view, CustomTabToolbar customTabToolbar, int i) {
        super.k(view, customTabToolbar, i);
        this.x.getClass();
        if (this.G) {
            customTabToolbar.P(this.H, new pq2(9, this));
        }
        customTabToolbar.setHandleStrategy(null);
        H(8);
    }

    @Override // defpackage.yv1
    public final void n(GradientDrawable gradientDrawable, int i) {
        gradientDrawable.mutate();
        float f = i;
        gradientDrawable.setCornerRadii(new float[]{f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
    }

    @Override // defpackage.yv1
    public final void o() {
        this.k.g(null);
    }

    @Override // defpackage.yv1
    public final int r() {
        return 0;
    }

    @Override // defpackage.yv1
    public final int s() {
        return 2;
    }

    @Override // defpackage.yv1
    public final void u() {
        t();
        Activity activity = this.e;
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.height = (this.l - this.r) - this.q;
        attributes.width = K(this.F);
        attributes.y = this.r;
        attributes.x = this.K ? this.k.c() - attributes.width : 0;
        attributes.gravity = 8388659;
        activity.getWindow().setAttributes(attributes);
        B(-1);
        H(8);
        if (this.H) {
            this.H = false;
            L(false);
        }
    }

    @Override // defpackage.yv1
    public final boolean v() {
        return false;
    }

    @Override // defpackage.yv1
    public final boolean x() {
        return this.H;
    }
}
