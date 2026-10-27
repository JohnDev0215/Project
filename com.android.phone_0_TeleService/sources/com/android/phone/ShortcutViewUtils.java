package com.android.phone;

import android.content.Context;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.telephony.emergency.EmergencyNumber;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.telephony.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ShortcutViewUtils {
    static final int[] PROMOTED_CATEGORIES;
    static final int PROMOTED_CATEGORIES_BITMASK;

    static {
        int[] iArr = {1, 2, 4};
        PROMOTED_CATEGORIES = iArr;
        int i = 0;
        for (int i2 : iArr) {
            i |= i2;
        }
        PROMOTED_CATEGORIES_BITMASK = i;
    }

    static class Config {
        private final boolean mCanEnableShortcutView;
        private PhoneInfo mPhoneInfo = null;

        Config(Context context, PersistableBundle persistableBundle, int i) {
            this.mCanEnableShortcutView = canEnableShortcutView(persistableBundle, i);
            refresh(context);
        }

        void refresh(Context context) {
            if (this.mCanEnableShortcutView && !isAirplaneModeOn(context)) {
                this.mPhoneInfo = ShortcutViewUtils.pickPreferredPhone(context);
            } else {
                this.mPhoneInfo = null;
            }
        }

        boolean isEnabled() {
            return this.mPhoneInfo != null;
        }

        PhoneInfo getPhoneInfo() {
            return this.mPhoneInfo;
        }

        String getCountryIso() {
            PhoneInfo phoneInfo = this.mPhoneInfo;
            if (phoneInfo == null) {
                return null;
            }
            return phoneInfo.getCountryIso();
        }

        boolean hasPromotedEmergencyNumber(String str) {
            PhoneInfo phoneInfo = this.mPhoneInfo;
            if (phoneInfo == null) {
                return false;
            }
            return phoneInfo.hasPromotedEmergencyNumber(str);
        }

        private boolean canEnableShortcutView(PersistableBundle persistableBundle, int i) {
            if (i != 2) {
                Log.d("ShortcutViewUtils", "Disables shortcut view since it's not launched from power menu");
                return false;
            }
            if (persistableBundle != null && persistableBundle.getBoolean("support_emergency_dialer_shortcut_bool")) {
                return true;
            }
            Log.d("ShortcutViewUtils", "Disables shortcut view by carrier requirement");
            return false;
        }

        private boolean isAirplaneModeOn(Context context) {
            return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
        }
    }

    static class PhoneInfo {
        private final boolean mCanPlaceEmergencyCall;
        private final String mCountryIso;
        private final PhoneAccountHandle mHandle;
        private final List<EmergencyNumber> mPromotedEmergencyNumbers;
        private final int mSubId;

        private PhoneInfo(PhoneAccountHandle phoneAccountHandle, boolean z, int i, String str, List<EmergencyNumber> list) {
            this.mHandle = phoneAccountHandle;
            this.mCanPlaceEmergencyCall = z;
            this.mSubId = i;
            this.mCountryIso = str;
            this.mPromotedEmergencyNumbers = list;
        }

        public PhoneAccountHandle getPhoneAccountHandle() {
            return this.mHandle;
        }

        public String getCountryIso() {
            return this.mCountryIso;
        }

        public List<EmergencyNumber> getPromotedEmergencyNumbers() {
            return this.mPromotedEmergencyNumbers;
        }

        public boolean isSufficientForEmergencyCall(Context context) {
            return this.mCanPlaceEmergencyCall && this.mPromotedEmergencyNumbers != null && ShortcutViewUtils.isSupportedCountry(context, this.mCountryIso);
        }

        public boolean hasPromotedEmergencyNumber(String str) {
            Iterator<EmergencyNumber> it = this.mPromotedEmergencyNumbers.iterator();
            while (it.hasNext()) {
                if (it.next().getNumber().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            if (this.mHandle != null) {
                sb.append("handle=");
                sb.append(this.mHandle.getId());
                sb.append(", ");
            }
            sb.append("subId=");
            sb.append(this.mSubId);
            sb.append(", canPlaceEmergencyCall=");
            sb.append(this.mCanPlaceEmergencyCall);
            sb.append(", networkCountryIso=");
            sb.append(this.mCountryIso);
            if (this.mPromotedEmergencyNumbers != null) {
                sb.append(", emergencyNumbers=");
                for (EmergencyNumber emergencyNumber : this.mPromotedEmergencyNumbers) {
                    sb.append(emergencyNumber.getNumber());
                    sb.append(":");
                    sb.append(emergencyNumber);
                    sb.append(",");
                }
            }
            sb.append("}");
            return sb.toString();
        }
    }

    static PhoneInfo pickPreferredPhone(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (telephonyManager.getPhoneCount() <= 0) {
            Log.w("ShortcutViewUtils", "No phone available!");
            return null;
        }
        Map<Integer, List<EmergencyNumber>> promotedEmergencyNumberLists = getPromotedEmergencyNumberLists(telephonyManager);
        if (promotedEmergencyNumberLists != null && !promotedEmergencyNumberLists.isEmpty()) {
            TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
            PhoneAccountHandle defaultOutgoingPhoneAccount = telecomManager.getDefaultOutgoingPhoneAccount("tel");
            if (defaultOutgoingPhoneAccount != null) {
                PhoneInfo phoneInfoLoadPhoneInfo = loadPhoneInfo(context, defaultOutgoingPhoneAccount, telephonyManager, telecomManager, promotedEmergencyNumberLists);
                if (phoneInfoLoadPhoneInfo.isSufficientForEmergencyCall(context)) {
                    return phoneInfoLoadPhoneInfo;
                }
                Log.w("ShortcutViewUtils", "Default PhoneAccount is insufficient for emergency call: " + phoneInfoLoadPhoneInfo.toString());
            } else {
                Log.w("ShortcutViewUtils", "Missing default PhoneAccount! Is this really a phone device?");
            }
            List<PhoneAccountHandle> callCapablePhoneAccounts = telecomManager.getCallCapablePhoneAccounts();
            if (callCapablePhoneAccounts != null && !callCapablePhoneAccounts.isEmpty()) {
                Iterator<PhoneAccountHandle> it = callCapablePhoneAccounts.iterator();
                while (it.hasNext()) {
                    PhoneInfo phoneInfoLoadPhoneInfo2 = loadPhoneInfo(context, it.next(), telephonyManager, telecomManager, promotedEmergencyNumberLists);
                    if (phoneInfoLoadPhoneInfo2.isSufficientForEmergencyCall(context)) {
                        return phoneInfoLoadPhoneInfo2;
                    }
                    if (Log.isLoggable("ShortcutViewUtils", 3)) {
                        Log.d("ShortcutViewUtils", "PhoneAccount " + phoneInfoLoadPhoneInfo2.toString() + " is insufficient for emergency call.");
                    }
                }
            }
            Log.w("ShortcutViewUtils", "No PhoneAccount available for emergency call!");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSupportedCountry(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : context.getResources().getStringArray(R.array.config_countries_to_enable_shortcut_view)) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    private static PhoneInfo loadPhoneInfo(Context context, PhoneAccountHandle phoneAccountHandle, TelephonyManager telephonyManager, TelecomManager telecomManager, Map<Integer, List<EmergencyNumber>> map) {
        boolean zHasCapabilities;
        int subIdForPhoneAccount;
        PhoneAccount phoneAccount = telecomManager.getPhoneAccount(phoneAccountHandle);
        if (phoneAccount != null) {
            zHasCapabilities = phoneAccount.hasCapabilities(16);
            subIdForPhoneAccount = telephonyManager.getSubIdForPhoneAccount(phoneAccount);
        } else {
            zHasCapabilities = false;
            subIdForPhoneAccount = -1;
        }
        int i = subIdForPhoneAccount;
        boolean z = zHasCapabilities;
        TelephonyManager telephonyManagerCreateForSubscriptionId = telephonyManager.createForSubscriptionId(i);
        return new PhoneInfo(phoneAccountHandle, z, i, telephonyManagerCreateForSubscriptionId != null ? telephonyManagerCreateForSubscriptionId.getNetworkCountryIso() : null, map != null ? removeCarrierSpecificPrefixes(context, i, map.get(Integer.valueOf(i))) : null);
    }

    private static String[] getCarrierSpecificPrefixes(Context context, int i) {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return null;
        }
        return configForSubId.getStringArray("emergency_number_prefix_string_array");
    }

    private static List<EmergencyNumber> removeCarrierSpecificPrefixes(Context context, int i, List<EmergencyNumber> list) {
        String[] carrierSpecificPrefixes = getCarrierSpecificPrefixes(context, i);
        if (ArrayUtils.isEmpty(carrierSpecificPrefixes)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<EmergencyNumber> it = list.iterator();
        while (it.hasNext()) {
            EmergencyNumber next = it.next();
            String number = next.getNumber();
            int length = carrierSpecificPrefixes.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str = carrierSpecificPrefixes[i2];
                if (!TextUtils.isEmpty(str) && number.startsWith(str) && !number.equals(str)) {
                    next = new EmergencyNumber(number.substring(str.length()), next.getCountryIso(), next.getMnc(), next.getEmergencyServiceCategoryBitmask(), next.getEmergencyUrns(), next.getEmergencyNumberSourceBitmask(), next.getEmergencyCallRouting());
                    break;
                }
                i2++;
            }
            if (!arrayList.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static Map<Integer, List<EmergencyNumber>> getPromotedEmergencyNumberLists(TelephonyManager telephonyManager) {
        Map<Integer, List<EmergencyNumber>> emergencyNumberList = telephonyManager.getEmergencyNumberList();
        if (emergencyNumberList == null || emergencyNumberList.isEmpty()) {
            Log.w("ShortcutViewUtils", "Unable to retrieve emergency number lists!");
            return new ArrayMap();
        }
        boolean zIsLoggable = Log.isLoggable("ShortcutViewUtils", 3);
        ArrayMap arrayMap = new ArrayMap();
        for (Map.Entry<Integer, List<EmergencyNumber>> entry : emergencyNumberList.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                List<EmergencyNumber> value = entry.getValue();
                if (zIsLoggable) {
                    Log.d("ShortcutViewUtils", "Emergency numbers of " + entry.getKey());
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (EmergencyNumber emergencyNumber : value) {
                    boolean z = (emergencyNumber.getEmergencyServiceCategoryBitmask() & PROMOTED_CATEGORIES_BITMASK) != 0;
                    boolean z2 = (emergencyNumber.getEmergencyNumberSourceBitmask() & 16) != 0;
                    if (zIsLoggable) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("  ");
                        sb.append(emergencyNumber);
                        sb.append(z ? "M" : "");
                        sb.append(z2 ? "P" : "");
                        Log.d("ShortcutViewUtils", sb.toString());
                    }
                    if (z) {
                        if (z2) {
                            arrayList.add(emergencyNumber);
                        } else {
                            arrayList2.add(emergencyNumber);
                        }
                    }
                }
                arrayList.addAll(arrayList2);
                if (!arrayList.isEmpty()) {
                    arrayMap.put(entry.getKey(), arrayList);
                }
            }
        }
        if (arrayMap.isEmpty()) {
            Log.w("ShortcutViewUtils", "No promoted emergency number found!");
        }
        return arrayMap;
    }
}
