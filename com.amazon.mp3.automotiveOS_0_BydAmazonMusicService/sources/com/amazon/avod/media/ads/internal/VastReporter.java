package com.amazon.avod.media.ads.internal;

import com.amazon.avod.content.event.AuxCacheKeyEvent;
import com.amazon.avod.content.event.ManifestFetchedEvent;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDiagnosticsReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.event.AdPlanFetchedEvent;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.ServerInsertedStreamPmetMetrics;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastReporter {

    @Nullable
    private final AloysiusDiagnosticsReporter mAloysiusDiagnosticsReporter;
    private Boolean mHasAdPlan;
    private Boolean mHasAuxKey;
    private Boolean mHasStitchedManifest;
    private final boolean mIsSSAIEnabled;
    private final boolean mIsVastReportingEnabled;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    private final PlaybackEventReporter mReporter;

    public VastReporter(boolean z, boolean z2, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mIsSSAIEnabled = z;
        this.mIsVastReportingEnabled = z2;
        this.mReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "reporter");
        this.mAloysiusDiagnosticsReporter = aloysiusDiagnosticsReporter;
        this.mPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "PmetMetricReporter");
    }

    @Subscribe
    public void handleManifestFetchedEvent(@Nonnull ManifestFetchedEvent manifestFetchedEvent) {
        this.mHasStitchedManifest = Boolean.valueOf(manifestFetchedEvent.getManifest().hasStitchedAds());
        checkVastState();
    }

    @Subscribe
    public void handleAuxCacheKeyEvent(@Nonnull AuxCacheKeyEvent auxCacheKeyEvent) {
        this.mHasAuxKey = Boolean.valueOf(auxCacheKeyEvent.hasAuxCacheKey());
        checkVastState();
    }

    @Subscribe
    public void handleAdPlanFetchedEvent(@Nonnull AdPlanFetchedEvent adPlanFetchedEvent) {
        this.mHasAdPlan = Boolean.valueOf(!adPlanFetchedEvent.getAdPlan().getBreaks().isEmpty());
        checkVastState();
    }

    private void checkVastState() {
        Boolean bool;
        Boolean bool2 = this.mHasAdPlan;
        if (bool2 == null || (bool = this.mHasStitchedManifest) == null || this.mHasAuxKey == null || !this.mIsVastReportingEnabled) {
            return;
        }
        if (!bool2.equals(bool) || !this.mHasAdPlan.equals(this.mHasAuxKey)) {
            this.mReporter.reportError("ServerInsertedAdMismatch", "VastMismatch", null);
            this.mPmetMetricReporter.reportServerInsertedStreamMetrics(ServerInsertedStreamPmetMetrics.VAST_MISMATCH);
            AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter = this.mAloysiusDiagnosticsReporter;
            if (aloysiusDiagnosticsReporter != null) {
                aloysiusDiagnosticsReporter.handleDiagnosticsEvent(new AloysiusDiagnosticEvent("VastMismatch", "VastMismatch", AloysiusDiagnosticsState.Discrete));
            }
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        objArr[0] = this.mIsSSAIEnabled ? "ssai" : "csai";
        objArr[1] = this.mHasAdPlan.booleanValue() ? "ads_enabled" : "ads_disabled";
        objArr[2] = this.mHasAuxKey.booleanValue() ? "aux_key_enabled" : "aux_key_disabled";
        objArr[3] = this.mHasStitchedManifest.booleanValue() ? "manifest_stitched" : "manifest_unstitched";
        String str = String.format(locale, "%s_%s_%s_%s", objArr);
        this.mReporter.reportMetric("AdEvent", "AdSessionMetaData", null, str, null);
        this.mPmetMetricReporter.reportServerInsertedStreamMetrics(this.mIsSSAIEnabled ? ServerInsertedStreamPmetMetrics.SSAI : ServerInsertedStreamPmetMetrics.CSAI);
        AloysiusDiagnosticsReporter aloysiusDiagnosticsReporter2 = this.mAloysiusDiagnosticsReporter;
        if (aloysiusDiagnosticsReporter2 != null) {
            aloysiusDiagnosticsReporter2.handleDiagnosticsEvent(new AloysiusDiagnosticEvent("AdSessionMetadata", str, AloysiusDiagnosticsState.Discrete));
        }
    }
}
