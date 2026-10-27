package com.amazon.avod.experiments;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface WeblabClientProxy {
    @Nonnull
    TreatmentFetchResult fetchResult(@Nonnull String str, @Nonnull WeblabTreatment weblabTreatment);

    void trigger(@Nonnull String str, boolean z, @Nonnull WeblabTreatment weblabTreatment);
}
