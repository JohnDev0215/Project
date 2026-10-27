package android.hardware.bydauto.tools.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.tools.AbsAutoDiagnoseListener;
import android.hardware.bydauto.tools.BYDAutoDiagnoseToolDevice;
import android.hardware.bydauto.tools.DiagnoseDeviceStatus;
import android.os.Bundle;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.lib.engineeringmode.bean.TSDiagnoseDeviceStatus;
import com.ts.lib.engineeringmode.callback.IEngineeringCallBack;
import h6.n;
import java.util.ArrayList;
import java.util.List;

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
    protected static final String TAG = "TS.ToolManagerImpl";
    private static final int TEST_VALUE = 100;
    private static ToolManagerImpl sTsToolManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    EngineeringCallBack mEngineeringCallBack;
    private EngineeringSdkManager mSdkManager;
    private int mForTest = 100;
    List<AbsAutoDiagnoseListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private boolean mNowBind = false;

    public class EngineeringCallBack implements IEngineeringCallBack {
        public EngineeringCallBack() {
        }

        public void isConnected(boolean z6) {
            ToolManagerImpl.this.mNowBind = z6;
            for (int i7 = 0; i7 < ToolManagerImpl.this.mListeners.size(); i7++) {
                if (ToolManagerImpl.this.mListeners.get(i7) != null) {
                    ToolManagerImpl.this.mListeners.get(i7).isConnected(z6);
                }
            }
        }

        public void onHwTemperature(Bundle bundle) {
            for (int i7 = 0; i7 < ToolManagerImpl.this.mListeners.size(); i7++) {
                if (ToolManagerImpl.this.mListeners.get(i7) != null) {
                    ToolManagerImpl.this.mListeners.get(i7).onHWTemperature(bundle);
                }
            }
        }

        public void onQnxLogStatusChanged(int i7, boolean z6) {
        }

        public void onSynchronousQNXData(int i7) {
            for (int i10 = 0; i10 < ToolManagerImpl.this.mListeners.size(); i10++) {
                if (ToolManagerImpl.this.mListeners.get(i10) != null) {
                    ToolManagerImpl.this.mListeners.get(i10).onSynchronousQNXData(i7);
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
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static synchronized ToolManagerImpl getInstance(Context context) {
        try {
            if (sTsToolManagerImpl == null) {
                sTsToolManagerImpl = new ToolManagerImpl(context);
            }
        } catch (Throwable th) {
            throw th;
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
            n.j(TAG, " getA2BState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getA2BState());
    }

    public DiagnoseDeviceStatus getADASState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getADASState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getADASState());
    }

    public DiagnoseDeviceStatus getAUXInState(int i7) {
        TSDiagnoseDeviceStatus auxInMainDriverState = null;
        if (this.mForTest == 0) {
            n.j(TAG, " getAUXInState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        a.o("getAUXInState position :", i7, TAG);
        if (i7 == 1) {
            auxInMainDriverState = this.mSdkManager.getAuxInMainDriverState();
        } else if (i7 == 2) {
            auxInMainDriverState = this.mSdkManager.getAuxInCoPilotState();
        }
        return toBYDBean(auxInMainDriverState);
    }

    public DiagnoseDeviceStatus getAVMState(int i7) {
        TSDiagnoseDeviceStatus avmFirstState = null;
        if (this.mForTest == 0) {
            n.j(TAG, " getAVMState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        a.o("getAVMState portName :", i7, TAG);
        if (i7 == 1) {
            avmFirstState = this.mSdkManager.getAvmFirstState();
        } else if (i7 == 2) {
            avmFirstState = this.mSdkManager.getAvmSecondState();
        } else if (i7 == 3) {
            avmFirstState = this.mSdkManager.getAvmThirdState();
        } else if (i7 == 4) {
            avmFirstState = this.mSdkManager.getAvmFourthState();
        }
        return toBYDBean(avmFirstState);
    }

    public DiagnoseDeviceStatus getAmplifierState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getAmplifierState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getAmplifierState());
    }

    public DiagnoseDeviceStatus getArmrestState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getArmrestState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getArmrestState());
    }

    public DiagnoseDeviceStatus getBTState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getBTState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getBTState());
    }

    public DiagnoseDeviceStatus getCanState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getCanState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getCanState());
    }

    public DiagnoseDeviceStatus getDMSPowerState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getDMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSPowerState());
    }

    public DiagnoseDeviceStatus getDMSState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getDMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSState());
    }

    public DiagnoseDeviceStatus getDVRState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getDVRState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDVRState());
    }

    public DiagnoseDeviceStatus getFaceIDState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getFaceIDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getFaceIDState());
    }

    public DiagnoseDeviceStatus getGNSSState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getGNSSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getGNSSState());
    }

    public DiagnoseDeviceStatus getHIDNetworkLeftState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getHIDNetworkLeftState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHIDNetworkLeftState());
    }

    public DiagnoseDeviceStatus getHIDNetworkRightState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getHIDNetworkRightState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHIDNetworkRightState());
    }

    public DiagnoseDeviceStatus getHUDNetWorkState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getHUDNetWorkState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHUDNetWorkState());
    }

    public DiagnoseDeviceStatus getHUDState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getHUDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHUDState());
    }

    public DiagnoseDeviceStatus getIMSPowerState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getIMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSPowerState());
    }

    public DiagnoseDeviceStatus getIMSState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getIMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSState());
    }

    public DiagnoseDeviceStatus getIVIState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getIVIState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIVIState());
    }

    public DiagnoseDeviceStatus getInstrumentState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getInstrumentState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getInstrumentState());
    }

    public DiagnoseDeviceStatus getMICState(int i7) {
        TSDiagnoseDeviceStatus micLeftFrontState = null;
        if (this.mForTest == 0) {
            n.j(TAG, " getMICState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        a.o("getMICState position :", i7, TAG);
        switch (i7) {
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
            n.j(TAG, " getOMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSPowerState());
    }

    public DiagnoseDeviceStatus getOMSState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getOMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSState());
    }

    public int getPowerOc(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getPowerOc mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getPowerOc(i7);
    }

    public DiagnoseDeviceStatus getRSEState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getRSEState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getRSEState());
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int i7) {
        TSDiagnoseDeviceStatus radioAntennaFirstState = null;
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getRadioAntennaState mForTestValue is "), this.mForTest, " execute return", TAG);
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        a.o("getRadioAntennaState radioType :", i7, TAG);
        if (i7 == 1) {
            radioAntennaFirstState = this.mSdkManager.getRadioAntennaFirstState();
        } else if (i7 == 2) {
            radioAntennaFirstState = this.mSdkManager.getRadioAntennaSecondState();
        }
        return toBYDBean(radioAntennaFirstState);
    }

    public DiagnoseDeviceStatus getSignalIntensityState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getSignalIntensityState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getSignalIntensityState());
    }

    public DiagnoseDeviceStatus getStreamMediaState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getStreamMediaState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getStreamMediaState());
    }

    public DiagnoseDeviceStatus getUAVState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getUAVState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUAVState());
    }

    public DiagnoseDeviceStatus getUSBCommunicationState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getUSBCommunicationState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBCommunicationState());
    }

    public DiagnoseDeviceStatus getUSBPowerState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getUSBPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBPowerState());
    }

    public boolean getUsbConnectStatus(int i7) {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getUsbConnectStatus mForTestValue is "), this.mForTest, " execute return", TAG);
            return false;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getUsbConnectStatus(i7);
    }

    public DiagnoseDeviceStatus getWHUDState() {
        if (this.mForTest == 0) {
            a.v(new StringBuilder(" getWHUDState mForTestValue is "), this.mForTest, " execute return", TAG);
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getWhudState());
    }

    public DiagnoseDeviceStatus getWifiState() {
        if (this.mForTest == 0) {
            n.j(TAG, " getWifiState mForTestValue is 0 execute return");
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
            n.j(TAG, "  synchronousQNXData mForTestValue is 0 execute return");
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
