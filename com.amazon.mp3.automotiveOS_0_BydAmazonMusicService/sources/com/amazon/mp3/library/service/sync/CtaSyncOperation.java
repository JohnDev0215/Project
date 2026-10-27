package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CtaSyncOperation extends SyncOperation {
    private static final String TAG = "CtaSyncOperation";
    private CTAPrimeCache mCtaPrimeCache;
    private long mNextCacheClearTime;
    private SharedPreferences mSharedPreferences;

    CtaSyncOperation(final Context context, final SQLiteDatabase db) {
        super(context, db);
        this.mNextCacheClearTime = Long.MIN_VALUE;
        this.mCtaPrimeCache = new CTAPrimeCache(getContext());
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3_cta", 0);
        this.mSharedPreferences = sharedPreferences;
        this.mNextCacheClearTime = sharedPreferences.getLong("next_clear_time", 0L);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str = TAG;
        Log.debug(str, "Next clear in " + (this.mNextCacheClearTime - jCurrentTimeMillis) + "ms");
        long j = this.mNextCacheClearTime - jCurrentTimeMillis;
        if (j < 0 || j >= ((long) this.mCtaPrimeCache.getCacheItemMaxLifetime())) {
            this.mNextCacheClearTime = Math.max(this.mCtaPrimeCache.clearExpiredTracks("album_asin"), 3600000 + jCurrentTimeMillis);
            SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
            editorEdit.putLong("next_clear_time", this.mNextCacheClearTime);
            editorEdit.commit();
            Log.debug(str, "Scheduling next cache clear in " + (this.mNextCacheClearTime - jCurrentTimeMillis) + "ms");
        }
        return 0;
    }
}
