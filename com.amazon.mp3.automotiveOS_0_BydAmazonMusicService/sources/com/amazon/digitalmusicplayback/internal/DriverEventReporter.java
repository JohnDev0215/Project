package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AudioSampleFormat;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes2.dex */
class DriverEventReporter {
    private final Context mApplicationContext;
    private final AudioDriverEventsListener mAudioDriverEventsListener;
    private final HashMap<String, Integer> mEventCounters = new HashMap<>();
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.amazon.digitalmusicplayback.internal.DriverEventReporter.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "kDriverEventThread");
        }
    });
    private final LoggingFacade.Logger mLogger;

    public static boolean IsPowerOfTwo(int i) {
        return (i & (i + (-1))) == 0;
    }

    DriverEventReporter(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull AudioDriverEventsListener audioDriverEventsListener) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mAudioDriverEventsListener = audioDriverEventsListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$asyncReportSupportedFormats$0(ArrayList arrayList) {
        this.mAudioDriverEventsListener.audioDriverFormatChanged(arrayList);
    }

    public void asyncReportSupportedFormats(final ArrayList<AudioFormat> arrayList) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.DriverEventReporter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncReportSupportedFormats$0(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$asyncReportStalled$1(boolean z) {
        this.mAudioDriverEventsListener.audioDriverPlaybackStalled(z);
    }

    public void asyncReportStalled(final boolean z) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.DriverEventReporter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncReportStalled$1(z);
            }
        });
    }

    public synchronized void asyncReportMetric(final String str, final String str2, final String str3, final String str4, final Double d, final Double d2, final Double d3) {
        Integer num = this.mEventCounters.get(str);
        Integer numValueOf = Integer.valueOf(num == null ? 1 : num.intValue() + 1);
        this.mEventCounters.put(str, numValueOf);
        if (IsPowerOfTwo(numValueOf.intValue())) {
            final double dDoubleValue = numValueOf.doubleValue();
            this.mExecutorService.execute(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.DriverEventReporter$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$asyncReportMetric$2(str, str2, str3, str4, d, d2, d3, dDoubleValue);
                }
            });
            this.mLogger.info("Driver metric: %s str2='%s' str3='%s' str4='%s' num1=%s num2=%s num3=%s count=%s", "Android." + str, str2, str3, str4, d, d2, d3, numValueOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$asyncReportMetric$2(String str, String str2, String str3, String str4, Double d, Double d2, Double d3, double d4) {
        this.mAudioDriverEventsListener.audioDriverMetric("Android." + str, str2, str3, str4, d, d2, d3, Double.valueOf(d4));
    }

    public void asyncMetricBeginRecovery(AudioSampleFormat audioSampleFormat) {
        asyncReportMetric("BeginRecovery", audioSampleFormat.toString(), null, null, null, null, null);
    }

    public void asyncMetricSuccessfulRecovery(AudioSampleFormat audioSampleFormat) {
        asyncReportMetric("SuccessfulRecovery", audioSampleFormat.toString(), null, null, null, null, null);
    }

    public void asyncMetricFailedRecovery(AudioSampleFormat audioSampleFormat) {
        asyncReportMetric("FailedRecovery", audioSampleFormat.toString(), null, null, null, null, null);
    }

    public void asyncMetricUnrecoverableState(String str) {
        asyncReportMetric("Unrecoverable", str, null, null, null, null, null);
    }

    public void asyncMetricNonProgression(AudioSampleFormat audioSampleFormat, long j) {
        asyncReportMetric("NonProgression", audioSampleFormat.toString(), null, null, Double.valueOf(j), null, null);
    }

    public void asyncMetricNonConsumption(AudioSampleFormat audioSampleFormat, long j) {
        asyncReportMetric("NonConsumption", audioSampleFormat.toString(), null, null, Double.valueOf(j), null, null);
    }

    public void asyncMetricNoLongerInitialized(AudioSampleFormat audioSampleFormat, int i) {
        asyncReportMetric("NoLongerInitialized", audioSampleFormat.toString(), null, null, Double.valueOf(i), null, null);
    }

    public void asyncMetricConsecutiveWriteErrors(AudioSampleFormat audioSampleFormat, int i) {
        asyncReportMetric("ConsecutiveWriteErrors", audioSampleFormat.toString(), null, null, Double.valueOf(i), null, null);
    }

    public void asyncMetricNoRoutedDevice() {
        asyncReportMetric("NoRoutedDevice", null, null, null, null, null, null);
    }

    public void asyncMetricNoOutputDevices() {
        asyncReportMetric("NoOutputDevices", null, null, null, null, null, null);
    }

    public void asyncMetricUnhandledException(Exception exc, String str) {
        asyncReportMetric("UnhandledException", exc.toString(), str, null, null, null, null);
    }

    public void asyncMetricAndLogProgrammingError(LoggingFacade.Logger logger, String str, String str2, Object... objArr) {
        try {
            str2 = String.format(str2, objArr);
        } catch (IllegalFormatException unused) {
        }
        String str3 = str2;
        logger.error("%s", str3);
        asyncReportMetric("ProgrammingError", str3, str, null, null, null, null);
    }
}
