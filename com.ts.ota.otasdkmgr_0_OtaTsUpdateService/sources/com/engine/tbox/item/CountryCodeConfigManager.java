package com.engine.tbox.item;

import android.content.Context;
import android.os.SystemProperties;
import com.engine.tbox.core.utils.LogUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
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
        LogUtils.e(TAG, "get config.area :" + currentCountryCode, new Object[0]);
        if (currentCountryCode == null || currentCountryCode.equals("")) {
            LogUtils.e(TAG, "get config.area is null return DEFAULT_COUNTRY_NO", new Object[0]);
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
                        LogUtils.i(TAG, "getCountryCode: countryCode is " + countryCode, new Object[0]);
                        return countryCode;
                    }
                }
            } catch (JSONException ex) {
                LogUtils.e(TAG, "getCountryCode error", ex);
            }
        }
        LogUtils.e(TAG, "countrycode.json does not have this countrycode, return COUNTRYCODE_ERROR", new Object[0]);
        return COUNTRYCODE_ERROR;
    }

    public String getCountryCode(String configCode) {
        String str = this.mCountryCodeJson;
        if (str == null || str.isEmpty()) {
            this.mCountryCodeJson = getCountryCodeJson();
        }
        LogUtils.i(TAG, "get config.area configCode :" + configCode, new Object[0]);
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
                        LogUtils.i(TAG, "getCountryCode: countryCode is " + countryCode, new Object[0]);
                        return countryCode;
                    }
                }
            } catch (JSONException ex) {
                LogUtils.e(TAG, "getCountryCode error", ex);
            }
        }
        LogUtils.e(TAG, "failed to get country code, return default value", new Object[0]);
        return DEFAULT_COUNTRY_NO;
    }

    private String getCountryCodeJson() {
        StringBuilder countryCodeJson = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(JSON_COUNTRYCODE);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                try {
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String line = reader.readLine();
                            if (line == null) {
                                break;
                            }
                            countryCodeJson.append(line);
                        } finally {
                        }
                    }
                    reader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (IOException ex) {
            LogUtils.e(TAG, "getCountryCodeJson error", ex);
        }
        return countryCodeJson.toString();
    }
}
