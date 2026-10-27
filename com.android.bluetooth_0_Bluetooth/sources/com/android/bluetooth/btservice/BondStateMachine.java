package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.OobData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.INetd;
import android.os.Build;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.a2dpsink.A2dpSinkService;
import com.android.bluetooth.btservice.RemoteDevices;
import com.android.bluetooth.hfp.HeadsetService;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import com.android.bluetooth.hid.HidHostService;
import com.android.bluetooth.pbapclient.PbapClientService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class BondStateMachine extends StateMachine {
    private static final String ACTION_MAX_BONDED_DEVICE = "android.bluetooth.action.MAX_PAIRED_DEVICE";
    static final int ADD_OOB_BOND_DEVICE = 240;
    static final int BONDING_STATE_CHANGE = 4;
    private static final String BOND_DEVICES_LIST = "bond_devices_file";
    private static final String BOND_DEVICES_LIST_KEY = "bond_devices_key";
    private static final String BOND_DEVICE_ADDRESS = "address";
    private static final String BOND_DEVICE_LAST_BOND_TIME = "lastbondtime";
    static final int BOND_STATE_BONDED = 2;
    static final int BOND_STATE_BONDING = 1;
    static final int BOND_STATE_NONE = 0;
    static final int CANCEL_BOND = 2;
    static final int CREATE_BOND = 1;
    private static final boolean DBG = true;
    private static final int MAX_BOND_DEVICE_SIZE = 21;
    public static final String OOBDATA = "oobdata";
    static final int PIN_REQUEST = 6;
    static final int REMOVE_BOND = 3;
    static final int SSP_REQUEST = 5;
    private static final String TAG = "BluetoothBondStateMachine";
    static final int UUID_UPDATE = 10;
    private BluetoothAdapter mAdapter;
    private AdapterProperties mAdapterProperties;
    private AdapterService mAdapterService;
    Set<BluetoothDevice> mPendingBondedDevices;
    private PendingCommandState mPendingCommandState;
    private SharedPreferences mPrefs;
    private RemoteDevices mRemoteDevices;
    private StableState mStableState;

    private BondStateMachine(AdapterService service, AdapterProperties prop, RemoteDevices remoteDevices) {
        super("BondStateMachine:");
        this.mPendingCommandState = new PendingCommandState();
        this.mStableState = new StableState();
        this.mPendingBondedDevices = new HashSet();
        addState(this.mStableState);
        addState(this.mPendingCommandState);
        this.mRemoteDevices = remoteDevices;
        this.mAdapterService = service;
        this.mAdapterProperties = prop;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        setInitialState(this.mStableState);
        this.mPrefs = getBondMap();
    }

    public static BondStateMachine make(AdapterService service, AdapterProperties prop, RemoteDevices remoteDevices) {
        Log.d(TAG, "make");
        BondStateMachine bsm = new BondStateMachine(service, prop, remoteDevices);
        bsm.start();
        return bsm;
    }

    public synchronized void doQuit() {
        quitNow();
    }

    private void cleanup() {
        this.mAdapterService = null;
        this.mRemoteDevices = null;
        this.mAdapterProperties = null;
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void onQuitting() {
        cleanup();
    }

    private class StableState extends State {
        private StableState() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            BondStateMachine.this.infoLog("StableState(): Entering Off State");
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public synchronized boolean processMessage(Message msg) {
            BluetoothDevice dev = (BluetoothDevice) msg.obj;
            BondStateMachine.this.logd("StableState msg.what: " + msg.what);
            int i = msg.what;
            if (i == 1) {
                OobData oobData = null;
                if (msg.getData() != null) {
                    oobData = (OobData) msg.getData().getParcelable(BondStateMachine.OOBDATA);
                }
                BondStateMachine.this.createBond(dev, msg.arg1, oobData, true);
            } else if (i != 10) {
                if (i == BondStateMachine.ADD_OOB_BOND_DEVICE) {
                    BondStateMachine.this.addOutOfBandBondDevice(dev, msg);
                } else if (i == 3) {
                    BondStateMachine.this.removeBond(dev, true);
                } else if (i == 4) {
                    int newState = msg.arg1;
                    if (newState == 11) {
                        BondStateMachine.this.sendIntent(dev, newState, 0);
                        BondStateMachine.this.transitionTo(BondStateMachine.this.mPendingCommandState);
                    } else if (newState != 10) {
                        Log.e(BondStateMachine.TAG, "In stable state, received invalid newState: " + BondStateMachine.this.state2str(newState));
                    } else {
                        BondStateMachine.this.sendIntent(dev, newState, 0);
                    }
                } else {
                    Log.e(BondStateMachine.TAG, "Received unhandled state: " + msg.what);
                    return false;
                }
            } else if (BondStateMachine.this.mPendingBondedDevices.contains(dev)) {
                BondStateMachine.this.sendIntent(dev, 12, 0);
            }
            return true;
        }
    }

    private class PendingCommandState extends State {
        private final ArrayList<BluetoothDevice> mDevices;

        private PendingCommandState() {
            this.mDevices = new ArrayList<>();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            BondStateMachine.this.infoLog("Entering PendingCommandState State");
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public synchronized boolean processMessage(Message msg) {
            BluetoothDevice dev = (BluetoothDevice) msg.obj;
            BondStateMachine.this.logd("PendingState  mDevices: " + this.mDevices + " dev:" + dev.getAddressOnlyforLog() + " what: " + msg.what);
            RemoteDevices.DeviceProperties devProp = BondStateMachine.this.mRemoteDevices.getDeviceProperties(dev);
            boolean result = false;
            if (this.mDevices.contains(dev) && msg.what != 2 && msg.what != 4 && msg.what != 5 && msg.what != 6) {
                BondStateMachine.this.deferMessage(msg);
                return true;
            }
            BondStateMachine.this.logd("PendingCommandState  what: " + msg.what);
            int i = msg.what;
            if (i == BondStateMachine.ADD_OOB_BOND_DEVICE) {
                BondStateMachine.this.addOutOfBandBondDevice(dev, msg);
            } else {
                switch (i) {
                    case 1:
                        OobData oobData = null;
                        if (msg.getData() != null) {
                            oobData = (OobData) msg.getData().getParcelable(BondStateMachine.OOBDATA);
                        }
                        result = BondStateMachine.this.createBond(dev, msg.arg1, oobData, false);
                        break;
                    case 2:
                        result = BondStateMachine.this.cancelBond(dev);
                        break;
                    case 3:
                        result = BondStateMachine.this.removeBond(dev, false);
                        break;
                    case 4:
                        int passkey = msg.arg1;
                        int reason = BondStateMachine.this.getUnbondReasonFromHALCode(msg.arg2);
                        if (passkey == 10 && reason == 0) {
                            reason = 9;
                        }
                        BondStateMachine.this.sendIntent(dev, passkey, reason);
                        if (passkey != 11) {
                            result = !this.mDevices.remove(dev);
                            if (this.mDevices.isEmpty()) {
                                result = false;
                                BondStateMachine.this.transitionTo(BondStateMachine.this.mStableState);
                            }
                            if (passkey == 10) {
                                BondStateMachine.this.mAdapterService.setPhonebookAccessPermission(dev, 0);
                                BondStateMachine.this.mAdapterService.setMessageAccessPermission(dev, 0);
                                BondStateMachine.this.mAdapterService.setSimAccessPermission(dev, 0);
                                BondStateMachine.this.clearProfilePriority(dev);
                            }
                        } else if (!this.mDevices.contains(dev)) {
                            result = true;
                        }
                        break;
                    case 5:
                        int passkey2 = msg.arg1;
                        int variant = msg.arg2;
                        BondStateMachine.this.sendDisplayPinIntent(devProp.getAddress(), passkey2, variant);
                        break;
                    case 6:
                        BluetoothClass btClass = dev.getBluetoothClass();
                        int btDeviceClass = btClass.getDeviceClass();
                        if (btDeviceClass == 1344 || btDeviceClass == 1472) {
                            int pin = ((int) Math.floor(Math.random() * 899999.0d)) + 100000;
                            BondStateMachine.this.sendDisplayPinIntent(devProp.getAddress(), pin, 5);
                        } else if (msg.arg2 == 1) {
                            BondStateMachine.this.sendDisplayPinIntent(devProp.getAddress(), 0, 7);
                        } else {
                            BondStateMachine.this.sendDisplayPinIntent(devProp.getAddress(), 0, 0);
                        }
                        break;
                    default:
                        Log.e(BondStateMachine.TAG, "Received unhandled event:" + msg.what);
                        return false;
                }
            }
            if (result) {
                this.mDevices.add(dev);
                BondStateMachine.this.mAdapterService.setPhonebookAccessPermission(dev, 2);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cancelBond(BluetoothDevice dev) {
        if (dev.getBondState() == 11) {
            byte[] addr = Utils.getBytesFromAddress(dev.getAddress());
            if (!this.mAdapterService.cancelBondNative(addr)) {
                Log.e(TAG, "Unexpected error while cancelling bond:");
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeBond(BluetoothDevice dev, boolean transition) {
        logd("removeBond bondState: " + dev.getBondState());
        if (dev.getBondState() == 12) {
            byte[] addr = Utils.getBytesFromAddress(dev.getAddress());
            if (!this.mAdapterService.removeBondNative(addr)) {
                Log.e(TAG, "Unexpected error while removing bond:");
                return false;
            }
            if (transition) {
                transitionTo(this.mPendingCommandState);
            }
            removeBondFromSp(dev);
            return true;
        }
        return false;
    }

    private void removeBondFromSp(BluetoothDevice dev) {
        String devicesListStr = this.mPrefs.getString(BOND_DEVICES_LIST_KEY, INetd.NEXTHOP_NONE);
        if (devicesListStr != null && !devicesListStr.isEmpty()) {
            int index = -1;
            try {
                JSONArray array = new JSONArray(devicesListStr);
                int i = 0;
                while (true) {
                    if (i >= array.length()) {
                        break;
                    }
                    JSONObject object = (JSONObject) array.get(i);
                    String deleteAddress = object.optString(BOND_DEVICE_ADDRESS);
                    if (!deleteAddress.equals(dev.getAddress())) {
                        i++;
                    } else {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    array.remove(index);
                    SharedPreferences.Editor editor = this.mPrefs.edit();
                    editor.putString(BOND_DEVICES_LIST_KEY, array.toString());
                    editor.apply();
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void checkBond(BluetoothDevice dev) {
        errorLog("checkBond dev: " + dev.getAddressOnlyforLog() + "; BondState: " + dev.getBondState());
        AdapterService adapterService = this.mAdapterService;
        if (adapterService != null) {
            BluetoothDevice[] bondedDevices = adapterService.getBondedDevices();
            errorLog("checkBond bondedDevices.length: " + bondedDevices.length);
            if (bondedDevices.length > 21) {
                SharedPreferences sharedPreferences = this.mPrefs;
                String deleteAddress = INetd.NEXTHOP_NONE;
                String devicesListStr = sharedPreferences.getString(BOND_DEVICES_LIST_KEY, INetd.NEXTHOP_NONE);
                errorLog("checkBond devicesListStr: " + devicesListStr);
                if (devicesListStr != null && !devicesListStr.isEmpty()) {
                    int index = -1;
                    try {
                        long tempTime = System.currentTimeMillis();
                        JSONArray array = new JSONArray(devicesListStr);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = (JSONObject) array.get(i);
                            long lastBondTime = object.optLong(BOND_DEVICE_LAST_BOND_TIME);
                            if (lastBondTime < tempTime) {
                                index = i;
                                tempTime = lastBondTime;
                                deleteAddress = object.optString(BOND_DEVICE_ADDRESS);
                            }
                        }
                        if (index != -1) {
                            for (BluetoothDevice bondedDevice : bondedDevices) {
                                if (bondedDevice.getAddress().equals(deleteAddress)) {
                                    errorLog("removeBond dev: " + bondedDevice.getAddressOnlyforLog());
                                    sendMaxBondedIntent(bondedDevice);
                                    return;
                                }
                            }
                        }
                    } catch (JSONException exception) {
                        exception.printStackTrace();
                    }
                }
                for (BluetoothDevice bondedDevice2 : bondedDevices) {
                    if (!bondedDevice2.getAddress().equals(dev.getAddress())) {
                        errorLog("checkBond, to removeBond dev: " + bondedDevice2.getAddressOnlyforLog());
                        sendMaxBondedIntent(bondedDevice2);
                        return;
                    }
                }
            }
        }
    }

    private void sendMaxBondedIntent(BluetoothDevice device) {
        warnLog("sendMaxBondedIntent removeBond dev: " + device.getAddressOnlyforLog());
        Intent intent = new Intent(ACTION_MAX_BONDED_DEVICE);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        this.mAdapterService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean createBond(BluetoothDevice dev, int transport, OobData oobData, boolean transition) {
        boolean result;
        if (dev.getBondState() != 10) {
            return false;
        }
        infoLog("Bond address is:" + dev);
        byte[] addr = Utils.getBytesFromAddress(dev.getAddress());
        if (oobData != null) {
            if (oobData.getC192() != null && oobData.getR192() != null) {
                infoLog(" remote c192 is: " + Utils.byteArrayToString(oobData.getC192()) + "\n remote r192 is: " + Utils.byteArrayToString(oobData.getR192()));
            }
            if (oobData.getC256() != null && oobData.getR256() != null) {
                infoLog(" remote c256 is: " + Utils.byteArrayToString(oobData.getC256()) + "\n remote r256 is: " + Utils.byteArrayToString(oobData.getR256()));
            }
            result = this.mAdapterService.createBondOutOfBandNative(addr, transport, oobData);
        } else {
            result = this.mAdapterService.createBondNative(addr, transport);
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, this.mAdapterService.obfuscateAddress(dev), transport, dev.getType(), 11, oobData == null ? 0 : 1, 0);
        if (!result) {
            BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, this.mAdapterService.obfuscateAddress(dev), transport, dev.getType(), 10, 0, 7);
            sendIntent(dev, 10, 9);
            return false;
        }
        if (transition) {
            transitionTo(this.mPendingCommandState);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addOutOfBandBondDevice(BluetoothDevice dev, Message msg) {
        String linkKey = msg.getData().getString("linkKey");
        return addOutOfBandBondDevice(dev, linkKey, msg.arg1, msg.arg2, true);
    }

    private boolean addOutOfBandBondDevice(BluetoothDevice dev, String linkKey, int linkKeyType, int pinLen, boolean transition) {
        if (this.mAdapterService == null) {
            return false;
        }
        infoLog("addOutOfBandBondDevice");
        if (dev.getBondState() == 10) {
            byte[] addr = Utils.getBytesFromAddress(dev.getAddress());
            byte[] key = getByteFromLinkkey(linkKey);
            if (key == null) {
                errorLog("non-numerical characters are not allowed in linkkey");
            } else {
                boolean result = this.mAdapterService.addOutOfBandBondDeviceNative(addr, key, linkKeyType, pinLen);
                if (result) {
                    if (transition) {
                        transitionTo(this.mPendingCommandState);
                    }
                    infoLog("LinkKey " + linkKey + "keytype " + linkKeyType);
                    return true;
                }
            }
        }
        sendIntent(dev, 10, 9);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDisplayPinIntent(byte[] address, int pin, int variant) {
        Intent intent = new Intent("android.bluetooth.device.action.PAIRING_REQUEST");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mRemoteDevices.getDevice(address));
        if (pin != 0) {
            intent.putExtra("android.bluetooth.device.extra.PAIRING_KEY", pin);
        }
        intent.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", variant);
        intent.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        intent.addFlags(16777216);
        this.mAdapterService.sendOrderedBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
    }

    private byte[] getByteFromLinkkey(String linkKey) {
        int j = 0;
        Math.min(linkKey.length(), 16);
        byte[] output = new byte[16];
        for (int i = 0; i < linkKey.length(); i = i + 1 + 1) {
            try {
                output[j] = (byte) Integer.parseInt(linkKey.substring(i, i + 2), 16);
                j++;
            } catch (NumberFormatException e) {
                Log.e(TAG, "getByteFromLinkkey Exception: NumberFormatException");
                return null;
            }
        }
        return output;
    }

    void sendIntent(BluetoothDevice device, int newState, int reason) {
        int oldState;
        int newState2;
        RemoteDevices.DeviceProperties devProp = this.mRemoteDevices.getDeviceProperties(device);
        int oldState2 = 10;
        if (newState != 10 && newState != 11 && newState != 12) {
            infoLog("Invalid bond state " + newState);
            return;
        }
        if (devProp != null) {
            oldState2 = devProp.getBondState();
        }
        if (!this.mPendingBondedDevices.contains(device)) {
            oldState = oldState2;
        } else {
            this.mPendingBondedDevices.remove(device);
            if (oldState2 == 12) {
                if (newState == 11) {
                    this.mAdapterProperties.onBondStateChanged(device, newState);
                }
                oldState = 11;
            } else {
                throw new IllegalArgumentException("Invalid old state " + oldState2);
            }
        }
        if (oldState == newState) {
            return;
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, this.mAdapterService.obfuscateAddress(device), 0, device.getType(), newState, 0, reason, this.mAdapterService.getMetricId(device));
        BluetoothClass deviceClass = device.getBluetoothClass();
        int classOfDevice = deviceClass == null ? 0 : deviceClass.getClassOfDevice();
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_CLASS_OF_DEVICE_REPORTED, this.mAdapterService.obfuscateAddress(device), classOfDevice, this.mAdapterService.getMetricId(device));
        this.mAdapterProperties.onBondStateChanged(device, newState);
        if (devProp != null && ((devProp.getDeviceType() == 1 || devProp.getDeviceType() == 3) && newState == 12 && devProp.getUuids() == null)) {
            infoLog(device + " is bonded, wait for SDP complete to broadcast bonded intent");
            if (!this.mPendingBondedDevices.contains(device)) {
                this.mPendingBondedDevices.add(device);
            }
            if (oldState == 10) {
                newState2 = 11;
            } else {
                return;
            }
        } else {
            newState2 = newState;
        }
        if (newState2 == 12) {
            saveBondDeviceTime(device);
            this.mAdapterService.setPhonebookAccessPermission(device, 2);
            Log.d(TAG, "close PBAP permission after pairing DEVICE :" + device.getAddressOnlyforLog() + "permission :" + this.mAdapterService.getDeviceAccessFromPrefs(device, "phonebook_access_permission"));
            checkBond(device);
        }
        Intent intent = new Intent("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.BOND_STATE", newState2);
        intent.putExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", oldState);
        if (newState2 == 10) {
            intent.putExtra("android.bluetooth.device.extra.REASON", reason);
        }
        this.mAdapterService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
        infoLog("Bond State Change Intent:" + device.getAddressOnlyforLog() + " " + state2str(oldState) + " => " + state2str(newState2));
    }

    private void saveBondDeviceTime(BluetoothDevice device) {
        String devicesListStr = this.mPrefs.getString(BOND_DEVICES_LIST_KEY, INetd.NEXTHOP_NONE);
        if (devicesListStr != null && !devicesListStr.isEmpty()) {
            int index = -1;
            try {
                JSONArray array = new JSONArray(devicesListStr);
                int i = 0;
                while (true) {
                    if (i >= array.length()) {
                        break;
                    }
                    JSONObject object = (JSONObject) array.get(i);
                    String address = object.optString(BOND_DEVICE_ADDRESS);
                    if (!device.getAddress().equals(address)) {
                        i++;
                    } else {
                        index = i;
                        object.put(BOND_DEVICE_LAST_BOND_TIME, System.currentTimeMillis());
                        array.put(i, object);
                        break;
                    }
                }
                if (index == -1) {
                    JSONObject object2 = new JSONObject();
                    object2.put(BOND_DEVICE_ADDRESS, device.getAddress());
                    object2.put(BOND_DEVICE_LAST_BOND_TIME, System.currentTimeMillis());
                    array.put(object2);
                }
                SharedPreferences.Editor editor = this.mPrefs.edit();
                editor.putString(BOND_DEVICES_LIST_KEY, array.toString());
                editor.apply();
                return;
            } catch (JSONException exception) {
                exception.printStackTrace();
                return;
            }
        }
        try {
            JSONArray array2 = new JSONArray();
            JSONObject object3 = new JSONObject();
            object3.put(BOND_DEVICE_ADDRESS, device.getAddress());
            object3.put(BOND_DEVICE_LAST_BOND_TIME, System.currentTimeMillis());
            array2.put(object3);
            SharedPreferences.Editor editor2 = this.mPrefs.edit();
            editor2.putString(BOND_DEVICES_LIST_KEY, array2.toString());
            editor2.apply();
        } catch (JSONException exception2) {
            exception2.printStackTrace();
        }
    }

    private SharedPreferences getBondMap() {
        return this.mAdapterService.getApplicationContext().getSharedPreferences(BOND_DEVICES_LIST, 0);
    }

    void bondStateChangeCallback(int status, byte[] address, int newState) {
        BluetoothDevice device = this.mRemoteDevices.getDevice(address);
        if (device == null) {
            infoLog("No record of the device:" + device);
            device = this.mAdapter.getRemoteDevice(Utils.getAddressStringFromByte(address));
        }
        infoLog("bondStateChangeCallback: Status: " + status + " Address: " + device.getAddressOnlyforLog() + " newState: " + newState);
        Message msg = obtainMessage(4);
        msg.obj = device;
        if (newState == 2) {
            msg.arg1 = 12;
        } else if (newState == 1) {
            msg.arg1 = 11;
        } else {
            msg.arg1 = 10;
        }
        msg.arg2 = status;
        sendMessage(msg);
    }

    void sspRequestCallback(byte[] address, byte[] name, int cod, int pairingVariant, int passkey) {
        int variant;
        BluetoothDevice bdDevice = this.mRemoteDevices.getDevice(address);
        if (bdDevice == null) {
            this.mRemoteDevices.addDeviceProperties(address);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sspRequestCallback: name: ");
        sb.append(name);
        sb.append(" cod: ");
        sb.append(cod);
        sb.append(" pairingVariant ");
        sb.append(pairingVariant);
        sb.append(" passkey: ");
        sb.append(Build.IS_DEBUGGABLE ? Integer.valueOf(passkey) : "******");
        infoLog(sb.toString());
        boolean displayPasskey = false;
        if (pairingVariant == 0) {
            variant = 2;
            displayPasskey = true;
        } else if (pairingVariant == 1) {
            variant = 1;
        } else if (pairingVariant == 2) {
            variant = 3;
        } else if (pairingVariant != 3) {
            errorLog("SSP Pairing variant not present");
            return;
        } else {
            variant = 4;
            displayPasskey = true;
        }
        BluetoothDevice device = this.mRemoteDevices.getDevice(address);
        if (device == null) {
            warnLog("Device is not known for:" + Utils.getAddressStringFromByte(address));
            this.mRemoteDevices.addDeviceProperties(address);
            BluetoothDevice device2 = this.mRemoteDevices.getDevice(address);
            Objects.requireNonNull(device2);
            device = device2;
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, this.mAdapterService.obfuscateAddress(device), 0L, device.getType(), 11, 4, 0);
        Message msg = obtainMessage(5);
        msg.obj = device;
        if (displayPasskey) {
            msg.arg1 = passkey;
        }
        msg.arg2 = variant;
        sendMessage(msg);
    }

    void pinRequestCallback(byte[] bArr, byte[] bArr2, int i, boolean z) {
        BluetoothDevice device = this.mRemoteDevices.getDevice(bArr);
        if (device == null) {
            this.mRemoteDevices.addDeviceProperties(bArr);
            BluetoothDevice device2 = this.mRemoteDevices.getDevice(bArr);
            Objects.requireNonNull(device2);
            device = device2;
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, this.mAdapterService.obfuscateAddress(device), 0L, device.getType(), 11, 2, 0);
        infoLog("pinRequestCallback: " + device.getAddressOnlyforLog() + " name:" + device.getName() + " cod:" + new BluetoothClass(i));
        Message messageObtainMessage = obtainMessage(6);
        messageObtainMessage.obj = device;
        messageObtainMessage.arg2 = z ? 1 : 0;
        sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProfilePriority(BluetoothDevice device) {
        HidHostService hidService = HidHostService.getHidHostService();
        A2dpService a2dpService = A2dpService.getA2dpService();
        HeadsetService headsetService = HeadsetService.getHeadsetService();
        HeadsetClientService headsetClientService = HeadsetClientService.getHeadsetClientService();
        A2dpSinkService a2dpSinkService = A2dpSinkService.getA2dpSinkService();
        PbapClientService pbapClientService = PbapClientService.getPbapClientService();
        if (hidService != null) {
            hidService.setConnectionPolicy(device, -1);
        }
        if (a2dpService != null) {
            a2dpService.setConnectionPolicy(device, -1);
        }
        if (headsetService != null) {
            headsetService.setConnectionPolicy(device, -1);
        }
        if (headsetClientService != null) {
            headsetClientService.setConnectionPolicy(device, -1);
        }
        if (a2dpSinkService != null) {
            a2dpSinkService.setConnectionPolicy(device, -1);
        }
        if (pbapClientService != null) {
            pbapClientService.setConnectionPolicy(device, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String state2str(int state) {
        if (state == 10) {
            return "BOND_NONE";
        }
        if (state == 11) {
            return "BOND_BONDING";
        }
        if (state == 12) {
            return "BOND_BONDED";
        }
        return "UNKNOWN(" + state + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void infoLog(String msg) {
        Log.i(TAG, msg);
    }

    private void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    private void warnLog(String msg) {
        Log.w(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getUnbondReasonFromHALCode(int reason) {
        if (reason == 0) {
            return 0;
        }
        if (reason == 10) {
            return 4;
        }
        if (reason == 9) {
            return 1;
        }
        if (reason == 11) {
            return 2;
        }
        if (reason != 12) {
            return 9;
        }
        return 6;
    }
}
