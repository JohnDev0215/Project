package android.hardware.bydauto.test.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.test.bean.NetMap;
import android.hardware.bydauto.test.bean.NetworkOperator;
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
import com.ts.lib.engineeringmode.bean.TsNetMap;
import com.ts.lib.engineeringmode.callback.IEngineeringCallBack;
import com.ts.ota.otasdkmgr.ITestTboxCallBack;
import com.ts.ota.otasdkmgr.NetworkOperatorAidl;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestManagerImpl {
    public static final int CHARGE_COMMAND_BUSY = -2147482647;
    public static final int CHARGE_COMMAND_FAILED = -2147482648;
    public static final int CHARGE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int CHARGE_COMMAND_SUCCESS = 0;
    public static final int CHARGE_COMMAND_TIMEOUT = -2147482646;
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
    public static final String TAG = "TS.TestManagerImpl";
    private static final int TEST_VALUE = 100;
    private static TestManagerImpl sTestManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private DiagCallBack mDiagCallBack;
    private DiagManager mDiagManager;
    public EngineeringCallBack mEngineeringCallBack;
    private OtaSdkManager mOtaSdkManager;
    private OtaTestCallback mOtaTestCallback;
    private EngineeringSdkManager mSdkManager;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 100;
    public List<AbsBYDAutoTestListener> mTestListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private boolean mNowBind = false;
    private boolean mDiagBind = false;
    private boolean mEngineerBind = false;

    public class DiagCallBack implements DiagReportCallBack {
        public DiagCallBack() {
        }

        public void readDTCDataReport(DtcData dtcData, String str) {
        }

        public void readInfoFromHalReport(ReadResp readResp, String str) {
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onReadConfigurationWordInfo(readResp.getDid(), readResp.getData());
                }
            }
        }

        public void writeInfoToHalReport(WriteResp writeResp, String str) {
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onWriteConfigurationWordInfo((short) writeResp.getDid(), writeResp.getRes());
                }
            }
        }
    }

    public class OtaTestCallback implements ITestTboxCallBack {
        public OtaTestCallback() {
        }

        public void onAutomaticNetworkChange(int i3) {
            Log.w(TestManagerImpl.TAG, "onAutomaticNetworkChange state:" + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onAutomaticNetworkChange(i3);
                }
            }
        }

        public void onCloudServiceConnectStateChanged(int i3) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged state : " + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onCloudServiceConnectStateChanged(i3);
                }
            }
        }

        public void onManuallyStationedNetworkChange(String str, int i3, int i4, String str2, int i5) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange data:" + str + " curNetType:" + i3 + " state:" + i4 + " lastData:" + str2 + " lastNetType:" + i5);
            for (int i6 = 0; i6 < TestManagerImpl.this.mTestListeners.size(); i6++) {
                if (TestManagerImpl.this.mTestListeners.get(i6) != null) {
                    TestManagerImpl.this.mTestListeners.get(i6).onManuallyStationedNetworkChange(str, i3, i4, str2, i5);
                }
            }
        }

        public void onNetworkOperatorList(ArrayList<String> arrayList) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorList: code : " + arrayList);
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onNetworkOperatorList(arrayList);
                }
            }
        }

        public void onNetworkOperatorModeChange(int i3) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange state:" + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onNetworkOperatorModeChange(i3);
                }
            }
        }

        public void onOperatorInfoChange(ArrayList<NetworkOperatorAidl> arrayList) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange list:" + arrayList);
            ArrayList<NetworkOperator> arrayListTransformNetworkOperator = TestManagerImpl.this.transformNetworkOperator(arrayList);
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onOperatorInfoChange(arrayListTransformNetworkOperator);
                }
            }
        }

        public void onSimCardStateChange(int i3) {
            Log.w(TestManagerImpl.TAG, "onSimCardStateChange status:" + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onSimCardStateChange(i3);
                }
            }
        }

        public void onTboxRestartChanged(int i3) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged: state : " + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onTboxRestartChanged(i3);
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
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
        DiagCallBack diagCallBack = new DiagCallBack();
        this.mDiagCallBack = diagCallBack;
        this.mDiagManager.registerDiagCallBack(diagCallBack);
        this.mDiagManager.registerConnectCallBack(new ConnectCallBack() { // from class: android.hardware.bydauto.test.tsimpl.TestManagerImpl.1
            public void onConnectState(boolean z3) {
                TestManagerImpl.this.mDiagBind = z3;
                TestManagerImpl.this.dealConnected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dealConnected() {
        if (this.mDiagBind && this.mEngineerBind) {
            this.mNowBind = true;
            for (int i3 = 0; i3 < this.mTestListeners.size(); i3++) {
                AbsBYDAutoTestListener absBYDAutoTestListener = this.mTestListeners.get(i3);
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

    private ArrayList<TsNetMap> netMapToTsNetMap(ArrayList<NetMap> arrayList) {
        ArrayList<TsNetMap> arrayList2 = new ArrayList<>(arrayList.size());
        for (NetMap netMap : arrayList) {
            arrayList2.add(new TsNetMap(netMap.getNetSag(), netMap.getIds()));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<NetworkOperator> transformNetworkOperator(ArrayList<NetworkOperatorAidl> arrayList) {
        ArrayList<NetworkOperator> arrayList2 = new ArrayList<>();
        for (NetworkOperatorAidl networkOperatorAidl : arrayList) {
            NetworkOperator networkOperator = new NetworkOperator();
            networkOperator.setMccMnc(networkOperatorAidl.getMccMnc());
            networkOperator.setEnable(networkOperatorAidl.getEnable());
            networkOperator.setSigLevel(networkOperatorAidl.getSigLevel());
            arrayList2.add(networkOperator);
        }
        return arrayList2;
    }

    public int enableAlarmLog(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableAlarmLog(z3);
        }
        Log.e(TAG, " enableAlarmLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableCloudServiceLog(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableCloudServiceLog(z3);
        }
        Log.e(TAG, " enableCloudServiceLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableConsoleSuspand(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableConsoleSuspand(z3);
        }
        Log.e(TAG, " enableConsoleSuspand mForTestValue is 0 execute return");
        return -1;
    }

    public int enableDexOpt(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableDexOpt(z3);
        }
        Log.e(TAG, " enableDexOpt mForTestValue is 0 execute return");
        return -1;
    }

    public int enableRamdumps(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableRamdumps(z3);
        }
        Log.e(TAG, " enableRamdumps mForTestValue is 0 execute return");
        return -1;
    }

    public int enableTCPDump(boolean z3) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableTCPDump(z3);
        }
        Log.e(TAG, " enableTCPDump mForTestValue is 0 execute return");
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

    public String getApn1Address() {
        Log.v(TAG, "getApn1Address is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getApn1Address();
    }

    public int getApn1FailReson() {
        Log.v(TAG, "getApn1FailReson is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getApn1FailReson();
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
    public int getAutoHasDevice(int i3) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoHasDevice mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        Log.w(TAG, "getAutoHasDevice: device : " + i3);
        int i4 = 2;
        int i5 = 1;
        int i6 = 7;
        switch (i3) {
            case 0:
                i5 = 0;
                i4 = 7;
                break;
            case 1:
                i4 = 7;
                break;
            case 2:
                i4 = 7;
                i5 = 4;
                break;
            case 3:
                i4 = 6;
                i6 = i4;
                i5 = 4;
                break;
            case 4:
                i4 = 5;
                i6 = i4;
                i5 = 4;
                break;
            case 5:
                i4 = 4;
                i5 = 4;
                i6 = 4;
                break;
            case 6:
                i6 = i4;
                i5 = 4;
                break;
            case 7:
                i4 = 1;
                i6 = i4;
                i5 = 4;
                break;
            case 8:
                i4 = 0;
                i6 = i4;
                i5 = 4;
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
    public int getPowerSupplyMode(int i3) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getPowerSupplyMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        Log.w(TAG, "getPowerSupplyMode: device : " + i3);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        int i4 = 2;
        int i5 = 1;
        int i6 = 5;
        switch (i3) {
            case 0:
                i4 = 6;
                i6 = i4;
                i5 = 0;
                break;
            case 1:
                i4 = 5;
                i5 = 0;
                break;
            case 2:
                i4 = 4;
                i6 = i4;
                i5 = 0;
                break;
            case 3:
                i4 = 3;
                i6 = i4;
                i5 = 0;
                break;
            case 4:
                i6 = i4;
                i5 = 0;
                break;
            case 5:
                i4 = 1;
                i6 = i4;
                i5 = 0;
                break;
            case 6:
                i4 = 0;
                i5 = 0;
                i6 = 0;
                break;
            case 7:
                i4 = 6;
                i6 = 6;
                break;
            case 8:
                i4 = 5;
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

    public String getYunUuidStr() {
        Log.v(TAG, "getYunUuidStr is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getYunUuidStr();
    }

    public void obdRequest(String str, String str2) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mSdkManager.obdRequest(str, str2);
            return;
        }
        Log.e(TAG, " obdRequest mForTestValue is " + this.mForTest + " execute return");
    }

    public int queryOffScreenFlag(int i3) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mSdkManager.queryOffScreenFlag(i3);
    }

    public void readConfigurationWordInfo(short s3) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            this.mDiagManager.readReport(new AidlShort(s3));
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

    public boolean reqNetworkOperatorMode() {
        Log.v(TAG, "reqNetworkOperatorMode is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.reqNetworkOperatorMode();
    }

    public boolean reqOperatorInfo() {
        Log.v(TAG, "reqOperatorInfo is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.reqOperatorInfo();
    }

    public boolean reqSimCardState() {
        Log.v(TAG, "reqSimCardState is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.reqSimCardState();
    }

    public int sendDLinkCountryCode(int i3) {
        Log.v(TAG, "sendDLinkCountryCode is execute：" + i3);
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "sendDLinkCountryCode adapter is not connect");
            return -2147482648;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mTsManagerImpl.getStudyAdapterManager().sendDLinkCountryCode(i3);
    }

    public boolean setAutomaticNetwork() {
        Log.v(TAG, "setAutomaticNetwork is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setAutomaticNetwork();
    }

    public int setCloudServieEv(int i3) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setCloudServieEv(i3);
        }
        Log.e(TAG, " setCloudServieEv mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public void setEnergyTest(int i3) {
        Log.v(TAG, "setEnergyTest is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        this.mSdkManager.setEnergyTest(i3);
    }

    public boolean setManuallyStationedNetwork(String str, int i3) {
        Log.v(TAG, "setManuallyStationedNetwork is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setManuallyStationedNetwork(str, i3);
    }

    public int setNetworkOperator(String str) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setNetworkOperator(str);
        }
        Log.e(TAG, " setNetworkOperator mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setOffScreenFlag(int i3, int i4) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mSdkManager.setOffScreenFlag(i3, i4);
    }

    public int setRestartTBox(int i3) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            return this.mOtaSdkManager.setRestartTbox(i3);
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

    public void writeConfigurationWordInfo(short s3, ArrayList<Byte> arrayList) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mDiagManager.writeReport(new WriteReq(s3, arrayList.size(), arrayList));
            return;
        }
        Log.e(TAG, " writeConfigurationWordInfo mForTestValue is " + this.mForTest + " execute return");
    }

    public class EngineeringCallBack implements IEngineeringCallBack {
        public EngineeringCallBack() {
        }

        public void isConnected(boolean z3) {
            TestManagerImpl.this.mEngineerBind = z3;
            TestManagerImpl.this.dealConnected();
        }

        public void onEnergyTestChange(int i3) {
            Log.w(TestManagerImpl.TAG, "onEnergyTestChange status : " + i3);
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onEnergyTestChange(i3);
                }
            }
        }

        public void onGetOffScreenFlagResult(int i3, int i4) {
            Log.w(TestManagerImpl.TAG, "onGetOffScreenFlagResult type : " + i3 + ", resultCode : " + i4);
            for (int i5 = 0; i5 < TestManagerImpl.this.mTestListeners.size(); i5++) {
                if (TestManagerImpl.this.mTestListeners.get(i5) != null) {
                    TestManagerImpl.this.mTestListeners.get(i5).onGetOffScreenFlagResult(i3, i4);
                }
            }
        }

        public void onHwTemperature(Bundle bundle) {
        }

        public void onObdReport(String str, String str2) {
            Log.w(TestManagerImpl.TAG, "onObdReport id :" + str + " data : " + str2);
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onObdReport(str, str2);
                }
            }
        }

        public void onQnxLogStatusChanged(int i3, boolean z3) {
            for (int i4 = 0; i4 < TestManagerImpl.this.mTestListeners.size(); i4++) {
                if (TestManagerImpl.this.mTestListeners.get(i4) != null) {
                    TestManagerImpl.this.mTestListeners.get(i4).onQNXLogStatusChanged(i3, z3);
                }
            }
        }

        public void onSynchronousQNXData(int i3) {
        }

        public void onObdReport(int i3, int i4) {
            Log.w(TestManagerImpl.TAG, "onObdReport iviObdOperationType :" + i3 + " iviObdStatus : " + i4);
            for (int i5 = 0; i5 < TestManagerImpl.this.mTestListeners.size(); i5++) {
                if (TestManagerImpl.this.mTestListeners.get(i5) != null) {
                    TestManagerImpl.this.mTestListeners.get(i5).onObdReport(i3, i4);
                }
            }
        }
    }

    public void obdRequest(int i3, int i4, ArrayList<NetMap> arrayList, boolean z3) {
        if (this.mForTest == 0) {
            Log.e(TAG, " obdRequest mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (arrayList == null) {
            Log.e(TAG, " obdRequest netMapList == null ");
        } else {
            this.mSdkManager.obdRequest(i3, i4, netMapToTsNetMap(arrayList), z3);
        }
    }
}
