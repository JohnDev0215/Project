package com.squareup.picasso;

import android.content.Context;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* JADX INFO: loaded from: classes7.dex */
public final class OkHttp3Downloader implements Downloader {
    private final okhttp3.Cache cache;
    final Call.Factory client;
    private boolean sharedClient;

    public OkHttp3Downloader(Context context) {
        this(Utils.createDefaultCacheDir(context));
    }

    public OkHttp3Downloader(File file) {
        this(file, Utils.calculateDiskCacheSize(file));
    }

    public OkHttp3Downloader(File file, long j) {
        this(new OkHttpClient.Builder().cache(new okhttp3.Cache(file, j)).build());
        this.sharedClient = false;
    }

    public OkHttp3Downloader(OkHttpClient okHttpClient) {
        this.sharedClient = true;
        this.client = okHttpClient;
        this.cache = okHttpClient.cache();
    }

    @Override // com.squareup.picasso.Downloader
    public Response load(okhttp3.Request request) throws IOException {
        return FirebasePerfOkHttpClient.execute(this.client.newCall(request));
    }
}
