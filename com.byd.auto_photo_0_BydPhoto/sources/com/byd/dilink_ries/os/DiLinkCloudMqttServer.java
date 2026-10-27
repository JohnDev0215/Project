package com.byd.dilink_ries.os;

import android.content.Context;
import android.os.IBYDCloudMqttListener;
import android.os.IBYDCloudMqttServer;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import androidx.annotation.Keep;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkCloudMqttListener;
import com.byd.dilink.os.IDiLinkCloudMqttServer;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class DiLinkCloudMqttServer implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f2638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CloudMqttListeners f2639b = new CloudMqttListeners();

    public static class CloudMqttListeners extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(final IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListener.Stub() { // from class: com.byd.dilink_ries.os.DiLinkCloudMqttServer.CloudMqttListeners.1
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
        this.f2638a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2638a.unregisterListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2638a.removeListener(iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2638a.registerListener(i, iBYDCloudMqttListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(IBYDCloudMqttListener iBYDCloudMqttListener) {
        this.f2638a.addListener(iBYDCloudMqttListener);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2639b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.os.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2646a.h((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f2638a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f2638a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f2638a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f2638a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2639b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.os.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2644a.g(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RSE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2639b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.os.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2643a.b((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f2638a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        this.f2638a.setMqttBrokerConfigureEnv(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RISE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f2639b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.os.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2647a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("RSE 不存在此接口！");
    }
}
