package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.model.TreatmentAssignment;

/* JADX INFO: loaded from: classes5.dex */
class DisabledPolicy implements ICachePolicy {
    @Override // com.amazon.weblab.mobile.cache.ICachePolicy
    public void applyPolicy(TreatmentAssignment treatmentAssignment) {
    }

    @Override // com.amazon.weblab.mobile.cache.ICachePolicy
    public void setListener(ICacheRefresher iCacheRefresher) {
    }

    DisabledPolicy() {
    }
}
