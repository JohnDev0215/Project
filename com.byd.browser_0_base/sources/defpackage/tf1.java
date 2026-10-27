package defpackage;

import android.os.Handler;
import android.util.Pair;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MerchantTrustMessageScheduler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tf1 {
    public final vg1 a;
    public final wf1 b;
    public final zp1<Tab> c;
    public final Handler d = new Handler(ThreadUtils.d());
    public Pair<rf1, PropertyModel> e;

    public tf1(vg1 vg1Var, wf1 wf1Var, z2 z2Var) {
        this.a = vg1Var;
        this.b = wf1Var;
        this.c = z2Var;
    }

    public final void a(int i) {
        if (this.e != null) {
            wf1 wf1Var = this.b;
            wf1Var.d();
            oj2.U(i, 5, "MerchantTrust.Message.ClearReason");
            wf1Var.a = false;
            wf1Var.b = false;
            wf1Var.c = 0L;
            wf1Var.d = 0L;
        }
        synchronized (this.d) {
            this.e = null;
        }
    }
}
