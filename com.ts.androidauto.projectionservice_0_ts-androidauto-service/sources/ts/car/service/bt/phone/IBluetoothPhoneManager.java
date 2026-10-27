package ts.car.service.bt.phone;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IBluetoothPhoneManager extends IPlatformService {
    public static final String ACCESS_BLUETOOTH_EXT = "android.permission.ACCESS_BLUETOOTH_EXT";
    public static final String UPDATE_BLUETOOTH_EXT = "android.permission.UPDATE_BLUETOOTH_EXT";

    boolean acceptCall(String str, int i) throws RemoteException;

    boolean connectAudio(String str) throws RemoteException;

    boolean dial(String str, String str2) throws RemoteException;

    boolean disconnectAudio(String str) throws RemoteException;

    int getAudioState(String str) throws RemoteException;

    List<CallItem> getCallList(String str) throws RemoteException;

    int getErrCode() throws RemoteException;

    boolean getInBandRing(String str) throws RemoteException;

    int getRemoteFeatures(String str) throws RemoteException;

    int getRemoteVersion(String str) throws RemoteException;

    boolean holdCall(String str) throws RemoteException;

    boolean registerListener(IBluetoothPhoneListener iBluetoothPhoneListener) throws RemoteException;

    boolean rejectCall(String str) throws RemoteException;

    boolean sendDtmf(String str, byte b) throws RemoteException;

    boolean startVoiceRecognition(String str) throws RemoteException;

    boolean stopVoiceRecognition(String str) throws RemoteException;

    boolean terminateCall(String str, int i) throws RemoteException;

    boolean unregisterListener(IBluetoothPhoneListener iBluetoothPhoneListener) throws RemoteException;
}
