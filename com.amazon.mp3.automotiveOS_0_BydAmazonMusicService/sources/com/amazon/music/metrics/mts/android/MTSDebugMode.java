package com.amazon.music.metrics.mts.android;

import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.MTSEventDataProvider;
import com.amazon.music.metrics.mts.MTSEventHandler;
import com.amazon.music.metrics.mts.MTSFileConfig;
import java.io.IOException;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSDebugMode {
    private static final String TAG = "MTSDebugMode";
    private MTSEventHandler mDebugEventHandler;
    private MetricsManager mMetricsManager;

    public synchronized void enableDebugMode(MetricsManager metricsManager, MTSEventHandler mTSEventHandler) {
        if (this.mDebugEventHandler == null) {
            this.mDebugEventHandler = mTSEventHandler;
            metricsManager.registerEventHandler(mTSEventHandler);
            this.mMetricsManager = metricsManager;
        } else {
            throw new IllegalStateException("Debug mode can only be enabled for one handler at a time");
        }
    }

    public synchronized void disableDebugMode() {
        MTSEventHandler mTSEventHandler = this.mDebugEventHandler;
        if (mTSEventHandler != null) {
            this.mMetricsManager.deregisterEventHandler(mTSEventHandler);
            try {
                this.mDebugEventHandler.deleteSavedMetrics();
            } catch (IOException e) {
                LoggerFactory.getLogger(TAG).warn("Unable to delete debug file", (Throwable) e);
            }
            this.mDebugEventHandler = null;
            this.mMetricsManager = null;
        } else {
            LoggerFactory.getLogger(TAG).warn("diableDebugMode called when no handler was registered to debug");
        }
    }

    public MTSEventHandler buildMTSDebugHandler(MTSEventDataProvider mTSEventDataProvider, MTSFileConfig mTSFileConfig) throws StorageNotAvailableException {
        return new MTSEventHandler(mTSEventDataProvider, mTSFileConfig);
    }

    public MTSFileConfig buildDebugFileConfig(String str) throws StorageNotAvailableException {
        return new MTSFileConfig(str, 0L);
    }

    public synchronized boolean isDebugEnabled() {
        return this.mDebugEventHandler != null;
    }
}
