package com.byd.systemsettings.system.dm;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import b3.d;
import c3.a;
import com.byd.info.BtCallInfo;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.cpaa.CpaaDeviceManager;
import com.byd.systemsettings.cpaa.SystemPanelChangeReceiver;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import com.byd.systemsettings.wifi2.BydWifiManager;
import java.lang.ref.WeakReference;
import java.util.List;
import p2.g;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import v2.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydDeviceManager {
    private static final String PKG_BT_CALL = "com.byd.bluetoothcall";
    private static final String TAG = "BydDeviceManager";
    private static final boolean isRunning = false;
    private volatile boolean isAADisplay;
    private volatile boolean isCPDisplay;
    private volatile boolean isCpaaDisplay;
    private BtCallListener mBtCallListener;
    private SystemPanelChangeReceiver panelChangeReceiver;
    private volatile int preBtCallState;
    private SharedPreferences spfCpaa;

    public class AndroidAutoStateCallback implements AndroidAutoManager.AndroidAutoDeviceListListener {
        private final WeakReference<BydDeviceManager> ref;

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
            WeakReference<BydDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().aaDeviceStatusChanged(i8, androidAutoDevice);
        }

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onVideoFocusStatusChanged(boolean z7) {
            WeakReference<BydDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().aaVideoFocusStatusChanged(z7);
        }

        private AndroidAutoStateCallback(BydDeviceManager bydDeviceManager) {
            this.ref = new WeakReference<>(bydDeviceManager);
        }
    }

    public class BtCallListener implements a {
        private final WeakReference<BydDeviceManager> ref;

        public BtCallListener(BydDeviceManager bydDeviceManager) {
            this.ref = new WeakReference<>(bydDeviceManager);
        }

        @Override // v2.a
        public void CallState_Change(List<BtCallInfo> list) {
            WeakReference<BydDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onBtCallStateChanged(list.size());
        }
    }

    public class CarPlayStateCallback implements CarPlayManager.CarPlayDeviceListListener {
        private final WeakReference<BydDeviceManager> ref;

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
            WeakReference<BydDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().cpDeviceStatusChanged(i8, carPlayDevice);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            WeakReference<BydDeviceManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().cpDisplayStatusChanged(z7);
        }

        private CarPlayStateCallback(BydDeviceManager bydDeviceManager) {
            this.ref = new WeakReference<>(bydDeviceManager);
        }
    }

    public static class Singleton {
        private static final BydDeviceManager instance = new BydDeviceManager();

        public static BydDeviceManager get() {
            return instance;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
        String[] strArr = new String[2];
        strArr[0] = "aaDeviceStatusChanged";
        StringBuilder sb = new StringBuilder();
        sb.append(", status: ");
        sb.append(i8);
        sb.append(", device: ");
        sb.append(androidAutoDevice != null ? androidAutoDevice.getDeviceName() : "null");
        strArr[1] = sb.toString();
        x3.a.a(TAG, strArr);
        if (i8 != 4 || androidAutoDevice == null) {
            return;
        }
        if (w2.a.p().o() > 0) {
            x3.a.a(TAG, "aaDeviceStatusChanged", "当前连接设备正在通话,蓝牙HFP不做断开处理");
            return;
        }
        String btAddr = androidAutoDevice.getBtAddr();
        if (TextUtils.isEmpty(btAddr)) {
            btAddr = androidAutoDevice.getUsbSerialNumber();
        }
        if (!TextUtils.isEmpty(btAddr)) {
            saveConnectedTime(btAddr.hashCode() + "");
        }
        BtSingleConnection.disConnectAndroidAutoHfpAndA2dp(androidAutoDevice.getBtAddr());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cpDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
        String[] strArr = new String[2];
        strArr[0] = "cpDeviceStatusChanged";
        StringBuilder sb = new StringBuilder();
        sb.append(", status: ");
        sb.append(i8);
        sb.append(", device: ");
        sb.append(carPlayDevice != null ? carPlayDevice.getDeviceName() : "null");
        strArr[1] = sb.toString();
        x3.a.a(TAG, strArr);
        if (i8 != 2 || carPlayDevice == null) {
            return;
        }
        String btAddr = carPlayDevice.getBtAddr();
        if (TextUtils.isEmpty(btAddr)) {
            btAddr = carPlayDevice.getUsbSerialNumber();
        }
        if (TextUtils.isEmpty(btAddr)) {
            return;
        }
        saveConnectedTime(btAddr.hashCode() + "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cpDisplayStatusChanged(boolean z7) {
        this.isCPDisplay = z7;
        x3.a.a(TAG, "cpDisplayStatusChanged", ", isShow: " + z7);
        onCpaaDisplay(isCpaaDisplayed());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCpaaDisplayed() {
        return this.isAADisplay || this.isCPDisplay;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBtCallStateChanged(int i8) {
        x3.a.a(TAG, "onBtCallStateChanged, state: " + i8 + ", preBtCallState: " + this.preBtCallState);
        if (i8 == this.preBtCallState) {
            return;
        }
        doOnBtCallStateChanged(i8);
        this.preBtCallState = i8;
    }

    private synchronized void saveConnectedTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.spfCpaa.edit();
        editorEdit.putLong(str, System.currentTimeMillis());
        editorEdit.commit();
    }

    public void aaVideoFocusStatusChanged(boolean z7) {
        this.isAADisplay = z7;
        x3.a.a(TAG, "aaVideoFocusStatusChanged", ", isShow: " + z7);
        onCpaaDisplay(isCpaaDisplayed());
    }

    public void doOnBtCallStateChanged(int i8) {
        if (i8 <= 0) {
            return;
        }
        SystemDialog.finishWifiSettings();
    }

    public void init(final Context context) {
        if (context == null) {
            return;
        }
        x3.a.a(TAG, "----init-----");
        Context applicationContext = context.getApplicationContext();
        this.spfCpaa = applicationContext.getSharedPreferences("key_cpaa_connected_time", 0);
        x3.a.a(TAG, "---onCreate---");
        IntentFilter intentFilter = new IntentFilter(SystemPanelChangeReceiver.SYS_PANEL_STATE_CHANGE_ACTION);
        SystemPanelChangeReceiver systemPanelChangeReceiver = new SystemPanelChangeReceiver();
        this.panelChangeReceiver = systemPanelChangeReceiver;
        context.registerReceiver(systemPanelChangeReceiver, intentFilter);
        if (this.mBtCallListener == null) {
            this.mBtCallListener = new BtCallListener(this);
        }
        w2.a aVarP = w2.a.p();
        aVarP.r(applicationContext, PKG_BT_CALL);
        aVarP.t(this.mBtCallListener);
        g.a().g(new Runnable() { // from class: com.byd.systemsettings.system.dm.BydDeviceManager.1
            @Override // java.lang.Runnable
            public void run() {
                CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
                cpaaDeviceManager.init(context.getApplicationContext());
                x3.a.a(BydDeviceManager.TAG, "---onCreate---cdm init completed.");
                String name = getClass().getName();
                BydDeviceManager bydDeviceManager = BydDeviceManager.this;
                cpaaDeviceManager.registerCarPlayDeviceListListener(name, new CarPlayStateCallback(bydDeviceManager));
                String name2 = getClass().getName();
                BydDeviceManager bydDeviceManager2 = BydDeviceManager.this;
                cpaaDeviceManager.registerAndroidAutoDeviceListListener(name2, new AndroidAutoStateCallback(bydDeviceManager2));
                cpaaDeviceManager.registerBtBondSateCallback(getClass().getName(), new d() { // from class: com.byd.systemsettings.system.dm.BydDeviceManager.1.1
                    @Override // b3.d
                    public void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
                        x3.a.a(BydDeviceManager.TAG, "--onCreate--onBtBondFailed---errCode: " + i8 + ", device: " + m4.d.c(bluetoothDevice));
                        if (i8 == 1 || i8 == 2 || i8 == 3) {
                            g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.dm.BydDeviceManager.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    SystemDialog.finishBtPairCode();
                                }
                            });
                        }
                    }

                    @Override // b3.d
                    public /* bridge */ /* synthetic */ void onBtBondNone(BluetoothDevice bluetoothDevice) {
                        super.onBtBondNone(bluetoothDevice);
                    }

                    @Override // b3.d
                    public /* bridge */ /* synthetic */ void onBtBonded(BluetoothDevice bluetoothDevice) {
                        super.onBtBonded(bluetoothDevice);
                    }

                    @Override // b3.d
                    public /* bridge */ /* synthetic */ void onBtBonding(BluetoothDevice bluetoothDevice) {
                        super.onBtBonding(bluetoothDevice);
                    }

                    @Override // b3.d
                    public /* bridge */ /* synthetic */ void onOobBonded(String str, String str2) {
                        super.onOobBonded(str, str2);
                    }
                });
                BtSingleConnection.init(context.getApplicationContext(), new BtSingleConnection.OnSecondConnectionCallback() { // from class: com.byd.systemsettings.system.dm.BydDeviceManager.1.2
                    @Override // com.byd.systemsettings.btsetting.BtSingleConnection.OnSecondConnectionCallback
                    public void onSecondConnected(BluetoothDevice bluetoothDevice) {
                        x3.a.a(BydDeviceManager.TAG, "---onSecondConnected---device: " + bluetoothDevice.getName());
                        if (BydDeviceManager.this.isCpaaDisplayed()) {
                            return;
                        }
                        x3.a.a(BydDeviceManager.TAG, "---onSecondConnected---without cp or aa device, and do toast.");
                    }
                });
                x3.a.a(BydDeviceManager.TAG, "---onCreate---SingleConnection init completed.");
                BydWifiManager.Singleton.get().init(context);
                x3.a.a(BydDeviceManager.TAG, "init_wifi_ok");
            }
        });
    }

    public void onCpaaDisplay(boolean z7) {
        this.isCpaaDisplay = z7;
        x3.a.a(TAG, "----onCpaaDisplay----display: " + z7);
        if (z7) {
            SystemDialog.finishBtPairCode();
            SystemDialog.finishBtSettings();
            SystemDialog.finishWifiSettings();
            SystemDialog.finishHotspotSettings();
        }
    }

    public void onServiceDestroy(Context context) {
        x3.a.a(TAG, "---onServiceDestroy---");
        a.c.a().u();
        if (this.mBtCallListener != null) {
            w2.a.p().v(this.mBtCallListener);
        }
        BtSingleConnection.onServiceDestroy();
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        cpaaDeviceManager.unregisterCarPlayDeviceListListener(getClass().getName());
        cpaaDeviceManager.unregisterAndroidAutoDeviceListListener(getClass().getName());
        cpaaDeviceManager.release(context);
        BydWifiManager.Singleton.get().release();
        ServiceDialogHolder.release();
        context.unregisterReceiver(this.panelChangeReceiver);
    }

    private BydDeviceManager() {
        this.isAADisplay = false;
        this.isCPDisplay = false;
        this.preBtCallState = -999;
        this.isCpaaDisplay = false;
    }
}
