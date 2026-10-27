package com.amazon.mp3.playback.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayWithContentUriPlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0019\u0010\u001aJf\u0010\u0018\u001a\u00020\u00172\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/playback/handler/PlayWithContentUriPlaybackUtil;", "", "Landroid/content/Context;", "context", "Lcom/amazon/music/media/playback/ControlSource;", "playbackControlSource", "Landroid/net/Uri;", "contentUri", "", "position", "", "sortOrder", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "metricInformation", "Lcom/amazon/music/media/playback/MediaCollectionId;", "mediaCollectionId", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackpageType", "", "startTime", "", "isFullScreen", "shuffle", "", "startPlayback", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayWithContentUriPlaybackUtil {
    public static final PlayWithContentUriPlaybackUtil INSTANCE = new PlayWithContentUriPlaybackUtil();

    private PlayWithContentUriPlaybackUtil() {
    }

    public final void startPlayback(Context context, ControlSource playbackControlSource, Uri contentUri, int position, String sortOrder, PlaybackMetricInformation metricInformation, MediaCollectionId mediaCollectionId, PlaybackPageType playbackpageType, long startTime, boolean isFullScreen, boolean shuffle) {
        Intrinsics.checkNotNullParameter(playbackControlSource, "playbackControlSource");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(playbackpageType, "playbackpageType");
        PlayQueueSequencer.getInstance().setAsCurrentSequencer(playbackControlSource, contentUri, null, null, sortOrder, position, shuffle, true, metricInformation == null ? new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackpageType) : metricInformation, mediaCollectionId, null, startTime);
        if (context == null || !isFullScreen) {
            return;
        }
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
        if (context == context.getApplicationContext()) {
            intentForContentUri.addFlags(268435456);
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        if (fragmentActivity.getIntent() != null && fragmentActivity.getIntent().getExtras() != null && !fragmentActivity.isFinishing()) {
            DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
            intentForContentUri.putExtra(deeplinkIntentKey.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey.toString()));
            DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_REF_MARKER;
            intentForContentUri.putExtra(deeplinkIntentKey2.toString(), fragmentActivity.getIntent().getStringExtra(deeplinkIntentKey2.toString()));
        }
        fragmentActivity.startActivity(intentForContentUri);
    }
}
