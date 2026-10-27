package com.amazon.alexa.voice.core.http;

import com.amazon.alexa.voice.core.Logger;
import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes2.dex */
public final class TLSv12HttpClient {
    private TLSv12HttpClient() {
        throw new IllegalStateException("No instances!");
    }

    public static OkHttpClient create() {
        return create(10, 10, 10);
    }

    public static OkHttpClient create(int i, int i2, int i3) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1) {
                TrustManager trustManager = trustManagers[0];
                if (trustManager instanceof X509TrustManager) {
                    X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                    SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                    sSLContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                    OkHttpClient.Builder builderConnectionSpecs = new OkHttpClient.Builder().sslSocketFactory(new TLSSocketFactory(sSLContext.getSocketFactory()), x509TrustManager).connectionSpecs(Collections.singletonList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build()));
                    long j = i2;
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    return builderConnectionSpecs.readTimeout(j, timeUnit).writeTimeout(i, timeUnit).connectTimeout(i3, timeUnit).connectionPool(new ConnectionPool(0, 1L, TimeUnit.NANOSECONDS)).addInterceptor(new LoggerInterceptor()).build();
                }
            }
            throw new IllegalStateException("Unexpected default trust managers: " + Arrays.toString(trustManagers));
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static class LoggerInterceptor implements Interceptor {
        LoggerInterceptor() {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            handleRequest(chain, request);
            return handleResponse(chain, request);
        }

        private Response handleResponse(Interceptor.Chain chain, Request request) throws Exception {
            long jNanoTime = System.nanoTime();
            try {
                Response responseProceed = chain.proceed(request);
                Logger.debug("<-- " + responseProceed.code() + ' ' + responseProceed.message() + ' ' + responseProceed.request().url() + " (" + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime) + "ms)");
                Headers headers = responseProceed.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    Logger.debug(headers.name(i) + ": " + headers.value(i));
                }
                return responseProceed;
            } catch (Exception e) {
                Logger.debug("<-- HTTP FAILED: " + e);
                throw e;
            }
        }

        private void handleRequest(Interceptor.Chain chain, Request request) throws IOException {
            RequestBody requestBodyBody = request.body();
            boolean z = requestBodyBody != null;
            Connection connection = chain.connection();
            String str = "--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1);
            if (z) {
                str = str + " (" + requestBodyBody.contentLength() + "-byte body)";
            }
            Logger.debug(str);
            if (z) {
                if (requestBodyBody.getContentType() != null) {
                    Logger.debug("Content-Type: " + requestBodyBody.getContentType());
                }
                if (requestBodyBody.contentLength() != -1) {
                    Logger.debug("Content-Length: " + requestBodyBody.contentLength());
                }
            }
            Headers headers = request.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                if (!HttpHeader.CONTENT_TYPE.equalsIgnoreCase(strName) && !HttpHeader.CONTENT_LENGTH.equalsIgnoreCase(strName)) {
                    Logger.debug(strName + ": " + headers.value(i));
                }
            }
        }
    }
}
