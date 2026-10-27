package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Date;

/* JADX INFO: loaded from: classes6.dex */
public class ConfigMetadataClient {
    static final Date LAST_FETCH_TIME_NO_FETCH_YET = new Date(-1);

    @VisibleForTesting
    static final Date NO_BACKOFF_TIME = new Date(-1);
    private final SharedPreferences frcMetadata;
    private final Object frcInfoLock = new Object();
    private final Object backoffMetadataLock = new Object();

    public ConfigMetadataClient(SharedPreferences sharedPreferences) {
        this.frcMetadata = sharedPreferences;
    }

    public long getFetchTimeoutInSeconds() {
        return this.frcMetadata.getLong("fetch_timeout_in_seconds", 60L);
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.frcMetadata.getLong("minimum_fetch_interval_in_seconds", ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS);
    }

    int getLastFetchStatus() {
        return this.frcMetadata.getInt("last_fetch_status", 0);
    }

    Date getLastSuccessfulFetchTime() {
        return new Date(this.frcMetadata.getLong("last_fetch_time_in_millis", -1L));
    }

    @Nullable
    String getLastFetchETag() {
        return this.frcMetadata.getString("last_fetch_etag", null);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfoImpl firebaseRemoteConfigInfoImplBuild;
        synchronized (this.frcInfoLock) {
            long j = this.frcMetadata.getLong("last_fetch_time_in_millis", -1L);
            int i = this.frcMetadata.getInt("last_fetch_status", 0);
            firebaseRemoteConfigInfoImplBuild = FirebaseRemoteConfigInfoImpl.newBuilder().withLastFetchStatus(i).withLastSuccessfulFetchTimeInMillis(j).withConfigSettings(new FirebaseRemoteConfigSettings.Builder().setFetchTimeoutInSeconds(this.frcMetadata.getLong("fetch_timeout_in_seconds", 60L)).setMinimumFetchIntervalInSeconds(this.frcMetadata.getLong("minimum_fetch_interval_in_seconds", ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS)).build()).build();
        }
        return firebaseRemoteConfigInfoImplBuild;
    }

    void updateLastFetchAsSuccessfulAt(Date date) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt("last_fetch_status", -1).putLong("last_fetch_time_in_millis", date.getTime()).apply();
        }
    }

    void updateLastFetchAsFailed() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt("last_fetch_status", 1).apply();
        }
    }

    void updateLastFetchAsThrottled() {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putInt("last_fetch_status", 2).apply();
        }
    }

    void setLastFetchETag(String str) {
        synchronized (this.frcInfoLock) {
            this.frcMetadata.edit().putString("last_fetch_etag", str).apply();
        }
    }

    BackoffMetadata getBackoffMetadata() {
        BackoffMetadata backoffMetadata;
        synchronized (this.backoffMetadataLock) {
            backoffMetadata = new BackoffMetadata(this.frcMetadata.getInt("num_failed_fetches", 0), new Date(this.frcMetadata.getLong("backoff_end_time_in_millis", -1L)));
        }
        return backoffMetadata;
    }

    void setBackoffMetadata(int i, Date date) {
        synchronized (this.backoffMetadataLock) {
            this.frcMetadata.edit().putInt("num_failed_fetches", i).putLong("backoff_end_time_in_millis", date.getTime()).apply();
        }
    }

    void resetBackoff() {
        setBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    static class BackoffMetadata {
        private Date backoffEndTime;
        private int numFailedFetches;

        BackoffMetadata(int i, Date date) {
            this.numFailedFetches = i;
            this.backoffEndTime = date;
        }

        int getNumFailedFetches() {
            return this.numFailedFetches;
        }

        Date getBackoffEndTime() {
            return this.backoffEndTime;
        }
    }
}
