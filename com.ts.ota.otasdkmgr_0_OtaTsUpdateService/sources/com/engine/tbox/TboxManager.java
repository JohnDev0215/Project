package com.engine.tbox;

import android.content.Context;
import android.text.TextUtils;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ActiveCode;
import com.engine.tbox.data.ActiveCodeV2;
import com.engine.tbox.data.Apn1Info;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.DigitalKeyAccountInfo;
import com.engine.tbox.data.NetworkQueryResult;
import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.data.TboxStaticInfo;
import com.engine.tbox.iface.ITboxIface;
import com.engine.tbox.iface.ITboxListener;
import com.engine.tbox.item.TBoxConnection;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TboxManager implements ITboxIface {
    private static final int APP_APN2 = 0;
    private static final int APP_APN3 = 1;
    private static final int CODE_LENGTH = 5;
    private static final byte FIVE_0_5_7 = 7;
    private static final byte FOUR_0_4_7 = 6;
    private static final short ID_FOUR_ZORE_A = 1034;
    private static final short ID_ONE_EIGHT_C = 396;
    private static final int IN_AND_OUT_OF_CAR = 1;
    private static final int MAX_PASS_ID_LENGTH = 24;
    private static final int OUT_OF_CAR = 0;
    private static final String REMOTE_IP = "172.16.3.101";
    private static final byte SIX_0_8_7 = 8;
    private static final byte SUB_ID_NINE_EIGHT = -104;
    private static final byte SUB_ID_NINE_SEVEN = -105;
    private static final byte SUB_ID_NINE_SIX = -106;
    private static final byte SUB_ID_THREE_SIX = 54;
    private static final String TAG = "TBoxManager";
    private static final int TBOX_APN2 = 2;
    private static final int TBOX_APN3 = 3;
    private static final int TIMEOUT = 50;
    private static final byte TWO_0_2_1 = 0;
    private static final byte TWO_0_2_2 = 4;
    private static final byte TWO_0_6_7 = 5;
    private static final byte TWO_2_2_4 = 1;
    private static final byte TWO_3_2_4 = 2;
    private static final byte TWO_5_2_7 = 3;
    private static volatile TboxManager mManager;
    private TBoxConnection mConn;
    private Context mContext;
    private ITboxListener mListener;
    private final Object mStaticInfoMutex = new Object();
    private final Object mUpgradeMutex = new Object();
    private final Object mExportLogMutex = new Object();
    private final Object mNetworkTrafficMutex = new Object();
    private final Object mActiveCodeMutex = new Object();
    private final Object mCloudServiceMutex = new Object();
    private final Object mNetworkApnMutex = new Object();
    private final Object mChargeMutex = new Object();
    private final Object mOtaMutex = new Object();
    private final Object mTBoxRestartMutex = new Object();
    private final Object mTBoxOperatorMutex = new Object();
    private final Object mNfcKeyMutex = new Object();
    private final Object mPrivatePolicyMutex = new Object();
    private final Object mTBoxCmdMutex = new Object();
    private final Object mVirtualVinMutext = new Object();
    private final Object mTcpdumMutex = new Object();
    private final Object mDeleteTboxLogMutex = new Object();
    private final Object mGetApn1StateMutex = new Object();
    private final Object mQxdmLogMutex = new Object();
    private boolean mStarted = false;

    public static TboxManager getInstance(Context context) {
        if (mManager != null) {
            return mManager;
        }
        synchronized (TboxManager.class) {
            if (mManager == null) {
                mManager = new TboxManager(context);
            }
        }
        return mManager;
    }

    private TboxManager(Context context) {
        this.mContext = context;
        this.mConn = new TBoxConnection(context);
    }

    public synchronized boolean start() {
        if (!this.mStarted) {
            this.mConn.start();
            this.mStarted = true;
            System.out.println(".......start......");
        }
        return true;
    }

    public synchronized boolean stop() {
        if (this.mStarted) {
            this.mConn.stop();
            this.mStarted = false;
            System.out.println(".......stop......");
        }
        return true;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public TboxStaticInfo getTboxStaticInfo() {
        TboxStaticInfo tboxStaticInfoRequestTBoxStaticInfo;
        synchronized (this.mStaticInfoMutex) {
            tboxStaticInfoRequestTBoxStaticInfo = this.mConn.requestTBoxStaticInfo();
        }
        return tboxStaticInfoRequestTBoxStaticInfo;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean upgrade(String upgradeFilePath) {
        if (TextUtils.isEmpty(upgradeFilePath)) {
            LogUtils.e(TAG, "upgrade file path:" + upgradeFilePath, new Object[0]);
            return false;
        }
        synchronized (this.mUpgradeMutex) {
            this.mConn.startUpgrade(upgradeFilePath);
        }
        return true;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public String getSoftCode() {
        String softCode;
        synchronized (this.mStaticInfoMutex) {
            softCode = this.mConn.requestTBoxStaticInfo().getSoftCode();
        }
        return softCode;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean exportLog(String path) {
        boolean zExportLog;
        if (TextUtils.isEmpty(path)) {
            LogUtils.e(TAG, "exportLog file path:" + path, new Object[0]);
            return false;
        }
        synchronized (this.mExportLogMutex) {
            zExportLog = this.mConn.exportLog(path);
        }
        return zExportLog;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendActiveCode(ActiveCode code) {
        boolean zSendActiveCode;
        if (code == null) {
            LogUtils.e(TAG, "sendActiveCode:" + code, new Object[0]);
            return false;
        }
        synchronized (this.mActiveCodeMutex) {
            zSendActiveCode = this.mConn.sendActiveCode(code);
        }
        return zSendActiveCode;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendActiveCode(ActiveCodeV2 code) {
        boolean zSendActiveCodeV2;
        if (code == null) {
            LogUtils.e(TAG, "sendActiveCode2:" + code, new Object[0]);
            return false;
        }
        synchronized (this.mActiveCodeMutex) {
            zSendActiveCodeV2 = this.mConn.sendActiveCodeV2(code);
        }
        return zSendActiveCodeV2;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendNetworkTrafficQueryResult(NetworkQueryResult result) {
        boolean zSendNetworkTrafficQueryResult;
        if (result == null) {
            LogUtils.e(TAG, "sendNetworkTrafficQueryResult:" + result, new Object[0]);
            return false;
        }
        synchronized (this.mNetworkTrafficMutex) {
            zSendNetworkTrafficQueryResult = this.mConn.sendNetworkTrafficQueryResult(result);
        }
        return zSendNetworkTrafficQueryResult;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setOtaState(int state) {
        boolean zSendOtaState;
        synchronized (this.mOtaMutex) {
            zSendOtaState = this.mConn.sendOtaState(state);
        }
        return zSendOtaState;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean chargePlaneResult(int result) {
        boolean zSendPlaneRes;
        synchronized (this.mChargeMutex) {
            zSendPlaneRes = this.mConn.sendPlaneRes(result);
        }
        return zSendPlaneRes;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean userChargePlaneResult(int result) {
        boolean zSendPlaneChargeRes;
        synchronized (this.mChargeMutex) {
            zSendPlaneChargeRes = this.mConn.sendPlaneChargeRes(result);
        }
        return zSendPlaneChargeRes;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean userUpdateChargePlane(ReservationCharging rc) {
        boolean zSendPlaneUpload;
        if (rc == null) {
            LogUtils.e(TAG, "userUpdateChargePlane rc null", new Object[0]);
            return false;
        }
        synchronized (this.mChargeMutex) {
            zSendPlaneUpload = this.mConn.sendPlaneUpload(rc);
        }
        return zSendPlaneUpload;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean chargeControl(int charge) {
        boolean zSendChargeControl;
        synchronized (this.mChargeMutex) {
            zSendChargeControl = this.mConn.sendChargeControl(charge);
        }
        return zSendChargeControl;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public void registerListener(ITboxListener listener) {
        if (listener == null) {
            LogUtils.e(TAG, "registerListener:" + listener, new Object[0]);
            return;
        }
        synchronized (this.mConn) {
            this.mConn.registerListener(listener);
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public void unregisterListener() {
        synchronized (this.mConn) {
            this.mConn.unregisterListener();
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public String getCloudServiceVersion() {
        String strRequestCloudServiceVersion;
        synchronized (this.mCloudServiceMutex) {
            strRequestCloudServiceVersion = this.mConn.requestCloudServiceVersion();
        }
        return strRequestCloudServiceVersion;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int getCloudServieEv() {
        int iRequestCloudServiceEnv;
        synchronized (this.mCloudServiceMutex) {
            iRequestCloudServiceEnv = this.mConn.requestCloudServiceEnv();
        }
        return iRequestCloudServiceEnv;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int getCloudServiceConnectState() {
        int iRequestCloudServiceConnectState;
        synchronized (this.mCloudServiceMutex) {
            iRequestCloudServiceConnectState = this.mConn.requestCloudServiceConnectState();
        }
        return iRequestCloudServiceConnectState;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean switchCloudServiceEnv(int env) {
        boolean zRequestSwitchCloudServiceEnv;
        synchronized (this.mCloudServiceMutex) {
            zRequestSwitchCloudServiceEnv = this.mConn.requestSwitchCloudServiceEnv(env);
        }
        return zRequestSwitchCloudServiceEnv;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean enableApn2(boolean state) {
        boolean zEnableApn2;
        synchronized (this.mNetworkApnMutex) {
            zEnableApn2 = this.mConn.enableApn2(state);
        }
        return zEnableApn2;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean enableApn3(boolean enable) {
        boolean zEnableApn3;
        synchronized (this.mNetworkApnMutex) {
            zEnableApn3 = this.mConn.enableApn3(enable);
        }
        return zEnableApn3;
    }

    private boolean isValidTboxRestartState(int state) {
        return state == 1;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setRestartTbox(int state) {
        boolean restartTbox;
        if (!isValidTboxRestartState(state)) {
            LogUtils.e(TAG, "setRestartTbox:" + state, new Object[0]);
            return false;
        }
        synchronized (this.mTBoxRestartMutex) {
            restartTbox = this.mConn.setRestartTbox(state);
        }
        return restartTbox;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int getNetworkOperatorList() {
        int networkOperatorList;
        synchronized (this.mTBoxOperatorMutex) {
            networkOperatorList = this.mConn.getNetworkOperatorList();
        }
        return networkOperatorList;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int setNetworkOperator(String code) {
        int networkOperator;
        if (TextUtils.isEmpty(code) || code.length() != 5) {
            LogUtils.e(TAG, "invalid code:" + code, new Object[0]);
            return -1;
        }
        synchronized (this.mTBoxOperatorMutex) {
            networkOperator = this.mConn.setNetworkOperator(code);
        }
        return networkOperator;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean isTboxConnected() {
        if (this.mConn.isTboxConnected()) {
            return true;
        }
        try {
            boolean status = this.mConn.isTboxReachable();
            return status;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean queryKeyListReq(DigitalKeyAccountInfo info) {
        boolean zQueryKeyListReq;
        if (info == null || !info.isValidDigitalKeyAccountInfo()) {
            LogUtils.e(TAG, "queryKeyListReq with info:" + info, new Object[0]);
            return false;
        }
        synchronized (this.mNfcKeyMutex) {
            zQueryKeyListReq = this.mConn.queryKeyListReq(info);
        }
        return zQueryKeyListReq;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean uploadNfcOpenInfo() {
        boolean zUploadNfcOpenInfo;
        synchronized (this.mNfcKeyMutex) {
            zUploadNfcOpenInfo = this.mConn.uploadNfcOpenInfo();
        }
        return zUploadNfcOpenInfo;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean startRegisterNfcKey() {
        boolean zStartRegisterNfcKey;
        synchronized (this.mNfcKeyMutex) {
            zStartRegisterNfcKey = this.mConn.startRegisterNfcKey();
        }
        return zStartRegisterNfcKey;
    }

    private boolean isInValidDeleteKeyOp(int op) {
        if (op != 1 && op != 2 && op != 4 && op != 6) {
            return true;
        }
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteNfcKey(int op) {
        boolean zDeleteNfcKey;
        if (isInValidDeleteKeyOp(op)) {
            LogUtils.e(TAG, "deleteNfcKey invalid op:" + op, new Object[0]);
            return false;
        }
        synchronized (this.mNfcKeyMutex) {
            zDeleteNfcKey = this.mConn.deleteNfcKey(op, "");
        }
        return zDeleteNfcKey;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteNfcKey(int op, String keyNo) {
        boolean zDeleteNfcKey;
        if (isInValidDeleteKeyOp(op)) {
            LogUtils.e(TAG, "deleteNfcKey invalid op:" + op, new Object[0]);
            return false;
        }
        if (TextUtils.isEmpty(keyNo)) {
            LogUtils.e(TAG, "deleteNfcKey with invalid keyNo", new Object[0]);
            return false;
        }
        if (keyNo.length() > 24 || keyNo.length() == 0) {
            LogUtils.e(TAG, "deleteNfcKey Invalid keyNo:" + keyNo, new Object[0]);
            return false;
        }
        synchronized (this.mNfcKeyMutex) {
            zDeleteNfcKey = this.mConn.deleteNfcKey(op, keyNo);
        }
        return zDeleteNfcKey;
    }

    private boolean isValidTrfficCmd(int cmd) {
        if (cmd < 0 || cmd > 3) {
            return false;
        }
        return true;
    }

    private boolean isValidTrafficResult(int result) {
        if (result < 0 || result > 2) {
            return false;
        }
        return true;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int notifyTBoxCmdResult(int cmd, int result) {
        int i = 0;
        if (!isValidTrfficCmd(cmd) || !isValidTrafficResult(result)) {
            LogUtils.e(TAG, "notifyTBoxCmdResult invalid cmd:" + cmd + " result:" + result, new Object[0]);
            return 3;
        }
        synchronized (this.mPrivatePolicyMutex) {
            if (!this.mConn.sendTrafficeQueryResult(cmd, result)) {
                i = 1;
            }
        }
        return i;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int uploadCdr(int length, String cdrStr) {
        if (cdrStr == null || length > cdrStr.length()) {
            LogUtils.e(TAG, "uploadCdr invalid length:" + length + " cdrStr:" + cdrStr, new Object[0]);
            return 3;
        }
        if (this.mConn.sendTrafficeQueryResult(length, cdrStr)) {
            return 0;
        }
        return 1;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean updateTBoxAdb(boolean open) {
        return this.mConn.updateTBoxAdb(open);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setPrivatePolicy(int auth, int action, String time, String version) {
        boolean privatePolicy;
        if (time == null || version == null) {
            LogUtils.e(TAG, "setPrivatePolicy invalid auth:" + auth + " action:" + action + " time:" + time + " version:" + version, new Object[0]);
            return false;
        }
        if (!isValidAuth(auth) || !isValidAction(action)) {
            LogUtils.e(TAG, "setPrivatePolicy invalid auth:" + auth + " or action:" + action, new Object[0]);
            return false;
        }
        synchronized (this.mPrivatePolicyMutex) {
            privatePolicy = this.mConn.setPrivatePolicy(auth, action, time, version);
        }
        return privatePolicy;
    }

    private boolean isValidAction(int action) {
        if (action >= 0 && action <= 3) {
            return true;
        }
        return false;
    }

    private boolean isValidAuth(int auth) {
        if (auth >= 0 && auth <= 2) {
            return true;
        }
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public String getVirtualVin() {
        String virtualVin;
        synchronized (this.mVirtualVinMutext) {
            virtualVin = this.mConn.getVirtualVin();
        }
        return virtualVin;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteNfcKeyByType(int type) {
        boolean zDeleteNfcKeyByType;
        if (type == 2 || type == 6) {
            synchronized (this.mNfcKeyMutex) {
                zDeleteNfcKeyByType = this.mConn.deleteNfcKeyByType(type);
            }
            return zDeleteNfcKeyByType;
        }
        LogUtils.e(TAG, "deleteNfcKeyByType error nfc key type:" + type, new Object[0]);
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteNfcKeyByKeyNo(int type, String keyNo) {
        boolean zDeleteNfcKeyByKeyNo;
        if (type == 1 && !TextUtils.isEmpty(keyNo)) {
            synchronized (this.mNfcKeyMutex) {
                zDeleteNfcKeyByKeyNo = this.mConn.deleteNfcKeyByKeyNo(type, keyNo);
            }
            return zDeleteNfcKeyByKeyNo;
        }
        LogUtils.e(TAG, "deleteNfcKeyByKeyNo error nfc key type:" + type, new Object[0]);
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteOwnIphoneKey(int type, String keyNo) {
        boolean zDeleteOwnIphoneKey;
        if (type == 4 && !TextUtils.isEmpty(keyNo)) {
            synchronized (this.mNfcKeyMutex) {
                zDeleteOwnIphoneKey = this.mConn.deleteOwnIphoneKey(type, keyNo);
            }
            return zDeleteOwnIphoneKey;
        }
        LogUtils.e(TAG, "deleteOwnIphoneKey error nfc key type:" + type, new Object[0]);
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean tcpdumpReq(int req) {
        boolean zTcpdumpReq;
        if (req == 0 || req == 1) {
            synchronized (this.mTcpdumMutex) {
                zTcpdumpReq = this.mConn.tcpdumpReq(req);
            }
            return zTcpdumpReq;
        }
        LogUtils.e(TAG, "tcpdumpReq with error req:" + req, new Object[0]);
        return false;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int deleteTboxLog(int req) {
        int iDeleteTboxLog;
        if (req == 1) {
            synchronized (this.mDeleteTboxLogMutex) {
                iDeleteTboxLog = this.mConn.deleteTboxLog(req);
            }
            return iDeleteTboxLog;
        }
        LogUtils.e(TAG, "deleteTboxLog with error req:" + req, new Object[0]);
        return -1;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public Apn1Info getApn1Info() {
        Apn1Info apn1Info;
        synchronized (this.mGetApn1StateMutex) {
            apn1Info = this.mConn.getApn1Info();
        }
        return apn1Info;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setQueryOwnersKey(int config, int op) {
        synchronized (this.mNfcKeyMutex) {
            try {
                if (config == 0) {
                    return this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SIX, (byte) 0, new byte[]{(byte) op});
                }
                if (config == 1) {
                    return this.mConn.sendNfcCanQuery(ID_ONE_EIGHT_C, SUB_ID_THREE_SIX, (byte) 2, new byte[]{(byte) op});
                }
                LogUtils.e(TAG, "setQueryOwnersKey config:" + config + " op:" + op, new Object[0]);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setRegisterKeyStartPairing(int config, int op) {
        LogUtils.i(TAG, "setRegisterKeyStartPairing config:" + config + " op:" + op, new Object[0]);
        synchronized (this.mNfcKeyMutex) {
            try {
                if (config == 0) {
                    return this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SIX, (byte) 1, new byte[]{(byte) op});
                }
                if (config == 1) {
                    return this.mConn.sendNfcCanQuery(ID_ONE_EIGHT_C, SUB_ID_THREE_SIX, TWO_5_2_7, new byte[]{(byte) op});
                }
                LogUtils.e(TAG, "setRegisterKeyStartPairing config:" + config + " op:" + op, new Object[0]);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setAllowStartTimeAfterUnlock(int config, int value) {
        LogUtils.i(TAG, "setAllowStartTimeAfterUnlock config:" + config + " type:" + value, new Object[0]);
        synchronized (this.mNfcKeyMutex) {
            try {
                if (config == 0) {
                    return this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SIX, TWO_5_2_7, new byte[]{(byte) value});
                }
                if (config == 1) {
                    return this.mConn.sendNfcCanQuery(ID_ONE_EIGHT_C, SUB_ID_THREE_SIX, TWO_0_2_2, new byte[]{(byte) value});
                }
                LogUtils.e(TAG, "setAllowStartTimeAfterUnlock config:" + config + " value:" + value, new Object[0]);
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int setOperationCmdAndKeyType(int cmd, int type) {
        int i = 0;
        LogUtils.i(TAG, "setOperationCmdAndKeyType cmd:" + cmd + " type:" + type, new Object[0]);
        synchronized (this.mNfcKeyMutex) {
            if (!this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SEVEN, FOUR_0_4_7, new byte[]{(byte) cmd})) {
                LogUtils.e(TAG, "setOperationCmdAndKeyType send type failed", new Object[0]);
                return -1;
            }
            boolean result = this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SEVEN, FIVE_0_5_7, new byte[]{(byte) type});
            LogUtils.i(TAG, "setOperationCmdAndKeyType result:" + result, new Object[0]);
            if (!result) {
                i = -1;
            }
            return i;
        }
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int setKeyIdOne(byte[] byteArray) {
        int i = 0;
        LogUtils.i(TAG, "setKeyIdOne byteArray:" + Arrays.toString(byteArray), new Object[0]);
        synchronized (this.mNfcKeyMutex) {
            boolean result = this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_SEVEN, SIX_0_8_7, byteArray);
            LogUtils.i(TAG, "setKeyIdOne result:" + result, new Object[0]);
            if (!result) {
                i = -1;
            }
        }
        return i;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public int setKeyIdTwo(byte[] arr) {
        int i = 0;
        LogUtils.i(TAG, "setKeyIdTwo byteArray:" + Arrays.toString(arr), new Object[0]);
        synchronized (this.mNfcKeyMutex) {
            boolean result = this.mConn.sendNfcCanQuery(ID_FOUR_ZORE_A, SUB_ID_NINE_EIGHT, TWO_0_6_7, arr);
            LogUtils.i(TAG, "setKeyIdTwo result:" + result, new Object[0]);
            if (!result) {
                i = -1;
            }
        }
        return i;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setQueryOwnersKeyV2(byte[] data) {
        LogUtils.i(TAG, "setQueryOwnersKeyV2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_ONE_EIGHT_C, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setRegisterKeyStartPairingV2(byte[] data) {
        LogUtils.i(TAG, "setRegisterKeyStartPairingV2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_ONE_EIGHT_C, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setAllowStartTimeAfterUnlockV2(byte[] data) {
        LogUtils.i(TAG, "setAllowStartTimeAfterUnlockV2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_ONE_EIGHT_C, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean deleteNfcFindKeyV2(byte[] data) {
        LogUtils.i(TAG, "deleteNfcFindKeyV2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_FOUR_ZORE_A, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean offOnlineRemoveKey97V2(byte[] data) {
        LogUtils.i(TAG, "offOnlineRemoveKey97V2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_FOUR_ZORE_A, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean offOnlineRemoveKey98V2(byte[] data) {
        LogUtils.i(TAG, "offOnlineRemoveKey98V2 data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_FOUR_ZORE_A, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendNfcData(short id, byte[] data) {
        if (data == null) {
            LogUtils.e(TAG, "sendNfcData id:" + ((int) id) + " data is null", new Object[0]);
            return false;
        }
        LogUtils.i(TAG, "sendNfcData id:" + ((int) id) + ",data:" + Arrays.toString(data), new Object[0]);
        return this.mConn.sendNfcCanQueryV2(id, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean relayDataToTbox40A(byte[] data) {
        LogUtils.i(TAG, "relayDataToTbox40A data:" + Arrays.toString(data), new Object[0]);
        return sendNfcData(ID_FOUR_ZORE_A, data);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendRemoteLockOrUnlockStatus(int type, int status) {
        LogUtils.i(TAG, "sendRemoteLockOrUnlockStatus: type:" + type + " status:" + status, new Object[0]);
        return this.mConn.remoteLockAndUnlock(type, status);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean reqNetworkOperatorMode() {
        LogUtils.i(TAG, "reqNetworkOperatorMode", new Object[0]);
        return this.mConn.reqNetworkOperatorMode();
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean reqOperatorInfo() {
        LogUtils.i(TAG, "reqOperatorInfo", new Object[0]);
        return this.mConn.reqOperatorInfo();
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setManuallyStationedNetwork(String network, int sigLevel) {
        LogUtils.i(TAG, "setManuallyStationedNetwork network:" + network + " sigLevel:" + sigLevel, new Object[0]);
        return this.mConn.setManuallyStationedNetwork(network, sigLevel);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setAutomaticNetwork() {
        LogUtils.i(TAG, "setAutomaticNetwork", new Object[0]);
        return this.mConn.setAutomaticNetwork();
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean reqSimCardState() {
        LogUtils.i(TAG, "reqSimCardState", new Object[0]);
        return this.mConn.reqSimCardState();
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean operaQxdmLog(int start, int logCategory) {
        boolean zOperaQxdmLog;
        LogUtils.i(TAG, "operaQxdmLog start:" + start + " logCategory:" + logCategory, new Object[0]);
        synchronized (this.mQxdmLogMutex) {
            zOperaQxdmLog = this.mConn.operaQxdmLog(start, logCategory);
        }
        return zOperaQxdmLog;
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendApnArgs(ApnArgs apnArgs) {
        LogUtils.i(TAG, "sendApnArgs:" + apnArgs, new Object[0]);
        if (apnArgs == null) {
            LogUtils.e(TAG, "sendApnArgs failed, apnArgs is null", new Object[0]);
            return false;
        }
        return this.mConn.sendApnArgs(apnArgs);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean sendSimCardType(int type) {
        LogUtils.i(TAG, "sendSimCardType type:" + type, new Object[0]);
        if (type < 0 || type > 1) {
            LogUtils.e(TAG, "sendSimCardType wrong type:" + type, new Object[0]);
        }
        return this.mConn.sendSimCardType(type);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean queryNetworkFlow(int apn, String iccid) {
        LogUtils.i(TAG, "queryNetworkFlow apn:" + apn + " iccid:" + iccid, new Object[0]);
        if (apn > 1 || apn < 0) {
            LogUtils.e(TAG, "queryNetworkFlow wrong apn:" + apn, new Object[0]);
        }
        if (TextUtils.isEmpty(iccid)) {
            LogUtils.e(TAG, "queryNetworkFlow wrong null iccid", new Object[0]);
            return false;
        }
        return this.mConn.queryNetworkFlow(apn, iccid);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean queryApnArgs() {
        LogUtils.i(TAG, "queryApnArgs", new Object[0]);
        return this.mConn.queryApnArgs();
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean getTboxNetworkState(int apn) {
        LogUtils.i(TAG, "getTboxNetworkState:" + apn, new Object[0]);
        if (apn == 0) {
            apn = 2;
        } else if (apn == 1) {
            apn = 3;
        } else {
            LogUtils.e(TAG, "getTboxNetworkState wrong apn:" + apn, new Object[0]);
        }
        LogUtils.i(TAG, "getTboxNetworkState transvert apn to apn" + apn, new Object[0]);
        return this.mConn.getTboxNetworkState(apn);
    }

    @Override // com.engine.tbox.iface.ITboxIface
    public boolean setNetworkLimit(boolean enable) {
        LogUtils.i(TAG, "setNetworkLimit:" + enable, new Object[0]);
        return this.mConn.setNetworkLimit(enable);
    }
}
