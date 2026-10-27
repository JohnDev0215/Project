package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;

/* JADX INFO: loaded from: classes2.dex */
public enum ContentStoreType implements MetricParameter {
    MEMSTORE,
    FILESTORE_FALLBACK,
    FILESTORE;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    public String toReportableString() {
        return name();
    }
}
