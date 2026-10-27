package ts.car.usb.v1.usb;

import android.hardware.usb.V1_0.PortDataRole;
import android.hardware.usb.V1_0.PortMode;
import android.hardware.usb.V1_0.PortPowerRole;
import android.hardware.usb.V1_0.PortStatus;
import android.os.HidlSupport;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class UsbPortStatus {
    public boolean canChangeDataRole;
    public boolean canChangeMode;
    public boolean canChangePowerRole;
    public int currentDataRole;
    public int currentMode;
    public int currentPowerRole;
    public String portName;
    public int supportedModes;

    UsbPortStatus clone(PortStatus portStatus) {
        this.portName = portStatus.portName;
        this.currentDataRole = portStatus.currentDataRole;
        this.currentPowerRole = portStatus.currentPowerRole;
        this.currentMode = portStatus.currentMode;
        this.canChangeMode = portStatus.canChangeMode;
        this.canChangeDataRole = portStatus.canChangeDataRole;
        this.canChangePowerRole = portStatus.canChangePowerRole;
        this.supportedModes = portStatus.supportedModes;
        return this;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.portName)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.currentDataRole))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.currentPowerRole))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.currentMode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.canChangeMode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.canChangeDataRole))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.canChangePowerRole))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.supportedModes))));
    }

    public final String toString() {
        return "{.portName = " + this.portName + ", .currentDataRole = " + PortDataRole.toString(this.currentDataRole) + ", .currentPowerRole = " + PortPowerRole.toString(this.currentPowerRole) + ", .currentMode = " + PortMode.toString(this.currentMode) + ", .canChangeMode = " + this.canChangeMode + ", .canChangeDataRole = " + this.canChangeDataRole + ", .canChangePowerRole = " + this.canChangePowerRole + ", .supportedModes = " + PortMode.toString(this.supportedModes) + "}";
    }
}
