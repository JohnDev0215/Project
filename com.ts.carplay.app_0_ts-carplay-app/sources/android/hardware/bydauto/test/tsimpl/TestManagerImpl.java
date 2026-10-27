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
    protected static final String TAG = "TS.TestManagerImpl";
    private static final int TEST_VALUE = 100;
    private static TestManagerImpl sTestManagerImpl = null;
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

    public TestManagerImpl(Context con) {
        this.mContext = null;
        this.mContentResolver = con.getContentResolver();
        this.mContext = con;
        this.mSdkManager = EngineeringSdkManager.getInstance(con);
        EngineeringCallBack engineeringCallBack = new EngineeringCallBack();
        this.mEngineeringCallBack = engineeringCallBack;
        this.mSdkManager.registerListener(engineeringCallBack);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        this.mOtaSdkManager = OtaSdkManager.getInstance(con);
        OtaTestCallback otaTestCallback = new OtaTestCallback();
        this.mOtaTestCallback = otaTestCallback;
        this.mOtaSdkManager.registerTestListener(otaTestCallback);
        this.mDiagManager = DiagManager.getInstance(con);
        this.mTsManagerImpl = TsManagerImpl.getInstance(con);
        DiagCallBack diagCallBack = new DiagCallBack();
        this.mDiagCallBack = diagCallBack;
        this.mDiagManager.registerDiagCallBack(diagCallBack);
        this.mDiagManager.registerConnectCallBack(new ConnectCallBack() { // from class: android.hardware.bydauto.test.tsimpl.TestManagerImpl.1
            public void onConnectState(boolean connectState) {
                TestManagerImpl.this.mDiagBind = connectState;
                TestManagerImpl.this.dealConnected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dealConnected() {
        if (this.mDiagBind && this.mEngineerBind) {
            this.mNowBind = true;
            for (int i = 0; i < this.mTestListeners.size(); i++) {
                AbsBYDAutoTestListener listener = this.mTestListeners.get(i);
                if (listener != null) {
                    listener.isConnected(this.mNowBind);
                }
            }
        } else {
            this.mNowBind = false;
        }
    }

    class EngineeringCallBack implements IEngineeringCallBack {
        EngineeringCallBack() {
        }

        public void onSynchronousQNXData(int state) {
        }

        public void onHwTemperature(Bundle bundle) {
        }

        public void onQnxLogStatusChanged(int logType, boolean status) {
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onQNXLogStatusChanged(logType, status);
                }
            }
        }

        public void onObdReport(String id, String data) {
            Log.w(TestManagerImpl.TAG, "onObdReport id :" + id + " data : " + data);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onObdReport(id, data);
                }
            }
        }

        public void onObdReport(int iviObdOperationType, int iviObdStatus) {
            Log.w(TestManagerImpl.TAG, "onObdReport iviObdOperationType :" + iviObdOperationType + " iviObdStatus : " + iviObdStatus);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onObdReport(iviObdOperationType, iviObdStatus);
                }
            }
        }

        public void onGetOffScreenFlagResult(int type, int resultCode) {
            Log.w(TestManagerImpl.TAG, "onGetOffScreenFlagResult type : " + type + ", resultCode : " + resultCode);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onGetOffScreenFlagResult(type, resultCode);
                }
            }
        }

        public void onEnergyTestChange(int status) {
            Log.w(TestManagerImpl.TAG, "onEnergyTestChange status : " + status);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onEnergyTestChange(status);
                }
            }
        }

        public void isConnected(boolean isBinding) {
            TestManagerImpl.this.mEngineerBind = isBinding;
            TestManagerImpl.this.dealConnected();
        }
    }

    class DiagCallBack implements DiagReportCallBack {
        DiagCallBack() {
        }

        public void readInfoFromHalReport(ReadResp readResp, String s) {
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onReadConfigurationWordInfo(readResp.getDid(), readResp.getData());
                }
            }
        }

        public void writeInfoToHalReport(WriteResp writeResp, String s) {
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onWriteConfigurationWordInfo((short) writeResp.getDid(), writeResp.getRes());
                }
            }
        }

        public void readDTCDataReport(DtcData dtcData, String s) {
        }
    }

    class OtaTestCallback implements ITestTboxCallBack {
        OtaTestCallback() {
        }

        public void onNetworkOperatorList(ArrayList<String> code) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorList: code : " + code);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onNetworkOperatorList(code);
                }
            }
        }

        public void onTboxRestartChanged(int state) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged: state : " + state);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onTboxRestartChanged(state);
                }
            }
        }

        public void onCloudServiceConnectStateChanged(int state) {
            Log.w(TestManagerImpl.TAG, "onTboxRestartChanged state : " + state);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onCloudServiceConnectStateChanged(state);
                }
            }
        }

        public void onNetworkOperatorModeChange(int state) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange state:" + state);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onNetworkOperatorModeChange(state);
                }
            }
        }

        public void onOperatorInfoChange(ArrayList<NetworkOperatorAidl> list) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange list:" + list);
            ArrayList<NetworkOperator> networkOperatorArrayList = TestManagerImpl.this.transformNetworkOperator(list);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onOperatorInfoChange(networkOperatorArrayList);
                }
            }
        }

        public void onManuallyStationedNetworkChange(String data, int curNetType, int state, String lastData, int lastNetType) {
            Log.w(TestManagerImpl.TAG, "onNetworkOperatorModeChange data:" + data + " curNetType:" + curNetType + " state:" + state + " lastData:" + lastData + " lastNetType:" + lastNetType);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onManuallyStationedNetworkChange(data, curNetType, state, lastData, lastNetType);
                }
            }
        }

        public void onAutomaticNetworkChange(int state) {
            Log.w(TestManagerImpl.TAG, "onAutomaticNetworkChange state:" + state);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onAutomaticNetworkChange(state);
                }
            }
        }

        public void onSimCardStateChange(int status) {
            Log.w(TestManagerImpl.TAG, "onSimCardStateChange status:" + status);
            for (int i = 0; i < TestManagerImpl.this.mTestListeners.size(); i++) {
                if (TestManagerImpl.this.mTestListeners.get(i) != null) {
                    TestManagerImpl.this.mTestListeners.get(i).onSimCardStateChange(status);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<NetworkOperator> transformNetworkOperator(ArrayList<NetworkOperatorAidl> aidlList) {
        ArrayList<NetworkOperator> networkList = new ArrayList<>();
        for (NetworkOperatorAidl aidl : aidlList) {
            NetworkOperator networkOperator = new NetworkOperator();
            networkOperator.setMccMnc(aidl.getMccMnc());
            networkOperator.setEnable(aidl.getEnable());
            networkOperator.setSigLevel(aidl.getSigLevel());
            networkList.add(networkOperator);
        }
        return networkList;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public static synchronized TestManagerImpl getInstance(Context con) {
        if (sTestManagerImpl == null) {
            sTestManagerImpl = new TestManagerImpl(con);
        }
        return sTestManagerImpl;
    }

    public void registerTestListener(AbsBYDAutoTestListener listener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (listener != null && !this.mTestListeners.contains(listener)) {
            this.mTestListeners.add(listener);
            if (this.mNowBind) {
                listener.isConnected(true);
            }
        }
    }

    public void unregisterTestListener(AbsBYDAutoTestListener listener) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_COMMON);
        if (listener != null && this.mTestListeners.contains(listener)) {
            this.mTestListeners.remove(listener);
        }
    }

    public int enableRamdumps(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableRamdumps mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableRamdumps(state);
    }

    public int enableCloudServiceLog(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableCloudServiceLog mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableCloudServiceLog(state);
    }

    public int enableAlarmLog(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableAlarmLog mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableAlarmLog(state);
    }

    public int enableDexOpt(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableDexOpt mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableDexOpt(state);
    }

    public int enableConsoleSuspand(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableConsoleSuspand mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableConsoleSuspand(state);
    }

    public int enableTCPDump(boolean state) {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (this.mForTest == 0) {
            Log.e(TAG, " enableTCPDump mForTestValue is 0 execute return");
            return -1;
        }
        return this.mSdkManager.enableTCPDump(state);
    }

    public void getConsoleSuspandState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            Log.e(TAG, " getConsoleSuspandState mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        this.mSdkManager.getConsoleSuspandState();
    }

    public void getTCPDumpState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            Log.e(TAG, " getTCPDumpState mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        this.mSdkManager.getTcpDumpState();
    }

    public String geCloudServiceVersion() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            Log.e(TAG, " geCloudServiceVersion mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        return this.mOtaSdkManager.getCloudServiceVersion();
    }

    public int getCloudServieEv() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            Log.e(TAG, " getCloudServieEv mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mOtaSdkManager.getCloudServieEv();
    }

    public int getCloudServiceConnectState() {
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        if (this.mForTest == 0) {
            Log.e(TAG, " getCloudServiceConnectState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        return this.mOtaSdkManager.getCloudServiceConnectState();
    }

    public void readConfigurationWordInfo(short did) {
        if (this.mForTest == 0) {
            Log.e(TAG, " readConfigurationWordInfo mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        this.mDiagManager.readReport(new AidlShort(did));
    }

    public void writeConfigurationWordInfo(short did, ArrayList<Byte> data) {
        if (this.mForTest == 0) {
            Log.e(TAG, " writeConfigurationWordInfo mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        this.mDiagManager.writeReport(new WriteReq(did, data.size(), data));
    }

    public int getPowerSupplyMode(int device) {
        int index;
        int begin;
        int begin2;
        if (this.mForTest == 0) {
            Log.e(TAG, " getPowerSupplyMode mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        Log.w(TAG, "getPowerSupplyMode: device : " + device);
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        switch (device) {
            case 0:
                index = 0;
                begin = 6;
                begin2 = 6;
                break;
            case 1:
                index = 0;
                begin = 5;
                begin2 = 5;
                break;
            case 2:
                index = 0;
                begin = 4;
                begin2 = 4;
                break;
            case 3:
                index = 0;
                begin = 3;
                begin2 = 3;
                break;
            case 4:
                index = 0;
                begin = 2;
                begin2 = 2;
                break;
            case 5:
                index = 0;
                begin = 1;
                begin2 = 1;
                break;
            case 6:
                index = 0;
                begin = 0;
                begin2 = 0;
                break;
            case 7:
                index = 1;
                begin = 6;
                begin2 = 6;
                break;
            case 8:
                index = 1;
                begin = 5;
                begin2 = 5;
                break;
            default:
                return -1;
        }
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, index, begin, begin2));
    }

    public int getAutoHasDevice(int device) {
        int index;
        int begin;
        int begin2;
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoHasDevice mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        Log.w(TAG, "getAutoHasDevice: device : " + device);
        switch (device) {
            case 0:
                index = 0;
                begin = 7;
                begin2 = 7;
                break;
            case 1:
                index = 1;
                begin = 7;
                begin2 = 7;
                break;
            case 2:
                index = 4;
                begin = 7;
                begin2 = 7;
                break;
            case 3:
                index = 4;
                begin = 6;
                begin2 = 6;
                break;
            case 4:
                index = 4;
                begin = 5;
                begin2 = 5;
                break;
            case 5:
                index = 4;
                begin = 4;
                begin2 = 4;
                break;
            case 6:
                index = 4;
                begin = 2;
                begin2 = 2;
                break;
            case 7:
                index = 4;
                begin = 1;
                begin2 = 1;
                break;
            case 8:
                index = 4;
                begin = 0;
                begin2 = 0;
                break;
            default:
                return -1;
        }
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, index, begin, begin2));
    }

    public int getAutoTBoxNetwork() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoTBoxNetwork mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 4, 4));
    }

    public int getUSVersion() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUSVersion mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 2, 2));
    }

    public int getHasSingleBt() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHasSingleBt mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 3, 3));
    }

    public int getHasCarplayWired() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHasCarplayWired mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 1, 1));
    }

    public int getSWCType() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSWCType mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 1, 0, 0));
    }

    public int getVehicleInfo() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getVehicleInfo mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 2, 0, 7));
    }

    public int getConfigArea() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getConfigArea mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 3, 0, 7));
    }

    public int getSteeringWheelPosition() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSteeringWheelPosition mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 4, 3, 3));
    }

    public int getAutoMicCount() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoMicCount mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 6, 7));
    }

    public int getAutoPowerType() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoPowerType mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 4, 5));
    }

    public int getAutoSpeakerCount() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoSpeakerCount mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 5, 0, 3));
    }

    public int getAutoAudioSource() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAutoAudioSource mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mDiagManager.readDidConfigurationWordInfo(new DidConfigurationWordInfo(DID2100, 6, 0, 7));
    }

    public int getNetworkOperatorList() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getNetworkOperatorList mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getNetworkOperatorList();
    }

    public String getCurNetworkOperator() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getCurNetworkOperator mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getCurNetworkOperator();
    }

    public int setNetworkOperator(String code) {
        if (this.mForTest == 0) {
            Log.e(TAG, " setNetworkOperator mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setNetworkOperator(code);
    }

    public int setRestartTBox(int state) {
        if (this.mForTest == 0) {
            Log.e(TAG, " setRestartTBox mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setRestartTbox(state);
    }

    public int getTBoxState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getTBoxState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getTboxState();
    }

    public int setCloudServieEv(int ev) {
        if (this.mForTest == 0) {
            Log.e(TAG, " setCloudServieEv mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setCloudServieEv(ev);
    }

    public void obdRequest(String id, String data) {
        if (this.mForTest == 0) {
            Log.e(TAG, " obdRequest mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        this.mSdkManager.obdRequest(id, data);
    }

    public void obdRequest(int iviObdOperationType, int ignCycleNum, ArrayList<NetMap> netMapList, boolean mapOpenStatus) {
        if (this.mForTest == 0) {
            Log.e(TAG, " obdRequest mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        if (netMapList == null) {
            Log.e(TAG, " obdRequest netMapList == null ");
        } else {
            this.mSdkManager.obdRequest(iviObdOperationType, ignCycleNum, netMapToTsNetMap(netMapList), mapOpenStatus);
        }
    }

    public int sendDLinkCountryCode(int countryCode) {
        Log.v(TAG, "sendDLinkCountryCode is execute：" + countryCode);
        if (this.mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.e(TAG, "sendDLinkCountryCode adapter is not connect");
            return -2147482648;
        }
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mTsManagerImpl.getStudyAdapterManager().sendDLinkCountryCode(countryCode);
    }

    public int getApn1FailReson() {
        Log.v(TAG, "getApn1FailReson is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getApn1FailReson();
    }

    public String getApn1Address() {
        Log.v(TAG, "getApn1Address is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getApn1Address();
    }

    public String getYunUuidStr() {
        Log.v(TAG, "getYunUuidStr is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.getYunUuidStr();
    }

    private ArrayList<TsNetMap> netMapToTsNetMap(ArrayList<NetMap> netMapList) {
        ArrayList<TsNetMap> netMaps = new ArrayList<>(netMapList.size());
        for (NetMap netMap : netMapList) {
            netMaps.add(new TsNetMap(netMap.getNetSag(), netMap.getIds()));
        }
        return netMaps;
    }

    public int setOffScreenFlag(int type, int flag) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mSdkManager.setOffScreenFlag(type, flag);
    }

    public int queryOffScreenFlag(int type) {
        Log.v(TAG, "setOffScreenFlag is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mSdkManager.queryOffScreenFlag(type);
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

    public boolean setManuallyStationedNetwork(String network, int sigLevel) {
        Log.v(TAG, "setManuallyStationedNetwork is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setManuallyStationedNetwork(network, sigLevel);
    }

    public boolean setAutomaticNetwork() {
        Log.v(TAG, "setAutomaticNetwork is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        return this.mOtaSdkManager.setAutomaticNetwork();
    }

    public boolean reqSimCardState() {
        Log.v(TAG, "reqSimCardState is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_GET);
        return this.mOtaSdkManager.reqSimCardState();
    }

    public void setEnergyTest(int status) {
        Log.v(TAG, "setEnergyTest is execute");
        enforcePermission(BYDAutoTestDevice.PERMISSION_BYDAUTO_TEST_SET);
        this.mSdkManager.setEnergyTest(status);
    }
}
