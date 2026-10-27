package com.amazon.music.explore.metrics;

import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class WatchedLiveStreamFlexEventEmitter implements DefaultLifecycleObserver {
    private static final long EMIT_EVENT_AFTER_MS = 30000;
    private static final String FLEX_EVENT_NAME = "watchedLiveStream";
    private static final long TIMER_INTERVAL_MS = 1000;
    private String artistAsin;
    private String liveStreamId;
    private final MetricsProvider metricsProvider;
    private CountDownTimer timer;
    private long msLeftUntilEmission = 30000;
    private boolean eventEmitted = false;

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(@NonNull LifecycleOwner lifecycleOwner) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(@NonNull LifecycleOwner lifecycleOwner) {
    }

    public WatchedLiveStreamFlexEventEmitter(MetricsProvider metricsProvider) {
        this.metricsProvider = metricsProvider;
    }

    public void setLiveStreamId(String str) {
        this.liveStreamId = str;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
        continueCountdown();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onPause(@NonNull LifecycleOwner lifecycleOwner) {
        pauseCountdown();
    }

    private void continueCountdown() {
        if (this.timer != null || this.eventEmitted) {
            return;
        }
        this.timer = new CountDownTimer(this.msLeftUntilEmission, TIMER_INTERVAL_MS) { // from class: com.amazon.music.explore.metrics.WatchedLiveStreamFlexEventEmitter.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                WatchedLiveStreamFlexEventEmitter.this.msLeftUntilEmission = j;
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                WatchedLiveStreamFlexEventEmitter.this.emitFlexEvent();
            }
        }.start();
    }

    private void pauseCountdown() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitFlexEvent() {
        if (!this.eventEmitted) {
            this.metricsProvider.sendMetricsEvent(new FlexWrapper.Builder().withName(FLEX_EVENT_NAME).withHcStr1(this.liveStreamId).withHcStr2(this.artistAsin).build());
            this.eventEmitted = true;
        }
        pauseCountdown();
    }
}
