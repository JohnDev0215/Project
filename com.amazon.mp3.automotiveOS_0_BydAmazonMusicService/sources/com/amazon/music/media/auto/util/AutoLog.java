package com.amazon.music.media.auto.util;

import androidx.core.app.NotificationCompat;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AutoLog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J \u0010\t\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/media/auto/util/AutoLog;", "", "", ParserUtil.TAG_QUERY_PARAM_NAME, NotificationCompat.CATEGORY_MESSAGE, "", "error", "", "tr", "warning", "debug", "verbose", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class AutoLog {
    public static final AutoLog INSTANCE = new AutoLog();

    private AutoLog() {
    }

    public final void error(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).error(msg);
    }

    public final void error(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).error(msg, tr);
    }

    public final void warning(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).warn(msg);
    }

    public final void warning(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).warn(msg, tr);
    }

    public final void debug(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).debug(msg);
    }

    public final void verbose(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoggerFactory.getLogger(tag).trace(msg);
    }
}
