package ts.car.service.carplay;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPlayServiceManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY = "car.permission.CARPLAY";

    void changeVideoFocus(int i8, boolean z7, String str, int i9) throws RemoteException;

    void disconnectCarPlaySession() throws RemoteException;

    String getBluetoothMacAddress() throws RemoteException;

    int getCarPlaySessionStatus() throws RemoteException;

    boolean getLastUserMode(int i8) throws RemoteException;

    int getSupportFeatures() throws RemoteException;

    boolean registerCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    boolean registerCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    void requestSiri(int i8) throws RemoteException;

    void requestSiri(int i8, long j8) throws RemoteException;

    void requestUi(int i8) throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    void sendCarPlayKey(int i8, int i9) throws RemoteException;

    void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException;

    void setLastUserMode(int i8, boolean z7) throws RemoteException;

    void setLimitedUi(boolean z7) throws RemoteException;

    void setNativeAppState(int i8, boolean z7, int i9) throws RemoteException;

    void setNightMode(boolean z7) throws RemoteException;

    void setSurface(Surface surface) throws RemoteException;

    default void setSurface(Surface surface, int i8) throws RemoteException {
    }

    void showUi(int i8, int i9) throws RemoteException;

    void stopUi(int i8) throws RemoteException;

    boolean unregisterCarPlayServiceListener(ICarPlayServiceListener iCarPlayServiceListener) throws RemoteException;

    boolean unregisterCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    default void updateViewArea(String str, int i8, int[] iArr, int i9) throws RemoteException {
    }
}
