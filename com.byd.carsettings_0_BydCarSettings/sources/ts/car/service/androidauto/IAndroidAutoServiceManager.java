package ts.car.service.androidauto;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import androidx.annotation.RequiresPermission;
import ts.car.service.IPlatformService;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes3.dex */
public interface IAndroidAutoServiceManager extends IPlatformService {
    public static final String PERMISSION_ANDROIDAUTO = "car.permission.ANDROIDAUTO";

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    int getAndroidAutoSessionStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void registerAndroidAutoServiceListener(IAndroidAutoServiceListener iAndroidAutoServiceListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    @Deprecated
    void requestUi(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    @Deprecated
    void sendKeyEvent(int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    @Deprecated
    void sendKeyEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent, int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    @Deprecated
    void sendKeyLongPressEvent(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    @Deprecated
    void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent aapHardkeyEvent) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    default void setClusterSurface(Surface surface, int i, int i2) throws RemoteException {
    }

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void setNativeAppStates(int i, boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void setNativePhoneCallStatus(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void setSurface(Surface surface, int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void setVehicleInfo(VehicleData vehicleData) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void unregisterAndroidAutoServiceListener(IAndroidAutoServiceListener iAndroidAutoServiceListener) throws RemoteException;

    @RequiresPermission(PERMISSION_ANDROIDAUTO)
    void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener iAndroidAutoSessionStatusListener) throws RemoteException;
}
