package com.amazon.mp3.find.dagger;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InstantSearchSuggestionServiceConfigurationDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\n\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/find/dagger/InstantSearchSuggestionServiceConfigurationDefault;", "Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestionServiceConfiguration;", "searchURL", "Ljava/net/URL;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", Splash.PARAMS_MUSIC_TERRITORY, "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "customerId", Splash.PARAMS_LOCALE, Splash.PARAMS_SESSION_ID, "(Ljava/net/URL;Lcom/amazon/hermes/RequestInterceptor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCustomerId", "getDeviceId", "getDeviceType", "getLocale", "getMusicTerritory", "getRequestInterceptor", "getSessionId", "getURL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InstantSearchSuggestionServiceConfigurationDefault implements InstantSearchSuggestionServiceConfiguration {
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private final String locale;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;
    private final URL searchURL;
    private final String sessionId;

    public InstantSearchSuggestionServiceConfigurationDefault(URL searchURL, RequestInterceptor requestInterceptor, String musicTerritory, String deviceId, String deviceType, String str, String locale, String str2) {
        Intrinsics.checkNotNullParameter(searchURL, "searchURL");
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(locale, "locale");
        this.searchURL = searchURL;
        this.requestInterceptor = requestInterceptor;
        this.musicTerritory = musicTerritory;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.customerId = str;
        this.locale = locale;
        this.sessionId = str2;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    /* JADX INFO: renamed from: getURL, reason: from getter */
    public URL getSearchURL() {
        return this.searchURL;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getDeviceId() {
        return this.deviceId;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getCustomerId() {
        return this.customerId;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getLocale() {
        return this.locale;
    }

    @Override // com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration
    public String getSessionId() {
        return this.sessionId;
    }
}
