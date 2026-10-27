package com.amazon.music.find.service;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistoryServiceException.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0013\b\u0016\u0012\n\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/find/service/SearchHistoryServiceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "exception", "(Ljava/lang/Exception;)V", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchHistoryServiceException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchHistoryServiceException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchHistoryServiceException(Exception exception) {
        super(exception);
        Intrinsics.checkNotNullParameter(exception, "exception");
    }
}
