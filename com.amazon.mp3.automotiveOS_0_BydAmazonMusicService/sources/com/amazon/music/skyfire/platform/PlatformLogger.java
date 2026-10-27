package com.amazon.music.skyfire.platform;

import co.touchlab.stately.concurrency.AtomicBoolean;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: Logger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/skyfire/platform/PlatformLogger;", "", "()V", "enabled", "Lco/touchlab/stately/concurrency/AtomicBoolean;", "getEnabled", "()Lco/touchlab/stately/concurrency/AtomicBoolean;", "setEnabled", "(Lco/touchlab/stately/concurrency/AtomicBoolean;)V", "logDebug", "", ParserUtil.TAG_QUERY_PARAM_NAME, "", "message", "logError", "exception", "", "logWarning", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PlatformLogger {
    private AtomicBoolean enabled = new AtomicBoolean(true);

    public final AtomicBoolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(AtomicBoolean atomicBoolean) {
        Intrinsics.checkNotNullParameter(atomicBoolean, "<set-?>");
        this.enabled = atomicBoolean;
    }

    public final void logDebug(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (this.enabled.getValue()) {
            LoggerFactory.getLogger(tag).debug(message);
        }
    }

    public final void logWarning(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (this.enabled.getValue()) {
            LoggerFactory.getLogger(tag).warn(message);
        }
    }

    public final void logError(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        if (this.enabled.getValue()) {
            LoggerFactory.getLogger(tag).error(message);
        }
    }

    public final void logError(String tag, String message, Throwable exception) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (this.enabled.getValue()) {
            LoggerFactory.getLogger(tag).error(message, exception);
        }
    }
}
