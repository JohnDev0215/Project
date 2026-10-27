package android.hardware.bydauto.tools.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.tools.AbsAutoDiagnoseListener;
import android.hardware.bydauto.tools.BYDAutoDiagnoseToolDevice;
import android.hardware.bydauto.tools.DiagnoseDeviceStatus;
import android.os.Bundle;
import android.util.Log;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.lib.engineeringmode.bean.TSDiagnoseDeviceStatus;
import com.ts.lib.engineeringmode.callback.IEngineeringCallBack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ToolManagerImpl {
    private static final int AM_FM1 = 1;
    private static final int AM_FM2 = 2;
    private static final int AUX_IN_CO_PILOT = 2;
    private static final int AUX_IN_MAIN_DRIVER = 1;
    private static final int AVM1 = 1;
    private static final int AVM2 = 2;
    private static final int AVM3 = 3;
    private static final int AVM4 = 4;
    private static final int MIC_LEFT_FRONT = 1;
    private static final int MIC_LEFT_MIDDLE = 3;
    private static final int MIC_LEFT_REAR = 5;
    private static final int MIC_RIGHT_FRONT = 2;
    private static final int MIC_RIGHT_MIDDLE = 4;
    private static final int MIC_RIGHT_REAR = 6;
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_TS.ToolManagerImpl";
    public static final String TAG = "TS.ToolManagerImpl";
    private static final int TEST_VALUE = 100;
    private static ToolManagerImpl sTsToolManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    public EngineeringCallBack mEngineeringCallBack;
    private EngineeringSdkManager mSdkManager;
    private int mForTest = 100;
    public List<AbsAutoDiagnoseListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private boolean mNowBind = false;

    /* JADX INFO: compiled from: caradapter-bug */
    public class EngineeringCallBack implements IEngineeringCallBack {
        public EngineeringCallBack() {
        }

        public void isConnected(boolean z) {
            ToolManagerImpl.this.mNowBind = z;
            for (int i = 0; i < ToolManagerImpl.this.mListeners.size(); i++) {
                if (ToolManagerImpl.this.mListeners.get(i) != null) {
                    ToolManagerImpl.this.mListeners.get(i).isConnected(z);
                }
            }
        }

        public void onHwTemperature(Bundle bundle) {
            for (int i = 0; i < ToolManagerImpl.this.mListeners.size(); i++) {
                if (ToolManagerImpl.this.mListeners.get(i) != null) {
                    ToolManagerImpl.this.mListeners.get(i).onHWTemperature(bundle);
                }
            }
        }

        public void onQnxLogStatusChanged(int i, boolean z) {
        }

        public void onSynchronousQNXData(int i) {
            for (int i2 = 0; i2 < ToolManagerImpl.this.mListeners.size(); i2++) {
                if (ToolManagerImpl.this.mListeners.get(i2) != null) {
                    ToolManagerImpl.this.mListeners.get(i2).onSynchronousQNXData(i);
                }
            }
        }
    }

    public ToolManagerImpl(Context context) {
        this.mContext = null;
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        this.mSdkManager = EngineeringSdkManager.getInstance(context);
        EngineeringCallBack engineeringCallBack = new EngineeringCallBack();
        this.mEngineeringCallBack = engineeringCallBack;
        this.mSdkManager.registerListener(engineeringCallBack);
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static synchronized ToolManagerImpl getInstance(Context context) {
        if (sTsToolManagerImpl == null) {
            sTsToolManagerImpl = new ToolManagerImpl(context);
        }
        return sTsToolManagerImpl;
    }

    private DiagnoseDeviceStatus toBYDBean(TSDiagnoseDeviceStatus tSDiagnoseDeviceStatus) {
        if (tSDiagnoseDeviceStatus == null) {
            return null;
        }
        DiagnoseDeviceStatus diagnoseDeviceStatus = new DiagnoseDeviceStatus();
        diagnoseDeviceStatus.setSHORT_OUT(tSDiagnoseDeviceStatus.isShortOut());
        diagnoseDeviceStatus.setCONNECTION(tSDiagnoseDeviceStatus.isConnection());
        diagnoseDeviceStatus.setCUTTING_OUT(tSDiagnoseDeviceStatus.isCuttingOut());
        diagnoseDeviceStatus.setSUPPLIER_NAME(tSDiagnoseDeviceStatus.getSupplierName());
        return diagnoseDeviceStatus;
    }

    public DiagnoseDeviceStatus getA2BState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getA2BState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getA2BState());
    }

    public DiagnoseDeviceStatus getADASState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getADASState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getADASState());
    }

    public DiagnoseDeviceStatus getAUXInState(int i) {
        TSDiagnoseDeviceStatus auxInMainDriverState = null;
        if (this.mForTest == 0) {
            Log.e(TAG, " getAUXInState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getAUXInState position :" + i);
        if (i == 1) {
            auxInMainDriverState = this.mSdkManager.getAuxInMainDriverState();
        } else if (i == 2) {
            auxInMainDriverState = this.mSdkManager.getAuxInCoPilotState();
        }
        return toBYDBean(auxInMainDriverState);
    }

    public DiagnoseDeviceStatus getAVMState(int i) {
        TSDiagnoseDeviceStatus avmFirstState = null;
        if (this.mForTest == 0) {
            Log.e(TAG, " getAVMState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getAVMState portName :" + i);
        if (i == 1) {
            avmFirstState = this.mSdkManager.getAvmFirstState();
        } else if (i == 2) {
            avmFirstState = this.mSdkManager.getAvmSecondState();
        } else if (i == 3) {
            avmFirstState = this.mSdkManager.getAvmThirdState();
        } else if (i == 4) {
            avmFirstState = this.mSdkManager.getAvmFourthState();
        }
        return toBYDBean(avmFirstState);
    }

    public DiagnoseDeviceStatus getAmplifierState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAmplifierState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getAmplifierState());
    }

    public DiagnoseDeviceStatus getArmrestState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getArmrestState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getArmrestState());
    }

    public DiagnoseDeviceStatus getBTState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getBTState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getBTState());
    }

    public DiagnoseDeviceStatus getCanState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getCanState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getCanState());
    }

    public DiagnoseDeviceStatus getDMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSPowerState());
    }

    public DiagnoseDeviceStatus getDMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSState());
    }

    public DiagnoseDeviceStatus getDVRState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDVRState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDVRState());
    }

    public boolean getDisplayForwardLinkMarginValue(int i) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getDisplayForwardLinkMarginValue(i);
    }

    public boolean getDisplayReverseLinkMarginValue(int i) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getDisplayReverseLinkMarginValue(i);
    }

    public DiagnoseDeviceStatus getFaceIDState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getFaceIDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getFaceIDState());
    }

    public DiagnoseDeviceStatus getGNSSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getGNSSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getGNSSState());
    }

    public DiagnoseDeviceStatus getHIDNetworkLeftState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHIDNetworkLeftState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHIDNetworkLeftState());
    }

    public DiagnoseDeviceStatus getHIDNetworkRightState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHIDNetworkRightState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHIDNetworkRightState());
    }

    public DiagnoseDeviceStatus getHUDNetWorkState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHUDNetWorkState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHUDNetWorkState());
    }

    public DiagnoseDeviceStatus getHUDState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHUDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHUDState());
    }

    public DiagnoseDeviceStatus getIMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSPowerState());
    }

    public DiagnoseDeviceStatus getIMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSState());
    }

    public DiagnoseDeviceStatus getIVIState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIVIState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIVIState());
    }

    public DiagnoseDeviceStatus getInstrumentState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getInstrumentState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getInstrumentState());
    }

    public DiagnoseDeviceStatus getMICState(int i) {
        TSDiagnoseDeviceStatus micLeftFrontState = null;
        if (this.mForTest == 0) {
            Log.e(TAG, " getMICState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getMICState position :" + i);
        switch (i) {
            case 1:
                micLeftFrontState = this.mSdkManager.getMicLeftFrontState();
                break;
            case 2:
                micLeftFrontState = this.mSdkManager.getMicRightFrontState();
                break;
            case 3:
                micLeftFrontState = this.mSdkManager.getMicLeftMiddleState();
                break;
            case 4:
                micLeftFrontState = this.mSdkManager.getMicRightMiddleState();
                break;
            case 5:
                micLeftFrontState = this.mSdkManager.getMicLeftRearState();
                break;
            case 6:
                micLeftFrontState = this.mSdkManager.getMicRightRearState();
                break;
        }
        return toBYDBean(micLeftFrontState);
    }

    public DiagnoseDeviceStatus getOMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getOMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSPowerState());
    }

    public DiagnoseDeviceStatus getOMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getOMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSState());
    }

    public int getPowerOc(int i) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
            return this.mSdkManager.getPowerOc(i);
        }
        Log.e(TAG, " getPowerOc mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public DiagnoseDeviceStatus getRSEState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRSEState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getRSEState());
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int i) {
        TSDiagnoseDeviceStatus radioAntennaFirstState = null;
        if (this.mForTest == 0) {
            Log.e(TAG, " getRadioAntennaState mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getRadioAntennaState radioType :" + i);
        if (i == 1) {
            radioAntennaFirstState = this.mSdkManager.getRadioAntennaFirstState();
        } else if (i == 2) {
            radioAntennaFirstState = this.mSdkManager.getRadioAntennaSecondState();
        }
        return toBYDBean(radioAntennaFirstState);
    }

    public DiagnoseDeviceStatus getSignalIntensityState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSignalIntensityState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getSignalIntensityState());
    }

    public DiagnoseDeviceStatus getStreamMediaState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getStreamMediaState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getStreamMediaState());
    }

    public DiagnoseDeviceStatus getUAVState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUAVState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUAVState());
    }

    public DiagnoseDeviceStatus getUSBCommunicationState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUSBCommunicationState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBCommunicationState());
    }

    public DiagnoseDeviceStatus getUSBPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUSBPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBPowerState());
    }

    public boolean getUsbConnectStatus(int i) {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
            return this.mSdkManager.getUsbConnectStatus(i);
        }
        Log.e(TAG, " getUsbConnectStatus mForTestValue is " + this.mForTest + " execute return");
        return false;
    }

    public DiagnoseDeviceStatus getWHUDState() {
        if (this.mForTest != 0) {
            enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
            return toBYDBean(this.mSdkManager.getWhudState());
        }
        Log.e(TAG, " getWHUDState mForTestValue is " + this.mForTest + " execute return");
        return null;
    }

    public DiagnoseDeviceStatus getWifiState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getWifiState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getWifiState());
    }

    public void registerListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
        if (absAutoDiagnoseListener == null || this.mListeners.contains(absAutoDiagnoseListener)) {
            return;
        }
        this.mListeners.add(absAutoDiagnoseListener);
        if (this.mNowBind) {
            absAutoDiagnoseListener.isConnected(true);
        }
    }

    public void synchronousQNXData() {
        if (this.mForTest == 0) {
            Log.e(TAG, "  synchronousQNXData mForTestValue is 0 execute return");
        } else {
            enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_SET);
            this.mSdkManager.synchronousQNXData();
        }
    }

    public void unregisterListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
        if (absAutoDiagnoseListener == null || !this.mListeners.contains(absAutoDiagnoseListener)) {
            return;
        }
        this.mListeners.remove(absAutoDiagnoseListener);
    }
}
