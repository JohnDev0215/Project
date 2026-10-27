package com.ts.car.dcm.usbcontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.subtitle.Cea708CCParser;
import android.text.TextUtils;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import ts.car.usb.UsbManagerExt;
import ts.car.usb.v1.ext.IUsbExtPowerCallback;
import ts.car.usb.v1.ext.UsbPortPower;
import ts.car.usb.v1.ext.UsbStatus4Ext;
import ts.car.usb.v1.ext.UsbUevent;
import ts.car.usb.v1.usb.IUsbRoleSwitchCallback;
import ts.car.usb.v1.usb.UsbPortRole;
import ts.car.usb.v1.usb.UsbPortStatus;
import ts.car.usb.v1.usb.UsbStatus;

/* JADX INFO: loaded from: classes.dex */
public class UsbDeviceMonitor extends Handler {
    protected static final boolean DBG = true;
    protected static final String TAG = UsbDeviceMonitor.class.getSimpleName();
    private String mAccessoryStateMatch;
    private AtomicInteger mAtomInteger;
    private BroadcastReceiver mBroadcastReceiver;
    private Object mConnHashLock;
    private Context mContext;
    private String mIpodoutMtach;
    private boolean mIsStarted;
    private List<String> mNoResponseDevices;
    private int mPlatformCapability;
    private List<String> mUnSupportDevices;
    private UsbConfiguration mUsbConfiguration;
    private UsbConnController mUsbConnController;
    private HashMap<Integer, UsbConnection> mUsbConnections;
    private UsbDeviceDetect mUsbDeviceDetect;
    private List<String> mUsbHubDevices;
    private UsbManager mUsbManager;
    private UsbManagerExt mUsbManagerExt;
    private String[] mUsbUeventMatchPaths;

    public UsbDeviceMonitor(UsbConnController connController, Looper looper) {
        super(looper);
        this.mConnHashLock = new Object();
        this.mIsStarted = false;
        this.mUnSupportDevices = new ArrayList();
        this.mUsbHubDevices = new ArrayList();
        this.mNoResponseDevices = new ArrayList();
        this.mAtomInteger = new AtomicInteger(0);
        this.mUsbConnections = new HashMap<>();
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.ts.car.dcm.usbcontroller.UsbDeviceMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.e(UsbDeviceMonitor.TAG, "mBroadcastReceiver, action:" + action);
                if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra("device");
                    if (device != null) {
                        HookUsbDeviceUtils.hookFilterUsbSerialNumber(device);
                    }
                    Log.e(UsbDeviceMonitor.TAG, "mBroadcastReceiver, UsbDevice:" + device);
                    Message msg = UsbDeviceMonitor.this.obtainMessage(1);
                    msg.arg1 = 0;
                    msg.obj = device;
                    UsbDeviceMonitor.this.sendMessage(msg);
                    return;
                }
                if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                    UsbDevice device2 = (UsbDevice) intent.getParcelableExtra("device");
                    if (device2 != null) {
                        HookUsbDeviceUtils.hookFilterUsbSerialNumber(device2);
                    }
                    Message msg2 = UsbDeviceMonitor.this.obtainMessage(2);
                    msg2.arg1 = 1;
                    msg2.obj = device2;
                    UsbDeviceMonitor.this.sendMessage(msg2);
                }
            }
        };
        this.mUsbConnController = connController;
        this.mUsbConfiguration = UsbConfiguration.getInstance();
        this.mUsbDeviceDetect = UsbDeviceDetect.getInstance();
    }

    public void init(Context context, int capability) {
        this.mContext = context;
        this.mPlatformCapability = capability;
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        this.mUsbDeviceDetect.init(context, capability);
        UsbManagerExt usbManagerExt = UsbManagerExt.getInstance();
        this.mUsbManagerExt = usbManagerExt;
        usbManagerExt.setExtCallback(new UsbExtCallback());
        this.mUsbManagerExt.setCallback(new UsbCallback());
    }

    public void updateCapability(int capability) {
        this.mPlatformCapability = capability;
        this.mUsbDeviceDetect.updateCapability(capability);
        Message msg = obtainMessage(23);
        msg.arg1 = capability;
        sendMessage(msg);
    }

    public void updateCarlifeSupportStatus(int deviceType, boolean flag) {
        this.mUsbDeviceDetect.updateCarlifeSupportStatus(deviceType, flag);
    }

    public UsbDevice getUsbDevice(String serial) {
        UsbDevice usbDevice = null;
        synchronized (this.mConnHashLock) {
            Iterator<UsbConnection> it = this.mUsbConnections.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UsbConnection uc = it.next();
                if (uc.getSerial() != null && uc.getSerial().contentEquals(serial)) {
                    usbDevice = uc.getUsbDevice();
                    break;
                }
            }
        }
        return usbDevice;
    }

    private void updatePlatformCap(int capability) {
        for (UsbConnection uc : this.mUsbConnections.values()) {
            Message msg = Message.obtain();
            msg.what = 111;
            msg.arg1 = capability;
            uc.getHandler().sendMessage(msg);
        }
    }

    private UsbConnection getConnection(String serial) {
        UsbConnection usbConnection = null;
        Iterator<UsbConnection> it = this.mUsbConnections.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UsbConnection uc = it.next();
            if (uc.getSerial() != null && uc.getSerial().contentEquals(serial)) {
                usbConnection = uc;
                break;
            }
        }
        if (usbConnection == null) {
            Log.e(TAG, "getConnection, could not match uc for:" + serial);
        }
        return usbConnection;
    }

    private void registerUsbBroadCast() {
        if (this.mUsbManager != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            this.mContext.registerReceiver(this.mBroadcastReceiver, filter, null, null);
        }
        this.mAccessoryStateMatch = this.mUsbConfiguration.getDeviceAccessoryMatchPath();
        this.mIpodoutMtach = this.mUsbConfiguration.getDeviceIpodOutMatchPath();
    }

    private class UsbExtCallback extends IUsbExtPowerCallback {
        private UsbExtCallback() {
        }

        @Override // ts.car.usb.v1.ext.IUsbExtPowerCallback
        public void notifyPortConnectStatusChange(String portName, UsbUevent events) {
            Log.d(UsbDeviceMonitor.TAG, "USB UEVENT: " + events);
            if (events != UsbUevent.DISCONNECT) {
                UsbUevent usbUevent = UsbUevent.CONNECT;
            }
        }

        @Override // ts.car.usb.v1.ext.IUsbExtPowerCallback
        public void notifyCurrentPortPower(String portName, UsbPortPower currentValue, UsbStatus4Ext retval) {
        }

        @Override // ts.car.usb.v1.ext.IUsbExtPowerCallback
        public void notifyPowerChangeStatus(String portName, UsbPortPower newValue, UsbStatus4Ext retval) {
        }

        @Override // ts.car.usb.v1.ext.IUsbExtPowerCallback
        public void notifyRoleSwitchPaths(String[] roleSwitchPaths) {
            Log.d(UsbDeviceMonitor.TAG, "notifyRoleSwitchPaths, roleSwitchPaths:" + roleSwitchPaths.length);
            UsbDeviceMonitor.this.mUsbConfiguration.setDeviceRoleSwitchDevBusPath(roleSwitchPaths);
        }
    }

    private class UsbCallback extends IUsbRoleSwitchCallback {
        private UsbCallback() {
        }

        @Override // ts.car.usb.v1.usb.IUsbRoleSwitchCallback
        public void notifyPortStatusChange(ArrayList<UsbPortStatus> currentPortStatus, UsbStatus retval) {
        }

        @Override // ts.car.usb.v1.usb.IUsbRoleSwitchCallback
        public void notifyRoleSwitchStatus(String portName, UsbPortRole newRole, UsbStatus retval) {
            Log.d(UsbDeviceMonitor.TAG, "notifyRoleSwitchStatus " + portName + "role.role = " + newRole.role + ", retval:" + retval.getValue());
            Message msg = UsbDeviceMonitor.this.obtainMessage(28);
            msg.obj = portName;
            msg.arg1 = newRole.role;
            msg.arg2 = retval.getValue();
            UsbDeviceMonitor.this.sendMessage(msg);
        }
    }

    private List<UsbDevice> getUsbDevices() {
        List<UsbDevice> devList = Collections.emptyList();
        if (this.mUsbManager != null) {
            List<UsbDevice> devList2 = new ArrayList<>(this.mUsbManager.getDeviceList().values());
            return devList2;
        }
        Log.e(TAG, "getUsbDevices, mUsbManager is null.");
        return devList;
    }

    private void checkExistingUsbDevcies() {
        Collections.emptyList();
        List<UsbDevice> hostDevList = getUsbDevices();
        if (!hostDevList.isEmpty()) {
            for (UsbDevice usbDevice : hostDevList) {
                processUsbEvent(usbDevice, 0);
            }
        }
    }

    private void enableConnection(String serial) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            Message msg = Message.obtain();
            msg.what = 109;
            uc.getHandler().sendMessage(msg);
        } else {
            Log.e(TAG, "enableConnection, could not match:" + LogEncrypt.IpAddressEncrypt(serial));
        }
    }

    private void disableConnection(String serial) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            Message msg = Message.obtain();
            msg.what = 108;
            uc.getHandler().sendMessage(msg);
        } else {
            Log.e(TAG, "disableConnection, could not match:" + LogEncrypt.IpAddressEncrypt(serial));
        }
    }

    private void usbReset(String serial) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            Message msg = Message.obtain();
            msg.what = 107;
            uc.getHandler().sendMessage(msg);
        } else {
            Log.e(TAG, "usbReset, could not match:" + LogEncrypt.IpAddressEncrypt(serial));
        }
    }

    private void usbRoleSwitch(String serial, int service, int mode) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            if (mode == 0) {
                Message msg = Message.obtain();
                msg.what = 106;
                msg.arg1 = service;
                uc.getHandler().sendMessage(msg);
                return;
            }
            if (mode == 1) {
                Message msg2 = Message.obtain();
                msg2.what = 105;
                msg2.arg1 = service;
                uc.getHandler().sendMessage(msg2);
                return;
            }
            return;
        }
        Log.e(TAG, "usbRoleSwitch, could not match:" + serial);
    }

    private void enableAoa(String serial, int mode, int flag) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            if (mode == 0) {
                Message msg = Message.obtain();
                if (flag > 0) {
                    msg.what = 112;
                } else {
                    msg.what = 116;
                }
                uc.getHandler().sendMessage(msg);
                return;
            }
            if (mode == 1) {
                Message msg2 = Message.obtain();
                if (flag > 0) {
                    msg2.what = 113;
                    msg2.obj = "";
                } else {
                    msg2.what = 117;
                }
                uc.getHandler().sendMessage(msg2);
                return;
            }
            return;
        }
        Log.e(TAG, "enableAoa, could not match:" + serial);
    }

    private void enableAoa(String serial, String serialNumber, int mode, int flag) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            if (mode == 0) {
                Message msg = Message.obtain();
                if (flag > 0) {
                    msg.what = 112;
                } else {
                    msg.what = 116;
                }
                uc.getHandler().sendMessage(msg);
                return;
            }
            if (mode == 1) {
                Message msg2 = Message.obtain();
                if (flag > 0) {
                    msg2.what = 113;
                    msg2.obj = serialNumber;
                } else {
                    msg2.what = 117;
                    msg2.obj = serialNumber;
                }
                uc.getHandler().sendMessage(msg2);
                return;
            }
            return;
        }
        Log.e(TAG, "enableAoa, could not match:" + serial);
    }

    private void acceptDevice(String serial, boolean allow) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            if (allow) {
                Message msg = Message.obtain();
                msg.what = 103;
                uc.getHandler().sendMessage(msg);
                return;
            } else {
                Message msg2 = Message.obtain();
                msg2.what = 104;
                uc.getHandler().sendMessage(msg2);
                return;
            }
        }
        Log.e(TAG, "acceptDevice, could not match:" + serial);
    }

    private UsbConnection findFreeConnection() {
        for (UsbConnection uc : this.mUsbConnections.values()) {
            if (uc.getIsInvalid() && !uc.getIsUsed()) {
                uc.setUse(true);
                Log.d(TAG, "findFreeConnection, found free UsbConnection dcac:" + uc);
                return uc;
            }
        }
        Log.d(TAG, "findFreeConnection, NO free UsbConnection.");
        return null;
    }

    private void handleUsbAttachEvent(UsbDevice usbDevice) {
        Log.e(TAG, "handleUsbAttachEvent, usbDevice:" + usbDevice);
        showDeviceInfoOnHostmode(usbDevice);
        UsbConnection usbConnection = null;
        if (this.mUsbConnections.isEmpty()) {
            Log.d(TAG, "handleUsbAttachEvent, mUsbConnections is null.");
            UsbConnection usbConnection2 = createUsbConnection(usbDevice);
            if (usbConnection2 != null) {
                Message msg = Message.obtain();
                msg.what = 100;
                usbConnection2.getHandler().sendMessage(msg);
                return;
            }
            Log.e(TAG, "handleUsbAttachEvent, create usbconnection failure.");
            return;
        }
        if (usbDevice.getSerialNumber() != null) {
            usbConnection = getConnection(usbDevice.getSerialNumber());
        }
        if (usbConnection != null) {
            Message msg2 = Message.obtain();
            msg2.obj = usbDevice;
            msg2.what = 101;
            usbConnection.getHandler().sendMessage(msg2);
            return;
        }
        for (UsbConnection connection : this.mUsbConnections.values()) {
            if (connection.getUsbDevice() != null && isSameUsbPort(connection.getUsbDevice().getDeviceName(), usbDevice.getDeviceName()) && (connection.isResetState() || connection.isAndroidAoaState())) {
                Message msg3 = Message.obtain();
                msg3.what = Cea708CCParser.Const.CODE_C1_DSW;
                connection.getHandler().sendMessage(msg3);
            }
        }
        UsbConnection newUsbConnection = findFreeConnection();
        if (newUsbConnection == null) {
            newUsbConnection = createUsbConnection(usbDevice);
        } else {
            Message msg4 = Message.obtain();
            msg4.what = 114;
            msg4.obj = usbDevice;
            newUsbConnection.getHandler().sendMessage(msg4);
        }
        if (newUsbConnection != null) {
            Message msg5 = Message.obtain();
            msg5.what = 100;
            newUsbConnection.getHandler().sendMessage(msg5);
        }
    }

    private void handleUsbDetachEvent(UsbDevice usbDevice) {
        UsbConnection usbConnection;
        if (usbDevice.getSerialNumber() != null) {
            usbConnection = getConnection(usbDevice.getSerialNumber());
        } else {
            usbConnection = getConnection(usbDevice.getDeviceName());
        }
        if (usbConnection != null) {
            Message msg = Message.obtain();
            msg.what = 102;
            usbConnection.getHandler().sendMessage(msg);
        } else {
            Log.e(TAG, "handleUsbDetachEvent, Could not match the device:" + LogEncrypt.IpAddressEncrypt(usbDevice.getSerialNumber()));
        }
    }

    private void processUsbEvent(UsbDevice usbDevice, int state) {
        Log.e(TAG, "processUsbEvent, usbDevice:" + usbDevice);
        if (this.mUsbConfiguration.blockBuiltInUsb() && this.mUsbDeviceDetect.isBuiltInUsbDevice(usbDevice)) {
            return;
        }
        if (usbDevice != null) {
            HookUsbDeviceUtils.hookFilterUsbSerialNumber(usbDevice);
        }
        if (this.mUsbConfiguration.isMonitorUnSupportDevice() && this.mUsbDeviceDetect.isUnSupportUsbDevice(usbDevice)) {
            String deviceId = usbDevice.getSerialNumber();
            if (usbDevice.getSerialNumber() == null) {
                deviceId = usbDevice.getDeviceName();
            }
            if (state == 0) {
                this.mUnSupportDevices.add(deviceId);
                this.mUsbConnController.notifyAbnormalDeviceAttach(deviceId, 1, 1);
                return;
            } else {
                if (state == 1) {
                    this.mUnSupportDevices.remove(deviceId);
                    this.mUsbConnController.notifyAbnormalDeviceDetach(deviceId);
                    return;
                }
                return;
            }
        }
        if (state == 0) {
            handleUsbAttachEvent(usbDevice);
        } else if (state == 1 && usbDevice != null) {
            handleUsbDetachEvent(usbDevice);
        }
    }

    private boolean isSameUsbPort(String firstUsbPath, String secondUsbPath) {
        if (firstUsbPath != null && firstUsbPath.isEmpty() && secondUsbPath != null && secondUsbPath.isEmpty()) {
            String[] usbPaths = this.mUsbConfiguration.getDeviceRoleSwitchDevBusPath();
            for (String usbPath : usbPaths) {
                if (firstUsbPath.contains(usbPath) && secondUsbPath.contains(usbPath)) {
                    return true;
                }
            }
        }
        return false;
    }

    private UsbConnection createUsbConnection(UsbDevice usbDevice) {
        int id = this.mAtomInteger.getAndIncrement();
        UsbConnection conn = UsbConnection.makeUsbConnection(id, this, usbDevice, this.mContext);
        if (conn != null) {
            synchronized (this.mConnHashLock) {
                if (this.mIsStarted) {
                    this.mUsbConnections.put(Integer.valueOf(id), conn);
                }
            }
        }
        return conn;
    }

    private void showDeviceInfoOnHostmode(UsbDevice usbDevice) {
        if (usbDevice != null) {
            Log.d(TAG, "showDeviceInfoOnHostmode, [Host mode device name: (" + usbDevice.getProductName() + ") serial:(" + LogEncrypt.IpAddressEncrypt(usbDevice.getSerialNumber()) + ") product id:(" + usbDevice.getProductId() + ") vendor id:(" + usbDevice.getVendorId() + ") ]");
        }
    }

    private void removeDevice(String serial) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            Message msg = Message.obtain();
            msg.what = 110;
            uc.getHandler().sendMessage(msg);
        }
    }

    public boolean deleteUsbConnection(String serial) {
        int ucId = -1;
        Iterator<Map.Entry<Integer, UsbConnection>> it = this.mUsbConnections.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Integer, UsbConnection> entry = it.next();
            UsbConnection uc = entry.getValue();
            if (!TextUtils.isEmpty(uc.getSerial()) && uc.getSerial().equals(serial)) {
                ucId = entry.getKey().intValue();
                this.mUsbConnections.remove(Integer.valueOf(ucId));
                break;
            }
        }
        if (ucId != -1 && this.mUsbConnections.get(Integer.valueOf(ucId)) != null) {
            Log.d(TAG, "deleteUsbConnection, failure, serial:" + LogEncrypt.IpAddressEncrypt(serial));
            return false;
        }
        if (ucId != -1 && this.mUsbConnections.get(Integer.valueOf(ucId)) == null) {
            Log.d(TAG, "deleteUsbConnection, success, serial:" + LogEncrypt.IpAddressEncrypt(serial));
            return true;
        }
        Log.d(TAG, "deleteUsbConnection, no usbConnection, serial: " + LogEncrypt.IpAddressEncrypt(serial));
        return true;
    }

    private void setCapability(String serial, int capability) {
        UsbConnection uc = getConnection(serial);
        if (uc != null) {
            Message msg = Message.obtain();
            msg.what = 111;
            msg.arg1 = capability;
            uc.getHandler().sendMessage(msg);
        }
    }

    private void resetDevMonitor() {
        Log.d(TAG, "resetDevMonitor.");
        if (this.mIsStarted) {
            this.mIsStarted = false;
            BroadcastReceiver broadcastReceiver = this.mBroadcastReceiver;
            if (broadcastReceiver != null) {
                this.mContext.unregisterReceiver(broadcastReceiver);
                this.mBroadcastReceiver = null;
            }
            synchronized (this.mConnHashLock) {
                for (UsbConnection uc : this.mUsbConnections.values()) {
                    Message msg = Message.obtain();
                    msg.what = 115;
                    uc.getHandler().sendMessage(msg);
                }
                this.mUsbConnections.clear();
            }
        }
    }

    private UsbConnection getUsbConnectionByPortName(String portName) {
        String otgPath = this.mUsbConfiguration.getOtgPortPathByPortName(portName);
        for (UsbConnection usbConnectionTemp : this.mUsbConnections.values()) {
            UsbDevice usbDevice = usbConnectionTemp.getUsbDevice();
            if (usbDevice != null) {
                if (usbDevice.getDeviceName().contains(otgPath)) {
                    return usbConnectionTemp;
                }
            } else {
                String deviceName = usbConnectionTemp.getUsbDeviceName();
                if (deviceName != null && deviceName.contains(otgPath)) {
                    return usbConnectionTemp;
                }
            }
        }
        return null;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        boolean allow;
        Log.d(TAG, "handleMessage msg:" + msg.what);
        if (!this.mIsStarted && msg.what != 0) {
            Log.e(TAG, "handleMessage, stop handle messages without EVENT_START_MONITOR.");
        }
        switch (msg.what) {
            case 0:
                if (!this.mIsStarted) {
                    this.mIsStarted = true;
                    this.mUsbConfiguration.getConfigInfo(this.mUsbConnController);
                    if (this.mUsbConfiguration.blockBuiltInUsb()) {
                        this.mUsbDeviceDetect.parseBuiltInUsbInfo();
                    }
                    if (this.mUsbConfiguration.isMonitorUnSupportDevice()) {
                        this.mUsbDeviceDetect.parseUnSupportDeviceInfo();
                    }
                    this.mUsbDeviceDetect.getStorageCarlifeSupportStatus();
                    registerUsbBroadCast();
                    checkExistingUsbDevcies();
                }
                break;
            case 1:
                int state = msg.arg1;
                UsbDevice usbDevice = (UsbDevice) msg.obj;
                processUsbEvent(usbDevice, state);
                break;
            case 2:
                int state2 = msg.arg1;
                UsbDevice usbDevice2 = (UsbDevice) msg.obj;
                processUsbEvent(usbDevice2, state2);
                break;
            case 3:
                LinkDevice linkDevice = (LinkDevice) msg.obj;
                this.mUsbConnController.notifyUsbDeviceAttach(linkDevice);
                break;
            case 4:
                String serial = (String) msg.obj;
                int contype = msg.arg1;
                this.mUsbConnController.notifyUsbDeviceDetach(serial, contype);
                break;
            case 5:
                int mode = msg.arg1;
                LinkDevice linkDevice2 = (LinkDevice) msg.obj;
                this.mUsbConnController.notifyUsbDeviceStatusChange(linkDevice2, mode);
                break;
            case 6:
                int mode2 = msg.arg1;
                String serial2 = (String) msg.obj;
                this.mUsbConnController.notifyDevRoleSwitchStarting(serial2, mode2);
                break;
            case 7:
                LinkDevice linkDevice3 = (LinkDevice) msg.obj;
                allow = msg.arg1 > 0;
                int mode3 = msg.arg2;
                this.mUsbConnController.notifyDevRoleSwitchDone(linkDevice3, mode3, allow);
                break;
            case 8:
                String serial3 = (String) msg.obj;
                this.mUsbConnController.notifyDevAoAStarting(serial3, msg.arg1);
                break;
            case 9:
                LinkDevice linkDevice4 = (LinkDevice) msg.obj;
                UsbConnController usbConnController = this.mUsbConnController;
                int i = msg.arg1;
                allow = msg.arg2 > 0;
                usbConnController.notifyDevAoAdone(linkDevice4, i, allow);
                break;
            case 10:
                String serial4 = (String) msg.obj;
                this.mUsbConnController.notifyDevResetStarting(serial4);
                break;
            case 11:
                String serial5 = (String) msg.obj;
                allow = msg.arg1 > 0;
                this.mUsbConnController.notifyDevResetDone(serial5, allow);
                break;
            case 15:
                String serial6 = (String) msg.obj;
                disableConnection(serial6);
                break;
            case 16:
                String serial7 = (String) msg.obj;
                enableConnection(serial7);
                break;
            case 17:
                String serial8 = (String) msg.obj;
                removeDevice(serial8);
                break;
            case 18:
                String serial9 = (String) msg.obj;
                usbRoleSwitch(serial9, msg.arg1, msg.arg2);
                break;
            case 19:
                String serial10 = (String) msg.obj;
                enableAoa(serial10, msg.arg1, msg.arg2);
                break;
            case 20:
                String serial11 = (String) msg.obj;
                allow = msg.arg1 > 0;
                acceptDevice(serial11, allow);
                break;
            case 21:
                String serial12 = (String) msg.obj;
                usbReset(serial12);
                break;
            case 22:
                String serial13 = (String) msg.obj;
                setCapability(serial13, msg.arg1);
                break;
            case 23:
                int cap = msg.arg1;
                updatePlatformCap(cap);
                break;
            case 24:
                resetDevMonitor();
                break;
            case 25:
                String portName = (String) msg.obj;
                UsbConnection usbConnection = getUsbConnectionByPortName(portName);
                if (usbConnection != null) {
                    Message message = Message.obtain();
                    message.what = 118;
                    usbConnection.getHandler().sendMessage(message);
                } else {
                    Log.e(TAG, "usb accessory attach, not found usbconnection.");
                }
                break;
            case 26:
                String portName2 = (String) msg.obj;
                UsbConnection usbConnection2 = getUsbConnectionByPortName(portName2);
                if (usbConnection2 != null) {
                    Message message2 = Message.obtain();
                    message2.what = 119;
                    usbConnection2.getHandler().sendMessage(message2);
                } else {
                    Log.e(TAG, "usb accessory dettach, not found usbconnection.");
                }
                break;
            case 28:
                String portName3 = (String) msg.obj;
                UsbConnection usbConnection3 = getUsbConnectionByPortName(portName3);
                if (usbConnection3 != null) {
                    Message message3 = Message.obtain();
                    message3.what = Cea708CCParser.Const.CODE_C1_DLW;
                    message3.arg1 = msg.arg1;
                    message3.arg2 = msg.arg2;
                    usbConnection3.getHandler().sendMessage(message3);
                } else {
                    Log.e(TAG, "usb roleswitch result, not found usbconnection.");
                }
                break;
            case 29:
                String portName4 = (String) msg.obj;
                UsbConnection usbConnection4 = getUsbConnectionByPortName(portName4);
                if (usbConnection4 != null) {
                    Message message4 = Message.obtain();
                    message4.what = Cea708CCParser.Const.CODE_C1_DLY;
                    message4.arg1 = msg.arg1;
                    message4.arg2 = msg.arg2;
                    usbConnection4.getHandler().sendMessage(message4);
                } else {
                    Log.e(TAG, "usb power status, not found usbconnection.");
                }
                break;
            case 30:
                String serial14 = (String) msg.obj;
                String devId = serial14.split(" ")[0];
                String serialNumber = serial14.split(" ")[1];
                enableAoa(devId, serialNumber, msg.arg1, msg.arg2);
                break;
        }
    }
}
