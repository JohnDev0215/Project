package defpackage;

import java.util.Iterator;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: AdaptiveToolbarButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l3 extends nh0 {
    public final /* synthetic */ m3 e;

    public l3(m3 m3Var) {
        this.e = m3Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
        Integer num = 0;
        m3 m3Var = this.e;
        Iterator it = m3Var.g.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Integer num2 = (Integer) it.next();
            if (m3Var.f == m3Var.g.get(num2)) {
                num = num2;
                break;
            }
        }
        oj2.U(num.intValue(), 8, "Android.AdaptiveToolbarButton.Variant.OnStartNavigation");
    }
}
