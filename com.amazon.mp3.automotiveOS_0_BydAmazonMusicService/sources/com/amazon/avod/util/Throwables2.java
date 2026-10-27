package com.amazon.avod.util;

import com.amazon.music.destination.parser.ParserUtil;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Throwables2 {
    private static final Set<WeakCrashListener> LISTENERS = Collections.synchronizedSet(new HashSet());
    private static volatile boolean sIsDebugBuild = false;
    private static volatile boolean sSuppressLogging = false;
    private static volatile boolean sWeakCrashesEnabled = true;

    public interface WeakCrashListener {
        void onWeakCrash(@Nonnull String str, @Nonnull String str2, @Nullable Throwable th);
    }

    public static void propagateIfWeakMode(@Nonnull Throwable th) {
        Preconditions.checkNotNull(th, "throwable");
        propagateIfWeakModeInternal(true, "AmazonVideo.wtf", th.getMessage(), th);
    }

    public static void propagateIfWeakMode(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, ParserUtil.TAG_QUERY_PARAM_NAME);
        Preconditions.checkNotNull(str2, "message");
        propagateIfWeakModeInternal(true, str, str2, null);
    }

    public static void propagateIfWeakMode(@Nonnull String str, @Nonnull Throwable th) {
        Preconditions.checkNotNull(str, ParserUtil.TAG_QUERY_PARAM_NAME);
        Preconditions.checkNotNull(th, "throwable");
        propagateIfWeakModeInternal(true, str, th.getMessage(), th);
    }

    public static void propagateIfWeakMode(boolean z, @Nonnull String str, @Nonnull String str2, @Nonnull Throwable th) {
        Preconditions.checkNotNull(str, ParserUtil.TAG_QUERY_PARAM_NAME);
        Preconditions.checkNotNull(str2, "message");
        Preconditions.checkNotNull(th, "throwable");
        propagateIfWeakModeInternal(z, str, str2, th);
    }

    private static void propagateIfWeakModeInternal(boolean z, @Nonnull String str, @Nullable String str2, @Nullable Throwable th) {
        if (sIsDebugBuild && sWeakCrashesEnabled) {
            throw new RuntimeException(String.format(Locale.US, "%s: %s", str, str2), th);
        }
        notifyWeakCrashListeners(str, str2, th);
        if (sSuppressLogging) {
            return;
        }
        if (z) {
            DLog.wtf(str, str2, th);
        } else {
            DLog.exceptionf(th, str, str2);
        }
    }

    private static void notifyWeakCrashListeners(String str, String str2, Throwable th) {
        Iterator<WeakCrashListener> it = LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().onWeakCrash(str, str2, th);
        }
    }
}
