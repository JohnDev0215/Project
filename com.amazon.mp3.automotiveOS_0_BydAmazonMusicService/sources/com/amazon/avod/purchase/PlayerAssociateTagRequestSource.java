package com.amazon.avod.purchase;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayerAssociateTagRequestSource implements AssociateTagRequester {
    QOS("QoS"),
    Aloysius("Aloysius");

    private final String mMetricName;

    PlayerAssociateTagRequestSource(@Nonnull String str) {
        this.mMetricName = (String) Preconditions.checkNotNull(str, "metricName");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mMetricName;
    }
}
