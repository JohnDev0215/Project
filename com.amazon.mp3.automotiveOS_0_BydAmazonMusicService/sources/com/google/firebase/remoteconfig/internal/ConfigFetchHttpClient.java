package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.core.content.pm.PackageInfoCompat;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.util.DateUtils;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ConfigFetchHttpClient {
    private static final Pattern GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private final String apiKey;
    private final String appId;
    private final long connectTimeoutInSeconds;
    private final Context context;
    private final String namespace;
    private final String projectNumber;
    private final long readTimeoutInSeconds;

    public ConfigFetchHttpClient(Context context, String str, String str2, String str3, long j, long j2) {
        this.context = context;
        this.appId = str;
        this.apiKey = str2;
        this.projectNumber = extractProjectNumberFromAppId(str);
        this.namespace = str3;
        this.connectTimeoutInSeconds = j;
        this.readTimeoutInSeconds = j2;
    }

    private static String extractProjectNumberFromAppId(String str) {
        Matcher matcher = GMP_APP_ID_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    HttpURLConnection createHttpURLConnection() throws FirebaseRemoteConfigException {
        try {
            return (HttpURLConnection) new URL(getFetchUrl(this.projectNumber, this.namespace)).openConnection();
        } catch (IOException e) {
            throw new FirebaseRemoteConfigException(e.getMessage());
        }
    }

    @Keep
    ConfigFetchHandler.FetchResponse fetch(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map, String str3, Map<String, String> map2, Long l, Date date) throws FirebaseRemoteConfigException {
        setUpUrlConnection(httpURLConnection, str3, str2, map2);
        try {
            try {
                setFetchRequestBody(httpURLConnection, createFetchRequestBody(str, str2, map, l).toString().getBytes("utf-8"));
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new FirebaseRemoteConfigServerException(responseCode, httpURLConnection.getResponseMessage());
                }
                String headerField = httpURLConnection.getHeaderField("ETag");
                JSONObject fetchResponseBody = getFetchResponseBody(httpURLConnection);
                try {
                    httpURLConnection.getInputStream().close();
                } catch (IOException unused) {
                }
                if (!backendHasUpdates(fetchResponseBody)) {
                    return ConfigFetchHandler.FetchResponse.forBackendHasNoUpdates(date);
                }
                return ConfigFetchHandler.FetchResponse.forBackendUpdatesFetched(extractConfigs(fetchResponseBody, date), headerField);
            } catch (IOException | JSONException e) {
                throw new FirebaseRemoteConfigClientException("The client had an error while calling the backend!", e);
            }
        } finally {
            httpURLConnection.disconnect();
            try {
                httpURLConnection.getInputStream().close();
            } catch (IOException unused2) {
            }
        }
    }

    private void setUpUrlConnection(HttpURLConnection httpURLConnection, String str, String str2, Map<String, String> map) {
        httpURLConnection.setDoOutput(true);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        httpURLConnection.setConnectTimeout((int) timeUnit.toMillis(this.connectTimeoutInSeconds));
        httpURLConnection.setReadTimeout((int) timeUnit.toMillis(this.readTimeoutInSeconds));
        httpURLConnection.setRequestProperty("If-None-Match", str);
        setCommonRequestHeaders(httpURLConnection, str2);
        setCustomRequestHeaders(httpURLConnection, map);
    }

    private String getFetchUrl(String str, String str2) {
        return String.format("https://firebaseremoteconfig.googleapis.com/v1/projects/%s/namespaces/%s:fetch", str, str2);
    }

    private void setCommonRequestHeaders(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty("X-Goog-Api-Key", this.apiKey);
        httpURLConnection.setRequestProperty("X-Android-Package", this.context.getPackageName());
        httpURLConnection.setRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
        httpURLConnection.setRequestProperty("X-Google-GFE-Can-Retry", "yes");
        httpURLConnection.setRequestProperty("X-Goog-Firebase-Installations-Auth", str);
        httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/json");
        httpURLConnection.setRequestProperty(HttpHeader.ACCEPT, "application/json");
    }

    private void setCustomRequestHeaders(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, context.getPackageName());
            if (packageCertificateHashBytes == null) {
                Log.e("FirebaseRemoteConfig", "Could not get fingerprint hash for package: " + this.context.getPackageName());
                return null;
            }
            return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("FirebaseRemoteConfig", "No such package: " + this.context.getPackageName(), e);
            return null;
        }
    }

    private JSONObject createFetchRequestBody(String str, String str2, Map<String, String> map, Long l) throws FirebaseRemoteConfigClientException {
        HashMap map2 = new HashMap();
        if (str == null) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: Firebase installation id is null.");
        }
        map2.put("appInstanceId", str);
        map2.put("appInstanceIdToken", str2);
        map2.put("appId", this.appId);
        Locale locale = this.context.getResources().getConfiguration().locale;
        map2.put("countryCode", locale.getCountry());
        int i = Build.VERSION.SDK_INT;
        map2.put("languageCode", locale.toLanguageTag());
        map2.put("platformVersion", Integer.toString(i));
        map2.put("timeZone", TimeZone.getDefault().getID());
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            if (packageInfo != null) {
                map2.put(Splash.PARAMS_APP_VERSION, packageInfo.versionName);
                map2.put("appBuild", Long.toString(PackageInfoCompat.getLongVersionCode(packageInfo)));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        map2.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.context.getPackageName());
        map2.put("sdkVersion", "21.2.1");
        map2.put("analyticsUserProperties", new JSONObject((Map<?, ?>) map));
        if (l != null) {
            map2.put("firstOpenTime", convertToISOString(l.longValue()));
        }
        return new JSONObject((Map<?, ?>) map2);
    }

    private String convertToISOString(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(Long.valueOf(j));
    }

    private void setFetchRequestBody(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setFixedLengthStreamingMode(bArr.length);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    private JSONObject getFetchResponseBody(URLConnection uRLConnection) throws JSONException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = bufferedReader.read();
            if (i != -1) {
                sb.append((char) i);
            } else {
                return new JSONObject(sb.toString());
            }
        }
    }

    private boolean backendHasUpdates(JSONObject jSONObject) {
        try {
            return !jSONObject.get("state").equals("NO_CHANGE");
        } catch (JSONException unused) {
            return true;
        }
    }

    private static ConfigContainer extractConfigs(JSONObject jSONObject, Date date) throws FirebaseRemoteConfigClientException {
        JSONObject jSONObject2;
        JSONArray jSONArray;
        try {
            ConfigContainer.Builder builderWithFetchTime = ConfigContainer.newBuilder().withFetchTime(date);
            JSONObject jSONObject3 = null;
            try {
                jSONObject2 = jSONObject.getJSONObject("entries");
            } catch (JSONException unused) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null) {
                builderWithFetchTime = builderWithFetchTime.replaceConfigsWith(jSONObject2);
            }
            try {
                jSONArray = jSONObject.getJSONArray("experimentDescriptions");
            } catch (JSONException unused2) {
                jSONArray = null;
            }
            if (jSONArray != null) {
                builderWithFetchTime = builderWithFetchTime.withAbtExperiments(jSONArray);
            }
            try {
                jSONObject3 = jSONObject.getJSONObject("personalizationMetadata");
            } catch (JSONException unused3) {
            }
            if (jSONObject3 != null) {
                builderWithFetchTime = builderWithFetchTime.withPersonalizationMetadata(jSONObject3);
            }
            return builderWithFetchTime.build();
        } catch (JSONException e) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: fetch response could not be parsed.", e);
        }
    }
}
