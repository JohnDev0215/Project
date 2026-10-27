package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultAlbumStateProvider implements StateProvider<PrimeAlbum> {
    private final Context mContext;
    private final Map<PrimeAlbum, AlbumState> mStateMap = new HashMap();

    public DefaultAlbumStateProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private static class AlbumState {
        private Uri mAllTracksUri;
        private Uri mContentUri;
        private int mDownloadState;
        private ContentOwnershipStatus mOwnershipStatus;

        private AlbumState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInLibrary() {
            return this.mOwnershipStatus != ContentOwnershipStatus.NOT_IN_LIBRARY;
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

    private class StatusRequest extends AsyncTask<Void, Void, AlbumState> {
        private final StateProvider.Listener<PrimeAlbum> mListener;
        private final PrimeAlbum mPrimeAlbum;

        private StatusRequest(PrimeAlbum primeAlbum, StateProvider.Listener<PrimeAlbum> listener) {
            this.mPrimeAlbum = primeAlbum;
            this.mListener = listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public AlbumState doInBackground(final Void... params) {
            return DefaultAlbumStateProvider.this.doQuery(this.mPrimeAlbum);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final AlbumState albumState) {
            DefaultAlbumStateProvider.this.mStateMap.put(this.mPrimeAlbum, albumState);
            StateProvider.Listener<PrimeAlbum> listener = this.mListener;
            if (listener != null) {
                listener.onStateChanged(this.mPrimeAlbum, albumState.mOwnershipStatus.getValue(), albumState.mDownloadState);
            }
        }
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public void requestState(PrimeAlbum album, StateProvider.Listener<PrimeAlbum> listener) {
        new StatusRequest(album, listener).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isInLibrary(PrimeAlbum album) {
        AlbumState albumState = this.mStateMap.get(album);
        if (albumState == null) {
            return false;
        }
        return albumState.isInLibrary();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloading(PrimeAlbum album) {
        AlbumState albumState = this.mStateMap.get(album);
        if (albumState == null) {
            return false;
        }
        return albumState.isDownloading();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloaded(PrimeAlbum album) {
        AlbumState albumState = this.mStateMap.get(album);
        if (albumState == null) {
            return false;
        }
        return albumState.isDownloaded();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public Uri getAllTracksUri(PrimeAlbum album) {
        AlbumState albumState = this.mStateMap.get(album);
        if (albumState == null) {
            return null;
        }
        return albumState.mAllTracksUri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlbumState doQuery(final PrimeAlbum album) {
        String[] strArr;
        String str;
        AlbumState albumState = this.mStateMap.get(album);
        if (albumState == null) {
            albumState = new AlbumState();
        }
        int i = 1;
        if (album.getId() != null) {
            strArr = new String[]{String.valueOf(album.getId())};
            str = "album_id=?";
        } else {
            strArr = new String[]{album.getAsin()};
            str = "album_asin=?";
        }
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{PageUriUtils.SOURCE_QUERY_PARAM, "album_id", "ownership_status", "download_state"}, str, strArr, null, null, null, null);
        try {
            boolean zMoveToFirst = cursorQuery.moveToFirst();
            CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(this.mContext);
            boolean z = !TextUtils.isEmpty(album.getAsin()) && cTAPrimeCache.containsAsin(album.getAsin()) && cTAPrimeCache.getNonLibPrimeTrackCount("album_asin", album.getAsin()) == 0;
            if (!zMoveToFirst) {
                albumState.mOwnershipStatus = z ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
                albumState.mDownloadState = 5;
                return albumState;
            }
            boolean z2 = true;
            boolean z3 = true;
            boolean z4 = true;
            int i2 = 0;
            boolean z5 = false;
            while (true) {
                i2 += i;
                ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status")));
                if (album.getId() == null && contentOwnershipStatusFromValue != ContentOwnershipStatus.NOT_IN_LIBRARY) {
                    album.setId(Long.valueOf(cursorQuery.getLong(cursorQuery.getColumnIndex("album_id"))));
                    if (album.getSource() == null) {
                        album.setSource(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM))));
                    }
                }
                z2 = z2 && contentOwnershipStatusFromValue != ContentOwnershipStatus.NOT_IN_LIBRARY;
                z3 = z3 && contentOwnershipStatusFromValue == ContentOwnershipStatus.OWNED;
                if (!"cirrus-local".equals(album.getSource())) {
                    int i3 = cursorQuery.getInt(cursorQuery.getColumnIndex("download_state"));
                    z4 = z4 && i3 == 0;
                    if (i3 == 4 || i3 == 1 || i3 == 3 || i3 == -2) {
                        z5 = true;
                    }
                }
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                i = 1;
            }
            if (i2 >= album.getTrackCount()) {
                if (z3) {
                    albumState.mOwnershipStatus = ContentOwnershipStatus.OWNED;
                } else if (z2 || z) {
                    albumState.mOwnershipStatus = ContentOwnershipStatus.ADDED;
                } else {
                    albumState.mOwnershipStatus = ContentOwnershipStatus.NOT_IN_LIBRARY;
                }
                albumState.mDownloadState = z4 ? 0 : z5 ? 4 : 5;
            } else {
                albumState.mOwnershipStatus = z ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
                albumState.mDownloadState = z5 ? 4 : 5;
            }
            if (i2 != 0) {
                if (album.getAsin() == null || (albumState.isInLibrary() && !TextUtils.isEmpty(album.getSource()))) {
                    albumState.mContentUri = MediaProvider.Albums.getContentUri(album.getSource(), album.getId().longValue());
                } else {
                    albumState.mContentUri = new QueryAlbumByAsin(this.mContext).findCollectionUriByAsin(album.getAsin());
                }
                albumState.mAllTracksUri = albumState.mContentUri != null ? albumState.mContentUri.buildUpon().appendEncodedPath("tracks").build() : null;
            }
            return albumState;
        } finally {
            cursorQuery.close();
        }
    }
}
