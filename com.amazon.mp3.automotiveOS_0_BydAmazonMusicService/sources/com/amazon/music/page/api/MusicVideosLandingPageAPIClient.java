package com.amazon.music.page.api;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.page.api.model.PageResponse;
import com.amazonaws.mobileconnectors.apigateway.annotation.Operation;
import com.amazonaws.mobileconnectors.apigateway.annotation.Parameter;
import com.amazonaws.mobileconnectors.apigateway.annotation.Service;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.perf.FirebasePerformance;

/* JADX INFO: loaded from: classes4.dex */
@Service(endpoint = "https://us-east-1.prod.api.videos.music.amazon.dev")
public interface MusicVideosLandingPageAPIClient {
    @Operation(method = FirebasePerformance.HttpMethod.GET, path = "/api/v1")
    PageResponse apiV1Get(@Parameter(location = "header", name = "x-amz-device-id") String str, @Parameter(location = "header", name = "x-amz-access-token") String str2, @Parameter(location = "header", name = "x-amz-customer-id") String str3, @Parameter(location = "header", name = "x-amz-device-type") String str4, @Parameter(location = SearchIntents.EXTRA_QUERY, name = Splash.PARAMS_MUSIC_TERRITORY) String str5, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "customerId") String str6, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "requestedContent") String str7, @Parameter(location = SearchIntents.EXTRA_QUERY, name = Splash.PARAMS_DEVICE_TYPE) String str8, @Parameter(location = SearchIntents.EXTRA_QUERY, name = Splash.PARAMS_DEVICE_ID) String str9, @Parameter(location = SearchIntents.EXTRA_QUERY, name = Splash.PARAMS_LOCALE) String str10, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "nextToken") String str11, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "featureFlag") String str12, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "widgetId") String str13, @Parameter(location = SearchIntents.EXTRA_QUERY, name = "isExplicitLanguageFilterEnabled") String str14);
}
