package com.amazon.music.curate.skyfire.network;

import ClientAuthenticationInterface.v1_0.ImmutableClientTokenElement;
import CoreInterface.v1_0.Response;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.skyfire.Mappers;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.music.find.skyfire.providers.FindNetworkInterceptorProvider;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.CustomerMetadata;
import com.amazon.music.platform.data.DeviceIdentity;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.util.List;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class InvokeHttpSkillTask {
    private static final String TAG = "InvokeHttpSkillTask";
    private static final Logger logger = LoggerFactory.getLogger(InvokeHttpSkillTask.class.getSimpleName());
    private static final OkHttpClient okHttpClient = UnifiedOkHttpClient.newClient();
    private final AppDatabase appDatabase;
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final FeatureFlagProvider featureFlagProvider;
    private final String url;

    public InvokeHttpSkillTask(String url, AppDatabase appDatabase, BuildInfoProvider buildInfoProvider, DeviceInfoProvider deviceInfoProvider, FeatureFlagProvider featureFlagProvider, AuthenticationProvider authProvider, CustomerMetadataProvider customerMetadataProvider) {
        this.url = url;
        this.appDatabase = appDatabase;
        this.buildInfoProvider = buildInfoProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.authProvider = authProvider;
        this.customerMetadataProvider = customerMetadataProvider;
    }

    public Response execute() {
        try {
            Headers headersBuildHeaders = buildHeaders();
            String string = HttpUrl.parse(this.url).newBuilder().build().uri().toString();
            String str = headersBuildHeaders.get("x-amzn-request-id");
            if (this.buildInfoProvider.getIsDebug()) {
                logger.debug("Making network request to: {}, requestId = {}", string, str);
            }
            Request.Builder builderUrl = new Request.Builder().headers(headersBuildHeaders).url(string);
            long jCurrentTimeMillis = System.currentTimeMillis();
            FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_REQUEST, "requestUrl = " + string, "requestId = " + str);
            okhttp3.Response responseExecute = FirebasePerfOkHttpClient.execute(okHttpClient.newCall(builderUrl.build()));
            if (responseExecute.code() == 200) {
                ResponseBody responseBodyBody = responseExecute.body();
                if (responseBodyBody != null) {
                    String strString = responseBodyBody.string();
                    responseBodyBody.close();
                    Response response = (Response) Mappers.mapper().readValue(strString, Response.class);
                    if (response.methods() != null && !response.methods().isEmpty()) {
                        FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_SUCCESS, "requestUrl = " + string + ", requestId = " + str, null, Float.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                        return response;
                    }
                    sendFailureFlexEvent(string, str, responseExecute);
                    logger.error("Empty convertedResponse for {} , headers = {}", string, headersBuildHeaders.toString());
                } else {
                    sendFailureFlexEvent(string, str, responseExecute);
                    logger.error("Empty responseBody from Skyfire for {} , headers = {}", string, headersBuildHeaders.toString());
                }
            } else {
                sendFailureFlexEvent(string, str, responseExecute);
                logger.error("Error response from Skyfire for {} , headers = {}", string, headersBuildHeaders.toString());
            }
        } catch (Exception e) {
            FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_FAILURE, "exception = " + e.getMessage(), "");
            logger.error("Curate Skyfire skill call failed", (Throwable) e);
        }
        return null;
    }

    private void sendFailureFlexEvent(final String requestUrl, final String requestId, final okhttp3.Response response) {
        AppDatabase appDatabase = this.appDatabase;
        boolean zIsContextMappingEmpty = appDatabase == null ? true : SyncScheduler.isContextMappingEmpty(appDatabase);
        FlexEventMetrics.sendEvent(zIsContextMappingEmpty ? FlexEventMetrics.CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_FIRST_FAILURE : FlexEventMetrics.CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_FAILURE, "requestUrl = " + requestUrl + ", requestId = " + requestId, "response code = " + response.code() + "response message = " + response.message());
        logger.error("Received empty response from Skyfire for {} , requestId = {}, isInitialState = {}", requestUrl, requestId, Boolean.valueOf(zIsContextMappingEmpty));
    }

    private Headers buildHeaders() {
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        DeviceIdentity deviceIdentity = deviceInfoProvider.getDeviceIdentity();
        Headers.Builder builderAdd = new Headers.Builder().add("x-amzn-device-id", deviceIdentity.getDeviceId()).add("x-amzn-device-type-id", deviceIdentity.getDeviceTypeId()).add("x-amzn-device-family", "MobileAndroid").add("x-amzn-device-manufacturer", deviceInfoProvider.getManufacturer()).add("x-amzn-device-model", deviceInfoProvider.getModel()).add("x-amzn-device-language", deviceInfoProvider.getLocales().get(0)).add("x-amzn-device-height", String.valueOf(deviceInfoProvider.getScreenHeight())).add("x-amzn-device-width", String.valueOf(deviceInfoProvider.getScreenWidth())).add("x-amzn-application-version", this.buildInfoProvider.getAppVersion()).add("x-amzn-os-version", deviceInfoProvider.getOsVersion()).add("x-amzn-device-time-zone", deviceInfoProvider.getTimeZone()).add("x-amzn-timestamp", String.valueOf(System.currentTimeMillis())).add("x-amzn-user-agent", deviceInfoProvider.getUserAgent());
        builderAdd.add("x-amzn-request-id", UUID.randomUUID().toString());
        String oAuthInfoBlocking = getOAuthInfoBlocking();
        if (StringUtils.isNotBlank(oAuthInfoBlocking)) {
            builderAdd.add(FindNetworkInterceptorProvider.AUTHENTICATION_HEADER, oAuthInfoBlocking);
        }
        String sessionId = getSessionId();
        if (StringUtils.isNotBlank(sessionId)) {
            builderAdd.add("x-amzn-session-id", sessionId);
        }
        List<String> listFeatureFlags = this.featureFlagProvider.featureFlags();
        if (listFeatureFlags != null && !listFeatureFlags.isEmpty()) {
            builderAdd.add("x-amzn-feature-flags", StringUtils.join(listFeatureFlags, ","));
        }
        return builderAdd.build();
    }

    private String getOAuthInfoBlocking() {
        Outcome<OAuthInfo> oAuthInfoBlocking = this.authProvider.getOAuthInfoBlocking();
        if (!(oAuthInfoBlocking instanceof Outcome.Success)) {
            return "";
        }
        String accessToken = ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        try {
            return Mappers.mapper().writeValueAsString(ImmutableClientTokenElement.builder().accessToken(accessToken).build());
        } catch (JsonProcessingException e) {
            logger.error("Error building client token", (Throwable) e);
            return "";
        }
    }

    private String getSessionId() {
        CustomerMetadata customerMetadata = this.customerMetadataProvider.getCustomerMetadata();
        if (customerMetadata != null) {
            return customerMetadata.getSessionId();
        }
        return null;
    }
}
