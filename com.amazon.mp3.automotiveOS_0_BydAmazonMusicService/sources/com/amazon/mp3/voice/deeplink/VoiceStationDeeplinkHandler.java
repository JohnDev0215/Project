package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.destination.parser.StationDeeplinkParser;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.station.StationItem;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoiceStationDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J*\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceStationDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "playFreeTierStation", "stationKey", "startPlayBack", "shouldPlayFullScreen", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceStationDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = VoiceStationDeeplinkHandler.class.getSimpleName();
    private final String metricName = "Station";

    public MediaCollectionId getMediaCollectionId(String str) {
        return VoiceDeeplinkHandler.DefaultImpls.getMediaCollectionId(this, str);
    }

    public boolean shouldStartFullScreenForFreeTier() {
        return VoiceDeeplinkHandler.DefaultImpls.shouldStartFullScreenForFreeTier(this);
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String urlRegex = new StationDeeplinkParser().getUrlRegex();
        Intrinsics.checkNotNullExpressionValue(urlRegex, "StationDeeplinkParser().urlRegex");
        return urlRegex;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getMetricName() {
        return this.metricName;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public void handle(Context context, Uri uri, boolean startPlayback, String playbackSessionId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        Log.info(TAG, Intrinsics.stringPlus("Handle station deeplink:", uri));
        String stationKey = new StationDeeplinkParser().parse(uri).getStationKey();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            playFreeTierStation(context, stationKey, startPlayback, playbackSessionId);
            return;
        }
        StationItemProvider stationItemProvider = new StationItemProvider();
        Intrinsics.checkNotNullExpressionValue(stationKey, "stationKey");
        StationItem stationItemWithKey = stationItemProvider.getStationItemWithKey(stationKey, "", "");
        if (stationItemWithKey == null) {
            return;
        }
        StationUtils.playStation(context, stationItemWithKey, ControlSource.ALEXA, startPlayback, shouldPlayFullScreen(uri), PlaybackPageType.EXTERNAL, getMediaCollectionId(playbackSessionId), Clock.now(), null, null, null, false, false);
    }

    private final void playFreeTierStation(Context context, String stationKey, boolean startPlayBack, String playbackSessionId) {
        FreeTierPlaybackUtil.playCloudStation(context, ControlSource.ALEXA, CollectionsKt__CollectionsJVMKt.listOf(new PlayableEntityIdentifier(stationKey, PlayableEntityIdentifierType.STATION_KEY)), null, startPlayBack, true, shouldStartFullScreenForFreeTier(), PlaybackPageType.SFA, Clock.now(), null, null, true, null, getMediaCollectionId(playbackSessionId));
    }

    public boolean shouldPlayFullScreen(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (CarModeUtility.isInCarMode() || AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning()) {
            return false;
        }
        return VoiceDeeplinkHandler.DefaultImpls.shouldPlayFullScreen(this, uri);
    }
}
