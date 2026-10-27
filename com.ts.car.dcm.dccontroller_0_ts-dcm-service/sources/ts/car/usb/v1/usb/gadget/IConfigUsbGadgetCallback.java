package ts.car.usb.v1.usb.gadget;

import android.hardware.usb.gadget.V1_0.IUsbGadgetCallback;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class IConfigUsbGadgetCallback extends IUsbGadgetCallback.Stub {
    public abstract void getCurrentUsbFunctionsCb(long j, UsbStatus4Gadget usbStatus4Gadget);

    public abstract void setCurrentUsbFunctionsCb(long j, UsbStatus4Gadget usbStatus4Gadget);

    @Override // android.hardware.usb.gadget.V1_0.IUsbGadgetCallback
    public final void setCurrentUsbFunctionsCb(long functions, int status) throws RemoteException {
        setCurrentUsbFunctionsCb(functions, UsbStatus4Gadget.clone(status));
    }

    @Override // android.hardware.usb.gadget.V1_0.IUsbGadgetCallback
    public final void getCurrentUsbFunctionsCb(long functions, int status) throws RemoteException {
        getCurrentUsbFunctionsCb(functions, UsbStatus4Gadget.clone(status));
    }
}
