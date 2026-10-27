package com.byd.vrdownload.net;

import android.text.TextUtils;
import android.util.Log;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrdownload.App;
import com.byd.vrdownload.bean.ArbitrationProfile;
import com.byd.vrdownload.bean.GetArbitrationProfile;
import com.byd.vrdownload.bean.GetDownloadLink;
import com.byd.vrdownload.bean.HttpResult;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.LanguageOTA;
import com.byd.vrdownload.model.Result;
import com.byd.vrdownload.utils.LogUtils;
import com.byd.vrdownload.utils.ParamUtils;
import com.google.gson.reflect.TypeToken;
import java.io.InvalidObjectException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NetRequest {
    private static final String TAG = "NEU_NetRequest";
    static String brUrl = "https://web-aivoiceota-br.byd.auto/res/";
    static String euUrl = "https://web-aivoiceota-eu.byd.auto/res/";
    static String globalUrl = "https://aivoiceota-global.byd.auto/res/";
    static String sgUrl = "https://web-aivoiceota-sg.byd.auto/res/";
    static String testUrl = "http://web-aivoiceota-eu-test.byd.auto/res/";
    static List<Integer> sgList = Arrays.asList(65, 852, 853, 977, 61, 66, 81, 94, 92, 91, 60, 62, 95, 856, 855, 63, 689, 687, 64, 84, 692, 976, 82, 975, 886, 850, 673);
    static List<Integer> euList = Arrays.asList(966, 971, 965, 968, 20, 233, 974, 212, 973, 238, 216, 961, 962, 27, 262, 263, 260, 230, 261, 244, 267, 47, 45, 31, 44, 351, 32, 46, 41, 43, 39, 34, 358, 353, 30, 49, 33, 359, 357, 385, 420, 372, 36, 371, 370, 352, 356, 48, 40, 421, 386, 354, 423, 90, 380, 972, 382, 381, 387, 389, 355, 374, 994, 375, 373, 997, 996, 992, 998, 377, 7, 379);
    static List<Integer> brList = Arrays.asList(56, 598, 57, 1890, 506, 51, 1, 593, 595, 55, 591, 1242, 507, 599, 52, 1, 502, 1876);
    static String countryCode = CountryCodeUtil.getInstance().getCountryCode(App.CONTEXT);

    public static String getDefaultUrl() {
        if (isTest()) {
            Log.i(TAG, "getDefaultUrl: test url");
            return testUrl;
        }
        Log.i(TAG, "getDefaultUrl: global url");
        return globalUrl;
    }

    public static String getCertainUrl() {
        Log.i(TAG, "setUrl: release");
        String str = globalUrl;
        if (isTest()) {
            str = testUrl;
        }
        String countryCode2 = getCountryCode();
        if (countryCode2.isEmpty()) {
            Log.i(TAG, "setUrl: br url,country code is empty");
            str = brUrl;
        }
        if (euList.contains(Integer.valueOf(Integer.parseInt(countryCode2.trim())))) {
            Log.i(TAG, "setUrl: eu url");
            str = euUrl;
        }
        if (sgList.contains(Integer.valueOf(Integer.parseInt(countryCode2.trim())))) {
            Log.i(TAG, "setUrl: sg url");
            str = sgUrl;
        }
        if (!brList.contains(Integer.valueOf(Integer.parseInt(countryCode2.trim())))) {
            return str;
        }
        Log.i(TAG, "setUrl: br url");
        return brUrl;
    }

    public static Result<List<LanguageOTA>> getLanguageOTA(GetLanguagesUpdate getLanguagesUpdate) {
        Log.i(TAG, "getLanguageOTA: BUILD_TYPE=release");
        if (Objects.equals("release", "dev")) {
            getLanguagesUpdate.setxPlatform("release");
        } else if (Objects.equals("release", "prod")) {
            getLanguagesUpdate.setxPlatform("pre-release");
        }
        if (Objects.equals("release", "release")) {
            getLanguagesUpdate.setxPlatform("release");
        }
        getLanguagesUpdate.setPkgVersion(new ArrayList(new HashSet(getLanguagesUpdate.getPkgVersion())));
        getLanguagesUpdate.setCountryCode(getCountryCode());
        Type type = new TypeToken<HttpResult<List<LanguageOTA>>>() { // from class: com.byd.vrdownload.net.NetRequest.1
        }.getType();
        Result<List<LanguageOTA>> resultSync = HttpUtils.builder().addParam(ParamUtils.getLanguagesUpdate(getLanguagesUpdate)).url(getDefaultUrl() + "api/v2/resources").post(true).addHeader("Content-Type", "application/json; charset=utf-8").sync(type);
        if (isTest() || resultSync.isSuccess()) {
            return resultSync;
        }
        if (!(resultSync.getExceptionMessage() instanceof UnknownHostException) && !(resultSync.getExceptionMessage() instanceof SocketTimeoutException) && !(resultSync.getExceptionMessage() instanceof InvalidObjectException) && !(resultSync.getExceptionMessage() instanceof ConnectException)) {
            return resultSync;
        }
        Log.e(TAG, "getLanguageOTA: timeout and request with certain server");
        return HttpUtils.builder().addParam(ParamUtils.getLanguagesUpdate(getLanguagesUpdate)).url(getCertainUrl() + "api/v2/resources").post(true).addHeader("Content-Type", "application/json; charset=utf-8").sync(type);
    }

    public static Result<String> getDownloadLink(String str) {
        Type type = new TypeToken<HttpResult<String>>() { // from class: com.byd.vrdownload.net.NetRequest.2
        }.getType();
        GetDownloadLink getDownloadLink = new GetDownloadLink();
        getDownloadLink.setPkgKey(str);
        getDownloadLink.setCountryCode(getCountryCode());
        getDownloadLink.setReqId(ParamUtils.getReqId());
        Result<String> resultSync = HttpUtils.builder().addParam(ParamUtils.getDownloadLink(getDownloadLink)).url(getDefaultUrl() + "api/v1/getPkgSignUrl").post(true).addHeader("Content-Type", "application/json; charset=utf-8").sync(type);
        if (isTest() || resultSync.isSuccess()) {
            return resultSync;
        }
        if (!(resultSync.getExceptionMessage() instanceof UnknownHostException) && !(resultSync.getExceptionMessage() instanceof SocketTimeoutException) && !(resultSync.getExceptionMessage() instanceof InvalidObjectException) && !(resultSync.getExceptionMessage() instanceof ConnectException)) {
            return resultSync;
        }
        Log.e(TAG, "getDownloadLink: timeout and request with certain server");
        return HttpUtils.builder().addParam(ParamUtils.getDownloadLink(getDownloadLink)).url(getCertainUrl() + "api/v1/getPkgSignUrl").post(true).addHeader("Content-Type", "application/json; charset=utf-8").sync(type);
    }

    public static Result<ArbitrationProfile> getArbitrationProfile(String str) {
        Type type = new TypeToken<HttpResult<ArbitrationProfile>>() { // from class: com.byd.vrdownload.net.NetRequest.3
        }.getType();
        GetArbitrationProfile getArbitrationProfile = new GetArbitrationProfile();
        getArbitrationProfile.setVersion(str);
        getArbitrationProfile.setCountryCode(getCountryCode());
        getArbitrationProfile.setReqId(ParamUtils.getReqId());
        Log.e(TAG, "getDefaultUrl()：" + getDefaultUrl());
        return HttpUtils.builder().addParam(ParamUtils.getArbitrationProfile(getArbitrationProfile)).url(getDefaultUrl() + "api/v1/getArbitrationProfile").post(true).addHeader("Content-Type", "application/json; charset=utf-8").sync(type);
    }

    public static String get(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return null;
        }
    }

    private static String getCountryCode() {
        if (TextUtils.isEmpty(countryCode) || "-1".equals(countryCode)) {
            countryCode = CountryCodeUtil.getInstance().getCountryCode(App.CONTEXT);
        }
        LogUtils.e(TAG, "download countrycode:" + countryCode);
        return countryCode;
    }

    public static boolean isTest() {
        return Objects.equals("release", "dev");
    }
}
