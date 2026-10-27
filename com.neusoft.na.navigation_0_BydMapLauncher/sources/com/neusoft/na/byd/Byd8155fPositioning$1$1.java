package com.neusoft.na.byd;

import android.os.Handler;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreConnectionException;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Byd8155fPositioning.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "status", "Lcom/neusoft/de/nts/OneCoreTest/communication/transports/websocketclient/OneCoreClientEndpoint$ConnectionState;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fPositioning$1$1 extends Lambda implements Function1<OneCoreClientEndpoint.ConnectionState, Unit> {
    final /* synthetic */ Byd8155fPositioning this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fPositioning$1$1(Byd8155fPositioning byd8155fPositioning) {
        super(1);
        this.this$0 = byd8155fPositioning;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(OneCoreClientEndpoint.ConnectionState connectionState) {
        invoke2(connectionState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(OneCoreClientEndpoint.ConnectionState status) {
        Intrinsics.checkNotNullParameter(status, "status");
        Byd8155fPositioning.INSTANCE.getLogger().info("connection status: " + status);
        if (status == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            this.this$0.onPosConnected(new OneCoreWSContextUtil(EProjectInterfaceType.OneCore, this.this$0.transport, this.this$0.threadPool));
            return;
        }
        if (status == OneCoreClientEndpoint.ConnectionState.DISCONNECTING || status == OneCoreClientEndpoint.ConnectionState.FAILURE) {
            this.this$0.onPosDisconnect();
            if (this.this$0.isRunning) {
                Handler handler = this.this$0.handler;
                final Byd8155fPositioning byd8155fPositioning = this.this$0;
                handler.postDelayed(new Runnable() { // from class: com.neusoft.na.byd.Byd8155fPositioning$1$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Byd8155fPositioning$1$1.invoke$lambda$0(byd8155fPositioning);
                    }
                }, 2000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(Byd8155fPositioning this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            OneCoreClientEndpoint oneCoreClientEndpoint = this$0.transport;
            if (oneCoreClientEndpoint != null) {
                oneCoreClientEndpoint.connectAsync("ws://127.0.0.1:22532");
            }
        } catch (OneCoreConnectionException e) {
            Byd8155fPositioning.INSTANCE.getLogger().error("OC connection error - " + e);
            e.printStackTrace();
        }
    }
}
