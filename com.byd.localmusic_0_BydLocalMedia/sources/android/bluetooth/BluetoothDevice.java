package android.bluetooth;

import android.annotation.SystemApi;
import android.app.PropertyInvalidatedCache;
import android.bluetooth.IBluetoothManagerCallback;
import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothDevice implements Parcelable {

    @SystemApi
    public static final int ACCESS_ALLOWED = 1;

    @SystemApi
    public static final int ACCESS_REJECTED = 2;

    @SystemApi
    public static final int ACCESS_UNKNOWN = 0;
    public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";
    public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";
    public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";
    public static final String ACTION_ALIAS_CHANGED = "android.bluetooth.device.action.ALIAS_CHANGED";
    public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED";
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    public static final String ACTION_CLASS_CHANGED = "android.bluetooth.device.action.CLASS_CHANGED";
    public static final String ACTION_CONNECTION_ACCESS_CANCEL = "android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL";
    public static final String ACTION_CONNECTION_ACCESS_REPLY = "android.bluetooth.device.action.CONNECTION_ACCESS_REPLY";
    public static final String ACTION_CONNECTION_ACCESS_REQUEST = "android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST";
    public static final String ACTION_CUSTOM_ACTION_RESULT = "android.bluetooth.adapter.action.CUSTOM_ACTION_RESULT";
    public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";
    public static final String ACTION_MAS_INSTANCE = "android.bluetooth.device.action.MAS_INSTANCE";
    public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";
    public static final String ACTION_NAME_FAILED = "android.bluetooth.device.action.NAME_FAILED";
    public static final String ACTION_PAIRING_CANCEL = "android.bluetooth.device.action.PAIRING_CANCEL";
    public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
    public static final String ACTION_SDP_RECORD = "android.bluetooth.device.action.SDP_RECORD";

    @SystemApi
    public static final String ACTION_SILENCE_MODE_CHANGED = "android.bluetooth.device.action.SILENCE_MODE_CHANGED";
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
    public static final int BATTERY_LEVEL_BLUETOOTH_OFF = -100;
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    private static final String BLUETOOTH_BONDING_CACHE_PROPERTY = "cache_key.bluetooth.get_bond_state";
    public static final int BOND_BONDED = 12;
    public static final int BOND_BONDING = 11;
    public static final int BOND_NONE = 10;
    public static final int BOND_SUCCESS = 0;
    public static final int CONNECTION_ACCESS_NO = 2;
    public static final int CONNECTION_ACCESS_YES = 1;
    private static final int CONNECTION_STATE_CONNECTED = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final int CONNECTION_STATE_ENCRYPTED_BREDR = 2;
    private static final int CONNECTION_STATE_ENCRYPTED_LE = 4;
    private static final boolean DBG = false;
    public static final int DEVICE_TYPE_CLASSIC = 1;
    public static final int DEVICE_TYPE_DUAL = 3;
    public static final int DEVICE_TYPE_LE = 2;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    private static final int END_INDEX = 6;
    public static final int ERROR = Integer.MIN_VALUE;
    public static final String EXTRA_ACCESS_REQUEST_TYPE = "android.bluetooth.device.extra.ACCESS_REQUEST_TYPE";
    public static final String EXTRA_ALWAYS_ALLOWED = "android.bluetooth.device.extra.ALWAYS_ALLOWED";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.device.extra.BATTERY_LEVEL";
    public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";
    public static final String EXTRA_CLASS = "android.bluetooth.device.extra.CLASS";
    public static final String EXTRA_CLASS_NAME = "android.bluetooth.device.extra.CLASS_NAME";
    public static final String EXTRA_CONNECTION_ACCESS_RESULT = "android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT";
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";
    public static final String EXTRA_KEY_LINK_KEY = "link_key";
    public static final String EXTRA_KEY_LINK_KEY_TYPE = "link_key_type";
    public static final String EXTRA_MAS_INSTANCE = "android.bluetooth.device.extra.MAS_INSTANCE";
    public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";
    public static final String EXTRA_PACKAGE_NAME = "android.bluetooth.device.extra.PACKAGE_NAME";
    public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";
    public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";
    public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";
    public static final String EXTRA_SDP_RECORD = "android.bluetooth.device.extra.SDP_RECORD";
    public static final String EXTRA_SDP_SEARCH_STATUS = "android.bluetooth.device.extra.SDP_SEARCH_STATUS";
    public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";
    public static final int LKEY_TYPE_AUTH_COMB_P_256 = 8;
    public static final int LKEY_TYPE_CHANGED_COMB = 6;
    public static final int LKEY_TYPE_COMBINATION = 0;
    public static final int LKEY_TYPE_DEBUG_COMB = 3;
    public static final int LKEY_TYPE_LOCAL_UNIT = 1;
    public static final int LKEY_TYPE_NO_LINK = -1;
    public static final int LKEY_TYPE_REMOTE_UNIT = 2;
    public static final int LKEY_TYPE_UNAUTH_COMB = 4;
    public static final int LKEY_TYPE_UNAUTH_COMB_P_256 = 7;
    private static final int MAC_LENGTH_MAX = 17;

    @SystemApi
    public static final int METADATA_COMPANION_APP = 4;

    @SystemApi
    public static final int METADATA_ENHANCED_SETTINGS_UI_URI = 16;

    @SystemApi
    public static final int METADATA_HARDWARE_VERSION = 3;

    @SystemApi
    public static final int METADATA_IS_UNTETHERED_HEADSET = 6;

    @SystemApi
    public static final int METADATA_MAIN_ICON = 5;

    @SystemApi
    public static final int METADATA_MANUFACTURER_NAME = 0;

    @SystemApi
    public static final int METADATA_MAX_LENGTH = 2048;

    @SystemApi
    public static final int METADATA_MODEL_NAME = 1;

    @SystemApi
    public static final int METADATA_SOFTWARE_VERSION = 2;

    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_BATTERY = 12;

    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_CHARGING = 15;

    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_ICON = 9;

    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_BATTERY = 10;

    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_CHARGING = 13;

    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_ICON = 7;

    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_BATTERY = 11;

    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_CHARGING = 14;

    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_ICON = 8;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    public static final int PAIRING_VARIANT_PIN = 0;
    public static final int PAIRING_VARIANT_PIN_16_DIGITS = 7;
    public static final int PHY_LE_1M = 1;
    public static final int PHY_LE_1M_MASK = 1;
    public static final int PHY_LE_2M = 2;
    public static final int PHY_LE_2M_MASK = 2;
    public static final int PHY_LE_CODED = 3;
    public static final int PHY_LE_CODED_MASK = 4;
    public static final int PHY_OPTION_NO_PREFERRED = 0;
    public static final int PHY_OPTION_S2 = 1;
    public static final int PHY_OPTION_S8 = 2;
    public static final int REQUEST_TYPE_MESSAGE_ACCESS = 3;
    public static final int REQUEST_TYPE_PHONEBOOK_ACCESS = 2;
    public static final int REQUEST_TYPE_PROFILE_CONNECTION = 1;
    public static final int REQUEST_TYPE_SIM_ACCESS = 4;
    private static final int START_INDEX = 0;
    private static final String TAG = "BluetoothDevice";
    public static final int TRANSPORT_AUTO = 0;
    public static final int TRANSPORT_BREDR = 1;
    public static final int TRANSPORT_LE = 2;
    public static final int TYPE_AUTH_COMB = 5;
    public static final int UNBOND_REASON_AUTH_CANCELED = 3;
    public static final int UNBOND_REASON_AUTH_FAILED = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
    public static final int UNBOND_REASON_REMOVED = 9;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;
    private static volatile IBluetooth sService;
    private final String mAddress;
    private final PropertyInvalidatedCache<BluetoothDevice, Integer> mBluetoothBondCache = new PropertyInvalidatedCache<BluetoothDevice, Integer>(8, BLUETOOTH_BONDING_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothDevice.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.app.PropertyInvalidatedCache
        public Integer recompute(BluetoothDevice bluetoothDevice) {
            try {
                return Integer.valueOf(BluetoothDevice.sService.getBondState(bluetoothDevice));
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        }
    };
    static IBluetoothManagerCallback sStateChangeCallback = new IBluetoothManagerCallback.Stub() { // from class: android.bluetooth.BluetoothDevice.1
        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() {
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth iBluetooth) throws RemoteException {
            synchronized (BluetoothDevice.class) {
                if (BluetoothDevice.sService == null) {
                    IBluetooth unused = BluetoothDevice.sService = iBluetooth;
                }
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() throws RemoteException {
            synchronized (BluetoothDevice.class) {
                IBluetooth unused = BluetoothDevice.sService = null;
            }
        }
    };
    public static final Parcelable.Creator<BluetoothDevice> CREATOR = new Parcelable.Creator<BluetoothDevice>() { // from class: android.bluetooth.BluetoothDevice.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothDevice createFromParcel(Parcel parcel) {
            return new BluetoothDevice(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothDevice[] newArray(int i) {
            return new BluetoothDevice[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccessPermission {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean setDeviceOutOfBandData(byte[] bArr, byte[] bArr2) {
        return false;
    }

    static IBluetooth getService() {
        synchronized (BluetoothDevice.class) {
            if (sService == null) {
                sService = BluetoothAdapter.getDefaultAdapter().getBluetoothService(sStateChangeCallback);
            }
        }
        return sService;
    }

    BluetoothDevice(String str) {
        getService();
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            throw new IllegalArgumentException(str + " is not a valid Bluetooth address");
        }
        this.mAddress = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BluetoothDevice) {
            return this.mAddress.equals(((BluetoothDevice) obj).getAddress());
        }
        return false;
    }

    public int hashCode() {
        return this.mAddress.hashCode();
    }

    public String toString() {
        return this.mAddress;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAddress);
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getAddressOnlyforLog() {
        String str = this.mAddress;
        if (str == null || str.length() < 17) {
            return "**************";
        }
        String str2 = this.mAddress;
        return str2.replaceFirst(str2.substring(0, 6), "******");
    }

    public String getName() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device name");
            return null;
        }
        try {
            String remoteName = iBluetooth.getRemoteName(this);
            if (remoteName != null) {
                return remoteName.replaceAll("[\\t\\n\\r]+", " ");
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public int getType() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device type");
            return 0;
        }
        try {
            return iBluetooth.getRemoteType(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        }
    }

    public String getAlias() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device Alias");
            return null;
        }
        try {
            String remoteAlias = iBluetooth.getRemoteAlias(this);
            return remoteAlias == null ? getName() : remoteAlias;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public boolean setAlias(String str) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot set Remote Device name");
            return false;
        }
        try {
            return iBluetooth.setRemoteAlias(this, str);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public int getBatteryLevel() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "Bluetooth disabled. Cannot get remote device battery level");
            return -100;
        }
        try {
            return iBluetooth.getBatteryLevel(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return -1;
        }
    }

    public boolean createBond() {
        return createBond(0);
    }

    public boolean createBond(int i) {
        return createBondOutOfBand(i, null);
    }

    public boolean createBondOutOfBand(int i, OobData oobData) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.w(TAG, "BT not enabled, createBondOutOfBand failed");
            return false;
        }
        try {
            return iBluetooth.createBond(this, i, oobData);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean addOutOfBandBondDevice(String str, int i, int i2) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.w(TAG, "BT not enabled, addOutOfBandBondDevice failed");
            return false;
        }
        try {
            return iBluetooth.addOutOfBandBondDevice(this, str, i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public void getLinkKey() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.w(TAG, "BT not enabled, getLinkKey failed");
            return;
        }
        try {
            iBluetooth.getLinkKey(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
        }
    }

    public boolean isBondingInitiatedLocally() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.w(TAG, "BT not enabled, isBondingInitiatedLocally failed");
            return false;
        }
        try {
            return iBluetooth.isBondingInitiatedLocally(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean cancelBondProcess() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot cancel Remote Device bond");
            return false;
        }
        try {
            Log.i(TAG, "cancelBondProcess() for device " + getAddressOnlyforLog() + " called by pid: " + Process.myPid() + " tid: " + Process.myTid());
            return iBluetooth.cancelBondProcess(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean removeBond() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot remove Remote Device bond");
            return false;
        }
        try {
            Log.i(TAG, "removeBond() for device " + getAddressOnlyforLog() + " called by pid: " + Process.myPid() + " tid: " + Process.myTid());
            return iBluetooth.removeBond(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public void disableBluetoothGetBondStateCache() {
        this.mBluetoothBondCache.disableLocal();
    }

    public static void invalidateBluetoothGetBondStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_BONDING_CACHE_PROPERTY);
    }

    public int getBondState() {
        if (sService == null) {
            Log.e(TAG, "BT not enabled. Cannot get bond state");
            return 10;
        }
        try {
            return this.mBluetoothBondCache.query(this).intValue();
        } catch (RuntimeException e) {
            if (e.getCause() instanceof RemoteException) {
                Log.e(TAG, "", e);
                return 10;
            }
            throw e;
        }
    }

    @SystemApi
    public boolean isConnected() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return false;
        }
        try {
            return iBluetooth.getConnectionState(this) != 0;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean isEncrypted() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return false;
        }
        try {
            return iBluetooth.getConnectionState(this) > 1;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public BluetoothClass getBluetoothClass() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot get Bluetooth Class");
            return null;
        }
        try {
            int remoteClass = iBluetooth.getRemoteClass(this);
            if (remoteClass == -16777216) {
                return null;
            }
            return new BluetoothClass(remoteClass);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public ParcelUuid[] getUuids() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null || !isBluetoothEnabled()) {
            Log.e(TAG, "BT not enabled. Cannot get remote device Uuids");
            return null;
        }
        try {
            return iBluetooth.getRemoteUuids(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public boolean fetchUuidsWithSdp() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null || !isBluetoothEnabled()) {
            Log.e(TAG, "BT not enabled. Cannot fetchUuidsWithSdp");
            return false;
        }
        try {
            return iBluetooth.fetchRemoteUuids(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean sdpSearch(ParcelUuid parcelUuid) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot query remote device sdp records");
            return false;
        }
        try {
            return iBluetooth.sdpSearch(this, parcelUuid);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean setPin(byte[] bArr) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot set Remote Device pin");
            return false;
        }
        try {
            return iBluetooth.setPin(this, true, bArr.length, bArr);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean setPin(String str) {
        byte[] bArrConvertPinToBytes = convertPinToBytes(str);
        if (bArrConvertPinToBytes == null) {
            return false;
        }
        return setPin(bArrConvertPinToBytes);
    }

    public boolean setPairingConfirmation(boolean z) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot set pairing confirmation");
            return false;
        }
        try {
            return iBluetooth.setPairingConfirmation(this, z);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean cancelPairing() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "BT not enabled. Cannot cancel pairing");
            return false;
        }
        try {
            return iBluetooth.cancelBondProcess(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public int getPhonebookAccessPermission() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return 0;
        }
        try {
            return iBluetooth.getPhonebookAccessPermission(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        }
    }

    @SystemApi
    public boolean setSilenceMode(boolean z) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            throw new IllegalStateException("Bluetooth is not turned ON");
        }
        try {
            return iBluetooth.setSilenceMode(this, z);
        } catch (RemoteException e) {
            Log.e(TAG, "setSilenceMode fail", e);
            return false;
        }
    }

    @SystemApi
    public boolean isInSilenceMode() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            throw new IllegalStateException("Bluetooth is not turned ON");
        }
        try {
            return iBluetooth.getSilenceMode(this);
        } catch (RemoteException e) {
            Log.e(TAG, "isInSilenceMode fail", e);
            return false;
        }
    }

    @SystemApi
    public boolean setPhonebookAccessPermission(int i) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return false;
        }
        try {
            return iBluetooth.setPhonebookAccessPermission(this, i);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public int getMessageAccessPermission() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return 0;
        }
        try {
            return iBluetooth.getMessageAccessPermission(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        }
    }

    @SystemApi
    public boolean setMessageAccessPermission(int i) {
        if (i != 1 && i != 2 && i != 0) {
            throw new IllegalArgumentException(i + "is not a valid AccessPermission value");
        }
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return false;
        }
        try {
            return iBluetooth.setMessageAccessPermission(this, i);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public int getSimAccessPermission() {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return 0;
        }
        try {
            return iBluetooth.getSimAccessPermission(this);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        }
    }

    @SystemApi
    public boolean setSimAccessPermission(int i) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            return false;
        }
        try {
            return iBluetooth.setSimAccessPermission(this, i);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public BluetoothSocket createRfcommSocket(int i) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(1, -1, true, true, this, i, null);
    }

    public BluetoothSocket createL2capSocket(int i) throws IOException {
        return new BluetoothSocket(3, -1, true, true, this, i, null);
    }

    public BluetoothSocket createInsecureL2capSocket(int i) throws IOException {
        return new BluetoothSocket(3, -1, false, false, this, i, null);
    }

    public BluetoothSocket createRfcommSocketToServiceRecord(UUID uuid) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(1, -1, true, true, this, -1, new ParcelUuid(uuid));
    }

    public BluetoothSocket createInsecureRfcommSocketToServiceRecord(UUID uuid) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(1, -1, false, false, this, -1, new ParcelUuid(uuid));
    }

    public BluetoothSocket createInsecureRfcommSocket(int i) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(1, -1, false, false, this, i, null);
    }

    public BluetoothSocket createScoSocket() throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(2, -1, true, true, this, -1, null);
    }

    public static byte[] convertPinToBytes(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
            if (bytes.length <= 0 || bytes.length > 16) {
                return null;
            }
            return bytes;
        } catch (UnsupportedEncodingException unused) {
            Log.e(TAG, "UTF-8 not supported?!?");
            return null;
        }
    }

    public BluetoothGatt connectGatt(Context context, boolean z, BluetoothGattCallback bluetoothGattCallback) {
        return connectGatt(context, z, bluetoothGattCallback, 0);
    }

    public BluetoothGatt connectGatt(Context context, boolean z, BluetoothGattCallback bluetoothGattCallback, int i) {
        return connectGatt(context, z, bluetoothGattCallback, i, 1);
    }

    public BluetoothGatt connectGatt(Context context, boolean z, BluetoothGattCallback bluetoothGattCallback, int i, int i2) {
        return connectGatt(context, z, bluetoothGattCallback, i, i2, null);
    }

    public BluetoothGatt connectGatt(Context context, boolean z, BluetoothGattCallback bluetoothGattCallback, int i, int i2, Handler handler) {
        return connectGatt(context, z, bluetoothGattCallback, i, false, i2, handler);
    }

    public BluetoothGatt connectGatt(Context context, boolean z, BluetoothGattCallback bluetoothGattCallback, int i, boolean z2, int i2, Handler handler) {
        if (bluetoothGattCallback == null) {
            throw new NullPointerException("callback is null");
        }
        try {
            IBluetoothGatt bluetoothGatt = BluetoothAdapter.getDefaultAdapter().getBluetoothManager().getBluetoothGatt();
            if (bluetoothGatt == null) {
                return null;
            }
            BluetoothGatt bluetoothGatt2 = new BluetoothGatt(bluetoothGatt, this, i, z2, i2);
            bluetoothGatt2.connect(Boolean.valueOf(z), bluetoothGattCallback, handler);
            return bluetoothGatt2;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }

    public BluetoothSocket createL2capChannel(int i) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "createL2capChannel: Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(4, -1, true, true, this, i, null);
    }

    public BluetoothSocket createInsecureL2capChannel(int i) throws IOException {
        if (!isBluetoothEnabled()) {
            Log.e(TAG, "createInsecureL2capChannel: Bluetooth is not enabled");
            throw new IOException();
        }
        return new BluetoothSocket(4, -1, false, false, this, i, null);
    }

    @SystemApi
    public boolean setMetadata(int i, byte[] bArr) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot set metadata");
            return false;
        }
        if (bArr.length > 2048) {
            throw new IllegalArgumentException("value length is " + bArr.length + ", should not over 2048");
        }
        try {
            return iBluetooth.setMetadata(this, i, bArr);
        } catch (RemoteException e) {
            Log.e(TAG, "setMetadata fail", e);
            return false;
        }
    }

    @SystemApi
    public byte[] getMetadata(int i) {
        IBluetooth iBluetooth = sService;
        if (iBluetooth == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot get metadata");
            return null;
        }
        try {
            return iBluetooth.getMetadata(this, i);
        } catch (RemoteException e) {
            Log.e(TAG, "getMetadata fail", e);
            return null;
        }
    }
}
