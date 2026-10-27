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
    private static ToolManagerImpl sTsToolManagerImpl = null;
    private ContentResolver mContentResolver;
    private Context mContext;
    EngineeringCallBack mEngineeringCallBack;
    private EngineeringSdkManager mSdkManager;
    private int mForTest = 100;
    List<AbsAutoDiagnoseListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private boolean mNowBind = false;

    public ToolManagerImpl(Context con) {
        this.mContext = null;
        this.mContentResolver = con.getContentResolver();
        this.mContext = con;
        this.mSdkManager = EngineeringSdkManager.getInstance(con);
        EngineeringCallBack engineeringCallBack = new EngineeringCallBack();
        this.mEngineeringCallBack = engineeringCallBack;
        this.mSdkManager.registerListener(engineeringCallBack);
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    class EngineeringCallBack implements IEngineeringCallBack {
        EngineeringCallBack() {
        }

        public void onSynchronousQNXData(int state) {
            for (int i = 0; i < ToolManagerImpl.this.mListeners.size(); i++) {
                if (ToolManagerImpl.this.mListeners.get(i) != null) {
                    ToolManagerImpl.this.mListeners.get(i).onSynchronousQNXData(state);
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

        public void onQnxLogStatusChanged(int logType, boolean status) {
        }

        public void isConnected(boolean isBinding) {
            ToolManagerImpl.this.mNowBind = isBinding;
            for (int i = 0; i < ToolManagerImpl.this.mListeners.size(); i++) {
                if (ToolManagerImpl.this.mListeners.get(i) != null) {
                    ToolManagerImpl.this.mListeners.get(i).isConnected(isBinding);
                }
            }
        }
    }

    public static synchronized ToolManagerImpl getInstance(Context con) {
        if (sTsToolManagerImpl == null) {
            sTsToolManagerImpl = new ToolManagerImpl(con);
        }
        return sTsToolManagerImpl;
    }

    public void registerListener(AbsAutoDiagnoseListener listener) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
        if (listener != null && !this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
            if (this.mNowBind) {
                listener.isConnected(true);
            }
        }
    }

    public void unregisterListener(AbsAutoDiagnoseListener listener) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_COMMON);
        if (listener != null && this.mListeners.contains(listener)) {
            this.mListeners.remove(listener);
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

    public DiagnoseDeviceStatus getDVRState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDVRState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDVRState());
    }

    public DiagnoseDeviceStatus getDMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSState());
    }

    public DiagnoseDeviceStatus getOMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getOMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSState());
    }

    public DiagnoseDeviceStatus getIMSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIMSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSState());
    }

    public DiagnoseDeviceStatus getFaceIDState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getFaceIDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getFaceIDState());
    }

    public DiagnoseDeviceStatus getAVMState(int portName) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAVMState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getAVMState portName :" + portName);
        TSDiagnoseDeviceStatus ts = null;
        if (portName == 1) {
            ts = this.mSdkManager.getAvmFirstState();
        } else if (portName == 2) {
            ts = this.mSdkManager.getAvmSecondState();
        } else if (portName == 3) {
            ts = this.mSdkManager.getAvmThirdState();
        } else if (portName == 4) {
            ts = this.mSdkManager.getAvmFourthState();
        }
        return toBYDBean(ts);
    }

    public DiagnoseDeviceStatus getInstrumentState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getInstrumentState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getInstrumentState());
    }

    public DiagnoseDeviceStatus getIVIState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIVIState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIVIState());
    }

    public DiagnoseDeviceStatus getArmrestState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getArmrestState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getArmrestState());
    }

    public DiagnoseDeviceStatus getRSEState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRSEState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getRSEState());
    }

    public DiagnoseDeviceStatus getStreamMediaState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getStreamMediaState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getStreamMediaState());
    }

    public DiagnoseDeviceStatus getHUDState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHUDState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getHUDState());
    }

    public DiagnoseDeviceStatus getDMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getDMSPowerState());
    }

    public DiagnoseDeviceStatus getOMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getOMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getOMSPowerState());
    }

    public DiagnoseDeviceStatus getIMSPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIMSPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getIMSPowerState());
    }

    public DiagnoseDeviceStatus getMICState(int position) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getMICState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getMICState position :" + position);
        TSDiagnoseDeviceStatus ts = null;
        switch (position) {
            case 1:
                ts = this.mSdkManager.getMicLeftFrontState();
                break;
            case 2:
                ts = this.mSdkManager.getMicRightFrontState();
                break;
            case 3:
                ts = this.mSdkManager.getMicLeftMiddleState();
                break;
            case 4:
                ts = this.mSdkManager.getMicRightMiddleState();
                break;
            case 5:
                ts = this.mSdkManager.getMicLeftRearState();
                break;
            case 6:
                ts = this.mSdkManager.getMicRightRearState();
                break;
        }
        return toBYDBean(ts);
    }

    public DiagnoseDeviceStatus getAUXInState(int position) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAUXInState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getAUXInState position :" + position);
        TSDiagnoseDeviceStatus ts = null;
        if (position == 1) {
            ts = this.mSdkManager.getAuxInMainDriverState();
        } else if (position == 2) {
            ts = this.mSdkManager.getAuxInCoPilotState();
        }
        return toBYDBean(ts);
    }

    public DiagnoseDeviceStatus getA2BState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getA2BState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getA2BState());
    }

    public DiagnoseDeviceStatus getAmplifierState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAmplifierState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getAmplifierState());
    }

    public DiagnoseDeviceStatus getWifiState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getWifiState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getWifiState());
    }

    public DiagnoseDeviceStatus getBTState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getBTState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getBTState());
    }

    public DiagnoseDeviceStatus getGNSSState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getGNSSState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getGNSSState());
    }

    public DiagnoseDeviceStatus getCanState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getCanState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getCanState());
    }

    public DiagnoseDeviceStatus getUSBPowerState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUSBPowerState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBPowerState());
    }

    public DiagnoseDeviceStatus getUSBCommunicationState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUSBCommunicationState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUSBCommunicationState());
    }

    public DiagnoseDeviceStatus getADASState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getADASState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getADASState());
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

    public DiagnoseDeviceStatus getSignalIntensityState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSignalIntensityState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getSignalIntensityState());
    }

    public DiagnoseDeviceStatus getUAVState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUAVState mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getUAVState());
    }

    private DiagnoseDeviceStatus toBYDBean(TSDiagnoseDeviceStatus bean) {
        if (bean == null) {
            return null;
        }
        DiagnoseDeviceStatus diagnoseDeviceStatus = new DiagnoseDeviceStatus();
        diagnoseDeviceStatus.setSHORT_OUT(bean.isShortOut());
        diagnoseDeviceStatus.setCONNECTION(bean.isConnection());
        diagnoseDeviceStatus.setCUTTING_OUT(bean.isCuttingOut());
        diagnoseDeviceStatus.setSUPPLIER_NAME(bean.getSupplierName());
        return diagnoseDeviceStatus;
    }

    public boolean getUsbConnectStatus(int portName) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getUsbConnectStatus mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getUsbConnectStatus(portName);
    }

    public DiagnoseDeviceStatus getWHUDState() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getWHUDState mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return toBYDBean(this.mSdkManager.getWhudState());
    }

    public int getPowerOc(int portName) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getPowerOc mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getPowerOc(portName);
    }

    public boolean getDisplayReverseLinkMarginValue(int displayPanelId) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getDisplayReverseLinkMarginValue(displayPanelId);
    }

    public boolean getDisplayForwardLinkMarginValue(int displayPanelId) {
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        return this.mSdkManager.getDisplayForwardLinkMarginValue(displayPanelId);
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int radioType) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRadioAntennaState mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(BYDAutoDiagnoseToolDevice.PERMISSION_BYDAUTO_ENGINEERMODE_GET);
        Log.d(TAG, "getRadioAntennaState radioType :" + radioType);
        TSDiagnoseDeviceStatus ts = null;
        if (radioType == 1) {
            ts = this.mSdkManager.getRadioAntennaFirstState();
        } else if (radioType == 2) {
            ts = this.mSdkManager.getRadioAntennaSecondState();
        }
        return toBYDBean(ts);
    }
}
