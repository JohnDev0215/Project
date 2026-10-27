package com.facebook.react.modules.network;

import android.content.Context;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes6.dex */
public class OkHttpClientProvider {

    @Nullable
    private static OkHttpClientFactory sFactory;

    public static void setOkHttpClientFactory(OkHttpClientFactory okHttpClientFactory) {
        sFactory = okHttpClientFactory;
    }

    public static OkHttpClient createClient() {
        OkHttpClientFactory okHttpClientFactory = sFactory;
        if (okHttpClientFactory != null) {
            return okHttpClientFactory.createNewNetworkModuleClient();
        }
        return createClientBuilder().build();
    }

    public static OkHttpClient createClient(Context context) {
        OkHttpClientFactory okHttpClientFactory = sFactory;
        if (okHttpClientFactory != null) {
            return okHttpClientFactory.createNewNetworkModuleClient();
        }
        return createClientBuilder(context).build();
    }

    public static OkHttpClient.Builder createClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return builder.connectTimeout(0L, timeUnit).readTimeout(0L, timeUnit).writeTimeout(0L, timeUnit).cookieJar(new ReactCookieJarContainer());
    }

    public static OkHttpClient.Builder createClientBuilder(Context context) {
        return createClientBuilder(context, 10485760);
    }

    public static OkHttpClient.Builder createClientBuilder(Context context, int i) {
        OkHttpClient.Builder builderCreateClientBuilder = createClientBuilder();
        return i == 0 ? builderCreateClientBuilder : builderCreateClientBuilder.cache(new Cache(new File(context.getCacheDir(), "http-cache"), i));
    }
}
