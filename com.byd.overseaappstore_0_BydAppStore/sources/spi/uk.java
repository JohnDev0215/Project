package spi;

import android.content.pm.PackageInstaller;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;

/* JADX INFO: loaded from: classes.dex */
public final class uk extends PackageInstaller.SessionCallback {
    public final /* synthetic */ DataGenerator a;
    public final /* synthetic */ ExecutorDialogRouterMgr b;

    public uk(DataGenerator dataGenerator, ExecutorDialogRouterMgr executorDialogRouterMgr) {
        this.a = dataGenerator;
        this.b = executorDialogRouterMgr;
    }

    @Override // android.content.pm.PackageInstaller.SessionCallback
    public final void onActiveChanged(int i, boolean z) {
        ct.a("operate work SessionCallback onActiveChanged " + i + " active:" + z, new Object[0]);
    }

    @Override // android.content.pm.PackageInstaller.SessionCallback
    public final void onBadgingChanged(int i) {
        ct.a(e.c("operate work SessionCallback onBadgingChanged ", i), new Object[0]);
    }

    @Override // android.content.pm.PackageInstaller.SessionCallback
    public final void onCreated(int i) {
        ct.a(e.c("operate work SessionCallback onCreated ", i), new Object[0]);
    }

    @Override // android.content.pm.PackageInstaller.SessionCallback
    public final void onFinished(int i, boolean z) {
        ct.a("operate work SessionCallback  onFinished " + i + " success:" + z, new Object[0]);
        if (z) {
            return;
        }
        this.b.addBizTypeAndData(3, this.a.parseToIUUState("", (short) 1, 8)).workNotify();
    }

    @Override // android.content.pm.PackageInstaller.SessionCallback
    public final void onProgressChanged(int i, float f) {
        ct.a("operate work SessionCallback onProgressChanged " + i + " progress:" + f, new Object[0]);
    }
}
