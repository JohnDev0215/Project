package ts.car.service.androidauto;

import android.os.RemoteException;
import androidx.annotation.RequiresPermission;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.androidauto.data.ConnectRequest;

/* JADX INFO: loaded from: classes3.dex */
public interface IAndroidAutoDeviceListManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO_DEVICELIST = "car.permission.ANDROIDAUTO_DEVICELIST";

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void connectAndroidAuto(String str, boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void connectAndroidAuto(ConnectRequest connectRequest) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void deleteAndroidAutoDevice(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void disconnectAndroidAuto(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    AndroidAutoDevice getAndroidAutoDevice(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    List<AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    List<AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void registerAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_DEVICELIST)
    void unregisterAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener) throws RemoteException;
}
