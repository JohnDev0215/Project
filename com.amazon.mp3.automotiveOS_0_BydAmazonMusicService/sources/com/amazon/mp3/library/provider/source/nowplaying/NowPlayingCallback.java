package com.amazon.mp3.library.provider.source.nowplaying;

import com.amazon.mp3.library.item.MusicTrack;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingCallback implements NowPlayingListener {
    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onCollectionCountChanged(final int newCount) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onCollectionLoaded(final MusicTrack track) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onCollectionNameLoaded(final String collectionName) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onMetaDataChanged(final MusicTrack track) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onRepeatModeChanged(final int mode, final MusicTrack track) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onShuffleSet(final boolean shuffleOn, final MusicTrack track) {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.NowPlayingListener
    public void onTrackChanged(int position, MusicTrack track) {
    }
}
