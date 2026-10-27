package ts.car.service.scene;

import android.os.RemoteException;
import ts.car.service.IPlatformService;
import ts.car.service.scene.data.SceneInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface ISceneManager extends IPlatformService {
    void addSceneStateListener(ISceneStateListener iSceneStateListener, int[] iArr, int i) throws RemoteException;

    boolean checkSceneCanShow(int i, int i2) throws RemoteException;

    int getSceneState(int i, int i2) throws RemoteException;

    SceneInfo getTopSceneInfo(int i) throws RemoteException;

    void removeSceneStateListener(ISceneStateListener iSceneStateListener, int i) throws RemoteException;
}
