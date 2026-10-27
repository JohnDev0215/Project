package com.amazon.alexa.comms.mediaInsights.service.transport;

import com.google.android.gms.cast.MediaError;
import java.net.HttpRetryException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HttpRetryHelper {
    private static final List<Integer> RETRIABLE_HTTP_RESPONSE_CODES = Arrays.asList(502, 503, 504, 500, 408);

    public static boolean isRetriable(int i) {
        return RETRIABLE_HTTP_RESPONSE_CODES.contains(Integer.valueOf(i));
    }

    public static HttpRetryException getRetriableException(Throwable th) {
        return new HttpRetryException(th.toString(), MediaError.DetailedErrorCode.SMOOTH_MANIFEST);
    }
}
