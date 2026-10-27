package defpackage;

import android.view.autofill.AutofillManager;
import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: AutofillSessionLifetimeController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jb extends z2.b {
    public final /* synthetic */ q2 h;
    public final /* synthetic */ kb i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jb(kb kbVar, z2 z2Var, r2 r2Var) {
        super(z2Var, false);
        this.i = kbVar;
        this.h = r2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
        AutofillManager autofillManager;
        if (navigationHandle.b || (autofillManager = (AutofillManager) this.i.e.getSystemService(AutofillManager.class)) == null) {
            return;
        }
        autofillManager.cancel();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void S0(Tab tab, boolean z) {
        AutofillManager autofillManager;
        boolean z2 = this.h.g() == 5;
        if (z || z2 || (autofillManager = (AutofillManager) this.i.e.getSystemService(AutofillManager.class)) == null) {
            return;
        }
        autofillManager.cancel();
    }
}
