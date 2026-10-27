package com.airbnb.lottie.network;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipInputStream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class NetworkFetcher {
    private final Context appContext;

    @Nullable
    private final NetworkCache networkCache;
    private final String url;

    private NetworkFetcher(Context context, String str, @Nullable String str2) {
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        this.url = str;
        if (str2 == null) {
            this.networkCache = null;
        } else {
            this.networkCache = new NetworkCache(applicationContext);
        }
    }

    @Nullable
    @WorkerThread
    private LottieComposition fetchFromCache() {
        Pair<FileExtension, InputStream> pairFetch;
        NetworkCache networkCache = this.networkCache;
        if (networkCache == null || (pairFetch = networkCache.fetch(this.url)) == null) {
            return null;
        }
        FileExtension fileExtension = pairFetch.first;
        InputStream inputStream = pairFetch.second;
        LottieResult<LottieComposition> lottieResultFromZipStreamSync = fileExtension == FileExtension.ZIP ? LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream), this.url) : LottieCompositionFactory.fromJsonInputStreamSync(inputStream, this.url);
        if (lottieResultFromZipStreamSync.getValue() != null) {
            return lottieResultFromZipStreamSync.getValue();
        }
        return null;
    }

    @WorkerThread
    private LottieResult<LottieComposition> fetchFromNetwork() {
        try {
            return fetchFromNetworkInternal();
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    @WorkerThread
    private LottieResult<LottieComposition> fetchFromNetworkInternal() throws IOException {
        Logger.debug("Fetching " + this.url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        httpURLConnection.setRequestMethod("GET");
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
                LottieResult<LottieComposition> resultFromConnection = getResultFromConnection(httpURLConnection);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(resultFromConnection.getValue() != null);
                Logger.debug(sb.toString());
                return resultFromConnection;
            }
            return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to fetch " + this.url + ". Failed with " + httpURLConnection.getResponseCode() + BydAdbHelper.COMMAND_LINE_END + getErrorFromConnection(httpURLConnection)));
        } catch (Exception e) {
            return new LottieResult<>((Throwable) e);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static LottieResult<LottieComposition> fetchSync(Context context, String str, @Nullable String str2) {
        return new NetworkFetcher(context, str, str2).fetchSync();
    }

    private String getErrorFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                        sb.append('\n');
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            } finally {
                try {
                    bufferedReader.close();
                } catch (Exception unused2) {
                }
            }
        }
        return sb.toString();
    }

    @Nullable
    private LottieResult<LottieComposition> getResultFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        FileExtension fileExtension;
        LottieResult<LottieComposition> lottieResultFromJsonInputStreamSync;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip")) {
            Logger.debug("Handling zip response.");
            fileExtension = FileExtension.ZIP;
            NetworkCache networkCache = this.networkCache;
            lottieResultFromJsonInputStreamSync = networkCache == null ? LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(httpURLConnection.getInputStream()), null) : LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(new FileInputStream(networkCache.writeTempCacheFile(this.url, httpURLConnection.getInputStream(), fileExtension))), this.url);
        } else {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            NetworkCache networkCache2 = this.networkCache;
            lottieResultFromJsonInputStreamSync = networkCache2 == null ? LottieCompositionFactory.fromJsonInputStreamSync(httpURLConnection.getInputStream(), null) : LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(new File(networkCache2.writeTempCacheFile(this.url, httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.url);
        }
        if (this.networkCache != null && lottieResultFromJsonInputStreamSync.getValue() != null) {
            this.networkCache.renameTempFile(this.url, fileExtension);
        }
        return lottieResultFromJsonInputStreamSync;
    }

    @WorkerThread
    public LottieResult<LottieComposition> fetchSync() {
        LottieComposition lottieCompositionFetchFromCache = fetchFromCache();
        if (lottieCompositionFetchFromCache != null) {
            return new LottieResult<>(lottieCompositionFetchFromCache);
        }
        Logger.debug("Animation for " + this.url + " not found in cache. Fetching from network.");
        return fetchFromNetwork();
    }
}
