package ts.car.service.bt;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.bt.data.BluetoothInfo;
import ts.car.service.bt.data.DeviceItem;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothServiceManager extends IPlatformService {
    public static final String ACCESS_BLUETOOTH_EXT = "android.permission.ACCESS_BLUETOOTH_EXT";
    public static final String UPDATE_BLUETOOTH_EXT = "android.permission.UPDATE_BLUETOOTH_EXT";

    boolean cancelBond(String str) throws RemoteException;

    boolean connectDevice(String str) throws RemoteException;

    boolean connectProfile(String str, int i) throws RemoteException;

    boolean createBond(String str) throws RemoteException;

    boolean disconnectDevice(String str) throws RemoteException;

    boolean disconnectProfile(String str, int i) throws RemoteException;

    boolean getAutoConnectState() throws RemoteException;

    int getBondState(String str) throws RemoteException;

    List<DeviceItem> getBondedDevices() throws RemoteException;

    BluetoothInfo getBtInfo() throws RemoteException;

    int getBtState() throws RemoteException;

    List<DeviceItem> getConnectedDevices(int i) throws RemoteException;

    int getConnectionState(String str, int i) throws RemoteException;

    int getErrCode() throws RemoteException;

    int getMaxConnectionCount(int i) throws RemoteException;

    String getPinCode() throws RemoteException;

    boolean getProfileEnable(String str, int i) throws RemoteException;

    int getRemoteUuids(String str) throws RemoteException;

    int getScanMode() throws RemoteException;

    boolean registerListener(IBluetoothListener iBluetoothListener) throws RemoteException;

    boolean removeBond(String str) throws RemoteException;

    boolean replybondRequested(String str, int i, boolean z, String str2) throws RemoteException;

    boolean setAutoConnectEnable(boolean z) throws RemoteException;

    boolean setBtEnable(boolean z) throws RemoteException;

    boolean setName(String str) throws RemoteException;

    boolean setPinCode(String str) throws RemoteException;

    boolean setProfileEnable(String str, int i, boolean z) throws RemoteException;

    boolean setScanMode(int i) throws RemoteException;

    boolean startDiscovery() throws RemoteException;

    boolean startPeriodicDiscovery() throws RemoteException;

    boolean stopDiscovery() throws RemoteException;

    boolean storeOobDeviceInfo(String str, String str2, byte[] bArr, int i) throws RemoteException;

    boolean unregisterListener(IBluetoothListener iBluetoothListener) throws RemoteException;
}
