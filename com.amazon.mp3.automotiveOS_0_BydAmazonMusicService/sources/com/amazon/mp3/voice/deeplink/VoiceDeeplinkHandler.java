package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.media.playback.MediaCollectionId;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: VoiceDeeplinkHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J(\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&R\u0014\u0010\u000e\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkHandler;", "", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "uri", "", "startPlayback", "", "playbackSessionId", "", "handle", "getUrlRegex", "()Ljava/lang/String;", "urlRegex", "getMetricName", "metricName", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface VoiceDeeplinkHandler {
    String getMetricName();

    String getUrlRegex();

    void handle(Context context, Uri uri, boolean startPlayback, String playbackSessionId);

    /* JADX INFO: compiled from: VoiceDeeplinkHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static String findSegmentAfter(VoiceDeeplinkHandler voiceDeeplinkHandler, Uri uri, String segment) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(segment, "segment");
            List<String> pathSegments = uri.getPathSegments();
            int size = pathSegments.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                String str = pathSegments.get(i);
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                if (Intrinsics.areEqual(str, segment) && i < pathSegments.size() - 1) {
                    String str2 = pathSegments.get(i2);
                    if (str2 != null) {
                        return str2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                i = i2;
            }
            return null;
        }

        public static boolean isShuffleOn(VoiceDeeplinkHandler voiceDeeplinkHandler, Uri uri) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            Intrinsics.checkNotNullParameter(uri, "uri");
            return "shuffle".equals(uri.getQueryParameter("do"));
        }

        public static boolean isRepeatOne(VoiceDeeplinkHandler voiceDeeplinkHandler, Uri uri) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            Intrinsics.checkNotNullParameter(uri, "uri");
            return "all".equals(uri.getQueryParameter("repeat"));
        }

        public static boolean shouldPlayFullScreen(VoiceDeeplinkHandler voiceDeeplinkHandler, Uri uri) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            Intrinsics.checkNotNullParameter(uri, "uri");
            String queryParameter = uri.getQueryParameter("do");
            if (queryParameter == null) {
                return false;
            }
            return StringsKt__StringsKt.contains$default((CharSequence) queryParameter, (CharSequence) PageUriUtils.DO_PLAYFULL_VALUE, false, 2, (Object) null);
        }

        public static boolean shouldStartFullScreenForFreeTier(VoiceDeeplinkHandler voiceDeeplinkHandler) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            return (CarModeUtility.isInCarMode() || AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning()) ? false : true;
        }

        public static MediaCollectionId getMediaCollectionId(VoiceDeeplinkHandler voiceDeeplinkHandler, String playbackSessionId) {
            Intrinsics.checkNotNullParameter(voiceDeeplinkHandler, "this");
            Intrinsics.checkNotNullParameter(playbackSessionId, "playbackSessionId");
            return new MediaCollectionId(MediaCollectionId.Type.ALEXA_PLAYBACK_SESSION_ID, playbackSessionId);
        }
    }
}
