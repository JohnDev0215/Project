package com.neusoft.na.ocservice.ctrl;

import android.car.AoapService;
import android.content.Context;
import android.os.Handler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseToken;
import com.neusoft.de.nts.OneCoreTest.communication.protocol.JsonProtocol;
import com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClient;
import com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClientMots;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.na.ocservice.ctrl.ActorServer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import mu.KLogging;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* JADX INFO: compiled from: ActorServer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\u0006\u0010!\u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020\u001fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\n \t*\u0004\u0018\u00010\u00170\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \t*\u0004\u0018\u00010\u001d0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/ActorServer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "broadcastTransport", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/AmqpClient/RxAmqpClient;", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getExecutor$ocservice_release", "()Ljava/util/concurrent/ExecutorService;", "internalSequenceNumber", "Ljava/util/concurrent/atomic/AtomicLong;", "isRunning", "", "resGetAvailableInterfacesArgs", "Lcom/neusoft/na/ocservice/ctrl/ResGetAvailableInterfacesArgs;", "rxTransport", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/AmqpClient/RxAmqpClientMots;", "getRxTransport$ocservice_release", "()Lcom/neusoft/de/nts/OneCoreTest/communication/transports/AmqpClient/RxAmqpClientMots;", "threadFactory", "Lorg/apache/commons/lang3/concurrent/BasicThreadFactory;", "tokenMap", "", "", "Lcom/google/gson/JsonElement;", "wsTransport", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint;", "onConnect", "", "onDisconnect", "start", "stop", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ActorServer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String MOTSBL_ENDPOINT = "ws://127.0.0.1:22559";
    private static final String SENDER_ROUTING_KEY = "controller.OctaServer";
    private static final String STATUS_ROUTING_KEY = "#.status.#";
    private final RxAmqpClient broadcastTransport;
    private final ExecutorService executor;
    private AtomicLong internalSequenceNumber;
    private boolean isRunning;
    private final ResGetAvailableInterfacesArgs resGetAvailableInterfacesArgs;
    private final RxAmqpClientMots rxTransport;
    private final BasicThreadFactory threadFactory;
    private final Map<Long, JsonElement> tokenMap;
    private final OneCoreClientEndpoint wsTransport;

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConnect() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDisconnect() {
    }

    /* JADX INFO: compiled from: ActorServer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/ActorServer$Companion;", "Lmu/KLogging;", "()V", "MOTSBL_ENDPOINT", "", "SENDER_ROUTING_KEY", "STATUS_ROUTING_KEY", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public ActorServer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BasicThreadFactory basicThreadFactoryBuild = new BasicThreadFactory.Builder().namingPattern("Actor-Server-%d").build();
        this.threadFactory = basicThreadFactoryBuild;
        this.broadcastTransport = new RxAmqpClient(EProjectInterfaceType.OneCore.getProtocol(), STATUS_ROUTING_KEY);
        OneCoreClientEndpoint oneCoreClientEndpointBuild = new OneCoreClientEndpoint.Builder().retryOnConnectionFailure(true).pingInterval(30000L).connectTimeout(1000L).build();
        this.wsTransport = oneCoreClientEndpointBuild;
        this.tokenMap = new LinkedHashMap();
        this.internalSequenceNumber = new AtomicLong(1L);
        this.resGetAvailableInterfacesArgs = new ResGetAvailableInterfacesArgs(CollectionsKt.listOf(new ListEntry("OnOff", CollectionsKt.listOf("com.neusoft.onoff.OnOffMaster"))));
        this.executor = Executors.newSingleThreadExecutor(basicThreadFactoryBuild);
        RxAmqpClientMots rxAmqpClientMots = new RxAmqpClientMots(new JsonProtocol(EProjectInterfaceType.OneCore.getGson()), "controller.#");
        this.rxTransport = rxAmqpClientMots;
        INSTANCE.getLogger().debug("ActorServer::init");
        Observable<OneCoreClientEndpoint.ConnectionState> connectionObservable = oneCoreClientEndpointBuild.getConnectionObservable();
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(context);
        connectionObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ActorServer._init_$lambda$0(anonymousClass1, obj);
            }
        });
        Observable<String> observable = oneCoreClientEndpointBuild.getObservable();
        final Function1<String, JsonObject> function1 = new Function1<String, JsonObject>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final JsonObject invoke(String str) {
                boolean z = false;
                if (str != null && StringsKt.isBlank(str)) {
                    z = true;
                }
                JsonObject jsonObject = null;
                if (z) {
                } else {
                    try {
                        JsonObject asJsonObject = new JsonParser().parse(str).getAsJsonObject();
                        if (Intrinsics.areEqual(asJsonObject.get("type").getAsString(), "request")) {
                            if (Intrinsics.areEqual(asJsonObject.get("method").getAsString(), "na.iface.adaptor.jsonws.Interface.GetAvailableInterfaces")) {
                                ActorServer.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.2.1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return "got a GetAvailableInterfaces request";
                                    }
                                });
                                asJsonObject.add("args", EProjectInterfaceType.OCTA.getGson().toJsonTree(ActorServer.this.resGetAvailableInterfacesArgs));
                                asJsonObject.addProperty("type", AoapService.KEY_RESULT);
                                ActorServer.this.wsTransport.send(asJsonObject.toString());
                            } else {
                                JsonElement realToken = asJsonObject.get("token");
                                long andIncrement = ActorServer.this.internalSequenceNumber.getAndIncrement();
                                Long lValueOf = Long.valueOf(andIncrement);
                                Map map = ActorServer.this.tokenMap;
                                Intrinsics.checkNotNullExpressionValue(realToken, "realToken");
                                map.put(lValueOf, realToken);
                                asJsonObject.add("token", EProjectInterfaceType.OCTA.getGson().toJsonTree(new BaseToken(Long.valueOf(andIncrement))));
                                asJsonObject.addProperty("senderRoutingKey", ActorServer.SENDER_ROUTING_KEY);
                                asJsonObject.addProperty("routingKey", ActorServer.SENDER_ROUTING_KEY);
                                jsonObject = asJsonObject;
                            }
                        }
                    } catch (Throwable th) {
                        ActorServer.INSTANCE.getLogger().warn("Error parsing json for message \"" + str + Typography.quote, th);
                    }
                }
                return jsonObject;
            }
        };
        Observable<R> map = observable.map(new Func1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ActorServer._init_$lambda$1(function1, obj);
            }
        });
        final Function1<JsonObject, Unit> function12 = new Function1<JsonObject, Unit>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsonObject jsonObject) {
                invoke2(jsonObject);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsonObject jsonObject) {
                if (jsonObject != null) {
                    ActorServer actorServer = ActorServer.this;
                    ActorServer.INSTANCE.getLogger().debug("sendjson: " + jsonObject);
                    actorServer.getRxTransport().sendJson(jsonObject);
                }
            }
        };
        map.subscribe((Action1<? super R>) new Action1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ActorServer._init_$lambda$2(function12, obj);
            }
        });
        Observable<JsonElement> jsonElementObservable = rxAmqpClientMots.getJsonElementObservable();
        final AnonymousClass4 anonymousClass4 = new Function1<JsonElement, Boolean>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.4
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(JsonElement jsonElement) {
                return Boolean.valueOf((jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("type") && Intrinsics.areEqual(jsonElement.getAsJsonObject().get("type").getAsString(), "request")) ? false : true);
            }
        };
        Observable<JsonElement> observableFilter = jsonElementObservable.filter(new Func1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ActorServer._init_$lambda$3(anonymousClass4, obj);
            }
        });
        final Function1<JsonElement, String> function13 = new Function1<JsonElement, String>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(JsonElement jsonElement) {
                String str = null;
                str = null;
                try {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    JsonElement jsonElement2 = (JsonElement) ActorServer.this.tokenMap.remove(((BaseToken) EProjectInterfaceType.OCTA.getGson().fromJson(asJsonObject.get("token"), BaseToken.class)).getSequenceId());
                    if (jsonElement2 != null) {
                        asJsonObject.add("token", jsonElement2);
                        asJsonObject.remove("routingKey");
                        String string = asJsonObject.toString();
                        str = string;
                        jsonElement = string;
                    } else {
                        jsonElement = jsonElement;
                    }
                } catch (Throwable th) {
                    ActorServer.INSTANCE.getLogger().warn("Error parsing json for result message \"" + jsonElement + Typography.quote, th);
                }
                return str;
            }
        };
        Observable<R> map2 = observableFilter.map(new Func1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda4
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ActorServer._init_$lambda$4(function13, obj);
            }
        });
        final Function1<String, Unit> function14 = new Function1<String, Unit>() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                if (str != null) {
                    ActorServer.this.wsTransport.send(str);
                }
            }
        };
        map2.subscribe((Action1<? super R>) new Action1() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ActorServer._init_$lambda$5(function14, obj);
            }
        });
    }

    /* JADX INFO: renamed from: getExecutor$ocservice_release, reason: from getter */
    public final ExecutorService getExecutor() {
        return this.executor;
    }

    /* JADX INFO: renamed from: getRxTransport$ocservice_release, reason: from getter */
    public final RxAmqpClientMots getRxTransport() {
        return this.rxTransport;
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.ctrl.ActorServer$1, reason: invalid class name */
    /* JADX INFO: compiled from: ActorServer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "status", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint$ConnectionState;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<OneCoreClientEndpoint.ConnectionState, Unit> {
        final /* synthetic */ Context $context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context) {
            super(1);
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(OneCoreClientEndpoint.ConnectionState connectionState) {
            invoke2(connectionState);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(OneCoreClientEndpoint.ConnectionState connectionState) {
            ActorServer.INSTANCE.getLogger().info("connection status: " + connectionState);
            if (connectionState == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
                ActorServer.this.onConnect();
                return;
            }
            if (connectionState == OneCoreClientEndpoint.ConnectionState.DISCONNECTING || connectionState == OneCoreClientEndpoint.ConnectionState.FAILURE) {
                ActorServer.this.onDisconnect();
                if (ActorServer.this.isRunning) {
                    Handler handler = new Handler(this.$context.getMainLooper());
                    final ActorServer actorServer = ActorServer.this;
                    handler.postDelayed(new Runnable() { // from class: com.neusoft.na.ocservice.ctrl.ActorServer$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActorServer.AnonymousClass1.invoke$lambda$0(actorServer);
                        }
                    }, 2000L);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActorServer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.wsTransport.connectAsync(ActorServer.MOTSBL_ENDPOINT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsonObject _init_$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return (JsonObject) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean _init_$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return (Boolean) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return (String) tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void start() {
        this.isRunning = true;
        this.wsTransport.connectAsync(MOTSBL_ENDPOINT);
    }

    public final void stop() {
        this.isRunning = false;
        this.wsTransport.close();
    }
}
