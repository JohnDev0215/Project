package com.byd.systemsettings.cpaa.present;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import b3.d;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.cpaa.CarPlayPager;
import com.byd.systemsettings.cpaa.CpaaDeviceManager;
import com.byd.systemsettings.cpaa.model.CPAADevice;
import com.byd.systemsettings.cpaa.model.CarPlayType;
import java.lang.ref.WeakReference;
import java.util.List;
import q2.b;
import q2.c;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import x3.a;
import z3.e;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayAAPresenter extends f<CarPlayPager> {
    public static final int LIST_UPDATE_MSG = 1;
    private static final String TAG = "CarPlayAAPresenter";
    private CpaaDeviceManager cdm;
    private Context mContext;

    public class AAStateCallback implements AndroidAutoManager.AndroidAutoDeviceListListener {
        private final WeakReference<CarPlayAAPresenter> ref;

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onAndroidAutoDeviceStatusChanged(int i8, AndroidAutoDevice androidAutoDevice) {
            WeakReference<CarPlayAAPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((CarPlayPager) this.ref.get().getV()).androidAutoDeviceStatusChanged(i8, androidAutoDevice);
        }

        @Override // com.byd.systemsettings.cpaa.AndroidAutoManager.AndroidAutoDeviceListListener
        public void onVideoFocusStatusChanged(boolean z7) {
            WeakReference<CarPlayAAPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((CarPlayPager) this.ref.get().getV()).androidAutoVideoFocusStatusChanged(z7);
        }

        private AAStateCallback(CarPlayAAPresenter carPlayAAPresenter) {
            this.ref = new WeakReference<>(carPlayAAPresenter);
        }
    }

    public class CPtateCallback implements CarPlayManager.CarPlayDeviceListListener {
        private final WeakReference<CarPlayAAPresenter> ref;

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDeviceStatusChanged(int i8, CarPlayDevice carPlayDevice) {
            WeakReference<CarPlayAAPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((CarPlayPager) this.ref.get().getV()).carPlayDeviceStatusChanged(i8, carPlayDevice);
        }

        @Override // com.byd.systemsettings.cpaa.CarPlayManager.CarPlayDeviceListListener
        public void onCarPlayDisplayStatusChanged(boolean z7) {
            WeakReference<CarPlayAAPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((CarPlayPager) this.ref.get().getV()).carPlayDisplayStatusChanged(z7);
        }

        private CPtateCallback(CarPlayAAPresenter carPlayAAPresenter) {
            this.ref = new WeakReference<>(carPlayAAPresenter);
        }
    }

    public void connect(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        a.a(TAG, ", connect device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        if (w2.a.p().o() > 0) {
            a.a(TAG, "当前连接设备正在通话,蓝牙HFP不做断开处理");
        } else {
            BtSingleConnection.keepSingle(cPAADevice.getBtMac(), null);
        }
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.cdm.connectCarPlay(cPAADevice);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            this.cdm.connectAndroidAuto(cPAADevice);
        }
    }

    public void connectAndroidAuto(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        this.cdm.requestConnectAndroidAuto(cPAADevice);
    }

    public void connectCarPlay(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        this.cdm.requestConnectCarPlay(cPAADevice.getBtMac());
    }

    public String disconnect(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return "";
        }
        a.a(TAG, ", disconnect device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        String btMac = cPAADevice.getBtMac();
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.cdm.disconnectCarPlay(btMac);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            if (TextUtils.isEmpty(btMac)) {
                btMac = cPAADevice.getUsbSN();
            }
            this.cdm.disconnectAndroidAuto(btMac);
        }
        return btMac;
    }

    public List<CPAADevice> getAACPDeviceList() {
        return this.cdm.getCPAADeviceList();
    }

    public AndroidAutoDevice getConnectedAndroidAutoDevice() {
        return this.cdm.getConnectedAndroidAutoDevice();
    }

    public CarPlayDevice getConnectedCarPlayDevice() {
        return this.cdm.getConnectedCarPlayDevice();
    }

    public AndroidAutoDevice getConnectingAndroidAutoDevice() {
        return this.cdm.getConnectingAndroidAutoDevice();
    }

    public CarPlayDevice getConnectingCarPlayDevice() {
        return this.cdm.getConnectingCarPlayDevice();
    }

    public String getCurDisplayDeviceMac() {
        return this.cdm.getCurDisplayDeviceMac();
    }

    public boolean getDeviceBtSignal(CPAADevice cPAADevice) {
        return this.cdm.getDeviceBtSignal(cPAADevice);
    }

    public boolean getDevicePhoneSignal(CPAADevice cPAADevice) {
        return this.cdm.getDevicePhoneSignal(cPAADevice);
    }

    public boolean getDeviceWifiSignal(CPAADevice cPAADevice) {
        return this.cdm.getDeviceWifiSignal(cPAADevice);
    }

    public void init(Context context) {
        this.mContext = context;
        CpaaDeviceManager cpaaDeviceManager = CpaaDeviceManager.Singleton.get();
        this.cdm = cpaaDeviceManager;
        cpaaDeviceManager.registerAndroidAutoDeviceListListener(getClass().getName(), new AAStateCallback(this));
        this.cdm.registerCarPlayDeviceListListener(getClass().getName(), new CPtateCallback(this));
        this.cdm.registerBtBondSateCallback(getClass().getName(), new d() { // from class: com.byd.systemsettings.cpaa.present.CarPlayAAPresenter.1
            @Override // b3.d
            public /* bridge */ /* synthetic */ void onBtBondFailed(BluetoothDevice bluetoothDevice, int i8) {
                super.onBtBondFailed(bluetoothDevice, i8);
            }

            @Override // b3.d
            public void onBtBondNone(BluetoothDevice bluetoothDevice) {
            }

            @Override // b3.d
            public void onBtBonded(BluetoothDevice bluetoothDevice) {
                if (CarPlayAAPresenter.this.getV() != null) {
                    ((CarPlayPager) CarPlayAAPresenter.this.getV()).sendUIMessageDelay(1, e.INTERVAL, null);
                }
            }

            @Override // b3.d
            public /* bridge */ /* synthetic */ void onBtBonding(BluetoothDevice bluetoothDevice) {
                super.onBtBonding(bluetoothDevice);
            }

            @Override // b3.d
            public void onOobBonded(String str, String str2) {
                if (CarPlayAAPresenter.this.getV() != null) {
                    ((CarPlayPager) CarPlayAAPresenter.this.getV()).sendUIMessageDelay(1, e.INTERVAL, null);
                }
            }
        });
    }

    public boolean isAndroidAutoConnected() {
        return this.cdm.isAndroidAutoConnected();
    }

    public boolean isAndroidAutoConnecting() {
        return this.cdm.isAndroidAutoConnecting();
    }

    public boolean isCarPlayConnected() {
        return this.cdm.isCarPlayConnected();
    }

    public boolean isCarPlayConnecting() {
        return this.cdm.isCarPlayConnecting();
    }

    public void remove(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        a.a(TAG, ", remove device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        String btMac = cPAADevice.getBtMac();
        if (TextUtils.isEmpty(btMac)) {
            btMac = cPAADevice.getUsbSN();
        }
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.cdm.deleteCarPlayDevice(btMac);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            this.cdm.deleteAndroidAutoDevice(btMac);
        }
        String strA = b.a(this.mContext, CarPlayPager.NO_SHOW_MAC_LIST_CACHE);
        if (c.b(strA) || !strA.contains(btMac)) {
            return;
        }
        b.b(this.mContext, CarPlayPager.NO_SHOW_MAC_LIST_CACHE, strA.replaceAll(btMac, ""));
    }

    public void startAndroidAutoUI() {
        this.cdm.startAndroidAutoUI();
    }

    public void startCarPlayUI() {
        this.cdm.startCarPlayUI();
    }

    @Override // z3.f
    public void unbindV() {
        CpaaDeviceManager cpaaDeviceManager = this.cdm;
        if (cpaaDeviceManager != null) {
            cpaaDeviceManager.unregisterAndroidAutoDeviceListListener(getClass().getName());
            this.cdm.unregisterCarPlayDeviceListListener(getClass().getName());
            this.cdm.unregisterBtBondSateCallback(getClass().getName());
        }
        super.unbindV();
    }
}
