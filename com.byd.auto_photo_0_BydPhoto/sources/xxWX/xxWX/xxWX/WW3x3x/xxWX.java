package xxWX.xxWX.xxWX.WW3x3x;

import android.content.Context;
import android.os.IBYDCloudMqttListener;
import android.os.IBYDCloudMqttServer;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkCloudMqttListener;
import com.byd.dilink.os.IDiLinkCloudMqttServer;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkCloudMqttServer.class, singleton = true)
public class xxWX implements IDiLinkCloudMqttServer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBYDCloudMqttServer f3245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f3246b = new a();

    public static class a extends DiLinkCallbackHolders<IDiLinkCloudMqttListener, IBYDCloudMqttListener> {

        /* JADX INFO: renamed from: xxWX.xxWX.xxWX.WW3x3x.xxWX$a$a, reason: collision with other inner class name */
        public class IBYDCloudMqttListenerStubC0091a extends IBYDCloudMqttListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkCloudMqttListener f3247a;

            public IBYDCloudMqttListenerStubC0091a(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
                this.f3247a = iDiLinkCloudMqttListener;
            }
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBYDCloudMqttListener createInnerCallback(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
            return new IBYDCloudMqttListenerStubC0091a(iDiLinkCloudMqttListener);
        }
    }

    public xxWX(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("mqttserv");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Cloud Mqtt Server");
        }
        this.f3245a = IBYDCloudMqttServer.Stub.asInterface(service);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        try {
            this.f3245a.unregisterListener(i, iBYDCloudMqttListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(IBYDCloudMqttListener iBYDCloudMqttListener) {
        try {
            this.f3245a.removeListener(iBYDCloudMqttListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(int i, IBYDCloudMqttListener iBYDCloudMqttListener) {
        try {
            this.f3245a.registerListener(i, iBYDCloudMqttListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(IBYDCloudMqttListener iBYDCloudMqttListener) {
        try {
            this.f3245a.addListener(iBYDCloudMqttListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f3246b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: xxWX.xxWX.xxWX.WW3x3x.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3244a.h((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void handlePingreq() {
        this.f3245a.handlePingreq();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public int isConnected() {
        return this.f3245a.isConnected();
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void notifyNw(int i) {
        this.f3245a.notifyNw(i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubFidVpuMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void pubMsg(int i, byte[] bArr, int i2, int i3) {
        this.f3245a.pubMsg(i, bArr, i2, i3);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void registerListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f3246b.addCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: xxWX.xxWX.xxWX.WW3x3x.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3239a.g(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean registerListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f3246b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: xxWX.xxWX.xxWX.WW3x3x.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3243a.b((IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidAutoHalMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidConfigMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidControlMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidDiagMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidInfoMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidLogMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidMcuMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStatusMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void sendFidStrategyMsg(int i, byte[] bArr, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigure(byte[] bArr, int i) {
        this.f3245a.setMqttBrokerConfigure(bArr, i);
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void setMqttBrokerConfigureEnv(byte[] bArr, int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有这个接口!");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public void unregisterListener(final int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        this.f3246b.removeCallback(iDiLinkCloudMqttListener, new IDiLinkCallbackHandler() { // from class: xxWX.xxWX.xxWX.WW3x3x.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f3241a.a(i, (IBYDCloudMqttListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkCloudMqttServer
    public boolean unregisterListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }
}
