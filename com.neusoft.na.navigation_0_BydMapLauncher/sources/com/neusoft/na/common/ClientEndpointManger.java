package com.neusoft.na.common;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;
import rx.functions.Action1;

/* JADX INFO: compiled from: ClientEndpointManger.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/neusoft/na/common/ClientEndpointManger;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "callbackList", "Ljava/util/ArrayList;", "Lcom/neusoft/na/common/ClientEndpointMangerCallback;", "Lkotlin/collections/ArrayList;", "getContext", "()Landroid/content/Context;", "handler", "Landroid/os/Handler;", "isRunning", "", "threadPool", "Ljava/util/concurrent/ExecutorService;", NotificationCompat.CATEGORY_TRANSPORT, "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint;", "wsContext", "Lcom/neusoft/de/nts/OneCoreTest/communication/utils/OneCoreWSContextUtil;", "getCWSContextUtil", "getConnectionState", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint$ConnectionState;", "onServerConnect", "", "onServerDisconnect", "setClientEndpointMangerCallback", "callback", "unsetClientEndpointMangerCallback", "Companion", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ClientEndpointManger {
    private static final String MOTSBL_ENDPOINT = "ws://127.0.0.1:22559";
    private static ClientEndpointManger instance;
    private final String TAG;
    private ArrayList<ClientEndpointMangerCallback> callbackList;
    private final Context context;
    private final Handler handler;
    private boolean isRunning;
    private final ExecutorService threadPool;
    private final OneCoreClientEndpoint transport;
    private OneCoreWSContextUtil wsContext;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static OneCoreClientEndpoint.ConnectionState connectionState = OneCoreClientEndpoint.ConnectionState.DISCONNECTED;

    /* JADX INFO: compiled from: ClientEndpointManger.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/neusoft/na/common/ClientEndpointManger$Companion;", "Lmu/KLogging;", "()V", "MOTSBL_ENDPOINT", "", "connectionState", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint$ConnectionState;", "instance", "Lcom/neusoft/na/common/ClientEndpointManger;", "getInstance", "context", "Landroid/content/Context;", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ClientEndpointManger getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ClientEndpointManger.instance == null) {
                ClientEndpointManger.instance = new ClientEndpointManger(context);
            }
            return ClientEndpointManger.instance;
        }
    }

    public ClientEndpointManger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "XJClientEndpointManger";
        this.handler = new Handler(context.getMainLooper());
        this.callbackList = new ArrayList<>();
        Log.i("XJClientEndpointManger", "init");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.threadPool = executorServiceNewSingleThreadExecutor;
        OneCoreClientEndpoint oneCoreClientEndpointBuild = new OneCoreClientEndpoint.Builder().connectTimeout(1000L).pingInterval(30000L).retryOnConnectionFailure(true).build();
        Intrinsics.checkNotNullExpressionValue(oneCoreClientEndpointBuild, "Builder().connectTimeout…tionFailure(true).build()");
        this.transport = oneCoreClientEndpointBuild;
        oneCoreClientEndpointBuild.getConnectionObservable().subscribe(new Action1() { // from class: com.neusoft.na.common.ClientEndpointManger$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ClientEndpointManger._init_$lambda$1(this.f$0, (OneCoreClientEndpoint.ConnectionState) obj);
            }
        });
        oneCoreClientEndpointBuild.connectAsync(MOTSBL_ENDPOINT);
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(final ClientEndpointManger this$0, OneCoreClientEndpoint.ConnectionState status) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(status, "status");
        INSTANCE.getLogger().info("connection status: " + status);
        Log.i(this$0.TAG, "connection status: " + status);
        connectionState = status;
        if (status == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            OneCoreWSContextUtil oneCoreWSContextUtil = new OneCoreWSContextUtil(EProjectInterfaceType.OneCore, this$0.transport, this$0.threadPool);
            this$0.wsContext = oneCoreWSContextUtil;
            Intrinsics.checkNotNull(oneCoreWSContextUtil);
            this$0.onServerConnect(oneCoreWSContextUtil);
            return;
        }
        if (status == OneCoreClientEndpoint.ConnectionState.DISCONNECTING || status == OneCoreClientEndpoint.ConnectionState.FAILURE) {
            this$0.onServerDisconnect();
            this$0.handler.postDelayed(new Runnable() { // from class: com.neusoft.na.common.ClientEndpointManger$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ClientEndpointManger.lambda$1$lambda$0(this.f$0);
                }
            }, 1000L);
        }
    }

    static final void lambda$1$lambda$0(ClientEndpointManger this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.transport.connectAsync(MOTSBL_ENDPOINT);
    }

    private final void onServerConnect(OneCoreWSContextUtil context) {
        int size = this.callbackList.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            this.callbackList.get(i).onServerConnect(context);
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    private final synchronized void onServerDisconnect() {
        int size = this.callbackList.size() - 1;
        if (size >= 0) {
            int i = 0;
            while (true) {
                this.callbackList.get(i).onServerDisconnect();
                if (i == size) {
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    public final void setClientEndpointMangerCallback(ClientEndpointMangerCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.i(this.TAG, "setClientEndpointMangerCallback");
        this.callbackList.add(callback);
    }

    public final void unsetClientEndpointMangerCallback(ClientEndpointMangerCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.i(this.TAG, "unsetClientEndpointMangerCallback");
        this.callbackList.remove(callback);
    }

    /* JADX INFO: renamed from: getCWSContextUtil, reason: from getter */
    public final OneCoreWSContextUtil getWsContext() {
        return this.wsContext;
    }

    public final OneCoreClientEndpoint.ConnectionState getConnectionState() {
        return connectionState;
    }
}
