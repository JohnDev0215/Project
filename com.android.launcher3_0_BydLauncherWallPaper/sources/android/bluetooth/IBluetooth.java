package android.bluetooth;

import android.bluetooth.IBluetoothCallback;
import android.bluetooth.IBluetoothMetadataListener;
import android.bluetooth.IBluetoothSocketManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetooth extends IInterface {

    public static class Default implements IBluetooth {
        @Override // android.bluetooth.IBluetooth
        public boolean addOutOfBandBondDevice(BluetoothDevice bluetoothDevice, String str, int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean cancelBondProcess(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean cancelDiscovery() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean connectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean createBond(BluetoothDevice bluetoothDevice, int i, OobData oobData) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean disable() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean disconnectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean enable(boolean z) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean factoryReset() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean fetchRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getAdapterConnectionState() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public String getAddress() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getBatteryLevel(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothClass getBluetoothClass() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getBondState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothDevice[] getBondedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getDiscoverableTimeout() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public long getDiscoveryEndMillis() throws RemoteException {
            return 0L;
        }

        @Override // android.bluetooth.IBluetooth
        public int getIoCapability() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getLeIoCapability() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getLeMaximumAdvertisingDataLength() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public void getLinkKey(BluetoothDevice bluetoothDevice) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public int getMaxConnectedAudioDevices() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getMessageAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public byte[] getMetadata(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public String getName() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getPhonebookAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getProfileConnectionState(int i) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public String getRemoteAlias(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getRemoteClass(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public String getRemoteName(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getRemoteType(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getScanMode() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean getSilenceMode(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getSimAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public IBluetoothSocketManager getSocketManager() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getState() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public long getSupportedProfiles() throws RemoteException {
            return 0L;
        }

        @Override // android.bluetooth.IBluetooth
        public ParcelUuid[] getUuids() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isBondingInitiatedLocally(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isDiscovering() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLe2MPhySupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLeCodedPhySupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isMultiAdvertisementSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isOffloadedFilteringSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isOffloadedScanBatchingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public void onBrEdrDown() throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public void onLeServiceUp() throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean readLocalOobData() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public void registerCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean registerMetadataListener(IBluetoothMetadataListener iBluetoothMetadataListener, BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean removeAcl(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean removeActiveDevice(int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean removeBond(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public void requestActivityInfo(ResultReceiver resultReceiver) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean sdpSearch(BluetoothDevice bluetoothDevice, ParcelUuid parcelUuid) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setBluetoothClass(BluetoothClass bluetoothClass) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setDiscoverableTimeout(int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setIoCapability(int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setLeIoCapability(int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setMessageAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setMetadata(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setName(String str) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPairingConfirmation(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPasskey(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPhonebookAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPin(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setRemoteAlias(BluetoothDevice bluetoothDevice, String str) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setScanMode(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setSilenceMode(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setSimAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean startDiscovery(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public void unregisterCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean unregisterMetadataListener(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }
    }

    boolean addOutOfBandBondDevice(BluetoothDevice bluetoothDevice, String str, int i, int i2) throws RemoteException;

    boolean cancelBondProcess(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean cancelDiscovery() throws RemoteException;

    boolean connectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean createBond(BluetoothDevice bluetoothDevice, int i, OobData oobData) throws RemoteException;

    boolean disable() throws RemoteException;

    boolean disconnectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean enable(boolean z) throws RemoteException;

    boolean factoryReset() throws RemoteException;

    boolean fetchRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getAdapterConnectionState() throws RemoteException;

    String getAddress() throws RemoteException;

    int getBatteryLevel(BluetoothDevice bluetoothDevice) throws RemoteException;

    BluetoothClass getBluetoothClass() throws RemoteException;

    int getBondState(BluetoothDevice bluetoothDevice) throws RemoteException;

    BluetoothDevice[] getBondedDevices() throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getDiscoverableTimeout() throws RemoteException;

    long getDiscoveryEndMillis() throws RemoteException;

    int getIoCapability() throws RemoteException;

    int getLeIoCapability() throws RemoteException;

    int getLeMaximumAdvertisingDataLength() throws RemoteException;

    void getLinkKey(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getMaxConnectedAudioDevices() throws RemoteException;

    int getMessageAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException;

    byte[] getMetadata(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException;

    String getName() throws RemoteException;

    int getPhonebookAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getProfileConnectionState(int i) throws RemoteException;

    String getRemoteAlias(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getRemoteClass(BluetoothDevice bluetoothDevice) throws RemoteException;

    String getRemoteName(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getRemoteType(BluetoothDevice bluetoothDevice) throws RemoteException;

    ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getScanMode() throws RemoteException;

    boolean getSilenceMode(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getSimAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException;

    IBluetoothSocketManager getSocketManager() throws RemoteException;

    int getState() throws RemoteException;

    long getSupportedProfiles() throws RemoteException;

    ParcelUuid[] getUuids() throws RemoteException;

    boolean isActivityAndEnergyReportingSupported() throws RemoteException;

    boolean isBondingInitiatedLocally(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isDiscovering() throws RemoteException;

    boolean isLe2MPhySupported() throws RemoteException;

    boolean isLeCodedPhySupported() throws RemoteException;

    boolean isLeExtendedAdvertisingSupported() throws RemoteException;

    boolean isLePeriodicAdvertisingSupported() throws RemoteException;

    boolean isMultiAdvertisementSupported() throws RemoteException;

    boolean isOffloadedFilteringSupported() throws RemoteException;

    boolean isOffloadedScanBatchingSupported() throws RemoteException;

    void onBrEdrDown() throws RemoteException;

    void onLeServiceUp() throws RemoteException;

    boolean readLocalOobData() throws RemoteException;

    void registerCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException;

    boolean registerMetadataListener(IBluetoothMetadataListener iBluetoothMetadataListener, BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean removeAcl(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean removeActiveDevice(int i) throws RemoteException;

    boolean removeBond(BluetoothDevice bluetoothDevice) throws RemoteException;

    BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException;

    void requestActivityInfo(ResultReceiver resultReceiver) throws RemoteException;

    boolean sdpSearch(BluetoothDevice bluetoothDevice, ParcelUuid parcelUuid) throws RemoteException;

    boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setBluetoothClass(BluetoothClass bluetoothClass) throws RemoteException;

    boolean setDiscoverableTimeout(int i) throws RemoteException;

    boolean setIoCapability(int i) throws RemoteException;

    boolean setLeIoCapability(int i) throws RemoteException;

    boolean setMessageAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setMetadata(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException;

    boolean setName(String str) throws RemoteException;

    boolean setPairingConfirmation(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException;

    boolean setPasskey(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException;

    boolean setPhonebookAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setPin(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException;

    boolean setRemoteAlias(BluetoothDevice bluetoothDevice, String str) throws RemoteException;

    boolean setScanMode(int i, int i2) throws RemoteException;

    boolean setSilenceMode(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException;

    boolean setSimAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean startDiscovery(String str, String str2) throws RemoteException;

    void unregisterCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException;

    boolean unregisterMetadataListener(BluetoothDevice bluetoothDevice) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetooth {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetooth";
        static final int TRANSACTION_addOutOfBandBondDevice = 27;
        static final int TRANSACTION_cancelBondProcess = 29;
        static final int TRANSACTION_cancelDiscovery = 20;
        static final int TRANSACTION_connectAllEnabledProfiles = 78;
        static final int TRANSACTION_createBond = 26;
        static final int TRANSACTION_disable = 3;
        static final int TRANSACTION_disconnectAllEnabledProfiles = 79;
        static final int TRANSACTION_enable = 2;
        static final int TRANSACTION_factoryReset = 60;
        static final int TRANSACTION_fetchRemoteUuids = 42;
        static final int TRANSACTION_getAdapterConnectionState = 23;
        static final int TRANSACTION_getAddress = 4;
        static final int TRANSACTION_getBatteryLevel = 44;
        static final int TRANSACTION_getBluetoothClass = 8;
        static final int TRANSACTION_getBondState = 31;
        static final int TRANSACTION_getBondedDevices = 25;
        static final int TRANSACTION_getConnectionState = 34;
        static final int TRANSACTION_getDiscoverableTimeout = 17;
        static final int TRANSACTION_getDiscoveryEndMillis = 22;
        static final int TRANSACTION_getIoCapability = 10;
        static final int TRANSACTION_getLeIoCapability = 12;
        static final int TRANSACTION_getLeMaximumAdvertisingDataLength = 69;
        static final int TRANSACTION_getLinkKey = 28;
        static final int TRANSACTION_getMaxConnectedAudioDevices = 45;
        static final int TRANSACTION_getMessageAccessPermission = 53;
        static final int TRANSACTION_getMetadata = 74;
        static final int TRANSACTION_getMostRecentlyConnectedDevices = 81;
        static final int TRANSACTION_getName = 7;
        static final int TRANSACTION_getPhonebookAccessPermission = 49;
        static final int TRANSACTION_getProfileConnectionState = 24;
        static final int TRANSACTION_getRemoteAlias = 38;
        static final int TRANSACTION_getRemoteClass = 40;
        static final int TRANSACTION_getRemoteName = 36;
        static final int TRANSACTION_getRemoteType = 37;
        static final int TRANSACTION_getRemoteUuids = 41;
        static final int TRANSACTION_getScanMode = 15;
        static final int TRANSACTION_getSilenceMode = 51;
        static final int TRANSACTION_getSimAccessPermission = 55;
        static final int TRANSACTION_getSocketManager = 59;
        static final int TRANSACTION_getState = 1;
        static final int TRANSACTION_getSupportedProfiles = 33;
        static final int TRANSACTION_getUuids = 5;
        static final int TRANSACTION_isActivityAndEnergyReportingSupported = 64;
        static final int TRANSACTION_isBondingInitiatedLocally = 32;
        static final int TRANSACTION_isDiscovering = 21;
        static final int TRANSACTION_isLe2MPhySupported = 65;
        static final int TRANSACTION_isLeCodedPhySupported = 66;
        static final int TRANSACTION_isLeExtendedAdvertisingSupported = 67;
        static final int TRANSACTION_isLePeriodicAdvertisingSupported = 68;
        static final int TRANSACTION_isMultiAdvertisementSupported = 61;
        static final int TRANSACTION_isOffloadedFilteringSupported = 62;
        static final int TRANSACTION_isOffloadedScanBatchingSupported = 63;
        static final int TRANSACTION_onBrEdrDown = 77;
        static final int TRANSACTION_onLeServiceUp = 76;
        static final int TRANSACTION_readLocalOobData = 14;
        static final int TRANSACTION_registerCallback = 57;
        static final int TRANSACTION_registerMetadataListener = 71;
        static final int TRANSACTION_removeAcl = 35;
        static final int TRANSACTION_removeActiveDevice = 82;
        static final int TRANSACTION_removeBond = 30;
        static final int TRANSACTION_reportActivityInfo = 70;
        static final int TRANSACTION_requestActivityInfo = 75;
        static final int TRANSACTION_sdpSearch = 43;
        static final int TRANSACTION_setActiveDevice = 80;
        static final int TRANSACTION_setBluetoothClass = 9;
        static final int TRANSACTION_setDiscoverableTimeout = 18;
        static final int TRANSACTION_setIoCapability = 11;
        static final int TRANSACTION_setLeIoCapability = 13;
        static final int TRANSACTION_setMessageAccessPermission = 54;
        static final int TRANSACTION_setMetadata = 73;
        static final int TRANSACTION_setName = 6;
        static final int TRANSACTION_setPairingConfirmation = 48;
        static final int TRANSACTION_setPasskey = 47;
        static final int TRANSACTION_setPhonebookAccessPermission = 52;
        static final int TRANSACTION_setPin = 46;
        static final int TRANSACTION_setRemoteAlias = 39;
        static final int TRANSACTION_setScanMode = 16;
        static final int TRANSACTION_setSilenceMode = 50;
        static final int TRANSACTION_setSimAccessPermission = 56;
        static final int TRANSACTION_startDiscovery = 19;
        static final int TRANSACTION_unregisterCallback = 58;
        static final int TRANSACTION_unregisterMetadataListener = 72;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getState";
                case 2:
                    return "enable";
                case 3:
                    return "disable";
                case 4:
                    return "getAddress";
                case 5:
                    return "getUuids";
                case 6:
                    return "setName";
                case 7:
                    return "getName";
                case 8:
                    return "getBluetoothClass";
                case 9:
                    return "setBluetoothClass";
                case 10:
                    return "getIoCapability";
                case 11:
                    return "setIoCapability";
                case 12:
                    return "getLeIoCapability";
                case 13:
                    return "setLeIoCapability";
                case 14:
                    return "readLocalOobData";
                case 15:
                    return "getScanMode";
                case 16:
                    return "setScanMode";
                case 17:
                    return "getDiscoverableTimeout";
                case 18:
                    return "setDiscoverableTimeout";
                case 19:
                    return "startDiscovery";
                case 20:
                    return "cancelDiscovery";
                case 21:
                    return "isDiscovering";
                case 22:
                    return "getDiscoveryEndMillis";
                case 23:
                    return "getAdapterConnectionState";
                case 24:
                    return "getProfileConnectionState";
                case 25:
                    return "getBondedDevices";
                case 26:
                    return "createBond";
                case 27:
                    return "addOutOfBandBondDevice";
                case 28:
                    return "getLinkKey";
                case 29:
                    return "cancelBondProcess";
                case 30:
                    return "removeBond";
                case 31:
                    return "getBondState";
                case 32:
                    return "isBondingInitiatedLocally";
                case 33:
                    return "getSupportedProfiles";
                case 34:
                    return "getConnectionState";
                case 35:
                    return "removeAcl";
                case 36:
                    return "getRemoteName";
                case 37:
                    return "getRemoteType";
                case 38:
                    return "getRemoteAlias";
                case 39:
                    return "setRemoteAlias";
                case 40:
                    return "getRemoteClass";
                case 41:
                    return "getRemoteUuids";
                case 42:
                    return "fetchRemoteUuids";
                case 43:
                    return "sdpSearch";
                case 44:
                    return "getBatteryLevel";
                case 45:
                    return "getMaxConnectedAudioDevices";
                case 46:
                    return "setPin";
                case 47:
                    return "setPasskey";
                case 48:
                    return "setPairingConfirmation";
                case 49:
                    return "getPhonebookAccessPermission";
                case 50:
                    return "setSilenceMode";
                case 51:
                    return "getSilenceMode";
                case 52:
                    return "setPhonebookAccessPermission";
                case 53:
                    return "getMessageAccessPermission";
                case 54:
                    return "setMessageAccessPermission";
                case 55:
                    return "getSimAccessPermission";
                case 56:
                    return "setSimAccessPermission";
                case 57:
                    return "registerCallback";
                case 58:
                    return "unregisterCallback";
                case 59:
                    return "getSocketManager";
                case 60:
                    return "factoryReset";
                case 61:
                    return "isMultiAdvertisementSupported";
                case 62:
                    return "isOffloadedFilteringSupported";
                case 63:
                    return "isOffloadedScanBatchingSupported";
                case 64:
                    return "isActivityAndEnergyReportingSupported";
                case 65:
                    return "isLe2MPhySupported";
                case 66:
                    return "isLeCodedPhySupported";
                case 67:
                    return "isLeExtendedAdvertisingSupported";
                case 68:
                    return "isLePeriodicAdvertisingSupported";
                case 69:
                    return "getLeMaximumAdvertisingDataLength";
                case 70:
                    return "reportActivityInfo";
                case 71:
                    return "registerMetadataListener";
                case 72:
                    return "unregisterMetadataListener";
                case 73:
                    return "setMetadata";
                case 74:
                    return "getMetadata";
                case 75:
                    return "requestActivityInfo";
                case 76:
                    return "onLeServiceUp";
                case 77:
                    return "onBrEdrDown";
                case 78:
                    return "connectAllEnabledProfiles";
                case 79:
                    return "disconnectAllEnabledProfiles";
                case 80:
                    return "setActiveDevice";
                case 81:
                    return "getMostRecentlyConnectedDevices";
                case 82:
                    return "removeActiveDevice";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetooth asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetooth)) {
                return (IBluetooth) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int state = getState();
                    parcel2.writeNoException();
                    parcel2.writeInt(state);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnable = enable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnable ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisable = disable();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisable ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String address = getAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(address);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelUuid[] uuids = getUuids();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(uuids, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean name = setName(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(name ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String name2 = getName();
                    parcel2.writeNoException();
                    parcel2.writeString(name2);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothClass bluetoothClass = getBluetoothClass();
                    parcel2.writeNoException();
                    if (bluetoothClass != null) {
                        parcel2.writeInt(1);
                        bluetoothClass.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bluetoothClass2 = setBluetoothClass(parcel.readInt() != 0 ? BluetoothClass.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(bluetoothClass2 ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ioCapability = getIoCapability();
                    parcel2.writeNoException();
                    parcel2.writeInt(ioCapability);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ioCapability2 = setIoCapability(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(ioCapability2 ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int leIoCapability = getLeIoCapability();
                    parcel2.writeNoException();
                    parcel2.writeInt(leIoCapability);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean leIoCapability2 = setLeIoCapability(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(leIoCapability2 ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean localOobData = readLocalOobData();
                    parcel2.writeNoException();
                    parcel2.writeInt(localOobData ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int scanMode = getScanMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(scanMode);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean scanMode2 = setScanMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(scanMode2 ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int discoverableTimeout = getDiscoverableTimeout();
                    parcel2.writeNoException();
                    parcel2.writeInt(discoverableTimeout);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean discoverableTimeout2 = setDiscoverableTimeout(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(discoverableTimeout2 ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartDiscovery = startDiscovery(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartDiscovery ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelDiscovery = cancelDiscovery();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelDiscovery ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDiscovering = isDiscovering();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDiscovering ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    long discoveryEndMillis = getDiscoveryEndMillis();
                    parcel2.writeNoException();
                    parcel2.writeLong(discoveryEndMillis);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int adapterConnectionState = getAdapterConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(adapterConnectionState);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int profileConnectionState = getProfileConnectionState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(profileConnectionState);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothDevice[] bondedDevices = getBondedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(bondedDevices, 1);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCreateBond = createBond(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? OobData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCreateBond ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddOutOfBandBondDevice = addOutOfBandBondDevice(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddOutOfBandBondDevice ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLinkKey(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelBondProcess = cancelBondProcess(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelBondProcess ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveBond = removeBond(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveBond ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bondState = getBondState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(bondState);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBondingInitiatedLocally = isBondingInitiatedLocally(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBondingInitiatedLocally ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    long supportedProfiles = getSupportedProfiles();
                    parcel2.writeNoException();
                    parcel2.writeLong(supportedProfiles);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionState = getConnectionState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionState);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveAcl = removeAcl(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveAcl ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    String remoteName = getRemoteName(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(remoteName);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int remoteType = getRemoteType(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(remoteType);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    String remoteAlias = getRemoteAlias(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(remoteAlias);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean remoteAlias2 = setRemoteAlias(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(remoteAlias2 ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int remoteClass = getRemoteClass(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(remoteClass);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelUuid[] remoteUuids = getRemoteUuids(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(remoteUuids, 1);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zFetchRemoteUuids = fetchRemoteUuids(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zFetchRemoteUuids ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSdpSearch = sdpSearch(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zSdpSearch ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int batteryLevel = getBatteryLevel(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(batteryLevel);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    int maxConnectedAudioDevices = getMaxConnectedAudioDevices();
                    parcel2.writeNoException();
                    parcel2.writeInt(maxConnectedAudioDevices);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pin = setPin(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(pin ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean passkey = setPasskey(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(passkey ? 1 : 0);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pairingConfirmation = setPairingConfirmation(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(pairingConfirmation ? 1 : 0);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    int phonebookAccessPermission = getPhonebookAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(phonebookAccessPermission);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean silenceMode = setSilenceMode(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(silenceMode ? 1 : 0);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean silenceMode2 = getSilenceMode(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(silenceMode2 ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean phonebookAccessPermission2 = setPhonebookAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(phonebookAccessPermission2 ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    int messageAccessPermission = getMessageAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(messageAccessPermission);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean messageAccessPermission2 = setMessageAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(messageAccessPermission2 ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int simAccessPermission = getSimAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(simAccessPermission);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean simAccessPermission2 = setSimAccessPermission(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(simAccessPermission2 ? 1 : 0);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IBluetoothCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IBluetoothCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBluetoothSocketManager socketManager = getSocketManager();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(socketManager != null ? socketManager.asBinder() : null);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zFactoryReset = factoryReset();
                    parcel2.writeNoException();
                    parcel2.writeInt(zFactoryReset ? 1 : 0);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsMultiAdvertisementSupported = isMultiAdvertisementSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsMultiAdvertisementSupported ? 1 : 0);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOffloadedFilteringSupported = isOffloadedFilteringSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOffloadedFilteringSupported ? 1 : 0);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsOffloadedScanBatchingSupported = isOffloadedScanBatchingSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsOffloadedScanBatchingSupported ? 1 : 0);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsActivityAndEnergyReportingSupported = isActivityAndEnergyReportingSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsActivityAndEnergyReportingSupported ? 1 : 0);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLe2MPhySupported = isLe2MPhySupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLe2MPhySupported ? 1 : 0);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLeCodedPhySupported = isLeCodedPhySupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLeCodedPhySupported ? 1 : 0);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLeExtendedAdvertisingSupported = isLeExtendedAdvertisingSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLeExtendedAdvertisingSupported ? 1 : 0);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLePeriodicAdvertisingSupported = isLePeriodicAdvertisingSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLePeriodicAdvertisingSupported ? 1 : 0);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    int leMaximumAdvertisingDataLength = getLeMaximumAdvertisingDataLength();
                    parcel2.writeNoException();
                    parcel2.writeInt(leMaximumAdvertisingDataLength);
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothActivityEnergyInfo bluetoothActivityEnergyInfoReportActivityInfo = reportActivityInfo();
                    parcel2.writeNoException();
                    if (bluetoothActivityEnergyInfoReportActivityInfo != null) {
                        parcel2.writeInt(1);
                        bluetoothActivityEnergyInfoReportActivityInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterMetadataListener = registerMetadataListener(IBluetoothMetadataListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterMetadataListener ? 1 : 0);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterMetadataListener = unregisterMetadataListener(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterMetadataListener ? 1 : 0);
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean metadata = setMetadata(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(metadata ? 1 : 0);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] metadata2 = getMetadata(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(metadata2);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestActivityInfo(parcel.readInt() != 0 ? ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    onLeServiceUp();
                    parcel2.writeNoException();
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBrEdrDown();
                    parcel2.writeNoException();
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectAllEnabledProfiles = connectAllEnabledProfiles(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectAllEnabledProfiles ? 1 : 0);
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectAllEnabledProfiles = disconnectAllEnabledProfiles(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectAllEnabledProfiles ? 1 : 0);
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean activeDevice = setActiveDevice(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(activeDevice ? 1 : 0);
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> mostRecentlyConnectedDevices = getMostRecentlyConnectedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(mostRecentlyConnectedDevices);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveActiveDevice = removeActiveDevice(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveActiveDevice ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetooth {
            public static IBluetooth sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.bluetooth.IBluetooth
            public int getState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean enable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enable(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean disable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public ParcelUuid[] getUuids() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUuids();
                    }
                    parcelObtain2.readException();
                    return (ParcelUuid[]) parcelObtain2.createTypedArray(ParcelUuid.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setName(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setName(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothClass getBluetoothClass() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothClass();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothClass.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setBluetoothClass(BluetoothClass bluetoothClass) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothClass != null) {
                        parcelObtain.writeInt(1);
                        bluetoothClass.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBluetoothClass(bluetoothClass);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getIoCapability() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIoCapability();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setIoCapability(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIoCapability(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getLeIoCapability() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeIoCapability();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setLeIoCapability(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLeIoCapability(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean readLocalOobData() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readLocalOobData();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getScanMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScanMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setScanMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setScanMode(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getDiscoverableTimeout() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDiscoverableTimeout();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setDiscoverableTimeout(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDiscoverableTimeout(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean startDiscovery(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDiscovery(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean cancelDiscovery() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelDiscovery();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isDiscovering() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDiscovering();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public long getDiscoveryEndMillis() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDiscoveryEndMillis();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getAdapterConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdapterConnectionState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getProfileConnectionState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileConnectionState(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothDevice[] getBondedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBondedDevices();
                    }
                    parcelObtain2.readException();
                    return (BluetoothDevice[]) parcelObtain2.createTypedArray(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean createBond(BluetoothDevice bluetoothDevice, int i, OobData oobData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (oobData != null) {
                        parcelObtain.writeInt(1);
                        oobData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createBond(bluetoothDevice, i, oobData);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean addOutOfBandBondDevice(BluetoothDevice bluetoothDevice, String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addOutOfBandBondDevice(bluetoothDevice, str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void getLinkKey(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLinkKey(bluetoothDevice);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean cancelBondProcess(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelBondProcess(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean removeBond(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeBond(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getBondState(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBondState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isBondingInitiatedLocally(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBondingInitiatedLocally(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public long getSupportedProfiles() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedProfiles();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean removeAcl(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAcl(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getRemoteName(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteName(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getRemoteType(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteType(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getRemoteAlias(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteAlias(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setRemoteAlias(BluetoothDevice bluetoothDevice, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRemoteAlias(bluetoothDevice, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getRemoteClass(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteClass(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteUuids(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return (ParcelUuid[]) parcelObtain2.createTypedArray(ParcelUuid.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean fetchRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchRemoteUuids(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean sdpSearch(BluetoothDevice bluetoothDevice, ParcelUuid parcelUuid) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sdpSearch(bluetoothDevice, parcelUuid);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getBatteryLevel(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryLevel(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getMaxConnectedAudioDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxConnectedAudioDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPin(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPin(bluetoothDevice, z, i, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPasskey(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPasskey(bluetoothDevice, z, i, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPairingConfirmation(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPairingConfirmation(bluetoothDevice, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getPhonebookAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhonebookAccessPermission(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setSilenceMode(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSilenceMode(bluetoothDevice, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean getSilenceMode(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSilenceMode(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPhonebookAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPhonebookAccessPermission(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getMessageAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMessageAccessPermission(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setMessageAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMessageAccessPermission(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getSimAccessPermission(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimAccessPermission(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setSimAccessPermission(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSimAccessPermission(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void registerCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBluetoothCallback != null ? iBluetoothCallback.asBinder() : null);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(iBluetoothCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void unregisterCallback(IBluetoothCallback iBluetoothCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBluetoothCallback != null ? iBluetoothCallback.asBinder() : null);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallback(iBluetoothCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public IBluetoothSocketManager getSocketManager() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSocketManager();
                    }
                    parcelObtain2.readException();
                    return IBluetoothSocketManager.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean factoryReset() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().factoryReset();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isMultiAdvertisementSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMultiAdvertisementSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isOffloadedFilteringSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOffloadedFilteringSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isOffloadedScanBatchingSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOffloadedScanBatchingSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActivityAndEnergyReportingSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLe2MPhySupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLe2MPhySupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLeCodedPhySupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(66, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeCodedPhySupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(67, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeExtendedAdvertisingSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(68, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLePeriodicAdvertisingSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getLeMaximumAdvertisingDataLength() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeMaximumAdvertisingDataLength();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reportActivityInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothActivityEnergyInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean registerMetadataListener(IBluetoothMetadataListener iBluetoothMetadataListener, BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBluetoothMetadataListener != null ? iBluetoothMetadataListener.asBinder() : null);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMetadataListener(iBluetoothMetadataListener, bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean unregisterMetadataListener(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterMetadataListener(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setMetadata(BluetoothDevice bluetoothDevice, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMetadata(bluetoothDevice, i, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public byte[] getMetadata(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadata(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void requestActivityInfo(ResultReceiver resultReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resultReceiver != null) {
                        parcelObtain.writeInt(1);
                        resultReceiver.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(75, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestActivityInfo(resultReceiver);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void onLeServiceUp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLeServiceUp();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void onBrEdrDown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBrEdrDown();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean connectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectAllEnabledProfiles(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean disconnectAllEnabledProfiles(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectAllEnabledProfiles(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setActiveDevice(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMostRecentlyConnectedDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean removeActiveDevice(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeActiveDevice(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetooth iBluetooth) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetooth == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetooth;
            return true;
        }

        public static IBluetooth getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
