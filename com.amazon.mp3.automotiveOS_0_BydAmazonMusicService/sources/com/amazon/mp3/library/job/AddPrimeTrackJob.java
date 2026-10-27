package com.amazon.mp3.library.job;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.PrimeContentUtil;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public class AddPrimeTrackJob extends Job {
    private final Collection<String> mAsins;
    private SQLiteDatabase mDb;

    public AddPrimeTrackJob(Context context, String trackAsin) {
        this(context, Collections.singleton(trackAsin));
    }

    public AddPrimeTrackJob(Context context, Collection<String> trackAsins) {
        this.mDb = CirrusDatabase.getWritableDatabase(context);
        this.mAsins = trackAsins;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        int iAddPrimeTracksInDb = PrimeContentUtil.addPrimeTracksInDb(this.mDb, this.mAsins);
        getContext().getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        AddRemovePrimeManager.getInstance(getContext()).queueTracksToAdd(this.mAsins);
        if (iAddPrimeTracksInDb != 0) {
            return 1;
        }
        SyncService.startSync(getContext(), 1024);
        final HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).postDelayed(new Runnable() { // from class: com.amazon.mp3.library.job.AddPrimeTrackJob.1
            @Override // java.lang.Runnable
            public void run() {
                SyncService.startSync(AddPrimeTrackJob.this.getContext(), 18);
                handlerThread.quit();
            }
        }, 4000L);
        return 1;
    }

    public int getTracksAddedCount() {
        return this.mAsins.size();
    }
}
