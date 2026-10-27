package com.apollographql.apollo3.network.ws;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: OkHttpWebSocketEngine.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eB\t\b\u0016¢\u0006\u0004\b\r\u0010\u000fJ)\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/network/ws/DefaultWebSocketEngine;", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "", ImagesContract.URL, "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "open", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lokhttp3/WebSocket$Factory;", "webSocketFactory", "Lokhttp3/WebSocket$Factory;", "<init>", "(Lokhttp3/WebSocket$Factory;)V", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class DefaultWebSocketEngine implements WebSocketEngine {
    private final WebSocket.Factory webSocketFactory;

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkHttpWebSocketEngine.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.DefaultWebSocketEngine", f = "OkHttpWebSocketEngine.kt", i = {0, 0}, l = {74}, m = "open", n = {"messageChannel", "webSocket"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultWebSocketEngine.this.open(null, null, this);
        }
    }

    public DefaultWebSocketEngine(WebSocket.Factory webSocketFactory) {
        Intrinsics.checkNotNullParameter(webSocketFactory, "webSocketFactory");
        this.webSocketFactory = webSocketFactory;
    }

    public DefaultWebSocketEngine() {
        this(new OkHttpClient());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.apollographql.apollo3.network.ws.WebSocketEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object open(java.lang.String r7, java.util.List<com.apollographql.apollo3.api.http.HttpHeader> r8, kotlin.coroutines.Continuation<? super com.apollographql.apollo3.network.ws.WebSocketConnection> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.apollographql.apollo3.network.ws.DefaultWebSocketEngine.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = (com.apollographql.apollo3.network.ws.DefaultWebSocketEngine.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r7 = r0.L$1
            okhttp3.WebSocket r7 = (okhttp3.WebSocket) r7
            java.lang.Object r8 = r0.L$0
            com.apollographql.apollo3.internal.ChannelWrapper r8 = (com.apollographql.apollo3.internal.ChannelWrapper) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7c
        L31:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            com.apollographql.apollo3.internal.ChannelWrapper r9 = new com.apollographql.apollo3.internal.ChannelWrapper
            r2 = 2147483647(0x7fffffff, float:NaN)
            r4 = 6
            r5 = 0
            kotlinx.coroutines.channels.Channel r2 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r2, r5, r5, r4, r5)
            r9.<init>(r2)
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r5, r3, r5)
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder
            r4.<init>()
            okhttp3.Request$Builder r7 = r4.url(r7)
            okhttp3.Headers r8 = com.apollographql.apollo3.network.OkHttpExtensionsKt.toOkHttpHeaders(r8)
            okhttp3.Request$Builder r7 = r7.headers(r8)
            okhttp3.Request r7 = r7.build()
            okhttp3.WebSocket$Factory r8 = r6.webSocketFactory
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$webSocket$1 r4 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$webSocket$1
            r4.<init>()
            okhttp3.WebSocket r7 = r8.newWebSocket(r7, r4)
            r0.L$0 = r9
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r2.await(r0)
            if (r8 != r1) goto L7b
            return r1
        L7b:
            r8 = r9
        L7c:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2 r9 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2
            r9.<init>()
            r8.setInvokeOnClose(r9)
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$3 r9 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$3
            r9.<init>()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.DefaultWebSocketEngine.open(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
