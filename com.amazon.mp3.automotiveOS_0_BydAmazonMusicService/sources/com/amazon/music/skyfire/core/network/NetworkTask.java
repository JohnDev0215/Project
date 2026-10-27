package com.amazon.music.skyfire.core.network;

import CoreInterface.v1_0.ClientInformation;
import CoreInterface.v1_0.Response;
import com.amazon.music.find.skyfire.providers.FindNetworkInterceptorProvider;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.utils.SkillPathUtils;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazonaws.http.HttpHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class NetworkTask {
    private final Authentication authentication;
    private final BuildInfo buildInfo;
    private final CapabilitiesInfo capabilitiesInfo;
    private final Map<String, ClientInformation> clientInformationMap;
    private final DeviceInfo deviceInfo;
    private final HttpSkillInfo httpSkillInfo;
    private final NetworkInterceptor networkInterceptor;
    private final SkillLatencyTracker skillLatencyTracker;
    private final String skillPath;
    private final String url;
    private static final Logger logger = LoggerFactory.getLogger("NetworkTask");
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient okHttpClient = UnifiedOkHttpClient.newClient();

    public NetworkTask(String str, Map<String, ClientInformation> map, Authentication authentication, DeviceInfo deviceInfo, BuildInfo buildInfo, HttpSkillInfo httpSkillInfo, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker) {
        this.url = str;
        this.skillPath = SkillPathUtils.getSkillPath(str);
        this.clientInformationMap = map;
        this.authentication = authentication;
        this.deviceInfo = deviceInfo;
        this.buildInfo = buildInfo;
        this.httpSkillInfo = httpSkillInfo;
        this.capabilitiesInfo = capabilitiesInfo;
        this.networkInterceptor = networkInterceptor;
        this.skillLatencyTracker = skillLatencyTracker;
    }

    public Response execute() {
        try {
            Headers headersBuildHeaders = buildHeaders();
            HttpUrl httpUrlBuildUrlComponents = buildUrlComponents();
            if (this.buildInfo.isDebug()) {
                logger.debug("Making network request to: {}", httpUrlBuildUrlComponents.uri().toString());
            }
            HttpUrl.Builder builderNewBuilder = httpUrlBuildUrlComponents.newBuilder();
            JSONObject jSONObject = new JSONObject();
            int iQuerySize = httpUrlBuildUrlComponents.querySize();
            for (int i = 0; i < iQuerySize; i++) {
                jSONObject.put(httpUrlBuildUrlComponents.queryParameterName(i), httpUrlBuildUrlComponents.queryParameterValue(i));
                builderNewBuilder.removeAllQueryParameters(httpUrlBuildUrlComponents.queryParameterName(i));
            }
            Request.Builder builderUrl = new Request.Builder().headers(headersBuildHeaders).post(RequestBody.create(jSONObject.toString(), JSON)).url(builderNewBuilder.build().uri().toString());
            ValidationResult validationResultValidateRequest = this.networkInterceptor.validateRequest(this.skillPath, headersBuildHeaders);
            if (!validationResultValidateRequest.isSuccess()) {
                throw validationResultValidateRequest.getException();
            }
            this.skillLatencyTracker.response(this.skillPath);
            okhttp3.Response responseExecute = FirebasePerfOkHttpClient.execute(okHttpClient.newCall(builderUrl.build()));
            try {
                this.skillLatencyTracker.process(this.skillPath);
                if (responseExecute.code() == 200) {
                    ResponseBody responseBodyBody = responseExecute.body();
                    if (responseBodyBody != null) {
                        String strString = responseBodyBody.string();
                        responseBodyBody.close();
                        return (Response) Mappers.mapper().readValue(strString, Response.class);
                    }
                } else if (responseExecute.code() >= 500 && responseExecute.code() <= 599) {
                    this.networkInterceptor.onError(this.skillPath, NetworkTaskError.SERVER_ERROR);
                }
                responseExecute.close();
                return null;
            } finally {
                responseExecute.close();
            }
        } catch (Exception e) {
            logger.error("Error executing https call.", (Throwable) e);
            if (e instanceof SocketTimeoutException) {
                this.networkInterceptor.onError(this.skillPath, NetworkTaskError.REQUEST_TIMEOUT);
                return null;
            }
            if (e instanceof ConnectException) {
                this.networkInterceptor.onError(this.skillPath, NetworkTaskError.INVALID_URL);
                return null;
            }
            if (e instanceof JsonProcessingException) {
                this.networkInterceptor.onError(this.skillPath, NetworkTaskError.DECODING_ERROR);
                return null;
            }
            if (e instanceof AuthenticationMissingException) {
                this.networkInterceptor.onError(this.skillPath, NetworkTaskError.AUTHENTICATION_ERROR);
                return null;
            }
            this.networkInterceptor.onError(this.skillPath, NetworkTaskError.SERVER_ERROR);
            return null;
        }
    }

    private HttpUrl buildUrlComponents() throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        int iIndexOf = this.url.indexOf("?");
        if (iIndexOf == -1) {
            sb.append(this.url);
        } else {
            int iIndexOf2 = this.url.indexOf("#");
            if (iIndexOf2 == -1) {
                iIndexOf2 = this.url.length();
            }
            sb.append((CharSequence) this.url, 0, iIndexOf);
            sb.append(this.url.substring(iIndexOf, iIndexOf2).replaceAll("\\+", "%20"));
            sb.append(this.url.substring(iIndexOf2));
        }
        HttpUrl httpUrl = HttpUrl.parse(sb.toString());
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        HttpSkillInfo httpSkillInfo = this.httpSkillInfo;
        if (httpSkillInfo != null && httpSkillInfo.url() != null) {
            HttpUrl httpUrl2 = HttpUrl.parse(this.httpSkillInfo.url());
            builderNewBuilder.scheme(httpUrl2.scheme()).host(httpUrl2.host()).port(httpUrl2.port());
        } else if (httpUrl.host().equals("localhost")) {
            builderNewBuilder.host("10.0.2.2");
        }
        Map<String, ClientInformation> map = this.clientInformationMap;
        if (map != null) {
            for (Map.Entry<String, ClientInformation> entry : map.entrySet()) {
                builderNewBuilder.addQueryParameter(entry.getKey(), Mappers.mapper().writeValueAsString(entry.getValue()));
            }
        }
        return builderNewBuilder.build();
    }

    private Headers buildHeaders() {
        List<String> listFeatureFlags;
        Headers.Builder builderAdd = new Headers.Builder().add("x-amzn-device-id", this.deviceInfo.id()).add("x-amzn-device-family", this.deviceInfo.family()).add("x-amzn-device-manufacturer", this.deviceInfo.manufacturer()).add("x-amzn-device-model", this.deviceInfo.model()).add("x-amzn-device-language", this.deviceInfo.locale()).add("x-amzn-device-height", this.deviceInfo.screenHeight()).add("x-amzn-device-width", this.deviceInfo.screenWidth()).add("x-amzn-device-scale", this.deviceInfo.screenScale()).add("x-amzn-application-version", this.buildInfo.versionName()).add("x-amzn-os-version", this.buildInfo.osVersion()).add("x-amzn-device-time-zone", this.deviceInfo.timeZone()).add("x-amzn-timestamp", String.valueOf(System.currentTimeMillis())).add("x-amzn-user-agent", this.deviceInfo.getUserAgent()).add(HttpHeader.CONTENT_TYPE, "application/json");
        String strDeviceTypeId = this.deviceInfo.deviceTypeId();
        if (StringUtils.isNotEmpty(strDeviceTypeId)) {
            builderAdd.add("x-amzn-device-type-id", strDeviceTypeId);
        }
        builderAdd.add("x-amzn-request-id", UUID.randomUUID().toString());
        String strPlaybackAuthentication = this.authentication.playbackAuthentication();
        if (StringUtils.isNotEmpty(strPlaybackAuthentication)) {
            builderAdd.add(FindNetworkInterceptorProvider.AUTHENTICATION_HEADER, strPlaybackAuthentication);
        }
        String videoAuthenticationHeader = this.authentication.getVideoAuthenticationHeader();
        if (StringUtils.isNotEmpty(videoAuthenticationHeader)) {
            builderAdd.add("x-amzn-video-player-token", videoAuthenticationHeader);
        }
        String sessionId = this.authentication.getSessionId();
        if (StringUtils.isNotEmpty(sessionId)) {
            builderAdd.add("x-amzn-session-id", sessionId);
        }
        CapabilitiesInfo capabilitiesInfo = this.capabilitiesInfo;
        if (capabilitiesInfo != null && (listFeatureFlags = capabilitiesInfo.featureFlags()) != null && !listFeatureFlags.isEmpty()) {
            builderAdd.add("x-amzn-feature-flags", StringUtils.join(listFeatureFlags, ","));
        }
        return builderAdd.build();
    }
}
