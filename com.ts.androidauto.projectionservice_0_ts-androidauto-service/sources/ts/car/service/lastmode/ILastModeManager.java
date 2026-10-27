package ts.car.service.lastmode;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface ILastModeManager extends IPlatformService {
    int getLastModeState(int i) throws RemoteException;

    int setLastModeState(int i, int i2) throws RemoteException;
}
