package com.amazon.avod.http;

import android.location.Location;
import android.net.Uri;
import android.os.Build;
import com.amazon.avod.config.HttpClientConfig;
import com.amazon.avod.config.ServiceDebugConfig;
import com.amazon.avod.http.MetricEventListener;
import com.amazon.avod.http.internal.AndroidHttpLogger;
import com.amazon.avod.http.internal.UrlServiceNameProvider;
import com.amazon.avod.location.LocationCoordinator;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.session.ServiceSessionManager;
import com.amazon.avod.util.IETFUtils;
import com.amazon.bolthttp.HttpInterceptor;
import com.amazon.bolthttp.Request;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackHttpRequestBuilder<T> {
    private static final MetricEventListener.ServiceNameProvider URL_SERVICE_NAME_PROVIDER = new UrlServiceNameProvider();
    private HttpInterceptor mAuthInterceptor;
    private final DeviceIdentity mDeviceIdentity;
    private final HttpClientConfig mHttpClientConfig;
    private final HttpRequestBuilder<T> mHttpRequestBuilder;
    private final HttpRequestConfig mHttpRequestConfig;
    private final LocationCoordinator mLocationCoordinator;
    private final PlaybackAuthProvider mPlaybackAuthProvider;
    private final ServiceDebugConfig mServiceDebugConfig;
    private final MetricEventListener.ServiceNameProvider mServiceNameProvider;
    private final ServiceSessionManager mServiceSessionManager;
    private final String mTerminatorId;
    private String mUrlPath;
    private final Map<String, String> mUrlParamMap = new HashMap();
    private final Map<String, Optional<String>> mHeaders = new HashMap();
    private Optional<String> mOverriddenEndpoint = Optional.absent();
    private boolean mShouldSuppressAcceptLanguageHeader = false;
    private boolean mShouldSuppressAtvUrlParams = false;
    private boolean mShouldRetry = true;

    public static <T> PlaybackHttpRequestBuilder<T> newBuilder() {
        return newBuilder(MediaSystemSharedDependencies.getInstance());
    }

    static <T> PlaybackHttpRequestBuilder<T> newBuilder(@Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies) {
        mediaSystemSharedDependencies.waitForInitializationUninterruptibly();
        return new PlaybackHttpRequestBuilder<>(HttpRequestBuilder.newBuilder(), mediaSystemSharedDependencies.getDeviceIdentity(), HttpRequestConfig.getInstance(), HttpClientConfig.getInstance(), URL_SERVICE_NAME_PROVIDER, ServiceSessionManager.getInstance(), LocationCoordinator.getInstance(), mediaSystemSharedDependencies.getPlaybackAuthProvider(), mediaSystemSharedDependencies.getTerminatorId(), ServiceDebugConfig.getInstance());
    }

    PlaybackHttpRequestBuilder(@Nonnull HttpRequestBuilder httpRequestBuilder, @Nonnull DeviceIdentity deviceIdentity, @Nonnull HttpRequestConfig httpRequestConfig, @Nonnull HttpClientConfig httpClientConfig, @Nonnull MetricEventListener.ServiceNameProvider serviceNameProvider, @Nonnull ServiceSessionManager serviceSessionManager, @Nonnull LocationCoordinator locationCoordinator, @Nonnull PlaybackAuthProvider playbackAuthProvider, @Nonnull String str, @Nonnull ServiceDebugConfig serviceDebugConfig) {
        this.mHttpRequestBuilder = (HttpRequestBuilder) Preconditions.checkNotNull(httpRequestBuilder, "httpRequestBuilder");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceProperties");
        this.mHttpRequestConfig = (HttpRequestConfig) Preconditions.checkNotNull(httpRequestConfig, "httpRequestConfig");
        this.mHttpClientConfig = (HttpClientConfig) Preconditions.checkNotNull(httpClientConfig, "config");
        this.mServiceNameProvider = (MetricEventListener.ServiceNameProvider) Preconditions.checkNotNull(serviceNameProvider, "serviceNameProvider");
        this.mServiceSessionManager = (ServiceSessionManager) Preconditions.checkNotNull(serviceSessionManager, "serviceSessionManager");
        this.mLocationCoordinator = (LocationCoordinator) Preconditions.checkNotNull(locationCoordinator, "locationCoordinator");
        this.mPlaybackAuthProvider = (PlaybackAuthProvider) Preconditions.checkNotNull(playbackAuthProvider, "playbackAuthProvider");
        this.mTerminatorId = (String) Preconditions.checkNotNull(str, "terminatorId");
        this.mServiceDebugConfig = (ServiceDebugConfig) Preconditions.checkNotNull(serviceDebugConfig, "serviceDebugConfig");
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setHttpMethod(@Nonnull Request.HttpMethod httpMethod) {
        this.mHttpRequestBuilder.setHttpMethod(httpMethod);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setUrlPath(@Nonnull String str) {
        this.mUrlPath = (String) Preconditions.checkNotNull(str, "urlPath");
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setUrlParamMap(@Nonnull Map<String, String> map) {
        Preconditions.checkNotNull(map, "urlParamMap");
        this.mUrlParamMap.putAll(map);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setUrlParamString(@Nullable String str) {
        this.mHttpRequestBuilder.setUrlParamString(str);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setBody(@Nullable Request.Body body) {
        this.mHttpRequestBuilder.setBody(body);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setRequestPriority(@Nonnull RequestPriority requestPriority) {
        Preconditions.checkNotNull(requestPriority, "priority");
        this.mHeaders.put("x-request-priority", Optional.of(requestPriority.getPriorityString()));
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setHeaders(@Nonnull Map<String, Optional<String>> map) {
        Preconditions.checkNotNull(map, "headers");
        this.mHeaders.putAll(map);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setResponseParser(@Nonnull Parser<T> parser) {
        this.mHttpRequestBuilder.setResponseParser(parser);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setAuthentication(@Nullable Map<String, String> map) {
        this.mAuthInterceptor = new PlaybackAuthHttpInterceptor(this.mPlaybackAuthProvider, map);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> setOverriddenEndpoint(@Nullable String str) {
        this.mOverriddenEndpoint = Optional.fromNullable(str);
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> suppressAcceptLanguageHeader() {
        this.mShouldSuppressAcceptLanguageHeader = true;
        return this;
    }

    @Nonnull
    public PlaybackHttpRequestBuilder<T> suppressAtvUrlParams() {
        this.mShouldSuppressAtvUrlParams = true;
        return this;
    }

    @Nonnull
    public Request<T> build() throws RequestBuildException {
        if (!this.mShouldSuppressAtvUrlParams) {
            appendAtvUrlParams();
        }
        appendAtvHeaders();
        this.mHttpRequestBuilder.setUri(getPartnerServiceUrl());
        this.mHttpRequestBuilder.setHeaders(this.mHeaders).setUrlParamMap(this.mUrlParamMap).addEventListener(new MetricEventListener(this.mServiceNameProvider)).setHttpInterceptor(this.mAuthInterceptor);
        if (this.mHttpRequestConfig.isRequestResponseLoggingEnabled()) {
            this.mHttpRequestBuilder.setHttpLogger(new AndroidHttpLogger(this.mServiceNameProvider));
        }
        if (!this.mShouldRetry) {
            this.mHttpRequestBuilder.withNoRetryPolicy();
        }
        return this.mHttpRequestBuilder.build();
    }

    private void appendAtvHeaders() {
        if (!this.mHeaders.containsKey(HttpHeader.ACCEPT)) {
            this.mHeaders.put(HttpHeader.ACCEPT, Optional.of("application/json"));
        }
        if (this.mShouldSuppressAcceptLanguageHeader) {
            this.mHeaders.put("Accept-Language", Optional.absent());
        } else {
            String amazonLocaleString = IETFUtils.toAmazonLocaleString(Locale.getDefault());
            if (!Strings.isNullOrEmpty(amazonLocaleString)) {
                this.mHeaders.put("Accept-Language", Optional.of(amazonLocaleString));
            }
        }
        String sessionId = this.mServiceSessionManager.getSessionId();
        if (sessionId != null) {
            this.mHeaders.put("x-atv-session-id", Optional.fromNullable(sessionId));
        }
        Location locationOrNull = this.mLocationCoordinator.getLastLocation().orNull();
        if (locationOrNull != null) {
            this.mHeaders.put("x-atv-latitude", Optional.of(String.valueOf(locationOrNull.getLatitude())));
            this.mHeaders.put("x-atv-longitude", Optional.of(String.valueOf(locationOrNull.getLongitude())));
        }
        this.mHeaders.put(HttpHeader.USER_AGENT, Optional.of(HttpRequestBuilder.getDefaultUserAgent(this.mHttpRequestConfig)));
    }

    private void appendAtvUrlParams() {
        this.mUrlParamMap.put("format", "json");
        if (!this.mUrlParamMap.containsKey("version")) {
            this.mUrlParamMap.put("version", "1");
        }
        this.mUrlParamMap.put("deviceTypeID", this.mDeviceIdentity.getDeviceTypeId());
        this.mUrlParamMap.put("firmware", "fmw:" + Build.VERSION.SDK + "-app:" + this.mDeviceIdentity.getAppVersionName());
        this.mUrlParamMap.put("deviceID", this.mDeviceIdentity.getDeviceId());
        this.mUrlParamMap.put("uxLocale", IETFUtils.toAmazonLocaleString(Locale.getDefault()));
        this.mUrlParamMap.put("osLocale", IETFUtils.toAmazonLocaleString(Locale.getDefault()));
        if (this.mHttpRequestConfig.isSoftwareVersionHeaderEnabled()) {
            this.mUrlParamMap.put(this.mHttpRequestConfig.getSoftwareVersionHeaderKey(), String.valueOf(this.mDeviceIdentity.getAppMajorVersion()));
        }
    }

    private Uri getPartnerServiceUrl() {
        String terminatorServiceCallUrl;
        if (this.mOverriddenEndpoint.isPresent()) {
            terminatorServiceCallUrl = this.mOverriddenEndpoint.get();
        } else if (shouldUseServerApiSpecificOverride()) {
            terminatorServiceCallUrl = this.mServiceDebugConfig.getServerApiSpecificOverride(this.mUrlPath).get();
        } else {
            terminatorServiceCallUrl = this.mHttpClientConfig.getTerminatorServiceCallUrl(this.mTerminatorId);
        }
        return Uri.parse(terminatorServiceCallUrl).buildUpon().path(this.mUrlPath).build();
    }

    private boolean shouldUseServerApiSpecificOverride() {
        if (this.mServiceDebugConfig.shouldUseServerApiSpecificOverride()) {
            return this.mServiceDebugConfig.getServerApiSpecificOverride(this.mUrlPath).isPresent();
        }
        return false;
    }
}
