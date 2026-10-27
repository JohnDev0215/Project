package ts.car.usb;

import android.hardware.usb.V1_0.IUsb;
import android.hardware.usb.V1_0.IUsbCallback;
import android.os.RemoteException;
import ts.car.usb.log.Logger;
import ts.car.usb.utils.AutoServiceConnector4Hidl;
import ts.car.usb.v1.usb.UsbPortRole;

/* JADX INFO: loaded from: classes.dex */
class UsbHalController extends AutoServiceConnector4Hidl<IUsb> {
    private static final Logger LOGGER = new Logger("UsbHalController");
    private static UsbHalController mInstance;
    private IUsb mService;

    UsbHalController() {
    }

    public static synchronized UsbHalController getInstance() {
        if (mInstance == null) {
            mInstance = new UsbHalController();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    public IUsb createService() {
        try {
            IUsb service = IUsb.getService();
            LOGGER.i("createService=" + service, new Object[0]);
            return service;
        } catch (RemoteException ee) {
            LOGGER.e(ee, "android.hardware.usb.V1_0.IUsb getService failed!", new Object[0]);
            return null;
        } catch (Exception ee2) {
            LOGGER.e(ee2, "android.hardware.usb.V1_0.IUsb getService failed!", new Object[0]);
            return null;
        }
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceConnected() {
        this.mService = getService();
        LOGGER.i("android.hardware.usb.V1_0.IUsb is connected!", new Object[0]);
    }

    @Override // ts.car.usb.utils.AutoServiceConnector4Hidl
    protected void onServiceDisconnected() {
        this.mService = null;
        LOGGER.i("android.hardware.usb.V1_0.IUsb is disconnected!", new Object[0]);
    }

    public void switchRole(String portName, UsbPortRole role) {
        IUsb iUsb = this.mService;
        if (iUsb == null) {
            LOGGER.e("android.hardware.usb.V1_0.IUsb is not connected!", new Object[0]);
            return;
        }
        try {
            iUsb.switchRole(portName, role.convert());
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }

    public void setCallback(IUsbCallback callback) {
        IUsb iUsb = this.mService;
        if (iUsb == null) {
            LOGGER.e("android.hardware.usb.V1_0.IUsb is not connected!", new Object[0]);
            return;
        }
        try {
            iUsb.setCallback(callback);
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }

    public void queryPortStatus() {
        IUsb iUsb = this.mService;
        if (iUsb == null) {
            LOGGER.e("android.hardware.usb.V1_0.IUsb is not connected!", new Object[0]);
            return;
        }
        try {
            iUsb.queryPortStatus();
        } catch (RemoteException ee) {
            LOGGER.e(ee, "", new Object[0]);
        }
    }
}
