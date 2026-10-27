package android.hardware.bydauto.tools;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.tools.tsimpl.ToolManagerImpl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDiagnoseToolDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_COMMON = "android.permission.BYDAUTO_ENGINEERMODE_COMMON";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_GET = "android.permission.BYDAUTO_ENGINEERMODE_GET";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_SET = "android.permission.BYDAUTO_ENGINEERMODE_SET";
    private static BYDAutoDiagnoseToolDevice sInstance;
    private final ToolManagerImpl mManagerImpl;

    public BYDAutoDiagnoseToolDevice(Context context) {
        super(context);
        this.mManagerImpl = ToolManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoDiagnoseToolDevice getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BYDAutoDiagnoseToolDevice(context);
        }
        return sInstance;
    }

    public DiagnoseDeviceStatus getA2BState() {
        return this.mManagerImpl.getA2BState();
    }

    public DiagnoseDeviceStatus getADASState() {
        return this.mManagerImpl.getADASState();
    }

    public DiagnoseDeviceStatus getAVMState(int i) {
        return this.mManagerImpl.getAVMState(i);
    }

    public DiagnoseDeviceStatus getAmplifierState() {
        return this.mManagerImpl.getAmplifierState();
    }

    public DiagnoseDeviceStatus getArmrestState() {
        return this.mManagerImpl.getArmrestState();
    }

    public DiagnoseDeviceStatus getCanState() {
        return this.mManagerImpl.getCanState();
    }

    public DiagnoseDeviceStatus getDMSPowerState() {
        return this.mManagerImpl.getDMSPowerState();
    }

    public DiagnoseDeviceStatus getDMSState() {
        return this.mManagerImpl.getDMSState();
    }

    public DiagnoseDeviceStatus getDVRState() {
        return this.mManagerImpl.getDVRState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public boolean getDisplayForwardLinkMarginValue(int i) {
        return this.mManagerImpl.getDisplayForwardLinkMarginValue(i);
    }

    public boolean getDisplayReverseLinkMarginValue(int i) {
        return this.mManagerImpl.getDisplayReverseLinkMarginValue(i);
    }

    public DiagnoseDeviceStatus getFaceIDState() {
        return this.mManagerImpl.getFaceIDState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public DiagnoseDeviceStatus getGNSSState() {
        return this.mManagerImpl.getGNSSState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_ENGINEERMODE_GET;
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

    public DiagnoseDeviceStatus getHUDState() {
        return this.mManagerImpl.getHUDState();
    }

    public DiagnoseDeviceStatus getIMSPowerState() {
        return this.mManagerImpl.getIMSPowerState();
    }

    public DiagnoseDeviceStatus getIMSState() {
        return this.mManagerImpl.getIMSState();
    }

    public DiagnoseDeviceStatus getIVIState() {
        return this.mManagerImpl.getIVIState();
    }

    public DiagnoseDeviceStatus getInstrumentState() {
        return this.mManagerImpl.getInstrumentState();
    }

    public DiagnoseDeviceStatus getMICState(int i) {
        return this.mManagerImpl.getMICState(i);
    }

    public DiagnoseDeviceStatus getOMSPowerState() {
        return this.mManagerImpl.getOMSPowerState();
    }

    public DiagnoseDeviceStatus getOMSState() {
        return this.mManagerImpl.getOMSState();
    }

    public int getPowerOc(int i) {
        return this.mManagerImpl.getPowerOc(i);
    }

    public DiagnoseDeviceStatus getRSEState() {
        return this.mManagerImpl.getRSEState();
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int i) {
        return this.mManagerImpl.getRadioAntennaState(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_ENGINEERMODE_SET;
    }

    public DiagnoseDeviceStatus getSignalIntensityState() {
        return this.mManagerImpl.getSignalIntensityState();
    }

    public DiagnoseDeviceStatus getStreamMediaState() {
        return this.mManagerImpl.getStreamMediaState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public DiagnoseDeviceStatus getUAVState() {
        return this.mManagerImpl.getUAVState();
    }

    public DiagnoseDeviceStatus getUSBCommunicationState() {
        return this.mManagerImpl.getUSBCommunicationState();
    }

    public DiagnoseDeviceStatus getUSBPowerState() {
        return this.mManagerImpl.getUSBPowerState();
    }

    public boolean getUsbConnectStatus(int i) {
        return this.mManagerImpl.getUsbConnectStatus(i);
    }

    public DiagnoseDeviceStatus getWHUDState() {
        return this.mManagerImpl.getWHUDState();
    }

    public void registerListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        this.mManagerImpl.registerListener(absAutoDiagnoseListener);
    }

    public void synchronousQNXData() {
        this.mManagerImpl.synchronousQNXData();
    }

    public void unregisterListener(AbsAutoDiagnoseListener absAutoDiagnoseListener) {
        this.mManagerImpl.unregisterListener(absAutoDiagnoseListener);
    }
}
