package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.music.media.playback.ratings.LikeState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultTrackStateProvider implements TrackStateProvider {
    protected final Context mContext;
    protected final Map<PrimeTrack, TrackState> mStateMap = new HashMap();
    private final List<Uri> mDownloadUris = new ArrayList();

    @Override // com.amazon.mp3.library.provider.StateProvider
    public Uri getAllTracksUri(PrimeTrack item) {
        return null;
    }

    public DefaultTrackStateProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    protected static class TrackState {
        protected Uri mContentUri;
        protected int mDownloadState;
        protected boolean mIsAddingToLibrary = false;
        protected LikeState mLikeState = LikeState.NEUTRAL;
        protected ContentOwnershipStatus mOwnershipStatus;

        protected TrackState() {
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

        public void setAddingToLibrary(final boolean isAddingToLibrary) {
            this.mIsAddingToLibrary = isAddingToLibrary;
        }

        public void setLikeState(LikeState likeState) {
            this.mLikeState = likeState;
        }

        public boolean isAddingToLibrary() {
            return this.mIsAddingToLibrary;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPurchased() {
            return this.mOwnershipStatus == ContentOwnershipStatus.OWNED;
        }
    }

    private class StatusRequest extends AsyncTask<Void, Void, TrackState> {
        private final StateProvider.Listener<PrimeTrack> mListener;
        private final PrimeTrack mPrimeTrack;

        private StatusRequest(PrimeTrack primeTrack, StateProvider.Listener<PrimeTrack> listener) {
            this.mPrimeTrack = primeTrack;
            this.mListener = listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public TrackState doInBackground(final Void... params) {
            return DefaultTrackStateProvider.this.doQuery(this.mPrimeTrack);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final TrackState trackState) {
            this.mPrimeTrack.setOwnershipStatus(trackState.mOwnershipStatus);
            this.mPrimeTrack.setDownloadState(trackState.mDownloadState);
            DefaultTrackStateProvider.this.mStateMap.put(this.mPrimeTrack, trackState);
            if (this.mListener != null) {
                if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                    this.mListener.onStateChanged(this.mPrimeTrack, trackState.mLikeState == LikeState.LIKE ? 1 : 0, trackState.mDownloadState);
                } else {
                    this.mListener.onStateChanged(this.mPrimeTrack, trackState.mOwnershipStatus.getValue(), trackState.mDownloadState);
                }
            }
        }
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public void requestState(final PrimeTrack track, final StateProvider.Listener<PrimeTrack> listener) {
        new StatusRequest(track, listener).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isInLibrary(PrimeTrack track) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            return false;
        }
        return trackState.isInLibrary();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloading(PrimeTrack track) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            return false;
        }
        return trackState.isDownloading();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloaded(PrimeTrack track) {
        if (isInLibrary(track)) {
            return this.mStateMap.get(track).isDownloaded();
        }
        return false;
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isPurchased(PrimeTrack track) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            return false;
        }
        return trackState.isPurchased();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public Uri getContentUri(PrimeTrack track) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            return null;
        }
        return trackState.mContentUri;
    }

    @Override // com.amazon.mp3.library.provider.TrackStateProvider
    public void monitorDownloadProgress(PrimeTrack primeTrack) {
        Uri contentUri = getContentUri(primeTrack);
        if (contentUri == null) {
            return;
        }
        this.mDownloadUris.add(contentUri);
    }

    @Override // com.amazon.mp3.library.provider.TrackStateProvider
    public void setAddingToLibrary(final PrimeTrack track, final boolean isAddingToLibrary) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            trackState = new TrackState();
            this.mStateMap.put(track, trackState);
        }
        trackState.setAddingToLibrary(isAddingToLibrary);
    }

    @Override // com.amazon.mp3.library.provider.TrackStateProvider
    public boolean isAddingToLibrary(final PrimeTrack track) {
        TrackState trackState = this.mStateMap.get(track);
        if (trackState != null) {
            return trackState.isAddingToLibrary();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TrackState doQuery(final PrimeTrack track) {
        String[] strArr;
        String str;
        TrackState trackState = this.mStateMap.get(track);
        if (trackState == null) {
            trackState = new TrackState();
        }
        if (track.getId() != null) {
            strArr = new String[]{String.valueOf(track.getId())};
            str = "_id=?";
        } else {
            strArr = new String[]{track.getAsin(), String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
            str = "asin=? AND playlist_track_status =?";
        }
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{"luid", "ownership_status", "download_state"}, str, strArr, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                populateTrackStateFromCursor(cursorQuery, trackState, track.getSource());
            } else {
                trackState.mOwnershipStatus = ContentOwnershipStatus.NOT_IN_LIBRARY;
                trackState.mDownloadState = 5;
            }
            cursorQuery.close();
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                List<LikeState> likesStateForTrackAsins = LikesServiceProxy.INSTANCE.getLikesStateForTrackAsins(this.mContext, Collections.singletonList(track.getAsin()));
                if (!likesStateForTrackAsins.isEmpty()) {
                    trackState.setLikeState(likesStateForTrackAsins.get(0));
                }
            }
            return trackState;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    protected void populateTrackStateFromCursor(Cursor cursor, TrackState trackState, String source) {
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
