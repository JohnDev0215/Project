package com.amazon.mp3.library.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
class AddToPlaylistByLuidTask extends AsyncTask<Void, Void, AddToPlaylistManager.ErrorReason> {
    private static final String TAG = AddToPlaylistByLuidTask.class.getSimpleName();
    private final String SELECTION = DbUtil.applyBinaryOperator(DeluxeContentUtil.getNonDeluxeSelection(), "AND", TrackListAdapter.getPlaylistSelection());
    private final String[] SELECTION_ARGS = DbUtil.mergeColumnArrays(DeluxeContentUtil.getNonDeluxeSelectionArgs(), TrackListAdapter.getPlaylistSelectionArgs());
    private final Context mContext;
    private final AddToPlaylistManager.AddToPlaylistListener mListener;
    private Map<String, String> mPlaylistTrackLuids;
    private final Uri mPlaylistUri;
    private final String mSourceId;
    private List<String> mTrackLuidsToAdd;
    private final Uri mTracksUri;

    public AddToPlaylistByLuidTask(Context context, Uri playlistUri, Uri tracksUri, AddToPlaylistManager.AddToPlaylistListener listener) {
        this.mContext = context;
        this.mPlaylistUri = playlistUri;
        this.mTracksUri = tracksUri;
        this.mSourceId = MediaProvider.getSource(playlistUri);
        this.mListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public AddToPlaylistManager.ErrorReason doInBackground(Void... params) {
        this.mPlaylistTrackLuids = getAllTrackLuids(this.mPlaylistUri);
        this.mTrackLuidsToAdd = getTrackLuidsToAdd();
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
        long playlistId = PlaylistUtil.getPlaylistId(this.mPlaylistUri);
        int i = 0;
        CirrusPlaylistScratch.create(this.mContext, playlistId);
        Iterator<String> it = this.mTrackLuidsToAdd.iterator();
        while (it.hasNext()) {
            Uri contentUri = MediaProvider.Tracks.getContentUri(this.mSourceId, it.next());
            int iInsertUdoPlaylistTracks = PlaylistUtil.insertUdoPlaylistTracks(this.mContext, this.mSourceId, playlistId, contentUri, null);
            if (iInsertUdoPlaylistTracks == 0) {
                Log.warning(TAG, "Failed to insert track " + contentUri + " into playlist scratch table");
            }
            i += iInsertUdoPlaylistTracks;
        }
        if (i <= 0) {
            return null;
        }
        try {
            CirrusPlaylistScratch.post(this.mContext, this.mSourceId, playlistId);
            AllAccessUserPlaylistLimitTracker.incrementAddCounter(this.mPlaylistUri, this.mTrackLuidsToAdd.size());
            return null;
        } catch (Exception e) {
            Log.warning(TAG, "Exception when trying to call CirrusPlaylistScratch.post(): ", e);
            return null;
        }
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

    private List<String> getTrackLuidsToAdd() {
        Map<String, String> allTrackLuids = getAllTrackLuids(this.mTracksUri);
        if (allTrackLuids.isEmpty()) {
            Log.warning(TAG, "No track luids found from track uri " + this.mTracksUri.toString() + ".  Are you sure the tracks are in the database?");
        } else {
            allTrackLuids.keySet().removeAll(this.mPlaylistTrackLuids.keySet());
            allTrackLuids.values().removeAll(Collections.singleton(null));
        }
        return new ArrayList(allTrackLuids.values());
    }

    private boolean isAlreadyInPlaylist() {
        return this.mTrackLuidsToAdd.size() == 0;
    }

    private boolean exceedsMaxPlaylistSize() {
        if ("cirrus-local".equals(this.mSourceId)) {
            return false;
        }
        return this.mPlaylistTrackLuids.size() + this.mTrackLuidsToAdd.size() > PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist();
    }

    private Map<String, String> getAllTrackLuids(Uri uri) {
        Cursor cursorQuery;
        if (uri != null && (cursorQuery = this.mContext.getContentResolver().query(uri, new String[]{"asin", "luid"}, this.SELECTION, this.SELECTION_ARGS, null)) != null) {
            try {
                HashMap map = new HashMap();
                int columnIndex = cursorQuery.getColumnIndex("luid");
                int columnIndex2 = cursorQuery.getColumnIndex("asin");
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndex);
                    String string2 = cursorQuery.getString(columnIndex2);
                    String str = TAG;
                    Log.debug(str, "Attempting to add track asin = [%s], luid = [%s] to playlist", string2, string);
                    if (!IdGenerator.isPrimeLuid(string)) {
                        if (StringUtils.isNotBlank(string2)) {
                            map.put(string2, string);
                        } else {
                            Log.debug(str, "Sideloaded song luid = [%s] can't be added to playlist", string);
                        }
                    }
                }
                return map;
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        return new HashMap();
    }
}
