package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.payments.PaymentDetailsUpdateService;
import org.chromium.components.payments.PaymentRequestUpdateEventListener;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gu implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ gu(int i, Bundle bundle, kw0 kw0Var) {
        this.e = 2;
        this.f = i;
        this.g = bundle;
        this.h = kw0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaymentRequestUpdateEventListener paymentRequestUpdateEventListener;
        int i = this.e;
        int i2 = this.f;
        Object obj = this.h;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                super/*org.chromium.chrome.browser.omnibox.suggestions.base.c*/.f((AutocompleteMatch) obj, i2);
                break;
            case 1:
                Callback callback = (Callback) obj;
                HashMap map = ((ai1) obj2).a;
                callback.onResult(map.get(ai1.h(i2)) == null ? null : (ByteBuffer) map.get(ai1.h(i2)));
                break;
            default:
                Bundle bundle = (Bundle) obj2;
                kw0 kw0Var = (kw0) obj;
                int i3 = PaymentDetailsUpdateService.a.e;
                if (uy1.a().b(i2)) {
                    uy1 uy1VarA = uy1.a();
                    uy1VarA.getClass();
                    Object obj3 = ThreadUtils.a;
                    if (bundle != null) {
                        String string = bundle.getString("methodName");
                        if (!TextUtils.isEmpty(string)) {
                            String string2 = bundle.getString("details", "{}");
                            if ((uy1VarA.a != null) || (paymentRequestUpdateEventListener = uy1VarA.b) == null || !paymentRequestUpdateEventListener.changePaymentMethodFromInvokedApp(string, string2)) {
                                uy1.c("Invalid state.", kw0Var);
                            } else {
                                uy1VarA.a = kw0Var;
                            }
                        } else {
                            uy1.c("Method name required.", kw0Var);
                        }
                    } else {
                        uy1.c("Method data required.", kw0Var);
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ gu(Object obj, int i, int i2, Object obj2) {
        this.e = i2;
        this.g = obj;
        this.h = obj2;
        this.f = i;
    }
}
