package com.byd.dilink51_main.os;

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
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class DiLinkCloudMqttServer implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f1830a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CloudMqttListeners f1831b = new CloudMqttListeners();

    public static class CloudMqttListeners extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(final IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListener.Stub() { // from class: com.byd.dilink51_main.os.DiLinkCloudMqttServer.CloudMqttListeners.1
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
        this.f1830a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.addListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).registerListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.removeListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void p(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).unregisterListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.unregisterFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f1830a.registerFidControlEvent(i, iBYDCloudMqttListener);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.q
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1872a.d((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f1830a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f1830a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f1830a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        this.f1830a.pubFidVpuMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f1830a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.n
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1866a.c(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.x
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1884a.n(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.o
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1868a.z(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.y
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1886a.u(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.z
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1888a.b(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.l
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1861a.i(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.i
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1855a.f(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1846a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.t
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1876a.g(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1848a.j(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.v
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1880a.q(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1831b.addCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.m
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.e(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.r
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1873a.o((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidControlMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidLogMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        this.f1830a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f1830a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        this.f1830a.setMqttBrokerConfigureEnv(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1840a.k(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.a0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1835a.h(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.u
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1878a.v(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.j
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1857a.x(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1850a.r(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.s
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1874a.l(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.w
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1882a.y(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.b0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1838a.m(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1843a.w(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.p
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1870a.t(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f1831b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.k
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1859a.s(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1831b.removeCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.os.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.p(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }
}
