package com.amazon.mp3.explore.playback;

import android.net.Uri;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes3.dex */
public class HttpsMediaItem extends MediaItem {
    private Uri imageUri;

    public HttpsMediaItem(final String audioUrl, final long durationMillis, final String title, final String imageUrl, final MediaCollectionInfo collectionInfo, final PlaybackPageType playbackPageType) {
        super(new MediaItemId(MediaItemId.Type.URL, audioUrl), title, durationMillis, MediaItem.Type.HTTPS_CONTENT, new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withCastable(false).withExplicit(false).withContentAccessType(ContentAccessType.NIGHTWING).build(), null, Playback.getInstance().getPlaybackConfig().getMediaLinkProvider(), Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider(), playbackPageType, false, collectionInfo, new MediaCollectionInfo[0]);
        this.imageUri = Uri.parse(imageUrl);
    }

    @Override // com.amazon.music.media.playback.MediaItem
    protected Uri getMediaItemImageUri(final int width, final int height) {
        return this.imageUri;
    }
}
