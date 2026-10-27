package android.app;

import android.app.AppOpsManager;
import java.util.function.Supplier;

/* JADX INFO: renamed from: android.app.-$$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A implements Supplier {
    public final /* synthetic */ AppOpsManager.HistoricalOp f$0;

    public /* synthetic */ $$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(AppOpsManager.HistoricalOp historicalOp) {
        this.f$0 = historicalOp;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f$0.getOrCreateRejectCount();
    }
}
