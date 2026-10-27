package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class StorageConfiguration {
    private static final String TAG = "StorageConfiguration";
    private long mCheckBatchOpenTimeMillis;
    private long mExpiryTimeMillis;
    private long mMaxBatchEntries;
    private long mMaxBatchOpenTimeMillis;
    private long mMaxBatchSizeBytes;
    private long mMaxNumberOfBatchFiles;
    private long mMaxStorageSpaceBytes;
    private long mPurgePeriodMillis;
    private long mTransmissionPeriodMillis;

    public StorageConfiguration(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        if (isValidInput(j, j2, j3, j4, j5, j6, j7, j8, j9)) {
            this.mMaxBatchOpenTimeMillis = j;
            this.mCheckBatchOpenTimeMillis = j2;
            this.mMaxBatchEntries = j3;
            this.mMaxBatchSizeBytes = j4;
            this.mMaxStorageSpaceBytes = j5;
            this.mMaxNumberOfBatchFiles = j6;
            this.mExpiryTimeMillis = j7;
            this.mPurgePeriodMillis = j8;
            this.mTransmissionPeriodMillis = j9;
        }
    }

    private boolean isValidInput(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        if (j <= 0) {
            Log.e(TAG, String.format("Invalid maxBatchOpenTimeMillis: %d.", Long.valueOf(j)));
            return false;
        }
        if (j2 <= 0) {
            Log.e(TAG, String.format("Invalid checkBatchOpenTimeMillis: %d.", Long.valueOf(j2)));
            return false;
        }
        if (j3 <= 0) {
            Log.e(TAG, String.format("Invalid maxBatchEntries: %d.", Long.valueOf(j3)));
            return false;
        }
        if (j4 <= 0) {
            Log.e(TAG, String.format("Invalid maxBatchSizeBytes: %d.", Long.valueOf(j4)));
            return false;
        }
        if (j5 <= 0) {
            Log.e(TAG, String.format("Invalid maxStorageSpaceBytes: %d.", Long.valueOf(j5)));
            return false;
        }
        if (j6 <= 0) {
            Log.e(TAG, String.format("Invalid maxNumberOfBatchFiles: %d.", Long.valueOf(j6)));
            return false;
        }
        if (j7 <= 0) {
            Log.e(TAG, String.format("Invalid expiryTimeMillis: %d.", Long.valueOf(j7)));
            return false;
        }
        if (j8 <= 0) {
            Log.e(TAG, String.format("Invalid purgePeriodMillis: %d.", Long.valueOf(j8)));
            return false;
        }
        if (j9 > 0) {
            return true;
        }
        Log.e(TAG, String.format("Invalid transmissionPeriodMillis: %d.", Long.valueOf(j9)));
        return false;
    }

    public long getMaxBatchOpenTimeMillis() {
        return this.mMaxBatchOpenTimeMillis;
    }

    public long getCheckBatchOpenTimeMillis() {
        return this.mCheckBatchOpenTimeMillis;
    }

    public long getMaxBatchEntries() {
        return this.mMaxBatchEntries;
    }

    public long getMaxBatchSizeBytes() {
        return this.mMaxBatchSizeBytes;
    }

    public long getMaxStorageSpaceBytes() {
        return this.mMaxStorageSpaceBytes;
    }

    public long getMaxNumberOfBatchFiles() {
        return this.mMaxNumberOfBatchFiles;
    }

    public long getExpiryTimeMillis() {
        return this.mExpiryTimeMillis;
    }

    public long getPurgePeriodMillis() {
        return this.mPurgePeriodMillis;
    }

    public long getTransmissionPeriodMillis() {
        return this.mTransmissionPeriodMillis;
    }
}
