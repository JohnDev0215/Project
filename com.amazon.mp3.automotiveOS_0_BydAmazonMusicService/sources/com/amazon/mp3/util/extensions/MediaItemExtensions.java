package com.amazon.mp3.util.extensions;

import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: MediaItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0004"}, d2 = {"isAudio", "", "Lcom/amazon/music/media/playback/MediaItem;", "isVideo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
@JvmName(name = "MediaItemExtensions")
public final class MediaItemExtensions {
    public static final boolean isVideo(MediaItem mediaItem) {
        return (mediaItem == null ? null : mediaItem.getMediaItemId(MediaItemId.Type.PID)) != null;
    }

    public static final boolean isAudio(MediaItem mediaItem) {
        return mediaItem != null && mediaItem.getMediaItemId(MediaItemId.Type.PID) == null;
    }
}
