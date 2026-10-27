package defpackage;

import android.app.Activity;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: OfflinePageTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cr1 implements ApplicationStatus.c {
    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        fr1 fr1Var;
        if (i == 6 && (fr1Var = (fr1) fr1.l.remove(activity)) != null) {
            fr1Var.h.a();
            HashMap map = fr1Var.i;
            if (!map.isEmpty()) {
                Iterator it = map.keySet().iterator();
                while (it.hasNext()) {
                    Tab tabS = fr1Var.g.s(((Integer) it.next()).intValue());
                    if (tabS != null) {
                        tabS.t(fr1Var);
                    }
                }
                map.clear();
            }
            if (fr1Var.j) {
                NetworkChangeNotifier.f(fr1Var);
                fr1Var.j = false;
            }
        }
    }
}
