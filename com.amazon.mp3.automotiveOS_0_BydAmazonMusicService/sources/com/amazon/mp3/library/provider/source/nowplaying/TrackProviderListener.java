package com.amazon.mp3.library.provider.source.nowplaying;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackProviderListener {
    void onCollectionChanged();

    void onCollectionLoaded(LoadCookie cookie);

    void onCollectionNameLoaded(String collectionName);

    void onCollectionRefreshed(boolean hasChanged);

    void onCountChanged(int newCount);

    void onPositionChanged(int newPosition);
}
