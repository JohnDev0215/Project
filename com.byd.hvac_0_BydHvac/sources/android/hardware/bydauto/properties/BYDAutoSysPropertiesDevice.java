package android.hardware.bydauto.properties;

import android.content.Context;
import android.hardware.bydauto.properties.tsimpl.ProperityManagerImpl;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSysPropertiesDevice {
    public static final String APN_STATE = "net.lte.apn3.real_state";
    public static final String AUTO_TIME = "CarSettings.UserTableData.AUTO_TIME";
    public static final String BLUETOOTH_CALL = "bluetooth.call";
    public static final String BOOT_BUSINESS = "sys.byd.boot_business";
    public static final String BOOT_BUSINESS_SUB = "sys.byd.boot_business_sub";
    public static final String BUILD_REGION = "ro.build.region";
    public static final String CAR_VIN = "persist.sys.cloud_virtual_vin";
    public static final String CLOUDSERVICE_START = "sys.byd.cloudservice_start";
    public static final String COUNTRY_CODE = "sys.byd.countrycode";
    public static final String CREEN_NET_VALUE = "CarSettings.UserTableData.GREEN_NET_VALUE";
    public static final String CURRENT_APP = "sys.byd.power_on_current_app";
    public static final String DILINK_UI = "ro.vehicle.type";
    public static final String DX_GLOBAL_VALUE = "persist.sys.byd.dx_global_value";
    public static final String HAS_SD = "persist.sys.byd.hasSDInserted";
    public static final String HAS_USB = "persist.sys.byd.hasUSBInserted";
    public static final String IFLYTEK_CONFIG = "hal.iflytek.config";
    public static final String MIC_CONFIG = "hal.activemic.config";
    public static final String PABP_LOAD_STATE = "bluetooth.load_state";
    public static final String PANO_START = "sys.byd.pano_start";
    public static final String PNO_START = "sys.byd.pano_start";
    public static final String RIGHT_RUDDER_VALUE = "persist.sys.byd.right_rudder_value";
    public static final String SCREEN_BRIGHTNESS = "screen_brightness";
    public static final String SCREEN_MODE = "sys_screen_mode";
    public static final String SD_EXIST = "sys.byd.isSDExist";
    private static final String TAG = "BYDAutoSysPropertiesDevice";
    public static final String TCP_CONNECT_STATUS = "sys.tcp_connect_status";
    public static final String TIME_12_24 = "time_12_24";
    public static final String UI_STYLE = "persist.sys.ui_style";
    public static final String URL_BLUETOOTH_CONTACT = "content://com.android.bluetooth.contactprovider";
    public static final String URL_CALLLOG_CONTACT = "content://call_log/calls";
    public static final String USB_EXIST = "sys.byd.isUSBExist";
    public static final String USB_EXIT = "sys.byd.isUSBExist";
    public static final String VEHICLE_CONFIG = "vehicle.config.cam_sort";
    public static final String VEHICLE_TYPE = "ro.vehicle.type";
    private static volatile BYDAutoSysPropertiesDevice sDevice;
    private final ProperityManagerImpl mProperityManagerImpl;

    private BYDAutoSysPropertiesDevice(Context context) {
        this.mProperityManagerImpl = ProperityManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static BYDAutoSysPropertiesDevice getInstance(Context context) {
        if (sDevice == null) {
            synchronized (ProperityManagerImpl.class) {
                if (sDevice == null) {
                    sDevice = new BYDAutoSysPropertiesDevice(context);
                }
            }
        }
        return sDevice;
    }

    public String getBtContact(String str) {
        Log.d(TAG, "getBtContact is execute:address is " + str);
        return this.mProperityManagerImpl.getBtContact(str);
    }

    public boolean getSettingsBoolean(String str) {
        Log.d(TAG, "getSettingsBoolean is execute:key is " + str);
        return this.mProperityManagerImpl.getSettingsBoolean(str);
    }

    public int getSettingsInt(String str) {
        Log.d(TAG, "getSettingsInt is execute:key is " + str);
        return this.mProperityManagerImpl.getSettingsInt(str);
    }

    public String getSettingsString(String str) {
        Log.d(TAG, "getSettingsString is execute:key is " + str);
        return this.mProperityManagerImpl.getSettingsString(str);
    }

    public String getSystemPersist(String str) {
        Log.d(TAG, "getSystemPersist is execute:key is " + str);
        return this.mProperityManagerImpl.getSystemPersist(str);
    }

    public boolean getSystemPersistBoolean(String str) {
        Log.d(TAG, "getSystemPersistBoolean is execute:key is " + str);
        return this.mProperityManagerImpl.getSystemPersistBoolean(str);
    }

    public int getSystemPersistInt(String str) {
        Log.d(TAG, "getSystemPersistInt is execute:key is " + str);
        return this.mProperityManagerImpl.getSystemPersistInt(str);
    }

    public void setSettingsBoolean(String str, int i) {
        Log.d(TAG, "setSettingsBoolean is execute:key is " + str + " value is " + i);
        this.mProperityManagerImpl.setSettingsBoolean(str, i);
    }

    public void setSettingsInt(String str, int i) {
        Log.d(TAG, "setSettingsInt is execute:key is " + str + " value is " + i);
        this.mProperityManagerImpl.setSettingsInt(str, i);
    }

    public void setSystemPersist(String str, String str2) {
        Log.d(TAG, "setSystemPersist is execute:key is " + str + " value is " + str2);
        this.mProperityManagerImpl.setSystemPersist(str, str2);
    }
}
