package com.apollographql.apollo3.network.ws;

import androidx.mediarouter.media.MediaRouter;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.internal.BackgroundDispatcher;
import com.apollographql.apollo3.internal.FlowsKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.apollographql.apollo3.network.ws.SubscriptionWsProtocol;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.apollographql.apollo3.network.ws.internal.Event;
import com.apollographql.apollo3.network.ws.internal.GeneralError;
import com.apollographql.apollo3.network.ws.internal.Message;
import com.apollographql.apollo3.network.ws.internal.NetworkError;
import com.apollographql.apollo3.network.ws.internal.OperationComplete;
import com.apollographql.apollo3.network.ws.internal.OperationError;
import com.apollographql.apollo3.network.ws.internal.OperationResponse;
import com.apollographql.apollo3.network.ws.internal.StartOperation;
import com.apollographql.apollo3.network.ws.internal.StopOperation;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0007*\u0001>\u0018\u00002\u00020\u0001:\u0001CB{\b\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b\u00129\u0010&\u001a5\b\u0001\u0012\u0004\u0012\u00020\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u001eø\u0001\u0000¢\u0006\u0004\bA\u0010BJ\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000b\"\b\b\u0000\u0010\b*\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016R\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dRJ\u0010&\u001a5\b\u0001\u0012\u0004\u0012\u00020\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u001e8\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b&\u0010'R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020-008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u001d\u00105\u001a\b\u0012\u0004\u0012\u000204038\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport;", "Lcom/apollographql/apollo3/network/NetworkTransport;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "", "supervise", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lcom/apollographql/apollo3/api/ApolloRequest;", "request", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "execute", "", "serverUrl", "Ljava/lang/String;", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "webSocketEngine", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "", "idleTimeoutMillis", "J", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "protocolFactory", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "attempt", "Lkotlin/coroutines/Continuation;", "", "", "reopenWhen", "Lkotlin/jvm/functions/Function3;", "Lkotlinx/coroutines/channels/Channel;", "Lcom/apollographql/apollo3/network/ws/internal/Message;", "messages", "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "mutableEvents", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlow;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "Lcom/apollographql/apollo3/internal/BackgroundDispatcher;", "backgroundDispatcher", "Lcom/apollographql/apollo3/internal/BackgroundDispatcher;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "com/apollographql/apollo3/network/ws/WebSocketNetworkTransport$listener$1", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$listener$1;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/apollographql/apollo3/network/ws/WebSocketEngine;JLcom/apollographql/apollo3/network/ws/WsProtocol$Factory;Lkotlin/jvm/functions/Function3;)V", "Builder", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class WebSocketNetworkTransport implements NetworkTransport {
    private final BackgroundDispatcher backgroundDispatcher;
    private final CoroutineScope coroutineScope;
    private final SharedFlow<Event> events;
    private final List<HttpHeader> headers;
    private final long idleTimeoutMillis;
    private final WebSocketNetworkTransport$listener$1 listener;
    private final Channel<Message> messages;
    private final MutableSharedFlow<Event> mutableEvents;
    private final WsProtocol.Factory protocolFactory;
    private final Function3<Throwable, Long, Continuation<? super Boolean>, Object> reopenWhen;
    private final String serverUrl;
    private final StateFlow<Integer> subscriptionCount;
    private final WebSocketEngine webSocketEngine;

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport", f = "WebSocketNetworkTransport.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {138, voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID, 176, 185, 195, 199}, m = "supervise", n = {"this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0"})
    static final class C04591 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C04591(Continuation<? super C04591> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketNetworkTransport.this.supervise(null, this);
        }
    }

    public /* synthetic */ WebSocketNetworkTransport(String str, List list, WebSocketEngine webSocketEngine, long j, WsProtocol.Factory factory, Function3 function3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, webSocketEngine, j, factory, function3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v6, types: [com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$listener$1] */
    private WebSocketNetworkTransport(String str, List<HttpHeader> list, WebSocketEngine webSocketEngine, long j, WsProtocol.Factory factory, Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3) {
        this.serverUrl = str;
        this.headers = list;
        this.webSocketEngine = webSocketEngine;
        this.idleTimeoutMillis = j;
        this.protocolFactory = factory;
        this.reopenWhen = function3;
        this.messages = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        MutableSharedFlow<Event> MutableSharedFlow = SharedFlowKt.MutableSharedFlow(0, Integer.MAX_VALUE, BufferOverflow.SUSPEND);
        this.mutableEvents = MutableSharedFlow;
        this.events = FlowKt.asSharedFlow(MutableSharedFlow);
        this.subscriptionCount = MutableSharedFlow.getSubscriptionCount();
        BackgroundDispatcher backgroundDispatcher = new BackgroundDispatcher();
        this.backgroundDispatcher = backgroundDispatcher;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(backgroundDispatcher.getCoroutineDispatcher());
        this.coroutineScope = CoroutineScope;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new AnonymousClass1(null), 3, null);
        this.listener = new WsProtocol.Listener() { // from class: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$listener$1
            @Override // com.apollographql.apollo3.network.ws.WsProtocol.Listener
            public void operationResponse(String id, Map<String, ? extends Object> payload) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(payload, "payload");
                this.this$0.messages.mo55trySendJP2dKIU(new OperationResponse(id, payload));
            }

            @Override // com.apollographql.apollo3.network.ws.WsProtocol.Listener
            public void operationError(String id, Map<String, ? extends Object> payload) {
                Intrinsics.checkNotNullParameter(id, "id");
                this.this$0.messages.mo55trySendJP2dKIU(new OperationError(id, payload));
            }

            @Override // com.apollographql.apollo3.network.ws.WsProtocol.Listener
            public void operationComplete(String id) {
                Intrinsics.checkNotNullParameter(id, "id");
                this.this$0.messages.mo55trySendJP2dKIU(new OperationComplete(id));
            }

            @Override // com.apollographql.apollo3.network.ws.WsProtocol.Listener
            public void generalError(Map<String, ? extends Object> payload) {
                this.this$0.messages.mo55trySendJP2dKIU(new GeneralError(payload));
            }

            @Override // com.apollographql.apollo3.network.ws.WsProtocol.Listener
            public void networkError(Throwable cause) {
                Intrinsics.checkNotNullParameter(cause, "cause");
                this.this$0.messages.mo55trySendJP2dKIU(new NetworkError(cause));
            }
        };
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$1, reason: invalid class name */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$1", f = "WebSocketNetworkTransport.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WebSocketNetworkTransport.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                WebSocketNetworkTransport webSocketNetworkTransport = WebSocketNetworkTransport.this;
                this.label = 1;
                if (webSocketNetworkTransport.supervise(coroutineScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            WebSocketNetworkTransport.this.backgroundDispatcher.dispose();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(13:90|91|92|136|93|(1:95)|96|113|(0)(0)|119|(0)(0)|127|141) */
    /* JADX WARN: Can't wrap try/catch for region: R(4:83|84|138|85) */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0333, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0334, code lost:
    
        r4 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0338, code lost:
    
        r12 = r1;
        r1 = r3;
        r3 = r4;
        r23 = r11;
        r11 = r5;
        r4 = r14;
        r14 = r23;
        r24 = r10;
        r10 = r6;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x02f4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02f5, code lost:
    
        r2 = r0;
        r0 = r13;
        r6 = 0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:75:0x0249, B:74:0x0247], limit reached: 143 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x031c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0367 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x017d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0266 A[Catch: Exception -> 0x0337, TryCatch #1 {Exception -> 0x0337, blocks: (B:69:0x0235, B:72:0x0241, B:82:0x0266, B:83:0x026b, B:75:0x0249, B:76:0x024d, B:78:0x0253), top: B:132:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x026b A[Catch: Exception -> 0x0337, TRY_LEAVE, TryCatch #1 {Exception -> 0x0337, blocks: (B:69:0x0235, B:72:0x0241, B:82:0x0266, B:83:0x026b, B:75:0x0249, B:76:0x024d, B:78:0x0253), top: B:132:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0299 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02d2 A[RETURN] */
    /* JADX WARN: Type inference failed for: r0v22, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r0v26, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r0v30, types: [T, com.apollographql.apollo3.network.ws.WsProtocol, java.lang.Object] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x031d -> B:110:0x0368). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0207 -> B:111:0x036a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object supervise(kotlinx.coroutines.CoroutineScope r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r30) {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport.supervise(kotlinx.coroutines.CoroutineScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void supervise$closeProtocol(Ref.ObjectRef<WsProtocol> objectRef, Ref.ObjectRef<Job> objectRef2, Ref.ObjectRef<Job> objectRef3) {
        WsProtocol wsProtocol = objectRef.element;
        if (wsProtocol != null) {
            wsProtocol.close();
        }
        objectRef.element = null;
        Job job = objectRef2.element;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        objectRef2.element = null;
        Job job2 = objectRef3.element;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        objectRef3.element = null;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3", f = "WebSocketNetworkTransport.kt", i = {}, l = {ContentCatalogStatus.SONIC_RUSH_OR_NOT_BOUNDARY}, m = "invokeSuspend", n = {}, s = {})
    static final class C04603 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<WsProtocol> $protocol;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04603(Ref.ObjectRef<WsProtocol> objectRef, Continuation<? super C04603> continuation) {
            super(2, continuation);
            this.$protocol = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04603(this.$protocol, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04603) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WsProtocol wsProtocol = this.$protocol.element;
                Intrinsics.checkNotNull(wsProtocol);
                this.label = 1;
                if (wsProtocol.run(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4, reason: invalid class name */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4", f = "WebSocketNetworkTransport.kt", i = {}, l = {227}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Job> $connectionJob;
        final /* synthetic */ Ref.ObjectRef<Job> $idleJob;
        final /* synthetic */ Ref.ObjectRef<WsProtocol> $protocol;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<WsProtocol> objectRef, Ref.ObjectRef<Job> objectRef2, Ref.ObjectRef<Job> objectRef3, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$protocol = objectRef;
            this.$connectionJob = objectRef2;
            this.$idleJob = objectRef3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebSocketNetworkTransport.this.new AnonymousClass4(this.$protocol, this.$connectionJob, this.$idleJob, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = WebSocketNetworkTransport.this.idleTimeoutMillis;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            WebSocketNetworkTransport.supervise$closeProtocol(this.$protocol, this.$connectionJob, this.$idleJob);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$1", f = "WebSocketNetworkTransport.kt", i = {}, l = {243}, m = "invokeSuspend", n = {}, s = {})
    static final class C04581 extends SuspendLambda implements Function2<FlowCollector<? super Event>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ApolloRequest<D> $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04581(ApolloRequest<D> apolloRequest, Continuation<? super C04581> continuation) {
            super(2, continuation);
            this.$request = apolloRequest;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebSocketNetworkTransport.this.new C04581(this.$request, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super Event> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04581) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Channel channel = WebSocketNetworkTransport.this.messages;
                StartOperation startOperation = new StartOperation(this.$request);
                this.label = 1;
                if (channel.send(startOperation, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.apollographql.apollo3.network.NetworkTransport
    public <D extends Operation.Data> Flow<ApolloResponse<D>> execute(final ApolloRequest<D> request) {
        Intrinsics.checkNotNullParameter(request, "request");
        final SharedFlow sharedFlowOnSubscription = FlowKt.onSubscription(this.events, new C04581(request, null));
        final Flow flowTransformWhile = FlowsKt.transformWhile(new Flow<Event>() { // from class: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1

            /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector<Event> {
                final /* synthetic */ ApolloRequest $request$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2", f = "WebSocketNetworkTransport.kt", i = {}, l = {voOSType.VOOSMP_PID_PREFERENCE}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ApolloRequest apolloRequest) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$request$inlined = apolloRequest;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(com.apollographql.apollo3.network.ws.internal.Event r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1 r0 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1 r0 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L62
                    L29:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L31:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow$inlined
                        r2 = r7
                        com.apollographql.apollo3.network.ws.internal.Event r2 = (com.apollographql.apollo3.network.ws.internal.Event) r2
                        java.lang.String r4 = r2.getId()
                        com.apollographql.apollo3.api.ApolloRequest r5 = r6.$request$inlined
                        java.util.UUID r5 = r5.getRequestUuid()
                        java.lang.String r5 = r5.toString()
                        boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
                        if (r4 != 0) goto L56
                        java.lang.String r2 = r2.getId()
                        if (r2 != 0) goto L54
                        goto L56
                    L54:
                        r2 = 0
                        goto L57
                    L56:
                        r2 = r3
                    L57:
                        if (r2 == 0) goto L62
                        r0.label = r3
                        java.lang.Object r7 = r8.emit(r7, r0)
                        if (r7 != r1) goto L62
                        return r1
                    L62:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Event> flowCollector, Continuation continuation) {
                Object objCollect = sharedFlowOnSubscription.collect(new AnonymousClass2(flowCollector, request), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new AnonymousClass3(request, null));
        return FlowKt.onCompletion(new Flow<ApolloResponse<D>>() { // from class: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1

            /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector<Event> {
                final /* synthetic */ ApolloRequest $request$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1$2", f = "WebSocketNetworkTransport.kt", i = {}, l = {voOSType.VOOSMP_PID_PREFERENCE}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ApolloRequest apolloRequest) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$request$inlined = apolloRequest;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(com.apollographql.apollo3.network.ws.internal.Event r7, kotlin.coroutines.Continuation r8) {
                    /*
                        Method dump skipped, instruction units count: 243
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flowTransformWhile.collect(new AnonymousClass2(flowCollector, request), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new AnonymousClass5(request, null));
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$3, reason: invalid class name */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/network/ws/internal/Event;", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$3", f = "WebSocketNetworkTransport.kt", i = {}, l = {252, MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super Event>, Event, Continuation<? super Boolean>, Object> {
        final /* synthetic */ ApolloRequest<D> $request;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ApolloRequest<D> apolloRequest, Continuation<? super AnonymousClass3> continuation) {
            super(3, continuation);
            this.$request = apolloRequest;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Event> flowCollector, Event event, Continuation<? super Boolean> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$request, continuation);
            anonymousClass3.L$0 = flowCollector;
            anonymousClass3.L$1 = event;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            boolean z = false;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Event event = (Event) this.L$1;
                if (!(event instanceof OperationComplete)) {
                    if (event instanceof NetworkError) {
                        this.L$0 = null;
                        this.label = 1;
                        if (flowCollector.emit(event, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (event instanceof GeneralError) {
                            System.out.println((Object) ("Received general error while executing operation " + this.$request.getOperation().name() + ": " + ((GeneralError) event).getPayload()));
                        } else {
                            this.L$0 = null;
                            this.label = 2;
                            if (flowCollector.emit(event, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        z = true;
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                z = true;
            }
            return Boxing.boxBoolean(z);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [D] */
    /* JADX INFO: renamed from: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$5, reason: invalid class name */
    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u008a@"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$5", f = "WebSocketNetworkTransport.kt", i = {}, l = {281}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5<D> extends SuspendLambda implements Function3<FlowCollector<? super ApolloResponse<D>>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ ApolloRequest<D> $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(ApolloRequest<D> apolloRequest, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.$request = apolloRequest;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super ApolloResponse<D>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return WebSocketNetworkTransport.this.new AnonymousClass5(this.$request, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Channel channel = WebSocketNetworkTransport.this.messages;
                StopOperation stopOperation = new StopOperation(this.$request);
                this.label = 1;
                if (channel.send(stopOperation, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: WebSocketNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJK\u0010\u0012\u001a\u00020\u000029\u0010\u0012\u001a5\b\u0001\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0006\u0010\u0015\u001a\u00020\u0014R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\u001cR\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u001dRL\u0010\u0012\u001a5\b\u0001\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000eø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0012\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "", "", "serverUrl", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "webSocketEngine", "", "idleTimeoutMillis", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "protocolFactory", "protocol", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "attempt", "Lkotlin/coroutines/Continuation;", "", "reopenWhen", "(Lkotlin/jvm/functions/Function3;)Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Ljava/lang/String;", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "Ljava/lang/Long;", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "Lkotlin/jvm/functions/Function3;", "<init>", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private List<HttpHeader> headers = new ArrayList();
        private Long idleTimeoutMillis;
        private WsProtocol.Factory protocolFactory;
        private Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> reopenWhen;
        private String serverUrl;
        private WebSocketEngine webSocketEngine;

        public final Builder serverUrl(String serverUrl) {
            Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
            this.serverUrl = serverUrl;
            return this;
        }

        public final Builder webSocketEngine(WebSocketEngine webSocketEngine) {
            Intrinsics.checkNotNullParameter(webSocketEngine, "webSocketEngine");
            this.webSocketEngine = webSocketEngine;
            return this;
        }

        public final Builder idleTimeoutMillis(long idleTimeoutMillis) {
            this.idleTimeoutMillis = Long.valueOf(idleTimeoutMillis);
            return this;
        }

        public final Builder protocol(WsProtocol.Factory protocolFactory) {
            Intrinsics.checkNotNullParameter(protocolFactory, "protocolFactory");
            this.protocolFactory = protocolFactory;
            return this;
        }

        public final Builder reopenWhen(Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> reopenWhen) {
            this.reopenWhen = reopenWhen;
            return this;
        }

        public final WebSocketNetworkTransport build() {
            String str = this.serverUrl;
            if (str == null) {
                throw new IllegalStateException("No serverUrl specified".toString());
            }
            List<HttpHeader> list = this.headers;
            WebSocketEngine defaultWebSocketEngine = this.webSocketEngine;
            if (defaultWebSocketEngine == null) {
                defaultWebSocketEngine = new DefaultWebSocketEngine();
            }
            WebSocketEngine webSocketEngine = defaultWebSocketEngine;
            Long l = this.idleTimeoutMillis;
            long jLongValue = l == null ? 60000L : l.longValue();
            WsProtocol.Factory factory = this.protocolFactory;
            if (factory == null) {
                factory = new SubscriptionWsProtocol.Factory(0L, null, null, 7, null);
            }
            return new WebSocketNetworkTransport(str, list, webSocketEngine, jLongValue, factory, this.reopenWhen, null);
        }
    }
}
