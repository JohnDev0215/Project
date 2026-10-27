package com.bumptech.glide.request;

import spi.jm;

/* JADX INFO: loaded from: classes.dex */
public interface RequestCoordinator {

    public enum RequestState {
        RUNNING(false),
        PAUSED(false),
        CLEARED(false),
        SUCCESS(true),
        FAILED(true);

        private final boolean isComplete;

        RequestState(boolean z) {
            this.isComplete = z;
        }

        public boolean isComplete() {
            return this.isComplete;
        }
    }

    boolean a();

    void b(jm jmVar);

    void d(jm jmVar);

    boolean e(jm jmVar);

    boolean g(jm jmVar);

    RequestCoordinator getRoot();

    boolean h(jm jmVar);
}
