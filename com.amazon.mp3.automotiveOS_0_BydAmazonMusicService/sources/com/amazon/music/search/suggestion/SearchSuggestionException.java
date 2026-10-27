package com.amazon.music.search.suggestion;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class SearchSuggestionException extends Exception {
    public final Reason reason;

    public enum Reason {
        THREAD_INTERRUPTED,
        ABORTED_TASK_ACCESSED,
        INVALID_URL,
        LISTENER_NOT_REGISTERED,
        UNSUPPORTED_ENCODING
    }

    public SearchSuggestionException(Throwable th) {
        super(th.getMessage());
        if (th instanceof ExecutionException) {
            this.reason = Reason.ABORTED_TASK_ACCESSED;
            return;
        }
        if ((th instanceof URISyntaxException) || (th instanceof MalformedURLException)) {
            this.reason = Reason.INVALID_URL;
        } else if (th instanceof UnsupportedEncodingException) {
            this.reason = Reason.UNSUPPORTED_ENCODING;
        } else {
            this.reason = Reason.THREAD_INTERRUPTED;
        }
    }

    public SearchSuggestionException(Reason reason) {
        this.reason = reason;
    }
}
