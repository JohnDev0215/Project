package com.android.inputmethod.latin.network;

import android.text.TextUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class HttpUrlConnectionBuilder {
    private static final int DEFAULT_TIMEOUT_MILLIS = 5000;
    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
    public static final String KEY_CACHE_CONTROL = "Cache-Control";
    public static final int MODE_BI_DIRECTIONAL = 3;
    public static final int MODE_DOWNLOAD_ONLY = 2;
    public static final int MODE_UPLOAD_ONLY = 1;
    public static final String VALUE_NO_CACHE = "no-cache";
    private int mMode;
    private URL mUrl;
    private boolean mUseCache;
    private final HashMap<String, String> mHeaderMap = new HashMap<>();
    private int mConnectTimeoutMillis = 5000;
    private int mReadTimeoutMillis = 5000;
    private int mContentLength = -1;

    public HttpUrlConnectionBuilder setUrl(String url) throws MalformedURLException {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("URL must not be empty");
        }
        this.mUrl = new URL(url);
        return this;
    }

    public HttpUrlConnectionBuilder setConnectTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("connect-timeout must be >= 0, but was " + timeoutMillis);
        }
        this.mConnectTimeoutMillis = timeoutMillis;
        return this;
    }

    public HttpUrlConnectionBuilder setReadTimeout(int timeoutMillis) {
        if (timeoutMillis < 0) {
            throw new IllegalArgumentException("read-timeout must be >= 0, but was " + timeoutMillis);
        }
        this.mReadTimeoutMillis = timeoutMillis;
        return this;
    }

    public HttpUrlConnectionBuilder addHeader(String key, String value) {
        this.mHeaderMap.put(key, value);
        return this;
    }

    public HttpUrlConnectionBuilder setAuthToken(String value) {
        this.mHeaderMap.put(HTTP_HEADER_AUTHORIZATION, value);
        return this;
    }

    public HttpUrlConnectionBuilder setFixedLengthForStreaming(int length) {
        this.mContentLength = length;
        return this;
    }

    public HttpUrlConnectionBuilder setUseCache(boolean useCache) {
        this.mUseCache = useCache;
        return this;
    }

    public HttpUrlConnectionBuilder setMode(int mode) {
        if (mode != 1 && mode != 2 && mode != 3) {
            throw new IllegalArgumentException("Invalid mode specified:" + mode);
        }
        this.mMode = mode;
        return this;
    }

    public HttpURLConnection build() throws IOException {
        URL url = this.mUrl;
        if (url == null) {
            throw new IllegalArgumentException("A URL must be specified!");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.mConnectTimeoutMillis);
        httpURLConnection.setReadTimeout(this.mReadTimeoutMillis);
        httpURLConnection.setUseCaches(this.mUseCache);
        int i = this.mMode;
        if (i == 1) {
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
        } else if (i == 2) {
            httpURLConnection.setDoInput(false);
            httpURLConnection.setDoOutput(true);
        } else if (i == 3) {
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        }
        for (Map.Entry<String, String> entry : this.mHeaderMap.entrySet()) {
            httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        int i2 = this.mContentLength;
        if (i2 >= 0) {
            httpURLConnection.setFixedLengthStreamingMode(i2);
        }
        return httpURLConnection;
    }
}
