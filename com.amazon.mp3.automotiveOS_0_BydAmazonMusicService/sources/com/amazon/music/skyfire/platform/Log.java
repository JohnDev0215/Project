package com.amazon.music.skyfire.platform;

import co.touchlab.stately.concurrency.AtomicBoolean;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Logger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\"\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0016\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/platform/Log;", "", "()V", "value", "Lco/touchlab/stately/concurrency/AtomicBoolean;", "enabled", "getEnabled", "()Lco/touchlab/stately/concurrency/AtomicBoolean;", "setEnabled", "(Lco/touchlab/stately/concurrency/AtomicBoolean;)V", "platformLogger", "Lcom/amazon/music/skyfire/platform/PlatformLogger;", "debug", "", ParserUtil.TAG_QUERY_PARAM_NAME, "", "message", "error", "exception", "", "warning", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Log {
    public static final Log INSTANCE = new Log();
    private static final PlatformLogger platformLogger = new PlatformLogger();

    private Log() {
    }

    public final AtomicBoolean getEnabled() {
        return platformLogger.getEnabled();
    }

    public final void setEnabled(AtomicBoolean value) {
        Intrinsics.checkNotNullParameter(value, "value");
        platformLogger.setEnabled(value);
    }

    public final void debug(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        platformLogger.logDebug(tag, message);
    }

    public final void warning(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        platformLogger.logWarning(tag, message);
    }

    public static /* synthetic */ void error$default(Log log, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        log.error(str, str2, th);
    }

    public final void error(String tag, String message, Throwable exception) {
        Unit unit;
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (exception != null) {
            platformLogger.logError(tag, message, exception);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            platformLogger.logError(tag, message);
        }
    }
}
