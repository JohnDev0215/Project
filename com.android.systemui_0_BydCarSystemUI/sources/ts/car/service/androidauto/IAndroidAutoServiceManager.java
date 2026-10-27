package ts.car.service.androidauto;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoServiceManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO = "car.permission.ANDROIDAUTO";

    int getAndroidAutoSessionStatus() throws RemoteException;

    void registerAndroidAutoServiceListener(IAndroidAutoServiceListener iAndroidAutoServiceListener) throws RemoteException;

    void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    @Deprecated
    void requestUi(int i) throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    @Deprecated
    void sendKeyEvent(int i, int i2) throws RemoteException;

    @Deprecated
    void sendKeyEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent, int i) throws RemoteException;

    @Deprecated
    void sendKeyLongPressEvent(int i) throws RemoteException;

    @Deprecated
    void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent) throws RemoteException;

    default void setClusterSurface(Surface surface, int i, int i2) throws RemoteException {
    }

    void setNativeAppStates(int i, boolean z) throws RemoteException;

    void setNativePhoneCallStatus(int i) throws RemoteException;

    void setSurface(Surface surface, int i, int i2) throws RemoteException;

    void setVehicleInfo(VehicleData vehicleData) throws RemoteException;

    void unregisterAndroidAutoServiceListener(IAndroidAutoServiceListener iAndroidAutoServiceListener) throws RemoteException;

    void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;
}
