package com.facebook.imagepipeline.instrumentation;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public final class FrescoInstrumenter {

    @Nullable
    private static volatile Instrumenter sInstance;

    public interface Instrumenter {
        @Nullable
        Runnable decorateRunnable(Runnable runnable, String tag);

        boolean isTracing();

        void markFailure(Object token, Throwable th);

        @Nullable
        Object onBeforeSubmitWork(String tag);

        @Nullable
        Object onBeginWork(Object token, @Nullable String tag);

        @Nullable
        void onEndWork(Object token);
    }

    public static boolean isTracing() {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null) {
            return false;
        }
        return instrumenter.isTracing();
    }

    @Nullable
    public static Object onBeforeSubmitWork(@Nullable String tag) {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null || tag == null) {
            return null;
        }
        return instrumenter.onBeforeSubmitWork(tag);
    }

    @Nullable
    public static Object onBeginWork(@Nullable Object token, @Nullable String tag) {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null || token == null) {
            return null;
        }
        return instrumenter.onBeginWork(token, tag);
    }

    public static void onEndWork(@Nullable Object token) {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null || token == null) {
            return;
        }
        instrumenter.onEndWork(token);
    }

    public static void markFailure(@Nullable Object token, Throwable th) {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null || token == null) {
            return;
        }
        instrumenter.markFailure(token, th);
    }

    @Nullable
    public static Runnable decorateRunnable(@Nullable Runnable runnable, @Nullable String tag) {
        Instrumenter instrumenter = sInstance;
        if (instrumenter == null || runnable == null) {
            return runnable;
        }
        if (tag == null) {
            tag = "";
        }
        return instrumenter.decorateRunnable(runnable, tag);
    }
}
