package ts.car.service.carplay;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import androidx.annotation.RequiresPermission;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayServiceManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY = "car.permission.CARPLAY";

    @RequiresPermission(PERMISSION_CARPLAY)
    void changeVideoFocus(int i, boolean z, String str, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void disconnectCarPlaySession() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    String getBluetoothMacAddress() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    int getCarPlaySessionStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    boolean getLastUserMode(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    int getSupportFeatures() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    boolean registerCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    boolean registerCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void requestSiri(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void requestSiri(int i, long j) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void requestUi(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void sendCarPlayKey(int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setLastUserMode(int i, boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setLimitedUi(boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setNativeAppState(int i, boolean z, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setNightMode(boolean z) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    void setSurface(Surface surface) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    default void setSurface(Surface surface, int i) throws RemoteException {
    }

    void showUi(int i, int i2) throws RemoteException;

    void stopUi(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    boolean unregisterCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY)
    boolean unregisterCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;
}
