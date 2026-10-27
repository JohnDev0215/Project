package com.engine.tbox.iface;

import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.ApnInfo;
import com.engine.tbox.data.DigitalKeyBean;
import com.engine.tbox.data.NetworkFlow;
import com.engine.tbox.data.NetworkOperator;
import com.engine.tbox.data.NetworkQuery;
import com.engine.tbox.data.OtaNotification;
import com.engine.tbox.data.ReservationCharging;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public interface ITboxListener {
    public static final int ACTIVE_FAILED = 2;
    public static final int ACTIVE_SUCCESS = 1;
    public static final int AUTOMATIC_NETWORK_CHANGE_FAILED = 1;
    public static final int AUTOMATIC_NETWORK_CHANGE_SUCCESS = 0;
    public static final int AUTO_MODE = 0;
    public static final int EXPORT_LOG_FTP_ERROR = 1;
    public static final int EXPORT_LOG_FTP_EXIT = 6;
    public static final int EXPORT_LOG_FTP_EXITING = 5;
    public static final int EXPORT_LOG_FTP_NO_RESPONSE = 3;
    public static final int EXPORT_LOG_FTP_SPACE_ERROR = 2;
    public static final int EXPORT_LOG_FTP_UNKNOWN_ERROR = 4;
    public static final int EXPORT_LOG_SUCCESS = 0;
    public static final int EXPORT_LOG_TBOX_CONNECTION_FAILED = 5;
    public static final int MANUAL_MODE = 1;
    public static final int QUERY_TRAFFIC = 3;
    public static final int RESET_TRAFFIC = 1;
    public static final int SIM_MATCH_ERROR = 2;
    public static final int SIM_NETWORK_ERROR = 3;
    public static final int SIM_OK = 0;
    public static final int SIM_UNLOAD = 1;
    public static final int TBOX_REBOOT = 1;
    public static final int TBOX_REBOOT_FAILED = -1;
    public static final int TBOX_REJECT_RBOOT = 0;
    public static final int TBOX_STATE_CONNECTED = 0;
    public static final int TBOX_STATE_DISCONNECTED = 1;
    public static final int UPGRADE_FILE_VALIDATE_FAILED = 2;
    public static final int UPGRADE_FTP_FAILED = 1;
    public static final int UPGRADE_REQ_FAILED = 5;
    public static final int UPGRADE_SUCCESS = 0;
    public static final int UPGRADE_TBOX_CONNECTION_FAILED = 6;
    public static final int UPGRADE_TBOX_INTERNAL_ERROR = 3;
    public static final int UPGRADE_TBOX_TIMEOUT = 4;
    public static final int USER_CHARGE_PLANE_CHARGE = 1;

    @Deprecated
    public static final int USER_CHARGE_PLANE_GET_PAD_TIME = 2;
    public static final int USER_CHARGE_PLANE_INVALID = 0;

    default void upgradeResult(int result) {
        LogUtils.d(LogUtils.TAG, "upgradeResult:" + result, new Object[0]);
    }

    default void upgradeProgress(int progress) {
        LogUtils.d(LogUtils.TAG, "upgradeProgress:" + progress, new Object[0]);
    }

    default void apn2StateChanged(boolean state) {
        LogUtils.d(LogUtils.TAG, "apn2StateChanged:" + state, new Object[0]);
    }

    default void tboxConnectState(int tboxState) {
        LogUtils.d(LogUtils.TAG, "tboxConnectState:" + tboxState, new Object[0]);
    }

    default void exportLogProgress(int progress) {
        LogUtils.d(LogUtils.TAG, "exportLogProgress:" + progress, new Object[0]);
    }

    default void exportLogResult(int result) {
        LogUtils.d(LogUtils.TAG, "exportLogResult:" + result, new Object[0]);
    }

    @Deprecated
    default void networkTrafficeReqCallback(NetworkQuery query) {
        LogUtils.d(LogUtils.TAG, "networkTrafficeReqCallback:" + query, new Object[0]);
    }

    @Deprecated
    default void appActiveResult(int result) {
        LogUtils.d(LogUtils.TAG, "appActiveResult:" + result, new Object[0]);
    }

    default void appActivityResult(int apptag, int result) {
        LogUtils.d(LogUtils.TAG, "appActivityResult apptag:" + apptag + " result:" + result, new Object[0]);
    }

    default void switchCloudEnvCallback(int env) {
        LogUtils.d(LogUtils.TAG, "switchCloudEnvCallback:" + env, new Object[0]);
    }

    default void otaNotifcaticationCallback(OtaNotification nty) {
        LogUtils.d(LogUtils.TAG, "otaNotifcaticationCallback:" + nty, new Object[0]);
    }

    default void tboxChargePlaneCallback(ReservationCharging rsv) {
        LogUtils.d(LogUtils.TAG, "tboxChargePlaneCallback:" + rsv, new Object[0]);
    }

    default void userChangeChargePlane(int userPlane) {
        LogUtils.d(LogUtils.TAG, "userChangeChargePlane:" + userPlane, new Object[0]);
    }

    default void onTboxRestartChanged(int state) {
        LogUtils.d(LogUtils.TAG, "onTboxRestartChanged:" + state, new Object[0]);
    }

    default void onNetworkOperatorList(ArrayList<String> codeList) {
        LogUtils.d(LogUtils.TAG, "onNetworkOperatorList:" + codeList, new Object[0]);
    }

    @Deprecated
    default void onKeyListResponse(ArrayList<DigitalKeyBean> beans) {
        LogUtils.d(LogUtils.TAG, "onKeyListResponse:" + beans, new Object[0]);
    }

    default void onKeyListResponse(int total, ArrayList<DigitalKeyBean> beans) {
        LogUtils.d(LogUtils.TAG, "onKeyListResponse total" + total + " beans:" + Arrays.toString(beans.toArray()), new Object[0]);
    }

    @Deprecated
    default void onDeleteNfcKeyResult(int op, int result) {
        LogUtils.d(LogUtils.TAG, "onDeleteNfcKeyResult op:" + op + " result:" + result, new Object[0]);
    }

    default void onDeleteAllNfcKeyResult(int type, int result) {
        LogUtils.d(LogUtils.TAG, "onDeleteAllNfcKeyResult type:" + type + " result:" + result, new Object[0]);
    }

    default void onDeleteNfcKeyNoByKeyNoResult(String keyNo, int result) {
        LogUtils.d(LogUtils.TAG, "onDeleteNfcKeyNoByKeyNoResult keyNo:" + keyNo + " result:" + result, new Object[0]);
    }

    default void onDeleteOwnIphoneKeyResult(String keyNo, int result) {
        LogUtils.d(LogUtils.TAG, "onDeleteOwnIphoneKeyResult keyNo:" + keyNo + " result:" + result, new Object[0]);
    }

    default void onKeyListPageRefresh() {
        LogUtils.d(LogUtils.TAG, "onKeyListPageRefresh", new Object[0]);
    }

    default void onTBoxCmd2Ivi(int cmd) {
        LogUtils.d(LogUtils.TAG, "onTBoxCmd2IVI cmd:" + cmd, new Object[0]);
    }

    default void onNetworkOperatorModeChange(int state) {
        LogUtils.d(LogUtils.TAG, "onNetworkOperatorModeChange state:" + state, new Object[0]);
    }

    default void onOperatorInfoChange(ArrayList<NetworkOperator> list) {
        LogUtils.d(LogUtils.TAG, "onNetworkOperatorModeChange list:" + list, new Object[0]);
    }

    default void onManuallyStationedNetworkChange(String mccMnc, int curNetType, int state, String lastMccMnc, int lastNetType) {
        LogUtils.d(LogUtils.TAG, "onNetworkOperatorModeChange mccMnc:" + mccMnc + " curNetType:" + curNetType + " state:" + state + " lastMccMnc:" + lastMccMnc + " lastNetType:" + lastNetType, new Object[0]);
    }

    default void onAutomaticNetworkChange(int state) {
        LogUtils.d(LogUtils.TAG, "onAutomaticNetworkChange state:" + state, new Object[0]);
    }

    default void onSimCardStateChange(int status) {
        LogUtils.d(LogUtils.TAG, "onSimCardStateChange status:" + status, new Object[0]);
    }

    default void sendApnArgsResult(int result) {
        LogUtils.d(LogUtils.TAG, "sendApnArgsResult:" + result, new Object[0]);
    }

    default void onApnArgsNty(ApnArgs args) {
        LogUtils.d(LogUtils.TAG, "onApnArgsNty:" + args, new Object[0]);
    }

    default void onNetworkFlowNty(NetworkFlow flow) {
        LogUtils.d(LogUtils.TAG, "onNetworkFlowNty", new Object[0]);
    }

    default void onTboxNetworkState(int apn, int state) {
        LogUtils.d(LogUtils.TAG, "onTboxNetworkState apn:" + apn + "state:" + state, new Object[0]);
    }

    default void onTboxNetworkStateResult(ApnInfo info) {
        LogUtils.d(LogUtils.TAG, "onTboxNetworkStateResult info:" + info, new Object[0]);
    }
}
