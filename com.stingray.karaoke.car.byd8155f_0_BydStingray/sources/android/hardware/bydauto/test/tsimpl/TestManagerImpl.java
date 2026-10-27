package android.hardware.bydauto.test.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
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
import h6.n;
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
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 100;
    List<AbsBYDAutoTestListener> mTestListeners = new ArrayList();
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

    public class OtaTestCallback implements ITestTboxCallBack {
        public OtaTestCallback() {
        }

        public void onCloudServiceConnectStateChanged(int i7) {
            a.r("onTboxRestartChanged state : ", i7, TestManagerImpl.TAG);
            for (int i10 = 0; i10 < TestManagerImpl.this.mTestListeners.size(); i10++) {
                if (TestManagerImpl.this.mTestListeners.get(i10) != null) {
                    TestManagerImpl.this.mTestListeners.get(i10).onCloudServiceConnectStateChanged(i7);
                }
            }
        }

        public void onNetworkOperatorList(ArrayList<String> arrayList) {
            n.t(TestManagerImpl.TAG, "onNetworkOperatorList: code : " + arrayList);
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onNetworkOperatorList(arrayList);
                }
            }
        }

        public void onTboxRestartChanged(int i7) {
            a.r("onTboxRestartChanged: state : ", i7, TestManagerImpl.TAG);
            for (int i10 = 0; i10 < TestManagerImpl.this.mTestListeners.size(); i10++) {
                if (TestManagerImpl.this.mTestListeners.get(i10) != null) {
                    TestManagerImpl.this.mTestListeners.get(i10).onTboxRestartChanged(i7);
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
            public void onConnectState(boolean z6) {
                TestManagerImpl.this.mDiagBind = z6;
                TestManagerImpl.this.dealConnected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dealConnected() {
        try {
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
        } catch (Throwable th) {
            throw th;
        }
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static synchronized TestManagerImpl getInstance(Context context) {
        try {
            if (sTestManagerImpl == null) {
                sTestManagerImpl = new TestManagerImpl(context);
            }
        } catch (Throwable th) {
            throw th;
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

    public int enableAlarmLog(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableAlarmLog(z6);
        }
        n.j(TAG, " enableAlarmLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableCloudServiceLog(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableCloudServiceLog(z6);
        }
        n.j(TAG, " enableCloudServiceLog mForTestValue is 0 execute return");
        return -1;
    }

    public int enableConsoleSuspand(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableConsoleSuspand(z6);
        }
        n.j(TAG, " enableConsoleSuspand mForTestValue is 0 execute return");
        return -1;
    }

    public int enableDexOpt(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableDexOpt(z6);
        }
        n.j(TAG, " enableDexOpt mForTestValue is 0 execute return");
        return -1;
    }

    public int enableRamdumps(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableRamdumps(z6);
        }
        n.j(TAG, " enableRamdumps mForTestValue is 0 execute return");
        return -1;
    }

    public int enableTCPDump(boolean z6) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest != 0) {
            return this.mSdkManager.enableTCPDump(z6);
        }
        n.j(TAG, " enableTCPDump mForTestValue is 0 execute return");
        return -1;
    }

    public String geCloudServiceVersion() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServiceVersion();
        }
        a.v(new StringBuilder(" geCloudServiceVersion mForTestValue is "), this.mForTest, " execute return", TAG);
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
            a.v(new StringBuilder(" getAutoAudioSource mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 6, 0, 7));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getAutoHasDevice(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getAutoHasDevice mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        a.r("getAutoHasDevice: device : ", i7, TAG);
        int i10 = 1;
        int i11 = 0;
        int i12 = 7;
        switch (i7) {
            case 0:
                i10 = 0;
                i11 = 7;
                break;
            case 1:
                i11 = 7;
                break;
            case 2:
                i11 = 7;
                i10 = 4;
                break;
            case 3:
                i10 = 6;
                i11 = i10;
                i12 = i11;
                i10 = 4;
                break;
            case 4:
                i10 = 5;
                i11 = i10;
                i12 = i11;
                i10 = 4;
                break;
            case 5:
                i10 = 4;
                i11 = 4;
                i12 = 4;
                break;
            case 6:
                i10 = 2;
                i11 = i10;
                i12 = i11;
                i10 = 4;
                break;
            case 7:
                i11 = i10;
                i12 = i11;
                i10 = 4;
                break;
            case 8:
                i12 = i11;
                i10 = 4;
                break;
        }
        return -1;
    }

    public int getAutoMicCount() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getAutoMicCount mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 6, 7));
    }

    public int getAutoPowerType() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getAutoPowerType mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 4, 5));
    }

    public int getAutoSpeakerCount() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getAutoSpeakerCount mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 0, 3));
    }

    public int getAutoTBoxNetwork() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getAutoTBoxNetwork mForTestValue is "), this.mForTest, " execute return", TAG);
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
        a.v(new StringBuilder(" getCloudServiceConnectState mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getCloudServieEv() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest != 0) {
            return this.mOtaSdkManager.getCloudServieEv();
        }
        a.v(new StringBuilder(" getCloudServieEv mForTestValue is "), this.mForTest, " execute return", TAG);
        return -1;
    }

    public int getConfigArea() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getConfigArea mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 3, 0, 7));
    }

    public void getConsoleSuspandState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getConsoleSuspandState mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            this.mSdkManager.getConsoleSuspandState();
        }
    }

    public String getCurNetworkOperator() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getCurNetworkOperator mForTestValue is "), this.mForTest, " execute return", TAG);
            return null;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getCurNetworkOperator();
    }

    public int getHasCarplayWired() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getHasCarplayWired mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 1, 1));
    }

    public int getHasSingleBt() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getHasSingleBt mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 3, 3));
    }

    public int getNetworkOperatorList() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getNetworkOperatorList mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getNetworkOperatorList();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getPowerSupplyMode(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getPowerSupplyMode mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        a.r("getPowerSupplyMode: device : ", i7, TAG);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        int i10 = 6;
        int i11 = 5;
        int i12 = 1;
        switch (i7) {
            case 0:
                i11 = i10;
                i12 = 0;
                break;
            case 1:
                i10 = 5;
                i12 = 0;
                break;
            case 2:
                i10 = 4;
                i11 = i10;
                i12 = 0;
                break;
            case 3:
                i10 = 3;
                i11 = i10;
                i12 = 0;
                break;
            case 4:
                i10 = 2;
                i11 = i10;
                i12 = 0;
                break;
            case 5:
                i10 = 1;
                i11 = i10;
                i12 = 0;
                break;
            case 6:
                i10 = 0;
                i11 = 0;
                i12 = 0;
                break;
            case 7:
                i11 = 6;
                break;
            case 8:
                i10 = 5;
                break;
        }
        return -1;
    }

    public int getSWCType() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getSWCType mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 0, 0));
    }

    public int getSteeringWheelPosition() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getSteeringWheelPosition mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 4, 3, 3));
    }

    public int getTBoxState() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getTBoxState mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getTboxState();
    }

    public void getTCPDumpState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getTCPDumpState mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            this.mSdkManager.getTcpDumpState();
        }
    }

    public int getUSVersion() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getUSVersion mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 2, 2));
    }

    public int getVehicleInfo() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getVehicleInfo mForTestValue is "), this.mForTest, " execute return", TAG);
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
            a.v(new StringBuilder(" obdRequest mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mSdkManager.obdRequest(str, str2);
        }
    }

    public void readConfigurationWordInfo(short s6) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" readConfigurationWordInfo mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
            this.mDiagManager.readReport(new AidlShort(s6));
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

    public int sendDLinkCountryCode(int i7) {
        b.o("sendDLinkCountryCode is execute：", i7, TAG);
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            n.j(TAG, "sendDLinkCountryCode adapter is not connect");
            return -2147482648;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mTsManagerImpl.getStudyAdapterManager().sendDLinkCountryCode(i7);
    }

    public int setCloudServieEv(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" setCloudServieEv mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setCloudServieEv(i7);
    }

    public int setNetworkOperator(String str) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" setNetworkOperator mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setNetworkOperator(str);
    }

    public int setRestartTBox(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" setRestartTBox mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setRestartTbox(i7);
    }

    public void unregisterTestListener(AbsBYDAutoTestListener absBYDAutoTestListener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (absBYDAutoTestListener == null || !this.mTestListeners.contains(absBYDAutoTestListener)) {
            return;
        }
        this.mTestListeners.remove(absBYDAutoTestListener);
    }

    public void writeConfigurationWordInfo(short s6, ArrayList<Byte> arrayList) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" writeConfigurationWordInfo mForTestValue is "), this.mForTest, " execute return", TAG);
        } else {
            enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
            this.mDiagManager.writeReport(new WriteReq(s6, arrayList.size(), arrayList));
        }
    }

    public class EngineeringCallBack implements IEngineeringCallBack {
        public EngineeringCallBack() {
        }

        public void isConnected(boolean z6) {
            TestManagerImpl.this.mEngineerBind = z6;
            TestManagerImpl.this.dealConnected();
        }

        public void onHwTemperature(Bundle bundle) {
        }

        public void onObdReport(String str, String str2) {
            n.t(TestManagerImpl.TAG, "onObdReport id :" + str + " data : " + str2);
            for (int i7 = 0; i7 < TestManagerImpl.this.mTestListeners.size(); i7++) {
                if (TestManagerImpl.this.mTestListeners.get(i7) != null) {
                    TestManagerImpl.this.mTestListeners.get(i7).onObdReport(str, str2);
                }
            }
        }

        public void onQnxLogStatusChanged(int i7, boolean z6) {
            for (int i10 = 0; i10 < TestManagerImpl.this.mTestListeners.size(); i10++) {
                if (TestManagerImpl.this.mTestListeners.get(i10) != null) {
                    TestManagerImpl.this.mTestListeners.get(i10).onQNXLogStatusChanged(i7, z6);
                }
            }
        }

        public void onSynchronousQNXData(int i7) {
        }

        public void onObdReport(int i7, int i10) {
            n.t(TestManagerImpl.TAG, "onObdReport iviObdOperationType :" + i7 + " iviObdStatus : " + i10);
            for (int i11 = 0; i11 < TestManagerImpl.this.mTestListeners.size(); i11++) {
                if (TestManagerImpl.this.mTestListeners.get(i11) != null) {
                    TestManagerImpl.this.mTestListeners.get(i11).onObdReport(i7, i10);
                }
            }
        }
    }

    public void obdRequest(int i7, int i10, ArrayList<NetMap> arrayList, boolean z6) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" obdRequest mForTestValue is "), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (arrayList == null) {
            n.j(TAG, " obdRequest netMapList == null ");
        } else {
            this.mSdkManager.obdRequest(i7, i10, netMapToTsNetMap(arrayList), z6);
        }
    }
}
