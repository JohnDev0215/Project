package defpackage;

import J.N;
import android.net.Uri;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.infobar.AutofillVirtualCardEnrollmentInfoBar;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.payments.CSPCheckerBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yb implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ yb(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                zb zbVar = (zb) obj2;
                Boolean bool = zbVar.f[i2].getHandleBackPressChangedSupplier().get();
                if (bool != null) {
                    boolean zBooleanValue = bool.booleanValue();
                    boolean[] zArr = zbVar.h;
                    if (zBooleanValue != zArr[i2]) {
                        if (bool.booleanValue()) {
                            zbVar.j++;
                        } else {
                            zbVar.j--;
                        }
                        zArr[i2] = bool.booleanValue();
                        zbVar.e.b(zbVar.j != 0);
                        break;
                    }
                }
                break;
            case 1:
                aq aqVar = (aq) obj2;
                aqVar.isIncognito();
                ContextMenuParams contextMenuParams = aqVar.f;
                String str = contextMenuParams.e;
                contextMenuParams.g.getClass();
                contextMenuParams.b.getClass();
                Uri.EMPTY.equals((Uri) obj);
                s61 s61Var = s61.b;
                aqVar.h();
                s61Var.a.getClass();
                break;
            case 2:
                AutofillVirtualCardEnrollmentInfoBar autofillVirtualCardEnrollmentInfoBar = (AutofillVirtualCardEnrollmentInfoBar) obj2;
                N.ML4fygsm(autofillVirtualCardEnrollmentInfoBar.r, autofillVirtualCardEnrollmentInfoBar, (String) obj, i2);
                break;
            default:
                Boolean bool2 = (Boolean) obj;
                long j = ((CSPCheckerBridge) obj2).b;
                if (j != 0) {
                    N.Mjoo1GDV(j, i2, bool2.booleanValue());
                }
                break;
        }
    }
}
