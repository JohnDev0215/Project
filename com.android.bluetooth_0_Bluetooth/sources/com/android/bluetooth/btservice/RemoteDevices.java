package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.auto.Constant;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class RemoteDevices {
    private static final boolean DBG = true;
    private static final int MAX_DEVICE_QUEUE_SIZE = 200;
    private static final int MESSAGE_UUID_INTENT = 1;
    private static final String TAG = "BluetoothRemoteDevices";
    private static final int UUID_INTENT_DELAY = 6000;
    private static BluetoothAdapter sAdapter;
    private static AdapterService sAdapterService;
    private static ArrayList<BluetoothDevice> sSdpTracker;
    private Queue<String> mDeviceQueue;
    private final HashMap<String, DeviceProperties> mDevices;
    private final Handler mHandler;
    private final Object mObject = new Object();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.RemoteDevices.1
        /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                java.lang.String r0 = r7.getAction()
                int r1 = r0.hashCode()
                r2 = -790471265(0xffffffffd0e25d9f, float:-3.038229E10)
                r3 = 2
                r4 = 1
                if (r1 == r2) goto L2e
                r2 = 545516589(0x2083ec2d, float:2.234855E-19)
                if (r1 == r2) goto L24
                r2 = 1772843706(0x69ab72ba, float:2.5908512E25)
                if (r1 == r2) goto L1a
            L19:
                goto L38
            L1a:
                java.lang.String r1 = "android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L19
                r1 = r4
                goto L39
            L24:
                java.lang.String r1 = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L19
                r1 = r3
                goto L39
            L2e:
                java.lang.String r1 = "android.bluetooth.headset.action.HF_INDICATORS_VALUE_CHANGED"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L19
                r1 = 0
                goto L39
            L38:
                r1 = -1
            L39:
                if (r1 == 0) goto L62
                if (r1 == r4) goto L5c
                if (r1 == r3) goto L56
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Unhandled intent: "
                r1.append(r2)
                r1.append(r7)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "BluetoothRemoteDevices"
                android.util.Log.w(r2, r1)
                goto L68
            L56:
                com.android.bluetooth.btservice.RemoteDevices r1 = com.android.bluetooth.btservice.RemoteDevices.this
                r1.onHeadsetConnectionStateChanged(r7)
                goto L68
            L5c:
                com.android.bluetooth.btservice.RemoteDevices r1 = com.android.bluetooth.btservice.RemoteDevices.this
                r1.onVendorSpecificHeadsetEvent(r7)
                goto L68
            L62:
                com.android.bluetooth.btservice.RemoteDevices r1 = com.android.bluetooth.btservice.RemoteDevices.this
                r1.onHfIndicatorValueChanged(r7)
            L68:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.btservice.RemoteDevices.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
        }
    };

    private class RemoteDevicesHandler extends Handler {
        RemoteDevicesHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BluetoothDevice device;
            if (msg.what == 1 && (device = (BluetoothDevice) msg.obj) != null) {
                DeviceProperties prop = RemoteDevices.this.getDeviceProperties(device);
                RemoteDevices.this.sendUuidIntent(device, prop);
            }
        }
    }

    RemoteDevices(AdapterService service, Looper looper) {
        sAdapter = BluetoothAdapter.getDefaultAdapter();
        sAdapterService = service;
        sSdpTracker = new ArrayList<>();
        this.mDevices = new HashMap<>();
        this.mDeviceQueue = new LinkedList();
        this.mHandler = new RemoteDevicesHandler(looper);
    }

    void init() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.headset.action.HF_INDICATORS_VALUE_CHANGED");
        filter.addAction("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        filter.addCategory("android.bluetooth.headset.intent.category.companyid.85");
        filter.addCategory("android.bluetooth.headset.intent.category.companyid.76");
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        sAdapterService.registerReceiver(this.mReceiver, filter);
    }

    void cleanup() {
        sAdapterService.unregisterReceiver(this.mReceiver);
        reset();
    }

    void reset() {
        ArrayList<BluetoothDevice> arrayList = sSdpTracker;
        if (arrayList != null) {
            arrayList.clear();
        }
        HashMap<String, DeviceProperties> map = this.mDevices;
        if (map != null) {
            map.clear();
        }
        Queue<String> queue = this.mDeviceQueue;
        if (queue != null) {
            queue.clear();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    DeviceProperties getDeviceProperties(BluetoothDevice device) {
        DeviceProperties deviceProperties;
        synchronized (this.mDevices) {
            deviceProperties = this.mDevices.get(device.getAddress());
        }
        return deviceProperties;
    }

    BluetoothDevice getDevice(byte[] address) {
        DeviceProperties prop = this.mDevices.get(Utils.getAddressStringFromByte(address));
        if (prop == null) {
            return null;
        }
        return prop.getDevice();
    }

    DeviceProperties addDeviceProperties(byte[] address) {
        synchronized (this.mDevices) {
            DeviceProperties prop = new DeviceProperties();
            prop.mDevice = sAdapter.getRemoteDevice(Utils.getAddressStringFromByte(address));
            prop.mAddress = address;
            String key = Utils.getAddressStringFromByte(address);
            DeviceProperties pv = this.mDevices.put(key, prop);
            if (pv == null) {
                this.mDeviceQueue.offer(key);
                if (this.mDeviceQueue.size() > 200) {
                    String deleteKey = this.mDeviceQueue.poll();
                    for (BluetoothDevice device : sAdapterService.getBondedDevices()) {
                        if (device.getAddress().equals(deleteKey)) {
                            return prop;
                        }
                    }
                    debugLog("Removing device " + deleteKey + " from property map");
                    this.mDevices.remove(deleteKey);
                }
            }
            return prop;
        }
    }

    class DeviceProperties {
        private byte[] mAddress;
        private String mAlias;
        private BluetoothDevice mDevice;
        int mDeviceType;
        private boolean mIsBondingInitiatedLocally;
        private String mName;
        private short mRssi;
        ParcelUuid[] mUuids;
        private int mBluetoothClass = 7936;
        private int mBatteryLevel = -1;
        int mBondState = 10;

        DeviceProperties() {
        }

        String getName() {
            String str;
            synchronized (RemoteDevices.this.mObject) {
                str = this.mName;
            }
            return str;
        }

        int getBluetoothClass() {
            int i;
            synchronized (RemoteDevices.this.mObject) {
                i = this.mBluetoothClass;
            }
            return i;
        }

        ParcelUuid[] getUuids() {
            ParcelUuid[] parcelUuidArr;
            synchronized (RemoteDevices.this.mObject) {
                parcelUuidArr = this.mUuids;
            }
            return parcelUuidArr;
        }

        byte[] getAddress() {
            byte[] bArr;
            synchronized (RemoteDevices.this.mObject) {
                bArr = this.mAddress;
            }
            return bArr;
        }

        BluetoothDevice getDevice() {
            BluetoothDevice bluetoothDevice;
            synchronized (RemoteDevices.this.mObject) {
                bluetoothDevice = this.mDevice;
            }
            return bluetoothDevice;
        }

        short getRssi() {
            short s;
            synchronized (RemoteDevices.this.mObject) {
                s = this.mRssi;
            }
            return s;
        }

        int getDeviceType() {
            int i;
            synchronized (RemoteDevices.this.mObject) {
                i = this.mDeviceType;
            }
            return i;
        }

        String getAlias() {
            String str;
            synchronized (RemoteDevices.this.mObject) {
                str = this.mAlias;
            }
            return str;
        }

        void setAlias(BluetoothDevice device, String mAlias) {
            synchronized (RemoteDevices.this.mObject) {
                this.mAlias = mAlias;
                RemoteDevices.sAdapterService.setDevicePropertyNative(this.mAddress, 10, mAlias.getBytes());
                Intent intent = new Intent("android.bluetooth.device.action.ALIAS_CHANGED");
                intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
                intent.putExtra("android.bluetooth.device.extra.NAME", mAlias);
                RemoteDevices.sAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
            }
        }

        void setBondState(int newBondState) {
            synchronized (RemoteDevices.this.mObject) {
                if ((this.mBondState == 12 && newBondState == 11) || newBondState == 10) {
                    this.mUuids = null;
                    this.mAlias = null;
                }
                this.mBondState = newBondState;
            }
        }

        int getBondState() {
            int i;
            synchronized (RemoteDevices.this.mObject) {
                i = this.mBondState;
            }
            return i;
        }

        boolean isBonding() {
            return getBondState() == 11;
        }

        boolean isBondingOrBonded() {
            return isBonding() || getBondState() == 12;
        }

        void setBondingInitiatedLocally(boolean isBondingInitiatedLocally) {
            synchronized (RemoteDevices.this.mObject) {
                this.mIsBondingInitiatedLocally = isBondingInitiatedLocally;
            }
        }

        boolean isBondingInitiatedLocally() {
            boolean z;
            synchronized (RemoteDevices.this.mObject) {
                z = this.mIsBondingInitiatedLocally;
            }
            return z;
        }

        int getBatteryLevel() {
            int i;
            synchronized (RemoteDevices.this.mObject) {
                i = this.mBatteryLevel;
            }
            return i;
        }

        void setBatteryLevel(int batteryLevel) {
            synchronized (RemoteDevices.this.mObject) {
                this.mBatteryLevel = batteryLevel;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUuidIntent(BluetoothDevice device, DeviceProperties prop) {
        Intent intent = new Intent("android.bluetooth.device.action.UUID");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.UUID", prop == null ? null : prop.mUuids);
        sAdapterService.sendBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
        sSdpTracker.remove(device);
    }

    void setBondingInitiatedLocally(byte[] address) {
        DeviceProperties properties;
        BluetoothDevice device = getDevice(address);
        if (device == null) {
            properties = addDeviceProperties(address);
        } else {
            properties = getDeviceProperties(device);
        }
        properties.setBondingInitiatedLocally(true);
    }

    void updateBatteryLevel(BluetoothDevice device, int batteryLevel) {
        if (device == null || batteryLevel < 0 || batteryLevel > 100) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid parameters device=");
            sb.append(String.valueOf(device == null));
            sb.append(", batteryLevel=");
            sb.append(String.valueOf(batteryLevel));
            warnLog(sb.toString());
            return;
        }
        DeviceProperties deviceProperties = getDeviceProperties(device);
        if (deviceProperties == null) {
            deviceProperties = addDeviceProperties(Utils.getByteAddress(device));
        }
        synchronized (this.mObject) {
            int currentBatteryLevel = deviceProperties.getBatteryLevel();
            if (batteryLevel == currentBatteryLevel) {
                debugLog("Same battery level for device " + device.getAddressOnlyforLog() + " received " + String.valueOf(batteryLevel) + "%");
                return;
            }
            deviceProperties.setBatteryLevel(batteryLevel);
            sendBatteryLevelChangedBroadcast(device, batteryLevel);
            Log.d(TAG, "Updated device " + device.getAddressOnlyforLog() + " battery level to " + batteryLevel + "%");
        }
    }

    void resetBatteryLevel(BluetoothDevice device) {
        if (device == null) {
            warnLog("Device is null");
            return;
        }
        DeviceProperties deviceProperties = getDeviceProperties(device);
        if (deviceProperties == null) {
            return;
        }
        synchronized (this.mObject) {
            if (deviceProperties.getBatteryLevel() == -1) {
                debugLog("Battery level was never set or is already reset, device=" + device.getAddressOnlyforLog());
                return;
            }
            deviceProperties.setBatteryLevel(-1);
            sendBatteryLevelChangedBroadcast(device, -1);
            Log.d(TAG, "Reset battery level, device=" + device.getAddressOnlyforLog());
        }
    }

    private void sendBatteryLevelChangedBroadcast(BluetoothDevice device, int batteryLevel) {
        Intent intent = new Intent("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.BATTERY_LEVEL", batteryLevel);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        intent.addFlags(16777216);
        sAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    private static boolean areUuidsEqual(ParcelUuid[] uuids1, ParcelUuid[] uuids2) {
        int length1 = uuids1 == null ? 0 : uuids1.length;
        int length2 = uuids2 == null ? 0 : uuids2.length;
        if (length1 != length2) {
            return false;
        }
        Set<ParcelUuid> set = new HashSet<>();
        for (int i = 0; i < length1; i++) {
            set.add(uuids1[i]);
        }
        for (int i2 = 0; i2 < length2; i2++) {
            set.remove(uuids2[i2]);
        }
        return set.isEmpty();
    }

    void devicePropertyChangedCallback(byte[] address, int[] types, byte[][] values) {
        DeviceProperties device;
        BluetoothDevice bdDevice = getDevice(address);
        if (bdDevice == null) {
            debugLog("Added new device property");
            device = addDeviceProperties(address);
            bdDevice = getDevice(address);
        } else {
            device = getDeviceProperties(bdDevice);
        }
        if (types.length <= 0) {
            errorLog("No properties to update");
            return;
        }
        sAdapterService.getName();
        for (int j = 0; j < types.length; j++) {
            int type = types[j];
            byte[] val = values[j];
            if (val.length > 0) {
                synchronized (this.mObject) {
                    debugLog("Property type: " + type);
                    if (type == 1) {
                        String newName = new String(val);
                        if (!newName.equals(device.mName)) {
                            device.mName = newName;
                            Intent intent = new Intent("android.bluetooth.device.action.NAME_CHANGED");
                            intent.putExtra("android.bluetooth.device.extra.DEVICE", bdDevice);
                            intent.putExtra("android.bluetooth.device.extra.NAME", device.mName);
                            intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                            sAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
                            debugLog("Remote Device name is: ***Aab" + device.mName);
                        } else {
                            debugLog("Skip name update for " + bdDevice.getAddressOnlyforLog());
                        }
                    } else if (type == 2) {
                        device.mAddress = val;
                    } else if (type == 3) {
                        int length = val.length / 16;
                        ParcelUuid[] newUuids = Utils.byteArrayToUuid(val);
                        if (areUuidsEqual(newUuids, device.mUuids)) {
                            debugLog("Skip uuids update for " + bdDevice.getAddressOnlyforLog());
                        } else {
                            device.mUuids = newUuids;
                            if (sAdapterService.getState() == 12) {
                                sAdapterService.deviceUuidUpdated(bdDevice);
                                sendUuidIntent(bdDevice, device);
                            }
                        }
                    } else if (type == 4) {
                        int newClass = Utils.byteArrayToInt(val);
                        if (newClass != device.mBluetoothClass) {
                            device.mBluetoothClass = Utils.byteArrayToInt(val);
                            Intent intent2 = new Intent("android.bluetooth.device.action.CLASS_CHANGED");
                            intent2.putExtra("android.bluetooth.device.extra.DEVICE", bdDevice);
                            intent2.putExtra("android.bluetooth.device.extra.CLASS", new BluetoothClass(device.mBluetoothClass));
                            intent2.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                            sAdapterService.sendBroadcast(intent2, ProfileService.BLUETOOTH_PERM);
                            debugLog("Remote class is:" + device.mBluetoothClass);
                        } else {
                            debugLog("Skip class update for " + bdDevice.getAddressOnlyforLog());
                        }
                    } else if (type == 5) {
                        device.mDeviceType = Utils.byteArrayToInt(val);
                    } else if (type == 10) {
                        device.mAlias = new String(val);
                        debugLog("Remote device alias is: " + device.mAlias);
                    } else if (type == 11) {
                        device.mRssi = val[0];
                    }
                }
            }
        }
    }

    void deviceFoundCallback(byte[] address) {
        BluetoothDevice device = getDevice(address);
        debugLog("deviceFoundCallback: Remote Address is:" + device.getAddressOnlyforLog());
        DeviceProperties deviceProp = getDeviceProperties(device);
        if (deviceProp == null) {
            errorLog("Device Properties is null for Device:" + device.getAddressOnlyforLog());
            return;
        }
        Intent intent = new Intent("android.bluetooth.device.action.FOUND");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.CLASS", new BluetoothClass(deviceProp.mBluetoothClass));
        intent.putExtra("android.bluetooth.device.extra.RSSI", deviceProp.mRssi);
        intent.putExtra("android.bluetooth.device.extra.NAME", deviceProp.mName);
        ArrayList<DiscoveringPackage> packages = sAdapterService.getDiscoveringPackages();
        synchronized (packages) {
            for (DiscoveringPackage pkg : packages) {
                intent.setPackage(pkg.getPackageName());
                sAdapterService.sendBroadcastMultiplePermissions(intent, new String[]{ProfileService.BLUETOOTH_PERM, pkg.getPermission()});
            }
        }
    }

    void aclStateChangeCallback(int status, byte[] address, int newState, int state_change_reason) {
        BluetoothDevice device = getDevice(address);
        if (device == null) {
            errorLog("aclStateChangeCallback: device is NULL, address=" + Utils.getAddressStringFromByte(address) + ", newState=" + newState);
            return;
        }
        int state = sAdapterService.getState();
        debugLog("aclStateChangeCallback:  newState=" + newState + "  state=" + state + " state_change_reason=" + state_change_reason);
        Utils.getAddressStringFromByte(address);
        Intent intent = null;
        if (newState == 0) {
            if (state == 12 || state == 11) {
                intent = new Intent("android.bluetooth.device.action.ACL_CONNECTED");
                if (sAdapterService.isCarPlayConnect()) {
                    int band = sAdapterService.getApConfigurationBand();
                    if (band == 1) {
                        sAdapterService.removeAcl(device, 1);
                        debugLog("cp is 2.4G ,disconnect acl");
                        return;
                    }
                }
            } else if (state == 15 || state == 14) {
                intent = new Intent("android.bluetooth.adapter.action.BLE_ACL_CONNECTED");
            }
            debugLog("aclStateChangeCallback: Adapter State: " + BluetoothAdapter.nameForState(state) + " Connected: " + device.getAddressOnlyforLog());
        } else {
            if (device.getBondState() == 11) {
                intent = new Intent("android.bluetooth.device.action.PAIRING_CANCEL");
                intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
                intent.setPackage(sAdapterService.getString(R.string.pairing_ui_package));
                sAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
            }
            if (state == 12 || state == 13) {
                intent = new Intent("android.bluetooth.device.action.ACL_DISCONNECTED");
                if (state_change_reason == 2 || state_change_reason == 8) {
                    Intent linkLoss = new Intent(Constant.LINK_LOSS_DETECTED);
                    linkLoss.putExtra("android.bluetooth.device.extra.DEVICE", device);
                    sAdapterService.sendBroadcast(linkLoss, ProfileService.BLUETOOTH_PERM);
                }
            } else if (state == 15 || state == 16) {
                intent = new Intent("android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED");
            }
            if (sAdapterService.getConnectionState(device) == 0) {
                resetBatteryLevel(device);
            }
            debugLog("aclStateChangeCallback: Adapter State: " + BluetoothAdapter.nameForState(state) + " Disconnected: " + device.getAddressOnlyforLog());
        }
        int connectionState = newState != 0 ? 0 : 2;
        int metricId = sAdapterService.getMetricId(device);
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_ACL_CONNECTION_STATE_CHANGED, sAdapterService.obfuscateAddress(device), connectionState, metricId);
        BluetoothClass deviceClass = device.getBluetoothClass();
        int classOfDevice = deviceClass != null ? deviceClass.getClassOfDevice() : 0;
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_CLASS_OF_DEVICE_REPORTED, sAdapterService.obfuscateAddress(device), classOfDevice, metricId);
        if (intent != null) {
            intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
            intent.putExtra("android.bluetooth.device.extra.REASON", state_change_reason);
            intent.addFlags(83886080);
            intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
            sAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
            return;
        }
        Log.e(TAG, "aclStateChangeCallback intent is null. deviceBondState: " + device.getBondState());
    }

    void fetchUuids(BluetoothDevice device) {
        if (sSdpTracker.contains(device)) {
            return;
        }
        sSdpTracker.add(device);
        Message message = this.mHandler.obtainMessage(1);
        message.obj = device;
        this.mHandler.sendMessageDelayed(message, 6000L);
        sAdapterService.getRemoteServicesNative(Utils.getBytesFromAddress(device.getAddress()));
    }

    void updateUuids(BluetoothDevice device) {
        Message message = this.mHandler.obtainMessage(1);
        message.obj = device;
        this.mHandler.sendMessage(message);
    }

    void onHeadsetConnectionStateChanged(Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (device == null) {
            Log.e(TAG, "onHeadsetConnectionStateChanged() remote device is null");
        } else if (intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0) == 0) {
            resetBatteryLevel(device);
        }
    }

    void onHfIndicatorValueChanged(Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (device == null) {
            Log.e(TAG, "onHfIndicatorValueChanged() remote device is null");
            return;
        }
        int indicatorId = intent.getIntExtra("android.bluetooth.headset.extra.HF_INDICATORS_IND_ID", -1);
        int indicatorValue = intent.getIntExtra("android.bluetooth.headset.extra.HF_INDICATORS_IND_VALUE", -1);
        if (indicatorId == 2) {
            updateBatteryLevel(device, indicatorValue);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void onVendorSpecificHeadsetEvent(android.content.Intent r10) {
        /*
            r9 = this;
            java.lang.String r0 = "android.bluetooth.device.extra.DEVICE"
            android.os.Parcelable r0 = r10.getParcelableExtra(r0)
            android.bluetooth.BluetoothDevice r0 = (android.bluetooth.BluetoothDevice) r0
            java.lang.String r1 = "BluetoothRemoteDevices"
            if (r0 != 0) goto L12
            java.lang.String r2 = "onVendorSpecificHeadsetEvent() remote device is null"
            android.util.Log.e(r1, r2)
            return
        L12:
            java.lang.String r2 = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD"
            java.lang.String r2 = r10.getStringExtra(r2)
            if (r2 != 0) goto L21
            java.lang.String r3 = "onVendorSpecificHeadsetEvent() command is null"
            android.util.Log.e(r1, r3)
            return
        L21:
            java.lang.String r3 = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE"
            r4 = -1
            int r3 = r10.getIntExtra(r3, r4)
            r5 = 2
            if (r3 == r5) goto L32
            java.lang.String r1 = "onVendorSpecificHeadsetEvent() only SET command is processed"
            debugLog(r1)
            return
        L32:
            android.os.Bundle r5 = r10.getExtras()
            java.lang.String r6 = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS"
            java.lang.Object r5 = r5.get(r6)
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            if (r5 != 0) goto L46
            java.lang.String r4 = "onVendorSpecificHeadsetEvent() arguments are null"
            android.util.Log.e(r1, r4)
            return
        L46:
            r1 = -1
            int r6 = r2.hashCode()
            r7 = 1884621890(0x70550c42, float:2.637405E29)
            r8 = 1
            if (r6 == r7) goto L61
            r7 = 2093671693(0x7ccae50d, float:8.4279173E36)
            if (r6 == r7) goto L57
        L56:
            goto L6b
        L57:
            java.lang.String r6 = "+XEVENT"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L56
            r6 = 0
            goto L6c
        L61:
            java.lang.String r6 = "+IPHONEACCEV"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L56
            r6 = r8
            goto L6c
        L6b:
            r6 = r4
        L6c:
            if (r6 == 0) goto L76
            if (r6 == r8) goto L71
            goto L7b
        L71:
            int r1 = getBatteryLevelFromAppleBatteryVsc(r5)
            goto L7b
        L76:
            int r1 = getBatteryLevelFromXEventVsc(r5)
        L7b:
            if (r1 == r4) goto La9
            r9.updateBatteryLevel(r0, r1)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Updated device "
            r4.append(r6)
            java.lang.String r6 = r0.getAddressOnlyforLog()
            r4.append(r6)
            java.lang.String r6 = " battery level to "
            r4.append(r6)
            java.lang.String r6 = java.lang.String.valueOf(r1)
            r4.append(r6)
            java.lang.String r6 = "%"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            infoLog(r4)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.btservice.RemoteDevices.onVendorSpecificHeadsetEvent(android.content.Intent):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        if (r3 < 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        if (r3 <= 9) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006b, code lost:
    
        return (r3 + 1) * 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int getBatteryLevelFromAppleBatteryVsc(java.lang.Object[] r9) {
        /*
            int r0 = r9.length
            java.lang.String r1 = "BluetoothRemoteDevices"
            r2 = -1
            if (r0 != 0) goto Lc
            java.lang.String r0 = "getBatteryLevelFromAppleBatteryVsc() empty arguments"
            android.util.Log.w(r1, r0)
            return r2
        Lc:
            r0 = 0
            r3 = r9[r0]
            boolean r3 = r3 instanceof java.lang.Integer
            if (r3 == 0) goto L6c
            r0 = r9[r0]
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            int r3 = r9.length
            int r4 = r0 * 2
            r5 = 1
            int r4 = r4 + r5
            if (r3 == r4) goto L28
            java.lang.String r3 = "getBatteryLevelFromAppleBatteryVsc() number of arguments does not match"
            android.util.Log.w(r1, r3)
            return r2
        L28:
            r3 = -1
            r4 = 0
        L2a:
            if (r4 >= r0) goto L60
            int r6 = r4 * 2
            int r6 = r6 + r5
            r6 = r9[r6]
            boolean r7 = r6 instanceof java.lang.Integer
            if (r7 == 0) goto L5a
            r7 = r6
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r7 == r5) goto L42
        L3f:
            int r4 = r4 + 1
            goto L2a
        L42:
            int r5 = r4 * 2
            int r5 = r5 + 2
            r5 = r9[r5]
            boolean r8 = r5 instanceof java.lang.Integer
            if (r8 == 0) goto L54
            r1 = r5
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r3 = r1.intValue()
            goto L60
        L54:
            java.lang.String r8 = "getBatteryLevelFromAppleBatteryVsc() error parsing indicator value"
            android.util.Log.w(r1, r8)
            return r2
        L5a:
            java.lang.String r5 = "getBatteryLevelFromAppleBatteryVsc() error parsing indicator type"
            android.util.Log.w(r1, r5)
            return r2
        L60:
            if (r3 < 0) goto L6b
            r1 = 9
            if (r3 <= r1) goto L67
            goto L6b
        L67:
            int r1 = r3 + 1
            int r2 = r1 * 10
        L6b:
            return r2
        L6c:
            java.lang.String r0 = "getBatteryLevelFromAppleBatteryVsc() error parsing number of arguments"
            android.util.Log.w(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.btservice.RemoteDevices.getBatteryLevelFromAppleBatteryVsc(java.lang.Object[]):int");
    }

    static int getBatteryLevelFromXEventVsc(Object[] args) {
        if (args.length == 0) {
            Log.w(TAG, "getBatteryLevelFromXEventVsc() empty arguments");
            return -1;
        }
        Object eventNameObj = args[0];
        if (!(eventNameObj instanceof String)) {
            Log.w(TAG, "getBatteryLevelFromXEventVsc() error parsing event name");
            return -1;
        }
        String eventName = (String) eventNameObj;
        if (!eventName.equals("BATTERY")) {
            infoLog("getBatteryLevelFromXEventVsc() skip none BATTERY event: " + eventName);
            return -1;
        }
        if (args.length != 5) {
            Log.w(TAG, "getBatteryLevelFromXEventVsc() wrong battery level event length: " + String.valueOf(args.length));
            return -1;
        }
        if (!(args[1] instanceof Integer) || !(args[2] instanceof Integer)) {
            Log.w(TAG, "getBatteryLevelFromXEventVsc() error parsing event values");
            return -1;
        }
        int batteryLevel = ((Integer) args[1]).intValue();
        int numberOfLevels = ((Integer) args[2]).intValue();
        if (batteryLevel < 0 || numberOfLevels <= 1 || batteryLevel > numberOfLevels) {
            Log.w(TAG, "getBatteryLevelFromXEventVsc() wrong event value, batteryLevel=" + String.valueOf(batteryLevel) + ", numberOfLevels=" + String.valueOf(numberOfLevels));
            return -1;
        }
        return (batteryLevel * 100) / (numberOfLevels - 1);
    }

    private static void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    private static void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    private static void infoLog(String msg) {
        Log.i(TAG, msg);
    }

    private static void warnLog(String msg) {
        Log.w(TAG, msg);
    }
}
