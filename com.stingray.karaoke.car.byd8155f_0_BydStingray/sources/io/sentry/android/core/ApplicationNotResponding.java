package io.sentry.android.core;

/* JADX INFO: loaded from: classes.dex */
final class ApplicationNotResponding extends RuntimeException {
    public final Thread l;

    public ApplicationNotResponding(String str, Thread thread) {
        super(str);
        h6.e.q(thread, "Thread must be provided.");
        this.l = thread;
        setStackTrace(thread.getStackTrace());
    }
}
