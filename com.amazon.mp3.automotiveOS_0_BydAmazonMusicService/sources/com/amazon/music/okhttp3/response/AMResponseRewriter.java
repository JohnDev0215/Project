package com.amazon.music.okhttp3.response;

import com.amazon.music.okhttp3.policy.AMPriorityPolicy;
import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: AMResponseRewriter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/okhttp3/response/AMResponseRewriter;", "", "rewrite", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "prioritizer", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer;", "response", "exception", "Ljava/lang/Exception;", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface AMResponseRewriter {
    Response rewrite(Request request, AMPriorityPolicy.Prioritizer prioritizer, Response response, Exception exception) throws IOException;
}
