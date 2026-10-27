package ts.car.service.tbox;

import android.os.RemoteException;
import ts.car.service.IPlatformService;
import ts.car.service.tbox.data.BaseStationInfo;
import ts.car.service.tbox.data.CallStateInfo;
import ts.car.service.tbox.data.MobileInfo;
import ts.car.service.tbox.data.SignalInfo;
import ts.car.service.tbox.data.TboxUpgradeInfo;
import ts.car.service.tbox.data.TboxVersion;

/* JADX INFO: loaded from: classes.dex */
public interface ITboxServiceManager extends IPlatformService {
    public static final String READ_TBOX_INFO_PERMISSION = "com.tbox.service.permission.READ_TBOX_INFORMATION";
    public static final String TBOX_CALL_PERMISSION = "com.tbox.service.permission.TBOX_CALL_CONTROL";
    public static final String TBOX_SETTING_PERMISSION = "com.tbox.service.permission.TBOX_SETTING";

    boolean answerCall(String str) throws RemoteException;

    boolean dial(int i, String str) throws RemoteException;

    boolean enableTboxTethering(boolean z) throws RemoteException;

    boolean exportTboxLog(String str, String str2, String str3) throws RemoteException;

    BaseStationInfo getBaseStationInfo() throws RemoteException;

    CallStateInfo getCallStateInfo() throws RemoteException;

    String getIccid() throws RemoteException;

    String getImei() throws RemoteException;

    String getImsi() throws RemoteException;

    int getKeepTboxAlive() throws RemoteException;

    MobileInfo getMobileInfo() throws RemoteException;

    String getMsidn() throws RemoteException;

    int getNetworkGeneration() throws RemoteException;

    String getNetworkOperator() throws RemoteException;

    String getNetworkOperatorName() throws RemoteException;

    int getNetworkRegState() throws RemoteException;

    int getNetworkType() throws RemoteException;

    String getNetworkTypeName(int i) throws RemoteException;

    String getSerialNumber() throws RemoteException;

    SignalInfo getSignalInfo() throws RemoteException;

    int getSignalStrength() throws RemoteException;

    int getSimStatus() throws RemoteException;

    int getTboxAirplaneMode() throws RemoteException;

    int getTboxConnectionState() throws RemoteException;

    int getTboxLogLevel() throws RemoteException;

    int getTboxPowerMode() throws RemoteException;

    TboxVersion getTboxVersion() throws RemoteException;

    String getVin() throws RemoteException;

    boolean hangupCall(String str) throws RemoteException;

    boolean isReady() throws RemoteException;

    boolean isTboxTetheringEnabled() throws RemoteException;

    boolean keepTboxAlive(int i) throws RemoteException;

    boolean registerTboxListener(TboxListener tboxListener) throws RemoteException;

    boolean rejectCall(String str) throws RemoteException;

    boolean resetTbox() throws RemoteException;

    boolean resetTboxNetwork(int i) throws RemoteException;

    boolean sendDtmf(String str) throws RemoteException;

    boolean sendTboxUpgradeInfo(TboxUpgradeInfo tboxUpgradeInfo) throws RemoteException;

    boolean setTboxAirplaneMode(boolean z) throws RemoteException;

    boolean setTboxLogLevel(byte b) throws RemoteException;

    boolean shutdownTbox() throws RemoteException;

    boolean unregisterTboxListener(TboxListener tboxListener) throws RemoteException;
}
