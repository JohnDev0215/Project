package com.amazon.avod.qos.metrics;

import com.amazon.avod.qos.QosReportingTag;
import com.amazon.avod.qos.internal.metrics.Metrics;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsBuilder {
    public Number adBitrate;
    public String adId;
    public Boolean asinOwned;
    public String attributionTag;
    public Number avgBandwidth;
    public Number avgBufferFullness;
    public Number avgDownloadTime;
    public Number avgFragmentBitrate;
    public Number avgFrameRate;
    public Number avgMaxBufferSize;
    public Number avgStreamingFrameRate;
    public String bitrateConsumption;
    public Number bufferCount;
    public String bufferFullnesses;
    public Number bufferSize;
    public Number bytesStreamed;
    public String cdn;
    public String chipset;
    public Number clientHeight;
    public String clientMode;
    public String clientVersion;
    public Number clientWidth;
    public String connectionType;
    public String contentType;
    public Long currentTime;
    public ImmutableMap<String, QosReportingTag<?>> customTags;
    public String deliveryType;
    public Number downloadBandwidth;
    public String downloadParameters;
    public String downloadSessionId;
    public String downloadTimes;
    public Number downshiftCount;
    public Number droppedFrameCount;
    public Number droppedFrames;
    public Number errorCount;
    public String errorMessage;
    public Number errorSeverity;
    public String errorUserAction;
    public Number eventDuration;
    public String eventSubtype;
    public Long eventTimestamp;
    public String eventType;
    public Number ffCount;
    public String firmwareVersion;
    public String fragmentBandwidths;
    public String fragments;
    public String fromAdaptationSet;
    public Number fromBitrate;
    public Number fromTimecode;
    public String language;
    public String manufacturer;
    public String maxBufferSizes;
    public Number millisecondsStreamed;
    public String modelName;
    public Number newBitrate;
    public String newUrlSetId;
    public String note;
    public String operatingSystem;
    public Number pauseCount;
    public String playbackAuthorityId;
    public Number playbackBufferFullTimeSec;
    public String playbackSettingsId;
    public String primitiveSessionId;
    public String referringUrl;
    public Number rewCount;
    public Number screenHeight;
    public Number screenWidth;
    public String serverId;
    public Long sessionEndTime;
    public Long sessionStartTime;
    public Number signalStrength;
    public String sourceEventType;
    public String sourceSystem;
    public Number stddevBandwidth;
    public Number stddevBufferFullness;
    public Number stddevDownloadTime;
    public Number stddevFragmentBitrate;
    public Number stddevFrameRate;
    public Number stddevMaxBufferSize;
    public Number streamingBitRate;
    public String streamingParameters;
    public String streamingSessionId;
    public Number timeSpentBuffering;
    public Number timeSpentPaused;
    public Boolean timedTextAvailableForTitle;
    public String titleId;
    public String toAdaptationSet;
    public Number toBitrate;
    public Number toTimecode;
    public Boolean trickPlayAvailableForTitle;
    public Number upshiftCount;
    public String url;
    public String urlSetId;
    public String userClickAction;
    public String userWatchSessionId;
    public Number videoDuration;
    public Boolean xrayEnabledForTitle;
    public Boolean xrayEnabledForUser;

    public Metrics build() {
        this.eventTimestamp = Long.valueOf(System.currentTimeMillis());
        return new Metrics(this);
    }

    public MetricsBuilder chipset(String str) {
        this.chipset = str;
        return this;
    }

    public MetricsBuilder connectionType(String str) {
        this.connectionType = str;
        return this;
    }

    public MetricsBuilder clientVersion(String str) {
        this.clientVersion = str;
        return this;
    }

    public MetricsBuilder firmwareVersion(String str) {
        this.firmwareVersion = str;
        return this;
    }

    public MetricsBuilder manufacturer(String str) {
        this.manufacturer = str;
        return this;
    }

    public MetricsBuilder modelName(String str) {
        this.modelName = str;
        return this;
    }

    public MetricsBuilder operatingSystem(String str) {
        this.operatingSystem = str;
        return this;
    }

    public MetricsBuilder sourceSystem(String str) {
        this.sourceSystem = str;
        return this;
    }

    public MetricsBuilder clientMode(String str) {
        this.clientMode = str;
        return this;
    }

    public MetricsBuilder userWatchSessionId(String str) {
        this.userWatchSessionId = str;
        return this;
    }

    public MetricsBuilder primitiveSessionId(String str) {
        this.primitiveSessionId = str;
        return this;
    }

    public MetricsBuilder urlSetId(String str) {
        if (this.urlSetId == null) {
            this.urlSetId = str;
        }
        return this;
    }

    public MetricsBuilder newUrlSetId(String str) {
        this.newUrlSetId = str;
        return this;
    }

    public MetricsBuilder referringUrl(String str) {
        this.referringUrl = str;
        return this;
    }

    public MetricsBuilder customTags(ImmutableMap<String, QosReportingTag<?>> immutableMap) {
        this.customTags = immutableMap;
        return this;
    }

    public MetricsBuilder asinOwned(Boolean bool) {
        this.asinOwned = bool;
        return this;
    }

    public MetricsBuilder downloadBandwidth(Number number) {
        this.downloadBandwidth = number;
        return this;
    }

    public MetricsBuilder avgBandwidth(Number number) {
        this.avgBandwidth = number;
        return this;
    }

    public MetricsBuilder stddevBandwidth(Number number) {
        this.stddevBandwidth = number;
        return this;
    }

    public MetricsBuilder sessionStartTime(Long l) {
        this.sessionStartTime = l;
        return this;
    }

    public MetricsBuilder sessionEndTime(Long l) {
        this.sessionEndTime = l;
        return this;
    }

    public MetricsBuilder errorCount(Number number) {
        this.errorCount = number;
        return this;
    }

    public MetricsBuilder errorMessage(String str) {
        this.errorMessage = str;
        return this;
    }

    public MetricsBuilder bufferCount(Number number) {
        this.bufferCount = number;
        return this;
    }

    public MetricsBuilder eventType(String str) {
        this.eventType = str;
        return this;
    }

    public MetricsBuilder sourceEventType(String str) {
        this.sourceEventType = str;
        return this;
    }

    public MetricsBuilder ffCount(int i) {
        this.ffCount = Integer.valueOf(i);
        return this;
    }

    public MetricsBuilder rewCount(int i) {
        this.rewCount = Integer.valueOf(i);
        return this;
    }

    public MetricsBuilder pauseCount(int i) {
        this.pauseCount = Integer.valueOf(i);
        return this;
    }

    public MetricsBuilder url(String str) {
        this.url = str;
        return this;
    }

    public MetricsBuilder playbackBufferFullTimeSec(Number number) {
        this.playbackBufferFullTimeSec = number;
        return this;
    }

    @Deprecated
    public MetricsBuilder asin(String str) {
        this.titleId = str;
        return this;
    }

    public MetricsBuilder titleId(String str) {
        this.titleId = str;
        return this;
    }

    public MetricsBuilder bytesStreamed(Number number) {
        this.bytesStreamed = number;
        return this;
    }

    public MetricsBuilder streamingBitRate(Number number) {
        this.streamingBitRate = number;
        return this;
    }

    public MetricsBuilder timeSpentBuffering(Number number) {
        this.timeSpentBuffering = number;
        return this;
    }

    public MetricsBuilder timeSpentPaused(Number number) {
        this.timeSpentPaused = number;
        return this;
    }

    public MetricsBuilder downshiftCount(Number number) {
        this.downshiftCount = number;
        return this;
    }

    public MetricsBuilder upshiftCount(Number number) {
        this.upshiftCount = number;
        return this;
    }

    public MetricsBuilder deliveryType(String str) {
        this.deliveryType = str;
        return this;
    }

    public MetricsBuilder clientWidth(Number number) {
        this.clientWidth = number;
        return this;
    }

    public MetricsBuilder clientHeight(Number number) {
        this.clientHeight = number;
        return this;
    }

    public MetricsBuilder screenResolutionWidth(Number number) {
        this.screenWidth = number;
        return this;
    }

    public MetricsBuilder screenResolutionHeight(Number number) {
        this.screenHeight = number;
        return this;
    }

    public MetricsBuilder downloadSessionId(String str) {
        this.downloadSessionId = str;
        return this;
    }

    public MetricsBuilder signalStrength(int i) {
        this.signalStrength = Integer.valueOf(i);
        return this;
    }

    public MetricsBuilder avgFrameRate(Number number) {
        this.avgFrameRate = number;
        return this;
    }

    public MetricsBuilder stddevFrameRate(Number number) {
        this.stddevFrameRate = number;
        return this;
    }

    public MetricsBuilder avgStreamingFrameRate(Number number) {
        this.avgStreamingFrameRate = number;
        return this;
    }

    public MetricsBuilder droppedFrames(Number number) {
        this.droppedFrames = number;
        return this;
    }

    public MetricsBuilder droppedFrameCount(Number number) {
        this.droppedFrameCount = number;
        return this;
    }

    public MetricsBuilder millisecondsStreamed(Number number) {
        this.millisecondsStreamed = number;
        return this;
    }

    public MetricsBuilder playbackAuthorityId(String str) {
        this.playbackAuthorityId = str;
        return this;
    }

    public MetricsBuilder streamingSessionId(String str) {
        this.streamingSessionId = str;
        return this;
    }

    public MetricsBuilder errorSeverity(Number number) {
        this.errorSeverity = number;
        return this;
    }

    public MetricsBuilder eventSubtype(String str) {
        this.eventSubtype = str;
        return this;
    }

    public MetricsBuilder eventDuration(Number number) {
        this.eventDuration = number;
        return this;
    }

    public MetricsBuilder note(String str) {
        this.note = str;
        return this;
    }

    public MetricsBuilder language(String str) {
        this.language = str;
        return this;
    }

    public MetricsBuilder fromTimecode(Number number) {
        this.fromTimecode = number;
        return this;
    }

    public MetricsBuilder serverId(String str) {
        this.serverId = str;
        return this;
    }

    public MetricsBuilder toTimecode(Number number) {
        this.toTimecode = number;
        return this;
    }

    public MetricsBuilder videoDuration(Number number) {
        this.videoDuration = number;
        return this;
    }

    public MetricsBuilder downloadParameters(String str) {
        this.downloadParameters = str;
        return this;
    }

    public MetricsBuilder streamingParameters(String str) {
        this.streamingParameters = str;
        return this;
    }

    public MetricsBuilder adBitrate(int i) {
        this.adBitrate = Integer.valueOf(i);
        return this;
    }

    @Nonnull
    public MetricsBuilder adId(@Nullable String str) {
        this.adId = str;
        return this;
    }

    public MetricsBuilder bitrateConsumption(String str) {
        this.bitrateConsumption = str;
        return this;
    }

    public MetricsBuilder downloadTimes(String str) {
        this.downloadTimes = str;
        return this;
    }

    public MetricsBuilder bufferFullnesses(String str) {
        this.bufferFullnesses = str;
        return this;
    }

    public MetricsBuilder maxBufferSizes(String str) {
        this.maxBufferSizes = str;
        return this;
    }

    public MetricsBuilder fragments(String str) {
        this.fragments = str;
        return this;
    }

    public MetricsBuilder fragmentBandwidths(String str) {
        this.fragmentBandwidths = str;
        return this;
    }

    public MetricsBuilder cdn(String str) {
        this.cdn = str;
        return this;
    }

    public MetricsBuilder avgFragmentBitrate(Number number) {
        this.avgFragmentBitrate = number;
        return this;
    }

    public MetricsBuilder stddevFragmentBitrate(Number number) {
        this.stddevFragmentBitrate = number;
        return this;
    }

    public MetricsBuilder avgBufferFullness(Number number) {
        this.avgBufferFullness = number;
        return this;
    }

    public MetricsBuilder stddevBufferFullness(Number number) {
        this.stddevBufferFullness = number;
        return this;
    }

    public MetricsBuilder avgDownloadTime(Number number) {
        this.avgDownloadTime = number;
        return this;
    }

    public MetricsBuilder stddevDownloadTime(Number number) {
        this.stddevDownloadTime = number;
        return this;
    }

    public MetricsBuilder avgMaxBufferSize(Number number) {
        this.avgMaxBufferSize = number;
        return this;
    }

    public MetricsBuilder stddevMaxBufferSize(Number number) {
        this.stddevMaxBufferSize = number;
        return this;
    }

    public MetricsBuilder attributionTag(String str) {
        this.attributionTag = str;
        return this;
    }

    public MetricsBuilder playbackSettingsId(String str) {
        this.playbackSettingsId = str;
        return this;
    }

    public MetricsBuilder toBitrate(Number number) {
        this.toBitrate = number;
        return this;
    }

    public MetricsBuilder fromBitrate(Number number) {
        this.fromBitrate = number;
        return this;
    }

    public MetricsBuilder toAdaptationSet(String str) {
        this.toAdaptationSet = str;
        return this;
    }

    public MetricsBuilder fromAdaptationSet(String str) {
        this.fromAdaptationSet = str;
        return this;
    }
}
