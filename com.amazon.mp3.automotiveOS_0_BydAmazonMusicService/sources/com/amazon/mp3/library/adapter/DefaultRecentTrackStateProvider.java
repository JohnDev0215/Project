package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultRecentTrackStateProvider implements StateProvider {
    protected final Context mContext;
    protected final Map<RecentTrack, TrackState> mTrackStateMap = new HashMap();
    private final List<Uri> mDownloadUriList = new ArrayList();

    @Override // com.amazon.mp3.library.provider.StateProvider
    @Nullable
    public Uri getAllTracksUri(final RecentTrack item) {
        return null;
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public /* bridge */ /* synthetic */ void requestState(final Object track, final StateProvider.Listener listener) {
        requestState((RecentTrack) track, (StateProvider.Listener<RecentTrack>) listener);
    }

    public DefaultRecentTrackStateProvider(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    protected static class TrackState {

        @Nullable
        protected Uri mContentUri;
        protected int mDownloadState;
        protected boolean mIsAddingToLibrary = false;

        @Nullable
        protected ContentOwnershipStatus mOwnershipStatus;

        protected TrackState() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInLibrary() {
            return this.mOwnershipStatus.isInLibrary();
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

    public void requestState(final RecentTrack track, final StateProvider.Listener<RecentTrack> listener) {
        new StatusRequest(track, listener).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isInLibrary(final RecentTrack track) {
        TrackState trackState = this.mTrackStateMap.get(track);
        if (trackState != null) {
            return trackState.isInLibrary();
        }
        return track.isInLibrary();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloading(final RecentTrack track) {
        TrackState trackState = this.mTrackStateMap.get(track);
        if (trackState != null) {
            return trackState.isDownloading();
        }
        return track.isDownloading();
    }

    private class StatusRequest extends AsyncTask<Void, Void, TrackState> {
        private final StateProvider.Listener<RecentTrack> mListener;
        private final RecentTrack mRecentTrack;

        private StatusRequest(final RecentTrack recentTrack, final StateProvider.Listener<RecentTrack> listener) {
            this.mRecentTrack = recentTrack;
            this.mListener = listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public TrackState doInBackground(final Void... params) {
            return DefaultRecentTrackStateProvider.this.doQuery(this.mRecentTrack);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final TrackState trackState) {
            this.mRecentTrack.setOwnershipStatus(trackState.mOwnershipStatus);
            DefaultRecentTrackStateProvider.this.mTrackStateMap.put(this.mRecentTrack, trackState);
            StateProvider.Listener<RecentTrack> listener = this.mListener;
            if (listener != null) {
                listener.onStateChanged(this.mRecentTrack, trackState.mOwnershipStatus.getValue(), trackState.mDownloadState);
            }
        }
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloaded(final RecentTrack track) {
        if (!isInLibrary(track)) {
            return false;
        }
        TrackState trackState = this.mTrackStateMap.get(track);
        if (trackState == null) {
            return track.isDownloaded();
        }
        return trackState.isDownloaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TrackState doQuery(final RecentTrack recentTrack) {
        String[] strArr;
        String str;
        TrackState trackState = this.mTrackStateMap.get(recentTrack);
        if (trackState == null) {
            trackState = new TrackState();
        }
        if (recentTrack.getLuid() != null) {
            strArr = new String[]{recentTrack.getLuid()};
            str = "luid=?";
        } else {
            strArr = new String[]{recentTrack.getAsin()};
            str = "asin=?";
        }
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{"luid", "ownership_status", "download_state"}, str, strArr, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                populateTrackStateFromCursor(cursorQuery, trackState, recentTrack.getSource());
            } else {
                trackState.mOwnershipStatus = ContentOwnershipStatus.NOT_IN_LIBRARY;
                trackState.mDownloadState = 5;
            }
            return trackState;
        } finally {
            cursorQuery.close();
        }
    }

    protected void populateTrackStateFromCursor(final Cursor cursor, final TrackState trackState, String source) {
        ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursor.getInt(cursor.getColumnIndex("ownership_status")));
        trackState.mOwnershipStatus = contentOwnershipStatusFromValue;
        if (source == null && (contentOwnershipStatusFromValue == ContentOwnershipStatus.ADDED || contentOwnershipStatusFromValue == ContentOwnershipStatus.OWNED)) {
            source = "cirrus";
        }
        trackState.mContentUri = MediaProvider.Tracks.getContentUri(source, cursor.getString(cursor.getColumnIndex("luid")));
        if (!"cirrus-local".equals(source)) {
            trackState.mDownloadState = cursor.getInt(cursor.getColumnIndex("download_state"));
        } else {
            trackState.mDownloadState = 0;
        }
    }
}
