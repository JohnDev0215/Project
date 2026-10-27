package com.amazon.avod.experiments;

import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpWeblabClientProxy implements WeblabClientProxy {
    @Override // com.amazon.avod.experiments.WeblabClientProxy
    @Nonnull
    public TreatmentFetchResult fetchResult(@Nonnull String str, @Nonnull WeblabTreatment weblabTreatment) {
        return new TreatmentFetchResult(weblabTreatment, true, "DefaultDueToNoOp");
    }

    @Override // com.amazon.avod.experiments.WeblabClientProxy
    public void trigger(@Nonnull String str, boolean z, @Nonnull WeblabTreatment weblabTreatment) {
        DLog.logf("MobileWeblab: no-op trigger() called on %s", str);
    }
}
