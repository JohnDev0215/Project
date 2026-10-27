package defpackage;

import J.N;
import defpackage.rb2;
import java.lang.ref.WeakReference;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.password_check.f;
import org.chromium.components.download.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sb2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ sb2(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = 5;
        int i3 = this.f;
        Object obj = this.g;
        switch (i) {
            case 0:
                ((rb2.c) obj).c(i3);
                break;
            case 1:
                AutocompleteController autocompleteController = ((d) obj).w;
                if (autocompleteController.f != 0 && autocompleteController.g.a(i3, 3)) {
                    N.Mji1IuFV(autocompleteController.f, i3);
                }
                break;
            case 2:
                ((f) obj).i(i3);
                break;
            case 3:
                od2 od2Var = (od2) obj;
                if (od2Var.a != null) {
                    PropertyModel.p pVar = pd2.a;
                    switch (i3) {
                        case 2:
                        case 8:
                            i2 = 8;
                            break;
                        case 3:
                            i2 = 4;
                            break;
                        case 4:
                            break;
                        case 5:
                            i2 = 6;
                            break;
                        case 6:
                        case 7:
                            i2 = 7;
                            break;
                        default:
                            i2 = 0;
                            break;
                    }
                    oj2.U(pd2.a(i2), 10, "Settings.SafetyCheck.PasswordsResult");
                    od2Var.a.n(pd2.a, i2);
                    od2Var.k();
                }
                break;
            case 4:
                Callback callback = (Callback) ((WeakReference) obj).get();
                if (callback != null) {
                    if (i3 == 0) {
                        i2 = 2;
                    } else if (i3 == 1) {
                        i2 = 3;
                    } else if (i3 == 2) {
                        i2 = 4;
                    }
                    callback.onResult(Integer.valueOf(i2));
                }
                break;
            case 5:
                ((a) obj).b.d(i3);
                break;
            case 6:
                lg1 lg1Var = (lg1) obj;
                ig1 ig1Var = lg1Var.d;
                Runnable runnable = ig1Var.b;
                if (runnable != null) {
                    ig1Var.a();
                    long j = ig1Var.a;
                    ig1Var.a = j;
                    ig1Var.b = runnable;
                    ig1Var.c.postDelayed(runnable, j);
                }
                lg1Var.a(i3);
                break;
            default:
                ((ss1) obj).d.accept(i3);
                break;
        }
    }
}
