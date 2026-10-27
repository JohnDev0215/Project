package defpackage;

import defpackage.ft2;
import defpackage.kj;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabbedRootUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h63 implements ft2.a {
    public final /* synthetic */ g63 e;

    public h63(g63 g63Var) {
        this.e = g63Var;
    }

    @Override // ft2.a
    public final void e(int i) {
        g63 g63Var = this.e;
        g63Var.f1 = i;
        int dimensionPixelSize = g63Var.g.getResources().getDimensionPixelSize(g63Var.a1);
        int i2 = g63Var.f1;
        int i3 = dimensionPixelSize + i2;
        fj fjVar = g63Var.R;
        boolean z = true;
        fjVar.p = true;
        if (fjVar.l != i3 || fjVar.m != i2) {
            TraceEvent traceEventH0 = TraceEvent.H0("BrowserControlsManager.setTopControlsHeight", null);
            try {
                int i4 = fjVar.l;
                int i5 = fjVar.m;
                fjVar.l = i3;
                fjVar.m = i2;
                Tab tab = fjVar.z;
                if (tab == null || !tab.isUserInteractable() || tab.isNativePage()) {
                    z = false;
                }
                if (!z) {
                    if (fjVar.p) {
                        fjVar.x(i4, i5);
                    } else {
                        fjVar.n(false);
                    }
                }
                Iterator<kj.a> it = fjVar.x.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((kj.a) aVar.next()).n();
                    }
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
        fjVar.p = false;
    }
}
