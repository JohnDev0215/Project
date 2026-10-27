package com.micphone.utils;

import android.util.Log;
import com.android.volley.BuildConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class HttpUtils {
    private static final String TAG = "HttpUtils";

    public interface HttpListener {
        void onResult(String str);
    }

    public static void httpGetAsyncRequest(final String str, final HttpListener httpListener) {
        Log.e(TAG, "url=" + str);
        final String[] strArr = {BuildConfig.FLAVOR};
        new Thread(new Runnable() { // from class: com.micphone.utils.HttpUtils.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Log.d(HttpUtils.TAG, "http start!");
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.micphone.utils.HttpUtils.1.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String str2, SSLSession sSLSession) {
                            return true;
                        }
                    };
                    TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.micphone.utils.HttpUtils.1.2
                        @Override // javax.net.ssl.X509TrustManager
                        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }};
                    SSLContext sSLContext = SSLContext.getInstance("SSL");
                    sSLContext.init(null, trustManagerArr, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setDoOutput(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(true);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setConnectTimeout(1000);
                    httpURLConnection.setReadTimeout(1000);
                    Log.d(HttpUtils.TAG, "start connect");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            StringBuilder sb = new StringBuilder();
                            String[] strArr2 = strArr;
                            sb.append(strArr2[0]);
                            sb.append(line);
                            sb.append("\n");
                            strArr2[0] = sb.toString();
                        }
                        bufferedReader.close();
                    }
                    httpURLConnection.disconnect();
                    Log.e(HttpUtils.TAG, "msg = " + strArr[0]);
                } catch (Exception e) {
                    Log.e(HttpUtils.TAG, "Exception=" + e.getMessage());
                }
                HttpListener httpListener2 = httpListener;
                if (httpListener2 != null) {
                    httpListener2.onResult(strArr[0]);
                }
            }
        }).start();
    }
}
