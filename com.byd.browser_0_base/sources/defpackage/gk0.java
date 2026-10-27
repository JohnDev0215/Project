package defpackage;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.ui.fast_checkout.R$dimen;
import org.chromium.chrome.browser.ui.fast_checkout.R$id;
import org.chromium.chrome.browser.ui.fast_checkout.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FastCheckoutSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gk0 implements ii {
    public final hk0 e;
    public final View f;

    public gk0(ek0 ek0Var, LinearLayout linearLayout) {
        this.e = ek0Var;
        this.f = linearLayout;
    }

    public final boolean b() {
        hk0 hk0Var = this.e;
        PropertyModel propertyModel = ((ek0) hk0Var).a;
        PropertyModel.p pVar = fk0.b;
        return (((ek0) hk0Var).a.h(pVar) == 0) || ((propertyModel.h(pVar) == 1) && (((float) (((ya1) ((ek0) hk0Var).a.j(fk0.d)).size() + (-1))) > 2.5f ? 1 : (((float) (((ya1) ((ek0) hk0Var).a.j(fk0.d)).size() + (-1))) == 2.5f ? 0 : -1)) < 0) || ((((ek0) hk0Var).a.h(pVar) == 2) && (((float) (((ya1) ((ek0) hk0Var).a.j(fk0.f)).size() + (-1))) > 3.5f ? 1 : (((float) (((ya1) ((ek0) hk0Var).a.j(fk0.f)).size() + (-1))) == 3.5f ? 0 : -1)) < 0);
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
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.fast_checkout_content_description;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.fast_checkout_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        hk0 hk0Var = this.e;
        PropertyModel propertyModel = ((ek0) hk0Var).a;
        PropertyModel.p pVar = fk0.b;
        if (!(propertyModel.h(pVar) == 1)) {
            if (!(((ek0) hk0Var).a.h(pVar) == 2)) {
                return 0;
            }
        }
        return ((RecyclerView) this.f.findViewById(R$id.fast_checkout_detail_screen_recycler_view)).computeVerticalScrollOffset();
    }

    @Override // defpackage.ii
    public final float n() {
        if (b()) {
            return -2.0f;
        }
        int dimensionPixelSize = this.f.getContext().getResources().getDimensionPixelSize(R$dimen.fast_checkout_detail_sheet_header_height);
        hk0 hk0Var = this.e;
        int iRound = ((ek0) hk0Var).a.h(fk0.b) == 1 ? Math.round(r1.getContext().getResources().getDimensionPixelSize(R$dimen.fast_checkout_detail_sheet_height_single_address) * 2.5f) : Math.round(r1.getContext().getResources().getDimensionPixelSize(R$dimen.fast_checkout_detail_sheet_height_single_credit_card) * 3.5f);
        ek0 ek0Var = (ek0) hk0Var;
        return Math.min(iRound + dimensionPixelSize, ek0Var.c.b()) / ek0Var.c.b();
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.fast_checkout_content_description;
    }

    @Override // defpackage.ii
    public final boolean q() {
        return false;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.fast_checkout_content_description;
    }

    @Override // defpackage.ii
    public final float s() {
        if (b()) {
            return -1.0f;
        }
        View view = this.f;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        view.measure(View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(), Integer.MIN_VALUE));
        float measuredHeight = view.getMeasuredHeight();
        ek0 ek0Var = (ek0) this.e;
        return Math.min(measuredHeight, ek0Var.c.b()) / ek0Var.c.b();
    }

    @Override // defpackage.ii
    public final boolean u() {
        return false;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
