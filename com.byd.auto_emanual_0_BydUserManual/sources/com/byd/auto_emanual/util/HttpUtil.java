package com.byd.auto_emanual.util;

import android.graphics.Point;
import android.text.TextUtils;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.NetStatusCallback;
import com.byd.auto_emanual.Parameters;
import com.byd.auto_emanual.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HttpUtil {
    public static final String BASE_URL;
    private static final String COUNTRY_CODE;
    public static final String KEY;
    public static final String REQUEST_METHOD = "POST";
    private static final String TAG = "HttpUtil";
    private static final int TIME_TIMEOUT_CONNECTION = 10000;
    public static final String TOKEN;
    public static final String URL_CHECK_VERSION = "http://productupgrade.byd.com:8055/ota-api/thirdParty/queryVersion";
    private static final String URL_UPDATE_BASE = "http://productupgrade.byd.com:8055/ota-api";
    private static final String colon = ":";
    private static final String doubleQuotation = "\"";

    public static class Response {
        public static final String CODE = "code";
        public static final String DATA = "data";
        public static final String MESSAGE = "msg";
        public static final String SUCCESS = "1";
    }

    static {
        String simpleName = HttpUtil.class.getSimpleName();
        String countryCode = AutoInfoUtil.getCountryCode();
        COUNTRY_CODE = countryCode;
        if (AutoInfoUtil.isDebugServer()) {
            if (ServerUtil.getCountryCodeList(R.array.country_code_of_korea).contains(countryCode)) {
                BASE_URL = ServerUtil.getServerPara(R.string.debug_url_of_korea);
                TOKEN = ServerUtil.getServerPara(R.string.debug_token_of_korea);
                KEY = ServerUtil.getServerPara(R.string.debug_after_encrypt_of_korea);
            } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_uzbekistan).contains(countryCode)) {
                BASE_URL = ServerUtil.getServerPara(R.string.debug_url_of_uzbekistan);
                TOKEN = ServerUtil.getServerPara(R.string.debug_token_of_uzbekistan);
                KEY = ServerUtil.getServerPara(R.string.debug_after_encrypt_of_uzbekistan);
            } else {
                BASE_URL = ServerUtil.getServerPara(R.string.debug_url_of_paris);
                TOKEN = ServerUtil.getServerPara(R.string.token_of_paris);
                KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_paris);
            }
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_singapore).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_singapore);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_singapore);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_singapore);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_australia).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_australia);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_australia);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_australia);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_paris).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_paris);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_paris);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_paris);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_brazil).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_brazil);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_brazil);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_brazil);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_latin_america).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_latin_america);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_latin_america);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_latin_america);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_middle_east_africa).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_middle_east_africa);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_middle_east_africa);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_middle_east_africa);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_japan).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_japan);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_japan);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_japan);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_uzbekistan).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_uzbekistan);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_uzbekistan);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_uzbekistan);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_no_server).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_no_server);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_no_server);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_no_server);
        } else if (ServerUtil.getCountryCodeList(R.array.country_code_of_korea).contains(countryCode)) {
            BASE_URL = ServerUtil.getServerPara(R.string.url_of_korea);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_korea);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_korea);
        } else {
            BASE_URL = ServerUtil.getServerPara(R.string.debug_url_of_paris);
            TOKEN = ServerUtil.getServerPara(R.string.token_of_paris);
            KEY = ServerUtil.getServerPara(R.string.after_encrypt_of_paris);
        }
        MLog.d(simpleName, "BASE_URL = " + BASE_URL);
    }

    public static String request(String str, Map<String, String> map, String str2) throws Throwable {
        String str3;
        String str4;
        HttpURLConnection httpURLConnection = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        strInputStream2String = null;
        String strInputStream2String = null;
        httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setConnectTimeout(10000);
                    httpURLConnection2.setRequestMethod(REQUEST_METHOD);
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    httpURLConnection2.connect();
                    if (str2 != null) {
                        MLog.v(TAG, "body = " + str2);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection2.getOutputStream(), StandardCharsets.UTF_8);
                        outputStreamWriter.write(str2);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                    }
                    MLog.v(TAG, "response code:" + httpURLConnection2.getResponseCode());
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    try {
                        try {
                            strInputStream2String = inputStream2String(inputStream);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e = e;
                                    str4 = TAG;
                                    MLog.e(str4, "InputStream close exception.", e);
                                }
                            }
                        } finally {
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        MLog.e(TAG, "InputStream to String exception.", e2);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                e = e3;
                                str4 = TAG;
                                MLog.e(str4, "InputStream close exception.", e);
                            }
                        }
                    }
                    if (httpURLConnection2 == null) {
                        return strInputStream2String;
                    }
                    httpURLConnection2.disconnect();
                    return strInputStream2String;
                } catch (Exception e4) {
                    e = e4;
                    str3 = strInputStream2String;
                    httpURLConnection = httpURLConnection2;
                    NetStatusCallback.onConnectException(str, e);
                    MLog.e(TAG, "post exception:", e);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return str3;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
            str3 = null;
        }
    }

    public static Map<String, String> getHeaders() {
        HashMap map = new HashMap();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");
        map.put("Charset", "UTF-8");
        map.put("token", TOKEN);
        return map;
    }

    public static String createBody(Map<String, String> ext) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        StringBuilder sbCreateBodyItem = createBodyItem(sb, Parameters.NAME_PLATFORM, AutoInfoUtil.getPlatformType());
        sbCreateBodyItem.append(",");
        String autoType = AutoInfoUtil.getAutoType(MApp.context);
        StringBuilder sbCreateBodyItem2 = createBodyItem(sbCreateBodyItem, Parameters.NAME_TYPE, autoType);
        sbCreateBodyItem2.append(",");
        StringBuilder sbCreateBodyItem3 = createBodyItem(sbCreateBodyItem2, Parameters.NAME_TYPE_SUB, AutoInfoUtil.getAutoTypeSub(MApp.context, autoType));
        sbCreateBodyItem3.append(",");
        Point screenSize = DeviceUtil.getScreenSize(MApp.context);
        StringBuilder sbCreateBodyItem4 = createBodyItem(sbCreateBodyItem3, Parameters.NAME_HORIZONTAL_DIMEN, String.valueOf(screenSize.x));
        sbCreateBodyItem4.append(",");
        StringBuilder sbCreateBodyItem5 = createBodyItem(sbCreateBodyItem4, Parameters.NAME_VERTICAL_DIMEN, String.valueOf(screenSize.y));
        sbCreateBodyItem5.append(",");
        StringBuilder sbCreateBodyItem6 = createBodyItem(sbCreateBodyItem5, Parameters.NAME_NET_TYPE, AutoInfoUtil.getNetType());
        sbCreateBodyItem6.append(",");
        StringBuilder sbCreateBodyItem7 = createBodyItem(sbCreateBodyItem6, "language", Utils.getCurrentLanguage());
        sbCreateBodyItem7.append(",");
        StringBuilder sbCreateBodyItem8 = createBodyItem(sbCreateBodyItem7, Parameters.NAME_COUNTRY_CODE, COUNTRY_CODE);
        sbCreateBodyItem8.append(",");
        StringBuilder sbCreateBodyItem9 = createBodyItem(sbCreateBodyItem8, Parameters.NAME_APP_VERSION, String.valueOf(Utils.getVersionCode(MApp.context)));
        if (ext != null) {
            for (Map.Entry<String, String> entry : ext.entrySet()) {
                sbCreateBodyItem9.append(",");
                sbCreateBodyItem9 = createBodyItem(sbCreateBodyItem9, entry.getKey(), entry.getValue());
            }
        }
        sbCreateBodyItem9.append("}");
        String string = sbCreateBodyItem9.toString();
        MLog.i(TAG, "postBody = " + string);
        return string;
    }

    private static StringBuilder createBodyItem(StringBuilder sb, String name, String value) {
        return sb.append(doubleQuotation).append(name).append(doubleQuotation).append(colon).append(doubleQuotation).append(value).append(doubleQuotation);
    }

    private static String inputStream2String(InputStream in) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                try {
                    int i = in.read(bArr);
                    if (i != -1) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    } else {
                        byteArrayOutputStream.flush();
                        return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
                    }
                } catch (IOException e) {
                    String str = TAG;
                    MLog.e(str, "inputStream2String error", e);
                    CloseUtil.close(byteArrayOutputStream, str);
                    return "";
                }
            } finally {
                CloseUtil.close(byteArrayOutputStream, TAG);
            }
            CloseUtil.close(byteArrayOutputStream, TAG);
        }
    }

    public static String getDomainName() {
        String str = BASE_URL;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String strReplace = str.replace("http://", "").replace("https://", "");
        return strReplace.substring(0, strReplace.indexOf(colon));
    }
}
