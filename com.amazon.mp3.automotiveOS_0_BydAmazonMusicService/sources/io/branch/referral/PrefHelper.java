package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class PrefHelper {
    private static String Branch_Key = null;
    private static String customServerURL_ = null;
    private static boolean enableLogging_ = false;
    private static PrefHelper prefHelper_;
    private static JSONObject savedAnalyticsData_;
    private SharedPreferences appSharedPrefs_;
    private final JSONObject installMetadata;
    private SharedPreferences.Editor prefsEditor_;
    private final JSONObject requestMetadata;
    private final JSONObject secondaryRequestMetadata;

    private PrefHelper(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("branch_referral_shared_pref", 0);
        this.appSharedPrefs_ = sharedPreferences;
        this.prefsEditor_ = sharedPreferences.edit();
        this.requestMetadata = new JSONObject();
        this.installMetadata = new JSONObject();
        this.secondaryRequestMetadata = new JSONObject();
    }

    public static PrefHelper getInstance(Context context) {
        if (prefHelper_ == null) {
            prefHelper_ = new PrefHelper(context);
        }
        return prefHelper_;
    }

    public String getAPIBaseUrl() {
        return URLUtil.isHttpsUrl(customServerURL_) ? customServerURL_ : "https://api2.branch.io/";
    }

    public void setTimeout(int i) {
        setInteger("bnc_timeout", i);
    }

    public int getTimeout() {
        return getInteger("bnc_timeout", 5500);
    }

    public void setRetryCount(int i) {
        setInteger("bnc_retry_count", i);
    }

    public int getRetryCount() {
        return getInteger("bnc_retry_count", 3);
    }

    public void setRetryInterval(int i) {
        setInteger("bnc_retry_interval", i);
    }

    public int getRetryInterval() {
        return getInteger("bnc_retry_interval", 1000);
    }

    public void setAppVersion(String str) {
        setString("bnc_app_version", str);
    }

    public String getAppVersion() {
        return getString("bnc_app_version");
    }

    public boolean setBranchKey(String str) {
        Branch_Key = str;
        String string = getString("bnc_branch_key");
        if (str != null && string != null && string.equals(str)) {
            return false;
        }
        clearPrefOnBranchKeyChange();
        setString("bnc_branch_key", str);
        return true;
    }

    public String getBranchKey() {
        if (Branch_Key == null) {
            Branch_Key = getString("bnc_branch_key");
        }
        return Branch_Key;
    }

    public void setDeviceFingerPrintID(String str) {
        setString("bnc_device_fingerprint_id", str);
    }

    public String getDeviceFingerPrintID() {
        return getString("bnc_device_fingerprint_id");
    }

    public void setSessionID(String str) {
        setString("bnc_session_id", str);
    }

    public String getSessionID() {
        return getString("bnc_session_id");
    }

    public void setIdentityID(String str) {
        setString("bnc_identity_id", str);
    }

    public String getIdentityID() {
        return getString("bnc_identity_id");
    }

    public void setIdentity(String str) {
        setString("bnc_identity", str);
    }

    public String getIdentity() {
        return getString("bnc_identity");
    }

    public void setLinkClickID(String str) {
        setString("bnc_link_click_id", str);
    }

    public String getLinkClickID() {
        return getString("bnc_link_click_id");
    }

    public void setIsAppLinkTriggeredInit(Boolean bool) {
        setBool("bnc_triggered_by_fb_app_link", bool);
    }

    public boolean getIsAppLinkTriggeredInit() {
        return getBool("bnc_triggered_by_fb_app_link");
    }

    public void setExternalIntentUri(String str) {
        setString("bnc_external_intent_uri", str);
    }

    public String getExternalIntentUri() {
        return getString("bnc_external_intent_uri");
    }

    public void setExternalIntentExtra(String str) {
        setString("bnc_external_intent_extra", str);
    }

    public String getExternalIntentExtra() {
        return getString("bnc_external_intent_extra");
    }

    public void setLinkClickIdentifier(String str) {
        setString("bnc_link_click_identifier", str);
    }

    public String getLinkClickIdentifier() {
        return getString("bnc_link_click_identifier");
    }

    public void setGoogleSearchInstallIdentifier(String str) {
        setString("bnc_google_search_install_identifier", str);
    }

    public String getGoogleSearchInstallIdentifier() {
        return getString("bnc_google_search_install_identifier");
    }

    public void setGooglePlayReferrer(String str) {
        setString("bnc_google_play_install_referrer_extras", str);
    }

    public String getGooglePlayReferrer() {
        return getString("bnc_google_play_install_referrer_extras");
    }

    public void setAppLink(String str) {
        setString("bnc_app_link", str);
    }

    public String getAppLink() {
        return getString("bnc_app_link");
    }

    public void setIsFullAppConversion(boolean z) {
        setBool("bnc_is_full_app_conversion", Boolean.valueOf(z));
    }

    public boolean isFullAppConversion() {
        return getBool("bnc_is_full_app_conversion");
    }

    public void setPushIdentifier(String str) {
        setString("bnc_push_identifier", str);
    }

    public String getPushIdentifier() {
        return getString("bnc_push_identifier");
    }

    public String getSessionParams() {
        return getString("bnc_session_params");
    }

    public void setSessionParams(String str) {
        setString("bnc_session_params", str);
    }

    public String getInstallParams() {
        return getString("bnc_install_params");
    }

    public void setInstallParams(String str) {
        setString("bnc_install_params", str);
    }

    public void setInstallReferrerParams(String str) {
        setString("bnc_install_referrer", str);
    }

    public void setUserURL(String str) {
        setString("bnc_user_url", str);
    }

    public String getUserURL() {
        return getString("bnc_user_url");
    }

    public int getIsReferrable() {
        return getInteger("bnc_is_referrable");
    }

    public void setIsReferrable() {
        setInteger("bnc_is_referrable", 1);
    }

    public void clearIsReferrable() {
        setInteger("bnc_is_referrable", 0);
    }

    boolean isAppTrackingLimited() {
        return getBool("bnc_limit_facebook_tracking");
    }

    public void clearUserValues() {
        Iterator<String> it = getBuckets().iterator();
        while (it.hasNext()) {
            setCreditCount(it.next(), 0);
        }
        setBuckets(new ArrayList<>());
        for (String str : getActions()) {
            setActionTotalCount(str, 0);
            setActionUniqueCount(str, 0);
        }
        setActions(new ArrayList<>());
    }

    private ArrayList<String> getBuckets() {
        String string = getString("bnc_buckets");
        if (string.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(string);
    }

    private void setBuckets(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            setString("bnc_buckets", "bnc_no_value");
        } else {
            setString("bnc_buckets", serializeArrayList(arrayList));
        }
    }

    public void setCreditCount(int i) {
        setCreditCount(Defines$Jsonkey.DefaultBucket.getKey(), i);
    }

    public void setCreditCount(String str, int i) {
        ArrayList<String> buckets = getBuckets();
        if (!buckets.contains(str)) {
            buckets.add(str);
            setBuckets(buckets);
        }
        setInteger("bnc_credit_base_" + str, i);
    }

    public int getCreditCount() {
        return getCreditCount(Defines$Jsonkey.DefaultBucket.getKey());
    }

    public int getCreditCount(String str) {
        return getInteger("bnc_credit_base_" + str);
    }

    private ArrayList<String> getActions() {
        String string = getString("bnc_actions");
        if (string.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(string);
    }

    private void setActions(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            setString("bnc_actions", "bnc_no_value");
        } else {
            setString("bnc_actions", serializeArrayList(arrayList));
        }
    }

    public void setActionTotalCount(String str, int i) {
        ArrayList<String> actions = getActions();
        if (!actions.contains(str)) {
            actions.add(str);
            setActions(actions);
        }
        setInteger("bnc_total_base_" + str, i);
    }

    public void setActionUniqueCount(String str, int i) {
        setInteger("bnc_balance_base_" + str, i);
    }

    public void setLATDAttributonWindow(int i) {
        setInteger("bnc_latd_attributon_window", i);
    }

    public int getLATDAttributonWindow() {
        return getInteger("bnc_latd_attributon_window");
    }

    private String serializeArrayList(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next() + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    private ArrayList<String> deserializeString(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, str.split(","));
        return arrayList;
    }

    public int getInteger(String str) {
        return getInteger(str, 0);
    }

    public int getInteger(String str, int i) {
        return prefHelper_.appSharedPrefs_.getInt(str, i);
    }

    public long getLong(String str) {
        return prefHelper_.appSharedPrefs_.getLong(str, 0L);
    }

    public String getString(String str) {
        return prefHelper_.appSharedPrefs_.getString(str, "bnc_no_value");
    }

    public boolean getBool(String str) {
        return prefHelper_.appSharedPrefs_.getBoolean(str, false);
    }

    public void setInteger(String str, int i) {
        prefHelper_.prefsEditor_.putInt(str, i);
        prefHelper_.prefsEditor_.apply();
    }

    public void setLong(String str, long j) {
        prefHelper_.prefsEditor_.putLong(str, j);
        prefHelper_.prefsEditor_.apply();
    }

    public void setString(String str, String str2) {
        prefHelper_.prefsEditor_.putString(str, str2);
        prefHelper_.prefsEditor_.apply();
    }

    public void setBool(String str, Boolean bool) {
        prefHelper_.prefsEditor_.putBoolean(str, bool.booleanValue());
        prefHelper_.prefsEditor_.apply();
    }

    public void updateBranchViewUsageCount(String str) {
        setInteger("bnc_branch_view_use_" + str, getBranchViewUsageCount(str) + 1);
    }

    public int getBranchViewUsageCount(String str) {
        return getInteger("bnc_branch_view_use_" + str, 0);
    }

    public JSONObject getBranchAnalyticsData() {
        JSONObject jSONObject = savedAnalyticsData_;
        if (jSONObject != null) {
            return jSONObject;
        }
        String string = getString("bnc_branch_analytical_data");
        JSONObject jSONObject2 = new JSONObject();
        if (!TextUtils.isEmpty(string) && !string.equals("bnc_no_value")) {
            try {
                return new JSONObject(string);
            } catch (JSONException unused) {
            }
        }
        return jSONObject2;
    }

    public void clearBranchAnalyticsData() {
        savedAnalyticsData_ = null;
        setString("bnc_branch_analytical_data", "");
    }

    public void saveBranchAnalyticsData(JSONObject jSONObject) {
        JSONArray jSONArray;
        String sessionID = getSessionID();
        if (sessionID.equals("bnc_no_value")) {
            return;
        }
        if (savedAnalyticsData_ == null) {
            savedAnalyticsData_ = getBranchAnalyticsData();
        }
        try {
            if (savedAnalyticsData_.has(sessionID)) {
                jSONArray = savedAnalyticsData_.getJSONArray(sessionID);
            } else {
                JSONArray jSONArray2 = new JSONArray();
                savedAnalyticsData_.put(sessionID, jSONArray2);
                jSONArray = jSONArray2;
            }
            jSONArray.put(jSONObject);
            setString("bnc_branch_analytical_data", savedAnalyticsData_.toString());
        } catch (JSONException unused) {
        }
    }

    public void saveLastStrongMatchTime(long j) {
        setLong("bnc_branch_strong_match_time", j);
    }

    public long getLastStrongMatchTime() {
        return getLong("bnc_branch_strong_match_time");
    }

    private void clearPrefOnBranchKeyChange() {
        String linkClickID = getLinkClickID();
        String linkClickIdentifier = getLinkClickIdentifier();
        String appLink = getAppLink();
        String pushIdentifier = getPushIdentifier();
        this.prefsEditor_.clear();
        setLinkClickID(linkClickID);
        setLinkClickIdentifier(linkClickIdentifier);
        setAppLink(appLink);
        setPushIdentifier(pushIdentifier);
        prefHelper_.prefsEditor_.apply();
    }

    public void setRequestMetadata(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            return;
        }
        if (this.requestMetadata.has(str) && str2 == null) {
            this.requestMetadata.remove(str);
        }
        try {
            this.requestMetadata.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public JSONObject getRequestMetadata() {
        return this.requestMetadata;
    }

    void addInstallMetadata(String str, String str2) {
        if (str == null) {
            return;
        }
        try {
            this.installMetadata.putOpt(str, str2);
        } catch (JSONException unused) {
        }
    }

    String getInstallMetaData(String str) {
        if (str == null) {
            return null;
        }
        try {
            return this.installMetadata.get(str).toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public JSONObject getInstallMetadata() {
        return this.installMetadata;
    }

    String getSecondaryRequestMetaData(String str) {
        if (str == null) {
            return null;
        }
        try {
            return this.secondaryRequestMetadata.get(str).toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    boolean shouldAddModules() {
        try {
            return this.secondaryRequestMetadata.length() != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void Debug(String str) {
        if ((BranchUtil.isDebugEnabled() || enableLogging_) && !TextUtils.isEmpty(str)) {
            Log.i("BranchSDK", str);
        }
    }

    public static void LogException(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.e("BranchSDK", str, th);
    }

    public static void LogAlways(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.i("BranchSDK", str);
    }
}
