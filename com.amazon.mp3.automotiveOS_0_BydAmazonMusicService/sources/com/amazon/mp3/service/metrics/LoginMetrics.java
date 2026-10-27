package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public interface LoginMetrics {

    public enum Source {
        STORE,
        CLOUD,
        DEVICE
    }

    void recordLoginAttempt(Source source);
}
