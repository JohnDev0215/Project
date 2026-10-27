package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class MTSAttributes {
    final String bitrateDescription;
    final String cacheHitStatus;
    final String cdnCacheHitStatus;
    final String cdnType;
    final Long durationMS;
    final Long durationS;
    final Long endOffsetMS;
    final String errorMessage;
    final long initialPlaybackDelay;
    final String initiationReason;
    final long loadTimeMilliseconds;
    final boolean localFile;
    final String mediaPlayerName;
    final String networkSource;
    final double percentageCompleted;
    final int rebufferCount;
    final Long startOffsetMS;
    final String streamOrDRMTech;
    final String substituteAsin;
    final String terminationReason;
    final long timestamp;
    final Long trackProgress;
    final Integer transferSpeedBPS;

    public MTSAttributes(long j, boolean z, Long l, Long l2, Long l3, long j2, int i, String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, long j3, double d, String str8, String str9, String str10, String str11, Long l4, Long l5) {
        this.timestamp = j;
        this.localFile = z;
        this.durationMS = l;
        this.durationS = l2;
        this.trackProgress = l3;
        this.initialPlaybackDelay = j2;
        this.rebufferCount = i;
        this.cacheHitStatus = str;
        this.terminationReason = str2;
        this.errorMessage = str3;
        this.initiationReason = str4;
        this.streamOrDRMTech = str5;
        this.transferSpeedBPS = num;
        this.networkSource = str6;
        this.bitrateDescription = str7;
        this.loadTimeMilliseconds = j3;
        this.percentageCompleted = d;
        this.mediaPlayerName = str8;
        this.cdnCacheHitStatus = str9;
        this.cdnType = str10;
        this.substituteAsin = str11;
        this.startOffsetMS = l4;
        this.endOffsetMS = l5;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean getLocalFile() {
        return this.localFile;
    }

    public Long getDurationMS() {
        return this.durationMS;
    }

    public Long getDurationS() {
        return this.durationS;
    }

    public Long getTrackProgress() {
        return this.trackProgress;
    }

    public long getInitialPlaybackDelay() {
        return this.initialPlaybackDelay;
    }

    public int getRebufferCount() {
        return this.rebufferCount;
    }

    public String getCacheHitStatus() {
        return this.cacheHitStatus;
    }

    public String getTerminationReason() {
        return this.terminationReason;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getInitiationReason() {
        return this.initiationReason;
    }

    public String getStreamOrDRMTech() {
        return this.streamOrDRMTech;
    }

    public Integer getTransferSpeedBPS() {
        return this.transferSpeedBPS;
    }

    public String getNetworkSource() {
        return this.networkSource;
    }

    public String getBitrateDescription() {
        return this.bitrateDescription;
    }

    public long getLoadTimeMilliseconds() {
        return this.loadTimeMilliseconds;
    }

    public double getPercentageCompleted() {
        return this.percentageCompleted;
    }

    public String getMediaPlayerName() {
        return this.mediaPlayerName;
    }

    public String getCdnCacheHitStatus() {
        return this.cdnCacheHitStatus;
    }

    public String getCdnType() {
        return this.cdnType;
    }

    public String getSubstituteAsin() {
        return this.substituteAsin;
    }

    public Long getStartOffsetMS() {
        return this.startOffsetMS;
    }

    public Long getEndOffsetMS() {
        return this.endOffsetMS;
    }

    public String toString() {
        return "MTSAttributes{timestamp=" + this.timestamp + ",localFile=" + this.localFile + ",durationMS=" + this.durationMS + ",durationS=" + this.durationS + ",trackProgress=" + this.trackProgress + ",initialPlaybackDelay=" + this.initialPlaybackDelay + ",rebufferCount=" + this.rebufferCount + ",cacheHitStatus=" + this.cacheHitStatus + ",terminationReason=" + this.terminationReason + ",errorMessage=" + this.errorMessage + ",initiationReason=" + this.initiationReason + ",streamOrDRMTech=" + this.streamOrDRMTech + ",transferSpeedBPS=" + this.transferSpeedBPS + ",networkSource=" + this.networkSource + ",bitrateDescription=" + this.bitrateDescription + ",loadTimeMilliseconds=" + this.loadTimeMilliseconds + ",percentageCompleted=" + this.percentageCompleted + ",mediaPlayerName=" + this.mediaPlayerName + ",cdnCacheHitStatus=" + this.cdnCacheHitStatus + ",cdnType=" + this.cdnType + ",substituteAsin=" + this.substituteAsin + ",startOffsetMS=" + this.startOffsetMS + ",endOffsetMS=" + this.endOffsetMS + "}";
    }
}
