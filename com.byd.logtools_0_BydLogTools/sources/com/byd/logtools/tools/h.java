package com.byd.logtools.tools;

import android.content.Context;
import android.util.Log;
import com.byd.logtools.application.BydLogApplication;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: compiled from: UploadLog.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3249b = 60;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3250c = 60;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3251d = 60;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3252e = "000000";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f3253f = "100001";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f3254g = "100002";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f3255h = "100003";

    public h(Context context) {
        this.f3248a = context;
    }

    public String a() {
        String strC = com.byd.logtools.auth.utils.c.b(this.f3248a).c();
        if (strC == null || !strC.startsWith("https")) {
            return null;
        }
        String strReplace = strC.replace("https", "http");
        Log.w("UploadLog", "getUrl urlStr = " + strC + " tempStr " + strReplace);
        StringBuilder sb = new StringBuilder();
        sb.append(strReplace);
        sb.append("apr/upload.do");
        String string = sb.toString();
        Log.w("UploadLog", "getUrl is = " + string);
        return string;
    }

    public boolean b(String str, String str2) {
        if (str == null || str2 == null) {
            Log.d("UploadLog", "sendAprInfoToServer crashInfo is null ");
            return false;
        }
        try {
            File file = new File(str2);
            ArrayList arrayList = new ArrayList();
            if (file.exists()) {
                arrayList.add(file);
            }
            return false;
        } catch (Exception e2) {
            Log.d("UploadLog", "upload file exception : " + e2);
            return false;
        }
    }

    public boolean c(String str, List<File> list) {
        String strA = a();
        if (strA == null) {
            Log.d("UploadLog", "sendAprInfoToServer url is null ");
            return false;
        }
        if (str == null) {
            Log.d("UploadLog", "sendAprInfoToServer stringPart is null ");
            return false;
        }
        if (list == null || list.isEmpty()) {
            Log.d("UploadLog", "sendAprInfoToServer filelist is null ");
            return false;
        }
        Log.d("UploadLog", "logFilePath is " + (list.get(0).getName().contains("All") ? "/storage/emulated/0/upLogs/" : list.get(0).getName().contains("Recovery") ? "/data/data/com.byd.logtools/recoveryLog/" : (list.get(0).getName().contains("REQUEST") || list.get(0).getName().contains("osd")) ? "/data/data/com.byd.logtools/requestZipLog/" : "/data/data/com.byd.logtools/crashLog/"));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient okHttpClientBuild = builder.connectTimeout(60L, timeUnit).readTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).connectionPool(new ConnectionPool(32, 5L, TimeUnit.MINUTES)).build();
        MultipartBody.Builder builder2 = new MultipartBody.Builder();
        builder2.addFormDataPart("iccid", str);
        for (int i = 0; i < list.size(); i++) {
            File file = new File(BydLogApplication.d().e() + list.get(i));
            Log.i("UploadLog", ", logFile = " + file.getPath() + ", logFile.length() = " + file.length());
            RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            if (i == 0) {
                builder2.addFormDataPart("files", file.getName(), requestBodyCreate);
            } else {
                builder2.addFormDataPart("files" + (i + 1), file.getName(), requestBodyCreate);
            }
        }
        Request requestBuild = new Request.Builder().url(strA).post(builder2.setType(MultipartBody.FORM).build()).build();
        Log.w("UploadLog", "request | " + requestBuild.toString() + "\n, request.headers() = " + requestBuild.headers().toString());
        try {
            Response responseExecute = okHttpClientBuild.newCall(requestBuild).execute();
            String strString = responseExecute.body().string();
            Log.e("UploadLog", "response.body().string() = " + strString + ", response.isSuccessful() = " + responseExecute.isSuccessful());
            if (responseExecute.isSuccessful()) {
                if ("success".equals(strString)) {
                    return true;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public boolean d(String str, String str2, String str3, ArrayList<String> arrayList) {
        Log.i("UploadLog", "sendMultipart: reqUrl = " + str + "，stringPart = " + str2 + ", logFilePath = " + str3);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        OkHttpClient okHttpClientBuild = builder.connectTimeout(60L, timeUnit).readTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).connectionPool(new ConnectionPool(32, 5L, TimeUnit.MINUTES)).build();
        MultipartBody.Builder builder2 = new MultipartBody.Builder();
        builder2.addFormDataPart("iccid", str2);
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(str3 + arrayList.get(i));
            Log.i("UploadLog", "sendMultipart: logFile " + i + " = " + file.getPath() + ", logFile.length() = " + file.length());
            RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("*/*"), file);
            if (i == 0) {
                builder2.addFormDataPart("files", file.getName(), requestBodyCreate);
            } else {
                builder2.addFormDataPart("files" + (i + 1), file.getName(), requestBodyCreate);
            }
        }
        MultipartBody multipartBodyBuild = builder2.setType(MultipartBody.FORM).build();
        Request requestBuild = new Request.Builder().url(str).post(multipartBodyBuild).build();
        Log.w("UploadLog", "sendMultipart request | " + requestBuild.toString() + "\n, request requestBody = " + multipartBodyBuild.toString());
        try {
            Response responseExecute = okHttpClientBuild.newCall(requestBuild).execute();
            String strString = responseExecute.body().string();
            Log.e("UploadLog", "sendMultipart response.body().string() = " + strString + ", response.isSuccessful() = " + responseExecute.isSuccessful());
            if (responseExecute.isSuccessful()) {
                if ("success".equals(strString)) {
                    return true;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public boolean e(String str, ArrayList<String> arrayList) {
        String strA = a();
        if (strA == null) {
            Log.d("UploadLog", "sendSpecificLogZip url is null ");
            return false;
        }
        if (str == null) {
            Log.d("UploadLog", "sendSpecificLogZip stringPart is null ");
            return false;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            return d(strA, str, BydLogApplication.d().c(), arrayList);
        }
        Log.d("UploadLog", "sendSpecificLogZip filenames is empty");
        return false;
    }
}
