package com.byd.dilink_DiR.os;

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
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class DiLinkCloudMqttServer implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f2258a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CloudMqttListeners f2259b = new CloudMqttListeners();

    public static class CloudMqttListeners extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(final IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListener.Stub() { // from class: com.byd.dilink_DiR.os.DiLinkCloudMqttServer.CloudMqttListeners.1
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
        this.f2258a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.addListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void Q(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).registerListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void R(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidDiagEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidVpuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.removeListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void Y(AtomicBoolean atomicBoolean, Context context, int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        atomicBoolean.set(MqttServiceManager.getMqttServiceManager(MemorySafeContext.get(context)).unregisterListenerByFid(i, iBYDCloudMqttListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidStatusEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidStrategyEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidConfigEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidAutoHalEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidMcuEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.registerFidInfoEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidControlEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidLogEvent(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2258a.unregisterFidStatusEvent(i, iBYDCloudMqttListener);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.u
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2308a.P((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f2258a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f2258a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f2258a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        this.f2258a.pubFidVpuMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f2258a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.s
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2304a.O(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2272a.W(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.i
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2283a.b(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.t
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2306a.T(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2275a.i(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.w
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2311a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.l
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2289a.h(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.a0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2263a.Z(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.j
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2285a.d(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.o
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2296a.M(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.k
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2287a.R(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f2259b.addCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.Q(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.y
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2315a.X((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidControlMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidLogMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        this.f2258a.sendFidConfigMsg(i, bArr, i2);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f2258a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        this.f2258a.setMqttBrokerConfigureEnv(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2265a.g(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.q
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2300a.e(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2281a.j(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.n
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2294a.N(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.g
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2279a.f(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.r
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2302a.k(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.p
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2298a.U(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.v
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2309a.l(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.z
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2316a.c(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.x
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2313a.V(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2259b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2277a.S(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(final Context context, final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f2259b.removeCallbackIgnoreRemoteException(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.os.m
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkCloudMqttServer.Y(atomicBoolean, context, i, (IBYDCloudMqttListener) obj);
            }
        });
        return atomicBoolean.get();
    }
}
