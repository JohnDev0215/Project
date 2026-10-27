package ts.car.service.carplay;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayServiceManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY = "car.permission.CARPLAY";

    void changeVideoFocus(int i, boolean z, String str, int i2) throws RemoteException;

    void disconnectCarPlaySession() throws RemoteException;

    String getBluetoothMacAddress() throws RemoteException;

    int getCarPlaySessionStatus() throws RemoteException;

    boolean getLastUserMode(int i) throws RemoteException;

    int getSupportFeatures() throws RemoteException;

    boolean registerCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    boolean registerCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    void requestSiri(int i) throws RemoteException;

    void requestSiri(int i, long j) throws RemoteException;

    void requestUi(int i) throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    void sendCarPlayKey(int i, int i2) throws RemoteException;

    void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException;

    void setLastUserMode(int i, boolean z) throws RemoteException;

    void setLimitedUi(boolean z) throws RemoteException;

    void setNativeAppState(int i, boolean z, int i2) throws RemoteException;

    void setNightMode(boolean z) throws RemoteException;

    void setSurface(Surface surface) throws RemoteException;

    default void setSurface(Surface surface, int i) throws RemoteException {
    }

    boolean unregisterCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    boolean unregisterCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;
}
