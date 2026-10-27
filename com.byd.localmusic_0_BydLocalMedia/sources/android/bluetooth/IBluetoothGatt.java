package android.bluetooth;

import android.app.PendingIntent;
import android.bluetooth.IBluetoothGattCallback;
import android.bluetooth.IBluetoothGattServerCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.WorkSource;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothGatt extends IInterface {

    public static class Default implements IBluetoothGatt {
        @Override // android.bluetooth.IBluetoothGatt
        public void addService(int i, BluetoothGattService bluetoothGattService) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void beginReliableWrite(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clearServices(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientConnect(int i, String str, boolean z, int i2, boolean z2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientDisconnect(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientReadPhy(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void configureMTU(int i, String str, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void connectionParameterUpdate(int i, String str, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void disconnectAll() throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void discoverServiceByUuid(int i, String str, ParcelUuid parcelUuid) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void discoverServices(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void enableAdvertisingSet(int i, boolean z, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void endReliableWrite(int i, String str, boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void flushPendingBatchResults(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void getOwnAddress(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void leConnectionUpdate(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public int numHwTrackFiltersAvailable() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readCharacteristic(int i, String str, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readDescriptor(int i, String str, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readRemoteRssi(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readUsingCharacteristicUuid(int i, String str, ParcelUuid parcelUuid, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void refreshDevice(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerClient(ParcelUuid parcelUuid, IBluetoothGattCallback iBluetoothGattCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerForNotification(int i, String str, int i2, boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerScanner(IScannerCallback iScannerCallback, WorkSource workSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerServer(ParcelUuid parcelUuid, IBluetoothGattServerCallback iBluetoothGattServerCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerSync(ScanResult scanResult, int i, int i2, IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void removeService(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void sendNotification(int i, String str, int i2, boolean z, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void sendResponse(int i, String str, int i2, int i3, int i4, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverConnect(int i, String str, boolean z, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverDisconnect(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverReadPhy(int i, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingSetParameters) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingEnable(int i, boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicAdvertisingParameters) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setScanResponseData(int i, AdvertiseData advertiseData) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startAdvertisingSet(AdvertisingSetParameters advertisingSetParameters, AdvertiseData advertiseData, AdvertiseData advertiseData2, PeriodicAdvertisingParameters periodicAdvertisingParameters, AdvertiseData advertiseData3, int i, int i2, IAdvertisingSetCallback iAdvertisingSetCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startScan(int i, ScanSettings scanSettings, List<ScanFilter> list, List list2, String str, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startScanForIntent(PendingIntent pendingIntent, ScanSettings scanSettings, List<ScanFilter> list, String str, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopAdvertisingSet(IAdvertisingSetCallback iAdvertisingSetCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopScan(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopScanForIntent(PendingIntent pendingIntent, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregAll() throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterClient(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterScanner(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterServer(int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterSync(IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void writeCharacteristic(int i, String str, int i2, int i3, int i4, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void writeDescriptor(int i, String str, int i2, int i3, byte[] bArr) throws RemoteException {
        }
    }

    void addService(int i, BluetoothGattService bluetoothGattService) throws RemoteException;

    void beginReliableWrite(int i, String str) throws RemoteException;

    void clearServices(int i) throws RemoteException;

    void clientConnect(int i, String str, boolean z, int i2, boolean z2, int i3) throws RemoteException;

    void clientDisconnect(int i, String str) throws RemoteException;

    void clientReadPhy(int i, String str) throws RemoteException;

    void clientSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException;

    void configureMTU(int i, String str, int i2) throws RemoteException;

    void connectionParameterUpdate(int i, String str, int i2) throws RemoteException;

    void disconnectAll() throws RemoteException;

    void discoverServiceByUuid(int i, String str, ParcelUuid parcelUuid) throws RemoteException;

    void discoverServices(int i, String str) throws RemoteException;

    void enableAdvertisingSet(int i, boolean z, int i2, int i3) throws RemoteException;

    void endReliableWrite(int i, String str, boolean z) throws RemoteException;

    void flushPendingBatchResults(int i) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException;

    void getOwnAddress(int i) throws RemoteException;

    void leConnectionUpdate(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException;

    int numHwTrackFiltersAvailable() throws RemoteException;

    void readCharacteristic(int i, String str, int i2, int i3) throws RemoteException;

    void readDescriptor(int i, String str, int i2, int i3) throws RemoteException;

    void readRemoteRssi(int i, String str) throws RemoteException;

    void readUsingCharacteristicUuid(int i, String str, ParcelUuid parcelUuid, int i2, int i3, int i4) throws RemoteException;

    void refreshDevice(int i, String str) throws RemoteException;

    void registerClient(ParcelUuid parcelUuid, IBluetoothGattCallback iBluetoothGattCallback) throws RemoteException;

    void registerForNotification(int i, String str, int i2, boolean z) throws RemoteException;

    void registerScanner(IScannerCallback iScannerCallback, WorkSource workSource) throws RemoteException;

    void registerServer(ParcelUuid parcelUuid, IBluetoothGattServerCallback iBluetoothGattServerCallback) throws RemoteException;

    void registerSync(ScanResult scanResult, int i, int i2, IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException;

    void removeService(int i, int i2) throws RemoteException;

    void sendNotification(int i, String str, int i2, boolean z, byte[] bArr) throws RemoteException;

    void sendResponse(int i, String str, int i2, int i3, int i4, byte[] bArr) throws RemoteException;

    void serverConnect(int i, String str, boolean z, int i2) throws RemoteException;

    void serverDisconnect(int i, String str) throws RemoteException;

    void serverReadPhy(int i, String str) throws RemoteException;

    void serverSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException;

    void setAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException;

    void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingSetParameters) throws RemoteException;

    void setPeriodicAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException;

    void setPeriodicAdvertisingEnable(int i, boolean z) throws RemoteException;

    void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicAdvertisingParameters) throws RemoteException;

    void setScanResponseData(int i, AdvertiseData advertiseData) throws RemoteException;

    void startAdvertisingSet(AdvertisingSetParameters advertisingSetParameters, AdvertiseData advertiseData, AdvertiseData advertiseData2, PeriodicAdvertisingParameters periodicAdvertisingParameters, AdvertiseData advertiseData3, int i, int i2, IAdvertisingSetCallback iAdvertisingSetCallback) throws RemoteException;

    void startScan(int i, ScanSettings scanSettings, List<ScanFilter> list, List list2, String str, String str2) throws RemoteException;

    void startScanForIntent(PendingIntent pendingIntent, ScanSettings scanSettings, List<ScanFilter> list, String str, String str2) throws RemoteException;

    void stopAdvertisingSet(IAdvertisingSetCallback iAdvertisingSetCallback) throws RemoteException;

    void stopScan(int i) throws RemoteException;

    void stopScanForIntent(PendingIntent pendingIntent, String str) throws RemoteException;

    void unregAll() throws RemoteException;

    void unregisterClient(int i) throws RemoteException;

    void unregisterScanner(int i) throws RemoteException;

    void unregisterServer(int i) throws RemoteException;

    void unregisterSync(IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException;

    void writeCharacteristic(int i, String str, int i2, int i3, int i4, byte[] bArr) throws RemoteException;

    void writeDescriptor(int i, String str, int i2, int i3, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothGatt {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGatt";
        static final int TRANSACTION_addService = 48;
        static final int TRANSACTION_beginReliableWrite = 36;
        static final int TRANSACTION_clearServices = 50;
        static final int TRANSACTION_clientConnect = 23;
        static final int TRANSACTION_clientDisconnect = 24;
        static final int TRANSACTION_clientReadPhy = 26;
        static final int TRANSACTION_clientSetPreferredPhy = 25;
        static final int TRANSACTION_configureMTU = 39;
        static final int TRANSACTION_connectionParameterUpdate = 40;
        static final int TRANSACTION_disconnectAll = 53;
        static final int TRANSACTION_discoverServiceByUuid = 29;
        static final int TRANSACTION_discoverServices = 28;
        static final int TRANSACTION_enableAdvertisingSet = 12;
        static final int TRANSACTION_endReliableWrite = 37;
        static final int TRANSACTION_flushPendingBatchResults = 8;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 1;
        static final int TRANSACTION_getOwnAddress = 11;
        static final int TRANSACTION_leConnectionUpdate = 41;
        static final int TRANSACTION_numHwTrackFiltersAvailable = 55;
        static final int TRANSACTION_readCharacteristic = 30;
        static final int TRANSACTION_readDescriptor = 33;
        static final int TRANSACTION_readRemoteRssi = 38;
        static final int TRANSACTION_readUsingCharacteristicUuid = 31;
        static final int TRANSACTION_refreshDevice = 27;
        static final int TRANSACTION_registerClient = 21;
        static final int TRANSACTION_registerForNotification = 35;
        static final int TRANSACTION_registerScanner = 2;
        static final int TRANSACTION_registerServer = 42;
        static final int TRANSACTION_registerSync = 19;
        static final int TRANSACTION_removeService = 49;
        static final int TRANSACTION_sendNotification = 52;
        static final int TRANSACTION_sendResponse = 51;
        static final int TRANSACTION_serverConnect = 44;
        static final int TRANSACTION_serverDisconnect = 45;
        static final int TRANSACTION_serverReadPhy = 47;
        static final int TRANSACTION_serverSetPreferredPhy = 46;
        static final int TRANSACTION_setAdvertisingData = 13;
        static final int TRANSACTION_setAdvertisingParameters = 15;
        static final int TRANSACTION_setPeriodicAdvertisingData = 17;
        static final int TRANSACTION_setPeriodicAdvertisingEnable = 18;
        static final int TRANSACTION_setPeriodicAdvertisingParameters = 16;
        static final int TRANSACTION_setScanResponseData = 14;
        static final int TRANSACTION_startAdvertisingSet = 9;
        static final int TRANSACTION_startScan = 4;
        static final int TRANSACTION_startScanForIntent = 5;
        static final int TRANSACTION_stopAdvertisingSet = 10;
        static final int TRANSACTION_stopScan = 7;
        static final int TRANSACTION_stopScanForIntent = 6;
        static final int TRANSACTION_unregAll = 54;
        static final int TRANSACTION_unregisterClient = 22;
        static final int TRANSACTION_unregisterScanner = 3;
        static final int TRANSACTION_unregisterServer = 43;
        static final int TRANSACTION_unregisterSync = 20;
        static final int TRANSACTION_writeCharacteristic = 32;
        static final int TRANSACTION_writeDescriptor = 34;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getDevicesMatchingConnectionStates";
                case 2:
                    return "registerScanner";
                case 3:
                    return "unregisterScanner";
                case 4:
                    return "startScan";
                case 5:
                    return "startScanForIntent";
                case 6:
                    return "stopScanForIntent";
                case 7:
                    return "stopScan";
                case 8:
                    return "flushPendingBatchResults";
                case 9:
                    return "startAdvertisingSet";
                case 10:
                    return "stopAdvertisingSet";
                case 11:
                    return "getOwnAddress";
                case 12:
                    return "enableAdvertisingSet";
                case 13:
                    return "setAdvertisingData";
                case 14:
                    return "setScanResponseData";
                case 15:
                    return "setAdvertisingParameters";
                case 16:
                    return "setPeriodicAdvertisingParameters";
                case 17:
                    return "setPeriodicAdvertisingData";
                case 18:
                    return "setPeriodicAdvertisingEnable";
                case 19:
                    return "registerSync";
                case 20:
                    return "unregisterSync";
                case 21:
                    return "registerClient";
                case 22:
                    return "unregisterClient";
                case 23:
                    return "clientConnect";
                case 24:
                    return "clientDisconnect";
                case 25:
                    return "clientSetPreferredPhy";
                case 26:
                    return "clientReadPhy";
                case 27:
                    return "refreshDevice";
                case 28:
                    return "discoverServices";
                case 29:
                    return "discoverServiceByUuid";
                case 30:
                    return "readCharacteristic";
                case 31:
                    return "readUsingCharacteristicUuid";
                case 32:
                    return "writeCharacteristic";
                case 33:
                    return "readDescriptor";
                case 34:
                    return "writeDescriptor";
                case 35:
                    return "registerForNotification";
                case 36:
                    return "beginReliableWrite";
                case 37:
                    return "endReliableWrite";
                case 38:
                    return "readRemoteRssi";
                case 39:
                    return "configureMTU";
                case 40:
                    return "connectionParameterUpdate";
                case 41:
                    return "leConnectionUpdate";
                case 42:
                    return "registerServer";
                case 43:
                    return "unregisterServer";
                case 44:
                    return "serverConnect";
                case 45:
                    return "serverDisconnect";
                case 46:
                    return "serverSetPreferredPhy";
                case 47:
                    return "serverReadPhy";
                case 48:
                    return "addService";
                case 49:
                    return "removeService";
                case 50:
                    return "clearServices";
                case 51:
                    return "sendResponse";
                case 52:
                    return "sendNotification";
                case 53:
                    return "disconnectAll";
                case 54:
                    return "unregAll";
                case 55:
                    return "numHwTrackFiltersAvailable";
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

        public static IBluetoothGatt asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothGatt)) {
                return (IBluetoothGatt) iInterfaceQueryLocalInterface;
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
                    List<BluetoothDevice> devicesMatchingConnectionStates = getDevicesMatchingConnectionStates(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesMatchingConnectionStates);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerScanner(IScannerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? WorkSource.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterScanner(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    startScan(parcel.readInt(), parcel.readInt() != 0 ? ScanSettings.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(ScanFilter.CREATOR), parcel.readArrayList(getClass().getClassLoader()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    startScanForIntent(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ScanSettings.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(ScanFilter.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopScanForIntent(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopScan(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    flushPendingBatchResults(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    startAdvertisingSet(parcel.readInt() != 0 ? AdvertisingSetParameters.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PeriodicAdvertisingParameters.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), IAdvertisingSetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopAdvertisingSet(IAdvertisingSetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    getOwnAddress(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableAdvertisingSet(parcel.readInt(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAdvertisingData(parcel.readInt(), parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setScanResponseData(parcel.readInt(), parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAdvertisingParameters(parcel.readInt(), parcel.readInt() != 0 ? AdvertisingSetParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPeriodicAdvertisingParameters(parcel.readInt(), parcel.readInt() != 0 ? PeriodicAdvertisingParameters.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPeriodicAdvertisingData(parcel.readInt(), parcel.readInt() != 0 ? AdvertiseData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPeriodicAdvertisingEnable(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerSync(parcel.readInt() != 0 ? ScanResult.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), IPeriodicAdvertisingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSync(IPeriodicAdvertisingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerClient(parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, IBluetoothGattCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterClient(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    clientConnect(parcel.readInt(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    clientDisconnect(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    clientSetPreferredPhy(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    clientReadPhy(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    refreshDevice(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    discoverServices(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    discoverServiceByUuid(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    readCharacteristic(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    readUsingCharacteristicUuid(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    writeCharacteristic(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    readDescriptor(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    writeDescriptor(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerForNotification(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    beginReliableWrite(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    endReliableWrite(parcel.readInt(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    readRemoteRssi(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    configureMTU(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    connectionParameterUpdate(parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    leConnectionUpdate(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerServer(parcel.readInt() != 0 ? ParcelUuid.CREATOR.createFromParcel(parcel) : null, IBluetoothGattServerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterServer(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    serverConnect(parcel.readInt(), parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    serverDisconnect(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    serverSetPreferredPhy(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    serverReadPhy(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    addService(parcel.readInt(), parcel.readInt() != 0 ? BluetoothGattService.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeService(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearServices(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendResponse(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendNotification(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnectAll();
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregAll();
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNumHwTrackFiltersAvailable = numHwTrackFiltersAvailable();
                    parcel2.writeNoException();
                    parcel2.writeInt(iNumHwTrackFiltersAvailable);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothGatt {
            public static IBluetoothGatt sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothGatt
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerScanner(IScannerCallback iScannerCallback, WorkSource workSource) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iScannerCallback != null ? iScannerCallback.asBinder() : null);
                    if (workSource != null) {
                        parcelObtain.writeInt(1);
                        workSource.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerScanner(iScannerCallback, workSource);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterScanner(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterScanner(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startScan(int i, ScanSettings scanSettings, List<ScanFilter> list, List list2, String str, String str2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (scanSettings != null) {
                        parcelObtain.writeInt(1);
                        scanSettings.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeList(list2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startScan(i, scanSettings, list, list2, str, str2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startScanForIntent(PendingIntent pendingIntent, ScanSettings scanSettings, List<ScanFilter> list, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (scanSettings != null) {
                        parcelObtain.writeInt(1);
                        scanSettings.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startScanForIntent(pendingIntent, scanSettings, list, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopScanForIntent(PendingIntent pendingIntent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopScanForIntent(pendingIntent, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopScan(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopScan(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void flushPendingBatchResults(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().flushPendingBatchResults(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startAdvertisingSet(AdvertisingSetParameters advertisingSetParameters, AdvertiseData advertiseData, AdvertiseData advertiseData2, PeriodicAdvertisingParameters periodicAdvertisingParameters, AdvertiseData advertiseData3, int i, int i2, IAdvertisingSetCallback iAdvertisingSetCallback) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (advertisingSetParameters != null) {
                        parcelObtain.writeInt(1);
                        advertisingSetParameters.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (advertiseData != null) {
                        parcelObtain.writeInt(1);
                        advertiseData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (advertiseData2 != null) {
                        parcelObtain.writeInt(1);
                        advertiseData2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (periodicAdvertisingParameters != null) {
                        parcelObtain.writeInt(1);
                        periodicAdvertisingParameters.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (advertiseData3 != null) {
                        parcelObtain.writeInt(1);
                        advertiseData3.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAdvertisingSetCallback != null ? iAdvertisingSetCallback.asBinder() : null);
                    try {
                        if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().startAdvertisingSet(advertisingSetParameters, advertiseData, advertiseData2, periodicAdvertisingParameters, advertiseData3, i, i2, iAdvertisingSetCallback);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopAdvertisingSet(IAdvertisingSetCallback iAdvertisingSetCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAdvertisingSetCallback != null ? iAdvertisingSetCallback.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopAdvertisingSet(iAdvertisingSetCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void getOwnAddress(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getOwnAddress(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void enableAdvertisingSet(int i, boolean z, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableAdvertisingSet(i, z, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (advertiseData != null) {
                        parcelObtain.writeInt(1);
                        advertiseData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAdvertisingData(i, advertiseData);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setScanResponseData(int i, AdvertiseData advertiseData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (advertiseData != null) {
                        parcelObtain.writeInt(1);
                        advertiseData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setScanResponseData(i, advertiseData);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingSetParameters) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (advertisingSetParameters != null) {
                        parcelObtain.writeInt(1);
                        advertisingSetParameters.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAdvertisingParameters(i, advertisingSetParameters);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicAdvertisingParameters) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (periodicAdvertisingParameters != null) {
                        parcelObtain.writeInt(1);
                        periodicAdvertisingParameters.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPeriodicAdvertisingParameters(i, periodicAdvertisingParameters);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingData(int i, AdvertiseData advertiseData) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (advertiseData != null) {
                        parcelObtain.writeInt(1);
                        advertiseData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPeriodicAdvertisingData(i, advertiseData);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingEnable(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPeriodicAdvertisingEnable(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerSync(ScanResult scanResult, int i, int i2, IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (scanResult != null) {
                        parcelObtain.writeInt(1);
                        scanResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iPeriodicAdvertisingCallback != null ? iPeriodicAdvertisingCallback.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSync(scanResult, i, i2, iPeriodicAdvertisingCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterSync(IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPeriodicAdvertisingCallback != null ? iPeriodicAdvertisingCallback.asBinder() : null);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSync(iPeriodicAdvertisingCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerClient(ParcelUuid parcelUuid, IBluetoothGattCallback iBluetoothGattCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBluetoothGattCallback != null ? iBluetoothGattCallback.asBinder() : null);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClient(parcelUuid, iBluetoothGattCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterClient(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterClient(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientConnect(int i, String str, boolean z, int i2, boolean z2, int i3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    int i4 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (!z2) {
                        i4 = 0;
                    }
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clientConnect(i, str, z, i2, z2, i3);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientDisconnect(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clientDisconnect(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clientSetPreferredPhy(i, str, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientReadPhy(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clientReadPhy(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void refreshDevice(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().refreshDevice(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void discoverServices(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().discoverServices(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void discoverServiceByUuid(int i, String str, ParcelUuid parcelUuid) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().discoverServiceByUuid(i, str, parcelUuid);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readCharacteristic(int i, String str, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().readCharacteristic(i, str, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readUsingCharacteristicUuid(int i, String str, ParcelUuid parcelUuid, int i2, int i3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().readUsingCharacteristicUuid(i, str, parcelUuid, i2, i3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void writeCharacteristic(int i, String str, int i2, int i3, int i4, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().writeCharacteristic(i, str, i2, i3, i4, bArr);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readDescriptor(int i, String str, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().readDescriptor(i, str, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void writeDescriptor(int i, String str, int i2, int i3, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().writeDescriptor(i, str, i2, i3, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerForNotification(int i, String str, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerForNotification(i, str, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void beginReliableWrite(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().beginReliableWrite(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void endReliableWrite(int i, String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endReliableWrite(i, str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readRemoteRssi(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().readRemoteRssi(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void configureMTU(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().configureMTU(i, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void connectionParameterUpdate(int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectionParameterUpdate(i, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void leConnectionUpdate(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeInt(i7);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().leConnectionUpdate(i, str, i2, i3, i4, i5, i6, i7);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerServer(ParcelUuid parcelUuid, IBluetoothGattServerCallback iBluetoothGattServerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelUuid != null) {
                        parcelObtain.writeInt(1);
                        parcelUuid.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBluetoothGattServerCallback != null ? iBluetoothGattServerCallback.asBinder() : null);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerServer(parcelUuid, iBluetoothGattServerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterServer(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterServer(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverConnect(int i, String str, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().serverConnect(i, str, z, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverDisconnect(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().serverDisconnect(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverSetPreferredPhy(int i, String str, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().serverSetPreferredPhy(i, str, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverReadPhy(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().serverReadPhy(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void addService(int i, BluetoothGattService bluetoothGattService) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bluetoothGattService != null) {
                        parcelObtain.writeInt(1);
                        bluetoothGattService.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addService(i, bluetoothGattService);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void removeService(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeService(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clearServices(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearServices(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void sendResponse(int i, String str, int i2, int i3, int i4, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendResponse(i, str, i2, i3, i4, bArr);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void sendNotification(int i, String str, int i2, boolean z, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendNotification(i, str, i2, z, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void disconnectAll() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectAll();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregAll() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregAll();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public int numHwTrackFiltersAvailable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().numHwTrackFiltersAvailable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothGatt iBluetoothGatt) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothGatt == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothGatt;
            return true;
        }

        public static IBluetoothGatt getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
