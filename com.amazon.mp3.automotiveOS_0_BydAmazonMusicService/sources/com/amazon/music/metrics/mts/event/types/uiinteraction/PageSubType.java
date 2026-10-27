package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum PageSubType implements MetricValueType {
    MATCH_PAST("MATCH.PAST"),
    MATCH_LIVE("MATCH.LIVE"),
    MATCH_FUTURE("MATCH.FUTURE"),
    PREGAME_LIVE("PREGAME.LIVE"),
    PREGAME_FUTURE("PREGAME.FUTURE"),
    HIGHLIGHTS_AVAILABLE("HIGHLIGHTS.AVAILABLE"),
    UNAVAILABLE_UNAVAILABLE("UNAVAILABLE.UNAVAILABLE"),
    CONFERENCE_FUTURE("CONFERENCE.FUTURE"),
    CONFERENCE_LIVE("CONFERENCE.LIVE"),
    CONFERENCE_PAST("CONFERENCE.PAST"),
    LIVE_AUDIO("liveAudio"),
    ONDEMAND_AUDIO("onDemandAudio"),
    ONDEMAND_VIDEO("onDemandVideo"),
    SELF_PROFILE("selfView"),
    PEER_PROFILE("peerView"),
    LIBRARY_ONLINE_MODE("library.online"),
    LIBRARY_OFFLINE_MODE("library.offline"),
    LIVE_EVENT_UPCOMING("liveEvent.upcoming"),
    LIVE_EVENT_LIVE("liveEvent.live"),
    LIVE_EVENT_BETWEEN("liveEvent.between"),
    LIVE_EVENT_AFTERGLOW("liveEvent.afterglow"),
    LEGAL_INFORMATION("legalInformation"),
    ON_DEMAND_PLAYLIST("onDemandPlaylist"),
    AUGMENTED_SHUFFLE_PLAYLIST("augmentedShufflePlaylist"),
    PERSISTENT_UPGRADE_DM_BLOCKING("persistentUpgrade.blockingDM"),
    PERSISTENT_UPGRADE_DM_NON_BLOCKING("persistentUpgrade.nonBlockingDM"),
    PERSISTENT_UPGRADE_PLAN_COMPARISON("persistentUpgrade.planComparison");

    private final String mMetricValue;

    PageSubType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
