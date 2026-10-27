package com.engine.tbox.iface;

import com.engine.tbox.data.ActiveCode;
import com.engine.tbox.data.ActiveCodeV2;
import com.engine.tbox.data.Apn1Info;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.DigitalKeyAccountInfo;
import com.engine.tbox.data.NetworkQueryResult;
import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.data.TboxStaticInfo;

/* JADX INFO: loaded from: classes.dex */
public interface ITboxIface {
    public static final int CHARGE_CONTROLL_CHARGE = 1;
    public static final int CHARGE_PLAN_FAILED = 2;
    public static final int CHARGE_PLAN_INVALID = 0;
    public static final int CHARGE_PLAN_SUCCESS = 1;
    public static final int CLOUD_CONNECTED_STATE = 1;
    public static final int CLOUD_UNCONNECTED_STATE = 2;
    public static final int CLOUD_UNKNOWN_STATE = 0;
    public static final int COMMAND_FAILED = 1;
    public static final int COMMAND_INVALID_VALUE = 3;
    public static final int COMMAND_SUCCESS = 0;
    public static final int COMMAND_TIMEOUT = 2;
    public static final String DEFAULT_VIRTUAL_VIN = "tes0000000000000000";
    public static final int DELETE_TBOX_LOG = 1;
    public static final int FORMAL_ENV = 2;
    public static final int IFACE_CHANGE_IPHONE_DEVICE = 4;
    public static final int IFACE_DELETE_ALL_KEY = 6;
    public static final int IFACE_DELETE_IPHONE_KEY = 2;
    public static final int IFACE_DELETE_SINGLE_KEY = 1;
    public static final int IFACE_LOCK_DEVICE = 8;
    public static final int IFACE_UNLOCK_DEVICE = 7;
    public static final int LOG_CATEGORY_ECALL = 3;
    public static final int LOG_CATEGORY_LOCATION = 0;
    public static final int LOG_CATEGORY_MUSIC = 1;
    public static final int LOG_CATEGORY_NETWORK = 2;
    public static final int OPERA_QXDMLOG_START = 0;
    public static final int OPERA_QXDMLOG_STOP = 1;
    public static final int OTA_END_STATE = 0;
    public static final int OTA_START_STATE = 1;
    public static final int PRIVACY_SET_STATUS_AAGREE = 1;
    public static final int PRIVACY_SET_STATUS_DISAGREE = 2;
    public static final int PRIVACY_SET_STATUS_INVALID = 0;
    public static final int RESTART_TBOX = 1;
    public static final int SIM_BYD_BOTH_APN = 2;
    public static final int SIM_BYD_MULTI_APN = 3;
    public static final int SIM_BYD_SINGLE_APN = 0;
    public static final int SIM_OTHERS = 1;
    public static final int TCPDUMP_START = 0;
    public static final int TCPDUMP_STOP = 1;
    public static final int TEST_ENV = 1;
    public static final int UNKNOW_ENV = 0;
    public static final int USER_CHARGE_FAILED = 2;

    @Deprecated
    public static final int USER_CHARGE_GET_PAD_TIME_SUCCESS = 2;
    public static final int USER_CHARGE_INVALID = 0;
    public static final int USER_CHARGE_SUCCESS = 1;
    public static final int USER_PRIVACY_SET_AGREE = 1;
    public static final int USER_PRIVACY_SET_INVALID = 0;
    public static final int USER_PRIVACY_SET_REVOKE_CONSENT = 2;
    public static final int USER_PRIVACY_SET_SKIP = 3;

    default TboxStaticInfo getTboxStaticInfo() {
        return new TboxStaticInfo();
    }

    default boolean isTboxConnected() {
        return false;
    }

    default boolean upgrade(String upgradeFilePath) {
        return true;
    }

    default boolean exportLog(String path) {
        return true;
    }

    default String getSoftCode() {
        return "N/A";
    }

    default String getCloudServiceVersion() {
        return "N/A";
    }

    default int getCloudServieEv() {
        return 0;
    }

    default int getCloudServiceConnectState() {
        return 0;
    }

    default boolean switchCloudServiceEnv(int env) {
        return false;
    }

    @Deprecated
    default boolean sendActiveCode(ActiveCode code) {
        return false;
    }

    default boolean sendActiveCode(ActiveCodeV2 code) {
        return false;
    }

    @Deprecated
    default boolean sendNetworkTrafficQueryResult(NetworkQueryResult result) {
        return false;
    }

    default int notifyTBoxCmdResult(int cmd, int result) {
        return 1;
    }

    default int uploadCdr(int length, String cdrStr) {
        return 1;
    }

    default boolean enableApn2(boolean enable) {
        return false;
    }

    default boolean enableApn3(boolean enable) {
        return false;
    }

    @Deprecated
    default boolean getApn2State() {
        return false;
    }

    default boolean setOtaState(int state) {
        return false;
    }

    default boolean chargePlaneResult(int result) {
        return false;
    }

    default boolean userChargePlaneResult(int result) {
        return false;
    }

    default boolean userUpdateChargePlane(ReservationCharging rc) {
        return false;
    }

    default boolean chargeControl(int charge) {
        return false;
    }

    default boolean setRestartTbox(int state) {
        return false;
    }

    default int getNetworkOperatorList() {
        return -1;
    }

    default boolean queryKeyListReq(DigitalKeyAccountInfo info) {
        return false;
    }

    @Deprecated
    default boolean uploadNfcOpenInfo() {
        return false;
    }

    default boolean startRegisterNfcKey() {
        return false;
    }

    @Deprecated
    default boolean deleteNfcKey(int op) {
        return false;
    }

    @Deprecated
    default boolean deleteNfcKey(int op, String passId) {
        return false;
    }

    default int setNetworkOperator(String code) {
        return -1;
    }

    default boolean updateTBoxAdb(boolean open) {
        return false;
    }

    default boolean setPrivatePolicy(int auth, int action, String time, String version) {
        return false;
    }

    default boolean deleteNfcKeyByType(int type) {
        return false;
    }

    default boolean deleteNfcKeyByKeyNo(int type, String keyNo) {
        return false;
    }

    default boolean deleteOwnIphoneKey(int type, String keyNo) {
        return false;
    }

    default String getVirtualVin() {
        return "";
    }

    default boolean tcpdumpReq(int req) {
        return false;
    }

    default int deleteTboxLog(int req) {
        return -1;
    }

    @Deprecated
    default Apn1Info getApn1Info() {
        return null;
    }

    @Deprecated
    default boolean setQueryOwnersKey(int config, int op) {
        return false;
    }

    @Deprecated
    default boolean setRegisterKeyStartPairing(int config, int op) {
        return false;
    }

    @Deprecated
    default boolean setAllowStartTimeAfterUnlock(int config, int value) {
        return false;
    }

    default boolean setQueryOwnersKeyV2(byte[] data) {
        return false;
    }

    default boolean setRegisterKeyStartPairingV2(byte[] data) {
        return false;
    }

    default boolean setAllowStartTimeAfterUnlockV2(byte[] data) {
        return false;
    }

    default boolean deleteNfcFindKeyV2(byte[] data) {
        return false;
    }

    default boolean offOnlineRemoveKey97V2(byte[] data) {
        return false;
    }

    default boolean offOnlineRemoveKey98V2(byte[] data) {
        return false;
    }

    default boolean sendNfcData(short id, byte[] data) {
        return false;
    }

    default int setOperationCmdAndKeyType(int cmd, int type) {
        return 0;
    }

    default int setKeyIdOne(byte[] byteArray) {
        return 0;
    }

    default int setKeyIdTwo(byte[] arr) {
        return 0;
    }

    default boolean relayDataToTbox40A(byte[] data) {
        return false;
    }

    default boolean sendRemoteLockOrUnlockStatus(int type, int status) {
        return false;
    }

    default boolean operaQxdmLog(int start, int logCategory) {
        return false;
    }

    default boolean reqNetworkOperatorMode() {
        return false;
    }

    default boolean reqOperatorInfo() {
        return false;
    }

    default boolean setManuallyStationedNetwork(String network, int sigLevel) {
        return false;
    }

    default boolean setAutomaticNetwork() {
        return false;
    }

    default boolean reqSimCardState() {
        return false;
    }

    default boolean sendApnArgs(ApnArgs apnArgs) {
        return true;
    }

    default boolean queryApnArgs() {
        return true;
    }

    default boolean sendSimCardType(int type) {
        return true;
    }

    default boolean queryNetworkFlow(int apn, String iccid) {
        return true;
    }

    default boolean setNetworkLimit(boolean enable) {
        return true;
    }

    default boolean getTboxNetworkState(int apn) {
        return true;
    }

    default void registerListener(ITboxListener listener) {
    }

    default void unregisterListener() {
    }
}
