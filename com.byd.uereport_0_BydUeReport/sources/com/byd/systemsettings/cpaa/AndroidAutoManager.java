package com.byd.systemsettings.cpaa;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ts.car.core.ServiceProvider;
import j7.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p2.g;
import q2.d;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoDeviceListListener;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoManager {
    public static final String TAG = "AndroidAutoManager";
    private static volatile AndroidAutoManager mInstance;
    private AndroidAutoDeviceListListener deviceListListener;
    private ConcurrentHashMap<String, IAndroidAutoAppListener> mAAAppListenerMap;
    private ConcurrentHashMap<String, IAndroidAutoSessionStatusListener> mAAStateListenerMap;
    private ConcurrentHashMap<String, AndroidAutoDeviceListListener> mAAStatusListenerMap;
    private volatile boolean mAppConnected;
    private final Context mContext;
    private volatile boolean mDevicesConnected;
    private IAndroidAutoDeviceListManager mAndroidAutoDeviceManager = null;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private final IAndroidAutoAppListener mAndroidAutoAppListener = new IAndroidAutoAppListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.1
        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onVideoFocusStatusChanged(final int i8) {
            a.a(AndroidAutoManager.TAG, "onVideoFocusStatusChanged:" + i8);
            g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.1.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean z7 = i8 == 1;
                    if (z7) {
                        d.a();
                    }
                    if (AndroidAutoManager.this.deviceListListener != null) {
                        AndroidAutoManager.this.deviceListListener.onVideoFocusStatusChanged(z7);
                    }
                    if (AndroidAutoManager.this.mAAStatusListenerMap == null || AndroidAutoManager.this.mAAStatusListenerMap.isEmpty()) {
                        return;
                    }
                    Iterator it = AndroidAutoManager.this.mAAStatusListenerMap.values().iterator();
                    while (it.hasNext()) {
                        ((AndroidAutoDeviceListListener) it.next()).onVideoFocusStatusChanged(z7);
                    }
                }
            });
        }
    };
    private final IAndroidAutoDeviceListListener mAndroidAutoDeviceListListener = new IAndroidAutoDeviceListListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.2
        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(final AndroidAutoDevice androidAutoDevice) {
            a.a(AndroidAutoManager.TAG, "onAndroidAutoDeviceStatusChanged:" + androidAutoDevice.getDeviceStatus());
            g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AndroidAutoManager.this.deviceListListener != null) {
                        AndroidAutoManager.this.deviceListListener.onAndroidAutoDeviceStatusChanged(androidAutoDevice.getDeviceStatus(), androidAutoDevice);
                    }
                    if (AndroidAutoManager.this.mAAStatusListenerMap == null || AndroidAutoManager.this.mAAStatusListenerMap.isEmpty()) {
                        return;
                    }
                    Iterator it = AndroidAutoManager.this.mAAStatusListenerMap.values().iterator();
                    while (it.hasNext()) {
                        ((AndroidAutoDeviceListListener) it.next()).onAndroidAutoDeviceStatusChanged(androidAutoDevice.getDeviceStatus(), androidAutoDevice);
                    }
                }
            });
        }

        @Override // ts.car.service.androidauto.IAndroidAutoDeviceListListener
        public void onProjectionRequested(AndroidAutoDevice androidAutoDevice, int i8) {
            a.a(AndroidAutoManager.TAG, "onProjectionRequested:" + androidAutoDevice);
            if (AndroidAutoManager.this.mAAStatusListenerMap == null || AndroidAutoManager.this.mAAStatusListenerMap.isEmpty()) {
                return;
            }
            Iterator it = AndroidAutoManager.this.mAAStatusListenerMap.values().iterator();
            while (it.hasNext()) {
                ((AndroidAutoDeviceListListener) it.next()).onProjectionRequested(androidAutoDevice);
            }
        }
    };
    private final IAndroidAutoSessionStatusListener mAASessionStatusListener = new IAndroidAutoSessionStatusListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.3
        @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
        public void onSessionStatusChanged(final int i8, int i9, String str) {
            a.a(AndroidAutoManager.TAG, "onSessionStateChanged:" + i8);
            g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.3.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AndroidAutoManager.this.deviceListListener != null) {
                        AndroidAutoManager.this.deviceListListener.onAndroidAutoHasConnected(4 == i8);
                    }
                    if (AndroidAutoManager.this.mAAStateListenerMap == null || AndroidAutoManager.this.mAAStateListenerMap.isEmpty()) {
                        return;
                    }
                    for (AndroidAutoDeviceListListener androidAutoDeviceListListener : AndroidAutoManager.this.mAAStatusListenerMap.values()) {
                        if (androidAutoDeviceListListener != null) {
                            androidAutoDeviceListListener.onSessionStatusChanged(i8);
                        }
                    }
                }
            });
        }
    };
    private final IServiceConnectionListener appListener = new IServiceConnectionListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.4
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            AndroidAutoManager.this.mAppConnected = true;
            try {
                AndroidAutoManager.this.mAndroidAutoAppManager.registerAndroidAutoAppListener(AndroidAutoManager.this.mAndroidAutoAppListener);
                AndroidAutoManager.this.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(AndroidAutoManager.this.mAASessionStatusListener);
                a.c(AndroidAutoManager.TAG, "--onServiceConnected--mac: " + m4.d.d(AndroidAutoManager.this.mAndroidAutoAppManager.getProjectionBluetoothAddress()));
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
            a.c(AndroidAutoManager.TAG, "mAndroidAutoAppManager.onServiceConnected:" + iPlatformService);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            AndroidAutoManager.this.mAppConnected = false;
            if (AndroidAutoManager.this.mAndroidAutoAppManager != null) {
                try {
                    AndroidAutoManager.this.mAndroidAutoAppManager.unregisterAndroidAutoAppListener(AndroidAutoManager.this.mAndroidAutoAppListener);
                    AndroidAutoManager.this.mAndroidAutoAppManager.unregisterAndroidAutoSessionStatusListener(AndroidAutoManager.this.mAASessionStatusListener);
                } catch (RemoteException e8) {
                    e8.printStackTrace();
                }
            }
            a.c(AndroidAutoManager.TAG, "mAndroidAutoAppManager.onServiceDisconnected:" + iPlatformService);
        }
    };
    private final IServiceConnectionListener deviceListener = new IServiceConnectionListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.5
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            try {
                AndroidAutoManager.this.mAndroidAutoDeviceManager.registerAndroidAutoDeviceListListener(AndroidAutoManager.this.mAndroidAutoDeviceListListener);
                AndroidAutoManager.this.mDevicesConnected = true;
                c.c().k(new m2.a(230008));
                a.c(AndroidAutoManager.TAG, "mAndroidAutoDeviceManager.onServiceConnected:" + iPlatformService);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            AndroidAutoManager.this.mDevicesConnected = false;
            if (AndroidAutoManager.this.mAndroidAutoDeviceManager != null) {
                try {
                    AndroidAutoManager.this.mAndroidAutoDeviceManager.unregisterAndroidAutoDeviceListListener(AndroidAutoManager.this.mAndroidAutoDeviceListListener);
                } catch (RemoteException e8) {
                    e8.printStackTrace();
                }
            }
            a.c(AndroidAutoManager.TAG, "mAndroidAutoDeviceManager.onServiceDisconnected:" + iPlatformService);
        }
    };
    private final IAndroidAutoAppListener aaAppListener = new IAndroidAutoAppListener() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.6
        @Override // ts.car.service.androidauto.IAndroidAutoAppListener
        public void onProjectionBluetoothAddressChanged(final String str) {
            a.a(AndroidAutoManager.TAG, "--onProjectionBluetoothAddressChanged--address: " + str);
            if (AndroidAutoManager.this.mAppConnected) {
                g.a().g(new Runnable() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (IAndroidAutoAppListener iAndroidAutoAppListener : AndroidAutoManager.this.mAAAppListenerMap.values()) {
                            if (iAndroidAutoAppListener != null) {
                                iAndroidAutoAppListener.onProjectionBluetoothAddressChanged(str);
                            }
                        }
                    }
                });
            }
        }
    };

    public interface AndroidAutoDeviceListListener {
        default void onAndroidAutoDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
        }

        default void onAndroidAutoHasConnected(boolean z7) {
        }

        default void onProjectionRequested(AndroidAutoDevice androidAutoDevice) {
        }

        default void onSessionStatusChanged(int i8) {
        }

        default void onVideoFocusStatusChanged(boolean z7) {
        }
    }

    private AndroidAutoManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static AndroidAutoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AndroidAutoManager.class) {
                if (mInstance == null) {
                    mInstance = new AndroidAutoManager(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public void connectAndroidAuto(ConnectRequest connectRequest) {
        if (this.mAndroidAutoDeviceManager == null) {
            a.b(TAG, "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.b(TAG, "mDevicesConnected not connect");
            return;
        }
        try {
            a.a(TAG, "connectAndroidAuto request:" + connectRequest.getDeviceId());
            Intent intent = new Intent("carsettings_device_connect_androidauto_action");
            intent.setPackage("com.byd.uereport");
            intent.putExtra("deviceId", connectRequest.getDeviceId());
            intent.putExtra("conType", connectRequest.getConnectType());
            this.mContext.sendBroadcast(intent);
        } catch (Exception e8) {
            e8.printStackTrace();
            a.b(TAG, "connectAndroidAuto ,RemoteException:" + e8);
        }
    }

    public void deleteAndroidAutoDevice(String str) {
        if (this.mAndroidAutoDeviceManager == null) {
            a.b(TAG, "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.b(TAG, "mDevicesConnected not connect");
            return;
        }
        try {
            this.mAndroidAutoDeviceManager.deleteAndroidAutoDevice(str);
            a.a(TAG, "deleteAndroidAutoDevice devId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.b(TAG, "deleteAndroidAutoDevice ,RemoteException:" + e8);
        }
    }

    public void disconnectAndroidAuto(String str) {
        if (this.mAndroidAutoDeviceManager == null) {
            a.b(TAG, "mAndroidAutoDeviceManager == null");
            return;
        }
        if (!this.mDevicesConnected) {
            a.b(TAG, "mDevicesConnected not connect");
            return;
        }
        try {
            this.mAndroidAutoDeviceManager.disconnectAndroidAuto(str);
            a.a(TAG, "disconnectAndroidAuto deviceId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public List<AndroidAutoDevice> getAndroidAutoDevices() {
        List<AndroidAutoDevice> arrayList = new ArrayList<>();
        if (this.mAndroidAutoDeviceManager == null) {
            a.b(TAG, "mAndroidAutoDeviceManager == null");
            return arrayList;
        }
        if (!this.mDevicesConnected) {
            a.b(TAG, "mDevicesConnected not connect");
            return arrayList;
        }
        try {
            arrayList = this.mAndroidAutoDeviceManager.getAndroidAutoDeviceList();
        } catch (RemoteException | RuntimeException e8) {
            e8.printStackTrace();
            a.b(TAG, "getAndroidAutoDevices,RemoteException:" + e8);
        }
        a.a(TAG, "getAndroidAutoDevices:" + arrayList);
        return arrayList;
    }

    public int getAndroidAutoStatus() {
        int androidAutoSessionStatus;
        if (this.mAndroidAutoAppManager == null) {
            a.b(TAG, "mAndroidAutoAppManager == null");
            return 0;
        }
        if (!this.mAppConnected) {
            a.b(TAG, "mAppConnected not connect");
            return 0;
        }
        try {
            androidAutoSessionStatus = this.mAndroidAutoAppManager.getAndroidAutoSessionStatus();
        } catch (RemoteException | RuntimeException e8) {
            e8.printStackTrace();
            a.b(TAG, "getAndroidAutoStatus,RemoteException:" + e8);
            androidAutoSessionStatus = 0;
        }
        a.a(TAG, "getAndroidAutoStatus:" + androidAutoSessionStatus);
        return androidAutoSessionStatus;
    }

    public AndroidAutoDevice getConnectedAndroidAutoDevice() {
        for (AndroidAutoDevice androidAutoDevice : getAndroidAutoDevices()) {
            if (androidAutoDevice.getDeviceStatus() == 4) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    public AndroidAutoDevice getConnectingAndroidAutoDevice() {
        for (AndroidAutoDevice androidAutoDevice : getAndroidAutoDevices()) {
            if (androidAutoDevice.getDeviceStatus() == 3) {
                return androidAutoDevice;
            }
        }
        return null;
    }

    public String getProjectionBluetoothAddress() {
        if (this.mAndroidAutoAppManager != null && this.mAppConnected) {
            try {
                return this.mAndroidAutoAppManager.getProjectionBluetoothAddress();
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
        return null;
    }

    public void init() {
        if (this.mAAStateListenerMap == null) {
            this.mAAStateListenerMap = new ConcurrentHashMap<>();
        }
        if (this.mAAAppListenerMap == null) {
            this.mAAAppListenerMap = new ConcurrentHashMap<>();
        }
        if (this.mAAStatusListenerMap == null) {
            this.mAAStatusListenerMap = new ConcurrentHashMap<>();
        }
        if (this.mAndroidAutoDeviceManager == null) {
            this.mAndroidAutoDeviceManager = (IAndroidAutoDeviceListManager) ServiceProvider.get(IAndroidAutoDeviceListManager.class, this.mContext);
        }
        if (this.mAndroidAutoAppManager == null) {
            this.mAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
        }
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceManager;
        if (iAndroidAutoDeviceListManager != null) {
            iAndroidAutoDeviceListManager.registerConnectionListener(this.deviceListener);
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.appListener);
        }
    }

    public boolean isAndroidAutoConnected() {
        return getAndroidAutoStatus() == 4;
    }

    public boolean isAndroidAutoConnecting() {
        return getAndroidAutoStatus() == 3;
    }

    public boolean isAndroidAutoDeviceConnected(String str) {
        AndroidAutoDevice connectedAndroidAutoDevice = getConnectedAndroidAutoDevice();
        if (connectedAndroidAutoDevice == null || str == null) {
            return false;
        }
        return str.equals(connectedAndroidAutoDevice.getBtAddr());
    }

    public boolean isAndroidAutoWifiConnected() {
        AndroidAutoDevice connectedAndroidAutoDevice = getConnectedAndroidAutoDevice();
        return (connectedAndroidAutoDevice == null || connectedAndroidAutoDevice.isUsbDevice()) ? false : true;
    }

    public void registerAndroidAutoAppListener(String str, IAndroidAutoAppListener iAndroidAutoAppListener) {
        ConcurrentHashMap<String, IAndroidAutoAppListener> concurrentHashMap = this.mAAAppListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iAndroidAutoAppListener);
    }

    public void registerAndroidAutoDeviceListListener(String str, AndroidAutoDeviceListListener androidAutoDeviceListListener) {
        ConcurrentHashMap<String, AndroidAutoDeviceListListener> concurrentHashMap = this.mAAStatusListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, androidAutoDeviceListListener);
    }

    public void registerAndroidAutoStateListener(String str, IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) {
        ConcurrentHashMap<String, IAndroidAutoSessionStatusListener> concurrentHashMap = this.mAAStateListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iAndroidAutoSessionStatusListener);
    }

    public void setDeviceListListener(AndroidAutoDeviceListListener androidAutoDeviceListListener) {
        this.deviceListListener = androidAutoDeviceListListener;
    }

    public void startAndroidAutoUI() {
        Intent intent = new Intent();
        intent.setClassName(CarPlayAAConstant.ANDROID_AUTO_PACKAGE_NAME, CarPlayAAConstant.ANDROID_AUTO_CLASS_NAME);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    public void unRegister() {
        a.a(TAG, "AndroidAuto unregisterConnectionListener");
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.unregisterConnectionListener(this.appListener);
            try {
                this.mAndroidAutoAppManager.unregisterAndroidAutoAppListener(this.mAndroidAutoAppListener);
                this.mAndroidAutoAppManager.unregisterAndroidAutoSessionStatusListener(this.mAASessionStatusListener);
            } catch (RemoteException | RuntimeException e8) {
                e8.printStackTrace();
            }
        }
        IAndroidAutoDeviceListManager iAndroidAutoDeviceListManager = this.mAndroidAutoDeviceManager;
        if (iAndroidAutoDeviceListManager != null) {
            iAndroidAutoDeviceListManager.unregisterConnectionListener(this.deviceListener);
            try {
                this.mAndroidAutoDeviceManager.unregisterAndroidAutoDeviceListListener(this.mAndroidAutoDeviceListListener);
            } catch (RemoteException | RuntimeException e9) {
                e9.printStackTrace();
            }
        }
        ConcurrentHashMap<String, AndroidAutoDeviceListListener> concurrentHashMap = this.mAAStatusListenerMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public void unpairDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            a.b(TAG, "device == null");
            return;
        }
        final String address = bluetoothDevice.getAddress();
        if (TextUtils.isEmpty(address)) {
            a.b(TAG, "btAdd isEmpty");
            return;
        }
        List<AndroidAutoDevice> androidAutoDevices = getAndroidAutoDevices();
        if (androidAutoDevices == null || androidAutoDevices.size() == 0) {
            a.b(TAG, "androidAutoDevices.size() == 0");
            return;
        }
        for (AndroidAutoDevice androidAutoDevice : androidAutoDevices) {
            if (address.equals(androidAutoDevice.getBtAddr())) {
                if (androidAutoDevice.getDeviceStatus() == 4) {
                    disconnectAndroidAuto(address);
                }
                g.a().d(new Runnable() { // from class: com.byd.systemsettings.cpaa.AndroidAutoManager.7
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidAutoManager.this.deleteAndroidAutoDevice(address);
                    }
                }, 200L);
            }
        }
    }

    public void unregisterAndroidAutoAppListener(String str) {
        ConcurrentHashMap<String, IAndroidAutoAppListener> concurrentHashMap = this.mAAAppListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterAndroidAutoDeviceListListener(String str) {
        ConcurrentHashMap<String, AndroidAutoDeviceListListener> concurrentHashMap = this.mAAStatusListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterAndroidAutoStateListener(String str) {
        ConcurrentHashMap<String, IAndroidAutoSessionStatusListener> concurrentHashMap = this.mAAStateListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }
}
