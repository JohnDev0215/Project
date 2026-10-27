package ts.car.service.power;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes.dex */
public interface IPowerManager extends IPlatformService {
    void addBackLightListener(IPowerBackLightListener iPowerBackLightListener) throws RemoteException;

    void addPowerModeCallback(IPowerModeListener iPowerModeListener) throws RemoteException;

    void addPowerSignalListener(IPowerSignalListener iPowerSignalListener) throws RemoteException;

    void addPowerStateListener(IPowerStateListener iPowerStateListener) throws RemoteException;

    void closeBackLight(int i) throws RemoteException;

    int getBackLightBrightness(int i) throws RemoteException;

    boolean getBackLightState(int i) throws RemoteException;

    int getPowerMode() throws RemoteException;

    int getPowerSignal(int i) throws RemoteException;

    int getPowerState() throws RemoteException;

    IBackLightLock newBackLightLock(String str, int i) throws RemoteException;

    void openBackLight(int i) throws RemoteException;

    void removeBackLightListener(IPowerBackLightListener iPowerBackLightListener) throws RemoteException;

    void removePowerModeCallback(IPowerModeListener iPowerModeListener) throws RemoteException;

    void removePowerSignalListener(IPowerSignalListener iPowerSignalListener) throws RemoteException;

    void removePowerStateListener(IPowerStateListener iPowerStateListener) throws RemoteException;

    void setBackLightBrightness(int i, int i2) throws RemoteException;
}
