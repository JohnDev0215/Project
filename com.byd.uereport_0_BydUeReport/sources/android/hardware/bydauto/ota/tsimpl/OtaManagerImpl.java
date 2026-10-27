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
import java.util.Iterator;
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
    public static final String TAG = "TS.OtaManagerImpl";
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
    private final List<AbsBYDAutoOtaListener> mOtaListenerList = new ArrayList();
    private Map<String, AbsMessageCallback> absMsgCallbackMap = new HashMap();
    private int mForTest = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mNowBind = false;
    public IAbsBYDAutoOtaListener.Stub otaBydAutoListener = new IAbsBYDAutoOtaListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.1
        public void on7dfExistState(boolean z7) {
            Log.w(OtaManagerImpl.TAG, "on7dfExistState: state = " + z7);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "on7dfExistState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).on7dfExistState(z7);
            }
        }

        public void onBackupAreaDownloadState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onBackupAreaDownloadState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onBackupAreaDownloadState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onBackupAreaDownloadState(i8);
            }
        }

        public void onBcmOtaState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onBcmOtaState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onBcmOtaState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onBcmOtaState(i8);
            }
        }

        public void onDcState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onDcState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onDcState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onDcState(i8);
            }
        }

        public void onIg1RelayState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onIg1RelayState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onIg1RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg1RelayState(i8);
            }
        }

        public void onIg3RelayState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onIg3RelayState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onIg3RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg3RelayState(i8);
            }
        }

        public void onIg4RelayState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onIg4RelayState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onIg4RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg4RelayState(i8);
            }
        }

        public void onLFDoorLockStatus(int i8) {
            Log.w(OtaManagerImpl.TAG, "onLFDoorLockStatus: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onLFDoorLockStatus: mOtaListenerList is null");
            } else {
                OtaManagerImpl.this.caseFeatureIds(2389, i8);
            }
        }

        public void onNfcOperationCommand(int i8) {
        }

        public void onProhibitPowerOnState(int i8) {
            Log.w(OtaManagerImpl.TAG, " onProhibitPowerOnState.in state : " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onProhibitPowerOnState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onProhibitPowerOnState(i8);
            }
        }

        public void onSmartPowerState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onSmartPowerState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onSmartPowerState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onSmartPowerState(i8);
            }
        }

        public void onSocValueChanged(int i8) {
            Log.w(OtaManagerImpl.TAG, "onSocValueChanged: value = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onSocValueChanged: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onSocValueChanged(i8);
            }
        }

        public void onStartKeyState(int i8) {
            Log.w(OtaManagerImpl.TAG, " onStartKeyState.in state : " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onStartKeyState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onStartKeyState(i8);
            }
        }

        public void onTurnOffState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onTurnOffState: state = " + i8);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onTurnOffState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onTurnOffState(i8);
            }
        }
    };
    public ITboxCallBack mITestTboxCallBack = new ITboxCallBack() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.2
        public void onGetApnParamFromTbox(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            Log.w(OtaManagerImpl.TAG, "onGetApnParamFromTbox");
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onGetApnParamFromTbox: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onGetApnParamFromTBox(str, str2, str3, str4, str5, str6, str7);
            }
        }

        public void onGetTboxNetworkState(int i8, int i9) {
            Log.d(OtaManagerImpl.TAG, "onGetTboxNetworkState : " + i8 + ", state : " + i9);
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onGetTboxNetworkState: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onGetTBoxNetworkState(i8, i9);
            }
        }

        public void onGetTrafficFromTbox(int i8, String str, String str2, long j8, long j9, long j10, long j11) {
            Log.w(OtaManagerImpl.TAG, "onGetTrafficFromTbox");
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onGetTrafficFromTbox: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onGetTrafficFromTBox(i8, str, str2, j8, j9, j10, j11);
            }
        }

        public void onSyncApnParamResult(int i8) {
            Log.w(OtaManagerImpl.TAG, "onSyncApnParamResult");
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onSyncApnParamResult: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onSyncApnParamResult(i8);
            }
        }

        public void onTboxCmd2Ivi(int i8) {
            Log.w(OtaManagerImpl.TAG, "onTboxCmd2Ivi callback cmd : " + i8);
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onTboxCmd2Ivi: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onTBoxCmd2IVI(i8);
            }
        }

        public void onUpdateTrafficData() {
            Log.w(OtaManagerImpl.TAG, "onUpdateTrafficData callback...");
            if (OtaManagerImpl.this.mTboxListener == null) {
                Log.e(OtaManagerImpl.TAG, "onUpdateTrafficData: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onUpdateTrafficData();
            }
        }
    };
    public IAbsMessageCallback.Stub otaBydAutoCallback = new IAbsMessageCallback.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.3
        public void onAndroidOtaState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onAndroidOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onAndroidOtaState: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAndroidOtaState")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAndroidOtaState");
                absMessageCallback.onAndroidOtaState(i8);
                Log.w(OtaManagerImpl.TAG, "onAndroidOtaState: register callback is " + absMessageCallback);
            }
        }

        public void onAppointmentTime(int i8) {
            Log.w(OtaManagerImpl.TAG, "onAppointmentTime callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onAppointmentTime: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAppointmentTime")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAppointmentTime");
                absMessageCallback.onAppointmentTime(i8);
                Log.w(OtaManagerImpl.TAG, "onAppointmentTime: register callback is " + absMessageCallback);
            }
        }

        public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            List<DiagnosticInfo> listGenerateBydApi = null;
            try {
                listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
            } catch (Exception e8) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e8);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onClearDiagnosticTroubleCode")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onClearDiagnosticTroubleCode");
                absMessageCallback.onClearDiagnosticTroubleCode(listGenerateBydApi);
                Log.w(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: register callback is " + absMessageCallback);
            }
        }

        public void onCloudServiceListener(int i8, Bundle bundle) {
            Log.w(OtaManagerImpl.TAG, "onCloudServiceListener callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onCloudServiceListener: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onCloudServiceListener")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onCloudServiceListener");
                absMessageCallback.onCloudServiceListener(i8, bundle);
                Log.w(OtaManagerImpl.TAG, "onCloudServiceListener: register callback is " + absMessageCallback);
            }
        }

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

        public void onGetAQSlot(int i8) {
            Log.w(OtaManagerImpl.TAG, "onGetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onGetAQSlot: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetAQSlot")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetAQSlot");
                absMessageCallback.onGetAQSlot(i8);
                Log.w(OtaManagerImpl.TAG, "onGetAQSlot: register callback is " + absMessageCallback);
            }
        }

        public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            List<DiagnosticInfo> listGenerateBydApi = null;
            try {
                listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
            } catch (Exception e8) {
                Log.e(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e8);
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetDiagnosticTroubleCode")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetDiagnosticTroubleCode");
                absMessageCallback.onGetDiagnosticTroubleCode(listGenerateBydApi);
                Log.w(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: register callback is " + absMessageCallback);
            }
        }

        public void onQnxOtaState(int i8) {
            Log.w(OtaManagerImpl.TAG, "onQnxOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onQnxOtaState: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onQnxOtaState")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onQnxOtaState");
                absMessageCallback.onQnxOtaState(i8);
                Log.w(OtaManagerImpl.TAG, "onQnxOtaState: register callback is " + absMessageCallback);
            }
        }

        public void onSendCanNM(int i8) {
            Log.w(OtaManagerImpl.TAG, "onSendCanNM callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onSendCanNM: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSendCanNM")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSendCanNM");
                absMessageCallback.onSendCanNM(i8);
                Log.w(OtaManagerImpl.TAG, "onSendCanNM: register callback is " + absMessageCallback);
            }
        }

        public void onSetAQSlot(int i8) {
            Log.w(OtaManagerImpl.TAG, "onSetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onSetAQSlot: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSetAQSlot")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSetAQSlot");
                absMessageCallback.onSetAQSlot(i8);
                Log.w(OtaManagerImpl.TAG, "onSetAQSlot: register callback is " + absMessageCallback);
            }
        }

        public void onUpdateFlag(int i8) {
            Log.w(OtaManagerImpl.TAG, "onUpdateFlag callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                Log.e(OtaManagerImpl.TAG, "onUpdateFlag: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onUpdateFlag")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onUpdateFlag");
                absMessageCallback.onUpdateFlag(i8);
                Log.w(OtaManagerImpl.TAG, "onUpdateFlag: register callback is " + absMessageCallback);
            }
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
        if (this.mContentResolver == null || 1 != this.mForTest) {
            Log.e(TAG, "OtaSdkManager.getInstance");
            OtaSdkManager otaSdkManager = OtaSdkManager.getInstance(this.mContext);
            this.mSdkManager = otaSdkManager;
            otaSdkManager.registerListener(this.otaBydAutoListener);
            return;
        }
        Log.w(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback = new TestOtaManagerImplCallback();
        this.mTestOtaUpgradeManagerCallBack = new TestOtaUpgradeManagerCallBack();
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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

    public static synchronized OtaManagerImpl getInstance(Context context) {
        if (sTsOtaManagerImpl == null) {
            sTsOtaManagerImpl = new OtaManagerImpl(context);
        }
        return sTsOtaManagerImpl;
    }

    private int getReadDtcInfoType(ReadDTCInfoType readDTCInfoType) {
        if (readDTCInfoType != null) {
            return readDTCInfoType.getValue();
        }
        return 0;
    }

    private ReadDTCInfoType setReadDtcInfoType(int i8) {
        if (i8 == 1) {
            return ReadDTCInfoType.DTC_INFO_STATUS_MASK;
        }
        if (i8 == 2) {
            return ReadDTCInfoType.DTC_INFO_SNAPSHOT_DATA;
        }
        if (i8 == 3) {
            return ReadDTCInfoType.DTC_INFO_EXTEND_DATA_RECORDS;
        }
        if (i8 != 4) {
            return null;
        }
        return ReadDTCInfoType.DTC_INFO_ALL;
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

    public int FinishOTA() {
        Log.w(TAG, "FinishOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.FinishOTA();
        }
        Log.e(TAG, " FinishOTA mForTestValue is 0 execute return");
        return -1;
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

    public int StartOTA() {
        Log.w(TAG, "StartOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.StartOTA();
        }
        Log.e(TAG, " StartOTA mForTestValue is 0 execute return");
        return -1;
    }

    public void caseFeatureIds(int i8, int i9) {
        if (i8 == 0 || this.mOtaListenerList.isEmpty()) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i9;
        Log.w(TAG, ":caseFeatureIds " + this.bydAutoEventValueListener.intValue);
        Iterator<AbsBYDAutoOtaListener> it = this.mOtaListenerList.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i8, this.bydAutoEventValueListener);
        }
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
            storeAbsMsgCallback("onClearDiagnosticTroubleCode", absMessageCallback);
            this.mSdkManager.clearDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
        } else {
            Log.e(TAG, " clearDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
        }
    }

    public int clearTboxLog() {
        Log.w(TAG, "clearTboxLog is execute");
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
            return this.mSdkManager.clearTboxLog();
        }
        Log.e(TAG, " clearTboxLog mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        Log.w(TAG, "decodeAndCheckSign is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.decodeAndCheckSign(bundle);
        }
        Log.e(TAG, " decodeAndCheckSign mForTestValue is 0 execute return");
        return false;
    }

    public int enableSimcardNetwork(int i8) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.enableSimcardNetwork(i8);
    }

    public boolean executeCMD(Bundle bundle) {
        Log.w(TAG, "executeCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.executeCMD(bundle);
        }
        Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
        return false;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i8 : iArr) {
                if (i8 > 0) {
                    if (i8 == 314) {
                        int socValue = getSocValue();
                        bYDAutoEventValue.intValue = socValue;
                        sparseArray.put(314, Integer.valueOf(socValue));
                    } else if (i8 == 2385) {
                        int dischargeMainContactorState = getDischargeMainContactorState();
                        bYDAutoEventValue.intValue = dischargeMainContactorState;
                        sparseArray.put(2385, Integer.valueOf(dischargeMainContactorState));
                    } else if (i8 == 2389) {
                        int lFDoorLockStatus = getLFDoorLockStatus();
                        bYDAutoEventValue.intValue = lFDoorLockStatus;
                        sparseArray.put(2389, Integer.valueOf(lFDoorLockStatus));
                    } else if (i8 == 2395) {
                        int turnOffState = getTurnOffState();
                        bYDAutoEventValue.intValue = turnOffState;
                        sparseArray.put(2395, Integer.valueOf(turnOffState));
                    } else if (i8 == 2411) {
                        int bcmOtaState = getBcmOtaState();
                        bYDAutoEventValue.intValue = bcmOtaState;
                        sparseArray.put(2411, Integer.valueOf(bcmOtaState));
                    } else if (i8 == 2432) {
                        int ig3RelayState = getIg3RelayState();
                        bYDAutoEventValue.intValue = ig3RelayState;
                        sparseArray.put(2432, Integer.valueOf(ig3RelayState));
                    } else if (i8 == 2437) {
                        int dcState = getDcState();
                        bYDAutoEventValue.intValue = dcState;
                        sparseArray.put(2437, Integer.valueOf(dcState));
                    } else if (i8 == 3319) {
                        int batteryTemp = getBatteryTemp(1);
                        bYDAutoEventValue.intValue = batteryTemp;
                        sparseArray.put(3319, Integer.valueOf(batteryTemp));
                    } else if (i8 == 3534) {
                        int batteryTemp2 = getBatteryTemp(1);
                        bYDAutoEventValue.intValue = batteryTemp2;
                        sparseArray.put(3534, Integer.valueOf(batteryTemp2));
                    } else if (i8 == 3606) {
                        int epbState = getEpbState();
                        bYDAutoEventValue.intValue = epbState;
                        sparseArray.put(3606, Integer.valueOf(epbState));
                    } else if (i8 == 2356) {
                        int batteryVoltage = getBatteryVoltage(0);
                        bYDAutoEventValue.intValue = batteryVoltage;
                        sparseArray.put(2356, Integer.valueOf(batteryVoltage));
                    } else if (i8 == 2357) {
                        int batteryVoltage2 = getBatteryVoltage(1);
                        bYDAutoEventValue.intValue = batteryVoltage2;
                        sparseArray.put(2357, Integer.valueOf(batteryVoltage2));
                    } else if (i8 == 2652) {
                        int smartChargeState = getSmartChargeState(0);
                        bYDAutoEventValue.intValue = smartChargeState;
                        sparseArray.put(2652, Integer.valueOf(smartChargeState));
                    } else if (i8 != 2653) {
                        switch (i8) {
                            case 2406:
                                boolean smartPowerUsable = getSmartPowerUsable();
                                bYDAutoEventValue.intValue = smartPowerUsable ? 1 : 0;
                                sparseArray.put(2406, Integer.valueOf(smartPowerUsable ? 1 : 0));
                                break;
                            case 2407:
                                int smartPowerException = getSmartPowerException();
                                bYDAutoEventValue.intValue = smartPowerException;
                                sparseArray.put(2407, Integer.valueOf(smartPowerException));
                                break;
                            case 2408:
                                int smartPowerState = getSmartPowerState();
                                bYDAutoEventValue.intValue = smartPowerState;
                                sparseArray.put(2408, Integer.valueOf(smartPowerState));
                                break;
                            default:
                                switch (i8) {
                                    case 2572:
                                        int dischargeState = getDischargeState(2);
                                        bYDAutoEventValue.intValue = dischargeState;
                                        sparseArray.put(2572, Integer.valueOf(dischargeState));
                                        break;
                                    case 2573:
                                        int dischargeState2 = getDischargeState(1);
                                        bYDAutoEventValue.intValue = dischargeState2;
                                        sparseArray.put(2573, Integer.valueOf(dischargeState2));
                                        break;
                                    case 2574:
                                        int chargeAndDischargeSystemState = getChargeAndDischargeSystemState();
                                        bYDAutoEventValue.intValue = chargeAndDischargeSystemState;
                                        sparseArray.put(2574, Integer.valueOf(chargeAndDischargeSystemState));
                                        break;
                                }
                                break;
                        }
                    } else {
                        int smartChargeState2 = getSmartChargeState(1);
                        bYDAutoEventValue.intValue = smartChargeState2;
                        sparseArray.put(2653, Integer.valueOf(smartChargeState2));
                    }
                    int i9 = 0;
                    for (int i10 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i9] = ((Integer) sparseArray.get(i10)).intValue();
                            i9++;
                        } catch (NullPointerException e8) {
                            e8.printStackTrace();
                        }
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public boolean get7dfExistState() {
        Log.w(TAG, "get7dfExistState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.get7dfExistState();
        }
        Log.e(TAG, " get7dfExistState mForTestValue is 0 execute return");
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

    public int getApnParamFromTbox() {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getApnParamFromTbox();
    }

    public int getBatteryManagementDeviceState() {
        Log.w(TAG, "getBatteryManagementDeviceState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryManagementDeviceState();
        }
        Log.e(TAG, " getBatteryManagementDeviceState mForTestValue is 0 execute return");
        return -1;
    }

    public double getBatteryPowerVoltage() {
        Log.w(TAG, "getBatteryPowerVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryPowerVoltage();
        }
        Log.e(TAG, " getBatteryPowerVoltage mForTestValue is 0 execute return");
        return -1.0d;
    }

    public int getBatteryTemp(int i8) {
        Log.w(TAG, "getBatteryTemp is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryTemp(i8);
        }
        Log.e(TAG, " getEnvironmentTemp mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getBatteryType() {
        Log.w(TAG, "getBatteryType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryType();
        }
        Log.e(TAG, " getBatteryType mForTestValue is 0 execute return");
        return -1;
    }

    public int getBatteryVoltage(int i8) {
        Log.w(TAG, "getBatteryVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryVoltage(i8);
        }
        Log.e(TAG, " getBatteryVoltage mForTestValue is 0 execute return");
        return -1;
    }

    public int getBcmOtaState() {
        Log.w(TAG, "getBcmOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBcmOtaState();
        }
        Log.e(TAG, " getBcmOtaState mForTestValue is 0 execute return");
        return -1;
    }

    public int getBurglarState() {
        Log.w(TAG, "getBurglarState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBurglarState();
        }
        Log.e(TAG, " getBurglarState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getCanInfo() {
        Log.w(TAG, "getCanInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getCanInfo();
        }
        Log.e(TAG, " getCanInfo mForTestValue is 0 execute return");
        return null;
    }

    public int getChargeAndDischargeSystemState() {
        Log.w(TAG, "getChargeAndDischargeSystemState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getChargeAndDischargeSystemState();
        }
        Log.e(TAG, " getChargeAndDischargeSystemState mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getChargeState() {
        Log.w(TAG, "getChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getChargeState();
        }
        Log.e(TAG, " getChargeState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDcState() {
        Log.w(TAG, "getDcState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDcState();
        }
        Log.e(TAG, " getDcState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDevicetype() {
        Log.w(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDevicetype();
        }
        Log.e(TAG, " getDevicetype mForTestValue is 0 execute return");
        return -1;
    }

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            storeAbsMsgCallback("onGetDiagnosticTroubleCode", absMessageCallback);
            this.mSdkManager.getDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
        } else {
            Log.e(TAG, " getDiagnosticTroubleCode mForTestValue is " + this.mForTest + " execute return");
        }
    }

    public int getDischargeMainContactorState() {
        Log.w(TAG, "getDischargeMainContactorState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDischargeMainContactorState();
        }
        Log.e(TAG, " getDischargeMainContactorState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDischargeState(int i8) {
        Log.w(TAG, "getDischargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDischargeState(i8);
        }
        Log.e(TAG, " getDischargeState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getECUSoftcode() {
        Log.w(TAG, "getECUSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUSoftcode();
        }
        Log.e(TAG, " getECUSoftcode mForTestValue is 0 execute return");
        return null;
    }

    public byte[] getECUVersion() {
        Log.w(TAG, "getECUVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUVersion();
        }
        Log.e(TAG, " getECUVersion mForTestValue is 0 execute return");
        return null;
    }

    public byte[] getECUVersionAndSoftcode() {
        Log.w(TAG, "getECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUVersionAndSoftcode();
        }
        Log.e(TAG, " getECUVersionAndSoftcode mForTestValue is 0 execute return");
        return null;
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
        if (list == null || list.size() == 0) {
            Log.w(TAG, "getEcuInfo: Info is empty");
            return false;
        }
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

    public int getEpbState() {
        Log.w(TAG, "getEpbState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getEpbState();
        }
        Log.e(TAG, " getEpbState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getFaultCodeInfo() {
        Log.w(TAG, "getFaultCodeInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getFaultCodeInfo();
        }
        Log.e(TAG, " getFaultCodeInfo mForTestValue is 0 execute return");
        return null;
    }

    public int[] getFeatureList() {
        Log.w(TAG, "getFeatureList is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getFeatureList();
        }
        Log.e(TAG, " getFeatureList mForTestValue is 0 execute return");
        return new int[]{0, 0};
    }

    public String getGetPermission() {
        Log.w(TAG, "getGetPermission is execute");
        if (1 != this.mForTest) {
            return "not impl";
        }
        Log.e(TAG, " getGetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int getIg1RelayState() {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            return this.mSdkManager.getIg1RelayState();
        }
        Log.e(TAG, " getIg1RelayState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getIg3RelayState() {
        Log.w(TAG, "getIg3RelayState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getIg3RelayState();
        }
        Log.e(TAG, " getIg3RelayState mForTestValue is 0 execute return");
        return -1;
    }

    public int getIg4RelayState() {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            return this.mSdkManager.getIg4RelayState();
        }
        Log.e(TAG, " getIg4RelayState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    @Deprecated
    public int getIgnitionState() {
        Log.w(TAG, "getIgnitionState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getIgnitionState();
        }
        Log.e(TAG, " getIgnitionState mForTestValue is 0 execute return");
        return -1;
    }

    public int getLFDoorLockState() {
        Log.w(TAG, "getLFDoorLockState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLFDoorLockState();
        }
        Log.e(TAG, " getLFDoorLockState mForTestValue is 0 execute return");
        return -1;
    }

    public int getLFDoorLockStatus() {
        Log.w(TAG, "getLFDoorLockStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLFDoorLockStatus();
        }
        Log.e(TAG, " getLFDoorLockStatus mForTestValue is 0 execute return");
        return 0;
    }

    public int getLocalDiagStatus() {
        Log.w(TAG, "getLocalDiagStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLocalDiagStatus();
        }
        Log.e(TAG, " getLocalDiagStatus mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getMCUVersionAndSoftcode() {
        Log.w(TAG, "getMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getMCUVersionAndSoftcode();
        }
        Log.e(TAG, " getMCUVersionAndSoftcode mForTestValue is 0 execute return");
        return null;
    }

    public boolean getMobileNetworkState() {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            return this.mSdkManager.getMobileNetworkState();
        }
        Log.e(TAG, " getMobileNetworkState mForTestValue is " + this.mForTest + " execute return");
        return false;
    }

    public byte[] getOTAInfoACK() {
        Log.w(TAG, "getOTAInfoACK is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTAInfoACK();
        }
        Log.e(TAG, " getOTAInfoACK mForTestValue is 0 execute return");
        return null;
    }

    public int getOTAState() {
        Log.w(TAG, "getOTAState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTAState();
        }
        Log.e(TAG, " getOTAState mForTestValue is 0 execute return");
        return -1;
    }

    public int getOTATimecountState() {
        Log.w(TAG, "getOTATimecountState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTATimecountState();
        }
        Log.e(TAG, " getOTATimecountState mForTestValue is 0 execute return");
        return -1;
    }

    public int getPowerOnForbidState() {
        Log.w(TAG, "getPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getPowerOnForbidState();
        }
        Log.e(TAG, " getPowerOnForbidState mForTestValue is 0 execute return");
        return -1;
    }

    public int getPoweroffInform() {
        Log.w(TAG, "getPoweroffInform is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getPoweroffInform();
        }
        Log.e(TAG, " getPoweroffInform mForTestValue is 0 execute return");
        return -1;
    }

    public int getProhibitPowerOnState() {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            return this.mSdkManager.getProhibitPowerOnState();
        }
        Log.e(TAG, " getProhibitPowerOnState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public String getSetPermission() {
        Log.w(TAG, "getSetPermission is execute");
        if (1 != this.mForTest) {
            return "not impl";
        }
        Log.e(TAG, " getSetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int getSmartChargeState(int i8) {
        Log.w(TAG, "getSmartChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartChargeState(i8);
        }
        Log.e(TAG, " getSmartChargeState mForTestValue is 0 execute return");
        return -1;
    }

    public int getSmartPowerException() {
        Log.w(TAG, "getSmartPowerException is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerException();
        }
        Log.e(TAG, " getSmartPowerException mForTestValue is 0 execute return");
        return -1;
    }

    public int getSmartPowerState() {
        Log.w(TAG, "getSmartPowerState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerState();
        }
        Log.e(TAG, " getSmartPowerState mForTestValue is 0 execute return");
        return -1;
    }

    public boolean getSmartPowerUsable() {
        Log.w(TAG, "getSmartPowerUsable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerUsable();
        }
        Log.e(TAG, " getSmartPowerUsable mForTestValue is 0 execute return");
        return false;
    }

    public int getSocValue() {
        Log.w(TAG, "getSocValue is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSocValue();
        }
        Log.e(TAG, " getSocValue mForTestValue is 0 execute return");
        return -1;
    }

    public String getTBoxHardwareVersion() {
        Log.w(TAG, "getTBoxHardwareVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxHardwareVersion();
        }
        Log.e(TAG, " getTBoxHardwareVersion mForTestValue is 0 execute return");
        return null;
    }

    public int getTBoxNetworkState(int i8) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.getTboxNetworkState(i8);
    }

    public String getTBoxSerialNumber() {
        Log.w(TAG, "getTBoxSerialNumber is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxSerialNumber();
        }
        Log.e(TAG, " getTBoxSerialNumber mForTestValue is 0 execute return");
        return null;
    }

    public String getTBoxSoftVersion() {
        Log.w(TAG, "getTBoxSoftVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxSoftVersion();
        }
        Log.e(TAG, " getTBoxSoftVersion mForTestValue is 0 execute return");
        return null;
    }

    public boolean getTBoxUpgradeLog(String str) {
        Log.w(TAG, "getTBoxUpgradeLog is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxUpgradeLog(str);
        }
        Log.e(TAG, " getTBoxUpgradeLog mForTestValue is 0 execute return");
        return false;
    }

    public int getTrafficFromTbox(int i8, String str) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getTrafficFromTbox(i8, str);
    }

    public int getTurnOffState() {
        Log.w(TAG, "getTurnOffState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTurnOffState();
        }
        Log.e(TAG, " getTurnOffState mForTestValue is 0 execute return");
        return -1;
    }

    public int getType() {
        Log.w(TAG, "getType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getType();
        }
        Log.e(TAG, " getType mForTestValue is 0 execute return");
        return -1;
    }

    public float getVehicleSpeed() {
        Log.w(TAG, "getVehicleSpeed is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleSpeed();
        }
        Log.e(TAG, " getVehicleSpeed mForTestValue is 0 execute return");
        return -1.0f;
    }

    @Deprecated
    public int getVehicleType() {
        Log.w(TAG, "getVehicleType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleType();
        }
        Log.e(TAG, " getVehicleType mForTestValue is 0 execute return");
        return -1;
    }

    public String getVehicleVersion() {
        Log.w(TAG, "getVehicleVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleVersion();
        }
        Log.e(TAG, " getVehicleVersion mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int notifyTBoxCmdResult(int i8, int i9) {
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTBoxCmdResult mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iNotifyTboxCmdResult = this.mSdkManager.notifyTboxCmdResult(i8, i9);
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

    public int notifyTBoxTrafficResult(int i8) {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
            return this.mSdkManager.notifyTboxTrafficResult(i8);
        }
        Log.e(TAG, " notifyTBoxTrafficResult mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int notifyTargetID(byte[] bArr) {
        Log.w(TAG, "notifyTargetID is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.notifyTargetID(bArr);
        }
        Log.e(TAG, " notifyTargetID mForTestValue is 0 execute return");
        return -1;
    }

    public int notifyTargetID2(byte[] bArr) {
        Log.w(TAG, "notifyTargetID2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.notifyTargetID2(bArr);
        }
        Log.e(TAG, " notifyTargetID2 mForTestValue is 0 execute return");
        return -1;
    }

    public void notifyTboxOtaState(int i8) {
        Log.w(TAG, "notifyTboxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " notifyTboxOtaState mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.notifyTboxOtaState(i8);
        }
    }

    public boolean postEvent(int i8, int i9, byte[] bArr, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public int queryECUVersionAndSoftcode(byte[] bArr) {
        Log.w(TAG, "queryECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.queryECUVersionAndSoftcode(bArr);
        }
        Log.e(TAG, " queryECUVersionAndSoftcode mForTestValue is 0 execute return");
        return -1;
    }

    public int queryMCUVersionAndSoftcode(byte[] bArr) {
        Log.w(TAG, "queryMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.queryMCUVersionAndSoftcode(bArr);
        }
        Log.e(TAG, " queryMCUVersionAndSoftcode mForTestValue is 0 execute return");
        return -1;
    }

    public void registerCallback(IUpdateListener iUpdateListener) {
        Log.w(TAG, "registerCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            this.mSdkManager.registerCallback(iUpdateListener);
        } else {
            Log.e(TAG, " registerCallback mForTestValue is 0 execute return");
            this.mTestOtaUpgradeManagerCallBack.setUpdateListener(iUpdateListener);
        }
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        registerListener(absBYDAutoOtaListener, null);
    }

    public void resetOTA() {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
            this.mSdkManager.resetOta();
            return;
        }
        Log.e(TAG, " resetOTA mForTestValue is " + this.mForTest + " execute return");
    }

    public boolean sendCanNM(int i8, int i9, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "sendCanNM is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " sendCanNM: mForTest is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSendCanNM", absMessageCallback);
        return this.mSdkManager.sendCanNM(i8, i9, this.otaBydAutoCallback);
    }

    public int sendECUIds(int i8, byte[] bArr) {
        Log.w(TAG, "sendECUIds is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendECUIds(i8, bArr);
        }
        Log.e(TAG, " sendECUIds mForTestValue is 0 execute return");
        return -1;
    }

    public int sendECUIds2(int i8, byte[] bArr) {
        Log.w(TAG, "sendECUIds2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendECUIds2(i8, bArr);
        }
        Log.e(TAG, " sendECUIds2 mForTestValue is 0 execute return");
        return -1;
    }

    public int sendMonitorTable(byte[] bArr) {
        Log.w(TAG, "sendMonitorTable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendMonitorTable(bArr);
        }
        Log.e(TAG, " sendMonitorTable mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTACtlCMD(OTAControlCMD oTAControlCMD) {
        Log.w(TAG, "sendOTACtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTACtlCMD((byte[]) null);
        }
        Log.e(TAG, " sendOTACtlCMD mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAData(byte[] bArr) {
        Log.w(TAG, "sendOTAData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAData(bArr);
        }
        Log.e(TAG, " sendOTAData mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAData2(byte[] bArr) {
        Log.w(TAG, "sendOTAData2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAData2(bArr);
        }
        Log.e(TAG, " sendOTAData2 mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAServiceData(byte[] bArr) {
        Log.w(TAG, "sendOTAServiceData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAServiceData(bArr);
        }
        Log.e(TAG, " sendOTAServiceData mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTATimecountCtlCMD(int i8, int i9, int i10, int i11) {
        Log.w(TAG, "sendOTATimecountCtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTATimecountCtlCMD(i8, i9, i10, i11);
        }
        Log.e(TAG, " sendOTATimecountCtlCMD mForTestValue is 0 execute return");
        return -1;
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i8 : iArr) {
                if (i8 <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i8 == 2362) {
                    setEclPower(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_CMD_ECL_Power.POWER_ON_SET:");
                } else if (i8 == 2386) {
                    setBackupAreaDownload(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_DOUBLE_DIFF_DOWNLOAD_FLAG_SET:");
                } else if (i8 == 2380) {
                    setHighVoltageCmd(bYDAutoEventValue.intValue);
                    Log.w(TAG, "OTA_CMD_OTA_HIGH_VOLTAGE_MODULE_SET:");
                } else if (i8 != 2381) {
                    switch (i8) {
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

    public boolean setAQSlot(int i8, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSetAQSlot", absMessageCallback);
        return this.mSdkManager.setAQSlot(i8, this.otaBydAutoCallback);
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

    public void setAndroidOtaState(int i8, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAndroidOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAndroidOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAndroidOtaState", absMessageCallback);
            this.mSdkManager.setAndroidOtaState(i8, this.otaBydAutoCallback);
        }
    }

    public int setApn3State(boolean z7) {
        Log.w(TAG, "setApn3State is execute: " + z7);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.setApn3State(z7);
    }

    public void setAppointmentTime(int i8, int i9, int i10, int i11, int i12, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setAppointmentTime is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setAppointmentTime mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAppointmentTime", absMessageCallback);
            this.mSdkManager.setAppointmentTime(i8, i9, i10, i11, i12, this.otaBydAutoCallback);
        }
    }

    public boolean setBackLightState(int i8) {
        Log.w(TAG, "setBackLightState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setBackLightState(i8);
        }
        Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public boolean setBackupAreaDownload(int i8) {
        Log.w(TAG, "setBackupAreaDownload is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setBackupAreaDownload(i8);
        }
        Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public int setCanInfoSwitchState(int i8) {
        Log.w(TAG, "setCanInfoSwitchState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setCanInfoSwitchState(i8);
        }
        Log.e(TAG, " setCanInfoSwitchState mForTestValue is 0 execute return");
        return -1;
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

    public boolean setEclPower(int i8) {
        Log.w(TAG, "setEclPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setEclPower(i8);
        }
        Log.e(TAG, " setEclPower mForTestValue is 0 execute return");
        return false;
    }

    public boolean setHighVoltageCmd(int i8) {
        Log.w(TAG, "setHighVoltageCmd is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setHighVoltageCmd(i8);
        }
        Log.e(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public int setMcuMessage(int i8) {
        Log.w(TAG, "setMcuMessage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setMcuMessage(i8);
        }
        Log.e(TAG, " setMcuMessage mForTestValue is 0 execute return");
        return -1;
    }

    public void setMobileNetworkState(boolean z7) {
        Log.w(TAG, "setMobileNetworkState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " executeCMD mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setMobileNetworkState(z7);
        }
    }

    public boolean setMultimediaSelfUpdate(int i8) {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
            return this.mSdkManager.setMultimediaSelfUpdate(i8);
        }
        Log.e(TAG, " setMultimediaSelfUpdate mForTestValue is " + this.mForTest + " execute return");
        return false;
    }

    public int setNonWhiteListNetworkEnable(boolean z7) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setNonWhiteListNetworkEnable(z7);
    }

    public boolean setOtaModel(int i8) {
        Log.w(TAG, "setOtaModel is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setOtaModel(i8);
        }
        Log.e(TAG, " setOtaModel mForTestValue is 0 execute return");
        return false;
    }

    public boolean setOtaPower(int i8) {
        Log.w(TAG, "setOtaPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setOtaPower(i8);
        }
        Log.e(TAG, " setOtaPower mForTestValue is 0 execute return");
        return false;
    }

    public int setPowerOnForbidState(int i8) {
        Log.w(TAG, "setPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setPowerOnForbidState(i8);
        }
        Log.e(TAG, " setPowerOnForbidState mForTestValue is 0 execute return");
        return -1;
    }

    public void setQnxOtaState(int i8, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setQnxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setQnxOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onQnxOtaState", absMessageCallback);
            this.mSdkManager.setQnxOtaState(i8, this.otaBydAutoCallback);
        }
    }

    public boolean setQuitChargeAndDischarge(int i8) {
        Log.w(TAG, "setQuitChargeAndDischarge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setQuitChargeAndDischarge(i8);
        }
        Log.e(TAG, " setQuitChargeAndDischarge mForTestValue is 0 execute return");
        return false;
    }

    public boolean setQuitSmartCharge(int i8) {
        Log.w(TAG, "setQuitSmartCharge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setQuitSmartCharge(i8);
        }
        Log.e(TAG, " setQuitSmartCharge mForTestValue is 0 execute return");
        return false;
    }

    public int setQxdmLogState(int i8, int i9) {
        Log.w(TAG, "setQxdmLogState is logState: " + i8 + ", logType: " + i9);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setQxdmLogState(i8, i9);
    }

    public int setScreenState(int i8) {
        Log.w(TAG, "setScreenState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setScreenState(i8);
        }
        Log.e(TAG, " setScreenState mForTestValue is 0 execute return");
        return -1;
    }

    public boolean setSmartPower(int i8) {
        Log.w(TAG, "setSmartPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setSmartPower(i8);
        }
        Log.e(TAG, " setSmartPower mForTestValue is 0 execute return");
        return false;
    }

    public int setTcpDumpState(boolean z7) {
        Log.w(TAG, "setTcpDumpState is execute");
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
            return this.mSdkManager.setTcpDumpState(z7);
        }
        Log.e(TAG, " setTcpDumpState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void setUpdateFlag(int i8, AbsMessageCallback absMessageCallback) {
        Log.w(TAG, "setUpdateFlag is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            Log.e(TAG, " setUpdateFlag mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onUpdateFlag", absMessageCallback);
            this.mSdkManager.setUpdateFlag(i8, this.otaBydAutoCallback);
        }
    }

    public void setUsbUpdateState(int i8) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        this.mSdkManager.setUsbUpdateState(i8);
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

    @Deprecated
    public void startUpdate(int i8, IUpdateListener iUpdateListener) {
        Log.w(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(i8, iUpdateListener);
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

    public void stopCloudServiceListener() {
        Log.w(TAG, "stopCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " stopCloudServiceListener mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.stopCloudServiceListener(this.otaBydAutoCallback);
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

    public int syncApnParam(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.syncApnParam(str, str2, str3, str4, str5, str6, str7);
    }

    public int syncMcuState() {
        Log.w(TAG, "syncMcuState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.syncMcuState();
        }
        Log.e(TAG, " syncMcuState mForTestValue is 0 execute return");
        return -1;
    }

    public void trigerTestOtaManagerImplCallback(int i8, Object obj) {
        this.mTestCallback.enqueueMessage(i8, obj);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int i8, Object obj) {
        this.mTestOtaUpgradeManagerCallBack.enqueueMessage(i8, obj);
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

    public void unregisterListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        Log.w(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(null);
            return;
        }
        synchronized (this.mOtaListenerList) {
            List<AbsBYDAutoOtaListener> list = this.mOtaListenerList;
            if (list != null && list.contains(absBYDAutoOtaListener)) {
                this.mOtaListenerList.remove(absBYDAutoOtaListener);
            }
        }
    }

    public int updateHsm(String str) {
        if (1 != this.mForTest) {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
            return this.mSdkManager.updateHsm(str);
        }
        Log.e(TAG, " updateHsm mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int uploadCDR(int i8, String str) {
        if (1 == this.mForTest) {
            Log.e(TAG, " uploadCDR mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iUploadCdr = this.mSdkManager.uploadCdr(i8, str);
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

    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
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

    public boolean postEvent(int i8, int i9, float f8, Object obj) {
        Log.w(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener, int[] iArr) {
        Log.w(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            Log.e(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(absBYDAutoOtaListener);
            return;
        }
        synchronized (this.mOtaListenerList) {
            List<AbsBYDAutoOtaListener> list = this.mOtaListenerList;
            if (list != null && !list.contains(absBYDAutoOtaListener)) {
                this.mOtaListenerList.add(absBYDAutoOtaListener);
            }
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
}
