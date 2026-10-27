package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/* JADX INFO: loaded from: classes2.dex */
public class CommsNetworkingClient implements INetworkingClient {
    static int defaultReadTimeout = 15;
    static int defaultWriteTimeout = 15;
    private AuthenticationProvider authProvider;
    private String host;
    private List<Interceptor> interceptors = new ArrayList();
    private AlexaCommsCoreMetricsService metricsService;
    private OkHttpClient okHttpClient;
    private int readTimeout;
    private String source;
    private int writeTimeout;

    public static class Builder implements INetworkingClient.Builder {
        AuthenticationProvider authProvider;
        String host;
        AlexaCommsCoreMetricsService metricsService;
        OkHttpClient okHttpClient;
        Integer readTimeout;
        String source;
        Integer writeTimeout;
        List<Interceptor> interceptors = new ArrayList();
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        public INetworkingClient.Builder withSource(@NonNull String str) {
            this.source = str;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withHost(@NonNull String str) {
            this.host = str;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withAuthProvider(@NonNull AuthenticationProvider authenticationProvider) {
            this.authProvider = authenticationProvider;
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withReadTimeout(int i) {
            this.readTimeout = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient.Builder withWriteTimeout(int i) {
            this.writeTimeout = Integer.valueOf(i);
            return this;
        }

        int getReadTimeout() {
            Integer num = this.readTimeout;
            return num == null ? CommsNetworkingClient.defaultReadTimeout : num.intValue();
        }

        int getWriteTimeout() {
            Integer num = this.writeTimeout;
            return num == null ? CommsNetworkingClient.defaultWriteTimeout : num.intValue();
        }

        @Override // com.amazon.commsnetworking.api.INetworkingClient.Builder
        public INetworkingClient build() throws IllegalArgumentException {
            if (this.source == null) {
                throw new IllegalArgumentException("`source` must be specified using `withSource`");
            }
            if (this.host == null) {
                throw new IllegalArgumentException("`host` must be specified using `withHost`");
            }
            if (this.authProvider == null) {
                throw new IllegalArgumentException("`authProvider` must be specified using `withAuthProvider`");
            }
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            this.okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor);
            AlexaCommsCoreMetricsService alexaCommsCoreMetricsService = this.metricsService;
            if (alexaCommsCoreMetricsService != null) {
                this.okHttpBuilder.addInterceptor(new CommsMetricInterceptor(alexaCommsCoreMetricsService));
            }
            Iterator<Interceptor> it = this.interceptors.iterator();
            while (it.hasNext()) {
                this.okHttpBuilder.addInterceptor(it.next());
            }
            OkHttpClient.Builder builder = this.okHttpBuilder;
            long readTimeout = getReadTimeout();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            builder.readTimeout(readTimeout, timeUnit);
            this.okHttpBuilder.writeTimeout(getWriteTimeout(), timeUnit);
            this.okHttpClient = this.okHttpBuilder.build();
            return new CommsNetworkingClient(this);
        }
    }

    CommsNetworkingClient(@NonNull Builder builder) {
        this.source = builder.source;
        this.host = builder.host;
        this.authProvider = builder.authProvider;
        this.readTimeout = builder.getReadTimeout();
        this.writeTimeout = builder.getWriteTimeout();
        this.metricsService = builder.metricsService;
        this.interceptors.addAll(builder.interceptors);
        this.okHttpClient = builder.okHttpClient;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public int getReadTimeout() {
        return this.readTimeout;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    @NonNull
    public AuthenticationProvider getAuthProvider() {
        return this.authProvider;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest get(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.get(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest post(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.post(this.source, this.host, str, str2);
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkRequest patch(@NonNull String str, @NonNull String str2) {
        return CommsNetworkRequest.patch(this.source, this.host, str, str2);
    }

    @VisibleForTesting
    Call createCall(@NonNull Request request) {
        return this.okHttpClient.newCall(request);
    }

    private Call createCall(@NonNull INetworkRequest iNetworkRequest) {
        Call callCreateCall = createCall(iNetworkRequest.buildRequest());
        iNetworkRequest.setCall(callCreateCall);
        return callCreateCall;
    }

    @Override // com.amazon.commsnetworking.api.INetworkingClient
    public INetworkResponse execute(@NonNull INetworkRequest iNetworkRequest) throws NetworkException {
        this.authProvider.authenticateRequest(iNetworkRequest);
        try {
            CommsNetworkResponse commsNetworkResponse = new CommsNetworkResponse(iNetworkRequest, FirebasePerfOkHttpClient.execute(createCall(iNetworkRequest)));
            if (commsNetworkResponse.isSuccessful()) {
                return commsNetworkResponse;
            }
            if (iNetworkRequest.canRetry(commsNetworkResponse)) {
                iNetworkRequest.prepareForRetry(commsNetworkResponse);
                return execute(iNetworkRequest);
            }
            throw new NetworkException(iNetworkRequest, commsNetworkResponse);
        } catch (IOException e) {
            throw new NetworkException(iNetworkRequest, e);
        }
    }
}
