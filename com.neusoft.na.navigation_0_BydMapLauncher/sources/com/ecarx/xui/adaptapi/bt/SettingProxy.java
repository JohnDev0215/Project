package com.ecarx.xui.adaptapi.bt;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.bt.settings.IBtSettings;
import com.ecarx.xui.adaptapi.bt.settings.IBtSettingsCallback;
import com.ecarx.xui.adaptapi.bt1.common.BtDevice;
import com.ecarx.xui.adaptapi.bt1.common.IPairResponse;
import com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SettingProxy implements IBtSettings {
    private static final String cTag = "SettingProxy";
    private BtImpl _btphone;
    private IBtSettingsCallback _cb;
    private IBtSettingsCallback.Stub _iBtSettingCallBack = new IBtSettingsCallback.Stub() { // from class: com.ecarx.xui.adaptapi.bt.SettingProxy.1
        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onBluetoothServiceReady() throws RemoteException {
            Log.d(SettingProxy.cTag, "onBluetoothServiceReady cb is null " + SettingProxy.this._cb);
            if (SettingProxy.this._cb != null) {
                SettingProxy.this._cb.onBluetoothServiceReady();
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onAdapterStateChanged(int i, int i2) throws RemoteException {
            Log.d(SettingProxy.cTag, "onAdapterStateChanged prevState=" + i + " newState=" + i2);
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onAdapterStateChanged");
                SettingProxy.this._cb.onAdapterStateChanged(i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onAdapterDiscoverableModeChanged(int i, int i2) throws RemoteException {
            Log.d(SettingProxy.cTag, "onAdapterDiscoverableModeChanged prevState=" + i + " newState=" + i2);
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onAdapterDiscoverableModeChanged");
                SettingProxy.this._cb.onAdapterDiscoverableModeChanged(i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onPairedDevicesChanged(List<BtDevice> list) throws RemoteException {
            Log.d(SettingProxy.cTag, "onPairedDevicesChanged");
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onPairedDevicesChanged");
                ArrayList arrayList = new ArrayList();
                Iterator<BtDevice> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(SettingProxy.this.getBtDevice(it.next()));
                }
                SettingProxy.this._cb.onPairedDevicesChanged(arrayList);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onDeviceFoundChanged(int i, BtDevice btDevice) throws RemoteException {
            Log.d(SettingProxy.cTag, "onDeviceFoundChanged");
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onDeviceFoundChanged");
                SettingProxy.this._cb.onDeviceFoundChanged(i, SettingProxy.this.getBtDevice(btDevice));
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onDeviceBondStateChanged(BtDevice btDevice, int i, int i2) throws RemoteException {
            Log.d(SettingProxy.cTag, "onDeviceBondStateChanged");
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onDeviceBondStateChanged");
                SettingProxy.this._cb.onDeviceBondStateChanged(SettingProxy.this.getBtDevice(btDevice), i, i2);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onDeviceUuidsUpdated(BtDevice btDevice) throws RemoteException {
            Log.d(SettingProxy.cTag, "onDeviceUuidsUpdated");
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onDeviceUuidsUpdated");
                SettingProxy.this._cb.onDeviceUuidsUpdated(SettingProxy.this.getBtDevice(btDevice));
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onLocalAdapterNameChanged(String str) throws RemoteException {
            Log.d(SettingProxy.cTag, "onLocalAdapterNameChanged name = " + str);
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onLocalAdapterNameChanged");
                SettingProxy.this._cb.onLocalAdapterNameChanged(str);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onDeviceOutOfRange(String str) throws RemoteException {
            Log.d(SettingProxy.cTag, "onDeviceOutOfRange address = " + str);
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onDeviceOutOfRange");
                SettingProxy.this._cb.onDeviceOutOfRange(str);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onBtRoleModeChanged(int i) throws RemoteException {
            Log.d(SettingProxy.cTag, "onBtRoleModeChanged mode = " + i);
            if (SettingProxy.this._cb != null) {
                Log.d(SettingProxy.cTag, "onBtRoleModeChanged");
                SettingProxy.this._cb.onBtRoleModeChanged(i);
            }
        }

        @Override // com.ecarx.xui.adaptapi.bt1.settings.IBtSettingsCallback
        public void onPairingRequest(String str, IPairResponse iPairResponse) throws RemoteException {
            Log.d(SettingProxy.cTag, "onPairingRequest code = " + str);
        }
    };
    private com.ecarx.xui.adaptapi.bt1.settings.IBtSettings _iBtSettings;

    public SettingProxy(BtImpl btImpl) {
        this._btphone = btImpl;
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean isBluetoothServiceReady() {
        Log.d(cTag, "isBluetoothServiceReady");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.isBluetoothServiceReady();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean registerBtCallback(com.ecarx.xui.adaptapi.bt.settings.IBtSettingsCallback iBtSettingsCallback) {
        Log.d(cTag, "registerBtCallback cb = " + iBtSettingsCallback);
        this._cb = iBtSettingsCallback;
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return true;
        }
        try {
            if (!iBtSettings.isBluetoothServiceReady()) {
                return true;
            }
            Log.d(cTag, "isBluetoothServiceReady");
            this._cb.onBluetoothServiceReady();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean unregisterBtCallback(com.ecarx.xui.adaptapi.bt.settings.IBtSettingsCallback iBtSettingsCallback) {
        Log.d(cTag, "unregisterBtCallback cb = " + iBtSettingsCallback);
        this._cb = null;
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.unregisterBtCallback(this._iBtSettingCallBack);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public String getServiceVersionName() {
        Log.d(cTag, "getServiceVersionName");
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return null;
        }
        try {
            return iBtSettings.getServiceVersionName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean setBtEnable(boolean z) {
        Log.d(cTag, "setBtEnable enable = " + z);
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.setBtEnable(z);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean setBtDiscoverableTimeout(int i) {
        Log.d(cTag, "setBtDiscoverableTimeout timeout = " + i);
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.setBtDiscoverableTimeout(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean startBtDiscovery() {
        Log.d(cTag, "startBtDiscovery");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.startBtDiscovery();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean cancelBtDiscovery() {
        Log.d(cTag, "cancelBtDiscovery");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.cancelBtDiscovery();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean reqBtPair(String str) {
        Log.d(cTag, "reqBtPair address = " + str);
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.reqBtPair(str);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean reqBtUnpair(String str) {
        Log.d(cTag, "reqBtUnpair address = " + str);
        try {
            return this._iBtSettings.reqBtUnpair(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public List<com.ecarx.xui.adaptapi.bt.common.BtDevice> reqBtPairedDevices() {
        Log.d(cTag, "reqBtPairedDevices");
        if (this._iBtSettings == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<BtDevice> it = this._iBtSettings.reqBtPairedDevices().iterator();
            while (it.hasNext()) {
                arrayList.add(getBtDevice(it.next()));
            }
            return arrayList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean reqBtPairedDevicesAsync() {
        Log.d(cTag, "reqBtPairedDevicesAsync");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.reqBtPairedDevicesAsync();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public List<com.ecarx.xui.adaptapi.bt.common.BtDevice> reqBtFoundDevices() {
        Log.d(cTag, "reqBtFoundDevices");
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<BtDevice> it = this._iBtSettings.reqBtFoundDevices().iterator();
            while (it.hasNext()) {
                arrayList.add(getBtDevice(it.next()));
            }
            return arrayList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public String getBtLocalName() {
        Log.d(cTag, "getBtLocalName");
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return null;
        }
        try {
            return iBtSettings.getBtLocalName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public String getBtRemoteDeviceName(String str) {
        Log.d(cTag, "getBtRemoteDeviceName address = " + str);
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return null;
        }
        try {
            return iBtSettings.getBtRemoteDeviceName(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public String getBtLocalAddress() {
        Log.d(cTag, "getBtLocalAddress");
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return null;
        }
        try {
            return iBtSettings.getBtLocalAddress();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean setBtLocalName(String str) {
        Log.d(cTag, "getBtLocalAddress name = " + str);
        try {
            return this._iBtSettings.setBtLocalName(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean isBtEnabled() {
        Log.d(cTag, "isBtEnabled");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.isBtEnabled();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public int getBtState() {
        Log.d(cTag, "getBtState");
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return 300;
        }
        try {
            return iBtSettings.getBtState();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 300;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean isBtDiscovering() {
        Log.d(cTag, "isBtDiscovering");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.isBtDiscovering();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean isBtDiscoverable() {
        Log.d(cTag, "isBtDiscoverable");
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.isBtDiscoverable();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public void setBtAutoConnectMode(boolean z) {
        Log.d(cTag, "setBtAutoConnectMode enable = " + z);
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings != null) {
            try {
                iBtSettings.setBtAutoConnectMode(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean isBtAutoConnectEnable() {
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.isBtAutoConnectEnable();
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public int reqBtConnectHfpA2dp(String str) {
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return 0;
        }
        try {
            iBtSettings.reqBtConnectHfpA2dp(str);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public int reqBtDisconnectAll() {
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return 0;
        }
        try {
            return iBtSettings.reqBtDisconnectAll();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public int getBtRemoteUuids(String str) {
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return 0;
        }
        try {
            return iBtSettings.getBtRemoteUuids(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public boolean switchBtRoleMode(int i) {
        try {
            com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
            if (iBtSettings != null) {
                return iBtSettings.switchBtRoleMode(i);
            }
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ecarx.xui.adaptapi.bt.settings.IBtSettings
    public int getBtRoleMode() {
        com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings = this._iBtSettings;
        if (iBtSettings == null) {
            return 0;
        }
        try {
            return iBtSettings.getBtRoleMode();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setIBtSettings(com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings) {
        this._iBtSettings = iBtSettings;
    }

    public com.ecarx.xui.adaptapi.bt.common.BtDevice getBtDevice(BtDevice btDevice) {
        com.ecarx.xui.adaptapi.bt.common.BtDevice btDevice2 = new com.ecarx.xui.adaptapi.bt.common.BtDevice();
        btDevice2.setAddress(btDevice.getAddress());
        btDevice2.setName(btDevice.getName());
        btDevice2.setCategory(0);
        btDevice2.setSupportProfile(0);
        return btDevice2;
    }

    public void setBtSetting(com.ecarx.xui.adaptapi.bt1.settings.IBtSettings iBtSettings) {
        Log.d(cTag, "setBtSetting btSettings = " + iBtSettings);
        this._iBtSettings = iBtSettings;
        if (iBtSettings == null) {
            return;
        }
        try {
            iBtSettings.registerBtCallback(this._iBtSettingCallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
