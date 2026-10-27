package ts.car.service.carplay;

import android.os.RemoteException;
import androidx.annotation.RequiresPermission;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes3.dex */
public interface ICarPlayDeviceListManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY_DEVICE_LIST = "car.permission.CARPLAY_DEVICE_LIST";

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    boolean cancelWirelessCarPlay(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    void checkWirelessCarPlayAvailability(String str, IWirelessCarPlayAvailabilityCallback iWirelessCarPlayAvailabilityCallback) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    @Deprecated
    void connectCarPlay(String str, boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    void connectCarPlay(ConnectRequest connectRequest) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    void deleteCarPlayDevice(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    void disconnectCarPlay(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    CarPlayDevice getActivedWirelessDevice() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    CarPlayDevice getCarPlayDevice(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    List<CarPlayDevice> getCarPlayDeviceList() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    List<CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    boolean registerCarPlayDeviceListListener(ICarPlayDeviceListListener iCarPlayDeviceListListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_DEVICE_LIST)
    boolean unregisterCarPlayDeviceListListener(ICarPlayDeviceListListener iCarPlayDeviceListListener) throws RemoteException;
}
