package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;

/* JADX INFO: compiled from: TabGroupUiToolbarView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o03 implements Runnable {
    public final /* synthetic */ t41 e;
    public final /* synthetic */ TabGroupUiToolbarView f;

    public o03(TabGroupUiToolbarView tabGroupUiToolbarView, t41 t41Var) {
        this.f = tabGroupUiToolbarView;
        this.e = t41Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.i(this.f.k);
    }
}
