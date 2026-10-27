package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.media.playback.MediaCollectionId;
import java.net.MalformedURLException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX INFO: compiled from: VoiceLibraryAlbumDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceLibraryAlbumDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uriOrig", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceLibraryAlbumDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceLibraryAlbumDeeplinkHandler.class).getSimpleName();
    private final String metricName = "LibraryAlbum";

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
    public String getMetricName() {
        return this.metricName;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String str = "/library/albums(/.*)?";
        Intrinsics.checkNotNullExpressionValue(str, "regex.toString()");
        return str;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public void handle(Context context, Uri uriOrig, boolean startPlayback, String playbackSessionId) throws MalformedURLException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uriOrig, "uriOrig");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        String str = TAG;
        Log.info(str, Intrinsics.stringPlus("Handle Library Album deeplink:", uriOrig));
        String string = uriOrig.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uriOrig.toString()");
        Uri uri = Uri.parse(StringsKt__StringsJVMKt.replace$default(string, "?asin", "&asin", false, 4, (Object) null));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        Log.debug(str, Intrinsics.stringPlus("Formatted Library Album deeplink:", uri));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        String strFindSegmentAfter = findSegmentAfter(uri, "albums");
        if (strFindSegmentAfter == null) {
            Log.error(str, "Cannot find artist name");
            throw new MalformedURLException("MissingArtistName");
        }
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        String strFindSegmentAfter2 = findSegmentAfter(uri, strFindSegmentAfter);
        if (strFindSegmentAfter2 == null) {
            Log.error(str, "Cannot find album name");
            throw new MalformedURLException("MissingAlbumName");
        }
        Log.verbose(str, Intrinsics.stringPlus("Asin: ", uri.getQueryParameter("asin")));
        String queryParameter = uri.getQueryParameter("asin");
        if (queryParameter == null) {
            queryParameter = "";
        }
        String str2 = queryParameter;
        Log.debug(str, "Artist:" + ((Object) strFindSegmentAfter) + " Album:" + ((Object) strFindSegmentAfter2) + " Album ASIN:" + str2);
        String strReplace$default = StringsKt__StringsJVMKt.replace$default(strFindSegmentAfter, MqttTopic.SINGLE_LEVEL_WILDCARD, " ", false, 4, (Object) null);
        String strReplace$default2 = StringsKt__StringsJVMKt.replace$default(strFindSegmentAfter2, MqttTopic.SINGLE_LEVEL_WILDCARD, " ", false, 4, (Object) null);
        Log.verbose(str, "After replacing + with space. Artist:" + ((Object) strReplace$default) + " Album:" + ((Object) strReplace$default2));
        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri("cirrus", IdGenerator.generateAlbumId(str2, strReplace$default2));
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        Log.debug(str, Intrinsics.stringPlus("Album uri :", contentUri));
        VoiceDeeplinkPlaybackUtil voiceDeeplinkPlaybackUtil = new VoiceDeeplinkPlaybackUtil();
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        voiceDeeplinkPlaybackUtil.startLibraryPlayback(contentUri, isShuffleOn(uri), TrackListAdapter.getDefaultSelection(), TrackListAdapter.getDefaultSelectionArgs(), getMediaCollectionId(playbackSessionId));
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        if (isRepeatOne(uri)) {
            new VoiceDeeplinkPlaybackUtil().setRepeatAll();
        }
    }
}
