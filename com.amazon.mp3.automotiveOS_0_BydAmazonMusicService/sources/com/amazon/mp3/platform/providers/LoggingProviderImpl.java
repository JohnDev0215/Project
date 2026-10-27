package com.amazon.mp3.platform.providers;

import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.providers.LoggingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoggingProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/platform/providers/LoggingProviderImpl;", "Lcom/amazon/music/platform/providers/LoggingProvider;", "", ParserUtil.TAG_QUERY_PARAM_NAME, "message", "", "logDebug", "logInfo", "logWarning", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LoggingProviderImpl implements LoggingProvider {
    @Override // com.amazon.music.platform.providers.LoggingProvider
    public void logDebug(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.debug(tag, message);
    }

    @Override // com.amazon.music.platform.providers.LoggingProvider
    public void logInfo(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.info(tag, message);
    }

    @Override // com.amazon.music.platform.providers.LoggingProvider
    public void logWarning(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(message, "message");
        Log.warning(tag, message);
    }
}
