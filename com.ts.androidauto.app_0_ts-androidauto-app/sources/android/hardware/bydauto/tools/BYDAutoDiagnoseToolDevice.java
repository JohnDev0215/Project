package android.hardware.bydauto.tools;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.tools.tsimpl.ToolManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDiagnoseToolDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_COMMON = "android.permission.BYDAUTO_ENGINEERMODE_COMMON";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_GET = "android.permission.BYDAUTO_ENGINEERMODE_GET";
    public static final String PERMISSION_BYDAUTO_ENGINEERMODE_SET = "android.permission.BYDAUTO_ENGINEERMODE_SET";
    private static BYDAutoDiagnoseToolDevice sInstance;
    private final ToolManagerImpl mManagerImpl;

    BYDAutoDiagnoseToolDevice(Context con) {
        super(con);
        this.mManagerImpl = ToolManagerImpl.getInstance(con);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_ENGINEERMODE_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_ENGINEERMODE_GET;
    }

    public static synchronized BYDAutoDiagnoseToolDevice getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new BYDAutoDiagnoseToolDevice(con);
        }
        return sInstance;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsAutoDiagnoseListener listener) {
        this.mManagerImpl.registerListener(listener);
    }

    public void unregisterListener(AbsAutoDiagnoseListener listener) {
        this.mManagerImpl.unregisterListener(listener);
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

    public DiagnoseDeviceStatus getAVMState(int portName) {
        return this.mManagerImpl.getAVMState(portName);
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

    public DiagnoseDeviceStatus getMICState(int position) {
        return this.mManagerImpl.getMICState(position);
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

    public boolean getUsbConnectStatus(int portName) {
        return this.mManagerImpl.getUsbConnectStatus(portName);
    }

    public DiagnoseDeviceStatus getRadioAntennaState(int radioType) {
        return this.mManagerImpl.getRadioAntennaState(radioType);
    }

    public DiagnoseDeviceStatus getWHUDState() {
        return this.mManagerImpl.getWHUDState();
    }

    public int getPowerOc(int portName) {
        return this.mManagerImpl.getPowerOc(portName);
    }

    public boolean getDisplayReverseLinkMarginValue(int displayPanelId) {
        return this.mManagerImpl.getDisplayReverseLinkMarginValue(displayPanelId);
    }

    public boolean getDisplayForwardLinkMarginValue(int displayPanelId) {
        return this.mManagerImpl.getDisplayForwardLinkMarginValue(displayPanelId);
    }
}
