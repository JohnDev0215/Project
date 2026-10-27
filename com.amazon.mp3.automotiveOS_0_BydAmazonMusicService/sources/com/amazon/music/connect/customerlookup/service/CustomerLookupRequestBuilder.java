package com.amazon.music.connect.customerlookup.service;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.HttpUrl;
import okhttp3.Request;

/* JADX INFO: compiled from: CustomerLookupRequestBuilder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J,\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/connect/customerlookup/service/CustomerLookupRequestBuilder;", "", "endpoints", "", "", "(Ljava/util/Map;)V", "API_RESULTS_URL", "API_SUGGESTIONS_URL", "AUTH_ERROR", "BLANK_INPUT", "HEADER_AUTH_TOKEN", "HEADER_DEVICE_ID", "HEADER_DEVICE_TYPE", "NO_ENDPOINT", "QUERY_PARAMETER_SEARCH_INPUT", "buildUrl", ImagesContract.URL, "input", "getResultsRequest", "Lokhttp3/Request;", "authenticationToken", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "getSuggestionsRequest", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupRequestBuilder {
    private final String API_RESULTS_URL;
    private final String API_SUGGESTIONS_URL;
    private final String AUTH_ERROR;
    private final String BLANK_INPUT;
    private final String HEADER_AUTH_TOKEN;
    private final String HEADER_DEVICE_ID;
    private final String HEADER_DEVICE_TYPE;
    private final String NO_ENDPOINT;
    private final String QUERY_PARAMETER_SEARCH_INPUT;
    private final Map<String, String> endpoints;

    public CustomerLookupRequestBuilder(Map<String, String> endpoints) {
        Intrinsics.checkNotNullParameter(endpoints, "endpoints");
        this.endpoints = endpoints;
        this.QUERY_PARAMETER_SEARCH_INPUT = SearchIntents.EXTRA_QUERY;
        this.HEADER_AUTH_TOKEN = "x-amz-access-token";
        this.HEADER_DEVICE_ID = "x-amz-device-id";
        this.HEADER_DEVICE_TYPE = "x-amz-device-type";
        this.BLANK_INPUT = "Search bar input must not be blank";
        this.AUTH_ERROR = "Missing required request information";
        this.NO_ENDPOINT = "No Endpoint made for this marketplace";
        this.API_RESULTS_URL = endpoints.get("resultsEndpoint");
        this.API_SUGGESTIONS_URL = endpoints.get("suggestionsEndpoint");
    }

    public final Request getResultsRequest(String input, String authenticationToken, String deviceId, String deviceType) throws Exception {
        Intrinsics.checkNotNullParameter(input, "input");
        if (authenticationToken == null || deviceId == null || deviceType == null) {
            throw new Exception(this.AUTH_ERROR);
        }
        if (StringsKt__StringsJVMKt.isBlank(input)) {
            throw new Exception(this.BLANK_INPUT);
        }
        String str = this.API_RESULTS_URL;
        if (str == null || StringsKt__StringsJVMKt.isBlank(str)) {
            throw new Exception(this.NO_ENDPOINT);
        }
        return new Request.Builder().url(buildUrl(this.API_RESULTS_URL, input)).get().header(this.HEADER_AUTH_TOKEN, authenticationToken).header(this.HEADER_DEVICE_TYPE, deviceType).header(this.HEADER_DEVICE_ID, deviceId).build();
    }

    public final Request getSuggestionsRequest(String input, String authenticationToken, String deviceId, String deviceType) throws Exception {
        Intrinsics.checkNotNullParameter(input, "input");
        if (authenticationToken == null || deviceId == null || deviceType == null) {
            throw new Exception(this.AUTH_ERROR);
        }
        if (StringsKt__StringsJVMKt.isBlank(input)) {
            throw new Exception(this.BLANK_INPUT);
        }
        String str = this.API_SUGGESTIONS_URL;
        if (str == null || StringsKt__StringsJVMKt.isBlank(str)) {
            throw new Exception(this.NO_ENDPOINT);
        }
        return new Request.Builder().url(buildUrl(this.API_SUGGESTIONS_URL, input)).get().header(this.HEADER_AUTH_TOKEN, authenticationToken).header(this.HEADER_DEVICE_TYPE, deviceType).header(this.HEADER_DEVICE_ID, deviceId).build();
    }

    private final String buildUrl(String url, String input) {
        HttpUrl httpUrl = HttpUrl.INSTANCE.parse(url);
        HttpUrl.Builder builderNewBuilder = httpUrl == null ? null : httpUrl.newBuilder();
        Intrinsics.checkNotNull(builderNewBuilder);
        builderNewBuilder.addQueryParameter(this.QUERY_PARAMETER_SEARCH_INPUT, input);
        return builderNewBuilder.build().getUrl();
    }
}
