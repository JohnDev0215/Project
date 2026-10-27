package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.DbDiagnostic;
import com.amazon.mp3.library.service.sync.SyncOperation;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
final class CheckAndFixDatabaseContentOperation extends SyncOperation {
    private static final long CHECK_FREQUENCY_MS = TimeUnit.DAYS.toMillis(1);
    private static final String TAG = "CheckAndFixDatabaseContentOperation";
    private final SyncOperationScheduler operationScheduler;

    CheckAndFixDatabaseContentOperation(final Context context, final SQLiteDatabase db) {
        super(context, db);
        this.operationScheduler = new SyncOperationScheduler(context, TAG, CHECK_FREQUENCY_MS);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() throws SyncOperation.AccountStatusException {
        if (!this.operationScheduler.isTimeToRun()) {
            return 0;
        }
        if (!new DbDiagnostic(getDatabase()).performDiagnosticAndFixFoundIssues()) {
            return 5;
        }
        this.operationScheduler.updateLastCheckCheckpoint();
        return 0;
    }
}
