package com.byd.common.countrycode;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CountryCodeUtil {
    private static final String TAG = "com.byd.common.countrycode.CountryCodeUtil";
    private static CountryCodeUtil instance;
    private List<CountryData> countryList = null;

    public static CountryCodeUtil getInstance() {
        if (instance == null) {
            instance = new CountryCodeUtil();
        }
        return instance;
    }

    private CountryCodeUtil() {
    }

    public String getCountryCode(Context context) {
        int countryNumber = getCountryNumber();
        if (this.countryList == null) {
            initCountryData(context);
        }
        if (countryNumber == 0) {
            LogUtils.e(TAG, "getCountryCode number ==0");
            return "-1";
        }
        List<CountryData> list = this.countryList;
        if (list == null) {
            LogUtils.e(TAG, "getCountryCode countryList == null");
            return "-1";
        }
        if (countryNumber + 1 > list.size()) {
            LogUtils.e(TAG, "getCountryCode number > countryList.size()");
            return "-1";
        }
        return String.valueOf(this.countryList.get(countryNumber - 1).getCountrycode());
    }

    public void initCountryData(Context context) {
        if (this.countryList == null) {
            this.countryList = new ArrayList();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("countrycode-20250218184553.json")));
            String str = "";
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    str = str + line + "\n";
                } else {
                    bufferedReader.readLine();
                    this.countryList = (List) new Gson().fromJson(str, new TypeToken<List<CountryData>>() { // from class: com.byd.common.countrycode.CountryCodeUtil.1
                    }.getType());
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e(TAG, "e:" + e.getMessage());
            this.countryList = new ArrayList();
        }
    }

    private int getCountryNumber() {
        int i = SystemPropertiesUtils.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        String str = TAG;
        LogUtils.e(str, "getCountryNumber:" + i);
        LogUtils.e(str, "NEU_persist.sys.byd.countrycode:" + SystemPropertiesUtils.getInt("persist.sys.byd.countrycode", -1));
        return i;
    }
}
