package spi;

import android.content.Context;
import android.content.pm.PackageInstaller;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class wk implements ll {
    public final ll<Context> a;
    public final ll<DataGenerator> b;
    public final ll<ExecutorDialogRouterMgr> c;

    public wk(ll<Context> llVar, ll<DataGenerator> llVar2, ll<ExecutorDialogRouterMgr> llVar3) {
        this.a = llVar;
        this.b = llVar2;
        this.c = llVar3;
    }

    public static PackageInstaller a(Context context, DataGenerator dataGenerator, ExecutorDialogRouterMgr executorDialogRouterMgr) {
        Objects.requireNonNull(vk.a);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        Intrinsics.checkNotNullExpressionValue(packageInstaller, "context.packageManager.packageInstaller");
        packageInstaller.registerSessionCallback(new uk(dataGenerator, executorDialogRouterMgr));
        return packageInstaller;
    }

    @Override // spi.ll
    public final Object get() {
        return a(this.a.get(), this.b.get(), this.c.get());
    }
}
