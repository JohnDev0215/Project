package ts.car.service.androidauto;

import android.os.RemoteException;
import androidx.annotation.RequiresPermission;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes3.dex */
public interface IAndroidAutoAppManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO_APP = "car.permission.ANDROIDAUTO_APP";

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    boolean getAndroidAutoAppStatus(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    int getAndroidAutoSessionStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    List<String> getAvailableDevices() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    int getLastAvailableCapability(String str) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    String getLastAvailableDevice() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    MediaMetadata getMediaMetadata() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    int getMediaProgress() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    int getMusicStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    String getProjectionBluetoothAddress() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    int getVideoFocusStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void next() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void pause() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void play() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void previous() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void registerAndroidAutoAppListener(IAndroidAutoAppListener iAndroidAutoAppListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void registerBatteryStatusListener(IBatteryStatusListener iBatteryStatusListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void registerMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void registerNavigationDataListener(INavigationDataListener iNavigationDataListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void requestUi(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void sendKeyEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent, int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void unregisterAndroidAutoAppListener(IAndroidAutoAppListener iAndroidAutoAppListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void unregisterBatteryStatusListener(IBatteryStatusListener iBatteryStatusListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void unregisterMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO_APP)
    void unregisterNavigationDataListener(INavigationDataListener iNavigationDataListener) throws RemoteException;
}
