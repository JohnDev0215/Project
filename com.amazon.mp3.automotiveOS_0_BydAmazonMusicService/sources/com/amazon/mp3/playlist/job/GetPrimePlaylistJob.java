package com.amazon.mp3.playlist.job;

import android.text.TextUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabase;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class GetPrimePlaylistJob extends Job {
    private static final String TAG = "GetPrimePlaylistJob";
    private final boolean mAddToLibrary;
    private final PrimePlaylistDatabase mCache;
    private boolean mIsLocalPrimePlaylistUpdateable;
    private final boolean mIsSharedUserPlaylist;
    private CatalogPlaylist mPlaylist;
    private final String mPlaylistId;
    private final int mPlaylistSource;
    private final boolean mShouldShowSuccessfulUserInterface;

    public GetPrimePlaylistJob(PrimePlaylistDatabase cache, String playlistId, int source, boolean addToLibrary, boolean isSharedUserPlaylist) {
        this(cache, playlistId, source, addToLibrary, isSharedUserPlaylist, true);
    }

    public GetPrimePlaylistJob(final PrimePlaylistDatabase cache, final String playlistId, final int source, final boolean addToLibrary, final boolean isSharedUserPlaylist, final boolean shouldShowSuccessfulUserInterface) {
        this.mCache = cache;
        this.mPlaylistId = playlistId;
        this.mPlaylistSource = source;
        this.mAddToLibrary = addToLibrary;
        this.mIsSharedUserPlaylist = isSharedUserPlaylist;
        this.mShouldShowSuccessfulUserInterface = shouldShowSuccessfulUserInterface;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        if (TextUtils.isEmpty(this.mPlaylistId)) {
            Log.error(TAG, "Cannot retrieve Playlist without an ID");
            return 3;
        }
        if (this.mAddToLibrary) {
            this.mPlaylist = PrimePlaylistUtil.followPrimePlaylist(getContext(), this.mCache, this.mPlaylistSource, this.mPlaylistId, this.mShouldShowSuccessfulUserInterface);
        } else if (this.mIsSharedUserPlaylist) {
            this.mPlaylist = PrimePlaylistUtil.fetchSharedUserPlaylist(this.mCache, this.mPlaylistSource, this.mPlaylistId);
        } else {
            this.mPlaylist = PrimePlaylistUtil.fetchPrimePlaylist(this.mCache, this.mPlaylistSource, this.mPlaylistId);
        }
        CatalogPlaylist catalogPlaylist = this.mPlaylist;
        if (catalogPlaylist == null) {
            return 3;
        }
        if (!catalogPlaylist.isFollowed()) {
            this.mPlaylist.setFollowed(PrimePlaylistUtil.getExistingFollowedState(this.mCache, this.mPlaylist.getLuid(), this.mPlaylistSource));
        }
        if (!TextUtils.isEmpty(this.mPlaylist.getLuid()) && this.mPlaylistSource == 0) {
            this.mIsLocalPrimePlaylistUpdateable = PrimePlaylistUtil.isLocalPrimePlaylistUpdatable(this.mCache, this.mPlaylist);
            return 1;
        }
        this.mIsLocalPrimePlaylistUpdateable = false;
        return 1;
    }

    public CatalogPlaylist getPlaylist() {
        return this.mPlaylist;
    }

    public boolean isIsLocalPrimePlaylistUpdateable() {
        return this.mIsLocalPrimePlaylistUpdateable;
    }
}
