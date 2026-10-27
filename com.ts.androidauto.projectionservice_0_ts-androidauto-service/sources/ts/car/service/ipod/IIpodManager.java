package ts.car.service.ipod;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.ipod.data.IpodDevice;
import ts.car.service.ipod.data.IpodMediaListInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IIpodManager extends IPlatformService {
    public static final String PERMISSION_IPOD = "car.permission.IPOD";

    int connect(String str) throws RemoteException;

    int deInitPlayback() throws RemoteException;

    int disconnect(String str) throws RemoteException;

    int getCurrentTrackDuration() throws RemoteException;

    int getDeviceConnectStatus() throws RemoteException;

    String getDeviceName() throws RemoteException;

    List<IpodDevice> getIpodDevices() throws RemoteException;

    IpodMediaListInfo getMediaList(int i, int i2) throws RemoteException;

    IpodMediaListInfo getNowPlayingList(int i, int i2) throws RemoteException;

    long getNowPlayingTrackIndex() throws RemoteException;

    long getNowPlayingTracksCount() throws RemoteException;

    int getPlaybackStatus() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    IpodMediaListInfo getSubContentsAllMediaItems(int i, int i2) throws RemoteException;

    int initPlayback() throws RemoteException;

    int playCurrentSelection(int i) throws RemoteException;

    int playSongByTrackIndex(int i) throws RemoteException;

    int playSongList(int i, String[] strArr) throws RemoteException;

    int pressHidKey(int i) throws RemoteException;

    void registerIpodDeviceListener(IIpodListener iIpodListener) throws RemoteException;

    int releaseHidKey() throws RemoteException;

    int requestArtworkData() throws RemoteException;

    int requestJumpList() throws RemoteException;

    int requestSearchMediaList(String str) throws RemoteException;

    int requestSearchSpeMediaList(String str, IpodMediaListInfo ipodMediaListInfo) throws RemoteException;

    int requestTrackInfo() throws RemoteException;

    int resetMediaList() throws RemoteException;

    List<String> searchCharacterCandidates(String str) throws RemoteException;

    int selectMediaList(int i, int i2) throws RemoteException;

    int setPlayPosition(long j) throws RemoteException;

    boolean switchDevice(IpodDevice ipodDevice) throws RemoteException;

    void unregisterIpodDeviceListener(IIpodListener iIpodListener) throws RemoteException;
}
