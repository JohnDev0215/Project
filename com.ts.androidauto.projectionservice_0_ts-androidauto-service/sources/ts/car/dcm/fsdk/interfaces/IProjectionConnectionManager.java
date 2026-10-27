package ts.car.dcm.fsdk.interfaces;

import android.os.RemoteException;
import com.ts.car.core.annotation.FoundationInterface;
import java.util.List;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.WifiApConfiguration;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
@FoundationInterface
public interface IProjectionConnectionManager extends IPlatformService {
    default void registerListener(IProjectionConnectionListener listener, int type) throws RemoteException {
    }

    default void unregisterListener(IProjectionConnectionListener listener, int type) throws RemoteException {
    }

    default boolean isEnableProjection(int service) throws RemoteException {
        return false;
    }

    default List<CmDevice> getDeviceList(int service) throws RemoteException {
        return null;
    }

    default void updateConnectionStatus(String devId, int capa, int status, int code) throws RemoteException {
    }

    default boolean doUsbRoleSwitch(String devId, int mode, int purpose) throws RemoteException {
        return false;
    }

    default boolean resetUsb(String devId) throws RemoteException {
        return false;
    }

    default boolean enableUsbAoa(String devId, int mode, boolean flag) throws RemoteException {
        return false;
    }

    default boolean enableUsbAoa(String devId, String serialNumber, int mode, boolean flag) throws RemoteException {
        return false;
    }

    default boolean disableBluetooth(String devId, String mac) throws RemoteException {
        return false;
    }

    default BtOobInfo getBtOobInfo() throws RemoteException {
        return null;
    }

    default void setBtOobInfo(String name, String mac, String linkKey, int linkKeyType) throws RemoteException {
    }

    default boolean mergeDevices(String devId, String key, int keyType) throws RemoteException {
        return false;
    }

    default boolean mergeDevicesByUsbAndBt(String usb, String bt) throws RemoteException {
        return false;
    }

    @Deprecated
    default void enableWifiAp(boolean enable, WifiApConfiguration config) throws RemoteException {
    }

    default void updateProjectionCapability(String id, int capability, boolean flag, int reason) throws RemoteException {
    }

    default boolean updateDeviceFriendlyName(String devId, String name) throws RemoteException {
        return false;
    }

    default boolean updateUsbDeviceName(String devId, String name) throws RemoteException {
        return false;
    }

    default void updateIapSessionState(String id, int state, int reason) throws RemoteException {
    }

    default int getSupportedWifiChannel() throws RemoteException {
        return 0;
    }

    default void setMobileDeviceId(String devId, String mobileDeviceId) {
    }
}
