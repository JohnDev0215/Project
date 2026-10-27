package defpackage;

import defpackage.o23;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: CustomTabTaskDescriptionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class j40 extends o23.b {
    public final /* synthetic */ l40 e;

    public j40(l40 l40Var) {
        this.e = l40Var;
    }

    @Override // o23.b
    public final void A1(Tab tab) {
        l40.a(this.e);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void L0(TabImpl tabImpl, boolean z) {
        this.e.c();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void M(int i, Tab tab) {
        this.e.c();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (!navigationHandle.g || navigationHandle.c) {
            return;
        }
        l40 l40Var = this.e;
        l40Var.r = null;
        l40Var.c();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void r1(Tab tab) {
        this.e.c();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        this.e.c();
    }

    @Override // o23.b
    public final void z1(Tab tab) {
        l40.a(this.e);
    }
}
