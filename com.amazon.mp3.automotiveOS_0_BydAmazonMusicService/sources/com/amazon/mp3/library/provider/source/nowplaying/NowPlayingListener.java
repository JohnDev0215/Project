package com.amazon.mp3.library.provider.source.nowplaying;

import com.amazon.mp3.library.item.MusicTrack;

/* JADX INFO: loaded from: classes3.dex */
public interface NowPlayingListener {
    void onCollectionCountChanged(int newCount);

    void onCollectionLoaded(MusicTrack track);

    void onCollectionNameLoaded(String collectionName);

    void onMetaDataChanged(MusicTrack track);

    void onRepeatModeChanged(int mode, MusicTrack track);

    void onShuffleSet(boolean shuffleOn, MusicTrack track);

    void onTrackChanged(int position, MusicTrack track);
}
