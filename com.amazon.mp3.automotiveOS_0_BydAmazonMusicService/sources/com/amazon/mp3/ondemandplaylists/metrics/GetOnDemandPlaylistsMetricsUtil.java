package com.amazon.mp3.ondemandplaylists.metrics;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetOnDemandPlaylistsMetricsUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/metrics/GetOnDemandPlaylistsMetricsUtil;", "", "Lcom/amazon/mp3/ondemandplaylists/metrics/GetOnDemandPlaylistsMetricsUtil$EventType;", "eventType", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "", "trackFailureEvent", "trackEvent", "<init>", "()V", "EventType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class GetOnDemandPlaylistsMetricsUtil {
    public static final GetOnDemandPlaylistsMetricsUtil INSTANCE = new GetOnDemandPlaylistsMetricsUtil();

    private GetOnDemandPlaylistsMetricsUtil() {
    }

    public static /* synthetic */ void trackFailureEvent$default(GetOnDemandPlaylistsMetricsUtil getOnDemandPlaylistsMetricsUtil, EventType eventType, Exception exc, int i, Object obj) {
        if ((i & 2) != 0) {
            exc = null;
        }
        getOnDemandPlaylistsMetricsUtil.trackFailureEvent(eventType, exc);
    }

    public final void trackFailureEvent(EventType eventType, Exception e) {
        Throwable cause;
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        String message = null;
        FlexEvent.Builder builderWithFlexStr2 = FlexEvent.builder("GetOnDemandPlaylistsServiceFailure").withFlexStr1(eventType.getValue()).withFlexStr2(e == null ? null : e.getMessage());
        if (e != null && (cause = e.getCause()) != null) {
            message = cause.getMessage();
        }
        FlexEvent flexEventBuild = builderWithFlexStr2.withFlexStr3(message).withEmitterId("em_200").build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(EVENT_NAME_FAILU…_ID)\n            .build()");
        MetricsHolder.getManager().handleEvent(flexEventBuild);
    }

    public final void trackEvent(EventType eventType) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        FlexEvent flexEventBuild = FlexEvent.builder("GetOnDemandPlaylistsServiceOperation").withFlexStr1(eventType.getValue()).withEmitterId("em_200").build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(EVENT_NAME_DEFAU…_ID)\n            .build()");
        MetricsHolder.getManager().handleEvent(flexEventBuild);
    }

    /* JADX INFO: compiled from: GetOnDemandPlaylistsMetricsUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/metrics/GetOnDemandPlaylistsMetricsUtil$EventType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "EVENT_AUTH", "EVENT_CACHE_PLAYLISTS", "EVENT_BROWSE_HOME_REFRESH", "EVENT_SEARCH_RESULT_AAP_REFRESH", "EVENT_CLEAR_PLAYLISTS_FROM_CACHE", "EVENT_CLIENT_SYNC_CYCLE", "EVENT_ON_DEMAND_PLAYBACK_FROM_PLAY_BUTTON", "EVENT_ON_DEMAND_PLAYBACK_FROM_SHUFFLE_BUTTON", "EVENT_ON_DEMAND_PLAYBACK_FROM_TRACK", "EVENT_USHER_SERVICE_CALL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum EventType {
        EVENT_AUTH("auth"),
        EVENT_CACHE_PLAYLISTS("cacheOnDemandPlaylists"),
        EVENT_BROWSE_HOME_REFRESH("browseHomeRefresh"),
        EVENT_SEARCH_RESULT_AAP_REFRESH("searchResultAapRefresh"),
        EVENT_CLEAR_PLAYLISTS_FROM_CACHE("clearOnDemandPlaylists"),
        EVENT_CLIENT_SYNC_CYCLE("clientSyncCycle"),
        EVENT_ON_DEMAND_PLAYBACK_FROM_PLAY_BUTTON("onDemandPlaybackFromPlayButton"),
        EVENT_ON_DEMAND_PLAYBACK_FROM_SHUFFLE_BUTTON("onDemandPlaybackFromShuffleButton"),
        EVENT_ON_DEMAND_PLAYBACK_FROM_TRACK("onDemandPlaybackFromTrack"),
        EVENT_USHER_SERVICE_CALL("usherServiceCall");

        private final String value;

        EventType(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
