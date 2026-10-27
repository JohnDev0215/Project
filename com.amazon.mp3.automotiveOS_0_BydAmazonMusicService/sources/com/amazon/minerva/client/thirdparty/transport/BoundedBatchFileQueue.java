package com.amazon.minerva.client.thirdparty.transport;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BoundedBatchFileQueue {
    private static final String TAG = "BoundedBatchFileQueue";
    private final File mDirOfBatchFiles;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private final ServiceKPIReporter mServiceKPIReporter;
    private Map<String, Long> mapFileNameToSizeInByte = new HashMap();
    private long mTotalSizeInByte = 0;
    private Deque<String> mFileNameQueue = new LinkedList();

    public BoundedBatchFileQueue(MetricsConfigurationHelper metricsConfigurationHelper, File file, ServiceKPIReporter serviceKPIReporter) {
        if (metricsConfigurationHelper == null) {
            throw new IllegalArgumentException("metricsConfigurationHelper cannot be null.");
        }
        if (file == null || !file.isDirectory()) {
            throw new IllegalArgumentException("Directory of batch files cannot be null or invalid.");
        }
        if (serviceKPIReporter == null) {
            throw new IllegalArgumentException("serviceKPIReporter cannot be null.");
        }
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mDirOfBatchFiles = file;
        this.mServiceKPIReporter = serviceKPIReporter;
        readFileNamesToQueue();
    }

    private void readFileNamesToQueue() {
        File[] fileArrListFiles = this.mDirOfBatchFiles.listFiles();
        Arrays.sort(fileArrListFiles);
        for (File file : fileArrListFiles) {
            if (file.exists() && file.isFile()) {
                String name = file.getName();
                long length = file.length();
                this.mFileNameQueue.add(name);
                this.mapFileNameToSizeInByte.put(name, Long.valueOf(length));
                this.mTotalSizeInByte += length;
            }
        }
    }

    public Deque<String> getFileNameQueue() {
        return this.mFileNameQueue;
    }

    public synchronized SerializedBatch remove() {
        String strPoll = this.mFileNameQueue.poll();
        if (strPoll != null) {
            Long l = this.mapFileNameToSizeInByte.get(strPoll);
            if (l != null) {
                this.mapFileNameToSizeInByte.remove(strPoll);
                this.mTotalSizeInByte -= l.longValue();
            }
            File file = new File(getAbsolutePath(strPoll));
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(bArr);
                    Log.i(TAG, "batch file deleted: " + strPoll);
                    file.delete();
                    SerializedBatch serializedBatch = new SerializedBatch(bArr, strPoll);
                    fileInputStream.close();
                    return serializedBatch;
                } finally {
                }
            } catch (IOException e) {
                Log.e(TAG, "An error occurs when reading bytes from the input stream.", e);
                this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.getMetricName(), "aminerva", 1L);
            }
        }
        return null;
    }

    public synchronized void addFirst(SerializedBatch serializedBatch) {
        String strWriteBatchToFile = writeBatchToFile(serializedBatch);
        if (strWriteBatchToFile != null) {
            this.mFileNameQueue.addFirst(strWriteBatchToFile);
            this.mapFileNameToSizeInByte.put(strWriteBatchToFile, Long.valueOf(serializedBatch.getBatchContent().length));
            this.mTotalSizeInByte += (long) serializedBatch.getBatchContent().length;
            this.mServiceKPIReporter.report(KPIMetric.BATCH_CREATED.getMetricName(), "aminerva", 1L);
        }
    }

    public synchronized long addBatch(String str, byte[] bArr) {
        String strWriteBatchToFile;
        if (bArr != null) {
            if (bArr.length > 0 && (strWriteBatchToFile = writeBatchToFile(new SerializedBatch(bArr, getNewFileName(str)))) != null) {
                this.mFileNameQueue.add(strWriteBatchToFile);
                this.mapFileNameToSizeInByte.put(strWriteBatchToFile, Long.valueOf(bArr.length));
                this.mTotalSizeInByte += (long) bArr.length;
                this.mServiceKPIReporter.report(KPIMetric.BATCH_CREATED.getMetricName(), "aminerva", 1L);
                return bArr.length;
            }
        }
        return 0L;
    }

    private String getNewFileName(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = 1;
        while (true) {
            int i2 = i + 1;
            String str2 = String.format(Locale.US, "%d_%s_%04d", Long.valueOf(jCurrentTimeMillis), str, Integer.valueOf(i));
            if (!this.mapFileNameToSizeInByte.containsKey(str2)) {
                return str2;
            }
            i = i2;
        }
    }

    String writeBatchToFile(SerializedBatch serializedBatch) {
        String fileName = serializedBatch.getFileName();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getAbsolutePath(fileName));
            try {
                fileOutputStream.write(serializedBatch.getBatchContent());
                Log.i(TAG, "batch added to file: " + fileName);
                fileOutputStream.close();
                return fileName;
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "An error occurs when writing bytes to the file.", e);
            this.mServiceKPIReporter.report(KPIMetric.BATCH_IOEXCEPTION_DROPPED.getMetricName(), "aminerva", 1L);
            return null;
        }
    }

    private String getAbsolutePath(String str) {
        return this.mDirOfBatchFiles.getAbsolutePath() + File.separator + str;
    }

    public synchronized void purgeExpiredBatches() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mMetricsConfigurationHelper.getStorageConfiguration().getExpiryTimeMillis();
        int size = this.mFileNameQueue.size();
        while (this.mFileNameQueue.peek() != null) {
            long fileTimestamp = getFileTimestamp(this.mFileNameQueue.peek());
            if (fileTimestamp != -1 && fileTimestamp >= jCurrentTimeMillis) {
                break;
            }
            SerializedBatch serializedBatchRemove = remove();
            if (serializedBatchRemove != null) {
                this.mServiceKPIReporter.report(KPIMetric.BATCH_TTL_DROPPED.getMetricName(), "aminerva", 1L);
                this.mServiceKPIReporter.report(KPIMetric.TTL_EXCEEDED_MAX, serializedBatchRemove.getBatchContent());
            }
        }
        Log.i(TAG, String.format("Number of batches purged: %d", Integer.valueOf(size - this.mFileNameQueue.size())));
    }

    private long getFileTimestamp(String str) {
        String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3);
        if (strArrSplit.length == 3) {
            return Long.parseLong(strArrSplit[0]);
        }
        return -1L;
    }

    public long getTotalSizeInByte() {
        return this.mTotalSizeInByte;
    }

    public long purgeAtLeast(long j) {
        long j2 = 0;
        while (true) {
            long j3 = this.mTotalSizeInByte;
            if (j3 <= 0 || j <= 0) {
                break;
            }
            SerializedBatch serializedBatchRemove = remove();
            if (serializedBatchRemove != null) {
                this.mServiceKPIReporter.report(KPIMetric.BATCH_DISK_EXCEEDED_MAX.getMetricName(), "aminerva", 1L);
                this.mServiceKPIReporter.report(KPIMetric.DISK_EXCEEDED_MAX, serializedBatchRemove.getBatchContent());
            }
            long j4 = j3 - this.mTotalSizeInByte;
            j2 += j4;
            j -= j4;
        }
        return j2;
    }

    public long purgeOneFile() {
        long j = this.mTotalSizeInByte;
        SerializedBatch serializedBatchRemove = remove();
        if (serializedBatchRemove != null) {
            this.mServiceKPIReporter.report(KPIMetric.BATCH_NUMBER_OF_FILES_EXCEEDED_MAX.getMetricName(), "aminerva", 1L);
            this.mServiceKPIReporter.report(KPIMetric.NUMBER_OF_FILES_EXCEEDED_MAX, serializedBatchRemove.getBatchContent());
        }
        return j - this.mTotalSizeInByte;
    }

    public String toString() {
        return "BoundedBatchFileQueue{mapFileNameToSizeInByte=" + this.mapFileNameToSizeInByte + ", totalSizeInByte=" + this.mTotalSizeInByte + '}';
    }
}
