package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: VoiceDeeplinkResolver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\rH\u0002J&\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u001a\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0013R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkResolver;", "", "()V", "supportedDeeplinkHandlers", "", "Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "getSupportedDeeplinkHandlers", "()Ljava/util/List;", "canResolve", "", "uri", "Landroid/net/Uri;", "prepareForPlayback", "", "resolve", "context", "Landroid/content/Context;", "encodedUri", "playbackSessionId", "", "isEmpPlayback", "sendMetric", "metricName", "additonalText", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceDeeplinkResolver {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceDeeplinkResolver.class).getSimpleName();
    private static final String SCHEME = "alexa";
    private static final String PATH_PREFIX = "/amznmp3:/";

    public final boolean canResolve(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Validate.notNull(uri);
        return SCHEME.equals(uri.getScheme());
    }

    public final void resolve(Context context, Uri encodedUri, String playbackSessionId, boolean isEmpPlayback) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(encodedUri, "encodedUri");
        Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
        Validate.notNull(encodedUri);
        if (isEmpPlayback) {
            sendMetric$default(this, "aomEMPDeeplinkReceived", null, 2, null);
        } else {
            sendMetric$default(this, "alexaDeeplinkReceived", null, 2, null);
        }
        Log.info(TAG, Intrinsics.stringPlus("Original Voice Deeplink:", encodedUri));
        try {
            string = URLDecoder.decode(encodedUri.toString(), ParserUtil.UTF_8);
            Intrinsics.checkNotNullExpressionValue(string, "decode(encodedUri.toString(), \"UTF-8\")");
        } catch (UnsupportedEncodingException e) {
            Log.error(TAG, "Error while decoding the uri", e);
            string = encodedUri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "encodedUri.toString()");
        }
        Uri uri = Uri.parse(StringsKt__StringsJVMKt.replace$default(string, "?index", "&index", false, 4, (Object) null));
        String str = TAG;
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        Log.debug(str, Intrinsics.stringPlus("Resolved Voice Deeplink:", uri));
        for (VoiceDeeplinkHandler voiceDeeplinkHandler : getSupportedDeeplinkHandlers()) {
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            Intrinsics.checkNotNullExpressionValue(path, "uri.getPath()!!");
            if (new Regex(Intrinsics.stringPlus(PATH_PREFIX, voiceDeeplinkHandler.getUrlRegex())).matches(path)) {
                prepareForPlayback();
                if (isEmpPlayback) {
                    sendMetric("aomEMPDeeplinkResolved", voiceDeeplinkHandler.getMetricName());
                } else {
                    sendMetric("alexaDeeplinkResolved", voiceDeeplinkHandler.getMetricName());
                }
                try {
                    voiceDeeplinkHandler.handle(context, uri, true, playbackSessionId);
                    return;
                } catch (Exception e2) {
                    Log.error(TAG, "Error while handling the deeplink", e2);
                    if (isEmpPlayback) {
                        sendMetric("aomEMPDeeplinkHandlerError", e2.getMessage());
                        return;
                    } else {
                        sendMetric("alexaDeeplinkHandlerError", e2.getMessage());
                        return;
                    }
                }
            }
        }
        if (isEmpPlayback) {
            sendMetric$default(this, "aomEMPDeeplinkUnResolved", null, 2, null);
        } else {
            sendMetric$default(this, "alexaDeeplinkUnResolved", null, 2, null);
        }
        Log.error(TAG, "The deeplink was not handled");
    }

    private final List<VoiceDeeplinkHandler> getSupportedDeeplinkHandlers() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VoiceCloudQueueDeeplinkHandler());
        arrayList.add(new VoiceAlbumDeeplinkHandler());
        arrayList.add(new VoicePlaylistDeeplinkHandler());
        arrayList.add(new VoiceStationDeeplinkHandler());
        arrayList.add(new VoiceLibraryArtistDeeplinkHandler());
        arrayList.add(new VoiceLibraryAlbumDeeplinkHandler());
        arrayList.add(new VoiceLibraryPlaylistDeeplinkHandler());
        arrayList.add(new VoiceQueueDeeplinkHandler());
        return arrayList;
    }

    private final void prepareForPlayback() {
        PlaybackControllerProvider.Companion companion = PlaybackControllerProvider.INSTANCE;
        ControlSource controlSource = ControlSource.ALEXA;
        ActionValidatedPlaybackController controller = companion.getController(controlSource);
        if (controller.getPlayStatus().willPlay()) {
            controller.stop(ChangeReason.SOURCE_MODIFIED);
        }
        Playback.getInstance().getPlaybackController(controlSource).autoResolveNextConcurrencyCheck();
    }

    public static /* synthetic */ void sendMetric$default(VoiceDeeplinkResolver voiceDeeplinkResolver, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        voiceDeeplinkResolver.sendMetric(str, str2);
    }

    public final void sendMetric(String metricName, String additonalText) {
        Intrinsics.checkNotNullParameter(metricName, "metricName");
        Log.verbose(TAG, "Sending Flex metric:%s", metricName);
        FlexEvent.Builder builder = FlexEvent.builder(metricName);
        builder.withFlexStr1(additonalText);
        MetricsLogger.sendEvent(builder.build());
    }
}
