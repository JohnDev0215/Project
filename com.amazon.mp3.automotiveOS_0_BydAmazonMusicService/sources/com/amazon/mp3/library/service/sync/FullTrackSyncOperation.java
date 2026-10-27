package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.library.sync.FullSyncResponseProcessor;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
class FullTrackSyncOperation extends TrackSyncOperation {
    private static final String TAG = "FullTrackSyncOperation";
    private boolean mShouldClearCheckpoint;
    private int mUpdatedResourceCount;

    FullTrackSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        this.mUpdatedResourceCount = 0;
        this.mShouldClearCheckpoint = true;
    }

    FullTrackSyncOperation(Context context, SQLiteDatabase db, boolean shouldClearCheckpoint) {
        super(context, db);
        this.mUpdatedResourceCount = 0;
        this.mShouldClearCheckpoint = shouldClearCheckpoint;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            Log.info(TAG, "Sync. Cloud is not supported. Finished.");
            return 0;
        }
        return syncTracks();
    }

    private int syncTracks() throws SyncOperation.AccountStatusException {
        try {
            if (this.mShouldClearCheckpoint) {
                SyncService.setSyncLastCheckpoint(getContext(), null);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("format", "CSV_1_0");
            this.mUpdatedResourceCount = new FullSyncResponseProcessor(getContext(), getDatabase()).process(CirrusV3Request.Sync.execute(jSONObject));
            return 0;
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Caught http client exception", e);
            return 2;
        } catch (Exception e2) {
            Log.error(TAG, "Sync. Cannot execute Full Sync operation. Exception:", e2);
            return 3;
        }
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getUpdatedResourceCount() {
        return this.mUpdatedResourceCount;
    }
}
