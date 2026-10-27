package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchBinarySerializer;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchTextSerializer;

/* JADX INFO: loaded from: classes3.dex */
public class UploadConfiguration {
    private static final String TAG = "UploadConfiguration";
    private int mConnectTimeoutMillis;
    private String mIonFormat;
    private String mKpiRegion;
    private int mReadTimeoutMillis;
    private String mUrlEndpoint;
    private long mWakeLockTimeoutMillis;

    public UploadConfiguration(String str, String str2, int i, int i2, long j, String str3) {
        if (isValidInput(str, str2, i, i2, j, str3)) {
            this.mUrlEndpoint = str;
            this.mIonFormat = str2;
            this.mConnectTimeoutMillis = i;
            this.mReadTimeoutMillis = i2;
            this.mWakeLockTimeoutMillis = j;
            this.mKpiRegion = str3;
        }
    }

    private boolean isValidInput(String str, String str2, long j, long j2, long j3, String str3) {
        if (str == null || str.length() == 0) {
            Log.e(TAG, String.format("Invalid urlEndpoint: %s.", str));
            return false;
        }
        if (str2 == null || str2.length() == 0) {
            Log.e(TAG, String.format("Invalid ionFormat: %s.", str2));
            return false;
        }
        if (j <= 0) {
            Log.e(TAG, String.format("Invalid connectTimeoutMillis: %d.", Long.valueOf(j)));
            return false;
        }
        if (j2 <= 0) {
            Log.e(TAG, String.format("Invalid readTimeoutMillis: %d.", Long.valueOf(j2)));
            return false;
        }
        if (j3 <= 0) {
            Log.e(TAG, String.format("Invalid wakeLockTimeoutMillis: %d.", Long.valueOf(j3)));
            return false;
        }
        if (str3 != null && str3.length() != 0) {
            return true;
        }
        Log.e(TAG, String.format("Invalid kpiRegion: %s.", str3));
        return false;
    }

    public String getUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    public String getIonFormat() {
        return this.mIonFormat;
    }

    public int getConnectTimeoutMillis() {
        return this.mConnectTimeoutMillis;
    }

    public int getReadTimeoutMillis() {
        return this.mReadTimeoutMillis;
    }

    public long getWakeLockTimeoutMillis() {
        return this.mWakeLockTimeoutMillis;
    }

    public String getKPIRegion() {
        return this.mKpiRegion;
    }

    public MetricBatchSerializer createMetricBatchSerializer() {
        if (this.mIonFormat.equals("text/x-amzn-ion")) {
            return new MetricBatchTextSerializer();
        }
        return new MetricBatchBinarySerializer();
    }
}
