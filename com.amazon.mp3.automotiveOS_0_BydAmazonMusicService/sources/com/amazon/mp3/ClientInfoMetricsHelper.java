package com.amazon.mp3;

import android.content.Context;
import android.os.Handler;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.mp3.service.metrics.InstallSource;
import com.amazon.mp3.service.metrics.MetricsLogger;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class ClientInfoMetricsHelper {
    private static final long CLIENT_INFO_EVENT_DELAY_MS = TimeUnit.SECONDS.toMillis(50);
    private Context mContext;
    private Handler mDefaultHandler;
    private boolean mIsClientInfoMetricSent;
    private Runnable mScheduledRunnable;

    public ClientInfoMetricsHelper(final Context context, final Handler sDefaultHandler) {
        this.mContext = context;
        this.mDefaultHandler = sDefaultHandler;
    }

    public void sendClientInfoMetricForApplicationStart() {
        cancelPreviouslyScheduledRunnable();
        this.mScheduledRunnable = getClientInfoRunnableForApplicationStart();
        scheduleRunnable();
    }

    public void sendClientInfoMetricForCampaignMetrics() {
        cancelPreviouslyScheduledRunnable();
        this.mScheduledRunnable = getClientInfoRunnableForCampaignMetrics();
        scheduleRunnable();
    }

    private void scheduleRunnable() {
        this.mDefaultHandler.postDelayed(this.mScheduledRunnable, CLIENT_INFO_EVENT_DELAY_MS);
    }

    private void cancelPreviouslyScheduledRunnable() {
        Runnable runnable = this.mScheduledRunnable;
        if (runnable == null) {
            return;
        }
        this.mDefaultHandler.removeCallbacks(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean areNotificationsEnabled() {
        return NotificationManagerCompat.from(this.mContext).areNotificationsEnabled();
    }

    private Runnable getClientInfoRunnableForApplicationStart() {
        return new Runnable() { // from class: com.amazon.mp3.ClientInfoMetricsHelper.1
            @Override // java.lang.Runnable
            public void run() {
                MetricsLogger.clientInfo(ClientInfoMetricsHelper.this.mContext, InstallSource.determineSource(ClientInfoMetricsHelper.this.mContext), true, ClientInfoMetricsHelper.this.areNotificationsEnabled());
                ClientInfoMetricsHelper.this.mIsClientInfoMetricSent = true;
            }
        };
    }

    private Runnable getClientInfoRunnableForCampaignMetrics() {
        return new Runnable() { // from class: com.amazon.mp3.ClientInfoMetricsHelper.3
            @Override // java.lang.Runnable
            public void run() {
                MetricsLogger.clientInfo(ClientInfoMetricsHelper.this.mContext, InstallSource.determineSource(ClientInfoMetricsHelper.this.mContext), !ClientInfoMetricsHelper.this.mIsClientInfoMetricSent, ClientInfoMetricsHelper.this.areNotificationsEnabled());
                ClientInfoMetricsHelper.this.mIsClientInfoMetricSent = true;
            }
        };
    }
}
