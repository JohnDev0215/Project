package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.SerializedBatch;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.UploadResult;
import com.amazon.dp.logger.DPLogger;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BatchTransmitter {
    private static final DPLogger log = new DPLogger("Metrics:BatchTransmitter");
    protected final ByteArrayQueue mByteArrayQueue;
    private Context mContext;
    protected final MetricsTransport mMetricsTransport;
    final PeriodicMetricReporter mPeriodicMetricReporter;
    protected final QueuePusher mQueuePusher;

    public abstract void transmitBatches(boolean z);

    public BatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, PeriodicMetricReporter periodicMetricReporter, Context context) {
        this.mByteArrayQueue = byteArrayQueue;
        this.mMetricsTransport = metricsTransport;
        this.mQueuePusher = new QueuePusher(uploadResultBroadcaster);
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        if (byteArrayQueue == null) {
            throw new IllegalArgumentException("Byte array queue cannot be null");
        }
        if (metricsTransport == null) {
            throw new IllegalArgumentException("Metrics transport instance cannot be null.");
        }
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mContext = context;
    }

    public void shutdown() {
        this.mQueuePusher.shutdown();
        this.mByteArrayQueue.shutdown();
        this.mMetricsTransport.shutdown();
    }

    protected class QueuePusher implements Runnable {
        private boolean mBroadcastResultForCurrentRun;
        private final UploadResultBroadcaster mUploadResultBroadcaster;
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);
        private final AtomicBoolean mEnableBroadcastResultForNextRun = new AtomicBoolean(false);

        public QueuePusher(UploadResultBroadcaster uploadResultBroadcaster) {
            this.mUploadResultBroadcaster = uploadResultBroadcaster;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                BatchTransmitter.log.verbose("QueuePusher.run", "Shutdown invoked.", new Object[0]);
                return;
            }
            this.mBroadcastResultForCurrentRun = this.mEnableBroadcastResultForNextRun.getAndSet(false);
            BatchTransmitter.log.verbose("QueuePusher.run", "Transmitting batches.", new Object[0]);
            sendBatches(BatchTransmitter.this.mByteArrayQueue);
        }

        public void enableBroadcastResultForNextRun() {
            this.mEnableBroadcastResultForNextRun.set(true);
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0045 A[Catch: all -> 0x003b, IOException -> 0x003e, TRY_ENTER, TryCatch #1 {IOException -> 0x003e, blocks: (B:5:0x0013, B:7:0x0019, B:8:0x0021, B:11:0x0026, B:13:0x002d, B:22:0x0045, B:24:0x006c, B:25:0x0071, B:27:0x0077, B:28:0x0081, B:31:0x008c, B:33:0x00b8, B:35:0x00bf), top: B:45:0x0013, outer: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008c A[Catch: all -> 0x003b, IOException -> 0x003e, TRY_ENTER, TryCatch #1 {IOException -> 0x003e, blocks: (B:5:0x0013, B:7:0x0019, B:8:0x0021, B:11:0x0026, B:13:0x002d, B:22:0x0045, B:24:0x006c, B:25:0x0071, B:27:0x0077, B:28:0x0081, B:31:0x008c, B:33:0x00b8, B:35:0x00bf), top: B:45:0x0013, outer: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean sendBatches(com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue r13) {
            /*
                Method dump skipped, instruction units count: 334
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter.QueuePusher.sendBatches(com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue):boolean");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private UploadResult transmitBatch(SerializedBatch serializedBatch) {
            UploadResult uploadResultTransmit = BatchTransmitter.this.mMetricsTransport.transmit(serializedBatch.getSerializedBytes());
            switch (uploadResultTransmit.getUploadStatus()) {
                case 1:
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.SUCCESS", 1.0d);
                    return uploadResultTransmit;
                case 2:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on network error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.NETWORK_ERROR", 1.0d);
                    return uploadResultTransmit;
                case 3:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on client error, discarded.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.CLIENT_ERROR", 1.0d);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.CLIENT_ERROR.Response." + uploadResultTransmit.getResponseCode(), 1.0d);
                    return uploadResultTransmit;
                case 4:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on server error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.SERVER_ERROR", 1.0d);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.SERVER_ERROR.Response." + uploadResultTransmit.getResponseCode(), 1.0d);
                    return uploadResultTransmit;
                case 5:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on credentials error, re-enqueuing batch.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.CREDENTIALS_ERROR", 1.0d);
                    return uploadResultTransmit;
                case 6:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on unknown error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.UNEXPECTED_ERROR", 1.0d);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.UNEXPECTED_ERROR.Response." + uploadResultTransmit.getResponseCode(), 1.0d);
                    return uploadResultTransmit;
                case 7:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed due to no usable connection, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.NO_USABLE_CONNECTION", 1.0d);
                    return uploadResultTransmit;
                case 8:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on empty data error, discarded.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.EMPTY_DATA_ERROR", 1.0d);
                    return uploadResultTransmit;
                case 9:
                    BatchTransmitter.log.warn("QueuePusher.sendBatches", "Batch transmission failed on illegal state error, re-enqueuing batch", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.ILLEGAL_STATE_ERROR", 1.0d);
                    return uploadResultTransmit;
                case 10:
                    BatchTransmitter.log.error("QueuePusher.sendBatches", "Batch transmission failed on IOException, re-enqueueing batch.", new Object[0]);
                    BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.IO_ERROR", 1.0d);
                    Throwable exceptionRootCause = getExceptionRootCause(uploadResultTransmit.getExceptionThrown());
                    if (exceptionRootCause != null) {
                        BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.IO_ERROR.Exception." + exceptionRootCause.getClass().getSimpleName(), 1.0d);
                    }
                    return uploadResultTransmit;
                default:
                    return uploadResultTransmit;
            }
        }

        private void updateActiveUploadStats() {
            SharedPreferences sharedPreferences = BatchTransmitter.this.mContext.getSharedPreferences("ActiveUploadStats", 0);
            Calendar calendar = Calendar.getInstance();
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            int i = calendar.get(6);
            int i2 = calendar.get(3);
            int i3 = calendar.get(2);
            if (sharedPreferences.getInt("lastUploadDay", -1) != i) {
                editorEdit.putInt("lastUploadDay", i);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.DAILY_ACTIVE_UPLOADS", 1.0d);
            }
            if (sharedPreferences.getInt("lastUploadWeek", -1) != i2) {
                editorEdit.putInt("lastUploadWeek", i2);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.WEEKLY_ACTIVE_UPLOADS", 1.0d);
            }
            if (sharedPreferences.getInt("lastUploadMonth", -1) != i3) {
                editorEdit.putInt("lastUploadMonth", i3);
                BatchTransmitter.this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("MetricsTransport.MONTHLY_ACTIVE_UPLOADS", 1.0d);
            }
            editorEdit.apply();
        }

        private Throwable getExceptionRootCause(Exception exc) {
            Throwable th = exc;
            if (exc == null) {
                return null;
            }
            while (true) {
                Throwable cause = th.getCause();
                if (cause == null || th == cause) {
                    break;
                }
                th = cause;
            }
            return th;
        }
    }
}
