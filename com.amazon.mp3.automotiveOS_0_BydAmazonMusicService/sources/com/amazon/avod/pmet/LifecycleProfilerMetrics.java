package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum LifecycleProfilerMetrics implements MetricParameter {
    PLSM_PREPARE("Prepare"),
    DAG_PREPARE("DAG.Prepare"),
    CONTENT_SESSION_BEGIN("CS.begin"),
    FETCH_PLAYBACK_RESOURCES("FetchPlaybackResources"),
    AVSM("AVSM"),
    CRSM("CRSM"),
    VDSM("VDSM"),
    PARSE_MANIFEST("rtwParseManifest"),
    ACQUIRE_MANIFEST("rtwAcquireManifest"),
    LICENSING("Licensing"),
    CONFIGURE_DECRYPTION("VideoRenderer.configureDecryption"),
    CONFIGURE_CODECS("VideoRenderer.configureCodecs"),
    QUEUEING_SAMPLES("QueueingSamples");

    private final String mReportableString;

    LifecycleProfilerMetrics(@Nonnull String str) {
        this.mReportableString = (String) Preconditions.checkNotNull(str, "reportableString");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mReportableString;
    }
}
