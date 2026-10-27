package com.amazon.mp3.library.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.api.track.CatalogPlaylistServiceTrack;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistByAsinTask extends AsyncTask<Void, Void, AddToPlaylistManager.ErrorReason> {
    private static final String TAG = AddToPlaylistByAsinTask.class.getSimpleName();
    private final String SELECTION = DeluxeContentUtil.getNonDeluxeSelection();
    private final String[] SELECTION_ARGS = DeluxeContentUtil.getNonDeluxeSelectionArgs();
    private final Context mContext;
    private final AddToPlaylistManager.AddToPlaylistListener mListener;
    private List<String> mPlaylistTrackAsins;
    private final Uri mPlaylistUri;
    private List<String> mTrackAsinsToAdd;
    private final Uri mTracksUri;

    public AddToPlaylistByAsinTask(Context context, Uri playlistUri, Uri tracksUri, AddToPlaylistManager.AddToPlaylistListener listener) {
        this.mContext = context;
        this.mPlaylistUri = playlistUri;
        this.mTracksUri = tracksUri;
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public AddToPlaylistManager.ErrorReason doInBackground(Void... params) throws Throwable {
        this.mPlaylistTrackAsins = getAllTrackAsins(this.mPlaylistUri);
        this.mTrackAsinsToAdd = getTrackAsinsToAdd();
        if (isAlreadyInPlaylist()) {
            return AddToPlaylistManager.ErrorReason.ITEM_ALREADY_IN_PLAYLIST;
        }
        if (exceedsMaxPlaylistSize()) {
            return AddToPlaylistManager.ErrorReason.MAX_PLAYLIST_SIZE_REACHED;
        }
        AllAccessUserPlaylistLimitTracker.Response responseCanAdd = AllAccessUserPlaylistLimitTracker.canAdd(this.mPlaylistUri, this.mTracksUri);
        if (responseCanAdd == AllAccessUserPlaylistLimitTracker.Response.AAUP_PLAYLIST_TRACK_LIMIT) {
            return AddToPlaylistManager.ErrorReason.AAUP_PLAYLIST_TRACK_LIMIT;
        }
        if (responseCanAdd == AllAccessUserPlaylistLimitTracker.Response.AAUP_RESTRICTION_LIMIT) {
            return AddToPlaylistManager.ErrorReason.AAUP_RESTRICTION_LIMIT;
        }
        addToCirrusPlaylist();
        addToDatabasePlaylist(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.mp3.library.util.AddToPlaylistByAsinTask.1
            @Override // java.lang.Runnable
            public void run() {
                SyncService.startSync(AddToPlaylistByAsinTask.this.mContext, 19);
            }
        }, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        AllAccessUserPlaylistLimitTracker.incrementAddCounter(this.mPlaylistUri, this.mTrackAsinsToAdd.size());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(AddToPlaylistManager.ErrorReason reason) {
        if (reason == null) {
            this.mListener.onSuccess();
        } else {
            this.mListener.onError(reason);
        }
    }

    private List<String> getTrackAsinsToAdd() {
        List<String> allTrackAsins = getAllTrackAsins(this.mTracksUri);
        if (allTrackAsins.isEmpty() && MediaProvider.Tracks.isPrimeAdditionalTrack(this.mTracksUri)) {
            allTrackAsins = new ArrayList(Arrays.asList(MediaProvider.Tracks.getPrimeAdditionalTrackAsin(this.mTracksUri)));
        }
        allTrackAsins.removeAll(this.mPlaylistTrackAsins);
        return allTrackAsins;
    }

    private boolean isAlreadyInPlaylist() {
        return this.mTrackAsinsToAdd.size() == 0;
    }

    private boolean exceedsMaxPlaylistSize() {
        return this.mPlaylistTrackAsins.size() + this.mTrackAsinsToAdd.size() > PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist();
    }

    private void addToCirrusPlaylist() {
        MC2PlaylistApi mC2PlaylistApi = new MC2PlaylistApi();
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = this.mTrackAsinsToAdd.iterator();
            while (it.hasNext()) {
                arrayList.add(new CatalogPlaylistServiceTrack(it.next()));
            }
            mC2PlaylistApi.appendTracksToPlaylist(this.mContext, this.mPlaylistUri, arrayList);
        } catch (Exception e) {
            Log.warning(TAG, "Exception when trying to call CirrusPlaylistScratch.post(): ", e);
        }
    }

    private void addToDatabasePlaylist(final boolean shouldStartAutoDownloadUnifiedPlaylist) throws Throwable {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        PrimeContentUtil.addPrimeTracksInDbForPlaylist(writableDatabase, getPrimeAdditionalTracks(writableDatabase));
        this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        long playlistId = MediaProvider.Playlists.getPlaylistId(this.mPlaylistUri);
        List<String> luidsForAsins = getLuidsForAsins(writableDatabase);
        CirrusPlaylistScratch.create(this.mContext, playlistId);
        PlaylistUtil.insertUdoPlaylistTracksFromLuidList(this.mContext, "cirrus", playlistId, luidsForAsins);
        CirrusPlaylistScratch.postToDb(this.mContext, playlistId, "cirrus", shouldStartAutoDownloadUnifiedPlaylist);
    }

    private List<String> getPrimeAdditionalTracks(SQLiteDatabase db) {
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(this.mTrackAsinsToAdd), this.mTrackAsinsToAdd.size());
        return getStringsFromCursor(db.query("Track", new String[]{"asin"}, DbUtil.applyBinaryOperator("prime_status>175 AND ownership_status>300", "AND", whereClause.getClause()), whereClause.getArgs(), null, null, null), "asin");
    }

    private List<String> getLuidsForAsins(SQLiteDatabase db) {
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new ArrayList(this.mTrackAsinsToAdd), this.mTrackAsinsToAdd.size());
        return getStringsFromCursor(db.query("Track", new String[]{"luid"}, whereClause.getClause(), whereClause.getArgs(), null, null, null), "luid");
    }

    private List<String> getAllTrackAsins(Uri uri) {
        String primeAsinLuidTrackAsin;
        if (uri != null) {
            if (MediaProvider.Tracks.isPrimeAsinLuidTrack(uri) && (primeAsinLuidTrackAsin = MediaProvider.Tracks.getPrimeAsinLuidTrackAsin(uri)) != null) {
                return new ArrayList(Arrays.asList(primeAsinLuidTrackAsin));
            }
            return getStringsFromCursor(this.mContext.getContentResolver().query(uri, new String[]{"asin"}, this.SELECTION, this.SELECTION_ARGS, null), "asin");
        }
        return new ArrayList();
    }

    private List<String> getStringsFromCursor(Cursor c, String column) {
        if (c != null) {
            try {
                ArrayList arrayList = new ArrayList();
                int columnIndex = c.getColumnIndex(column);
                while (c.moveToNext()) {
                    arrayList.add(c.getString(columnIndex));
                }
                return arrayList;
            } finally {
                DbUtil.closeCursor(c);
            }
        }
        return new ArrayList();
    }
}
