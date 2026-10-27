package ts.car.service.androidauto;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoAppManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO_APP = "car.permission.ANDROIDAUTO_APP";

    boolean getAndroidAutoAppStatus(int i8) throws RemoteException;

    int getAndroidAutoSessionStatus() throws RemoteException;

    List<String> getAvailableDevices() throws RemoteException;

    int getLastAvailableCapability(String str) throws RemoteException;

    String getLastAvailableDevice() throws RemoteException;

    MediaMetadata getMediaMetadata() throws RemoteException;

    int getMediaProgress() throws RemoteException;

    int getMusicStatus() throws RemoteException;

    String getProjectionBluetoothAddress() throws RemoteException;

    int getVideoFocusStatus() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void previous() throws RemoteException;

    void registerAndroidAutoAppListener(IAndroidAutoAppListener iAndroidAutoAppListener) throws RemoteException;

    void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    void registerBatteryStatusListener(IBatteryStatusListener iBatteryStatusListener) throws RemoteException;

    void registerMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;

    void registerNavigationDataListener(INavigationDataListener iNavigationDataListener) throws RemoteException;

    void requestUi(int i8) throws RemoteException;

    void sendKeyEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent, int i8) throws RemoteException;

    void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent) throws RemoteException;

    void unregisterAndroidAutoAppListener(IAndroidAutoAppListener iAndroidAutoAppListener) throws RemoteException;

    void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    void unregisterBatteryStatusListener(IBatteryStatusListener iBatteryStatusListener) throws RemoteException;

    void unregisterMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;

    void unregisterNavigationDataListener(INavigationDataListener iNavigationDataListener) throws RemoteException;
}
