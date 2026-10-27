package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.lyrics.LyricsDB;
import com.amazon.mp3.lyrics.LyricsDatabase;
import com.amazon.mp3.lyrics.LyricsManager;
import com.amazon.mp3.lyrics.exception.LyricsParseExeption;
import com.amazon.mp3.lyrics.exception.NetworkException;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.util.Log;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsProvider {
    private static final String TAG = "LyricsProvider";
    private LoadLyricsTask mLoadLyricsTask;
    private final LyricsDB mLyricsDB;
    private final LyricsManager mLyricsManager;

    public interface LoadLyricsCallback {
        void onLoadDelay();

        void onLyricsLoaded(@NonNull Lyrics lyrics);

        void onNoLyrics();
    }

    public LyricsProvider(Context context) {
        LyricsDatabase lyricsDatabase = new LyricsDatabase(context, AccountDetailUtil.get(context).getHomeMarketPlaceId());
        this.mLyricsDB = lyricsDatabase;
        this.mLyricsManager = new LyricsManager(context, lyricsDatabase);
    }

    public void fetchLyrics(LyricsTrackInfo info, LoadLyricsCallback callback) {
        LoadLyricsTask loadLyricsTask = this.mLoadLyricsTask;
        if (loadLyricsTask != null) {
            loadLyricsTask.cancel(true);
        }
        if (!AccountDetailUtil.get().getHomeMarketPlaceId().equals(info.getMarketplaceId())) {
            callback.onNoLyrics();
            return;
        }
        LoadLyricsTask loadLyricsTask2 = new LoadLyricsTask(callback);
        this.mLoadLyricsTask = loadLyricsTask2;
        loadLyricsTask2.execute(info);
    }

    private class LoadLyricsTask extends AsyncTask<LyricsTrackInfo, Void, Lyrics> {
        private final LoadLyricsCallback mLoadLyricsCallback;

        public LoadLyricsTask(LoadLyricsCallback callback) {
            this.mLoadLyricsCallback = callback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Lyrics doInBackground(final LyricsTrackInfo... params) {
            LyricsTrackInfo lyricsTrackInfo = params[0];
            try {
                if (isCancelled()) {
                    return null;
                }
                return LyricsProvider.this.mLyricsManager.loadLyrics(lyricsTrackInfo);
            } catch (LyricsParseExeption unused) {
                publishProgress(new Void[0]);
                return downloadLyrics(lyricsTrackInfo);
            } catch (FileNotFoundException unused2) {
                publishProgress(new Void[0]);
                if (LyricsProvider.this.mLyricsDB.loadLyricsStatusForTrackInfo(lyricsTrackInfo) != LyricsStatus.NO_LYRICS) {
                    return downloadLyrics(lyricsTrackInfo);
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(final Void... values) {
            this.mLoadLyricsCallback.onLoadDelay();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Lyrics lyrics) {
            if (lyrics != null) {
                this.mLoadLyricsCallback.onLyricsLoaded(lyrics);
            } else {
                this.mLoadLyricsCallback.onNoLyrics();
            }
        }

        private Lyrics downloadLyrics(final LyricsTrackInfo info) {
            try {
                if (isCancelled()) {
                    return null;
                }
                return LyricsProvider.this.mLyricsManager.downloadLyrics(info);
            } catch (LyricsParseExeption unused) {
                Log.verbose(LyricsProvider.TAG, "Downloaded lyrics is corrupted.");
                return null;
            } catch (NetworkException unused2) {
                Log.verbose(LyricsProvider.TAG, "Could not download lyrics. Check if device has network.");
                return null;
            }
        }
    }
}
