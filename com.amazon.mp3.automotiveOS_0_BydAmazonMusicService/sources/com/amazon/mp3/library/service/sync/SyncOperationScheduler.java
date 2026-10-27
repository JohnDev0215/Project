package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Date;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
final class SyncOperationScheduler {
    private final Context context;
    private final long operationFrequencyMs;
    private final String operationName;

    SyncOperationScheduler(final Context context, final String operationName, final long operationFrequencyMs) {
        Validate.notNull(context, "Context should not be null", new Object[0]);
        Validate.notBlank(operationName, "The operation name should not be null or empty.", new Object[0]);
        Validate.isTrue(operationFrequencyMs > 0, "The operation frequency should be more than 0", operationFrequencyMs);
        this.context = context;
        this.operationName = operationName;
        this.operationFrequencyMs = operationFrequencyMs;
    }

    synchronized long getLastCheckCheckpoint() {
        return getSharedPreference().getLong(this.operationName, 0L);
    }

    synchronized void updateLastCheckCheckpoint() {
        setLastCheckCheckpoint(getCurrentTime());
    }

    synchronized void setLastCheckCheckpoint(final long timeMs) {
        SharedPreferences.Editor editorEdit = getSharedPreference().edit();
        editorEdit.putLong(this.operationName, timeMs);
        editorEdit.apply();
    }

    boolean isTimeToRun() {
        return getCurrentTime() - getLastCheckCheckpoint() > this.operationFrequencyMs;
    }

    long getCurrentTime() {
        return new Date().getTime();
    }

    private SharedPreferences getSharedPreference() {
        return this.context.getSharedPreferences("com.amazon.mp3_sync_scheduler", 4);
    }
}
