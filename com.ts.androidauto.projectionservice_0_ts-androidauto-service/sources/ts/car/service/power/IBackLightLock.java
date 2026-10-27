package ts.car.service.power;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IBackLightLock {
    void acquireLock() throws RemoteException;

    boolean isHeld() throws RemoteException;

    void releaseLock() throws RemoteException;
}
