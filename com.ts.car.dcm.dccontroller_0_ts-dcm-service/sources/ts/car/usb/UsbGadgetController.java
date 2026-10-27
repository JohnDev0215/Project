package ts.car.usb;

import android.hardware.usb.gadget.V1_0.IUsbGadget;
import android.hardware.usb.gadget.V1_0.IUsbGadgetCallback;
import android.os.RemoteException;
import ts.car.usb.log.Logger;
import ts.car.usb.utils.AutoServiceConnector4Hidl;

/* JADX INFO: loaded from: classes.dex */
class UsbGadgetController extends AutoServiceConnector4Hidl<IUsbGadget> {
    private static final Logger LOGGER = new Logger("UsbGadgetController");
    private static UsbGadgetController mInstance;
    private IUsbGadget mService;

    UsbGadgetController() {
    }

    public static synchronized UsbGadgetController getInstance() {
        if (mInstance == null) {
            mInstance = new UsbGadgetController();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    public IUsbGadget createService() {
        try {
            IUsbGadget service = IUsbGadget.getService();
            LOGGER.i("createService=" + service, new Object[0]);
            return service;
        } catch (RemoteException ee) {
            LOGGER.e(ee, "android.hardware.usb.gadget.V1_0.IUsbGadget getService failed!", new Object[0]);
            return null;
        } catch (Exception ee2) {
            LOGGER.e(ee2, "android.hardware.usb.gadget.V1_0.IUsbGadget getService failed!", new Object[0]);
            return null;
        }
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceConnected() {
        this.mService = getService();
        LOGGER.i("android.hardware.usb.gadget.V1_0.IUsbGadget is connected!", new Object[0]);
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceDisconnected() {
        this.mService = null;
        LOGGER.i("android.hardware.usb.gadget.V1_0.IUsbGadget is disconnected!", new Object[0]);
    }

    public void setCurrentUsbFunctions(long functions, IUsbGadgetCallback callback, long timeout) {
        IUsbGadget iUsbGadget = this.mService;
        if (iUsbGadget == null) {
            LOGGER.e("android.hardware.usb.gadget.V1_0.IUsbGadget is not connected!", new Object[0]);
            return;
        }
        try {
            iUsbGadget.setCurrentUsbFunctions(functions, callback, timeout);
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }

    public void getCurrentUsbFunctions(IUsbGadgetCallback callback) {
        IUsbGadget iUsbGadget = this.mService;
        if (iUsbGadget == null) {
            LOGGER.e("android.hardware.usb.gadget.V1_0.IUsbGadget is not connected!", new Object[0]);
            return;
        }
        try {
            iUsbGadget.getCurrentUsbFunctions(callback);
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }
}
