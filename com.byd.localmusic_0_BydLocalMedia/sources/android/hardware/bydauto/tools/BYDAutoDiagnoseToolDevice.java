package android.hardware.bydauto.tools;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.tools.tsimpl.ToolManagerImpl;

/* JADX INFO: loaded from: classes2.dex */
public final class BYDAutoDiagnoseToolDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_COMMON = "android.permission.BYDAUTO_ENGINEERMODE_COMMON";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_GET = "android.permission.BYDAUTO_ENGINEERMODE_GET";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_SET = "android.permission.BYDAUTO_ENGINEERMODE_SET";
    private static BYDAutoDiagnoseToolDevice sInstance;
    private final ToolManagerImpl mManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "android.permission.BYDAUTO_ENGINEERMODE_GET";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_ENGINEERMODE_SET";
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    BYDAutoDiagnoseToolDevice(Context context) {
        super(context);
        this.mManagerImpl = ToolManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoDiagnoseToolDevice getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BYDAutoDiagnoseToolDevice(context);
        }
        return sInstance;
    }

    public void registerListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        this.mManagerImpl.registerListener(absAutoDiagnoseListener);
    }

    public void unregisterListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        this.mManagerImpl.unregisterListener(absAutoDiagnoseListener);
    }

    public void synchronousQNXData() {
        this.mManagerImpl.synchronousQNXData();
    }

    public DiagnoseDeviceStatus getDVRState() {
        return this.mManagerImpl.getDVRState();
    }

    public DiagnoseDeviceStatus getDMSState() {
        return this.mManagerImpl.getDMSState();
    }

    public DiagnoseDeviceStatus getOMSState() {
        return this.mManagerImpl.getOMSState();
    }

    public DiagnoseDeviceStatus getIMSState() {
        return this.mManagerImpl.getIMSState();
    }

    public DiagnoseDeviceStatus getFaceIDState() {
        return this.mManagerImpl.getFaceIDState();
    }

    public DiagnoseDeviceStatus getAVMState(int i) {
        return this.mManagerImpl.getAVMState(i);
    }

    public DiagnoseDeviceStatus getInstrumentState() {
        return this.mManagerImpl.getInstrumentState();
    }

    public DiagnoseDeviceStatus getIVIState() {
        return this.mManagerImpl.getIVIState();
    }

    public DiagnoseDeviceStatus getArmrestState() {
        return this.mManagerImpl.getArmrestState();
    }

    public DiagnoseDeviceStatus getRSEState() {
        return this.mManagerImpl.getRSEState();
    }

    public DiagnoseDeviceStatus getStreamMediaState() {
        return this.mManagerImpl.getStreamMediaState();
    }

    public DiagnoseDeviceStatus getHUDState() {
        return this.mManagerImpl.getHUDState();
    }

    public DiagnoseDeviceStatus getDMSPowerState() {
        return this.mManagerImpl.getDMSPowerState();
    }

    public DiagnoseDeviceStatus getOMSPowerState() {
        return this.mManagerImpl.getOMSPowerState();
    }

    public DiagnoseDeviceStatus getIMSPowerState() {
        return this.mManagerImpl.getIMSPowerState();
    }

    public DiagnoseDeviceStatus getMICState(int i) {
        return this.mManagerImpl.getMICState(i);
    }

    public DiagnoseDeviceStatus getA2BState() {
        return this.mManagerImpl.getA2BState();
    }

    public DiagnoseDeviceStatus getAmplifierState() {
        return this.mManagerImpl.getAmplifierState();
    }

    public DiagnoseDeviceStatus getGNSSState() {
        return this.mManagerImpl.getGNSSState();
    }

    public DiagnoseDeviceStatus getCanState() {
        return this.mManagerImpl.getCanState();
    }

    public DiagnoseDeviceStatus getUSBPowerState() {
        return this.mManagerImpl.getUSBPowerState();
    }

    public DiagnoseDeviceStatus getUSBCommunicationState() {
        return this.mManagerImpl.getUSBCommunicationState();
    }

    public DiagnoseDeviceStatus getADASState() {
        return this.mManagerImpl.getADASState();
    }

    public DiagnoseDeviceStatus getHIDNetworkLeftState() {
        return this.mManagerImpl.getHIDNetworkLeftState();
    }

    public DiagnoseDeviceStatus getHIDNetworkRightState() {
        return this.mManagerImpl.getHIDNetworkRightState();
    }

    public DiagnoseDeviceStatus getHUDNetWorkState() {
        return this.mManagerImpl.getHUDNetWorkState();
    }

    public DiagnoseDeviceStatus getSignalIntensityState() {
        return this.mManagerImpl.getSignalIntensityState();
    }

    public DiagnoseDeviceStatus getUAVState() {
        return this.mManagerImpl.getUAVState();
    }

    public boolean getUsbConnectStatus(int i) {
        return this.mManagerImpl.getUsbConnectStatus(i);
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int i) {
        return this.mManagerImpl.getRadioAntennaState(i);
    }

    public DiagnoseDeviceStatus getWHUDState() {
        return this.mManagerImpl.getWHUDState();
    }

    public int getPowerOc(int i) {
        return this.mManagerImpl.getPowerOc(i);
    }

    public boolean getDisplayReverseLinkMarginValue(int i) {
        return this.mManagerImpl.getDisplayReverseLinkMarginValue(i);
    }

    public boolean getDisplayForwardLinkMarginValue(int i) {
        return this.mManagerImpl.getDisplayForwardLinkMarginValue(i);
    }
}
