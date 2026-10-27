package com.amazon.music.media.playback;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public interface OnMetadataChangedListener {
    void onMediaCollectionInfoChanged(@Nullable MediaCollectionInfo mediaCollectionInfo);

    void onMetadataChanged(MediaItem mediaItem);
}
