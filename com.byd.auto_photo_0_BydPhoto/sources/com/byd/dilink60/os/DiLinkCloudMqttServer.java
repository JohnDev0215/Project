package com.byd.dilink60.os;

import android.content.Context;
import android.os.IBYDCloudMqttListener;
import android.os.IBYDCloudMqttServer;
import android.os.IBinder;
import android.os.MqttServiceManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkCloudMqttListener;
import com.byd.dilink.os.IDiLinkCloudMqttServer;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class DiLinkCloudMqttServer implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f2059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CloudMqttListeners f2060b = new CloudMqttListeners();

    public static class CloudMqttListeners extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(final IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListener.Stub() { // from class: com.byd.dilink60.os.DiLinkCloudMqttServer.CloudMqttListeners.1
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
        this.f2059a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.addListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).registerListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.removeListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).unregisterListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.registerListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2059a.unregisterFidDiagEvent(i, iBYDCloudMqttListener);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2080a.c((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f2059a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f2059a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f2059a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        this.f2059a.pubFidVpuMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f2059a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2069a.b(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2076a.e(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.u
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2107a.n(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2078a.p(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.v
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2109a.k(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.t
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2105a.x(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.w
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2111a.u(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.s
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2103a.w(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.j
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2083a.j(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.p
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2096a.h(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.n
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2092a.y(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f2060b.addCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.r
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.d(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2075a.f((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidControlMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidLogMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        this.f2059a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f2059a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        this.f2059a.setMqttBrokerConfigureEnv(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.z
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2117a.v(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.o
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2094a.r(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.i
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2081a.t(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.q
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2098a.z(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.k
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2085a.o(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.x
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2113a.s(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2066a.l(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.y
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2115a.i(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.a0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2064a.m(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.l
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2087a.q(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2060b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2072a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f2060b.removeCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.m
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.g(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }
}
