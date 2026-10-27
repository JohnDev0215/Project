package com.byd.dilink51.os;

import android.content.Context;
import android.os.IBYDCloudMqttListener;
import android.os.IBYDCloudMqttServer;
import android.os.IBinder;
import android.os.MqttServiceManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkCloudMqttListener;
import com.byd.dilink.os.IDiLinkCloudMqttServer;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class DiLinkCloudMqttServer implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f1622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CloudMqttListeners f1623b = new CloudMqttListeners();

    public static class CloudMqttListeners extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(final IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListener.Stub() { // from class: com.byd.dilink51.os.DiLinkCloudMqttServer.CloudMqttListeners.1
                @Keep
                public void msgReceived(byte[] bArr, int i) {
                    iDiLinkCloudMqttListener.msgReceived(bArr, i);
                }

                @Keep
                public void notify_mqtt_connect_status(int i) {
                    iDiLinkCloudMqttListener.notify_mqtt_connect_status(i);
                }
            };
        }
    }

    public DiLinkCloudMqttServer(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("mqttserv");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Cloud Mqtt Server");
        }
        this.f1622a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.removeListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void p(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).unregisterListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.addListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).registerListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.unregisterFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1622a.registerFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.w
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1674a.t((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f1622a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f1622a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f1622a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        this.f1622a.pubFidVpuMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f1622a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.v
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1672a.s(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1638a.n(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.r
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1663a.c(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.k
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1650a.l(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.j
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1648a.i(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.y
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1678a.f(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.u
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1670a.m(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.i
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1646a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.m
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1654a.z(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.n
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1656a.x(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.t
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1668a.h(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1623b.addCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.s
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.u(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.o
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1658a.o((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidControlMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidLogMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        this.f1622a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f1622a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        this.f1622a.setMqttBrokerConfigureEnv(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1632a.d(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.p
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1659a.w(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.q
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1661a.g(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1635a.r(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1642a.b(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1644a.y(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.a0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1627a.j(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1629a.k(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1640a.v(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.z
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1680a.q(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1623b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.l
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1652a.e(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1623b.removeCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.os.x
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.p(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }
}
