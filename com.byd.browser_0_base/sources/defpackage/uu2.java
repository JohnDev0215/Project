package defpackage;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.regex.Pattern;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.payments.Address;
import org.chromium.components.payments.PaymentDetailsUpdateService;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uu2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ uu2(int i, Bundle bundle, kw0 kw0Var) {
        this.e = 3;
        this.f = i;
        this.g = bundle;
        this.h = kw0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaymentRequestUpdateEventListener paymentRequestUpdateEventListener;
        int i = this.f;
        int i2 = this.e;
        Object obj = this.h;
        Object obj2 = this.g;
        switch (i2) {
            case 0:
                vu2 vu2Var = (vu2) obj2;
                vu2Var.getClass();
                vu2Var.C(SystemClock.uptimeMillis(), ((Tab) obj).getId(), i, true);
                break;
            case 1:
                zf1 zf1Var = (zf1) obj2;
                String str = (String) obj;
                if (i != 1) {
                    zf1Var.getClass();
                } else {
                    zf1Var.c(str, true);
                }
                break;
            case 2:
                ((c) obj2).f((AutocompleteMatch) obj, i);
                break;
            default:
                Bundle bundle = (Bundle) obj2;
                kw0 kw0Var = (kw0) obj;
                int i3 = PaymentDetailsUpdateService.a.e;
                if (uy1.a().b(i)) {
                    uy1 uy1VarA = uy1.a();
                    uy1VarA.getClass();
                    Object obj3 = ThreadUtils.a;
                    if (bundle == null || bundle.isEmpty()) {
                        uy1.c("Payment app returned invalid shipping address in response.", kw0Var);
                    } else {
                        Address addressA = Address.a(bundle);
                        if (Address.k == null) {
                            Address.k = Pattern.compile("^[A-Z]{2}$");
                        }
                        if (!Address.k.matcher(addressA.a).matches()) {
                            uy1.c("Payment app returned invalid shipping address in response.", kw0Var);
                        } else if ((uy1VarA.a != null) || (paymentRequestUpdateEventListener = uy1VarA.b) == null || !paymentRequestUpdateEventListener.a(n3.y(addressA))) {
                            uy1.c("Invalid state.", kw0Var);
                        } else {
                            uy1VarA.a = kw0Var;
                        }
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ uu2(zf1 zf1Var, int i, String str) {
        this.e = 1;
        this.g = zf1Var;
        this.f = i;
        this.h = str;
    }

    public /* synthetic */ uu2(Object obj, int i, int i2, Object obj2) {
        this.e = i2;
        this.g = obj;
        this.h = obj2;
        this.f = i;
    }
}
