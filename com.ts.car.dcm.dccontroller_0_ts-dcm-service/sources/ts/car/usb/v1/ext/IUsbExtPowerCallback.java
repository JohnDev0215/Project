package ts.car.usb.v1.ext;

import android.os.RemoteException;
import android.util.Log;
import vendor.rbxc.usbext.V1_0.IUsbextCallback;

/* JADX INFO: loaded from: classes.dex */
public abstract class IUsbExtPowerCallback extends IUsbextCallback.Stub {
    private static String TAG = "IUsbExtPowerCallback";
    private static int ROLE_SWITCH_PATH_NUM = 1;

    public abstract void notifyCurrentPortPower(String str, UsbPortPower usbPortPower, UsbStatus4Ext usbStatus4Ext);

    public abstract void notifyPortConnectStatusChange(String str, UsbUevent usbUevent);

    public abstract void notifyPowerChangeStatus(String str, UsbPortPower usbPortPower, UsbStatus4Ext usbStatus4Ext);

    public abstract void notifyRoleSwitchPaths(String[] strArr);

    @Override // vendor.rbxc.usbext.V1_0.IUsbextCallback
    public void notifyPortConnectStatusChange(String portName, int events) throws RemoteException {
        notifyPortConnectStatusChange(portName, UsbUevent.clone(events));
    }

    @Override // vendor.rbxc.usbext.V1_0.IUsbextCallback
    public final void notifyCurrentPortPower(String portName, int currentValue, int retval) throws RemoteException {
        notifyCurrentPortPower(portName, UsbPortPower.clone(currentValue), UsbStatus4Ext.clone(retval));
    }

    @Override // vendor.rbxc.usbext.V1_0.IUsbextCallback
    public final void notifyPowerChangeStatus(String portName, int newValue, int retval) throws RemoteException {
        notifyPowerChangeStatus(portName, UsbPortPower.clone(newValue), UsbStatus4Ext.clone(retval));
    }

    @Override // vendor.rbxc.usbext.V1_0.IUsbextCallback
    public final void notifyCurrentBusNum(String portName, int busNum, int retval) throws RemoteException {
        Log.d(TAG, "notifyCurrentBusNum, portName: " + portName + ", busNum: " + busNum + ", retval:" + retval);
        String[] roleSwitchPathSet = new String[ROLE_SWITCH_PATH_NUM];
        if (busNum == 0) {
            Log.d(TAG, "notifyCurrentBusNum, invalid bus number.");
            return;
        }
        String currentRoleSwitchPath = "/dev/bus/usb/00" + busNum;
        roleSwitchPathSet[0] = currentRoleSwitchPath;
        notifyRoleSwitchPaths(roleSwitchPathSet);
    }
}
