package ts.car.usb;

import ts.car.usb.v1.ext.IUsbExtPowerCallback;
import ts.car.usb.v1.ext.UsbPortPower;
import ts.car.usb.v1.usb.IUsbRoleSwitchCallback;
import ts.car.usb.v1.usb.UsbPortRole;
import ts.car.usb.v1.usb.gadget.IConfigUsbGadgetCallback;

/* JADX INFO: loaded from: classes.dex */
public abstract class UsbManagerExt {
    private static UsbManagerExt mInstance;

    public abstract void getCurrentUsbFunctions(IConfigUsbGadgetCallback iConfigUsbGadgetCallback);

    public abstract void getUsbPower(String str);

    public abstract void queryPortStatus();

    public abstract void setCallback(IUsbRoleSwitchCallback iUsbRoleSwitchCallback);

    public abstract void setCurrentUsbFunctions(long j, IConfigUsbGadgetCallback iConfigUsbGadgetCallback, long j2);

    public abstract void setExtCallback(IUsbExtPowerCallback iUsbExtPowerCallback);

    public abstract void setUsbPower(String str, UsbPortPower usbPortPower);

    public abstract void switchRole(String str, UsbPortRole usbPortRole);

    public static synchronized UsbManagerExt getInstance() {
        if (mInstance == null) {
            mInstance = new UsbManagerImpl();
        }
        return mInstance;
    }
}
