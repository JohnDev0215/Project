package defpackage;

import android.view.View;
import android.widget.LinearLayout;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: AccountSelectionBottomSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l1 implements ii {
    public final View e;
    public final nw2<Integer> f;
    public Runnable g;
    public final aq1<Boolean> h = new aq1<>();

    public l1(LinearLayout linearLayout, yq2 yq2Var) {
        this.e = linearLayout;
        this.f = yq2Var;
    }

    @Override // defpackage.ii
    public final void a() {
        o();
    }

    @Override // defpackage.ii
    public final View d() {
        return this.e;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final boolean f() {
        return true;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.account_selection_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.account_selection_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final aq1<Boolean> l() {
        return this.h;
    }

    @Override // defpackage.ii
    public final int m() {
        return this.f.get().intValue();
    }

    @Override // defpackage.ii
    public final boolean o() {
        Runnable runnable = this.g;
        if (runnable == null) {
            return false;
        }
        runnable.run();
        return true;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.account_selection_content_description;
    }

    @Override // defpackage.ii
    public final boolean q() {
        return false;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.account_selection_sheet_full_height;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean t() {
        return false;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
