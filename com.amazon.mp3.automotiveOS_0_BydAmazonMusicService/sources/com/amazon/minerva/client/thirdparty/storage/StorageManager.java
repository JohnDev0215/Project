package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.TargetApi;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.transport.BoundedBatchFileQueue;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class StorageManager {
    public static int MAX_STORAGE_PRIORITY = 15;
    private static final String TAG = "StorageManager";
    public static volatile boolean isUploading = false;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private ScheduledThreadPoolExecutor mQueueTTLPurgerExecutor;
    private final ServiceKPIReporter mServiceKPIReporter;
    private File rootDir;
    private Object uploadDoneSignal = new Object();
    Map<Integer, BoundedBatchFileQueue> mapStoragePriorityToBatchQueue = new HashMap();
    private long totalSizeInByte = 0;
    private long totalBatchFileCount = 0;
    private final QueuesTTLPurger mQueuesTTLPurger = new QueuesTTLPurger();

    public StorageManager(MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter, File file) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mServiceKPIReporter = serviceKPIReporter;
        this.rootDir = file;
        Log.i(TAG, "absolute file path: " + file.getAbsolutePath());
        reloadBatchFiles();
        scheduleQueueTTLPeriodicPurger();
    }

    private void scheduleQueueTTLPeriodicPurger() {
        this.mQueueTTLPurgerExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory("MnvBchQsTTLPgr"));
        long purgePeriodMillis = this.mMetricsConfigurationHelper.getStorageConfiguration().getPurgePeriodMillis();
        this.mQueueTTLPurgerExecutor.scheduleAtFixedRate(this.mQueuesTTLPurger, purgePeriodMillis, purgePeriodMillis, TimeUnit.MILLISECONDS);
        this.mQueueTTLPurgerExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    public synchronized void reloadBatchFiles() {
        this.mapStoragePriorityToBatchQueue.clear();
        this.totalSizeInByte = 0L;
        this.totalBatchFileCount = 0L;
        for (File file : this.rootDir.listFiles(new FileFilter() { // from class: com.amazon.minerva.client.thirdparty.storage.StorageManager.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory() && !file2.getName().equals("KPI");
            }
        })) {
            int i = Integer.parseInt(file.getName());
            BoundedBatchFileQueue boundedBatchFileQueue = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
            this.totalSizeInByte += boundedBatchFileQueue.getTotalSizeInByte();
            this.totalBatchFileCount += (long) boundedBatchFileQueue.getFileNameQueue().size();
            this.mapStoragePriorityToBatchQueue.put(Integer.valueOf(i), boundedBatchFileQueue);
        }
    }

    public void shutdown() {
        this.mQueuesTTLPurger.shutdown();
        this.mQueueTTLPurgerExecutor.shutdown();
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mQueueTTLPurgerExecutor;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (scheduledThreadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            this.mQueueTTLPurgerExecutor.shutdownNow();
            if (this.mQueueTTLPurgerExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            Log.e(TAG, "stopQueueTTLPeriodicPurger, Thread pool did not terminate.");
        } catch (InterruptedException e) {
            this.mQueueTTLPurgerExecutor.shutdownNow();
            Log.e(TAG, "stopQueueTTLPeriodicPurger, Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void recalculateTotalSizeAndCount() {
        this.totalSizeInByte = 0L;
        this.totalBatchFileCount = 0L;
        for (Map.Entry<Integer, BoundedBatchFileQueue> entry : this.mapStoragePriorityToBatchQueue.entrySet()) {
            this.totalSizeInByte += entry.getValue().getTotalSizeInByte();
            this.totalBatchFileCount += (long) entry.getValue().getFileNameQueue().size();
        }
    }

    @TargetApi(9)
    public synchronized long addBatch(byte[] bArr, String str, int i) {
        BoundedBatchFileQueue boundedBatchFileQueue;
        long jPurgeOneFile;
        String str2 = TAG;
        Log.i(str2, "addBatch to BoundedBatchQueue, then write to disk file...");
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("Parameter batchContent can not be null or empty");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Parameter region can not be null or empty");
        }
        if (i < 0 || i > MAX_STORAGE_PRIORITY) {
            throw new IllegalArgumentException(String.format("Parameter storagePriority should be with range of [0..%d]", Integer.valueOf(MAX_STORAGE_PRIORITY)));
        }
        BoundedBatchFileQueue boundedBatchFileQueue2 = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i));
        if (boundedBatchFileQueue2 == null) {
            File file = new File(this.rootDir.getAbsolutePath() + File.separator + i);
            if (!file.isDirectory()) {
                file.mkdir();
            }
            BoundedBatchFileQueue boundedBatchFileQueue3 = new BoundedBatchFileQueue(this.mMetricsConfigurationHelper, file, this.mServiceKPIReporter);
            this.mapStoragePriorityToBatchQueue.put(Integer.valueOf(i), boundedBatchFileQueue3);
            boundedBatchFileQueue = boundedBatchFileQueue3;
        } else {
            boundedBatchFileQueue = boundedBatchFileQueue2;
        }
        long maxStorageSpaceBytes = this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxStorageSpaceBytes();
        long maxNumberOfBatchFiles = this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxNumberOfBatchFiles();
        if (!canFreeEnoughSpace(maxStorageSpaceBytes, i, bArr.length)) {
            this.mServiceKPIReporter.report(KPIMetric.DISK_EXCEEDED_MAX, bArr);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_DISK_EXCEEDED_MAX.getMetricName(), "aminerva", 1L);
            Log.e(str2, String.format("Can not free enough space to save batchContent for this storage Priority. The batch is dropped and KPI is reported. (storagePriority: %d, batchContent.length: %d)", Integer.valueOf(i), Integer.valueOf(bArr.length)));
            return 0L;
        }
        if (!canFreeOneCount(maxNumberOfBatchFiles, i)) {
            this.mServiceKPIReporter.report(KPIMetric.NUMBER_OF_FILES_EXCEEDED_MAX, bArr);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_NUMBER_OF_FILES_EXCEEDED_MAX.getMetricName(), "aminerva", 1L);
            Log.e(str2, String.format("Can not free 1 batch file to save batchContent for this storage Priority. The batch is dropped and KPI is reported. (storagePriority: %d, batchContent.length: %d)", Integer.valueOf(i), Integer.valueOf(bArr.length)));
            return 0L;
        }
        if (this.totalSizeInByte + ((long) bArr.length) > maxStorageSpaceBytes) {
            jPurgeOneFile = purgeAtLeast(i, bArr.length);
        } else {
            jPurgeOneFile = this.totalBatchFileCount >= maxNumberOfBatchFiles ? purgeOneFile(i) : 0L;
        }
        long jAddBatch = boundedBatchFileQueue.addBatch(str, bArr);
        this.totalSizeInByte += jAddBatch;
        this.totalBatchFileCount++;
        return jAddBatch - jPurgeOneFile;
    }

    private boolean canFreeEnoughSpace(long j, int i, long j2) {
        long totalSizeInByte = 0;
        for (int i2 = i + 1; i2 <= MAX_STORAGE_PRIORITY; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                totalSizeInByte += boundedBatchFileQueue.getTotalSizeInByte();
            }
        }
        return j - totalSizeInByte >= j2;
    }

    private boolean canFreeOneCount(long j, int i) {
        long size = 0;
        for (int i2 = i + 1; i2 <= MAX_STORAGE_PRIORITY; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                size += (long) boundedBatchFileQueue.getFileNameQueue().size();
            }
        }
        return j - size > 0;
    }

    private long purgeAtLeast(int i, long j) {
        long j2 = 0;
        long j3 = j;
        for (int i2 = 0; i2 <= i; i2++) {
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                long jPurgeAtLeast = boundedBatchFileQueue.purgeAtLeast(j3);
                j3 -= jPurgeAtLeast;
                j2 += jPurgeAtLeast;
                if (j2 >= j) {
                    break;
                }
            }
        }
        recalculateTotalSizeAndCount();
        return j2;
    }

    private long purgeOneFile(int i) {
        long j;
        int i2 = 0;
        while (true) {
            j = 0;
            if (i2 > i) {
                break;
            }
            BoundedBatchFileQueue boundedBatchFileQueue = this.mapStoragePriorityToBatchQueue.get(Integer.valueOf(i2));
            if (boundedBatchFileQueue != null) {
                long jPurgeOneFile = boundedBatchFileQueue.purgeOneFile();
                if (jPurgeOneFile > 0) {
                    j = 0 + jPurgeOneFile;
                    break;
                }
            }
            i2++;
        }
        recalculateTotalSizeAndCount();
        return j;
    }

    public String toString() {
        return "StorageManager{mapStoragePriorityToBatchQueue=" + this.mapStoragePriorityToBatchQueue + ", totalSizeInByte=" + this.totalSizeInByte + ", totalBatchFileCount=" + this.totalBatchFileCount + '}';
    }

    private class QueuesTTLPurger implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public QueuesTTLPurger() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                Log.i(StorageManager.TAG, "QueuePurger.run. Shutdown invoked.");
            } else {
                Log.i(StorageManager.TAG, "QueuePurger.run. Purging expired batches.");
                StorageManager.this.purgeExpiredBatches();
            }
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void purgeExpiredBatches() {
        synchronized (this.uploadDoneSignal) {
            while (isUploading) {
                try {
                    this.uploadDoneSignal.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "Exception in purgeExpiredBatches.", e);
                    return;
                }
            }
        }
        Iterator<Map.Entry<Integer, BoundedBatchFileQueue>> it = this.mapStoragePriorityToBatchQueue.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().purgeExpiredBatches();
        }
        reloadBatchFiles();
    }

    public void sendUploadDoneSignal() {
        synchronized (this.uploadDoneSignal) {
            this.uploadDoneSignal.notifyAll();
        }
    }
}
