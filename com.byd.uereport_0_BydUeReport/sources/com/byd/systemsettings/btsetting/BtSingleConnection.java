package com.byd.systemsettings.btsetting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import b3.d;
import b3.e;
import b3.i;
import c3.a;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.ConnectState;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CpaaDeviceManager;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import p2.g;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import x3.a;
import y2.b;

/* JADX INFO: loaded from: classes2.dex */
public final class BtSingleConnection {
    public static final String CP_CONNECTED_MAC = "cp_connected_mac";
    private static final long INTERVAL_CALLBACK = 5000;
    private static final int KEEP_MAX_TIMES = 105;
    private static final long KEPP_SINGLE_CONNECTION_TIME = 3000;
    private static final long PAIRED_DELAY = 6000;
    private static final long SAME_DEVICE_INTERVAL_CALLBACK = 20000;
    private static final String TAG = "BtSingleConnection";
    private static d mBondStateCallback;
    private static e mConnectCallback;
    private static i mProfileCallback;
    private static String preCallbackMac;
    private static long preCallbackTime;
    private static final Class<?> CLASS = BtSingleConnection.class;
    private static final ConcurrentHashMap<String, PairedDevice> pairedMacMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> disableMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> targetMap = new ConcurrentHashMap<>();
    private static volatile String targetMac = null;
    private static volatile String hfpMac = null;
    private static volatile boolean isStrategyEnd = true;
    private static volatile boolean isStrategyStop = true;

    /* JADX INFO: renamed from: com.byd.systemsettings.btsetting.BtSingleConnection$10, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass10 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$ConnectState;

        static {
            int[] iArr = new int[ConnectState.values().length];
            $SwitchMap$com$byd$bt$model$ConnectState = iArr;
            try {
                iArr[ConnectState.DISCONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public interface OnDisconnectCompletedCallback {
        void onDisconnectCompleted();
    }

    public interface OnSecondConnectionCallback {
        void onSecondConnected(BluetoothDevice bluetoothDevice);
    }

    public static class PairedDevice {
        private final String mac;
        private final String name;
        private final long pairedTime;

        public PairedDevice(String str, String str2, long j8) {
            this.name = str;
            this.mac = str2;
            this.pairedTime = j8;
        }
    }

    public static class Target {
        public volatile String mac;
    }

    public static void checkAndroidAuto(List<BluetoothDevice> list, String str) {
        b bVarA = b.o.a();
        List<AndroidAutoDevice> androidAutoDevices = CpaaDeviceManager.Singleton.get().getAndroidAutoDevices();
        for (BluetoothDevice bluetoothDevice : list) {
            if (!bluetoothDevice.getAddress().equals(str)) {
                if (supportAndroidAuto(androidAutoDevices, bluetoothDevice)) {
                    a.a(TAG, "--checkAndroidAuto--disconnect hfp and a2dp, d: " + m4.d.c(bluetoothDevice));
                    disableAndroidAutoHfpAndA2dp(bluetoothDevice);
                } else {
                    a.a(TAG, "--checkAndroidAuto--disconnect device, d: " + m4.d.c(bluetoothDevice));
                    bVarA.y(bluetoothDevice);
                    bVarA.z(bluetoothDevice);
                }
            }
        }
    }

    public static synchronized void checkConnectedCountOnConnected(final BluetoothDevice bluetoothDevice, final OnSecondConnectionCallback onSecondConnectionCallback) {
        a.c(TAG, "checkConnectedCountOnConnected", ", device: " + m4.d.c(bluetoothDevice) + ", KEEP: " + m4.d.d(targetMac));
        b bVarA = b.o.a();
        if (!isTarget(bluetoothDevice)) {
            a.a(TAG, "checkConnectedCountOnConnected", ", It is not target, and do disconnectOnAclLayer, device: " + m4.d.c(bluetoothDevice));
            bVarA.z(bluetoothDevice);
            return;
        }
        List<BluetoothDevice> btConnectedList = getBtConnectedList();
        a.e(TAG, "checkConnectedCountOnConnected", ", connectedList.size: " + btConnectedList.size());
        if (btConnectedList.size() < 2) {
            if (checkUserAction(bluetoothDevice)) {
                a.a(TAG, "checkConnectedCountOnConnected", ", do disconnectOnAclLayer, device: " + m4.d.c(bluetoothDevice));
                bVarA.z(bluetoothDevice);
            }
            return;
        }
        if (pairedMacMap == null || !isPairing(bluetoothDevice.getAddress())) {
            a.c(TAG, "checkConnectedCountOnConnected", "-----Device is bonded for long time, do nothing.");
            CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
            if (supportCpaa(bluetoothDevice, cpaaDeviceManager.getAndroidAutoDevices(), cpaaDeviceManager.getCarPlayDevices())) {
                a.c(TAG, "checkConnectedCountOnConnected", "--support AndroidAuto or CarPlay, device: " + m4.d.c(bluetoothDevice));
                return;
            }
            final String address = bluetoothDevice.getAddress();
            a.c(TAG, "checkConnectedCountOnConnected", "-----Device is bonded for long time, do toast.");
            g.a().c(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.7
                @Override // java.lang.Runnable
                public void run() {
                    a.c(BtSingleConnection.TAG, "checkConnectedCountOnConnected", "--onSecondConnected, device: " + m4.d.c(bluetoothDevice));
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    BigDecimal bigDecimalSubtract = new BigDecimal(jCurrentTimeMillis).subtract(new BigDecimal(BtSingleConnection.preCallbackTime));
                    a.c(BtSingleConnection.TAG, "checkConnectedCountOnConnected", "--onSecondConnected, delta: " + bigDecimalSubtract.longValue());
                    if (bigDecimalSubtract.longValue() < 5000) {
                        return;
                    }
                    if (!address.equals(BtSingleConnection.preCallbackMac) || bigDecimalSubtract.longValue() >= 20000) {
                        long unused = BtSingleConnection.preCallbackTime = jCurrentTimeMillis;
                        String unused2 = BtSingleConnection.preCallbackMac = address;
                        OnSecondConnectionCallback onSecondConnectionCallback2 = onSecondConnectionCallback;
                        if (onSecondConnectionCallback2 != null) {
                            onSecondConnectionCallback2.onSecondConnected(bluetoothDevice);
                        }
                    }
                }
            }, 200L);
        } else {
            a.c(TAG, "checkConnectedCountOnConnected", "-----Device is bonded, disconnect other device and auto to connect.");
            checkAndroidAuto(btConnectedList, bluetoothDevice.getAddress());
        }
    }

    private static boolean checkUserAction(BluetoothDevice bluetoothDevice) {
        ConcurrentHashMap<String, Long> concurrentHashMap = disableMap;
        if (concurrentHashMap.get(bluetoothDevice.getAddress()) == null) {
            return false;
        }
        BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(concurrentHashMap.get(bluetoothDevice.getAddress()).longValue()));
        a.a(TAG, "---checkUserAction---delta: " + bigDecimalSubtract.longValue());
        return bigDecimalSubtract.longValue() < 5000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void delayToClearDisableMap(String str) {
        targetMac = null;
        a.c(TAG, "---delayToClearDisableMap---do clear.");
        c3.a aVarA = a.c.a();
        HashMap map = new HashMap();
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            map.put(bluetoothDevice.getAddress(), bluetoothDevice);
        }
        Iterator<String> it = disableMap.keySet().iterator();
        while (it.hasNext()) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) map.get(it.next());
            AndroidAutoDevice connectedAndroidAutoDevice = AndroidAutoManager.getInstance(SystemDialog.getContext()).getConnectedAndroidAutoDevice();
            if (connectedAndroidAutoDevice != null && bluetoothDevice2 != null && bluetoothDevice2.getAddress() != null && bluetoothDevice2.getAddress().equals(connectedAndroidAutoDevice.getBtAddr())) {
                x3.a.a(TAG, "当前device AA连接中,不能解禁用AA hfp");
            } else if (bluetoothDevice2 != null && bluetoothDevice2.getAddress() != null && bluetoothDevice2.getAddress().equals(str)) {
                aVarA.g(bluetoothDevice2);
            }
        }
        ConcurrentHashMap<String, Long> concurrentHashMap = disableMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
    }

    public static void disConnectAndroidAutoHfpAndA2dp(String str) {
        if (str == null) {
            return;
        }
        c3.a aVarA = a.c.a();
        b bVarA = b.o.a();
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        List<AndroidAutoDevice> androidAutoDevices = cpaaDeviceManager.getAndroidAutoDevices();
        List<CarPlayDevice> carPlayDevices = cpaaDeviceManager.getCarPlayDevices();
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices == null || bondedDevices.size() <= 0) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (((Boolean) m4.a.b(bluetoothDevice, "isConnected")).booleanValue()) {
                if (!aVarA.o(bluetoothDevice)) {
                    x3.a.a(TAG, "disConnectAndroidAutoHfpAndA2dp:isHfpProfileConnected:false");
                } else if (!str.equals(bluetoothDevice.getAddress())) {
                    aVarA.f(bluetoothDevice);
                    aVarA.e(bluetoothDevice);
                    if (!supportCpaa(bluetoothDevice, androidAutoDevices, carPlayDevices)) {
                        bVarA.y(bluetoothDevice);
                    }
                }
            }
        }
    }

    private static void disableAndroidAutoHfpAndA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !checkUserAction(bluetoothDevice)) {
            return;
        }
        c3.a aVarA = a.c.a();
        aVarA.f(bluetoothDevice);
        aVarA.e(bluetoothDevice);
        aVarA.d(bluetoothDevice);
    }

    public static void disconnectAndroidAutoHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.a(TAG, "---disconnectAndroidAutoHfp---device: " + m4.d.c(bluetoothDevice));
        if (supportAndroidAuto(CpaaDeviceManager.Singleton.get().getAndroidAutoDevices(), bluetoothDevice)) {
            hfpMac = bluetoothDevice.getAddress();
            g.a().c(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.9
                @Override // java.lang.Runnable
                public void run() {
                    String unused = BtSingleConnection.hfpMac = null;
                    x3.a.a(BtSingleConnection.TAG, "---disconnectAndroidAutoHfp---hfpMac is null");
                }
            }, KEPP_SINGLE_CONNECTION_TIME);
        }
    }

    public static void disconnectCpaa() {
        List<BluetoothDevice> btConnectedList = getBtConnectedList();
        if (btConnectedList.isEmpty()) {
            return;
        }
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        List<AndroidAutoDevice> androidAutoDevices = cpaaDeviceManager.getAndroidAutoDevices();
        List<CarPlayDevice> carPlayDevices = cpaaDeviceManager.getCarPlayDevices();
        for (BluetoothDevice bluetoothDevice : btConnectedList) {
            if (supportAndroidAuto(androidAutoDevices, bluetoothDevice)) {
                cpaaDeviceManager.disconnectAndroidAuto(bluetoothDevice.getAddress());
            } else if (supportCarPlay(carPlayDevices, bluetoothDevice)) {
                cpaaDeviceManager.disconnectCarPlay(bluetoothDevice.getAddress());
            }
        }
    }

    private static void doSingleConnectionStrategy(final String str) {
        x3.a.c(TAG, "doSingleConnectionStrategy", "---doSingleConnectionStrategy---");
        if (!isStrategyEnd) {
            isStrategyStop = true;
        }
        g.a().g(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.8
            @Override // java.lang.Runnable
            public void run() {
                x3.a.e(BtSingleConnection.TAG, "doSingleConnectionStrategy", "-----Strategy READY, mac: " + m4.d.d((String) BtSingleConnection.targetMap.get(str)));
                while (!BtSingleConnection.isStrategyEnd) {
                    x3.a.e(BtSingleConnection.TAG, "doSingleConnectionStrategy", "-----Strategy STANDBY. isStrategyEnd: " + BtSingleConnection.isStrategyEnd + ", isStrategyStop: " + BtSingleConnection.isStrategyStop);
                    SystemClock.sleep(10L);
                }
                boolean unused = BtSingleConnection.isStrategyEnd = false;
                boolean unused2 = BtSingleConnection.isStrategyStop = false;
                x3.a.e(BtSingleConnection.TAG, "doSingleConnectionStrategy", "-----Strategy START. mac: " + m4.d.d((String) BtSingleConnection.targetMap.get(str)));
                int i8 = 0;
                while (i8 < 105 && BtSingleConnection.targetMap.get(str) != null && !BtSingleConnection.isStrategyStop) {
                    BtSingleConnection.keepSingleConnection(str);
                    i8++;
                    SystemClock.sleep(330L);
                }
                x3.a.e(BtSingleConnection.TAG, "doSingleConnectionStrategy", "-----Strategy END. keepTimes: " + i8 + ", mac: " + m4.d.d((String) BtSingleConnection.targetMap.get(str)));
                if (BtSingleConnection.targetMap.get(str) != null) {
                    BtSingleConnection.targetMap.remove(str);
                }
                BtSingleConnection.delayToClearDisableMap(str);
                boolean unused3 = BtSingleConnection.isStrategyEnd = true;
            }
        });
    }

    public static void enableAndroidAutoHfp(BluetoothDevice bluetoothDevice, boolean z7) {
        x3.a.a(TAG, "enableAndroidAutoHfp:" + z7);
        a.c.a().h(bluetoothDevice, z7);
    }

    public static Map<String, String> getAndroidAutoDeviceMacMap() {
        HashMap map = new HashMap();
        List<AndroidAutoDevice> androidAutoDevices = CpaaDeviceManager.Singleton.get().getAndroidAutoDevices();
        if (androidAutoDevices != null && !androidAutoDevices.isEmpty()) {
            for (AndroidAutoDevice androidAutoDevice : androidAutoDevices) {
                map.put(androidAutoDevice.getBtAddr(), androidAutoDevice.getBtAddr());
            }
        }
        return map;
    }

    private static List<BluetoothDevice> getBtConnectedList() {
        return b.o.a().I();
    }

    public static Map<String, String> getCarPlayDeviceMacMap() {
        HashMap map = new HashMap();
        List<CarPlayDevice> carPlayDevices = CpaaDeviceManager.Singleton.get().getCarPlayDevices();
        if (carPlayDevices != null && !carPlayDevices.isEmpty()) {
            for (CarPlayDevice carPlayDevice : carPlayDevices) {
                map.put(carPlayDevice.getBtAddr(), carPlayDevice.getBtAddr());
                if (carPlayDevice.getDeviceStatus() == 4) {
                    map.put(CP_CONNECTED_MAC, carPlayDevice.getBtAddr());
                }
            }
        }
        return map;
    }

    public static void init(Context context, final OnSecondConnectionCallback onSecondConnectionCallback) {
        x3.a.c(TAG, "init", "-----init-----");
        final b bVarA = b.o.a();
        if (mConnectCallback == null) {
            mConnectCallback = new e() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.1
                @Override // b3.e
                public /* bridge */ /* synthetic */ void onConnectFailed(BluetoothDevice bluetoothDevice) {
                    super.onConnectFailed(bluetoothDevice);
                }

                @Override // b3.e
                public void onConnectStateChanged(BluetoothDevice bluetoothDevice, ConnectState connectState) {
                    BtSingleConnection.updateTargetConnectState(bluetoothDevice, connectState);
                    if (connectState == ConnectState.CONNECTED) {
                        bVarA.K0();
                        BtSingleConnection.checkConnectedCountOnConnected(bluetoothDevice, onSecondConnectionCallback);
                    }
                }

                @Override // b3.e
                public /* bridge */ /* synthetic */ void onConnectedBtNameChanged(BTDevice bTDevice) {
                    super.onConnectedBtNameChanged(bTDevice);
                }
            };
        }
        Class<?> cls = CLASS;
        bVarA.t0(cls.getName(), mConnectCallback);
        if (mBondStateCallback == null) {
            mBondStateCallback = new d() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.2
                @Override // b3.d
                public void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
                    x3.a.c(BtSingleConnection.TAG, "---onBtBondFailed---device: " + m4.d.c(bluetoothDevice) + ", errCode: " + i8);
                    SystemDialog.finishBtPairCodeOnMain();
                }

                @Override // b3.d
                public void onBtBondNone(BluetoothDevice bluetoothDevice) {
                    x3.a.c(BtSingleConnection.TAG, "---onBtBondNone---device: " + m4.d.f(bluetoothDevice.getName()));
                    BtSingleConnection.pairedMacMap.remove(bluetoothDevice.getAddress());
                    SystemDialog.finishBtPairCodeOnMain();
                }

                @Override // b3.d
                public void onBtBonded(BluetoothDevice bluetoothDevice) {
                    BtSingleConnection.onDeviceBonded(bluetoothDevice);
                    x3.a.c(BtSingleConnection.TAG, "---onBtBonded---device: " + m4.d.c(bluetoothDevice));
                    SystemDialog.finishBtPairCodeOnMain();
                }

                @Override // b3.d
                public /* bridge */ /* synthetic */ void onBtBonding(BluetoothDevice bluetoothDevice) {
                    super.onBtBonding(bluetoothDevice);
                }

                @Override // b3.d
                public /* bridge */ /* synthetic */ void onOobBonded(String str, String str2) {
                    super.onOobBonded(str, str2);
                }
            };
        }
        bVarA.s0(cls.getName(), mBondStateCallback);
        if (mProfileCallback == null) {
            mProfileCallback = new i() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.3
                @Override // b3.i
                public void onConnectedChanged(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
                    BtSingleConnection.updateTargetConnectState(bluetoothDevice, connectState);
                    if (i8 == 16) {
                        BtSingleConnection.onHfpConnectedChanged(bluetoothDevice, connectState);
                    }
                }

                @Override // b3.i
                public /* bridge */ /* synthetic */ void onServiceConnected(int i8) {
                    super.onServiceConnected(i8);
                }

                @Override // b3.i
                public /* bridge */ /* synthetic */ void onServiceDisconnected(int i8) {
                    super.onServiceDisconnected(i8);
                }
            };
        }
        bVarA.z0(cls.getName(), mProfileCallback);
        CpaaDeviceManager.Singleton.get().registerAndroidAutoDeviceListListener(cls.getName(), new AndroidAutoManager.AndroidAutoDeviceListListener() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.4
            @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
            public void onAndroidAutoDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
            }
        });
    }

    public static boolean isPairing(String str) {
        return pairedMacMap.get(str) != null;
    }

    private static boolean isTarget(BluetoothDevice bluetoothDevice) {
        if (TextUtils.isEmpty(targetMac) || bluetoothDevice == null) {
            return true;
        }
        return targetMac.equals(bluetoothDevice.getAddress());
    }

    public static void keepSingle(String str, OnDisconnectCompletedCallback onDisconnectCompletedCallback) {
        x3.a.c(TAG, "keepSingle", "-----do keep single connection. mac: " + m4.d.d(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        targetMac = str;
        targetMap.put(str, str);
        doSingleConnectionStrategy(str);
        if (onDisconnectCompletedCallback != null) {
            onDisconnectCompletedCallback.onDisconnectCompleted();
        }
    }

    public static int keepSingleConnection(String str) {
        List<BluetoothDevice> btConnectedList = getBtConnectedList();
        if (btConnectedList.isEmpty()) {
            return 0;
        }
        b bVarA = b.o.a();
        List<AndroidAutoDevice> androidAutoDevices = CpaaDeviceManager.Singleton.get().getAndroidAutoDevices();
        int i8 = 0;
        for (BluetoothDevice bluetoothDevice : btConnectedList) {
            if (!bluetoothDevice.getAddress().equals(str)) {
                x3.a.c(TAG, "--keepSingleConnection--disconnect--d: " + m4.d.c(bluetoothDevice));
                i8++;
                if (supportAndroidAuto(androidAutoDevices, bluetoothDevice)) {
                    x3.a.c(TAG, "--keepSingleConnection--disconnect--aa--d: " + m4.d.c(bluetoothDevice));
                    disableMap.put(bluetoothDevice.getAddress(), Long.valueOf(System.currentTimeMillis()));
                    disableAndroidAutoHfpAndA2dp(bluetoothDevice);
                }
                bVarA.y(bluetoothDevice);
                bVarA.z(bluetoothDevice);
            }
        }
        return i8;
    }

    public static void onDeviceBonded(final BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.c(TAG, "onDeviceBonded", "---device: " + m4.d.c(bluetoothDevice));
        final c3.a aVarA = a.c.a();
        List<BluetoothDevice> btConnectedList = getBtConnectedList();
        if (btConnectedList == null || btConnectedList.size() <= 1) {
            aVarA.c(bluetoothDevice);
        } else {
            x3.a.c(TAG, "onDeviceBonded", "---connectedList.size: " + btConnectedList.size());
            keepSingle(bluetoothDevice.getAddress(), null);
            g.a().d(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.5
                @Override // java.lang.Runnable
                public void run() {
                    x3.a.c(BtSingleConnection.TAG, "onDeviceBonded", "---do connectHfpAndA2dpProfile, device: " + m4.d.c(bluetoothDevice));
                    aVarA.c(bluetoothDevice);
                }
            }, 800L);
        }
        final String address = bluetoothDevice.getAddress();
        pairedMacMap.put(address, new PairedDevice(bluetoothDevice.getName(), address, System.currentTimeMillis()));
        g.a().d(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSingleConnection.6
            @Override // java.lang.Runnable
            public void run() {
                PairedDevice pairedDevice = (PairedDevice) BtSingleConnection.pairedMacMap.get(address);
                if (pairedDevice == null) {
                    return;
                }
                BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(pairedDevice.pairedTime));
                x3.a.c(BtSingleConnection.TAG, "onDeviceBonded", "---device: " + m4.d.f(pairedDevice.name) + ", pairedTimeDelay: " + bigDecimalSubtract.longValue());
                if (bigDecimalSubtract.longValue() >= BtSingleConnection.PAIRED_DELAY) {
                    BtSingleConnection.pairedMacMap.remove(address);
                }
            }
        }, PAIRED_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onHfpConnectedChanged(BluetoothDevice bluetoothDevice, ConnectState connectState) {
        x3.a.c(TAG, "---onHfpConnectedChanged---device: " + m4.d.c(bluetoothDevice) + ", state: " + connectState);
        int i8 = AnonymousClass10.$SwitchMap$com$byd$bt$model$ConnectState[connectState.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                return;
            }
            x3.a.e(TAG, "---onHfpConnectedChanged---CONNECTED--device: " + m4.d.c(bluetoothDevice));
            if (isPairing(bluetoothDevice.getAddress())) {
                checkConnectedCountOnConnected(bluetoothDevice, null);
                return;
            }
            return;
        }
        if (hfpMac == null) {
            return;
        }
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        String address = bluetoothDevice.getAddress();
        if (supportAndroidAuto(cpaaDeviceManager.getAndroidAutoDevices(), bluetoothDevice) && cpaaDeviceManager.isAndroidAutoDeviceConnected(address)) {
            return;
        }
        if (supportCarPlay(cpaaDeviceManager.getCarPlayDevices(), bluetoothDevice) && cpaaDeviceManager.isCarPlayDeviceConnected(address)) {
            return;
        }
        x3.a.a(TAG, "---onHfpConnectedChanged---do disconnectOnAclLayer, device: " + m4.d.c(bluetoothDevice));
    }

    public static void onServiceDestroy() {
        x3.a.c(TAG, "-----onServiceDestroy-----");
        b bVarA = b.o.a();
        if (bVarA != null) {
            Class<?> cls = CLASS;
            bVarA.N0(cls.getName());
            bVarA.M0(cls.getName());
            bVarA.T0(cls.getName());
        }
        a3.a.b();
        ConcurrentHashMap<String, PairedDevice> concurrentHashMap = pairedMacMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, Long> concurrentHashMap2 = disableMap;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        if (cpaaDeviceManager != null) {
            cpaaDeviceManager.unregisterAndroidAutoDeviceListListener(CLASS.getName());
        }
    }

    public static void restoreAndroidAutoHfp(List<BondDevice> list, String str) {
        c3.a aVarA = a.c.a();
        List<AndroidAutoDevice> androidAutoDevices = CpaaDeviceManager.Singleton.get().getAndroidAutoDevices();
        for (BondDevice bondDevice : list) {
            if (!bondDevice.getMac().equals(str) && supportAndroidAuto(androidAutoDevices, bondDevice.getDevice())) {
                aVarA.g(bondDevice.getDevice());
            }
        }
    }

    public static boolean supportAndroidAuto(List<AndroidAutoDevice> list, BluetoothDevice bluetoothDevice) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<AndroidAutoDevice> it = list.iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportCarPlay(List<CarPlayDevice> list, BluetoothDevice bluetoothDevice) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<CarPlayDevice> it = list.iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportCpaa(BluetoothDevice bluetoothDevice, List<AndroidAutoDevice> list, List<CarPlayDevice> list2) {
        return supportCarPlay(list2, bluetoothDevice) || supportAndroidAuto(list, bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateTargetConnectState(BluetoothDevice bluetoothDevice, ConnectState connectState) {
        if (targetMac == null || bluetoothDevice == null || !bluetoothDevice.getAddress().equals(targetMac)) {
            return;
        }
        x3.a.c(TAG, "---updateTargetConnectState---device: " + m4.d.c(bluetoothDevice) + ", state: " + connectState);
        if (connectState == ConnectState.CONNECTED) {
            targetMap.remove(bluetoothDevice.getAddress());
        }
    }

    public static boolean supportCpaa(BluetoothDevice bluetoothDevice) {
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        return supportCarPlay(cpaaDeviceManager.getCarPlayDevices(), bluetoothDevice) || supportAndroidAuto(cpaaDeviceManager.getAndroidAutoDevices(), bluetoothDevice);
    }

    public static boolean supportAndroidAuto(BluetoothDevice bluetoothDevice) {
        List<AndroidAutoDevice> androidAutoDevices = CpaaDeviceManager.Singleton.get().getAndroidAutoDevices();
        if (androidAutoDevices == null || androidAutoDevices.isEmpty()) {
            return false;
        }
        Iterator<AndroidAutoDevice> it = androidAutoDevices.iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                return true;
            }
        }
        return false;
    }

    public static boolean supportCarPlay(BluetoothDevice bluetoothDevice) {
        List<CarPlayDevice> carPlayDevices = CpaaDeviceManager.Singleton.get().getCarPlayDevices();
        if (carPlayDevices == null || carPlayDevices.isEmpty()) {
            return false;
        }
        Iterator<CarPlayDevice> it = carPlayDevices.iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.getAddress().equals(it.next().getBtAddr())) {
                return true;
            }
        }
        return false;
    }

    public static void restoreAndroidAutoHfp(BondDevice bondDevice) {
        if (bondDevice == null) {
            return;
        }
        a.c.a().g(bondDevice.getDevice());
    }
}
