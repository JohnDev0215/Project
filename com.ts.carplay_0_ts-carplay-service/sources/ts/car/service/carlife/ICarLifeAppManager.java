package ts.car.service.carlife;

import android.os.RemoteException;
import ts.car.service.IPlatformService;
import ts.car.service.carlife.data.MediaMetaData;

/* JADX INFO: loaded from: classes.dex */
public interface ICarLifeAppManager extends IPlatformService {
    public static final String PERMISSION_CARLIFE_APP = "car.permission.CARLIFE_APP";

    int getCarLifeSessionStatus() throws RemoteException;

    MediaMetaData getMediaMetadata() throws RemoteException;

    int getMediaProgress() throws RemoteException;

    int getMusicStatus() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void previous() throws RemoteException;

    boolean registerCarLifeAppListener(ICarLifeAppListener iCarLifeAppListener) throws RemoteException;

    boolean registerCarLifeStateListener(ICarLifeStateListener iCarLifeStateListener) throws RemoteException;

    boolean registerMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;

    boolean unregisterCarLifeAppListener(ICarLifeAppListener iCarLifeAppListener) throws RemoteException;

    boolean unregisterCarLifeStateListener(ICarLifeStateListener iCarLifeStateListener) throws RemoteException;

    boolean unregisterMediaDataListener(IMediaDataListener iMediaDataListener) throws RemoteException;
}
