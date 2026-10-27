package android.hardware.bydauto.test.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.Bundle;
import android.util.Log;
import com.ts.diag.AidlShort;
import com.ts.diag.DidConfigurationWordInfo;
import com.ts.diag.DtcData;
import com.ts.diag.ReadResp;
import com.ts.diag.WriteReq;
import com.ts.diag.WriteResp;
import com.ts.diag.manager.ConnectCallBack;
import com.ts.diag.manager.DiagManager;
import com.ts.diag.manager.DiagReportCallBack;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.lib.engineeringmode.callback.IEngineeringCallBack;
import com.ts.ota.otasdkmgr.ITestTboxCallBack;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestManagerImpl {
    private static final int DEVICE_AM = 6;
    private static final int DEVICE_AUX_IN = 0;
    private static final int DEVICE_DAB = 3;
    private static final int DEVICE_DOUBLE_FM = 8;
    private static final int DEVICE_DRM = 5;
    private static final int DEVICE_HD = 4;
    private static final int DEVICE_RDS = 2;
    private static final int DEVICE_SINGLE_FM = 7;
    private static final int DEVICE_SXM = 1;
    private static final short DID2100 = 8448;
    private static final int NUM0 = 0;
    private static final int NUM1 = 1;
    private static final int NUM2 = 2;
    private static final int NUM3 = 3;
    private static final int NUM4 = 4;
    private static final int NUM5 = 5;
    private static final int NUM6 = 6;
    private static final int NUM7 = 7;
    private static final int NUM_FAIL = -1;
    private static final int POWER_SUPPLY_AVM_APA_MDC = 2;
    private static final int POWER_SUPPLY_DAB = 0;
    private static final int POWER_SUPPLY_DMS = 3;
    private static final int POWER_SUPPLY_DVR = 7;
    private static final int POWER_SUPPLY_FACE_ID = 8;
    private static final int POWER_SUPPLY_FM_AM = 1;
    private static final int POWER_SUPPLY_IMS = 4;
    private static final int POWER_SUPPLY_OMS = 5;
    private static final int POWER_SUPPLY_STREAMING_VIDEO = 6;
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_TS.TestManagerImpl";
    protected static final String TAG = "TS.TestManagerImpl";
    private static final int TEST_VALUE = 100;
    private static TestManagerImpl sTestManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private DiagCallBack mDiagCallBack;
    private DiagManager mDiagManager;
    EngineeringCallBack mEngineeringCallBack;
    private OtaSdkManager mOtaSdkManager;
    private OtaTestCallback mOtaTestCallback;
    private EngineeringSdkManager mSdkManager;
    private int mForTest = 100;
    List<AbsBYDAutoTestListener> mTestListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private boolean mNowBind = false;
    private boolean mDiagBind = false;
    private boolean mEngineerBind = false;

    class DiagCallBack implements DiagReportCallBack {
        DiagCallBack() {
        }

        public void readDTCDataReport(DtcData dtcData, String str) {
        }

        public void readInfoFromHalReport(ReadResp readResp, String str) {
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onReadConfigurationWordInfo(readResp.getDid(), readResp.getData());
                }
            }
        }

        public void writeInfoToHalReport(WriteResp writeResp, String str) {
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onWriteConfigurationWordInfo((short) writeResp.getDid(), writeResp.getRes());
                }
            }
        }
    }

    class EngineeringCallBack implements IEngineeringCallBack {
        EngineeringCallBack() {
        }

        public void isConnected(boolean z6) {
            TestManagerImpl.this.mEngineerBind = z6;
            TestManagerImpl.this.dealConnected();
        }

        public void onHwTemperature(Bundle bundle) {
        }

        public void onObdReport(String str, String str2) {
            Log.w(TestManagerImpl.TAG, "onObdReport id :" + str + " data : " + str2);
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onObdReport(str, str2);
                }
            }
        }

        public void onQnxLogStatusChanged(int i7, boolean z6) {
            for (int i8 = 0; i8 < TestManagerImpl.this.mTestListeners.size(); i8++) {
                if (TestManagerImpl.this.mTestListeners.get(i8) != null) {
                    TestManagerImpl.this.mTestListeners.get(i8).onQNXLogStatusChanged(i7, z6);
                }
            }
        }

        public void onSynchronousQNXData(int i7) {
        }
    }

    class OtaTestCallback implements ITestTboxCallBack {
        OtaTestCallback() {
        }

        public void onCloudServiceConnectStateChanged(int i7) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged state : " + i7);
            for (int i8 = 0; i8 < TestManagerImpl.this.mTestListeners.size(); i8++) {
                if (TestManagerImpl.this.mTestListeners.get(i8) != null) {
                    TestManagerImpl.this.mTestListeners.get(i8).onCloudServiceConnectStateChanged(i7);
                }
            }
        }

        public void onNetworkOperatorList(ArrayList<String> arrayList) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorList: code : " + arrayList);
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onNetworkOperatorList(arrayList);
                }
            }
        }

        public void onTboxRestartChanged(int i7) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged: state : " + i7);
            for (int i8 = 0; i8 < TestManagerImpl.this.mTestListeners.size(); i8++) {
                if (TestManagerImpl.this.mTestListeners.get(i8) != null) {
                    TestManagerImpl.this.mTestListeners.get(i8).onTboxRestartChanged(i7);
                }
            }
        }
    }

    public TestManagerImpl(Context context) {
        this.mContext = null;
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        this.mSdkManager = EngineeringSdkManager.getInstance(context);
        EngineeringCallBack engineeringCallBack = new EngineeringCallBack();
        this.mEngineeringCallBack = engineeringCallBack;
        this.mSdkManager.registerListener(engineeringCallBack);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
        OtaTestCallback otaTestCallback = new OtaTestCallback();
        this.mOtaTestCallback = otaTestCallback;
        this.mOtaSdkManager.registerTestListener(otaTestCallback);
        this.mDiagManager = DiagManager.getInstance(context);
        DiagCallBack diagCallBack = new DiagCallBack();
        this.mDiagCallBack = diagCallBack;
        this.mDiagManager.registerDiagCallBack(diagCallBack);
        this.mDiagManager.registerConnectCallBack(new ConnectCallBack() { // from class: android.hardware.bydauto.test.tsimpl.TestManagerImpl.1
            public void onConnectState(boolean z6) {
                TestManagerImpl.this.mDiagBind = z6;
                TestManagerImpl.this.dealConnected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dealConnected() {
        if (this.mDiagBind && this.mEngineerBind) {
            this.mNowBind = true;
            for (int i7 = 0; i7 < this.mTestListeners.size(); i7++) {
                AbsBYDAutoTestListener absBYDAutoTestListener = this.mTestListeners.get(i7);
                if (absBYDAutoTestListener != null) {
                    absBYDAutoTestListener.isConnected(this.mNowBind);
                }
            }
        } else {
            this.mNowBind = false;
        }
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static synchronized TestManagerImpl getInstance(Context context) {
        if (sTestManagerImpl == null) {
            sTestManagerImpl = new TestManagerImpl(context);
        }
        return sTestManagerImpl;
    }

    public int enableADB(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableADB(z6);
        }
        Log.e(TAG, " enableADB mForTestValue is 0 execute return");
        return -1;
    }

    public int enableAlarmLog(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableAlarmLog(z6);
        }
        Log.e(TAG, " enableAlarmLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableCloudServiceLog(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableCloudServiceLog(z6);
        }
        Log.e(TAG, " enableCloudServiceLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableConsoleSuspand(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableConsoleSuspand(z6);
        }
        Log.e(TAG, " enableConsoleSuspand mForTestValue is 0 execute return");
        return -1;
    }

    public int enableDexOpt(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableDexOpt(z6);
        }
        Log.e(TAG, " enableDexOpt mForTestValue is 0 execute return");
        return -1;
    }

    public int enableRamdumps(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableRamdumps(z6);
        }
        Log.e(TAG, " enableRamdumps mForTestValue is 0 execute return");
        return -1;
    }

    public int enableTCPDump(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableTCPDump(z6);
        }
        Log.e(TAG, " enableTCPDump mForTestValue is 0 execute return");
        return -1;
    }

    public int enableWirelessADB(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableWirelessADB(z6);
        }
        Log.e(TAG, " enableWirelessADB mForTestValue is 0 execute return");
        return -1;
    }

    public String geCloudServiceVersion() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServiceVersion();
        }
        Log.e(TAG, " geCloudServiceVersion mForTestValue is " + this.mForTest + " execute return");
        return null;
    }

    public int getAutoAudioSource() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 6, 0, 7));
        }
        Log.e(TAG, " getAutoAudioSource mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getAutoHasDevice(int i7) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoHasDevice mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        Log.w(TAG, "getAutoHasDevice: device : " + i7);
        int i8 = 2;
        int i9 = 1;
        int i10 = 7;
        switch (i7) {
            case 0:
                i9 = 0;
                i8 = 7;
                break;
            case 1:
                i8 = 7;
                break;
            case 2:
                i8 = 7;
                i9 = 4;
                break;
            case 3:
                i8 = 6;
                i10 = i8;
                i9 = 4;
                break;
            case 4:
                i8 = 5;
                i10 = i8;
                i9 = 4;
                break;
            case 5:
                i8 = 4;
                i9 = 4;
                i10 = 4;
                break;
            case 6:
                i10 = i8;
                i9 = 4;
                break;
            case 7:
                i8 = 1;
                i10 = i8;
                i9 = 4;
                break;
            case 8:
                i8 = 0;
                i10 = i8;
                i9 = 4;
                break;
        }
        return -1;
    }

    public int getAutoMicCount() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 6, 7));
        }
        Log.e(TAG, " getAutoMicCount mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAutoPowerType() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 4, 5));
        }
        Log.e(TAG, " getAutoPowerType mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAutoSpeakerCount() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 0, 3));
        }
        Log.e(TAG, " getAutoSpeakerCount mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAutoTBoxNetwork() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 4, 4));
        }
        Log.e(TAG, " getAutoTBoxNetwork mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getCloudServiceConnectState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServiceConnectState();
        }
        Log.e(TAG, " getCloudServiceConnectState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getCloudServieEv() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServieEv();
        }
        Log.e(TAG, " getCloudServieEv mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getConfigArea() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 3, 0, 7));
        }
        Log.e(TAG, " getConfigArea mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void getConsoleSuspandState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            this.mSdkManager.getConsoleSuspandState();
            return;
        }
        Log.e(TAG, " getConsoleSuspandState mForTestValue is " + this.mForTest + " execute return");
    }

    public String getCurNetworkOperator() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mOtaSdkManager.getCurNetworkOperator();
        }
        Log.e(TAG, " getCurNetworkOperator mForTestValue is " + this.mForTest + " execute return");
        return null;
    }

    public int getHasCarplayWired() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 1, 1));
        }
        Log.e(TAG, " getHasCarplayWired mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getHasSingleBt() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 3, 3));
        }
        Log.e(TAG, " getHasSingleBt mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getNetworkOperatorList() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mOtaSdkManager.getNetworkOperatorList();
        }
        Log.e(TAG, " getNetworkOperatorList mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getPowerSupplyMode(int i7) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getPowerSupplyMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        Log.w(TAG, "getPowerSupplyMode: device : " + i7);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        int i8 = 2;
        int i9 = 1;
        int i10 = 5;
        switch (i7) {
            case 0:
                i8 = 6;
                i10 = i8;
                i9 = 0;
                break;
            case 1:
                i8 = 5;
                i9 = 0;
                break;
            case 2:
                i8 = 4;
                i10 = i8;
                i9 = 0;
                break;
            case 3:
                i8 = 3;
                i10 = i8;
                i9 = 0;
                break;
            case 4:
                i10 = i8;
                i9 = 0;
                break;
            case 5:
                i8 = 1;
                i10 = i8;
                i9 = 0;
                break;
            case 6:
                i8 = 0;
                i9 = 0;
                i10 = 0;
                break;
            case 7:
                i8 = 6;
                i10 = 6;
                break;
            case 8:
                i8 = 5;
                break;
        }
        return -1;
    }

    public int getSWCType() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 0, 0));
        }
        Log.e(TAG, " getSWCType mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSteeringWheelPosition() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 4, 3, 3));
        }
        Log.e(TAG, " getSteeringWheelPosition mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTBoxState() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mOtaSdkManager.getTboxState();
        }
        Log.e(TAG, " getTBoxState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void getTCPDumpState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            this.mSdkManager.getTcpDumpState();
            return;
        }
        Log.e(TAG, " getTCPDumpState mForTestValue is " + this.mForTest + " execute return");
    }

    public int getUSVersion() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 2, 2));
        }
        Log.e(TAG, " getUSVersion mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getVehicleInfo() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 2, 0, 7));
        }
        Log.e(TAG, " getVehicleInfo mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void obdRequest(String str, String str2) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mSdkManager.obdRequest(str, str2);
            return;
        }
        Log.e(TAG, " obdRequest mForTestValue is " + this.mForTest + " execute return");
    }

    public void readConfigurationWordInfo(short s6) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            this.mDiagManager.readReport(new AidlShort(s6));
            return;
        }
        Log.e(TAG, " readConfigurationWordInfo mForTestValue is " + this.mForTest + " execute return");
    }

    public void registerTestListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null || this.mTestListeners.contains(absBYDAutoTestListener)) {
            return;
        }
        this.mTestListeners.add(absBYDAutoTestListener);
        if (this.mNowBind) {
            absBYDAutoTestListener.isConnected(true);
        }
    }

    public int setCloudServieEv(int i7) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setCloudServieEv(i7);
        }
        Log.e(TAG, " setCloudServieEv mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setNetworkOperator(String str) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setNetworkOperator(str);
        }
        Log.e(TAG, " setNetworkOperator mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setRestartTBox(int i7) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setRestartTbox(i7);
        }
        Log.e(TAG, " setRestartTBox mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void unregisterTestListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null || !this.mTestListeners.contains(absBYDAutoTestListener)) {
            return;
        }
        this.mTestListeners.remove(absBYDAutoTestListener);
    }

    public void writeConfigurationWordInfo(short s6, ArrayList<Byte> arrayList) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mDiagManager.writeReport(new WriteReq(s6, arrayList.size(), arrayList));
            return;
        }
        Log.e(TAG, " writeConfigurationWordInfo mForTestValue is " + this.mForTest + " execute return");
    }
}
