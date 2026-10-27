package com.amazon.music.platform.aom;

import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Logger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\"\u0010\t\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/aom/Logger;", "", "", ParserUtil.TAG_QUERY_PARAM_NAME, "message", "", "info", "", "exception", "error", "Lcom/amazon/music/platform/aom/PlatformLogger;", "platformLogger", "Lcom/amazon/music/platform/aom/PlatformLogger;", "<init>", "()V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final class Logger {
    public static final Logger INSTANCE = new Logger();
    private static final PlatformLogger platformLogger = new PlatformLogger();

    private Logger() {
    }

    public final void info(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        platformLogger.logInfo(tag, message);
    }

    public static /* synthetic */ void error$default(Logger logger, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        logger.error(str, str2, th);
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
