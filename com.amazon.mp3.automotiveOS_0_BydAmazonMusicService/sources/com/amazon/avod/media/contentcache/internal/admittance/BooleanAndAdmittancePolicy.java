package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

/* JADX INFO: loaded from: classes2.dex */
public class BooleanAndAdmittancePolicy implements AdmittancePolicy {
    private final ImmutableList<AdmittancePolicy> mDelegates;

    public BooleanAndAdmittancePolicy(ImmutableList<AdmittancePolicy> immutableList) {
        Preconditions.checkState(!immutableList.isEmpty(), "delegate list shouldn't be empty!");
        this.mDelegates = immutableList;
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    public AdmittancePolicyResult canAdmit(WritableCacheRecord writableCacheRecord) {
        Preconditions.checkNotNull(writableCacheRecord, "record");
        UnmodifiableIterator<AdmittancePolicy> it = this.mDelegates.iterator();
        while (it.hasNext()) {
            AdmittancePolicyResult admittancePolicyResultCanAdmit = it.next().canAdmit(writableCacheRecord);
            if (admittancePolicyResultCanAdmit != AdmittancePolicyResult.ALLOWED) {
                DLog.logf("BooleanAndAdmittancePolicy - Cache denied for %s: %s", writableCacheRecord.getSpecification(), admittancePolicyResultCanAdmit.toString());
                return admittancePolicyResultCanAdmit;
            }
        }
        return AdmittancePolicyResult.ALLOWED;
    }
}
