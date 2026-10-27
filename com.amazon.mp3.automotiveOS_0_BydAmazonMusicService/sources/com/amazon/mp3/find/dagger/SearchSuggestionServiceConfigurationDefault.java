package com.amazon.mp3.find.dagger;

import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchSuggestionServiceConfigurationDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\u0007H\u0017J\b\u0010\f\u001a\u00020\u0007H\u0017J\b\u0010\r\u001a\u00020\u0007H\u0017J\b\u0010\u000e\u001a\u00020\u0005H\u0017J\b\u0010\u000f\u001a\u00020\u0003H\u0017R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/find/dagger/SearchSuggestionServiceConfigurationDefault;", "Lcom/amazon/music/search/suggestion/SearchSuggestionServiceConfiguration;", "searchURL", "Ljava/net/URL;", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", Splash.PARAMS_MUSIC_TERRITORY, "", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "(Ljava/net/URL;Lcom/amazon/hermes/RequestInterceptor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceId", "getDeviceType", "getMusicTerritory", "getRequestInterceptor", "getURL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchSuggestionServiceConfigurationDefault implements SearchSuggestionServiceConfiguration {
    private final String deviceId;
    private final String deviceType;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;
    private final URL searchURL;

    public SearchSuggestionServiceConfigurationDefault(URL searchURL, RequestInterceptor requestInterceptor, String musicTerritory, String deviceId, String deviceType) {
        Intrinsics.checkNotNullParameter(searchURL, "searchURL");
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        this.searchURL = searchURL;
        this.requestInterceptor = requestInterceptor;
        this.musicTerritory = musicTerritory;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
    @NonNull
    /* JADX INFO: renamed from: getURL, reason: from getter */
    public URL getSearchURL() {
        return this.searchURL;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
    @NonNull
    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
    @NonNull
    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
    @NonNull
    public String getDeviceId() {
        return this.deviceId;
    }

    @Override // com.amazon.music.search.suggestion.SearchSuggestionServiceConfiguration
    @NonNull
    public String getDeviceType() {
        return this.deviceType;
    }
}
