package com.byd.systemsettings.cpaa;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.systemsettings.btsetting.model.CarPlayParams;
import com.ts.car.core.ServiceProvider;
import j7.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p2.g;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListListener;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.ConnectRequest;
import x3.a;
import y2.b;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayManager {
    public static final String TAG = "CarPlayManager";
    private static volatile CarPlayManager mInstance;
    public static boolean mIsShow;
    private CarPlayDeviceListListener deviceListListener;
    private boolean mAppServiceConnected;
    private ConcurrentHashMap<String, CarPlayDeviceListListener> mCPStateListenerMap;
    private final Context mContext;
    private boolean mDeviceServiceConnected;
    private ICarPlayDeviceListManager mCarPlayDeviceManager = null;
    private ICarPlayAppManager mCarPlayAppManager = null;
    private final ICarPlayAppListener mCarPlayAppListener = new ICarPlayAppListener() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.1
        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            CarPlayManager.mIsShow = z7;
            a.a(CarPlayManager.TAG, "ue_onCarPlayDisplayStatusChanged:" + z7);
            if (SystemPanelChangeReceiver.CURRENT_PANEL_STATE != 0) {
                a.b(CarPlayManager.TAG, "状态栏面板非默认状态,进入CP投屏不关闭系统设置弹框,3s后恢复默认状态");
            } else {
                CarPlayManager.this.carPlayDisplayStatusChanged(z7, -1);
            }
        }
    };
    private final ICarPlayStateListener mCPStateListener = new ICarPlayStateListener() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.2
        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(final int i8) {
            a.a(CarPlayManager.TAG, "onSessionStateChanged:" + i8);
            if (CarPlayManager.this.deviceListListener != null) {
                g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CarPlayManager.this.deviceListListener.onCarPlaySessionStatusChanged(i8 == 2);
                    }
                });
            }
            CarPlayManager.this.onCarPlaySessionStateChanged(i8);
        }
    };
    private final IServiceConnectionListener appServiceConnectListener = new IServiceConnectionListener() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.4
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            CarPlayManager.this.mAppServiceConnected = true;
            try {
                CarPlayManager.this.mCarPlayAppManager.registerCarPlayAppListener(CarPlayManager.this.mCarPlayAppListener);
                CarPlayManager.this.mCarPlayAppManager.registerCarPlayStateListener(CarPlayManager.this.mCPStateListener);
                c.c().k(new m2.a(230008));
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
            a.c(CarPlayManager.TAG, "mCarPlayAppManager.onServiceConnected:" + iPlatformService);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            CarPlayManager.this.mAppServiceConnected = false;
            try {
                if (CarPlayManager.this.mCarPlayAppManager != null) {
                    CarPlayManager.this.mCarPlayAppManager.unregisterCarPlayAppListener(CarPlayManager.this.mCarPlayAppListener);
                    CarPlayManager.this.mCarPlayAppManager.unregisterCarPlayStateListener(CarPlayManager.this.mCPStateListener);
                }
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
            a.b(CarPlayManager.TAG, "mCarPlayAppManager.onServiceDisconnected:" + iPlatformService);
        }
    };
    private final IServiceConnectionListener devicesServiceConnectListener = new IServiceConnectionListener() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.5
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            CarPlayManager.this.mDeviceServiceConnected = true;
            try {
                CarPlayManager.this.mCarPlayDeviceManager.registerCarPlayDeviceListListener(CarPlayManager.this.mCarPlayDeviceListListener);
                c.c().k(new m2.a(230008));
                a.c(CarPlayManager.TAG, "mCarPlayDeviceManager.onServiceConnected:" + iPlatformService);
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            CarPlayManager.this.mDeviceServiceConnected = false;
            try {
                if (CarPlayManager.this.mCarPlayDeviceManager != null) {
                    CarPlayManager.this.mCarPlayDeviceManager.unregisterCarPlayDeviceListListener(CarPlayManager.this.mCarPlayDeviceListListener);
                }
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
            a.c(CarPlayManager.TAG, "mCarPlayDeviceManager.onServiceDisconnected:" + iPlatformService);
        }
    };
    private final ICarPlayDeviceListListener mCarPlayDeviceListListener = new ICarPlayDeviceListListener() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.6
        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(CarPlayDevice carPlayDevice) {
            a.a(CarPlayManager.TAG, "onCarPlayDeviceStatusChanged:" + carPlayDevice);
            CarPlayManager.this.carPlayDeviceStatusChanged(carPlayDevice.getDeviceStatus(), carPlayDevice);
        }

        @Override // ts.car.service.carplay.ICarPlayDeviceListListener
        public void onProjectionRequested(CarPlayDevice carPlayDevice, int i8) {
            a.a(CarPlayManager.TAG, "onProjectionRequested:" + carPlayDevice);
            if (CarPlayManager.this.mCPStateListenerMap == null || CarPlayManager.this.mCPStateListenerMap.isEmpty()) {
                return;
            }
            for (CarPlayDeviceListListener carPlayDeviceListListener : CarPlayManager.this.mCPStateListenerMap.values()) {
                if (carPlayDeviceListListener != null) {
                    carPlayDeviceListListener.onProjectionRequested(carPlayDevice);
                }
            }
        }
    };

    public interface CarPlayDeviceListListener {
        default void onCarPlayDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
        }

        default void onCarPlayDisplayStatusChanged(boolean z7) {
        }

        default void onCarPlaySessionStatusChanged(boolean z7) {
        }

        default void onProjectionRequested(CarPlayDevice carPlayDevice) {
        }

        default void onSessionStateChanged(int i8) {
        }
    }

    private CarPlayManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void carPlayDeviceStatusChanged(final int i8, final CarPlayDevice carPlayDevice) {
        a.a(TAG, "--carPlayDeviceStatusChanged--getDeviceStatus:" + carPlayDevice.getDeviceStatus());
        c.c().k(new m2.a(112, new CarPlayParams(i8, carPlayDevice.getDeviceName(), carPlayDevice.getBtAddr())));
        ConcurrentHashMap<String, CarPlayDeviceListListener> concurrentHashMap = this.mCPStateListenerMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.8
            @Override // java.lang.Runnable
            public void run() {
                for (CarPlayDeviceListListener carPlayDeviceListListener : CarPlayManager.this.mCPStateListenerMap.values()) {
                    if (carPlayDeviceListListener != null) {
                        carPlayDeviceListListener.onCarPlayDeviceStatusChanged(i8, carPlayDevice);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void carPlayDisplayStatusChanged(final boolean z7, int i8) {
        ConcurrentHashMap<String, CarPlayDeviceListListener> concurrentHashMap = this.mCPStateListenerMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.7
            @Override // java.lang.Runnable
            public void run() {
                for (CarPlayDeviceListListener carPlayDeviceListListener : CarPlayManager.this.mCPStateListenerMap.values()) {
                    if (carPlayDeviceListListener != null) {
                        carPlayDeviceListListener.onCarPlayDisplayStatusChanged(z7);
                    }
                }
            }
        });
    }

    public static CarPlayManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (CarPlayManager.class) {
                if (mInstance == null) {
                    mInstance = new CarPlayManager(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCarPlaySessionStateChanged(final int i8) {
        if (i8 == 2) {
            b.o.a().x();
        }
        ConcurrentHashMap<String, CarPlayDeviceListListener> concurrentHashMap = this.mCPStateListenerMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.3
            @Override // java.lang.Runnable
            public void run() {
                for (CarPlayDeviceListListener carPlayDeviceListListener : CarPlayManager.this.mCPStateListenerMap.values()) {
                    if (carPlayDeviceListListener != null) {
                        carPlayDeviceListListener.onSessionStateChanged(i8);
                    }
                }
            }
        });
    }

    public void connectCarPlay(ConnectRequest connectRequest) {
        if (this.mCarPlayDeviceManager == null) {
            a.b(TAG, "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.mDeviceServiceConnected) {
            a.b(TAG, "mDevicesConnected not connected");
            return;
        }
        try {
            a.a(TAG, "connectCarPlay request:" + connectRequest.getDeviceId().hashCode());
            Intent intent = new Intent("carsettings_device_connect_carplay_action");
            intent.putExtra("deviceId", connectRequest.getDeviceId());
            intent.setPackage("com.byd.uereport");
            this.mContext.sendBroadcast(intent);
        } catch (Exception e8) {
            e8.printStackTrace();
            a.b(TAG, "connectCarPlay ,RemoteException:" + e8);
        }
    }

    public void deleteCarPlayDevice(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.b(TAG, "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.mDeviceServiceConnected) {
            a.b(TAG, "mDevicesConnected not connected");
            return;
        }
        try {
            iCarPlayDeviceListManager.deleteCarPlayDevice(str);
            a.a(TAG, "deleteCarPlayDevice devId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
            a.b(TAG, "deleteCarPlayDevice ,RemoteException:" + e8);
        }
    }

    public void disconnectCarPlay(String str) {
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.b(TAG, "mCarPlayDeviceManager == null");
            return;
        }
        if (!this.mDeviceServiceConnected) {
            a.b(TAG, "mDevicesConnected not connected");
            return;
        }
        try {
            iCarPlayDeviceListManager.disconnectCarPlay(str);
            a.a(TAG, "disconnectCarPlay deviceId:" + str);
        } catch (RemoteException e8) {
            e8.printStackTrace();
        }
    }

    public List<CarPlayDevice> getCarPlayDevices() {
        List<CarPlayDevice> arrayList = new ArrayList<>();
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager == null) {
            a.b(TAG, "mCarPlayDeviceManager == null");
            return arrayList;
        }
        if (!this.mDeviceServiceConnected) {
            a.b(TAG, "mDevicesConnected not connected");
            return arrayList;
        }
        try {
            arrayList = iCarPlayDeviceListManager.getCarPlayDeviceList();
        } catch (RemoteException | RuntimeException e8) {
            e8.printStackTrace();
            a.b(TAG, "getCarPlayDevices,RemoteException:" + e8);
        }
        a.a(TAG, "getCarPlayDevices:" + arrayList);
        return arrayList;
    }

    public boolean getCarPlayDisplayStatus() {
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            try {
                return iCarPlayAppManager.getCarPlayDisplayStatus();
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
        }
        return false;
    }

    public int getCarPlayStatus() {
        int carPlaySessionStatus;
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager == null) {
            a.b(TAG, "mCarPlayAppManager == null");
            return 0;
        }
        if (!this.mAppServiceConnected) {
            a.b(TAG, "mAppConnected not connected");
            return 0;
        }
        try {
            carPlaySessionStatus = iCarPlayAppManager.getCarPlaySessionStatus();
        } catch (RemoteException | RuntimeException e8) {
            e8.printStackTrace();
            a.b(TAG, "getCarPlayStatus,RemoteException:" + e8);
            carPlaySessionStatus = 0;
        }
        a.a(TAG, "getCarPlayStatus:" + carPlaySessionStatus);
        return carPlaySessionStatus;
    }

    public CarPlayDevice getConnectedCarPlayDevice() {
        for (CarPlayDevice carPlayDevice : getCarPlayDevices()) {
            if (carPlayDevice.getDeviceStatus() == 4) {
                return carPlayDevice;
            }
        }
        return null;
    }

    public CarPlayDevice getConnectingCarPlayDevice() {
        for (CarPlayDevice carPlayDevice : getCarPlayDevices()) {
            if (carPlayDevice.getDeviceStatus() == 3) {
                return carPlayDevice;
            }
        }
        return null;
    }

    public void init() {
        this.mCarPlayDeviceManager = (ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, this.mContext);
        this.mCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this.mContext);
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.registerConnectionListener(this.devicesServiceConnectListener);
            a.c(TAG, "mCarPlayDeviceManager != null");
        } else {
            a.b(TAG, "mCarPlayDeviceManager == null");
        }
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.appServiceConnectListener);
            a.c(TAG, "mCarPlayAppManager != null");
        } else {
            a.b(TAG, "mCarPlayAppManager == null");
        }
        if (this.mCPStateListenerMap == null) {
            this.mCPStateListenerMap = new ConcurrentHashMap<>();
        }
    }

    public boolean isCarPlayConnected() {
        return getCarPlayStatus() == 2;
    }

    public boolean isCarPlayConnecting() {
        return getCarPlayStatus() == 1;
    }

    public boolean isCarPlayDeviceConnected(String str) {
        CarPlayDevice connectedCarPlayDevice = getConnectedCarPlayDevice();
        if (connectedCarPlayDevice == null || str == null) {
            return false;
        }
        return str.equals(connectedCarPlayDevice.getBtAddr());
    }

    public void registerCarPlayDeviceListListener(String str, CarPlayDeviceListListener carPlayDeviceListListener) {
        ConcurrentHashMap<String, CarPlayDeviceListListener> concurrentHashMap = this.mCPStateListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, carPlayDeviceListListener);
    }

    public void setDeviceListListener(CarPlayDeviceListListener carPlayDeviceListListener) {
        this.deviceListListener = carPlayDeviceListListener;
    }

    public void startCarPlayUI() {
        if (w2.a.p().o() >= 1) {
            a.a(TAG, "存在蓝牙电话，不能跳转CarPlay投屏");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(CarPlayAAConstant.CAR_PLAY_PACKAGE_NAME, CarPlayAAConstant.CAR_PLAY_CLASS_NAME);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    public void unRegister() {
        a.a(TAG, "CarPlay unregisterConnectionListener");
        ICarPlayAppManager iCarPlayAppManager = this.mCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.unregisterConnectionListener(this.appServiceConnectListener);
            try {
                this.mCarPlayAppManager.unregisterCarPlayAppListener(this.mCarPlayAppListener);
                this.mCarPlayAppManager.unregisterCarPlayStateListener(this.mCPStateListener);
            } catch (RemoteException | RuntimeException e8) {
                e8.printStackTrace();
            }
        }
        ICarPlayDeviceListManager iCarPlayDeviceListManager = this.mCarPlayDeviceManager;
        if (iCarPlayDeviceListManager != null) {
            iCarPlayDeviceListManager.unregisterConnectionListener(this.devicesServiceConnectListener);
            try {
                this.mCarPlayDeviceManager.unregisterCarPlayDeviceListListener(this.mCarPlayDeviceListListener);
            } catch (RemoteException | RuntimeException e9) {
                e9.printStackTrace();
            }
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
        List<CarPlayDevice> carPlayDevices = getCarPlayDevices();
        if (carPlayDevices == null || carPlayDevices.size() == 0) {
            a.b(TAG, "carPlayDevices.size() == 0");
            return;
        }
        for (CarPlayDevice carPlayDevice : carPlayDevices) {
            if (address.equals(carPlayDevice.getBtAddr())) {
                if (carPlayDevice.getDeviceStatus() == 4) {
                    disconnectCarPlay(address);
                }
                g.a().d(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayManager.9
                    @Override // java.lang.Runnable
                    public void run() {
                        CarPlayManager.this.deleteCarPlayDevice(address);
                    }
                }, 200L);
            }
        }
    }

    public void unregisterCarPlayDeviceListListener(String str) {
        ConcurrentHashMap<String, CarPlayDeviceListListener> concurrentHashMap = this.mCPStateListenerMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }
}
