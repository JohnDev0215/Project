package com.amazon.music.search.suggestion;

import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public interface SearchSuggestionServiceConfiguration {
    @NonNull
    String getDeviceId();

    @NonNull
    String getDeviceType();

    @NonNull
    String getMusicTerritory();

    @NonNull
    RequestInterceptor getRequestInterceptor();

    @NonNull
    URL getURL();
}
