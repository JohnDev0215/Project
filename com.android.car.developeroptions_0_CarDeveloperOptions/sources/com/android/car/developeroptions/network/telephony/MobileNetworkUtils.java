package com.android.car.developeroptions.network.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.provider.Telephony;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkUtils {
    public static boolean isDpcApnEnforced(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Telephony.Carriers.ENFORCE_MANAGED_URI, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() == 1) {
                    cursorQuery.moveToFirst();
                    boolean z = cursorQuery.getInt(0) > 0;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }

    public static boolean isWifiCallingEnabled(Context context, int i) {
        PhoneAccountHandle simCallManager = TelecomManager.from(context).getSimCallManager();
        if (simCallManager != null) {
            if (buildPhoneAccountConfigureIntent(context, simCallManager) != null) {
                return true;
            }
        } else {
            ImsManager imsManager = ImsManager.getInstance(context, i);
            if (imsManager != null && imsManager.isWfcEnabledByPlatform() && imsManager.isWfcProvisionedOnDevice() && isImsServiceStateReady(imsManager)) {
                return true;
            }
        }
        return false;
    }

    static Intent buildPhoneAccountConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent intentBuildConfigureIntent = buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONFIGURE_PHONE_ACCOUNT");
        return intentBuildConfigureIntent == null ? buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONNECTION_SERVICE_CONFIGURE") : intentBuildConfigureIntent;
    }

    private static Intent buildConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        if (phoneAccountHandle == null || phoneAccountHandle.getComponentName() == null || TextUtils.isEmpty(phoneAccountHandle.getComponentName().getPackageName())) {
            return null;
        }
        Intent intent = new Intent(str);
        intent.setPackage(phoneAccountHandle.getComponentName().getPackageName());
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
            return null;
        }
        return intent;
    }

    public static boolean isImsServiceStateReady(ImsManager imsManager) {
        boolean z = false;
        if (imsManager != null) {
            try {
                if (imsManager.getImsServiceState() == 2) {
                    z = true;
                }
            } catch (ImsException e) {
                Log.e("MobileNetworkUtils", "Exception when trying to get ImsServiceStatus: " + e);
            }
        }
        Log.d("MobileNetworkUtils", "isImsServiceStateReady=" + z);
        return z;
    }

    public static boolean showEuiccSettings(Context context) {
        if (!((EuiccManager) context.getSystemService(EuiccManager.class)).isEnabled()) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        String lowerCase = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getNetworkCountryIso().toLowerCase();
        String string = Settings.Global.getString(contentResolver, "euicc_supported_countries");
        boolean z = TextUtils.isEmpty(lowerCase) || (!TextUtils.isEmpty(string) && Arrays.asList(TextUtils.split(string.toLowerCase(), ",")).contains(lowerCase));
        boolean zContains = Arrays.asList(TextUtils.split(SystemProperties.get("ro.setupwizard.esim_cid_ignore", ""), ",")).contains(SystemProperties.get("ro.boot.cid", (String) null));
        boolean z2 = SystemProperties.getBoolean("esim.enable_esim_system_ui_by_default", true);
        return (Settings.Global.getInt(contentResolver, "development_settings_enabled", 0) != 0) || (Settings.Global.getInt(contentResolver, "euicc_provisioned", 0) != 0) || (!zContains && z2 && z);
    }

    public static void setMobileDataEnabled(Context context, int i, boolean z, boolean z2) {
        List<SubscriptionInfo> activeSubscriptionInfoList;
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        telephonyManagerCreateForSubscriptionId.setDataEnabled(z);
        if (!z2 || (activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList(true)) == null) {
            return;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (subscriptionInfo.getSubscriptionId() != i && !subscriptionInfo.isOpportunistic()) {
                ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(subscriptionInfo.getSubscriptionId()).setDataEnabled(false);
            }
        }
    }

    public static boolean isCdmaOptions(Context context, int i) {
        if (i == -1) {
            return false;
        }
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (telephonyManagerCreateForSubscriptionId.getPhoneType() == 2) {
            return true;
        }
        if (configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && configForSubId.getBoolean("world_phone_bool")) {
            return true;
        }
        if (isWorldMode(context, i)) {
            int i2 = Settings.Global.getInt(context.getContentResolver(), "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
            if (i2 == 9 || i2 == 8 || (i2 == 10 && !isTdscdmaSupported(context, telephonyManagerCreateForSubscriptionId))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGsmOptions(Context context, int i) {
        if (i == -1) {
            return false;
        }
        if (isGsmBasicOptions(context, i)) {
            return true;
        }
        int i2 = Settings.Global.getInt(context.getContentResolver(), "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
        return isWorldMode(context, i) && (i2 == 8 || i2 == 9 || (i2 == 10 && !isTdscdmaSupported(context, i)));
    }

    private static boolean isGsmBasicOptions(Context context, int i) {
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (telephonyManagerCreateForSubscriptionId.getPhoneType() == 1) {
            return true;
        }
        return (configForSubId == null || configForSubId.getBoolean("hide_carrier_network_settings_bool") || !configForSubId.getBoolean("world_phone_bool")) ? false : true;
    }

    public static boolean isWorldMode(Context context, int i) {
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (configForSubId == null) {
            return false;
        }
        return configForSubId.getBoolean("world_mode_enabled_bool");
    }

    public static boolean shouldDisplayNetworkSelectOptions(Context context, int i) {
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(context).createForSubscriptionId(i);
        PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfigForSubId(i);
        if (i != -1 && configForSubId != null && configForSubId.getBoolean("operator_selection_expand_bool") && (!configForSubId.getBoolean("csp_enabled_bool") || telephonyManagerCreateForSubscriptionId.isManualNetworkSelectionAllowed())) {
            if (isGsmBasicOptions(context, i)) {
                return true;
            }
            int i2 = Settings.Global.getInt(context.getContentResolver(), "preferred_network_mode" + i, Phone.PREFERRED_NT_MODE);
            if (isWorldMode(context, i) && i2 == 9) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTdscdmaSupported(Context context, int i) {
        return isTdscdmaSupported(context, ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i));
    }

    private static boolean isTdscdmaSupported(Context context, TelephonyManager telephonyManager) {
        PersistableBundle config = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfig();
        if (config == null) {
            return false;
        }
        if (config.getBoolean("support_tdscdma_bool")) {
            return true;
        }
        String operatorNumeric = telephonyManager.getServiceState().getOperatorNumeric();
        String[] stringArray = config.getStringArray("support_tdscdma_roaming_networks_string_array");
        if (stringArray != null && operatorNumeric != null) {
            for (String str : stringArray) {
                if (operatorNumeric.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getSearchableSubscriptionId(Context context) {
        int[] activeSubscriptionIdList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getActiveSubscriptionIdList();
        if (activeSubscriptionIdList.length >= 1) {
            return activeSubscriptionIdList[0];
        }
        return -1;
    }

    public static int getAvailability(Context context, int i, TelephonyAvailabilityCallback telephonyAvailabilityCallback) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        if (i != -1) {
            return telephonyAvailabilityCallback.getAvailabilityStatus(i);
        }
        int[] activeSubscriptionIdList = subscriptionManager.getActiveSubscriptionIdList();
        if (ArrayUtils.isEmpty(activeSubscriptionIdList)) {
            return telephonyAvailabilityCallback.getAvailabilityStatus(-1);
        }
        for (int i2 : activeSubscriptionIdList) {
            int availabilityStatus = telephonyAvailabilityCallback.getAvailabilityStatus(i2);
            if (availabilityStatus == 0) {
                return availabilityStatus;
            }
        }
        return telephonyAvailabilityCallback.getAvailabilityStatus(activeSubscriptionIdList[0]);
    }
}
