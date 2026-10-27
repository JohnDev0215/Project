package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.media.playback.MediaCollectionId;
import java.net.MalformedURLException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: VoiceCloudQueueDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceCloudQueueDeeplinkHandler;", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "()V", "metricName", "", "getMetricName", "()Ljava/lang/String;", "urlRegex", "getUrlRegex", "handle", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "startPlayback", "", "playbackSessionId", "shouldPlayFullScreen", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceCloudQueueDeeplinkHandler implements VoiceDeeplinkHandler {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceCloudQueueDeeplinkHandler.class).getSimpleName();
    private final String metricName = "CloudQueues";

    public String findSegmentAfter(Uri uri, String str) {
        return VoiceDeeplinkHandler.DefaultImpls.findSegmentAfter(this, uri, str);
    }

    public MediaCollectionId getMediaCollectionId(String str) {
        return VoiceDeeplinkHandler.DefaultImpls.getMediaCollectionId(this, str);
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getUrlRegex() {
        String str = "/cloudqueues(/.*)?";
        Intrinsics.checkNotNullExpressionValue(str, "regex.toString()");
        return str;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public String getMetricName() {
        return this.metricName;
    }

    @Override // com.amazon.mp3.voice.deeplink.VoiceDeeplinkHandler
    public void handle(Context context, Uri uri, boolean startPlayback, String playbackSessionId) throws MalformedURLException {
        List listSplit$default;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        String str = TAG;
        Log.info(str, Intrinsics.stringPlus("Handle cloud queue  deeplink: ", uri));
        String strFindSegmentAfter = findSegmentAfter(uri, "cloudqueues");
        String str2 = null;
        if (strFindSegmentAfter != null && (listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strFindSegmentAfter, new String[]{"&"}, false, 0, 6, (Object) null)) != null) {
            str2 = (String) CollectionsKt___CollectionsKt.firstOrNull(listSplit$default);
        }
        if (str2 == null) {
            Log.error(str, "Cannot find queueId");
            throw new MalformedURLException("MissingQueueId");
        }
        Log.debug(str, Intrinsics.stringPlus("QueueId: ", str2));
        new VoiceDeeplinkPlaybackUtil().startCQPlayback(context, str2, getMediaCollectionId(playbackSessionId), shouldPlayFullScreen(uri));
    }

    public boolean shouldPlayFullScreen(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (CarModeUtility.isInCarMode() || AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning()) {
            return false;
        }
        return VoiceDeeplinkHandler.DefaultImpls.shouldPlayFullScreen(this, uri);
    }
}
