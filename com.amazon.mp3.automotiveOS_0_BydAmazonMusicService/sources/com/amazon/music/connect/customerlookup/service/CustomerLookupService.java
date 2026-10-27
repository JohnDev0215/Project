package com.amazon.music.connect.customerlookup.service;

import com.amazon.music.connect.IdentityProvider;
import com.amazon.music.connect.customerlookup.CustomerLookupResult;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\u0006\u0010\u0018\u001a\u00020\u0006J\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0015j\b\u0012\u0004\u0012\u00020\u0006`\u00172\u0006\u0010\u0018\u001a\u00020\u0006J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/connect/customerlookup/service/CustomerLookupService;", "", "identityProvider", "Lcom/amazon/music/connect/IdentityProvider;", "endpoints", "", "", "(Lcom/amazon/music/connect/IdentityProvider;Ljava/util/Map;)V", "AUTH_ERROR", "RESPONSE_ERROR", "RESULTS_API_ERROR", "SUGGESTIONS_API_ERROR", "TAG", "httpClient", "Lokhttp3/OkHttpClient;", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "requestBuilder", "Lcom/amazon/music/connect/customerlookup/service/CustomerLookupRequestBuilder;", "getCustomerResults", "Ljava/util/ArrayList;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "Lkotlin/collections/ArrayList;", "input", "getCustomerSuggestions", "getResultsRequest", "Lokhttp3/Request;", "getSuggestionRequest", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupService {
    private final String AUTH_ERROR;
    private final String RESPONSE_ERROR;
    private final String RESULTS_API_ERROR;
    private final String SUGGESTIONS_API_ERROR;
    private final String TAG;
    private final Map<String, String> endpoints;
    private final OkHttpClient httpClient;
    private final IdentityProvider identityProvider;
    private final Logger logger;
    private final CustomerLookupRequestBuilder requestBuilder;

    public CustomerLookupService(IdentityProvider identityProvider, Map<String, String> endpoints) {
        Intrinsics.checkNotNullParameter(identityProvider, "identityProvider");
        Intrinsics.checkNotNullParameter(endpoints, "endpoints");
        this.identityProvider = identityProvider;
        this.endpoints = endpoints;
        String simpleName = Reflection.getOrCreateKotlinClass(CustomerLookupService.class).getSimpleName();
        this.TAG = simpleName;
        this.SUGGESTIONS_API_ERROR = "Customer Suggestions API Call Failed";
        this.RESULTS_API_ERROR = "Customer Results API Call Failed";
        this.RESPONSE_ERROR = "No Response Received";
        this.AUTH_ERROR = "Missing required information to build request";
        this.httpClient = UnifiedOkHttpClient.newClient();
        this.logger = LoggerFactory.getLogger(simpleName);
        this.requestBuilder = new CustomerLookupRequestBuilder(endpoints);
    }

    public final ArrayList<CustomerLookupResult> getCustomerResults(String input) {
        String strString;
        Intrinsics.checkNotNullParameter(input, "input");
        try {
            try {
                ResponseBody responseBodyBody = FirebasePerfOkHttpClient.execute(this.httpClient.newCall(getResultsRequest(input))).body();
                strString = responseBodyBody == null ? null : responseBodyBody.string();
            } catch (IOException e) {
                this.logger.error(this.RESULTS_API_ERROR, (Throwable) e);
                e.printStackTrace();
                strString = "";
            }
            if (strString == null || strString.length() == 0) {
                this.logger.error(this.RESPONSE_ERROR);
                return new ArrayList<>();
            }
            return new CustomerLookupServiceResponse(strString).getLookupResults();
        } catch (Exception unused) {
            this.logger.error(this.AUTH_ERROR);
            return new ArrayList<>();
        }
    }

    public final ArrayList<String> getCustomerSuggestions(String input) {
        String strString;
        Intrinsics.checkNotNullParameter(input, "input");
        try {
            try {
                ResponseBody responseBodyBody = FirebasePerfOkHttpClient.execute(this.httpClient.newCall(getSuggestionRequest(input))).body();
                strString = responseBodyBody == null ? null : responseBodyBody.string();
            } catch (IOException e) {
                this.logger.error(this.SUGGESTIONS_API_ERROR, (Throwable) e);
                e.printStackTrace();
                strString = "";
            }
            if (strString == null || strString.length() == 0) {
                this.logger.error(this.RESPONSE_ERROR);
                return new ArrayList<>();
            }
            return new CustomerLookupServiceResponse(strString).getSuggestionResults();
        } catch (Exception unused) {
            this.logger.error(this.AUTH_ERROR);
            return new ArrayList<>();
        }
    }

    private final Request getResultsRequest(String input) {
        return this.requestBuilder.getResultsRequest(input, this.identityProvider.getToken(), this.identityProvider.getDeviceId(), this.identityProvider.getDeviceType());
    }

    private final Request getSuggestionRequest(String input) {
        return this.requestBuilder.getSuggestionsRequest(input, this.identityProvider.getToken(), this.identityProvider.getDeviceId(), this.identityProvider.getDeviceType());
    }
}
