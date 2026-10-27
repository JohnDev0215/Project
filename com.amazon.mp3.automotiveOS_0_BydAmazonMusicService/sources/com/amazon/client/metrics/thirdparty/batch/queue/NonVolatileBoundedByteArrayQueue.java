package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.dp.logger.DPLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class NonVolatileBoundedByteArrayQueue extends BoundedByteArrayQueue {
    private static final DPLogger log = new DPLogger("Metrics:NonVolatileBoundedByteArrayQueue");
    private final File mDirOfBatchFiles;
    private final Deque<String> mFileNameQueue;

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void persistBatches() {
    }

    public NonVolatileBoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, File file) throws IOException, IllegalArgumentException {
        super(batchPipelineConfiguration, periodicMetricReporter);
        this.mFileNameQueue = new LinkedList();
        if (batchPipelineConfiguration.getMaxBatchQueueEntries() <= 0) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queue.capacityError", 1.0d);
            throw new IllegalArgumentException("Capacity of queue must be greater than 0 entry");
        }
        if (file == null || !file.isDirectory()) {
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queue.dirError", 1.0d);
            throw new IllegalArgumentException("dirOfBatchFiles must not be null and should be a valid directory.");
        }
        this.mDirOfBatchFiles = file;
        readPersistedData();
        trimQueueToFit();
    }

    private void readPersistedData() {
        File[] fileArrListFiles = this.mDirOfBatchFiles.listFiles();
        Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                Long lValueOf;
                long jValueOf = 0L;
                try {
                    lValueOf = Long.valueOf(file.getName());
                } catch (NumberFormatException unused) {
                    NonVolatileBoundedByteArrayQueue.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueRead.NumberFormatException", 1.0d);
                    NonVolatileBoundedByteArrayQueue.log.error("readPersistedData", "Unsupported file name format: " + file.getName(), new Object[0]);
                    lValueOf = jValueOf;
                }
                try {
                    jValueOf = Long.valueOf(file2.getName());
                } catch (NumberFormatException unused2) {
                    NonVolatileBoundedByteArrayQueue.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueRead.NumberFormatException", 1.0d);
                    NonVolatileBoundedByteArrayQueue.log.error("readPersistedData", "Unsupported file name format: " + file2.getName(), new Object[0]);
                }
                return lValueOf.compareTo(jValueOf);
            }
        });
        int length = fileArrListFiles.length;
        for (int i = 0; i < length; i++) {
            this.mBytesUsed += fileArrListFiles[i].length();
            this.mFileNameQueue.add(fileArrListFiles[i].getName());
            this.mNumEntries++;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void add(SerializedBatch serializedBatch, boolean z) throws IOException, IllegalArgumentException {
        validateInput(serializedBatch);
        this.mFileNameQueue.add(writeBatchToFile(serializedBatch));
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addFirst(SerializedBatch serializedBatch, boolean z) throws IOException {
        validateInput(serializedBatch);
        String strWriteBatchToFile = writeBatchToFile(serializedBatch);
        if (strWriteBatchToFile != null) {
            this.mFileNameQueue.addFirst(strWriteBatchToFile);
        }
        if (z) {
            notifyListeners();
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0073: MOVE (r0 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:16:0x0073 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String writeBatchToFile(com.amazon.client.metrics.thirdparty.batch.queue.SerializedBatch r10) throws java.lang.Throwable {
        /*
            r9 = this;
            long r0 = r9.mBytesUsed
            long r2 = r10.getLength()
            long r0 = r0 + r2
            r9.mBytesUsed = r0
            long r0 = r9.mNumEntries
            r2 = 1
            long r0 = r0 + r2
            r9.mNumEntries = r0
            r9.trimQueueToFit()
            r0 = 0
            long r4 = r10.getTimestamp()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.lang.String r1 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            java.lang.String r5 = r9.getAbsolutePath(r1)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L34
            byte[] r0 = r10.getSerializedBytes()     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L72
            r4.write(r0)     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L72
            r4.close()
            return r1
        L30:
            r0 = move-exception
            goto L37
        L32:
            r10 = move-exception
            goto L74
        L34:
            r1 = move-exception
            r4 = r0
            r0 = r1
        L37:
            long r5 = r9.mBytesUsed     // Catch: java.lang.Throwable -> L72
            long r7 = r10.getLength()     // Catch: java.lang.Throwable -> L72
            long r5 = r5 - r7
            r9.mBytesUsed = r5     // Catch: java.lang.Throwable -> L72
            long r5 = r9.mNumEntries     // Catch: java.lang.Throwable -> L72
            long r5 = r5 - r2
            r9.mNumEntries = r5     // Catch: java.lang.Throwable -> L72
            com.amazon.client.metrics.thirdparty.PeriodicMetricReporter r10 = r9.mPeriodicMetricReporter     // Catch: java.lang.Throwable -> L72
            com.amazon.client.metrics.thirdparty.MetricEvent r10 = r10.getMetricEvent()     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = "addBatchesIOFailure"
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r10.incrementCounter(r1, r2)     // Catch: java.lang.Throwable -> L72
            com.amazon.dp.logger.DPLogger r10 = com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue.log     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = "add"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            r2.<init>()     // Catch: java.lang.Throwable -> L72
            java.lang.String r3 = "Unable to persist the serializedObject to internal Storage."
            r2.append(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r3 = r0.getMessage()     // Catch: java.lang.Throwable -> L72
            r2.append(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L72
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L72
            r10.error(r1, r2, r3)     // Catch: java.lang.Throwable -> L72
            throw r0     // Catch: java.lang.Throwable -> L72
        L72:
            r10 = move-exception
            r0 = r4
        L74:
            if (r0 == 0) goto L79
            r0.close()
        L79:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue.writeBatchToFile(com.amazon.client.metrics.thirdparty.batch.queue.SerializedBatch):java.lang.String");
    }

    private void trimQueueToFit() throws IOException {
        while (true) {
            if (this.mBytesUsed <= this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes() && this.mNumEntries <= this.mBatchPipelineConfiguration.getMaxBatchQueueEntries()) {
                return;
            }
            log.debug("trimQueueToFit", "Queue is full. Dropping an element", new Object[0]);
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("droppedBatches", 1.0d);
            String strPoll = this.mFileNameQueue.poll();
            if (strPoll == null) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("queueSizeError", 1.0d);
                throw new IllegalArgumentException("All items removed and the queue is still full.");
            }
            File file = new File(getAbsolutePath(strPoll));
            this.mBytesUsed -= file.length();
            file.delete();
            this.mNumEntries--;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized SerializedBatch remove() throws IOException {
        String strPoll = this.mFileNameQueue.poll();
        FileInputStream fileInputStream = null;
        if (strPoll == null) {
            return null;
        }
        try {
            try {
                File file = new File(getAbsolutePath(strPoll));
                long length = file.length();
                if (length > 2147483647L) {
                    log.error("remove", "size of metrics batch file should not be greater than Integer.MAX_VALUE", "file size", Long.valueOf(length));
                    this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("removeBatchMAXLengthError", 1.0d);
                    throw new IOException("Size of metrics batch file greater than Integer.MAX_VALUE");
                }
                byte[] bArr = new byte[(int) length];
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileInputStream2.read(bArr);
                    this.mBytesUsed -= file.length();
                    file.delete();
                    this.mNumEntries--;
                    SerializedBatch serializedBatch = new SerializedBatch(bArr, Long.parseLong(strPoll));
                    fileInputStream2.close();
                    return serializedBatch;
                } catch (IOException e) {
                    e = e;
                    this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("removeBatchesIOFailure", 1.0d);
                    log.error("remove", "Unable to delete the file." + e.getMessage(), new Object[0]);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.BoundedByteArrayQueue
    public synchronized void purgeExpiredBatches() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mBatchPipelineConfiguration.getExpiryTimeMillis();
        long j = this.mNumEntries;
        while (this.mFileNameQueue.peek() != null && Long.parseLong(this.mFileNameQueue.peek()) < jCurrentTimeMillis) {
            try {
                remove();
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("expiredBatches", 1.0d);
            } catch (IOException e) {
                this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("removeBatchesIOFailure", 1.0d);
                log.error("purgeExpiredBatches", "Unabled to purge batch." + e.getMessage(), new Object[0]);
            }
        }
        log.debug("purgeExpiredBatches", "Number of batches purged: ", Long.valueOf(j - this.mNumEntries));
    }

    private String getAbsolutePath(String str) {
        return this.mDirOfBatchFiles.getAbsolutePath() + File.separator + str;
    }
}
