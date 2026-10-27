package com.byd.bydcommonlib;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AppUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context) {
        String str;
        String str2 = null;
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open("countrycode.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
            try {
                inputStreamOpen.close();
            } catch (IOException e2) {
                e = e2;
                str2 = str;
                e.printStackTrace();
                str = str2;
            }
        } catch (IOException e3) {
            e = e3;
        }
        if (str == null) {
            Log.d("AppUtils", "content is null ");
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49);
        Log.d("AppUtils", "parseJson codeNum =" + i);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String string = jSONObject.getString("countrycode");
                String string2 = jSONObject.getString("number");
                if (!string2.isEmpty() && string2.equals(String.valueOf(i))) {
                    return string;
                }
            }
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }
}
