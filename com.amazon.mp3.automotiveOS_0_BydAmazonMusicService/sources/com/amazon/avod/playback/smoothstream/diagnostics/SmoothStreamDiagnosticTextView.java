package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DataUnit;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SmoothStreamDiagnosticTextView extends TextView implements DiagnosticUpdateListener {
    private static final Pattern MIYAGI_INFO_REGEX = Pattern.compile("(\\S+)--(\\S+)_\\S+");
    private final SmoothStreamingPlaybackConfig mConfig;
    private final DeviceResources mDeviceResources;

    public SmoothStreamDiagnosticTextView(Context context) {
        this(context, DeviceResources.getInstance(), SmoothStreamingPlaybackConfig.INSTANCE);
    }

    SmoothStreamDiagnosticTextView(@Nonnull Context context, @Nonnull DeviceResources deviceResources, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        super((Context) Preconditions.checkNotNull(context, "context"));
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        setTextColor(-1);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setAlpha(0.5f);
        setTextSize(11.0f);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        Preconditions.checkNotNull(diagnosticDataCollector, "collector");
        updateScreenDebugMessage(diagnosticDataCollector);
    }

    private void updateScreenDebugMessage(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        String cdnName;
        ContentSessionContext context = diagnosticDataCollector.getContext();
        String str = ContentUrl.isDashUrl(context.getContentUrl()) ? "dash" : "smooth";
        if (!diagnosticDataCollector.getCdnSwitchedDataPoints().isEmpty()) {
            cdnName = diagnosticDataCollector.getCdnSwitchedDataPoints().last().getNewCdn();
        } else {
            cdnName = context.getContentUrl().getCdnName();
        }
        ContentUrlSelector contentUrlSelector = context.getContentUrlSelector();
        String url = contentUrlSelector == null ? "" : contentUrlSelector.getCurrentContentUrl().getUrl();
        StreamIndex videoStream = context.getStreamSelections().getVideoStream();
        StreamIndex audioStream = context.getStreamSelections().getAudioStream();
        int lastVideoBitrate = diagnosticDataCollector.getLastVideoBitrate();
        int lastAudioBitrate = diagnosticDataCollector.getLastAudioBitrate();
        setText(new TextBuilder(" | ").append(context.getContent()).append(context.getSessionType()).append(getCdnInfo(cdnName, diagnosticDataCollector)).append(str).append(getVideoStringFormat(videoStream.getQualityLevelGreaterThanEqual(context.getState().getConsumptionHead(videoStream.getIndex()), lastVideoBitrate), videoStream, context.getState().getConsumptionHead(videoStream.getIndex()), lastVideoBitrate, diagnosticDataCollector.getFramesPerSecond(), this.mDeviceResources)).append(getAudioStringFormat(audioStream.getQualityLevelGreaterThanEqual(context.getState().getConsumptionHead(audioStream.getIndex()), lastAudioBitrate), audioStream, context.getState().getConsumptionHead(audioStream.getIndex()), lastAudioBitrate)).append(getBufferingStringFormat(diagnosticDataCollector.getBufferingCount())).append(getMemoryStringFormat()).append(getNativeMemoryStringFormat()).append(getFrameDropMetrics(diagnosticDataCollector)).append(getRendererDrmSchemeString(diagnosticDataCollector)).append(getContentRelatedInfo(diagnosticDataCollector)).append(getBetaStatusString()).append(appendProfileName(this.mDeviceResources.getDeviceProfileName())).append(getHeuristicInfo(context, this.mDeviceResources)).append(getDeviceCanonicalModel()).append(url).toString());
        invalidate();
    }

    @Nonnull
    private String getCdnInfo(String str, @Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        TreeSet<DiagnosticDataPoint> diagnosticDataPoints = diagnosticDataCollector.getDiagnosticDataPoints();
        if (diagnosticDataPoints.isEmpty()) {
            return String.format(Locale.US, " %s (- Kbps, - ms)", str);
        }
        return String.format(Locale.US, " %s (%s Kbps, %s ms)", str, Long.valueOf((long) DataUnit.BITS.toKiloBits(r9.getDownloadRate())), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(diagnosticDataPoints.last().getLatencyInNanos())));
    }

    static String appendProfileName(@Nullable String str) {
        return !Strings.isNullOrEmpty(str) ? String.format(Locale.US, "| %s", str) : "";
    }

    @Nonnull
    private String getDeviceCanonicalModel() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    @Nonnull
    static String getHeuristicInfo(@Nonnull ContentSessionContext contentSessionContext, @Nonnull DeviceResources deviceResources) {
        String strGroup;
        Preconditions.checkNotNull(deviceResources, "deviceResources");
        Heuristics heuristics = ((ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "context")).getHeuristics();
        String settingsId = heuristics != null ? heuristics.getSettingsId() : "NA";
        Matcher matcher = MIYAGI_INFO_REGEX.matcher(settingsId);
        String strGroup2 = "Legacy";
        if (matcher.find()) {
            strGroup = matcher.group(1);
            if (matcher.groupCount() > 1 && !Strings.isNullOrEmpty(matcher.group(2))) {
                strGroup2 = matcher.group(2);
            }
        } else {
            strGroup = settingsId.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)[0];
        }
        TextBuilder textBuilder = new TextBuilder(", ");
        Locale locale = Locale.US;
        return textBuilder.append(String.format(locale, "Heuristics: %s", deviceResources.getHeuristicAlgorithm())).append(String.format(locale, "Miyagi: %s", strGroup)).append(String.format(locale, "Cluster: %s", strGroup2)).append(String.format(locale, "Timeout: %s", deviceResources.getTimeoutStrategy().toLowerCase(locale))).append(String.format(locale, "Review: %s", deviceResources.getReviewProgressStrategy().toLowerCase(locale))).toString();
    }

    @Nonnull
    private String getContentRelatedInfo(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        if (ContentType.isLive(this.mDeviceResources.getContentType())) {
            return String.format(Locale.US, "%s (DBL: %d, MinUpdPeriod: manifest %d /config %d, RefreshForLegacy: %s)", this.mDeviceResources.getContentType().name(), Integer.valueOf(this.mDeviceResources.getDelayBehindLiveHeadInSeconds()), Integer.valueOf(this.mDeviceResources.getMinimumUpdatePeriodInSeconds()), Integer.valueOf(TimeSpan.fromMilliseconds(this.mConfig.getMinUpdatePeriodToEnableManifestRefreshThresholdMillis()).getTotalSeconds()), Boolean.toString(this.mConfig.getMultiPeriodManifestSupportForLegacyLiveStreamsEnabled()));
        }
        return this.mDeviceResources.getContentType().name();
    }

    @Nonnull
    private static String getRendererDrmSchemeString(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        String rendererScheme = diagnosticDataCollector.getRendererScheme();
        String drmScheme = diagnosticDataCollector.getDrmScheme();
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        if (rendererScheme == null) {
            rendererScheme = "";
        }
        objArr[0] = rendererScheme;
        if (drmScheme == null) {
            drmScheme = "";
        }
        objArr[1] = drmScheme;
        return String.format(locale, "Renderer Scheme: %s | Drm Scheme: %s", objArr);
    }

    private static String getBufferingStringFormat(int i) {
        return String.format(Locale.US, "Buffering count: %s", Integer.valueOf(i));
    }

    private static String getVideoStringFormat(QualityLevel qualityLevel, StreamIndex streamIndex, int i, int i2, double d, @Nonnull DeviceResources deviceResources) {
        VideoQualityLevel videoQualityLevel = (VideoQualityLevel) CastUtils.castTo(streamIndex.getQualityLevelGreaterThanEqual(i, i2), VideoQualityLevel.class);
        if (videoQualityLevel == null) {
            return "V: Invalid quality";
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[6];
        objArr[0] = getQualityStringFormat(qualityLevel, streamIndex, i, i2);
        objArr[1] = Integer.valueOf(videoQualityLevel.getMaxWidth());
        objArr[2] = Integer.valueOf(videoQualityLevel.getMaxHeight());
        objArr[3] = streamIndex.isHdr() ? "HDR" : "SDR";
        objArr[4] = Double.valueOf(d);
        objArr[5] = deviceResources.getTunneledPlaybackInfo();
        return String.format(locale, "V: %s %dx%d %s %.3f fps %s", objArr);
    }

    private static String getAudioStringFormat(QualityLevel qualityLevel, StreamIndex streamIndex, int i, int i2) {
        return String.format(Locale.US, "A: %s", getQualityStringFormat(qualityLevel, streamIndex, i, i2));
    }

    private static String getQualityStringFormat(QualityLevel qualityLevel, StreamIndex streamIndex, int i, int i2) {
        return String.format(Locale.US, "%s %d kbps (%d/%d)", qualityLevel.getFourCC(), Integer.valueOf(i2 / 1000), Integer.valueOf(findIndex(streamIndex, i, qualityLevel)), Integer.valueOf(streamIndex.getSortedQualityLevels(i).length));
    }

    private static int findIndex(StreamIndex streamIndex, int i, QualityLevel qualityLevel) {
        for (int i2 = 0; i2 < streamIndex.getSortedQualityLevels(i).length; i2++) {
            if (qualityLevel == streamIndex.getSortedQualityLevels(i)[i2]) {
                return i2 + 1;
            }
        }
        return -1;
    }

    public String getMemoryStringFormat() {
        return String.format(Locale.US, "Java heap: %.2f MB / %.2f MB", Float.valueOf(this.mDeviceResources.getCurrentApplicationMemoryUsageInMB()), Float.valueOf(this.mDeviceResources.getApplicationMaxHeapSizeMaxHeapSizeInMB()));
    }

    public String getNativeMemoryStringFormat() {
        Locale locale = Locale.US;
        DataUnit dataUnit = DataUnit.BYTES;
        return String.format(locale, "Native heap: %.2f MB / %.2f MB", Float.valueOf(dataUnit.toMegaBytes(this.mDeviceResources.getNativeHeapUsedSizeInBytes())), Float.valueOf(dataUnit.toMegaBytes(this.mDeviceResources.getNativeHeapMaxSizeInBytes())));
    }

    public String getBetaStatusString() {
        return "Beta Status: " + BetaConfigProvider.getInstance().provideBetaConfig().isInternalBeta();
    }

    public String getFrameDropMetrics(DiagnosticDataCollector diagnosticDataCollector) {
        return String.format(Locale.US, "Frames Dropped: %d", Integer.valueOf(diagnosticDataCollector.getTotalDecoderDroppedCount() + diagnosticDataCollector.getTotalRendererDroppedCount()));
    }

    private static class TextBuilder {
        private final String mJoiner;
        private final StringBuilder sb = new StringBuilder();

        TextBuilder(@Nonnull String str) {
            this.mJoiner = (String) Preconditions.checkNotNull(str, "joiner");
        }

        @Nonnull
        TextBuilder append(@Nullable Object obj) {
            String string = obj != null ? obj.toString() : null;
            if (!Strings.isNullOrEmpty(string)) {
                StringBuilder sb = this.sb;
                sb.append(string);
                sb.append(this.mJoiner);
            }
            return this;
        }

        @Nonnull
        public String toString() {
            return this.sb.toString();
        }
    }
}
