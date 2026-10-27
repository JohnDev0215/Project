package com.amazon.bolthttp;

import com.amazon.bolthttp.HttpResponse;
import com.amazon.bolthttp.internal.HttpLogger;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes2.dex */
public class Request<T> {
    private final Body mBody;
    private final List<EventListener> mEventListenerList;
    private final String mFullLogString;
    private final Headers mHeaders;
    private final HttpInterceptor mHttpInterceptor;
    private final HttpLogger mHttpLogger;
    private volatile boolean mIsCancelled;
    private final HttpMethod mMethod;
    private final NetworkStrategy mNetworkStrategy;
    private final String mRequestKey;
    private final RequestStrategy mRequestStrategy;
    private final HttpResponse.Handler<T> mResponseHandler;
    private final String mSimpleLogString;
    private final URL mUrl;

    public enum HttpMethod {
        GET,
        POST,
        HEAD,
        PUT,
        PATCH,
        DELETE
    }

    public interface KeyStrategy {
        String buildKeyFromRequest(@Nonnull Request<?> request);
    }

    public interface LogOutputFormatter {
        String toString(@Nonnull Request<?> request);
    }

    private Request(Builder<T> builder) {
        this.mUrl = ((Builder) builder).mUrl;
        this.mMethod = ((Builder) builder).mMethod;
        this.mHeaders = ((Builder) builder).mHeadersBuilder.build();
        this.mBody = ((Builder) builder).mBody;
        this.mResponseHandler = ((Builder) builder).mResponseHandler;
        this.mRequestStrategy = ((Builder) builder).mRequestStrategy;
        this.mNetworkStrategy = ((Builder) builder).mNetworkStrategy;
        this.mHttpInterceptor = ((Builder) builder).mHttpInterceptor;
        this.mHttpLogger = ((Builder) builder).mHttpLogger;
        this.mEventListenerList = ((Builder) builder).mEventListenerList;
        this.mSimpleLogString = "Request@" + Integer.toHexString(hashCode());
        this.mFullLogString = (String) Preconditions.checkNotNull(((Builder) builder).mLogOutput.toString(this), "LogOutputFormatter returned null string");
        this.mRequestKey = (String) Preconditions.checkNotNull(((Builder) builder).mKeyStrategy.buildKeyFromRequest(this), "RequestKeyStrategy returned null string");
    }

    @Nonnull
    public URL getUrl() {
        return this.mUrl;
    }

    @Nonnull
    public HttpMethod getMethod() {
        return this.mMethod;
    }

    @Nonnull
    public Headers getHeaders() {
        return this.mHeaders;
    }

    @Nullable
    public Body getBody() {
        return this.mBody;
    }

    @Nonnull
    public HttpResponse.Handler<T> getResponseHandler() {
        return this.mResponseHandler;
    }

    @Nullable
    public RequestStrategy getRequestStrategy() {
        return this.mRequestStrategy;
    }

    @Nullable
    public NetworkStrategy getNetworkStrategy() {
        return this.mNetworkStrategy;
    }

    @Nullable
    public HttpInterceptor getHttpInterceptor() {
        return this.mHttpInterceptor;
    }

    @Nullable
    public HttpLogger getHttpLogger() {
        return this.mHttpLogger;
    }

    @Nonnull
    public List<EventListener> getEventListeners() {
        return this.mEventListenerList;
    }

    public String toString() {
        return (isCancelled() ? "cancelled " : "") + this.mFullLogString;
    }

    public String toSimpleString() {
        return this.mSimpleLogString;
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public static abstract class Body {
        @Nonnull
        public abstract RequestBody toRequestBody() throws IOException;

        public static Body create(@Nonnull MediaType mediaType, @Nonnull String str) {
            Preconditions.checkNotNull(mediaType, "contentType");
            Preconditions.checkNotNull(str, "content");
            if (mediaType.charset() == null) {
                mediaType = MediaType.parse(mediaType.getMediaType() + "; charset=utf-8");
            }
            return new ByteArrayBody(mediaType, str.getBytes(mediaType.charset()));
        }

        public static Body createFormBody(@Nonnull Map<String, String> map) {
            return new FormRequestBody((Map) Preconditions.checkNotNull(map, "formValues"));
        }

        @Nonnull
        public static Body createByteArrayBody(@Nonnull MediaType mediaType, @Nonnull byte[] bArr) {
            return new ByteArrayBody(mediaType, bArr);
        }

        private static class ByteArrayBody extends Body {
            private final byte[] mBytes;
            private final MediaType mMediaType;

            private ByteArrayBody(MediaType mediaType, byte[] bArr) {
                this.mMediaType = mediaType;
                this.mBytes = bArr;
            }

            @Override // com.amazon.bolthttp.Request.Body
            public RequestBody toRequestBody() {
                return RequestBody.create(this.mMediaType, this.mBytes);
            }
        }

        private static class FormRequestBody extends Body {
            private final Map<String, String> mFormValues;

            private FormRequestBody(@Nonnull Map<String, String> map) {
                this.mFormValues = map;
            }

            @Override // com.amazon.bolthttp.Request.Body
            @Nonnull
            public RequestBody toRequestBody() {
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, String> entry : this.mFormValues.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue());
                }
                return builder.build();
            }
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        public static final DefaultKeyStrategy DEFAULT_KEY_STRATEGY;
        public static final DefaultLogOutputFormatter DEFAULT_LOG_OUTPUT_FORMATTER;
        private Body mBody;
        private final List<EventListener> mEventListenerList;
        private final Headers.Builder mHeadersBuilder;
        private HttpInterceptor mHttpInterceptor;
        private HttpLogger mHttpLogger;
        private KeyStrategy mKeyStrategy;
        private LogOutputFormatter mLogOutput;
        private HttpMethod mMethod;
        private NetworkStrategy mNetworkStrategy;
        private RequestStrategy mRequestStrategy;
        private HttpResponse.Handler<T> mResponseHandler;
        private URL mUrl;

        static {
            DEFAULT_LOG_OUTPUT_FORMATTER = new DefaultLogOutputFormatter();
            DEFAULT_KEY_STRATEGY = new DefaultKeyStrategy();
        }

        private Builder() {
            this.mMethod = HttpMethod.GET;
            this.mHeadersBuilder = new Headers.Builder();
            this.mEventListenerList = new LinkedList();
            this.mLogOutput = DEFAULT_LOG_OUTPUT_FORMATTER;
            this.mKeyStrategy = DEFAULT_KEY_STRATEGY;
        }

        public Builder<T> setUrl(@Nonnull String str) {
            try {
                setUrl(new URL((String) Preconditions.checkNotNull(str, ImagesContract.URL)));
                return this;
            } catch (MalformedURLException unused) {
                throw new IllegalArgumentException("malformed url: " + str);
            }
        }

        public Builder<T> setUrl(@Nonnull URL url) {
            this.mUrl = (URL) Preconditions.checkNotNull(url, ImagesContract.URL);
            return this;
        }

        public Builder<T> setMethod(@Nonnull HttpMethod httpMethod) {
            this.mMethod = (HttpMethod) Preconditions.checkNotNull(httpMethod, "method");
            return this;
        }

        public Builder<T> setHeader(@Nonnull String str, @Nonnull String str2) {
            this.mHeadersBuilder.set((String) Preconditions.checkNotNull(str, "fieldName"), (String) Preconditions.checkNotNull(str2, "value"));
            return this;
        }

        public Builder<T> removeHeader(@Nonnull String str) {
            this.mHeadersBuilder.removeAll((String) Preconditions.checkNotNull(str, "fieldName"));
            return this;
        }

        public Builder<T> setBody(@Nullable Body body) {
            this.mBody = body;
            return this;
        }

        public Builder<T> setResponseHandler(@Nonnull HttpResponse.Handler<T> handler) {
            this.mResponseHandler = (HttpResponse.Handler) Preconditions.checkNotNull(handler, "responseHandler");
            return this;
        }

        public Builder<T> setRequestStrategy(@Nullable RequestStrategy requestStrategy) {
            this.mRequestStrategy = requestStrategy;
            return this;
        }

        public Builder<T> setNetworkStrategy(@Nullable NetworkStrategy networkStrategy) {
            this.mNetworkStrategy = networkStrategy;
            return this;
        }

        public Builder<T> setHttpInterceptor(@Nullable HttpInterceptor httpInterceptor) {
            this.mHttpInterceptor = httpInterceptor;
            return this;
        }

        public Builder<T> setHttpLogger(@Nullable HttpLogger httpLogger) {
            this.mHttpLogger = httpLogger;
            return this;
        }

        public Builder<T> addEventListener(@Nonnull EventListener eventListener) {
            this.mEventListenerList.add((EventListener) Preconditions.checkNotNull(eventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
            return this;
        }

        public Builder<T> setLogOutputFormatter(@Nonnull LogOutputFormatter logOutputFormatter) {
            this.mLogOutput = (LogOutputFormatter) Preconditions.checkNotNull(logOutputFormatter, "logOutputFormatter");
            return this;
        }

        @Nonnull
        public Request<T> build() {
            Preconditions.checkState(this.mUrl != null, "setUrl() never called");
            Preconditions.checkState(this.mResponseHandler != null, "setResponseHandler() never called");
            return new Request<>(this);
        }
    }

    private static class DefaultLogOutputFormatter implements LogOutputFormatter {
        private DefaultLogOutputFormatter() {
        }

        @Override // com.amazon.bolthttp.Request.LogOutputFormatter
        public String toString(@Nonnull Request<?> request) {
            return String.format("%s[%s %s%s]", request.toSimpleString(), request.getMethod(), request.getUrl(), request.getBody() != null ? " w/ body" : "");
        }
    }

    private static class DefaultKeyStrategy implements KeyStrategy {
        private DefaultKeyStrategy() {
        }

        @Override // com.amazon.bolthttp.Request.KeyStrategy
        public String buildKeyFromRequest(@Nonnull Request<?> request) {
            return request.getUrl().toString();
        }
    }
}
