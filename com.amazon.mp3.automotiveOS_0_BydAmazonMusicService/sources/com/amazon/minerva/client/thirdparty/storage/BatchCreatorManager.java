package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.TargetApi;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class BatchCreatorManager {
    private static final String TAG = "BatchCreatorManager";
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private final StorageManager mStorageManager;
    Map<String, BatchCreator> mapStoragePriorityToBatchCreator = new HashMap();

    public BatchCreatorManager(MetricsConfigurationHelper metricsConfigurationHelper, StorageManager storageManager) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mStorageManager = storageManager;
    }

    @TargetApi(24)
    public void addMetricEvent(IonMetricEvent ionMetricEvent) {
        String str = TAG;
        Log.d(str, "addMetricEvent");
        Objects.requireNonNull(ionMetricEvent, "Parameter metricEvent can not be null");
        int storagePriority = getStoragePriority(ionMetricEvent);
        String region = ionMetricEvent.getRegion();
        String str2 = storagePriority + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + region;
        Log.d(str, "storagePriority: " + storagePriority);
        BatchCreator batchCreator = this.mapStoragePriorityToBatchCreator.get(str2);
        Log.d(str, "batchCreatorKey: " + str2);
        Log.d(str, "mapStoragePriorityToBatchCreator size: " + this.mapStoragePriorityToBatchCreator.size());
        if (batchCreator == null) {
            batchCreator = new BatchCreator(this.mMetricsConfigurationHelper, this.mStorageManager, region, storagePriority);
            this.mapStoragePriorityToBatchCreator.put(str2, batchCreator);
        }
        for (Map.Entry<String, BatchCreator> entry : this.mapStoragePriorityToBatchCreator.entrySet()) {
            Log.d(TAG, "batchCreatorKey = " + entry.getKey() + ", batchCreator = " + entry.getValue());
        }
        batchCreator.addMetricEvent(ionMetricEvent);
    }

    private int getStoragePriority(IonMetricEvent ionMetricEvent) {
        return new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).getVersionedAttributes().getIntegerValue(AttributeEnumV2.STORAGE_PRIORITY).intValue();
    }

    public void shutdown() {
        Iterator<Map.Entry<String, BatchCreator>> it = this.mapStoragePriorityToBatchCreator.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().shutdown();
        }
    }
}
