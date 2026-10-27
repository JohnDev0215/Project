package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.model.TreatmentAssignment;

/* JADX INFO: loaded from: classes5.dex */
abstract class CachePolicy implements ICachePolicy {
    private ICacheRefresher mCachePolicyListener;

    public abstract boolean hasExpired(TreatmentAssignment treatmentAssignment);

    protected CachePolicy() {
    }

    @Override // com.amazon.weblab.mobile.cache.ICachePolicy
    public void setListener(ICacheRefresher iCacheRefresher) {
        this.mCachePolicyListener = iCacheRefresher;
    }

    protected ICacheRefresher getListener() {
        return this.mCachePolicyListener;
    }

    @Override // com.amazon.weblab.mobile.cache.ICachePolicy
    public void applyPolicy(TreatmentAssignment treatmentAssignment) {
        ICacheRefresher listener = getListener();
        if (listener == null || !hasExpired(treatmentAssignment)) {
            return;
        }
        listener.refresh();
    }
}
