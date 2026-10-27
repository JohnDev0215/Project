package com.ts.appservice.settings.impl.appconfig;

import android.content.Context;
import android.os.SystemProperties;
import com.android.common.speech.LoggingEvents;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CountryCodeConfigManager {
    private static final String COUNTRYCODE_ERROR = "COUNTRYCODE_ERROR";
    private static final String COUNTRY_CODE = "countrycode";
    private static final String DEFAULT_COUNTRY_NO = "47";
    private static final String DEFAULT_VALUE = "0";
    private static final String JSON_COUNTRYCODE = "/system/etc/data/countrycode.json";
    private static final String NUMBER = "number";
    private static final String PERSIST_VENDOR_BYD_CFG_HUC_CONFIG_AREA = "persist.vendor.byd.cfg.huc.config.area";
    private static final String TAG = CountryCodeConfigManager.class.getSimpleName();
    private Context mContext;
    private String mCountryCodeJson;

    public CountryCodeConfigManager(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public String getCountryCode() {
        String str = this.mCountryCodeJson;
        if (str == null || str.isEmpty()) {
            this.mCountryCodeJson = getCountryCodeJson();
        }
        String currentCountryCode = SystemProperties.get(PERSIST_VENDOR_BYD_CFG_HUC_CONFIG_AREA);
        LogUtil.error(TAG, "get config.area :" + currentCountryCode);
        if (currentCountryCode == null || currentCountryCode.equals(LoggingEvents.EXTRA_CALLING_APP_NAME)) {
            LogUtil.error(TAG, "get config.area is null return DEFAULT_COUNTRY_NO");
            return DEFAULT_COUNTRY_NO;
        }
        String str2 = this.mCountryCodeJson;
        if (str2 != null && !str2.isEmpty()) {
            try {
                JSONArray array = new JSONArray(this.mCountryCodeJson);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = (JSONObject) array.get(i);
                    String countryCode = object.optString(COUNTRY_CODE);
                    String number = object.optString(NUMBER);
                    if (number.equalsIgnoreCase(currentCountryCode)) {
                        LogUtil.info(TAG, "getCountryCode: countryCode is " + countryCode);
                        return countryCode;
                    }
                }
            } catch (JSONException ex) {
                LogUtil.error(TAG, "getCountryCode error", ex);
            }
        }
        LogUtil.error(TAG, "countrycode.json does not have this countrycode, return COUNTRYCODE_ERROR");
        return COUNTRYCODE_ERROR;
    }

    public String getCountryCode(String configCode) {
        String str = this.mCountryCodeJson;
        if (str == null || str.isEmpty()) {
            this.mCountryCodeJson = getCountryCodeJson();
        }
        LogUtil.info(TAG, "get config.area configCode :" + configCode);
        if (DEFAULT_VALUE.equals(configCode)) {
            return DEFAULT_COUNTRY_NO;
        }
        String str2 = this.mCountryCodeJson;
        if (str2 != null && !str2.isEmpty()) {
            try {
                JSONArray array = new JSONArray(this.mCountryCodeJson);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = (JSONObject) array.get(i);
                    String countryCode = object.optString(COUNTRY_CODE);
                    String number = object.optString(NUMBER);
                    if (number.equalsIgnoreCase(configCode)) {
                        LogUtil.info(TAG, "getCountryCode: countryCode is " + countryCode);
                        return countryCode;
                    }
                }
            } catch (JSONException ex) {
                LogUtil.error(TAG, "getCountryCode error", ex);
            }
        }
        LogUtil.error(TAG, "failed to get country code, return default value");
        return DEFAULT_COUNTRY_NO;
    }

    private String getCountryCodeJson() {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader;
        StringBuilder countryCodeJson = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(JSON_COUNTRYCODE);
            inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
        } catch (Exception ex) {
            LogUtil.error(TAG, "getCountryCodeJson error", ex);
        }
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            countryCodeJson.append(line);
            return countryCodeJson.toString();
        }
        reader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return countryCodeJson.toString();
    }
}
