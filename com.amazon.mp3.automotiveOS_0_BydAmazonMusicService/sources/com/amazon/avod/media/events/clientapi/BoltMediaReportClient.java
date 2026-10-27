package com.amazon.avod.media.events.clientapi;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.http.HttpStatusCodeException;
import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.json.JacksonResponseParser;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SerializationUtils;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes2.dex */
public class BoltMediaReportClient implements MediaReportClient {
    private final MediaReportClientConfig mClientConfig;
    private String mDomain;
    private boolean mIsAloysiusGZIPCompressionEnabled;
    private boolean mIsTestEndpointReportingEnabled;
    private final JsonFactory mJsonFactory;
    private String mProtocol;
    private final PlaybackHttpRequestBuilderFactory mRequestFactory;
    private String mSchemaVersion;
    private final ServiceClient mServiceClient;
    private String mTestEndpointDomain;
    private String mTestEndpointPath;
    private String mTestEndpointProtocol;
    private Map<String, String> mTestEndpointUrlParams;
    private Map<String, String> mUrlParams;
    private final MediaType mMediaTypeApplicationJson = MediaType.parse("application/json; charset=utf-8");
    private boolean mAreConfigsCached = false;
    private final JacksonResponseParser<BootstrapConfig> mBootstrapResponseParser = new JacksonResponseParser<>(BootstrapConfig.class);
    private final JacksonResponseParser<UpdateConfig> mUpdateResponseParser = new JacksonResponseParser<>(UpdateConfig.class);

    private boolean isNonRetryableErrorCode(int i) {
        return i >= 400 && i < 500;
    }

    public BoltMediaReportClient(@Nonnull PlaybackHttpRequestBuilderFactory playbackHttpRequestBuilderFactory, @Nonnull JsonFactory jsonFactory, @Nonnull ServiceClient serviceClient, @Nonnull MediaReportClientConfig mediaReportClientConfig) {
        this.mRequestFactory = (PlaybackHttpRequestBuilderFactory) Preconditions.checkNotNull(playbackHttpRequestBuilderFactory, "requestFactory");
        this.mJsonFactory = (JsonFactory) Preconditions.checkNotNull(jsonFactory, "jsonFactory");
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mClientConfig = (MediaReportClientConfig) Preconditions.checkNotNull(mediaReportClientConfig, "clientConfig");
    }

    private void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mAreConfigsCached = true;
        this.mIsAloysiusGZIPCompressionEnabled = this.mClientConfig.isAloysiusGZIPCompressionEnabled();
        this.mIsTestEndpointReportingEnabled = this.mClientConfig.isTestEndpointReportingEnabled();
        this.mUrlParams = this.mClientConfig.getUrlParams();
        this.mTestEndpointPath = this.mClientConfig.getTestEndpointPath();
        this.mTestEndpointUrlParams = this.mClientConfig.getTestEndpointUrlParams();
        this.mProtocol = this.mClientConfig.getProtocol();
        this.mDomain = this.mClientConfig.getDomain();
        this.mTestEndpointProtocol = this.mClientConfig.getTestEndpointProtocol();
        this.mTestEndpointDomain = this.mClientConfig.getTestEndpointDomain();
        this.mSchemaVersion = this.mClientConfig.getSchemaVersion();
    }

    @Override // com.amazon.avod.media.events.clientapi.MediaReportClient
    @Nonnull
    public BootstrapConfig bootstrap() throws ServerResponseException, InvalidRequestException {
        DLog.devf("calling aloysius bootstrap endpoint");
        cacheConfigs();
        return (BootstrapConfig) request(this.mRequestFactory.create().setOverriddenEndpoint(createEndpoint()).setUrlPath(createUri("Bootstrap")).setHttpMethod(Request.HttpMethod.GET).setResponseParser(this.mBootstrapResponseParser));
    }

    @Override // com.amazon.avod.media.events.clientapi.MediaReportClient
    public UpdateConfig submitReport(@Nonnull MediaReport mediaReport) throws ServerResponseException, InvalidRequestException {
        Preconditions.checkNotNull(mediaReport, "mediaReport");
        DLog.devf("preparing to call aloysius events endpoint");
        cacheConfigs();
        String strCreateMediaReportJsonPayload = createMediaReportJsonPayload(mediaReport);
        try {
            DLog.devf("submitting aloysius batch, number of events: %d, payload: %s", Integer.valueOf(mediaReport.getEvents().size()), strCreateMediaReportJsonPayload);
            if (this.mIsTestEndpointReportingEnabled) {
                submitTestReport(strCreateMediaReportJsonPayload);
            }
            return buildReport(createEndpoint(), createUri("Events"), this.mUrlParams, strCreateMediaReportJsonPayload);
        } catch (IOException e) {
            DLog.errorf("Cannot serialize string %s", strCreateMediaReportJsonPayload);
            throw new InvalidRequestException("Cannot GZIP payload", e, mediaReport);
        }
    }

    private void submitTestReport(@Nonnull String str) throws IOException {
        Preconditions.checkNotNull(str, "jsonPayload");
        cacheConfigs();
        try {
            buildReport(createTestEndpoint(), this.mTestEndpointPath, this.mTestEndpointUrlParams, str);
        } catch (InvalidRequestException | ServerResponseException e) {
            DLog.exceptionf(e, "Unable to report events to Aloysius test endpoint", new Object[0]);
        }
    }

    private UpdateConfig buildReport(@Nonnull String str, @Nonnull String str2, @Nonnull Map<String, String> map, @Nonnull String str3) throws ServerResponseException, InvalidRequestException, IOException {
        cacheConfigs();
        if (this.mIsAloysiusGZIPCompressionEnabled) {
            byte[] bArrGzipStringToBytes = SerializationUtils.gzipStringToBytes(StandardCharsets.UTF_8, str3);
            HashMap mapNewHashMap = Maps.newHashMap();
            mapNewHashMap.put("Content-Encoding", Optional.of("gzip"));
            mapNewHashMap.put(HttpHeader.CONTENT_LENGTH, Optional.of(String.valueOf(bArrGzipStringToBytes.length)));
            return (UpdateConfig) request(this.mRequestFactory.create().setOverriddenEndpoint(str).setUrlPath(str2).setUrlParamMap(map).setHttpMethod(Request.HttpMethod.POST).setBody(Request.Body.createByteArrayBody(this.mMediaTypeApplicationJson, bArrGzipStringToBytes)).setHeaders(mapNewHashMap).setResponseParser(this.mUpdateResponseParser));
        }
        return (UpdateConfig) request(this.mRequestFactory.create().setOverriddenEndpoint(str).setUrlPath(str2).setUrlParamMap(map).setHttpMethod(Request.HttpMethod.POST).setBody(Request.Body.create(this.mMediaTypeApplicationJson, str3)).setResponseParser(this.mUpdateResponseParser));
    }

    private String createMediaReportJsonPayload(MediaReport mediaReport) throws InvalidRequestException {
        cacheConfigs();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JsonGenerator jsonGeneratorCreateGenerator = this.mJsonFactory.createGenerator(byteArrayOutputStream, JsonEncoding.UTF8);
            jsonGeneratorCreateGenerator.writeStartObject();
            jsonGeneratorCreateGenerator.writeStringField("appInstanceId", mediaReport.getAppInstanceId());
            jsonGeneratorCreateGenerator.writeNumberField("clientPublishTimestamp", mediaReport.getTimestamp());
            jsonGeneratorCreateGenerator.writeNumberField("clientPublishRelativeTime", TimeUnit.NANOSECONDS.toMillis(System.nanoTime()));
            jsonGeneratorCreateGenerator.writeArrayFieldStart("events");
            Iterator<String> it = mediaReport.getEvents().iterator();
            while (it.hasNext()) {
                jsonGeneratorCreateGenerator.writeRawValue(it.next());
            }
            jsonGeneratorCreateGenerator.writeEndArray();
            jsonGeneratorCreateGenerator.writeEndObject();
            jsonGeneratorCreateGenerator.close();
            return byteArrayOutputStream.toString(Charsets.UTF_8.name());
        } catch (IOException e) {
            throw new InvalidRequestException("error while creating the mediaReport json payload", e, null);
        }
    }

    private <T> T request(PlaybackHttpRequestBuilder<T> playbackHttpRequestBuilder) throws ServerResponseException, InvalidRequestException {
        cacheConfigs();
        try {
            Response<T> responseExecuteSync = this.mServiceClient.executeSync(playbackHttpRequestBuilder.setAuthentication(null).build());
            if (responseExecuteSync.hasException()) {
                if (responseExecuteSync.getException().getCause() instanceof HttpStatusCodeException) {
                    if (isNonRetryableErrorCode(((HttpStatusCodeException) responseExecuteSync.getException().getCause()).getStatusCode())) {
                        throw new InvalidRequestException("invalid payload", responseExecuteSync.getException(), responseExecuteSync.getValue());
                    }
                    throw new ServerResponseException("server error", responseExecuteSync.getException());
                }
                throw new ServerResponseException("server error", responseExecuteSync.getException());
            }
            return responseExecuteSync.getValue();
        } catch (RequestBuildException e) {
            throw new InvalidRequestException("bad request, validate inputs before retry", e, null);
        }
    }

    private String createEndpoint() {
        cacheConfigs();
        return String.format(Locale.US, "%s://%s", this.mProtocol, this.mDomain);
    }

    private String createTestEndpoint() {
        cacheConfigs();
        return String.format(Locale.US, "%s://%s", this.mTestEndpointProtocol, this.mTestEndpointDomain);
    }

    private String createUri(String str) {
        cacheConfigs();
        return String.format(Locale.US, "/%s/%s", str, this.mSchemaVersion);
    }

    public static class MediaReportClientConfig extends MediaConfigBase {
        private final ConfigurationValue<Boolean> mAloysiusGZIPCompressionEnabled;
        private final ConfigurationValue<Boolean> mEnableTestEndpointReporting;
        private final ConfigurationValue<String> mTestEndpointDomain;
        private final ConfigurationValue<String> mTestEndpointPath;
        private final ConfigurationValue<String> mTestEndpointProtocol;
        private final ConfigurationValue<Map<String, String>> mTestEndpointUrlParams;
        private final ConfigurationValue<Map<String, String>> mUrlParams;
        private final ConfigurationValue<String> mProtocol = newStringConfigValue("playback.aloysius.endpoint.protocol", "https");
        private final ConfigurationValue<String> mDomain = newStringConfigValue("playback.aloysius.endpoint.domain", "api.us-east-1.aiv-delivery.net");
        private final ConfigurationValue<String> mSchemaVersion = newStringConfigValue("playback.aloysius.endpoint.schemaVersion_1", "AV20180101");

        public MediaReportClientConfig() {
            ConfigType configType = ConfigType.SERVER;
            this.mUrlParams = newStringMapConfigValue("playback.aloysius.endpoint.urlParams", "", ",", "=", configType);
            this.mEnableTestEndpointReporting = newBooleanConfigValue("playback.aloysius.test.endpoint.enabled", false);
            this.mTestEndpointProtocol = newStringConfigValue("playback.aloysius.test.endpoint.protocol", "https");
            this.mTestEndpointDomain = newStringConfigValue("playback.aloysius.test.endpoint.domain", "8hwvbwgkd6.execute-api.us-east-1.amazonaws.com");
            this.mTestEndpointPath = newStringConfigValue("playback.aloysius.test.endpoint.path", "/Prod/v1/");
            this.mTestEndpointUrlParams = newStringMapConfigValue("playback.aloysius.test.endpoint.urlParams", "callbackurl=https://oil2r4k8hi.execute-api.us-east-1.amazonaws.com/prod/automation/aloysius/test/debugapi", ",", "=", configType);
            this.mAloysiusGZIPCompressionEnabled = newBooleanConfigValue("playback.aloysius.gzip.enabled", true, configType);
        }

        @Nonnull
        String getProtocol() {
            return this.mProtocol.getValue();
        }

        @Nonnull
        String getDomain() {
            return this.mDomain.getValue();
        }

        @Nonnull
        String getSchemaVersion() {
            return this.mSchemaVersion.getValue();
        }

        @Nonnull
        Map<String, String> getUrlParams() {
            return this.mUrlParams.getValue();
        }

        boolean isTestEndpointReportingEnabled() {
            return this.mEnableTestEndpointReporting.getValue().booleanValue();
        }

        @Nonnull
        String getTestEndpointProtocol() {
            return this.mTestEndpointProtocol.getValue();
        }

        @Nonnull
        String getTestEndpointDomain() {
            return this.mTestEndpointDomain.getValue();
        }

        @Nonnull
        String getTestEndpointPath() {
            return this.mTestEndpointPath.getValue();
        }

        @Nonnull
        Map<String, String> getTestEndpointUrlParams() {
            return this.mTestEndpointUrlParams.getValue();
        }

        boolean isAloysiusGZIPCompressionEnabled() {
            return this.mAloysiusGZIPCompressionEnabled.getValue().booleanValue();
        }
    }

    public static class PlaybackHttpRequestBuilderFactory {
        public <T> PlaybackHttpRequestBuilder<T> create() {
            return PlaybackHttpRequestBuilder.newBuilder();
        }
    }
}
