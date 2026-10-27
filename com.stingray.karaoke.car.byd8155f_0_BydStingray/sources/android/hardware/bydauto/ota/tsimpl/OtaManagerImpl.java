package android.hardware.bydauto.ota.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.DiagnosticInfo;
import android.hardware.bydauto.ota.Info;
import android.hardware.bydauto.ota.OTAControlCMD;
import android.hardware.bydauto.ota.ReadDTCInfoType;
import android.hardware.bydauto.tbox.AbsBYDTBoxListener;
import android.os.Bundle;
import android.util.SparseArray;
import com.ts.ota.otasdkmgr.DiagnosticAidlInfo;
import com.ts.ota.otasdkmgr.EcuInfo;
import com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener;
import com.ts.ota.otasdkmgr.IAbsMessageCallback;
import com.ts.ota.otasdkmgr.ITboxCallBack;
import com.ts.ota.otasdkmgr.IUpdateListener;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import h6.n;
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
    private final List<AbsBYDAutoOtaListener> mOtaListenerList = new ArrayList();
    private Map<String, AbsMessageCallback> absMsgCallbackMap = new HashMap();
    private int mForTest = 0;
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private boolean mNowBind = false;
    IAbsBYDAutoOtaListener.Stub otaBydAutoListener = new IAbsBYDAutoOtaListener.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.1
        public void on7dfExistState(boolean z6) {
            n.t(OtaManagerImpl.TAG, "on7dfExistState: state = " + z6);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "on7dfExistState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).on7dfExistState(z6);
            }
        }

        public void onBackupAreaDownloadState(int i7) {
            a.r("onBackupAreaDownloadState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onBackupAreaDownloadState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onBackupAreaDownloadState(i7);
            }
        }

        public void onBcmOtaState(int i7) {
            a.r("onBcmOtaState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onBcmOtaState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onBcmOtaState(i7);
            }
        }

        public void onDcState(int i7) {
            a.r("onDcState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onDcState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onDcState(i7);
            }
        }

        public void onIg1RelayState(int i7) {
            a.r("onIg1RelayState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onIg1RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg1RelayState(i7);
            }
        }

        public void onIg3RelayState(int i7) {
            a.r("onIg3RelayState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onIg3RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg3RelayState(i7);
            }
        }

        public void onIg4RelayState(int i7) {
            a.r("onIg4RelayState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onIg4RelayState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onIg4RelayState(i7);
            }
        }

        public void onLFDoorLockStatus(int i7) {
            a.r("onLFDoorLockStatus: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onLFDoorLockStatus: mOtaListenerList is null");
            } else {
                OtaManagerImpl.this.caseFeatureIds(2389, i7);
            }
        }

        public void onNfcOperationCommand(int i7) {
        }

        public void onProhibitPowerOnState(int i7) {
            a.r(" onProhibitPowerOnState.in state : ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onProhibitPowerOnState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onProhibitPowerOnState(i7);
            }
        }

        public void onSmartPowerState(int i7) {
            a.r("onSmartPowerState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onSmartPowerState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onSmartPowerState(i7);
            }
        }

        public void onSocValueChanged(int i7) {
            a.r("onSocValueChanged: value = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onSocValueChanged: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onSocValueChanged(i7);
            }
        }

        public void onStartKeyState(int i7) {
            a.r(" onStartKeyState.in state : ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onStartKeyState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onStartKeyState(i7);
            }
        }

        public void onTurnOffState(int i7) {
            a.r("onTurnOffState: state = ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mOtaListenerList == null && OtaManagerImpl.this.mOtaListenerList.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onTurnOffState: mOtaListenerList is null");
                return;
            }
            Iterator it = OtaManagerImpl.this.mOtaListenerList.iterator();
            while (it.hasNext()) {
                ((AbsBYDAutoOtaListener) it.next()).onTurnOffState(i7);
            }
        }
    };
    ITboxCallBack mITestTboxCallBack = new ITboxCallBack() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.2
        public void onTboxCmd2Ivi(int i7) {
            a.r("onTboxCmd2Ivi callback cmd : ", i7, OtaManagerImpl.TAG);
            if (OtaManagerImpl.this.mTboxListener == null) {
                n.j(OtaManagerImpl.TAG, "onTboxCmd2Ivi: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onTBoxCmd2IVI(i7);
            }
        }

        public void onUpdateTrafficData() {
            n.t(OtaManagerImpl.TAG, "onUpdateTrafficData callback...");
            if (OtaManagerImpl.this.mTboxListener == null) {
                n.j(OtaManagerImpl.TAG, "onUpdateTrafficData: callback is null");
            } else {
                OtaManagerImpl.this.mTboxListener.onUpdateTrafficData();
            }
        }
    };
    IAbsMessageCallback.Stub otaBydAutoCallback = new IAbsMessageCallback.Stub() { // from class: android.hardware.bydauto.ota.tsimpl.OtaManagerImpl.3
        public void onAndroidOtaState(int i7) {
            n.t(OtaManagerImpl.TAG, "onAndroidOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onAndroidOtaState: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAndroidOtaState")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAndroidOtaState");
                absMessageCallback.onAndroidOtaState(i7);
                n.t(OtaManagerImpl.TAG, "onAndroidOtaState: register callback is " + absMessageCallback);
            }
        }

        public void onAppointmentTime(int i7) {
            n.t(OtaManagerImpl.TAG, "onAppointmentTime callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onAppointmentTime: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onAppointmentTime")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onAppointmentTime");
                absMessageCallback.onAppointmentTime(i7);
                n.t(OtaManagerImpl.TAG, "onAppointmentTime: register callback is " + absMessageCallback);
            }
        }

        public void onClearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            List<DiagnosticInfo> listGenerateBydApi;
            n.t(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            try {
                listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
            } catch (Exception e10) {
                n.i(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e10);
                listGenerateBydApi = null;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onClearDiagnosticTroubleCode")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onClearDiagnosticTroubleCode");
                absMessageCallback.onClearDiagnosticTroubleCode(listGenerateBydApi);
                n.t(OtaManagerImpl.TAG, "onClearDiagnosticTroubleCode: register callback is " + absMessageCallback);
            }
        }

        public void onCloudServiceListener(int i7, Bundle bundle) {
            n.t(OtaManagerImpl.TAG, "onCloudServiceListener callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onCloudServiceListener: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onCloudServiceListener")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onCloudServiceListener");
                absMessageCallback.onCloudServiceListener(i7, bundle);
                n.t(OtaManagerImpl.TAG, "onCloudServiceListener: register callback is " + absMessageCallback);
            }
        }

        public void onEcuInfo(List<EcuInfo> list) {
            n.t(OtaManagerImpl.TAG, "onEcuInfo callback->ecuInfoList size: " + list.size());
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onEcuInfo: absMsgCallbackMap is null");
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
                n.t(OtaManagerImpl.TAG, "onEcuInfo: register callback is " + absMessageCallback);
            }
        }

        public void onGetAQSlot(int i7) {
            n.t(OtaManagerImpl.TAG, "onGetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onGetAQSlot: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetAQSlot")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetAQSlot");
                absMessageCallback.onGetAQSlot(i7);
                n.t(OtaManagerImpl.TAG, "onGetAQSlot: register callback is " + absMessageCallback);
            }
        }

        public void onGetDiagnosticTroubleCode(List<DiagnosticAidlInfo> list) {
            List<DiagnosticInfo> listGenerateBydApi;
            n.t(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: absMessageCallbacks is null");
                return;
            }
            try {
                listGenerateBydApi = OtaManagerImpl.this.generateBydApi(list);
            } catch (Exception e10) {
                n.i(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: ", e10);
                listGenerateBydApi = null;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onGetDiagnosticTroubleCode")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onGetDiagnosticTroubleCode");
                absMessageCallback.onGetDiagnosticTroubleCode(listGenerateBydApi);
                n.t(OtaManagerImpl.TAG, "onGetDiagnosticTroubleCode: register callback is " + absMessageCallback);
            }
        }

        public void onQnxOtaState(int i7) {
            n.t(OtaManagerImpl.TAG, "onQnxOtaState callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onQnxOtaState: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onQnxOtaState")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onQnxOtaState");
                absMessageCallback.onQnxOtaState(i7);
                n.t(OtaManagerImpl.TAG, "onQnxOtaState: register callback is " + absMessageCallback);
            }
        }

        public void onSendCanNM(int i7) {
            n.t(OtaManagerImpl.TAG, "onSendCanNM callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onSendCanNM: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSendCanNM")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSendCanNM");
                absMessageCallback.onSendCanNM(i7);
                n.t(OtaManagerImpl.TAG, "onSendCanNM: register callback is " + absMessageCallback);
            }
        }

        public void onSetAQSlot(int i7) {
            n.t(OtaManagerImpl.TAG, "onSetAQSlot callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onSetAQSlot: absMsgCallbackMap is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onSetAQSlot")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onSetAQSlot");
                absMessageCallback.onSetAQSlot(i7);
                n.t(OtaManagerImpl.TAG, "onSetAQSlot: register callback is " + absMessageCallback);
            }
        }

        public void onUpdateFlag(int i7) {
            n.t(OtaManagerImpl.TAG, "onUpdateFlag callback...");
            if (OtaManagerImpl.this.absMsgCallbackMap == null || OtaManagerImpl.this.absMsgCallbackMap.isEmpty()) {
                n.j(OtaManagerImpl.TAG, "onUpdateFlag: absMessageCallbacks is null");
                return;
            }
            if (OtaManagerImpl.this.absMsgCallbackMap.containsKey("onUpdateFlag")) {
                AbsMessageCallback absMessageCallback = (AbsMessageCallback) OtaManagerImpl.this.absMsgCallbackMap.get("onUpdateFlag");
                absMessageCallback.onUpdateFlag(i7);
                n.t(OtaManagerImpl.TAG, "onUpdateFlag: register callback is " + absMessageCallback);
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
            n.j(TAG, "OtaSdkManager.getInstance");
            OtaSdkManager otaSdkManager = OtaSdkManager.getInstance(this.mContext);
            this.mSdkManager = otaSdkManager;
            otaSdkManager.registerListener(this.otaBydAutoListener);
            return;
        }
        n.t(TAG, "OtaManagerImpl mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback = new TestOtaManagerImplCallback();
        this.mTestOtaUpgradeManagerCallBack = new TestOtaUpgradeManagerCallBack();
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
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
        try {
            if (sTsOtaManagerImpl == null) {
                sTsOtaManagerImpl = new OtaManagerImpl(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return sTsOtaManagerImpl;
    }

    private int getReadDtcInfoType(ReadDTCInfoType readDTCInfoType) {
        if (readDTCInfoType != null) {
            return readDTCInfoType.getValue();
        }
        return 0;
    }

    private ReadDTCInfoType setReadDtcInfoType(int i7) {
        if (i7 == 1) {
            return ReadDTCInfoType.DTC_INFO_STATUS_MASK;
        }
        if (i7 == 2) {
            return ReadDTCInfoType.DTC_INFO_SNAPSHOT_DATA;
        }
        if (i7 == 3) {
            return ReadDTCInfoType.DTC_INFO_EXTEND_DATA_RECORDS;
        }
        if (i7 != 4) {
            return null;
        }
        return ReadDTCInfoType.DTC_INFO_ALL;
    }

    private boolean storeAbsMsgCallback(String str, AbsMessageCallback absMessageCallback) {
        Map<String, AbsMessageCallback> map;
        n.t(TAG, "storeAbsMsgCallback: callback->" + absMessageCallback);
        if (absMessageCallback == null || (map = this.absMsgCallbackMap) == null) {
            return false;
        }
        map.put(str, absMessageCallback);
        return true;
    }

    public int FinishOTA() {
        n.t(TAG, "FinishOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.FinishOTA();
        }
        n.j(TAG, " FinishOTA mForTestValue is 0 execute return");
        return -1;
    }

    public void QNXreboot() {
        n.t(TAG, "QNXreboot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " QNXreboot: mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.QNXreboot();
        }
    }

    public int StartOTA() {
        n.t(TAG, "StartOTA is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.StartOTA();
        }
        n.j(TAG, " StartOTA mForTestValue is 0 execute return");
        return -1;
    }

    public void caseFeatureIds(int i7, int i10) {
        if (i7 == 0 || this.mOtaListenerList.isEmpty()) {
            return;
        }
        this.bydAutoEventValueListener.intValue = i10;
        n.t(TAG, ":caseFeatureIds " + this.bydAutoEventValueListener.intValue);
        Iterator<AbsBYDAutoOtaListener> it = this.mOtaListenerList.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, this.bydAutoEventValueListener);
        }
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" clearDiagnosticTroubleCode mForTestValue is "), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        storeAbsMsgCallback("onClearDiagnosticTroubleCode", absMessageCallback);
        this.mSdkManager.clearDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
    }

    public int clearTboxLog() {
        n.t(TAG, "clearTboxLog is execute");
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" clearTboxLog mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.clearTboxLog();
    }

    public boolean decodeAndCheckSign(Bundle bundle) {
        n.t(TAG, "decodeAndCheckSign is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.decodeAndCheckSign(bundle);
        }
        n.j(TAG, " decodeAndCheckSign mForTestValue is 0 execute return");
        return false;
    }

    public boolean executeCMD(Bundle bundle) {
        n.t(TAG, "executeCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.executeCMD(bundle);
        }
        n.j(TAG, " executeCMD mForTestValue is 0 execute return");
        return false;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    if (i7 == 314) {
                        int socValue = getSocValue();
                        bYDAutoEventValue.intValue = socValue;
                        sparseArray.put(314, Integer.valueOf(socValue));
                    } else if (i7 == 2385) {
                        int dischargeMainContactorState = getDischargeMainContactorState();
                        bYDAutoEventValue.intValue = dischargeMainContactorState;
                        sparseArray.put(2385, Integer.valueOf(dischargeMainContactorState));
                    } else if (i7 == 2389) {
                        int lFDoorLockStatus = getLFDoorLockStatus();
                        bYDAutoEventValue.intValue = lFDoorLockStatus;
                        sparseArray.put(2389, Integer.valueOf(lFDoorLockStatus));
                    } else if (i7 == 2395) {
                        int turnOffState = getTurnOffState();
                        bYDAutoEventValue.intValue = turnOffState;
                        sparseArray.put(2395, Integer.valueOf(turnOffState));
                    } else if (i7 == 2411) {
                        int bcmOtaState = getBcmOtaState();
                        bYDAutoEventValue.intValue = bcmOtaState;
                        sparseArray.put(2411, Integer.valueOf(bcmOtaState));
                    } else if (i7 == 2432) {
                        int ig3RelayState = getIg3RelayState();
                        bYDAutoEventValue.intValue = ig3RelayState;
                        sparseArray.put(2432, Integer.valueOf(ig3RelayState));
                    } else if (i7 == 2437) {
                        int dcState = getDcState();
                        bYDAutoEventValue.intValue = dcState;
                        sparseArray.put(2437, Integer.valueOf(dcState));
                    } else if (i7 == 3319) {
                        int batteryTemp = getBatteryTemp(1);
                        bYDAutoEventValue.intValue = batteryTemp;
                        sparseArray.put(3319, Integer.valueOf(batteryTemp));
                    } else if (i7 == 3534) {
                        int batteryTemp2 = getBatteryTemp(1);
                        bYDAutoEventValue.intValue = batteryTemp2;
                        sparseArray.put(3534, Integer.valueOf(batteryTemp2));
                    } else if (i7 == 3606) {
                        int epbState = getEpbState();
                        bYDAutoEventValue.intValue = epbState;
                        sparseArray.put(3606, Integer.valueOf(epbState));
                    } else if (i7 == 2356) {
                        int batteryVoltage = getBatteryVoltage(0);
                        bYDAutoEventValue.intValue = batteryVoltage;
                        sparseArray.put(2356, Integer.valueOf(batteryVoltage));
                    } else if (i7 == 2357) {
                        int batteryVoltage2 = getBatteryVoltage(1);
                        bYDAutoEventValue.intValue = batteryVoltage2;
                        sparseArray.put(2357, Integer.valueOf(batteryVoltage2));
                    } else if (i7 == 2652) {
                        int smartChargeState = getSmartChargeState(0);
                        bYDAutoEventValue.intValue = smartChargeState;
                        sparseArray.put(2652, Integer.valueOf(smartChargeState));
                    } else if (i7 != 2653) {
                        switch (i7) {
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
                                switch (i7) {
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
                    int i10 = 0;
                    for (int i11 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i10] = ((Integer) sparseArray.get(i11)).intValue();
                            i10++;
                        } catch (NullPointerException e10) {
                            e10.printStackTrace();
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
        n.t(TAG, "get7dfExistState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.get7dfExistState();
        }
        n.j(TAG, " get7dfExistState mForTestValue is 0 execute return");
        return false;
    }

    public boolean getAQSlot(AbsMessageCallback absMessageCallback) {
        n.t(TAG, "getAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            n.j(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onGetAQSlot", absMessageCallback);
        return this.mSdkManager.getAQSlot(this.otaBydAutoCallback);
    }

    public int getBatteryManagementDeviceState() {
        n.t(TAG, "getBatteryManagementDeviceState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryManagementDeviceState();
        }
        n.j(TAG, " getBatteryManagementDeviceState mForTestValue is 0 execute return");
        return -1;
    }

    public double getBatteryPowerVoltage() {
        n.t(TAG, "getBatteryPowerVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryPowerVoltage();
        }
        n.j(TAG, " getBatteryPowerVoltage mForTestValue is 0 execute return");
        return -1.0d;
    }

    public int getBatteryTemp(int i7) {
        n.t(TAG, "getBatteryTemp is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryTemp(i7);
        }
        n.j(TAG, " getEnvironmentTemp mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getBatteryType() {
        n.t(TAG, "getBatteryType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryType();
        }
        n.j(TAG, " getBatteryType mForTestValue is 0 execute return");
        return -1;
    }

    public int getBatteryVoltage(int i7) {
        n.t(TAG, "getBatteryVoltage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBatteryVoltage(i7);
        }
        n.j(TAG, " getBatteryVoltage mForTestValue is 0 execute return");
        return -1;
    }

    public int getBcmOtaState() {
        n.t(TAG, "getBcmOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBcmOtaState();
        }
        n.j(TAG, " getBcmOtaState mForTestValue is 0 execute return");
        return -1;
    }

    public int getBurglarState() {
        n.t(TAG, "getBurglarState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getBurglarState();
        }
        n.j(TAG, " getBurglarState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getCanInfo() {
        n.t(TAG, "getCanInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getCanInfo();
        }
        n.j(TAG, " getCanInfo mForTestValue is 0 execute return");
        return null;
    }

    public int getChargeAndDischargeSystemState() {
        n.t(TAG, "getChargeAndDischargeSystemState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getChargeAndDischargeSystemState();
        }
        n.j(TAG, " getChargeAndDischargeSystemState mForTestValue is 0 execute return");
        return -1;
    }

    @Deprecated
    public int getChargeState() {
        n.t(TAG, "getChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getChargeState();
        }
        n.j(TAG, " getChargeState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDcState() {
        n.t(TAG, "getDcState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDcState();
        }
        n.j(TAG, " getDcState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDevicetype() {
        n.t(TAG, "getDevicetype is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDevicetype();
        }
        n.j(TAG, " getDevicetype mForTestValue is 0 execute return");
        return -1;
    }

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" getDiagnosticTroubleCode mForTestValue is "), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        storeAbsMsgCallback("onGetDiagnosticTroubleCode", absMessageCallback);
        this.mSdkManager.getDiagnosticTroubleCode(generateAidl(list), this.otaBydAutoCallback);
    }

    public int getDischargeMainContactorState() {
        n.t(TAG, "getDischargeMainContactorState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDischargeMainContactorState();
        }
        n.j(TAG, " getDischargeMainContactorState mForTestValue is 0 execute return");
        return -1;
    }

    public int getDischargeState(int i7) {
        n.t(TAG, "getDischargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getDischargeState(i7);
        }
        n.j(TAG, " getDischargeState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getECUSoftcode() {
        n.t(TAG, "getECUSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUSoftcode();
        }
        n.j(TAG, " getECUSoftcode mForTestValue is 0 execute return");
        return null;
    }

    public byte[] getECUVersion() {
        n.t(TAG, "getECUVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUVersion();
        }
        n.j(TAG, " getECUVersion mForTestValue is 0 execute return");
        return null;
    }

    public byte[] getECUVersionAndSoftcode() {
        n.t(TAG, "getECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getECUVersionAndSoftcode();
        }
        n.j(TAG, " getECUVersionAndSoftcode mForTestValue is 0 execute return");
        return null;
    }

    public boolean getEcuInfo(List<Info> list, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "getEcuInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 == this.mForTest) {
            n.j(TAG, " getEcuInfo: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onEcuInfo", absMessageCallback);
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            n.t(TAG, "getEcuInfo: Info is empty");
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
        n.t(TAG, "getEpbState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getEpbState();
        }
        n.j(TAG, " getEpbState mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getFaultCodeInfo() {
        n.t(TAG, "getFaultCodeInfo is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getFaultCodeInfo();
        }
        n.j(TAG, " getFaultCodeInfo mForTestValue is 0 execute return");
        return null;
    }

    public int[] getFeatureList() {
        n.t(TAG, "getFeatureList is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getFeatureList();
        }
        n.j(TAG, " getFeatureList mForTestValue is 0 execute return");
        return new int[]{0, 0};
    }

    public String getGetPermission() {
        n.t(TAG, "getGetPermission is execute");
        if (1 != this.mForTest) {
            return "not impl";
        }
        n.j(TAG, " getGetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int getIg1RelayState() {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" getIg1RelayState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getIg1RelayState();
    }

    public int getIg3RelayState() {
        n.t(TAG, "getIg3RelayState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getIg3RelayState();
        }
        n.j(TAG, " getIg3RelayState mForTestValue is 0 execute return");
        return -1;
    }

    public int getIg4RelayState() {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" getIg4RelayState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getIg4RelayState();
    }

    @Deprecated
    public int getIgnitionState() {
        n.t(TAG, "getIgnitionState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getIgnitionState();
        }
        n.j(TAG, " getIgnitionState mForTestValue is 0 execute return");
        return -1;
    }

    public int getLFDoorLockState() {
        n.t(TAG, "getLFDoorLockState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLFDoorLockState();
        }
        n.j(TAG, " getLFDoorLockState mForTestValue is 0 execute return");
        return -1;
    }

    public int getLFDoorLockStatus() {
        n.t(TAG, "getLFDoorLockStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLFDoorLockStatus();
        }
        n.j(TAG, " getLFDoorLockStatus mForTestValue is 0 execute return");
        return 0;
    }

    public int getLocalDiagStatus() {
        n.t(TAG, "getLocalDiagStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getLocalDiagStatus();
        }
        n.j(TAG, " getLocalDiagStatus mForTestValue is 0 execute return");
        return -1;
    }

    public byte[] getMCUVersionAndSoftcode() {
        n.t(TAG, "getMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getMCUVersionAndSoftcode();
        }
        n.j(TAG, " getMCUVersionAndSoftcode mForTestValue is 0 execute return");
        return null;
    }

    public boolean getMobileNetworkState() {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" getMobileNetworkState mForTestValue is "), this.mForTest, " execute return", TAG);
            return false;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getMobileNetworkState();
    }

    public byte[] getOTAInfoACK() {
        n.t(TAG, "getOTAInfoACK is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTAInfoACK();
        }
        n.j(TAG, " getOTAInfoACK mForTestValue is 0 execute return");
        return null;
    }

    public int getOTAState() {
        n.t(TAG, "getOTAState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTAState();
        }
        n.j(TAG, " getOTAState mForTestValue is 0 execute return");
        return -1;
    }

    public int getOTATimecountState() {
        n.t(TAG, "getOTATimecountState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getOTATimecountState();
        }
        n.j(TAG, " getOTATimecountState mForTestValue is 0 execute return");
        return -1;
    }

    public int getPowerOnForbidState() {
        n.t(TAG, "getPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getPowerOnForbidState();
        }
        n.j(TAG, " getPowerOnForbidState mForTestValue is 0 execute return");
        return -1;
    }

    public int getPoweroffInform() {
        n.t(TAG, "getPoweroffInform is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getPoweroffInform();
        }
        n.j(TAG, " getPoweroffInform mForTestValue is 0 execute return");
        return -1;
    }

    public int getProhibitPowerOnState() {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" getProhibitPowerOnState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.getProhibitPowerOnState();
    }

    public String getSetPermission() {
        n.t(TAG, "getSetPermission is execute");
        if (1 != this.mForTest) {
            return "not impl";
        }
        n.j(TAG, " getSetPermission mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int getSmartChargeState(int i7) {
        n.t(TAG, "getSmartChargeState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartChargeState(i7);
        }
        n.j(TAG, " getSmartChargeState mForTestValue is 0 execute return");
        return -1;
    }

    public int getSmartPowerException() {
        n.t(TAG, "getSmartPowerException is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerException();
        }
        n.j(TAG, " getSmartPowerException mForTestValue is 0 execute return");
        return -1;
    }

    public int getSmartPowerState() {
        n.t(TAG, "getSmartPowerState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerState();
        }
        n.j(TAG, " getSmartPowerState mForTestValue is 0 execute return");
        return -1;
    }

    public boolean getSmartPowerUsable() {
        n.t(TAG, "getSmartPowerUsable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSmartPowerUsable();
        }
        n.j(TAG, " getSmartPowerUsable mForTestValue is 0 execute return");
        return false;
    }

    public int getSocValue() {
        n.t(TAG, "getSocValue is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getSocValue();
        }
        n.j(TAG, " getSocValue mForTestValue is 0 execute return");
        return -1;
    }

    public String getTBoxHardwareVersion() {
        n.t(TAG, "getTBoxHardwareVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxHardwareVersion();
        }
        n.j(TAG, " getTBoxHardwareVersion mForTestValue is 0 execute return");
        return null;
    }

    public String getTBoxSerialNumber() {
        n.t(TAG, "getTBoxSerialNumber is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxSerialNumber();
        }
        n.j(TAG, " getTBoxSerialNumber mForTestValue is 0 execute return");
        return null;
    }

    public String getTBoxSoftVersion() {
        n.t(TAG, "getTBoxSoftVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxSoftVersion();
        }
        n.j(TAG, " getTBoxSoftVersion mForTestValue is 0 execute return");
        return null;
    }

    public boolean getTBoxUpgradeLog(String str) {
        n.t(TAG, "getTBoxUpgradeLog is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTBoxUpgradeLog(str);
        }
        n.j(TAG, " getTBoxUpgradeLog mForTestValue is 0 execute return");
        return false;
    }

    public int getTurnOffState() {
        n.t(TAG, "getTurnOffState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getTurnOffState();
        }
        n.j(TAG, " getTurnOffState mForTestValue is 0 execute return");
        return -1;
    }

    public int getType() {
        n.t(TAG, "getType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getType();
        }
        n.j(TAG, " getType mForTestValue is 0 execute return");
        return -1;
    }

    public float getVehicleSpeed() {
        n.t(TAG, "getVehicleSpeed is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleSpeed();
        }
        n.j(TAG, " getVehicleSpeed mForTestValue is 0 execute return");
        return -1.0f;
    }

    @Deprecated
    public int getVehicleType() {
        n.t(TAG, "getVehicleType is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleType();
        }
        n.j(TAG, " getVehicleType mForTestValue is 0 execute return");
        return -1;
    }

    public String getVehicleVersion() {
        n.t(TAG, "getVehicleVersion is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getVehicleVersion();
        }
        n.j(TAG, " getVehicleVersion mForTestValue is 0 execute return");
        return "ForTest";
    }

    public int notifyTBoxCmdResult(int i7, int i10) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" notifyTBoxCmdResult mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iNotifyTboxCmdResult = this.mSdkManager.notifyTboxCmdResult(i7, i10);
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

    public int notifyTBoxTrafficResult(int i7) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" notifyTBoxTrafficResult mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_GET);
        return this.mSdkManager.notifyTboxTrafficResult(i7);
    }

    public int notifyTargetID(byte[] bArr) {
        n.t(TAG, "notifyTargetID is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.notifyTargetID(bArr);
        }
        n.j(TAG, " notifyTargetID mForTestValue is 0 execute return");
        return -1;
    }

    public int notifyTargetID2(byte[] bArr) {
        n.t(TAG, "notifyTargetID2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.notifyTargetID2(bArr);
        }
        n.j(TAG, " notifyTargetID2 mForTestValue is 0 execute return");
        return -1;
    }

    public void notifyTboxOtaState(int i7) {
        n.t(TAG, "notifyTboxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " notifyTboxOtaState mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.notifyTboxOtaState(i7);
        }
    }

    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        n.t(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public int queryECUVersionAndSoftcode(byte[] bArr) {
        n.t(TAG, "queryECUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.queryECUVersionAndSoftcode(bArr);
        }
        n.j(TAG, " queryECUVersionAndSoftcode mForTestValue is 0 execute return");
        return -1;
    }

    public int queryMCUVersionAndSoftcode(byte[] bArr) {
        n.t(TAG, "queryMCUVersionAndSoftcode is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.queryMCUVersionAndSoftcode(bArr);
        }
        n.j(TAG, " queryMCUVersionAndSoftcode mForTestValue is 0 execute return");
        return -1;
    }

    public void registerCallback(IUpdateListener iUpdateListener) {
        n.t(TAG, "registerCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            this.mSdkManager.registerCallback(iUpdateListener);
        } else {
            n.j(TAG, " registerCallback mForTestValue is 0 execute return");
            this.mTestOtaUpgradeManagerCallBack.setUpdateListener(iUpdateListener);
        }
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        n.t(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        registerListener(absBYDAutoOtaListener, null);
    }

    public void resetOTA() {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" resetOTA mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
            this.mSdkManager.resetOta();
        }
    }

    public boolean sendCanNM(int i7, int i10, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "sendCanNM is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " sendCanNM: mForTest is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSendCanNM", absMessageCallback);
        return this.mSdkManager.sendCanNM(i7, i10, this.otaBydAutoCallback);
    }

    public int sendECUIds(int i7, byte[] bArr) {
        n.t(TAG, "sendECUIds is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendECUIds(i7, bArr);
        }
        n.j(TAG, " sendECUIds mForTestValue is 0 execute return");
        return -1;
    }

    public int sendECUIds2(int i7, byte[] bArr) {
        n.t(TAG, "sendECUIds2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendECUIds2(i7, bArr);
        }
        n.j(TAG, " sendECUIds2 mForTestValue is 0 execute return");
        return -1;
    }

    public int sendMonitorTable(byte[] bArr) {
        n.t(TAG, "sendMonitorTable is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendMonitorTable(bArr);
        }
        n.j(TAG, " sendMonitorTable mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTACtlCMD(OTAControlCMD oTAControlCMD) {
        n.t(TAG, "sendOTACtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTACtlCMD((byte[]) null);
        }
        n.j(TAG, " sendOTACtlCMD mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAData(byte[] bArr) {
        n.t(TAG, "sendOTAData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAData(bArr);
        }
        n.j(TAG, " sendOTAData mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAData2(byte[] bArr) {
        n.t(TAG, "sendOTAData2 is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAData2(bArr);
        }
        n.j(TAG, " sendOTAData2 mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTAServiceData(byte[] bArr) {
        n.t(TAG, "sendOTAServiceData is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTAServiceData(bArr);
        }
        n.j(TAG, " sendOTAServiceData mForTestValue is 0 execute return");
        return -1;
    }

    public int sendOTATimecountCtlCMD(int i7, int i10, int i11, int i12) {
        n.t(TAG, "sendOTATimecountCtlCMD is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.sendOTATimecountCtlCMD(i7, i10, i11, i12);
        }
        n.j(TAG, " sendOTATimecountCtlCMD mForTestValue is 0 execute return");
        return -1;
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 <= 0) {
                    bYDAutoEventValue.intValue = -2147482648;
                } else if (i7 == 2362) {
                    setEclPower(bYDAutoEventValue.intValue);
                    n.t(TAG, "OTA_CMD_ECL_Power.POWER_ON_SET:");
                } else if (i7 == 2386) {
                    setBackupAreaDownload(bYDAutoEventValue.intValue);
                    n.t(TAG, "OTA_DOUBLE_DIFF_DOWNLOAD_FLAG_SET:");
                } else if (i7 == 2380) {
                    setHighVoltageCmd(bYDAutoEventValue.intValue);
                    n.t(TAG, "OTA_CMD_OTA_HIGH_VOLTAGE_MODULE_SET:");
                } else if (i7 != 2381) {
                    switch (i7) {
                        case 2374:
                            setOtaModel(bYDAutoEventValue.intValue);
                            n.t(TAG, "OTA_CMD_ENTER_OTA_MODE_SET:");
                            break;
                        case 2375:
                            setQuitChargeAndDischarge(bYDAutoEventValue.intValue);
                            n.t(TAG, "OTA_CMD_EXIT_CHARGING_DIS_SET:");
                            break;
                        case 2376:
                            setQuitSmartCharge(bYDAutoEventValue.intValue);
                            n.t(TAG, "OTA_CMD_EXIT_SMART_CHARGING_SET:");
                            break;
                    }
                } else {
                    setOtaPower(bYDAutoEventValue.intValue);
                    n.t(TAG, "OTA_CMD_OTA_Power.POWER_CMD_SET:");
                }
            }
        }
        return 1;
    }

    public boolean setAQSlot(int i7, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setAQSlot is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " getAQSlot: mForTestValue is 0 execute return");
            return false;
        }
        storeAbsMsgCallback("onSetAQSlot", absMessageCallback);
        return this.mSdkManager.setAQSlot(i7, this.otaBydAutoCallback);
    }

    public void setAllStatus() {
        n.t(TAG, "setAllStatus is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setAllStatus mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setAllStatus();
        }
    }

    public void setAndroidOtaState(int i7, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setAndroidOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setAndroidOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAndroidOtaState", absMessageCallback);
            this.mSdkManager.setAndroidOtaState(i7, this.otaBydAutoCallback);
        }
    }

    public int setApn3State(boolean z6) {
        n.t(TAG, "setApn3State is execute: " + z6);
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.setApn3State(z6);
    }

    public void setAppointmentTime(int i7, int i10, int i11, int i12, int i13, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setAppointmentTime is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setAppointmentTime mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onAppointmentTime", absMessageCallback);
            this.mSdkManager.setAppointmentTime(i7, i10, i11, i12, i13, this.otaBydAutoCallback);
        }
    }

    public boolean setBackLightState(int i7) {
        n.t(TAG, "setBackLightState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setBackLightState(i7);
        }
        n.j(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public boolean setBackupAreaDownload(int i7) {
        n.t(TAG, "setBackupAreaDownload is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setBackupAreaDownload(i7);
        }
        n.j(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public int setCanInfoSwitchState(int i7) {
        n.t(TAG, "setCanInfoSwitchState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setCanInfoSwitchState(i7);
        }
        n.j(TAG, " setCanInfoSwitchState mForTestValue is 0 execute return");
        return -1;
    }

    public void setCloudServiceListener(AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setCloudServiceListener mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onCloudServiceListener", absMessageCallback);
            this.mSdkManager.setCloudServiceListener(this.otaBydAutoCallback);
        }
    }

    public boolean setEclPower(int i7) {
        n.t(TAG, "setEclPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setEclPower(i7);
        }
        n.j(TAG, " setEclPower mForTestValue is 0 execute return");
        return false;
    }

    public boolean setHighVoltageCmd(int i7) {
        n.t(TAG, "setHighVoltageCmd is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setHighVoltageCmd(i7);
        }
        n.j(TAG, " setHighVoltageCmd mForTestValue is 0 execute return");
        return false;
    }

    public int setMcuMessage(int i7) {
        n.t(TAG, "setMcuMessage is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setMcuMessage(i7);
        }
        n.j(TAG, " setMcuMessage mForTestValue is 0 execute return");
        return -1;
    }

    public void setMobileNetworkState(boolean z6) {
        n.t(TAG, "setMobileNetworkState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " executeCMD mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.setMobileNetworkState(z6);
        }
    }

    public boolean setMultimediaSelfUpdate(int i7) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" setMultimediaSelfUpdate mForTestValue is "), this.mForTest, " execute return", TAG);
            return false;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setMultimediaSelfUpdate(i7);
    }

    public boolean setOtaModel(int i7) {
        n.t(TAG, "setOtaModel is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setOtaModel(i7);
        }
        n.j(TAG, " setOtaModel mForTestValue is 0 execute return");
        return false;
    }

    public boolean setOtaPower(int i7) {
        n.t(TAG, "setOtaPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setOtaPower(i7);
        }
        n.j(TAG, " setOtaPower mForTestValue is 0 execute return");
        return false;
    }

    public int setPowerOnForbidState(int i7) {
        n.t(TAG, "setPowerOnForbidState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setPowerOnForbidState(i7);
        }
        n.j(TAG, " setPowerOnForbidState mForTestValue is 0 execute return");
        return -1;
    }

    public void setQnxOtaState(int i7, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setQnxOtaState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setQnxOtaState mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onQnxOtaState", absMessageCallback);
            this.mSdkManager.setQnxOtaState(i7, this.otaBydAutoCallback);
        }
    }

    public boolean setQuitChargeAndDischarge(int i7) {
        n.t(TAG, "setQuitChargeAndDischarge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setQuitChargeAndDischarge(i7);
        }
        n.j(TAG, " setQuitChargeAndDischarge mForTestValue is 0 execute return");
        return false;
    }

    public boolean setQuitSmartCharge(int i7) {
        n.t(TAG, "setQuitSmartCharge is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setQuitSmartCharge(i7);
        }
        n.j(TAG, " setQuitSmartCharge mForTestValue is 0 execute return");
        return false;
    }

    public int setScreenState(int i7) {
        n.t(TAG, "setScreenState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setScreenState(i7);
        }
        n.j(TAG, " setScreenState mForTestValue is 0 execute return");
        return -1;
    }

    public boolean setSmartPower(int i7) {
        n.t(TAG, "setSmartPower is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 != this.mForTest) {
            return this.mSdkManager.setSmartPower(i7);
        }
        n.j(TAG, " setSmartPower mForTestValue is 0 execute return");
        return false;
    }

    public int setTcpDumpState(boolean z6) {
        n.t(TAG, "setTcpDumpState is execute");
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" setTcpDumpState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        return this.mSdkManager.setTcpDumpState(z6);
    }

    public void setUpdateFlag(int i7, AbsMessageCallback absMessageCallback) {
        n.t(TAG, "setUpdateFlag is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        if (1 == this.mForTest) {
            n.j(TAG, " setUpdateFlag mForTestValue is 0 execute return");
        } else {
            storeAbsMsgCallback("onUpdateFlag", absMessageCallback);
            this.mSdkManager.setUpdateFlag(i7, this.otaBydAutoCallback);
        }
    }

    public void startDownloadBackup(String str) {
        n.t(TAG, "startDownloadBackup is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " startDownloadBackup mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startDownloadBackup(str);
        }
    }

    @Deprecated
    public void startUpdate(int i7, IUpdateListener iUpdateListener) {
        n.t(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(i7, iUpdateListener);
        }
    }

    public void startUpdateTbox(String str) {
        n.t(TAG, "startUpdateTbox is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " startUpdateTbox mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdateTbox(str);
        }
    }

    public void stopCloudServiceListener() {
        n.t(TAG, "stopCloudServiceListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " stopCloudServiceListener mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.stopCloudServiceListener(this.otaBydAutoCallback);
        }
    }

    @Deprecated
    public void stopUpdate() {
        n.t(TAG, "stopUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " stopUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.stopUpdate();
        }
    }

    public int syncMcuState() {
        n.t(TAG, "syncMcuState is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 != this.mForTest) {
            return this.mSdkManager.syncMcuState();
        }
        n.j(TAG, " syncMcuState mForTestValue is 0 execute return");
        return -1;
    }

    public void trigerTestOtaManagerImplCallback(int i7, Object obj) {
        this.mTestCallback.enqueueMessage(i7, obj);
    }

    public void trigerTestOtaUpgradeManagerCallBack(int i7, Object obj) {
        this.mTestOtaUpgradeManagerCallBack.enqueueMessage(i7, obj);
    }

    public void unregisterCallback(IUpdateListener iUpdateListener) {
        n.t(TAG, "unregisterCallback is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " unregisterCallback mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.unregisterCallback(iUpdateListener);
        }
    }

    public void unregisterListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        n.t(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " unregisterListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(null);
            return;
        }
        synchronized (this.mOtaListenerList) {
            try {
                List<AbsBYDAutoOtaListener> list = this.mOtaListenerList;
                if (list != null && list.contains(absBYDAutoOtaListener)) {
                    this.mOtaListenerList.remove(absBYDAutoOtaListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int updateHsm(String str) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" updateHsm mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        return this.mSdkManager.updateHsm(str);
    }

    public int uploadCDR(int i7, String str) {
        if (1 == this.mForTest) {
            a.v(new StringBuilder(" uploadCDR mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_SET);
        int iUploadCdr = this.mSdkManager.uploadCdr(i7, str);
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
        n.t(TAG, "register AbsBYDTBoxListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " registerListener mForTestValue is 0 execute return");
        } else {
            this.mTboxListener = absBYDTBoxListener;
            this.mSdkManager.registerTboxListener(this.mITestTboxCallBack);
        }
    }

    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        n.t(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public void startUpdate(String str) {
        n.t(TAG, "startUpdate is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " startUpdate mForTestValue is 0 execute return");
        } else {
            this.mSdkManager.startUpdate(str);
        }
    }

    public boolean postEvent(int i7, int i10, float f10, Object obj) {
        n.t(TAG, "postEvent is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " postEvent mForTestValue is 0 execute return");
        }
        return false;
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener, int[] iArr) {
        n.t(TAG, "registerListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " registerListener mForTestValue is 0 execute return");
            this.mTestCallback.setAbsBYDAutoOtaListener(absBYDAutoOtaListener);
            return;
        }
        synchronized (this.mOtaListenerList) {
            try {
                List<AbsBYDAutoOtaListener> list = this.mOtaListenerList;
                if (list != null && !list.contains(absBYDAutoOtaListener)) {
                    this.mOtaListenerList.add(absBYDAutoOtaListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
        n.t(TAG, "unregister AbsBYDTBoxListener is execute");
        enforcePermission(BYDAutoOtaDevice.PERMISSION_BYDAUTO_OTA_COMMON);
        if (1 == this.mForTest) {
            n.j(TAG, " unregisterListener mForTestValue is 0 execute return");
        } else {
            this.mTboxListener = null;
            this.mSdkManager.unregisterTboxListener(this.mITestTboxCallBack);
        }
    }
}
