package com.bumptech.glide.request;

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

        boolean isComplete() {
            return this.isComplete;
        }
    }

    void a(c cVar);

    boolean b();

    boolean c(c cVar);

    boolean f(c cVar);

    RequestCoordinator g();

    void i(c cVar);

    boolean k(c cVar);
}
