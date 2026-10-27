package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.RecentlyPlayedItemStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.DbUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultRecentlyPlayedItemStateProvider implements RecentlyPlayedItemStateProvider {
    private static DefaultRecentlyPlayedItemStateProvider sInstance;
    private final Context mContext;
    private Map<Uri, Integer> mDownloadStateMap = new HashMap();

    private boolean isDownloaded(final int downloadState) {
        return downloadState == 0;
    }

    private boolean isDownloading(final int downloadState) {
        return downloadState == 1 || downloadState == 4 || downloadState == 3 || downloadState == -2;
    }

    private DefaultRecentlyPlayedItemStateProvider(final Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized DefaultRecentlyPlayedItemStateProvider getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = new DefaultRecentlyPlayedItemStateProvider(context);
        }
        return sInstance;
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public void requestState(final RecentlyPlayedItem recentlyPlayedItem, @Nullable final StateProvider.Listener<RecentlyPlayedItem> listener) {
        new StatusRequest(recentlyPlayedItem, listener).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public Uri getAllTracksUri(final RecentlyPlayedItem recentlyPlayedItem) {
        return recentlyPlayedItem.getContentUri().buildUpon().appendPath("tracks").build();
    }

    @Override // com.amazon.mp3.library.provider.RecentlyPlayedItemStateProvider
    public void setDownloadState(final Uri contentUri, final int downloadState) {
        this.mDownloadStateMap.put(contentUri, Integer.valueOf(downloadState));
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isInLibrary(final RecentlyPlayedItem recentlyPlayedItem) {
        return recentlyPlayedItem.getOwnershipStatus().isInLibrary();
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloading(final RecentlyPlayedItem recentlyPlayedItem) {
        return isDownloading(getDownloadState(recentlyPlayedItem));
    }

    @Override // com.amazon.mp3.library.provider.StateProvider
    public boolean isDownloaded(final RecentlyPlayedItem recentlyPlayedItem) {
        return isDownloaded(getDownloadState(recentlyPlayedItem));
    }

    private int getDownloadState(final RecentlyPlayedItem item) {
        Integer num = this.mDownloadStateMap.get(item.getContentUri());
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    private class StatusRequest extends AsyncTask<Void, Void, Integer> {
        private final RecentlyPlayedItem mRecentlyPlayedItem;

        @Nullable
        private final StateProvider.Listener<RecentlyPlayedItem> mRecentlyPlayedItemListener;

        private StatusRequest(final RecentlyPlayedItem recentlyPlayedItem, @Nullable final StateProvider.Listener<RecentlyPlayedItem> recentlyPlayedItemListener) {
            this.mRecentlyPlayedItem = recentlyPlayedItem;
            this.mRecentlyPlayedItemListener = recentlyPlayedItemListener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(final Void... params) {
            Cursor cursorQuery;
            String[] strArr = {"download_state"};
            Uri contentUri = this.mRecentlyPlayedItem.getContentUri();
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri)) {
                CatalogPlaylist primePlaylist = AmazonApplication.getLibraryItemFactory().getPrimePlaylist(contentUri);
                if (primePlaylist != null) {
                    return Integer.valueOf(primePlaylist.getDownloadState());
                }
            } else if (!MediaProvider.PrimeBrowseTracksCollection.isCollection(contentUri.buildUpon().appendPath("tracks").build()) && !MediaProvider.Station.isStation(contentUri) && !MediaProvider.AlexaRecentlyPlayed.isAlexaRecentlyPlayedCollection(contentUri) && !MediaProvider.CastingRecentlyPlayed.isCastingRecentlyPlayedCollection(contentUri) && (cursorQuery = DefaultRecentlyPlayedItemStateProvider.this.mContext.getContentResolver().query(contentUri, strArr, "ownership_status < 300", null, null)) != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        return Integer.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndex("download_state")));
                    }
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            } else {
                return -1;
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Integer downloadState) {
            DefaultRecentlyPlayedItemStateProvider.this.mDownloadStateMap.put(this.mRecentlyPlayedItem.getContentUri(), downloadState);
            StateProvider.Listener<RecentlyPlayedItem> listener = this.mRecentlyPlayedItemListener;
            if (listener != null) {
                RecentlyPlayedItem recentlyPlayedItem = this.mRecentlyPlayedItem;
                listener.onStateChanged(recentlyPlayedItem, recentlyPlayedItem.getOwnershipStatus().getValue(), downloadState.intValue());
            }
        }
    }
}
