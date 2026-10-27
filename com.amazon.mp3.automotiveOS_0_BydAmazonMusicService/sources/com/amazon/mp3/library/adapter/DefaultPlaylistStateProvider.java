package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultPlaylistStateProvider implements StateProvider<PrimePlaylist> {
    private Context mContext;
    private final Map<PrimePlaylist, PlaylistState> mStateMap = new HashMap();
    private final String TAG = DefaultPlaylistStateProvider.class.getSimpleName();

    public DefaultPlaylistStateProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public Uri getAllTracksUri(PrimePlaylist playlist) {
        PlaylistState playlistState = this.mStateMap.get(playlist);
        if (playlistState == null) {
            return null;
        }
        return playlistState.mAllTracksUri;
    }

    private static class PlaylistState {
        private Uri mAllTracksUri;
        private int mDownloadState;
        private int mOwnershipStatus;

        private PlaylistState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInLibrary() {
            int i = this.mOwnershipStatus;
            return i == 1 || i == 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDownloading() {
            int i = this.mDownloadState;
            return i == 1 || i == 4 || i == 3 || i == -2;
        }

        public boolean isDownloaded() {
            return this.mDownloadState == 0;
        }
    }

    private class StatusRequest extends AsyncTask<Void, Void, PlaylistState> {
        private final StateProvider.Listener<PrimePlaylist> mListener;
        private final PrimePlaylist mPrimePlaylist;

        private StatusRequest(PrimePlaylist playlist, StateProvider.Listener<PrimePlaylist> listener) {
            this.mPrimePlaylist = playlist;
            this.mListener = listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public PlaylistState doInBackground(final Void... params) {
            return DefaultPlaylistStateProvider.this.doQuery(this.mPrimePlaylist);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final PlaylistState playlistState) {
            DefaultPlaylistStateProvider.this.mStateMap.put(this.mPrimePlaylist, playlistState);
            StateProvider.Listener<PrimePlaylist> listener = this.mListener;
            if (listener == null || playlistState == null) {
                return;
            }
            listener.onStateChanged(this.mPrimePlaylist, playlistState.mOwnershipStatus, playlistState.mDownloadState);
        }
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public void requestState(PrimePlaylist playlist, StateProvider.Listener<PrimePlaylist> listener) {
        new StatusRequest(playlist, listener).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isInLibrary(PrimePlaylist playlist) {
        PlaylistState playlistState = this.mStateMap.get(playlist);
        if (playlistState == null) {
            return false;
        }
        return playlistState.isInLibrary();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloading(PrimePlaylist playlist) {
        PlaylistState playlistState = this.mStateMap.get(playlist);
        if (playlistState == null) {
            return false;
        }
        return playlistState.isDownloading();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloaded(PrimePlaylist playlist) {
        PlaylistState playlistState = this.mStateMap.get(playlist);
        if (playlistState == null) {
            return false;
        }
        return playlistState.isDownloaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlaylistState doQuery(PrimePlaylist playlist) {
        if (playlist != null && playlist.getAsin() == null && playlist.getOwnership()) {
            return queryBrushOwnedUserPlaylist(playlist);
        }
        return queryPrimePlaylist(playlist);
    }

    private PlaylistState queryPrimePlaylist(PrimePlaylist primePlaylist) {
        Cursor cursorQuery = null;
        PlaylistState playlistState = new PlaylistState();
        try {
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("PrimePlaylists", new String[]{"download_state", "is_following"}, "asin=?", new String[]{primePlaylist.getAsin() == null ? primePlaylist.getPlaylistId() : primePlaylist.getAsin()}, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    playlistState.mOwnershipStatus = 0;
                    playlistState.mDownloadState = 5;
                } else {
                    playlistState.mOwnershipStatus = cursorQuery.getInt(cursorQuery.getColumnIndex("is_following"));
                    playlistState.mDownloadState = cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
                }
            } catch (Exception e) {
                Log.error(this.TAG, "Exception while querying prime playlist: " + e.toString());
            }
            return playlistState;
        } finally {
            DbUtil.closeCursor(null);
        }
    }

    private PlaylistState queryBrushOwnedUserPlaylist(PrimePlaylist primePlaylist) {
        Cursor cursorQuery = null;
        PlaylistState playlistState = new PlaylistState();
        try {
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Playlist", new String[]{"download_state"}, "luid=?", new String[]{String.valueOf(primePlaylist.getPlaylistId())}, null, null, null);
                Uri contentUriFromLuid = MediaProvider.UdoPlaylists.getContentUriFromLuid(this.mContext, primePlaylist.getSource(), primePlaylist.getPlaylistId());
                if (contentUriFromLuid != null) {
                    playlistState.mAllTracksUri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(contentUriFromLuid);
                }
                if (cursorQuery.moveToFirst()) {
                    playlistState.mOwnershipStatus = 2;
                    if ("cirrus".equals(primePlaylist.getSource())) {
                        playlistState.mDownloadState = cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
                    } else {
                        playlistState.mDownloadState = 0;
                    }
                }
            } catch (IllegalArgumentException e) {
                Log.error(this.TAG, e.toString());
            }
            return playlistState;
        } finally {
            DbUtil.closeCursor(null);
        }
    }
}
