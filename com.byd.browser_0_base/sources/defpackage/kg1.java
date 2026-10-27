package defpackage;

import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: compiled from: MessageBannerCoordinator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kg1 implements ListMenuButton.a {
    public final /* synthetic */ ig1 a;
    public final /* synthetic */ long b;
    public final /* synthetic */ Runnable c;

    public kg1(ig1 ig1Var, long j, ve3 ve3Var) {
        this.a = ig1Var;
        this.b = j;
        this.c = ve3Var;
    }

    @Override // org.chromium.components.browser_ui.widget.listmenu.ListMenuButton.a
    public final void a() {
        this.a.a();
    }

    @Override // org.chromium.components.browser_ui.widget.listmenu.ListMenuButton.a
    public final void b() {
        ig1 ig1Var = this.a;
        long j = this.b;
        ig1Var.a = j;
        Runnable runnable = this.c;
        ig1Var.b = runnable;
        ig1Var.c.postDelayed(runnable, j);
    }
}
