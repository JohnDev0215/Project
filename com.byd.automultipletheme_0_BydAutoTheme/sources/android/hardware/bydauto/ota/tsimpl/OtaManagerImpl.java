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
    private static OtaManagerImpl sTsOtaManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private OtaSdkManager mSdkManager;
    private AbsBYDTBoxListener mTboxListener;
    private TestOtaManagerImplCallback mTestCallback;
    private TestOtaUpgradeManagerCallBack mTestOtaUpgradeManagerCallBack;
    private AbsBYDAutoOtaListener mlistener = null;
    private Map<String, AbsMessageCallback> absMsgCallbackMap = new HashMap();
    private int mForTest = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mNowBind = false;
    IAbsBYDAutoOtaListener.Stub otaBydAutoListener = new IAbsBYDAutoOtaListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.1
        public void onNfcOperationCommand(int i) {
        }

        public void onTurnOffState(int i) {
            Log.w(OtaManagerImpl.TAG, "onTurnOffState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onTurnOffState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onTurnOffState: mlistener is null");
            }
        }

        public void onSmartPowerState(int i) {
            Log.w(OtaManagerImpl.TAG, "onSmartPowerState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onSmartPowerState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onSmartPowerState: mlistener is null");
            }
        }

        public void onDcState(int i) {
            Log.w(OtaManagerImpl.TAG, "onDcState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onDcState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onDcState: mlistener is null");
            }
        }

        public void onIg3RelayState(int i) {
            Log.w(OtaManagerImpl.TAG, "onIg3RelayState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onIg3RelayState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onIg3RelayState: mlistener is null");
            }
        }

        public void onIg1RelayState(int i) {
            Log.w(OtaManagerImpl.TAG, "onIg1RelayState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onIg1RelayState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onIg1RelayState: mlistener is null");
            }
        }

        public void onIg4RelayState(int i) {
            Log.w(OtaManagerImpl.TAG, "onIg4RelayState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onIg4RelayState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onIg4RelayState: mlistener is null");
            }
        }

        public void onBackupAreaDownloadState(int i) {
            Log.w(OtaManagerImpl.TAG, "onBackupAreaDownloadState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onBackupAreaDownloadState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onBackupAreaDownloadState: mlistener is null");
            }
        }

        public void on7dfExistState(boolean z) {
            Log.w(OtaManagerImpl.TAG, "on7dfExistState: state = " + z);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.on7dfExistState(z);
            } else {
                Log.e(OtaManagerImpl.TAG, "on7dfExistState: mlistener is null");
            }
        }

        public void onBcmOtaState(int i) {
            Log.w(OtaManagerImpl.TAG, "onBcmOtaState: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onBcmOtaState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onBcmOtaState: mlistener is null");
            }
        }

        public void onLFDoorLockStatus(int i) {
            Log.w(OtaManagerImpl.TAG, "onLFDoorLockStatus: state = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onLFDoorLockStatus(i);
                OtaManagerImpl.this.caseFeatureIds(2389, i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onLFDoorLockStatus: mlistener is null");
            }
        }

        public void onProhibitPowerOnState(int i) {
            Log.w(OtaManagerImpl.TAG, " onProhibitPowerOnState.in state : " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onProhibitPowerOnState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onProhibitPowerOnState: mlistener is null");
            }
        }

        public void onStartKeyState(int i) {
            Log.w(OtaManagerImpl.TAG, " onStartKeyState.in state : " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onStartKeyState(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onStartKeyState: mlistener is null");
            }
        }

        public void onSocValueChanged(int i) {
            Log.w(OtaManagerImpl.TAG, "onSocValueChanged: value = " + i);
            if (OtaManagerImpl.this.mlistener != null) {
                OtaManagerImpl.this.mlistener.onSocValueChanged(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onSocValueChanged: mlistener is null");
            }
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

        public void onTboxCmd2Ivi(int i) {
            Log.w(OtaManagerImpl.TAG, "onTboxCmd2Ivi callback cmd : " + i);
            if (OtaManagerImpl.this.mTboxListener != null) {
                OtaManagerImpl.this.mTboxListener.onTBoxCmd2IVI(i);
            } else {
                Log.e(OtaManagerImpl.TAG, "onTboxCmd2Ivi: callback is null");
            }
        }
    };
    IAbsMessageCallback.Stub otaBydAutoCallback = new IAbsMessageCallback.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.3
        public void onEcuInfo(List<EcuInfo> list) {
            Log.w(OtaManagerImpl.TAG, "onEcuInfo callback->ecuInfoList size: " + list.size());
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onEcuInfo: absMsgCallbackMap is null");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (EcuInfo ecuInfo : list) {
                android.hardware.bydauto.ota.EcuInfo ecuInfo2 = new android.hardware.bydauto.ota.EcuInfo();
                ecuInfo2.setEcuId(ecuInfo.getEcuId());
                ecuInfo2.setEcuSoftCode(ecuInfo.getEcuSoftCode());
                ecuInfo2.setEcuSoftVer(ecuInfo.getEcuSoftVer());
                ecuInfo2.setEcuHardVer(ecuInfo.getEcuHardVer());
                ecuInfo2.setEcuConfigVer(ecuInfo.getEcuConfigVer());
                ecuInfo2.setEcuConfigCode(ecuInfo.getEcuConfigCode());
                ecuInfo2.setEcuDId(ecuInfo.getEcuDId());
                ecuInfo2.setEcuDiaId(ecuInfo.getEcuDiaId());
                ecuInfo2.setEcuHardDid(ecuInfo.getEcuHardDid());
                ecuInfo2.setEcuConfigDid(ecuInfo.getEcuConfigDid());
                arrayList.add(ecuInfo2);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onEcuInfo")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onEcuInfo");
                absMessageCallback.onEcuInfo(arrayList);
                Log.w(OtaManagerImpl.TAG, "onEcuInfo: register callback is " + absMessageCallback);
            }
        }

        public void onGetAQSlot(int i) {
            Log.w(OtaManagerImpl.TAG, "onGetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetAQSlot")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetAQSlot");
                    absMessageCallback.onGetAQSlot(i);
                    Log.w(OtaManagerImpl.TAG, "onGetAQSlot: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onGetAQSlot: absMsgCallbackMap is null");
        }

        public void onSetAQSlot(int i) {
            Log.w(OtaManagerImpl.TAG, "onSetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSetAQSlot")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSetAQSlot");
                    absMessageCallback.onSetAQSlot(i);
                    Log.w(OtaManagerImpl.TAG, "onSetAQSlot: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSetAQSlot: absMsgCallbackMap is null");
        }

        public void onAppointmentTime(int i) {
            Log.w(OtaManagerImpl.TAG, "onAppointmentTime callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAppointmentTime")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAppointmentTime");
                    absMessageCallback.onAppointmentTime(i);
                    Log.w(OtaManagerImpl.TAG, "onAppointmentTime: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onAppointmentTime: absMsgCallbackMap is null");
        }

        public void onUpdateFlag(int i) {
            Log.w(OtaManagerImpl.TAG, "onUpdateFlag callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onUpdateFlag")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onUpdateFlag");
                    absMessageCallback.onUpdateFlag(i);
                    Log.w(OtaManagerImpl.TAG, "onUpdateFlag: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onUpdateFlag: absMessageCallbacks is null");
        }

        public void onQnxOtaState(int i) {
            Log.w(OtaManagerImpl.TAG, "onQnxOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onQnxOtaState")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onQnxOtaState");
                    absMessageCallback.onQnxOtaState(i);
                    Log.w(OtaManagerImpl.TAG, "onQnxOtaState: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onQnxOtaState: absMessageCallbacks is null");
        }

        public void onAndroidOtaState(int i) {
            Log.w(OtaManagerImpl.TAG, "onAndroidOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAndroidOtaState")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAndroidOtaState");
                    absMessageCallback.onAndroidOtaState(i);
                    Log.w(OtaManagerImpl.TAG, "onAndroidOtaState: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onAndroidOtaState: absMessageCallbacks is null");
        }

        public void onCloudServiceListener(int i, Bundle bundle) {
            Log.w(OtaManagerImpl.TAG, "onCloudServiceListener callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onCloudServiceListener")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onCloudServiceListener");
                    absMessageCallback.onCloudServiceListener(i, bundle);
                    Log.w(OtaManagerImpl.TAG, "onCloudServiceListener: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onCloudServiceListener: absMessageCallbacks is null");
        }

        public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                List<DiagnosticInfo> listGenerateBydApi = null;
                try {
                    listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
                } catch (Exception e) {
                    Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e);
                }
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetDiagnosticTroubleCode")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetDiagnosticTroubleCode");
                    absMessageCallback.onGetDiagnosticTroubleCode(listGenerateBydApi);
                    Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: absMessageCallbacks is null");
        }

        public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                List<DiagnosticInfo> listGenerateBydApi = null;
                try {
                    listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
                } catch (Exception e) {
                    Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e);
                }
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onClearDiagnosticTroubleCode")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onClearDiagnosticTroubleCode");
                    absMessageCallback.onClearDiagnosticTroubleCode(listGenerateBydApi);
                    Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: absMessageCallbacks is null");
        }

        public void onSendCanNM(int i) {
            Log.w(OtaManagerImpl.TAG, "onSendCanNM callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap != null && !OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSendCanNM")) {
                    AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSendCanNM");
                    absMessageCallback.onSendCanNM(i);
                    Log.w(OtaManagerImpl.TAG, "onSendCanNM: register callback is " + absMessageCallback);
                    return;
                }
                return;
            }
            Log.e(OtaManagerImpl.TAG, "onSendCanNM: absMessageCallbacks is null");
        }
    };

    public OtaManagerImpl(Context context) {
        this.mTestCallback = null;
        this.mTestOtaUpgradeManagerCallBack = null;
        this.mSdkManager = null;
        this.mContext = null;
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (this.mContentResolver != null && 1 == this.mForTest) {
            Log.w(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback = new TestOtaManagerImplCallback();
            this.mTestOtaUpgradeManagerCallBack = new TestOtaUpgradeManagerCallBack();
            return;
        }
        Log.e(TAG, "OtaSdkManager.getInstance");
        this.mSdkManager = OtaSdkManager.getInstance(this.mContext);
    }

    public static synchronized OtaManagerImpl getInstance(Context context) {
        if (sTsOtaManagerImpl == null) {
            sTsOtaManagerImpl = new OtaManagerImpl(context);
        }
        return sTsOtaManagerImpl;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    private boolean storeAbsMsgCallback(String str, AbsMessageCallback absMessageCallback) {
        Map<String, AbsMessageCallback> map;
        Log.w(TAG, "storeAbsMsgCallback: callback->" + absMessageCallback);
        if (absMessageCallback == null || (map = this.absMsgCallbackMap) == null) {
            return false;
        }
        map.put(str, absMessageCallback);
        return true;
    }

    public void trigerTestOtaManagerImplCallback(int i, Object obj) {
        this.mTestCallback.enqueueMessage(i, obj);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int i, Object obj) {
        this.mTestOtaUpgradeManagerCallBack.enqueueMessage(i, obj);
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        registerListener(absBYDAutoOtaListener, null);
    }

    public void registerListener(AbsBYDTBoxListener absBYDTBoxListener) {
        Log.w(TAG, "register AbsBYDTBoxListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
        } else {
            this.mTboxListener = absBYDTBoxListener;
            this.mSdkManager.registerTboxListener(this.mITestTboxCallBack);
        }
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener, int[] iArr) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(absBYDAutoOtaListener);
        } else {
            this.mlistener = absBYDAutoOtaListener;
            this.mSdkManager.registerListener(this.otaBydAutoListener);
        }
    }

    public void unregisterListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        Log.w(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(null);
        } else {
            this.mlistener = null;
            this.mSdkManager.unregisterListener(this.otaBydAutoListener);
        }
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
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
        if (1 != this.mForTest) {
            return "not impl";
        }
        Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public String getGetPermission() {
        Log.w(TAG, "getGetPermission is execute");
        if (1 != this.mForTest) {
            return "not impl";
        }
        Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
        return "ForTest";
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

    public int notifyTargetID(byte[] bArr) {
        Log.w(TAG, "notifyTargetID is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTargetID mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.notifyTargetID(bArr);
    }

    public int notifyTargetID2(byte[] bArr) {
        Log.w(TAG, "notifyTargetID2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTargetID2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.notifyTargetID2(bArr);
    }

    public int sendOTAData(byte[] bArr) {
        Log.w(TAG, "sendOTAData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAData mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAData(bArr);
    }

    public int sendOTAData2(byte[] bArr) {
        Log.w(TAG, "sendOTAData2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAData2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAData2(bArr);
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

    public int sendOTAServiceData(byte[] bArr) {
        Log.w(TAG, "sendOTAServiceData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTAServiceData mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTAServiceData(bArr);
    }

    public int sendECUIds(int i, byte[] bArr) {
        Log.w(TAG, "sendECUIds is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendECUIds mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendECUIds(i, bArr);
    }

    public int sendOTACtlCMD(OTAControlCMD oTAControlCMD) {
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

    public int queryECUVersionAndSoftcode(byte[] bArr) {
        Log.w(TAG, "queryECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " queryECUVersionAndSoftcode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.queryECUVersionAndSoftcode(bArr);
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

    public int queryMCUVersionAndSoftcode(byte[] bArr) {
        Log.w(TAG, "queryMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " queryMCUVersionAndSoftcode mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.queryMCUVersionAndSoftcode(bArr);
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

    public int sendOTATimecountCtlCMD(int i, int i2, int i3, int i4) {
        Log.w(TAG, "sendOTATimecountCtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendOTATimecountCtlCMD mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendOTATimecountCtlCMD(i, i2, i3, i4);
    }

    public int setScreenState(int i) {
        Log.w(TAG, "setScreenState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setScreenState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setScreenState(i);
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

    public int sendECUIds2(int i, byte[] bArr) {
        Log.w(TAG, "sendECUIds2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendECUIds2 mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendECUIds2(i, bArr);
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

    public int setPowerOnForbidState(int i) {
        Log.w(TAG, "setPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setPowerOnForbidState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setPowerOnForbidState(i);
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

    public int setCanInfoSwitchState(int i) {
        Log.w(TAG, "setCanInfoSwitchState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setCanInfoSwitchState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setCanInfoSwitchState(i);
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

    public int setMcuMessage(int i) {
        Log.w(TAG, "setMcuMessage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setMcuMessage mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.setMcuMessage(i);
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

    public int sendMonitorTable(byte[] bArr) {
        Log.w(TAG, "sendMonitorTable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendMonitorTable mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.sendMonitorTable(bArr);
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
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

    public boolean getEcuInfo(List<Info> list, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "getEcuInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getEcuInfo: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onEcuInfo", absMessageCallback);
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            for (Info info : list) {
                com.ts.ota.otasdkmgr.Info info2 = new com.ts.ota.otasdkmgr.Info();
                info2.setEcuId(info.getEcuId());
                info2.setEcuDiaId(info.getEcuDiaId());
                info2.setEcuDId(info.getEcuDId());
                info2.setEcuHardDid(info.getEcuHardDid());
                info2.setEcuConfigDid(info.getEcuConfigDid());
                arrayList.add(info2);
            }
            return this.mSdkManager.getEcuInfo(arrayList, this.otaBydAutoCallback);
        }
        Log.w(TAG, "getEcuInfo: Info is empty");
        return false;
    }

    public boolean getAQSlot(AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "getAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onGetAQSlot", absMessageCallback);
        return this.mSdkManager.getAQSlot(this.otaBydAutoCallback);
    }

    public boolean setAQSlot(int i, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSetAQSlot", absMessageCallback);
        return this.mSdkManager.setAQSlot(i, this.otaBydAutoCallback);
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

    public int getBatteryTemp(int i) {
        Log.w(TAG, "getBatteryTemp is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getEnvironmentTemp mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryTemp(i);
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

    public int getBatteryVoltage(int i) {
        Log.w(TAG, "getBatteryVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getBatteryVoltage mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getBatteryVoltage(i);
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

    public int getDischargeState(int i) {
        Log.w(TAG, "getDischargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getDischargeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getDischargeState(i);
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

    public int getSmartChargeState(int i) {
        Log.w(TAG, "getSmartChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getSmartChargeState mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.getSmartChargeState(i);
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

    public boolean setSmartPower(int i) {
        Log.w(TAG, "setSmartPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setSmartPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setSmartPower(i);
    }

    public boolean setEclPower(int i) {
        Log.w(TAG, "setEclPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setEclPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setEclPower(i);
    }

    public boolean setQuitSmartCharge(int i) {
        Log.w(TAG, "setQuitSmartCharge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQuitSmartCharge mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setQuitSmartCharge(i);
    }

    public boolean setQuitChargeAndDischarge(int i) {
        Log.w(TAG, "setQuitChargeAndDischarge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQuitChargeAndDischarge mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setQuitChargeAndDischarge(i);
    }

    public boolean setOtaModel(int i) {
        Log.w(TAG, "setOtaModel is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setOtaModel mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setOtaModel(i);
    }

    public boolean setOtaPower(int i) {
        Log.w(TAG, "setOtaPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setOtaPower mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setOtaPower(i);
    }

    public boolean setHighVoltageCmd(int i) {
        Log.w(TAG, "setHighVoltageCmd is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setHighVoltageCmd(i);
    }

    public boolean setBackupAreaDownload(int i) {
        Log.w(TAG, "setBackupAreaDownload is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setBackupAreaDownload(i);
    }

    public boolean setBackLightState(int i) {
        Log.w(TAG, "setBackLightState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.setBackLightState(i);
    }

    @Deprecated
    public void startUpdate(int i, IUpdateListener iUpdateListener) {
        Log.w(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(i, iUpdateListener);
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

    public void registerCallback(IUpdateListener iUpdateListener) {
        Log.w(TAG, "registerCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerCallback mForTestValue is 0 execute return");
            this.mTestOtaUpgradeManagerCallBack.setUpdateListener(iUpdateListener);
        } else {
            this.mSdkManager.registerCallback(iUpdateListener);
        }
    }

    public void unregisterCallback(IUpdateListener iUpdateListener) {
        Log.w(TAG, "unregisterCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterCallback mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.unregisterCallback(iUpdateListener);
        }
    }

    public void startUpdate(String str) {
        Log.w(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(str);
        }
    }

    public void startDownloadBackup(String str) {
        Log.w(TAG, "startDownloadBackup is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startDownloadBackup mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startDownloadBackup(str);
        }
    }

    public void startUpdateTbox(String str) {
        Log.w(TAG, "startUpdateTbox is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdateTbox mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdateTbox(str);
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

    public boolean getTBoxUpgradeLog(String str) {
        Log.w(TAG, "getTBoxUpgradeLog is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getTBoxUpgradeLog mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.getTBoxUpgradeLog(str);
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

    public void setAppointmentTime(int i, int i2, int i3, int i4, int i5, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAppointmentTime is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAppointmentTime mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAppointmentTime", absMessageCallback);
            this.mSdkManager.setAppointmentTime(i, i2, i3, i4, i5, this.otaBydAutoCallback);
        }
    }

    public boolean sendCanNM(int i, int i2, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "sendCanNM is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendCanNM: mForTest is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSendCanNM", absMessageCallback);
        return this.mSdkManager.sendCanNM(i, i2, this.otaBydAutoCallback);
    }

    public void setUpdateFlag(int i, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setUpdateFlag is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setUpdateFlag mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onUpdateFlag", absMessageCallback);
            this.mSdkManager.setUpdateFlag(i, this.otaBydAutoCallback);
        }
    }

    public void setQnxOtaState(int i, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setQnxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQnxOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onQnxOtaState", absMessageCallback);
            this.mSdkManager.setQnxOtaState(i, this.otaBydAutoCallback);
        }
    }

    public void setAndroidOtaState(int i, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAndroidOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAndroidOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAndroidOtaState", absMessageCallback);
            this.mSdkManager.setAndroidOtaState(i, this.otaBydAutoCallback);
        }
    }

    public void setCloudServiceListener(AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setCloudServiceListener mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onCloudServiceListener", absMessageCallback);
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

    public void notifyTboxOtaState(int i) {
        Log.w(TAG, "notifyTboxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTboxOtaState mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.notifyTboxOtaState(i);
        }
    }

    public boolean executeCMD(Bundle bundle) {
        Log.w(TAG, "executeCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
            return false;
        }
        return this.mSdkManager.executeCMD(bundle);
    }

    public void setMobileNetworkState(boolean z) {
        Log.w(TAG, "setMobileNetworkState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setMobileNetworkState(z);
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

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 == this.mForTest) {
            Log.e(TAG, " getDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        storeAbsMsgCallback("onGetDiagnosticTroubleCode", absMessageCallback);
        this.mSdkManager.getDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 == this.mForTest) {
            Log.e(TAG, " clearDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        storeAbsMsgCallback("onClearDiagnosticTroubleCode", absMessageCallback);
        this.mSdkManager.clearDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
    }

    public int getProhibitPowerOnState() {
        if (1 == this.mForTest) {
            Log.e(TAG, " getProhibitPowerOnState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getProhibitPowerOnState();
    }

    public boolean setMultimediaSelfUpdate(int i) {
        if (1 == this.mForTest) {
            Log.e(TAG, " setMultimediaSelfUpdate mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setMultimediaSelfUpdate(i);
    }

    public int notifyTBoxTrafficResult(int i) {
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTBoxTrafficResult mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.notifyTboxTrafficResult(i);
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

    public int updateHsm(String str) {
        if (1 == this.mForTest) {
            Log.e(TAG, " updateHsm mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.updateHsm(str);
    }

    public int notifyTBoxCmdResult(int i, int i2) {
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTBoxCmdResult mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iNotifyTboxCmdResult = this.mSdkManager.notifyTboxCmdResult(i, i2);
        if (iNotifyTboxCmdResult == 0) {
            return 0;
        }
        if (iNotifyTboxCmdResult == 1) {
            return -2147482648;
        }
        if (iNotifyTboxCmdResult != 2) {
            return iNotifyTboxCmdResult != 3 ? -2147482648 : -2147482645;
        }
        return -2147482646;
    }

    public int uploadCDR(int i, String str) {
        if (1 == this.mForTest) {
            Log.e(TAG, " uploadCDR mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iUploadCdr = this.mSdkManager.uploadCdr(i, str);
        if (iUploadCdr == 0) {
            return 0;
        }
        if (iUploadCdr == 1) {
            return -2147482648;
        }
        if (iUploadCdr != 2) {
            return iUploadCdr != 3 ? -2147482648 : -2147482645;
        }
        return -2147482646;
    }

    public int setTcpDumpState(boolean z) {
        Log.w(TAG, "setTcpDumpState is execute");
        if (1 == this.mForTest) {
            Log.e(TAG, " setTcpDumpState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setTcpDumpState(z);
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

    public int setApn3State(boolean z) {
        Log.w(TAG, "setApn3State is execute: " + z);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.setApn3State(z);
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

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i == 2362) {
                    setEclPower(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_CMD_ECL_Power.POWER_ON_SET:");
                } else if (i == 2386) {
                    setBackupAreaDownload(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_DOUBLE_DIFF_DOWNLOAD_FLAG_SET:");
                } else if (i == 2380) {
                    setHighVoltageCmd(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_CMD_OTA_HIGH_VOLTAGE_MODULE_SET:");
                } else if (i != 2381) {
                    switch (i) {
                        case 2374:
                            setOtaModel(bYDAutoEventValue.intValue);
                            Log.w(TAG, "OTA_CMD_ENTER_OTA_MODE_SET:");
                            break;
                        case 2375:
                            setQuitChargeAndDischarge(bYDAutoEventValue.intValue);
                            Log.w(TAG, "OTA_CMD_EXIT_CHARGING_DIS_SET:");
                            break;
                        case 2376:
                            setQuitSmartCharge(bYDAutoEventValue.intValue);
                            Log.w(TAG, "OTA_CMD_EXIT_SMART_CHARGING_SET:");
                            break;
                    }
                } else {
                    setOtaPower(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_CMD_OTA_Power.POWER_CMD_SET:");
                }
            }
        }
        return 1;
    }

    private List<DiagnosticAidlInfo> generateAidl(List<DiagnosticInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (DiagnosticInfo diagnosticInfo : list) {
            DiagnosticAidlInfo diagnosticAidlInfo = new DiagnosticAidlInfo();
            diagnosticAidlInfo.ecuId = diagnosticInfo.getEcuId();
            diagnosticAidlInfo.ecuDiaId = diagnosticInfo.getEcuDiaId();
            diagnosticAidlInfo.ecuReprogramType = diagnosticInfo.getEcuReprogramType();
            diagnosticAidlInfo.readDtcInfoType = getReadDtcInfoType(diagnosticInfo.getReadDtcInfoType());
            diagnosticAidlInfo.ecuDtcInfo = diagnosticInfo.getEcuDtcInfo();
            diagnosticAidlInfo.ecuDtcId = diagnosticInfo.getEcuDtcId();
            diagnosticAidlInfo.snapshotRecordNumber = diagnosticInfo.getSnapshotRecordNumber();
            diagnosticAidlInfo.extDataRecordNumber = diagnosticInfo.getExtDataRecordNumber();
            diagnosticAidlInfo.statusMask = diagnosticInfo.getStatusMask();
            diagnosticAidlInfo.recordMask = diagnosticInfo.getRecordMask();
            arrayList.add(diagnosticAidlInfo);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DiagnosticInfo> generateBydApi(List<DiagnosticAidlInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (DiagnosticAidlInfo diagnosticAidlInfo : list) {
            DiagnosticInfo diagnosticInfo = new DiagnosticInfo();
            diagnosticInfo.ecuId = diagnosticAidlInfo.getEcuId();
            diagnosticInfo.ecuDiaId = diagnosticAidlInfo.getEcuDiaId();
            diagnosticInfo.ecuReprogramType = diagnosticAidlInfo.getEcuReprogramType();
            diagnosticInfo.readDtcInfoType = setReadDtcInfoType(diagnosticAidlInfo.getReadDtcInfoType());
            diagnosticInfo.ecuDtcInfo = diagnosticAidlInfo.getEcuDtcInfo();
            diagnosticInfo.ecuDtcId = diagnosticAidlInfo.getEcuDtcId();
            diagnosticInfo.snapshotRecordNumber = diagnosticAidlInfo.getSnapshotRecordNumber();
            diagnosticInfo.extDataRecordNumber = diagnosticAidlInfo.getExtDataRecordNumber();
            diagnosticInfo.statusMask = diagnosticAidlInfo.getStatusMask();
            diagnosticInfo.recordMask = diagnosticAidlInfo.getRecordMask();
            arrayList.add(diagnosticInfo);
        }
        return arrayList;
    }

    private int getReadDtcInfoType(ReadDTCInfoType readDTCInfoType) {
        if (readDTCInfoType != null) {
            return readDTCInfoType.getValue();
        }
        return 0;
    }

    private ReadDTCInfoType setReadDtcInfoType(int i) {
        if (i == 1) {
            return ReadDTCInfoType.DTC_INFO_STATUS_MASK;
        }
        if (i == 2) {
            return ReadDTCInfoType.DTC_INFO_SNAPSHOT_DATA;
        }
        if (i == 3) {
            return ReadDTCInfoType.DTC_INFO_EXTEND_DATA_RECORDS;
        }
        if (i != 4) {
            return null;
        }
        return ReadDTCInfoType.DTC_INFO_ALL;
    }

    public void caseFeatureIds(int i, int i2) {
        if (i == 0 || this.mlistener == null) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i2;
        Log.w(TAG, ":caseFeatureIds " + this.bydAutoEventValueListener.intValue);
        this.mlistener.onDataEventChanged(i, this.bydAutoEventValueListener);
    }
}
