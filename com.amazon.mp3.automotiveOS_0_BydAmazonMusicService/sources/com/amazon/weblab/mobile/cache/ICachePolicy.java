package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.model.TreatmentAssignment;

/* JADX INFO: loaded from: classes5.dex */
public interface ICachePolicy {
    void applyPolicy(TreatmentAssignment treatmentAssignment);

    void setListener(ICacheRefresher iCacheRefresher);
}
