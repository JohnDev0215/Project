package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.destination.parser.PlaylistDeeplinkParser;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoicePlaylistDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J*\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoicePlaylistDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "playFreeTierStation", "asin", "startPlayBack", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoicePlaylistDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = VoicePlaylistDeeplinkHandler.class.getSimpleName();
    private final String metricName = "Playlist";

    public MediaCollectionId getMediaCollectionId(String str) {
        return VoiceDeeplinkHandler.DefaultImpls.getMediaCollectionId(this, str);
    }

    public boolean isRepeatOne(Uri uri) {
        return VoiceDeeplinkHandler.DefaultImpls.isRepeatOne(this, uri);
    }

    public boolean isShuffleOn(Uri uri) {
        return VoiceDeeplinkHandler.DefaultImpls.isShuffleOn(this, uri);
    }

    public boolean shouldStartFullScreenForFreeTier() {
        return VoiceDeeplinkHandler.DefaultImpls.shouldStartFullScreenForFreeTier(this);
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String urlRegex = new PlaylistDeeplinkParser().getUrlRegex();
        Intrinsics.checkNotNullExpressionValue(urlRegex, "PlaylistDeeplinkParser().urlRegex");
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
        Log.info(TAG, Intrinsics.stringPlus("Handle playlist deeplink:", uri));
        String collectionAsin = new PlaylistDeeplinkParser().parse(uri).getCollectionAsin();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            playFreeTierStation(context, collectionAsin, startPlayback, playbackSessionId);
        } else {
            boolean zIsShuffleOn = isShuffleOn(uri);
            GetPrimePlaylistTracksTask getPrimePlaylistTracksTaskCreatePlaybackTask = GetPrimePlaylistTracksTask.createPlaybackTask(context, collectionAsin, zIsShuffleOn ? -1 : 0, PlaybackPageType.PLAYLIST_DETAIL, GetPrimePlaylistTracksTask.Task.PLAYBACK);
            getPrimePlaylistTracksTaskCreatePlaybackTask.setPlaybackPageType(PlaybackPageType.EXTERNAL);
            getPrimePlaylistTracksTaskCreatePlaybackTask.withShuffleState(zIsShuffleOn);
            getPrimePlaylistTracksTaskCreatePlaybackTask.withPlaybackControlSource(ControlSource.ALEXA);
            getPrimePlaylistTracksTaskCreatePlaybackTask.withMediaCollectionId(getMediaCollectionId(playbackSessionId));
            getPrimePlaylistTracksTaskCreatePlaybackTask.execute(new Void[0]);
        }
        if (isRepeatOne(uri)) {
            new VoiceDeeplinkPlaybackUtil().setRepeatAll();
        }
    }

    private final void playFreeTierStation(Context context, String asin, boolean startPlayBack, String playbackSessionId) {
        FreeTierPlaybackUtil.playCloudStation(context, ControlSource.ALEXA, CollectionsKt__CollectionsJVMKt.listOf(new PlayableEntityIdentifier(asin, PlayableEntityIdentifierType.ASIN)), null, startPlayBack, true, shouldStartFullScreenForFreeTier(), PlaybackPageType.SFA, Clock.now(), null, null, true, null, getMediaCollectionId(playbackSessionId));
    }
}
