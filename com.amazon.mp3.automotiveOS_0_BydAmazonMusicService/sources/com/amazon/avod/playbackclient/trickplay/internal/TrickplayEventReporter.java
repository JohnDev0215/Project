package com.amazon.avod.playbackclient.trickplay.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.plugin.PluginLoadStatus;
import com.amazon.avod.media.download.plugin.reporting.PluginErrorType;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playbackclient.reporting.MetricEventReporter;
import com.amazon.avod.playbackclient.trickplay.download.TrickplayLoadingResponse;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Throwables2;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TrickplayEventReporter {
    private AloysiusDiagnosticsReporter mAloysiusDiagnosticsReporter;
    private final TrickplayEventDataBuilder mBuilder;
    private MetricEventReporter mEventReporter;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter = PlaybackPmetMetricReporter.getInstance();
    private final boolean mReportImageFidelityToPmet;
    private final boolean mReportImageLatencyToPmet;
    private final boolean mReportImageUpdateFailurePctToPmet;
    private final boolean mReportPlaybackPositionChangeToPmet;
    private final boolean mReportScrubbingDurationToPmet;
    private final boolean mReportSyeAvgImageWidthToPmet;
    private final boolean mReportSyeNumImageQualityFlipToPmet;
    private final boolean mReportTimeToFirstSeekToPmet;
    private final boolean mReportToAloysius;
    private final boolean mReportToPmet;
    private final boolean mReportToQos;

    public enum Result {
        UNKNOWN,
        CANCELLED,
        FAILED,
        SUCCEEDED
    }

    public TrickplayEventReporter(@Nonnull TrickplayEventDataBuilder trickplayEventDataBuilder) {
        this.mBuilder = trickplayEventDataBuilder;
        TrickplayEventReporterConfig trickplayEventReporterConfig = TrickplayEventReporterConfig.getInstance();
        this.mReportToQos = trickplayEventReporterConfig.shouldReportToQos();
        this.mReportToPmet = trickplayEventReporterConfig.shouldReportToPmet();
        this.mReportToAloysius = trickplayEventReporterConfig.shouldReportToAloysius();
        this.mReportImageLatencyToPmet = trickplayEventReporterConfig.shouldReportImageLatencyToPmet();
        this.mReportImageFidelityToPmet = trickplayEventReporterConfig.shouldReportImageFidelityToPmet();
        this.mReportImageUpdateFailurePctToPmet = trickplayEventReporterConfig.shouldReportImageUpdateFailurePctToPmet();
        this.mReportSyeNumImageQualityFlipToPmet = trickplayEventReporterConfig.shouldReportSyeNumImageQualityFlipToPmet();
        this.mReportSyeAvgImageWidthToPmet = trickplayEventReporterConfig.shouldReportSyeAvgImageWidthToPmet();
        this.mReportScrubbingDurationToPmet = trickplayEventReporterConfig.shouldReportScrubbingDurationToPmet();
        this.mReportPlaybackPositionChangeToPmet = trickplayEventReporterConfig.shouldReportPlaybackPositionChangeToPmet();
        this.mReportTimeToFirstSeekToPmet = trickplayEventReporterConfig.shouldReportTimeToFirstSeekToPmet();
    }

    public void bindEventReporter(@Nonnull MetricEventReporter metricEventReporter) {
        this.mEventReporter = (MetricEventReporter) Preconditions.checkNotNull(metricEventReporter, "pluginReporter");
    }

    public void bindAloysiusReporters(@Nonnull PlaybackMediaEventReporters playbackMediaEventReporters) {
        this.mAloysiusDiagnosticsReporter = ((PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "aloysiusReporters")).getDiagnosticsReporter();
    }

    public void reportTrickplayUnavailable(TrickplayLoadingResponse trickplayLoadingResponse, TimeSpan timeSpan) throws IllegalStateException, NullPointerException {
        validateInputsAndState(trickplayLoadingResponse, timeSpan);
        PluginLoadStatus loadingStatus = trickplayLoadingResponse.getLoadingStatus();
        boolean z = true;
        Preconditions.checkArgument(loadingStatus.getStatus() != PluginLoadStatus.Status.LOADED);
        Result resultDetermineResult = determineResult(loadingStatus);
        Result result = Result.FAILED;
        if (resultDetermineResult != result && resultDetermineResult != Result.CANCELLED) {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected result: %s", resultDetermineResult);
        reportLoadedMetric(trickplayLoadingResponse, timeSpan, resultDetermineResult);
        if (resultDetermineResult == result) {
            reportFailure(trickplayLoadingResponse, (PluginErrorType) MoreObjects.firstNonNull(loadingStatus.getErrorType(), PluginErrorType.RUNTIME), "Trickplay manifest could not be loaded.");
        }
    }

    private void validateInputsAndState(@Nonnull TrickplayLoadingResponse trickplayLoadingResponse, @Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(trickplayLoadingResponse, "response");
        Preconditions.checkNotNull(timeSpan, "downloadTime");
        Preconditions.checkState(this.mEventReporter != null, "Event reporter must be bound first to report this event.");
    }

    @Nonnull
    private static Result determineResult(@Nonnull PluginLoadStatus pluginLoadStatus) {
        if (pluginLoadStatus.getStatus() == PluginLoadStatus.Status.ERRORED) {
            return Result.FAILED;
        }
        if (pluginLoadStatus.getStatus() == PluginLoadStatus.Status.LOADED) {
            return Result.SUCCEEDED;
        }
        if (pluginLoadStatus.getStatus() == PluginLoadStatus.Status.CANCELLED) {
            return determineCancelledResult(pluginLoadStatus);
        }
        return Result.FAILED;
    }

    @Nonnull
    private static Result determineCancelledResult(@Nonnull PluginLoadStatus pluginLoadStatus) {
        Preconditions.checkArgument(pluginLoadStatus.getStatus() == PluginLoadStatus.Status.CANCELLED);
        PluginLoadStatus.CancellationReason cancellationReason = pluginLoadStatus.getCancellationReason();
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason[cancellationReason.ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            return Result.CANCELLED;
        }
        if (i == 5) {
            return Result.FAILED;
        }
        Throwables2.propagateIfWeakMode("Trickplay", String.format(Locale.US, "Unsupported cancellation reason found: %s.", cancellationReason));
        return Result.FAILED;
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.trickplay.internal.TrickplayEventReporter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason;

        static {
            int[] iArr = new int[PluginLoadStatus.CancellationReason.values().length];
            $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason = iArr;
            try {
                iArr[PluginLoadStatus.CancellationReason.DISABLED_BY_USER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason[PluginLoadStatus.CancellationReason.DISABLED_FROM_SERVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason[PluginLoadStatus.CancellationReason.NOT_APPLICABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason[PluginLoadStatus.CancellationReason.CONTENT_UNAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$download$plugin$PluginLoadStatus$CancellationReason[PluginLoadStatus.CancellationReason.NO_DATA_CONNECTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void reportFailure(@Nonnull TrickplayLoadingResponse trickplayLoadingResponse, @Nonnull PluginErrorType pluginErrorType, @Nonnull String str) {
        String strCreateNote = this.mBuilder.createNote(trickplayLoadingResponse);
        DLog.warnf("Trickplay data failed to load: [%s (%s)]", str, strCreateNote);
        this.mEventReporter.reportPluginError("TrickPlay", pluginErrorType, str, strCreateNote);
    }

    private void reportLoadedMetric(@Nonnull TrickplayLoadingResponse trickplayLoadingResponse, @Nonnull TimeSpan timeSpan, @Nonnull Result result) {
        String strCreateNote = this.mBuilder.createNote(trickplayLoadingResponse);
        DLog.logf("Sending note: [%s (%s)]", result, strCreateNote);
        this.mEventReporter.reportPluginAvailability("TrickPlay", trickplayLoadingResponse.getLoadingStatus().getStatus() == PluginLoadStatus.Status.LOADED);
        if (result == Result.SUCCEEDED) {
            this.mEventReporter.reportPluginDownloadSuccessful("TrickPlay", timeSpan, strCreateNote);
        }
    }
}
