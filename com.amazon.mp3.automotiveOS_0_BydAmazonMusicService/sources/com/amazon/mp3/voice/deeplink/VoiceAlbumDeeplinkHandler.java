package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.parser.AlbumDeeplinkParser;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: VoiceAlbumDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceAlbumDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceAlbumDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceAlbumDeeplinkHandler.class).getSimpleName();
    private final String metricName = "Album";

    public MediaCollectionId getMediaCollectionId(String str) {
        return VoiceDeeplinkHandler.DefaultImpls.getMediaCollectionId(this, str);
    }

    public boolean isRepeatOne(Uri uri) {
        return VoiceDeeplinkHandler.DefaultImpls.isRepeatOne(this, uri);
    }

    public boolean isShuffleOn(Uri uri) {
        return VoiceDeeplinkHandler.DefaultImpls.isShuffleOn(this, uri);
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String urlRegex = new AlbumDeeplinkParser().getUrlRegex();
        Intrinsics.checkNotNullExpressionValue(urlRegex, "AlbumDeeplinkParser().urlRegex");
        return urlRegex;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getMetricName() {
        return this.metricName;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public void handle(Context context, Uri uri, boolean startPlayback, String playbackSessionId) {
        PrimeCollectionTask.Task task;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        Log.info(TAG, Intrinsics.stringPlus("Handle Album deeplink:", uri));
        AlbumDestination albumDestination = new AlbumDeeplinkParser().parse(uri);
        PrimeCollectionTask.OnUriReadyCallback onUriReadyCallback = new PrimeCollectionTask.OnUriReadyCallback() { // from class: com.amazon.mp3.voice.deeplink.VoiceAlbumDeeplinkHandler$handle$albumUriCallback$1
            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onAlbumUriReady(Uri uri2) {
                Intrinsics.checkNotNullParameter(uri2, "uri");
                Log.debug(VoiceAlbumDeeplinkHandler.TAG, Intrinsics.stringPlus("Album uri is ready ", uri2));
            }

            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onError(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.error(VoiceAlbumDeeplinkHandler.TAG, "Failed to get album uri.", e);
            }
        };
        boolean zIsShuffleOn = isShuffleOn(uri);
        String collectionAsin = albumDestination.getCollectionAsin();
        String trackAsin = albumDestination.getTrackAsin();
        PlaybackPageType playbackPageType = PlaybackPageType.EXTERNAL;
        if (zIsShuffleOn) {
            task = PrimeCollectionTask.Task.PLAYBACK_SHUFFLE;
        } else {
            task = PrimeCollectionTask.Task.PLAYBACK;
        }
        PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(context, collectionAsin, trackAsin, true, playbackPageType, task, new QueryAlbumByAsin(context), onUriReadyCallback);
        primeCollectionTaskCreatePlaybackTask.withStartIndex(zIsShuffleOn ? -1 : 0);
        primeCollectionTaskCreatePlaybackTask.withPlaybackControlSource(ControlSource.ALEXA);
        primeCollectionTaskCreatePlaybackTask.withMediaCollectionId(getMediaCollectionId(playbackSessionId));
        primeCollectionTaskCreatePlaybackTask.execute();
        if (isRepeatOne(uri)) {
            new VoiceDeeplinkPlaybackUtil().setRepeatAll();
        }
    }
}
