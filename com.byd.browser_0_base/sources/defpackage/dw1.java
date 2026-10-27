package defpackage;

import android.R;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import defpackage.r30;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* JADX INFO: compiled from: PartialCustomTabFullSizeStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class dw1 extends yv1 {
    public dw1(Activity activity, r30.a aVar, rq0 rq0Var, boolean z, boolean z2, fw1 fw1Var) {
        super(activity, aVar, rq0Var, z, z2, fw1Var);
        this.o = new aw(24, this);
        D();
    }

    @Override // defpackage.yv1
    public final void C(int i, int i2) {
        ((ViewGroup.MarginLayoutParams) this.e.findViewById(R$id.custom_tabs_handle_view).getLayoutParams()).setMargins(0, 0, 0, 0);
        ((ViewGroup.MarginLayoutParams) this.v.getLayoutParams()).setMargins(0, 0, 0, 0);
    }

    @Override // defpackage.yv1
    public final boolean F() {
        return true;
    }

    @Override // defpackage.yv1
    public final void I() {
        if (w() || this.e.findViewById(R.id.content) == null) {
            return;
        }
        u();
        J();
    }

    @Override // defpackage.yv1, defpackage.r30
    public final void k(View view, CustomTabToolbar customTabToolbar, int i) {
        super.k(view, customTabToolbar, i);
        this.x.getClass();
        customTabToolbar.setHandleStrategy(null);
        H(8);
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
        return 3;
    }

    @Override // defpackage.yv1
    public final void u() {
        ow1 ow1Var = this.k;
        this.s = ow1Var.b();
        this.t = ow1Var.c();
        Activity activity = this.e;
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.height = -1;
        attributes.width = -1;
        attributes.y = 0;
        attributes.x = 0;
        attributes.gravity = 48;
        activity.getWindow().setAttributes(attributes);
        B(-1);
        H(8);
    }

    @Override // defpackage.yv1
    public final boolean v() {
        return true;
    }

    @Override // defpackage.yv1
    public final boolean x() {
        return false;
    }

    @Override // defpackage.yv1
    public final void n(GradientDrawable gradientDrawable, int i) {
    }
}
