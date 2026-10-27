package android.hardware.hdmi;

import android.hardware.hdmi.IHdmiControlService;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class HdmiControlServiceWrapper {
    public static final int DEVICE_PURE_CEC_SWITCH = 6;
    private List<HdmiPortInfo> mInfoList = null;
    private int[] mTypes = null;
    private final IHdmiControlService mInterface = new IHdmiControlService.Stub() { // from class: android.hardware.hdmi.HdmiControlServiceWrapper.1
        @Override // android.hardware.hdmi.IHdmiControlService
        public int[] getSupportedTypes() {
            return HdmiControlServiceWrapper.this.getSupportedTypes();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public HdmiDeviceInfo getActiveSource() {
            return HdmiControlServiceWrapper.this.getActiveSource();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void oneTouchPlay(IHdmiControlCallback iHdmiControlCallback) {
            HdmiControlServiceWrapper.this.oneTouchPlay(iHdmiControlCallback);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void queryDisplayStatus(IHdmiControlCallback iHdmiControlCallback) {
            HdmiControlServiceWrapper.this.queryDisplayStatus(iHdmiControlCallback);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener) {
            HdmiControlServiceWrapper.this.addHdmiControlStatusChangeListener(iHdmiControlStatusChangeListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener) {
            HdmiControlServiceWrapper.this.removeHdmiControlStatusChangeListener(iHdmiControlStatusChangeListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addHotplugEventListener(IHdmiHotplugEventListener iHdmiHotplugEventListener) {
            HdmiControlServiceWrapper.this.addHotplugEventListener(iHdmiHotplugEventListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void removeHotplugEventListener(IHdmiHotplugEventListener iHdmiHotplugEventListener) {
            HdmiControlServiceWrapper.this.removeHotplugEventListener(iHdmiHotplugEventListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addDeviceEventListener(IHdmiDeviceEventListener iHdmiDeviceEventListener) {
            HdmiControlServiceWrapper.this.addDeviceEventListener(iHdmiDeviceEventListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void deviceSelect(int i, IHdmiControlCallback iHdmiControlCallback) {
            HdmiControlServiceWrapper.this.deviceSelect(i, iHdmiControlCallback);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void portSelect(int i, IHdmiControlCallback iHdmiControlCallback) {
            HdmiControlServiceWrapper.this.portSelect(i, iHdmiControlCallback);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void sendKeyEvent(int i, int i2, boolean z) {
            HdmiControlServiceWrapper.this.sendKeyEvent(i, i2, z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void sendVolumeKeyEvent(int i, int i2, boolean z) {
            HdmiControlServiceWrapper.this.sendVolumeKeyEvent(i, i2, z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public List<HdmiPortInfo> getPortInfo() {
            return HdmiControlServiceWrapper.this.getPortInfo();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public boolean canChangeSystemAudioMode() {
            return HdmiControlServiceWrapper.this.canChangeSystemAudioMode();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public boolean getSystemAudioMode() {
            return HdmiControlServiceWrapper.this.getSystemAudioMode();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public int getPhysicalAddress() {
            return HdmiControlServiceWrapper.this.getPhysicalAddress();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setSystemAudioMode(boolean z, IHdmiControlCallback iHdmiControlCallback) {
            HdmiControlServiceWrapper.this.setSystemAudioMode(z, iHdmiControlCallback);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener iHdmiSystemAudioModeChangeListener) {
            HdmiControlServiceWrapper.this.addSystemAudioModeChangeListener(iHdmiSystemAudioModeChangeListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener iHdmiSystemAudioModeChangeListener) {
            HdmiControlServiceWrapper.this.removeSystemAudioModeChangeListener(iHdmiSystemAudioModeChangeListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setArcMode(boolean z) {
            HdmiControlServiceWrapper.this.setArcMode(z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setProhibitMode(boolean z) {
            HdmiControlServiceWrapper.this.setProhibitMode(z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setSystemAudioVolume(int i, int i2, int i3) {
            HdmiControlServiceWrapper.this.setSystemAudioVolume(i, i2, i3);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setSystemAudioMute(boolean z) {
            HdmiControlServiceWrapper.this.setSystemAudioMute(z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setInputChangeListener(IHdmiInputChangeListener iHdmiInputChangeListener) {
            HdmiControlServiceWrapper.this.setInputChangeListener(iHdmiInputChangeListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public List<HdmiDeviceInfo> getInputDevices() {
            return HdmiControlServiceWrapper.this.getInputDevices();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public List<HdmiDeviceInfo> getDeviceList() {
            return HdmiControlServiceWrapper.this.getDeviceList();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void powerOffRemoteDevice(int i, int i2) {
            HdmiControlServiceWrapper.this.powerOffRemoteDevice(i, i2);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void powerOnRemoteDevice(int i, int i2) {
            HdmiControlServiceWrapper.this.powerOnRemoteDevice(i, i2);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void askRemoteDeviceToBecomeActiveSource(int i) {
            HdmiControlServiceWrapper.this.askRemoteDeviceToBecomeActiveSource(i);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void sendVendorCommand(int i, int i2, byte[] bArr, boolean z) {
            HdmiControlServiceWrapper.this.sendVendorCommand(i, i2, bArr, z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addVendorCommandListener(IHdmiVendorCommandListener iHdmiVendorCommandListener, int i) {
            HdmiControlServiceWrapper.this.addVendorCommandListener(iHdmiVendorCommandListener, i);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void sendStandby(int i, int i2) {
            HdmiControlServiceWrapper.this.sendStandby(i, i2);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setHdmiRecordListener(IHdmiRecordListener iHdmiRecordListener) {
            HdmiControlServiceWrapper.this.setHdmiRecordListener(iHdmiRecordListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void startOneTouchRecord(int i, byte[] bArr) {
            HdmiControlServiceWrapper.this.startOneTouchRecord(i, bArr);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void stopOneTouchRecord(int i) {
            HdmiControlServiceWrapper.this.stopOneTouchRecord(i);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void startTimerRecording(int i, int i2, byte[] bArr) {
            HdmiControlServiceWrapper.this.startTimerRecording(i, i2, bArr);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void clearTimerRecording(int i, int i2, byte[] bArr) {
            HdmiControlServiceWrapper.this.clearTimerRecording(i, i2, bArr);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void sendMhlVendorCommand(int i, int i2, int i3, byte[] bArr) {
            HdmiControlServiceWrapper.this.sendMhlVendorCommand(i, i2, i3, bArr);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener iHdmiMhlVendorCommandListener) {
            HdmiControlServiceWrapper.this.addHdmiMhlVendorCommandListener(iHdmiMhlVendorCommandListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setStandbyMode(boolean z) {
            HdmiControlServiceWrapper.this.setStandbyMode(z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setHdmiCecVolumeControlEnabled(boolean z) {
            HdmiControlServiceWrapper.this.setHdmiCecVolumeControlEnabled(z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public boolean isHdmiCecVolumeControlEnabled() {
            return HdmiControlServiceWrapper.this.isHdmiCecVolumeControlEnabled();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void reportAudioStatus(int i, int i2, int i3, boolean z) {
            HdmiControlServiceWrapper.this.reportAudioStatus(i, i2, i3, z);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void setSystemAudioModeOnForAudioOnlySource() {
            HdmiControlServiceWrapper.this.setSystemAudioModeOnForAudioOnlySource();
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener) {
            HdmiControlServiceWrapper.this.addHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListener);
        }

        @Override // android.hardware.hdmi.IHdmiControlService
        public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener) {
            HdmiControlServiceWrapper.this.removeHdmiCecVolumeControlFeatureListener(iHdmiCecVolumeControlFeatureListener);
        }
    };

    public void addDeviceEventListener(IHdmiDeviceEventListener iHdmiDeviceEventListener) {
    }

    public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener) {
    }

    public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener) {
    }

    public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener iHdmiMhlVendorCommandListener) {
    }

    public void addHotplugEventListener(IHdmiHotplugEventListener iHdmiHotplugEventListener) {
    }

    public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener iHdmiSystemAudioModeChangeListener) {
    }

    public void addVendorCommandListener(IHdmiVendorCommandListener iHdmiVendorCommandListener, int i) {
    }

    public void askRemoteDeviceToBecomeActiveSource(int i) {
    }

    public boolean canChangeSystemAudioMode() {
        return true;
    }

    public void clearTimerRecording(int i, int i2, byte[] bArr) {
    }

    public void deviceSelect(int i, IHdmiControlCallback iHdmiControlCallback) {
    }

    public HdmiDeviceInfo getActiveSource() {
        return null;
    }

    public List<HdmiDeviceInfo> getDeviceList() {
        return null;
    }

    public List<HdmiDeviceInfo> getInputDevices() {
        return null;
    }

    public int getPhysicalAddress() {
        return 65535;
    }

    public boolean getSystemAudioMode() {
        return true;
    }

    public boolean isHdmiCecVolumeControlEnabled() {
        return true;
    }

    public void oneTouchPlay(IHdmiControlCallback iHdmiControlCallback) {
    }

    public void portSelect(int i, IHdmiControlCallback iHdmiControlCallback) {
    }

    public void powerOffRemoteDevice(int i, int i2) {
    }

    public void powerOnRemoteDevice(int i, int i2) {
    }

    public void queryDisplayStatus(IHdmiControlCallback iHdmiControlCallback) {
    }

    public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener iHdmiCecVolumeControlFeatureListener) {
    }

    public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener iHdmiControlStatusChangeListener) {
    }

    public void removeHotplugEventListener(IHdmiHotplugEventListener iHdmiHotplugEventListener) {
    }

    public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener iHdmiSystemAudioModeChangeListener) {
    }

    public void reportAudioStatus(int i, int i2, int i3, boolean z) {
    }

    public void sendKeyEvent(int i, int i2, boolean z) {
    }

    public void sendMhlVendorCommand(int i, int i2, int i3, byte[] bArr) {
    }

    public void sendStandby(int i, int i2) {
    }

    public void sendVendorCommand(int i, int i2, byte[] bArr, boolean z) {
    }

    public void sendVolumeKeyEvent(int i, int i2, boolean z) {
    }

    public void setArcMode(boolean z) {
    }

    public void setHdmiCecVolumeControlEnabled(boolean z) {
    }

    public void setHdmiRecordListener(IHdmiRecordListener iHdmiRecordListener) {
    }

    public void setInputChangeListener(IHdmiInputChangeListener iHdmiInputChangeListener) {
    }

    public void setProhibitMode(boolean z) {
    }

    public void setStandbyMode(boolean z) {
    }

    public void setSystemAudioMode(boolean z, IHdmiControlCallback iHdmiControlCallback) {
    }

    public void setSystemAudioModeOnForAudioOnlySource() {
    }

    public void setSystemAudioMute(boolean z) {
    }

    public void setSystemAudioVolume(int i, int i2, int i3) {
    }

    public void startOneTouchRecord(int i, byte[] bArr) {
    }

    public void startTimerRecording(int i, int i2, byte[] bArr) {
    }

    public void stopOneTouchRecord(int i) {
    }

    public HdmiControlManager createHdmiControlManager() {
        return new HdmiControlManager(this.mInterface);
    }

    public void setPortInfo(List<HdmiPortInfo> list) {
        this.mInfoList = list;
    }

    public void setDeviceTypes(int[] iArr) {
        this.mTypes = iArr;
    }

    public List<HdmiPortInfo> getPortInfo() {
        return this.mInfoList;
    }

    public int[] getSupportedTypes() {
        return this.mTypes;
    }
}
