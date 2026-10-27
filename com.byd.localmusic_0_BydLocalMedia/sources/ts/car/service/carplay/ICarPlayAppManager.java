package ts.car.service.carplay;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;

/* JADX INFO: loaded from: classes5.dex */
public interface ICarPlayAppManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY_APP = "car.permission.CARPLAY_APP";

    void acceptCall(int i, String str) throws RemoteException;

    void endCall(int i, String str) throws RemoteException;

    boolean getCarPlayDisplayStatus() throws RemoteException;

    int getCarPlaySessionStatus() throws RemoteException;

    void initiateCall(int i, String str, int i2) throws RemoteException;

    void playSong(PlaybackMediaItemInfo playbackMediaItemInfo) throws RemoteException;

    boolean registerCallStateListener(ICallStateListener iCallStateListener) throws RemoteException;

    boolean registerCarPlayAppListener(ICarPlayAppListener iCarPlayAppListener) throws RemoteException;

    boolean registerCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    boolean registerContactListListener(IContactListListener iContactListListener) throws RemoteException;

    boolean registerNowPlayingListener(INowPlayingListener iNowPlayingListener) throws RemoteException;

    boolean registerRouteGuidanceListener(IRouteGuidanceListener iRouteGuidanceListener) throws RemoteException;

    void requestSiri(int i, long j) throws RemoteException;

    void requestUi(int i) throws RemoteException;

    default void sendDtmf(int i, String str) throws RemoteException {
    }

    void sendRemoteMediaPlayback(int i, int i2) throws RemoteException;

    void setEnhancedSiriInfo(List<String> list, String str, int i) throws RemoteException;

    int setMapAppearanceMode(String str, int i, int i2) throws RemoteException;

    void setNativeAppState(int i, boolean z, int i2) throws RemoteException;

    void setSongElapsedTime(long j) throws RemoteException;

    int setUiAppearanceMode(String str, int i, int i2) throws RemoteException;

    void swapCalls() throws RemoteException;

    boolean unregisterCallStateListener(ICallStateListener iCallStateListener) throws RemoteException;

    boolean unregisterCarPlayAppListener(ICarPlayAppListener iCarPlayAppListener) throws RemoteException;

    boolean unregisterCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    boolean unregisterContactListListener(IContactListListener iContactListListener) throws RemoteException;

    boolean unregisterNowPlayingListener(INowPlayingListener iNowPlayingListener) throws RemoteException;

    boolean unregisterRouteGuidanceListener(IRouteGuidanceListener iRouteGuidanceListener) throws RemoteException;
}
