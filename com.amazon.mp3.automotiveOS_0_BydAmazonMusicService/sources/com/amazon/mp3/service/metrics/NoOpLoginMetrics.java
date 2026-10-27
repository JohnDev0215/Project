package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.LoginMetrics;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpLoginMetrics implements LoginMetrics {
    @Override // com.amazon.mp3.service.metrics.LoginMetrics
    public void recordLoginAttempt(LoginMetrics.Source source) {
    }
}
