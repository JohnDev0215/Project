package defpackage;

import android.os.Handler;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kw1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ kw1(Object obj, int i, boolean z) {
        this.e = i;
        this.g = obj;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.g;
        switch (i) {
            case 0:
                lw1 lw1Var = (lw1) obj;
                if (!lw1Var.H) {
                    new Handler().post(new iw1(lw1Var, 0));
                } else {
                    lw1Var.p(false);
                    lw1Var.y();
                }
                break;
            default:
                ((CustomTabToolbar.b) obj).t(this.f);
                break;
        }
    }
}
