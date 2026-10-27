package com.byd.systemsettings.cpaa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiClient;
import android.text.TextUtils;
import c3.a;
import com.byd.bt.model.BondDevice;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.cpaa.callback.CpaaConnectStateCallback;
import com.byd.systemsettings.cpaa.model.CPAADevice;
import com.byd.systemsettings.cpaa.model.CarPlayType;
import com.byd.systemsettings.hotspot.HotspotManager;
import com.byd.systemsettings.service.ReceiverActionService;
import j7.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import m4.d;
import p2.g;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;
import ts.car.service.carplay.data.CarPlayDevice;
import x3.a;
import y2.b;

/* JADX INFO: loaded from: classes2.dex */
public class CpaaDeviceManager {
    private static final String TAG = "CpaaDeviceManager";
    private static final long TIMEOUT_CP_DICONNECT = 5000;
    private AndroidAutoManager aam;
    private b bbm;
    private CarPlayManager cpm;
    private volatile String curDisplayDeviceMac;
    private HotspotManager hm;
    private volatile int mAndroidAutoStatus;
    private CpaaConnectStateCallback mCallback;
    private volatile int mCarPlayStatus;
    private BluetoothDevice mUnpairDevice;
    private SharedPreferences spf;

    public class AndroidAutoStateCallback implements AndroidAutoManager.AndroidAutoDeviceListListener {
        private volatile AndroidAutoDevice curAA;
        private final WeakReference<CpaaDeviceManager> ref;

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
            WeakReference<CpaaDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().mAndroidAutoStatus = i8;
            this.curAA = androidAutoDevice;
            if (i8 == 1) {
                if (CpaaDeviceManager.this.mUnpairDevice != null && CpaaDeviceManager.this.mUnpairDevice.getAddress().equals(androidAutoDevice.getBtAddr())) {
                    a.a(CpaaDeviceManager.TAG, "AA连接状态断开后,再解绑设备");
                    CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.this;
                    cpaaDeviceManager.deleteAndroidAutoDevice(cpaaDeviceManager.mUnpairDevice.getAddress());
                    CpaaDeviceManager.this.bbm.L0(CpaaDeviceManager.this.mUnpairDevice);
                    CpaaDeviceManager.this.mUnpairDevice = null;
                    c.c().k(new m2.a(230010));
                }
                if (CpaaDeviceManager.this.mCallback != null) {
                    CpaaDeviceManager.this.mCallback.onConnectChanged(false);
                    CpaaDeviceManager.this.mCallback = null;
                }
            }
        }

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onSessionStatusChanged(int i8) {
            WeakReference<CpaaDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onAADisplayStatusChanged(4 == i8, this.curAA);
        }

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onVideoFocusStatusChanged(boolean z7) {
            WeakReference<CpaaDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onAADisplayStatusChanged(z7, this.curAA);
        }

        private AndroidAutoStateCallback(CpaaDeviceManager cpaaDeviceManager) {
            this.ref = new WeakReference<>(cpaaDeviceManager);
        }
    }

    public class CarPlayStateCallback implements CarPlayManager.CarPlayDeviceListListener {
        private volatile CarPlayDevice curCP;
        private final WeakReference<CpaaDeviceManager> ref;

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
            WeakReference<CpaaDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().mCarPlayStatus = i8;
            this.curCP = carPlayDevice;
            if (i8 == 1) {
                if (CpaaDeviceManager.this.mUnpairDevice != null && CpaaDeviceManager.this.mUnpairDevice.getAddress().equals(carPlayDevice.getBtAddr())) {
                    a.a(CpaaDeviceManager.TAG, "CP连接状态断开后,再解绑设备");
                    CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.this;
                    cpaaDeviceManager.deleteCarPlayDevice(cpaaDeviceManager.mUnpairDevice.getAddress());
                    CpaaDeviceManager.this.bbm.L0(CpaaDeviceManager.this.mUnpairDevice);
                    CpaaDeviceManager.this.mUnpairDevice = null;
                    c.c().k(new m2.a(230010));
                }
                if (CpaaDeviceManager.this.mCallback != null) {
                    CpaaDeviceManager.this.mCallback.onConnectChanged(false);
                    CpaaDeviceManager.this.mCallback = null;
                }
            }
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            WeakReference<CpaaDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onCPDisplayStatusChanged(z7, this.curCP);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onSessionStateChanged(int i8) {
            a.a(CpaaDeviceManager.TAG, "onSessionStateChanged", ", status: " + i8);
        }

        private CarPlayStateCallback(CpaaDeviceManager cpaaDeviceManager) {
            this.ref = new WeakReference<>(cpaaDeviceManager);
        }
    }

    public static class Singleton {
        private static final CpaaDeviceManager instance = new CpaaDeviceManager();

        public static CpaaDeviceManager get() {
            return instance;
        }
    }

    private AndroidAutoDevice androidAutoListContainDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && !TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            for (AndroidAutoDevice androidAutoDevice : getAndroidAutoDevices()) {
                if (bluetoothDevice.getAddress().equals(androidAutoDevice.getBtAddr())) {
                    return androidAutoDevice;
                }
            }
        }
        return null;
    }

    private CarPlayDevice carPlayListContainDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && !TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            for (CarPlayDevice carPlayDevice : getCarPlayDevices()) {
                if (bluetoothDevice.getAddress().equals(carPlayDevice.getBtAddr())) {
                    return carPlayDevice;
                }
            }
        }
        return null;
    }

    private boolean checkConnectedDevice(CPAADevice cPAADevice) {
        int deviceStatus;
        a.c(TAG, "---checkConnectedDevice---device: " + cPAADevice);
        List<AndroidAutoDevice> androidAutoDevices = getAndroidAutoDevices();
        HashMap map = new HashMap();
        for (AndroidAutoDevice androidAutoDevice : androidAutoDevices) {
            map.put(androidAutoDevice.getBtAddr(), androidAutoDevice.getBtAddr());
            if (cPAADevice.getType() == CarPlayType.CAR_PLAY && ((deviceStatus = androidAutoDevice.getDeviceStatus()) == 4 || deviceStatus == 1)) {
                androidAutoDevice.getBtAddr();
            }
        }
        String btMac = cPAADevice.getBtMac();
        boolean z7 = false;
        for (CPAADevice cPAADevice2 : getCPAADeviceList()) {
            if (btMac == null || !btMac.equals(cPAADevice2.getBtMac())) {
                if (cPAADevice2.isConnected()) {
                    a.c(TAG, "--checkConnectedDevice--disconnect. d: " + cPAADevice2);
                    if (CarPlayType.ANDROID_AUTO.equals(cPAADevice2.getType())) {
                        this.aam.disconnectAndroidAuto(cPAADevice2.getBtMac());
                        z7 = true;
                    } else if (CarPlayType.CAR_PLAY.equals(cPAADevice2.getType())) {
                        this.cpm.disconnectCarPlay(cPAADevice2.getBtMac());
                    }
                }
            }
        }
        return z7;
    }

    private String getName(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice == null ? "null" : d.f(bluetoothDevice.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAADisplayStatusChanged(boolean z7, AndroidAutoDevice androidAutoDevice) {
        a.c(TAG, "--onAADisplayStatusChanged--isShow: " + z7 + ", device: " + androidAutoDevice);
        if (androidAutoDevice == null) {
            return;
        }
        this.curDisplayDeviceMac = z7 ? androidAutoDevice.getBtAddr() : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCPDisplayStatusChanged(boolean z7, CarPlayDevice carPlayDevice) {
        a.c(TAG, "--onCPDisplayStatusChanged--isShow: " + z7 + ", device: " + carPlayDevice);
        if (carPlayDevice == null) {
            return;
        }
        this.curDisplayDeviceMac = z7 ? carPlayDevice.getBtAddr() : null;
    }

    public boolean checkCarPlaySupportOnBtConnected(BondDevice bondDevice) {
        if (!supportCarPlay(bondDevice.getDevice())) {
            return false;
        }
        requestConnectCarPlay(bondDevice.getMac());
        return true;
    }

    public void connectAndroidAuto(final CPAADevice cPAADevice) {
        if (this.aam == null) {
            return;
        }
        a.c(TAG, "---connectAndroidAuto---device: " + cPAADevice);
        if (checkConnectedDevice(cPAADevice)) {
            g.a().c(new Runnable() { // from class: com.byd.systemsettings.cpaa.CpaaDeviceManager.2
                @Override // java.lang.Runnable
                public void run() {
                    CpaaDeviceManager.this.requestConnectAndroidAuto(cPAADevice);
                }
            }, 1000L);
        } else {
            requestConnectAndroidAuto(cPAADevice);
        }
    }

    public void connectCarPlay(CPAADevice cPAADevice) {
        if (this.cpm == null) {
            return;
        }
        a.c(TAG, "---connectCarPlay---device: " + cPAADevice);
        final String btMac = cPAADevice.getBtMac();
        if (checkConnectedDevice(cPAADevice)) {
            g.a().c(new Runnable() { // from class: com.byd.systemsettings.cpaa.CpaaDeviceManager.1
                @Override // java.lang.Runnable
                public void run() {
                    CpaaDeviceManager.this.requestConnectCarPlay(btMac);
                }
            }, 1000L);
        } else {
            requestConnectCarPlay(btMac);
        }
    }

    public void deleteAndroidAutoDevice(String str) {
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager != null) {
            androidAutoManager.deleteAndroidAutoDevice(str);
        }
    }

    public void deleteCarPlayDevice(String str) {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager != null) {
            carPlayManager.deleteCarPlayDevice(str);
        }
    }

    public void disconnectAndroidAuto(String str) {
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager != null) {
            androidAutoManager.disconnectAndroidAuto(str);
        }
    }

    public void disconnectCarPlay(String str) {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager != null) {
            carPlayManager.disconnectCarPlay(str);
        }
    }

    public List<AndroidAutoDevice> getAndroidAutoDevices() {
        return this.aam.getAndroidAutoDevices();
    }

    public int getAndroidAutoStatus() {
        return this.aam.getAndroidAutoStatus();
    }

    public List<CPAADevice> getCPAADeviceList() {
        ArrayList arrayList = new ArrayList();
        Iterator<AndroidAutoDevice> it = this.aam.getAndroidAutoDevices().iterator();
        while (it.hasNext()) {
            CPAADevice cPAADevice = new CPAADevice(it.next());
            cPAADevice.setHasBtSignal(getDeviceBtSignal(cPAADevice));
            cPAADevice.setHasWiFiSignal(getDeviceWifiSignal(cPAADevice));
            cPAADevice.setHasPhoneSignal(getDevicePhoneSignal(cPAADevice));
            String btMac = cPAADevice.getBtMac();
            if (TextUtils.isEmpty(btMac)) {
                btMac = cPAADevice.getUsbSN();
            }
            if (!TextUtils.isEmpty(btMac)) {
                cPAADevice.setLastConnectedTime(this.spf.getLong(btMac.hashCode() + "", 0L));
            }
            arrayList.add(cPAADevice);
        }
        Iterator<CarPlayDevice> it2 = this.cpm.getCarPlayDevices().iterator();
        while (it2.hasNext()) {
            CPAADevice cPAADevice2 = new CPAADevice(it2.next());
            cPAADevice2.setHasBtSignal(getDeviceBtSignal(cPAADevice2));
            cPAADevice2.setHasWiFiSignal(getDeviceWifiSignal(cPAADevice2));
            cPAADevice2.setHasPhoneSignal(getDevicePhoneSignal(cPAADevice2));
            String btMac2 = cPAADevice2.getBtMac();
            if (TextUtils.isEmpty(btMac2)) {
                btMac2 = cPAADevice2.getUsbSN();
            }
            if (!TextUtils.isEmpty(btMac2)) {
                cPAADevice2.setLastConnectedTime(this.spf.getLong(btMac2.hashCode() + "", 0L));
            }
            arrayList.add(cPAADevice2);
        }
        return arrayList;
    }

    public List<CarPlayDevice> getCarPlayDevices() {
        return this.cpm.getCarPlayDevices();
    }

    public int getCarPlayStatus() {
        return this.cpm.getCarPlayStatus();
    }

    public AndroidAutoDevice getConnectedAndroidAutoDevice() {
        return this.aam.getConnectedAndroidAutoDevice();
    }

    public CarPlayDevice getConnectedCarPlayDevice() {
        return this.cpm.getConnectedCarPlayDevice();
    }

    public AndroidAutoDevice getConnectingAndroidAutoDevice() {
        return this.aam.getConnectingAndroidAutoDevice();
    }

    public CarPlayDevice getConnectingCarPlayDevice() {
        return this.cpm.getConnectingCarPlayDevice();
    }

    public String getCurDisplayDeviceMac() {
        return this.curDisplayDeviceMac;
    }

    public boolean getDeviceBtSignal(CPAADevice cPAADevice) {
        if (!this.bbm.W()) {
            a.a(TAG, "bt no enable-->no show bt");
            return false;
        }
        List<BluetoothDevice> listG = this.bbm.G();
        if (listG == null || listG.size() == 0) {
            a.a(TAG, "list == null -->no show bt");
            return false;
        }
        Iterator<BluetoothDevice> it = listG.iterator();
        while (it.hasNext()) {
            String address = it.next().getAddress();
            a.a(TAG, "address：" + address + ",device.getBtMac：" + cPAADevice.getBtMac());
            if (address != null && address.equals(cPAADevice.getBtMac())) {
                a.a(TAG, "bt adr equals-->show bt");
                return true;
            }
        }
        a.a(TAG, "bt no adr equals-->no show bt");
        return false;
    }

    public boolean getDevicePhoneSignal(CPAADevice cPAADevice) {
        a.a(TAG, "bt phone signal get");
        if (getDeviceBtSignal(cPAADevice)) {
            return a.c.a().o(this.bbm.C(cPAADevice.getBtMac()));
        }
        return false;
    }

    public boolean getDeviceWifiSignal(CPAADevice cPAADevice) {
        if (!this.hm.isHotspotEnable()) {
            x3.a.a(TAG, "Hotspot no enable-->no show wifi");
            return false;
        }
        if (this.hm.getConnectedCount() <= 0) {
            x3.a.a(TAG, "no connected hotspot count-->no show wifi");
            return false;
        }
        List<WifiClient> apClients = this.hm.getApClients();
        if (apClients == null || apClients.size() == 0) {
            x3.a.a(TAG, "apWifiClients == null -->no show wifi");
            return false;
        }
        for (WifiClient wifiClient : apClients) {
            if (wifiClient != null && wifiClient.getMacAddress() != null) {
                String string = wifiClient.getMacAddress().toString();
                x3.a.a(TAG, "curWifiAdr：" + string + ",device.getWifiMac：" + cPAADevice.getWifiMac());
                if (string != null && string.equals(cPAADevice.getWifiMac())) {
                    x3.a.a(TAG, "Hotspot wifi adr equals-->show wifi");
                    return true;
                }
            }
        }
        x3.a.a(TAG, "Hotspot no adr equals-->no show wifi");
        return false;
    }

    public void init(Context context) {
        x3.a.a(TAG, "init_start");
        CarPlayManager carPlayManager = CarPlayManager.getInstance(context);
        this.cpm = carPlayManager;
        carPlayManager.init();
        this.cpm.registerCarPlayDeviceListListener(getClass().getName(), new CarPlayStateCallback(this));
        x3.a.a(TAG, "init_cp_ok");
        AndroidAutoManager androidAutoManager = AndroidAutoManager.getInstance(context);
        this.aam = androidAutoManager;
        androidAutoManager.init();
        this.aam.registerAndroidAutoDeviceListListener(getClass().getName(), new AndroidAutoStateCallback(this));
        x3.a.a(TAG, "init_aa_ok");
        b bVarA = b.o.a();
        this.bbm = bVarA;
        bVarA.Q(context);
        x3.a.a(TAG, "init_bt_ok");
        a3.a.f("pairing_request_service", ReceiverActionService.class.getName());
        HotspotManager hotspotManager = HotspotManager.Singleton.get();
        this.hm = hotspotManager;
        hotspotManager.init(context);
        x3.a.a(TAG, "init_hotspot_ok");
        this.spf = context.getSharedPreferences("key_cpaa_connected_time", 0);
        o2.c.b().d(new DxDataService(context));
        x3.a.a(TAG, "init_end");
    }

    public boolean isAndroidAutoConnected() {
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager == null) {
            return false;
        }
        return androidAutoManager.isAndroidAutoConnected();
    }

    public boolean isAndroidAutoConnecting() {
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager == null) {
            return false;
        }
        return androidAutoManager.isAndroidAutoConnecting();
    }

    public boolean isAndroidAutoDeviceConnected(String str) {
        AndroidAutoDevice connectedAndroidAutoDevice = getConnectedAndroidAutoDevice();
        if (connectedAndroidAutoDevice == null || str == null) {
            return false;
        }
        return str.equals(connectedAndroidAutoDevice.getBtAddr());
    }

    public boolean isCarPlayConnected() {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager == null) {
            return false;
        }
        return carPlayManager.isCarPlayConnected();
    }

    public boolean isCarPlayConnecting() {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager == null) {
            return false;
        }
        return carPlayManager.isCarPlayConnecting();
    }

    public boolean isCarPlayDeviceConnected(String str) {
        CarPlayDevice connectedCarPlayDevice = getConnectedCarPlayDevice();
        if (connectedCarPlayDevice == null || str == null) {
            return false;
        }
        return str.equals(connectedCarPlayDevice.getBtAddr());
    }

    public boolean isProfileConnected(int i8) {
        int profileConnectionState = BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(i8);
        x3.a.a(TAG, "ProfileConnectionState：" + profileConnectionState);
        return profileConnectionState == 2;
    }

    public void registerAndroidAutoDeviceListListener(String str, AndroidAutoManager.AndroidAutoDeviceListListener androidAutoDeviceListListener) {
        this.aam.registerAndroidAutoDeviceListListener(str, androidAutoDeviceListListener);
    }

    public void registerBtBondSateCallback(String str, b3.d dVar) {
        b bVar = this.bbm;
        if (bVar != null) {
            bVar.s0(str, dVar);
        }
    }

    public void registerCarPlayDeviceListListener(String str, CarPlayManager.CarPlayDeviceListListener carPlayDeviceListListener) {
        this.cpm.registerCarPlayDeviceListListener(str, carPlayDeviceListListener);
    }

    public void release(Context context) {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager != null) {
            carPlayManager.unregisterCarPlayDeviceListListener(getClass().getName());
            this.cpm.unRegister();
        }
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager != null) {
            androidAutoManager.unregisterAndroidAutoDeviceListListener(getClass().getName());
            this.aam.unRegister();
        }
        b bVar = this.bbm;
        if (bVar != null) {
            bVar.C0();
        }
        HotspotManager hotspotManager = this.hm;
        if (hotspotManager != null) {
            hotspotManager.release();
        }
    }

    public void requestConnectAndroidAuto(CPAADevice cPAADevice) {
        ConnectRequest connectRequest = new ConnectRequest();
        String btMac = cPAADevice.getBtMac();
        if (TextUtils.isEmpty(btMac)) {
            btMac = cPAADevice.getUsbSN();
        }
        connectRequest.setDeviceId(btMac);
        connectRequest.setConnectType(0);
        connectRequest.setUserRequest(true);
        connectRequest.setForceProjection(true);
        this.aam.connectAndroidAuto(connectRequest);
    }

    public void requestConnectCarPlay(String str) {
        ts.car.service.carplay.data.ConnectRequest connectRequest = new ts.car.service.carplay.data.ConnectRequest();
        connectRequest.setDeviceId(str);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        this.cpm.connectCarPlay(connectRequest);
    }

    public void startAndroidAutoUI() {
        AndroidAutoManager androidAutoManager = this.aam;
        if (androidAutoManager != null) {
            androidAutoManager.startAndroidAutoUI();
        }
    }

    public void startCarPlayUI() {
        CarPlayManager carPlayManager = this.cpm;
        if (carPlayManager != null) {
            carPlayManager.startCarPlayUI();
        }
    }

    public boolean supportCarPlay(BluetoothDevice bluetoothDevice) {
        List<CarPlayDevice> carPlayDevices;
        if (bluetoothDevice != null && (carPlayDevices = this.cpm.getCarPlayDevices()) != null && !carPlayDevices.isEmpty()) {
            Iterator<CarPlayDevice> it = carPlayDevices.iterator();
            while (it.hasNext()) {
                if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void unpairCarPlayDevice(BluetoothDevice bluetoothDevice) {
        this.cpm.unpairDevice(bluetoothDevice);
    }

    public void unpairDevice(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG, "---unpairDevice---device: " + getName(bluetoothDevice));
        CarPlayDevice carPlayDeviceCarPlayListContainDevice = carPlayListContainDevice(bluetoothDevice);
        AndroidAutoDevice androidAutoDeviceAndroidAutoListContainDevice = androidAutoListContainDevice(bluetoothDevice);
        if (carPlayDeviceCarPlayListContainDevice != null) {
            if (carPlayDeviceCarPlayListContainDevice.getDeviceStatus() == 4 || carPlayDeviceCarPlayListContainDevice.getDeviceStatus() == 3) {
                this.mUnpairDevice = bluetoothDevice;
                disconnectCarPlay(carPlayDeviceCarPlayListContainDevice.getBtAddr());
                return;
            } else {
                deleteCarPlayDevice(carPlayDeviceCarPlayListContainDevice.getBtAddr());
                this.bbm.L0(bluetoothDevice);
                c.c().k(new m2.a(230010));
                return;
            }
        }
        if (androidAutoDeviceAndroidAutoListContainDevice == null) {
            this.bbm.L0(bluetoothDevice);
            return;
        }
        if (androidAutoDeviceAndroidAutoListContainDevice.getDeviceStatus() == 4 || androidAutoDeviceAndroidAutoListContainDevice.getDeviceStatus() == 3) {
            this.mUnpairDevice = bluetoothDevice;
            disconnectAndroidAuto(androidAutoDeviceAndroidAutoListContainDevice.getBtAddr());
        } else {
            deleteAndroidAutoDevice(androidAutoDeviceAndroidAutoListContainDevice.getBtAddr());
            this.bbm.L0(bluetoothDevice);
            c.c().k(new m2.a(230010));
        }
    }

    public void unregisterAndroidAutoDeviceListListener(String str) {
        this.aam.unregisterAndroidAutoDeviceListListener(str);
    }

    public void unregisterBtBondSateCallback(String str) {
        b bVar = this.bbm;
        if (bVar != null) {
            bVar.M0(str);
        }
    }

    public void unregisterCarPlayDeviceListListener(String str) {
        this.cpm.unregisterCarPlayDeviceListListener(str);
    }

    private CpaaDeviceManager() {
    }

    public void disconnectAndroidAuto(String str, CpaaConnectStateCallback cpaaConnectStateCallback) {
        if (str == null) {
            return;
        }
        this.mCallback = cpaaConnectStateCallback;
        disconnectAndroidAuto(str);
    }

    public void disconnectCarPlay(String str, CpaaConnectStateCallback cpaaConnectStateCallback) {
        if (str == null) {
            return;
        }
        this.mCallback = cpaaConnectStateCallback;
        disconnectCarPlay(str);
    }

    public boolean isCarPlayConnected(BondDevice bondDevice) {
        CarPlayDevice connectedCarPlayDevice;
        return (bondDevice == null || bondDevice.getMac() == null || !supportCarPlay(bondDevice.getDevice()) || (connectedCarPlayDevice = getConnectedCarPlayDevice()) == null || !bondDevice.getMac().equals(connectedCarPlayDevice.getBtAddr())) ? false : true;
    }
}
