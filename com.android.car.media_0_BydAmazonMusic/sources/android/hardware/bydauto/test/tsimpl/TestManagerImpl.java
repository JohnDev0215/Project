package android.hardware.bydauto.test.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.test.bean.NetMap;
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
import com.ts.ota.otasdkmgr.OtaSdkManager;
import java.util.ArrayList;
import java.util.List;
import spi.c;

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
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onReadConfigurationWordInfo(readResp.getDid(), readResp.getData());
                }
            }
        }

        public void writeInfoToHalReport(WriteResp writeResp, String str) {
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onWriteConfigurationWordInfo((short) writeResp.getDid(), writeResp.getRes());
                }
            }
        }
    }

    public class OtaTestCallback implements ITestTboxCallBack {
        public OtaTestCallback() {
        }

        public void onCloudServiceConnectStateChanged(int i) {
            c.A("onTboxRestartChanged state : ", i, TestManagerImpl.TAG);
            for (int i2 = 0; i2 < TestManagerImpl.this.mTestListeners.size(); i2++) {
                if (TestManagerImpl.this.mTestListeners.get(i2) != null) {
                    TestManagerImpl.this.mTestListeners.get(i2).onCloudServiceConnectStateChanged(i);
                }
            }
        }

        public void onNetworkOperatorList(ArrayList<String> arrayList) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorList: code : " + arrayList);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onNetworkOperatorList(arrayList);
                }
            }
        }

        public void onTboxRestartChanged(int i) {
            c.A("onTboxRestartChanged: state : ", i, TestManagerImpl.TAG);
            for (int i2 = 0; i2 < TestManagerImpl.this.mTestListeners.size(); i2++) {
                if (TestManagerImpl.this.mTestListeners.get(i2) != null) {
                    TestManagerImpl.this.mTestListeners.get(i2).onTboxRestartChanged(i);
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
            public void onConnectState(boolean z) {
                TestManagerImpl.this.mDiagBind = z;
                TestManagerImpl.this.dealConnected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dealConnected() {
        if (this.mDiagBind && this.mEngineerBind) {
            this.mNowBind = true;
            for (int i = 0; i < this.mTestListeners.size(); i++) {
                AbsBYDAutoTestListener absBYDAutoTestListener = this.mTestListeners.get(i);
                if (absBYDAutoTestListener != null) {
                    absBYDAutoTestListener.isConnected(this.mNowBind);
                }
            }
        } else {
            this.mNowBind = false;
        }
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(c.f("requires permission ", str));
        }
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

    public int enableAlarmLog(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableAlarmLog(z);
        }
        Log.e(TAG, " enableAlarmLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableCloudServiceLog(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableCloudServiceLog(z);
        }
        Log.e(TAG, " enableCloudServiceLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableConsoleSuspand(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableConsoleSuspand(z);
        }
        Log.e(TAG, " enableConsoleSuspand mForTestValue is 0 execute return");
        return -1;
    }

    public int enableDexOpt(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableDexOpt(z);
        }
        Log.e(TAG, " enableDexOpt mForTestValue is 0 execute return");
        return -1;
    }

    public int enableRamdumps(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableRamdumps(z);
        }
        Log.e(TAG, " enableRamdumps mForTestValue is 0 execute return");
        return -1;
    }

    public int enableTCPDump(boolean z) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableTCPDump(z);
        }
        Log.e(TAG, " enableTCPDump mForTestValue is 0 execute return");
        return -1;
    }

    public String geCloudServiceVersion() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServiceVersion();
        }
        c.z(c.k(" geCloudServiceVersion mForTestValue is "), this.mForTest, " execute return", TAG);
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
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoAudioSource mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 6, 0, 7));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getAutoHasDevice(int i) {
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoHasDevice mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        c.A("getAutoHasDevice: device : ", i, TAG);
        int i2 = 2;
        int i3 = 1;
        int i4 = 7;
        int i5 = 4;
        switch (i) {
            case 0:
                i3 = 0;
                i5 = i4;
                break;
            case 1:
                i5 = i4;
                break;
            case 2:
                i3 = 4;
                i5 = i4;
                break;
            case 3:
                i2 = 6;
                i4 = i2;
                i3 = 4;
                i5 = i4;
                break;
            case 4:
                i2 = 5;
                i4 = i2;
                i3 = 4;
                i5 = i4;
                break;
            case 5:
                i3 = 4;
                i4 = 4;
                break;
            case 6:
                i4 = i2;
                i3 = 4;
                i5 = i4;
                break;
            case 7:
                i2 = 1;
                i4 = i2;
                i3 = 4;
                i5 = i4;
                break;
            case 8:
                i2 = 0;
                i4 = i2;
                i3 = 4;
                i5 = i4;
                break;
        }
        return -1;
    }

    public int getAutoMicCount() {
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoMicCount mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 6, 7));
    }

    public int getAutoPowerType() {
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoPowerType mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 4, 5));
    }

    public int getAutoSpeakerCount() {
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoSpeakerCount mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 0, 3));
    }

    public int getAutoTBoxNetwork() {
        if (this.mForTest == 0) {
            c.z(c.k(" getAutoTBoxNetwork mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 4, 4));
    }

    public int getCloudServiceConnectState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServiceConnectState();
        }
        c.z(c.k(" getCloudServiceConnectState mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getCloudServieEv() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServieEv();
        }
        c.z(c.k(" getCloudServieEv mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getConfigArea() {
        if (this.mForTest == 0) {
            c.z(c.k(" getConfigArea mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 3, 0, 7));
    }

    public void getConsoleSuspandState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            c.z(c.k(" getConsoleSuspandState mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            this.mSdkManager.getConsoleSuspandState();
        }
    }

    public String getCurNetworkOperator() {
        if (this.mForTest == 0) {
            c.z(c.k(" getCurNetworkOperator mForTestValue is "), this.mForTest, " execute return", TAG);
            return null;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getCurNetworkOperator();
    }

    public int getHasCarplayWired() {
        if (this.mForTest == 0) {
            c.z(c.k(" getHasCarplayWired mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 1, 1));
    }

    public int getHasSingleBt() {
        if (this.mForTest == 0) {
            c.z(c.k(" getHasSingleBt mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 3, 3));
    }

    public int getNetworkOperatorList() {
        if (this.mForTest == 0) {
            c.z(c.k(" getNetworkOperatorList mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getNetworkOperatorList();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getPowerSupplyMode(int i) {
        if (this.mForTest == 0) {
            c.z(c.k(" getPowerSupplyMode mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        c.A("getPowerSupplyMode: device : ", i, TAG);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        int i2 = 2;
        int i3 = 1;
        int i4 = 5;
        switch (i) {
            case 0:
                i2 = 6;
                i4 = i2;
                i3 = 0;
                break;
            case 1:
                i3 = 0;
                break;
            case 2:
                i2 = 4;
                i4 = i2;
                i3 = 0;
                break;
            case 3:
                i2 = 3;
                i4 = i2;
                i3 = 0;
                break;
            case 4:
                i4 = i2;
                i3 = 0;
                break;
            case 5:
                i2 = 1;
                i4 = i2;
                i3 = 0;
                break;
            case 6:
                i3 = 0;
                i4 = 0;
                break;
            case 7:
                i4 = 6;
                break;
        }
        return -1;
    }

    public int getSWCType() {
        if (this.mForTest == 0) {
            c.z(c.k(" getSWCType mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 0, 0));
    }

    public int getSteeringWheelPosition() {
        if (this.mForTest == 0) {
            c.z(c.k(" getSteeringWheelPosition mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 4, 3, 3));
    }

    public int getTBoxState() {
        if (this.mForTest == 0) {
            c.z(c.k(" getTBoxState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getTboxState();
    }

    public void getTCPDumpState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            c.z(c.k(" getTCPDumpState mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            this.mSdkManager.getTcpDumpState();
        }
    }

    public int getUSVersion() {
        if (this.mForTest == 0) {
            c.z(c.k(" getUSVersion mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 2, 2));
    }

    public int getVehicleInfo() {
        if (this.mForTest == 0) {
            c.z(c.k(" getVehicleInfo mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 2, 0, 7));
    }

    public String getYunUuidStr() {
        Log.v(TAG, "getYunUuidStr is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getYunUuidStr();
    }

    public void obdRequest(String str, String str2) {
        if (this.mForTest == 0) {
            c.z(c.k(" obdRequest mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mSdkManager.obdRequest(str, str2);
        }
    }

    public int queryOffScreenFlag(int i) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mSdkManager.queryOffScreenFlag(i);
    }

    public void readConfigurationWordInfo(short s) {
        if (this.mForTest == 0) {
            c.z(c.k(" readConfigurationWordInfo mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            this.mDiagManager.readReport(new AidlShort(s));
        }
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

    public int sendDLinkCountryCode(int i) {
        c.u("sendDLinkCountryCode is execute：", i, TAG);
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "sendDLinkCountryCode adapter is not connect");
            return -2147482648;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mTsManagerImpl.getStudyAdapterManager().sendDLinkCountryCode(i);
    }

    public int setCloudServieEv(int i) {
        if (this.mForTest == 0) {
            c.z(c.k(" setCloudServieEv mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setCloudServieEv(i);
    }

    public int setNetworkOperator(String str) {
        if (this.mForTest == 0) {
            c.z(c.k(" setNetworkOperator mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setNetworkOperator(str);
    }

    public int setOffScreenFlag(int i, int i2) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mSdkManager.setOffScreenFlag(i, i2);
    }

    public int setRestartTBox(int i) {
        if (this.mForTest == 0) {
            c.z(c.k(" setRestartTBox mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setRestartTbox(i);
    }

    public void unregisterTestListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null || !this.mTestListeners.contains(absBYDAutoTestListener)) {
            return;
        }
        this.mTestListeners.remove(absBYDAutoTestListener);
    }

    public void writeConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
        if (this.mForTest == 0) {
            c.z(c.k(" writeConfigurationWordInfo mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mDiagManager.writeReport(new WriteReq(s, arrayList.size(), arrayList));
        }
    }

    public class EngineeringCallBack implements IEngineeringCallBack {
        public EngineeringCallBack() {
        }

        public void isConnected(boolean z) {
            TestManagerImpl.this.mEngineerBind = z;
            TestManagerImpl.this.dealConnected();
        }

        public void onGetOffScreenFlagResult(int i, int i2) {
            Log.w(TestManagerImpl.TAG, "onObdReport type : " + i + ", resultCode : " + i2);
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onGetOffScreenFlagResult(i, i2);
                }
            }
        }

        public void onHwTemperature(Bundle bundle) {
        }

        public void onObdReport(String str, String str2) {
            Log.w(TestManagerImpl.TAG, "onObdReport id :" + str + " data : " + str2);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onObdReport(str, str2);
                }
            }
        }

        public void onQnxLogStatusChanged(int i, boolean z) {
            for (int i2 = 0; i2 < TestManagerImpl.this.mTestListeners.size(); i2++) {
                if (TestManagerImpl.this.mTestListeners.get(i2) != null) {
                    TestManagerImpl.this.mTestListeners.get(i2).onQNXLogStatusChanged(i, z);
                }
            }
        }

        public void onSynchronousQNXData(int i) {
        }

        public void onObdReport(int i, int i2) {
            Log.w(TestManagerImpl.TAG, "onObdReport iviObdOperationType :" + i + " iviObdStatus : " + i2);
            for (int i3 = 0; i3 < TestManagerImpl.this.mTestListeners.size(); i3++) {
                if (TestManagerImpl.this.mTestListeners.get(i3) != null) {
                    TestManagerImpl.this.mTestListeners.get(i3).onObdReport(i, i2);
                }
            }
        }
    }

    public void obdRequest(int i, int i2, ArrayList<NetMap> arrayList, boolean z) {
        if (this.mForTest == 0) {
            c.z(c.k(" obdRequest mForTestValue is "), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (arrayList == null) {
            Log.e(TAG, " obdRequest netMapList == null ");
        } else {
            this.mSdkManager.obdRequest(i, i2, netMapToTsNetMap(arrayList), z);
        }
    }
}
