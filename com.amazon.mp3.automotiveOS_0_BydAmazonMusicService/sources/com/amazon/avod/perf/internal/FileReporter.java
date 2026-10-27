package com.amazon.avod.perf.internal;

import android.content.Context;
import android.os.Build;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.fileio.MediaStoreFileUtil;
import com.amazon.avod.perf.CounterMetric;
import com.amazon.avod.perf.Extra;
import com.amazon.avod.perf.Marker;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TimerMetric;
import com.amazon.avod.util.DLog;
import com.amplifyframework.core.model.ModelIdentifier;
import com.google.common.collect.EvictingQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class FileReporter implements ProfilerListener {
    private AtomicBoolean mHasHitMemoryStorageSizeLimit = new AtomicBoolean(false);
    private final MediaStoreFileUtil mMediaStoreFileUtil = new MediaStoreFileUtil();
    private final Collection<Object> mMemoryStorage;
    private final int mMemoryStorageMaxSize;

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onMarker(Marker marker, Extra extra) {
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceBegin(Trace trace) {
    }

    public FileReporter(int i) {
        this.mMemoryStorageMaxSize = i;
        this.mMemoryStorage = Collections.synchronizedCollection(EvictingQueue.create(i));
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStart() {
        DLog.logf("Starting file logging...");
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onProfilerStop(@Nullable Context context) {
        flush(context);
        this.mHasHitMemoryStorageSizeLimit.set(false);
        DLog.logf("Stopping file logging...");
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTimerMetric(TimerMetric timerMetric) {
        if (timerMetric.getTypeList().isEmpty()) {
            return;
        }
        this.mMemoryStorage.add(new MetricRecord(timerMetric));
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onCounterMetric(CounterMetric counterMetric) {
        if (counterMetric.getTypeList().isEmpty()) {
            return;
        }
        this.mMemoryStorage.add(new CounterRecord(counterMetric));
    }

    @Override // com.amazon.avod.perf.internal.ProfilerListener
    public void onTraceEnd(Trace trace) {
        if (this.mMemoryStorage.size() >= this.mMemoryStorageMaxSize && this.mHasHitMemoryStorageSizeLimit.compareAndSet(false, true)) {
            DLog.warnf("Memory storage maximum size reached while profiling enabled");
        }
        this.mMemoryStorage.add(trace);
    }

    private void flush(@Nullable Context context) {
        boolean zFlushToFile;
        File file = new File(Profiler.getFileLoggingPath());
        if (Build.VERSION.SDK_INT < 29 || !Profiler.isFileLoggingPathExternal()) {
            zFlushToFile = flushToFile(file);
        } else {
            zFlushToFile = context != null && flushToMediaStore(file.getName(), context);
        }
        if (zFlushToFile) {
            return;
        }
        DLog.warnf("Could not save trace information to default location, trying fallback location in application data directory");
        flushToFile(new File(StorageHelper.getInstance().getGeneralFileDir(), file.getName()));
    }

    private boolean flushToMediaStore(@Nonnull String str, @Nonnull Context context) {
        try {
            OutputStream outputStreamOpenOutputStream = this.mMediaStoreFileUtil.openOutputStream(str, context, "text/csv");
            try {
                if (outputStreamOpenOutputStream == null) {
                    DLog.errorf("Unable to write to file");
                    if (outputStreamOpenOutputStream != null) {
                        outputStreamOpenOutputStream.close();
                    }
                    return false;
                }
                boolean zFlushToStream = flushToStream(outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.close();
                return zFlushToStream;
            } finally {
            }
        } catch (IOException e) {
            DLog.exceptionf(e, "Unable to write to file", new Object[0]);
            return false;
        }
    }

    private boolean flushToFile(@Nonnull File file) {
        DLog.logf("Saving metrics to: %s", file.getAbsoluteFile());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            try {
                boolean zFlushToStream = flushToStream(fileOutputStream);
                fileOutputStream.close();
                return zFlushToStream;
            } finally {
            }
        } catch (IOException e) {
            DLog.exceptionf(e, "Unable to write to file", new Object[0]);
            return false;
        }
    }

    private boolean flushToStream(@Nonnull OutputStream outputStream) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            try {
                StringBuilder sb = new StringBuilder(256);
                synchronized (this.mMemoryStorage) {
                    for (Object obj : this.mMemoryStorage) {
                        sb.setLength(0);
                        String csvForPerfEntry = getCsvForPerfEntry(obj, sb);
                        if (csvForPerfEntry.length() > 0) {
                            outputStreamWriter.write(csvForPerfEntry);
                        }
                    }
                    this.mMemoryStorage.clear();
                }
                outputStreamWriter.flush();
                outputStreamWriter.close();
                return true;
            } finally {
            }
        } catch (IOException e) {
            DLog.exceptionf(e, "Failed to save metrics", new Object[0]);
            return false;
        }
    }

    private static String getCsvForPerfEntry(Object obj, StringBuilder sb) {
        if (obj instanceof MetricRecord) {
            return metricToCsv((MetricRecord) obj, sb);
        }
        if (obj instanceof CounterRecord) {
            return counterToCsv((CounterRecord) obj, sb);
        }
        if (obj instanceof Trace) {
            return traceToCsv((Trace) obj, sb);
        }
        throw new IllegalStateException("Unexpected perf entry object found in the memory storage; cannot create a csv representation of it.");
    }

    private static String metricToCsv(MetricRecord metricRecord, StringBuilder sb) {
        sb.append("metric");
        sb.append(",");
        sb.append(metricRecord.name);
        sb.append(":");
        sb.append(metricRecord.type);
        sb.append(",");
        sb.append(metricRecord.startTimeMillis);
        sb.append(",");
        sb.append(metricRecord.durationMillis);
        sb.append("\n");
        return sb.toString();
    }

    private static String counterToCsv(CounterRecord counterRecord, StringBuilder sb) {
        sb.append("counter");
        sb.append(",");
        sb.append(counterRecord.name);
        sb.append(":");
        sb.append(counterRecord.type);
        sb.append(",");
        sb.append(counterRecord.incrementValue);
        sb.append("\n");
        return sb.toString();
    }

    private static String traceToCsv(Trace trace, StringBuilder sb) {
        sb.append(TracePublisherServiceMessageType.TRACE_MESSAGE_KEY);
        sb.append(",");
        sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        sb.append(trace.getName());
        sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        sb.append(",");
        sb.append(trace.getKey().getThreadName());
        sb.append(",");
        sb.append(trace.getKey().getThreadId());
        sb.append(",");
        sb.append(trace.getStartTime());
        sb.append(",");
        sb.append(trace.getDuration());
        sb.append("\n");
        return sb.toString();
    }

    private static class MetricRecord {
        private final long durationMillis;
        private final String name;
        private final long startTimeMillis;
        private final String type;

        private MetricRecord(@Nonnull TimerMetric timerMetric) {
            this.name = timerMetric.getName();
            this.startTimeMillis = timerMetric.getStartTimeMillis();
            this.durationMillis = timerMetric.getDurationMillis();
            this.type = timerMetric.getTypeList().get(r3.size() - 1);
        }
    }

    private static class CounterRecord {
        private final long incrementValue;
        private final String name;
        private final String type;

        private CounterRecord(@Nonnull CounterMetric counterMetric) {
            this.name = counterMetric.getName();
            this.incrementValue = counterMetric.getIncrementValue();
            this.type = counterMetric.getTypeList().get(r3.size() - 1);
        }
    }
}
