package com.byd.systemsettings.btsetting;

import android.bluetooth.BluetoothDevice;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import b3.c;
import b3.e;
import b3.g;
import b3.h;
import b3.i;
import b3.j;
import com.byd.base.thread.Priority;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.PhoneType;
import com.byd.systemsettings.btsetting.model.BondFailedParams;
import com.byd.systemsettings.btsetting.model.BtBatteryParams;
import com.byd.systemsettings.btsetting.model.ProfileParams;
import com.byd.systemsettings.btsetting.model.RequestPairParams;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.cpaa.CpaaDeviceManager;
import com.byd.systemsettings.cpaa.callback.CpaaConnectStateCallback;
import com.byd.systemsettings.cpaa.model.CPAADevice;
import com.byd.systemsettings.cpaa.model.CarPlayType;
import com.byd.systemsettings.hotspot.HotspotManager;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import m4.d;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import x3.a;
import y2.b;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtSettingsPresenter extends f<BtSettingsPager> {
    public static final int ARG1_AA_NEW_DEVICE_PAIR = 116;
    public static final int ARG1_AA_PROJECTION_REQUESTED = 114;
    public static final int ARG1_BT_BATTERY_CHANGED = 96;
    public static final int ARG1_BT_NAME_CHANGED = 19;
    public static final int ARG1_BT_PAIR_CODE = 32;
    public static final int ARG1_BT_SATE = 1;
    public static final int ARG1_CP_DISPLAY_CHANGED = 113;
    public static final int ARG1_CP_PROJECTION_REQUESTED = 115;
    public static final int ARG1_CP_STATE_CHANGED = 112;
    public static final int ARG1_DISCOVER_FINISHED = 18;
    public static final int ARG1_DISCOVER_FOUND = 16;
    public static final int ARG1_DISCOVER_NOT_FOUND = 17;
    public static final int ARG1_PROFILE_SATE = 80;
    public static final int ARG1_PROFILE_SERVICE_SATE = 81;
    public static final int ARG1_SATE_BONDED = 48;
    public static final int ARG1_SATE_BONDING = 49;
    public static final int ARG1_SATE_BOND_FAILED = 51;
    public static final int ARG1_SATE_CONNECTED = 64;
    public static final int ARG1_SATE_CONNECTING = 67;
    public static final int ARG1_SATE_CONNECT_FAILED = 66;
    public static final int ARG1_SATE_DISCONNECTED = 65;
    public static final int ARG1_SATE_OOB_BONDED = 52;
    public static final int ARG1_SATE_UNBOND = 50;
    private static final String CLASS = "com.byd.systemsettings.btsetting.BtSettingsPresenter";
    private static final Class<?> CLAZZ = BtSettingsPresenter.class;
    private static final String TAG = "BtSettingsPresenter";
    public static final long TIMEOUT_AUTO_CONNECT = 20000;
    private int handlerWhat = 0;
    private volatile boolean autoConnectCheckStop = false;
    private final b bbm = b.o.a();
    private final CpaaDeviceManager cdm = CpaaDeviceManager.Singleton.get();
    private final HotspotManager hm = HotspotManager.Singleton.get();

    /* JADX INFO: renamed from: com.byd.systemsettings.btsetting.BtSettingsPresenter$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$ConnectState;

        static {
            int[] iArr = new int[ConnectState.values().length];
            $SwitchMap$com$byd$bt$model$ConnectState = iArr;
            try {
                iArr[ConnectState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class AndroidAutoPairCallback extends PresenterCallback implements b3.b {
        public AndroidAutoPairCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.b
        public void onNewDevicePair(int i8) {
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(116, Integer.valueOf(i8));
            a.a(BtSettingsPresenter.TAG, "onNewDevicePair", ", op: " + i8);
        }
    }

    public class AndroidAutoStateCallback extends PresenterCallback implements AndroidAutoManager.AndroidAutoDeviceListListener {
        public AndroidAutoStateCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onProjectionRequested(AndroidAutoDevice androidAutoDevice) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onProjectionRequested", ", device: " + androidAutoDevice);
            this.ref.get().sendMessage(114, androidAutoDevice);
        }
    }

    public class BtBatteryChangedCallback extends PresenterCallback implements c {
        public BtBatteryChangedCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.c
        public void onBatteryChanged(BluetoothDevice bluetoothDevice, int i8) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onBatteryChanged", ", level: " + i8 + ", device: " + d.c(bluetoothDevice));
            this.ref.get().sendMessage(96, new BtBatteryParams(bluetoothDevice, this.ref.get().bbm.Z(i8)));
        }
    }

    public class BtBondStateChangeListener extends PresenterCallback implements b3.d {
        public BtBondStateChangeListener(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.d
        public void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(51, new BondFailedParams(bluetoothDevice, i8));
        }

        @Override // b3.d
        public void onBtBondNone(BluetoothDevice bluetoothDevice) {
            a.a(BtSettingsPresenter.TAG, "---onBtBondNone---device: " + d.c(bluetoothDevice));
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(50, bluetoothDevice);
        }

        @Override // b3.d
        public void onBtBonded(BluetoothDevice bluetoothDevice) {
            a.a(BtSettingsPresenter.TAG, "onBtBonded", ", device = " + d.c(bluetoothDevice));
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(48, bluetoothDevice);
        }

        @Override // b3.d
        public void onBtBonding(BluetoothDevice bluetoothDevice) {
            a.a(BtSettingsPresenter.TAG, "onBtBonding", ", device = " + d.c(bluetoothDevice));
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(49, bluetoothDevice);
        }

        @Override // b3.d
        public void onOobBonded(String str, String str2) {
            a.a(BtSettingsPresenter.TAG, "onOobBonded", ", from: " + str + ", mac:" + d.d(str2));
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(52, Pair.create(str, str2));
        }
    }

    public class BtConnectSateCallback extends PresenterCallback implements e {
        public BtConnectSateCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.e
        public void onConnectFailed(BluetoothDevice bluetoothDevice) {
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(66, bluetoothDevice);
        }

        @Override // b3.e
        public void onConnectStateChanged(BluetoothDevice bluetoothDevice, ConnectState connectState) {
            a.a(BtSettingsPresenter.TAG, "onConnectStateChanged", ", device: " + d.c(bluetoothDevice) + ", state: " + connectState);
            if (isVInvalid()) {
                return;
            }
            if (connectState == ConnectState.CONNECTED) {
                this.ref.get().sendMessage(64, bluetoothDevice);
            } else if (connectState == ConnectState.DISCONNECTED) {
                this.ref.get().sendMessage(65, bluetoothDevice);
            } else if (connectState == ConnectState.CONNECTING) {
                this.ref.get().sendMessage(67, bluetoothDevice);
            }
        }

        @Override // b3.e
        public void onConnectedBtNameChanged(BTDevice bTDevice) {
            a.a(BtSettingsPresenter.TAG, "onConnectedBtNameChanged", ", device: " + bTDevice);
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(19, bTDevice);
        }
    }

    public class BtDiscoverListener extends PresenterCallback implements g {
        public BtDiscoverListener(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.g
        public void onDeviceFound(BTDevice bTDevice) {
            a.a(BtSettingsPresenter.TAG, "onDeviceFound", ", device:" + bTDevice);
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(16, bTDevice);
        }

        @Override // b3.g
        public void onDiscoveryFinished() {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onDiscoveryFinished", "---onDiscoveryFinished---");
            this.ref.get().autoConnectCheckStop = true;
            this.ref.get().sendMessage(18, null);
        }

        @Override // b3.g
        public void onTargetNotFound(BluetoothDevice bluetoothDevice) {
            a.a(BtSettingsPresenter.TAG, "onTargetNotFound", ", device: " + d.c(bluetoothDevice));
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(17, bluetoothDevice);
        }
    }

    public class BtPairRequestCallback extends PresenterCallback implements h {
        public BtPairRequestCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.h
        public void onReceivedPairRequest(BluetoothDevice bluetoothDevice, boolean z7, int i8) {
            a.a(BtSettingsPresenter.TAG, "onReceivedPairRequest", ", device:" + d.c(bluetoothDevice) + ", needToInput: " + z7);
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(32, new RequestPairParams(bluetoothDevice, z7, i8));
        }
    }

    public class BtProfileConnectSateCallback extends PresenterCallback implements i {
        public BtProfileConnectSateCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.i
        public void onConnectedChanged(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onConnectedChanged", ", profile: " + i8 + ", device: " + d.c(bluetoothDevice) + ", state: " + connectState);
            this.ref.get().sendMessage(80, new ProfileParams(i8, bluetoothDevice, connectState));
        }

        @Override // b3.i
        public void onServiceConnected(int i8) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onServiceConnected", ", profile: " + i8);
            this.ref.get().sendMessage(81, Integer.valueOf(i8));
        }

        @Override // b3.i
        public /* bridge */ /* synthetic */ void onServiceDisconnected(int i8) {
            super.onServiceDisconnected(i8);
        }
    }

    public class BtSateChangedCallback extends PresenterCallback implements j {
        public BtSateChangedCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // b3.j
        public void onBtOff() {
            a.a(BtSettingsPresenter.TAG, "onBtOff", "---onBtOff---");
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(1, Boolean.FALSE);
        }

        @Override // b3.j
        public void onBtOn() {
            a.a(BtSettingsPresenter.TAG, "onBtOn", "---onBtOn---");
            if (isVInvalid()) {
                return;
            }
            this.ref.get().sendMessage(1, Boolean.TRUE);
        }

        @Override // b3.j
        public void onBtTurningOff() {
        }

        @Override // b3.j
        public void onBtTurningOn() {
        }
    }

    public class CarPlaySateCallback extends PresenterCallback implements CarPlayManager.CarPlayDeviceListListener {
        public CarPlaySateCallback(BtSettingsPresenter btSettingsPresenter) {
            super(btSettingsPresenter);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
            if (isVInvalid()) {
            }
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onCarPlayDisplayStatusChanged", ", isShow: " + z7);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onProjectionRequested(CarPlayDevice carPlayDevice) {
            if (isVInvalid()) {
                return;
            }
            a.a(BtSettingsPresenter.TAG, "onProjectionRequested", ", device: " + carPlayDevice);
            this.ref.get().sendMessage(115, carPlayDevice);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onSessionStateChanged(int i8) {
            a.a(BtSettingsPresenter.TAG, "onSessionStateChanged", ", status: " + i8);
        }
    }

    public class PresenterCallback {
        public WeakReference<BtSettingsPresenter> ref;

        public PresenterCallback(BtSettingsPresenter btSettingsPresenter) {
            this.ref = new WeakReference<>(btSettingsPresenter);
        }

        public boolean isVInvalid() {
            WeakReference<BtSettingsPresenter> weakReference = this.ref;
            return weakReference == null || weakReference.get() == null || this.ref.get().getV() == null;
        }
    }

    private CarPlayDevice carPlayListContainDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && !TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            for (CarPlayDevice carPlayDevice : this.cdm.getCarPlayDevices()) {
                if (bluetoothDevice.getAddress().equals(carPlayDevice.getBtAddr())) {
                    return carPlayDevice;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i8, Object obj) {
        if (getV() != null) {
            getV().sendMessage(this.handlerWhat, i8, obj);
        }
    }

    public void cancelPairDevice(BluetoothDevice bluetoothDevice) {
        a.a(TAG, "---cancelPairDevice---result: " + this.bbm.s(bluetoothDevice) + ", device: " + d.c(bluetoothDevice));
    }

    public boolean checkCarPlaySupportOnBtConnected(BondDevice bondDevice) {
        return this.cdm.checkCarPlaySupportOnBtConnected(bondDevice);
    }

    public void connectAndroidAuto(BondDevice bondDevice) {
        BtSingleConnection.enableAndroidAutoHfp(bondDevice.getDevice(), true);
        CPAADevice cPAADevice = new CPAADevice(bondDevice.getName());
        cPAADevice.setType(CarPlayType.ANDROID_AUTO);
        cPAADevice.setBtMac(bondDevice.getMac());
        this.cdm.connectAndroidAuto(cPAADevice);
    }

    public void connectCarPlay(BondDevice bondDevice) {
        CPAADevice cPAADevice = new CPAADevice(bondDevice.getName());
        cPAADevice.setType(CarPlayType.CAR_PLAY);
        cPAADevice.setBtMac(bondDevice.getMac());
        this.cdm.connectCarPlay(cPAADevice);
    }

    public boolean connectDevice(BluetoothDevice bluetoothDevice) {
        a.a(TAG, "---connectDevice---device: " + d.c(bluetoothDevice));
        return this.bbm.t(bluetoothDevice);
    }

    public void disconnect() {
        a.a(TAG, "---disconnect---");
        this.bbm.v();
    }

    public void disconnectAndroidAuto(String str, CpaaConnectStateCallback cpaaConnectStateCallback) {
        this.cdm.disconnectAndroidAuto(str, cpaaConnectStateCallback);
    }

    public void disconnectCarPlay(String str, CpaaConnectStateCallback cpaaConnectStateCallback) {
        this.cdm.disconnectCarPlay(str, cpaaConnectStateCallback);
    }

    public void enable(boolean z7) {
        a.a(TAG, "---enable---enable = " + z7);
        this.bbm.A(z7);
    }

    public void forceDisconnect(BluetoothDevice bluetoothDevice) {
        a.a(TAG, "---disconnect---device: " + d.c(bluetoothDevice));
        this.bbm.w(bluetoothDevice, bluetoothDevice != null ? isAndroidAutoDeviceConnected(bluetoothDevice.getAddress()) : false, true);
    }

    public Map<String, String> getAndroidAutoMap() {
        return BtSingleConnection.getAndroidAutoDeviceMacMap();
    }

    public List<BondDevice> getBondedDevices() {
        List<BondDevice> listD = this.bbm.D();
        if (listD != null && !listD.isEmpty()) {
            Map<String, String> androidAutoDeviceMacMap = BtSingleConnection.getAndroidAutoDeviceMacMap();
            Map<String, String> carPlayDeviceMacMap = BtSingleConnection.getCarPlayDeviceMacMap();
            String str = carPlayDeviceMacMap.get(BtSingleConnection.CP_CONNECTED_MAC);
            if (!listD.isEmpty()) {
                for (BondDevice bondDevice : listD) {
                    boolean zIsConnected = isConnected(bondDevice.getDevice());
                    bondDevice.setHfpConnected(isHfpConnected(bondDevice.getDevice()));
                    boolean z7 = false;
                    bondDevice.setConnectState(zIsConnected ? 2 : 0);
                    if (carPlayDeviceMacMap.get(bondDevice.getMac()) != null) {
                        bondDevice.setPhoneType(PhoneType.CAR_PLAY);
                        if (str != null && str.equals(bondDevice.getMac())) {
                            z7 = true;
                        }
                        bondDevice.setCpConnected(z7);
                    } else if (androidAutoDeviceMacMap.get(bondDevice.getMac()) != null) {
                        bondDevice.setPhoneType(PhoneType.ANDROID_AUTO);
                        bondDevice.setAaConnected(this.cdm.isAndroidAutoDeviceConnected(bondDevice.getMac()));
                    } else {
                        bondDevice.setPhoneType(PhoneType.NORMAL);
                    }
                }
            }
        }
        return listD;
    }

    public String getBtName() {
        return this.bbm.E();
    }

    public Map<String, String> getCarPlayMap() {
        return BtSingleConnection.getCarPlayDeviceMacMap();
    }

    public int getConnectStateInt(ConnectState connectState) {
        int i8 = AnonymousClass2.$SwitchMap$com$byd$bt$model$ConnectState[connectState.ordinal()];
        int i9 = 1;
        if (i8 != 1) {
            i9 = 2;
            if (i8 != 2) {
                return 0;
            }
        }
        return i9;
    }

    public AndroidAutoDevice getConnectedAndroidAutoDevice() {
        return this.cdm.getConnectedAndroidAutoDevice();
    }

    public CarPlayDevice getConnectedCarPlayDevice() {
        return this.cdm.getConnectedCarPlayDevice();
    }

    public BluetoothDevice getConnectedDevice() {
        return this.bbm.F();
    }

    public int getConnectedDevicesCount() {
        return this.bbm.J();
    }

    public String getConnectedName() {
        return this.bbm.H();
    }

    public int getConnectedProfileCount(BluetoothDevice bluetoothDevice) {
        return this.bbm.K(bluetoothDevice);
    }

    public long getDevicePreconnectedTime(String str) {
        return this.bbm.L(str);
    }

    public void init(int i8) {
        a.a(TAG, "---init---");
        this.handlerWhat = i8;
        this.bbm.Q(getV().getContext());
        b bVar = this.bbm;
        String str = CLASS;
        bVar.x0(str, new BtSateChangedCallback(this));
        this.bbm.u0(str, new BtDiscoverListener(this));
        this.bbm.v0(str, new BtPairRequestCallback(this));
        this.bbm.s0(str, new BtBondStateChangeListener(this));
        this.bbm.t0(str, new BtConnectSateCallback(this));
        this.bbm.z0(str, new BtProfileConnectSateCallback(this));
        this.bbm.w0(str, new BtBatteryChangedCallback(this));
        this.bbm.y0(str, new AndroidAutoPairCallback(this));
        this.cdm.registerCarPlayDeviceListListener(str, new CarPlaySateCallback(this));
        this.cdm.registerAndroidAutoDeviceListListener(str, new AndroidAutoStateCallback(this));
    }

    public boolean isAndroidAutoConnected() {
        return this.cdm.isAndroidAutoConnected();
    }

    public boolean isAndroidAutoDeviceConnected(String str) {
        return this.cdm.isAndroidAutoDeviceConnected(str);
    }

    public boolean isCarPlayConnected() {
        return this.cdm.isCarPlayConnected();
    }

    public boolean isConnected() {
        return this.bbm.U();
    }

    public boolean isCurApBand24GHz() {
        return this.hm.getHotspotApBand() == 1;
    }

    public boolean isEnabled() {
        return this.bbm.W();
    }

    public boolean isHfpConnected(BluetoothDevice bluetoothDevice) {
        return this.bbm.X(bluetoothDevice);
    }

    public void onBtStateChanged(boolean z7) {
        a.a(TAG, "onBtStateChanged", ", isOn: " + z7);
        if (getV() != null) {
            getV().onBtStateChanged(z7);
        }
        if (!z7) {
            this.autoConnectCheckStop = true;
            stopScan();
        } else {
            startScan();
            this.autoConnectCheckStop = false;
            a.c(TAG, "onBtStateChanged", ", checking auto connect operation.");
            p2.g.a().e(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPresenter.1
                @Override // java.lang.Runnable
                public void run() {
                    a.c(BtSettingsPresenter.TAG, "onBtStateChanged", ", auto connect operation. checking START.");
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    List<BluetoothDevice> listG = BtSettingsPresenter.this.bbm.G();
                    while (!BtSettingsPresenter.this.autoConnectCheckStop && ((listG == null || listG.isEmpty()) && System.currentTimeMillis() - jCurrentTimeMillis < BtSettingsPresenter.TIMEOUT_AUTO_CONNECT)) {
                        a.c(BtSettingsPresenter.TAG, "onBtStateChanged", ", auto connect operation. checking...");
                        SystemClock.sleep(200L);
                        listG = BtSettingsPresenter.this.bbm.G();
                    }
                    if (listG != null) {
                        a.c(BtSettingsPresenter.TAG, "onBtStateChanged", ", auto connect operation. list.size: " + listG.size());
                    }
                    a.c(BtSettingsPresenter.TAG, "onBtStateChanged", ", auto connect operation. checking END.");
                    if (BtSettingsPresenter.this.bbm != null) {
                        a.c(BtSettingsPresenter.TAG, "onBtStateChanged", ", do stopScan.");
                        BtSettingsPresenter.this.bbm.K0();
                    }
                    BtSettingsPresenter.this.autoConnectCheckStop = true;
                }
            }, Priority.HIGH);
        }
    }

    public void pairDevice(BluetoothDevice bluetoothDevice) {
        a.a(TAG, "---pairDevice---device: " + d.c(bluetoothDevice));
        this.bbm.q0(bluetoothDevice);
    }

    public void setBleName(String str) {
        this.bbm.G0(str);
    }

    public void startScan() {
        a.a(TAG, "---startScan---");
        this.bbm.J0();
        if (getV() != null) {
            getV().startRefreshAnimation();
        }
    }

    public void stopScan() {
        a.a(TAG, "---stopScan---");
        this.bbm.K0();
        if (getV() != null) {
            getV().onScanFinished();
        }
    }

    @Override // z3.f
    public void unbindV() {
        super.unbindV();
        a.a(TAG, "---unbindV---");
        this.autoConnectCheckStop = true;
        b bVar = this.bbm;
        if (bVar != null) {
            bVar.K0();
            b bVar2 = this.bbm;
            String str = CLASS;
            bVar2.R0(str);
            this.bbm.O0(str);
            this.bbm.P0(str);
            this.bbm.M0(str);
            this.bbm.N0(str);
            this.bbm.T0(str);
            this.bbm.Q0(str);
            this.bbm.S0(str);
        }
        CpaaDeviceManager cpaaDeviceManager = this.cdm;
        if (cpaaDeviceManager != null) {
            cpaaDeviceManager.unregisterCarPlayDeviceListListener(CLASS);
        }
    }

    public void unpairDeviceDelayCallBack(BluetoothDevice bluetoothDevice) {
        this.cdm.unpairDevice(bluetoothDevice);
    }

    public boolean isCarPlayConnected(BondDevice bondDevice) {
        return this.cdm.isCarPlayConnected(bondDevice);
    }

    public boolean isConnected(BluetoothDevice bluetoothDevice) {
        return this.bbm.V(bluetoothDevice);
    }

    public void disconnect(BluetoothDevice bluetoothDevice) {
        a.a(TAG, "---disconnect---device: " + d.c(bluetoothDevice));
        this.bbm.w(bluetoothDevice, bluetoothDevice != null ? isAndroidAutoDeviceConnected(bluetoothDevice.getAddress()) : false, false);
    }
}
