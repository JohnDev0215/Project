package com.amazon.mp3.library.job;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class DeletePrimeTrackJob extends Job {
    private static final String TAG = "DeletePrimeTrackJob";
    private final String mAsin;
    private SQLiteDatabase mDb;

    public DeletePrimeTrackJob(final SQLiteDatabase db, final String asin) {
        this.mAsin = asin;
        this.mDb = db;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        if (StringUtil.isNullOrWhiteSpaces(this.mAsin)) {
            return 3;
        }
        MusicDownloader.getInstance(getContext()).cancel(this.mAsin);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mAsin);
        if (PrimeContentUtil.markPrimeLibraryTracksAsNotDownloaded(this.mDb, arrayList) == 0) {
            Log.error(TAG, "Unable to update track %s for deletion", this.mAsin);
            return 3;
        }
        PrimeContentUtil.copyAsCatalogTrackAndUpdatePlaylist(this.mDb, arrayList);
        getContext().getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        AddRemovePrimeManager.getInstance(getContext()).queueTrackToRemove(this.mAsin);
        Log.info(TAG, "Put the track with asin: %s into deletion queue from cloud", this.mAsin);
        return 1;
    }
}
