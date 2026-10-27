package com.apollographql.apollo3.network.ws;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.DefaultHttpRequestComposer;
import com.apollographql.apollo3.exception.ApolloNetworkException;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SubscriptionWsProtocol.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001dB^\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012.\b\u0002\u0010\b\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0012\u001a\u00020\u0013H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00132\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bH\u0016J \u0010\u0017\u001a\u00020\u0013\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016J \u0010\u001c\u001a\u00020\u0013\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\b\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocol;", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "connectionAcknowledgeTimeoutMs", "", "connectionPayload", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "", "frameType", "Lcom/apollographql/apollo3/network/ws/WsFrameType;", "(Lcom/apollographql/apollo3/network/ws/WebSocketConnection;Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;JLkotlin/jvm/functions/Function1;Lcom/apollographql/apollo3/network/ws/WsFrameType;)V", "Lkotlin/jvm/functions/Function1;", "connectionInit", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleServerMessage", "messageMap", "startOperation", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "stopOperation", "Factory", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SubscriptionWsProtocol extends WsProtocol {
    private final long connectionAcknowledgeTimeoutMs;
    private final Function1<Continuation<? super Map<String, ? extends Object>>, Object> connectionPayload;
    private final WsFrameType frameType;

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionWsProtocol.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol", f = "SubscriptionWsProtocol.kt", i = {0, 0}, l = {31, 38}, m = "connectionInit", n = {"this", "message"}, s = {"L$0", "L$1"})
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
            return SubscriptionWsProtocol.this.connectionInit(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public SubscriptionWsProtocol(WebSocketConnection webSocketConnection, WsProtocol.Listener listener, long j, Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> connectionPayload, WsFrameType frameType) {
        super(webSocketConnection, listener);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(connectionPayload, "connectionPayload");
        Intrinsics.checkNotNullParameter(frameType, "frameType");
        this.connectionAcknowledgeTimeoutMs = j;
        this.connectionPayload = connectionPayload;
        this.frameType = frameType;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.apollographql.apollo3.network.ws.WsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object connectionInit(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1 r0 = (com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1 r0 = new com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L87
        L2c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L34:
            java.lang.Object r2 = r0.L$1
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r4 = r0.L$0
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol r4 = (com.apollographql.apollo3.network.ws.SubscriptionWsProtocol) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L64
        L40:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.Pair[] r8 = new kotlin.Pair[r4]
            r2 = 0
            java.lang.String r5 = "type"
            java.lang.String r6 = "connection_init"
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            r8[r2] = r5
            java.util.Map r2 = kotlin.collections.MapsKt.mutableMapOf(r8)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>>, java.lang.Object> r8 = r7.connectionPayload
            r0.L$0 = r7
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r8 = r8.invoke(r0)
            if (r8 != r1) goto L63
            return r1
        L63:
            r4 = r7
        L64:
            java.util.Map r8 = (java.util.Map) r8
            if (r8 == 0) goto L6d
            java.lang.String r5 = "payload"
            r2.put(r5, r8)
        L6d:
            com.apollographql.apollo3.network.ws.WsFrameType r8 = r4.frameType
            r4.sendMessageMap(r2, r8)
            long r5 = r4.connectionAcknowledgeTimeoutMs
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2 r8 = new com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2
            r2 = 0
            r8.<init>(r2)
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.TimeoutKt.withTimeout(r5, r8, r0)
            if (r8 != r1) goto L87
            return r1
        L87:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.connectionInit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionWsProtocol.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2", f = "SubscriptionWsProtocol.kt", i = {}, l = {39}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SubscriptionWsProtocol.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SubscriptionWsProtocol subscriptionWsProtocol = SubscriptionWsProtocol.this;
                this.label = 1;
                obj = subscriptionWsProtocol.receiveMessageMap(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Map map = (Map) obj;
            Object obj2 = map.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            if (Intrinsics.areEqual(obj2, "connection_ack")) {
                return Unit.INSTANCE;
            }
            if (Intrinsics.areEqual(obj2, "connection_error")) {
                throw new ApolloNetworkException(Intrinsics.stringPlus("Connection error:\n", map), null, 2, null);
            }
            System.out.println((Object) Intrinsics.stringPlus("unknown message while waiting for connection_ack: '", obj2));
            return Unit.INSTANCE;
        }
    }

    @Override // com.apollographql.apollo3.network.ws.WsProtocol
    public void handleServerMessage(Map<String, ? extends Object> messageMap) {
        Intrinsics.checkNotNullParameter(messageMap, "messageMap");
        Object obj = messageMap.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (Intrinsics.areEqual(obj, "data")) {
            WsProtocol.Listener listener = getListener();
            Object obj2 = messageMap.get("id");
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj2;
            Object obj3 = messageMap.get("payload");
            if (obj3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            }
            listener.operationResponse(str, (Map) obj3);
            return;
        }
        if (Intrinsics.areEqual(obj, "error")) {
            Object obj4 = messageMap.get("id");
            if (obj4 instanceof String) {
                getListener().operationError((String) obj4, (Map) messageMap.get("payload"));
                return;
            } else {
                getListener().generalError((Map) messageMap.get("payload"));
                return;
            }
        }
        if (Intrinsics.areEqual(obj, "complete")) {
            WsProtocol.Listener listener2 = getListener();
            Object obj5 = messageMap.get("id");
            if (obj5 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            listener2.operationComplete((String) obj5);
        }
    }

    @Override // com.apollographql.apollo3.network.ws.WsProtocol
    public <D extends Operation.Data> void startOperation(ApolloRequest<D> request) {
        Intrinsics.checkNotNullParameter(request, "request");
        sendMessageMap(MapsKt__MapsKt.mapOf(TuplesKt.to(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, AsmString.METHOD_FIREBASE_PERF_TRACE_START), TuplesKt.to("id", request.getRequestUuid().toString()), TuplesKt.to("payload", DefaultHttpRequestComposer.INSTANCE.composePayload(request))), this.frameType);
    }

    @Override // com.apollographql.apollo3.network.ws.WsProtocol
    public <D extends Operation.Data> void stopOperation(ApolloRequest<D> request) {
        Intrinsics.checkNotNullParameter(request, "request");
        sendMessageMap(MapsKt__MapsKt.mapOf(TuplesKt.to(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP), TuplesKt.to("id", request.getRequestUuid().toString())), this.frameType);
    }

    /* JADX INFO: compiled from: SubscriptionWsProtocol.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BN\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012.\b\u0002\u0010\u0004\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0002\u0010\fJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u0004\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocol$Factory;", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "connectionAcknowledgeTimeoutMs", "", "connectionPayload", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "", "frameType", "Lcom/apollographql/apollo3/network/ws/WsFrameType;", "(JLkotlin/jvm/functions/Function1;Lcom/apollographql/apollo3/network/ws/WsFrameType;)V", "Lkotlin/jvm/functions/Function1;", "name", "getName", "()Ljava/lang/String;", "create", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Factory implements WsProtocol.Factory {
        private final long connectionAcknowledgeTimeoutMs;
        private final Function1<Continuation<? super Map<String, ? extends Object>>, Object> connectionPayload;
        private final WsFrameType frameType;

        @JvmOverloads
        public Factory() {
            this(0L, null, null, 7, null);
        }

        @Override // com.apollographql.apollo3.network.ws.WsProtocol.Factory
        public String getName() {
            return "graphql-ws";
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmOverloads
        public Factory(long j, Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> connectionPayload, WsFrameType frameType) {
            Intrinsics.checkNotNullParameter(connectionPayload, "connectionPayload");
            Intrinsics.checkNotNullParameter(frameType, "frameType");
            this.connectionAcknowledgeTimeoutMs = j;
            this.connectionPayload = connectionPayload;
            this.frameType = frameType;
        }

        /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1, reason: invalid class name */
        /* JADX INFO: compiled from: SubscriptionWsProtocol.kt */
        @Metadata(bv = {}, d1 = {"\u0000\u0006\n\u0002\u0010\u0001\n\u0000\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u008a@"}, d2 = {"", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1", f = "SubscriptionWsProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation, Object> {
            int label;

            AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(1, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new AnonymousClass1(continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation continuation) {
                return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return null;
            }
        }

        public /* synthetic */ Factory(long j, Function1 function1, WsFrameType wsFrameType, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 10000L : j, (i & 2) != 0 ? new AnonymousClass1(null) : function1, (i & 4) != 0 ? WsFrameType.Text : wsFrameType);
        }

        @Override // com.apollographql.apollo3.network.ws.WsProtocol.Factory
        public WsProtocol create(WebSocketConnection webSocketConnection, WsProtocol.Listener listener, CoroutineScope scope) {
            Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
            Intrinsics.checkNotNullParameter(listener, "listener");
            Intrinsics.checkNotNullParameter(scope, "scope");
            return new SubscriptionWsProtocol(webSocketConnection, listener, this.connectionAcknowledgeTimeoutMs, this.connectionPayload, this.frameType);
        }
    }
}
