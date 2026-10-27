package com.amazon.mp3.voice.deeplink;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: VoiceDeeplinkPlaybackUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J*\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J=\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u000b¢\u0006\u0002\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/VoiceDeeplinkPlaybackUtil;", "", "()V", "setRepeatAll", "", "startCQPlayback", "context", "Landroid/content/Context;", "queueId", "", "additionalMediaCollectionId", "Lcom/amazon/music/media/playback/MediaCollectionId;", "startFullScreen", "", "startLibraryPlayback", "uri", "Landroid/net/Uri;", "isShuffleOn", "selection", "selectionArgs", "", "mediaCollectionId", "(Landroid/net/Uri;ZLjava/lang/String;[Ljava/lang/String;Lcom/amazon/music/media/playback/MediaCollectionId;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceDeeplinkPlaybackUtil {
    private static final String TAG = Reflection.getOrCreateKotlinClass(VoiceDeeplinkPlaybackUtil.class).getSimpleName();

    public final void startLibraryPlayback(Uri uri, boolean isShuffleOn, String selection, String[] selectionArgs, MediaCollectionId mediaCollectionId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(mediaCollectionId, "mediaCollectionId");
        PlayQueueSequencer.getInstance().setAsCurrentSequencer(ControlSource.ALEXA, uri, selection, selectionArgs, null, isShuffleOn ? -1 : 0, isShuffleOn, true, new PlaybackMetricInformation(DirectedPlayStatus.UNKNOWN, PlaybackPageType.EXTERNAL), mediaCollectionId, null, Clock.now());
    }

    public final void setRepeatAll() {
        Playback.getInstance().getPlaybackController(ControlSource.ALEXA).setRepeatMode(RepeatMode.REPEAT_ALL);
    }

    @SuppressLint({"CheckResult"})
    public final void startCQPlayback(Context context, String queueId, MediaCollectionId additionalMediaCollectionId, boolean startFullScreen) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Log.debug(TAG, Intrinsics.stringPlus("Starting playback based on CQ queueId: ", queueId));
        try {
            StartPlaybackUtil startPlaybackUtil = StartPlaybackUtil.INSTANCE;
            ControlSource ALEXA = ControlSource.ALEXA;
            Intrinsics.checkNotNullExpressionValue(ALEXA, "ALEXA");
            startPlaybackUtil.startCQPlayback(context, queueId, ALEXA, additionalMediaCollectionId, startFullScreen, true);
        } catch (Exception e) {
            Log.error(TAG, "Error starting playback based on CQ queueId " + queueId + ": " + ((Object) e.getMessage()));
        }
    }
}
