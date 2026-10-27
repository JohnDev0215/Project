package com.loostone.libtuning.channel.byd.upgrade.inf;

import android.hardware.usb.UsbDevice;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface IUpdateFirmware<T> {

    public interface Callback {
        void error(int i);

        void finish();

        void process(int i);
    }

    public interface ErrorCode {
        public static final int CRC_CHECK_FAILED = 3;
        public static final int FILE_NOT_EXIST = 21;
        public static final int FILE_SIZE_OUT = 22;
        public static final int HID_FAILED = 1;
        public static final int HID_LEN_ERROR = 2;
        public static final int NO_INTERFACE = 11;
        public static final int NO_PERMISSION = 10;
        public static final int TRANSFER_FAILED = 4;
        public static final int TRANSFER_INTERRUPT = 5;
        public static final int UNINIT = -1;
        public static final int UNKNOWN = 0;
    }

    public interface MapData {
        public static final String DEVICE_INFO = "DeviceInfo";
        public static final String DEVICE_INFO_ALL = "DeviceInfoAll";
        public static final String DEVICE_VERSION_CODE = "DeviceVersionCode";
    }

    void cancelUpdate();

    boolean init(UsbDevice usbDevice);

    boolean queryCheckResult();

    int queryDeviceStatus();

    ArrayList<byte[]> queryDeviceVersionCode();

    long queryHidVersionCode();

    boolean queryUpdateResult();

    int release();

    boolean requestUpdate(long j, String str);

    T setCallback(Callback callback);

    boolean startUpdate();

    boolean updateProcess();
}
