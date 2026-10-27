package com.amazon.alexa.voice;

import android.net.Uri;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.internal.util.ExtensionUtils;
import com.amazonaws.http.HttpHeader;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes2.dex */
public final class RemotePlaylistDataSource implements DataSource {
    private DataSource dataSource;
    private final DefaultHttpDataSourceFactory factory;
    private Uri uri;
    private final String userAgent;

    public RemotePlaylistDataSource(String str) {
        this.userAgent = str;
        this.factory = new DefaultHttpDataSourceFactory(str, null, 8000, 8000, false);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        Uri uriRequestPlaylistUrl = requestPlaylistUrl(dataSpec.uri.toString());
        this.uri = uriRequestPlaylistUrl;
        DataSpec dataSpec2 = new DataSpec(uriRequestPlaylistUrl, dataSpec.postBody, dataSpec.absoluteStreamPosition, dataSpec.position, dataSpec.length, dataSpec.key, dataSpec.flags);
        HttpDataSource httpDataSourceCreateDataSource = this.factory.createDataSource();
        this.dataSource = httpDataSourceCreateDataSource;
        return httpDataSourceCreateDataSource.open(dataSpec2);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.dataSource.read(bArr, i, i2);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            dataSource.close();
            this.dataSource = null;
        }
    }

    private Uri requestPlaylistUrl(String str) throws IOException {
        String line;
        int iIndexOf;
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, this.userAgent);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoOutput(false);
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode < 200 || responseCode >= 300) {
            closeConnection(httpURLConnection);
            throw new IOException("Invalid response code " + responseCode + ", " + httpURLConnection.getHeaderFields());
        }
        String audioContentType = getAudioContentType(httpURLConnection.getContentType());
        if (!"audio/x-mpegurl".equals(audioContentType) && !"audio/x-scpls".equals(audioContentType)) {
            throw new IOException("Invalid Content-Type: " + audioContentType);
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            do {
                try {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        throw new IOException("Invalid content");
                    }
                    iIndexOf = line.indexOf("https://");
                    if (iIndexOf == -1) {
                        iIndexOf = line.indexOf("http://");
                    }
                } finally {
                }
            } while (iIndexOf < 0);
            Uri uri = Uri.parse(line.substring(iIndexOf));
            bufferedReader.close();
            closeConnection(httpURLConnection);
            String extension = ExtensionUtils.getExtension(uri);
            return (".pls".equalsIgnoreCase(extension) || ".m3u".equalsIgnoreCase(extension) || ".m3u8".equalsIgnoreCase(extension)) ? requestPlaylistUrl(uri.toString()) : uri;
        } catch (Throwable th) {
            closeConnection(httpURLConnection);
            throw th;
        }
    }

    private String getAudioContentType(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : str.split(";")) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("audio/")) {
                return strTrim;
            }
        }
        return null;
    }

    private void closeConnection(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.disconnect();
        } catch (Exception e) {
            Logger.error("Failed to close a connection", e);
        }
    }

    public static final class Factory implements DataSource.Factory {
        private final String userAgent;

        public Factory(String str) {
            this.userAgent = str;
        }

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        public DataSource createDataSource() {
            return new RemotePlaylistDataSource(this.userAgent);
        }
    }
}
