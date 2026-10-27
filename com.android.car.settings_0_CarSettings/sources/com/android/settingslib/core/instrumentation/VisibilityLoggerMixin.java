package com.android.settingslib.core.instrumentation;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnAttach;

/* JADX INFO: loaded from: classes6.dex */
public class VisibilityLoggerMixin implements LifecycleObserver, OnAttach {
    private static final String TAG = "VisibilityLoggerMixin";
    private long mCreationTimestamp;
    private final int mMetricsCategory;
    private MetricsFeatureProvider mMetricsFeature;
    private int mSourceMetricsCategory = 0;
    private long mVisibleTimestamp;

    public VisibilityLoggerMixin(int metricsCategory, MetricsFeatureProvider metricsFeature) {
        this.mMetricsCategory = metricsCategory;
        this.mMetricsFeature = metricsFeature;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnAttach
    public void onAttach() {
        this.mCreationTimestamp = SystemClock.elapsedRealtime();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (this.mMetricsFeature == null || this.mMetricsCategory == 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.mVisibleTimestamp = jElapsedRealtime;
        long j = this.mCreationTimestamp;
        if (j != 0) {
            int elapse = (int) (jElapsedRealtime - j);
            this.mMetricsFeature.visible(null, this.mSourceMetricsCategory, this.mMetricsCategory, elapse);
        } else {
            this.mMetricsFeature.visible(null, this.mSourceMetricsCategory, this.mMetricsCategory, 0);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mCreationTimestamp = 0L;
        if (this.mMetricsFeature != null && this.mMetricsCategory != 0) {
            int elapse = (int) (SystemClock.elapsedRealtime() - this.mVisibleTimestamp);
            this.mMetricsFeature.hidden(null, this.mMetricsCategory, elapse);
        }
    }

    public void writeElapsedTimeMetric(int action, String key) {
        if (this.mMetricsFeature != null && this.mMetricsCategory != 0 && this.mCreationTimestamp != 0) {
            int elapse = (int) (SystemClock.elapsedRealtime() - this.mCreationTimestamp);
            this.mMetricsFeature.action(0, action, this.mMetricsCategory, key, elapse);
        }
    }

    public void setSourceMetricsCategory(Activity activity) {
        Intent intent;
        if (this.mSourceMetricsCategory != 0 || activity == null || (intent = activity.getIntent()) == null) {
            return;
        }
        this.mSourceMetricsCategory = intent.getIntExtra(MetricsFeatureProvider.EXTRA_SOURCE_METRICS_CATEGORY, 0);
    }
}
