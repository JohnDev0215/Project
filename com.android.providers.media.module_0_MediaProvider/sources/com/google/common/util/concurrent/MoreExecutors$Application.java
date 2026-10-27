package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes.dex */
class MoreExecutors$Application {
    MoreExecutors$Application() {
    }

    void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }
}
