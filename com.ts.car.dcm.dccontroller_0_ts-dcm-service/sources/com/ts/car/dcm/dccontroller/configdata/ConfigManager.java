package com.ts.car.dcm.dccontroller.configdata;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.R;
import com.ts.car.dcm.dccontroller.controllermanager.ControllerInformation;
import com.ts.car.dcm.dccontroller.controllermanager.WifiApChannelInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ConfigManager {
    private static final String BOOT_DEFAULT_WIFI_COUNTRY_CODE = "ro.boot.wificountrycode";
    private static final String TAG = "ConfigManager";
    private IConfiguration mConfiguration;
    private Context mContext;
    private DcController mDccontroller;
    private WifiApChannelInfo mWifiApChannelInfo;
    private HashMap<String, String> mConfigList = new HashMap<>();
    private LinkedList<String> mProjectionPriority = new LinkedList<>();
    private List<ControllerInformation> mConnControllerInfoList = new ArrayList();

    public ConfigManager(Context context, DcController dcController) {
        this.mConfiguration = null;
        this.mDccontroller = dcController;
        this.mContext = context;
        XmlConfiguration xmlConfiguration = new XmlConfiguration(context);
        this.mConfiguration = xmlConfiguration;
        this.mConfigList.putAll(xmlConfiguration.getConfigInfo());
        this.mConfigList.putAll(new StaticConfiguration(context).getConfigInfo());
        Log.e(TAG, "init list:" + this.mConfigList);
        parseControllerInfo();
        String projectionPriority = this.mConfigList.get(IConfiguration.KEY_PROJECTION_PRIORITY);
        if (projectionPriority != null && !projectionPriority.isEmpty()) {
            String[] projectionType = projectionPriority.split(",");
            Collections.addAll(this.mProjectionPriority, projectionType);
        }
        getCurrentCountryApChannel();
    }

    private void parseControllerInfo() {
        String controllerInform;
        String controllerInform2 = this.mConfigList.get(IConfiguration.KEY_CONTROLLERINFORMATION);
        char c = 0;
        int number = IConfiguration.DEFAULT_CONTROLLERINFORMATION.split(";")[0].split(",").length;
        if (controllerInform2 == null) {
            Log.e(TAG, "parseControllerInfo, not found controllerInformation.");
            return;
        }
        String[] controllerInfoAll = controllerInform2.split(";");
        int length = controllerInfoAll.length;
        int i = 0;
        while (i < length) {
            String info = controllerInfoAll[i];
            String[] controllerInfo = info.split(",");
            if (controllerInfo.length == number) {
                String ctrName = controllerInfo[c].split("\\.")[c];
                String versionKey = ctrName + "Version";
                String ctrVersion = this.mConfigList.get(versionKey);
                boolean needUpdate = false;
                if (ctrVersion.equals(controllerInfo[3])) {
                    controllerInform = controllerInform2;
                } else {
                    needUpdate = true;
                    controllerInform = controllerInform2;
                    this.mConfigList.put(versionKey, controllerInfo[3]);
                    this.mConfiguration.saveConfigInfo(versionKey, controllerInfo[3]);
                }
                c = 0;
                ControllerInformation controllerInformation = new ControllerInformation(controllerInfo[0], controllerInfo[1], Boolean.parseBoolean(controllerInfo[2]), controllerInfo[3], needUpdate);
                this.mConnControllerInfoList.add(controllerInformation);
            } else {
                controllerInform = controllerInform2;
                Log.e(TAG, "parseControllerInfo, controllerInformation exception. number:" + number + ", controllerInfo.length:" + controllerInfo.length);
            }
            i++;
            controllerInform2 = controllerInform;
        }
    }

    public boolean saveConfigInfo(String key, String value) {
        if (!this.mConfigList.get(key).equals(String.valueOf(value))) {
            if (this.mConfiguration.saveConfigInfo(key, value)) {
                this.mConfigList.put(key, value);
                return true;
            }
            Log.e(TAG, "saveConfigInfo, failed. key:" + key + ", value:" + value);
            return false;
        }
        return true;
    }

    public synchronized boolean isSupportCarPlay() {
        String isSupportCarPlay = this.mConfigList.get(IConfiguration.KEY_SUPPORT_CARPLAY);
        if (isSupportCarPlay == null) {
            Log.e(TAG, "isSupportCarPlay, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isSupportCarPlay);
    }

    private synchronized boolean enableSupportCarplay(boolean supportCarplay) {
        return saveConfigInfo(IConfiguration.KEY_SUPPORT_CARPLAY, String.valueOf(supportCarplay));
    }

    public synchronized boolean isEnableCarPlay() {
        if (!isSupportCarPlay()) {
            return false;
        }
        String isEnableCarPlay = this.mConfigList.get(IConfiguration.KEY_CARPLAY_ENABLE);
        if (isEnableCarPlay == null) {
            Log.e(TAG, "isEnableCarPlay, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isEnableCarPlay);
    }

    public synchronized boolean setEnableCarPlay(boolean enableCarPlay) {
        return saveConfigInfo(IConfiguration.KEY_CARPLAY_ENABLE, String.valueOf(enableCarPlay));
    }

    public synchronized boolean isSupportCarLife() {
        String isSupportCarLife = this.mConfigList.get(IConfiguration.KEY_SUPPORT_CARLIFE);
        if (isSupportCarLife == null) {
            Log.e(TAG, "isSupportCarLife, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isSupportCarLife);
    }

    private synchronized boolean enableSupportCarlife(boolean supportCarlife) {
        return saveConfigInfo(IConfiguration.KEY_SUPPORT_CARLIFE, String.valueOf(supportCarlife));
    }

    public synchronized boolean isEnableCarLife() {
        if (!isSupportCarLife()) {
            return false;
        }
        String isEnableCarLife = this.mConfigList.get(IConfiguration.KEY_CARLIFE_ENABLE);
        if (isEnableCarLife == null) {
            Log.e(TAG, "isEnableCarLife, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isEnableCarLife);
    }

    public synchronized boolean setEnableCarLife(boolean enableCarLife) {
        return saveConfigInfo(IConfiguration.KEY_CARLIFE_ENABLE, String.valueOf(enableCarLife));
    }

    public synchronized int setEnableAndroidCarlife(boolean flag) {
        if (isEnableCarLife() && this.mConfiguration.saveConfigInfo(IConfiguration.KEY_SUPPORT_ANDROID_CARLIFE, String.valueOf(flag))) {
            this.mConfigList.put(IConfiguration.KEY_SUPPORT_ANDROID_CARLIFE, String.valueOf(flag));
            return 0;
        }
        Log.e(TAG, "setEnableAndroidCarlife, store failed.");
        return 1;
    }

    public synchronized int setEnableIosCarlife(boolean flag) {
        if (isEnableCarLife() && this.mConfiguration.saveConfigInfo(IConfiguration.KEY_SUPPORT_IOS_CARLIFE, String.valueOf(flag))) {
            this.mConfigList.put(IConfiguration.KEY_SUPPORT_IOS_CARLIFE, String.valueOf(flag));
            return 0;
        }
        Log.e(TAG, "setEnableIosCarlife, store failed.");
        return 1;
    }

    public boolean isSupportAndroidAuto() {
        String isSupportAndroidAuto = this.mConfigList.get(IConfiguration.KEY_SUPPORT_ANDROIDAUTO);
        if (isSupportAndroidAuto == null) {
            Log.e(TAG, "isSupportAndroidAuto, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isSupportAndroidAuto);
    }

    public synchronized boolean isEnableAndroidAuto() {
        if (!isSupportAndroidAuto()) {
            return false;
        }
        String isEnableAndroidAuto = this.mConfigList.get(IConfiguration.KEY_ANDROIDAUTO_ENABLE);
        if (isEnableAndroidAuto == null) {
            Log.e(TAG, "isEnableAndroidAuto, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isEnableAndroidAuto);
    }

    public synchronized boolean setEnableAndroidAuto(boolean enableAndroidAuto) {
        return saveConfigInfo(IConfiguration.KEY_ANDROIDAUTO_ENABLE, String.valueOf(enableAndroidAuto));
    }

    public synchronized boolean isSupportIpod() {
        String isSupportIpod = this.mConfigList.get(IConfiguration.KEY_SUPPORT_IPOD);
        if (isSupportIpod == null) {
            Log.e(TAG, "isSupportIpod, config info does not exist.");
            return false;
        }
        Log.d(TAG, "isSupportIpod: " + isSupportIpod);
        return Boolean.parseBoolean(isSupportIpod);
    }

    private synchronized boolean enableSupportIpod(boolean supportIpod) {
        return saveConfigInfo(IConfiguration.KEY_SUPPORT_IPOD, String.valueOf(supportIpod));
    }

    public synchronized boolean isEnableIpod() {
        if (!isSupportIpod()) {
            return false;
        }
        String isEnableIpod = this.mConfigList.get(IConfiguration.KEY_IPOD_ENABLE);
        if (isEnableIpod == null) {
            Log.e(TAG, "isEnableIpod, config info does not exist.");
            return false;
        }
        Log.d(TAG, "isEnableIpod: " + isEnableIpod);
        return Boolean.parseBoolean(isEnableIpod);
    }

    public synchronized boolean enableIpod(boolean flag) {
        if (isSupportIpod()) {
            return saveConfigInfo(IConfiguration.KEY_IPOD_ENABLE, String.valueOf(flag));
        }
        Log.e(TAG, "enableIpod, store failed.");
        return false;
    }

    public int getPlatformCapabilitys() {
        int capability = 65535;
        if (!isSupportCarLife() || !isEnableCarLife()) {
            capability = 65535 & (-2305);
        }
        if (!isSupportCarPlay() || !isEnableCarPlay()) {
            capability &= -577;
        }
        if (!isSupportAndroidAuto() || !isEnableAndroidAuto()) {
            capability &= -1153;
        }
        if (!isSupportIpod() || !isEnableIpod()) {
            return capability & (-9);
        }
        return capability;
    }

    public List<ControllerInformation> getControllerInformations() {
        return this.mConnControllerInfoList;
    }

    public synchronized boolean isProjectionAutoShow() {
        String isAutoShow = this.mConfigList.get(IConfiguration.KEY_PROJECTION_AUTO_SHOW);
        if (isAutoShow == null) {
            Log.e(TAG, "isProjectionAutoShow, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isAutoShow);
    }

    public synchronized boolean setProjectionAutoShow(boolean flag) {
        return saveConfigInfo(IConfiguration.KEY_PROJECTION_AUTO_SHOW, String.valueOf(flag));
    }

    public synchronized List<String> getProjectionPriority() {
        return this.mProjectionPriority;
    }

    public synchronized boolean setProjectionPriority(List<String> list) {
        if (list != null) {
            if (list.size() != 0) {
                if (list.equals(this.mProjectionPriority)) {
                    Log.d(TAG, "setProjectionPriority, old:" + this.mProjectionPriority + ", new:" + list);
                    return true;
                }
                if (this.mConfiguration.saveConfigInfo(IConfiguration.KEY_PROJECTION_PRIORITY, String.join(",", list))) {
                    this.mProjectionPriority.clear();
                    this.mProjectionPriority.addAll(list);
                    return true;
                }
                Log.e(TAG, "setProjectionPriority, store failed.");
                return false;
            }
        }
        Log.e(TAG, "setProjectionPriority, invalid parameter.");
        return false;
    }

    public void setHppService(int service) {
        String strService;
        if (service == 1) {
            strService = "CarPlay";
        } else if (service == 2) {
            strService = "CarLife";
        } else if (service == 3) {
            strService = "AndroidAuto";
        } else {
            return;
        }
        if (!strService.equals(this.mProjectionPriority.getFirst())) {
            this.mProjectionPriority.remove(strService);
            this.mProjectionPriority.addFirst(strService);
            setProjectionPriority(this.mProjectionPriority);
        }
    }

    public boolean isDeviceAccess() {
        String allow = this.mConfigList.get(IConfiguration.KEY_DEVICE_ACCESS_AUTHORIZATION);
        if (allow == null) {
            Log.e(TAG, "isDeviceAccess, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(allow);
    }

    public boolean enableDeviceAccess(boolean flag) {
        return saveConfigInfo(IConfiguration.KEY_DEVICE_ACCESS_AUTHORIZATION, String.valueOf(flag));
    }

    public boolean isCapabilityFromProjectionService() {
        String allow = this.mConfigList.get(IConfiguration.KEY_CAPABILITY_FROM_PROJECTIONSERVICE);
        if (allow == null) {
            Log.e(TAG, "isCapabilityFromProjectionService, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(allow);
    }

    public String getConfig(String key) {
        return this.mConfigList.get(key);
    }

    public boolean enableProjectionNotify() {
        String enableProjectionNotify = this.mConfigList.get(IConfiguration.KEY_NOTIFY_BT_PROJECTION_STATE);
        if (enableProjectionNotify == null) {
            Log.e(TAG, "enableProjectionNotify, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(enableProjectionNotify);
    }

    public int getWifiApChannel(int band, int projectionType) {
        int apChannel = 0;
        WifiApChannelInfo wifiApChannelInfo = this.mWifiApChannelInfo;
        if (wifiApChannelInfo != null) {
            if (band != 1) {
                if (band == 2) {
                    if (projectionType == 1) {
                        apChannel = wifiApChannelInfo.get5gCpchannel();
                    } else if (projectionType == 3) {
                        apChannel = wifiApChannelInfo.get5gAachannel();
                    } else if (projectionType == 2) {
                        apChannel = wifiApChannelInfo.get5gClchannel();
                    }
                }
            } else if (projectionType == 1) {
                apChannel = wifiApChannelInfo.get2gCpchannel();
            } else if (projectionType == 3) {
                apChannel = wifiApChannelInfo.get2gAachannel();
            } else if (projectionType == 2) {
                apChannel = wifiApChannelInfo.get2gClchannel();
            }
        }
        Log.e(TAG, "getWifiApChannel, band:" + band + ", projectionType:" + projectionType + ", apChannel:" + apChannel);
        return apChannel;
    }

    private void getCurrentCountryApChannel() {
        String[] defaultWifiApChannelInfo = {"DEF", "153", "153", "153", "6", "6", "6", "Default"};
        this.mWifiApChannelInfo = new WifiApChannelInfo(defaultWifiApChannelInfo);
        String countryCode = SystemProperties.get(BOOT_DEFAULT_WIFI_COUNTRY_CODE);
        Log.e(TAG, "getCurrentCountryApChannel, countryCode: " + countryCode);
        if (countryCode == null || countryCode.isEmpty()) {
            Log.e(TAG, "countryCode is null, use defaultWifiApConfig.");
            return;
        }
        String wifiApConfigs = this.mConfigList.get(IConfiguration.KEY_GET_WIFI_AP_CHANNEL_INFO);
        if (wifiApConfigs == null || wifiApConfigs.isEmpty()) {
            Log.e(TAG, "getCurrentCountryApChannel, not found wifi ap channel info, use default info.");
            return;
        }
        String[] wifiApConfigItems = wifiApConfigs.split(";");
        for (String wifiApConfigItem : wifiApConfigItems) {
            String[] wifiApConfig = wifiApConfigItem.split(",");
            if (wifiApConfig.length == 8 && wifiApConfig[0].equals(countryCode)) {
                this.mWifiApChannelInfo = new WifiApChannelInfo(wifiApConfig);
                return;
            }
        }
    }

    public boolean isCpwlDetectingOverIapEnabled() {
        String isCpwlDetectingOverIapEnabled = this.mConfigList.get(IConfiguration.KEY_ENABLE_CWPL_DETECING_OVER_IAP);
        if (isCpwlDetectingOverIapEnabled == null) {
            Log.e(TAG, "isCpwlDetectingOverIapEnabled, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isCpwlDetectingOverIapEnabled);
    }

    public int getDatabaseSyncTime() {
        String timeInterval = this.mConfigList.get(IConfiguration.KEY_DATABASE_SYSNC_TIME);
        if (timeInterval == null) {
            Log.e(TAG, "getDatabaseSyncTime, config info does not exist.");
            timeInterval = this.mContext.getString(R.string.DatabaseSyncTime);
        }
        return Integer.parseInt(timeInterval);
    }

    public int getProjectionConnRequestMode() {
        String mode = this.mConfigList.get(IConfiguration.KEY_PROJECTION_CONN_REQUEST_MODE);
        if (mode == null) {
            Log.e(TAG, "getProjectionConnRequestMode, config info does not exist.");
            mode = this.mContext.getString(R.string.ProjectionConnRequestMode);
        }
        return Integer.parseInt(mode);
    }

    public boolean isProjectionHandover() {
        String isProjectionHandover = this.mConfigList.get(IConfiguration.KEY_PROJECTION_HANDOVER);
        if (isProjectionHandover == null) {
            Log.e(TAG, "isProjectionHandover, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isProjectionHandover);
    }

    public int getDefaultProjection(int devType) {
        if (devType == 2) {
            String projectionName = this.mConfigList.get(IConfiguration.KEY_DEFAULT_PROJECTION_ON_IPHONE);
            if (projectionName.compareToIgnoreCase("CarPlay") == 0) {
                return 1;
            }
            return projectionName.compareToIgnoreCase("CarLife") == 0 ? 2 : 0;
        }
        if (devType == 1) {
            String projectionName2 = this.mConfigList.get(IConfiguration.KEY_DEFAULT_PROJECTION_ON_ANDROID);
            if (projectionName2.compareToIgnoreCase("CarLife") == 0) {
                return 2;
            }
            if (projectionName2.compareToIgnoreCase("AndroidAuto") == 0) {
                return 3;
            }
            return 0;
        }
        return 0;
    }

    public boolean isSupportServiceByCapability(int capabaility) {
        int service = DcmUtils.getSrvTypeByCapability(capabaility);
        if (service == 1) {
            boolean isSupport = isEnableCarPlay();
            return isSupport;
        }
        if (service == 2) {
            boolean isSupport2 = isEnableCarLife();
            return isSupport2;
        }
        if (service == 3) {
            boolean isSupport3 = isEnableAndroidAuto();
            return isSupport3;
        }
        if (service != 4) {
            return false;
        }
        boolean isSupport4 = isEnableIpod();
        return isSupport4;
    }

    public boolean isEnableDeviceDetectionControl() {
        String detectionControlFlag = this.mConfigList.get(IConfiguration.KEY_ENABLE_DEVICE_DETECTION_CONTROL);
        if (detectionControlFlag == null) {
            Log.e(TAG, "isEnableDeviceDetectionControl, config info does not exist.");
            detectionControlFlag = this.mContext.getString(R.string.EnableDeviceDetectionControl);
        }
        return Boolean.parseBoolean(detectionControlFlag);
    }

    public boolean setDeviceConnectionDetection(boolean on, boolean persist) {
        if (!isEnableDeviceDetectionControl()) {
            return false;
        }
        if (persist) {
            return saveConfigInfo(IConfiguration.KEY_ALLOW_DEVICE_DETECTION, String.valueOf(on));
        }
        this.mConfigList.put(IConfiguration.KEY_ALLOW_DEVICE_DETECTION, String.valueOf(on));
        return true;
    }

    public boolean isDeviceDetectionAllowed() {
        if (isEnableDeviceDetectionControl()) {
            return Boolean.parseBoolean(this.mConfigList.get(IConfiguration.KEY_ALLOW_DEVICE_DETECTION));
        }
        return true;
    }

    public List<Integer> getProjectionReadyCase() {
        String projectionReadyCase = this.mConfigList.get(IConfiguration.KEY_PROJECTION_READY_CASE);
        if (projectionReadyCase == null) {
            Log.e(TAG, "getProjectionReadyCase, config info does not exist.");
            projectionReadyCase = this.mContext.getString(R.string.ProjectionReadyCase);
        }
        String[] srvTypeStrings = projectionReadyCase.split(",");
        List<Integer> srvTypes = new ArrayList<>();
        for (String srvTypeString : srvTypeStrings) {
            if (srvTypeString.equals("CarPlay")) {
                srvTypes.add(1);
            } else if (srvTypeString.equals("CarLife")) {
                srvTypes.add(2);
            } else if (srvTypeString.equals("AndroidAuto")) {
                srvTypes.add(3);
            } else if (srvTypeString.equals("Ipod")) {
                srvTypes.add(4);
            }
        }
        return srvTypes;
    }

    public int getSupportedProjectionCapablity() {
        String projectionReadyCase = this.mConfigList.get(IConfiguration.KEY_PROJECTION_READY_CASE);
        if (projectionReadyCase == null) {
            Log.e(TAG, "getProjectionReadyCase, config info does not exist.");
            projectionReadyCase = this.mContext.getString(R.string.ProjectionReadyCase);
        }
        String[] srvTypeStrings = projectionReadyCase.split(",");
        int capabilityType = 0;
        for (String srvTypeString : srvTypeStrings) {
            if (srvTypeString.equals("CarPlay")) {
                capabilityType |= 576;
            } else if (srvTypeString.equals("CarLife")) {
                capabilityType |= 2304;
            } else if (srvTypeString.equals("AndroidAuto")) {
                capabilityType |= 1152;
            } else if (srvTypeString.equals("Ipod")) {
                capabilityType |= 8;
            }
        }
        Log.e(TAG, "getSupportedProjectionCapablity, supportedCapability: " + capabilityType);
        return capabilityType;
    }

    public boolean isNeedGetPhoneName() {
        String isNeed = this.mConfigList.get(IConfiguration.KEY_GET_PHONE_NAME_BY_IAP);
        if (isNeed == null) {
            isNeed = this.mContext.getString(R.string.GetPhoneNameByIap);
        }
        return Boolean.valueOf(isNeed).booleanValue();
    }

    public boolean isMonitorPower() {
        String isMonitor = this.mConfigList.get(IConfiguration.KEY_MONITOR_POWER_STATUS);
        if (isMonitor == null) {
            Log.e(TAG, "getEnablePowerFocus, config info does not exist.");
            isMonitor = this.mContext.getString(R.string.MonitorPowerStatus);
        }
        return Boolean.parseBoolean(isMonitor);
    }

    public int getWifiApBandForCarplay() {
        String band = this.mConfigList.get(IConfiguration.KEY_WIFI_AP_BAND_FOR_CARPLAY);
        if (band == null) {
            Log.d(TAG, "getWifiApBandForCarplay, config info does not exist.");
            band = this.mContext.getString(R.string.WifiApBandForCarplay);
        }
        return Integer.parseInt(band);
    }

    public boolean isIndexOfSameDeviceNameEnabled() {
        String isIndexOfSameDeviceNameEnabled = this.mConfigList.get(IConfiguration.KEY_ENABLE_INDEX_OF_SAME_DEVICE_NAME);
        if (isIndexOfSameDeviceNameEnabled == null) {
            Log.e(TAG, "isIndexOfSameDeviceNameEnabled, config info does not exist.");
            return false;
        }
        return Boolean.parseBoolean(isIndexOfSameDeviceNameEnabled);
    }
}
