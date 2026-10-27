package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.input.b;

/* JADX INFO: compiled from: PartialCustomTabTabObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class mw1 extends nh0 {
    public final Callback<Runnable> e;
    public hw1 f;
    public Tab g;

    public mw1(Callback<Runnable> callback) {
        this.e = callback;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        ImeAdapterImpl imeAdapterImplB = ImeAdapterImpl.b(tab.c());
        hw1 hw1Var = this.f;
        imeAdapterImplB.f = hw1Var;
        b bVar = imeAdapterImplB.l;
        if (bVar != null) {
            bVar.r = hw1Var;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        if (this.f == null) {
            this.f = new hw1(tabImpl.getContext(), tabImpl.e, this.e);
        }
        if (this.g != tabImpl) {
            ImeAdapterImpl imeAdapterImplB = ImeAdapterImpl.b(tabImpl.c());
            hw1 hw1Var = this.f;
            imeAdapterImplB.f = hw1Var;
            b bVar = imeAdapterImplB.l;
            if (bVar != null) {
                bVar.r = hw1Var;
            }
            this.g = tabImpl;
        }
    }
}
