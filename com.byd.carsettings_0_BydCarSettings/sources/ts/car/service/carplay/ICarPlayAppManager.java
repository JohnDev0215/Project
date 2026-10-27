package ts.car.service.carplay;

import android.os.RemoteException;
import androidx.annotation.RequiresPermission;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface ICarPlayAppManager extends IPlatformService {
    public static final String PERMISSION_CARPLAY_APP = "car.permission.CARPLAY_APP";

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void acceptCall(int i, String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void endCall(int i, String str) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean getCarPlayDisplayStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    int getCarPlaySessionStatus() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void initiateCall(int i, String str, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void playSong(PlaybackMediaItemInfo playbackMediaItemInfo) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerCallStateListener(ICallStateListener iCallStateListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerCarPlayAppListener(ICarPlayAppListener iCarPlayAppListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerContactListListener(IContactListListener iContactListListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerNowPlayingListener(INowPlayingListener iNowPlayingListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean registerRouteGuidanceListener(IRouteGuidanceListener iRouteGuidanceListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void requestSiri(int i, long j) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void requestUi(int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    default void sendDtmf(int i, String str) throws RemoteException {
    }

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void sendRemoteMediaPlayback(int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void setEnhancedSiriInfo(List<String> list, String str, int i) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    int setMapAppearanceMode(String str, int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void setNativeAppState(int i, boolean z, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void setSongElapsedTime(long j) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    int setUiAppearanceMode(String str, int i, int i2) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    void swapCalls() throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterCallStateListener(ICallStateListener iCallStateListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterCarPlayAppListener(ICarPlayAppListener iCarPlayAppListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterCarPlayStateListener(ICarPlayStateListener iCarPlayStateListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterContactListListener(IContactListListener iContactListListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterNowPlayingListener(INowPlayingListener iNowPlayingListener) throws RemoteException;

    @RequiresPermission(PERMISSION_CARPLAY_APP)
    boolean unregisterRouteGuidanceListener(IRouteGuidanceListener iRouteGuidanceListener) throws RemoteException;
}
