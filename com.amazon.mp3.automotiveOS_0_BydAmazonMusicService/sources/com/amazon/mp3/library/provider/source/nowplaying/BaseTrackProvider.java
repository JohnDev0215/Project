package com.amazon.mp3.library.provider.source.nowplaying;

import android.net.Uri;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseTrackProvider implements TrackProvider {
    protected static final String[] DEFAULT_TRACK_PROJECTION = {"luid", "album_id", "album_art_id", "title", "album", "artist", "duration", PageUriUtils.SOURCE_QUERY_PARAM, "remote_uri", "album_artist", "match_hash", "asin", "album_asin", "prime_status", "ownership_status", "artist_asin", "download_state", "local_uri", "track_num", "artist_id", "is_explicit", "content_encoding", "asset_qualities", "asset_type", "is_exclusion_tagged", "playmode_eligibility", "catalog_status_tiers"};
    protected static final String DEFAULT_TRACK_SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    protected static final String[] DEFAULT_TRACK_SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();
    private String mCollectionName;
    private boolean mIsLoading;
    private CopyOnWriteArraySet<TrackProviderListener> mTrackListenerSet = new CopyOnWriteArraySet<>();

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public abstract /* synthetic */ int getCount();

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public Uri getUri() {
        return null;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsRepeatMode() {
        return true;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsShuffle() {
        return true;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void registerListener(TrackProviderListener listener) {
        this.mTrackListenerSet.add(listener);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void deregisterListener(TrackProviderListener listener) {
        this.mTrackListenerSet.remove(listener);
    }

    public void setCollectionName(String collectionName) {
        this.mCollectionName = collectionName;
        Iterator<TrackProviderListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onCollectionNameLoaded(this.mCollectionName);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public String getCollectionName() {
        return this.mCollectionName;
    }

    protected Set<TrackProviderListener> getListeners() {
        return this.mTrackListenerSet;
    }

    protected void setLoading(boolean loading) {
        this.mIsLoading = loading;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean isLoading() {
        return this.mIsLoading;
    }
}
