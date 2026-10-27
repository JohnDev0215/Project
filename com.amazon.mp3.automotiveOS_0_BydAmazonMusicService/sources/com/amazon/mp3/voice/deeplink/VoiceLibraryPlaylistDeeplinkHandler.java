package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.media.playback.MediaCollectionId;
import java.net.MalformedURLException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: VoiceLibraryPlaylistDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceLibraryPlaylistDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceLibraryPlaylistDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceLibraryPlaylistDeeplinkHandler.class).getSimpleName();
    private final String metricName = "LibraryPlaylist";

    public String findSegmentAfter(Uri uri, String str) {
        return VoiceDeeplinkHandler.DefaultImpls.findSegmentAfter(this, uri, str);
    }

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
        String str = "/user-playlists(/.*)?";
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
        Log.info(str, Intrinsics.stringPlus("Handle User playlist deeplink:", uri));
        String strFindSegmentAfter = findSegmentAfter(uri, "user-playlists");
        if (strFindSegmentAfter == null) {
            Log.error(str, "Cannot find Playlist Id");
            throw new MalformedURLException("MissingPlaylistId");
        }
        Log.debug(str, Intrinsics.stringPlus("PlaylisID:", strFindSegmentAfter));
        Uri contentUri = MediaProvider.UdoPlaylists.Tracks.getContentUri("cirrus", IdGenerator.generatePlaylistId(strFindSegmentAfter));
        Log.debug(str, Intrinsics.stringPlus("Playlist uri :", uri));
        VoiceDeeplinkPlaybackUtil voiceDeeplinkPlaybackUtil = new VoiceDeeplinkPlaybackUtil();
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        voiceDeeplinkPlaybackUtil.startLibraryPlayback(contentUri, isShuffleOn(uri), null, null, getMediaCollectionId(playbackSessionId));
        if (isRepeatOne(uri)) {
            new VoiceDeeplinkPlaybackUtil().setRepeatAll();
        }
    }
}
