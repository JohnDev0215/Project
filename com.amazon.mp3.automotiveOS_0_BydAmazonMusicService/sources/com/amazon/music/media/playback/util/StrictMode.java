package com.amazon.music.media.playback.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class StrictMode {
    private static boolean unitTestMode;
    private static final Logger logger = LoggerFactory.getLogger(StrictMode.class.getSimpleName());
    private static boolean strictMode = false;
    private static Timer noOpTimer = new Timer() { // from class: com.amazon.music.media.playback.util.StrictMode.1
        @Override // java.lang.AutoCloseable
        public void close() {
        }
    };

    public interface Timer extends AutoCloseable {
    }

    public static boolean isStrictMode() {
        return strictMode;
    }

    public static void crashIfStrict(String str) {
        crashIfStrict(str, null);
    }

    public static void crashIfStrict(Throwable th) {
        crashIfStrict(null, th);
    }

    public static void crashIfStrict(String str, Throwable th) {
        final AssertionError assertionError = new AssertionError(str, th);
        if (!StringUtils.isEmpty(str) || th != null) {
            logger.error(str, (Throwable) assertionError);
        }
        if (strictMode) {
            if (!unitTestMode) {
                ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.util.StrictMode.2
                    @Override // java.lang.Runnable
                    public void run() {
                        throw assertionError;
                    }
                });
                throw assertionError;
            }
            throw assertionError;
        }
    }

    public static <T> T validateNotNull(T t, String str) {
        if (t == null) {
            validationFailed(null, str);
        }
        return t;
    }

    public static void validationFailed(Object obj, String str) {
        crashIfStrict(str, obj == null ? new NullPointerException(str) : new IllegalArgumentException(str));
    }

    private StrictMode() {
    }
}
