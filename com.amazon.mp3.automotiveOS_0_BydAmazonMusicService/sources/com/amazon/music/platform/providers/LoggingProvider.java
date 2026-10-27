package com.amazon.music.platform.providers;

import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;

/* JADX INFO: compiled from: LoggingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/providers/LoggingProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "", ParserUtil.TAG_QUERY_PARAM_NAME, "message", "", "logWarning", "logDebug", "logInfo", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface LoggingProvider extends InterfaceProvider {
    void logDebug(String tag, String message);

    void logInfo(String tag, String message);

    void logWarning(String tag, String message);
}
