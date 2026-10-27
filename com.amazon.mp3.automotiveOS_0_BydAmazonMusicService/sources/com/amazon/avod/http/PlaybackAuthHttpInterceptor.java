package com.amazon.avod.http;

import com.amazon.avod.http.PlaybackAuthProvider;
import com.amazon.bolthttp.HttpInterceptor;
import com.amazon.bolthttp.policy.NetworkContext;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;

/* JADX INFO: compiled from: PlaybackAuthHttpInterceptor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/avod/http/PlaybackAuthHttpInterceptor;", "Lcom/amazon/bolthttp/HttpInterceptor;", "authProvider", "Lcom/amazon/avod/http/PlaybackAuthProvider;", "sessionContext", "", "", "(Lcom/amazon/avod/http/PlaybackAuthProvider;Ljava/util/Map;)V", "addHeadersFromAuthContext", "", "builder", "Lokhttp3/Headers$Builder;", "addRetryCountHeader", "context", "Lcom/amazon/bolthttp/policy/NetworkContext;", "beforeExecute", "params", "Lcom/amazon/bolthttp/HttpInterceptor$Params;", "networkContext", "ATVPlaybackFramework_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackAuthHttpInterceptor implements HttpInterceptor {
    private final PlaybackAuthProvider authProvider;
    private final Map<String, String> sessionContext;

    public PlaybackAuthHttpInterceptor(PlaybackAuthProvider authProvider, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        this.authProvider = authProvider;
        this.sessionContext = map;
    }

    @Override // com.amazon.bolthttp.HttpInterceptor
    public void beforeExecute(HttpInterceptor.Params params, NetworkContext networkContext) throws IOException {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(networkContext, "networkContext");
        Preconditions.checkNotNull(params, "params", new Object[0]);
        Preconditions.checkNotNull(networkContext, "context", new Object[0]);
        Headers.Builder headersBuilder = params.getHeadersBuilder();
        Intrinsics.checkNotNullExpressionValue(headersBuilder, "params.headersBuilder");
        addRetryCountHeader(headersBuilder, networkContext);
        addHeadersFromAuthContext(headersBuilder);
        params.setHeaders(headersBuilder.build());
    }

    private final void addRetryCountHeader(Headers.Builder builder, NetworkContext context) {
        builder.set("x-retry-count", String.valueOf(context.getRequestContext().getAttemptCount() - 1));
    }

    private final void addHeadersFromAuthContext(Headers.Builder builder) throws PlaybackAuthProvider.InvalidSessionContextException {
        Map<String, String> map = this.sessionContext;
        if (map == null) {
            return;
        }
        Map<String, String> headers = this.authProvider.getHeaders(map);
        Intrinsics.checkNotNullExpressionValue(headers, "authProvider.getHeaders(sessionContext)");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.set(entry.getKey(), entry.getValue());
        }
    }
}
