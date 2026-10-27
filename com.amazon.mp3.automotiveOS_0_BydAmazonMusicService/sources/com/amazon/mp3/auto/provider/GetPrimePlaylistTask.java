package com.amazon.mp3.auto.provider;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabase;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class GetPrimePlaylistTask extends AsyncTask<Void, Void, CatalogPlaylist> {
    private static final String TAG = GetPrimePlaylistTask.class.getSimpleName();
    private boolean mAddToLibrary;
    private final String mAsin;
    private final PrimePlaylistDatabase mCache;
    private Context mContext;
    private PrimePlaylistRetrievedListener mListener;
    private CatalogPlaylist mPlaylist;
    private final int mPlaylistSource;
    private long requestTime;
    private boolean startPlayBack;

    public interface PrimePlaylistRetrievedListener {
        void onPlaylistRetrievalFailed(Exception e);

        void onPrimePlaylistRetrieved(CatalogPlaylist catalogPlaylist, boolean startPlayBack, long time);
    }

    public GetPrimePlaylistTask(Context context, PrimePlaylistRetrievedListener listener, PrimePlaylistDatabase cache, String asin, int source, boolean addToLibrary, boolean start, long time) {
        this.mContext = context;
        this.mListener = listener;
        this.mCache = cache;
        this.mAsin = asin;
        this.mPlaylistSource = source;
        this.mAddToLibrary = addToLibrary;
        this.startPlayBack = start;
        this.requestTime = time;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public CatalogPlaylist doInBackground(final Void... voids) {
        if (TextUtils.isEmpty(this.mAsin)) {
            Log.error(TAG, "Cannot retrieve Playlist without an ASIN");
            return null;
        }
        this.mPlaylist = this.mCache.getPlaylist(this.mAsin, this.mPlaylistSource);
        try {
            if (this.mAddToLibrary) {
                this.mPlaylist = PrimePlaylistUtil.followPrimePlaylist(this.mContext, this.mCache, this.mPlaylistSource, this.mAsin);
            } else {
                this.mPlaylist = PrimePlaylistUtil.fetchPrimePlaylist(this.mCache, this.mPlaylistSource, this.mAsin);
            }
            return this.mPlaylist;
        } catch (AbstractHttpClient.CanceledException | AbstractHttpClient.FailedException | AbstractHttpClient.IncompleteResultException | AbstractHttpClient.UnexpectedHttpStatusException | ServiceException e) {
            this.mListener.onPlaylistRetrievalFailed(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(final CatalogPlaylist catalogPlaylist) {
        super.onPostExecute(catalogPlaylist);
        this.mListener.onPrimePlaylistRetrieved(catalogPlaylist, this.startPlayBack, this.requestTime);
    }
}
