package defpackage;

import defpackage.fc;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;

/* JADX INFO: compiled from: OmahaService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mr1 extends i9<Void> {
    public final /* synthetic */ fc.a h;
    public final /* synthetic */ or1 i;

    public mr1(or1 or1Var, BackgroundTaskJobService.a aVar) {
        this.i = or1Var;
        this.h = aVar;
    }

    @Override // defpackage.i9
    public final Void b() {
        this.i.a.getClass();
        AppHooks.get().getClass();
        return null;
    }

    @Override // defpackage.i9
    public final void l(Void r1) {
        this.h.b(false);
    }
}
