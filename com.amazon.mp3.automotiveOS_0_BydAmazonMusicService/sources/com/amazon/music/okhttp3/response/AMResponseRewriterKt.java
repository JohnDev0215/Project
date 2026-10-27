package com.amazon.music.okhttp3.response;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: AMResponseRewriter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"failureResponse", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "exception", "Ljava/lang/Exception;", "DMMOkHttpClient_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class AMResponseRewriterKt {
    public static final Response failureResponse(Request request, Exception exc) throws Exception {
        Intrinsics.checkNotNullParameter(request, "request");
        if (exc instanceof IOException) {
            throw exc;
        }
        if (exc != null) {
            throw new IOException(exc);
        }
        throw new IOException("Unknown cause");
    }
}
