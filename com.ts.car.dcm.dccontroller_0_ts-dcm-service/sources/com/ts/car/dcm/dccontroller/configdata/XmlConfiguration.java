package com.ts.car.dcm.dccontroller.configdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.ts.car.dcm.dccontroller.R;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class XmlConfiguration implements IConfiguration {
    private static final String TAG = "XmlConfiguration";
    private HashMap<String, String> mConfigList = new HashMap<>();
    private SharedPreferences mSharedPreferences = null;

    public XmlConfiguration(Context context) {
        setConfigInfo(context);
    }

    @Override // com.ts.car.dcm.dccontroller.configdata.IConfiguration
    public HashMap<String, String> getConfigInfo() {
        return this.mConfigList;
    }

    private void setConfigInfo(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(context.getString(CONFIG_NAME_ID), 0);
        this.mConfigList.put(IConfiguration.KEY_CONTROLLERINFORMATION, String.join(";", context.getResources().getStringArray(R.array.controllerInformation)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_CARPLAY, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_CARPLAY, context.getString(R.string.SupportCarplay)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_CARLIFE, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_CARLIFE, context.getString(R.string.SupportCarlife)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_ANDROIDAUTO, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_ANDROIDAUTO, context.getString(R.string.SupportAndroidauto)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_IPOD, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_IPOD, context.getString(R.string.SupportIpod)));
        this.mConfigList.put(IConfiguration.KEY_CARPLAY_ENABLE, this.mSharedPreferences.getString(IConfiguration.KEY_CARPLAY_ENABLE, context.getString(R.string.EnableCarplay)));
        this.mConfigList.put(IConfiguration.KEY_CARLIFE_ENABLE, this.mSharedPreferences.getString(IConfiguration.KEY_CARLIFE_ENABLE, context.getString(R.string.EnableCarlife)));
        this.mConfigList.put(IConfiguration.KEY_ANDROIDAUTO_ENABLE, this.mSharedPreferences.getString(IConfiguration.KEY_ANDROIDAUTO_ENABLE, context.getString(R.string.EnableAndroidauto)));
        this.mConfigList.put(IConfiguration.KEY_IPOD_ENABLE, this.mSharedPreferences.getString(IConfiguration.KEY_IPOD_ENABLE, context.getString(R.string.EnableIpod)));
        this.mConfigList.put(IConfiguration.KEY_PROJECTION_PRIORITY, this.mSharedPreferences.getString(IConfiguration.KEY_PROJECTION_PRIORITY, context.getResources().getString(R.string.ProjectionPriority)));
        this.mConfigList.put(IConfiguration.KEY_DEVICE_ACCESS_AUTHORIZATION, this.mSharedPreferences.getString(IConfiguration.KEY_DEVICE_ACCESS_AUTHORIZATION, context.getString(R.string.DeviceAccessAuthorization)));
        this.mConfigList.put(IConfiguration.KEY_DEFAULT_PROJECTION_ON_IPHONE, this.mSharedPreferences.getString(IConfiguration.KEY_DEFAULT_PROJECTION_ON_IPHONE, context.getString(R.string.DefaultProjectionOnIphone)));
        this.mConfigList.put(IConfiguration.KEY_DEFAULT_PROJECTION_ON_ANDROID, this.mSharedPreferences.getString(IConfiguration.KEY_DEFAULT_PROJECTION_ON_ANDROID, context.getString(R.string.DefaultProjectionOnAndroid)));
        this.mConfigList.put("WifiApSsidRandom", this.mSharedPreferences.getString("WifiApSsidRandom", context.getString(R.string.WifiApSsidRandom)));
        this.mConfigList.put(IConfiguration.KEY_BLOCK_BUILTIN_USB, this.mSharedPreferences.getString(IConfiguration.KEY_BLOCK_BUILTIN_USB, context.getString(R.string.BlockBuiltinUsb)));
        this.mConfigList.put(IConfiguration.KEY_BUILTIN_USB_VIDS, this.mSharedPreferences.getString(IConfiguration.KEY_BUILTIN_USB_VIDS, context.getString(R.string.BuiltinUsbVids)));
        this.mConfigList.put(IConfiguration.KEY_BUILTIN_USB_PIDS, this.mSharedPreferences.getString(IConfiguration.KEY_BUILTIN_USB_PIDS, context.getString(R.string.BuiltinUsbPids)));
        this.mConfigList.put(IConfiguration.KEY_ENABLE_MULTI_USB_PORT, this.mSharedPreferences.getString(IConfiguration.KEY_ENABLE_MULTI_USB_PORT, context.getString(R.string.EnableMultiUsbPort)));
        this.mConfigList.put(IConfiguration.KEY_GET_WIFI_AP_CHANNEL_INFO, this.mSharedPreferences.getString(IConfiguration.KEY_GET_WIFI_AP_CHANNEL_INFO, String.join(";", context.getResources().getStringArray(R.array.WifiApChannel))));
        this.mConfigList.put(IConfiguration.KEY_ALLOW_DEVICE_DETECTION, this.mSharedPreferences.getString(IConfiguration.KEY_ALLOW_DEVICE_DETECTION, context.getString(R.string.AllowDeviceDetection)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_ANDROID_CARLIFE, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_ANDROID_CARLIFE, context.getString(R.string.SupportAndroidCarlife)));
        this.mConfigList.put(IConfiguration.KEY_SUPPORT_IOS_CARLIFE, this.mSharedPreferences.getString(IConfiguration.KEY_SUPPORT_IOS_CARLIFE, context.getString(R.string.SupportIosCarlife)));
        this.mConfigList.put(IConfiguration.KEY_WIFI_AP_BAND_FOR_CARPLAY, this.mSharedPreferences.getString(IConfiguration.KEY_WIFI_AP_BAND_FOR_CARPLAY, context.getString(R.string.WifiApBandForCarplay)));
        this.mConfigList.put("WifiAp5GChannelList", this.mSharedPreferences.getString("WifiAp5GChannelList", context.getString(R.string.WifiAp5GChannelList)));
        String[] controllerInfos = this.mConfigList.get(IConfiguration.KEY_CONTROLLERINFORMATION).split(";");
        for (String controllerInfo : controllerInfos) {
            String jarName = controllerInfo.split(",")[0].split("\\.")[0];
            String versionKey = jarName + "Version";
            this.mConfigList.put(versionKey, this.mSharedPreferences.getString(versionKey, IConfiguration.DEFAULT_JAR_VERSION));
        }
    }

    @Override // com.ts.car.dcm.dccontroller.configdata.IConfiguration
    public boolean saveConfigInfo(String key, String value) {
        if (key.isEmpty()) {
            Log.e(TAG, "saveConfigInfo, key is empty.");
            return false;
        }
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
        return true;
    }
}
