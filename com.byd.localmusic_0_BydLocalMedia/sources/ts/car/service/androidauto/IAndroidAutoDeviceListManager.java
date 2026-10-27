package ts.car.service.androidauto;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;

/* JADX INFO: loaded from: classes5.dex */
public interface IAndroidAutoDeviceListManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO_DEVICELIST = "car.permission.ANDROIDAUTO_DEVICELIST";

    void connectAndroidAuto(String str, boolean z) throws RemoteException;

    void connectAndroidAuto(ConnectRequest connectRequest) throws RemoteException;

    void deleteAndroidAutoDevice(String str) throws RemoteException;

    void disconnectAndroidAuto(String str) throws RemoteException;

    AndroidAutoDevice getAndroidAutoDevice(String str) throws RemoteException;

    List<AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException;

    List<AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException;

    void registerAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener) throws RemoteException;

    void unregisterAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener) throws RemoteException;
}
