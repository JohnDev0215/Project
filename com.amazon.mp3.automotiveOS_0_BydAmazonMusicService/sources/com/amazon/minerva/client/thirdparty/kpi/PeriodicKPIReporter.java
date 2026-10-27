package com.amazon.minerva.client.thirdparty.kpi;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.metric.DataPointEnvelope;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class PeriodicKPIReporter extends AbstractKPIReporter {
    private static final String TAG = "PeriodicKPIReporter";
    private static ScheduledExecutorService sScheduler;
    protected MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;

    public PeriodicKPIReporter() {
        schedulePeriodicReport();
    }

    @Override // com.amazon.minerva.client.thirdparty.kpi.AbstractKPIReporter
    protected void initializeKPIKeys() {
        for (KPIMetric kPIMetric : KPIMetric.values()) {
            this.mKPIKeys.add(kPIMetric.getMetricName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordKPIMetricEvents() {
        Iterator<Map.Entry<String, MetricEvent>> it = transferToMetricEvent().entrySet().iterator();
        while (it.hasNext()) {
            MetricEvent value = it.next().getValue();
            value.setClientTimestamp(Timestamp.now());
            this.mMinervaServiceAndroidAdapter.record("", value.getMetricGroupId(), value.getSchemaId(), value.getMetricEventId().toString(), value.getClientTimestamp().getEpochMillis(), (int) TimeUnit.MILLISECONDS.toMinutes(value.getClientTimestamp().getTimeZoneOffset()), DataPointEnvelope.convertToEnvelopes(value.getKeyValuePairsAsDataPoints()));
            value.clear();
        }
    }

    private void schedulePeriodicReport() {
        Runnable runnable = new Runnable() { // from class: com.amazon.minerva.client.thirdparty.kpi.PeriodicKPIReporter.1
            @Override // java.lang.Runnable
            public void run() {
                PeriodicKPIReporter.this.recordKPIMetricEvents();
            }
        };
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        sScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(runnable, 60L, 60L, TimeUnit.MINUTES);
    }

    private synchronized Map<String, MetricEvent> transferToMetricEvent() {
        HashMap map;
        map = new HashMap();
        for (Map.Entry<String, HashMap<String, Long>> entry : this.mKPIMap.entrySet()) {
            String key = entry.getKey();
            HashMap<String, Long> value = entry.getValue();
            MetricEvent metricEvent = new MetricEvent("aminerva", "ckpi/2/00f30233");
            metricEvent.addString("customerMetricGroupId", key);
            for (Map.Entry<String, Long> entry2 : value.entrySet()) {
                metricEvent.addLong(entry2.getKey(), entry2.getValue().longValue());
            }
            map.put(key, metricEvent);
        }
        this.mKPIMap.clear();
        return map;
    }

    public void shutdown() {
        Log.i(TAG, "Shutdown was invoked.");
        recordKPIMetricEvents();
    }

    public void setMinervaServiceAndroidAdapter(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
    }
}
