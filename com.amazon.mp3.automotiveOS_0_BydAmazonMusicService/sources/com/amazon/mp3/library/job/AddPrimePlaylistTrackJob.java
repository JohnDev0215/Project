package com.amazon.mp3.library.job;

import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.cta.AddRemovePrimeManager;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class AddPrimePlaylistTrackJob extends Job {
    private final String mAsin;

    public AddPrimePlaylistTrackJob(String asin) {
        this.mAsin = asin;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        new PrimePlaylistDatabaseManager(getContext()).addTrackToLibrary(this.mAsin);
        getContext().getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        AddRemovePrimeManager.getInstance(getContext()).queueTrackToAdd(this.mAsin);
        return 1;
    }
}
