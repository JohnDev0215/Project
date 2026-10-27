package ts.car.service.bt.audio;

import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothAudioManager extends IPlatformService {
    public static final String ACCESS_BLUETOOTH_EXT = "android.permission.ACCESS_BLUETOOTH_EXT";
    public static final String UPDATE_BLUETOOTH_EXT = "android.permission.UPDATE_BLUETOOTH_EXT";

    boolean changePath(String str, int i, String str2) throws RemoteException;

    boolean fastForward(String str, boolean z) throws RemoteException;

    int getAudioFocusState() throws RemoteException;

    int getErrCode() throws RemoteException;

    int getFolderItem(String str, int i) throws RemoteException;

    boolean getItemAttributes(String str, int i, String str2) throws RemoteException;

    MetadataItem getMetaData(String str) throws RemoteException;

    int getNowPlayingList(String str) throws RemoteException;

    int getPlayState(String str) throws RemoteException;

    Map<Integer, ArrayList<Integer>> getPlayerAppSettingValuesList(String str) throws RemoteException;

    int getRemoteFeatures(String str) throws RemoteException;

    int getRemoteVersion(String str) throws RemoteException;

    boolean next(String str) throws RemoteException;

    boolean pause(String str) throws RemoteException;

    boolean play(String str) throws RemoteException;

    boolean prev(String str) throws RemoteException;

    boolean registerListener(IBluetoothAudioListener iBluetoothAudioListener) throws RemoteException;

    boolean rewind(String str, boolean z) throws RemoteException;

    int searchMedia(String str, String str2) throws RemoteException;

    boolean setPlayerAppSettings(String str, Map<Integer, Integer> map) throws RemoteException;

    boolean stop(String str) throws RemoteException;

    boolean switchTrack(String str, String str2) throws RemoteException;

    boolean unregisterListener(IBluetoothAudioListener iBluetoothAudioListener) throws RemoteException;
}
