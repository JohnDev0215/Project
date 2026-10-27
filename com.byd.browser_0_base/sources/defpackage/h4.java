package defpackage;

import J.N;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.AllPasswordsBottomSheetBridge;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.b;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h4 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ b f;

    public /* synthetic */ h4(b bVar, int i) {
        this.e = i;
        this.f = bVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        PropertyModel.n nVar = c.a;
        int i = this.e;
        b bVar = this.f;
        switch (i) {
            case 0:
                if (bVar.b.k(nVar)) {
                    bVar.b.l(nVar, false);
                    long j = ((AllPasswordsBottomSheetBridge) bVar.a).a;
                    if (j != 0) {
                        N.M0obhfYM(j);
                    }
                    break;
                }
                break;
            default:
                u10 u10Var = (u10) obj;
                bVar.b.l(nVar, false);
                long j2 = ((AllPasswordsBottomSheetBridge) bVar.a).a;
                s10 s10Var = u10Var.a;
                N.MvNYgfef(j2, s10Var.a, s10Var.b, u10Var.b);
                break;
        }
    }
}
