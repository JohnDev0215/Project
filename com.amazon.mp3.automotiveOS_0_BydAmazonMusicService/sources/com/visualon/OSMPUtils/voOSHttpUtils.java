package com.visualon.OSMPUtils;

import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes7.dex */
public class voOSHttpUtils {
    public static int postRequest(String str, String str2, String str3, int i) throws Exception {
        if (str == null) {
            return 0;
        }
        byte[] bytes = new StringBuilder(str2).toString().getBytes();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
        httpsURLConnection.setConnectTimeout(i);
        httpsURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, str3);
        httpsURLConnection.setRequestProperty(HttpHeader.CONTENT_LENGTH, String.valueOf(bytes.length));
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        if (httpsURLConnection.getResponseCode() == 200) {
            return httpsURLConnection.getResponseCode();
        }
        return httpsURLConnection.getResponseCode();
    }
}
