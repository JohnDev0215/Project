package com.amazon.minerva.client.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.work.PeriodicWorkRequest;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.storage.KPIBatchCreator;
import com.amazon.minerva.client.thirdparty.storage.StorageManager;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import com.amazon.minerva.client.thirdparty.utils.MetricEventResponseIonConverter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class MetricsTransmissionManager {
    private static final String TAG = "MetricsTransmissionManager";
    protected static MetricBatchSerializer sMetricBatchSerializer;
    private KPIBatchCreator mKPIBatchCreator;
    MetricsConfigurationHelper mMetricsConfigurationHelper;
    private MetricsTransporter mMetricsTransporter;
    private File mRootDir;
    protected ScheduledThreadPoolExecutor mScheduledExecutor;
    private ServiceKPIReporter mServiceKPIReporter;
    private StorageManager mStorageManager;
    private long mTransmissionOffsetMillis;
    private Transmitter mTransmitter;
    private long mUploadInterval;

    public MetricsTransmissionManager(Context context, MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter, StorageManager storageManager, File file, KPIBatchCreator kPIBatchCreator) {
        this.mMetricsTransporter = new MetricsTransporter(context, metricsConfigurationHelper, serviceKPIReporter);
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mStorageManager = storageManager;
        this.mRootDir = file;
        this.mKPIBatchCreator = kPIBatchCreator;
        this.mServiceKPIReporter = serviceKPIReporter;
        sMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        this.mTransmissionOffsetMillis = ((long) ((Math.random() * 9.223372036854776E18d) / 1000.0d)) * 1000;
        if (this.mMetricsConfigurationHelper.getStorageConfiguration().getTransmissionPeriodMillis() < PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            this.mUploadInterval = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
            Log.e(TAG, "transmission period is set too short, override to the minimal limitation: 5 mins");
        } else {
            this.mUploadInterval = metricsConfigurationHelper.getStorageConfiguration().getTransmissionPeriodMillis();
            Log.i(TAG, "scheduled transmission interval is set to: " + this.mUploadInterval);
        }
        createDefaultThreadPoolExecutor(this.mTransmissionOffsetMillis);
    }

    private void createDefaultThreadPoolExecutor(long j) {
        long jAddOffset = addOffset(this.mUploadInterval, j);
        Log.i(TAG, "initialTransmissionPeriodMillis: " + jAddOffset);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory("BatchTransmitterThreadName"));
        this.mScheduledExecutor = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        Transmitter transmitter = new Transmitter();
        this.mTransmitter = transmitter;
        this.mScheduledExecutor.schedule(transmitter, jAddOffset, TimeUnit.MILLISECONDS);
    }

    protected long addOffset(long j, long j2) {
        long j3 = j2 % j;
        return j3 < j / 2 ? j3 + j : j3;
    }

    protected class Transmitter implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public Transmitter() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                Log.i(MetricsTransmissionManager.TAG, "Transmitter.run... Shutdown invoked.");
                return;
            }
            MetricsTransmissionManager.this.transmitMetricBatches();
            Log.i(MetricsTransmissionManager.TAG, "Transmitter.run....Rescheduling next transmission.");
            MetricsTransmissionManager metricsTransmissionManager = MetricsTransmissionManager.this;
            metricsTransmissionManager.mScheduledExecutor.schedule(metricsTransmissionManager.mTransmitter, MetricsTransmissionManager.this.mUploadInterval, TimeUnit.MILLISECONDS);
        }
    }

    public void transmitMetricBatches() {
        transmitKPIMetricBatches();
        final LinkedList linkedList = new LinkedList();
        loadMetricBatches(linkedList, false);
        this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                MetricsTransmissionManager.this.transmitMetricBatch(linkedList, false);
            }
        });
    }

    public void transmitKPIMetricBatches() {
        final LinkedList linkedList = new LinkedList();
        loadMetricBatches(linkedList, true);
        this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                MetricsTransmissionManager.this.transmitMetricBatch(linkedList, true);
            }
        });
    }

    public void transmitMetricBatch(List<BoundedBatchFileQueue> list, boolean z) throws Throwable {
        if (z) {
            KPIBatchCreator.isUploading = true;
            String str = TAG;
            Log.i(str, "transmitKPIMetricBatch started");
            transmit(list);
            Log.i(str, "transmitKPIMetricBatch finished");
            KPIBatchCreator.isUploading = false;
            this.mKPIBatchCreator.sendUploadDoneSignal();
            return;
        }
        StorageManager.isUploading = true;
        String str2 = TAG;
        Log.i(str2, "transmitMetricBatches started");
        boolean zTransmit = transmit(list);
        Log.i(str2, "transmitMetricBatches finished");
        if (zTransmit) {
            this.mStorageManager.reloadBatchFiles();
            Log.i(str2, "transmitMetricBatches, storageManager(after execute): " + this.mStorageManager.toString());
        }
        StorageManager.isUploading = false;
        this.mStorageManager.sendUploadDoneSignal();
    }

    public int loadMetricBatches(List<BoundedBatchFileQueue> list, final boolean z) {
        int size = 0;
        for (File file : this.mRootDir.listFiles(new FileFilter() { // from class: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory() && ((z && file2.getName().equals("KPI")) || !(z || file2.getName().equals("KPI")));
            }
        })) {
            BoundedBatchFileQueue boundedBatchFileQueue = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
            list.add(boundedBatchFileQueue);
            size += boundedBatchFileQueue.getFileNameQueue().size();
        }
        Log.i(TAG, "typeOfFilesLoaded: " + (z ? "KPI" : "non_KPI") + ", numberOfFilesLoaded: " + size);
        return size;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x017e A[LOOP:2: B:44:0x0178->B:46:0x017e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean transmit(java.util.List<com.amazon.minerva.client.thirdparty.transport.BoundedBatchFileQueue> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager.transmit(java.util.List):boolean");
    }

    private byte[] createRetryableBatch(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            Log.e(TAG, "Response payload or original batch is invalid.");
            return null;
        }
        if (bArr.length <= 2) {
            Log.i(TAG, "All metric events have been successfully uploaded.");
            return null;
        }
        try {
            List<IonMetricEvent> listDeserialize = sMetricBatchSerializer.deserialize(bArr2);
            Map<String, String> mapConvertIonBinaryToResponseMap = MetricEventResponseIonConverter.convertIonBinaryToResponseMap(bArr);
            ArrayList arrayList = new ArrayList();
            for (IonMetricEvent ionMetricEvent : listDeserialize) {
                if (mapConvertIonBinaryToResponseMap.containsKey(ionMetricEvent.getMetricEventId().stringValue())) {
                    String str = mapConvertIonBinaryToResponseMap.get(ionMetricEvent.getMetricEventId().stringValue());
                    if (str.equals(MetricEventResponseResult.SERVER_ERROR.toString())) {
                        Log.w(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s", str, ionMetricEvent.getMetricSchemaId()));
                        arrayList.add(ionMetricEvent);
                    } else if (str.equals(MetricEventResponseResult.SCHEMA_NOT_FOUND.toString()) || str.equals(MetricEventResponseResult.METRIC_DENIED.toString()) || str.equals(MetricEventResponseResult.VALIDATION_FAILURE.toString())) {
                        Log.e(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s, and discard.", str, ionMetricEvent.getMetricSchemaId()));
                        this.mServiceKPIReporter.report(KPIMetric.UPLOAD_CLIENT_ERROR.getMetricName(), ionMetricEvent.getMetricGroupId().stringValue(), 1L);
                    } else {
                        Log.w(TAG, String.format("An error occurs with code %s after uploading metric event with schemaID %s", str, ionMetricEvent.getMetricSchemaId()));
                        arrayList.add(ionMetricEvent);
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            try {
                return sMetricBatchSerializer.serialize(arrayList);
            } catch (IOException e) {
                Log.e(TAG, "An error occurs when converting updated batch to byte array.", e);
                this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.getMetricName(), "aminerva", 1L);
                return null;
            }
        } catch (IOException e2) {
            Log.e(TAG, "An error occurs when converting from Ion.", e2);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.getMetricName(), "aminerva", 1L);
            return null;
        }
    }

    public void shutdown() {
        this.mScheduledExecutor.shutdown();
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mScheduledExecutor;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (scheduledThreadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            this.mScheduledExecutor.shutdownNow();
            if (this.mScheduledExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            Log.e(TAG, "shutdown(), Thread pool did not terminate.");
        } catch (InterruptedException e) {
            Log.e(TAG, "shutdown(), Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }
}
