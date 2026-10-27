package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.net.MalformedURLException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: VoiceQueueDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J(\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceQueueDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "playFreeTierStation", "pageToken", "startPlayBack", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceQueueDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceQueueDeeplinkHandler.class).getSimpleName();
    private final String metricName = "Queues";

    public String findSegmentAfter(Uri uri, String str) {
        return VoiceDeeplinkHandler.DefaultImpls.findSegmentAfter(this, uri, str);
    }

    public MediaCollectionId getMediaCollectionId(String str) {
        return VoiceDeeplinkHandler.DefaultImpls.getMediaCollectionId(this, str);
    }

    public boolean shouldStartFullScreenForFreeTier() {
        return VoiceDeeplinkHandler.DefaultImpls.shouldStartFullScreenForFreeTier(this);
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String str = "/queues(/.*)?";
        Intrinsics.checkNotNullExpressionValue(str, "regex.toString()");
        return str;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getMetricName() {
        return this.metricName;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public void handle(Context context, Uri uri, boolean startPlayback, String playbackSessionId) throws MalformedURLException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        String str = TAG;
        Log.info(str, Intrinsics.stringPlus("Handle queues deeplink:", uri));
        String strFindSegmentAfter = findSegmentAfter(uri, "queues");
        if (strFindSegmentAfter == null) {
            Log.error(str, "Cannot find page token");
            throw new MalformedURLException("MissingPageToken");
        }
        Log.debug(str, Intrinsics.stringPlus("Page Token:", strFindSegmentAfter));
        if (UserSubscriptionUtil.isNightwingOnly()) {
            playFreeTierStation(context, strFindSegmentAfter, startPlayback, playbackSessionId);
        } else {
            StationUtils.playStation(context, new StationItemProvider().getStationItemWithPageToken(context, strFindSegmentAfter), ControlSource.ALEXA, startPlayback, false, PlaybackPageType.EXTERNAL, getMediaCollectionId(playbackSessionId), Clock.now(), null, null, null, false, false);
        }
    }

    private final void playFreeTierStation(Context context, String pageToken, boolean startPlayBack, String playbackSessionId) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getMediaCollectionId(playbackSessionId));
        arrayList.add(new MediaCollectionId(MediaCollectionId.Type.STATION_KEY, "ALEXA_INITIATED_DYNAMIC_QUEUE"));
        FreeTierPlaybackUtil.playCloudStation(context, ControlSource.ALEXA, null, pageToken, startPlayBack, false, shouldStartFullScreenForFreeTier(), PlaybackPageType.SFA, Clock.now(), null, MediaCollectionType.AMF_STATION_SEED, false, null, arrayList, null);
    }
}
