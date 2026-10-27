package com.amazon.avod.ads.http;

import com.amazon.avod.ads.api.AdNetworkException;
import com.google.common.io.Closeables;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultAdHttpClient extends AdHttpClient {
    public DefaultAdHttpClient(HttpParameters httpParameters, HttpCookieStore httpCookieStore) {
        super(httpParameters, httpCookieStore);
    }

    @Override // com.amazon.avod.ads.http.AdHttpClient
    protected void pingHttpServer(URL url, int i, int i2) throws Throwable {
        HttpURLConnection httpURLConnection;
        AdNetworkException adNetworkException;
        AdNetworkException adNetworkException2 = new AdNetworkException();
        boolean z = false;
        while (i > 0 && !z) {
            try {
                httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
            } catch (IOException e) {
                e = e;
                httpURLConnection = null;
            } catch (Throwable th) {
                th = th;
                httpURLConnection = null;
            }
            try {
                try {
                    httpURLConnection.setConnectTimeout(i2);
                    httpURLConnection.setReadTimeout(i2);
                    String cookie = this.mCookieStore.getCookie(url);
                    if (cookie != null) {
                        httpURLConnection.setRequestProperty("Cookie", cookie);
                    }
                    AdHttpClient.updateParameters(httpURLConnection, getHttpParameters());
                    try {
                        int responseCode = httpURLConnection.getResponseCode();
                        if (responseCode < 200 || responseCode > 299) {
                            adNetworkException2 = new AdNetworkException(String.format(Locale.US, "Error response from server. Error Code(%d) for url: %s", Integer.valueOf(responseCode), url.toString()));
                        } else {
                            z = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e2) {
                        try {
                            adNetworkException2 = new AdNetworkException(String.format("Exception %s caught closing stream for url: %s", e2.getMessage(), url.toString()));
                            z = false;
                        } catch (IOException e3) {
                            e = e3;
                            z = false;
                            AdNetworkException adNetworkException3 = new AdNetworkException(e);
                            try {
                                closeQuietly(null, httpURLConnection);
                                adNetworkException2 = adNetworkException3;
                            } catch (ArrayIndexOutOfBoundsException e4) {
                                adNetworkException = new AdNetworkException(String.format("Exception %s caught closing stream for url: %s", e4.getMessage(), url.toString()));
                                adNetworkException2 = adNetworkException;
                                z = false;
                            }
                        }
                    }
                    setCookies(httpURLConnection);
                    try {
                        closeQuietly(httpURLConnection.getInputStream(), httpURLConnection);
                    } catch (ArrayIndexOutOfBoundsException e5) {
                        adNetworkException = new AdNetworkException(String.format("Exception %s caught closing stream for url: %s", e5.getMessage(), url.toString()));
                        adNetworkException2 = adNetworkException;
                        z = false;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
                i--;
            } catch (Throwable th2) {
                th = th2;
                try {
                    closeQuietly(null, httpURLConnection);
                } catch (ArrayIndexOutOfBoundsException e7) {
                    new AdNetworkException(String.format("Exception %s caught closing stream for url: %s", e7.getMessage(), url.toString()));
                }
                throw th;
            }
        }
        if (!z) {
            throw adNetworkException2;
        }
    }

    @Override // com.amazon.avod.ads.http.AdHttpClient
    protected String processHttpRequest(URL url, int i, int i2) throws Throwable {
        HttpURLConnection httpURLConnection;
        BufferedInputStream bufferedInputStream;
        AdNetworkException adNetworkException = new AdNetworkException();
        BufferedInputStream bufferedInputStream2 = null;
        String strConvertStreamToString = null;
        for (int i3 = 0; strConvertStreamToString == null && i3 <= i; i3++) {
            try {
                httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
                try {
                    httpURLConnection.setConnectTimeout(i2);
                    httpURLConnection.setReadTimeout(i2);
                    String cookie = this.mCookieStore.getCookie(url);
                    if (cookie != null) {
                        httpURLConnection.setRequestProperty("Cookie", cookie);
                    }
                    AdHttpClient.updateParameters(httpURLConnection, getHttpParameters());
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode >= 200 && responseCode <= 299) {
                        setCookies(httpURLConnection);
                        bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                        try {
                            try {
                                strConvertStreamToString = AdHttpClient.convertStreamToString(bufferedInputStream);
                            } catch (Throwable th) {
                                th = th;
                                bufferedInputStream2 = bufferedInputStream;
                                closeQuietly(bufferedInputStream2, httpURLConnection);
                                throw th;
                            }
                        } catch (IOException e) {
                            e = e;
                            AdNetworkException adNetworkException2 = new AdNetworkException(e);
                            closeQuietly(bufferedInputStream, httpURLConnection);
                            adNetworkException = adNetworkException2;
                        }
                    } else {
                        adNetworkException = new AdNetworkException(String.format(Locale.US, "Error response from server. Error Code(%d) for url: %s", Integer.valueOf(responseCode), url.toString()));
                        bufferedInputStream = null;
                    }
                    closeQuietly(bufferedInputStream, httpURLConnection);
                } catch (IOException e2) {
                    e = e2;
                    bufferedInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
                httpURLConnection = null;
                bufferedInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
        }
        if (strConvertStreamToString != null) {
            return strConvertStreamToString;
        }
        throw adNetworkException;
    }

    private static void closeQuietly(InputStream inputStream, HttpURLConnection httpURLConnection) {
        Closeables.closeQuietly(inputStream);
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }
}
