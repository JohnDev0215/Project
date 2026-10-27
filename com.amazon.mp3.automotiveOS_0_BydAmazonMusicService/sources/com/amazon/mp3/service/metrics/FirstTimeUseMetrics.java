package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public interface FirstTimeUseMetrics {

    public enum Source {
        STORE,
        CLOUD,
        DEVICE
    }

    void recordFirstAppOpen();

    void recordFirstSignInAttempt(Source source);

    void recordFirstSuccessfulSignIn(Source source);
}
