package com.amazon.podcast.interaction;

import SOACoreInterface.v1_0.ApplicationIdentity;
import SOACoreInterface.v1_0.ClientState;
import SOACoreInterface.v1_0.DeviceIdentity;
import SOACoreInterface.v1_0.Identity;
import SOACoreInterface.v1_0.Request;
import SOACoreInterface.v1_0.RequestIdentity;
import SOACoreInterface.v1_0.Response;
import SOACoreInterface.v1_0.UserIdentity;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class InvokeRemoteSkillTask {
    private static OkHttpClient sharedOkHttpClient;
    private static OkHttpClient sharedRequestCompressingOkHttpClient;
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final Map<String, ClientState> clientStateMap;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final Map<String, String> extras;
    private final boolean isPodcastCompressRequestsEnabled;
    private final String latencyPageType;
    private final OkHttpClient okHttpClient;
    private final String preset;
    private final String target;
    private final String url;
    private final UserInfoProvider userInfoProvider;
    private static final Logger logger = LoggerFactory.getLogger("InvokeRemoteSkillTask");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public InvokeRemoteSkillTask(final String url, final String target, final String preset, final DeviceInfoProvider deviceInfoProvider, final BuildInfoProvider buildInfoProvider, final Map<String, String> extras, final UserInfoProvider userInfoProvider, final AuthenticationProvider authProvider, final Map<String, ClientState> clientStateMap, final String latencyPageType) {
        OkHttpClient okHttpClient;
        this.url = url;
        this.target = target;
        this.preset = preset;
        this.extras = extras;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.userInfoProvider = userInfoProvider;
        this.authProvider = authProvider;
        this.clientStateMap = clientStateMap;
        this.latencyPageType = latencyPageType;
        boolean zIsEnabled = PodcastFeatureGating.PODCAST_COMPRESSED_REQUEST.isEnabled();
        this.isPodcastCompressRequestsEnabled = zIsEnabled;
        this.customerMetadataProvider = (CustomerMetadataProvider) Providers.INSTANCE.get(CustomerMetadataProvider.class);
        if (Podcast.getNetworkSettingsProvider().isOverridingEndpoint()) {
            okHttpClient = getUnsafeOkHttpClient();
        } else {
            okHttpClient = getOkHttpClient(Boolean.valueOf(zIsEnabled));
        }
        this.okHttpClient = okHttpClient;
    }

    private static OkHttpClient getOkHttpClient(Boolean isPodcastCompressRequestsEnabled) {
        if (isPodcastCompressRequestsEnabled.booleanValue()) {
            if (sharedRequestCompressingOkHttpClient == null) {
                sharedRequestCompressingOkHttpClient = UnifiedOkHttpClient.newBuilder().addInterceptor(new GzipRequestInterceptor()).build();
            }
            return sharedRequestCompressingOkHttpClient;
        }
        if (sharedOkHttpClient == null) {
            sharedOkHttpClient = UnifiedOkHttpClient.newClient();
        }
        return sharedOkHttpClient;
    }

    public Response execute() {
        try {
            Headers.Builder builderAdd = new Headers.Builder().add(HttpHeader.ACCEPT, "application/json").add("Accept-Charset", "utf-8").add(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8").add("Connection", "keep-alive").add("x-amzn-RequestId", UUID.randomUUID().toString()).add("x-amz-target", this.target);
            if (!this.isPodcastCompressRequestsEnabled) {
                builderAdd.add("Content-Encoding", "amz-1.0");
            }
            Headers headersBuild = builderAdd.build();
            Request requestBuild = Request.builder().withPreset(this.preset).withExtra(this.extras).withClientStates(this.clientStateMap).withIdentity(getIdentity()).build();
            Call callNewCall = this.okHttpClient.newCall(new Request.Builder().headers(headersBuild).post(RequestBody.create(JSON, Mappers.mapper().writeValueAsString(requestBuild))).url(HttpUrl.parse(this.url).newBuilder().build().uri().toString()).build());
            LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RESPONSE, this.latencyPageType);
            okhttp3.Response responseExecute = FirebasePerfOkHttpClient.execute(callNewCall);
            int iCode = responseExecute.code();
            if (iCode == 200) {
                LatencyMetricInterface.trackLatency(LatencyTrackingLeg.PROCESSING, this.latencyPageType);
                ResponseBody responseBodyBody = responseExecute.body();
                if (responseBodyBody != null) {
                    return (Response) Mappers.mapper().readValue(responseBodyBody.string(), Response.class);
                }
            } else {
                logger.error("Error making network call, response code {}", Integer.valueOf(iCode));
            }
        } catch (Exception e) {
            logger.error("Error making network call", (Throwable) e);
        }
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, this.latencyPageType);
        return null;
    }

    private Identity getIdentity() {
        ApplicationIdentity applicationIdentityBuild = ApplicationIdentity.builder().withVersion(this.buildInfoProvider.getAppVersion()).build();
        String deviceId = this.deviceInfoProvider.getDeviceIdentity().getDeviceId();
        String deviceTypeId = this.deviceInfoProvider.getDeviceIdentity().getDeviceTypeId();
        DeviceIdentity deviceIdentityBuild = DeviceIdentity.builder().withId(deviceId).withTypeId(deviceTypeId).withModel(this.deviceInfoProvider.getModel()).withManufacturer(this.deviceInfoProvider.getManufacturer()).withOsVersion(this.deviceInfoProvider.getOsVersion()).withTimeZone(this.deviceInfoProvider.getTimeZone()).withLanguage(this.deviceInfoProvider.getLocales().get(0)).withWidth(Float.toString(this.deviceInfoProvider.getScreenWidth())).withHeight(Float.toString(this.deviceInfoProvider.getScreenHeight())).build();
        String string = UUID.randomUUID().toString();
        logger.debug("requestId {}", string);
        return Identity.builder().withApplication(applicationIdentityBuild).withDevice(deviceIdentityBuild).withUser(userIdentity()).withRequest(RequestIdentity.builder().withTimestamp(System.currentTimeMillis()).withId(string).withSessionId(this.customerMetadataProvider.getCustomerMetadata().getSessionId()).build()).withAccount(Accounts.getAccountIdentity(this.userInfoProvider)).build();
    }

    private UserIdentity userIdentity() {
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authProvider.getOAuthInfoBlocking();
        if (oAuthInfoBlocking instanceof Outcome.Success) {
            return UserIdentity.builder().withAuthentication(((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken()).withCustomerId(this.userInfoProvider.customerId()).build();
        }
        throw new UnsupportedOperationException();
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.amazon.podcast.interaction.InvokeRemoteSkillTask.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builderNewBuilder = UnifiedOkHttpClient.newBuilder();
            if (this.isPodcastCompressRequestsEnabled) {
                builderNewBuilder.addInterceptor(new GzipRequestInterceptor());
            }
            builderNewBuilder.sslSocketFactory(socketFactory, (X509TrustManager) trustManagerArr[0]);
            builderNewBuilder.hostnameVerifier(new HostnameVerifier() { // from class: com.amazon.podcast.interaction.InvokeRemoteSkillTask.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builderNewBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
