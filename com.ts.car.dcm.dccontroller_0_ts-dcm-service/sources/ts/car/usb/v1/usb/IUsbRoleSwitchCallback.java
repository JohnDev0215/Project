package ts.car.usb.v1.usb;

import android.hardware.usb.V1_0.IUsbCallback;
import android.hardware.usb.V1_0.PortRole;
import android.hardware.usb.V1_0.PortStatus;
import android.os.RemoteException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class IUsbRoleSwitchCallback extends IUsbCallback.Stub {
    public abstract void notifyPortStatusChange(ArrayList<UsbPortStatus> arrayList, UsbStatus usbStatus);

    public abstract void notifyRoleSwitchStatus(String str, UsbPortRole usbPortRole, UsbStatus usbStatus);

    @Override // android.hardware.usb.V1_0.IUsbCallback
    public final void notifyPortStatusChange(ArrayList<PortStatus> currentPortStatus, int retval) throws RemoteException {
        ArrayList<UsbPortStatus> portStatus = null;
        if (currentPortStatus != null) {
            portStatus = new ArrayList<>();
            for (PortStatus status : currentPortStatus) {
                portStatus.add(new UsbPortStatus().clone(status));
            }
        }
        notifyPortStatusChange(portStatus, UsbStatus.clone(retval));
    }

    @Override // android.hardware.usb.V1_0.IUsbCallback
    public final void notifyRoleSwitchStatus(String portName, PortRole newRole, int retval) throws RemoteException {
        UsbPortRole portRole = null;
        if (newRole != null) {
            portRole = new UsbPortRole().clone(newRole);
        }
        notifyRoleSwitchStatus(portName, portRole, UsbStatus.clone(retval));
    }
}
