package com.ts.car.sdk.dm;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.car.sdk.dm.DmConsumer;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.aidl.IDeviceManagementService;
import com.ts.car.sdk.dm.data.DmDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import ts.car.service.dm.IDeviceManager;
import ts.car.service.dm.IDeviceManagerListener;
import ts.car.service.dm.IWirelessCarPlayAvailabilityCallback;
import ts.car.service.dm.data.ConnectRequest;
import ts.car.service.dm.data.UnifiedDevice;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformDeviceManagerService extends SimpleServiceBase implements IDeviceManager {
    private static final String REMOTE_SERVICE_NAME = "DeviceManagerService";
    private static final String SERVICE_COMPONENT = "com.ts.dm.service/.DeviceManagerService";
    private static String TAG = "PlatformDeviceManagerService";
    private IDeviceManagementListener mIDeviceManagementListenerImpl;
    private CopyOnWriteArrayList<IDeviceManagerListener> mListeners;
    private IDeviceManagementService mService;

    @PlatformConstruction
    public PlatformDeviceManagerService(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mListeners = new CopyOnWriteArrayList<>();
        this.mIDeviceManagementListenerImpl = new AnonymousClass2();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-DmService4.5.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder binder) {
        setService(binder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        this.mService = null;
    }

    private synchronized void setService(IBinder binder) {
        if (binder != null) {
            IDeviceManagementService iDeviceManagementServiceAsInterface = IDeviceManagementService.Stub.asInterface(binder);
            this.mService = iDeviceManagementServiceAsInterface;
            if (iDeviceManagementServiceAsInterface != null) {
                try {
                    iDeviceManagementServiceAsInterface.registerListener(this.mIDeviceManagementListenerImpl);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public void registerListener(IDeviceManagerListener listener) {
        if (this.mListeners.contains(listener)) {
            return;
        }
        this.mListeners.add(listener);
    }

    @Override // ts.car.service.dm.IDeviceManager
    public void unregisterListener(IDeviceManagerListener listener) {
        this.mListeners.remove(listener);
    }

    @Override // ts.car.service.dm.IDeviceManager
    public List<UnifiedDevice> getDeviceList() {
        ArrayList<UnifiedDevice> list = new ArrayList<>();
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null) {
            return list;
        }
        try {
            List<DmDevice> devices = iDeviceManagementService.getDeviceList();
            if (devices != null && devices.size() > 0) {
                for (DmDevice device : devices) {
                    list.add(Utils.convertToUnifiedDevice(device));
                }
            }
            return list;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return list;
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public boolean deleteDevice(UnifiedDevice device) {
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null || device == null) {
            return false;
        }
        try {
            iDeviceManagementService.deleteDevice(device.getDeviceUuid());
            return true;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return true;
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public int connect(UnifiedDevice device, int type) {
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null || device == null) {
            return -1;
        }
        try {
            int result = iDeviceManagementService.connect(device.getDeviceUuid(), Utils.convertCommonConstantsCapability(type));
            return result == 0 ? 0 : -1;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public int connect(ConnectRequest connectRequest) {
        if (this.mService == null || connectRequest == null) {
            return -1;
        }
        try {
            com.ts.car.sdk.dm.data.ConnectRequest newRequest = new com.ts.car.sdk.dm.data.ConnectRequest();
            newRequest.setDevice(Utils.convertToDmdDevice(connectRequest.getDevice()));
            newRequest.setCapabilityType(Utils.convertCommonConstantsCapability(connectRequest.getCapabilityType()));
            newRequest.setConnectType(Utils.convertConnectionType(connectRequest.getConnectionType()));
            int result = this.mService.connectByRequest(newRequest);
            return result == 0 ? 0 : -1;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public boolean disconnect(UnifiedDevice device, int type) {
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null || device == null) {
            return false;
        }
        try {
            return iDeviceManagementService.disconnect(device.getDeviceUuid(), Utils.convertCommonConstantsCapability(type)) == 0;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public void checkCarPlayWirelessCapability(final String mac, final IWirelessCarPlayAvailabilityCallback callback) {
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null) {
            return;
        }
        try {
            iDeviceManagementService.checkCarPlayWirelessCapability(mac, new ICheckResultCallback.Stub() { // from class: com.ts.car.sdk.dm.PlatformDeviceManagerService.1
                @Override // com.ts.car.sdk.dm.aidl.ICheckResultCallback
                public void callback(boolean support) {
                    callback.onWirelessCarPlayAvailabilityChanged(mac, support);
                }
            });
        } catch (RemoteException ex) {
            ex.printStackTrace();
            callback.onWirelessCarPlayAvailabilityChanged(mac, false);
        }
    }

    @Override // ts.car.service.dm.IDeviceManager
    public boolean cancelWirelessCarPlay(String mac) {
        IDeviceManagementService iDeviceManagementService = this.mService;
        if (iDeviceManagementService == null) {
            return false;
        }
        try {
            return iDeviceManagementService.cancelWirelessCarPlay(mac);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: renamed from: com.ts.car.sdk.dm.PlatformDeviceManagerService$2, reason: invalid class name */
    class AnonymousClass2 extends IDeviceManagementListener.Stub {
        AnonymousClass2() {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceChanged(final DmDevice device) {
            int optAction = device.getOptAction();
            if (optAction == 0) {
                PlatformDeviceManagerService.this.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.car.sdk.dm.-$$Lambda$PlatformDeviceManagerService$2$HUQJ0NLjMuBWLyGnarRi4yS6Oy8
                    @Override // com.ts.car.sdk.dm.DmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((IDeviceManagerListener) obj).onDeviceChanged(Utils.convertToUnifiedDevice(device));
                    }
                });
            } else if (optAction == 1) {
                PlatformDeviceManagerService.this.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.car.sdk.dm.-$$Lambda$PlatformDeviceManagerService$2$4sivPqYGQA-17rL5jxnKTzc5ETE
                    @Override // com.ts.car.sdk.dm.DmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((IDeviceManagerListener) obj).onDeviceAttached(Utils.convertToUnifiedDevice(device));
                    }
                });
            } else if (optAction == 2) {
                PlatformDeviceManagerService.this.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.car.sdk.dm.-$$Lambda$PlatformDeviceManagerService$2$1JMNGnP0fvFd0lQ4bZ1lBCNaPTU
                    @Override // com.ts.car.sdk.dm.DmConsumer.ReConsumer
                    public final void accept(Object obj) {
                        ((IDeviceManagerListener) obj).onDeviceDetached(Utils.convertToUnifiedDevice(device));
                    }
                });
            }
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceProjectionRequested(boolean supported, com.ts.car.sdk.dm.data.ConnectRequest request) {
        }

        @Override // com.ts.car.sdk.dm.aidl.IDeviceManagementListener
        public void onDeviceDetachedOverUsb(DmDevice device) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastListener(DmConsumer.ReConsumer<IDeviceManagerListener> consumer) {
        for (int i = 0; i < this.mListeners.size(); i++) {
            try {
                IDeviceManagerListener listener = this.mListeners.get(i);
                if (listener != null) {
                    consumer.accept(listener);
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
