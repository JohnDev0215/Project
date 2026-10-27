package com.amazon.mls.config.internal.core.network;

import com.amazon.mls.config.internal.core.util.SafeStreamCloser;
import com.amazonaws.http.HttpHeader;
import com.amazonaws.util.DateUtils;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
class UploadConnection implements Closeable {
    private OutputStream outputStream;
    private HttpURLConnection urlConnection;

    private UploadConnection(URL url, long j) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
        this.urlConnection = httpURLConnection;
        decorateConnection(httpURLConnection, j);
        this.outputStream = this.urlConnection.getOutputStream();
    }

    OutputStream getOutputStream() {
        return this.outputStream;
    }

    int getResponseCode() throws IOException {
        return this.urlConnection.getResponseCode();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        SafeStreamCloser.close(this.outputStream);
        this.outputStream = null;
        HttpURLConnection httpURLConnection = this.urlConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            this.urlConnection = null;
        }
    }

    private void decorateConnection(HttpURLConnection httpURLConnection, long j) {
        httpURLConnection.setRequestProperty("X-Upload-Timestamp", getUploadTimestampString());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        httpURLConnection.setConnectTimeout((int) timeUnit.convert(60L, timeUnit2));
        httpURLConnection.setReadTimeout((int) timeUnit.convert(60L, timeUnit2));
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/json");
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setFixedLengthStreamingMode((int) j);
    }

    private String getUploadTimestampString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }

    public static class Factory {
        public UploadConnection createWithFixedLength(URL url, long j) throws IOException {
            return new UploadConnection(url, j);
        }
    }
}
