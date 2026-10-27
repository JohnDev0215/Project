package com.ts.car.dcm.usbcontroller;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.usbcontroller.UsbDeviceDetect;
import com.ts.car.dcm.utils.statemachine.IState;
import com.ts.car.dcm.utils.statemachine.State;
import com.ts.car.dcm.utils.statemachine.StateMachine;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import ts.car.usb.UsbManagerExt;
import ts.car.usb.v1.ext.UsbPortPower;
import ts.car.usb.v1.ext.UsbStatus4Ext;
import ts.car.usb.v1.usb.UsbPortRole;
import ts.car.usb.v1.usb.UsbPortRoleType;
import ts.car.usb.v1.usb.UsbStatus;
import ts.car.usb.v1.usb.gadget.IConfigUsbGadgetCallback;
import ts.car.usb.v1.usb.gadget.UsbGadgetFunction;
import ts.car.usb.v1.usb.gadget.UsbStatus4Gadget;

/* JADX INFO: loaded from: classes.dex */
public class UsbConnection extends StateMachine {
    static final int ANDROID_AUTO_AOA_MODE = 1;
    static final int BASE = 100;
    static final int CARLIFE_AOA_MODE = 0;
    private static final int CMD_TO_STRING_COUNT = 43;
    private static final int DO_AOA_TIMEOUT = 3000;
    private static final int DO_RESET_WAIT_ATTACH_TIME = 4000;
    private static final int DO_SWITCH_HOST_DELAY_TIME = 1500;
    private static final int DO_SWITCH_TIMEOUT = 3000;
    static final int EVENT_DCM_ALLOW = 103;
    static final int EVENT_DCM_ANDROID_ATUO_AOA_END = 117;
    static final int EVENT_DCM_ANDROID_ATUO_AOA_START = 113;
    static final int EVENT_DCM_CARLIFE_AOA_END = 116;
    static final int EVENT_DCM_CARLIFE_AOA_START = 112;
    static final int EVENT_DCM_DISABLE_DEVICE = 108;
    static final int EVENT_DCM_ENABLE_DEVICE = 109;
    static final int EVENT_DCM_NOT_ALLOW = 104;
    static final int EVENT_DCM_RELEASE_CONNECTION = 115;
    static final int EVENT_DCM_REMOVE_DEVICE = 110;
    static final int EVENT_DCM_ROLE_SWITCH_TO_DEVICE = 105;
    static final int EVENT_DCM_ROLE_SWITCH_TO_HOST = 106;
    static final int EVENT_DCM_SET_CAPABILITY = 111;
    static final int EVENT_DCM_USB_RESET = 107;
    static final int EVENT_DEVICE_DETECTING = 100;
    private static final int EVENT_INTERNAL_AOA_OPERATOR_DONE = 131;
    private static final int EVENT_INTERNAL_DEVICE_DETECTED = 130;
    static final int EVENT_INTERNAL_DEVICE_PULLED_OUT = 137;
    private static final int EVENT_INTERNAL_RESET_OPERATOR_DONE = 135;
    private static final int EVENT_INTERNAL_RESET_OPERATOR_FAILED = 142;
    private static final int EVENT_INTERNAL_RESET_WAIT_ATTACH_TIMEOUT = 136;
    private static final int EVENT_INTERNAL_ROLE_SWITCH_DEVICE_OPERATOR_DONE = 132;
    private static final int EVENT_INTERNAL_ROLE_SWITCH_HOST_OPERATOR_DONE = 133;
    private static final int EVENT_INTERNAL_USB_OPERATION_TIMEOUT = 134;
    private static final int EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT = 125;
    static final int EVENT_ROLESWTICH_STATUS_RESULT = 140;
    private static final int EVENT_SET_USB_FUNCTIONS_RESULT = 138;
    private static final int EVENT_SET_USB_FUNCTIONS_TIMEOUT = 139;
    static final int EVENT_STATE_TO_INACTIVE = 114;
    static final int EVENT_USB_ACCESSORY_ATTACH = 118;
    static final int EVENT_USB_ACCESSORY_DETACH = 119;
    static final int EVENT_USB_ATTCH = 101;
    static final int EVENT_USB_DETACH = 102;
    static final int EVENT_USB_IAPNODE_CREATE = 120;
    static final int EVENT_USB_POWER_STATUS = 141;
    private static final String PROP_ROLESWITCH_STATE = "vendor.usb.roleswitch";
    private static final String ROLESWITCH_FAIL = "fail";
    private static final String ROLESWITCH_SUCCEED = "success";
    private static final int SET_FUNCTIONS_LEEWAY_MS = 500;
    private static final int SET_FUNCTIONS_TIMEOUT_MS = 2000;
    private static final String TAG = "UsbConnection";
    private static final int USB_BUS_MODE_ADB_DIAG = 4;
    private static final int USB_BUS_MODE_IAP_EAP = 1;
    private static final int USB_BUS_MODE_IAP_NCM = 3;
    private static final int USB_BUS_MODE_NCM = 2;
    private static final int USB_BUS_MODE_NONE = 0;
    static final int USB_DEVICE_MODE = 1;
    private static final String USB_DEVICE_PORTNAME = "usb3_0.3";
    static final int USB_HOST_MODE = 0;
    private static final int USB_OPERATION_FAIL = 0;
    private static final int USB_OPERATION_SUCCESS = 1;
    private static String[] sCmdToString;
    private Context mContext;
    private UsbDefaultState mDefaultState;
    private int mDevCapability;
    private HandlerThread mHandlerThread;
    private UsbInvalidState mInvalidState;
    private boolean mIsUsbDeviceMode;
    private boolean mIsUsed;
    private LinkDevice mLinkDevice;
    private OperationHandler mOperationHandler;
    private String mPortName;
    private String mSerial;
    private int mUntreatedCapa;
    private boolean mUntreatedDevice;
    private UsbConfiguration mUsbConfiguration;
    private UsbConnectedState mUsbConnectedState;
    private UsbConnectingState mUsbConnectingState;
    private int mUsbConnectionId;
    private UsbDevice mUsbDevice;
    private UsbDeviceDetect mUsbDeviceDetect;
    private UsbDeviceMonitor mUsbDeviceMonitor;
    private String mUsbDeviceName;
    private UsbDisconnected mUsbDisconnectedState;
    private UsbGadgetCallback mUsbGadgetCallback;
    private UsbInactiveState mUsbInactiveState;
    private UsbManagerExt mUsbManagerExt;
    private UsbOperationState mUsbOperationState;
    private UsbRemovedState mUsbRemovedState;
    protected static final String LOG_TAG = UsbConnection.class.getSimpleName();
    private static AtomicInteger mInstanceNumber = new AtomicInteger(0);
    private static final long USB_CONFIG_IAP_NCM = UsbGadgetFunction.IAP.getValue() | UsbGadgetFunction.NCM.getValue();
    private static final long USB_CONFIG_IAP_EAP = UsbGadgetFunction.IAP.getValue() | UsbGadgetFunction.EAP.getValue();
    private static final long USB_CONFIG_NONE = UsbGadgetFunction.NONE.getValue();

    private enum ServiceType {
        USB_SERVICE_TYPE_NONE,
        USB_SERVICE_TYPE_IOS_CARPLAY,
        USB_SERVICE_TYPE_IOS_CARLIFE,
        USB_SERVICE_TYPE_ANDROID_CARLIFE,
        USB_SERVICE_TYPE_ANDROID_AUTO,
        USB_SERVICE_TYPE_HOST,
        USB_SERVICE_TYPE_RESET
    }

    static {
        String[] strArr = new String[43];
        sCmdToString = strArr;
        strArr[0] = "EVENT_DEVICE_DETECTING";
        strArr[30] = "EVENT_INTERNAL_DEVICE_DETECTED";
        strArr[1] = "EVENT_USB_ATTCH";
        strArr[2] = "EVENT_USB_DETACH";
        strArr[3] = "EVENT_DCM_ALLOW";
        strArr[4] = "EVENT_DCM_NOT_ALLOW";
        strArr[5] = "EVENT_DCM_ROLE_SWITCH_TO_DEVICE";
        strArr[6] = "EVENT_DCM_ROLE_SWITCH_TO_HOST";
        strArr[7] = "EVENT_DCM_USB_RESET";
        strArr[8] = "EVENT_DCM_DISABLE_DEVICE";
        strArr[9] = "EVENT_DCM_ENABLE_DEVICE";
        strArr[10] = "EVENT_DCM_REMOVE_DEVICE";
        strArr[11] = "EVENT_DCM_SET_CAPABILITY";
        strArr[12] = "EVENT_DCM_CARLIFE_AOA_START";
        strArr[13] = "EVENT_DCM_ANDROID_ATUO_AOA_START";
        strArr[16] = "EVENT_DCM_CARLIFE_AOA_END";
        strArr[17] = "EVENT_DCM_ANDROID_ATUO_AOA_END";
        strArr[31] = "EVENT_INTERNAL_AOA_OPERATOR_DONE";
        strArr[18] = "EVENT_USB_ACCESSORY_ATTACH";
        strArr[19] = "EVENT_USB_ACCESSORY_DETACH";
        strArr[20] = "EVENT_USB_IAPNODE_CREATE";
        strArr[35] = "EVENT_INTERNAL_RESET_OPERATOR_DONE";
        strArr[34] = "EVENT_INTERNAL_USB_OPERATION_TIMEOUT";
        strArr[32] = "EVENT_INTERNAL_ROLE_SWITCH_OPERATOR_DONE";
        strArr[33] = "EVENT_INTERNAL_ROLE_HOST_OPERATOR_DONE";
        strArr[14] = "EVENT_STATE_TO_INACTIVE";
        strArr[15] = "EVENT_DCM_RELEASE_CONNECTION";
        strArr[25] = "EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT";
        strArr[36] = "EVENT_INTERNAL_RESET_WAIT_ATTACH_TIMEOUT";
        strArr[37] = "EVENT_INTERNAL_DEVICE_PULLED_OUT";
        strArr[38] = "EVENT_SET_USB_FUNCTIONS_RESULT";
        strArr[39] = "EVENT_SET_USB_FUNCTIONS_TIMEOUT";
        strArr[40] = "EVENT_ROLESWTICH_STATUS_RESULT";
        strArr[41] = "EVENT_USB_POWER_STATUS";
        strArr[42] = "EVENT_INTERNAL_RESET_OPERATOR_FAILED";
    }

    private static String cmdToString(int cmd) {
        String value = null;
        int cmd2 = cmd - 100;
        if (cmd2 >= 0) {
            String[] strArr = sCmdToString;
            if (cmd2 < strArr.length) {
                value = strArr[cmd2];
            }
        }
        if (value == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN + Integer.toHexString(cmd2 + 100);
        }
        return value;
    }

    public static UsbConnection makeUsbConnection(int id, UsbDeviceMonitor usbDeviceMonitor, UsbDevice usbHost, Context context) {
        Log.e(TAG, "makeUsbConnection, usbDevice:" + usbHost);
        UsbConnection uc = new UsbConnection(id, usbDeviceMonitor, usbHost, "UC-" + mInstanceNumber.incrementAndGet(), context);
        uc.start();
        return uc;
    }

    public String getSerial() {
        return this.mSerial;
    }

    public UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    public String getUsbDeviceName() {
        return this.mUsbDeviceName;
    }

    public int getUsbConnectionId() {
        return this.mUsbConnectionId;
    }

    public boolean getIsInvalid() {
        return getCurrentState() == this.mInvalidState;
    }

    public boolean getIsUsed() {
        return this.mIsUsed;
    }

    public void setUse(boolean use) {
        this.mIsUsed = use;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUsbDevice(UsbDevice usbDevice) {
        log("updateUsbDevice, usbDevice:" + usbDevice);
        this.mUsbDevice = usbDevice;
        if (usbDevice != null) {
            String deviceName = usbDevice.getDeviceName();
            this.mUsbDeviceName = deviceName;
            this.mPortName = this.mUsbConfiguration.getPortNameByOtgPortPath(deviceName);
        }
    }

    public boolean isResetState() {
        IState currentState = getCurrentState();
        UsbOperationState usbOperationState = this.mUsbOperationState;
        return currentState == usbOperationState && usbOperationState.getCurrentService() == ServiceType.USB_SERVICE_TYPE_RESET;
    }

    public boolean isAndroidAoaState() {
        IState currentState = getCurrentState();
        UsbOperationState usbOperationState = this.mUsbOperationState;
        return currentState == usbOperationState && usbOperationState.getCurrentService() == ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDevInfo() {
        this.mUsbDevice = null;
        this.mSerial = null;
        this.mUntreatedDevice = false;
        this.mUntreatedCapa = 0;
        this.mIsUsed = false;
        this.mPortName = null;
        this.mUsbDeviceName = null;
        this.mLinkDevice.setId(null);
        this.mLinkDevice.setUsbSsid(null);
        this.mLinkDevice.setCapability(0);
        this.mLinkDevice.setConnectionType(0);
        this.mLinkDevice.setIapNode(null);
        this.mLinkDevice.setUsbNcmNode(null);
        this.mLinkDevice.setUsbHidNode(null);
        this.mLinkDevice.setFriendlyName(null);
        this.mLinkDevice.setDeviceState(0);
    }

    private void initConnection(Context context) {
        this.mIsUsed = true;
        this.mUsbConfiguration = UsbConfiguration.getInstance();
        this.mUsbDeviceDetect = UsbDeviceDetect.getInstance();
        this.mLinkDevice = new LinkDevice();
        HandlerThread handlerThread = new HandlerThread("StateMachine" + this.mUsbConnectionId);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mOperationHandler = new OperationHandler(getHandler(), this.mHandlerThread.getLooper());
        this.mUsbManagerExt = UsbManagerExt.getInstance();
        this.mContext = context;
        this.mUsbGadgetCallback = new UsbGadgetCallback();
        UsbDevice usbDevice = this.mUsbDevice;
        if (usbDevice != null) {
            String deviceName = usbDevice.getDeviceName();
            this.mUsbDeviceName = deviceName;
            this.mPortName = this.mUsbConfiguration.getPortNameByOtgPortPath(deviceName);
        }
        updateSerial();
        addState(this.mDefaultState);
        addState(this.mInvalidState, this.mDefaultState);
        addState(this.mUsbInactiveState, this.mDefaultState);
        addState(this.mUsbConnectingState, this.mDefaultState);
        addState(this.mUsbConnectedState, this.mDefaultState);
        addState(this.mUsbOperationState, this.mDefaultState);
        addState(this.mUsbDisconnectedState, this.mDefaultState);
        addState(this.mUsbRemovedState, this.mDefaultState);
        setInitialState(this.mUsbInactiveState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSerial() {
        UsbDevice usbDevice = this.mUsbDevice;
        if (usbDevice != null) {
            String serialNumber = usbDevice.getSerialNumber();
            this.mSerial = serialNumber;
            if (serialNumber == null) {
                this.mUntreatedDevice = true;
                this.mSerial = this.mUsbDevice.getDeviceName();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCurrentDeferMessage() {
        if (hasDeferredMessages(EVENT_DCM_ROLE_SWITCH_TO_DEVICE)) {
            removeDeferredMessages(EVENT_DCM_ROLE_SWITCH_TO_DEVICE);
            log("removeCurrentDeferMessage, EVENT_DCM_ROLE_SWITCH_TO_DEVICE");
        }
        if (hasDeferredMessages(EVENT_DCM_ROLE_SWITCH_TO_HOST)) {
            removeDeferredMessages(EVENT_DCM_ROLE_SWITCH_TO_HOST);
            log("removeCurrentDeferMessage, EVENT_DCM_ROLE_SWITCH_TO_HOST");
        }
        if (hasDeferredMessages(EVENT_DCM_ANDROID_ATUO_AOA_START)) {
            removeDeferredMessages(EVENT_DCM_ANDROID_ATUO_AOA_START);
            log("removeCurrentDeferMessage, EVENT_DCM_ANDROID_ATUO_AOA_START");
        }
        if (hasDeferredMessages(EVENT_DCM_CARLIFE_AOA_START)) {
            removeDeferredMessages(EVENT_DCM_CARLIFE_AOA_START);
            log("removeCurrentDeferMessage, EVENT_DCM_CARLIFE_AOA_START");
        }
        if (hasDeferredMessages(EVENT_DCM_ENABLE_DEVICE)) {
            removeDeferredMessages(EVENT_DCM_ENABLE_DEVICE);
            log("removeCurrentDeferMessage, EVENT_DCM_ENABLE_DEVICE");
        }
    }

    private UsbConnection(int id, UsbDeviceMonitor usbDeviceMonitor, UsbDevice usbDevice, String name, Context context) {
        super(name, usbDeviceMonitor);
        this.mHandlerThread = null;
        this.mUsbManagerExt = null;
        this.mSerial = null;
        this.mUntreatedDevice = false;
        this.mUntreatedCapa = 0;
        this.mIsUsed = false;
        this.mIsUsbDeviceMode = false;
        this.mDefaultState = new UsbDefaultState();
        this.mInvalidState = new UsbInvalidState();
        this.mUsbInactiveState = new UsbInactiveState();
        this.mUsbConnectingState = new UsbConnectingState();
        this.mUsbConnectedState = new UsbConnectedState();
        this.mUsbOperationState = new UsbOperationState();
        this.mUsbDisconnectedState = new UsbDisconnected();
        this.mUsbRemovedState = new UsbRemovedState();
        this.mUsbConnectionId = id;
        this.mUsbDeviceMonitor = usbDeviceMonitor;
        this.mUsbDevice = usbDevice;
        initConnection(context);
        Log.d(LOG_TAG, "UsbConnection created id: " + id + ", name: " + name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRoleSwitch(int mode, int service) {
        if (mode == 0) {
            Message msg = Message.obtain(this.mOperationHandler);
            msg.what = 2;
            this.mOperationHandler.sendMessage(msg);
        } else if (mode == 1) {
            if (service == 1) {
                setUsbBusMode(3);
                return;
            }
            if (service == 2) {
                setUsbBusMode(1);
                return;
            }
            Message msg2 = Message.obtain(this.mOperationHandler);
            msg2.what = 1;
            msg2.arg1 = service;
            this.mOperationHandler.sendMessage(msg2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setPortMode(int mode) {
        if (this.mPortName == null) {
            Log.e(TAG, "setPortMode, mode:" + mode + ", mPortName is null");
            return false;
        }
        UsbPortRole newRole = new UsbPortRole();
        newRole.type = UsbPortRoleType.DATA_ROLE.getValue();
        if (mode == 1) {
            newRole.role = 2;
            this.mUsbManagerExt.switchRole(this.mPortName, newRole);
            this.mIsUsbDeviceMode = true;
        } else {
            newRole.role = 1;
            this.mUsbManagerExt.switchRole(this.mPortName, newRole);
            this.mIsUsbDeviceMode = false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean resetUsb() {
        boolean flag = setUsbPower(UsbPortPower.DISABLE_POWER);
        if (flag) {
            Message msg = obtainMessage(29);
            msg.obj = this.mPortName;
            msg.arg1 = UsbPortPower.DISABLE_POWER.getValue();
            msg.arg2 = UsbStatus4Ext.SUCCESS.getValue();
            this.mUsbOperationState.handleUsbPowerState(msg);
        }
        return flag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setUsbPower(UsbPortPower usbPortPower) {
        if (this.mPortName == null) {
            return false;
        }
        if (usbPortPower == UsbPortPower.DISABLE_POWER) {
            return true;
        }
        Log.d(TAG, "setUsbPower mPortName: " + this.mPortName);
        Log.d(TAG, "------resetusb START------");
        this.mUsbManagerExt.setUsbPower(USB_DEVICE_PORTNAME, UsbPortPower.DISABLE_POWER);
        this.mUsbManagerExt.setUsbPower(USB_DEVICE_PORTNAME, UsbPortPower.ENABLE_POWER);
        Log.d(TAG, "------resetusb END------");
        return true;
    }

    private void setUsbBusMode(int mode) {
        if (mode == 1) {
            setUsbConfig(USB_CONFIG_IAP_EAP, false);
        } else if (mode == 3) {
            setUsbConfig(USB_CONFIG_IAP_NCM, false);
        }
    }

    private void setUsbConfig(long config, boolean chargingFunctions) {
        Log.d(TAG, "setUsbConfig " + config);
        try {
            setVendorUsbConfig();
            this.mUsbManagerExt.setCurrentUsbFunctions(config, this.mUsbGadgetCallback, 1500L);
            sendMessageDelayed(139, 2000L);
        } catch (Exception ex) {
            Log.d(TAG, "Exception while calling setCurrentUsbFunctions", ex);
        }
    }

    private void setVendorUsbConfig() {
        Log.d(TAG, "setVendorUsbConfig, Set system properties vendor.usb.config");
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method set = cls.getDeclaredMethod("set", String.class, String.class);
            set.invoke(cls, "vendor.usb.config", "a800000.dwc3");
            Log.d(TAG, "setVendorUsbConfig, set system properties vendor.usb.config succeeded");
        } catch (Exception ex) {
            Log.e(TAG, "setVendorUsbConfig, set system properties vendor.usb.config failed");
            ex.printStackTrace();
        }
    }

    public class UsbGadgetCallback extends IConfigUsbGadgetCallback {
        public UsbGadgetCallback() {
        }

        @Override // ts.car.usb.v1.usb.gadget.IConfigUsbGadgetCallback
        public void setCurrentUsbFunctionsCb(long functions, UsbStatus4Gadget status) {
            Log.d(UsbConnection.TAG, "setCurrentUsbFunctionsCb functions:" + functions + ", status:" + status.getValue());
            if (UsbConnection.this.hasMessages(139)) {
                UsbConnection.this.removeMessages(139);
                Message msg = new Message();
                msg.what = 138;
                msg.obj = Long.valueOf(functions);
                msg.arg1 = status.getValue();
                UsbConnection.this.sendMessage(msg);
            }
        }

        @Override // ts.car.usb.v1.usb.gadget.IConfigUsbGadgetCallback
        public void getCurrentUsbFunctionsCb(long functions, UsbStatus4Gadget status) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAoaSwitch(int type) {
        if (type == 0) {
            Message msg = Message.obtain(this.mOperationHandler);
            msg.what = 3;
            msg.arg1 = 0;
            this.mOperationHandler.sendMessage(msg);
            return;
        }
        if (type == 1) {
            Message msg2 = Message.obtain(this.mOperationHandler);
            msg2.what = 3;
            msg2.arg1 = 1;
            this.mOperationHandler.sendMessage(msg2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAoaSwitch(String serialNumber, int type) {
        if (type == 0) {
            Message msg = Message.obtain(this.mOperationHandler);
            msg.what = 3;
            msg.arg1 = 0;
            this.mOperationHandler.sendMessage(msg);
            return;
        }
        if (type == 1) {
            Message msg2 = Message.obtain(this.mOperationHandler);
            msg2.what = 5;
            msg2.obj = serialNumber;
            msg2.arg1 = 1;
            this.mOperationHandler.sendMessage(msg2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDeviceRecog() {
        Message msg = Message.obtain(this.mOperationHandler);
        msg.what = 0;
        this.mOperationHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doBusReset() {
        Message msg = Message.obtain(this.mOperationHandler);
        msg.what = 4;
        this.mOperationHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean allowRoleSwitch() {
        if (this.mUsbDevice != null) {
            if (!this.mUsbConfiguration.enableMultiUsbPort()) {
                Log.d(LOG_TAG, "allowRoleSwitchm only one usb slot for user return support.");
                return true;
            }
            String name = this.mUsbDevice.getDeviceName();
            String[] roleSwitchDevBusPaths = this.mUsbConfiguration.getDeviceRoleSwitchDevBusPath();
            for (String roleSwitchDevBusPath : roleSwitchDevBusPaths) {
                if (name.contains(roleSwitchDevBusPath)) {
                    return true;
                }
            }
            Log.d(LOG_TAG, "allowRoleSwitch, device name:" + this.mUsbDevice.getDeviceName() + ", RoleSwitch path:" + Arrays.toString(this.mUsbConfiguration.getDeviceRoleSwitchDevBusPath()));
            return false;
        }
        Log.e(LOG_TAG, "allowRoleSwitch, Host usbdevice is null.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDeviceCapabilityBySetting(int newCap) {
        Log.d(LOG_TAG, "updateDeviceCapabilityBySetting, new capability:" + newCap);
        if ((newCap & 256) == 256 && (this.mDevCapability & 256) == 0) {
            newCap &= -257;
        }
        if ((newCap & 64) == 64 && (this.mDevCapability & 64) == 0) {
            newCap &= -65;
        }
        if ((newCap & 128) == 128 && (this.mDevCapability & 128) == 0) {
            newCap &= -129;
        }
        if ((newCap & 8) == 8 && (this.mDevCapability & 8) == 0) {
            newCap &= -9;
        }
        if ((newCap & 16) == 16 && (this.mDevCapability & 16) == 0) {
            newCap &= -17;
        }
        if ((newCap & 32768) == 32768 && (32768 & this.mDevCapability) == 0) {
            newCap &= -32769;
        }
        Log.d(LOG_TAG, "updateDeviceCapabilityBySetting, done:" + newCap);
        this.mLinkDevice.setCapability(newCap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAoa() {
        this.mUsbDeviceDetect.stopAoaConnection(this.mUsbDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccDeviceStatusChanged(int reason) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(5);
        msg.arg1 = reason;
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccDeviceAttach() {
        this.mLinkDevice.setId(this.mSerial);
        if (this.mUntreatedDevice) {
            this.mLinkDevice.setUsbSsid(null);
        } else {
            this.mLinkDevice.setUsbSsid(this.mSerial);
        }
        this.mLinkDevice.setFriendlyName(this.mUsbDevice.getProductName());
        this.mLinkDevice.setConnectionType(1);
        this.mLinkDevice.setDeviceState(2);
        this.mLinkDevice.setCapability(0);
        this.mLinkDevice.setIapNode(null);
        this.mLinkDevice.setUsbNcmNode(null);
        this.mLinkDevice.setUsbHidNode(null);
        this.mLinkDevice.setUsbOtgFlag(allowRoleSwitch());
        Message msg = this.mUsbDeviceMonitor.obtainMessage(3);
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccDeviceDetach() {
        this.mLinkDevice.setDeviceState(4);
        Message msg = this.mUsbDeviceMonitor.obtainMessage(4);
        msg.obj = this.mLinkDevice.getId();
        msg.arg1 = this.mLinkDevice.getConnectionType();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccRoleSwitchStart(int mode) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(6);
        msg.arg1 = mode;
        msg.obj = this.mLinkDevice.getId();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccRoleSwitchDone(int mode, int result) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(7);
        msg.arg1 = result;
        msg.arg2 = mode;
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccAoaStarting(String serial, int mode) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(8);
        msg.arg1 = mode;
        msg.obj = serial;
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccAoaDone(int mode, int result) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(9);
        msg.arg1 = mode;
        msg.arg2 = result;
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccDeviceResetStarting() {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(10);
        msg.obj = this.mLinkDevice.getId();
        msg.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUccDeviceResetDone(int result) {
        Message msg = this.mUsbDeviceMonitor.obtainMessage(11);
        msg.arg1 = result;
        msg.obj = this.mLinkDevice.getId();
        msg.sendToTarget();
    }

    private class UsbDefaultState extends State {
        private UsbDefaultState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbDefaultState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i == 101) {
                UsbDevice usbDevice = (UsbDevice) msg.obj;
                UsbConnection.this.updateUsbDevice(usbDevice);
                UsbConnection.this.mLinkDevice.setUsbHidNode(UsbConnection.this.mUsbDeviceDetect.getHidPath(usbDevice));
                UsbConnection.this.notifyUccDeviceStatusChanged(3);
            } else if (i == UsbConnection.EVENT_DCM_USB_RESET) {
                UsbConnection.this.notifyUccDeviceResetDone(0);
            } else if (i == UsbConnection.EVENT_DCM_RELEASE_CONNECTION) {
                if (UsbConnection.this.mHandlerThread != null) {
                    UsbConnection.this.mHandlerThread.quit();
                    UsbConnection.this.mHandlerThread = null;
                    UsbConnection.this.mOperationHandler = null;
                }
                UsbConnection.this.quitNow();
            } else {
                UsbConnection.this.log("UsbDefaultState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
            }
            return true;
        }
    }

    private class UsbInvalidState extends State {
        private UsbInvalidState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(UsbConnection.LOG_TAG, "UsbInvalidState, enter.");
            UsbConnection.this.resetDevInfo();
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbInvalidState msg:" + UsbConnection.this.getWhatToString(msg.what));
            if (msg.what == UsbConnection.EVENT_STATE_TO_INACTIVE) {
                UsbDevice usbDevice = (UsbDevice) msg.obj;
                UsbConnection.this.updateUsbDevice(usbDevice);
                UsbConnection.this.updateSerial();
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.transitionTo(usbConnection.mUsbInactiveState);
                return true;
            }
            UsbConnection.this.log("UsbInvalidState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
            return false;
        }
    }

    private class UsbInactiveState extends State {
        private UsbInactiveState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(UsbConnection.LOG_TAG, "UsbInactiveState, enter.");
            UsbConnection.this.mUntreatedCapa = 0;
            if (UsbConnection.this.mUsbDevice == null) {
                UsbConnection.this.mPortName = null;
                UsbConnection.this.mUsbDeviceName = null;
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbInactiveState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i == UsbConnection.EVENT_DCM_SET_CAPABILITY) {
                if (UsbConnection.this.mUsbDevice == null) {
                    return false;
                }
                UsbConnection.this.mUntreatedCapa = msg.arg1;
                return true;
            }
            if (i != 130) {
                switch (i) {
                    case 100:
                        UsbConnection.this.notifyUccDeviceAttach();
                        UsbConnection.this.doDeviceRecog();
                        return true;
                    case 101:
                        if (UsbConnection.this.mUsbDevice != null) {
                            return false;
                        }
                        UsbDevice usbDevice = (UsbDevice) msg.obj;
                        UsbConnection.this.updateUsbDevice(usbDevice);
                        UsbConnection.this.notifyUccDeviceAttach();
                        UsbConnection.this.doDeviceRecog();
                        return true;
                    case 102:
                        UsbConnection.this.updateUsbDevice(null);
                        UsbConnection.this.notifyUccDeviceDetach();
                        if (UsbConnection.this.mUsbDevice == null) {
                            UsbConnection.this.mPortName = null;
                            UsbConnection.this.mUsbDeviceName = null;
                        }
                        if (UsbConnection.this.mUntreatedDevice) {
                            UsbConnection usbConnection = UsbConnection.this;
                            usbConnection.transitionTo(usbConnection.mInvalidState);
                        }
                        return true;
                    case UsbConnection.EVENT_DCM_ALLOW /* 103 */:
                    case UsbConnection.EVENT_DCM_NOT_ALLOW /* 104 */:
                        UsbConnection.this.deferMessage(msg);
                        return true;
                    default:
                        UsbConnection.this.log("UsbInactiveState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
                        return false;
                }
            }
            if (UsbConnection.this.mUsbDevice != null && UsbConnection.this.mUsbDeviceDetect.checkDevice(UsbConnection.this.mUsbDevice)) {
                UsbConnection.this.mDevCapability = msg.arg1;
                if (UsbConnection.this.mLinkDevice.getCapability() != 0) {
                    UsbConnection.this.mLinkDevice.setDeviceState(3);
                } else {
                    UsbConnection.this.mLinkDevice.setDeviceState(1);
                }
                UsbConnection usbConnection2 = UsbConnection.this;
                usbConnection2.transitionTo(usbConnection2.mUsbConnectingState);
            } else {
                UsbConnection.this.log("UsbInactiveState :Receive DETECTED when Device is null,ignore");
            }
            return true;
        }
    }

    private class UsbConnectingState extends State {
        private UsbConnectingState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbConnectingState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i != UsbConnection.EVENT_DCM_SET_CAPABILITY) {
                switch (i) {
                    case 102:
                        UsbConnection.this.updateUsbDevice(null);
                        UsbConnection.this.notifyUccDeviceDetach();
                        if (UsbConnection.this.mUntreatedDevice) {
                            UsbConnection usbConnection = UsbConnection.this;
                            usbConnection.transitionTo(usbConnection.mInvalidState);
                        } else {
                            UsbConnection usbConnection2 = UsbConnection.this;
                            usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                        }
                        return true;
                    case UsbConnection.EVENT_DCM_ALLOW /* 103 */:
                        if (UsbConnection.this.mUntreatedCapa != 0 && UsbConnection.this.mUntreatedCapa != UsbConnection.this.mLinkDevice.getCapability()) {
                            UsbConnection usbConnection3 = UsbConnection.this;
                            usbConnection3.updateDeviceCapabilityBySetting(usbConnection3.mUntreatedCapa);
                            UsbConnection.this.mUntreatedCapa = 0;
                        }
                        UsbConnection.this.notifyUccDeviceStatusChanged(3);
                        UsbConnection usbConnection4 = UsbConnection.this;
                        usbConnection4.transitionTo(usbConnection4.mUsbConnectedState);
                        return true;
                    case UsbConnection.EVENT_DCM_NOT_ALLOW /* 104 */:
                        UsbConnection.this.mUntreatedCapa = 0;
                        if (UsbConnection.this.mUntreatedDevice) {
                            UsbConnection usbConnection5 = UsbConnection.this;
                            usbConnection5.transitionTo(usbConnection5.mInvalidState);
                        } else {
                            UsbConnection usbConnection6 = UsbConnection.this;
                            usbConnection6.transitionTo(usbConnection6.mUsbInactiveState);
                        }
                        return true;
                    default:
                        UsbConnection.this.log("UsbConnectingState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
                        return false;
                }
            }
            UsbConnection.this.updateDeviceCapabilityBySetting(msg.arg1);
            return true;
        }
    }

    private class UsbConnectedState extends State {
        private UsbConnectedState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(UsbConnection.LOG_TAG, "UsbConnectedState enter.");
            if (UsbConnection.this.mUntreatedCapa != 0 && UsbConnection.this.mUntreatedCapa != UsbConnection.this.mLinkDevice.getCapability()) {
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.updateDeviceCapabilityBySetting(usbConnection.mUntreatedCapa);
                UsbConnection.this.mUntreatedCapa = 0;
                UsbConnection.this.notifyUccDeviceStatusChanged(3);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbConnectedState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i == 102) {
                UsbConnection.this.updateUsbDevice(null);
                if (!UsbConnection.this.mIsUsbDeviceMode) {
                    UsbConnection.this.notifyUccDeviceDetach();
                    if (UsbConnection.this.mUntreatedDevice) {
                        UsbConnection usbConnection = UsbConnection.this;
                        usbConnection.transitionTo(usbConnection.mInvalidState);
                    } else {
                        UsbConnection usbConnection2 = UsbConnection.this;
                        usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                    }
                } else {
                    UsbConnection.this.log("UsbConnectedState usb mode in device, ignore detach message.");
                }
                return true;
            }
            if (i == UsbConnection.EVENT_USB_ACCESSORY_DETACH) {
                UsbConnection.this.updateUsbDevice(null);
                UsbConnection.this.notifyUccDeviceDetach();
                UsbConnection.this.setPortMode(0);
                UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mUsbInactiveState);
                return true;
            }
            if (i == 130) {
                if (UsbConnection.this.mDevCapability != msg.arg1) {
                    UsbConnection.this.mDevCapability = msg.arg1;
                    UsbConnection.this.notifyUccDeviceStatusChanged(3);
                }
                return true;
            }
            if (i != UsbConnection.EVENT_DCM_CARLIFE_AOA_END && i != UsbConnection.EVENT_DCM_ANDROID_ATUO_AOA_END) {
                switch (i) {
                    case UsbConnection.EVENT_DCM_ROLE_SWITCH_TO_DEVICE /* 105 */:
                    case UsbConnection.EVENT_DCM_ROLE_SWITCH_TO_HOST /* 106 */:
                    case UsbConnection.EVENT_DCM_USB_RESET /* 107 */:
                        break;
                    case UsbConnection.EVENT_DCM_DISABLE_DEVICE /* 108 */:
                        UsbConnection.this.mLinkDevice.setDeviceState(4);
                        UsbConnection.this.notifyUccDeviceStatusChanged(4);
                        UsbConnection usbConnection4 = UsbConnection.this;
                        usbConnection4.transitionTo(usbConnection4.mUsbDisconnectedState);
                        return true;
                    default:
                        switch (i) {
                            case UsbConnection.EVENT_DCM_REMOVE_DEVICE /* 110 */:
                                UsbConnection usbConnection5 = UsbConnection.this;
                                usbConnection5.transitionTo(usbConnection5.mUsbRemovedState);
                                return true;
                            case UsbConnection.EVENT_DCM_SET_CAPABILITY /* 111 */:
                                UsbConnection.this.updateDeviceCapabilityBySetting(msg.arg1);
                                UsbConnection.this.notifyUccDeviceStatusChanged(3);
                                return true;
                            case UsbConnection.EVENT_DCM_CARLIFE_AOA_START /* 112 */:
                            case UsbConnection.EVENT_DCM_ANDROID_ATUO_AOA_START /* 113 */:
                                break;
                            default:
                                UsbConnection.this.log("UsbConnectedState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
                                return false;
                        }
                        break;
                }
                UsbConnection.this.deferMessage(msg);
                UsbConnection usbConnection6 = UsbConnection.this;
                usbConnection6.transitionTo(usbConnection6.mUsbOperationState);
                return true;
            }
            boolean result = UsbConnection.this.mUsbDeviceDetect.checkDeviceIsAoaMode(UsbConnection.this.mUsbDevice, UsbConnection.this.getUsbConnectionId());
            if (result) {
                UsbConnection.this.stopAoa();
                return true;
            }
            return false;
        }
    }

    private class UsbOperationState extends State {
        private boolean mAccessoryDevAttached;
        private ServiceType mCurrentService;

        private UsbOperationState() {
        }

        public ServiceType getCurrentService() {
            return this.mCurrentService;
        }

        private void handleRoleSwitchDevEvent(Message msg) {
            ServiceType service = msg.arg1 == 1 ? ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY : ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE;
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_NONE) {
                this.mCurrentService = service;
                UsbConnection.this.notifyUccRoleSwitchStart(1);
                if (UsbConnection.this.allowRoleSwitch()) {
                    UsbConnection.this.doRoleSwitch(1, msg.arg1);
                    UsbConnection.this.sendMessageDelayed(134, 3000L);
                    return;
                }
                UsbConnection.this.mLinkDevice.setIapNode(null);
                UsbConnection.this.mLinkDevice.setUsbNcmNode(null);
                UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.transitionTo(usbConnection.mUsbConnectedState);
                return;
            }
            ServiceType serviceType = this.mCurrentService;
            if (service == serviceType) {
                UsbConnection.this.notifyUccRoleSwitchStart(1);
            } else if (serviceType != ServiceType.USB_SERVICE_TYPE_HOST) {
                UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
            } else {
                UsbConnection.this.deferMessage(msg);
            }
        }

        private void handleSetUsbConfigResult(Message msg) {
            long functions = ((Long) msg.obj).longValue();
            int status = msg.arg1;
            boolean isSuccess = false;
            int service = 0;
            if (status == UsbStatus4Gadget.SUCCESS.getValue()) {
                if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY) {
                    service = 1;
                    if (functions == UsbConnection.USB_CONFIG_IAP_NCM) {
                        isSuccess = true;
                    }
                } else if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE) {
                    service = 2;
                    if (functions == UsbConnection.USB_CONFIG_IAP_EAP) {
                        isSuccess = true;
                    }
                }
            }
            if (isSuccess) {
                Message msg1 = Message.obtain(UsbConnection.this.mOperationHandler);
                msg1.what = 1;
                msg1.arg1 = service;
                UsbConnection.this.mOperationHandler.sendMessage(msg1);
                return;
            }
            Log.e(UsbConnection.TAG, "handleSetUsbConfigResult exception, status: " + status + ",function:" + functions + ",currentService:" + this.mCurrentService);
            if (UsbConnection.this.hasMessages(134)) {
                UsbConnection.this.removeMessages(134);
            }
            UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
            UsbConnection usbConnection = UsbConnection.this;
            usbConnection.transitionTo(usbConnection.mUsbConnectedState);
        }

        private void handleSetUsbFunctionsTimeout() {
            if (UsbConnection.this.hasMessages(134)) {
                UsbConnection.this.removeMessages(134);
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE) {
                UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.transitionTo(usbConnection.mUsbConnectedState);
            }
        }

        private void handleRoleSwitchHostEvent(Message msg) {
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_NONE) {
                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_HOST;
                UsbConnection.this.notifyUccRoleSwitchStart(0);
                UsbConnection.this.doRoleSwitch(0, msg.arg1);
                UsbConnection.this.sendMessageDelayed(UsbConnection.EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT, 1500L);
                return;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_HOST) {
                UsbConnection.this.notifyUccRoleSwitchStart(0);
            } else if (this.mCurrentService != ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE && this.mCurrentService != ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY) {
                UsbConnection.this.notifyUccRoleSwitchDone(0, 0);
            } else {
                UsbConnection.this.deferMessage(msg);
            }
        }

        private void handleCarlifeAoaStartEvent() {
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_NONE) {
                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE;
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.notifyUccAoaStarting(usbConnection.mSerial, 0);
                if ((UsbConnection.this.mLinkDevice.getCapability() & 256) > 0) {
                    UsbConnection.this.doAoaSwitch(0);
                    return;
                }
                UsbConnection.this.notifyUccAoaDone(0, 0);
                UsbConnection usbConnection2 = UsbConnection.this;
                usbConnection2.transitionTo(usbConnection2.mUsbConnectedState);
                return;
            }
            if (this.mCurrentService != ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE) {
                UsbConnection.this.notifyUccAoaDone(0, 0);
            } else {
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.notifyUccAoaStarting(usbConnection3.mSerial, 0);
            }
        }

        private void handleAndroidAutoAoaStartEvent(String serialNumber) {
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_NONE) {
                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO;
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.notifyUccAoaStarting(usbConnection.mSerial, 1);
                if ((UsbConnection.this.mLinkDevice.getCapability() & 128) > 0) {
                    UsbConnection.this.doAoaSwitch(serialNumber, 1);
                    return;
                }
                UsbConnection.this.notifyUccAoaDone(1, 0);
                UsbConnection usbConnection2 = UsbConnection.this;
                usbConnection2.transitionTo(usbConnection2.mUsbConnectedState);
                return;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO) {
                UsbConnection.this.notifyUccRoleSwitchStart(1);
            } else {
                UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
            }
        }

        private void handleAoaOperatorDoneEvent(Message msg) {
            if (msg.arg2 == 1) {
                UsbConnection.this.notifyUccAoaDone(msg.arg1, 0);
                if (UsbConnection.this.mUsbDevice == null || !UsbConnection.this.mUsbDeviceDetect.checkDevice(UsbConnection.this.mUsbDevice)) {
                    UsbConnection.this.updateUsbDevice(null);
                    UsbConnection.this.notifyUccDeviceDetach();
                    UsbConnection usbConnection = UsbConnection.this;
                    usbConnection.transitionTo(usbConnection.mUsbInactiveState);
                    return;
                }
                UsbConnection usbConnection2 = UsbConnection.this;
                usbConnection2.transitionTo(usbConnection2.mUsbConnectedState);
                return;
            }
            if (msg.arg2 == 2) {
                UsbConnection.this.notifyUccAoaDone(msg.arg1, 0);
                UsbConnection.this.resetUsb();
            } else if (msg.arg2 == 3) {
                UsbConnection.this.notifyUccAoaDone(msg.arg1, 1);
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mUsbConnectedState);
            } else if (msg.arg2 == 0) {
                UsbConnection.this.sendMessageDelayed(134, 3000L);
            }
        }

        private boolean handleAttachEvent(Message msg) {
            UsbDevice usbDevice = (UsbDevice) msg.obj;
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE) {
                UsbConnection.this.removeMessages(134);
                boolean result = UsbConnection.this.mUsbDeviceDetect.checkDeviceIsAoaMode(usbDevice, UsbConnection.this.getUsbConnectionId());
                if (result) {
                    UsbConnection.this.notifyUccAoaDone(this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO ? 1 : 0, 1);
                    UsbConnection.this.updateUsbDevice(usbDevice);
                    UsbConnection usbConnection = UsbConnection.this;
                    usbConnection.transitionTo(usbConnection.mUsbConnectedState);
                    return true;
                }
                UsbConnection.this.removeMessages(136);
                UsbConnection.this.doAoaSwitch(1);
                return false;
            }
            if ((this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY) && this.mAccessoryDevAttached) {
                this.mAccessoryDevAttached = false;
                UsbConnection.this.updateUsbDevice(usbDevice);
                UsbConnection.this.mLinkDevice.setUsbHidNode(UsbConnection.this.mUsbDeviceDetect.getHidPath(usbDevice));
                UsbConnection usbConnection2 = UsbConnection.this;
                usbConnection2.transitionTo(usbConnection2.mUsbConnectedState);
                return true;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_HOST) {
                UsbConnection.this.removeMessages(UsbConnection.EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT);
                UsbConnection.this.updateUsbDevice(usbDevice);
                UsbConnection.this.mLinkDevice.setUsbHidNode(UsbConnection.this.mUsbDeviceDetect.getHidPath(usbDevice));
                UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mUsbConnectedState);
                return true;
            }
            if (this.mCurrentService != ServiceType.USB_SERVICE_TYPE_RESET) {
                return false;
            }
            Log.e(UsbConnection.TAG, "ServiceType.USB_SERVICE_TYPE_RESET resetUsb successful ");
            UsbConnection.this.removeMessages(136);
            UsbConnection.this.updateUsbDevice(usbDevice);
            UsbConnection.this.notifyUccDeviceResetDone(1);
            this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
            UsbConnection usbConnection4 = UsbConnection.this;
            usbConnection4.transitionTo(usbConnection4.mUsbConnectedState);
            return true;
        }

        private void handleDetachEvent() {
            UsbConnection.this.updateUsbDevice(null);
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET) {
                Log.e(UsbConnection.LOG_TAG, "UsbOperationState handleDetachEvent, device detach in reset mode.");
                return;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_HOST) {
                UsbConnection.this.removeCurrentDeferMessage();
                UsbConnection.this.notifyUccDeviceDetach();
                if (UsbConnection.this.mUntreatedDevice) {
                    UsbConnection usbConnection = UsbConnection.this;
                    usbConnection.transitionTo(usbConnection.mInvalidState);
                    return;
                } else {
                    UsbConnection usbConnection2 = UsbConnection.this;
                    usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                    return;
                }
            }
            Log.e(UsbConnection.LOG_TAG, "UsbOperationState handleDetachEvent, device detach in service:" + this.mCurrentService);
        }

        private void handleUsbOperatorTimeOutEvent() {
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE) {
                UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
                if (this.mAccessoryDevAttached) {
                    UsbConnection.this.doRoleSwitch(0, 0);
                    return;
                }
                UsbConnection.this.setPortMode(0);
                UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                UsbConnection.this.updateUsbDevice(null);
                UsbConnection.this.notifyUccDeviceDetach();
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.transitionTo(usbConnection.mUsbInactiveState);
                return;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO) {
                UsbConnection.this.notifyUccAoaDone(this.mCurrentService != ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO ? 0 : 1, 0);
                if (UsbConnection.this.mUsbDevice == null || !UsbConnection.this.mUsbDeviceDetect.checkDevice(UsbConnection.this.mUsbDevice)) {
                    UsbConnection.this.updateUsbDevice(null);
                    UsbConnection.this.notifyUccDeviceDetach();
                    UsbConnection usbConnection2 = UsbConnection.this;
                    usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                    return;
                }
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mUsbConnectedState);
                return;
            }
            Log.e(UsbConnection.LOG_TAG, "UsbOperationState handleUsbOperatorTimeOutEvent, internal error.");
            UsbConnection usbConnection4 = UsbConnection.this;
            usbConnection4.transitionTo(usbConnection4.mUsbConnectedState);
        }

        private void handleResetEvent(Message msg) {
            int i = msg.what;
            if (i != UsbConnection.EVENT_DCM_USB_RESET) {
                if (i == 135) {
                    UsbConnection.this.sendMessageDelayed(136, 4000L);
                }
            } else {
                if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET) {
                    UsbConnection.this.notifyUccDeviceResetStarting();
                    return;
                }
                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_RESET;
                UsbConnection.this.removeMessages(134);
                UsbConnection.this.removeCurrentDeferMessage();
                UsbConnection.this.notifyUccDeviceResetStarting();
                UsbConnection.this.doBusReset();
            }
        }

        private void checkIapNode() {
            File file = new File(UsbConnection.this.mUsbConfiguration.getDeviceIapDevPath());
            if (file.exists()) {
                handleRoleSwitchSuccess();
            }
        }

        private void setProperty(String key, String value) {
            Log.d(UsbConnection.TAG, "setProperty: set " + key + " to " + value);
            try {
                new ProcessBuilder("setprop", key, value).start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void handleRoleSwitchSuccess() {
            UsbConnection.this.removeMessages(134);
            UsbConnection.this.mLinkDevice.setIapNode(UsbConnection.this.mUsbConfiguration.getDeviceIapDevPath());
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY) {
                UsbConnection.this.mLinkDevice.setUsbNcmNode(UsbConnection.this.mUsbConfiguration.getDeviceNcmName());
                setProperty(UsbConnection.PROP_ROLESWITCH_STATE, UsbConnection.ROLESWITCH_SUCCEED);
            } else if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE) {
                UsbConnection.this.mLinkDevice.setUsbNcmNode(UsbConnection.this.mUsbConfiguration.getDeviceEapPath());
            }
            UsbConnection.this.notifyUccRoleSwitchDone(1, 1);
            UsbConnection usbConnection = UsbConnection.this;
            usbConnection.transitionTo(usbConnection.mUsbConnectedState);
        }

        private void handleRoleSwitchResult(Message msg) {
            int role = msg.arg1;
            int result = msg.arg2;
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE) {
                if (UsbConnection.this.mIsUsbDeviceMode && role == 2 && result != UsbStatus.SUCCESS.getValue()) {
                    setProperty(UsbConnection.PROP_ROLESWITCH_STATE, UsbConnection.ROLESWITCH_FAIL);
                    if (UsbConnection.this.hasMessages(134)) {
                        UsbConnection.this.removeMessages(134);
                    }
                    Message message = new Message();
                    message.what = 132;
                    message.arg1 = 1;
                    UsbConnection.this.sendMessage(message);
                    UsbConnection.this.mIsUsbDeviceMode = false;
                    return;
                }
                return;
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET && !UsbConnection.this.mIsUsbDeviceMode && role == 1 && result != UsbStatus.SUCCESS.getValue()) {
                UsbConnection.this.notifyUccDeviceResetDone(0);
                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                UsbConnection usbConnection = UsbConnection.this;
                usbConnection.transitionTo(usbConnection.mUsbConnectedState);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleUsbPowerState(Message msg) {
            int powerValue = msg.arg1;
            int powerResult = msg.arg2;
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_AUTO) {
                if (powerValue == UsbPortPower.DISABLE_POWER.getValue()) {
                    if (powerResult != UsbStatus4Ext.SUCCESS.getValue()) {
                        UsbConnection.this.notifyUccDeviceResetDone(0);
                        this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                        UsbConnection usbConnection = UsbConnection.this;
                        usbConnection.transitionTo(usbConnection.mUsbConnectedState);
                        return;
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (UsbConnection.this.setUsbPower(UsbPortPower.ENABLE_POWER)) {
                        UsbConnection.this.sendMessage(135);
                        return;
                    }
                    Log.d(UsbConnection.LOG_TAG, "device setUsbPower failed. deivce:" + LogEncrypt.IpAddressEncrypt(UsbConnection.this.mLinkDevice.getId()));
                    UsbConnection.this.notifyUccDeviceResetDone(0);
                    UsbConnection.this.updateUsbDevice(null);
                    UsbConnection.this.notifyUccDeviceDetach();
                    this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                    UsbConnection usbConnection2 = UsbConnection.this;
                    usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                    return;
                }
                if (powerResult != UsbStatus4Ext.SUCCESS.getValue()) {
                    Log.d(UsbConnection.LOG_TAG, "device power enable failed. deivce:" + LogEncrypt.IpAddressEncrypt(UsbConnection.this.mLinkDevice.getId()));
                    UsbConnection.this.notifyUccDeviceResetDone(0);
                    UsbConnection.this.updateUsbDevice(null);
                    UsbConnection.this.notifyUccDeviceDetach();
                    this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                    UsbConnection usbConnection3 = UsbConnection.this;
                    usbConnection3.transitionTo(usbConnection3.mUsbInactiveState);
                }
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
            this.mAccessoryDevAttached = false;
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbOperationState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i == 101) {
                boolean retVal = handleAttachEvent(msg);
                return retVal;
            }
            if (i == 102) {
                handleDetachEvent();
                return true;
            }
            if (i != UsbConnection.EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT) {
                switch (i) {
                    case UsbConnection.EVENT_DCM_ROLE_SWITCH_TO_DEVICE /* 105 */:
                        handleRoleSwitchDevEvent(msg);
                        return true;
                    case UsbConnection.EVENT_DCM_ROLE_SWITCH_TO_HOST /* 106 */:
                        handleRoleSwitchHostEvent(msg);
                        return true;
                    case UsbConnection.EVENT_DCM_USB_RESET /* 107 */:
                        handleResetEvent(msg);
                        return true;
                    case UsbConnection.EVENT_DCM_DISABLE_DEVICE /* 108 */:
                        UsbConnection.this.removeCurrentDeferMessage();
                        UsbConnection.this.deferMessage(msg);
                        return true;
                    default:
                        switch (i) {
                            case UsbConnection.EVENT_DCM_REMOVE_DEVICE /* 110 */:
                                Log.e(UsbConnection.LOG_TAG, "UsbOperationState defer remove message to prevent interrupting asynchronous processing");
                                UsbConnection.this.deferMessage(msg);
                                return true;
                            case UsbConnection.EVENT_DCM_SET_CAPABILITY /* 111 */:
                                UsbConnection.this.mUntreatedCapa = msg.arg1;
                                return true;
                            case UsbConnection.EVENT_DCM_CARLIFE_AOA_START /* 112 */:
                                handleCarlifeAoaStartEvent();
                                return true;
                            case UsbConnection.EVENT_DCM_ANDROID_ATUO_AOA_START /* 113 */:
                                String serialNumber = (String) msg.obj;
                                handleAndroidAutoAoaStartEvent(serialNumber);
                                return true;
                            default:
                                switch (i) {
                                    case UsbConnection.EVENT_USB_ACCESSORY_ATTACH /* 118 */:
                                        UsbConnection.this.updateUsbDevice(null);
                                        this.mAccessoryDevAttached = true;
                                        checkIapNode();
                                        return true;
                                    case UsbConnection.EVENT_USB_ACCESSORY_DETACH /* 119 */:
                                        if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARLIFE || this.mCurrentService == ServiceType.USB_SERVICE_TYPE_IOS_CARPLAY) {
                                            UsbConnection.this.removeMessages(134);
                                            this.mAccessoryDevAttached = false;
                                            UsbConnection.this.updateUsbDevice(null);
                                            UsbConnection.this.notifyUccDeviceDetach();
                                            UsbConnection.this.setPortMode(0);
                                            UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                                            UsbConnection usbConnection = UsbConnection.this;
                                            usbConnection.transitionTo(usbConnection.mUsbInactiveState);
                                        } else if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET) {
                                            if (UsbConnection.this.mLinkDevice.getDeviceType() == 2) {
                                                UsbConnection.this.setPortMode(0);
                                            }
                                            Log.e(UsbConnection.LOG_TAG, "UsbOperationState device accessory detach in reset mode.");
                                        }
                                        return true;
                                    case UsbConnection.EVENT_USB_IAPNODE_CREATE /* 120 */:
                                        if (this.mAccessoryDevAttached) {
                                            handleRoleSwitchSuccess();
                                        }
                                        return true;
                                    default:
                                        switch (i) {
                                            case 131:
                                                handleAoaOperatorDoneEvent(msg);
                                                return true;
                                            case 132:
                                                if (msg.arg1 == 1) {
                                                    UsbConnection.this.removeMessages(134);
                                                    UsbConnection.this.mLinkDevice.setIapNode(null);
                                                    UsbConnection.this.mLinkDevice.setUsbNcmNode(null);
                                                    UsbConnection.this.notifyUccRoleSwitchDone(1, 0);
                                                    UsbConnection usbConnection2 = UsbConnection.this;
                                                    usbConnection2.transitionTo(usbConnection2.mUsbConnectedState);
                                                }
                                                return true;
                                            case 133:
                                                if (msg.arg1 == 1) {
                                                    UsbConnection.this.removeMessages(UsbConnection.EVENT_INTERNAL_USB_SWITCH_HOST_OPERATION_TIMEOUT);
                                                    UsbConnection.this.notifyUccRoleSwitchDone(0, 0);
                                                    UsbConnection usbConnection3 = UsbConnection.this;
                                                    usbConnection3.transitionTo(usbConnection3.mUsbConnectedState);
                                                } else {
                                                    UsbConnection.this.mLinkDevice.setIapNode(null);
                                                    UsbConnection.this.mLinkDevice.setUsbNcmNode(null);
                                                    UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                                                }
                                                return true;
                                            case 134:
                                                handleUsbOperatorTimeOutEvent();
                                                return true;
                                            case 135:
                                                handleResetEvent(msg);
                                                return true;
                                            case 136:
                                                Log.e(UsbConnection.LOG_TAG, "UsbOperationState usb reset operation timed out. deivce:" + LogEncrypt.IpAddressEncrypt(UsbConnection.this.mLinkDevice.getId()));
                                                UsbConnection.this.setPortMode(0);
                                                UsbConnection.this.notifyUccDeviceResetDone(0);
                                                UsbConnection.this.updateUsbDevice(null);
                                                UsbConnection.this.notifyUccDeviceDetach();
                                                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                                                UsbConnection usbConnection4 = UsbConnection.this;
                                                usbConnection4.transitionTo(usbConnection4.mUsbInactiveState);
                                                return true;
                                            case 137:
                                                if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_RESET) {
                                                    UsbConnection.this.removeMessages(136);
                                                } else if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_ANDROID_CARLIFE) {
                                                    UsbConnection.this.removeMessages(134);
                                                }
                                                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                                                UsbConnection.this.updateUsbDevice(null);
                                                UsbConnection.this.notifyUccDeviceDetach();
                                                UsbConnection usbConnection5 = UsbConnection.this;
                                                usbConnection5.transitionTo(usbConnection5.mUsbInactiveState);
                                                return true;
                                            case 138:
                                                handleSetUsbConfigResult(msg);
                                                return true;
                                            case 139:
                                                handleSetUsbFunctionsTimeout();
                                                return true;
                                            case 140:
                                                handleRoleSwitchResult(msg);
                                                return true;
                                            case 141:
                                                handleUsbPowerState(msg);
                                                return true;
                                            case 142:
                                                Log.d(UsbConnection.LOG_TAG, "device reset usb failed. deivce:" + LogEncrypt.IpAddressEncrypt(UsbConnection.this.mLinkDevice.getId()));
                                                UsbConnection.this.notifyUccDeviceResetDone(0);
                                                this.mCurrentService = ServiceType.USB_SERVICE_TYPE_NONE;
                                                UsbConnection usbConnection6 = UsbConnection.this;
                                                usbConnection6.transitionTo(usbConnection6.mUsbConnectedState);
                                                return true;
                                            default:
                                                return false;
                                        }
                                }
                        }
                }
            }
            if (this.mCurrentService == ServiceType.USB_SERVICE_TYPE_HOST) {
                UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                UsbConnection.this.updateUsbDevice(null);
                UsbConnection.this.notifyUccDeviceDetach();
                UsbConnection usbConnection7 = UsbConnection.this;
                usbConnection7.transitionTo(usbConnection7.mUsbInactiveState);
            }
            return true;
        }
    }

    private class UsbDisconnected extends State {
        private UsbDisconnected() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbDisconnected msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i != 102) {
                switch (i) {
                    case UsbConnection.EVENT_DCM_ENABLE_DEVICE /* 109 */:
                        UsbConnection.this.mLinkDevice.setDeviceState(3);
                        UsbConnection.this.notifyUccDeviceStatusChanged(5);
                        UsbConnection usbConnection = UsbConnection.this;
                        usbConnection.transitionTo(usbConnection.mUsbConnectedState);
                        break;
                    case UsbConnection.EVENT_DCM_REMOVE_DEVICE /* 110 */:
                        UsbConnection usbConnection2 = UsbConnection.this;
                        usbConnection2.transitionTo(usbConnection2.mUsbRemovedState);
                        break;
                    case UsbConnection.EVENT_DCM_SET_CAPABILITY /* 111 */:
                        UsbConnection.this.updateDeviceCapabilityBySetting(msg.arg1);
                        break;
                    default:
                        Log.e(UsbConnection.LOG_TAG, "UsbDisconnected shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
                        break;
                }
                return false;
            }
            UsbConnection.this.updateUsbDevice(null);
            UsbConnection.this.notifyUccDeviceDetach();
            if (UsbConnection.this.mUntreatedDevice) {
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mInvalidState);
                return false;
            }
            UsbConnection usbConnection4 = UsbConnection.this;
            usbConnection4.transitionTo(usbConnection4.mUsbInactiveState);
            return false;
        }
    }

    private class UsbRemovedState extends State {
        private UsbRemovedState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(UsbConnection.LOG_TAG, "UsbRemovedState enter.");
            UsbConnection.this.notifyUccDeviceDetach();
            UsbConnection.this.setPortMode(0);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            UsbConnection.this.log("UsbRemovedState msg:" + UsbConnection.this.getWhatToString(msg.what));
            int i = msg.what;
            if (i == 102) {
                UsbConnection.this.updateUsbDevice(null);
                if (UsbConnection.this.mUntreatedDevice) {
                    UsbConnection usbConnection = UsbConnection.this;
                    usbConnection.transitionTo(usbConnection.mInvalidState);
                } else {
                    UsbConnection usbConnection2 = UsbConnection.this;
                    usbConnection2.transitionTo(usbConnection2.mUsbInactiveState);
                }
                return true;
            }
            if (i == UsbConnection.EVENT_USB_ACCESSORY_DETACH) {
                UsbConnection.this.updateUsbDevice(null);
                UsbConnection.this.setPortMode(0);
                UsbConnection.this.notifyUccRoleSwitchDone(0, 1);
                UsbConnection usbConnection3 = UsbConnection.this;
                usbConnection3.transitionTo(usbConnection3.mUsbInactiveState);
                return true;
            }
            UsbConnection.this.log("UsbRemovedState shouldn't happen but ignore msg.what:" + UsbConnection.this.getWhatToString(msg.what));
            return false;
        }
    }

    @Override // com.ts.car.dcm.utils.statemachine.StateMachine
    protected void log(String info) {
        Log.d(LOG_TAG, "[conn id " + getUsbConnectionId() + "] " + info);
    }

    @Override // com.ts.car.dcm.utils.statemachine.StateMachine
    protected String getWhatToString(int what) {
        return cmdToString(what);
    }

    private final class OperationHandler extends Handler {
        static final int OPERATOR_AOA_SEND_CT53_FAIL = 2;
        static final int OPERATOR_AOA_SEND_DATA_DONE = 0;
        static final int OPERATOR_AOA_SEND_DATA_FAIL = 1;
        static final int OPERATOR_AOA_SWITCHED_AOA_DONE = 3;
        static final int OPERATOR_EVENT_BUS_RESET = 4;
        static final int OPERATOR_EVENT_GET_DEVICE_CAPABILITY = 0;
        static final int OPERATOR_EVENT_ROLE_SWITCH_TO_APPLE = 1;
        static final int OPERATOR_EVENT_ROLE_SWITCH_TO_IVI = 2;
        static final int OPERATOR_EVENT_SWITCH_AOA = 3;
        static final int OPERATOR_EVENT_SWITCH_AOA_FOR_ANDROIDAUTO = 5;
        static final int OPERATOR_ROLE_SWITCH_RESULT_FAIL = 1;
        static final int OPERATOR_ROLE_SWITCH_RESULT_SUCCESS = 0;
        static final int OPERATOR_TYPE_ANDROID_AUTO_AOA_MODE = 1;
        static final int OPERATOR_TYPE_CARLIFE_AOA_MODE = 0;
        protected static final String TAG = "OperationHandler";
        Handler mStateMachineHandler;

        public OperationHandler(Handler smHandler, Looper looper) {
            super(looper);
            this.mStateMachineHandler = smHandler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage msg:" + msg);
            int i = msg.what;
            if (i == 0) {
                int devCap = UsbConnection.this.mUsbDeviceDetect.detectDeviceCapability(UsbConnection.this.mUsbDevice, UsbConnection.this.mLinkDevice, UsbConnection.this.getUsbConnectionId());
                Message message = Message.obtain(this.mStateMachineHandler, 130);
                message.arg1 = devCap;
                this.mStateMachineHandler.sendMessage(message);
                return;
            }
            if (i == 1) {
                boolean result = UsbConnection.this.mUsbDeviceDetect.sendRoleSwitchToApple(UsbConnection.this.mUsbDevice, msg.arg1, UsbConnection.this.getUsbConnectionId());
                if (result) {
                    UsbConnection.this.setPortMode(1);
                    return;
                }
                Message message2 = Message.obtain(this.mStateMachineHandler, 132);
                message2.arg1 = 1;
                this.mStateMachineHandler.sendMessage(message2);
                return;
            }
            if (i == 2) {
                Message message3 = Message.obtain(this.mStateMachineHandler, 133);
                if (UsbConnection.this.setPortMode(0)) {
                    message3.arg1 = 0;
                } else {
                    message3.arg1 = 1;
                }
                this.mStateMachineHandler.sendMessage(message3);
                return;
            }
            if (i == 3) {
                int result2 = 1;
                int aoaType = msg.arg1;
                UsbDeviceDetect.AoaStatus aoastatus = UsbDeviceDetect.AoaStatus.AOA_STATUS_FAIL;
                if (aoaType == 0) {
                    aoastatus = UsbConnection.this.mUsbDeviceDetect.switchAccessory(UsbConnection.this.mUsbDevice, UsbDeviceDetect.AoaProjection.AOA_PROJECTION_CARLIFE, UsbConnection.this.getUsbConnectionId());
                } else if (aoaType == 1) {
                    aoastatus = UsbConnection.this.mUsbDeviceDetect.switchAccessory(UsbConnection.this.mUsbDevice, UsbDeviceDetect.AoaProjection.AOA_PROJECTION_ANDROIDAUTO, UsbConnection.this.getUsbConnectionId());
                }
                if (aoastatus == UsbDeviceDetect.AoaStatus.AOA_STATUS_SEND_REQUEST_SUCCESS) {
                    result2 = 0;
                } else if (aoastatus == UsbDeviceDetect.AoaStatus.AOA_STATUS_ALREADY_AOA) {
                    result2 = 3;
                } else if (aoastatus == UsbDeviceDetect.AoaStatus.AOA_STATUS_CT53_FAIL) {
                    result2 = 2;
                }
                Message message4 = Message.obtain(this.mStateMachineHandler, 131);
                message4.arg1 = aoaType;
                message4.arg2 = result2;
                this.mStateMachineHandler.sendMessage(message4);
                return;
            }
            if (i == 4) {
                if (UsbConnection.this.resetUsb()) {
                    this.mStateMachineHandler.sendMessage(Message.obtain(this.mStateMachineHandler, 135));
                    return;
                } else {
                    this.mStateMachineHandler.sendMessage(Message.obtain(this.mStateMachineHandler, 142));
                    return;
                }
            }
            if (i == 5) {
                int result3 = 1;
                String serialNumber = (String) msg.obj;
                int aoaType2 = msg.arg1;
                UsbDeviceDetect.AoaStatus aoastatus2 = UsbDeviceDetect.AoaStatus.AOA_STATUS_FAIL;
                if (aoaType2 == 0) {
                    aoastatus2 = UsbConnection.this.mUsbDeviceDetect.switchAccessory(UsbConnection.this.mUsbDevice, UsbDeviceDetect.AoaProjection.AOA_PROJECTION_CARLIFE, UsbConnection.this.getUsbConnectionId());
                } else if (aoaType2 == 1) {
                    aoastatus2 = UsbConnection.this.mUsbDeviceDetect.switchAccessory(UsbConnection.this.mUsbDevice, serialNumber, UsbDeviceDetect.AoaProjection.AOA_PROJECTION_ANDROIDAUTO, UsbConnection.this.getUsbConnectionId());
                }
                if (aoastatus2 == UsbDeviceDetect.AoaStatus.AOA_STATUS_SEND_REQUEST_SUCCESS) {
                    result3 = 0;
                } else if (aoastatus2 == UsbDeviceDetect.AoaStatus.AOA_STATUS_ALREADY_AOA) {
                    result3 = 3;
                } else if (aoastatus2 == UsbDeviceDetect.AoaStatus.AOA_STATUS_CT53_FAIL) {
                    result3 = 2;
                }
                Message message5 = Message.obtain(this.mStateMachineHandler, 131);
                message5.arg1 = aoaType2;
                message5.arg2 = result3;
                this.mStateMachineHandler.sendMessage(message5);
            }
        }
    }
}
