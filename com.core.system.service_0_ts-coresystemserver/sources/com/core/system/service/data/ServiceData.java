package com.core.system.service.data;

import android.text.TextUtils;
import com.ts.car.input.ConfigParser;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ServiceData {
    public static final String DEFAULT_VALUE_STARTMODE_BIND = "bind";
    public static final String JSON_KEY_MAX_RETRY_TIMES = "mMaxRetryTimes";
    public static final String JSON_KEY_ONESHOT = "mOneShot";
    public static final String JSON_KEY_USERCONFIG = "mUserConfig";
    public static final String PROTECTION_LEVEL_DANGEROUS = "dangerous";
    public static final int PROTECTION_LEVEL_DANGEROUS_LEVEL = 1;
    public static final String PROTECTION_LEVEL_NORMAL = "normal";
    public static final int PROTECTION_LEVEL_NORMAL_LEVEL = 0;
    public static final String PROTECTION_LEVEL_SIGNATURE = "signature";
    public static final String PROTECTION_LEVEL_SIGNATUREORSYSTEM = "signatureOrSystem";
    public static final int PROTECTION_LEVEL_SIGNATUREORSYSTEM_LEVEL = 3;
    public static final int PROTECTION_LEVEL_SIGNATURE_LEVEL = 2;
    public static final String START_MODE_BIND = "bind";
    public static final String START_MODE_START = "start";
    public static final String TAG_CLASS_NAME = "mServiceClassName";
    public static final String TAG_COMPONENT = "mComponent";
    public static final String TAG_DELAY = "mDelay";
    public static final String TAG_DEPENDENCY = "mDependency";
    public static final String TAG_DEPEND_NAME = "mDependName";
    public static final String TAG_DEPEND_TYPE = "mDependType";
    public static final String TAG_DIRECT_BOOT = "mDirectBoot";
    public static final String TAG_EXTERNAL_SERVICE = "mIsExternalService";
    public static final String TAG_IS_ACCESSIBILITY_SERVICE = "mIsAccessibilityService";
    public static final String TAG_NAME = "mServiceName";
    public static final String TAG_PACKAGE = "mServicePackage";
    public static final String TAG_PROTECTION_LEVEL = "mProtectionLevel";
    public static final String TAG_START_MODE = "mStartMode";
    public static final String TAG_USED_STATE = "mServiceUsedState";
    public static final String USER_CONFIG_USER_ALL = "UserAll";
    public static final String USER_CONFIG_USER_ALL_EXCLUDE_PROFILE_USER = "UserAllExcludeProfileUser";
    public static final String USER_CONFIG_USER_FOREGROUND = "UserForeground";
    public static final String USER_CONFIG_USER_SYSTEM = "UserSystem";
    public static final String VALUE_DEPENDENCY_STRONG = "strong";
    public static final String VALUE_DEPENDENCY_WEAK = "weak";
    private String mComponent;
    private String mServiceClassName;
    private String mServiceName;
    private String mServicePackage;
    private String mStartMode;
    private boolean oneShot;
    private boolean mIsExternalService = true;
    private String mUserConfig = USER_CONFIG_USER_SYSTEM;
    private String mProtectionLevel = PROTECTION_LEVEL_NORMAL;
    private boolean mDirectBoot = false;
    private boolean mIsAccessibilityService = false;
    private long mDelay = 0;
    private int mMaxRetryTimes = 0;
    private boolean mServiceUsedState = false;
    public ArrayList<String> mStrongDependServiceNameList = new ArrayList<>(0);
    public ArrayList<String> mWeakDependServiceNameList = new ArrayList<>(0);

    public boolean isForegroundUserConfig() {
        return TextUtils.equals(USER_CONFIG_USER_FOREGROUND, this.mUserConfig);
    }

    public boolean isSystemUserConfig() {
        return TextUtils.equals(USER_CONFIG_USER_SYSTEM, this.mUserConfig);
    }

    public boolean isExcludeProfileUserConfig() {
        return TextUtils.equals(USER_CONFIG_USER_ALL_EXCLUDE_PROFILE_USER, this.mUserConfig);
    }

    public boolean isAllUserConfig() {
        return TextUtils.equals(USER_CONFIG_USER_ALL, this.mUserConfig);
    }

    public boolean getOneShot() {
        return this.oneShot;
    }

    public void setOneShot(Boolean oneShot) {
        this.oneShot = oneShot.booleanValue();
    }

    public String getUserConfig() {
        return this.mUserConfig;
    }

    public void setUserConfig(String userConfig) {
        this.mUserConfig = userConfig;
    }

    public void setServiceName(String name) {
        this.mServiceName = name;
    }

    public String getServiceName() {
        return this.mServiceName;
    }

    public void addStrongDepend(String name, boolean ready) {
        this.mStrongDependServiceNameList.add(name);
    }

    public ArrayList<String> getStrongDependServiceNameList() {
        return this.mStrongDependServiceNameList;
    }

    public void addWeakDepend(String name, boolean ready) {
        this.mWeakDependServiceNameList.add(name);
    }

    public ArrayList<String> getWeakDependServiceNameList() {
        return this.mWeakDependServiceNameList;
    }

    public void setIsExternalService(boolean isExternalService) {
        this.mIsExternalService = isExternalService;
    }

    public boolean isExternalService() {
        return this.mIsExternalService;
    }

    public void setServiceClassName(String serviceClassName) {
        this.mServiceClassName = serviceClassName;
    }

    public String getServiceClassName() {
        return this.mServiceClassName;
    }

    public void setComponent(String component) {
        this.mComponent = component;
    }

    public String getComponent() {
        return this.mComponent;
    }

    public void setStartMode(String startMode) {
        this.mStartMode = startMode;
    }

    public String getStartMode() {
        return this.mStartMode;
    }

    public Boolean isModeStart() {
        return Boolean.valueOf(TextUtils.equals(this.mStartMode, START_MODE_START));
    }

    public void setProtectionLevel(String protectionLevel) {
        if (protectionLevel == null || ConfigParser.EMPTY.equals(protectionLevel)) {
            this.mProtectionLevel = PROTECTION_LEVEL_NORMAL;
        } else {
            this.mProtectionLevel = protectionLevel;
        }
    }

    public String getProtection() {
        return this.mProtectionLevel;
    }

    public int getProtectionLevel() {
        if (PROTECTION_LEVEL_DANGEROUS.equals(this.mProtectionLevel)) {
            return 1;
        }
        if (PROTECTION_LEVEL_SIGNATURE.equals(this.mProtectionLevel)) {
            return 2;
        }
        if (PROTECTION_LEVEL_SIGNATUREORSYSTEM.equals(this.mProtectionLevel)) {
            return 3;
        }
        return 0;
    }

    public void setDirectBoot(boolean directBoot) {
        this.mDirectBoot = directBoot;
    }

    public boolean isDirectBoot() {
        return this.mDirectBoot;
    }

    public void setDelay(long delay) {
        this.mDelay = delay;
    }

    public long getDelay() {
        return this.mDelay;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.mMaxRetryTimes = maxRetryTimes;
    }

    public int getMaxRetryTimes() {
        return this.mMaxRetryTimes;
    }

    public void setServicePackage(String servicePackage) {
        this.mServicePackage = servicePackage;
    }

    public String getServicePackage() {
        return this.mServicePackage;
    }

    public void setIsAccessibilityService(boolean isAccessibilityService) {
        this.mIsAccessibilityService = isAccessibilityService;
    }

    public boolean isAccessibilityService() {
        return this.mIsAccessibilityService;
    }

    public boolean isServiceUsedState() {
        return this.mServiceUsedState;
    }

    public void setServiceUsedState(boolean serviceUsedState) {
        this.mServiceUsedState = serviceUsedState;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ServiceData{");
        stringBuffer.append("mServiceName:");
        stringBuffer.append(this.mServiceName);
        stringBuffer.append(", ");
        stringBuffer.append("mServicePackage:");
        stringBuffer.append(this.mServicePackage);
        stringBuffer.append(", ");
        stringBuffer.append("mServiceClassName:");
        stringBuffer.append(this.mServiceClassName);
        stringBuffer.append(", ");
        stringBuffer.append("mComponent:");
        stringBuffer.append(this.mComponent);
        stringBuffer.append(", ");
        stringBuffer.append("mStartMode:");
        stringBuffer.append(this.mStartMode);
        stringBuffer.append(", ");
        stringBuffer.append("mProtectionLevel:");
        stringBuffer.append(this.mProtectionLevel);
        stringBuffer.append(", ");
        stringBuffer.append("mIsExternalService:");
        stringBuffer.append(this.mIsExternalService);
        stringBuffer.append(", ");
        stringBuffer.append("mDirectBoot:");
        stringBuffer.append(this.mDirectBoot);
        stringBuffer.append(", ");
        stringBuffer.append("mIsAccessibilityService:");
        stringBuffer.append(this.mIsAccessibilityService);
        stringBuffer.append(", ");
        stringBuffer.append("mDelay:");
        stringBuffer.append(this.mDelay);
        stringBuffer.append(", ");
        stringBuffer.append("mServiceUsedState:");
        stringBuffer.append(this.mServiceUsedState);
        stringBuffer.append(", ");
        stringBuffer.append("mUserConfig:");
        stringBuffer.append(this.mUserConfig);
        stringBuffer.append(", ");
        stringBuffer.append("oneShot:");
        stringBuffer.append(this.oneShot);
        stringBuffer.append(", ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        if (obj != null && (obj instanceof ServiceData) && (str = this.mServiceName) != null && str.equals(((ServiceData) obj).getServiceName()) && (str2 = this.mServicePackage) != null && str2.equals(((ServiceData) obj).getServicePackage()) && (str3 = this.mServiceClassName) != null && str3.equals(((ServiceData) obj).getServiceClassName())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.mServiceName;
        int code = str != null ? 0 + str.hashCode() : 0;
        String str2 = this.mServicePackage;
        if (str2 != null) {
            code += str2.hashCode();
        }
        String str3 = this.mServiceClassName;
        if (str3 != null) {
            return code + str3.hashCode();
        }
        return code;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.core.system.service.data.ServiceData createFromJson(org.json.JSONObject r25) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.core.system.service.data.ServiceData.createFromJson(org.json.JSONObject):com.core.system.service.data.ServiceData");
    }
}
