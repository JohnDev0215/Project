package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.model.TreatmentAssignment;

/* JADX INFO: loaded from: classes5.dex */
class AutoPolicy extends CachePolicy {
    AutoPolicy() {
    }

    @Override // com.amazon.weblab.mobile.cache.CachePolicy
    public boolean hasExpired(TreatmentAssignment treatmentAssignment) {
        if (treatmentAssignment != null) {
            return treatmentAssignment.getSuggestedExpiration().longValue() < System.currentTimeMillis();
        }
        throw new IllegalArgumentException("treatment can't be null");
    }
}
