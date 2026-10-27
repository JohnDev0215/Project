package com.amazon.commscore.api.metrics;

import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class OperationalMetric extends BaseMetric<OperationalMetric> {
    private OperationalMetricType type;

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ BaseMetric setCustomEntries(@Nullable Map map) {
        return super.setCustomEntries(map);
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getComponent() {
        return super.getComponent();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getOwnerIdentifier() {
        return super.getOwnerIdentifier();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getSubComponent() {
        return super.getSubComponent();
    }

    protected OperationalMetric(String str, String str2, String str3, OperationalMetricType operationalMetricType) {
        super(str, str2, str3);
        this.type = operationalMetricType;
    }

    protected OperationalMetric(String str, String str2, String str3, String str4, OperationalMetricType operationalMetricType) {
        super(str, str2, str3, str4);
        this.type = operationalMetricType;
    }

    public OperationalMetricType getType() {
        return this.type;
    }
}
