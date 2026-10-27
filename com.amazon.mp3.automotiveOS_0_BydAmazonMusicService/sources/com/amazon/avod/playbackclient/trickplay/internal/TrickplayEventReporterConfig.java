package com.amazon.avod.playbackclient.trickplay.internal;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class TrickplayEventReporterConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mReportLiveTrickplayScrubbingEvents;
    private final ConfigurationValue<Boolean> mReportSyeTrickplayAvgImageWidthToPmet;
    private final ConfigurationValue<Boolean> mReportSyeTrickplayNumImageQualityFlipToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayEventsToAloysius;
    private final ConfigurationValue<Boolean> mReportTrickplayEventsToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayEventsToQos;
    private final ConfigurationValue<Boolean> mReportTrickplayImageFidelityToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayImageLatencyToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayImageUpdateFailurePctToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayPlaybackPositionChangeToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayScrubbingDurationToPmet;
    private final ConfigurationValue<Boolean> mReportTrickplayTimeToFirstSeekToPmet;
    private final ConfigurationValue<Boolean> mReportVODTrickplayScrubbingEvents;

    public static TrickplayEventReporterConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final TrickplayEventReporterConfig INSTANCE = new TrickplayEventReporterConfig();

        private SingletonHolder() {
        }
    }

    private TrickplayEventReporterConfig() {
        this.mReportLiveTrickplayScrubbingEvents = newBooleanConfigValue("playback_reportLiveTrickplayScrubbingEvents", true);
        this.mReportVODTrickplayScrubbingEvents = newBooleanConfigValue("playback_reportVODTrickplayScrubbingEvents", false);
        this.mReportTrickplayEventsToQos = newBooleanConfigValue("playback_reportTrickplayEventsToQos", true);
        this.mReportTrickplayEventsToPmet = newBooleanConfigValue("playback_reportTrickplayEventsToPmet", true);
        this.mReportTrickplayEventsToAloysius = newBooleanConfigValue("playback_reportTrickplayEventsToAloysius", true);
        this.mReportTrickplayImageLatencyToPmet = newBooleanConfigValue("playback_reportTrickplayImageLatencyToPmet", true);
        this.mReportTrickplayImageFidelityToPmet = newBooleanConfigValue("playback_reportTrickplayImageFidelityToPmet", true);
        this.mReportTrickplayImageUpdateFailurePctToPmet = newBooleanConfigValue("playback_reportTrickplayImageUpdateFailurePctToPmet", true);
        this.mReportSyeTrickplayNumImageQualityFlipToPmet = newBooleanConfigValue("playback_reportSyeTrickplayNumImageQualityFlipToPmet", true);
        this.mReportSyeTrickplayAvgImageWidthToPmet = newBooleanConfigValue("playback_reportSyeTrickplayAvgImageWidthToPmet", true);
        this.mReportTrickplayScrubbingDurationToPmet = newBooleanConfigValue("playback_reportTrickplayScrubbingDurationToPmet", true);
        this.mReportTrickplayTimeToFirstSeekToPmet = newBooleanConfigValue("playback_reportTrickplayTimeToFirstSeekToPmet", true);
        this.mReportTrickplayPlaybackPositionChangeToPmet = newBooleanConfigValue("playback_reportTrickplayPlaybackPositionChangeToPmet", true);
    }

    public boolean shouldReportToQos() {
        return this.mReportTrickplayEventsToQos.getValue().booleanValue();
    }

    public boolean shouldReportToPmet() {
        return this.mReportTrickplayEventsToPmet.getValue().booleanValue();
    }

    public boolean shouldReportToAloysius() {
        return this.mReportTrickplayEventsToAloysius.getValue().booleanValue();
    }

    public boolean shouldReportImageLatencyToPmet() {
        return this.mReportTrickplayImageLatencyToPmet.getValue().booleanValue();
    }

    public boolean shouldReportImageFidelityToPmet() {
        return this.mReportTrickplayImageFidelityToPmet.getValue().booleanValue();
    }

    public boolean shouldReportImageUpdateFailurePctToPmet() {
        return this.mReportTrickplayImageUpdateFailurePctToPmet.getValue().booleanValue();
    }

    public boolean shouldReportSyeNumImageQualityFlipToPmet() {
        return this.mReportSyeTrickplayNumImageQualityFlipToPmet.getValue().booleanValue();
    }

    public boolean shouldReportSyeAvgImageWidthToPmet() {
        return this.mReportSyeTrickplayAvgImageWidthToPmet.getValue().booleanValue();
    }

    public boolean shouldReportScrubbingDurationToPmet() {
        return this.mReportTrickplayScrubbingDurationToPmet.getValue().booleanValue();
    }

    public boolean shouldReportPlaybackPositionChangeToPmet() {
        return this.mReportTrickplayPlaybackPositionChangeToPmet.getValue().booleanValue();
    }

    public boolean shouldReportTimeToFirstSeekToPmet() {
        return this.mReportTrickplayTimeToFirstSeekToPmet.getValue().booleanValue();
    }
}
