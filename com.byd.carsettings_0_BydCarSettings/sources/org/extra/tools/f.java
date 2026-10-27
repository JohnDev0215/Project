package org.extra.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.bydauto.TsNormalNum;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.libpag.PAG;

/* JADX INFO: loaded from: classes3.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicReference<a> f3996a = new AtomicReference<>(a.Unreported);

    public enum a {
        Unreported,
        Reporting,
        Reported
    }

    public static void b() {
        ThreadPoolExecutor threadPoolExecutor;
        Throwable th;
        AtomicReference<a> atomicReference = f3996a;
        if (atomicReference.get() != a.Unreported) {
            return;
        }
        atomicReference.set(a.Reporting);
        try {
            threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue(2));
        } catch (Throwable th2) {
            threadPoolExecutor = null;
            th = th2;
        }
        try {
            threadPoolExecutor.execute(new e());
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
            } finally {
                threadPoolExecutor.shutdown();
            }
        }
    }

    private static String c() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        Context contextA = ContextProvider.a();
        if (contextA == null) {
            f3996a.set(a.Unreported);
            return;
        }
        String strSDKVersion = PAG.SDKVersion();
        if (TextUtils.isEmpty(strSDKVersion)) {
            f3996a.set(a.Unreported);
            return;
        }
        SharedPreferences sharedPreferences = contextA.getSharedPreferences("pag_reporter", 0);
        String string = "";
        String string2 = sharedPreferences.getString("last_pag_version", "");
        String string3 = sharedPreferences.getString("pag_report_time", "");
        String strC = c();
        if (strSDKVersion.equals(string2) && strC.equals(string3)) {
            f3996a.set(a.Reported);
            return;
        }
        String packageName = contextA.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            f3996a.set(a.Unreported);
            return;
        }
        PackageManager packageManager = contextA.getPackageManager();
        try {
            string = packageManager.getPackageInfo(packageName, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Throwable unused) {
        }
        if (packageManager.checkPermission("android.permission.INTERNET", packageName) != 0) {
            f3996a.set(a.Unreported);
        } else {
            if (!a(strSDKVersion, string, packageName, string2)) {
                f3996a.set(a.Unreported);
                return;
            }
            sharedPreferences.edit().putString("last_pag_version", strSDKVersion).apply();
            sharedPreferences.edit().putString("pag_report_time", strC).apply();
            f3996a.set(a.Reported);
        }
    }

    private static boolean a(String str, String str2, String str3, String str4) {
        boolean z = false;
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://otheve.beacon.qq.com/analytics/v2_upload").openConnection();
            httpsURLConnection.setConnectTimeout(2000);
            httpsURLConnection.setReadTimeout(TsNormalNum.THOUSAND_FIVE);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream(), "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(b(str, str2, str3, str4));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                StringBuilder sb = new StringBuilder();
                InputStreamReader inputStreamReader = new InputStreamReader(httpsURLConnection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
                bufferedReader.close();
                inputStreamReader.close();
                if (new JSONObject(sb.toString()).optInt("result") == 200) {
                    z = true;
                }
            }
            httpsURLConnection.disconnect();
        } catch (Throwable unused) {
        }
        return z;
    }

    private static String b(String str, String str2, String str3, String str4) {
        return "{\"appVersion\":\"\",\"sdkId\":\"\",\"sdkVersion\":\"" + str + "\",\"mainAppKey\":\"0DOU0K0WD05SLYU3\",\"platformId\":\"\",\"common\":{\"A2\":\"pag_sdk_report\"},\"events\":[{\"eventCode\":\"pag_sdk_report\",\"eventTime\":\"" + System.currentTimeMillis() + "\",\"mapValue\":{\"appName\":\"" + str2 + "\",\"appID\":\"" + str3 + "\",\"appPlatform\":\"Android\",\"previousSDKVersion\":\"" + str4 + "\"}}]}";
    }
}
