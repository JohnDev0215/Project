package com.ts.car.dcm.usbcontroller;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class UsbDeviceDetect {
    private static final String ACCESSORY_DESCRIPTION = "Android Auto";
    private static final String ACCESSORY_DESCRIPTION_CARLIFE = "Baidu  CarLife";
    private static final String ACCESSORY_MANUFACTURER_NAME = "Android";
    private static final String ACCESSORY_MANUFACTURER_NAME_CARLIFE = "Baidu";
    private static final String ACCESSORY_MODEL_NAME = "Android Auto";
    private static final String ACCESSORY_MODEL_NAME_CARLIFE = "CarLife";
    private static final int ACCESSORY_PRODUCT_ID0 = 11520;
    private static final int ACCESSORY_PRODUCT_ID1 = 11521;
    private static final int ACCESSORY_PRODUCT_ID4 = 11524;
    private static final int ACCESSORY_PRODUCT_ID5 = 11525;
    private static final String ACCESSORY_SERIAL_NUMBER = "0000000012345678";
    private static final String ACCESSORY_SERIAL_NUMBER_CARLIFE = "0720SerialNo";
    private static final String ACCESSORY_URI = "http://www.android.com/auto/";
    private static final String ACCESSORY_URI_CARLIFE = "http://carlife.baidu.com/";
    private static final int ACCESSORY_VENODR_ID = 6353;
    private static final String ACCESSORY_VERSION = "1.0";
    private static final String ACCESSORY_VERSION_CARLIFE = "1.0.0";
    private static final int AOAP_STR_DESCRIPTION = 2;
    private static final int AOAP_STR_MANUFACTURER_NAME = 0;
    private static final int AOAP_STR_MODEL_NAME = 1;
    private static final int AOAP_STR_SERIAL_NUMBER = 5;
    private static final int AOAP_STR_URI = 4;
    private static final int AOAP_STR_VERSION = 3;
    private static final int AOA_HS_TIMEOUT_MS = 50;
    private static final int APPLE_DEVICE_ID = 1452;
    private static final int BOUNDARY = 8;
    private static final int BYTE_MASK = 255;
    private static final boolean DBG = true;
    private static final int DEVICE_UEVENT_BUFFER_COUNT = 1024;
    private static final String DEVICE_UEVENT_PATH = "/device/uevent";
    private static final String IPHONE = "iPhone";
    private static final int LENGTH = 2;
    private static final int PROTOCOL_MTP = 0;
    private static final int PROTOCOL_PICTURE_TRANSFER = 1;
    private static final int REQUEST_51 = 51;
    private static final int REQUEST_52 = 52;
    private static final int REQUEST_53 = 53;
    private static final int REQUEST_ROLESWITCH_PROTOCOL = 81;
    private static final int REQUEST_SUPPORTS_PROTOCOL = 83;
    private static final int REQUEST_TYPE_ROLESWITCH_PROTOCOL = 64;
    private static final int REQUEST_TYPE_SUPPORTS_PROTOCOL = 192;
    private static final String SPLIT_COMMA = ",";
    private static final int SUBCLASS_MTP = 255;
    private static final int SUBCLASS_STILL_CAPTURE = 1;
    private static final String SYS_CLASS_HIDRAW_PATH = "/sys/class/hidraw";
    private static final String TAG = "UsbDeviceDetect";
    private static final int TIME_OUT = 0;
    private static final int TYPE_DEVICE_SUPPORTS_APPLE_CARPLAY = 1;
    private static final int USB_TIMEOUT_MS = 100000;
    private static Set<Integer> ACCESSORY_PRODUCT_IDS = new HashSet();
    private static Set<Integer> MODEM_VENDOR_IDS = new HashSet();
    private static Set<Integer> MODEM_PRODUCT_IDS = new HashSet();
    private static Set<Integer> UNSUPPORT_USB_VENDOR_IDS = new HashSet();
    private static Set<Integer> UNSUPPORT_USB_PRODUCT_IDS = new HashSet();
    private static UsbDeviceDetect sUsbDeviceDetect = null;
    private UsbManager mUsbManager = null;
    private boolean mSupportUsbCarplay = false;
    private boolean mSupportUsbCarlife = false;
    private boolean mSupportUsbAndroidAuto = false;
    private boolean mSupportUsbIpod = false;
    private boolean mSupportUsbMtp = false;
    private boolean mSupportUsbChagre = false;
    private boolean mSupportUsbStorage = false;
    private boolean mCurrentSupportAndroidCarlife = false;
    private boolean mCurrentSupportIosCarlife = false;
    private int mUsbEmuMaxCount = 2;
    private UsbConfiguration mUsbConfigration = UsbConfiguration.getInstance();

    public enum AoaProjection {
        AOA_PROJECTION_ANDROIDAUTO,
        AOA_PROJECTION_CARLIFE
    }

    public enum AoaStatus {
        AOA_STATUS_SEND_REQUEST_SUCCESS,
        AOA_STATUS_SUCCESS,
        AOA_STATUS_FAIL,
        AOA_STATUS_TIMEOUT,
        AOA_STATUS_NOT_SUPPORT,
        AOA_STATUS_ALREADY_AOA,
        AOA_STATUS_CT53_FAIL,
        AOA_STATUS_OTHER_ERROR
    }

    public static synchronized UsbDeviceDetect getInstance() {
        if (sUsbDeviceDetect == null) {
            sUsbDeviceDetect = new UsbDeviceDetect();
        }
        return sUsbDeviceDetect;
    }

    private UsbDeviceDetect() {
        loadConfig();
    }

    public void init(Context context, int capability) {
        updateCapability(capability);
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        ACCESSORY_PRODUCT_IDS.add(Integer.valueOf(ACCESSORY_PRODUCT_ID0));
        ACCESSORY_PRODUCT_IDS.add(Integer.valueOf(ACCESSORY_PRODUCT_ID1));
        ACCESSORY_PRODUCT_IDS.add(Integer.valueOf(ACCESSORY_PRODUCT_ID4));
        ACCESSORY_PRODUCT_IDS.add(Integer.valueOf(ACCESSORY_PRODUCT_ID5));
    }

    public void updateCapability(int capability) {
        Log.d(TAG, "updateCapability, capability:" + capability);
        this.mSupportUsbCarplay = (capability & 64) == 64;
        this.mSupportUsbCarlife = (capability & 256) == 256;
        this.mSupportUsbAndroidAuto = (capability & 128) == 128;
        this.mSupportUsbIpod = (capability & 8) == 8;
        this.mSupportUsbMtp = (capability & 16) == 16;
        this.mSupportUsbChagre = (capability & 1) == 1;
        this.mSupportUsbStorage = (capability & 32768) == 32768;
    }

    public void getStorageCarlifeSupportStatus() {
        this.mCurrentSupportAndroidCarlife = this.mUsbConfigration.isSupportAndroidCarlife();
        this.mCurrentSupportIosCarlife = this.mUsbConfigration.isSupportIosCarlife();
    }

    public void updateCarlifeSupportStatus(int deviceType, boolean flag) {
        Log.d(TAG, "updateCarlifeSupportStatus, deviceType:" + deviceType + ", flag:" + flag);
        if (deviceType == 1) {
            this.mCurrentSupportAndroidCarlife = flag;
        } else if (deviceType == 2) {
            this.mCurrentSupportIosCarlife = flag;
        }
    }

    public boolean checkDevice(UsbDevice device) {
        UsbDeviceConnection conn = getUsbDeviceConnection(device);
        if (conn == null) {
            return false;
        }
        conn.close();
        return true;
    }

    private void settingIphoneDeviceInfo(int capability, UsbDevice usbDevice, LinkDevice linkDevice) {
        if ((capability & 64) > 0 || (capability & 256) > 0) {
            linkDevice.setDeviceType(2);
        } else {
            linkDevice.setDeviceType(3);
        }
        if ((capability & 8) > 0) {
            linkDevice.setUsbHidNode(getHidPath(usbDevice));
        }
        linkDevice.setCapability(matchPlatformCapability(capability, linkDevice.getDeviceType()));
    }

    private int matchPlatformCapability(int devCapability, int deviceType) {
        int capability = devCapability;
        if (!this.mSupportUsbAndroidAuto) {
            capability &= -129;
        }
        if (!this.mSupportUsbCarlife) {
            capability &= -257;
        } else if (deviceType == 1) {
            if (!this.mCurrentSupportAndroidCarlife) {
                capability &= -257;
            }
        } else if (deviceType == 2 && !this.mCurrentSupportIosCarlife) {
            capability &= -257;
        }
        if (!this.mSupportUsbMtp) {
            capability &= -17;
        }
        if (!this.mSupportUsbIpod) {
            capability &= -9;
        }
        if (!this.mSupportUsbCarplay) {
            capability &= -65;
        }
        if (!this.mSupportUsbChagre) {
            return capability & (-2);
        }
        return capability;
    }

    private int checkAndroidDeviceCapability(UsbDevice usbDevice) {
        int fullCapability = 0 | 1;
        if (!this.mUsbConfigration.isOnlyOtgPortSupportProjection() || usbPortSupportRoleSwitch(usbDevice)) {
            fullCapability = fullCapability | 128 | 256;
        }
        if (isMtpDevice(usbDevice)) {
            fullCapability |= 16;
        }
        Log.d(TAG, "checkAndroidDeviceCapability, android device:" + LogEncrypt.IpAddressEncrypt(usbDevice.getSerialNumber()) + ", full capability:" + fullCapability);
        return fullCapability;
    }

    private int checkAppleDeviceCapability(UsbDevice usbDevice) {
        int fullCapability = 0 | 1;
        if (isHidDevice(usbDevice)) {
            fullCapability |= 8;
        }
        if (checkSupportCarPlay(usbDevice)) {
            if (usbPortSupportRoleSwitch(usbDevice)) {
                fullCapability = fullCapability | 256 | 64;
            }
        } else if (usbDevice.getProductName() != null && usbDevice.getProductName().contains(IPHONE) && usbPortSupportRoleSwitch(usbDevice)) {
            fullCapability |= 256;
        }
        if (isMtpDevice(usbDevice)) {
            fullCapability |= 16;
        }
        Log.d(TAG, "checkAppleDeviceCapability, ios device:" + LogEncrypt.IpAddressEncrypt(usbDevice.getSerialNumber()) + ", full capability:" + fullCapability);
        return fullCapability;
    }

    private int checkOtherDeviceCapability(UsbDevice usbDevice) {
        int fullCapability = 0;
        if (isMtpDevice(usbDevice)) {
            int fullCapability2 = 0 | 16;
            fullCapability = fullCapability2 | 32768;
        }
        Log.d(TAG, "checkOtherDeviceCapability, other device:" + LogEncrypt.IpAddressEncrypt(usbDevice.getSerialNumber()) + ", full capability:" + fullCapability);
        return fullCapability;
    }

    public int detectDeviceCapability(UsbDevice usbDevice, LinkDevice linkDevice, int connId) {
        Log.d(TAG, "detectDeviceCapability, usbDevice:" + usbDevice + ", linkDevice:" + linkDevice + ", connId:" + connId);
        int capability = 0;
        if (usbDevice != null) {
            if (isUnProjectionDevice(usbDevice.getVendorId(), usbDevice.getProductId())) {
                linkDevice.setDeviceType(0);
                linkDevice.setCapability(0);
            } else if (isUsbStorageDevice(usbDevice)) {
                linkDevice.setDeviceType(0);
                capability = 32768;
                int capa = this.mSupportUsbStorage ? 32768 : 0;
                linkDevice.setCapability(capa);
            } else if (isAndroidDevice(usbDevice)) {
                Log.e(TAG, "detectDeviceCapability, [connid:" + connId + "] Android Device");
                linkDevice.setDeviceType(1);
                capability = checkAndroidDeviceCapability(usbDevice);
                linkDevice.setCapability(matchPlatformCapability(capability, linkDevice.getDeviceType()));
            } else if (isAppleDevice(usbDevice)) {
                Log.e(TAG, "detectDeviceCapability, [connid:" + connId + "] Apple Device");
                capability = checkAppleDeviceCapability(usbDevice);
                settingIphoneDeviceInfo(capability, usbDevice, linkDevice);
            } else {
                linkDevice.setDeviceType(0);
                capability = checkOtherDeviceCapability(usbDevice);
                linkDevice.setCapability(matchPlatformCapability(capability, linkDevice.getDeviceType()));
            }
            Log.e(TAG, "detectDeviceCapability, [connid: " + connId + "] device capability:" + capability + ", match platform capability:" + linkDevice.getCapability());
        }
        return capability;
    }

    public boolean checkDeviceIsAoaMode(UsbDevice usbDevice, int connId) {
        boolean isAoaMode = false;
        if (usbDevice != null) {
            int vendorId = usbDevice.getVendorId();
            int productId = usbDevice.getProductId();
            if (ACCESSORY_VENODR_ID == vendorId && ACCESSORY_PRODUCT_IDS.contains(Integer.valueOf(productId))) {
                isAoaMode = true;
            }
        }
        Log.d(TAG, "checkDeviceIsAoaMode, [connid:" + connId + "] the usb  device in aoa mode:" + isAoaMode);
        return isAoaMode;
    }

    public void stopAoaConnection(UsbDevice usbDevice) {
        UsbDeviceConnection connection = getUsbDeviceConnection(usbDevice);
        if (connection != null) {
            Log.d(TAG, "stopAoaConnection, ResetUsb.");
            connection.resetDevice();
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void loadConfig() {
    }

    private AoaStatus sendAndroidAutoAccessoryString(UsbDeviceConnection connection) {
        if (!sendAccessoryString(connection, 0, ACCESSORY_MANUFACTURER_NAME)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 1, "Android Auto")) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 2, "Android Auto")) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 3, ACCESSORY_VERSION)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 4, ACCESSORY_URI)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 5, ACCESSORY_SERIAL_NUMBER)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        return AoaStatus.AOA_STATUS_SUCCESS;
    }

    private AoaStatus sendAndroidAutoAccessoryString(String serialNumber, UsbDeviceConnection connection) {
        if (!sendAccessoryString(connection, 0, ACCESSORY_MANUFACTURER_NAME)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 1, "Android Auto")) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 2, "Android Auto")) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 3, ACCESSORY_VERSION)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 4, ACCESSORY_URI)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        String finalSerial = ACCESSORY_SERIAL_NUMBER;
        if (!"".equals(serialNumber)) {
            finalSerial = serialNumber;
        }
        if (!sendAccessoryString(connection, 5, finalSerial)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        return AoaStatus.AOA_STATUS_SUCCESS;
    }

    private AoaStatus sendCarlifeAccessoryString(UsbDeviceConnection connection) {
        if (!sendAccessoryString(connection, 0, ACCESSORY_MANUFACTURER_NAME_CARLIFE)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 1, "CarLife")) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 2, ACCESSORY_DESCRIPTION_CARLIFE)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 3, ACCESSORY_VERSION_CARLIFE)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 4, ACCESSORY_URI_CARLIFE)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        if (!sendAccessoryString(connection, 5, ACCESSORY_SERIAL_NUMBER_CARLIFE)) {
            return AoaStatus.AOA_STATUS_FAIL;
        }
        return AoaStatus.AOA_STATUS_SUCCESS;
    }

    public AoaStatus switchAccessory(UsbDevice usbDevice, AoaProjection type, int connId) {
        if (usbDevice != null) {
            boolean isAoaMode = checkDeviceIsAoaMode(usbDevice, connId);
            if (isAoaMode) {
                return AoaStatus.AOA_STATUS_ALREADY_AOA;
            }
            UsbDeviceConnection connection = getUsbDeviceConnection(usbDevice);
            Log.d(TAG, "switchAccessory, [connid:" + connId + "]");
            if (connection != null) {
                int protocolVersion = getAccessoryProtocol(connection);
                Log.d(TAG, "switchAccessory, [connid:" + connId + "] protocolVersion:" + protocolVersion);
                if (protocolVersion <= 0) {
                    protocolVersion = getAccessoryProtocol(connection);
                }
                if (protocolVersion <= 0) {
                    connection.close();
                    return AoaStatus.AOA_STATUS_NOT_SUPPORT;
                }
                if (!sendAccessoryString(type, connection) && !sendAccessoryString(type, connection)) {
                    connection.close();
                    return AoaStatus.AOA_STATUS_FAIL;
                }
                if (!startAccessory(connection)) {
                    connection.close();
                    int i = this.mUsbEmuMaxCount;
                    if (i > 0) {
                        this.mUsbEmuMaxCount = i - 1;
                        return AoaStatus.AOA_STATUS_CT53_FAIL;
                    }
                    this.mUsbEmuMaxCount = 2;
                    return AoaStatus.AOA_STATUS_FAIL;
                }
                connection.close();
                this.mUsbEmuMaxCount = 2;
                return AoaStatus.AOA_STATUS_SEND_REQUEST_SUCCESS;
            }
        }
        return AoaStatus.AOA_STATUS_FAIL;
    }

    public AoaStatus switchAccessory(UsbDevice usbDevice, String serialNumber, AoaProjection type, int connId) {
        if (usbDevice != null) {
            boolean isAoaMode = checkDeviceIsAoaMode(usbDevice, connId);
            if (isAoaMode) {
                return AoaStatus.AOA_STATUS_ALREADY_AOA;
            }
            UsbDeviceConnection connection = getUsbDeviceConnection(usbDevice);
            Log.d(TAG, "switchAccessory, [connid:" + connId + "]");
            if (connection != null) {
                int protocolVersion = getAccessoryProtocol(connection);
                Log.d(TAG, "switchAccessory, [connid:" + connId + "] protocolVersion:" + protocolVersion);
                if (protocolVersion <= 0) {
                    protocolVersion = getAccessoryProtocol(connection);
                }
                if (protocolVersion <= 0) {
                    connection.close();
                    return AoaStatus.AOA_STATUS_NOT_SUPPORT;
                }
                if (!sendAccessoryString(serialNumber, type, connection) && !sendAccessoryString(serialNumber, type, connection)) {
                    connection.close();
                    return AoaStatus.AOA_STATUS_FAIL;
                }
                if (!startAccessory(connection)) {
                    connection.close();
                    int i = this.mUsbEmuMaxCount;
                    if (i > 0) {
                        this.mUsbEmuMaxCount = i - 1;
                        return AoaStatus.AOA_STATUS_CT53_FAIL;
                    }
                    this.mUsbEmuMaxCount = 2;
                    return AoaStatus.AOA_STATUS_FAIL;
                }
                connection.close();
                this.mUsbEmuMaxCount = 2;
                return AoaStatus.AOA_STATUS_SEND_REQUEST_SUCCESS;
            }
        }
        return AoaStatus.AOA_STATUS_FAIL;
    }

    private boolean sendAccessoryString(AoaProjection type, UsbDeviceConnection connection) {
        if (type == AoaProjection.AOA_PROJECTION_ANDROIDAUTO && sendAndroidAutoAccessoryString(connection) == AoaStatus.AOA_STATUS_SUCCESS) {
            return true;
        }
        return type == AoaProjection.AOA_PROJECTION_CARLIFE && sendCarlifeAccessoryString(connection) == AoaStatus.AOA_STATUS_SUCCESS;
    }

    private boolean sendAccessoryString(String serialNumber, AoaProjection type, UsbDeviceConnection connection) {
        if (type == AoaProjection.AOA_PROJECTION_ANDROIDAUTO && sendAndroidAutoAccessoryString(serialNumber, connection) == AoaStatus.AOA_STATUS_SUCCESS) {
            return true;
        }
        return type == AoaProjection.AOA_PROJECTION_CARLIFE && sendCarlifeAccessoryString(connection) == AoaStatus.AOA_STATUS_SUCCESS;
    }

    private boolean sendAccessoryString(UsbDeviceConnection conn, int index, String str) {
        return sendAccessoryControlTransfer(conn, 52, index, str.getBytes()) == str.length();
    }

    private boolean startAccessory(UsbDeviceConnection conn) {
        return sendAccessoryControlTransfer(conn, 53, 0, null) == 0;
    }

    private int sendAccessoryControlTransfer(UsbDeviceConnection conn, int request, int index, byte[] buffer) {
        int state = 0;
        if (conn != null) {
            state = conn.controlTransfer(64, request, 0, index, buffer, buffer == null ? 0 : buffer.length, 50);
        }
        Log.d(TAG, "sendAccessoryControlTransfer, STATE:" + state);
        return state;
    }

    private UsbDeviceConnection getUsbDeviceConnection(UsbDevice usbDevice) {
        UsbManager usbManager = this.mUsbManager;
        if (usbManager == null) {
            return null;
        }
        UsbDeviceConnection conn = usbManager.openDevice(usbDevice);
        return conn;
    }

    private boolean isAndroidDevice(UsbDevice usbDevice) {
        int aoaVersion = getAoaVersion(usbDevice);
        Log.d(TAG, "checkAndroidDevice, aoaVersion:" + aoaVersion);
        if (aoaVersion <= 0) {
            return false;
        }
        return true;
    }

    private int getAoaVersion(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return -1;
        }
        UsbDeviceConnection connection = getUsbDeviceConnection(usbDevice);
        if (connection != null) {
            int aoaVersion = getAccessoryProtocol(connection);
            connection.close();
            return aoaVersion;
        }
        Log.d(TAG, "getAoaVersion, connection is null");
        return -1;
    }

    private int getAccessoryProtocol(UsbDeviceConnection conn) {
        byte[] buffer = new byte[2];
        if (conn != null) {
            int bytesReceived = conn.controlTransfer(REQUEST_TYPE_SUPPORTS_PROTOCOL, 51, 0, 0, buffer, 2, 50);
            Log.d(TAG, "getAccessoryProtocol, bytesReceived:" + bytesReceived);
            if (bytesReceived == 2) {
                return (buffer[0] & 255) + ((buffer[1] & 255) << 8);
            }
            return -1;
        }
        return -1;
    }

    private boolean isAppleDevice(UsbDevice usbDevice) {
        boolean isAppleDevice = false;
        if (usbDevice != null) {
            int deviceId = usbDevice.getVendorId();
            Log.d(TAG, "checkAppleDevice, deviceId:" + deviceId);
            if (deviceId == APPLE_DEVICE_ID) {
                isAppleDevice = true;
            }
        }
        Log.d(TAG, "checkAppleDevice, is Apple device:" + isAppleDevice);
        return isAppleDevice;
    }

    private boolean checkSupportCarPlay(UsbDevice device) {
        if (device == null) {
            Log.e(TAG, "checkSupportCarPlay, the device is null.");
            return false;
        }
        UsbDeviceConnection conn = getUsbDeviceConnection(device);
        if (conn == null) {
            Log.e(TAG, "checkSupportCarPlay, getUsbDeviceConnection fial.");
            return false;
        }
        UsbControlPackage item = new UsbControlPackage();
        item.mRequestType = REQUEST_TYPE_SUPPORTS_PROTOCOL;
        item.mRequest = REQUEST_SUPPORTS_PROTOCOL;
        item.mIndex = 0;
        item.mValue = 0;
        item.mBuffer = new byte[4];
        int result = controlTransferOut(conn, item);
        conn.close();
        if (result < 0) {
            Log.e(TAG, "checkSupportCarPlay, controlTransferOut fial.");
            return false;
        }
        int type = (item.mBuffer[3] << 8) | (item.mBuffer[2] << 8) | (item.mBuffer[1] << 8) | item.mBuffer[0];
        Log.d(TAG, "checkSupportCarPlay, Apple device supports type is:" + type);
        return type == 1;
    }

    public boolean sendRoleSwitchToApple(UsbDevice device, int service, int connId) {
        int i;
        Log.d(TAG, "sendRoleSwitchToApple, [connid:" + connId + "] start.");
        if (device == null) {
            Log.d(TAG, "sendRoleSwitchToApple, [connid:" + connId + "] the device is null.");
            return false;
        }
        UsbDeviceConnection conn = getUsbDeviceConnection(device);
        if (conn == null) {
            Log.d(TAG, "sendRoleSwitchToApple, [connid:" + connId + "] getUsbDeviceConnection fial.");
            return false;
        }
        UsbControlPackage item = new UsbControlPackage();
        item.mRequestType = 64;
        item.mRequest = REQUEST_ROLESWITCH_PROTOCOL;
        item.mIndex = 0;
        if (service != 1) {
            i = 0;
        } else {
            i = 1;
        }
        item.mValue = i;
        if (controlTransferOut(conn, item) >= 0) {
            conn.close();
            Log.d(TAG, "sendRoleSwitchToApple, [connid:" + connId + "] controlTransferOut success.");
            return true;
        }
        conn.close();
        Log.d(TAG, "sendRoleSwitchToApple, [connid:" + connId + "] controlTransferOut failure.");
        return false;
    }

    public String getHidPath(UsbDevice usbDevice) {
        if (isHidDevice(usbDevice) && usbDevice.getSerialNumber() != null) {
            return getHidDevPath(usbDevice.getSerialNumber());
        }
        return null;
    }

    private int controlTransferOut(UsbDeviceConnection conn, UsbControlPackage item) {
        if (conn == null || item == null) {
            return -1;
        }
        return conn.controlTransfer(item.mRequestType, item.mRequest, item.mValue, item.mIndex, item.mBuffer, item.mBuffer == null ? 0 : item.mBuffer.length, 0);
    }

    private boolean usbPortSupportRoleSwitch(UsbDevice usbDevice) {
        if (!this.mUsbConfigration.enableMultiUsbPort()) {
            Log.d(TAG, "usbPortSupportRoleSwitch, only one usb slot for user return support.");
            return true;
        }
        String name = usbDevice.getDeviceName();
        String[] roleSwitchDevBusPaths = this.mUsbConfigration.getDeviceRoleSwitchDevBusPath();
        for (String roleSwitchDevBusPath : roleSwitchDevBusPaths) {
            if (name.contains(roleSwitchDevBusPath)) {
                return true;
            }
        }
        Log.d(TAG, "usbPortSupportRoleSwitch, Bus not support Role Switch device name:" + name + ", RoleSwitch path:" + Arrays.toString(this.mUsbConfigration.getDeviceRoleSwitchDevBusPath()));
        return false;
    }

    private boolean isMtpDevice(UsbDevice device) {
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            UsbInterface usbInterface = device.getInterface(i);
            if (usbInterface.getInterfaceClass() == 6 && usbInterface.getInterfaceSubclass() == 1 && usbInterface.getInterfaceProtocol() == 1) {
                return true;
            }
            if (usbInterface.getInterfaceClass() == 255 && usbInterface.getInterfaceSubclass() == 255 && usbInterface.getInterfaceProtocol() == 0 && "MTP".equals(usbInterface.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsbStorageDevice(UsbDevice device) {
        if (device == null) {
            return false;
        }
        if (device.getSerialNumber() == null) {
            Log.d(TAG, "isUsbStorageDevice, SerialNumber is null as a storage device.");
            return true;
        }
        if (device.getInterfaceCount() != 1) {
            return false;
        }
        UsbInterface usbInterface = device.getInterface(0);
        if (usbInterface.getInterfaceClass() != 8) {
            return false;
        }
        Log.d(TAG, "isUsbStorageDevice, is Usb storage device.");
        return true;
    }

    private boolean isUnProjectionDevice(int vid, int pid) {
        HashMap<Integer, Integer> vidPidsMap = this.mUsbConfigration.getUnProjectionDeviceVidPids();
        if (vidPidsMap.size() == 0) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : vidPidsMap.entrySet()) {
            if (vid == entry.getKey().intValue() && pid == entry.getValue().intValue()) {
                Log.e(TAG, "isUnProjectionDevice, suceess, vid:" + vid + ", pid:" + pid);
                return true;
            }
        }
        return false;
    }

    private boolean isHidDevice(UsbDevice device) {
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            UsbInterface usbInterface = device.getInterface(i);
            if (usbInterface.getInterfaceClass() == 3) {
                return true;
            }
        }
        return false;
    }

    private String getHidDevPath(String serial) {
        String hidDevPath = null;
        FileInputStream inputStream = null;
        try {
            File file = new File(SYS_CLASS_HIDRAW_PATH);
            if (file.exists()) {
                File[] tempList = file.listFiles();
                if (tempList == null) {
                    return "";
                }
                int i = 0;
                while (true) {
                    if (i >= tempList.length) {
                        break;
                    }
                    if (tempList[i].isDirectory()) {
                        File hidFile = new File(tempList[i].toString() + DEVICE_UEVENT_PATH);
                        Log.d(TAG, "getHidDevPath, file name:" + hidFile.toString());
                        StringBuilder sb = new StringBuilder("");
                        if (hidFile.exists()) {
                            FileInputStream inputStream2 = new FileInputStream(hidFile);
                            byte[] buffer = new byte[1024];
                            for (int len = inputStream2.read(buffer); len > 0; len = inputStream2.read(buffer)) {
                                sb.append(new String(buffer, 0, len));
                            }
                            inputStream2.close();
                            Log.d(TAG, "getHidDevPath, content:" + sb.toString());
                            if (sb.toString().contains(serial)) {
                                hidDevPath = i == 0 ? this.mUsbConfigration.getDeviceHidDevPath0() : this.mUsbConfigration.getDeviceHidDevPath1();
                            }
                        } else {
                            Log.d(TAG, "getHidDevPath, File not exist");
                        }
                    }
                    i++;
                }
            }
            Log.d(TAG, "getHidDevPath, HID path:" + hidDevPath);
            return hidDevPath;
        } catch (IOException exception) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException(exception);
        }
    }

    public boolean isBuiltInUsbDevice(UsbDevice usbDevice) {
        boolean result = false;
        if (usbDevice != null) {
            int vendorId = usbDevice.getVendorId();
            int productId = usbDevice.getProductId();
            Log.d(TAG, "isBuiltInUsbDevice, vid:" + vendorId + ", pid:" + productId);
            if (MODEM_VENDOR_IDS.contains(Integer.valueOf(vendorId)) && MODEM_PRODUCT_IDS.contains(Integer.valueOf(productId))) {
                result = true;
            }
        }
        Log.d(TAG, "isBuiltInUsbDevice, result:" + result);
        return result;
    }

    public boolean isUnSupportUsbDevice(UsbDevice usbDevice) {
        boolean result = false;
        if (usbDevice != null) {
            int vendorId = usbDevice.getVendorId();
            int productId = usbDevice.getProductId();
            if (UNSUPPORT_USB_VENDOR_IDS.contains(Integer.valueOf(vendorId)) && UNSUPPORT_USB_PRODUCT_IDS.contains(Integer.valueOf(productId))) {
                result = true;
            }
            Log.d(TAG, "isUnSupportUsbDevice, vid:" + vendorId + ", pid:" + productId + ", result:" + result);
        }
        return result;
    }

    public void parseBuiltInUsbInfo() {
        String[] vids = this.mUsbConfigration.getBuiltInUsbVids().split(",");
        for (String vid : vids) {
            MODEM_VENDOR_IDS.add(Integer.valueOf(Integer.parseInt(vid)));
        }
        String[] pids = this.mUsbConfigration.getBuiltInUsbPids().split(",");
        for (String pid : pids) {
            MODEM_PRODUCT_IDS.add(Integer.valueOf(Integer.parseInt(pid)));
        }
    }

    public void parseUnSupportDeviceInfo() {
        String[] vids = this.mUsbConfigration.getUnSupportDeviceVids().split(",");
        for (String vid : vids) {
            UNSUPPORT_USB_VENDOR_IDS.add(Integer.valueOf(Integer.parseInt(vid)));
        }
        String[] pids = this.mUsbConfigration.getUnSupportDevicePids().split(",");
        for (String pid : pids) {
            UNSUPPORT_USB_PRODUCT_IDS.add(Integer.valueOf(Integer.parseInt(pid)));
        }
    }

    private static class UsbControlPackage {
        public byte[] mBuffer;
        public int mIndex;
        public int mRequest;
        public int mRequestType;
        public int mValue;

        private UsbControlPackage() {
        }
    }
}
