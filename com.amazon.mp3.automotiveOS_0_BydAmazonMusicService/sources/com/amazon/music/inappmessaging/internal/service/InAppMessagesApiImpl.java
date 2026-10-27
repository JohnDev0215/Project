package com.amazon.music.inappmessaging.internal.service;

import android.content.res.Resources;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageRequest;
import com.amazon.music.inappmessaging.external.model.HandleActionRequest;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.external.model.WeblabTriggerRequest;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.model.DynamicMessageResponse;
import com.amazon.music.inappmessaging.internal.model.HandleActionResponse;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.inappmessaging.internal.util.Timer;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.exceptions.Exceptions;

/* JADX INFO: loaded from: classes4.dex */
public class InAppMessagesApiImpl implements InAppMessagesApi {
    static final String HEADER_ACCESS_TOKEN = "x-amz-access-token";
    static final String HEADER_APP_VERSION = "x-amz-app-version";
    static final String HEADER_DEVICE_BRAND = "x-amz-device-brand";
    static final String HEADER_DEVICE_ID = "x-amz-device-id";
    static final String HEADER_DEVICE_LOCALE = "x-amz-deviceLocale";
    static final String HEADER_DEVICE_MODEL = "x-amz-device-model";
    static final String HEADER_DEVICE_SCREEN_SIZE = "x-amz-screen-size";
    static final String HEADER_DEVICE_SCREEN_TYPE = "x-amz-retina-screen";
    static final String HEADER_DEVICE_TYPE = "x-amz-device-type";
    static final String HEADER_DIRECTED_ID = "x-amz-directed-id";
    static final String HEADER_MUSIC_TERRITORY = "x-amz-music-territory";
    static final String HEADER_SESSION_ID = "x-amz-session-id";
    static final String HEADER_TELECOM_CARRIER = "x-amz-telecom-carrier";
    static final String HEADER_USER_AGENT = "User-Agent";
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) InAppMessagesApiImpl.class);
    private static final String MEDIA_TYPE_APP_JSON = "application/json";
    private static final int MIN_RETINA_DENSITY = 2;
    private static final String TIMEOUT = "timeout";
    private final OkHttpClient dynMsgClient;
    private final String dynMsgEndpointUrl;
    private final String endpointUrl;
    private final OkHttpClient handleActionClient;
    private final String handleActionUrl;
    private final OkHttpClient weblabTriggerClient;
    private final String weblabTriggerUrl;
    private final Gson gson = new Gson();
    private final OkHttpClient client = UnifiedOkHttpClient.newClient();

    public InAppMessagesApiImpl(final String url, final String dynMsgUrl, final String weblabTriggerUrl, final String handleActionUrl) {
        OkHttpClient.Builder builderNewBuilder = UnifiedOkHttpClient.newBuilder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.dynMsgClient = builderNewBuilder.readTimeout(30L, timeUnit).build();
        this.weblabTriggerClient = UnifiedOkHttpClient.newClient();
        this.handleActionClient = UnifiedOkHttpClient.newBuilder().readTimeout(60L, timeUnit).build();
        this.endpointUrl = url;
        this.dynMsgEndpointUrl = dynMsgUrl;
        this.weblabTriggerUrl = weblabTriggerUrl;
        this.handleActionUrl = handleActionUrl;
    }

    RequestProvider createIAMAPIRequestProvider(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final Map<String, String> queryParams) {
        return createIAMAPIRequestProvider(iamCredentials, deviceInformation, this.endpointUrl, queryParams);
    }

    RequestProvider createIAMAPIRequestProvider(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final String url, final Map<String, String> queryParams) {
        return new RequestProvider() { // from class: com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl$$ExternalSyntheticLambda0
            @Override // com.amazon.music.inappmessaging.internal.service.RequestProvider
            public final Request getRequest() {
                return this.f$0.lambda$createIAMAPIRequestProvider$0(url, queryParams, iamCredentials, deviceInformation);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Request lambda$createIAMAPIRequestProvider$0(String str, Map map, IAMCredentials iAMCredentials, DeviceInformation deviceInformation) {
        HttpUrl.Builder builderNewBuilder = HttpUrl.parse(str).newBuilder();
        for (Map.Entry entry : map.entrySet()) {
            builderNewBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String str2 = (String) Validate.notBlank(iAMCredentials.getTokenProvider() == null ? null : iAMCredentials.getTokenProvider().getToken(), "Could not retrieve auth token", new Object[0]);
        Headers.Builder builder = new Headers.Builder();
        builder.add(HEADER_ACCESS_TOKEN, str2).add(HEADER_DEVICE_ID, iAMCredentials.getDeviceId()).add(HEADER_DEVICE_TYPE, iAMCredentials.getDeviceType()).add(HEADER_DIRECTED_ID, iAMCredentials.getDirectedId()).add(HEADER_SESSION_ID, iAMCredentials.getSessionId()).add(HEADER_MUSIC_TERRITORY, iAMCredentials.getMusicTerritory()).add(HEADER_APP_VERSION, iAMCredentials.getAppVersion()).add("User-Agent", iAMCredentials.getUserAgent());
        addValidDeviceHeaders(builder, deviceInformation);
        return new Request.Builder().url(builderNewBuilder.build()).headers(builder.build()).build();
    }

    @Override // com.amazon.music.inappmessaging.internal.service.InAppMessagesApi
    public String getInAppBatchJson(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final String batchURL) {
        try {
            return FirebasePerfOkHttpClient.execute(this.client.newCall(createIAMAPIRequestProvider(iamCredentials, deviceInformation, batchURL, new HashMap()).getRequest())).body().string();
        } catch (Exception e) {
            LOG.error("Error communicating with IAM API", (Throwable) e);
            throw Exceptions.propagate(e);
        }
    }

    @Override // com.amazon.music.inappmessaging.internal.service.InAppMessagesApi
    public Observable<InAppMessageListJson> getInAppMessages(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final IAMRequest request) {
        return Observable.fromCallable(new Callable<InAppMessageListJson>() { // from class: com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public InAppMessageListJson call() throws Exception {
                HashMap map = new HashMap();
                for (Map.Entry<String, String> entry : request.filters().entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
                try {
                    Response responseExecute = FirebasePerfOkHttpClient.execute(InAppMessagesApiImpl.this.client.newCall(InAppMessagesApiImpl.this.createIAMAPIRequestProvider(iamCredentials, deviceInformation, map).getRequest()));
                    String strString = responseExecute.body().string();
                    if (responseExecute.isSuccessful()) {
                        return InAppMessagesApiImpl.this.validateApiResponse((InAppMessageListJson) InAppMessagesApiImpl.this.gson.fromJson(strString, InAppMessageListJson.class));
                    }
                    InAppMessagesApiImpl.LOG.error("IAM API threw an exception with {} status code", Integer.valueOf(responseExecute.code()));
                    throw new Exception("Error in IAM API call: " + responseExecute.code() + " " + strString);
                } catch (Exception e) {
                    InAppMessagesApiImpl.LOG.error("Error communicating with IAM API", (Throwable) e);
                    throw Exceptions.propagate(e);
                }
            }
        });
    }

    InAppMessageListJson validateApiResponse(final InAppMessageListJson inAppMessageListJson) {
        Validate.notNull(inAppMessageListJson, "Could not parse API response: response was empty", new Object[0]);
        Validate.notNull(inAppMessageListJson.inAppMessages, "Could not parse API response: inAppMessages list was null", new Object[0]);
        for (InAppMessageJson inAppMessageJson : inAppMessageListJson.inAppMessages) {
            Validate.notNull(inAppMessageJson.type, "Could not parse API response: type was null", new Object[0]);
            Validate.notNull(inAppMessageJson.trigger, "Could not parse API response: trigger was null", new Object[0]);
            Validate.notNull(inAppMessageJson.campaignId, "Could not parse API response: campaignId was null", new Object[0]);
            if (Splash.NAME.equals(inAppMessageJson.type)) {
                Validate.notNull(inAppMessageJson.splash, "Could not parse API response: splash object was null", new Object[0]);
                Validate.notNull(inAppMessageJson.splash.url, "Could not parse API response: url for splash was null", new Object[0]);
            }
        }
        return inAppMessageListJson;
    }

    @Override // com.amazon.music.inappmessaging.internal.service.InAppMessagesApi
    public Observable<DynamicMessageResponse> getDynamicMessages(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final DynamicMessageRequest request) {
        return Observable.fromCallable(new Callable<DynamicMessageResponse>() { // from class: com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public DynamicMessageResponse call() throws Exception {
                DynamicMessagingErrorCode dynamicMessagingErrorCode;
                Headers headersCreateHeadersForDynamicMessagingAPI = InAppMessagesApiImpl.this.createHeadersForDynamicMessagingAPI(iamCredentials, deviceInformation);
                Timer timer = new Timer("latency");
                timer.start();
                try {
                    try {
                        Request requestBuild = new Request.Builder().url(HttpUrl.parse(InAppMessagesApiImpl.this.dynMsgEndpointUrl).newBuilder().build()).post(RequestBody.create(MediaType.parse(InAppMessagesApiImpl.MEDIA_TYPE_APP_JSON), request.toJson())).headers(headersCreateHeadersForDynamicMessagingAPI).build();
                        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGES_REQUESTED).send();
                        Response responseExecute = FirebasePerfOkHttpClient.execute(InAppMessagesApiImpl.this.dynMsgClient.newCall(requestBuild));
                        String strString = responseExecute.body().string();
                        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGES_RECEIVED).send();
                        if (responseExecute.isSuccessful()) {
                            return InAppMessagesApiImpl.this.validateDynMsgResponse((DynamicMessageResponse) InAppMessagesApiImpl.this.gson.fromJson(strString, DynamicMessageResponse.class));
                        }
                        InAppMessagesApiImpl.LOG.error("Dynamic Message API threw an exception with {} status code", Integer.valueOf(responseExecute.code()));
                        throw new Exception("Error in DynMsg API call: " + responseExecute.code() + " " + strString);
                    } catch (Exception e) {
                        if (e.getMessage().contains(InAppMessagesApiImpl.TIMEOUT)) {
                            dynamicMessagingErrorCode = DynamicMessagingErrorCode.SERVER_TIMEOUT;
                        } else {
                            dynamicMessagingErrorCode = DynamicMessagingErrorCode.SERVICE_ERROR;
                        }
                        String str = "";
                        StringBuilder sb = new StringBuilder();
                        for (String str2 : request.getTriggers()) {
                            sb.append(str);
                            sb.append(str2);
                            str = ",";
                        }
                        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(dynamicMessagingErrorCode).withErrorMessage(e.getMessage()).withTrigger(sb.toString()).send();
                        InAppMessagesApiImpl.LOG.error("Error communicating with Dynamic Message API", (Throwable) e);
                        throw Exceptions.propagate(e);
                    }
                } finally {
                    DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_API_LATENCY).withApiLatencyMillis(Long.valueOf(timer.logInterval("responseReceived").intervalTime)).send();
                }
            }
        });
    }

    @Override // com.amazon.music.inappmessaging.internal.service.InAppMessagesApi
    public void recordWeblabTriggers(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final WeblabTriggerRequest request, final DynamicMessageEvent event) {
        Headers headersCreateHeadersForDynamicMessagingAPI = createHeadersForDynamicMessagingAPI(iamCredentials, deviceInformation);
        try {
            Response responseExecute = FirebasePerfOkHttpClient.execute(this.weblabTriggerClient.newCall(new Request.Builder().url(HttpUrl.parse(this.weblabTriggerUrl).newBuilder().build()).headers(headersCreateHeadersForDynamicMessagingAPI).post(RequestBody.create(MediaType.parse(MEDIA_TYPE_APP_JSON), request.toJson())).build()));
            String strString = responseExecute.body().string();
            if (responseExecute.isSuccessful()) {
                return;
            }
            LOG.error("Weblab Record Trigger API threw an exception with {} status code", Integer.valueOf(responseExecute.code()));
            throw new Exception("Error in recordWeblabTrigger API call: " + responseExecute.code() + " " + strString);
        } catch (Exception e) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.SERVICE_ERROR).withErrorMessage("Error communicating with Record Weblab Trigger API " + e.getMessage()).withEvent(event.getEvent()).send();
            throw Exceptions.propagate(e);
        }
    }

    @Override // com.amazon.music.inappmessaging.internal.service.InAppMessagesApi
    public Observable<HandleActionResponse> handleAction(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation, final HandleActionRequest request) {
        return Observable.fromCallable(new Callable<HandleActionResponse>() { // from class: com.amazon.music.inappmessaging.internal.service.InAppMessagesApiImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public HandleActionResponse call() throws Exception {
                Headers headersCreateHeadersForHandleActionAPI = InAppMessagesApiImpl.this.createHeadersForHandleActionAPI(iamCredentials, deviceInformation);
                try {
                    Response responseExecute = FirebasePerfOkHttpClient.execute(InAppMessagesApiImpl.this.handleActionClient.newCall(new Request.Builder().url(HttpUrl.parse(InAppMessagesApiImpl.this.handleActionUrl).newBuilder().build()).post(RequestBody.create(MediaType.parse(InAppMessagesApiImpl.MEDIA_TYPE_APP_JSON), request.toJson())).headers(headersCreateHeadersForHandleActionAPI).build()));
                    String strString = responseExecute.body().string();
                    if (responseExecute.isSuccessful()) {
                        return InAppMessagesApiImpl.this.validateHandleActionResponse((HandleActionResponse) InAppMessagesApiImpl.this.gson.fromJson(strString, HandleActionResponse.class));
                    }
                    InAppMessagesApiImpl.LOG.error("Action Service API threw an exception with {} status code", Integer.valueOf(responseExecute.code()));
                    throw new Exception("Error in Action Service API call: " + responseExecute.code() + " " + strString);
                } catch (Exception e) {
                    InAppMessagesApiImpl.LOG.error("Error communicating with Action Service API: ", (Throwable) e);
                    throw Exceptions.propagate(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Headers createHeadersForDynamicMessagingAPI(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
        Headers.Builder builder = new Headers.Builder();
        builder.add(HEADER_ACCESS_TOKEN, (String) Validate.notBlank(iamCredentials.getTokenProvider() == null ? null : iamCredentials.getTokenProvider().getToken(), "Could not retrieve auth token", new Object[0])).add(HEADER_DEVICE_ID, iamCredentials.getDeviceId()).add(HEADER_DEVICE_TYPE, iamCredentials.getDeviceType()).add(HEADER_DIRECTED_ID, iamCredentials.getDirectedId()).add(HEADER_SESSION_ID, iamCredentials.getSessionId()).add(HEADER_APP_VERSION, iamCredentials.getAppVersion()).add("User-Agent", iamCredentials.getUserAgent()).add(HEADER_DEVICE_SCREEN_TYPE, String.valueOf(Resources.getSystem().getDisplayMetrics().density >= 2.0f)).add(HEADER_DEVICE_SCREEN_SIZE, getScreenSizeHeader());
        addValidDeviceHeaders(builder, deviceInformation);
        return builder.build();
    }

    public static String getScreenSizeHeader() {
        StringBuilder sb = new StringBuilder();
        float f = Resources.getSystem().getDisplayMetrics().density;
        sb.append(Resources.getSystem().getDisplayMetrics().heightPixels / f);
        sb.append(":");
        sb.append(Resources.getSystem().getDisplayMetrics().widthPixels / f);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Headers createHeadersForHandleActionAPI(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
        Headers.Builder builder = new Headers.Builder();
        builder.add(HEADER_ACCESS_TOKEN, (String) Validate.notBlank(iamCredentials.getTokenProvider() == null ? null : iamCredentials.getTokenProvider().getToken(), "Could not retrieve auth token", new Object[0])).add(HEADER_DEVICE_ID, iamCredentials.getDeviceId()).add(HEADER_DEVICE_TYPE, iamCredentials.getDeviceType()).add(HEADER_DIRECTED_ID, iamCredentials.getDirectedId()).add(HEADER_SESSION_ID, iamCredentials.getSessionId()).add(HEADER_APP_VERSION, iamCredentials.getAppVersion()).add("User-Agent", iamCredentials.getUserAgent()).add(HEADER_MUSIC_TERRITORY, iamCredentials.getMusicTerritory());
        addValidDeviceHeaders(builder, deviceInformation);
        return builder.build();
    }

    HandleActionResponse validateHandleActionResponse(final HandleActionResponse response) throws NullPointerException {
        Validate.notNull(response, "Could not parse handle action response: response was null", new Object[0]);
        DynamicMessage dynamicMessage = response.nextMessage;
        if (dynamicMessage != null) {
            Validate.notNull(dynamicMessage.messageRef, "Could not parse handle action response: message ref was null", new Object[0]);
            Validate.notNull(response.nextMessage.templateId, "Could not parse handle action response: templateId was null", new Object[0]);
            Validate.notNull(response.nextMessage.templateAttributes, "Could not parse handle action response: templateAttributes was null", new Object[0]);
            Validate.notNull(response.nextMessage.weblabsToTrigger, "Could not parse handle action response: weblabsToTrigger was null", new Object[0]);
        }
        return response;
    }

    DynamicMessageResponse validateDynMsgResponse(final DynamicMessageResponse response) {
        Validate.notNull(response, "Could not parse DynMsgAPI response: response was null", new Object[0]);
        Validate.notNull(response.dynamicMessagesByTrigger, "Could not parse DynMsgAPI response: dynMsg map was null", new Object[0]);
        for (Map.Entry<Trigger, List<DynamicMessage>> entry : response.dynamicMessagesByTrigger.entrySet()) {
            Validate.notNull(entry.getKey(), "Could not parse DynMsgAPI response: trigger was null", new Object[0]);
            Validate.notNull(entry.getValue(), "Could not parse DynMsgAPI response: list of dynMsg was null", new Object[0]);
            for (DynamicMessage dynamicMessage : entry.getValue()) {
                Validate.notNull(dynamicMessage.messageRef, "Could not parse DynMsgAPI response: message ref was null", new Object[0]);
                Validate.notNull(dynamicMessage.templateId, "Could not parse DynMsgAPI response: templateId was null", new Object[0]);
                Validate.notNull(dynamicMessage.templateAttributes, "Could not parse DynMsgAPI response: templateAttributes was null", new Object[0]);
                Validate.notNull(dynamicMessage.weblabsToTrigger, "Could not parse DynMsgAPI response: weblabsToTrigger was null", new Object[0]);
                Validate.notNull(dynamicMessage.cachePolicy, "Could not parse DynMsgAPI response: cachePolicy was null", new Object[0]);
            }
        }
        return response;
    }

    void addValidDeviceHeaders(final Headers.Builder headerBuilder, final DeviceInformation deviceInformation) {
        if (deviceInformation.getDeviceModel() != null) {
            headerBuilder.add(HEADER_DEVICE_MODEL, deviceInformation.getDeviceModel());
        }
        if (deviceInformation.getDeviceBrand() != null) {
            headerBuilder.add(HEADER_DEVICE_BRAND, deviceInformation.getDeviceBrand());
        }
        if (deviceInformation.getTelecomCarrier() != null) {
            headerBuilder.add(HEADER_TELECOM_CARRIER, deviceInformation.getTelecomCarrier());
        }
        if (deviceInformation.getDeviceLocale() != null) {
            headerBuilder.add(HEADER_DEVICE_LOCALE, deviceInformation.getDeviceLocale());
        }
    }
}
