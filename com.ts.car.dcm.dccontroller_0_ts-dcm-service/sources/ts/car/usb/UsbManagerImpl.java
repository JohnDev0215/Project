package ts.car.usb;

import ts.car.usb.log.Logger;
import ts.car.usb.v1.ext.IUsbExtPowerCallback;
import ts.car.usb.v1.ext.UsbPortPower;
import ts.car.usb.v1.usb.IUsbRoleSwitchCallback;
import ts.car.usb.v1.usb.UsbPortRole;
import ts.car.usb.v1.usb.gadget.IConfigUsbGadgetCallback;

/* JADX INFO: loaded from: classes.dex */
class UsbManagerImpl extends UsbManagerExt {
    private static final Logger LOGGER = new Logger("UsbManagerExt");
    private UsbGadgetController mUsbGadgetController;
    private UsbHalController mUsbHalController;
    private UsbHalExtController mUsbHalExtController;

    protected UsbManagerImpl() {
        LOGGER.i("init start>>>", new Object[0]);
        this.mUsbHalController = UsbHalController.getInstance();
        this.mUsbHalExtController = UsbHalExtController.getInstance();
        this.mUsbGadgetController = UsbGadgetController.getInstance();
        LOGGER.i("init finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void setUsbPower(String portName, UsbPortPower power) {
        LOGGER.i("setUsbPower start>>>", new Object[0]);
        this.mUsbHalExtController.setUsbPower(portName, power);
        LOGGER.i("setUsbPower finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void getUsbPower(String portName) {
        LOGGER.i("getUsbPower start>>>", new Object[0]);
        this.mUsbHalExtController.getUsbPower(portName);
        LOGGER.i("getUsbPower finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void setExtCallback(IUsbExtPowerCallback callback) {
        LOGGER.i("setExtCallback start>>>", new Object[0]);
        this.mUsbHalExtController.setCallback(callback);
        LOGGER.i("setExtCallback finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void switchRole(String portName, UsbPortRole role) {
        LOGGER.i("switchRole start>>>", new Object[0]);
        this.mUsbHalController.switchRole(portName, role);
        LOGGER.i("switchRole finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void setCallback(IUsbRoleSwitchCallback callback) {
        LOGGER.i("setCallback start>>>", new Object[0]);
        this.mUsbHalController.setCallback(callback);
        LOGGER.i("setCallback finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void queryPortStatus() {
        LOGGER.i("queryPortStatus start>>>", new Object[0]);
        this.mUsbHalController.queryPortStatus();
        LOGGER.i("queryPortStatus finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void setCurrentUsbFunctions(long functions, IConfigUsbGadgetCallback callback, long timeout) {
        LOGGER.i("setCurrentUsbFunctions start>>>", new Object[0]);
        this.mUsbGadgetController.setCurrentUsbFunctions(functions, callback, timeout);
        LOGGER.i("setCurrentUsbFunctions finished<<<", new Object[0]);
    }

    @Override // ts.car.usb.UsbManagerExt
    public void getCurrentUsbFunctions(IConfigUsbGadgetCallback callback) {
        LOGGER.i("getCurrentUsbFunctions start>>>", new Object[0]);
        this.mUsbGadgetController.getCurrentUsbFunctions(callback);
        LOGGER.i("getCurrentUsbFunctions finished<<<", new Object[0]);
    }
}
