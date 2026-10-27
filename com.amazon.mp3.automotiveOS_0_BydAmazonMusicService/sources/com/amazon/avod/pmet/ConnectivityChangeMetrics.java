package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum ConnectivityChangeMetrics implements MetricParameter {
    CONNECTED_TO_NETWORK,
    DISCONNECTED_FROM_NETWORK;

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
