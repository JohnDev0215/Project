package com.amazon.music.push.sonarpush;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class AuthenticatedHttpURLConnectionFactory implements HttpURLConnectionFactory {
    private static final String TAG = "AuthenticatedHttpURLConnectionFactory";
    private String mOAuthToken;

    public AuthenticatedHttpURLConnectionFactory(String str) {
        this.mOAuthToken = str;
    }

    @Override // com.amazon.CoralAndroidClient.Connector.HttpURLConnectionFactory
    public HttpURLConnection create(URL url, Proxy proxy) throws NativeException {
        HttpURLConnection httpURLConnection;
        try {
            if (proxy == null) {
                httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
            } else {
                httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection(proxy)));
            }
            if (!TextUtils.isEmpty(this.mOAuthToken)) {
                httpURLConnection.setRequestProperty(HttpHeader.AUTHORIZATION, String.format(Locale.US, "bearer %s", this.mOAuthToken));
            }
            return httpURLConnection;
        } catch (IOException e) {
            Log.e(TAG, "Caught IOException ", e);
            throw new NativeException(e);
        }
    }
}
