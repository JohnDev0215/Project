package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.metrics.MetricMetadata;

/* JADX INFO: loaded from: classes2.dex */
public class RequestTag {
    private MetricMetadata metricMetadata;
    private String requestId;

    RequestTag(@NonNull String str, @NonNull MetricMetadata metricMetadata) {
        this.requestId = str;
        this.metricMetadata = metricMetadata;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getSource() {
        return this.metricMetadata.getSource();
    }

    public String getMetricName() {
        return this.metricMetadata.getMetricName();
    }

    public boolean includeCommsNetworkingPrefix() {
        return this.metricMetadata.includeCommsNetworkingPrefix();
    }
}
