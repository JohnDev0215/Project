package ts.car.service.dm;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.dm.data.ConnectRequest;
import ts.car.service.dm.data.UnifiedDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IDeviceManager extends IPlatformService {
    public static final String ACCESS_DM_STATE_PERMISSION = "car.permission.ACCESS_DM_STATE";
    public static final String CHANGE_DM_STATE_PERMISSION = "car.permission.CHANGE_DM_STATE";

    boolean cancelWirelessCarPlay(String str) throws RemoteException;

    void checkCarPlayWirelessCapability(String str, IWirelessCarPlayAvailabilityCallback iWirelessCarPlayAvailabilityCallback) throws RemoteException;

    int connect(ConnectRequest connectRequest) throws RemoteException;

    int connect(UnifiedDevice unifiedDevice, int i) throws RemoteException;

    boolean deleteDevice(UnifiedDevice unifiedDevice) throws RemoteException;

    boolean disconnect(UnifiedDevice unifiedDevice, int i) throws RemoteException;

    List<UnifiedDevice> getDeviceList() throws RemoteException;

    void registerListener(IDeviceManagerListener iDeviceManagerListener) throws RemoteException;

    void unregisterListener(IDeviceManagerListener iDeviceManagerListener) throws RemoteException;
}
