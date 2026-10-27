package com.amazon.music.search.suggestion.instantsearch;

import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public interface InstantSearchSuggestionServiceConfiguration {
    String getCustomerId();

    @NonNull
    String getDeviceId();

    @NonNull
    String getDeviceType();

    @NonNull
    String getLocale();

    @NonNull
    String getMusicTerritory();

    @NonNull
    RequestInterceptor getRequestInterceptor();

    String getSessionId();

    @NonNull
    URL getURL();
}
