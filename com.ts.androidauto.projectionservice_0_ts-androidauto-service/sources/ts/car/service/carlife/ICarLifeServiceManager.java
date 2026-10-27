package ts.car.service.carlife;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import ts.car.service.IPlatformService;
import ts.car.service.carlife.data.CarLifeConfig;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarLifeServiceManager extends IPlatformService {
    public static final String PERMISSION_CARLIFE = "car.permission.CARLIFE";

    int getCarLifeSessionStatus() throws RemoteException;

    void launch(int i) throws RemoteException;

    boolean registerCarLifeServiceListener(ICarLifeServiceListener iCarLifeServiceListener) throws RemoteException;

    boolean registerCarLifeStateListener(ICarLifeStateListener iCarLifeStateListener) throws RemoteException;

    boolean requestMediaAudioFocus() throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    boolean sendControlCmd(int i, boolean z) throws RemoteException;

    void sendKeyEvent(int i) throws RemoteException;

    void setCarLifeConfig(CarLifeConfig carLifeConfig) throws RemoteException;

    void setSurface(Surface surface, int i, int i2) throws RemoteException;

    boolean unregisterCarLifeServiceListener(ICarLifeServiceListener iCarLifeServiceListener) throws RemoteException;

    boolean unregisterCarLifeStateListener(ICarLifeStateListener iCarLifeStateListener) throws RemoteException;
}
