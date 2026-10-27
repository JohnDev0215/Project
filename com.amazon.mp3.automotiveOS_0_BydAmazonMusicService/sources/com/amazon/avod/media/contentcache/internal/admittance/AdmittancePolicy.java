package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdmittancePolicy {
    @Nonnull
    AdmittancePolicyResult canAdmit(@Nonnull WritableCacheRecord writableCacheRecord);
}
