package com.amazon.music.media.playback.config;

import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnLibraryChangedListener;

/* JADX INFO: loaded from: classes4.dex */
public interface LibraryAccessProvider {
    void addOnLibraryChangedListener(OnLibraryChangedListener onLibraryChangedListener);

    void addToLibrary(MediaItem mediaItem);

    boolean canAddToLibrary(MediaItem mediaItem);

    boolean isAvailableOffline(MediaItem mediaItem);

    boolean isInLibrary(MediaItem mediaItem);

    void removeFromLibrary(MediaItem mediaItem);

    void removeOnLibraryChangedListener(OnLibraryChangedListener onLibraryChangedListener);

    void updateLibraryWithItem(MediaItem mediaItem);
}
