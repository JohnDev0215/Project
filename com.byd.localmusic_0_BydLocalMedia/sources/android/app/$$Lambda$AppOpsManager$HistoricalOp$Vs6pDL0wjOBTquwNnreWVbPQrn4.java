package android.app;

import android.app.AppOpsManager;
import java.util.function.Supplier;

/* JADX INFO: renamed from: android.app.-$$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4 implements Supplier {
    public final /* synthetic */ AppOpsManager.HistoricalOp f$0;

    public /* synthetic */ $$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(AppOpsManager.HistoricalOp historicalOp) {
        this.f$0 = historicalOp;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f$0.getOrCreateAccessDuration();
    }
}
