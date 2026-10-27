package android.hardware.bydauto.ota.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.DiagnosticInfo;
import android.hardware.bydauto.ota.Info;
import android.hardware.bydauto.ota.OTAControlCMD;
import android.hardware.bydauto.ota.ReadDTCInfoType;
import android.hardware.bydauto.tbox.AbsBYDTBoxListener;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.ts.ota.otasdkmgr.DiagnosticAidlInfo;
import com.ts.ota.otasdkmgr.EcuInfo;
import com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener;
import com.ts.ota.otasdkmgr.IAbsMessageCallback;
import com.ts.ota.otasdkmgr.ITboxCallBack;
import com.ts.ota.otasdkmgr.IUpdateListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class OtaManagerImpl {
    private static final int NUM_1 = 1;
    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;
    private static final int NUM_4 = 4;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_TS.OtaManagerImpl";
    protected static final String TAG = "TS.OtaManagerImpl";
    private static final int TBOX_COMMAND_FAILED = 1;
    private static final int TBOX_COMMAND_INVALID_VALUE = 3;
    private static final int TBOX_COMMAND_SUCCESS = 0;
    private static final int TBOX_COMMAND_TIMEOUT = 2;
    private static OtaManagerImpl sTsOtaManagerImpl = null;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private OtaSdkManager mSdkManager;
    private AbsBYDTBoxListener mTboxListener;
    private TestOtaManagerImplCallback mTestCallback;
    private TestOtaUpgradeManagerCallBack mTestOtaUpgradeManagerCallBack;
    private final List<AbsBYDAutoOtaListener> mOtaListenerList = new ArrayList();
    private Map<String, AbsMessageCallback> absMsgCallbackMap = new HashMap();
    private int mForTest = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mNowBind = false;
    IAbsBYDAutoOtaListener.Stub otaBydAutoListener = new IAbsBYDAutoOtaListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.1
        public void onNfcOperationCommand(int cmd) {
        }

        public void onTurnOffState(int state) {
            Log.w(OtaManagerImpl.TAG, "onTurnOffState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onTurnOffState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onTurnOffState: mOtaListenerList is null");
        }

        public void onSmartPowerState(int state) {
            Log.w(OtaManagerImpl.TAG, "onSmartPowerState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onSmartPowerState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSmartPowerState: mOtaListenerList is null");
        }

        public void onDcState(int state) {
            Log.w(OtaManagerImpl.TAG, "onDcState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onDcState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onDcState: mOtaListenerList is null");
        }

        public void onIg3RelayState(int state) {
            Log.w(OtaManagerImpl.TAG, "onIg3RelayState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onIg3RelayState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onIg3RelayState: mOtaListenerList is null");
        }

        public void onIg1RelayState(int state) {
            Log.w(OtaManagerImpl.TAG, "onIg1RelayState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onIg1RelayState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onIg1RelayState: mOtaListenerList is null");
        }

        public void onIg4RelayState(int state) {
            Log.w(OtaManagerImpl.TAG, "onIg4RelayState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onIg4RelayState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onIg4RelayState: mOtaListenerList is null");
        }

        public void onBackupAreaDownloadState(int state) {
            Log.w(OtaManagerImpl.TAG, "onBackupAreaDownloadState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onBackupAreaDownloadState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onBackupAreaDownloadState: mOtaListenerList is null");
        }

        public void on7dfExistState(boolean exist) {
            Log.w(OtaManagerImpl.TAG, "on7dfExistState: state = " + exist);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.on7dfExistState(exist);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "on7dfExistState: mOtaListenerList is null");
        }

        public void onBcmOtaState(int state) {
            Log.w(OtaManagerImpl.TAG, "onBcmOtaState: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onBcmOtaState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onBcmOtaState: mOtaListenerList is null");
        }

        public void onLFDoorLockStatus(int state) {
            Log.w(OtaManagerImpl.TAG, "onLFDoorLockStatus: state = " + state);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onLFDoorLockStatus: mOtaListenerList is null");
            } else {
                OtaManagerImpl.this.caseFeatureIds(2389, state);
            }
        }

        public void onProhibitPowerOnState(int state) {
            Log.w(OtaManagerImpl.TAG, " onProhibitPowerOnState.in state : " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onProhibitPowerOnState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onProhibitPowerOnState: mOtaListenerList is null");
        }

        public void onStartKeyState(int state) {
            Log.w(OtaManagerImpl.TAG, " onStartKeyState.in state : " + state);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onStartKeyState(state);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onStartKeyState: mOtaListenerList is null");
        }

        public void onSocValueChanged(int value) {
            Log.w(OtaManagerImpl.TAG, "onSocValueChanged: value = " + value);
            if (OtaManagerImpl.this.mOtaListenerList != null || !OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                for (AbsBYDAutoOtaListener listener : OtaManagerImpl.this.mOtaListenerList) {
                    listener.onSocValueChanged(value);
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSocValueChanged: mOtaListenerList is null");
        }
    };
    ITboxCallBack mITestTboxCallBack = new ITboxCallBack() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.2
        public void onUpdateTrafficData() {
            Log.w(OtaManagerImpl.TAG, "onUpdateTrafficData callback...");
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onUpdateTrafficData();
            } else {
                Log.e(OtaManagerImpl.TAG, "onUpdateTrafficData: callback is null");
            }
        }

        public void onTboxCmd2Ivi(int cmd) {
            Log.w(OtaManagerImpl.TAG, "onTboxCmd2Ivi callback cmd : " + cmd);
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onTBoxCmd2IVI(cmd);
            } else {
                Log.e(OtaManagerImpl.TAG, "onTboxCmd2Ivi: callback is null");
            }
        }

        public void onSyncApnParamResult(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onSyncApnParamResult");
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onSyncApnParamResult(resultCode);
            } else {
                Log.e(OtaManagerImpl.TAG, "onSyncApnParamResult: callback is null");
            }
        }

        public void onGetApnParamFromTbox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
            Log.w(OtaManagerImpl.TAG, "onGetApnParamFromTbox");
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onGetApnParamFromTBox(apn, username, pwd, apnType, apnProtocol, proxy, port);
            } else {
                Log.e(OtaManagerImpl.TAG, "onGetApnParamFromTbox: callback is null");
            }
        }

        public void onGetTrafficFromTbox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) {
            Log.w(OtaManagerImpl.TAG, "onGetTrafficFromTbox");
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onGetTrafficFromTBox(apnType, iccid, time, dayUpTraffic, dayDownTraffic, monthUpTraffic, monthDownTraffic);
            } else {
                Log.e(OtaManagerImpl.TAG, "onGetTrafficFromTbox: callback is null");
            }
        }

        public void onGetTboxNetworkState(int apn, int state) {
            Log.d(OtaManagerImpl.TAG, "onGetTboxNetworkState : " + apn + ", state : " + state);
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onGetTBoxNetworkState(apn, state);
            } else {
                Log.e(OtaManagerImpl.TAG, "onGetTboxNetworkState: callback is null");
            }
        }
    };
    IAbsMessageCallback.Stub otaBydAutoCallback = new IAbsMessageCallback.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.3
        public void onEcuInfo(List<EcuInfo> ecuInfoList) {
            Log.w(OtaManagerImpl.TAG, "onEcuInfo callback->ecuInfoList size: " + ecuInfoList.size());
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onEcuInfo: absMsgCallbackMap is null");
                return;
            }
            List<android.hardware.bydauto.ota.EcuInfo> ecuList = new ArrayList<>();
            for (EcuInfo sdkEcuInfo : ecuInfoList) {
                android.hardware.bydauto.ota.EcuInfo bydEcuInfo = new android.hardware.bydauto.ota.EcuInfo();
                bydEcuInfo.setEcuId(sdkEcuInfo.getEcuId());
                bydEcuInfo.setEcuSoftCode(sdkEcuInfo.getEcuSoftCode());
                bydEcuInfo.setEcuSoftVer(sdkEcuInfo.getEcuSoftVer());
                bydEcuInfo.setEcuHardVer(sdkEcuInfo.getEcuHardVer());
                bydEcuInfo.setEcuConfigVer(sdkEcuInfo.getEcuConfigVer());
                bydEcuInfo.setEcuConfigCode(sdkEcuInfo.getEcuConfigCode());
                bydEcuInfo.setEcuDId(sdkEcuInfo.getEcuDId());
                bydEcuInfo.setEcuDiaId(sdkEcuInfo.getEcuDiaId());
                bydEcuInfo.setEcuHardDid(sdkEcuInfo.getEcuHardDid());
                bydEcuInfo.setEcuConfigDid(sdkEcuInfo.getEcuConfigDid());
                ecuList.add(bydEcuInfo);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onEcuInfo")) {
                AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onEcuInfo");
                callback.onEcuInfo(ecuList);
                Log.w(OtaManagerImpl.TAG, "onEcuInfo: register callback is " + callback);
            }
        }

        public void onGetAQSlot(int slot) {
            Log.w(OtaManagerImpl.TAG, "onGetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetAQSlot")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetAQSlot");
                    callback.onGetAQSlot(slot);
                    Log.w(OtaManagerImpl.TAG, "onGetAQSlot: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onGetAQSlot: absMsgCallbackMap is null");
        }

        public void onSetAQSlot(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onSetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSetAQSlot")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSetAQSlot");
                    callback.onSetAQSlot(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onSetAQSlot: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSetAQSlot: absMsgCallbackMap is null");
        }

        public void onAppointmentTime(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onAppointmentTime callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAppointmentTime")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAppointmentTime");
                    callback.onAppointmentTime(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onAppointmentTime: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onAppointmentTime: absMsgCallbackMap is null");
        }

        public void onUpdateFlag(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onUpdateFlag callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onUpdateFlag")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onUpdateFlag");
                    callback.onUpdateFlag(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onUpdateFlag: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onUpdateFlag: absMessageCallbacks is null");
        }

        public void onQnxOtaState(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onQnxOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onQnxOtaState")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onQnxOtaState");
                    callback.onQnxOtaState(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onQnxOtaState: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onQnxOtaState: absMessageCallbacks is null");
        }

        public void onAndroidOtaState(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onAndroidOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAndroidOtaState")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAndroidOtaState");
                    callback.onAndroidOtaState(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onAndroidOtaState: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onAndroidOtaState: absMessageCallbacks is null");
        }

        public void onCloudServiceListener(int type, Bundle bundle) {
            Log.w(OtaManagerImpl.TAG, "onCloudServiceListener callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onCloudServiceListener")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onCloudServiceListener");
                    callback.onCloudServiceListener(type, bundle);
                    Log.w(OtaManagerImpl.TAG, "onCloudServiceListener: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onCloudServiceListener: absMessageCallbacks is null");
        }

        public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) {
            Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            List<DiagnosticInfo> infos = null;
            try {
                infos = OtaManagerImpl.this.generateBydApi(info);
            } catch (Exception exception) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", exception);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetDiagnosticTroubleCode")) {
                AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetDiagnosticTroubleCode");
                callback.onGetDiagnosticTroubleCode(infos);
                Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: register callback is " + callback);
            }
        }

        public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info) {
            Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            List<DiagnosticInfo> infos = null;
            try {
                infos = OtaManagerImpl.this.generateBydApi(info);
            } catch (Exception exception) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", exception);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onClearDiagnosticTroubleCode")) {
                AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onClearDiagnosticTroubleCode");
                callback.onClearDiagnosticTroubleCode(infos);
                Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: register callback is " + callback);
            }
        }

        public void onSendCanNM(int resultCode) {
            Log.w(OtaManagerImpl.TAG, "onSendCanNM callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSendCanNM")) {
                    AbsMessageCallback callback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSendCanNM");
                    callback.onSendCanNM(resultCode);
                    Log.w(OtaManagerImpl.TAG, "onSendCanNM: register callback is " + callback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSendCanNM: absMessageCallbacks is null");
        }
    };

    public OtaManagerImpl(Context con) {
        this.mTestCallback = null;
        this.mTestOtaUpgradeManagerCallBack = null;
        this.mSdkManager = null;
        this.mContext = null;
        this.mContentResolver = con.getContentResolver();
        this.mContext = con;
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (this.mContentResolver != null && 1 == this.mForTest) {
            Log.w(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback = new TestOtaManagerImplCallback();
            this.mTestOtaUpgradeManagerCallBack = new TestOtaUpgradeManagerCallBack();
            return;
        }
        Log.e(TAG, "OtaSdkManager.getInstance");
        OtaSdkManager otaSdkManager = OtaSdkManager.getInstance(this.mContext);
        this.mSdkManager = otaSdkManager;
        otaSdkManager.registerListener(this.otaBydAutoListener);
    }

    public static synchronized OtaManagerImpl getInstance(Context con) {
        if (sTsOtaManagerImpl == null) {
            sTsOtaManagerImpl = new OtaManagerImpl(con);
        }
        return sTsOtaManagerImpl;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    private boolean storeAbsMsgCallback(String name, AbsMessageCallback callback) {
        Map<String, AbsMessageCallback> map;
        Log.w(TAG, "storeAbsMsgCallback: callback->" + callback);
        if (callback != null && (map = this.absMsgCallbackMap) != null) {
            map.put(name, callback);
            return true;
        }
        return false;
    }

    public void trigerTestOtaManagerImplCallback(int what, Object para1object) {
        this.mTestCallback.enqueueMessage(what, para1object);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int what, Object para1object) {
        this.mTestOtaUpgradeManagerCallBack.enqueueMessage(what, para1object);
    }

    public void registerListener(AbsBYDAutoOtaListener listener) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        registerListener(listener, null);
    }

    public void registerListener(AbsBYDTBoxListener listener) {
        Log.w(TAG, "register AbsBYDTBoxListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
        } else {
            this.mTboxListener = listener;
            this.mSdkManager.registerTboxListener(this.mITestTboxCallBack);
        }
    }

    public void registerListener(AbsBYDAutoOtaListener listener, int[] featureIDs) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(listener);
            return;
        }
        synchronized (this.mOtaListenerList) {
            if (this.mOtaListenerList != null && !this.mOtaListenerList.contains(listener)) {
                this.mOtaListenerList.add(listener);
            }
        }
    }

    public void unregisterListener(AbsBYDAutoOtaListener listener) {
        Log.w(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(null);
            return;
        }
        synchronized (this.mOtaListenerList) {
            if (this.mOtaListenerList != null && this.mOtaListenerList.contains(listener)) {
                this.mOtaListenerList.remove(listener);
            }
        }
    }

    public void unregisterListener(AbsBYDTBoxListener listener) {
        Log.w(TAG, "unregister AbsBYDTBoxListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
        } else {
            this.mTboxListener = null;
            this.mSdkManager.unregisterTboxListener(this.mITestTboxCallBack);
        }
    }

    public int getDevicetype() {
        Log.w(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDevicetype mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getDevicetype();
    }

    public String getSetPermission() {
        Log.w(TAG, "getSetPermission is execute");
        if (1 == this.mForTest) {
            Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
            return "ForTest";
        }
        return "not impl";
    }

    public String getGetPermission() {
        Log.w(TAG, "getGetPermission is execute");
        if (1 == this.mForTest) {
            Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
            return "ForTest";
        }
        return "not impl";
    }

    public byte[] getOTAInfoACK() {
        Log.w(TAG, "getOTAInfoACK is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getOTAInfoACK mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getOTAInfoACK();
    }

    public int notifyTargetID(byte[] id) {
        Log.w(TAG, "notifyTargetID is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTargetID mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.notifyTargetID(id);
    }

    public int notifyTargetID2(byte[] channelAndId) {
        Log.w(TAG, "notifyTargetID2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTargetID2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.notifyTargetID2(channelAndId);
    }

    public int sendOTAData(byte[] data) {
        Log.w(TAG, "sendOTAData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAData mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAData(data);
    }

    public int sendOTAData2(byte[] channelAndData) {
        Log.w(TAG, "sendOTAData2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAData2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAData2(channelAndData);
    }

    public int StartOTA() {
        Log.w(TAG, "StartOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " StartOTA mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.StartOTA();
    }

    public int FinishOTA() {
        Log.w(TAG, "FinishOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " FinishOTA mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.FinishOTA();
    }

    public int sendOTAServiceData(byte[] data) {
        Log.w(TAG, "sendOTAServiceData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAServiceData mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAServiceData(data);
    }

    public int sendECUIds(int purpose, byte[] ids) {
        Log.w(TAG, "sendECUIds is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendECUIds mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendECUIds(purpose, ids);
    }

    public int sendOTACtlCMD(OTAControlCMD controlCmd) {
        Log.w(TAG, "sendOTACtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTACtlCMD mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTACtlCMD((byte[]) null);
    }

    public byte[] getECUVersion() {
        Log.w(TAG, "getECUVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getECUVersion mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getECUVersion();
    }

    public byte[] getECUSoftcode() {
        Log.w(TAG, "getECUSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getECUSoftcode mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getECUSoftcode();
    }

    public byte[] getECUVersionAndSoftcode() {
        Log.w(TAG, "getECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getECUVersionAndSoftcode mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getECUVersionAndSoftcode();
    }

    public int queryECUVersionAndSoftcode(byte[] ids) {
        Log.w(TAG, "queryECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " queryECUVersionAndSoftcode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.queryECUVersionAndSoftcode(ids);
    }

    public byte[] getMCUVersionAndSoftcode() {
        Log.w(TAG, "getMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getMCUVersionAndSoftcode mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getMCUVersionAndSoftcode();
    }

    public int queryMCUVersionAndSoftcode(byte[] ids) {
        Log.w(TAG, "queryMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " queryMCUVersionAndSoftcode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.queryMCUVersionAndSoftcode(ids);
    }

    public int getPoweroffInform() {
        Log.w(TAG, "getPoweroffInform is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getPoweroffInform mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getPoweroffInform();
    }

    public int getOTAState() {
        Log.w(TAG, "getOTAState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getOTAState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getOTAState();
    }

    public int getOTATimecountState() {
        Log.w(TAG, "getOTATimecountState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getOTATimecountState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getOTATimecountState();
    }

    public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) {
        Log.w(TAG, "sendOTATimecountCtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTATimecountCtlCMD mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTATimecountCtlCMD(install, user, hour, minute);
    }

    public int setScreenState(int state) {
        Log.w(TAG, "setScreenState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setScreenState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setScreenState(state);
    }

    public int getDischargeMainContactorState() {
        Log.w(TAG, "getDischargeMainContactorState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDischargeMainContactorState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getDischargeMainContactorState();
    }

    public int sendECUIds2(int purpose, byte[] ids) {
        Log.w(TAG, "sendECUIds2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendECUIds2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendECUIds2(purpose, ids);
    }

    public int getPowerOnForbidState() {
        Log.w(TAG, "getPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getPowerOnForbidState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getPowerOnForbidState();
    }

    public int getLFDoorLockState() {
        Log.w(TAG, "getLFDoorLockState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getLFDoorLockState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getLFDoorLockState();
    }

    public int setPowerOnForbidState(int state) {
        Log.w(TAG, "setPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setPowerOnForbidState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setPowerOnForbidState(state);
    }

    public byte[] getCanInfo() {
        Log.w(TAG, "getCanInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getCanInfo mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getCanInfo();
    }

    public int setCanInfoSwitchState(int state) {
        Log.w(TAG, "setCanInfoSwitchState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setCanInfoSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setCanInfoSwitchState(state);
    }

    public double getBatteryPowerVoltage() {
        Log.w(TAG, "getBatteryPowerVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryPowerVoltage mForTestValue is 0 execute return");
            return -1.0d;
        }
        return this.mSdkManager.getBatteryPowerVoltage();
    }

    public byte[] getFaultCodeInfo() {
        Log.w(TAG, "getFaultCodeInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getFaultCodeInfo mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getFaultCodeInfo();
    }

    public int getLocalDiagStatus() {
        Log.w(TAG, "getLocalDiagStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getLocalDiagStatus mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getLocalDiagStatus();
    }

    public int setMcuMessage(int message) {
        Log.w(TAG, "setMcuMessage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setMcuMessage mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setMcuMessage(message);
    }

    public int syncMcuState() {
        Log.w(TAG, "syncMcuState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " syncMcuState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.syncMcuState();
    }

    public int sendMonitorTable(byte[] id) {
        Log.w(TAG, "sendMonitorTable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendMonitorTable mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendMonitorTable(id);
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return false;
        }
        Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        return false;
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return false;
        }
        Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        return false;
    }

    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return false;
        }
        Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        return false;
    }

    public void setAllStatus() {
        Log.w(TAG, "setAllStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAllStatus mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setAllStatus();
        }
    }

    public int getType() {
        Log.w(TAG, "getType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getType();
    }

    public int[] getFeatureList() {
        Log.w(TAG, "getFeatureList is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
            return new int[]{0, 0};
        }
        return this.mSdkManager.getFeatureList();
    }

    public String getVehicleVersion() {
        Log.w(TAG, "getVehicleVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getVehicleVersion mForTestValue is 0 execute return");
            return "ForTest";
        }
        return this.mSdkManager.getVehicleVersion();
    }

    public boolean getEcuInfo(List<Info> infos, AbsMessageCallback callback) {
        Log.w(TAG, "getEcuInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getEcuInfo: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onEcuInfo", callback);
        List<com.ts.ota.otasdkmgr.Info> infoList = new ArrayList<>();
        if (infos == null || infos.size() == 0) {
            Log.w(TAG, "getEcuInfo: Info is empty");
            return false;
        }
        for (Info bydInfo : infos) {
            com.ts.ota.otasdkmgr.Info sdkInfo = new com.ts.ota.otasdkmgr.Info();
            sdkInfo.setEcuId(bydInfo.getEcuId());
            sdkInfo.setEcuDiaId(bydInfo.getEcuDiaId());
            sdkInfo.setEcuDId(bydInfo.getEcuDId());
            sdkInfo.setEcuHardDid(bydInfo.getEcuHardDid());
            sdkInfo.setEcuConfigDid(bydInfo.getEcuConfigDid());
            infoList.add(sdkInfo);
        }
        return this.mSdkManager.getEcuInfo(infoList, this.otaBydAutoCallback);
    }

    public boolean getAQSlot(AbsMessageCallback callback) {
        Log.w(TAG, "getAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onGetAQSlot", callback);
        return this.mSdkManager.getAQSlot(this.otaBydAutoCallback);
    }

    public boolean setAQSlot(int slot, AbsMessageCallback callback) {
        Log.w(TAG, "setAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSetAQSlot", callback);
        return this.mSdkManager.setAQSlot(slot, this.otaBydAutoCallback);
    }

    public void QNXreboot() {
        Log.w(TAG, "QNXreboot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " QNXreboot: mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.QNXreboot();
        }
    }

    @Deprecated
    public int getVehicleType() {
        Log.w(TAG, "getVehicleType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getVehicleType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getVehicleType();
    }

    public int getTurnOffState() {
        Log.w(TAG, "getTurnOffState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTurnOffState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getTurnOffState();
    }

    @Deprecated
    public int getIgnitionState() {
        Log.w(TAG, "getIgnitionState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getIgnitionState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getIgnitionState();
    }

    public int getBurglarState() {
        Log.w(TAG, "getBurglarState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBurglarState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBurglarState();
    }

    public int getBatteryTemp(int batteryType) {
        Log.w(TAG, "getBatteryTemp is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getEnvironmentTemp mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryTemp(batteryType);
    }

    public int getEpbState() {
        Log.w(TAG, "getEpbState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getEpbState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getEpbState();
    }

    public boolean getSmartPowerUsable() {
        Log.w(TAG, "getSmartPowerUsable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSmartPowerUsable mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.getSmartPowerUsable();
    }

    public int getSmartPowerState() {
        Log.w(TAG, "getSmartPowerState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSmartPowerState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getSmartPowerState();
    }

    public int getSmartPowerException() {
        Log.w(TAG, "getSmartPowerException is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSmartPowerException mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getSmartPowerException();
    }

    public int getDcState() {
        Log.w(TAG, "getDcState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDcState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getDcState();
    }

    @Deprecated
    public int getBatteryType() {
        Log.w(TAG, "getBatteryType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryType mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryType();
    }

    public int getBatteryVoltage(int batteryType) {
        Log.w(TAG, "getBatteryVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryVoltage mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryVoltage(batteryType);
    }

    @Deprecated
    public int getChargeState() {
        Log.w(TAG, "getChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getChargeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getChargeState();
    }

    public int getBatteryManagementDeviceState() {
        Log.w(TAG, "getBatteryManagementDeviceState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryManagementDeviceState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryManagementDeviceState();
    }

    public int getDischargeState(int type) {
        Log.w(TAG, "getDischargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDischargeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getDischargeState(type);
    }

    public int getChargeAndDischargeSystemState() {
        Log.w(TAG, "getChargeAndDischargeSystemState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getChargeAndDischargeSystemState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getChargeAndDischargeSystemState();
    }

    public int getSmartChargeState(int batteryType) {
        Log.w(TAG, "getSmartChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSmartChargeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getSmartChargeState(batteryType);
    }

    public int getIg3RelayState() {
        Log.w(TAG, "getIg3RelayState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getIg3RelayState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getIg3RelayState();
    }

    public int getBcmOtaState() {
        Log.w(TAG, "getBcmOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBcmOtaState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBcmOtaState();
    }

    public boolean get7dfExistState() {
        Log.w(TAG, "get7dfExistState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " get7dfExistState mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.get7dfExistState();
    }

    public boolean setSmartPower(int state) {
        Log.w(TAG, "setSmartPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setSmartPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setSmartPower(state);
    }

    public boolean setEclPower(int state) {
        Log.w(TAG, "setEclPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setEclPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setEclPower(state);
    }

    public boolean setQuitSmartCharge(int state) {
        Log.w(TAG, "setQuitSmartCharge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQuitSmartCharge mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setQuitSmartCharge(state);
    }

    public boolean setQuitChargeAndDischarge(int state) {
        Log.w(TAG, "setQuitChargeAndDischarge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQuitChargeAndDischarge mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setQuitChargeAndDischarge(state);
    }

    public boolean setOtaModel(int state) {
        Log.w(TAG, "setOtaModel is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setOtaModel mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setOtaModel(state);
    }

    public boolean setOtaPower(int state) {
        Log.w(TAG, "setOtaPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setOtaPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setOtaPower(state);
    }

    public boolean setHighVoltageCmd(int command) {
        Log.w(TAG, "setHighVoltageCmd is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setHighVoltageCmd(command);
    }

    public boolean setBackupAreaDownload(int state) {
        Log.w(TAG, "setBackupAreaDownload is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setBackupAreaDownload(state);
    }

    public boolean setBackLightState(int state) {
        Log.w(TAG, "setBackLightState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setBackLightState(state);
    }

    @Deprecated
    public void startUpdate(int type, IUpdateListener listener) {
        Log.w(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(type, listener);
        }
    }

    @Deprecated
    public void stopUpdate() {
        Log.w(TAG, "stopUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " stopUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.stopUpdate();
        }
    }

    public void registerCallback(IUpdateListener listener) {
        Log.w(TAG, "registerCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerCallback mForTestValue is 0 execute return");
            this.mTestOtaUpgradeManagerCallBack.setUpdateListener(listener);
        } else {
            this.mSdkManager.registerCallback(listener);
        }
    }

    public void unregisterCallback(IUpdateListener listener) {
        Log.w(TAG, "unregisterCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterCallback mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.unregisterCallback(listener);
        }
    }

    public void startUpdate(String path) {
        Log.w(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(path);
        }
    }

    public void startDownloadBackup(String path) {
        Log.w(TAG, "startDownloadBackup is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startDownloadBackup mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startDownloadBackup(path);
        }
    }

    public void startUpdateTbox(String path) {
        Log.w(TAG, "startUpdateTbox is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdateTbox mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdateTbox(path);
        }
    }

    public String getTBoxSerialNumber() {
        Log.w(TAG, "getTBoxSerialNumber is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTBoxSerialNumber mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getTBoxSerialNumber();
    }

    public String getTBoxSoftVersion() {
        Log.w(TAG, "getTBoxSoftVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTBoxSoftVersion mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getTBoxSoftVersion();
    }

    public String getTBoxHardwareVersion() {
        Log.w(TAG, "getTBoxHardwareVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTBoxHardwareVersion mForTestValue is 0 execute return");
            return null;
        }
        return this.mSdkManager.getTBoxHardwareVersion();
    }

    public float getVehicleSpeed() {
        Log.w(TAG, "getVehicleSpeed is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getVehicleSpeed mForTestValue is 0 execute return");
            return -1.0f;
        }
        return this.mSdkManager.getVehicleSpeed();
    }

    public int getSocValue() {
        Log.w(TAG, "getSocValue is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSocValue mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getSocValue();
    }

    public boolean getTBoxUpgradeLog(String path) {
        Log.w(TAG, "getTBoxUpgradeLog is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTBoxUpgradeLog mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.getTBoxUpgradeLog(path);
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        Log.w(TAG, "decodeAndCheckSign is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " decodeAndCheckSign mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.decodeAndCheckSign(bundle);
    }

    public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, AbsMessageCallback callback) {
        Log.w(TAG, "setAppointmentTime is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAppointmentTime mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAppointmentTime", callback);
            this.mSdkManager.setAppointmentTime(day, hours, minutes, type, wakeup, this.otaBydAutoCallback);
        }
    }

    public boolean sendCanNM(int source, int state, AbsMessageCallback callback) {
        Log.w(TAG, "sendCanNM is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendCanNM: mForTest is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSendCanNM", callback);
        return this.mSdkManager.sendCanNM(source, state, this.otaBydAutoCallback);
    }

    public void setUpdateFlag(int flag, AbsMessageCallback callback) {
        Log.w(TAG, "setUpdateFlag is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setUpdateFlag mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onUpdateFlag", callback);
            this.mSdkManager.setUpdateFlag(flag, this.otaBydAutoCallback);
        }
    }

    public void setQnxOtaState(int state, AbsMessageCallback callback) {
        Log.w(TAG, "setQnxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQnxOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onQnxOtaState", callback);
            this.mSdkManager.setQnxOtaState(state, this.otaBydAutoCallback);
        }
    }

    public void setAndroidOtaState(int state, AbsMessageCallback callback) {
        Log.w(TAG, "setAndroidOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAndroidOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAndroidOtaState", callback);
            this.mSdkManager.setAndroidOtaState(state, this.otaBydAutoCallback);
        }
    }

    public void setCloudServiceListener(AbsMessageCallback callback) {
        Log.w(TAG, "setCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setCloudServiceListener mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onCloudServiceListener", callback);
            this.mSdkManager.setCloudServiceListener(this.otaBydAutoCallback);
        }
    }

    public void stopCloudServiceListener() {
        Log.w(TAG, "stopCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " stopCloudServiceListener mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.stopCloudServiceListener(this.otaBydAutoCallback);
        }
    }

    public void notifyTboxOtaState(int state) {
        Log.w(TAG, "notifyTboxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTboxOtaState mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.notifyTboxOtaState(state);
        }
    }

    public boolean executeCMD(Bundle cmd) {
        Log.w(TAG, "executeCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.executeCMD(cmd);
    }

    public void setMobileNetworkState(boolean state) {
        Log.w(TAG, "setMobileNetworkState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setMobileNetworkState(state);
        }
    }

    public int getLFDoorLockStatus() {
        Log.w(TAG, "getLFDoorLockStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getLFDoorLockStatus mForTestValue is 0 execute return");
            return 0;
        }
        return this.mSdkManager.getLFDoorLockStatus();
    }

    public boolean getMobileNetworkState() {
        if (1 == this.mForTest) {
            Log.e(TAG, " getMobileNetworkState mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getMobileNetworkState();
    }

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> info, AbsMessageCallback callback) {
        if (1 == this.mForTest) {
            Log.e(TAG, " getDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        storeAbsMsgCallback("onGetDiagnosticTroubleCode", callback);
        this.mSdkManager.getDiagnosticTroubleCode(generateAidl(info), this.otaBydAutoCallback);
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> info, AbsMessageCallback callback) {
        if (1 == this.mForTest) {
            Log.e(TAG, " clearDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        storeAbsMsgCallback("onClearDiagnosticTroubleCode", callback);
        this.mSdkManager.clearDiagnosticTroubleCode(generateAidl(info), this.otaBydAutoCallback);
    }

    public int getProhibitPowerOnState() {
        if (1 == this.mForTest) {
            Log.e(TAG, " getProhibitPowerOnState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getProhibitPowerOnState();
    }

    public boolean setMultimediaSelfUpdate(int state) {
        if (1 == this.mForTest) {
            Log.e(TAG, " setMultimediaSelfUpdate mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setMultimediaSelfUpdate(state);
    }

    public int notifyTBoxTrafficResult(int result) {
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTBoxTrafficResult mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.notifyTboxTrafficResult(result);
    }

    public int getIg1RelayState() {
        if (1 == this.mForTest) {
            Log.e(TAG, " getIg1RelayState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getIg1RelayState();
    }

    public int getIg4RelayState() {
        if (1 == this.mForTest) {
            Log.e(TAG, " getIg4RelayState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getIg4RelayState();
    }

    public void resetOTA() {
        if (1 == this.mForTest) {
            Log.e(TAG, " resetOTA mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        this.mSdkManager.resetOta();
    }

    public int updateHsm(String path) {
        if (1 == this.mForTest) {
            Log.e(TAG, " updateHsm mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.updateHsm(path);
    }

    public void setUsbUpdateState(int state) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        this.mSdkManager.setUsbUpdateState(state);
    }

    public int notifyTBoxCmdResult(int cmd, int result) {
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTBoxCmdResult mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int tboxResult = this.mSdkManager.notifyTboxCmdResult(cmd, result);
        if (tboxResult == 0) {
            return 0;
        }
        if (tboxResult == 1) {
            return -2147482648;
        }
        if (tboxResult == 2) {
            return -2147482646;
        }
        if (tboxResult == 3) {
            return -2147482645;
        }
        return -2147482648;
    }

    public int uploadCDR(int length, String cdrStr) {
        if (1 == this.mForTest) {
            Log.e(TAG, " uploadCDR mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int tboxResult = this.mSdkManager.uploadCdr(length, cdrStr);
        if (tboxResult == 0) {
            return 0;
        }
        if (tboxResult == 1) {
            return -2147482648;
        }
        if (tboxResult == 2) {
            return -2147482646;
        }
        if (tboxResult == 3) {
            return -2147482645;
        }
        return -2147482648;
    }

    public int setTcpDumpState(boolean state) {
        Log.w(TAG, "setTcpDumpState is execute");
        if (1 == this.mForTest) {
            Log.e(TAG, " setTcpDumpState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setTcpDumpState(state);
    }

    public int clearTboxLog() {
        Log.w(TAG, "clearTboxLog is execute");
        if (1 == this.mForTest) {
            Log.e(TAG, " clearTboxLog mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.clearTboxLog();
    }

    public int setApn3State(boolean state) {
        Log.w(TAG, "setApn3State is execute: " + state);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.setApn3State(state);
    }

    public int setQxdmLogState(int logState, int logType) {
        Log.w(TAG, "setQxdmLogState is logState: " + logState + ", logType: " + logType);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setQxdmLogState(logState, logType);
    }

    public int enableSimcardNetwork(int simCardType) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.enableSimcardNetwork(simCardType);
    }

    public int syncApnParam(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.syncApnParam(apn, username, pwd, apnType, apnProtocol, proxy, port);
    }

    public int getTrafficFromTbox(int apnType, String iccid) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getTrafficFromTbox(apnType, iccid);
    }

    public int setNonWhiteListNetworkEnable(boolean enableFlag) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setNonWhiteListNetworkEnable(enableFlag);
    }

    public int getApnParamFromTbox() {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getApnParamFromTbox();
    }

    public int getTBoxNetworkState(int apnType) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.getTboxNetworkState(apnType);
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i != 314) {
                        if (i != 2385) {
                            if (i != 2389) {
                                if (i != 2395) {
                                    if (i != 2411) {
                                        if (i != 2432) {
                                            if (i != 2437) {
                                                if (i != 3319) {
                                                    if (i != 3534) {
                                                        if (i != 3606) {
                                                            if (i != 2356) {
                                                                if (i != 2357) {
                                                                    if (i != 2652) {
                                                                        if (i == 2653) {
                                                                            bYDAutoEventValue.intValue = getSmartChargeState(1);
                                                                            sparseArray.put(2653, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                        } else {
                                                                            switch (i) {
                                                                                case 2406:
                                                                                    bYDAutoEventValue.intValue = getSmartPowerUsable() ? 1 : 0;
                                                                                    sparseArray.put(2406, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 2407:
                                                                                    bYDAutoEventValue.intValue = getSmartPowerException();
                                                                                    sparseArray.put(2407, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                case 2408:
                                                                                    bYDAutoEventValue.intValue = getSmartPowerState();
                                                                                    sparseArray.put(2408, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                    break;
                                                                                default:
                                                                                    switch (i) {
                                                                                        case 2572:
                                                                                            bYDAutoEventValue.intValue = getDischargeState(2);
                                                                                            sparseArray.put(2572, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 2573:
                                                                                            bYDAutoEventValue.intValue = getDischargeState(1);
                                                                                            sparseArray.put(2573, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                        case 2574:
                                                                                            bYDAutoEventValue.intValue = getChargeAndDischargeSystemState();
                                                                                            sparseArray.put(2574, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                                            break;
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        bYDAutoEventValue.intValue = getSmartChargeState(0);
                                                                        sparseArray.put(2652, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                    }
                                                                } else {
                                                                    bYDAutoEventValue.intValue = getBatteryVoltage(1);
                                                                    sparseArray.put(2357, Integer.valueOf(bYDAutoEventValue.intValue));
                                                                }
                                                            } else {
                                                                bYDAutoEventValue.intValue = getBatteryVoltage(0);
                                                                sparseArray.put(2356, Integer.valueOf(bYDAutoEventValue.intValue));
                                                            }
                                                        } else {
                                                            bYDAutoEventValue.intValue = getEpbState();
                                                            sparseArray.put(3606, Integer.valueOf(bYDAutoEventValue.intValue));
                                                        }
                                                    } else {
                                                        bYDAutoEventValue.intValue = getBatteryTemp(1);
                                                        sparseArray.put(3534, Integer.valueOf(bYDAutoEventValue.intValue));
                                                    }
                                                } else {
                                                    bYDAutoEventValue.intValue = getBatteryTemp(1);
                                                    sparseArray.put(3319, Integer.valueOf(bYDAutoEventValue.intValue));
                                                }
                                            } else {
                                                bYDAutoEventValue.intValue = getDcState();
                                                sparseArray.put(2437, Integer.valueOf(bYDAutoEventValue.intValue));
                                            }
                                        } else {
                                            bYDAutoEventValue.intValue = getIg3RelayState();
                                            sparseArray.put(2432, Integer.valueOf(bYDAutoEventValue.intValue));
                                        }
                                    } else {
                                        bYDAutoEventValue.intValue = getBcmOtaState();
                                        sparseArray.put(2411, Integer.valueOf(bYDAutoEventValue.intValue));
                                    }
                                } else {
                                    bYDAutoEventValue.intValue = getTurnOffState();
                                    sparseArray.put(2395, Integer.valueOf(bYDAutoEventValue.intValue));
                                }
                            } else {
                                bYDAutoEventValue.intValue = getLFDoorLockStatus();
                                sparseArray.put(2389, Integer.valueOf(bYDAutoEventValue.intValue));
                            }
                        } else {
                            bYDAutoEventValue.intValue = getDischargeMainContactorState();
                            sparseArray.put(2385, Integer.valueOf(bYDAutoEventValue.intValue));
                        }
                    } else {
                        bYDAutoEventValue.intValue = getSocValue();
                        sparseArray.put(314, Integer.valueOf(bYDAutoEventValue.intValue));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                            i2++;
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        if (eventTypes.length < 1) {
            eventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 2362) {
                        setEclPower(eventValue.intValue);
                        Log.w(TAG, "OTA_CMD_ECL_Power.POWER_ON_SET:");
                    } else if (eventType == 2386) {
                        setBackupAreaDownload(eventValue.intValue);
                        Log.w(TAG, "OTA_DOUBLE_DIFF_DOWNLOAD_FLAG_SET:");
                    } else if (eventType == 2380) {
                        setHighVoltageCmd(eventValue.intValue);
                        Log.w(TAG, "OTA_CMD_OTA_HIGH_VOLTAGE_MODULE_SET:");
                    } else if (eventType != 2381) {
                        switch (eventType) {
                            case 2374:
                                setOtaModel(eventValue.intValue);
                                Log.w(TAG, "OTA_CMD_ENTER_OTA_MODE_SET:");
                                break;
                            case 2375:
                                setQuitChargeAndDischarge(eventValue.intValue);
                                Log.w(TAG, "OTA_CMD_EXIT_CHARGING_DIS_SET:");
                                break;
                            case 2376:
                                setQuitSmartCharge(eventValue.intValue);
                                Log.w(TAG, "OTA_CMD_EXIT_SMART_CHARGING_SET:");
                                break;
                        }
                    } else {
                        setOtaPower(eventValue.intValue);
                        Log.w(TAG, "OTA_CMD_OTA_Power.POWER_CMD_SET:");
                    }
                } else {
                    eventValue.intValue = -2147482648;
                }
            }
        }
        return 1;
    }

    private List<DiagnosticAidlInfo> generateAidl(List<DiagnosticInfo> infos) {
        List<DiagnosticAidlInfo> aidl = new ArrayList<>(infos.size());
        for (DiagnosticInfo info : infos) {
            DiagnosticAidlInfo message = new DiagnosticAidlInfo();
            message.ecuId = info.getEcuId();
            message.ecuDiaId = info.getEcuDiaId();
            message.ecuReprogramType = info.getEcuReprogramType();
            message.readDtcInfoType = getReadDtcInfoType(info.getReadDtcInfoType());
            message.ecuDtcInfo = info.getEcuDtcInfo();
            message.ecuDtcId = info.getEcuDtcId();
            message.snapshotRecordNumber = info.getSnapshotRecordNumber();
            message.extDataRecordNumber = info.getExtDataRecordNumber();
            message.statusMask = info.getStatusMask();
            message.recordMask = info.getRecordMask();
            aidl.add(message);
        }
        return aidl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DiagnosticInfo> generateBydApi(List<DiagnosticAidlInfo> infos) {
        List<DiagnosticInfo> byd = new ArrayList<>(infos.size());
        for (DiagnosticAidlInfo info : infos) {
            DiagnosticInfo message = new DiagnosticInfo();
            message.ecuId = info.getEcuId();
            message.ecuDiaId = info.getEcuDiaId();
            message.ecuReprogramType = info.getEcuReprogramType();
            message.readDtcInfoType = setReadDtcInfoType(info.getReadDtcInfoType());
            message.ecuDtcInfo = info.getEcuDtcInfo();
            message.ecuDtcId = info.getEcuDtcId();
            message.snapshotRecordNumber = info.getSnapshotRecordNumber();
            message.extDataRecordNumber = info.getExtDataRecordNumber();
            message.statusMask = info.getStatusMask();
            message.recordMask = info.getRecordMask();
            byd.add(message);
        }
        return byd;
    }

    private int getReadDtcInfoType(ReadDTCInfoType readDtcInfoType) {
        if (readDtcInfoType != null) {
            return readDtcInfoType.getValue();
        }
        return 0;
    }

    private ReadDTCInfoType setReadDtcInfoType(int readDtcInfoType) {
        if (readDtcInfoType == 1) {
            ReadDTCInfoType type = ReadDTCInfoType.DTC_INFO_STATUS_MASK;
            return type;
        }
        if (readDtcInfoType == 2) {
            ReadDTCInfoType type2 = ReadDTCInfoType.DTC_INFO_SNAPSHOT_DATA;
            return type2;
        }
        if (readDtcInfoType == 3) {
            ReadDTCInfoType type3 = ReadDTCInfoType.DTC_INFO_EXTEND_DATA_RECORDS;
            return type3;
        }
        if (readDtcInfoType != 4) {
            return null;
        }
        ReadDTCInfoType type4 = ReadDTCInfoType.DTC_INFO_ALL;
        return type4;
    }

    public void caseFeatureIds(int featureIds, int getNums) {
        if (featureIds != 0 && !this.mOtaListenerList.isEmpty()) {
            this.bydAutoEventValueListener.intValue = getNums;
            Log.w(TAG, ":caseFeatureIds " + this.bydAutoEventValueListener.intValue);
            for (AbsBYDAutoOtaListener listener : this.mOtaListenerList) {
                listener.onDataEventChanged(featureIds, this.bydAutoEventValueListener);
            }
        }
    }
}
