package ts.car.service.carplay;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.CarPlayDevice;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes5.dex */
public interface ICarPlayDeviceListManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY_DEVICE_LIST = "car.permission.CARPLAY_DEVICE_LIST";

    boolean cancelWirelessCarPlay(String str) throws RemoteException;

    void checkWirelessCarPlayAvailability(String str, IWirelessCarPlayAvailabilityCallback iWirelessCarPlayAvailabilityCallback) throws RemoteException;

    @Deprecated
    void connectCarPlay(String str, boolean z) throws RemoteException;

    void connectCarPlay(ConnectRequest connectRequest) throws RemoteException;

    void deleteCarPlayDevice(String str) throws RemoteException;

    void disconnectCarPlay(String str) throws RemoteException;

    CarPlayDevice getActivedWirelessDevice() throws RemoteException;

    CarPlayDevice getCarPlayDevice(String str) throws RemoteException;

    List<CarPlayDevice> getCarPlayDeviceList() throws RemoteException;

    List<CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException;

    boolean registerCarPlayDeviceListListener(ICarPlayDeviceListListener iCarPlayDeviceListListener) throws RemoteException;

    boolean unregisterCarPlayDeviceListListener(ICarPlayDeviceListListener iCarPlayDeviceListListener) throws RemoteException;
}
