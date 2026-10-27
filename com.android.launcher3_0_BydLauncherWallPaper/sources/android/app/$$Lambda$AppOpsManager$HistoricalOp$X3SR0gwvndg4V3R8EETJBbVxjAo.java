package android.app;

import android.app.AppOpsManager;
import java.util.function.Supplier;

/* JADX INFO: renamed from: android.app.-$$Lambda$AppOpsManager$HistoricalOp$X3SR0gwvndg4V3R8EETJBbVxjAo, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AppOpsManager$HistoricalOp$X3SR0gwvndg4V3R8EETJBbVxjAo implements Supplier {
    public final /* synthetic */ AppOpsManager.HistoricalOp f$0;

    public /* synthetic */ $$Lambda$AppOpsManager$HistoricalOp$X3SR0gwvndg4V3R8EETJBbVxjAo(AppOpsManager.HistoricalOp historicalOp) {
        this.f$0 = historicalOp;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f$0.getOrCreateRejectCount();
    }
}
