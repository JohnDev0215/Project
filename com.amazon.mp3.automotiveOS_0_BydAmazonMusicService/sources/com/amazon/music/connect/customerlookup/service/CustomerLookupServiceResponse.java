package com.amazon.music.connect.customerlookup.service;

import com.amazon.music.connect.customerlookup.CustomerLookupResult;
import com.google.gson.Gson;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupServiceResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nJ\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\nJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u0006*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/connect/customerlookup/service/CustomerLookupServiceResponse;", "", "rawResponse", "", "(Ljava/lang/String;)V", "TAG", "kotlin.jvm.PlatformType", "customerResults", "Ljava/util/ArrayList;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "Lkotlin/collections/ArrayList;", "customerSuggestions", "gson", "Lcom/google/gson/Gson;", "logger", "Lorg/slf4j/Logger;", "raw", "getLookupResults", "getSuggestionResults", "interpretResultsRawResponse", "", "interpretSuggestionsRawResponse", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupServiceResponse {
    private final String TAG;
    private ArrayList<CustomerLookupResult> customerResults;
    private ArrayList<String> customerSuggestions;
    private final Gson gson;
    private final Logger logger;
    private final String raw;

    public CustomerLookupServiceResponse(String rawResponse) {
        Intrinsics.checkNotNullParameter(rawResponse, "rawResponse");
        String simpleName = CustomerLookupServiceResponse.class.getSimpleName();
        this.TAG = simpleName;
        this.raw = rawResponse;
        this.customerResults = new ArrayList<>();
        this.customerSuggestions = new ArrayList<>();
        this.logger = LoggerFactory.getLogger(simpleName);
        this.gson = new Gson();
    }

    public final ArrayList<CustomerLookupResult> getLookupResults() {
        interpretResultsRawResponse(this.raw);
        return this.customerResults;
    }

    public final ArrayList<String> getSuggestionResults() {
        interpretSuggestionsRawResponse(this.raw);
        return this.customerSuggestions;
    }

    private final void interpretResultsRawResponse(String rawResponse) {
        try {
            Object objFromJson = this.gson.fromJson(new JSONArray(rawResponse).toString(), (Class<Object>) CustomerLookupResult[].class);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "gson.fromJson(responseOb…ookupResult>::class.java)");
            CustomerLookupResult[] customerLookupResultArr = (CustomerLookupResult[]) objFromJson;
            if (!(customerLookupResultArr.length == 0)) {
                this.customerResults = (ArrayList) ArraysKt___ArraysKt.toCollection(customerLookupResultArr, new ArrayList());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private final void interpretSuggestionsRawResponse(String rawResponse) {
        try {
            String[] suggestionsArray = (String[]) this.gson.fromJson(new JSONArray(rawResponse).toString(), String[].class);
            Intrinsics.checkNotNullExpressionValue(suggestionsArray, "suggestionsArray");
            if (!(suggestionsArray.length == 0)) {
                this.customerSuggestions = (ArrayList) ArraysKt___ArraysKt.toCollection(suggestionsArray, new ArrayList());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
