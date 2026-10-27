package com.amazon.avod.playback.smoothstream.diagnostics;

import android.os.Build;
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
import com.amazon.avod.util.DataUnit;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DefaultDiagnosticsTextFormatter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u001f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010#\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010$\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010%\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010&\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010'\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J(\u0010(\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/DefaultDiagnosticsTextFormatter;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticsTextFormatter;", "()V", "MIYAGI_INFO_REGEX", "Ljava/util/regex/Pattern;", "findIndex", "", "stream", "Lcom/amazon/avod/content/smoothstream/manifest/StreamIndex;", "fragmentIndex", "quality", "Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "formattedAudioStreamText", "", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "formattedBufferingCountText", "count", "formattedCdnInfoText", "formattedContentTypeText", "deviceResources", "Lcom/amazon/avod/playback/capability/DeviceResources;", "config", "Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;", "formattedDeliveryProtocolText", "context", "Lcom/amazon/avod/content/ContentSessionContext;", "formattedDeviceModelText", "manufacturer", "model", "formattedDeviceProfileNameText", "formattedDiagnosticsString", "formattedDroppedFramesText", "formattedHeuristicText", "formattedJavaHeapText", "formattedManifestUrlText", "formattedNativeHeapText", "formattedRendererDrmSchemeText", "formattedSessionTypeText", "formattedVideoStreamText", "qualityStringFormat", "bitrate", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultDiagnosticsTextFormatter implements DiagnosticsTextFormatter {
    public static final DefaultDiagnosticsTextFormatter INSTANCE = new DefaultDiagnosticsTextFormatter();
    private static final Pattern MIYAGI_INFO_REGEX;

    private DefaultDiagnosticsTextFormatter() {
    }

    static {
        Pattern patternCompile = Pattern.compile("(\\S+)--(\\S+)_\\S+");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(\"(\\\\S+)--(\\\\S+)_\\\\S+\")");
        MIYAGI_INFO_REGEX = patternCompile;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedDiagnosticsString(DiagnosticDataCollector collector, DeviceResources deviceResources, SmoothStreamingPlaybackConfig config) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        Intrinsics.checkNotNullParameter(config, "config");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        ContentSessionContext context = collector.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "collector.context");
        String MANUFACTURER = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        ContentSessionContext context2 = collector.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "collector.context");
        ContentSessionContext context3 = collector.getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "collector.context");
        ContentSessionContext context4 = collector.getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "collector.context");
        String str = String.format(locale, "Diagnostics: %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s", Arrays.copyOf(new Object[]{formattedDeliveryProtocolText(context), formattedVideoStreamText(collector, deviceResources), formattedAudioStreamText(collector), formattedBufferingCountText(collector.getBufferingCount()), formattedJavaHeapText(deviceResources), formattedNativeHeapText(deviceResources), formattedDroppedFramesText(collector), formattedRendererDrmSchemeText(collector), formattedDeviceProfileNameText(deviceResources), formattedDeviceModelText(MANUFACTURER, MODEL), formattedSessionTypeText(context2), formattedCdnInfoText(collector), formattedContentTypeText(deviceResources, config), formattedHeuristicText(context3, deviceResources), formattedManifestUrlText(context4)}, 15));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedContentTypeText(DeviceResources deviceResources, SmoothStreamingPlaybackConfig config) {
        String strName;
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        Intrinsics.checkNotNullParameter(config, "config");
        if (ContentType.isLive(deviceResources.getContentType())) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            strName = String.format(Locale.US, "%s (DBL: %d, MinUpdPeriod: manifest %d /config %d, RefreshForLegacy: %s)", Arrays.copyOf(new Object[]{deviceResources.getContentType().name(), Integer.valueOf(deviceResources.getDelayBehindLiveHeadInSeconds()), Integer.valueOf(deviceResources.getMinimumUpdatePeriodInSeconds()), Integer.valueOf(TimeSpan.fromMilliseconds(config.getMinUpdatePeriodToEnableManifestRefreshThresholdMillis()).getTotalSeconds()), String.valueOf(config.getMultiPeriodManifestSupportForLegacyLiveStreamsEnabled())}, 5));
            Intrinsics.checkNotNullExpressionValue(strName, "format(locale, format, *args)");
        } else {
            strName = deviceResources.getContentType().name();
        }
        return Intrinsics.stringPlus("Content Type: ", strName);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedSessionTypeText(ContentSessionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.stringPlus("Session Type: ", context.getSessionType());
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedDeliveryProtocolText(ContentSessionContext context) {
        ContentUrl currentContentUrl;
        String url;
        Intrinsics.checkNotNullParameter(context, "context");
        ContentUrlSelector contentUrlSelector = context.getContentUrlSelector();
        String str = "";
        if (contentUrlSelector != null && (currentContentUrl = contentUrlSelector.getCurrentContentUrl()) != null && (url = currentContentUrl.getUrl()) != null) {
            str = url;
        }
        return Intrinsics.stringPlus("Delivery Protocol: ", ContentUrl.isDashUrl(str) ? "dash" : "smooth");
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedBufferingCountText(int count) {
        return Intrinsics.stringPlus("Buffering Count: ", Integer.valueOf(count));
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedCdnInfoText(DiagnosticDataCollector collector) {
        ContentUrl currentContentUrl;
        String cdnName;
        String strValueOf;
        Intrinsics.checkNotNullParameter(collector, "collector");
        ContentSessionContext context = collector.getContext();
        TreeSet<DiagnosticDataPoint> diagnosticDataPoints = collector.getDiagnosticDataPoints();
        TreeSet<CdnSwitchedDataPoint> cdnSwitchedDataPoints = collector.getCdnSwitchedDataPoints();
        String newCdn = "N/A";
        if (cdnSwitchedDataPoints.isEmpty()) {
            ContentUrlSelector contentUrlSelector = context.getContentUrlSelector();
            if (contentUrlSelector != null && (currentContentUrl = contentUrlSelector.getCurrentContentUrl()) != null && (cdnName = currentContentUrl.getCdnName()) != null) {
                newCdn = cdnName;
            }
        } else {
            newCdn = cdnSwitchedDataPoints.last().getNewCdn();
            Intrinsics.checkNotNullExpressionValue(newCdn, "cdnSwitchedDataPoints.last().newCdn");
        }
        String strValueOf2 = "-";
        if (diagnosticDataPoints.isEmpty()) {
            strValueOf = "-";
        } else {
            DiagnosticDataPoint diagnosticDataPointLast = diagnosticDataPoints.last();
            strValueOf2 = String.valueOf((long) DataUnit.BITS.toKiloBits(diagnosticDataPointLast.getDownloadRate()));
            strValueOf = String.valueOf(TimeUnit.NANOSECONDS.toMillis(diagnosticDataPointLast.getLatencyInNanos()));
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "CDN: %s (%s Kbps, %s ms)", Arrays.copyOf(new Object[]{newCdn, strValueOf2, strValueOf}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedDeviceModelText(String manufacturer, String model) {
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(model, "model");
        return "Device Model: " + manufacturer + ' ' + model;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedDeviceProfileNameText(DeviceResources deviceResources) {
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        String deviceProfileName = deviceResources.getDeviceProfileName();
        if (deviceProfileName == null) {
            deviceProfileName = "";
        }
        return Intrinsics.stringPlus("Device Profile Name: ", deviceProfileName);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedJavaHeapText(DeviceResources deviceResources) {
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "Java heap: %.2f MB / %.2f MB", Arrays.copyOf(new Object[]{Float.valueOf(deviceResources.getCurrentApplicationMemoryUsageInMB()), Float.valueOf(deviceResources.getApplicationMaxHeapSizeMaxHeapSizeInMB())}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedNativeHeapText(DeviceResources deviceResources) {
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        DataUnit dataUnit = DataUnit.BYTES;
        String str = String.format(locale, "Native heap: %.2f MB / %.2f MB", Arrays.copyOf(new Object[]{Float.valueOf(dataUnit.toMegaBytes(deviceResources.getNativeHeapUsedSizeInBytes())), Float.valueOf(dataUnit.toMegaBytes(deviceResources.getNativeHeapMaxSizeInBytes()))}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedManifestUrlText(ContentSessionContext context) {
        ContentUrl currentContentUrl;
        String url;
        Intrinsics.checkNotNullParameter(context, "context");
        ContentUrlSelector contentUrlSelector = context.getContentUrlSelector();
        String str = "";
        if (contentUrlSelector != null && (currentContentUrl = contentUrlSelector.getCurrentContentUrl()) != null && (url = currentContentUrl.getUrl()) != null) {
            str = url;
        }
        return Intrinsics.stringPlus("Manifest URL: ", str);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedDroppedFramesText(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "Frames Dropped: %d", Arrays.copyOf(new Object[]{Integer.valueOf(collector.getTotalDecoderDroppedCount() + collector.getTotalRendererDroppedCount())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedRendererDrmSchemeText(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        String rendererScheme = collector.getRendererScheme();
        if (rendererScheme == null) {
            rendererScheme = "";
        }
        String drmScheme = collector.getDrmScheme();
        String str = drmScheme != null ? drmScheme : "";
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format(Locale.US, "Renderer Scheme: %s | Drm Scheme: %s", Arrays.copyOf(new Object[]{rendererScheme, str}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
        return str2;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedHeuristicText(ContentSessionContext context, DeviceResources deviceResources) {
        String settingsId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        Heuristics heuristics = context.getHeuristics();
        String str = (heuristics == null || (settingsId = heuristics.getSettingsId()) == null) ? "NA" : settingsId;
        Matcher matcher = MIYAGI_INFO_REGEX.matcher(str);
        String strGroup = (String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Attributes.PREDEFINED_ATTRIBUTE_PREFIX}, false, 0, 6, (Object) null).get(0);
        String str2 = "Legacy";
        if (matcher.find()) {
            strGroup = matcher.group(1);
            if (strGroup == null) {
                strGroup = "";
            }
            if (matcher.groupCount() > 1 && !Strings.isNullOrEmpty(matcher.group(2))) {
                String strGroup2 = matcher.group(2);
                str2 = strGroup2 == null ? "" : strGroup2;
            }
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale US = Locale.US;
        String timeoutStrategy = deviceResources.getTimeoutStrategy();
        Intrinsics.checkNotNullExpressionValue(timeoutStrategy, "deviceResources.timeoutStrategy");
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = timeoutStrategy.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String reviewProgressStrategy = deviceResources.getReviewProgressStrategy();
        Intrinsics.checkNotNullExpressionValue(reviewProgressStrategy, "deviceResources.reviewProgressStrategy");
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase2 = reviewProgressStrategy.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
        String str3 = String.format(US, "Heuristics: %s, Miyagi: %s, Cluster: %s, Timeout: %s, Review: %s", Arrays.copyOf(new Object[]{deviceResources.getHeuristicAlgorithm(), strGroup, str2, lowerCase, lowerCase2}, 5));
        Intrinsics.checkNotNullExpressionValue(str3, "format(locale, format, *args)");
        return str3;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedAudioStreamText(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        ContentSessionContext context = collector.getContext();
        StreamIndex audioStream = context.getStreamSelections().getAudioStream();
        Intrinsics.checkNotNullExpressionValue(audioStream, "context.streamSelections.audioStream");
        int lastAudioBitrate = collector.getLastAudioBitrate();
        int consumptionHead = context.getState().getConsumptionHead(audioStream.getIndex());
        QualityLevel qualityLevelGreaterThanEqual = audioStream.getQualityLevelGreaterThanEqual(context.getState().getConsumptionHead(audioStream.getIndex()), lastAudioBitrate);
        Intrinsics.checkNotNullExpressionValue(qualityLevelGreaterThanEqual, "audioStream.getQualityLe…eam.index), audioBitrate)");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "A: %s", Arrays.copyOf(new Object[]{qualityStringFormat(qualityLevelGreaterThanEqual, audioStream, consumptionHead, lastAudioBitrate)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsTextFormatter
    public String formattedVideoStreamText(DiagnosticDataCollector collector, DeviceResources deviceResources) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        Intrinsics.checkNotNullParameter(deviceResources, "deviceResources");
        ContentSessionContext context = collector.getContext();
        StreamIndex videoStream = context.getStreamSelections().getVideoStream();
        Intrinsics.checkNotNullExpressionValue(videoStream, "context.streamSelections.videoStream");
        int index = videoStream.getIndex();
        int lastVideoBitrate = collector.getLastVideoBitrate();
        int consumptionHead = context.getState().getConsumptionHead(index);
        double framesPerSecond = collector.getFramesPerSecond();
        QualityLevel qualityLevelGreaterThanEqual = videoStream.getQualityLevelGreaterThanEqual(context.getState().getConsumptionHead(index), lastVideoBitrate);
        VideoQualityLevel videoQualityLevel = qualityLevelGreaterThanEqual instanceof VideoQualityLevel ? (VideoQualityLevel) qualityLevelGreaterThanEqual : null;
        if (videoQualityLevel == null) {
            return "V: Invalid Quality";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Object[] objArr = new Object[6];
        objArr[0] = qualityStringFormat(videoQualityLevel, videoStream, consumptionHead, lastVideoBitrate);
        objArr[1] = Integer.valueOf(videoQualityLevel.getMaxWidth());
        objArr[2] = Integer.valueOf(videoQualityLevel.getMaxHeight());
        objArr[3] = videoStream.isHdr() ? "HDR" : "SDR";
        objArr[4] = Double.valueOf(framesPerSecond);
        objArr[5] = deviceResources.getTunneledPlaybackInfo();
        String str = String.format(locale, "V: %s %dx%d %s %.3f fps %s", Arrays.copyOf(objArr, 6));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    private final String qualityStringFormat(QualityLevel quality, StreamIndex stream, int fragmentIndex, int bitrate) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%s %d kbps (%d/%d)", Arrays.copyOf(new Object[]{quality.getFourCC(), Integer.valueOf(bitrate / 1000), Integer.valueOf(findIndex(stream, fragmentIndex, quality)), Integer.valueOf(stream.getSortedQualityLevels(fragmentIndex).length)}, 4));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return str;
    }

    private final int findIndex(StreamIndex stream, int fragmentIndex, QualityLevel quality) {
        int length = stream.getSortedQualityLevels(fragmentIndex).length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            if (quality == stream.getSortedQualityLevels(fragmentIndex)[i]) {
                return i2;
            }
            i = i2;
        }
        return -1;
    }
}
