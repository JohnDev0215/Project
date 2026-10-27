package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.FirstTimeUseMetrics;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpFirstTimeUseMetrics implements FirstTimeUseMetrics {
    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstAppOpen() {
    }

    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstSignInAttempt(FirstTimeUseMetrics.Source source) {
    }

    @Override // com.amazon.mp3.service.metrics.FirstTimeUseMetrics
    public void recordFirstSuccessfulSignIn(FirstTimeUseMetrics.Source source) {
    }
}
