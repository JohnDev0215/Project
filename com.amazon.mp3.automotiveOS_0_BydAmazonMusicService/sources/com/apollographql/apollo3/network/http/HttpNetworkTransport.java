package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.DefaultHttpRequestComposer;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpRequestComposer;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.exception.ApolloHttpException;
import com.apollographql.apollo3.exception.ApolloParseException;
import com.apollographql.apollo3.internal.MultipartKt;
import com.apollographql.apollo3.internal.NonMainWorker;
import com.apollographql.apollo3.mpp.UtilsKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.visualon.OSMPUtils.voOSType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.BufferedSource;

/* JADX INFO: compiled from: HttpNetworkTransport.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 (2\u00020\u0001:\u0003)(*B/\b\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b&\u0010'J,\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016J:\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001c\u001a\u00020\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010$\u001a\u00060#R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006+"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;", "Lcom/apollographql/apollo3/network/NetworkTransport;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lcom/apollographql/apollo3/api/ApolloRequest;", "request", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "execute", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "httpRequest", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "httpRequestComposer", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "engine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "getEngine", "()Lcom/apollographql/apollo3/network/http/HttpEngine;", "", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "interceptors", "Ljava/util/List;", "getInterceptors", "()Ljava/util/List;", "", "exposeErrorBody", "Z", "getExposeErrorBody", "()Z", "Lcom/apollographql/apollo3/internal/NonMainWorker;", "worker", "Lcom/apollographql/apollo3/internal/NonMainWorker;", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$EngineInterceptor;", "engineInterceptor", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$EngineInterceptor;", "<init>", "(Lcom/apollographql/apollo3/api/http/HttpRequestComposer;Lcom/apollographql/apollo3/network/http/HttpEngine;Ljava/util/List;Z)V", "Companion", "Builder", "EngineInterceptor", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class HttpNetworkTransport implements NetworkTransport {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HttpEngine engine;
    private final EngineInterceptor engineInterceptor;
    private final boolean exposeErrorBody;
    private final HttpRequestComposer httpRequestComposer;
    private final List<HttpInterceptor> interceptors;
    private final NonMainWorker worker;

    public /* synthetic */ HttpNetworkTransport(HttpRequestComposer httpRequestComposer, HttpEngine httpEngine, List list, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpRequestComposer, httpEngine, list, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private HttpNetworkTransport(HttpRequestComposer httpRequestComposer, HttpEngine httpEngine, List<? extends HttpInterceptor> list, boolean z) {
        this.httpRequestComposer = httpRequestComposer;
        this.engine = httpEngine;
        this.interceptors = list;
        this.exposeErrorBody = z;
        this.worker = new NonMainWorker();
        this.engineInterceptor = new EngineInterceptor(this);
    }

    public final HttpEngine getEngine() {
        return this.engine;
    }

    public final List<HttpInterceptor> getInterceptors() {
        return this.interceptors;
    }

    public final boolean getExposeErrorBody() {
        return this.exposeErrorBody;
    }

    @Override // com.apollographql.apollo3.network.NetworkTransport
    public <D extends Operation.Data> Flow<ApolloResponse<D>> execute(ApolloRequest<D> request) {
        Intrinsics.checkNotNullParameter(request, "request");
        ExecutionContext.Element element = request.getExecutionContext().get(CustomScalarAdapters.INSTANCE);
        Intrinsics.checkNotNull(element);
        return execute(request, this.httpRequestComposer.compose(request), (CustomScalarAdapters) element);
    }

    /* JADX INFO: Add missing generic type declarations: [D] */
    /* JADX INFO: renamed from: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002H\u008a@"}, d2 = {"Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1", f = "HttpNetworkTransport.kt", i = {0, 0}, l = {62, 87}, m = "invokeSuspend", n = {"$this$flow", "millisStart"}, s = {"L$0", "J$0"})
    static final class AnonymousClass1<D> extends SuspendLambda implements Function2<FlowCollector<? super ApolloResponse<D>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CustomScalarAdapters $customScalarAdapters;
        final /* synthetic */ HttpRequest $httpRequest;
        final /* synthetic */ ApolloRequest<D> $request;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HttpRequest httpRequest, ApolloRequest<D> apolloRequest, CustomScalarAdapters customScalarAdapters, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$httpRequest = httpRequest;
            this.$request = apolloRequest;
            this.$customScalarAdapters = customScalarAdapters;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = HttpNetworkTransport.this.new AnonymousClass1(this.$httpRequest, this.$request, this.$customScalarAdapters, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super ApolloResponse<D>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            FlowCollector flowCollector;
            long jCurrentTimeMillis;
            Flow<BufferedSource> flowFlowOf;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            boolean z = false;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = (FlowCollector) this.L$0;
                jCurrentTimeMillis = UtilsKt.currentTimeMillis();
                DefaultHttpInterceptorChain defaultHttpInterceptorChain = new DefaultHttpInterceptorChain(CollectionsKt___CollectionsKt.plus((Collection<? extends EngineInterceptor>) HttpNetworkTransport.this.getInterceptors(), HttpNetworkTransport.this.engineInterceptor), 0);
                HttpRequest httpRequest = this.$httpRequest;
                this.L$0 = flowCollector;
                this.J$0 = jCurrentTimeMillis;
                this.label = 1;
                obj = defaultHttpInterceptorChain.proceed(httpRequest, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                jCurrentTimeMillis = this.J$0;
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final long j = jCurrentTimeMillis;
            final HttpResponse httpResponse = (HttpResponse) obj;
            int statusCode = httpResponse.getStatusCode();
            if (200 <= statusCode && statusCode < 300) {
                z = true;
            }
            BufferedSource body = null;
            if (!z) {
                if (HttpNetworkTransport.this.getExposeErrorBody()) {
                    body = httpResponse.getBody();
                } else {
                    BufferedSource body2 = httpResponse.getBody();
                    if (body2 != null) {
                        body2.close();
                    }
                }
                throw new ApolloHttpException(httpResponse.getStatusCode(), httpResponse.getHeaders(), body, "Http request failed with status code `" + httpResponse.getStatusCode() + '`', null, 16, null);
            }
            if (MultipartKt.isMultipart(httpResponse)) {
                flowFlowOf = MultipartKt.multipartBodyFlow(httpResponse);
            } else {
                BufferedSource body3 = httpResponse.getBody();
                Intrinsics.checkNotNull(body3);
                flowFlowOf = FlowKt.flowOf(body3);
            }
            final Flow<BufferedSource> flow = flowFlowOf;
            final ApolloRequest<D> apolloRequest = this.$request;
            final HttpNetworkTransport httpNetworkTransport = HttpNetworkTransport.this;
            final CustomScalarAdapters customScalarAdapters = this.$customScalarAdapters;
            Flow<ApolloResponse<D>> flow2 = new Flow<ApolloResponse<D>>() { // from class: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1

                /* JADX INFO: renamed from: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Collect.kt */
                @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
                public static final class AnonymousClass2 implements FlowCollector<BufferedSource> {
                    final /* synthetic */ CustomScalarAdapters $customScalarAdapters$inlined;
                    final /* synthetic */ HttpResponse $httpResponse$inlined;
                    final /* synthetic */ long $millisStart$inlined;
                    final /* synthetic */ ApolloRequest $request$inlined;
                    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                    final /* synthetic */ HttpNetworkTransport this$0;

                    /* JADX INFO: renamed from: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                    @DebugMetadata(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1$2", f = "HttpNetworkTransport.kt", i = {0, 1}, l = {voOSType.VOOSMP_PID_SET_I_FRAME_ONLY_MODE, 151, 174}, m = "emit", n = {"this", "this"}, s = {"L$0", "L$0"})
                    public static final class AnonymousClass1 extends ContinuationImpl {
                        Object L$0;
                        Object L$1;
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

                    public AnonymousClass2(FlowCollector flowCollector, ApolloRequest apolloRequest, HttpNetworkTransport httpNetworkTransport, long j, HttpResponse httpResponse, CustomScalarAdapters customScalarAdapters) {
                        this.$this_unsafeFlow$inlined = flowCollector;
                        this.$request$inlined = apolloRequest;
                        this.this$0 = httpNetworkTransport;
                        this.$millisStart$inlined = j;
                        this.$httpResponse$inlined = httpResponse;
                        this.$customScalarAdapters$inlined = customScalarAdapters;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public java.lang.Object emit(okio.BufferedSource r17, kotlin.coroutines.Continuation r18) {
                        /*
                            Method dump skipped, instruction units count: 254
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public Object collect(FlowCollector flowCollector2, Continuation continuation) {
                    Object objCollect = flow.collect(new AnonymousClass2(flowCollector2, apolloRequest, httpNetworkTransport, j, httpResponse, customScalarAdapters), continuation);
                    return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (FlowKt.emitAll(flowCollector, flow2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final <D extends Operation.Data> Flow<ApolloResponse<D>> execute(ApolloRequest<D> request, HttpRequest httpRequest, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(httpRequest, "httpRequest");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return FlowKt.flow(new AnonymousClass1(httpRequest, request, customScalarAdapters, null));
    }

    /* JADX INFO: compiled from: HttpNetworkTransport.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$EngineInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "(Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;)V", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class EngineInterceptor implements HttpInterceptor {
        final /* synthetic */ HttpNetworkTransport this$0;

        public EngineInterceptor(HttpNetworkTransport this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.apollographql.apollo3.network.http.HttpInterceptor
        public Object intercept(HttpRequest httpRequest, HttpInterceptorChain httpInterceptorChain, Continuation<? super HttpResponse> continuation) {
            return this.this$0.getEngine().execute(httpRequest, continuation);
        }
    }

    /* JADX INFO: compiled from: HttpNetworkTransport.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u0014\u0010\n\u001a\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0006\u0010\f\u001a\u00020\u000bR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Builder;", "", "", "serverUrl", "", "exposeErrorBody", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "httpEngine", "", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "interceptors", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "httpRequestComposer", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "Ljava/lang/String;", "engine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "", "Ljava/util/List;", "Z", "<init>", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private HttpEngine engine;
        private boolean exposeErrorBody;
        private HttpRequestComposer httpRequestComposer;
        private final List<HttpInterceptor> interceptors = new ArrayList();
        private String serverUrl;

        public final Builder serverUrl(String serverUrl) {
            Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
            this.serverUrl = serverUrl;
            return this;
        }

        public final Builder exposeErrorBody(boolean exposeErrorBody) {
            this.exposeErrorBody = exposeErrorBody;
            return this;
        }

        public final Builder httpEngine(HttpEngine httpEngine) {
            Intrinsics.checkNotNullParameter(httpEngine, "httpEngine");
            this.engine = httpEngine;
            return this;
        }

        public final Builder interceptors(List<? extends HttpInterceptor> interceptors) {
            Intrinsics.checkNotNullParameter(interceptors, "interceptors");
            this.interceptors.clear();
            this.interceptors.addAll(interceptors);
            return this;
        }

        public final HttpNetworkTransport build() {
            HttpRequestComposer defaultHttpRequestComposer = this.httpRequestComposer;
            int i = 1;
            if (!(defaultHttpRequestComposer == null || this.serverUrl == null)) {
                throw new IllegalStateException("It is an error to set both 'httpRequestComposer' and 'serverUrl'".toString());
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (defaultHttpRequestComposer == null) {
                String str = this.serverUrl;
                defaultHttpRequestComposer = str == null ? null : new DefaultHttpRequestComposer(str);
                if (defaultHttpRequestComposer == null) {
                    throw new IllegalStateException("No HttpRequestComposer found. Use 'httpRequestComposer' or 'serverUrl'".toString());
                }
            }
            HttpRequestComposer httpRequestComposer = defaultHttpRequestComposer;
            HttpEngine defaultHttpEngine = this.engine;
            if (defaultHttpEngine == null) {
                defaultHttpEngine = new DefaultHttpEngine(0L, i, defaultConstructorMarker);
            }
            return new HttpNetworkTransport(httpRequestComposer, defaultHttpEngine, this.interceptors, this.exposeErrorBody, null);
        }
    }

    /* JADX INFO: compiled from: HttpNetworkTransport.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Companion;", "", "()V", "wrapThrowableIfNeeded", "Lcom/apollographql/apollo3/exception/ApolloException;", "throwable", "", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ApolloException wrapThrowableIfNeeded(Throwable throwable) {
            if (throwable instanceof ApolloException) {
                return (ApolloException) throwable;
            }
            return new ApolloParseException("Failed to parse GraphQL http network response", throwable);
        }
    }
}
