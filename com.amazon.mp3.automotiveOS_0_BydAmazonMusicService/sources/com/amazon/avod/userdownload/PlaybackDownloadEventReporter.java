package com.amazon.avod.userdownload;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.download.internal.PlaybackDownloadQueueConfig;
import com.amazon.avod.metrics.aloysius.AloysiusDownloadEventReporter;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.PlaybackDownloadMetrics;
import com.amazon.avod.metrics.pmet.ValidatedCounterMetricBuilder;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.userdownload.reporting.Cause;
import com.amazon.avod.userdownload.reporting.DrmRecordChangeType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadEventReporter {
    private AloysiusDownloadEventReporter mAloysiusDownloadReporter;
    private DownloadQoSReporter mDownloadQosReporter;
    private final PlaybackDownloadQueueConfig mDownloadQueueConfig;
    private final ExecutorService mExecutorService;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final Map<String, Integer> mAloysiusUpdateEventMap = new HashMap();

    public PlaybackDownloadEventReporter(@Nonnull PlaybackDownloadQueueConfig playbackDownloadQueueConfig, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull ExecutorService executorService) {
        this.mDownloadQueueConfig = (PlaybackDownloadQueueConfig) Preconditions.checkNotNull(playbackDownloadQueueConfig, "downloadQueueConfig");
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
    }

    public void initialize(@Nonnull DownloadQoSReporter downloadQoSReporter) {
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        this.mDownloadQosReporter = (DownloadQoSReporter) Preconditions.checkNotNull(downloadQoSReporter, "downloadEventReporter");
        this.mInitializationLatch.complete();
    }

    public void reportOperationWithCause(@Nonnull PlaybackDownload playbackDownload, @Nonnull Cause cause) {
        Preconditions.checkNotNull(playbackDownload, "download");
        Preconditions.checkNotNull(cause, "cause");
        reportDownloadQos(playbackDownload, cause.getEventSubType(), String.format(Locale.US, "Cause: %s, Download: %s", cause.getCauseMessage(), playbackDownload.toQosNote()));
        new ValidatedCounterMetricBuilder(PlaybackDownloadMetrics.OPERATION).addNameParameter(cause).addValueParameters(getGenericTypes(playbackDownload)).report();
    }

    public void reportDownloadPersistenceFailure(@Nonnull PlaybackDownload playbackDownload, @Nullable String str) {
        Preconditions.checkNotNull(playbackDownload, "download");
        reportDownloadQos(playbackDownload, "ErrorCondition", String.format(Locale.US, "Persistence failure, %s", playbackDownload.toQosNote()), str, 0);
        new ValidatedCounterMetricBuilder(PlaybackDownloadMetrics.PERSISTENCE_FAILED).addValueParameters(getGenericTypes(playbackDownload)).report();
    }

    public void reportDrmRecordRemoved(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownload playbackDownload2) {
        reportDrmStateChange(DrmRecordChangeType.RECORD_REMOVED, playbackDownload, playbackDownload2, ImmutableSet.of(playbackDownload));
    }

    public void reportDrmStateChange(@Nonnull DrmRecordChangeType drmRecordChangeType, @Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownload playbackDownload2, @Nonnull ImmutableSet<PlaybackDownload> immutableSet) {
        Preconditions.checkNotNull(drmRecordChangeType, "changeType");
        DrmRecord drmRecordOrNull = playbackDownload.getDrmRecord().orNull();
        DrmRecord drmRecordOrNull2 = playbackDownload2.getDrmRecord().orNull();
        String reportableString = (drmRecordOrNull2 == null || drmRecordOrNull2.getErrorCode() == null) ? null : drmRecordOrNull2.getErrorCode().toReportableString();
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(immutableSet.size());
        objArr[1] = drmRecordOrNull != null ? drmRecordOrNull.toQosNote() : null;
        objArr[2] = playbackDownload2.toQosNote();
        reportDownloadQos(playbackDownload2, drmRecordChangeType.getEventSubtype(), String.format(locale, "DRM change, %d downloads. From %s to %s", objArr), reportableString, 0);
        if (immutableSet.size() > 1) {
            ImmutableList.Builder builder = ImmutableList.builder();
            UnmodifiableIterator<PlaybackDownload> it = immutableSet.iterator();
            while (it.hasNext()) {
                builder.add(it.next().toShortQosNote());
            }
            reportDownloadQos(playbackDownload2, drmRecordChangeType.getEventSubtype(), String.format(Locale.US, "DRM change, %d downloads. Details: %s", Integer.valueOf(immutableSet.size()), Joiner.on(", ").join(builder.build())), null, 0);
        }
    }

    public void reportDownloadStateTransition(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownload playbackDownload2) {
        if (this.mAloysiusDownloadReporter == null) {
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$userdownload$UserDownloadState[playbackDownload2.getState().ordinal()]) {
            case 1:
                reportDownload(AloysiusDownloadEventReporter.Action.Start, playbackDownload2);
                break;
            case 2:
                reportDownload(AloysiusDownloadEventReporter.Action.Error, playbackDownload2);
                break;
            case 3:
                if (playbackDownload.getState() != UserDownloadState.PAUSED) {
                    reportDownload(AloysiusDownloadEventReporter.Action.Queue, playbackDownload2);
                }
                break;
            case 4:
                reportDownload(AloysiusDownloadEventReporter.Action.Complete, playbackDownload2);
                break;
            case 5:
                reportDownload(AloysiusDownloadEventReporter.Action.Delete, playbackDownload2);
                break;
            case 6:
                reportDownload(AloysiusDownloadEventReporter.Action.Stop, playbackDownload2);
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.userdownload.PlaybackDownloadEventReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$userdownload$UserDownloadState;

        static {
            int[] iArr = new int[UserDownloadState.values().length];
            $SwitchMap$com$amazon$avod$userdownload$UserDownloadState = iArr;
            try {
                iArr[UserDownloadState.DOWNLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$userdownload$UserDownloadState[UserDownloadState.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$userdownload$UserDownloadState[UserDownloadState.QUEUED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$userdownload$UserDownloadState[UserDownloadState.DOWNLOADED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$userdownload$UserDownloadState[UserDownloadState.DELETED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$userdownload$UserDownloadState[UserDownloadState.PAUSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public void reportDownloadReadyToWatch(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        new ValidatedCounterMetricBuilder(PlaybackDownloadMetrics.READY_TO_WATCH).addValueParameters(getGenericTypes(playbackDownload)).report();
        reportDownloadQos(playbackDownload, "ReadyToWatch", String.format(Locale.US, "The following download is ready to watch, %s", playbackDownload.toQosNote()));
    }

    private void reportDownloadQos(@Nonnull PlaybackDownload playbackDownload, @Nonnull String str, @Nonnull String str2) {
        reportDownloadQos(playbackDownload, str, str2, null, 0);
    }

    private void reportDownloadQos(@Nonnull PlaybackDownload playbackDownload, @Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnegative int i) {
        int length = str2.length();
        if (length >= 475) {
            Locale locale = Locale.US;
            Object[] objArr = new Object[2];
            objArr[0] = length >= 500 ? "Exceeded" : "Warning";
            objArr[1] = str;
            Profiler.reportCounterMetric(new SimpleCounterMetric(String.format(locale, "DownloadQoSNoteLength:%s:%s", objArr), length));
        }
        queueMetric(playbackDownload.getAsin(), playbackDownload.getSessionId().orNull(), playbackDownload.getStoragePath(), null, str, str2, str3, i);
    }

    private ImmutableList<MetricParameter> getGenericTypes(@Nonnull PlaybackDownload playbackDownload) {
        return ImmutableList.of((MediaQuality) playbackDownload.getUserDownloadLocation(), playbackDownload.getDownloadQuality());
    }

    private void queueMetric(@Nonnull final String str, @Nullable final String str2, @Nullable final File file, @Nullable final String str3, @Nonnull final String str4, @Nullable final String str5, @Nullable final String str6, final int i) {
        if (this.mDownloadQueueConfig.getQosReportingEventSubtypeBlocklist().contains(str4)) {
            DLog.logf("DWNLD_SDK UserDownloadEventReporter: suppressing reporting for event_subtype=%s", str4);
        } else {
            this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.userdownload.PlaybackDownloadEventReporter.1ReportMetric
                @Override // java.lang.Runnable
                public void run() {
                    PlaybackDownloadEventReporter.this.mInitializationLatch.waitOnInitializationUninterruptibly();
                    DownloadQoSReporter downloadQoSReporter = PlaybackDownloadEventReporter.this.mDownloadQosReporter;
                    String str7 = str4;
                    String str8 = str;
                    String str9 = str2;
                    File file2 = file;
                    String string = file2 != null ? file2.toString() : null;
                    String str10 = str3;
                    String str11 = str5;
                    downloadQoSReporter.reportMetric(str7, str8, str9, string, str10, str11 != null ? str11.toLowerCase(Locale.US) : null, str6, i);
                }
            });
        }
    }

    public void reportDiagnosticNote(@Nonnull String str, @Nonnull String str2) {
        queueInformationMetric(str, str2);
    }

    private void queueInformationMetric(@Nonnull final String str, @Nonnull final String str2) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.userdownload.PlaybackDownloadEventReporter.1ReportInformationMetric
            @Override // java.lang.Runnable
            public void run() {
                PlaybackDownloadEventReporter.this.mInitializationLatch.waitOnInitializationUninterruptibly();
                PlaybackDownloadEventReporter.this.mDownloadQosReporter.reportInformationalMetric(str, str2.toLowerCase(Locale.US));
            }
        });
    }

    public void setAloysiusDownloadReporter(@Nonnull AloysiusDownloadEventReporter aloysiusDownloadEventReporter) {
        this.mAloysiusDownloadReporter = (AloysiusDownloadEventReporter) Preconditions.checkNotNull(aloysiusDownloadEventReporter, "aloysiusDownloadReporter");
    }

    public void reportDownload(@Nonnull AloysiusDownloadEventReporter.Action action, @Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(action, "action");
        Preconditions.checkNotNull(playbackDownload, "download");
        int percentage = (int) playbackDownload.getPercentage();
        String strOr = playbackDownload.getSessionId().or("");
        if (action == AloysiusDownloadEventReporter.Action.Update) {
            Integer num = 0;
            if (this.mAloysiusUpdateEventMap.containsKey(strOr)) {
                num = this.mAloysiusUpdateEventMap.get(strOr);
            }
            Preconditions.checkNotNull(num, "percentageForConsumptionId");
            if (num.compareTo(Integer.valueOf(percentage - 25)) >= 0) {
                return;
            } else {
                this.mAloysiusUpdateEventMap.put(strOr, Integer.valueOf(percentage));
            }
        }
        if (action == AloysiusDownloadEventReporter.Action.Complete) {
            this.mAloysiusUpdateEventMap.remove(strOr);
        }
        this.mAloysiusDownloadReporter.reportDownloadEvent(action, percentage, playbackDownload.getSessionId().or(""));
    }
}
