package com.amazon.mls.config.internal.core.metrics.internal;

import com.amazon.mls.config.ConfigurationApi;
import com.amazon.mls.config.metadata.CommonMetadataKeys;
import com.amazon.mls.config.metadata.EventMetadata;
import com.amazon.mls.config.metadata.EventMetadataSnapshot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class Counter {
    private static final MetricUnit DEFAULT_METRIC_UNIT = MetricUnit.Count;
    private final String counterName;
    private final long counterValue;
    private final String createdAt;
    private final MetricUnit metricUnit;
    private final long metricWeight;
    private final String obfMarketplaceId;

    public Counter(String str, long j) {
        this(str, j, DEFAULT_METRIC_UNIT);
    }

    public Counter(String str, long j, MetricUnit metricUnit) {
        this(str, j, metricUnit, 1L);
    }

    public Counter(String str, long j, MetricUnit metricUnit, long j2) {
        this.counterName = str;
        this.counterValue = j;
        this.metricUnit = metricUnit;
        this.metricWeight = j2;
        this.obfMarketplaceId = getConfiguredMarketplaceId();
        this.createdAt = getCurrentISOTime();
    }

    private String getConfiguredMarketplaceId() {
        EventMetadata eventMetadata;
        EventMetadataSnapshot metadataSnapshot;
        ConfigurationApi configurationApi = ConfigurationApi.getInstance();
        if (configurationApi == null || (eventMetadata = configurationApi.getEventMetadata()) == null || (metadataSnapshot = eventMetadata.getMetadataSnapshot()) == null) {
            return null;
        }
        return metadataSnapshot.getMetadata().get(CommonMetadataKeys.ObfuscatedMarketplaceId.getKeyValue());
    }

    private static String getCurrentISOTime() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }
}
