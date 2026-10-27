package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.settings.Interval;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class OnFirstPolicy extends LocalPolicy {
    public OnFirstPolicy(Interval interval) {
        super(interval);
    }

    @Override // com.amazon.weblab.mobile.cache.CachePolicy
    public boolean hasExpired(TreatmentAssignment treatmentAssignment) {
        if (treatmentAssignment != null) {
            return treatmentAssignment.getDateModified().longValue() + this.mTtl.convertTo(TimeUnit.MILLISECONDS) < System.currentTimeMillis();
        }
        throw new IllegalArgumentException("treatment can't be null");
    }
}
