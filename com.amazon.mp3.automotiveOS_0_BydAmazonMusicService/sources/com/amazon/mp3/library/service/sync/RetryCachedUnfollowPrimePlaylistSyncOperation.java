package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class RetryCachedUnfollowPrimePlaylistSyncOperation extends SyncOperation {
    private static final String TAG = "RetryCachedUnfollowPrimePlaylistSyncOperation";

    public RetryCachedUnfollowPrimePlaylistSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        String str = TAG;
        Log.debug(str, "Starting UnfollowPrimePlaylistSyncOperation");
        PrimePlaylistUtil.retryFailedUnfollowPrimePlaylists(this.mContext);
        Log.debug(str, "End of UnfollowPrimePlaylistSyncOperation");
        return 0;
    }
}
