package com.amazon.mp3.util;

import android.content.Intent;
import android.net.Uri;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: MediaPlaybackUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/util/MediaPlaybackUtil;", "", "()V", "getNowPlayingMediaItemAsin", "", "getPlayVideoIntent", "Landroid/content/Intent;", ImagesContract.URL, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaPlaybackUtil {
    public static final MediaPlaybackUtil INSTANCE = new MediaPlaybackUtil();

    private MediaPlaybackUtil() {
    }

    @JvmStatic
    public static final Intent getPlayVideoIntent(String url) {
        Intent intent = new Intent();
        intent.setDataAndType(Uri.parse(url), "video/*");
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @JvmStatic
    public static final String getNowPlayingMediaItemAsin() {
        MediaItemId mediaItemId;
        MediaItem currentMediaItem = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null || (mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN)) == null) {
            return null;
        }
        return mediaItemId.getId();
    }
}
