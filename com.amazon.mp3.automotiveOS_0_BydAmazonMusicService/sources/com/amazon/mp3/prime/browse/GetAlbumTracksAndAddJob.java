package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.library.fragment.AddAndDownloadAlbumTask;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class GetAlbumTracksAndAddJob extends Job {
    private final AddAndDownloadAlbumTask mAddAndDownloadAlbumTask;
    private final Context mContext;

    public GetAlbumTracksAndAddJob(Context context, String albumAsin) {
        this.mContext = context;
        this.mAddAndDownloadAlbumTask = new AddAndDownloadAlbumTask(context, new PrimeAlbum(null, albumAsin, null, null, null, 0, null, null), false);
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        this.mAddAndDownloadAlbumTask.execute(new Void[0]).get();
        SyncService.waitForSync(this.mContext, 1026);
        return 1;
    }
}
