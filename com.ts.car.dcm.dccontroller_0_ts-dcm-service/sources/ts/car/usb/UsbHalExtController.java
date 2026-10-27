package ts.car.usb;

import android.os.RemoteException;
import ts.car.usb.log.Logger;
import ts.car.usb.utils.AutoServiceConnector4Hidl;
import ts.car.usb.v1.ext.UsbPortPower;
import vendor.rbxc.usbext.V1_0.IUsbext;
import vendor.rbxc.usbext.V1_0.IUsbextCallback;

/* JADX INFO: loaded from: classes.dex */
class UsbHalExtController extends AutoServiceConnector4Hidl<IUsbext> {
    private static final Logger LOGGER = new Logger("UsbHalExtController");
    private static UsbHalExtController mInstance;
    private IUsbext mService;

    UsbHalExtController() {
    }

    public static synchronized UsbHalExtController getInstance() {
        if (mInstance == null) {
            mInstance = new UsbHalExtController();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    public IUsbext createService() {
        try {
            IUsbext service = IUsbext.getService();
            LOGGER.i("createService=" + service, new Object[0]);
            return service;
        } catch (RemoteException ee) {
            LOGGER.e(ee, "vendor.ts.usbext.V1_0.IUsbext getService failed!", new Object[0]);
            return null;
        } catch (Exception ee2) {
            LOGGER.e(ee2, "vendor.ts.usbext.V1_0.IUsbext getService failed!", new Object[0]);
            return null;
        }
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceConnected() {
        this.mService = getService();
        LOGGER.i("vendor.ts.usbext.V1_0.IUsbext is connected!", new Object[0]);
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceDisconnected() {
        this.mService = null;
        LOGGER.i("vendor.ts.usbext.V1_0.IUsbext is disconnected!", new Object[0]);
    }

    public void setUsbPower(String portName, UsbPortPower power) {
        IUsbext iUsbext = this.mService;
        if (iUsbext == null) {
            LOGGER.e("vendor.ts.usbext.V1_0.IUsbext is not connected!", new Object[0]);
            return;
        }
        try {
            iUsbext.SetUsbPower(portName, power.getValue());
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }

    public void getUsbPower(String portName) {
        IUsbext iUsbext = this.mService;
        if (iUsbext == null) {
            LOGGER.e("vendor.ts.usbext.V1_0.IUsbext is not connected!", new Object[0]);
            return;
        }
        try {
            iUsbext.GetUsbPower(portName);
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }

    public void setCallback(IUsbextCallback callback) {
        IUsbext iUsbext = this.mService;
        if (iUsbext == null) {
            LOGGER.e("vendor.ts.usbext.V1_0.IUsbext is not connected!", new Object[0]);
            return;
        }
        try {
            iUsbext.setCallback(callback);
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }
}
