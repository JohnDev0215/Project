package defpackage;

import android.view.View;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: MerchantTrustBottomSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lf1 implements ii {
    public final View e;
    public final View f;
    public final nw2<Integer> g;
    public final Runnable h;
    public final aq1<Boolean> i;

    public lf1(View view, View view2, yq2 yq2Var, zc0 zc0Var) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.i = aq1Var;
        this.e = view;
        this.f = view2;
        this.g = yq2Var;
        this.h = zc0Var;
        aq1Var.r(Boolean.TRUE);
    }

    @Override // defpackage.ii
    public final void a() {
        this.h.run();
    }

    @Override // defpackage.ii
    public final View d() {
        return this.f;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.e;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.merchant_viewer_preview_sheet_opened_half;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.merchant_viewer_preview_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final aq1<Boolean> l() {
        return this.i;
    }

    @Override // defpackage.ii
    public final int m() {
        return this.g.get().intValue();
    }

    @Override // defpackage.ii
    public final float n() {
        return 0.6f;
    }

    @Override // defpackage.ii
    public final boolean o() {
        this.h.run();
        return true;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.merchant_viewer_preview_sheet_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.merchant_viewer_preview_sheet_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        return 0.9f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
