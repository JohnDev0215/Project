package com.amazon.avod.http;

import android.net.Uri;
import android.webkit.URLUtil;
import com.amazon.avod.core.Framework;
import com.amazon.avod.core.MissingResponseBodyException;
import com.amazon.avod.perf.MetricsDebugger;
import com.amazon.avod.service.ResponseRetryPolicy;
import com.amazon.avod.util.CharsetUtils;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.HttpInterceptor;
import com.amazon.bolthttp.HttpResponse;
import com.amazon.bolthttp.NetworkStrategy;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.RequestStrategy;
import com.amazon.bolthttp.internal.HttpLogger;
import com.amazon.bolthttp.policy.impl.ExponentialBackoffPolicy;
import com.amazon.bolthttp.policy.impl.Retryability;
import com.amazon.bolthttp.policy.impl.SimpleRetryPolicy;
import com.amazon.bolthttp.policy.impl.SimpleTimeoutPolicy;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes2.dex */
public class HttpRequestBuilder<T> {
    private static final Request.LogOutputFormatter LOG_OUTPUT = new SanitizedLogOutput();
    private HttpInterceptor mHttpInterceptor;
    private HttpLogger mHttpLogger;
    private final HttpRequestConfig mHttpRequestConfig;
    private final MetricsDebugger mMetricsDebugger;
    private Parser<T> mParser;
    private Request.Body mRequestBody;
    private Uri mUri;
    private String mUrlParamString;
    private ResponseRetryPolicy mResponseRetryPolicy = null;
    private SimpleRetryPolicy mNetworkRetryPolicy = null;
    private HttpResponse.Handler<T> mResponseHandler = null;
    private boolean mShouldRetry = true;
    private Set<Class<? extends Exception>> mRetryableExceptions = Retryability.SAFE_RETRYABLE_NETWORK_EXCEPTIONS;
    private Request.HttpMethod mHttpMethod = Request.HttpMethod.GET;
    private Map<String, String> mUrlParamMap = ImmutableMap.of();
    private Map<String, Optional<String>> mHeaders = ImmutableMap.of();
    private final Set<EventListener> mEventListeners = Sets.newHashSet();

    public static class LazyLoad {
        public static final MediaType STRING_MEDIA_TYPE = MediaType.parse("text/plain; charset=utf-8");

        @Nullable
        public static final String DEFAULT_USER_AGENT = HttpRequestBuilder.sanitizeUserAgent(System.getProperty("http.agent"));
    }

    public static <T> HttpRequestBuilder<T> newBuilder() {
        return new HttpRequestBuilder<>(HttpRequestConfig.getInstance(), MetricsDebugger.getInstance());
    }

    HttpRequestBuilder(@Nonnull HttpRequestConfig httpRequestConfig, @Nonnull MetricsDebugger metricsDebugger) {
        this.mHttpRequestConfig = (HttpRequestConfig) Preconditions.checkNotNull(httpRequestConfig, "config");
        this.mMetricsDebugger = (MetricsDebugger) Preconditions.checkNotNull(metricsDebugger, "metricsDebugger");
    }

    @Nonnull
    public HttpRequestBuilder<T> setHttpMethod(@Nonnull Request.HttpMethod httpMethod) {
        this.mHttpMethod = (Request.HttpMethod) Preconditions.checkNotNull(httpMethod, "method");
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setUri(@Nonnull Uri uri) {
        this.mUri = (Uri) Preconditions.checkNotNull(uri, "uri");
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setUrlParamMap(@Nonnull Map<String, String> map) {
        this.mUrlParamMap = (Map) Preconditions.checkNotNull(map, "urlParamMap");
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setUrlParamString(@Nullable String str) {
        this.mUrlParamString = str;
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setBody(@Nullable Request.Body body) {
        this.mRequestBody = body;
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setHeaders(@Nonnull Map<String, Optional<String>> map) {
        this.mHeaders = (Map) Preconditions.checkNotNull(map, "headers");
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setResponseParser(@Nonnull Parser<T> parser) {
        this.mParser = (Parser) Preconditions.checkNotNull(parser, "parser");
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> withNoRetryPolicy() {
        this.mShouldRetry = false;
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> addEventListener(@Nonnull EventListener eventListener) {
        Preconditions.checkNotNull(eventListener, "eventListener");
        this.mEventListeners.add(eventListener);
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setHttpInterceptor(@Nullable HttpInterceptor httpInterceptor) {
        this.mHttpInterceptor = httpInterceptor;
        return this;
    }

    @Nonnull
    public HttpRequestBuilder<T> setHttpLogger(@Nonnull HttpLogger httpLogger) {
        Preconditions.checkState(Framework.isDebugConfigurationEnabled(), "Can't enable request/response logging on release build!");
        this.mHttpLogger = httpLogger;
        return this;
    }

    @Nonnull
    public Request<T> build() throws RequestBuildException {
        Request.Builder<T> builder = Request.builder();
        builder.setMethod(this.mHttpMethod);
        builder.setUrl(buildUrl());
        builder.setBody(this.mRequestBody);
        attachHeaders(builder);
        configureStrategies(builder);
        HttpResponse.Handler<T> responseHandler = this.mResponseHandler;
        if (responseHandler == null) {
            responseHandler = new ResponseHandler<>(this.mParser);
        }
        builder.setResponseHandler(responseHandler);
        builder.setLogOutputFormatter(LOG_OUTPUT);
        Iterator<EventListener> it = this.mEventListeners.iterator();
        while (it.hasNext()) {
            builder.addEventListener(it.next());
        }
        builder.addEventListener(this.mMetricsDebugger);
        builder.setHttpInterceptor(this.mHttpInterceptor);
        builder.setHttpLogger(this.mHttpLogger);
        return builder.build();
    }

    private String buildUrl() throws RequestBuildException {
        String str = this.mUri.toString() + buildUrlParams();
        if (URLUtil.isNetworkUrl(str)) {
            return str;
        }
        throw new RequestBuildException(new MalformedURLException("Url is malformed: " + str));
    }

    private String buildUrlParams() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayListNewArrayList = Lists.newArrayList(this.mUrlParamMap.keySet());
        Collections.sort(arrayListNewArrayList, String.CASE_INSENSITIVE_ORDER);
        String str = "?";
        boolean z = false;
        for (String str2 : arrayListNewArrayList) {
            String str3 = this.mUrlParamMap.get(str2);
            if (z) {
                str = "&";
            }
            if (str2 == null || str3 == null) {
                DLog.warnf("Dropping pair (key: %s, val: %s) due to null value(s)", str2, str3);
            } else {
                try {
                    sb.append(str);
                    sb.append(URLEncoder.encode(str2, ParserUtil.UTF_8));
                    sb.append("=");
                    sb.append(URLEncoder.encode(str3, ParserUtil.UTF_8));
                    z = true;
                } catch (UnsupportedEncodingException e) {
                    DLog.exceptionf(e);
                }
            }
        }
        if (!Strings.isNullOrEmpty(this.mUrlParamString)) {
            sb.append(str);
            sb.append(this.mUrlParamString);
        }
        return sb.toString();
    }

    private void attachHeaders(Request.Builder<T> builder) {
        builder.setHeader("x-gasc-enabled", "true");
        String host = this.mUri.getHost();
        if (this.mHttpRequestConfig.shouldUseHostHeader() && !Strings.isNullOrEmpty(host)) {
            builder.setHeader(HttpHeader.HOST, host);
        }
        for (Map.Entry<String, Optional<String>> entry : this.mHeaders.entrySet()) {
            Optional<String> value = entry.getValue();
            if (value.isPresent()) {
                builder.setHeader(entry.getKey(), value.get());
            } else {
                builder.removeHeader(entry.getKey());
            }
        }
    }

    private void configureStrategies(Request.Builder<T> builder) {
        ResponseRetryPolicy responseRetryPolicy = this.mResponseRetryPolicy;
        if (responseRetryPolicy == null) {
            responseRetryPolicy = new ResponseRetryPolicy(this.mShouldRetry ? this.mHttpRequestConfig.getMaxRequestRetryAttempts() : 1, this.mHttpRequestConfig.getMaxRequestRetryDuration());
        }
        builder.setRequestStrategy(RequestStrategy.builder().setRetryPolicy(responseRetryPolicy).setBackoffPolicy(new ExponentialBackoffPolicy(this.mHttpRequestConfig.getBaseRequestRetryIntervalBackoff(), 2.0d, this.mHttpRequestConfig.getRequestRetryJitterBackoff())).build());
        SimpleRetryPolicy simpleRetryPolicy = this.mNetworkRetryPolicy;
        if (simpleRetryPolicy == null) {
            simpleRetryPolicy = new SimpleRetryPolicy(this.mShouldRetry ? 3 : 1, SimpleRetryPolicy.DEFAULT_RETRY_LIMIT_MILLIS, this.mRetryableExceptions);
        }
        builder.setNetworkStrategy(NetworkStrategy.builder().setRetryPolicy(simpleRetryPolicy).setTimeoutPolicy(new SimpleTimeoutPolicy(this.mHttpRequestConfig.getConnectivityTimeout(), this.mHttpRequestConfig.getConnectionTimeout(), this.mHttpRequestConfig.getSocketTimeout())).build());
    }

    @Nonnull
    public static String getDefaultUserAgent(HttpRequestConfig httpRequestConfig) {
        return (String) Optional.fromNullable(LazyLoad.DEFAULT_USER_AGENT).or(httpRequestConfig.getFallbackUserAgent());
    }

    @Nullable
    public static String sanitizeUserAgent(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static class ResponseHandler<T> implements HttpResponse.Handler<T> {
        private final Parser<T> mParser;

        public ResponseHandler(@Nonnull Parser<T> parser) {
            this.mParser = (Parser) Preconditions.checkNotNull(parser, "parser == null");
        }

        @Override // com.amazon.bolthttp.HttpResponse.Handler
        public T process(HttpResponse<T> httpResponse) throws Exception {
            int responseCode = httpResponse.getResponseCode();
            if (responseCode != 200) {
                String str = httpResponse.getBody() == null ? null : new String(httpResponse.getBody(), CharsetUtils.getCharset(httpResponse.getHeaders()));
                throw new HttpStatusCodeException(String.format(Locale.US, "Invalid response: %s %s %s", Integer.valueOf(responseCode), httpResponse.getResponseMessage(), str), responseCode, str, httpResponse.getHeaders().get("x-atv-error-type"));
            }
            byte[] body = httpResponse.getBody();
            if (body == null) {
                throw new MissingResponseBodyException("No response body");
            }
            return this.mParser.parse(httpResponse.getRequest(), httpResponse.getHeaders(), body);
        }
    }

    private static class SanitizedLogOutput implements Request.LogOutputFormatter {
        private SanitizedLogOutput() {
        }

        @Override // com.amazon.bolthttp.Request.LogOutputFormatter
        public String toString(Request<?> request) {
            return String.format(Locale.US, "%s[%s %s%s]", request.toSimpleString(), request.getMethod(), DLog.sanitize_avod_message(request.getUrl()), request.getBody() != null ? " w/ body" : "");
        }
    }
}
