package com.byd.vrdownload.utils;

import com.byd.vrdownload.BuildConfig;
import com.byd.vrdownload.bean.GetArbitrationProfile;
import com.byd.vrdownload.bean.GetDownloadLink;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.LanguageUpdate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ParamUtils {
    private static final String TAG = "ParamUtils";

    public static Map<String, String> getLanguagesUpdate(GetLanguagesUpdate getLanguagesUpdate) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < getLanguagesUpdate.getPkgVersion().size(); i++) {
            try {
                jSONArray.put(new JSONObject(GsonUtils.toJson(getLanguagesUpdate.getPkgVersion().get(i))));
            } catch (JSONException e) {
                LogUtils.e(TAG, "getLanguagesUpdate: " + e.getMessage());
            }
        }
        getLanguagesUpdate.setReqId(getReqId());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("vin", getLanguagesUpdate.getVin());
        linkedHashMap.put(LanguageUpdate.APK_VERSION, getLanguagesUpdate.getApkVersion());
        linkedHashMap.put("pkgVersion", jSONArray.toString());
        linkedHashMap.put("reqId", getLanguagesUpdate.getReqId());
        linkedHashMap.put("xPlatform", getLanguagesUpdate.getxPlatform());
        linkedHashMap.put("countryCode", getLanguagesUpdate.getCountryCode());
        linkedHashMap.put("vehicleType", getLanguagesUpdate.getVehicleType());
        linkedHashMap.put("vehicePlatform", BuildConfig.FLAVOR);
        getLanguagesUpdate.setSign(getSign(linkedHashMap));
        linkedHashMap.put("sign", getLanguagesUpdate.getSign());
        LogUtils.i(TAG, "getLanguagesUpdate: " + GsonUtils.toJson((Map) linkedHashMap));
        return linkedHashMap;
    }

    public static Map<String, String> getDownloadLink(GetDownloadLink getDownloadLink) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("reqId", getDownloadLink.getReqId());
        linkedHashMap.put("pkgKey", getDownloadLink.getPkgKey());
        linkedHashMap.put("countryCode", getDownloadLink.getCountryCode());
        linkedHashMap.put("sign", getSign(linkedHashMap));
        LogUtils.i(TAG, "getDownloadLink: " + GsonUtils.toJson((Map) linkedHashMap));
        return linkedHashMap;
    }

    public static Map<String, String> getArbitrationProfile(GetArbitrationProfile getArbitrationProfile) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("reqId", getArbitrationProfile.getReqId());
        linkedHashMap.put("version", "");
        linkedHashMap.put("countryCode", getArbitrationProfile.getCountryCode());
        linkedHashMap.put("sign", getSign(linkedHashMap));
        LogUtils.i(TAG, "getAttributeProfile: " + GsonUtils.toJson((Map) linkedHashMap));
        return linkedHashMap;
    }

    public static String getSign(Map<String, String> map) {
        final ArrayList arrayList = new ArrayList();
        map.forEach(new BiConsumer() { // from class: com.byd.vrdownload.utils.ParamUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                arrayList.add(((String) obj) + "=" + ((String) obj2));
            }
        });
        return SignUtils.SHA256(arrayList);
    }

    public static String getReqId() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                stringBuffer.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
            } else if (iNextInt == 1) {
                stringBuffer.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
            } else if (iNextInt == 2) {
                stringBuffer.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return stringBuffer.toString();
    }

    public static String getUrlParams(String str, String str2) {
        Pattern patternCompile = Pattern.compile("(?<=" + str2 + "=).*?(?=&)");
        LogUtils.i(TAG, "getUrlParams: " + str);
        Matcher matcher = patternCompile.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        LogUtils.i(TAG, "getUrlParams: count=" + matcher.groupCount());
        LogUtils.i(TAG, "getUrlParams: " + matcher.group());
        return matcher.group();
    }
}
