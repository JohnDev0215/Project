package com.android.provision;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class BootGuideManager {
    public static final String ACTIVATED = "activated";
    public static final String BOOT_BUSINESS_KEY = "sys.byd.boot_business";
    private static final String DEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String GUIDE = "guide";
    public static final String PRIVACY_COUNTRY_INDEX = "persist.sys.byd.country_index";
    public static final String PRIVACY_POLICY_AGREE = "persist.sys.vehicle_privacy_status";
    private static final String PRIVACY_POLICY_KEY = "persist.byd.privacy.policy";
    private static final String PRIVACY_POLICY_TIME = "persist.sys.vehicle_privacy_time";
    private static final int RIGHT_RUDDER = 1;
    private static final String RUDDER_VALUE = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private static final String SAFETY_WARN_KEY = "sys.byd.safety.warn";
    private static String TAG = "BootGuideManager";
    private static final String USER_AGREEMENT_AGREE = "persist.byd.user.agreement.agree";
    public static volatile BootGuideManager instance;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;

    public static BootGuideManager getInstance() {
        if (instance == null) {
            synchronized (BootGuideManager.class) {
                if (instance == null) {
                    instance = new BootGuideManager();
                }
            }
        }
        return instance;
    }

    public void init() {
        initBydSettings(BootGuideApplication.getAppContext());
    }

    public static boolean isPrivacyPolicyFinished() {
        String str = SystemProperties.get(PRIVACY_POLICY_KEY, String.valueOf(0));
        BydLogHelper.logW(TAG, "PrivacyPolicy status:" + str);
        return TextUtils.equals(str, "1");
    }

    public static boolean isAgreePrivacy() {
        int i = SystemProperties.getInt(PRIVACY_POLICY_AGREE, 0);
        BydLogHelper.logW(TAG, "isAgreePrivacy value:" + i);
        return i == 1;
    }

    public static boolean isSafetyWarnFinished() {
        int i = Settings.System.getInt(BootGuideApplication.getAppContext().getContentResolver(), SAFETY_WARN_KEY, 0);
        BydLogHelper.logW(TAG, "SafetyWarn status:" + i);
        return i == 1;
    }

    public void setUserAgreementAgree(boolean agree) {
        BydLogHelper.logW(TAG, "agree:" + agree);
        SystemProperties.set(USER_AGREEMENT_AGREE, agree ? "1" : "0");
    }

    public void setPrivacyPolicyAgree(Context context, boolean agree, long currentTime) {
        BydLogHelper.logW(TAG, "agree:" + agree + ",currentTime:" + currentTime);
        SystemProperties.set(PRIVACY_POLICY_TIME, String.valueOf(currentTime));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PRIVACY_PAGE_OPERATION_CHANGE");
        if (agree) {
            SystemProperties.set(PRIVACY_POLICY_AGREE, "1");
            Settings.Global.putInt(context.getContentResolver(), PRIVACY_POLICY_AGREE, 1);
            intent.putExtra("user_operation", 1);
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, 1);
        } else {
            SystemProperties.set(PRIVACY_POLICY_AGREE, "3");
            Settings.Global.putInt(context.getContentResolver(), PRIVACY_POLICY_AGREE, 3);
            intent.putExtra("user_operation", 3);
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, 2);
        }
        intent.putExtra("privacy_policy_version", "056.20221101");
        intent.putExtra("effective_date", new SimpleDateFormat(DEF_FORMAT).format(new Date(currentTime)));
        context.sendBroadcast(intent);
        BydLogHelper.logW(TAG, "send action:android.intent.action.PRIVACY_PAGE_OPERATION_CHANGE");
    }

    public void setPrivacyPolicyFinished() {
        try {
            SystemProperties.set(PRIVACY_POLICY_KEY, String.valueOf(1));
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e:" + e);
            e.printStackTrace();
        }
    }

    public void setSafetyWarnFinished() {
        setSettingsFinished(SAFETY_WARN_KEY, 1);
    }

    private void setSettingsFinished(String key, int value) {
        try {
            Settings.System.putInt(BootGuideApplication.getAppContext().getContentResolver(), key, value);
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e:" + e);
            e.printStackTrace();
        }
    }

    public void finishSetUpWizard(Context context) {
        if (!TextUtils.equals(ACTIVATED, getGuideStatus())) {
            getInstance().setGuideStatus(ACTIVATED);
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Settings.Global.putInt(context.getContentResolver(), "device_provisioned", 1);
            Settings.Secure.putInt(context.getContentResolver(), "user_setup_complete", 1);
            BydLogHelper.logW(TAG, "time:" + (System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e:" + e);
            e.printStackTrace();
        }
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) DefaultActivity.class), 2, 1);
    }

    public void setGuideStatus(String s) {
        try {
            if (TextUtils.isEmpty(s)) {
                return;
            }
            BydLogHelper.logW(TAG, "set BOOT_BUSINESS:" + s);
            SystemProperties.set("sys.byd.boot_business", s);
            SystemProperties.reportSyspropChanged();
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e:" + e);
            e.printStackTrace();
        }
    }

    public void setCountryIndex(int index) {
        BydLogHelper.logW(TAG, "setCountryIndex index = " + index);
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("_");
        if (strArrSplit != null && strArrSplit.length > index) {
            BydLogHelper.logW(TAG, "setCountryIndex versions = 2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_2.0_1.1_2.0_2.0_2.0_2.0_2.0_2.0_2.0_1.1_1.0_2.0_1.0_acy_korea_chinese_acy_korea_english_acy_korea_korea_1.0_1.0_1.0_2.0_2.0_1.0_1.0_2.0_2.0_1.0 ,version = " + strArrSplit[index]);
        }
        SystemProperties.set(PRIVACY_COUNTRY_INDEX, String.valueOf(index));
        SystemProperties.reportSyspropChanged();
    }

    public String getGuideStatus() {
        return SystemProperties.get("sys.byd.boot_business");
    }

    private void initBydSettings(Context context) {
        this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
    }

    public void notifyDiLink() {
        if (this.mBYDAutoSettingDevice != null) {
            Log.e(TAG, "--- notifyDilinkStarted ---");
            this.mBYDAutoSettingDevice.notifyDilinkStarted();
        }
    }

    public boolean isRightRudder() {
        int i = -1;
        try {
            i = SystemProperties.getInt(RUDDER_VALUE, -1);
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e:" + e);
            e.printStackTrace();
        }
        BydLogHelper.logW(TAG, "isRightRudder steer = " + i);
        return 1 == i;
    }
}
