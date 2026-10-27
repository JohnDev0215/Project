package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryTrackProvider extends AbsLibraryTrackProvider {
    private static final String TAG = "LibraryTrackProvider";
    protected Uri mContentUri;
    protected AsyncTask<Uri, Void, String> mLoadCollectionNameTask;
    private AsyncQueryHandler mQueryHandler = new AsyncQueryHandler(AmazonApplication.getContext().getContentResolver()) { // from class: com.amazon.mp3.library.provider.source.nowplaying.LibraryTrackProvider.1
        @Override // android.content.AsyncQueryHandler
        protected synchronized void onQueryComplete(int token, Object cookie, Cursor cursor) {
            LibraryTrackProvider.this.onQueryComplete(token, cookie, cursor);
        }
    };
    private String mSelection;
    private String[] mSelectionArgs;
    private String mSortOrder;

    private void init(Uri contentUri, String sortOrder, String selection, String[] selectionArgs) {
        this.mContentUri = contentUri;
        this.mSortOrder = sortOrder;
        this.mSelection = selection;
        this.mSelectionArgs = selectionArgs;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.AbsLibraryTrackProvider
    protected synchronized void cancel(int id) {
        AsyncTask<Uri, Void, String> asyncTask;
        super.cancel(id);
        if (id == 1) {
            this.mQueryHandler.cancelOperation(1);
            this.mQueryHandler.cancelOperation(2);
            AsyncTask<Uri, Void, String> asyncTask2 = this.mLoadCollectionNameTask;
            if (asyncTask2 != null) {
                asyncTask2.cancel(true);
            }
        } else if (id == 2) {
            this.mQueryHandler.cancelOperation(2);
        } else if (id == 3 && (asyncTask = this.mLoadCollectionNameTask) != null) {
            asyncTask.cancel(true);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.AbsLibraryTrackProvider
    protected void loadCollectionInternal(LoadCookie cookie) {
        this.mQueryHandler.startQuery(1, cookie, MediaProvider.NowPlaying.CONTENT_URI, BaseTrackProvider.DEFAULT_TRACK_PROJECTION, this.mSelection, this.mSelectionArgs, this.mSortOrder);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.AbsLibraryTrackProvider
    protected void refreshInternal(LoadCookie cookie) {
        this.mQueryHandler.startQuery(2, cookie, MediaProvider.NowPlaying.CONTENT_URI, BaseTrackProvider.DEFAULT_TRACK_PROJECTION, this.mSelection, this.mSelectionArgs, this.mSortOrder);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.BaseTrackProvider, com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public Uri getUri() {
        return this.mContentUri;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.AbsLibraryTrackProvider
    protected void updateUri(MusicTrack track) {
        Uri collectionUri = track.getCollectionUri();
        if (collectionUri != null) {
            this.mContentUri = collectionUri;
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollectionName() {
        if (this.mContentUri != null) {
            cancel(3);
            LoadCollectionNameTask loadCollectionNameTask = new LoadCollectionNameTask();
            this.mLoadCollectionNameTask = loadCollectionNameTask;
            loadCollectionNameTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.mContentUri);
        }
    }

    private final class LoadCollectionNameTask extends AsyncTask<Uri, Void, String> {
        private LoadCollectionNameTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Uri... uris) {
            String string;
            if (uris == null || uris.length == 0) {
                return null;
            }
            Uri uri = uris[0];
            int iMatch = DefaultUriMatcher.match(uri);
            if (iMatch != 1) {
                if (iMatch == 2) {
                    MusicTrack musicTrack = (MusicTrack) AmazonApplication.getLibraryItemFactory().getItem(uri);
                    if (musicTrack == null) {
                        Log.error(LibraryTrackProvider.TAG, "Track is null ", uri);
                        return "";
                    }
                    return musicTrack.getAlbumName();
                }
                if (iMatch != 5 && iMatch != 9 && iMatch != 26) {
                    if (iMatch == 28 || iMatch == 33) {
                        CatalogPlaylist playlist = new PrimePlaylistDatabaseManager().getPlaylist(MediaProvider.Playlists.getCatalogPlaylistId(uri), PrimePlaylistsTable.translateSource(MediaProvider.getSource(uri)));
                        return playlist != null ? playlist.getTitle() : "";
                    }
                    if (iMatch != 15 && iMatch != 16) {
                        return "";
                    }
                }
            }
            Uri collectionUri = CirrusMediaSource.getCollectionUri(uri);
            if (collectionUri == null) {
                return "";
            }
            AbstractItem item = AmazonApplication.getLibraryItemFactory().getItem(collectionUri);
            if (item instanceof Album) {
                string = ((Album) item).getTitle();
            } else if (item instanceof Artist) {
                string = ((Artist) item).getName();
            } else if (item instanceof Playlist) {
                string = ((Playlist) item).getName();
            } else if (item instanceof Genre) {
                string = ((Genre) item).getName();
            } else {
                if (item instanceof MusicTrack) {
                    return "";
                }
                string = AmazonApplication.getContext().getString(R.string.dmusic_player_now_playing_all_songs);
            }
            return string;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String collectionName) {
            LibraryTrackProvider.this.setCollectionName(collectionName);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void populateState(final JSONObject blob) throws JSONException {
        blob.put("uri", this.mContentUri);
        blob.put("order", this.mSortOrder);
        if (!TextUtils.equals(this.mSelection, BaseTrackProvider.DEFAULT_TRACK_SELECTION)) {
            blob.put("selection", this.mSelection);
        }
        if (this.mSelectionArgs != BaseTrackProvider.DEFAULT_TRACK_SELECTION_ARGS) {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.mSelectionArgs) {
                jSONArray.put(str);
            }
            blob.put("selection_args", jSONArray);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void restore(Context context, final JSONObject blob) throws JSONException {
        String[] strArr;
        String string = blob.getString("uri");
        String strOptString = blob.optString("order", null);
        String strOptString2 = blob.optString("selection");
        if (TextUtils.isEmpty(strOptString2)) {
            strOptString2 = BaseTrackProvider.DEFAULT_TRACK_SELECTION;
        }
        JSONArray jSONArrayOptJSONArray = blob.optJSONArray("selection_args");
        if (jSONArrayOptJSONArray == null) {
            strArr = BaseTrackProvider.DEFAULT_TRACK_SELECTION_ARGS;
        } else {
            String[] strArr2 = new String[jSONArrayOptJSONArray.length()];
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                strArr2[i] = jSONArrayOptJSONArray.getString(i);
            }
            strArr = strArr2;
        }
        init(Uri.parse(string), strOptString, strOptString2, strArr);
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public String getSortOrder() {
        return this.mSortOrder;
    }
}
