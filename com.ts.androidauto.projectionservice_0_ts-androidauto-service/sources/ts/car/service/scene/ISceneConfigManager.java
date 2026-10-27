package ts.car.service.scene;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface ISceneConfigManager extends IPlatformService {
    String getWindowTitle(int i, int i2) throws RemoteException;

    int getWindowType(int i, int i2) throws RemoteException;
}
