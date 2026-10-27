package com.amazon.mp3.library.service.sync;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.lyrics.LyricsDatabase;
import com.amazon.mp3.lyrics.LyricsManager;
import com.amazon.mp3.lyrics.exception.NetworkException;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsSyncOperation extends SyncOperation {
    private static final String TAG = "LyricsSyncOperation";

    public LyricsSyncOperation(Context context) {
        super(context, null);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws Throwable {
        String str = TAG;
        Log.info(str, "Starting lyrics sync");
        Context context = getContext();
        try {
            new LyricsManager(context.getApplicationContext(), new LyricsDatabase(context, AccountDetailUtil.get(context).getHomeMarketPlaceId())).updateLyrics();
            Log.info(str, "Lyrics sync is now complete");
            return 0;
        } catch (NetworkException unused) {
            Log.info(TAG, "Lyrics sync failed.");
            return 0;
        }
    }
}
