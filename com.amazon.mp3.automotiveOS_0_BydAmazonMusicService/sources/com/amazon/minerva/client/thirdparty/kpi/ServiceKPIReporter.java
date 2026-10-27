package com.amazon.minerva.client.thirdparty.kpi;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.storage.StorageManager;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceKPIReporter extends AbstractKPIReporter {
    private static final String TAG = StorageManager.class.getSimpleName();
    private final MetricBatchSerializer mMetricBatchSerializer;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;

    public ServiceKPIReporter(MetricsConfigurationHelper metricsConfigurationHelper) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
    }

    @Override // com.amazon.minerva.client.thirdparty.kpi.AbstractKPIReporter
    protected void initializeKPIKeys() {
        for (KPIMetric kPIMetric : KPIMetric.values()) {
            this.mKPIKeys.add(kPIMetric.getMetricName());
        }
    }

    public void report(KPIMetric kPIMetric, byte[] bArr) {
        try {
            Iterator<IonMetricEvent> it = this.mMetricBatchSerializer.deserialize(bArr).iterator();
            while (it.hasNext()) {
                report(kPIMetric.getMetricName(), it.next().getMetricGroupId().stringValue(), 1L);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error happens when converting Ion Binary to list of IonMetricEvent.", e);
        }
    }
}
